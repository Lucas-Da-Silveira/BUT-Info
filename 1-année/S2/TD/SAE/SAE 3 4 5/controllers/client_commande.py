#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import Flask, request, render_template, redirect, url_for, abort, flash, session, g
from datetime import datetime
from connexion_db import get_db

client_commande = Blueprint('client_commande', __name__,
                        template_folder='templates')


@client_commande.route('/client/commande/valide', methods=['POST'])
def client_commande_valide():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    sql = '''SELECT conditionnement_id AS id_declinaison_article, id_boisson as id_article, nom_boisson AS nom, quantite, (prix_boisson*facteur_prix) AS prix, id_conteneur, id_volume, libelle_conteneur, valeur_cl as libelle_volume
             FROM ligne_panier 
             LEFT JOIN conditionnement ON conditionnement.id_conditionnement =  ligne_panier.conditionnement_id 
             LEFT JOIN boisson ON boisson.id_boisson = conditionnement.boisson_id
             LEFT JOIN volume ON conditionnement.volume_id = volume.id_volume
             LEFT JOIN conteneur ON conditionnement.conteneur_id = conteneur.id_conteneur
             WHERE utilisateur_id = %s;'''
    mycursor.execute(sql, id_client)
    articles_panier = mycursor.fetchall()
    if len(articles_panier) >= 1:
        sql = '''SELECT SUM(prix_boisson*facteur_prix*quantite) AS prix_total 
                 FROM ligne_panier
                 LEFT JOIN conditionnement ON ligne_panier.conditionnement_id = conditionnement.id_conditionnement
                 LEFT JOIN volume ON conditionnement.volume_id = volume.id_volume
                 LEFT JOIN boisson ON conditionnement.boisson_id = boisson.id_boisson WHERE utilisateur_id = %s'''
        mycursor.execute(sql, id_client)
        prix_total = mycursor.fetchone()['prix_total']
    else:
        prix_total = None

    sql = '''
        SELECT a.id_adresse, a.nom_adresse AS nom, a.rue, a.code_postal, a.ville
        FROM adresse a
        INNER JOIN (
            SELECT adresse_livraison_id, MAX(date_achat) AS max_date
            FROM commande
            GROUP BY adresse_livraison_id
        ) c ON c.adresse_livraison_id = a.id_adresse
        WHERE a.utilisateur_id = %s AND a.valide = 1
        ORDER BY c.max_date DESC;
    '''
    mycursor.execute(sql, id_client)
    adresses = mycursor.fetchall()

    return render_template('client/boutique/panier_validation_adresses.html'
                           , adresses=adresses
                           , articles_panier=articles_panier
                           , prix_total= prix_total
                           , validation=1
                           )

@client_commande.route('/client/commande/add', methods=['POST'])
def client_commande_add():
    mycursor = get_db().cursor()

    id_adresse_livraison = request.form.get('id_adresse_livraison', '')
    if request.form.get('adresse_identique', '') == "adresse_identique":
        id_adresse_facturation = id_adresse_livraison
    else:
        id_adresse_facturation = request.form.get('id_adresse_facturation', '')

    id_client = session['id_user']
    sql = '''SELECT conditionnement_id, (prix_boisson*facteur_prix) AS prix, quantite 
             FROM ligne_panier
             LEFT JOIN conditionnement ON conditionnement.id_conditionnement = ligne_panier.conditionnement_id
             LEFT JOIN volume ON conditionnement.volume_id = volume.id_volume
             LEFT JOIN boisson ON conditionnement.boisson_id = boisson.id_boisson WHERE utilisateur_id = %s'''
    mycursor.execute(sql, id_client)
    items_ligne_panier = mycursor.fetchall()
    if items_ligne_panier is None or len(items_ligne_panier) < 1:
        flash(u'Pas d\'articles dans le ligne_panier', 'alert-warning')
        return redirect(url_for('client_index'))
    # https://pynative.com/python-mysql-transaction-management-using-commit-rollback/
    #a = datetime.strptime('my date', "%b %d %Y %H:%M")

    sql = '''INSERT INTO commande VALUES (NULL, current_time, %s, %s, %s, %s)'''
    mycursor.execute(sql, (id_client, 1, id_adresse_livraison, id_adresse_facturation))

    sql = '''SELECT last_insert_id() as last_insert_id'''
    mycursor.execute(sql)
    id_commande = mycursor.fetchone()['last_insert_id']

    for item in items_ligne_panier:
        sql = '''DELETE FROM ligne_panier WHERE conditionnement_id = %s AND utilisateur_id = %s'''
        mycursor.execute(sql, (item['conditionnement_id'], id_client))
        sql = "INSERT INTO ligne_commande VALUES (%s, %s, %s, %s)"
        mycursor.execute(sql, (id_commande, item['conditionnement_id'], item['prix'],item['quantite']))

    get_db().commit()
    flash(u'Commande ajoutée','alert-success')
    return redirect('/client/article/show')




@client_commande.route('/client/commande/show', methods=['get','post'])
def client_commande_show():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    sql = '''
        SELECT
            id_commande,
            etat_id,
            date_achat,
            (SELECT SUM(quantite) FROM ligne_commande WHERE commande_id = id_commande GROUP BY commande_id) AS nbr_articles,
            (SELECT SUM(prix*quantite) FROM ligne_commande WHERE commande_id = id_commande GROUP BY commande_id) AS prix_total,
            libelle
        FROM commande
        LEFT JOIN etat ON etat.id_etat = commande.etat_id
        WHERE utilisateur_id = %s
        ORDER BY commande.etat_id, commande.date_achat DESC;
    '''
    mycursor.execute(sql, (id_client))
    commandes = mycursor.fetchall()

    articles_commande = None
    commande_adresses = None
    id_commande = request.args.get('id_commande', None)

    if id_commande != None:
        sql = '''
            SELECT
                CONCAT(boisson.nom_boisson, ' ', libelle_conteneur, ' (', valeur_cl, 'cl)') AS nom,
                ligne_commande.quantite,
                ROUND(ligne_commande.prix, 2) AS prix,
                ROUND(ligne_commande.quantite * ligne_commande.prix, 2) AS prix_ligne
            FROM ligne_commande
            LEFT JOIN conditionnement ON ligne_commande.conditionnement_id = conditionnement.id_conditionnement
            LEFT JOIN conteneur ON conditionnement.conteneur_id = conteneur.id_conteneur
            LEFT JOIN volume ON conditionnement.volume_id = volume.id_volume
            LEFT JOIN boisson ON conditionnement.boisson_id = boisson.id_boisson
            LEFT JOIN commande ON ligne_commande.commande_id = commande.id_commande
            WHERE ligne_commande.commande_id = %s AND commande.utilisateur_id = %s;
        '''
        mycursor.execute(sql, (id_commande, id_client))
        articles_commande = mycursor.fetchall()

        # partie 2 : selection de l'adresse de livraison et de facturation de la commande selectionnée
        sql = '''
            SELECT
                adresse_livraison.nom_adresse AS nom_livraison,
                adresse_livraison.rue AS rue_livraison,
                adresse_livraison.code_postal AS code_postal_livraison,
                adresse_livraison.ville AS ville_livraison,
                IF (
                    adresse_livraison.nom_adresse = adresse_facturation.nom_adresse AND
                    adresse_livraison.rue = adresse_facturation.rue AND
                    adresse_livraison.code_postal = adresse_facturation.code_postal AND
                    adresse_livraison.ville = adresse_facturation.ville,
                "adresse_identique", "adresse_differente") AS adresse_identique,
                adresse_facturation.nom_adresse AS nom_facturation,
                adresse_facturation.rue AS rue_facturation,
                adresse_facturation.code_postal AS code_postal_facturation,
                adresse_facturation.ville AS ville_facturation
            FROM commande
            LEFT JOIN adresse adresse_livraison ON commande.adresse_livraison_id = adresse_livraison.id_adresse
            LEFT JOIN adresse adresse_facturation ON commande.adresse_facturation_id = adresse_facturation.id_adresse
            WHERE commande.utilisateur_id = %s AND commande.id_commande = %s;
        '''
        mycursor.execute(sql, (id_client, id_commande))
        commande_adresses = mycursor.fetchone()

    return render_template('client/commandes/show.html'
                           , commandes=commandes
                           , articles_commande=articles_commande
                           , commande_adresses=commande_adresses
                           )

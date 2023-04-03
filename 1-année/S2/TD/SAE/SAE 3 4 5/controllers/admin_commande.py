#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import Flask, request, render_template, redirect, flash, session

from connexion_db import get_db

admin_commande = Blueprint('admin_commande', __name__,
                        template_folder='templates')

@admin_commande.route('/admin')
@admin_commande.route('/admin/commande/index')
def admin_index():
    return render_template('admin/layout_admin.html')

@admin_commande.route('/admin/commande/show', methods=['get','post'])
def admin_commande_show():
    mycursor = get_db().cursor()
    admin_id = session['id_user']
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
        ORDER BY commande.etat_id, commande.date_achat DESC;
    '''
    mycursor.execute(sql)
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
            WHERE ligne_commande.commande_id = %s;
        '''
        mycursor.execute(sql, (id_commande))
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
            WHERE commande.id_commande = %s;
        '''
        mycursor.execute(sql, (id_commande))
        commande_adresses = mycursor.fetchone()

    return render_template('admin/commandes/show.html'
                           , commandes=commandes
                           , articles_commande=articles_commande
                           , commande_adresses=commande_adresses
                           )


@admin_commande.route('/admin/commande/valider', methods=['get','post'])
def admin_commande_valider():
    mycursor = get_db().cursor()
    commande_id = request.form.get('id_commande', None)

    if commande_id != None:
        sql = '''
            UPDATE commande
            SET etat_id = 2
            WHERE id_commande = %s
        '''
        mycursor.execute(sql, (commande_id))
        get_db().commit()

    return redirect('/admin/commande/show')
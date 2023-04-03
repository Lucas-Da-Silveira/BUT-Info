#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import render_template, session

from connexion_db import get_db

client_article = Blueprint('client_article', __name__,
                        template_folder='templates')

@client_article.route('/client/index')
@client_article.route('/client/article/show')              # remplace /client
def client_article_show():                                 # remplace client_index
    mycursor = get_db().cursor()
    id_client = session['id_user']

    sql = '''
            SELECT id_boisson AS id_article,
                   nom_boisson AS nom,
                   prix_boisson AS prix,
                   image AS image,
                   COUNT(id_conditionnement) as nb_declinaison,
                   (CASE COUNT(id_conditionnement)
                        WHEN 1 THEN (SELECT stock_conditionnement FROM conditionnement WHERE boisson_id = id_boisson LIMIT 1)
                        WHEN 0 THEN 0
                        ELSE 1
                   END) AS stock
            FROM boisson
            LEFT JOIN conditionnement ON conditionnement.boisson_id = boisson.id_boisson
    '''
    list_param = []
    condition_and = ""

    if "filter_word" in session or "filter_prix_min" in session or "filter_prix_max" in session or "filter_types" in session:
        sql += " WHERE "
    if "filter_word" in session:
        sql += " nom_boisson LIKE %s "
        recherche = "%" + session["filter_word"] + "%"
        list_param.append(recherche)
        condition_and = " AND "
    if "filter_prix_min" in session or "filter_prix_max" in session:
        if "filter_prix_min" in session and "filter_prix_max" not in session:
            sql += condition_and + " prix_boisson >= %s "
            list_param.append(session['filter_prix_min'])
        elif "filter_prix_min" not in session and "filter_prix_max" in session:
            sql += condition_and + " prix_boisson <= %s "
            list_param.append(session['filter_prix_max'])
        else:
            sql += condition_and + " prix_boisson BETWEEN %s AND %s "
            list_param.append(session['filter_prix_min'])
            list_param.append(session['filter_prix_max'])
        condition_and = " AND "
    if "filter_types" in session:
        sql += condition_and + "("
        last_item = session['filter_types'][-1]
        for item in session['filter_types']:
            sql += " type_eau_id = %s "
            if item != last_item:
                sql += " or "
            list_param.append(item)
        sql += ")"
    tuple_sql = tuple(list_param)

    sql += " GROUP BY id_boisson ORDER BY nb_declinaison DESC;"

    # sql3=''' prise en compte des commentaires et des notes dans le SQL    '''
    mycursor.execute(sql, tuple_sql)
    articles = mycursor.fetchall()

    sql = '''
        SELECT id_type_eau AS id_type_article, libelle_type_eau AS libelle
        FROM type_eau
        ORDER BY libelle
    '''
    mycursor.execute(sql)
    # pour le filtre
    types_article = mycursor.fetchall()

    articles_panier = []
    sql = '''SELECT conditionnement_id AS id_declinaison_article, 
                    id_boisson as id_article, 
                    nom_boisson AS nom, quantite, 
                    ROUND((prix_boisson*facteur_prix), 2) AS prix, 
                    id_conteneur, 
                    id_volume, 
                    libelle_conteneur, 
                    valeur_cl as libelle_volume
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
    return render_template('client/boutique/panier_article.html'
                           , articles=articles
                           , articles_panier=articles_panier
                           , prix_total=prix_total
                           , items_filtre=types_article
                           )

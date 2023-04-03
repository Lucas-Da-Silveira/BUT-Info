#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import request, render_template, redirect, abort, flash, session

from connexion_db import get_db

client_panier = Blueprint('client_panier', __name__,
                        template_folder='templates')


@client_panier.route('/client/panier/add', methods=['POST'])
def client_panier_add():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    id_boisson = request.form.get('id_article')
    quantite = request.form.get('quantite')
    id_declinaison_article=request.form.get('id_declinaison_article',None)

    # ajout dans le panier d'une déclinaison d'un article (si 1 declinaison : immédiat sinon => vu pour faire un choix
    if id_declinaison_article == None:
        sql = '''SELECT id_conditionnement as id_declinaison_article, 
                        conteneur_id, 
                        volume_id, 
                        stock_conditionnement as stock, 
                        id_volume, 
                        id_conteneur, 
                        libelle_conteneur, 
                        valeur_cl as libelle_volume,
                        FORMAT((prix_boisson*facteur_prix), 2) as prix
                 FROM conditionnement 
                 LEFT JOIN volume ON volume.id_volume = conditionnement.volume_id
                 LEFT JOIN conteneur ON conteneur.id_conteneur = conditionnement.conteneur_id
                 LEFT JOIN boisson ON conditionnement.boisson_id = boisson.id_boisson
                 WHERE boisson_id = %s'''
        mycursor.execute(sql, id_boisson)
        declinaisons = mycursor.fetchall()
        if len(declinaisons) == 1:
             id_declinaison_article = declinaisons[0]['id_declinaison_article']
        elif len(declinaisons) == 0:
             abort("pb nb de declinaison")
        else:
             sql = '''SELECT id_boisson as id_article, nom_boisson as nom, prix_boisson as prix, image FROM boisson WHERE  id_boisson = %s'''
             mycursor.execute(sql, (id_boisson))
             article = mycursor.fetchone()
             return render_template('client/boutique/declinaison_article.html'
                                        , declinaisons=declinaisons
                                        , quantite=quantite
                                        , article=article)

# ajout dans le panier d'un article
    sql = "SELECT * FROM ligne_panier WHERE conditionnement_id = %s AND utilisateur_id=%s"
    mycursor.execute(sql, (id_declinaison_article,id_client))
    article_panier = mycursor.fetchone()

    sql = "SELECT * FROM conditionnement WHERE  id_conditionnement = %s"
    mycursor.execute(sql ,id_declinaison_article)
    article = mycursor.fetchone()

    if not(article_panier is None) and article_panier['quantite']>=1:
        tuple_update = (quantite,id_client,id_declinaison_article)
        sql = ''' UPDATE ligne_panier SET quantite = quantite + %s WHERE utilisateur_id = %s AND conditionnement_id = %s '''
        mycursor.execute(sql, tuple_update)
    else:
        tuple_insert = (id_client,id_declinaison_article,quantite)
        sql = ''' INSERT INTO ligne_panier (utilisateur_id, conditionnement_id, quantite,date_ajout) VALUES (%s,%s,%s,current_timestamp)'''
        mycursor.execute(sql, tuple_insert)

    sql = '''UPDATE conditionnement SET stock_conditionnement = stock_conditionnement - %s WHERE id_conditionnement = %s'''
    mycursor.execute(sql, (quantite, id_declinaison_article))
    get_db().commit()
    return redirect('/client/article/show')

@client_panier.route('/client/panier/delete', methods=['POST'])
def client_panier_delete():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    id_boisson = request.form.get('id_article', '')
    id_declinaison_article = request.form.get('id_declinaison_article', None)
    quantite = 1
    sql = '''SELECT quantite FROM ligne_panier WHERE conditionnement_id = %s AND utilisateur_id = %s'''
    mycursor.execute(sql, (id_declinaison_article, id_client))
    article_panier = mycursor.fetchone()
    if not(article_panier is None) and article_panier['quantite'] > 1:
        sql = '''UPDATE ligne_panier SET quantite = quantite - 1 WHERE conditionnement_id = %s AND utilisateur_id = %s'''
        mycursor.execute(sql, (id_declinaison_article, id_client))
    if not(article_panier is None) and article_panier['quantite'] == 1:
        sql = '''DELETE FROM ligne_panier WHERE conditionnement_id = %s AND utilisateur_id = %s'''
        mycursor.execute(sql, (id_declinaison_article, id_client))
        pass
    #partie 2 : on supprime une déclinaison de l'article
    id_declinaison_article = request.form.get('id_declinaison_article', None)

    # sql = ''' DELETE FROM ligne_panier WHERE utilisateur_id = %s AND boisson_id = %s'''
    # article_panier=[]

    # if not(article_panier is None) and article_panier['quantite'] > 1:
    #     sql = ''' UPDATE ligne_panier SET quantite = quantite - 1 WHERE utilisateur_id = %s AND boisson_id = %s '''
    #     tuple_update = (id_client,id_boisson)
    #     mycursor.execute(sql, tuple_update)
    # else:
    #     sql = ''' DELETE FROM ligne_panier WHERE utilisateur_id = %s AND boisson_id = %s'''
    # tuple_delete = (id_client,id_boisson)
    # mycursor.execute(sql, tuple_delete)

    #mise à jour du stock de l'article disponible
    sql = '''UPDATE conditionnement SET stock_conditionnement = stock_conditionnement + 1 WHERE id_conditionnement = %s'''
    mycursor.execute(sql, id_declinaison_article)
    get_db().commit()
    return redirect('/client/article/show')





@client_panier.route('/client/panier/vider', methods=['POST'])
def client_panier_vider():
    mycursor = get_db().cursor()
    client_id = session['id_user']
    sql = '''SELECT conditionnement_id, quantite FROM ligne_panier WHERE utilisateur_id = %s'''
    mycursor.execute(sql, client_id)
    items_panier = mycursor.fetchall()
    print(items_panier)
    for item in items_panier:
        sql = '''DELETE FROM ligne_panier WHERE conditionnement_id = %s AND utilisateur_id = %s'''
        mycursor.execute(sql, (item['conditionnement_id'], client_id))

        sql2 = '''UPDATE conditionnement SET stock_conditionnement = stock_conditionnement + %s WHERE id_conditionnement = %s'''
        mycursor.execute(sql2, (item['quantite'], item['conditionnement_id']))
        get_db().commit()
    return redirect('/client/article/show')


@client_panier.route('/client/panier/delete/line', methods=['POST'])
def client_panier_delete_line():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    id_boisson = request.form.get('id_article')
    quantite = request.form.get('quantite')
    id_declinaison_article = request.form.get('id_declinaison_article')

    sql = ''' SELECT quantite FROM ligne_panier WHERE utilisateur_id = %s AND conditionnement_id = %s'''
    tuple_select = (id_client,id_declinaison_article)
    mycursor.execute(sql, tuple_select)
    quantite = mycursor.fetchone()['quantite']

    sql = ''' DELETE FROM ligne_panier WHERE utilisateur_id = %s AND conditionnement_id = %s'''
    tuple_delete = (id_client,id_declinaison_article)
    mycursor.execute(sql, tuple_delete)

    sql2=''' UPDATE conditionnement SET stock_conditionnement = stock_conditionnement + %s WHERE id_conditionnement = %s'''
    tuple_update = (quantite,id_declinaison_article)
    mycursor.execute(sql2, tuple_update)

    get_db().commit()
    return redirect('/client/article/show')

def is_float(s):
    try:
        float(s)
        return True
    except ValueError:
        pass

@client_panier.route('/client/panier/filtre', methods=['POST'])
def client_panier_filtre():
    filter_word = request.form.get('filter_word', None)
    filter_prix_min = request.form.get('filter_prix_min', None)
    filter_prix_max = request.form.get('filter_prix_max', None)
    filter_types = request.form.getlist('filter_types', None)

    if filter_word or filter_word == "":
        if len(filter_word) > 1:
            if filter_word.isalpha():
                session['filter_word'] = filter_word
            else:
                flash('Votre mot recherché doit uniquement être composé de lettres')
        else:
            if len(filter_word) == 1:
                flash('Votre mot recherché doit comporter au moins 2 lettres')
            else:
                session.pop('filter_word', None)

    if filter_prix_min or filter_prix_max:
        if filter_prix_min and filter_prix_min != '' and filter_prix_max and filter_prix_max != '':
            filter_prix_min = filter_prix_min.replace(',', '.')
            filter_prix_max = filter_prix_max.replace(',', '.')

            if is_float(filter_prix_min) and is_float(filter_prix_max):
                if float(filter_prix_min) < float(filter_prix_max):
                    session['filter_prix_min'] = filter_prix_min
                    session['filter_prix_max'] = filter_prix_max
                else:
                    flash('min < max')
            else:
                flash('min et max doivent êtres des numériques')
        else:
            if filter_prix_min or filter_prix_min != '':
                filter_prix_min = filter_prix_min.replace(',', '.')
                if is_float(filter_prix_min):
                        session['filter_prix_min'] = filter_prix_min
                else:
                    flash('min doit être un numérique')
                session.pop('filter_prix_max', None)
            if filter_prix_max or filter_prix_min != '':
                filter_prix_max = filter_prix_max.replace(',', '.')
                if is_float(filter_prix_max):
                    session['filter_prix_max'] = filter_prix_max
                else:
                    flash('max doit être un numérique')
                session.pop('filter_prix_min', None)

    if filter_types and filter_types != []:
        session['filter_types'] = filter_types

    return redirect('/client/article/show')


@client_panier.route('/client/panier/filtre/suppr', methods=['POST'])
def client_panier_filtre_suppr():
    session.pop('filter_types', None)
    session.pop('filter_word', None)
    session.pop('filter_prix_min', None)
    session.pop('filter_prix_max', None)

    return redirect('/client/article/show')

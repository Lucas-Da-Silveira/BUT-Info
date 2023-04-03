#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import Flask, request, render_template, redirect, url_for, abort, flash, session, g

from connexion_db import get_db

from controllers.client_liste_envies import client_historique_add

client_commentaire = Blueprint('client_commentaire', __name__,
                        template_folder='templates')


@client_commentaire.route('/client/article/details', methods=['GET'])
def client_article_details():
    mycursor = get_db().cursor()
    id_article =  request.args.get('id_article', None)
    id_client = session['id_user']

    ## partie 4
    client_historique_add(id_article, id_client)

    sql = ''' SELECT boisson.id_boisson AS id_article, AVG(note.note) AS moyenne_notes, COUNT(note.note) AS nb_notes,
        boisson.nom_boisson AS nom, boisson.prix_boisson AS prix, boisson.image AS image, boisson.description AS description
        FROM note
        LEFT JOIN boisson ON boisson.id_boisson = note.boisson_id
        WHERE boisson.id_boisson = %s
    '''
    mycursor.execute(sql, id_article)
    article = mycursor.fetchone()
    if article is None:
        abort(404, "pb id article")
    sql = ''' SELECT utilisateur.id_utilisateur ,utilisateur.nom, commentaire.boisson_id AS id_article, commentaire,date_publication
        FROM commentaire
        LEFT JOIN utilisateur ON commentaire.utilisateur_id = utilisateur.id_utilisateur
        LEFT JOIN boisson ON commentaire.boisson_id = boisson.id_boisson
        WHERE commentaire.boisson_id = %s 
    '''
    mycursor.execute(sql, (id_article))
    commentaires = mycursor.fetchall()
    sql = ''' SELECT ligne_commande.conditionnement_id, COUNT(*) as nb_commandes_article
    FROM ligne_commande
    LEFT JOIN conditionnement ON ligne_commande.conditionnement_id = conditionnement.id_conditionnement
    LEFT JOIN commande ON ligne_commande.commande_id = commande.id_commande
    LEFT JOIN utilisateur ON commande.utilisateur_id = utilisateur.id_utilisateur
    WHERE commande.utilisateur_id = %s AND ligne_commande.conditionnement_id = %s
    '''
    mycursor.execute(sql, (id_client, id_article))
    commandes_articles = mycursor.fetchone()
    sql = ''' SELECT note.note AS note
    FROM note
    LEFT JOIN boisson ON note.boisson_id = boisson.id_boisson
    LEFT JOIN utilisateur ON note.utilisateur_id = utilisateur.id_utilisateur
    WHERE note.utilisateur_id = %s AND note.boisson_id = %s
    '''
    mycursor.execute(sql, (id_client, id_article))
    note = mycursor.fetchone()
    print('note',note)
    if note:
        note=note['note']
    sql = ''' SELECT COUNT(commentaire.utilisateur_id) AS nb_commentaires_utilisateur
    FROM commentaire
    WHERE commentaire.utilisateur_id = %s AND commentaire.boisson_id = %s
    '''
    mycursor.execute(sql, (id_client, id_article))
    nb_commentaires = mycursor.fetchone()

    sql = ''' SELECT COUNT(utilisateur_id) AS nb_commentaires_total
    FROM commentaire
    WHERE commentaire.boisson_id = %s
    '''
    mycursor.execute(sql, (id_article))
    nb_commentaires_total = mycursor.fetchone()
    return render_template('client/article_info/article_details.html'
                           , article=article
                           , commentaires=commentaires
                           , commandes_articles=commandes_articles
                           , note=note
                           , nb_commentaires=nb_commentaires
                           , nb_commentaires_total=nb_commentaires_total
                           )

@client_commentaire.route('/client/commentaire/add', methods=['POST'])
def client_comment_add():
    mycursor = get_db().cursor()
    commentaire = request.form.get('commentaire', None)
    id_client = session['id_user']
    id_article = request.form.get('id_article', None)
    if commentaire == '':
        flash(u'Commentaire non prise en compte')
        return redirect('/client/article/details?id_article='+id_article)
    if commentaire != None and len(commentaire)>0 and len(commentaire) <3 :
        flash(u'Commentaires en plus de 2 caractères','alert-warning')
        return redirect('/client/article/details?id_article='+id_article)

    tuple_insert = (commentaire, id_client, id_article)
    print(tuple_insert)
    sql = ''' 
    INSERT INTO commentaire (commentaire, utilisateur_id, boisson_id, date_publication) 
    VALUES (%s, %s, %s, NOW())
    '''
    mycursor.execute(sql, tuple_insert)
    get_db().commit()
    return redirect('/client/article/details?id_article='+id_article)


@client_commentaire.route('/client/commentaire/delete', methods=['POST'])
def client_comment_detete():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    id_article = request.form.get('id_article', None)
    date_publication = request.form.get('date_publication', None)
    sql = ''' 
    DELETE FROM commentaire
    WHERE utilisateur_id = %s AND boisson_id = %s AND date_publication = %s
    '''
    tuple_delete=(id_client,id_article,date_publication)
    mycursor.execute(sql, tuple_delete)
    get_db().commit()
    return redirect('/client/article/details?id_article='+id_article)

@client_commentaire.route('/client/note/add', methods=['POST'])
def client_note_add():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    note = request.form.get('note', None)
    id_article = request.form.get('id_article', None)
    tuple_insert = (note, id_client, id_article)
    print(tuple_insert)
    sql = ''' 
    INSERT INTO note(note, utilisateur_id, boisson_id) VALUES (%s, %s,%s)
      '''
    mycursor.execute(sql, tuple_insert)
    get_db().commit()
    return redirect('/client/article/details?id_article='+id_article)

@client_commentaire.route('/client/note/edit', methods=['POST'])
def client_note_edit():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    note = request.form.get('note', None)
    id_article = request.form.get('id_article', None)
    tuple_update = (note, id_client, id_article)
    print(tuple_update)
    sql = ''' 
    UPDATE note SET note.note =%s
    WHERE note.utilisateur_id = %s AND note.boisson_id = %s
    '''
    mycursor.execute(sql, tuple_update)
    get_db().commit()
    return redirect('/client/article/details?id_article='+id_article)

@client_commentaire.route('/client/note/delete', methods=['POST'])
def client_note_delete():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    id_article = request.form.get('id_article', None)
    tuple_delete = (id_client, id_article)
    print(tuple_delete)
    sql = ''' 
    DELETE FROM note 
    WHERE note.utilisateur_id = %s AND note.boisson_id = %s
      '''
    mycursor.execute(sql, tuple_delete)
    get_db().commit()
    return redirect('/client/article/details?id_article='+id_article)
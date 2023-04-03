#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import Flask, request, render_template, redirect, abort, flash, session

from connexion_db import get_db

admin_commentaire = Blueprint('admin_commentaire', __name__,
                        template_folder='templates')


@admin_commentaire.route('/admin/article/commentaires', methods=['GET'])
def admin_article_details():
    mycursor = get_db().cursor()
    id_article =  request.args.get('id_article', None)
    sql = ''' SELECT utilisateur.id_utilisateur ,utilisateur.nom, commentaire.boisson_id AS id_article, commentaire,date_publication
        FROM commentaire
        LEFT JOIN utilisateur ON commentaire.utilisateur_id = utilisateur.id_utilisateur
        LEFT JOIN boisson ON commentaire.boisson_id = boisson.id_boisson
        WHERE commentaire.boisson_id = %s
      '''

    mycursor.execute(sql, (id_article))
    commentaires = mycursor.fetchall()
    sql = ''' 
    SELECT boisson.id_boisson AS id_article, AVG(note.note) AS moyenne_notes, COUNT(note.note) AS nb_notes,
    boisson.nom_boisson AS nom, boisson.prix_boisson AS prix, boisson.image AS image, boisson.description AS description
    FROM note
    LEFT JOIN boisson ON boisson.id_boisson = note.boisson_id
    WHERE boisson.id_boisson = %s
      '''
    mycursor.execute(sql, id_article)
    article = mycursor.fetchone()
    return render_template('admin/article/show_article_commentaires.html'
                           , commentaires=commentaires
                           , article=article
                           )

@admin_commentaire.route('/admin/article/commentaires/delete', methods=['POST'])
def admin_comment_delete():
    mycursor = get_db().cursor()
    id_utilisateur = request.form.get('id_utilisateur', None)
    id_article = request.form.get('id_article', None)
    date_publication = request.form.get('date_publication', None)
    sql = '''  
    DELETE FROM commentaire
    WHERE utilisateur_id = %s AND boisson_id = %s AND date_publication = %s
     '''
    tuple_delete=(id_utilisateur,id_article,date_publication)
    mycursor.execute(sql, tuple_delete)
    get_db().commit()
    return redirect('/admin/article/commentaires?id_article='+id_article)


@admin_commentaire.route('/admin/article/commentaires/repondre', methods=['POST','GET'])
def admin_comment_add():
    if request.method == 'GET':
        id_utilisateur = request.args.get('id_utilisateur', None)
        id_article = request.args.get('id_article', None)
        date_publication = request.args.get('date_publication', None)
        return render_template('admin/article/add_commentaire.html',id_utilisateur=id_utilisateur,id_article=id_article,date_publication=date_publication )

    mycursor = get_db().cursor()
    id_utilisateur = session['id_user']   #1 admin
    id_article = request.form.get('id_article', None)
    date_publication = request.form.get('date_publication', None)
    commentaire = request.form.get('commentaire', None)
    sql = '''
    INSERT INTO commentaire (utilisateur_id,boisson_id,commentaire,date_publication)
    VALUES (%s,%s,%s,NOW())
    '''
    tuple_insert=(id_utilisateur,id_article,commentaire)
    mycursor.execute(sql, tuple_insert)
    get_db().commit()
    return redirect('/admin/article/commentaires?id_article='+id_article)


@admin_commentaire.route('/admin/article/commentaires/valider', methods=['POST','GET'])
def admin_comment_valider():
    id_article = request.args.get('id_article', None)
    mycursor = get_db().cursor()
    sql = ''' 
    UPDATE commentaire
    SET valider = 1 
    WHERE boisson_id = %s
       '''
    mycursor.execute(sql, id_article)
    get_db().commit()
    return redirect('/admin/article/commentaires?id_article='+id_article)
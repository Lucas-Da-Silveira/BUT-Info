#! /usr/bin/python
# -*- coding:utf-8 -*-
import math
import os.path
from random import random

from flask import Blueprint
from flask import request, render_template, redirect, flash
#from werkzeug.utils import secure_filename

from connexion_db import get_db

admin_article = Blueprint('admin_article', __name__,
                          template_folder='templates')


@admin_article.route('/admin/article/show')
def show_article():
    mycursor = get_db().cursor()
    _sql_dec = '''SELECT id_boisson AS id_article, 
                    nom_boisson AS nom, 
                    type_eau_id AS type_article_id, 
                    prix_boisson AS prix, 
                    image, 
                    (SELECT IFNULL(SUM(stock_conditionnement), 0) FROM conditionnement WHERE boisson_id = id_boisson) AS stock,
                    (SELECT COUNT(*) FROM conditionnement WHERE boisson_id = id_boisson) AS nb_declinaisons
             FROM boisson
             ORDER BY stock, id_boisson;
    '''
    sql = '''SELECT id_boisson AS id_article, 
                    nom_boisson AS nom, 
                    type_eau_id AS type_article_id, 
                    prix_boisson AS prix, 
                    image, 
                    stock,
                    (SELECT libelle_type_eau FROM type_eau WHERE type_eau_id = id_type_eau) AS libelle,
                    (SELECT COUNT(*) FROM conditionnement WHERE boisson_id = id_boisson) AS nb_declinaisons
             FROM boisson
             ORDER BY id_boisson;
    '''
    mycursor.execute(_sql_dec)
    articles = mycursor.fetchall()
    return render_template('admin/article/show_article.html', articles=articles)


@admin_article.route('/admin/article/add', methods=['GET'])
def add_article():
    mycursor = get_db().cursor()

    sql = 'SELECT id_type_eau AS id_type_article, libelle_type_eau AS libelle FROM type_eau ORDER BY libelle_type_eau;'
    mycursor.execute(sql)
    type_article = mycursor.fetchall()

    sql = 'SELECT * FROM marque ORDER BY libelle_marque;'
    mycursor.execute(sql)
    marques = mycursor.fetchall()

    return render_template('admin/article/add_article.html'
                           ,types_article=type_article
                           ,marques = marques
                           #,couleurs=colors
                           #,tailles=tailles
                            )


@admin_article.route('/admin/article/add', methods=['POST'])
def valid_add_article():
    mycursor = get_db().cursor()

    nom = request.form.get('nom', '')
    type_article_id = request.form.get('type_article_id', '')
    prix = request.form.get('prix', '')
    marque = request.form.get('marque', '')
    image = request.files.get('image', '')

    if image:
        filename = 'img_upload'+ str(int(2147483647 * random())) + '.png'
        image.save(os.path.join('static/images/', filename))
    else:
        print("erreur")
        filename = "placeholder.png"

    sql = '''
        INSERT INTO boisson
        VALUES (NULL, %s, %s, %s, 0, %s, %s);
    '''

    tuple_add = (nom, prix, filename, type_article_id, marque)
    mycursor.execute(sql, tuple_add)
    get_db().commit()
    message = u'article ajouté, nom:' + nom + \
              '- type_article:' + type_article_id + \
              ' - prix:' + prix +\
              ' - marque:' + marque + \
              ' - image:' + filename
    flash(message, 'alert-success')

    return redirect('/admin/article/show')


@admin_article.route('/admin/article/delete', methods=['GET'])
def delete_article():
    id_article=request.args.get('id_article')
    mycursor = get_db().cursor()
    sql = '''SELECT COUNT(*) AS nb_declinaison 
             FROM conditionnement
             WHERE boisson_id = %s'''
    mycursor.execute(sql, id_article)
    nb_declinaison = mycursor.fetchone()
    if nb_declinaison['nb_declinaison'] > 0:
        message= u'il y a des declinaisons dans cet article : vous ne pouvez pas le supprimer'
        flash(message, 'alert-warning')
    else:
        sql = 'SELECT image FROM boisson WHERE id_boisson = %s'
        mycursor.execute(sql, id_article)
        article = mycursor.fetchone()
        print(article)
        image = article['image']

        sql = 'SELECT COUNT(*) as nb_note FROM note WHERE boisson_id = %s'
        mycursor.execute(sql, id_article)

        if mycursor.fetchone()['nb_note'] > 0:
            sql = 'DELETE FROM note WHERE boisson_id = %s'
            mycursor.execute(sql, id_article)

        sql = 'SELECT COUNT(*) as nb_commentaire FROM commentaire WHERE boisson_id = %s'
        mycursor.execute(sql, id_article)
        if mycursor.fetchone()['nb_commentaire'] > 0:
            sql = 'DELETE FROM commentaire WHERE boisson_id = %s'
            mycursor.execute(sql, id_article)

        sql = 'DELETE FROM boisson WHERE id_boisson = %s'
        mycursor.execute(sql, id_article)
        get_db().commit()
        if image != None and image != "placeholder.png" and os.path.exists('static/images/' + image):
            os.remove('static/images/' + image)

        print("un article supprimé, id :", id_article)
        message = u'un article supprimé, id : ' + id_article
        flash(message, 'alert-success')

    return redirect('/admin/article/show')


@admin_article.route('/admin/article/edit', methods=['GET'])
def edit_article():
    id_article=request.args.get('id_article')
    mycursor = get_db().cursor()
    sql = '''
        SELECT * FROM boisson WHERE id_boisson = %s;
    '''
    mycursor.execute(sql, id_article)
    article = mycursor.fetchone()

    sql = 'SELECT id_type_eau AS id_type_article, libelle_type_eau AS libelle FROM type_eau ORDER BY libelle_type_eau;'
    mycursor.execute(sql)
    types_article = mycursor.fetchall()

    sql = 'SELECT * FROM marque ORDER BY libelle_marque;'
    mycursor.execute(sql)
    marques = mycursor.fetchall()

    sql = '''SELECT id_conditionnement as id_declinaison_article, libelle_conteneur, valeur_cl as libelle_volume, stock_conditionnement as stock, boisson_id as article_id
             FROM conditionnement
             LEFT JOIN conteneur ON conteneur.id_conteneur = conditionnement.conteneur_id
             LEFT JOIN volume ON volume.id_volume = conditionnement.volume_id
             WHERE boisson_id = %s'''
    mycursor.execute(sql, id_article)
    declinaisons_article = mycursor.fetchall()

    return render_template('admin/article/edit_article.html'
                           ,article=article
                           ,types_article=types_article
                           ,marques=marques
                           ,declinaisons_article=declinaisons_article
                           )


@admin_article.route('/admin/article/edit', methods=['POST'])
def valid_edit_article():
    mycursor = get_db().cursor()
    nom = request.form.get('nom')
    id_article = request.form.get('id_article')
    image = request.files.get('image', '')
    type_article_id = request.form.get('type_article_id', '')
    prix = request.form.get('prix', '')
    stock = request.form.get('stock', '')
    marque = request.form.get('marque')

    sql = 'SELECT image FROM boisson WHERE id_boisson = %s;'
    mycursor.execute(sql, id_article)
    image_nom = mycursor.fetchone()
    image_nom = image_nom['image']
    if image:
        if image_nom != "" and image_nom is not None and os.path.exists(
                os.path.join(os.getcwd() + "static/images/", image_nom)):
            os.remove(os.path.join(os.getcwd() + "static/images/", image_nom))
        # filename = secure_filename(image.filename)
        if image:
            filename = 'img_upload_' + str(int(2147483647 * random())) + '.png'
            image.save(os.path.join('static/images/', filename))
            image_nom = filename

    sql = '''
        UPDATE boisson
        SET
            nom_boisson = %s,
            image = %s,
            prix_boisson = %s,
            type_eau_id = %s,
            stock = %s,
            marque_id = %s
        WHERE id_boisson = %s;
    '''
    mycursor.execute(sql, (nom, image_nom, prix, type_article_id, stock, marque, id_article))
    get_db().commit()

    if image_nom is None:
        image_nom = ''
    message = u'article modifié , nom:' + nom + '- type_article :' + type_article_id + ' - prix:' + prix  + ' - image:' + image_nom + ' - marque: ' + marque + ' - stock: ' + stock
    flash(message, 'alert-success')

    return redirect('/admin/article/show')







@admin_article.route('/admin/article/avis/<int:id>', methods=['GET'])
def admin_avis(id):
    mycursor = get_db().cursor()
    article=[]
    commentaires = {}
    return render_template('admin/article/show_avis.html'
                           , article=article
                           , commentaires=commentaires
                           )


@admin_article.route('/admin/comment/delete', methods=['POST'])
def admin_avis_delete():
    mycursor = get_db().cursor()
    article_id = request.form.get('idArticle', None)
    userId = request.form.get('idUser', None)

    return admin_avis(article_id)

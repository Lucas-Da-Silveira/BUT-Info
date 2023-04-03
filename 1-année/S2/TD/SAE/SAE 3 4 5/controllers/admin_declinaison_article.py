#! /usr/bin/python
# -*- coding:utf-8 -*-

from flask import Blueprint
from flask import request, render_template, redirect, flash
from connexion_db import get_db

admin_declinaison_article = Blueprint('admin_declinaison_article', __name__,
                         template_folder='templates')


@admin_declinaison_article.route('/admin/declinaison_article/add')
def add_declinaison_article():
    id_article=request.args.get('id_article')
    mycursor = get_db().cursor()
    sql = '''SELECT id_boisson as id_article, image FROM boisson WHERE id_boisson = %s'''
    mycursor.execute(sql, id_article)
    article=mycursor.fetchone()
    sql = '''SELECT id_conteneur, libelle_conteneur as libelle FROM conteneur'''
    mycursor.execute(sql)
    conteneurs=mycursor.fetchall()
    sql = '''SELECT id_volume, CONCAT(valeur_cl, 'cl') as libelle FROM volume'''
    mycursor.execute(sql)
    volumes=mycursor.fetchall()
    return render_template('admin/article/add_declinaison_article.html'
                           , article=article
                           , conteneurs=conteneurs
                           , volumes=volumes
                           )


@admin_declinaison_article.route('/admin/declinaison_article/add', methods=['POST'])
def valid_add_declinaison_article():
    mycursor = get_db().cursor()

    id_article = request.form.get('id_article')
    stock = request.form.get('stock')
    volume = request.form.get('volume')
    conteneur = request.form.get('conteneur')
    sql = '''SELECT * FROM conditionnement WHERE boisson_id = %s AND volume_id = %s AND conteneur_id = %s'''
    mycursor.execute(sql, (id_article, volume, conteneur))
    if mycursor.fetchone() == None:
        sql = '''INSERT INTO conditionnement(id_conditionnement, boisson_id, conteneur_id, volume_id, stock_conditionnement) VALUES (NULL, %s, %s, %s, %s)'''
        mycursor.execute(sql, (id_article, conteneur, volume, stock))
        get_db().commit()
    else:
        flash(u'Une déclinaison avec comme volume_id : ' + str(volume) + ', et conteneur_id : ' + str(conteneur) + ' pour l\'article id : ' + str(id_article) + ' existe déjà', 'alert-danger')
    return redirect('/admin/article/edit?id_article=' + id_article)


@admin_declinaison_article.route('/admin/declinaison_article/edit', methods=['GET'])
def edit_declinaison_article():
    id_declinaison_article = request.args.get('id_declinaison_article')
    mycursor = get_db().cursor()
    sql = '''SELECT id_conditionnement as id_declinaison_article, stock_conditionnement as stock, boisson_id as article_id, volume_id, conteneur_id, image as image_article
             FROM conditionnement
             LEFT JOIN boisson ON boisson.id_boisson = conditionnement.boisson_id
             WHERE id_conditionnement = %s'''
    mycursor.execute(sql, id_declinaison_article)
    declinaison_article=mycursor.fetchone()
    sql = '''SELECT id_conteneur, libelle_conteneur as libelle FROM conteneur'''
    mycursor.execute(sql)
    conteneurs=mycursor.fetchall()
    sql = '''SELECT id_volume, CONCAT(valeur_cl, 'cl') as libelle FROM volume'''
    mycursor.execute(sql)
    volumes=mycursor.fetchall()
    return render_template('admin/article/edit_declinaison_article.html'
                           , volumes=volumes
                           , conteneurs=conteneurs
                           , declinaison_article=declinaison_article
                           )


@admin_declinaison_article.route('/admin/declinaison_article/edit', methods=['POST'])
def valid_edit_declinaison_article():
    id_declinaison_article = request.form.get('id_declinaison_article','')
    id_article = request.form.get('id_article','')
    stock = request.form.get('stock','')
    volume_id = request.form.get('id_volume','')
    conteneur_id = request.form.get('id_conteneur','')
    mycursor = get_db().cursor()
    sql = '''UPDATE conditionnement SET stock_conditionnement = %s, volume_id = %s, conteneur_id = %s WHERE id_conditionnement = %s'''
    mycursor.execute(sql, (stock, volume_id, conteneur_id, id_declinaison_article))
    get_db().commit()
    message = u'declinaison_article modifié , id:' + str(id_declinaison_article) + '- stock :' + str(stock) + ' - volume_id:' + str(volume_id) + ' - conteneur_id:' + str(conteneur_id)
    flash(message, 'alert-success')
    return redirect('/admin/article/edit?id_article=' + str(id_article))


@admin_declinaison_article.route('/admin/declinaison_article/delete', methods=['GET'])
def admin_delete_declinaison_article():
    id_declinaison_article = request.args.get('id_declinaison_article','')
    id_article = request.args.get('id_article','')
    mycursor = get_db().cursor()

    sql = '''SELECT COUNT(*) as nb_commande FROM ligne_commande WHERE conditionnement_id = %s'''
    mycursor.execute(sql, id_declinaison_article)
    nb_commande = mycursor.fetchone()['nb_commande']
    if nb_commande > 0:
        message = u'il y a des commandes liées à cette déclinaison : vous ne pouvez pas la supprimer'
        flash(message, 'alert-warning')
    else:
        sql = '''DELETE FROM ligne_panier WHERE conditionnement_id = %s'''
        mycursor.execute(sql, id_declinaison_article)
        get_db().commit()
        sql = '''DELETE FROM conditionnement WHERE id_conditionnement = %s'''
        mycursor.execute(sql, id_declinaison_article)
        get_db().commit()
        flash(u'declinaison supprimée, id_declinaison_article : ' + str(id_declinaison_article),  'alert-success')
    return redirect('/admin/article/edit?id_article=' + str(id_article))
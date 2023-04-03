#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import Flask, request, render_template, redirect, flash, session

from connexion_db import get_db

admin_type_article = Blueprint('admin_type_article', __name__,
                        template_folder='templates')

@admin_type_article.route('/admin/type-article/show')
def show_type_article():
    mycursor = get_db().cursor()

    """ alternative avec jointures
    sql = '''
        SELECT
            type_eau.id_type_eau AS id_type_article,
            type_eau.libelle_type_eau AS libelle,
            COUNT(*) AS nbr_articles
        FROM boisson
        LEFT JOIN type_eau ON boisson.type_eau_id = type_eau.id_type_eau
        GROUP BY type_eau.id_type_eau
        ORDER BY type_eau.libelle_type_eau;
    '''
    """

    sql = '''
        SELECT
            id_type_eau AS id_type_article,
            libelle_type_eau AS libelle,
            (SELECT COUNT(*) FROM boisson WHERE type_eau_id = id_type_eau) AS nbr_articles
        FROM type_eau
        ORDER BY libelle_type_eau;
    '''
    mycursor.execute(sql)
    types_article = mycursor.fetchall()
    return render_template('admin/type_article/show_type_article.html', types_article=types_article)

@admin_type_article.route('/admin/type-article/add', methods=['GET'])
def add_type_article():
    return render_template('admin/type_article/add_type_article.html')

@admin_type_article.route('/admin/type-article/add', methods=['POST'])
def valid_add_type_article():
    libelle = request.form.get('libelle', '')
    tuple_insert = (libelle,)
    mycursor = get_db().cursor()
    sql = 'INSERT INTO type_eau VALUES (NULL, %s);'
    mycursor.execute(sql, tuple_insert)
    get_db().commit()

    message = u'type ajouté , libellé :'+libelle
    flash(message, 'alert-success')

    return redirect('/admin/type-article/show') #url_for('show_type_article')

@admin_type_article.route('/admin/type-article/delete', methods=['GET'])
def delete_type_article():
    id_type_article = request.args.get('id_type_article', '')
    mycursor = get_db().cursor()

    sql = 'SELECT COUNT(*) AS nb_articles FROM boisson WHERE type_eau_id = %s;'
    mycursor.execute(sql, id_type_article)
    query = mycursor.fetchone()

    if query['nb_articles'] != 0:
        flash(f'suppression type article impossible, il faut supprimer : {query["nb_articles"]} article(s) de ce type', 'alert-warning')
        return redirect('/admin/type-article/show')

    sql = 'DELETE FROM type_eau WHERE id_type_eau = %s;'
    mycursor.execute(sql, id_type_article)
    get_db().commit()

    flash(u'suppression type article , id : ' + id_type_article, 'alert-success')
    return redirect('/admin/type-article/show')

@admin_type_article.route('/admin/type-article/edit', methods=['GET'])
def edit_type_article():
    id_type_article = request.args.get('id_type_article', '')
    mycursor = get_db().cursor()
    sql = 'SELECT id_type_eau AS id_type_article, libelle_type_eau AS libelle FROM type_eau WHERE id_type_eau = %s;'
    mycursor.execute(sql, (id_type_article,))
    type_article = mycursor.fetchone()
    return render_template('admin/type_article/edit_type_article.html', type_article=type_article)

@admin_type_article.route('/admin/type-article/edit', methods=['POST'])
def valid_edit_type_article():
    libelle = request.form['libelle']
    id_type_article = request.form.get('id_type_article', '')
    tuple_update = (libelle, id_type_article)
    mycursor = get_db().cursor()
    sql = '''
        UPDATE type_eau
        SET libelle_type_eau = %s
        WHERE id_type_eau = %s;
    '''
    mycursor.execute(sql, tuple_update)
    get_db().commit()

    flash(u'type article modifié, id: ' + id_type_article + " libelle : " + libelle, 'alert-success')

    return redirect('/admin/type-article/show')








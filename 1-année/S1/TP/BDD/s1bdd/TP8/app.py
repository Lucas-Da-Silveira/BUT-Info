#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Flask, request, render_template, redirect, url_for, abort, flash, session, g

# application WSGI
# (interface de serveur web python)
# comportements et methodes d'un serveur web

app = Flask(__name__)    # instance de classe Flask (en parametre le nom du module)
app.secret_key = 'une cle(token) : grain de sel(any random string)'

import pymysql.cursors

def get_db():
    if 'db' not in g:
        g.db =  pymysql.connect(
            host="localhost",  # localhost sur les machines perso.
            user="ldasilve",
            password="1603",
            database="BDD_ldasilve",
            charset='utf8mb4',  # 2 attributs à ajouter
            cursorclass=pymysql.cursors.DictCursor  # 2 attributs à ajouter
        )
    return g.db

@app.teardown_appcontext
def teardown_db(exception):
    db = g.pop('db', None)
    if db is not None:
        db.close()

@app.route('/')
def show_accueil():
    return render_template('layout.html')

@app.route('/show/type-article')
def type_article_show():
    mycursor = get_db().cursor
    sql = "SELECT * FROM type_article ORDER BY libelle"
    mycursor.execute(sql)
    types_articles = mycursor.fetchall()
    return render_template('type_article/type_article_show.html', types_articles=types_articles)

@app.route('/add/type-article', methods=['GET'])
def type_article_add():
    return render_template('type_article/type_article_add.html')

@app.route('/add/type-article', methods=['POST'])
def type_article_valid_add():
    mycursor = get_db().cursor()
    libelle = request.form.get('libelle', '')
    tuple_insert = (libelle,)
    sql = "INSERT INTO type_article(libelle) VALUES (%s);"
    mycursor.execute(sql, tuple_insert)
    get_db().commit()
    message = u'type ajouté , libellé :'+libelle
    flash(message, 'alert-success')
    return redirect('/show/type-article')
   

@app.route('/delete/type-article', methods=['GET'])
def delete_type_article():
    mycursor = get_db().cursor()
    id_type_article = request.args.get('id', '')
    tuple_delete = (id_type_article,)
    sql = "DELETE FROM type_article WHERE id = %s;"
    mycursor.execute(sql, tuple_delete)
    get_db().commit()
    flash=(u'un type d\'article supprimé, id : ' + id_type_article)
    return redirect('/show/type-article')

@app.route('/edit/type-article', methods=['GET'])
def type_article_edit():
    mycursor = get_db().cursor()
    id_type_article = request.args.get('id', '')
    sql = "SELECT id,libelle FROM type_article WHERE id= %s"
    mycursor.execute(sql, (id_type_article,))
    type_article = mycursor.fetchone()
    return render_template('type_article/type_article_edit.html', type_article=type_article)

@app.route('/edit/type-article', methods=['POST'])
def type_article_valid_edit():
    libelle = request.form['libelle']
    id = request.form.get('id', '')
    print(u'type article modifié, id: ',id, " libelle :", libelle)
    message=u'type article modifié, id: ' + id + " libelle : " + libelle
    flash(message, 'alert-success')
    return redirect('/show/type-article')

@app.route('/show/article')
def article_show():
    # print(articles)
    return render_template('article/article_show.html', articles=articles)

@app.route('/add/article', methods=['GET'])
def article_add():
    return render_template('article/article_add.html', types_articles=types_articles)

@app.route('/add/article', methods=['POST'])
def article_add_valid():
    nom = request.form.get('nom', '')
    type_article_id = request.form.get('type_article_id', '')
    prix = request.form.get('prix', '')
    stock = request.form.get('stock', '')
    description = request.form.get('description', '')
    image = request.form.get('image', '')
    print(u'article ajouté , nom: ', nom, ' - type_article_id :', type_article_id, ' - prix:', prix, ' - stock:', stock, ' - description:', description, ' - image:', image)
    message = u'article ajouté , nom:'+nom + '- type_article_id :' + type_article_id + ' - prix:' + prix + ' - stock:'+  stock + ' - description:' + description + ' - image:' + image
    flash(message, 'alert-success')
    return redirect('/show/article')

@app.route('/delete/article', methods=['GET'])
def article_delete():
    id = request.args.get('id', '')
    message=u'un article supprimé, id : ' + id
    flash(message, 'alert-warning')
    return redirect('/show/article')

@app.route('/edit/article', methods=['GET'])
def article_edit():
    id = request.args.get('id', '')
    id=int(id)
    article = articles[id-1]
    return render_template('article/article_edit.html', article=article, types_articles=types_articles)

@app.route('/edit/article', methods=['POST'])
def article_valid_edit():
    nom = request.form['nom']
    id = request.form.get('id', '')
    type_article_id = request.form.get('type_article_id', '')
    prix = request.form.get('prix', '')
    stock = request.form.get('stock', '')
    description = request.form.get('description', '')
    image = request.form.get('image', '')
    print(u'article modifié , nom : ', nom, ' - type_article_id :', type_article_id, ' - prix:', prix, ' - stock:', stock, ' - description:', description, ' - image:', image)
    message = u'article modifié , nom:'+nom + '- type_article_id :' + type_article_id + ' - prix:' + prix + ' - stock:'+  stock + ' - description:' + description + ' - image:' + image
    flash(message, 'alert-success')
    return redirect('/show/article')

if __name__ == '__main__':
    app.run()

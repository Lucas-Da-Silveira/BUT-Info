#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Flask, request, render_template, redirect, url_for, abort, flash, session, g
import pymysql.cursors
import os
from werkzeug.utils import secure_filename

app = Flask(__name__)
app.secret_key = 'azerty'

# extensions acceptées pour les images
app.config['UPLOAD_EXTENSIONS'] = ['.jpg', '.jpeg', '.png']
app.config['UPLOAD_FOLDER'] = 'static/images'


# fonction pour se connecter à la bdd
def get_db():
    if 'db' not in g:
        g.db = pymysql.connect(
            host="localhost",
            # à modifier les kheys
            user="ldasilve",
            # à modifier les kheys
            password="1603",
            # à modifier les kheys
            database="BDD_ldasilve",
            charset='utf8mb4',
            cursorclass=pymysql.cursors.DictCursor
        )
    return g.db


# fonction pour fermer la connexion à chaque requête HTTP pour éviter des memory leaks
@app.teardown_appcontext
def teardown_db(exception):
    db = g.pop('db', None)
    if db is not None:
        db.close()


# affiche types de ski
@app.route('/')
def show_main():
    return render_template('index.html')


@app.route('/query_client')
def query_db():
    code_client = request.args.get('code_client')
    if code_client:
        cursor = get_db().cursor()
        sql = '''
            SELECT nom, prenom, adresse, codePostal, ville FROM Client WHERE codeClient = %s
        '''
        cursor.execute(sql, code_client)
        res = cursor.fetchone()
        if not res:
            res = {}
        return res
    else:
        return 'erreur'

@app.route('/query_article')
def query_article():
    cursor = get_db().cursor()
    sql = '''
        SELECT codePrestation, prestation FROM Prestation;
    '''
    cursor.execute(sql)
    return cursor.fetchall()

@app.route('/query_specific_article')
def query_specific_article():
    id_article = request.args.get('id_article')
    if not id_article:
        id_article = "1"

    cursor = get_db().cursor()
    sql = '''
        SELECT * FROM Prestation WHERE codePrestation = %s
    '''
    cursor.execute(sql, id_article)
    return cursor.fetchone()

if __name__ == '__main__':
    app.run()

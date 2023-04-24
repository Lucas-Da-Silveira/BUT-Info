from connexion_db import get_db
from flask import *


def find_auteurs():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.nom, a.prenom, a.id_auteur, COUNT(o.id_oeuvre) AS nbrOeuvre
            FROM auteur a
            LEFT JOIN oeuvre o ON o.auteur_id = a.id_auteur
            GROUP BY a.id_auteur
            ORDER BY a.nom, a.prenom;
        '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 1_1')


def find_auteur_nbOeuvres(id_auteur):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT COUNT(*) AS nb_oeuvres FROM oeuvre WHERE auteur_id = %s;'
        cursor.execute(sql, id_auteur)
        res_nb_oeuvres = cursor.fetchone()
        if 'nb_oeuvres' in res_nb_oeuvres.keys():
            nb_oeuvres = res_nb_oeuvres['nb_oeuvres']
            return nb_oeuvres
    except ValueError:
        abort(400, 'erreur requete 1_6')


def find_one_auteur(id_auteur):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT id_auteur, nom, prenom FROM auteur WHERE id_auteur = %s;'
        cursor.execute(sql, id_auteur)
        return cursor.fetchone()
    except ValueError:
        abort(400, 'erreur requete 1_4')


def find_auteurs_dropdown():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = ''' SELECT 'requete3_6' FROM DUAL '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 3_6')


def auteur_insert(nom, prenom):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'INSERT INTO auteur(nom, prenom) VALUES (%s, %s);'
        cursor.execute(sql, (nom, prenom))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 1_2')


def auteur_update(id_auteur, nom, prenom):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'UPDATE auteur SET nom = %s, prenom = %s WHERE id_auteur = %s;'
        cursor.execute(sql, (nom, prenom, id_auteur))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 1_5')


def auteur_delete(id_auteur):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'DELETE FROM auteur WHERE id_auteur = %s;'
        cursor.execute(sql, id_auteur)
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 1_3')

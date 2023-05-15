from connexion_db import get_db
from flask import *


def find_oeuvres():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT
                a.nom AS nom,
                o.titre AS titre,
                o.id_oeuvre AS id_oeuvre,
                IFNULL(o.date_parution, '') AS date_parution_en,
                o.photo AS photo,
                COUNT(e.id_exemplaire) AS nb_exemplaire,
                COUNT(e2.id_exemplaire) AS nb_exemp_dispo,
                IFNULL(DATE_FORMAT(o.date_parution, '%d/%m/%Y'), '') AS date_parution
            FROM oeuvre o
            LEFT JOIN exemplaire e on o.id_oeuvre = e.oeuvre_id
            LEFT JOIN exemplaire e2 on e.id_exemplaire = e2.id_exemplaire
            AND e2.id_exemplaire NOT IN (SELECT emprunt.exemplaire_id FROM emprunt WHERE emprunt.date_retour IS NULL)
            LEFT JOIN auteur a on o.auteur_id = a.id_auteur
            GROUP BY o.id_oeuvre
            ORDER BY a.nom, o.titre;
        '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 3_1')


def find_oeuvre_nbExemplaires(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT COUNT(*) AS nb_exemplaires FROM exemplaire WHERE oeuvre_id = %s;'
        cursor.execute(sql, (id))
        res_nb_exemplaires = cursor.fetchone()
        if 'nb_exemplaires' in res_nb_exemplaires.keys():
            nb_exemplaires = res_nb_exemplaires['nb_exemplaires']
            return nb_exemplaires
        else:
            abort(500, 'erreur requete 3_7')
    except ValueError:
        abort(400, 'erreur requete 3_7')


def find_one_oeuvre(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT * FROM oeuvre WHERE id_oeuvre = %s;'
        cursor.execute(sql, (id))
        return cursor.fetchone()
    except ValueError:
        abort(400, 'erreur requete 3_4')


def oeuvre_insert(titre, dateParution, photo, idAuteur):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'INSERT INTO oeuvre(titre, date_parution, photo, auteur_id) VALUES(%s, %s, %s, %s);'
        cursor.execute(sql, (titre, dateParution, photo, idAuteur))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 3_2')


def oeuvre_update(titre, idAuteur, dateParution, photo, id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'UPDATE oeuvre SET titre = %s, auteur_id = %s, date_parution = %s, photo = %s WHERE id_oeuvre = %s;'
        cursor.execute(sql, (titre, idAuteur, dateParution, photo, id))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 3_5')


def oeuvre_delete(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'DELETE FROM oeuvre WHERE id_oeuvre = %s;'
        cursor.execute(sql, (id))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 3_3')

from connexion_db import get_db
from flask import *


def find_details_oeuvre_exemplaires(id_oeuvre):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT
                a.nom,
                o.titre,
                o.id_oeuvre,
                o.date_parution,
                COUNT(e.id_exemplaire) AS nb_exemplaire,
                COUNT(e2.id_exemplaire) AS nb_exemp_dispo
            FROM exemplaire e
            LEFT JOIN oeuvre o ON e.oeuvre_id = o.id_oeuvre
            LEFT JOIN exemplaire e2 ON e.id_exemplaire = e2.id_exemplaire
            AND e2.id_exemplaire NOT IN (SELECT emprunt.exemplaire_id FROM emprunt WHERE emprunt.date_retour IS NULL)
            LEFT JOIN auteur a ON a.id_auteur = o.auteur_id
            WHERE o.id_oeuvre = %s;
        '''
        cursor.execute(sql, (id_oeuvre))
        return cursor.fetchone()
    except ValueError:
        abort(400, 'erreur requete 4_1')


def find_exemplaires_oeuvre(id_oeuvre):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT
                e.*,
                o.titre,
                o.id_oeuvre,
                o.date_parution,
                IF(COUNT(e2.id_exemplaire)=0, 'abs', 'present') AS present
            FROM exemplaire e
            LEFT JOIN oeuvre o ON e.oeuvre_id = o.id_oeuvre
            LEFT JOIN exemplaire e2 ON e.id_exemplaire = e2.id_exemplaire
            AND e2.id_exemplaire NOT IN (SELECT emprunt.exemplaire_id FROM emprunt WHERE emprunt.date_retour IS NULL)
            WHERE o.id_oeuvre = %s
            GROUP BY e.id_exemplaire
            ORDER BY e.id_exemplaire DESC;
        '''
        cursor.execute(sql, (id_oeuvre))
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 4_2')


def find_exemplaire_nbEmprunts(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT COUNT(*) AS nb_emprunts FROM emprunt WHERE exemplaire_id = %s;'
        cursor.execute(sql, (id))
        res_nb_emprunts = cursor.fetchone()
        if 'nb_emprunts' in res_nb_emprunts.keys():
            nb_emprunts = res_nb_emprunts['nb_emprunts']
            return nb_emprunts
        else:
            abort(500, 'erreur requete 4_7')
    except ValueError:
        abort(400, 'erreur requete 4_7')


def find_edit_one_exemplaire(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT * FROM exemplaire WHERE id_exemplaire = %s;'
        cursor.execute(sql, (id))
        return cursor.fetchone()
    except ValueError:
        abort(400, 'erreur requete 4_11')


def find_id_oeuvre_exemplaire(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT oeuvre_id FROM exemplaire WHERE id_exemplaire = %s;'
        cursor.execute(sql, (id))
        oeuvre = cursor.fetchone()
        oeuvre_id = str(oeuvre['oeuvre_id'])
        return oeuvre_id
    except ValueError:
        abort(400, 'erreur requete 4_9')


def find_add_exemplaire_info_oeuvre(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.nom, o.titre, o.id_oeuvre, o.date_parution
            FROM oeuvre o
            LEFT JOIN auteur a ON o.auteur_id = a.id_auteur
            WHERE o.id_oeuvre = %s;
        '''
        cursor.execute(sql, (id))
        oeuvre = cursor.fetchone()
        return oeuvre
    except ValueError:
        abort(400, 'erreur requete 4_3')


def find_edit_details_oeuvre_id_exemplaire(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.nom, o.titre, o.date_parution, o.id_oeuvre
            FROM exemplaire e
            LEFT JOIN oeuvre o ON e.oeuvre_id = o.id_oeuvre
            LEFT JOIN auteur a ON o.auteur_id = a.id_auteur
            WHERE e.id_exemplaire = %s;
        '''
        cursor.execute(sql, (id))
        exemplaire = cursor.fetchone()
        return exemplaire
    except ValueError:
        abort(400, 'erreur requete 4_10')


def exemplaire_insert(etat, date_achat, prix, oeuvre_id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'INSERT INTO exemplaire(etat, date_achat, prix, oeuvre_id) VALUES(%s, %s, %s, %s);'
        cursor.execute(sql, (etat, date_achat, prix, oeuvre_id))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 4_5')


def exemplaire_update(noOeuvre, etat, dateAchat, prix, id_exemplaire):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'UPDATE exemplaire SET oeuvre_id = %s, etat = %s, date_achat = %s, prix = %s WHERE id_exemplaire = %s;'
        cursor.execute(sql, (noOeuvre, etat, dateAchat, prix, id_exemplaire))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 4_12')


def exemplaire_delete(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'DELETE FROM exemplaire WHERE id_exemplaire = %s;'
        cursor.execute(sql, (id))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 4_8')

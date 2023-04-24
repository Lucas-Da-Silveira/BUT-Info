from connexion_db import get_db
from flask import *


def find_adherents():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT
                a.nom,
                a.adresse,
                a.date_paiement,
                a.id_adherent,
                COUNT(e.adherent_id) AS nbr_emprunt,
                DATE_ADD(a.date_paiement, INTERVAL 1 YEAR) AS date_paiement_futur,
                IF(CURRENT_DATE() > DATE_ADD(a.date_paiement, INTERVAL 1 YEAR), 1, 0) AS retard,
                IF(CURRENT_DATE() > DATE_ADD(a.date_paiement, INTERVAL 11 MONTH), 1, 0) AS retardProche
            FROM adherent a
            LEFT JOIN emprunt e ON e.adherent_id = a.id_adherent AND e.date_retour IS NULL
            GROUP BY a.id_adherent
            ORDER BY a.nom;
        '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 2_1')


def find_adherent_nbEmprunts(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT COUNT(emprunt.adherent_id) AS nb_emprunts
            FROM adherent
            LEFT JOIN emprunt ON emprunt.adherent_id = adherent.id_adherent
            WHERE adherent.id_adherent = %s;
        '''
        cursor.execute(sql, id)
        res_nb_emprunts = cursor.fetchone()
        if 'nb_emprunts' in res_nb_emprunts.keys():
            nb_emprunts = res_nb_emprunts['nb_emprunts']
            return nb_emprunts
    except ValueError:
        abort(400, 'erreur requete 2_6')


def find_one_adherent(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT id_adherent, nom, adresse, date_paiement FROM adherent WHERE id_adherent = %s;'
        cursor.execute(sql, id)
        return cursor.fetchone()
    except ValueError:
        abort(400, 'erreur requete')


def adherent_insert(nom, adresse, datePaiement):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'INSERT INTO adherent VALUES (NULL, %s, %s, %s);'
        cursor.execute(sql, (nom, adresse, datePaiement))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 1_2')


def adherent_update(nom, adresse, datePaiement, id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'UPDATE adherent SET nom = %s, adresse = %s, date_paiement = %s WHERE id_adherent = %s;'
        cursor.execute(sql, (nom, adresse, datePaiement, id))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 2_5')


def adherent_delete(id):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'DELETE FROM adherent WHERE id_adherent = %s;'
        cursor.execute(sql, id)
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 2_3')

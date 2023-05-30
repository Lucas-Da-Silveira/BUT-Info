from flask import *

from connexion_db import get_db


def find_adherents_emprunter():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.id_adherent, a.nom, a.date_paiement
            FROM adherent a
            WHERE
                DATE_ADD(a.date_paiement, INTERVAL 1 YEAR) > CURRENT_DATE()
                AND (SELECT COUNT(*) FROM emprunt WHERE emprunt.date_retour IS NULL AND emprunt.adherent_id = a.id_adherent) < 6
                AND (SELECT COUNT(*) FROM emprunt WHERE DATE_ADD(emprunt.date_emprunt, INTERVAL 90 DAY) > CURRENT_DATE() AND emprunt.adherent_id = a.id_adherent) = 0
            ORDER BY a.nom;
        '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_1')


def find_adherents_rendre():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.id_adherent, a.nom
            FROM adherent a
            WHERE (SELECT COUNT(*) FROM emprunt WHERE emprunt.date_retour IS NULL AND emprunt.adherent_id = a.id_adherent) > 0
            ORDER BY a.nom;
        '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_2')


def find_adherent_nbre_emprunt_retardDatePaiment(id_adherent):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT COUNT(e.adherent_id) AS nbr_emprunt, COUNT(CASE WHEN DATE_ADD(e.date_emprunt, INTERVAL 90 DAY) <= CURRENT_DATE() THEN 1 END) AS retard
            FROM adherent a
            LEFT JOIN emprunt e ON a.id_adherent = e.adherent_id AND e.date_retour IS NULL
            WHERE a.id_adherent = %s;
        '''
        cursor.execute(sql, (id_adherent))
        return cursor.fetchone()
    except ValueError:
        abort(400, 'erreur requete 5_3')


def find_adherent_data(id_adherent):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT * FROM adherent WHERE id_adherent = %s;'
        cursor.execute(sql, (id_adherent))
        return cursor.fetchone()
    except ValueError:
        abort(400, 'erreur requete 5_7')


def find_exemplaire_oeuvre_disponible():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.nom, o.titre, o.id_oeuvre, e.id_exemplaire
            FROM exemplaire e
            LEFT JOIN oeuvre o ON e.oeuvre_id = o.id_oeuvre
            LEFT JOIN auteur a ON o.auteur_id = a.id_auteur
            WHERE e.id_exemplaire NOT IN (
                SELECT emprunt.exemplaire_id
                FROM emprunt
                WHERE emprunt.date_retour IS NULL AND emprunt.exemplaire_id = e.id_exemplaire
            )
            ORDER BY a.nom;
        '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_4')


def find_emprunt_data_adherent(id_adherent):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.id_adherent, ex.id_exemplaire, o.titre, a.nom, emp.date_emprunt, emp.date_retour, DATEDIFF(CURRENT_DATE(), emp.date_emprunt) AS nb_jours_emprunt
            FROM emprunt emp
            LEFT JOIN adherent a ON emp.adherent_id = a.id_adherent
            LEFT JOIN exemplaire ex ON emp.exemplaire_id = ex.id_exemplaire
            LEFT JOIN oeuvre o ON ex.oeuvre_id = o.id_oeuvre
            WHERE emp.date_retour IS NULL AND emp.adherent_id = %s
            ORDER BY emp.date_emprunt DESC;
        '''
        cursor.execute(sql, (id_adherent))
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_5')


def find_select_emprunt_split(list_emprunts_split):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT * FROM emprunt WHERE adherent_id = %s AND exemplaire_id = %s AND date_emprunt = %s;'
        cursor.execute(sql, list_emprunts_split)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_9')


def find_adherents_dropdown():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'SELECT id_adherent, nom FROM adherent ORDER BY nom;'
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_12')


def find_adherents_select_emprunts(idAdherent):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT a.id_adherent, ex.id_exemplaire, o.titre, a.nom, emp.date_emprunt, emp.date_retour, DATEDIFF(CURRENT_DATE(), emp.date_emprunt) AS nb_jours_emprunt
            FROM emprunt emp
            LEFT JOIN adherent a ON emp.adherent_id = a.id_adherent
            LEFT JOIN exemplaire ex ON emp.exemplaire_id = ex.id_exemplaire
            LEFT JOIN oeuvre o ON ex.oeuvre_id = o.id_oeuvre
        '''
        if idAdherent.isnumeric():
            sql += " WHERE a.id_adherent = " + str(idAdherent)
        sql += " ORDER BY a.nom ASC, emp.date_emprunt DESC;"
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_11')


def find_bilan_emprunt():
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            SELECT
                a.id_adherent,
                ex.id_exemplaire,
                o.titre,
                a.nom,
                emp.date_emprunt,
                emp.date_retour,
                DATEDIFF(CURRENT_DATE(), emp.date_emprunt) AS nb_jours_emprunt,
                DATEDIFF(CURRENT_DATE(), DATE_ADD(emp.date_emprunt, INTERVAL 90 DAY)) AS retard,
                DATE_ADD(emp.date_emprunt, INTERVAL 90 DAY) AS date_rendu_theorique,
                IF(CURRENT_DATE() > DATE_ADD(emp.date_emprunt, INTERVAL 90 DAY), 1, 0) AS flag_retard,
                IF(CURRENT_DATE() > DATE_ADD(emp.date_emprunt, INTERVAL 120 DAY), 1, 0) AS flag_penalite,
                IF(
                    CURRENT_DATE() > DATE_ADD(emp.date_emprunt, INTERVAL 120 DAY),
                    IF(
                        DATEDIFF(CURRENT_DATE(), DATE_ADD(emp.date_emprunt, INTERVAL 120 DAY)) * 0.5 > 25,
                        25,
                        DATEDIFF(CURRENT_DATE(), DATE_ADD(emp.date_emprunt, INTERVAL 120 DAY)) * 0.5
                    ),
                    0
                ) AS dette
            FROM emprunt emp
            LEFT JOIN adherent a ON emp.adherent_id = a.id_adherent
            LEFT JOIN exemplaire ex ON emp.exemplaire_id = ex.id_exemplaire
            LEFT JOIN oeuvre o ON ex.oeuvre_id = o.id_oeuvre
            WHERE emp.date_retour IS NULL AND DATEDIFF(CURRENT_DATE(), DATE_ADD(emp.date_emprunt, INTERVAL 90 DAY)) > 0
            ORDER BY emp.date_emprunt, a.nom, ex.id_exemplaire, o.titre;
        '''
        cursor.execute(sql)
        return cursor.fetchall()
    except ValueError:
        abort(400, 'erreur requete 5_13')


def emprunt_insert(idAdherent, noExemplaire, dateEmprunt):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'INSERT INTO emprunt(adherent_id, exemplaire_id, date_emprunt,date_retour) VALUES(%s, %s, %s, NULL);'
        cursor.execute(sql, (idAdherent, noExemplaire, dateEmprunt))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 5_6')


def emprunt_update(dateRetour, idAdherent, dateEmprunt, noExemplaire):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = '''
            UPDATE emprunt
            SET date_retour = %s
            WHERE adherent_id = %s AND date_emprunt = %s AND exemplaire_id = %s;
        '''
        cursor.execute(sql, (dateRetour, idAdherent, dateEmprunt, noExemplaire))
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 5_8')


def emprunt_delete(list_emprunts_split):
    connection = get_db()
    try:
        cursor = connection.cursor()
        sql = 'DELETE FROM emprunt WHERE adherent_id = %s AND exemplaire_id = %s AND date_emprunt = %s;'
        cursor.execute(sql, list_emprunts_split)
        connection.commit()
    except ValueError:
        abort(400, 'erreur requete 5_10')

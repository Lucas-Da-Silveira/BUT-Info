#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import Flask, request, render_template, redirect, abort, flash, session

from connexion_db import get_db

admin_dataviz = Blueprint('admin_dataviz', __name__,
                          template_folder='templates')


@admin_dataviz.route('/admin/dataviz/etat1')
def show_type_article_stock():
    mycursor = get_db().cursor()
    sql = '''
            SELECT CONCAT(libelle_type_eau, COALESCE(CONCAT(' ', valeur_cl, 'cl'), ' (autres volumes)')) as libelle, 
                   COALESCE(SUM(stock_conditionnement), 0) as stock,
                   COALESCE(ROUND(SUM(stock_conditionnement*prix_boisson*facteur_prix), 2), 0) as prix_stock_total,
                   COUNT(id_boisson) as nb_boisson,
                   COUNT(id_conditionnement) as nb_conditionnement
            FROM type_eau
            LEFT JOIN boisson ON boisson.type_eau_id = type_eau.id_type_eau
            LEFT JOIN conditionnement ON conditionnement.boisson_id = boisson.id_boisson
            LEFT JOIN volume ON volume.id_volume = conditionnement.volume_id
            GROUP BY libelle
            ORDER BY prix_stock_total DESC
           '''
    mycursor.execute(sql)
    datas_show = mycursor.fetchall()
    labels = [str(row['libelle']) for row in datas_show]
    values = [int(row['stock']) for row in datas_show]
    prices = [float(row['prix_stock_total']) for row in datas_show]

    # sql = '''
    #         
    #        '''
    return render_template('admin/dataviz/dataviz_etat_1.html'
                           , datas_show=datas_show
                           , labels=labels
                           , values=values
                           , prices=prices)


@admin_dataviz.route('/admin/dataviz/etat2')
def show_note_commentaire():
    mycursor = get_db().cursor()
    sql = ''' SELECT boisson.nom_boisson AS libelle, 
            COUNT(commande.id_commande) AS nb_commandes,
            COUNT(commentaire) AS nb_commentaires,
            COUNT(note) AS nb_notes,
            ROUND(AVG(note), 2) AS note_moyenne
            FROM boisson
            LEFT JOIN conditionnement ON conditionnement.boisson_id = boisson.id_boisson
            LEFT JOIN ligne_commande ON ligne_commande.conditionnement_id = conditionnement.id_conditionnement
            LEFT JOIN commande ON commande.id_commande = ligne_commande.commande_id
            LEFT JOIN commentaire ON commentaire.boisson_id = boisson.id_boisson
            LEFT JOIN note ON note.boisson_id = boisson.id_boisson
            GROUP BY boisson.id_boisson
            ORDER BY note_moyenne DESC
            
           '''
    mycursor.execute(sql)
    datas_show = mycursor.fetchall()
    labels = [str(row['libelle']) for row in datas_show]
    nbr_commandes = [int(row['nb_commandes']) for row in datas_show]
    comments = [int(row['nb_commentaires']) for row in datas_show]
    notes = [int(row['nb_notes']) for row in datas_show]
    notes_moy = [float(row['note_moyenne']) for row in datas_show]

    sql = ''' SELECT ROUND(AVG(note), 2) AS note_moyenne,
            COUNT(note) AS nb_notes
            FROM note
            LEFT JOIN boisson ON boisson.id_boisson = note.boisson_id
            LEFT JOIN conditionnement ON conditionnement.boisson_id = boisson.id_boisson
            LEFT JOIN ligne_commande ON ligne_commande.conditionnement_id = conditionnement.id_conditionnement
            LEFT JOIN commande ON commande.id_commande = ligne_commande.commande_id
            WHERE commande.etat_id = 2
            GROUP BY boisson.id_boisson
            ORDER BY note_moyenne DESC
    
    '''


    return render_template('admin/dataviz/dataviz_etat_2.html'
                           , datas_show=datas_show
                           , labels=labels
                           , nbr_commandes=nbr_commandes
                           , comments=comments
                           , notes=notes
                           , notes_moy=notes_moy)


@admin_dataviz.route('/admin/dataviz/etat3')
def show_adresse():
    mycursor = get_db().cursor()
    sql = '''
        SELECT
            b.nom_boisson AS libelle,
            COUNT(CASE WHEN etat.id_etat = 1 THEN 1 ELSE NULL END) AS commandes_en_cours,
            COUNT(CASE WHEN etat.id_etat = 2 THEN 1 ELSE NULL END) AS commandes_expediees,
            COUNT(lc.commande_id) AS commandes_totales
        FROM boisson b
        LEFT JOIN conditionnement ON conditionnement.boisson_id = b.id_boisson
        LEFT JOIN ligne_commande lc ON lc.conditionnement_id = conditionnement.id_conditionnement
        LEFT JOIN commande ON commande.id_commande = lc.commande_id
        LEFT JOIN etat ON etat.id_etat = commande.etat_id
        GROUP BY b.id_boisson;
    '''
    mycursor.execute(sql)
    datas_show = mycursor.fetchall()

    labels = [str(row['libelle']) for row in datas_show]
    commandes_en_cours = [int(row['commandes_en_cours']) for row in datas_show]
    commandes_expediees = [int(row['commandes_expediees']) for row in datas_show]
    commandes_totales = [int(row['commandes_totales']) for row in datas_show]

    sql = '''
        SELECT
            SUBSTRING(a.code_postal, 1, 2) AS code_postal,
            COUNT(*) AS nb_commandes,
            (SELECT COUNT(*) FROM commande) AS total_commandes
        FROM commande c
        INNER JOIN adresse a ON c.adresse_livraison_id = a.id_adresse
        GROUP BY SUBSTRING(a.code_postal, 1, 2);
    '''
    mycursor.execute(sql)
    map_data = mycursor.fetchall()

    labels_map = [int(row['code_postal']) for row in map_data]
    nb_commandes_dep = [int(row['nb_commandes']) for row in map_data]

    return render_template('admin/dataviz/dataviz_etat_3.html'
                           , datas_show=datas_show
                           , labels=labels
                           , commandes_en_cours=commandes_en_cours
                           , commandes_expediees=commandes_expediees
                           , commandes_totales=commandes_totales
                           , map_data=map_data
                           , labels_map=labels_map
                           , nb_commandes_dep=nb_commandes_dep)

#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import Flask, request, render_template, redirect, url_for, abort, flash, session, g

from flask import *
import re
import datetime

from connexion_db import get_db

admin_exemplaire = Blueprint('admin_exemplaire', __name__,
                        template_folder='templates')

@admin_exemplaire.route('/admin/exemplaire/show')
def show_exemplaire():
    id_oeuvre = request.args.get('id_oeuvre', '')
    mycursor = get_db().cursor()
    sql = ''' SELECT  oeuvre.id_oeuvre, oeuvre.titre, oeuvre.auteur_id, oeuvre.date_parution, COUNT(e.id_exemplaire) AS nb_exemplaire, COUNT(e2.id_exemplaire) AS nb_exemp_dispo
        FROM oeuvre
        LEFT JOIN exemplaire e ON e.oeuvre_id = oeuvre.id_oeuvre
        LEFT JOIN exemplaire e2 ON e2.oeuvre_id = oeuvre.id_oeuvre AND e2.etat = 'disponible'
        WHERE oeuvre.id_oeuvre = %s
     '''
    mycursor.execute(sql,(id_oeuvre))
    oeuvre = mycursor.fetchone()
    sql = ''' SELECT  exemplaire.id_exemplaire, exemplaire.etat, exemplaire.date_achat, exemplaire.prix, exemplaire.oeuvre_id
     FROM exemplaire
     WHERE exemplaire.oeuvre_id = %s
    '''
    mycursor.execute(sql,(id_oeuvre))
    exemplaires = mycursor.fetchall()
    return render_template('admin/exemplaire/show_exemplaires.html', exemplaires=exemplaires, oeuvre=oeuvre)

@admin_exemplaire.route('/admin/exemplaire/add', methods=['GET'])
def add_exemplaire():
    id_oeuvre = request.args.get('id_oeuvre', '')
    mycursor = get_db().cursor()
    sql = ''' SELECT oeuvre.id_oeuvre, oeuvre.titre, oeuvre.auteur_id, oeuvre.date_parution
    FROM oeuvre
    WHERE oeuvre.id_oeuvre = %s
     '''
    mycursor.execute(sql, (id_oeuvre))
    oeuvre = mycursor.fetchone()
    date_achat = datetime.datetime.now().strftime("%d/%m/%Y")
    return render_template('admin/exemplaire/add_exemplaire.html', oeuvre=oeuvre, exemplaire={'date_achat': date_achat, 'id_oeuvre': id_oeuvre}, erreurs=[])
@admin_exemplaire.route('/admin/exemplaire/add', methods=['POST'])
def valid_add_exemplaire():
    mycursor = get_db().cursor()
    id_oeuvre = request.form.get('id_oeuvre', '')
    id_oeuvre=int(float(id_oeuvre))
    date_achat = request.form.get('date_achat', '')
    etat = request.form.get('etat', '')
    prix = request.form.get('prix', '')

    dto_data={'id_oeuvre': id_oeuvre, 'etat': etat, 'date_achat': date_achat, 'prix': prix}
    valid, errors, dto_data = validator_exemplaire(dto_data)
    if valid:
        date_achat = dto_data['date_achat_iso']
        tuple_insert = (id_oeuvre,etat,date_achat,prix)
        print(tuple_insert)
        sql = ''' UPDATE exemplaire SET etat = %s, date_achat = %s, prix = %s WHERE oeuvre_id = %s   '''
        mycursor.execute(sql, tuple_insert)
        get_db().commit()
        message = u'exemplaire ajouté , oeuvre_id :'+str(id_oeuvre)
        flash(message, 'success radius')
        return redirect('/admin/exemplaire/show?id_oeuvre='+str(id_oeuvre))

    sql = ''' SELECT 'requete4_3' FROM DUAL '''
    mycursor.execute(sql, (id_oeuvre))
    oeuvre = mycursor.fetchone()
    return render_template('admin/exemplaire/add_exemplaire.html', oeuvre=oeuvre,
                           exemplaire=dto_data, erreurs=errors)

@admin_exemplaire.route('/admin/exemplaire/delete', methods=['GET'])
def delete_exemplaire():
    mycursor = get_db().cursor()
    id_exemplaire = request.args.get('id_exemplaire', '')
    tuple_delete = (id_exemplaire,)
    sql = ''' SELECT 'requete4_9' FROM DUAL '''
    sql = '''
    SELECT oeuvre_id FROM exemplaire WHERE id_exemplaire =%s
    '''
    mycursor.execute(sql, tuple_delete)
    oeuvre = mycursor.fetchone()
    oeuvre_id=str(oeuvre['oeuvre_id'])
    print(oeuvre,oeuvre_id)
    if not(oeuvre_id and oeuvre_id.isnumeric()):
        abort("404","erreur id_oeuvre")
    nb_emprunts = 0
    sql = ''' SELECT 'requete4_7' FROM DUAL '''
    mycursor.execute(sql, tuple_delete)
    res_nb_emprunts = mycursor.fetchone()
    if 'nb_emprunts' in res_nb_emprunts.keys():
        nb_emprunts=res_nb_emprunts['nb_emprunts']
    if nb_emprunts == 0 :
        sql = ''' SELECT 'requete4_8' FROM DUAL '''
        mycursor.execute(sql, tuple_delete)
        get_db().commit()
        message=u'exemplaire supprimé, id: ' + id_exemplaire
        flash(message, 'success radius')
    else :
        message=u'suppression impossible, il faut supprimer  : ' + str(nb_emprunts) + u' emprunt(s) de cet exemplaire'
        flash(message, 'warning')
    return redirect('/admin/exemplaire/show?id_oeuvre='+oeuvre_id)

@admin_exemplaire.route('/admin/exemplaire/edit', methods=['GET'])
def edit_exemplaire():
    mycursor = get_db().cursor()
    id_exemplaire = request.args.get('id_exemplaire', '')
    sql = ''' SELECT 'requete4_10' FROM DUAL '''
    mycursor.execute(sql, (id_exemplaire))
    oeuvre = mycursor.fetchone()
    sql = ''' SELECT 'requete4_11' FROM DUAL '''
    mycursor.execute(sql, (id_exemplaire,))
    exemplaire = mycursor.fetchone()
    if exemplaire['date_achat']:
        exemplaire['date_achat']=exemplaire['date_achat'].strftime("%d/%m/%Y")
    return render_template('admin/exemplaire/edit_exemplaire.html', exemplaire=exemplaire, oeuvre=oeuvre, erreurs=[])

@admin_exemplaire.route('/admin/exemplaire/edit', methods=['POST'])
def valid_edit_exemplaire():
    mycursor = get_db().cursor()
    id_exemplaire = request.form.get('id_exemplaire', '')
    oeuvre_id = request.form.get('oeuvre_id', '')
    date_achat = request.form.get('date_achat', '')
    etat = request.form.get('etat', '')
    prix = request.form.get('prix', '')

    dto_data={'oeuvre_id': oeuvre_id, 'etat': etat, 'date_achat': date_achat, 'prix': prix, 'id_exemplaire':id_exemplaire}
    valid, errors, dto_data = validator_exemplaire(dto_data)
    print(valid, errors, dto_data)
    if valid:
        date_achat = dto_data['date_achat_iso']
        tuple_update = (oeuvre_id, etat, date_achat, prix, id_exemplaire)
        print(tuple_update)
        sql = ''' SELECT 'requete4_12' FROM DUAL '''
        mycursor.execute(sql, tuple_update)
        get_db().commit()
        message=u' exemplaire modifié, id_exemplaire: ' + id_exemplaire
        flash(message, 'success radius')
        return redirect('/admin/exemplaire/show?id_oeuvre='+oeuvre_id)
    sql = ''' SELECT 'requete4_10' FROM DUAL '''
    mycursor.execute(sql, (oeuvre_id))
    oeuvre = mycursor.fetchone()
    return render_template('admin/exemplaire/edit_exemplaire.html', exemplaire=dto_data, oeuvre=oeuvre, erreurs=errors)


def validator_exemplaire(data):
    valid = True
    errors = dict()
    if 'id_exemplaire' in data.keys():
        if not data['id_exemplaire'].isnumeric():
            errors['id_exemplaire'] = 'type id incorrect(numeric)'
            valid = False
    if not re.match(r'\w{2,}', data['etat']):
        errors['etat'] = "Le titre doit avoir au moins deux caractères"
        valid = False
    try:
        datetime.datetime.strptime(data['date_achat'], '%d/%m/%Y')
    except ValueError:
        errors['date_achat'] = "la Date n'est pas valide format:%d/%m/%Y"
        valid = False
    else:
        data['date_achat_iso'] = datetime.datetime.strptime(data['date_achat'], "%d/%m/%Y").strftime("%Y-%m-%d")
    try:
        float(data['prix'])
    except ValueError:
        errors['prix'] = "le Prix n'est pas valide"
        valid = False
    return (valid, errors, data)

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
    noOeuvre=request.args.get('noOeuvre', '')
    mycursor = get_db().cursor()
    sql = ''' SELECT 'requete4_1' FROM DUAL '''
    mycursor.execute(sql,(noOeuvre))
    oeuvre = mycursor.fetchone()
    sql = ''' SELECT 'requete4_2' FROM DUAL '''
    mycursor.execute(sql,(noOeuvre))
    exemplaires = mycursor.fetchall()
    return render_template('admin/exemplaire/show_exemplaires.html', exemplaires=exemplaires, oeuvre=oeuvre)

@admin_exemplaire.route('/admin/exemplaire/add', methods=['GET'])
def add_exemplaire():
    noOeuvre = request.args.get('noOeuvre', '')
    mycursor = get_db().cursor()
    sql = ''' SELECT 'requete4_3' FROM DUAL '''
    mycursor.execute(sql, (noOeuvre))
    oeuvre = mycursor.fetchone()
    date_achat = datetime.datetime.now().strftime("%d/%m/%Y")
    return render_template('admin/exemplaire/add_exemplaire.html', donnees2=oeuvre, donnees={'dateAchat': date_achat, 'noOeuvre': noOeuvre}, erreurs=[])

@admin_exemplaire.route('/admin/exemplaire/add', methods=['POST'])
def valid_add_exemplaire():
    mycursor = get_db().cursor()
    noOeuvre = request.form.get('noOeuvre', '')
    noOeuvre=int(float(noOeuvre))
    dateAchat = request.form.get('dateAchat', '')
    etat = request.form.get('etat', '')
    prix = request.form.get('prix', '')

    dto_data={'noOeuvre': noOeuvre, 'etat': etat, 'dateAchat': dateAchat, 'prix': prix}
    valid, errors, dto_data = validator_exemplaire(dto_data)
    if valid:
        dateAchat = dto_data['dateAchat_us']
        tuple_insert = (noOeuvre,etat,dateAchat,prix)
        print(tuple_insert)
        sql = ''' SELECT 'requete4_5' FROM DUAL '''
        mycursor.execute(sql, tuple_insert)
        get_db().commit()
        message = u'exemplaire ajouté , oeuvre_id :'+str(noOeuvre)
        flash(message)
        return redirect('/admin/exemplaire/show?noOeuvre='+str(noOeuvre))

    sql = ''' SELECT 'requete4_3' FROM DUAL '''
    mycursor.execute(sql, (noOeuvre))
    oeuvre = mycursor.fetchone()
    return render_template('admin/exemplaire/add_exemplaire.html', donnees2=oeuvre,
                           donnees=dto_data, erreurs=errors)

@admin_exemplaire.route('/admin/exemplaire/delete', methods=['GET'])
def delete_exemplaire():
    mycursor = get_db().cursor()
    id = request.args.get('id', '') #id de l'exemplaire
    tuple_delete = (id,)
    sql = ''' SELECT 'requete4_9' FROM DUAL '''
    mycursor.execute(sql, tuple_delete)
    oeuvre = mycursor.fetchone()
    oeuvre_id=str(oeuvre['oeuvre_id'])
    print(oeuvre,oeuvre_id)
    #noOeuvre = request.form.get('noOeuvre', '')
    if not(id and id.isnumeric()):
        abort("404","erreur id oeuvre")

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
        flash(u'oeuvre supprimée, id: ' + id)
    else :
        flash(u'suppression impossible, il faut supprimer  : ' + str(nb_emprunts) + u' emprunt(s) de cet exemplaire')
    return redirect('/admin/exemplaire/show?noOeuvre='+oeuvre_id)

@admin_exemplaire.route('/admin/exemplaire/edit', methods=['GET'])
def edit_exemplaire():
    mycursor = get_db().cursor()
    id = request.args.get('id', '')
    sql = ''' SELECT 'requete4_10' FROM DUAL '''
    mycursor.execute(sql, (id))
    oeuvre = mycursor.fetchone()


    id_exemplaire = request.args.get('id', '')
    sql = ''' SELECT 'requete4_11' FROM DUAL '''
    mycursor.execute(sql, (id_exemplaire,))
    exemplaire = mycursor.fetchone()
    if exemplaire['dateAchat']:
        exemplaire['dateAchat']=exemplaire['dateAchat'].strftime("%d/%m/%Y")
    return render_template('admin/exemplaire/edit_exemplaire.html', donnees=exemplaire, donnees2=oeuvre, erreurs=[])

@admin_exemplaire.route('/admin/exemplaire/edit', methods=['POST'])
def valid_edit_exemplaire():
    mycursor = get_db().cursor()
    id_exemplaire = request.form.get('noExemplaire', '')
    noOeuvre = request.form.get('noOeuvre', '')
    dateAchat = request.form.get('dateAchat', '')
    etat = request.form.get('etat', '')
    prix = request.form.get('prix', '')

    dto_data={'noOeuvre': noOeuvre, 'etat': etat, 'dateAchat': dateAchat, 'prix': prix, 'id_exemplaire':id_exemplaire}
    valid, errors, dto_data = validator_exemplaire(dto_data)
    if valid:
        dateAchat = dto_data['dateAchat_us']
        tuple_update = (noOeuvre, etat, dateAchat, prix, id_exemplaire)
        print(tuple_update)
        sql = ''' SELECT 'requete4_12' FROM DUAL '''
        mycursor.execute(sql, tuple_update)
        get_db().commit()
        flash(u' exemplaire modifié, noOeuvre: ' + noOeuvre )
        return redirect('/admin/exemplaire/show?noOeuvre='+noOeuvre)
    sql = ''' SELECT 'requete4_10' FROM DUAL '''
    mycursor.execute(sql, (noOeuvre))
    oeuvre = mycursor.fetchone()
    return render_template('admin/exemplaire/edit_exemplaire.html', donnees=dto_data, donnees2=oeuvre, erreurs=errors)

def validator_exemplaire(data):
    mycursor = get_db().cursor()
    # id,etat,date_achat,prix,oeuvre_id
    valid = True
    errors = dict()

    if 'id' in data.keys():
        if not data['id'].isdecimal():
            errors['id'] = 'type id incorrect'
            valid = False

    if not re.match(r'\w{2,}', data['etat']):
        flash('Titre doit avoir au moins deux caractères')
        errors['etat'] = "Le titre doit avoir au moins deux caractères"
        valid = False

    try:
        datetime.datetime.strptime(data['dateAchat'], '%d/%m/%Y')
    except ValueError:
        flash("la Date n'est pas valide")
        errors['dateAchat'] = "la Date n'est pas valide format:%d/%m/%Y"
        valid = False
    else:
        data['dateAchat_us'] = datetime.datetime.strptime(data['dateAchat'], "%d/%m/%Y").strftime("%Y-%m-%d")

    try:
        float(data['prix'])
    except ValueError:
        flash("Prix n'est pas valide")
        errors['prix'] = "le Prix n'est pas valide"
        valid = False

    return (valid, errors, data)
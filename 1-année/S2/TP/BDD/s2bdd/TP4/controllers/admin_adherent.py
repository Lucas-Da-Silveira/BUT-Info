#! /usr/bin/python
# -*- coding:utf-8 -*-
import re
from flask import *
import datetime


from connexion_db import get_db

admin_adherent = Blueprint('admin_adherent', __name__,
                        template_folder='templates')

@admin_adherent.route('/admin/adherent/show')
def show_adherent():
    mycursor = get_db().cursor()
    sql = ''' SELECT adherent.id_adherent,adherent.nom,oeuvre.titre,emprunt.date_emprunt,emprunt.date_retour,adherent.date_paiement
                    CASE
                    WHEN DATE_ADD(adherent.date_paiement, INTERVAL 1 YEAR) < NOW() THEN 'red', DATE_FORMAT(adherent.date_paiement, '%d/%m/%Y')
                    WHEN DATE_ADD
            
    '''
    
    mycursor.execute(sql)
    adherents = mycursor.fetchall()
    return render_template('admin/adherent/show_adherents.html', adherents=adherents)

@admin_adherent.route('/admin/adherent/add', methods=['GET'])
def add_adherent():
    erreurs=[]
    donnees=[]
    return render_template('admin/adherent/add_adherent.html', erreurs=erreurs, donnees=donnees)

@admin_adherent.route('/admin/adherent/add', methods=['POST'])
def valid_add_adherent():
    nom = request.form.get('nom', '')
    adresse = request.form.get('adresse', '')
    date_paiement = request.form.get('date_paiement', '')

    dto_data={'nom': nom, 'adresse': adresse, 'date_paiement': date_paiement}
    valid, errors, dto_data = validator_adherent(dto_data)
    if valid:
        date_paiement=dto_data['date_paiement_us']
        tuple_insert = (nom,adresse,date_paiement)
        mycursor = get_db().cursor()
        sql = ''' INSERT INTO adherent (nom, adresse, date_paiement) VALUES (%s,%s,%s)'''
        mycursor.execute(sql, tuple_insert)
        get_db().commit()
        message=u'adherent ajouté , libellé :'+nom
        flash(message, 'success radius')
        return redirect('/admin/adherent/show')
    return render_template('admin/adherent/add_adherent.html', erreurs=errors, donnees=dto_data)

@admin_adherent.route('/admin/adherent/delete', methods=['GET'])
def delete_adherent():
    mycursor = get_db().cursor()
    id_adherent = request.args.get('id_adherent', '')
    if not(id_adherent and id_adherent.isnumeric()):
        abort("404","erreur id adherent")
    tuple_delete=(id_adherent)
    nb_emprunts = 0
    sql = ''' SELECT 'requete2_6' FROM DUAL '''
    mycursor.execute(sql, tuple_delete)
    res_nb_emprunts = mycursor.fetchone()
    if 'nb_emprunts' in res_nb_emprunts.keys():
        nb_emprunts=res_nb_emprunts['nb_emprunts']
    if nb_emprunts == 0 :
        sql = ''' SELECT 'requete2_3' FROM DUAL '''
        mycursor.execute(sql, tuple_delete)
        get_db().commit()
        message=u'adherent supprimé, id_adherent: ' + id_adherent
        flash(message, 'success radius')
    else :
        message=u'suppression impossible, il faut supprimer  : ' + str(nb_emprunts) + u' emprunt(s) de cet adherent'
        flash(message, 'warning')
    return redirect('/admin/adherent/show')

@admin_adherent.route('/admin/adherent/edit', methods=['GET'])
def edit_adherent():
    id_adherent = request.args.get('id_adherent', '')
    mycursor = get_db().cursor()
    sql = ''' SELECT 'requete2_4' FROM DUAL '''
    mycursor.execute(sql, (id_adherent,))
    adherent = mycursor.fetchone()
    if adherent['date_paiement']:
        adherent['date_paiement']=adherent['date_paiement'].strftime("%d/%m/%Y")
    erreurs=[]
    return render_template('admin/adherent/edit_adherent.html', donnees=adherent, erreurs=erreurs)

@admin_adherent.route('/admin/adherent/edit', methods=['POST'])
def valid_edit_adherent():
    id_adherent = request.form.get('id_adherent', '')
    nom = request.form.get('nom', '')
    adresse = request.form.get('adresse', '')
    date_paiement = request.form.get('date_paiement', '')
    dto_data={'nom': nom, 'adresse': adresse, 'date_paiement': date_paiement, 'id_adherent': id_adherent}
    valid, errors, dto_data = validator_adherent(dto_data)
    if valid:
        date_paiement=dto_data['date_paiement_us']
        tuple_update = (nom,adresse,date_paiement,id_adherent)
        mycursor = get_db().cursor()
        sql = ''' SELECT 'requete2_5' FROM DUAL '''
        mycursor.execute(sql,tuple_update)
        get_db().commit()
        message=u'adherent modifié, id_adherent: ' + id_adherent + " nom : " + nom
        flash(message, 'success radius')
        return redirect('/admin/adherent/show')
    return render_template('admin/adherent/edit_adherent.html', erreurs=errors, donnees=dto_data)

def validator_adherent(data):
    valid = True
    errors = dict()

    if 'id_adherent' in data.keys():
        if not data['id_adherent'].isdecimal():
           errors['id_adherent'] = 'type id incorrect'
           valid= False

    if not re.match(r'\w{2,}', data['nom']):
        errors['nom'] = "Le Nom doit avoir au moins deux caractères"
        valid = False

    try:
        datetime.datetime.strptime(data['date_paiement'], '%d/%m/%Y')
    except ValueError:
        errors['date_paiement'] = "Date n'est pas valide format:%d/%m/%Y"
        valid = False
    else:
        data['date_paiement_us'] = datetime.datetime.strptime(data['date_paiement'], "%d/%m/%Y").strftime("%Y-%m-%d")
    return (valid, errors, data)







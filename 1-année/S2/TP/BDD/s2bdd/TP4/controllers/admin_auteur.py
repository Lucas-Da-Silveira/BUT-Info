#! /usr/bin/python
# -*- coding:utf-8 -*-
import re
from flask import *
from models.dao_auteur import *

from connexion_db import get_db

admin_auteur = Blueprint('admin_auteur', __name__,
                        template_folder='templates')

@admin_auteur.route('/admin/auteur/show')
def show_auteur():
    mycursor = get_db().cursor()
    sql = '''
    SELECT count(oeuvre.id_oeuvre) as nbrOeuvre, id_auteur, nom, prenom
    FROM auteur
    LEFT JOIN oeuvre ON auteur.id_auteur = oeuvre.auteur_id
    GROUP BY id_auteur
    ORDER BY nom
    ;
    '''
    mycursor.execute(sql)
    auteurs = mycursor.fetchall()
    return render_template('admin/auteur/show_auteurs.html', auteurs=auteurs)

@admin_auteur.route('/admin/auteur/add', methods=['GET'])
def add_auteur():
    erreurs=[]
    donnees=[]
    return render_template('admin/auteur/add_auteur.html', erreurs=erreurs, donnees=donnees)

@admin_auteur.route('/admin/auteur/add', methods=['POST'])
def valid_add_auteur():
    nom = request.form.get('nom', '')
    prenom = request.form.get('prenom', '')
    dto_data={'nom': nom, 'prenom':prenom}
    valid, errors = validator_auteur(dto_data)  # validator_auteur is in models/dao_auteur.py
    if valid:
        tuple_insert = (nom,prenom)
        mycursor = get_db().cursor()
        sql = '''
        INSERT INTO auteur (nom, prenom) VALUES (%s,%s)
        '''
        mycursor.execute(sql, tuple_insert)
        get_db().commit()
        message = u'auteur ajouté , nom :'+nom
        flash(message, 'success radius')
        return redirect('/admin/auteur/show')
    return render_template('admin/auteur/add_auteur.html', erreurs=errors, donnees=dto_data)

@admin_auteur.route('/admin/auteur/delete', methods=['GET'])
def delete_auteur():
    mycursor = get_db().cursor()
    id_auteur = request.args.get('id', '')
    if not(id_auteur and id_auteur.isnumeric()):
        abort("404","erreur id auteur")
    tuple_delete=(id_auteur)
    nb_oeuvres = 0
    sql = ''' 
    SELECT count(*) as nb_oeuvres
    FROM oeuvre
    WHERE auteur_id = %s
     '''
    #mycursor.execute(sql, tuple_delete)              ###
    #res_nb_oeuvres = mycursor.fetchone()             ###
    #if 'nb_oeuvres' in res_nb_oeuvres.keys():        ###
    #    nb_oeuvres=res_nb_oeuvres['nb_oeuvres']      ###
    if nb_oeuvres == 0 :
        sql = '''
        DELETE FROM auteur
        WHERE id_auteur = %s
        '''
        mycursor.execute(sql,tuple_delete)
        get_db().commit()
        message=u'auteur supprimé, id: ' + id_auteur
        flash(message, 'success radius')
    else :
        message= u'suppression impossible, il faut supprimer  : ' + str(nb_oeuvres) + u' oeuvre(s) de cet auteur'
        flash(message, 'warning')
    return redirect('/admin/auteur/show')

@admin_auteur.route('/admin/auteur/edit', methods=['GET'])
def edit_auteur():
    id = request.args.get('id', '')
    mycursor = get_db().cursor()
    sql = '''
SELECT id_auteur, nom, prenom
FROM auteur
WHERE id_auteur = %s
    '''
    mycursor.execute(sql, (id,))
    auteur = mycursor.fetchone()
    print(id,sql)
    erreurs=[]
    return render_template('admin/auteur/edit_auteur.html', donnees=auteur, erreurs=erreurs)

@admin_auteur.route('/admin/auteur/edit', methods=['POST'])
def valid_edit_auteur():
    nom = request.form.get('nom', '')
    prenom = request.form.get('prenom', '')
    id_auteur = request.form.get('id_auteur', '')
    dto_data={'nom': nom, 'prenom':prenom, 'id_auteur': id_auteur}
    valid, errors = validator_auteur(dto_data)
    if valid:
        tuple_update = (nom, prenom, id_auteur)
        mycursor = get_db().cursor()
        sql = '''
        UPDATE auteur
        SET nom = %s, prenom = %s
        WHERE id_auteur = %s
        '''
        print(sql)
        mycursor.execute(sql, tuple_update)
        get_db().commit()
        message=u'auteur modifié, id: ' + id_auteur + " nom : " + nom
        flash(message, 'success radius')
        return redirect('/admin/auteur/show')
    return render_template('admin/auteur/edit_auteur.html', donnees=dto_data, erreurs=errors)

def validator_auteur(data):
    valid = True
    errors = dict()
    if not re.match(r'\w{2,}', data['nom']):
        # flash('Nom doit avoir au moins deux caractères')
        errors['nom'] = 'Nom doit avoir au moins deux caractères'
        valid = False
    if 'id_auteur' in data.keys():
        if not data['id_auteur'].isdecimal():
           errors['id_auteur'] = 'type id incorrect'
           valid= False
    return (valid, errors)






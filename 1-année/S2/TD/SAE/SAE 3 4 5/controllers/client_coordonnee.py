#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import request, render_template, redirect, flash, session

from connexion_db import get_db

client_coordonnee = Blueprint('client_coordonnee', __name__,
                              template_folder='templates')


@client_coordonnee.route('/client/coordonnee/show')
def client_coordonnee_show():
    mycursor = get_db().cursor()
    id_client = session['id_user']

    sql = 'SELECT login, nom, email FROM utilisateur WHERE id_utilisateur = %s;'
    mycursor.execute(sql, id_client)
    utilisateur = mycursor.fetchone()

    sql = '''
        SELECT
            nom_adresse AS nom,
            rue,
            code_postal,
            ville,
            id_adresse,
            valide
        FROM adresse
        WHERE utilisateur_id = %s
        ORDER BY id_adresse;
    '''
    mycursor.execute(sql, id_client)
    adresses = mycursor.fetchall()

    sql = 'SELECT COUNT(*) AS nb_adresses FROM adresse WHERE valide = 1 AND utilisateur_id = %s;'
    mycursor.execute(sql, id_client)
    nb_adresses = mycursor.fetchone()
    nb_adresses = nb_adresses.get("nb_adresses")

    return render_template('client/coordonnee/show_coordonnee.html',
                           utilisateur=utilisateur,
                           adresses=adresses,
                           nb_adresses=nb_adresses
                           )


@client_coordonnee.route('/client/coordonnee/edit', methods=['GET'])
def client_coordonnee_edit():
    mycursor = get_db().cursor()
    id_client = session['id_user']

    sql = '''
        SELECT
            id_utilisateur,
            IFNULL(login, 'None') AS login,
            IFNULL(email, 'None') AS email,
            IFNULL(nom, 'None') AS nom
        FROM utilisateur
        WHERE id_utilisateur = %s;
    '''
    mycursor.execute(sql, id_client)
    user = mycursor.fetchone()

    return render_template('client/coordonnee/edit_coordonnee.html', user=user)


@client_coordonnee.route('/client/coordonnee/edit', methods=['POST'])
def client_coordonnee_edit_valide():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    nom = request.form.get('nom')
    login = request.form.get('login')
    email = request.form.get('email')

    utilisateur = None

    sql = 'SELECT id_utilisateur FROM utilisateur WHERE ((email = %s OR login = %s) AND id_utilisateur != %s);'
    mycursor.execute(sql, (email, login, id_client))
    utilisateur = mycursor.fetchone()

    if utilisateur:
        flash(u'Cet Email ou ce Login existe déjà pour un autre utilisateur', 'alert-warning')
        return redirect('/client/coordonnee/edit')

    sql = '''
        UPDATE utilisateur
        SET login = %s, email = %s, nom = %s
        WHERE id_utilisateur = %s;
    '''
    mycursor.execute(sql, (login, email, nom, id_client))
    get_db().commit()

    return redirect('/client/coordonnee/show')


@client_coordonnee.route('/client/coordonnee/delete_adresse', methods=['POST'])
def client_coordonnee_delete_adresse():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    id_adresse = request.form.get('id_adresse')

    sql = '''
        SELECT id_commande FROM commande
        WHERE adresse_livraison_id = %s OR adresse_facturation_id = %s;
    '''
    mycursor.execute(sql, (id_adresse, id_adresse))
    commandes = mycursor.fetchall()

    if commandes:
        sql = '''
            UPDATE adresse
            SET valide = 0
            WHERE id_adresse = %s AND utilisateur_id = %s;
        '''
        mycursor.execute(sql, (id_adresse, id_client))
        get_db().commit()
        flash(u'Cette adresse est présente dans des commandes.', 'alert-warning')
    else:
        sql = '''
            DELETE FROM adresse
            WHERE id_adresse = %s AND utilisateur_id = %s;
        '''
        mycursor.execute(sql, (id_adresse, id_client))
        get_db().commit()
        flash(f'Adresse supprimée, id : {id_adresse}', 'alert-success')

    return redirect('/client/coordonnee/show')


@client_coordonnee.route('/client/coordonnee/add_adresse')
def client_coordonnee_add_adresse():
    mycursor = get_db().cursor()
    id_client = session['id_user']

    sql = 'SELECT COUNT(*) AS nb_adresses FROM adresse WHERE valide = 1 AND utilisateur_id = %s;'
    mycursor.execute(sql, id_client)
    nb_adresses = mycursor.fetchone()

    if nb_adresses.get("nb_adresses") >= 4:
        flash(u'Ajout impossible, vous avez déjà 4 adresses.', 'alert-warning')
        return redirect('/client/coordonnee/show')

    sql = '''
        SELECT nom, login
        FROM utilisateur
        WHERE id_utilisateur = %s;
    '''
    mycursor.execute(sql, id_client)
    utilisateur = mycursor.fetchone()

    return render_template('client/coordonnee/add_adresse.html'
                           , utilisateur=utilisateur
                           )


@client_coordonnee.route('/client/coordonnee/add_adresse', methods=['POST'])
def client_coordonnee_add_adresse_valide():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    nom = request.form.get('nom')
    rue = request.form.get('rue')
    code_postal = request.form.get('code_postal')
    ville = request.form.get('ville')

    sql = 'SELECT COUNT(*) AS nb_adresses FROM adresse WHERE valide = 1 AND utilisateur_id = %s;'
    mycursor.execute(sql, id_client)
    nb_adresses = mycursor.fetchone()

    if nb_adresses.get("nb_adresses") >= 4:
        flash(u'Ajout impossible, vous avez déjà 4 adresses.', 'alert-warning')
        return redirect('/client/coordonnee/show')

    sql = '''
        INSERT INTO adresse
        VALUES(NULL, %s, %s, %s, %s, 1, %s);
    '''
    mycursor.execute(sql, (nom, rue, code_postal, ville, id_client))
    get_db().commit()

    return redirect('/client/coordonnee/show')


@client_coordonnee.route('/client/coordonnee/edit_adresse')
def client_coordonnee_edit_adresse():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    id_adresse = request.args.get('id_adresse')

    sql = '''
            SELECT nom, login
            FROM utilisateur
            WHERE id_utilisateur = %s;
        '''
    mycursor.execute(sql, id_client)
    utilisateur = mycursor.fetchone()

    sql = '''
        SELECT
            id_adresse,
            nom_adresse AS nom,
            rue,
            code_postal,
            ville,
            valide
        FROM adresse
        WHERE utilisateur_id = %s AND id_adresse = %s;
    '''
    mycursor.execute(sql, (id_client, id_adresse))
    adresse = mycursor.fetchone()

    if not adresse or adresse.get('valide') == 0:
        flash(u'Modification impossible : adresse invalide ou n\'existe pas.', 'alert-warning')
        return redirect('/client/coordonnee/show')

    return render_template('/client/coordonnee/edit_adresse.html'
                           , utilisateur=utilisateur
                           , adresse=adresse
                           )


@client_coordonnee.route('/client/coordonnee/edit_adresse', methods=['POST'])
def client_coordonnee_edit_adresse_recoit():
    mycursor = get_db().cursor()
    id_client = session['id_user']
    nom = request.form.get('nom')
    rue = request.form.get('rue')
    code_postal = request.form.get('code_postal')
    ville = request.form.get('ville')
    id_adresse = request.form.get('id_adresse')

    sql = '''
        UPDATE adresse
        SET nom_adresse = %s, rue = %s, code_postal = %s, ville = %s
        WHERE utilisateur_id = %s AND id_adresse = %s;
    '''
    mycursor.execute(sql, (nom, rue, code_postal, ville, id_client, id_adresse))
    get_db().commit()

    return redirect('/client/coordonnee/show')

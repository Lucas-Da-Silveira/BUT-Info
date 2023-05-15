#! /usr/bin/python
# -*- coding:utf-8 -*-
import datetime
import os
import re

from models.dao_auteur import *


def validator_oeuvre(data):
    valid = True
    errors = dict()

    if 'auteur_id' in data.keys():
        if not data['auteur_id'].isdecimal():
            errors['auteur_id'] = 'type id incorrect'
            valid = False
    auteur = find_one_auteur(data['auteur_id'])
    if not auteur:
        errors['auteur_id'] = "Saisir un Auteur"
        valid = False
    else:
        data['auteur_id'] = int(data['auteur_id'])

    if not re.match(r'\w{2,}', data['titre']):
        errors['titre'] = "Le titre doit avoir au moins deux caractères"
        valid = False

    try:
        datetime.datetime.strptime(data['date_parution'], '%d/%m/%Y')
    except ValueError:
        errors['date_parution'] = "la Date n'est pas valide format:%d/%m/%Y"
        valid = False
    else:
        data['date_parution_iso'] = datetime.datetime.strptime(data['date_parution'], "%d/%m/%Y").strftime("%Y-%m-%d")

    if data['photo']:
        photo_path = os.path.join(current_app.root_path,
                                  'static', 'assets', 'images', data['photo'])
        if not os.path.isfile(photo_path):
            errors['photo'] = f"la Photo n'existe pas: {photo_path}"
            valid = False
    return (valid, errors, data)

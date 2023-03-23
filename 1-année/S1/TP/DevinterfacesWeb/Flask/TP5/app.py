#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Flask, request, render_template, redirect, url_for, abort, flash

app = Flask(__name__)
app.secret_key = 'une cle(token) : grain de sel(any random string)'

articles = [
    {'id': 1, 'nom': 'Enveloppes (50p)', 'type_article_id': 1, 'prix': 2, 'stock': 1, 'description': None, 'image': None},
    {'id': 2, 'nom': 'Stylo noir', 'type_article_id': 1, 'prix': 1, 'stock': 10, 'description': None, 'image': 'stylo.jpeg'},
    {'id': 3, 'nom': 'Boite de rangement', 'type_article_id': 1, 'prix': 3, 'stock': 7, 'description': None, 'image': 'boites.jpeg'},
    {'id': 4, 'nom': 'Chaise', 'type_article_id': 2, 'prix': 40, 'stock': 2, 'description': None, 'image': 'chaise.jpeg'},
    {'id': 5, 'nom': 'Tables', 'type_article_id': 2, 'prix': 200, 'stock': 3, 'description': None, 'image': 'table.jpeg'},
    {'id': 6, 'nom': 'Salon de Jardin alu', 'type_article_id': 3, 'prix': 149, 'stock': 3, 'description': None, 'image': 'salonJardin2.jpg'},
    {'id': 7, 'nom': 'Table+6 fauteuilles de Jardin', 'type_article_id': 3, 'prix': 790, 'stock': 3, 'description': None, 'image': 'tableFauteuilsJardin1.jpg'},
    {'id': 8, 'nom': 'Set Table + 4 bancs', 'type_article_id': 3, 'prix': 229, 'stock': 2, 'description': None, 'image': 'setTableChaises.jpg'},
    {'id': 9, 'nom': 'arrosoir bleu', 'type_article_id': 4, 'prix': 14, 'stock': 2, 'description': None, 'image': 'arrosoir1.jpg'},
    {'id': 10, 'nom': 'arrosoir griotte', 'type_article_id': 4, 'prix': 10, 'stock': 2, 'description': None, 'image': 'arrosoir2.jpg'},
    {'id': 11, 'nom': 'tuyau arrosage', 'type_article_id': 4, 'prix': 32, 'stock': 3, 'description': None, 'image': 'tuyauArrosage1.jpg'},
    {'id': 12, 'nom': 'tournevis', 'type_article_id': 5, 'prix': 24, 'stock': 3, 'description': None, 'image': 'lotTourneVis.jpg'},
    {'id': 13, 'nom': 'marteau menuisier', 'type_article_id': 5, 'prix': 8, 'stock': 3, 'description': None, 'image': 'marteau.jpg'},
    {'id': 14, 'nom': 'pince multiprise', 'type_article_id': 5, 'prix': 22, 'stock': 3, 'description': None, 'image': 'pinceMultiprise.jpg'},
    {'id': 15, 'nom': 'perceuse', 'type_article_id': 5, 'prix': 150, 'stock': 3, 'description': None, 'image': 'perceuse.jpg'}
]

types_articles = [
    {'id': 1, 'libelle': 'Fourniture de bureau'},
    {'id': 2, 'libelle': 'Mobilier'},
    {'id': 3, 'libelle': 'Mobilier Jardin'},
    {'id': 4, 'libelle': 'Arrosage'},
    {'id': 5, 'libelle': 'Outils'},
    {'id': 6, 'libelle': 'Divers'}
]

@app.route('/')
def show_accueil():
    return render_template('layout.html')

@app.route('/type-article/show')
def show_type_article():
    #print(types_articles)
    return render_template('type_article/show_type_article.html', types_articles=types_articles)

@app.route('/type-article/add', methods=['GET'])
def add_type_article():
    return render_template('type_article/add_type_article.html')

@app.route('/type-article/add', methods=['POST'])
def valid_add_type_article():
    libelle = request.form.get('libelle', '')
    print(u'type ajouté , libellé :', libelle)
    message = u'type ajouté , libellé :'+libelle
    flash(message, 'alert-success')
    return redirect('/type-article/show')

@app.route('/type-article/delete', methods=['GET'])
def delete_type_article():
    id = request.args.get('id', '')
    print ("un type d'article supprimé, id :",id)
    message=u'un type d\'article supprimé, id : ' + id
    flash(message, 'alert-warning')
    return redirect('/type-article/show')

@app.route('/type-article/edit', methods=['GET'])
def edit_type_article():
    id = request.args.get('id', '')
    libelle = request.args.get('libelle', '')     # comment passé plusieurs paramètres (clé primaire composés)
    id=int(id)
    type_article = types_articles[id-1]
    return render_template('type_article/edit_type_article.html', type_article=type_article)

@app.route('/type-article/edit', methods=['POST'])
def valid_edit_type_article():
    libelle = request.form['libelle']
    id = request.form.get('id', '')
    print(u'type article modifié, id: ',id, " libelle :", libelle)
    message=u'type article modifié, id: ' + id + " libelle : " + libelle
    flash(message, 'alert-success')
    return redirect('/type-article/show')

@app.route('/article/show')
def show_article():
    # print(articles)
    return render_template('article/show_article.html', articles=articles)

@app.route('/article/add', methods=['GET'])
def add_article():
    return render_template('article/add_article.html', types_articles=types_articles)

@app.route('/article/add', methods=['POST'])
def valid_add_article():
    nom = request.form.get('nom', '')
    type_article_id = request.form.get('type_article_id', '')
    prix = request.form.get('prix', '')
    stock = request.form.get('stock', '')
    description = request.form.get('description', '')
    image = request.form.get('image', '')
    print(u'article ajouté , nom: ', nom, ' - type_article_id :', type_article_id, ' - prix:', prix, ' - stock:', stock, ' - description:', description, ' - image:', image)
    message = u'article ajouté , nom:'+nom + '- type_article_id :' + type_article_id + ' - prix:' + prix + ' - stock:'+  stock + ' - description:' + description + ' - image:' + image
    flash(message, 'alert-success')
    return redirect('/article/show')

@app.route('/article/delete', methods=['GET'])
def delete_article():
    id = request.args.get('id', '')
    message=u'un article supprimé, id : ' + id
    flash(message, 'alert-warning')
    return redirect('/article/show')

@app.route('/article/edit', methods=['GET'])
def edit_article():
    id = request.args.get('id', '')
    id = int(id)
    article = articles[id-1]
    return render_template('article/edit_article.html', article=article, types_articles=types_articles)

@app.route('/article/edit', methods=['POST'])
def valid_edit_article():
    nom = request.form['nom']
    id = request.form.get('id', '')
    type_article_id = request.form.get('type_article_id', '')
    prix = request.form.get('prix', '')
    stock = request.form.get('stock', '')
    description = request.form.get('description', '')
    image = request.form.get('image', '')
    print(u'article modifié , nom : ', nom, ' - type_article_id :', type_article_id, ' - prix:', prix, ' - stock:', stock, ' - description:', description, ' - image:', image)
    message = u'article modifié , nom:'+nom + '- type_article_id :' + type_article_id + ' - prix:' + prix + ' - stock:'+  stock + ' - description:' + description + ' - image:' + image
    flash(message, 'alert-success')
    return redirect('/article/show')

if __name__ == '__main__':
    app.run()
#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import *
import datetime
from decimal import *
from connexion_db import get_db

fixtures_load = Blueprint('fixtures_load', __name__,
                        template_folder='templates')

@fixtures_load.route('/base/init')
def fct_fixtures_load():
    mycursor = get_db().cursor()
    sql='''DROP TABLE IF EXISTS commentaire, note, historique, ligne_panier, ligne_commande, conditionnement, boisson, marque, type_eau, conteneur, volume, commande, adresse, etat, utilisateur;'''

    mycursor.execute(sql)
    sql='''
    CREATE TABLE utilisateur(
        id_utilisateur INT NOT NULL AUTO_INCREMENT,
        login VARCHAR(255),
        email VARCHAR(255),
        nom VARCHAR(255),
        password VARCHAR(255),
        role VARCHAR(255),
        est_actif TINYINT(1),
        PRIMARY KEY(id_utilisateur)
    ) CHARACTER SET utf8; 
    '''
    mycursor.execute(sql)
    sql=''' 
    CREATE TABLE etat(
        id_etat INT NOT NULL AUTO_INCREMENT,
        libelle VARCHAR(255),
        PRIMARY KEY(id_etat)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE adresse(
        id_adresse INT NOT NULL AUTO_INCREMENT,
        nom_adresse VARCHAR(255),
        rue VARCHAR(255),
        code_postal VARCHAR(255),
        ville VARCHAR(255),
        valide TINYINT(1),
        utilisateur_id INT,
        PRIMARY KEY (id_adresse),
        CONSTRAINT fk_adresse_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id_utilisateur)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE commande(
        id_commande INT NOT NULL AUTO_INCREMENT,
        date_achat DATE,
        utilisateur_id INT,
        etat_id INT,
        adresse_livraison_id INT,
        adresse_facturation_id INT,
        PRIMARY KEY(id_commande),
        CONSTRAINT fk_commande_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id_utilisateur),
        CONSTRAINT fk_commande_etat FOREIGN KEY (etat_id) REFERENCES etat(id_etat),
        CONSTRAINT fk_commande_livraison FOREIGN KEY (adresse_livraison_id) REFERENCES adresse(id_adresse),
        CONSTRAINT fk_commande_facturation FOREIGN KEY (adresse_facturation_id) REFERENCES adresse(id_adresse)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE volume(
        id_volume INT NOT NULL AUTO_INCREMENT,
        valeur_cl INT,
        facteur_prix DECIMAL(4,2),
        PRIMARY KEY(id_volume)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE conteneur(
        id_conteneur INT NOT NULL AUTO_INCREMENT,
        libelle_conteneur VARCHAR(255),
        PRIMARY KEY(id_conteneur)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE type_eau(
        id_type_eau INT NOT NULL AUTO_INCREMENT,
        libelle_type_eau VARCHAR(255),
        PRIMARY KEY(id_type_eau)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE marque(
        id_marque INT NOT NULL AUTO_INCREMENT,
        libelle_marque VARCHAR(255),
        PRIMARY KEY(id_marque)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE boisson(
        id_boisson INT NOT NULL AUTO_INCREMENT,
        nom_boisson VARCHAR(100),
        prix_boisson DECIMAL(5, 2),
        description VARCHAR(255),
        image VARCHAR(255),
        stock INT,
        type_eau_id INT,
        marque_id INT,
        PRIMARY KEY (id_boisson),
        CONSTRAINT fk_boisson_type_eau  FOREIGN KEY (type_eau_id) REFERENCES type_eau(id_type_eau),
        CONSTRAINT fk_boisson_marque FOREIGN KEY (marque_id) REFERENCES marque(id_marque)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE conditionnement(
        id_conditionnement INT NOT NULL AUTO_INCREMENT,
        boisson_id INT,
        conteneur_id INT,
        volume_id INT,
        stock_conditionnement INT,
        prix_conditionnement DECIMAL(5, 2),
        PRIMARY KEY (id_conditionnement),
        CONSTRAINT fk_conditionnement_boisson FOREIGN KEY (boisson_id) REFERENCES boisson(id_boisson),
        CONSTRAINT fk_conditionnement_conteneur FOREIGN KEY (conteneur_id) REFERENCES conteneur(id_conteneur),
        CONSTRAINT fk_conditionnement_volume FOREIGN KEY (volume_id) REFERENCES volume(id_volume)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE ligne_commande(
        commande_id INT,
        conditionnement_id INT,
        prix NUMERIC(7,2),
        quantite INT,
        PRIMARY KEY(commande_id, conditionnement_id),
        CONSTRAINT fk_ligne_commande_commande FOREIGN KEY (commande_id) REFERENCES commande(id_commande),
        CONSTRAINT fk_ligne_commande_conditionnement FOREIGN KEY (conditionnement_id) REFERENCES conditionnement(id_conditionnement)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE ligne_panier(
        utilisateur_id INT,
        conditionnement_id INT,
        quantite INT,
        date_ajout DATE,
        PRIMARY KEY(utilisateur_id, conditionnement_id),
        CONSTRAINT fk_ligne_panier_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id_utilisateur),
        CONSTRAINT fk_ligne_panier_conditionnement FOREIGN KEY (conditionnement_id) REFERENCES conditionnement(id_conditionnement)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE commentaire(
        utilisateur_id INT,
        boisson_id INT,
        commentaire VARCHAR(255),
        date_publication DATE,
        valider INT DEFAULT 0,
        CONSTRAINT fk_commentaire_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id_utilisateur),
        CONSTRAINT fk_commentaire_boisson FOREIGN KEY (boisson_id) REFERENCES boisson(id_boisson)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE note(
        utilisateur_id INT,
        boisson_id INT,
        note INT,
        CONSTRAINT fk_note_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id_utilisateur),
        CONSTRAINT fk_note_boisson FOREIGN KEY (boisson_id) REFERENCES boisson(id_boisson)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    CREATE TABLE historique(
        utilisateur_id INT,
        boisson_id INT,
        date_achat DATE,
        CONSTRAINT fk_historique_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id_utilisateur),
        CONSTRAINT fk_historique_boisson FOREIGN KEY (boisson_id) REFERENCES boisson(id_boisson)
    ) CHARACTER SET utf8;
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO utilisateur(id_utilisateur,login,email,password,role,nom,est_actif) VALUES
    (1,'admin','admin@admin.fr',
        'sha256$dPL3oH9ug1wjJqva$2b341da75a4257607c841eb0dbbacb76e780f4015f0499bb1a164de2a893fdbf',
        'ROLE_admin','admin','1'),
    (2,'client','client@client.fr',
        'sha256$1GAmexw1DkXqlTKK$31d359e9adeea1154f24491edaa55000ee248f290b49b7420ced542c1bf4cf7d',
        'ROLE_client','client','1'),
    (3,'client2','client2@client2.fr',
        'sha256$MjhdGuDELhI82lKY$2161be4a68a9f236a27781a7f981a531d11fdc50e4112d912a7754de2dfa0422',
        'ROLE_client','client2','1');
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO etat VALUES
        (1, 'en cours de traitement'),
        (2, 'expédié');
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO adresse VALUES (NULL, 'Maison', '25 rue de IUT', '57000', 'Metz', 1, 2),
                           (NULL, 'Travail', '5 rue du stage', '75000', 'Paris', 1, 2),
                           (NULL, 'Appart Belfort', '30 rue du Chêne', '90000', 'Belfort', 1, 3),
                           (NULL, 'Maison Alpes', '5 rue du ski', '74400', 'Chamonix', 1, 3),
                           (NULL, 'Residence Annecy', '5 rue de la montagne', '74000', 'Annecy', 1, 3);

    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO commande VALUES
        (NULL, '2022-09-04', 2, 1, 1, 1),
        (NULL, '2022-12-06', 2, 1, 1, 2),
        (NULL, '2022-11-12', 2, 2, 2, 2),
        (NULL, '2022-02-05', 2, 1, 2, 1),
        (NULL, '2023-01-06', 2, 2, 1, 1),
        (NULL, '2023-04-01', 2, 2, 1, 2),
        (NULL, '2023-04-01', 2, 2, 1, 2),
        (NULL, '2022-09-02', 3, 1, 3, 4),
        (NULL, '2021-07-09', 3, 2, 4, 3),
        (NULL, '2023-02-02', 3, 2, 3, 3),
        (NULL, '2020-10-29', 3, 1, 3, 3),
        (NULL, '2022-12-01', 3, 2, 4, 4),
        (NULL, '2022-05-05', 3, 2, 5, 5),
        (NULL, '2022-08-02', 3, 1, 5, 4),
        (NULL, '2022-09-02', 3, 1, 5, 5),
        (NULL, '2022-10-02', 3, 2, 5, 4),
        (NULL, '2022-11-03', 3, 1, 5, 4),
        (NULL, '2022-06-06', 3, 1, 4, 5),
        (NULL, '2022-07-15', 3, 2, 5, 4);
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO volume VALUES
        (NULL, 25, 0.30),
        (NULL, 33, 0.50),
        (NULL, 50, 0.70),
        (NULL, 75, 1.10),
        (NULL, 100, 1.00),
        (NULL, 150, 1.40),
        (NULL, 200, 1.80),
        (NULL, 800, 6.50);
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO conteneur VALUES
        (NULL, 'Verre'),
        (NULL, 'Plastique'),
        (NULL, 'Aluminium'),
        (NULL, 'Carton');
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO type_eau VALUES
        (NULL, 'Plate'),
        (NULL, 'Pétillante'),
        (NULL, 'Fine bulles'),
        (NULL, 'Fruitée');
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO marque VALUES
        (NULL, 'Volvic'),
        (NULL, 'Evian'),
        (NULL, 'San Pellegrino'),
        (NULL, 'Vittel'),
        (NULL, 'Rozana'),
        (NULL, 'Cristaline'),
        (NULL, 'Contrex'),
        (NULL, 'Perrier'),
        (NULL, 'Courmayeur'),
        (NULL, 'Hépar');
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO boisson VALUES
        (NULL, 'Volvic Citronnade', 1.40,'test', 'plastique/fruitee/Citronade-plastique-1,5l.png', 300, 4, 1),
        (NULL, 'Volvic Fraise', 1.60,'test', 'plastique/fruitee/Fraise-plastique-1,5l.png', 500, 4, 1),
        (NULL, 'Volvic Exotique', 2.00,'test', 'plastique/fruitee/Exotique-plastique-1,5l.png', 1000, 4, 1),
        (NULL, 'Bidon Volvic', 8.45,'test', 'plastique/plate/Plate-plastique-8l.png', 50, 1, 1),
        (NULL, 'San Pellegrino', 1.20,'test', 'plastique/petillante/Pétillante-plastique-1l.jpg', 240, 2, 3),
        (NULL, 'Evian Bouteille Verre', 2.01,'test', 'verre/plate/Plate-verre-75cl.png', 150, 1, 2),
        (NULL, 'San Pellegrino Verre', 2.95,'test', 'verre/petillante/Pétillante-verre-50cl.jpg', 400, 2, 3),
        (NULL, 'Perrier Fruitée', 1.50,'test', 'placeholder.png', 100, 4, 8),
        (NULL, 'Contrex Pétillante', 1.75,'test', 'placeholder.png', 10, 2, 7),
        (NULL, 'Courmayeur Fine bulles', 1.25,'test', 'placeholder.png', 1000, 3, 9),
        (NULL, 'Vittel Pétillante', 1.3,'test', 'placeholder.png', 100, 2, 4),
        (NULL, 'Rozana Plate', 0.5,'test', 'placeholder.png', 100, 1, 5),
        (NULL, 'Evian Plate', 10.33,'test', 'placeholder.png', 10, 1, 2),
        (NULL, 'Hépar Fruitée', 12.5,'test', 'placeholder.png', 10, 4, 10),
        (NULL, 'Cristaline Fine bulles', 1.05,'test', 'placeholder.png', 1000, 3, 6);
    '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO conditionnement VALUES
        (NULL, 4, 2, 5, 1000, 1.10),
        (NULL, 1, 1, 1, 1000, 0.90),
        (NULL, 2, 2, 2, 1000, 1.50),
        (NULL, 3, 3, 3, 1000, 2.20),
        (NULL, 1, 1, 2, 1000, 1.10),
        (NULL, 4, 1, 5, 1000, 1.10),
        (NULL, 1, 2, 1, 1000, 0.90),
        (NULL, 2, 1, 2, 1000, 1.50),
        (NULL, 3, 1, 3, 1000, 2.20),
        (NULL, 1, 1, 4, 1000, 1.10);
        '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO ligne_commande VALUES
        (1, 1, 1.90, 3),
        (1, 2, 1.85, 1),
        (2, 3, 2.50, 2),
        (2, 5, 1.50, 3),
        (2, 4, 2.00, 1),
        (3, 3, 1.00, 1),
        (3, 2, 3.00, 5),
        (4, 2, 3.00, 5),
        (5, 5, 1.50, 2),
        (5, 2, 2.50, 1),
        (6, 1, 1.90, 3);
        '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO note VALUES
        (2, 1, 2),
        (2, 2, 2),
        (2, 3, 5),
        (2, 4, 2),
        (2, 5, 2),
        (2, 6, 1),
        (2, 7, 2),
        (2, 8, 2),
        (2, 9, 2),
        (2, 10, 2),
        (2, 11, 3),
        (2, 12, 2),
        (2, 13, 2),
        (2, 14, 2),
        (2, 15, 2),
        (3, 1, 2),
        (3, 2, 2),
        (3, 3, 2),
        (3, 4, 2),
        (3, 5, 2),
        (3, 6, 2),
        (3, 7, 3),
        (3, 8, 2),
        (3, 9, 2),
        (3, 10, 1),
        (3, 11, 2),
        (3, 12, 2),
        (3, 13, 2),
        (3, 14, 2),
        (3, 15, 2);
        '''
    mycursor.execute(sql)

    sql = ''' 
    INSERT INTO commentaire VALUES
        (2, 1,'Très bon produit','2019-01-01',0),
        (2, 2,'Très bon produit','2019-01-01',0),
        (2, 3,'Très bon produit','2019-01-01',0),
        (2, 4,'Très bon produit','2019-01-01',0),
        (2, 5,'Très bon produit','2019-01-01',0),
        (2, 6,'Très bon produit','2019-01-01',0),
        (2, 7,'Très bon produit','2019-01-01',0),
        (2, 8,'Très bon produit','2019-01-01',0),
        (2, 9,'Très bon produit','2019-01-01',0),
        (2, 10,'Très bon produit','2019-01-01',0),
        (2, 11,'Très bon produit','2019-01-01',0),
        (2, 12,'Très bon produit','2019-01-01',0),
        (2, 13,'Très bon produit','2019-01-01',0),
        (2, 14,'Très bon produit','2019-01-01',0),
        (3, 1,'Très bon produit','2019-01-01',0),
        (3, 2,'Très bon produit','2019-01-01',0),
        (3, 3,'Très bon produit','2019-01-01',0),
        (3, 4,'Très bon produit','2019-01-01',0),
        (3, 5,'Très bon produit','2019-01-01',0),
        (3, 6,'Très bon produit','2019-01-01',0),
        (3, 7,'Très bon produit','2019-01-01',0),
        (3, 8,'Très bon produit','2019-01-01',0),
        (3, 9,'Très bon produit','2019-01-01',0),
        (3, 10,'Très bon produit','2019-01-01',0),
        (3, 11,'Très bon produit','2019-01-01',0),
        (3, 12,'Très bon produit','2019-01-01',0),
        (3, 13,'Très bon produit','2019-01-01',0),
        (3, 14,'Très bon produit','2019-01-01',0);
        '''
    mycursor.execute(sql)

    get_db().commit()
    return redirect('/')

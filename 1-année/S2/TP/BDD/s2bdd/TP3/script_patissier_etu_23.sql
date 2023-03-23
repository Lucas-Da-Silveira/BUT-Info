-- DROP TABLE IF EXISTS ;
DROP TABLE IF EXISTS realisation;
DROP TABLE IF EXISTS estRattache;
DROP TABLE IF EXISTS personne;
DROP TABLE IF EXISTS recette;
DROP TABLE IF EXISTS patisserie;
DROP TABLE IF EXISTS boutique;

-- CREATE TABLE 
CREATE TABLE boutique(
   idBoutique INT,
   nom VARCHAR(50),
   adresse VARCHAR(50),
   codePostal INT,
   ville VARCHAR(50),
   PRIMARY KEY(idBoutique)
);

CREATE TABLE patisserie(
   idPatisserie VARCHAR(50),
   nom VARCHAR(50),
   categorie VARCHAR(50),
   prixUnitaire INT,
   PRIMARY KEY(idPatisserie)
);

CREATE TABLE recette(
   idPatisserie VARCHAR(50),
   numDeclinaison INT,
   ingredients VARCHAR(500),
   description VARCHAR(50),
   auteur VARCHAR(50),
   annee VARCHAR(50),
   PRIMARY KEY(idPatisserie, numDeclinaison),
   FOREIGN KEY(idPatisserie) REFERENCES patisserie(idPatisserie)
);

CREATE TABLE personne(
   idPersonne INT,
   nomPersonne VARCHAR(50),
   prenom VARCHAR(50),
   anneeNaissance INT,
   PRIMARY KEY(idPersonne)
);

CREATE TABLE estRattache(
    idPersonne INT,
   idBoutique INT,
   dateEmbauche DATE,
   PRIMARY KEY(idPersonne, idBoutique,dateEmbauche),
   FOREIGN KEY(idBoutique) REFERENCES boutique(idBoutique),
   FOREIGN KEY(idPersonne) REFERENCES personne(idPersonne)
);

CREATE TABLE realisation(
    idPersonne INT,
   idPatisserie VARCHAR(50),
   numDeclinaison INT,
   dateRealisation DATE,
   nbRealisation INT,
   PRIMARY KEY(idPatisserie, numDeclinaison, idPersonne, dateRealisation),
   FOREIGN KEY(idPatisserie, numDeclinaison) REFERENCES recette(idPatisserie, numDeclinaison),
   FOREIGN KEY(idPersonne) REFERENCES personne(idPersonne)
);


INSERT INTO patisserie(idPatisserie, nom, categorie, prixUnitaire) VALUES (1, 'Baba au Rhum', 'gâteau', 4.15);
INSERT INTO patisserie VALUES (2, 'éclair au chocolat', 'gâteau', 3.5);
INSERT INTO patisserie VALUES (3, 'tarte aux pommes', 'tarte', 30.5);
INSERT INTO patisserie VALUES (4, 'tarte aux poires', 'tarte', 31.5);
INSERT INTO patisserie VALUES (5, 'croissant nature', 'viennoiserie', 30.5);
INSERT INTO patisserie VALUES (6, 'brioche', 'viennoiserie', 31.5);
INSERT INTO patisserie VALUES (7, 'gâteau aux marrons', 'gâteau', 21.5);
INSERT INTO patisserie VALUES (8, 'bûche poires marrons et chocolat', 'gâteau', 41.5);
INSERT INTO patisserie VALUES (9, 'macaron marrons', 'macaron', 3.5);
INSERT INTO patisserie VALUES (10, 'macaron chocolat', 'macaron', 3.5);

INSERT INTO recette(idPatisserie, numDeclinaison, ingredients, description, auteur, annee)
VALUES (1, 0, 'Beurre, eau, farine, lait, levure, oeufs, rhum', 'Mélanger...', 'Nicolas Stohrer', 1835);
INSERT INTO recette VALUES (1, 1, 'Beurre, eau, farine, lait, levure, oeufs, rhum', 'Mélanger...', 'poulain leo', 1990);

INSERT INTO recette VALUES (2, 0, 'Beurre, chocolat, crème fleurette, eau, farine, lait, œufs, sel, sucre', 'Mélanger...', '', Null);
INSERT INTO recette VALUES (3, 0, 'pommes, pâte feuilletée, crème fleurette, œufs, sucre', 'Mélanger...',Null, Null);
INSERT INTO recette VALUES (4, 0, 'poires, pâte feuilletée, crème fleurette, œufs, sucre', 'Mélanger...',Null, Null);
INSERT INTO recette VALUES (5, 0, 'Beurre, eau, farine, sucre', 'Mélanger...', '', Null);
INSERT INTO recette VALUES (6, 0, 'Beurre, eau, farine, sucre', 'Mélanger...', '', Null);
INSERT INTO recette VALUES (7, 0, 'Beurre, eau, farine, marrons, sucre', 'Mélanger...', '', Null);
INSERT INTO recette VALUES (8, 0, 'Beurre, eau, farine, marrons, crème, sucre', 'Mélanger...', '', Null);
INSERT INTO recette VALUES (8, 1, 'Beurre, eau, farine, marrons, crème, sucre', 'Mélanger...', 'Mantey jean', 2015);
INSERT INTO recette VALUES (8, 2, 'Beurre, eau, farine, marrons, crème, sucre', 'Mélanger...', 'Mantey jean', 2020);
INSERT INTO recette VALUES (9, 0, 'Beurre, eau, farine, marrons, crème, sucre', 'Mélanger...', '', NULL);
INSERT INTO recette VALUES (10, 0, 'Beurre, eau, farine, chocolat, crème, sucre', 'Mélanger...', '', NULL);
INSERT INTO recette VALUES (10, 1, 'Beurre, eau, farine, chocolat, crème, sucre', 'Mélanger...', 'Mantey jean', 2020);

INSERT INTO personne(idPersonne, nomPersonne, prenom, anneeNaissance)
VALUES (1, 'DURAND', 'Jean', '1985');
INSERT INTO personne VALUES (2, 'DUPOND', 'Paul', '2000');
INSERT INTO personne VALUES (3, 'MANTEY', 'Jean', '1980');
INSERT INTO personne VALUES (4, 'Klebber', 'edouard', '1999');
INSERT INTO personne VALUES (5, 'poulain', 'leo', '1965');
INSERT INTO personne VALUES (6, 'perreira', 'philippe', '1990');

INSERT INTO boutique(idBoutique, nom, adresse, codePostal, ville)
VALUES (1, 'MANTEY', '16 rue principale', '90300', 'Valdoie');
INSERT INTO boutique VALUES (2, 'La RoseMontoise', '1 rue de la savoureuse', '90300', 'Valdoie');
INSERT INTO boutique VALUES (3, 'Klebber', '1 rue du chateau', '90000', 'Belfort');
INSERT INTO boutique VALUES (4, 'le bon pain', '9 rue du chateau', '90000', 'Belfort');

INSERT INTO estRattache(idPersonne, idBoutique, dateEmbauche) values
(1,1,'2000-06-01');
INSERT INTO estRattache values(2,2,'2001-06-01');
INSERT INTO estRattache values(3,1,'2001-06-01');
INSERT INTO estRattache values(4,3,'2001-06-01');
INSERT INTO estRattache values(5,2,'1990-06-01');

INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (1, 1, 0, '2021-12-02', 120);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (1, 9, 0, '2021-10-02', 220);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (1, 9, 0, '2020-10-02', 220);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (1, 8, 1, '2021-12-02', 20);

INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (1, 1, 0, '2021-9-02', 10);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (2, 1, 1, '2021-8-02', 5);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (3, 1, 0, '2021-7-02', 4);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (2, 1, 0, '2021-9-02', 4);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (3, 1, 0, '2020-7-02', 4);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (4, 1, 0, '2020-7-02', 4);

INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (1, 2, 0, '2021-12-02', 10);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (2, 3, 0, '2021-12-02', 5);
INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (3, 3, 0, '2021-12-02', 4);

INSERT INTO realisation(idPersonne, idPatisserie, numDeclinaison, dateRealisation, nbRealisation)
 VALUES (4, 6, 0, '2021-12-02', 400);




-- test avec des variables sur datagrip

SELECT idPersonne, nomPersonne, prenom, anneeNaissance
FROM personne
WHERE idPersonne=:id1 or idPersonne=:id2;


-- 1. Donner le nombre de catégorie différente de pâtisseries triée selon l’ordre lexicographique (les doublons devront
-- être supprimés).

SELECT COUNT(DISTINCT categorie) as nb_categories
FROM patisserie
ORDER BY categorie;

-- +--------------+
-- | nb_categorie |
-- +--------------+
-- |            4 |
-- +--------------+

-- 2. Donner le nom des pâtisseries de catégorie ’gâteau’ ayant des marrons parmi ses ingrédients. Le résultat
-- sera trié selon l’ordre lexicographique inverse sur le nom de la pâtisserie.

SELECT DISTINCT nom
FROM patisserie
WHERE categorie LIKE '%gâteau' AND nom LIKE '%marron%'
ORDER BY nom DESC;

-- +-----------------------------------+
-- | nom                               |
-- +-----------------------------------+
-- | gâteau aux marrons                |
-- | bûche poires marrons et chocolat  |
-- +-----------------------------------+


-- 3. Donner le nom et le prénom des pâtissiers ayant réalisé plus de 100 macarons dans une journée entre
-- le 1 septembre 2021 et le 31 décembre 2021. On précisera également la date la réalisation.

SELECT DISTINCT nomPersonne, prenom, dateRealisation
FROM personne
JOIN realisation ON personne.idPersonne = realisation.idPersonne
JOIN patisserie ON realisation.idPatisserie = patisserie.idPatisserie
WHERE nbRealisation > 100 AND dateRealisation BETWEEN '2021-09-01' AND '2021-12-31' AND nom LIKE '%macaron%'
ORDER BY nomPersonne, prenom, dateRealisation;

-- +-------------+--------+-----------------+
-- | nomPersonne | prenom | dateRealisation |
-- +-------------+--------+-----------------+
-- | DURAND      | Jean   | 2021-10-02      |
-- +-------------+--------+-----------------+


-- 4. Donner le nombre de recettes différentes pour réaliser un macaron. Le résultat sera renommé en ’nbMacaronsDifférents’. Le résultat
-- sera trié selon l'ordre lexicographique .

SELECT COUNT(DISTINCT numDeclinaison) as nbMacaronsDifferents, nom
FROM patisserie,recette
WHERE patisserie.idPatisserie = recette.idPatisserie AND nom LIKE '%macaron%'
GROUP BY nom;


-- +--------------------

-- +----------------------+------------------+
-- | nbMacaronsDifferents | nom              |
-- +----------------------+------------------+
-- |                    2 | macaron chocolat |
-- |                    1 | macaron marrons  |
-- +----------------------+------------------+


-- 5. Donner le nom et le prix unitaire de la pâtisserie la plus chère 

SELECT nom, prixUnitaire
FROM patisserie
WHERE prixUnitaire = (SELECT MAX(prixUnitaire) FROM patisserie)
ORDER BY nom;

-- +-----------------------------------+--------------+
-- | nom                               | prixUnitaire |
-- +-----------------------------------+--------------+
-- | bûche poires marrons et chocolat  | 41.5         |
-- +-----------------------------------+--------------+



-- 6. Donner les pâtissiers qui n’ont jamais réalisé de ’Baba au Rhum’ en 2021.


SELECT * FROM personne
WHERE idPersonne NOT IN (
SELECT idPersonne FROM realisation WHERE idPatisserie = 1 AND dateRealisation LIKE '2021%'
);

-- +------------+-------------+----------+----------------+
-- | idPersonne | nomPersonne | prenom   | anneeNaissance |
-- +------------+-------------+----------+----------------+
-- |          4 | Klebber     | edouard  |           1999 |
-- |          5 | poulain     | leo      |           1965 |
-- |          6 | perreira    | philippe |           1990 |
-- +------------+-------------+----------+----------------+


-- 7 : Pour chaque boutique de la ville valdoie, donner le montant en euros des pâtisseries dans le mois de décembre 2021.
-- Le résultat sera renommé en ’prod12-2021-euros’

SELECT nom, SUM(prixUnitaire*nbRealisation) as 'prod12-2021-euros', nom
FROM personne
JOIN realisation ON personne.idPersonne = realisation.idPersonne
JOIN patisserie ON realisation.idPatisserie = patisserie.idPatisserie
WHERE dateRealisation LIKE '2021-12%';

-- +-----------------+-----------------------+-----------------+
-- | nom             | prod12−2021−euros     | nom             |
-- +-----------------+-----------------------+-----------------+
-- | MANTEY          |                  1485 | MANTEY          |
-- | La RoseMontoise |                 152.5 | La RoseMontoise |
-- +-----------------+-----------------------+-----------------+


-- 8 : Donner le nom et le prix unitaire de la pâtisserie la plus chère pour chaque catégorie de pâtisserie.


-- +--------------+-----------------------------------+--------------+
-- | categorie    | nom                               | prixUnitaire |
-- +--------------+-----------------------------------+--------------+
-- | tarte        | tarte aux poires                  | 31.5         |
-- | viennoiserie | brioche                           | 31.5         |
-- | gâteau       | bûche poires marrons et chocolat  | 41.5         |
-- | macaron      | macaron marrons                   | 3.5          |
-- | macaron      | macaron chocolat                  | 3.5          |
-- +--------------+-----------------------------------+--------------+


-- 9 :   Donner les pâtisseries réalisées par plus de 2 pâtissiers différents en 2021.


-- ------------------


-- 10 :  Donner les recettes initiales dont on ne connaît pas l’auteur. On précisera le nom de la pâtisserie avec
-- la recette.

-- 11 :  Donner les boutiques de la ville de valdoie qui ont employé le pâtissier ’jean Mantey’. On précisera pour chaque
-- boutique la date d’embauche et le résultat sera trié par rapport à cette date selon l’ordre chronologique
-- inverse.

-- 12 :   Donner les personnes de moins de 40 ans (cette année) qui sont auteurs d’au moins une recette.

-- 13 :    Donner les boutiques n’ayant jamais proposé de pâtisserie à base de Rhum.


-- 14 :  Donner les pâtissiers qui ont déjà réalisés au moins une recette de chacune des pâtisseries du catalogue.
-- (indice : Pour ces pâtissiers, il n’existe aucune pâtisserie pour laquelle il n’existe aucune réalisation
-- effectuée par le pâtissier)





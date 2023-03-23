-- commentaire  -- suivi d'un un espace !  (ou  # mais ce n'est pas normalisé)
-- se deplacer dans le bon dossier ( cd S1_BDD)
-- ouvrir un fichier : subl TP1.sql

# mysql --user=login --password=jjmm --hostname=serveurmysql  --database=BDD_login
-- mot de passe : jjmm (date de naissance)

# mysql> system ls
# mysql> source TP1.sql
# mysql> help

-- visualiser les bases de données
# show databases;

-- visualiser les tables dans la BDD
# show tables;

DROP TABLE etudiant;

-- CREATE TABLE etudiant (
--     id_etudiant INT
--     , nom_etudiant VARCHAR(25)
-- );

CREATE TABLE etudiant (
    id_etudiant INT
    , nom_etudiant VARCHAR(25)
    , PRIMARY KEY(id_etudiant)
);

DESCRIBE etudiant;

INSERT INTO etudiant (id_etudiant, nom_etudiant) VALUES (1,'dupond');
INSERT INTO etudiant  VALUES (2,'durand');
INSERT INTO etudiant  VALUES (1,'Gauthier');
INSERT INTO etudiant  VALUES (NULL,'laval');
INSERT INTO etudiant (nom_etudiant) VALUES ('lepaul');

SELECT * FROM etudiant;


--  remplacer id_etudiant INT,
--  par  id_etudiant INT NOT NULL,
--  puis par  id_etudiant INT NOT NULL UNIQUE,
--  puis par  id_etudiant INT PRIMARY KEY,
--  puis par  id_etudiant INT,  et rajouter PRIMARY KEY(id_etudiant)


-- CREATE TABLE etudiant (
--     id_etudiant INT NOT NULL
--     , nom_etudiant VARCHAR(25)
-- );

-- CREATE TABLE etudiant (
--     id_etudiant INT NOT NULL UNIQUE,
--     , nom_etudiant VARCHAR(25)
-- );

-- CREATE TABLE etudiant (
--     id_etudiant INT  PRIMARY KEY
--     , nom_etudiant VARCHAR(25)
-- );

-- CREATE TABLE etudiant (
--     id_etudiant INT
--     , nom_etudiant VARCHAR(25)
--     , PRIMARY KEY(id_etudiant)
-- );


DROP TABLE IF EXISTS etudiant;

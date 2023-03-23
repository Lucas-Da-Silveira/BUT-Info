-- Pour se connecter sur MySQL
--mysql --user=ldasilve --password=1603 --host=serveurmysql --database=BDD_ldasilve

DROP TABLE etudiant;

CREATE TABLE etudiant (
  id_etudiant INT
  , nom_etudiant VARCHAR(25)
  , date_de_naissance INT
);

-- CREATE TABLE etudiant (
--   id_etudiant INT NOT NULL
--   , nom_etudiant VARCHAR(25)
-- );

-- CREATE TABLE etudiant (
--   id_etudiant INT NOT NULL UNIQUE
--   , nom_etudiant VARCHAR(25)
-- );

-- CREATE TABLE etudiant (
--   id_etudiant INT PRIMARY KEY
--   , nom_etudiant VARCHAR(25)
-- );

-- CREATE TABLE etudiant (
--   id_etudiant INT
--   , nom_etudiant VARCHAR(25)
--   , PRIMARY KEY (id_etudiant)
-- );

DESCRIBE etudiant;

INSERT INTO etudiant (id_etudiant, nom_etudiant, date_de_naissance) VALUES (1,'dupond',2000);
INSERT INTO etudiant VALUES (2,'durand',NULL);
INSERT INTO etudiant VALUES (1,'Gauthier',1999);
INSERT INTO etudiant VALUES (NULL,'laval',2002);
INSERT INTO etudiant (nom_etudiant) VALUES ('lepaul');

SELECT * FROM etudiant;

SHOW CREATE TABLE etudiant;

DROP TABLE IF EXISTS etudiant;

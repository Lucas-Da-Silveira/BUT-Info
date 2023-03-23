-- Pour se connecter sur MySQL
-- mysql --user=ldasilve --password=1603 --host=serveurmysql --database=BDD_ldasilve


DROP TABLE IF EXISTS etudiant;

CREATE TABLE etudiant (
    id_etudiant INT UNSIGNED AUTO_INCREMENT,
    nom VARCHAR(20),
    adresse TEXT,
    code_postal VARCHAR(5),
    telephone VARCHAR(10),
    date_de_naissance DATE,
    PRIMARY KEY (id_etudiant)
);

DESCRIBE etudiant;

INSERT INTO etudiant VALUES
    (NULL,'dupond' , 'rue de chateau', '01000', '0123456789', '1990-12-31');

INSERT INTO etudiant (nom,adresse,telephone, date_de_naissance )VALUES
    ('durand', 'rue de pont', '0123456789', '1990-12-31');

INSERT INTO etudiant VALUES
    (NULL, 'DAVAL', 'rue du CHATEAU', '90000', '0123456789', '1990-12-31');

RENAME TABLE etudiant TO etudiants;
SHOW TABLE

ALTER TABLE etudiant ADD groupe VARCHAR(20);
DESCRIBE etudiants;
SELECT * FROM etudiant;
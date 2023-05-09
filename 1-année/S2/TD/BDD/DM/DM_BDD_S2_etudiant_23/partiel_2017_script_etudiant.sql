
DROP TABLE IF EXISTS ESTAFFECTE, PANNE, LIGNE,CHAUFFEUR, BUS, MARQUE, TYPEPANNE;

-- tables sans les contraintes

CREATE TABLE MARQUE(
        idMarque Int AUTO_INCREMENT
        , intitule Varchar (255)
        , PRIMARY KEY (idMarque )
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;

CREATE TABLE BUS(
        idBus    Int AUTO_INCREMENT
        , modele   Varchar (255)
        , idMarque Int
        , PRIMARY KEY (idBus )
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;


CREATE TABLE CHAUFFEUR(
        idChauffeur Int AUTO_INCREMENT
        , nom         Varchar (255)
        , prenom      Varchar (255)
        , adresse     Varchar (255)
        , PRIMARY KEY (idChauffeur )
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;


CREATE TABLE LIGNE(
        idLigne Int AUTO_INCREMENT 
        , libelle Varchar (255) 
        , PRIMARY KEY (idLigne )
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;



CREATE TABLE TYPEPANNE(
        idTypePanne int (11)  AUTO_INCREMENT 
        , libelle     Varchar (255) 
        , PRIMARY KEY (idTypePanne )
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;


CREATE TABLE PANNE(
        idBus       Int 
        , idTypePanne Int 
        , datePanne   Date 
        , PRIMARY KEY (idBus ,idTypePanne, datePanne )
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;

CREATE TABLE ESTAFFECTE(
   idBus INT,
   idChauffeur INT,
   idLigne INT,
   dateAffectation DATE,
   PRIMARY KEY(idChauffeur,idLigne, idBus, dateAffectation),
   FOREIGN KEY(idChauffeur) REFERENCES CHAUFFEUR(idChauffeur),
   FOREIGN KEY(idLigne) REFERENCES LIGNE(idLigne),
   FOREIGN KEY(idBus) REFERENCES BUS(idBus)
);

LOAD DATA LOCAL INFILE '/home/lucas/BUT-Info/1-année/S2/TD/BDD/DM/DM_BDD_S2_etudiant_23/MARQUE.csv' INTO TABLE MARQUE CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE '/home/lucas/BUT-Info/1-année/S2/TD/BDD/DM/DM_BDD_S2_etudiant_23/BUS.csv' INTO TABLE BUS CHARACTER SET utf8  FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE '/home/lucas/BUT-Info/1-année/S2/TD/BDD/DM/DM_BDD_S2_etudiant_23/CHAUFFEUR.csv' INTO TABLE CHAUFFEUR CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE '/home/lucas/BUT-Info/1-année/S2/TD/BDD/DM/DM_BDD_S2_etudiant_23/LIGNE.csv' INTO TABLE LIGNE CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE '/home/lucas/BUT-Info/1-année/S2/TD/BDD/DM/DM_BDD_S2_etudiant_23/TYPEPANNE.csv' INTO TABLE TYPEPANNE CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE '/home/lucas/BUT-Info/1-année/S2/TD/BDD/DM/DM_BDD_S2_etudiant_23/PANNE.csv' INTO TABLE PANNE CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE '/home/lucas/BUT-Info/1-année/S2/TD/BDD/DM/DM_BDD_S2_etudiant_23/ESTAFFECTE.csv' INTO TABLE ESTAFFECTE CHARACTER SET utf8 FIELDS TERMINATED BY ',';


SELECT idBus
FROM BUS
JOIN MARQUE ON BUS.idMarque = MARQUE.idMarque
WHERE MARQUE.intitule = 'marquedebus1';

SELECT COUNT(*) AS "nombre de chauffeurs"
FROM ESTAFFECTE
WHERE dateAffectation = '2016-12-02';

SELECT BUS.idBus, CHAUFFEUR.nom
FROM BUS
JOIN ESTAFFECTE ON BUS.idBus = ESTAFFECTE.idBus
JOIN CHAUFFEUR ON ESTAFFECTE.idChauffeur = CHAUFFEUR.idChauffeur
WHERE ESTAFFECTE.idLigne = 1 AND ESTAFFECTE.dateAffectation = '2016-12-02';

SELECT MARQUE.idMarque, MARQUE.intitule AS "intitulé marque", COUNT(PANNE.idBus) AS "nb de pannes"
FROM MARQUE
JOIN BUS ON MARQUE.idMarque = BUS.idMarque
JOIN PANNE ON BUS.idBus = PANNE.idBus
WHERE YEAR(PANNE.datePanne) = 2016
GROUP BY MARQUE.idMarque, MARQUE.intitule;

SELECT idBus, modele
FROM BUS
WHERE idMarque = (
   SELECT idMarque
   FROM BUS
   WHERE idBus = 6
);

SELECT idChauffeur, nom
FROM CHAUFFEUR
WHERE idChauffeur IN (
   SELECT idChauffeur
   FROM ESTAFFECTE
   WHERE idLigne = 1
   AND MONTH(dateAffectation) = 12
   AND YEAR(dateAffectation) = 2016
);

SELECT idChauffeur, nom
FROM CHAUFFEUR
WHERE idChauffeur IN (
   SELECT idChauffeur
   FROM ESTAFFECTE
   WHERE idLigne != 1
   AND MONTH(dateAffectation) = 12
   AND YEAR(dateAffectation) = 2016
);

SELECT CHAUFFEUR.nom, CHAUFFEUR.prenom, COUNT(*) AS "nb affectation ligne 1"
FROM CHAUFFEUR
JOIN ESTAFFECTE ON CHAUFFEUR.idChauffeur = ESTAFFECTE.idChauffeur
WHERE EXISTS (
   SELECT idChauffeur
   FROM ESTAFFECTE
   WHERE idChauffeur = CHAUFFEUR.idChauffeur
   AND idLigne = 1
   AND MONTH(dateAffectation) = 12
   AND YEAR(dateAffectation) = 2016
   GROUP BY idChauffeur
   HAVING COUNT(*) > 4
)
GROUP BY CHAUFFEUR.idChauffeur, CHAUFFEUR.nom, CHAUFFEUR.prenom;
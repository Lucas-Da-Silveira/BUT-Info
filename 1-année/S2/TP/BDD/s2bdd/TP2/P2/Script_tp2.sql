DROP TABLE IF EXISTS classement ; 
DROP TABLE IF EXISTS comporte ;
DROP TABLE IF EXISTS competition ; 
DROP TABLE IF EXISTS skieur ; 
DROP TABLE IF EXISTS station ; 
DROP TABLE IF EXISTS specialite ;
DROP TABLE IF EXISTS classement, comporte , competition , skieur , station , specialite;


CREATE TABLE IF NOT EXISTS specialite (
idSpecialite INT AUTO_INCREMENT 
, libelleSpecialite VARCHAR(100) 
, PRIMARY KEY (idSpecialite) 
);

CREATE TABLE IF NOT EXISTS station (
idStation INT AUTO_INCREMENT
, nomStation VARCHAR(100)
, altitude FLOAT
, pays VARCHAR(100) 
, PRIMARY KEY (idStation)
);  

CREATE TABLE IF NOT EXISTS skieur (
idSkieur INT AUTO_INCREMENT 
, nomSkieur VARCHAR(100)
, specialite_id INT 
, station_id INT
, PRIMARY KEY (idSkieur)
, CONSTRAINT fk_skieur_station FOREIGN KEY (station_id) REFERENCES station(idStation)
, CONSTRAINT fk_skieur_specialite FOREIGN KEY (specialite_id) REFERENCES specialite(idSpecialite)  
);  


CREATE TABLE  IF NOT EXISTS competition (
idCompet INT AUTO_INCREMENT 
, libelleCompet VARCHAR(100) 
, dateCompet DATE 
, station_id INT 
, PRIMARY KEY (idCompet)
, CONSTRAINT fk_competition_station FOREIGN KEY (station_id) REFERENCES station (idStation)
);


CREATE TABLE IF NOT EXISTS comporte
(
    competition_id INT,
    specialite_id  INT,
    PRIMARY KEY (specialite_id, competition_id),
    CONSTRAINT fk_comporte_specialite FOREIGN KEY (specialite_id) REFERENCES specialite (idSpecialite),
    CONSTRAINT fk_comporte_competition FOREIGN KEY (competition_id) REFERENCES competition (idCompet)
); 


CREATE TABLE IF NOT EXISTS  classement (
skieur_id INT 
, competition_id INT 
, classement INT
, CONSTRAINT fk_classement_skieur FOREIGN KEY (skieur_id) REFERENCES skieur (idSkieur)
, CONSTRAINT fk_classement_competiton FOREIGN KEY (competition_id) REFERENCES competition (idCompet)
);

LOAD DATA LOCAL
    INFILE 'SPECIALITE.csv' INTO TABLE specialite
    FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'STATION.csv' INTO TABLE station
FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'SKIEUR.csv' INTO TABLE skieur
FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'COMPETITION.csv' INTO TABLE competition
FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'COMPORTE.csv' INTO TABLE comporte
FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'CLASSEMENT.csv' INTO TABLE classement
FIELDS TERMINATED BY ',';
-- system mysqlimport -u user_S2 -h serveurmysql -pbdd BDDuser_S2 -d -L --fields-terminated-by=, SPECIALITE.csv STATION.csv SKIEUR.csv COMPETITION.csv COMPORTE.csv CLASSEMENT.csv

-- SELECT * FROM specialite;
-- SELECT * FROM station;
-- SELECT * FROM skieur;
-- SELECT * FROM competition;


-- requete 1
-- 1.Nombre de skieurs ayant participé à au moins une compétition.
SELECT '1 : Nombre de skieurs ayant participé à au moins une compétition' as 'requetes';

SELECT COUNT(DISTINCT skieur_id) AS NbreSkieurDansUneCompet 
FROM classement;

-- 2.Nom de la station de chaque skieur (affichage : nom skieur + nom station)
SELECT '2 : Nom de la station de chaque skieur (affichage : nom skieur + nom station)' as 'requetes';

SELECT skieur.nomSkieur, station.nomStation
FROM skieur
INNER JOIN station
ON  skieur.station_id = station.idStation
ORDER BY  station.nomStation,skieur.nomSkieur;

SELECT sk.nomSkieur, st.nomStation
FROM skieur AS sk
         INNER JOIN station AS st ON sk.station_id = st.idStation
ORDER BY st.nomStation, sk.nomSkieur;

-- SELECT skieur.nomSkieur, station.nomStation
-- FROM skieur, station
-- WHERE skieur.station_id = station.idStation
-- ORDER BY  st.nomStation,skieur.nomSkieur;


-- 3.Classement de la compétition de libellé ‘compet’ (affichage : nom skieur + classement)
SELECT '3 : Classement de la compétition de libellé ‘compet’ (affichage : nom skieur + classement)' as 'requetes';

SELECT sk.nomSkieur, cl.classement, ct.libelleCompet 
FROM competition AS ct
INNER JOIN  classement AS cl ON ct.idCompet = cl.competition_id
INNER JOIN  skieur AS sk ON cl.skieur_id = sk.idSkieur
WHERE 
ct.libelleCompet like '%compet%'
ORDER BY ct.libelleCompet,cl.classement;


-- 4.Liste des compétitions s’étant déroulées à ‘Tignes’ (nom de station) avec leur vainqueur
-- (affichage : libellé Compétition, nom skieur vainqueur)
SELECT '4 : Liste des compétitions s’étant déroulées à ‘Tignes’ avec leur vainqueur' as 'requetes';

SELECT comp.libelleCompet, sk.nomSkieur
FROM classement AS cl
         INNER JOIN skieur AS sk ON cl.skieur_id = sk.idSkieur
         INNER JOIN competition AS comp ON cl.competition_id = comp.idCompet
         INNER JOIN station AS s ON comp.station_id = s.idStation
WHERE s.nomStation like 'Tignes%'
  AND cl.classement = 1;

-- 5. Nombre de compétitions se déroulant dans chaque station (affichage : id station + nom station + nb de compétition)
SELECT '5 : Nombre de compétitions se déroulant dans chaque station ' as 'requetes';

SELECT st.idStation , st.nomStation, COUNT(st.idStation)as nbrDeCompet
FROM station st
INNER JOIN competition c 
ON c.station_id = st.idStation 
GROUP BY st.idStation , st.nomStation
ORDER BY st.nomStation;

SELECT '6 : Nombre de victoires à ‘Tignes’ pour chaque skieur ' as 'requetes';

SELECT skieur.idSkieur, skieur.nomSkieur
, COUNT(*) as NbreDeVictoires
FROM classement 
INNER JOIN  skieur  ON classement.skieur_id = skieur.idSkieur
INNER JOIN competition ON classement.competition_id = competition.idCompet
INNER JOIN station ON competition.station_id=station.idStation
WHERE 
classement.classement=1
AND station.nomStation like 'Tignes'
GROUP BY skieur.idSkieur, skieur.nomSkieur;

SELECT '7. Noms des skieurs ayant toujours terminé premier (nom skieur)' as 'requetes';

SELECT DISTINCT sk.nomSkieur
FROM classement cl
INNER JOIN skieur sk ON sk.idSkieur = cl.skieur_id
WHERE cl.classement = 1
AND sk.nomSkieur NOT IN (
SELECT sk.nomSkieur
FROM classement cl
INNER JOIN skieur sk
	ON sk.idSkieur = cl.skieur_id
WHERE cl.classement <>1 );

SELECT DISTINCT sk.nomSkieur
FROM classement cl
         INNER JOIN skieur sk ON sk.idSkieur = cl.skieur_id
WHERE cl.classement = 1
  AND sk.idSkieur NOT IN (
    SELECT cl.skieur_id
    FROM classement cl
    WHERE cl.classement <> 1);

-- 8. Noms des skieurs ayant toujours terminé dans les 3 premiers (nom skieur)


DELETE FROM classement;
DELETE FROM comporte;
-- DELETE FROM competition;

-- TRUNCATE competition;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE competition;
SET FOREIGN_KEY_CHECKS = 1;

LOAD DATA LOCAL INFILE 'COMPETITIONv2.csv' INTO TABLE competition
FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'COMPORTEv2.csv' INTO TABLE comporte
FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'CLASSEMENTv2.csv' INTO TABLE classement
FIELDS TERMINATED BY ',';


SELECT DISTINCT sk.nomSkieur, cl.classement, st.nomStation, st2.nomStation
FROM classement
INNER JOIN skieur AS sk ON sk.idSkieur = cl.skieur_id
INNER JOIN competition AS cl ON cl.idCompet = cl.competition_id
INNER JOIN station AS st ON st.idStation = st.station_id
INNER JOIN station AS st2 ON st2.idStation = st2.station_id
WHERE st.nomStation LIKE 'Tignes' AND cl.classement <= 3
AND sk.idSkieur NOT IN (
  SELECT classement.skieur_id
  FROM classement
  INNER JOIN competition ON classement.competition_id = competition.idCompet
  INNER JOIN station ON competition.station_id = station.idStation
  WHERE classement.classement > 3
    AND station.nomStation LIKE 'Tignes'
);

SELECT DISTINCT sk.nomSkieur,
FROM classement
INNER JOIN skieur AS sk ON sk.idSkieur = cl.skieur_id
INNER JOIN competition AS cl ON cl.idCompet = cl.competition_id
WHERE sk.station_id = ct.station_id AND cl.classement <= 3
AND sk.skieur NOT IN(
  SELECT cl2.skieur_id
  FROM classement AS cl2
  INNER JOIN competition.cl2 ON cl2.idCompet = cl2.competition_id
  INNER JOIN skieur.sk2 ON sk2.idSkieur = cl2.skieur_id
  WHERE cl2.classement > 3 AND sk2.station_id = cl2.station_id
);

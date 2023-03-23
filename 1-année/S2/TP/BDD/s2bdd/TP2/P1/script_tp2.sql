DROP TABLE IF EXISTS classement, skieur, comporte, specialite, competition, station;

CREATE TABLE IF NOT EXISTS station(
    idStation INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomStation VARCHAR(255),
    altitude INT,
    pays VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS competition(
    idCompetition INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    libelleCompet VARCHAR(255),
    dateComp VARCHAR(255),
    station_id INT,
    CONSTRAINT fk_competition_station FOREIGN KEY (station_id) REFERENCES station(idStation)
);

CREATE TABLE IF NOT EXISTS specialite(
    idSpecialite INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    libelleSpecialite VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS comporte(
    competition_id INT,
    specialite_id INT,
    PRIMARY KEY (competition_id, specialite_id),
    CONSTRAINT fk_comporte_competition FOREIGN KEY (competition_id) REFERENCES competition(idCompetition),
    CONSTRAINT fk_comporte_specialite FOREIGN KEY (specialite_id) REFERENCES specialite(idSpecialite)
);

CREATE TABLE IF NOT EXISTS skieur(
    idSkieur INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomSkieur VARCHAR(255),
    specialite_id INT,
    station_id INT,
    CONSTRAINT fk_skieur_specialite FOREIGN KEY (specialite_id) REFERENCES specialite(idSpecialite),
    CONSTRAINT fk_skieur_station FOREIGN KEY (station_id) REFERENCES station(idStation)
);

CREATE TABLE IF NOT EXISTS classement(
    skieur_id INT,
    competition_id INT,
    classement INT,
    CONSTRAINT fk_classement_skieur FOREIGN KEY (skieur_id) REFERENCES skieur(idSkieur),
    CONSTRAINT fk_classement_competition FOREIGN KEY (competition_id) REFERENCES competition(idCompetition)
);

LOAD DATA LOCAL INFILE 'STATION.csv' INTO TABLE station CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'COMPETITION.csv' INTO TABLE competition CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'SPECIALITE.csv' INTO TABLE specialite CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'COMPORTE.csv' INTO TABLE comporte CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'SKIEUR.csv' INTO TABLE skieur CHARACTER SET utf8 FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'CLASSEMENT.csv' INTO TABLE classement CHARACTER SET utf8 FIELDS TERMINATED BY ',';


SELECT * FROM station;
SELECT * FROM competition;
SELECT * FROM specialite;
SELECT * FROM comporte;
SELECT * FROM skieur;
SELECT * FROM classement;


SELECT COUNT(skieur.idSkieur) AS NbreSkieurDansUneCompet FROM skieur;

SELECT skieur.nomSkieur, station.nomStation
FROM skieur
LEFT JOIN station ON skieur.station_id = station.idStation
ORDER BY station.nomStation, skieur.nomSkieur;

SELECT skieur.nomSkieur, classement.classement, competition.libelleCompet
FROM classement
INNER JOIN skieur ON classement.skieur_id = skieur.idSkieur
INNER JOIN competition ON classement.competition_id = competition.idCompetition
ORDER BY competition.libelleCompet, classement.classement;

SELECT competition.libelleCompet, skieur.nomSkieur
FROM classement
INNER JOIN skieur ON classement.skieur_id = skieur.idSkieur
INNER JOIN competition ON classement.competition_id = competition.idCompetition
INNER JOIN station ON competition.station_id = station.idStation
WHERE classement.classement = 1 AND station.nomStation = 'Tignes';

SELECT station.idStation, station.nomStation, COUNT(competition.station_id) AS nbrDeCompet
FROM station
INNER JOIN competition ON station.idStation = competition.station_id
GROUP BY station.idStation
ORDER BY nomStation;

SELECT skieur.idSkieur, skieur.nomSkieur, COUNT(*) AS NbreDeVictoire
FROM station
INNER JOIN competition ON station.idStation = competition.station_id
INNER JOIN classement ON competition.idCompetition = classement.competition_id
INNER JOIN skieur ON classement.skieur_id = skieur.idSkieur
WHERE classement.classement = 1 AND station.nomStation = 'Tignes'
GROUP BY skieur.idSkieur
ORDER BY NbreDeVictoire DESC;

SELECT DISTINCT skieur.nomSkieur
FROM classement
INNER JOIN skieur ON skieur.idSkieur = classement.skieur_id
WHERE classement.classement = 1;
AND skieur.nomSkieur NOT IN (
    SELECT classement.skieur_id
    FROM classement
    WHERE classement.classement <>1
);
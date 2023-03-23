
DROP TABLE IF EXISTS groupe ;
DROP TABLE IF EXISTS etudiant ;

CREATE TABLE groupe (
    idGroupe INT AUTO_INCREMENT
    ,libelleGroupe VARCHAR(25)
    ,PRIMARY KEY (idGroupe)

) ;

CREATE TABLE etudiant (
    noEtudiant INT AUTO_INCREMENT
    ,nomEtudiant VARCHAR(25)
    ,idGroupe INT
    ,PRIMARY KEY (noEtudiant)
    ,FOREIGN KEY (idGroupe) REFERENCES groupe(idGroupe)

) ;

INSERT INTO groupe VALUES(1,'S2A1');    -- il id 1
INSERT INTO groupe VALUES(2,'S2A2');    -- id 2
INSERT INTO groupe VALUES(3,'S2bisA1'); -- id 3

INSERT INTO etudiant VALUES(1,'paul',1);
INSERT INTO etudiant VALUES(2,'pierre',2);
INSERT INTO etudiant VALUES(3,'toto',2);
INSERT INTO etudiant VALUES(4,'fjzjf',NULL);

SELECT COUNT(etudiant.noEtudiant), libelleGroupe
FROM etudiant
    RIGHT JOIN groupe g on etudiant.idGroupe = g.idGroupe
GROUP BY libelleGroupe;

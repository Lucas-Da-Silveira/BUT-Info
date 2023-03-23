DROP TABLE IF EXISTS Employe;
DROP TABLE IF EXISTS Departement;

CREATE TABLE Departement(
  Id_Departement INT NOT NULL AUTO_INCREMENT,
  Nom_Departement VARCHAR(100),
  PRIMARY KEY (Id_Departement)
);

CREATE TABLE Employe
(
    Id_Employe      INT AUTO_INCREMENT,
    Nom_Employe     VARCHAR(20),
    Tel_pro_Employe VARCHAR(13),
    Salaire_Employe NUMERIC(7, 2),
    Num_Bureau      NUMERIC(2),
    Date_embauche   DATE,
    Departement_Id  INT,
    PRIMARY KEY (Id_Employe)
);

DESCRIBE Employe;
DESCRIBE Departement;

INSERT INTO Departement VALUES
(NULL, 'Production'),
(NULL, 'Marketing'),
(NULL, 'MÃ©thode'),
(NULL, 'Recherche et developpement');

INSERT INTO Employe (Id_Employe, Nom_Employe, Tel_pro_Employe, Salaire_Employe, Num_Bureau, Date_embauche, Departement_Id) VALUES
            (NULL, 'Durand', '0384545401', 2000.00, 1, '2010-10-01', 1),
            (NULL, 'Dupond', '0384545402', 1500.00, 2, '2012-09-30', 1),
            (NULL, 'Renaud', '0384545403', 2000.00, 2, '2009-09-21', 2),
            (NULL, 'Pierre', '0384545404', 2500.00, 3, '2000-10-02', 2),
            (NULL, 'Danigo', '0384545405', 1400.50, 3, '2018-10-02', 3);

SELECT * FROM Employe;
SELECT * FROM Departement;

SELECT Nom_Employe , Tel_pro_Employe
FROM Employe
WHERE Num_Bureau < 3 AND Salaire_Employe > 1800;

SELECT Nom_Employe
FROM Employe
WHERE (Nom_Employe LIKE 'D%' OR Nom_Employe LIKE 'P%') AND (Num_Bureau = 1 OR Num_Bureau = 3);  /*LIKE = chercher en commençant '..%', finissant '%..'*/

UPDATE Employe SET   /*Changer la donnée dans une critère*/
Tel_pro_Employe = 0601010101
WHERE Nom_Employe = 'Pierre' ;

SELECT * FROM Employe;

ALTER TABLE Employe ADD Prenom_Employe VARCHAR(20) AFTER Nom_Employe; /* Ajouter un champ = "ALTER TABLE ... ADD" pour mettre apres = "AFTER" */

SELECT * FROM Employe;


SELECT DISTINCT Num_Bureau
FROM Employe
WHERE Date_embauche BETWEEN '2001-01-01' AND '2015-01-01'
ORDER BY Num_Bureau DESC ;


SELECT Num_Bureau, COUNT(Id_Employe) AS NB_employe, ROUND(AVG(Salaire_Employe), 2) AS AVG_salaire
FROM Employe
WHERE Nom_Employe LIKE 'D%' OR Nom_Employe LIKE 'R%'
GROUP BY Num_Bureau
ORDER BY Num_Bureau DESC;


SELECT Num_Bureau, COUNT(Id_Employe) AS NB_employe, ROUND(AVG(Salaire_Employe), 2) AS AVG_salaire
FROM Employe
WHERE Nom_Employe LIKE 'D%' OR Nom_Employe LIKE 'R%'
GROUP BY Num_Bureau
HAVING AVG(Salaire_Employe) >= 1500
ORDER BY Num_Bureau DESC;


SELECT Departement.Nom_Departement, COUNT(Employe.Id_Employe) AS NB_Employe, ROUND(AVG(Employe.Salaire_Employe), 2) AS Moy_salaire
FROM Departement
LEFT JOIN Employe
ON Departement.Id_Departement = Employe.Departement_Id
GROUP BY Departement.Nom_Departement
ORDER BY Departement.Nom_Departement DESC;



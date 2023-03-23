DROP TABLE IF EXISTS Etudiant;

CREATE TABLE Etudiant (
    idEtudiant INT AUTO_INCREMENT
    ,nom VARCHAR(20)
    ,adresse VARCHAR(40)
    ,ville VARCHAR(20)
    ,code_postal VARCHAR(5)
    ,telephone VARCHAR(13)
    ,date_naissance DATE
    ,sexe VARCHAR(1)
    ,boursier VARCHAR(1)
    ,groupe_id INT
    ,semestre_id INT
    ,note_ue1 NUMERIC(4,2)
    ,note_ue2 NUMERIC(4,2)
    , PRIMARY KEY (idEtudiant)

);

DESCRIBE Etudiant;

INSERT INTO Etudiant (idEtudiant, nom, adresse, ville, code_postal, telephone, date_naissance, sexe, boursier, groupe_id, semestre_id, note_ue1, note_ue2) VALUES
(1, 'BERNARD', '1 rue sous bois', 'Belfort', '90000', '0384545401', '2004-01-01', 'F', 'N', 1, 1, '11.50', '10.50'),
(19, 'CHAVEAUX', '5 rue du chasseur', 'Strasbourg', '68000', '0384545419', '2004-01-01', 'H', 'N', 2, 2, '10.50', '9.50'),
(20, 'PRETTOT', '8 rue vilapogo', 'Belfort', '90000', '0384545420', '2004-07-12', 'H', 'O', 2, 2, '12.50', '8.50'),
(7, 'RIOT', '67 rue pasteur', 'Montbeliard', '25200', '0384545407', '2004-06-06', 'H', 'N', 2, 1, '13.50', '12.50'),
(8, 'BOISSENIN', '1 rue sous bois', 'Belfort', '90000', '0384545408', '2004-08-09', 'H', 'O', 2, 1, '15.5', '16.50'),
(2, 'PEQUIGNOT', '2 rue de la liberation', 'Valdoie', '90300', '0384545402', '2003-06-06', 'H', 'O', 1, 1, '17.50', '9.50'),
(3, 'ZILLIOX', '7 rue du verger', 'Bavilliers', '90120', '0384545403', '2003-01-01', 'H', 'N', 1, 1, '11.50','9.25'),
(4, 'MONNIER', '3 rue du boulanger', 'TAILLECOURT', '25400', '0384545404', '2003-02-06', 'H', 'O', 1, 1, '9.50', '15.5'),
(5, 'BRISCHOUX', '5 rue du chasseur', 'Belfort', '90000', '0384545405', '2003-05-25', 'H', 'N', 2, 1, '10', '9.50'),
(6, 'DUVAL', '8 rue vilapogo', 'Bavilliers', '90120', '0384545406', '2003-03-11', 'H', 'O', 2, 1, '13.50', '8.50'),
(10, 'FAIVRE', '7 rue des vergers  de rioz', 'Valdoie', '90300', '0384545410', '2003-01-05', 'H', 'O', 3, 1, '8.50', '12.50'),
(17, 'DELANOE', '7 rue du verger', 'Valdoie', '90300', '0384545417', '2003-01-01', 'H', 'N', 1, 2, '12.50', '14.50'),
(18, 'BONVALOT', '3 rue du boulanger', 'Belfort', '90000', '0384545418', '2003-01-01', 'H', 'O', 1, 2, '10', '11.50'),
(21, 'COULON', '67 rue pasteur', 'Valdoie', '90300', '0384545421', '2003-01-01', 'H', 'N', 2, 2, '17', '6.5'),
(23, 'KENDE', '2 rue de la liberation', 'Bavilliers', '90120', '0384545423', '2003-05-31', 'H', 'N', 3, 2, '15', '7.5'),
(24, 'KLEIN', '7 rue du verger', 'Montbeliard', '25200', '0384545424', '2003-01-01', 'H', 'N', 3, 2, '13', '15'),
(25, 'VALZER', '3 rue du boulanger', 'Valdoie', '90300', '0384545425', '2003-01-06', 'H', 'O', 3, 2, '11', '10'),
(26, 'PY', '5 rue du chasseur', NULL, '90000', '0384545426', '2003-01-10', 'F', 'N', 3, 2, '12.20', '7.90'),
(27, 'VERNET', '8 rue vilapogo', NULL, '90120', '0384545427', '2003-02-02', 'H', 'O', 4, 3, '10.30', '11.85'),
(28, 'BAILLIT', '67 rue pasteur', NULL, '25200', '0384545428', '2003-01-01', 'H', 'N', 4, 3, '7.90', '15.90'),
(16, 'LUZET', '2 rue de la liberation', 'Belfort', '90000', '0384545416', '2002-01-01', 'H', 'O', 1, 2, '10.25', '9.25'),
(22, 'VALOT', '1 rue sous bois', '', '90000', '0384545422', '2002-12-12', 'H', 'O', 2, 2, '10', '9.20'),
(29, 'DUPONT', '8 rue vilapogo', '', '90300', '0384545429', '2002-06-06', 'H', 'O', 4, 3, '13.20', '15.50'),
(11, 'FAIVRE', '3 rue des vergers', 'Cernay', '68000', '0384545411', '2002-01-01', 'F', 'N', 3, 1, '5.6', '12.05'),
(12, 'DUCHENNE', '5 rue du chasseur', 'Belfort', '90000', '0384545412', '2002-01-01', 'F', 'O', 3, 1, '10.4', '11.3'),
(13, 'BOULANGER', '8 rue vilapogo', 'Belfort', '90000', '0384545413', '2002-01-01', 'F', 'N', 1, 2, '13', '9.20'),
(9, 'FONTAINE-LEGIOT', '2 rue des vergers', 'Mulhouse', '68000', '0384545409', '2001-01-01', 'H', 'N', 3, 1, '11.25', '12'),
(14, 'MOREAU', '67 rue pasteur', 'Belfort', '90000', '0384545414', '2001-06-01', 'H', 'O', 1, 2, '9', '12.50'),
(15, 'RIGOULOT', '1 rue sous bois', 'Valdoie', '90300', '0384545415', '2001-12-12', 'H', 'N', 1, 2, '15', '10.50');

SELECT * FROM Etudiant;

SELECT nom
     ,note_ue1
     ,note_ue1*2 AS note_ue1_sur_40

FROM
    Etudiant
WHERE
    note_ue1 >= 14
ORDER BY
    note_ue1;

SELECT nom
    ,groupe_id
    ,note_ue1
    ,(note_ue1 + note_ue2)/2 AS Moyenne_UE
FROM
    Etudiant
HAVING
    Moyenne_UE >= 14
ORDER BY
    Moyenne_UE DESC, groupe_id DESC
;

SELECT nom
    ,groupe_id
    ,((note_ue1 + note_ue2)/2) AS Moyenne_UE
FROM
    Etudiant
HAVING
    Moyenne_UE >= 13
ORDER BY
    Moyenne_UE DESC
;

SELECT CONCAT('année :' ,YEAR (NOW())) ;
SELECT CONCAT('année :' ,YEAR(NOW())) AS TEST;
SELECT CONCAT('mois', MONTH(NOW()), 'jour : ', DAY(NOW())) AS TEST2;

SELECT nom
    ,CONCAT(DAY(NOW(date_naissance)),MONTH(NOW(date_naissance)),YEAR(NOW(date_naissance))) AS Date_Naissance
FROM
    Etudiant
WHERE
    date_naissance >= 2004
ORDER BY
    date_naissance DESC
;

SELECT  Count(Etudiant.idEtudiant) AS Nbre_etudiants
    ,Etudiant.code_postal
    ,Etudiant.sexe
FROM
    Etudiant
WHERE
    Etudiant.code_postal BETWEEN 90000 AND 90999 AND Etudiant.sexe='H'
GROUP BY
    Etudiant.code_postal, Etudiant.sexe
ORDER BY
    Etudiant.code_postal, Etudiant.sexe
;

SELECT
    Count(Etudiant.idEtudiant) AS Nbre_etudiants
    ,Etudiant.sexe
FROM
    Etudiant
WHERE
    Etudiant.code_postal BETWEEN 90000 AND 90999
GROUP BY
    Etudiant.sexe
;

SELECT
    Count(Etudiant.idEtudiant) AS Nbre_etudiants
FROM
    Etudiant
WHERE
    Etudiant.code_postal BETWEEN 90000 AND 90999 AND Etudiant.sexe='H'
GROUP BY
    Etudiant.sexe
;

SELECT
    Count(Etudiant.idEtudiant) AS Nbre_etudiants_Franche_Comte
FROM
    Etudiant
WHERE
    Etudiant.code_postal LIKE '90%' OR Etudiant.code_postal LIKE  '70%' OR Etudiant.code_postal LIKE '25%'
;

SELECT
    ROUND(AVG(note_ue1), 2) as moyenne_ue1,
    ROUND(AVG(note_ue2), 2) as moyenne_ue2,
    ROUND((AVG(note_ue1) + AVG(note_ue2)) / 2, 2) as moyenne,
    groupe_id,
    semestre_id
FROM Etudiant
GROUP BY groupe_id, semestre_id
ORDER BY semestre_id, groupe_id;
;
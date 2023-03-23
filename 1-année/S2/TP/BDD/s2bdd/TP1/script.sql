DROP TABLE IF EXISTS ligne;
DROP TABLE IF EXISTS commande;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS article;
    
    
CREATE TABLE IF NOT EXISTS client(
    idClient INT(11) NOT NULL AUTO_INCREMENT,
    nom VARCHAR(255),
    ville VARCHAR(255),
    PRIMARY KEY (idClient)
);

CREATE TABLE IF NOT EXISTS article(
    idArticle INT(11) NOT NULL AUTO_INCREMENT,
    designation VARCHAR (255),
    prix NUMERIC(5,2),
    PRIMARY KEY (idArticle)
)CHARACTER SET 'utf8'; 

CREATE TABLE IF NOT EXISTS commande(
    idCommande INT(11) NOT NULL AUTO_INCREMENT,
    dateCommande DATE,
    idClient INT,
    PRIMARY KEY (idCommande),
    FOREIGN KEY (idClient) REFERENCES client(idClient)
);

CREATE TABLE IF NOT EXISTS ligne(
    idCommande INT,
    idArticle INT,
    quantite INT,
    PRIMARY KEY (idCommande, idArticle),
    FOREIGN KEY (idCommande) REFERENCES commande(idCommande),
    FOREIGN KEY (idArticle) REFERENCES article(idArticle)
);

LOAD DATA LOCAL INFILE 'client.csv' INTO TABLE client FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'article.csv' INTO TABLE article FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'commande.csv' INTO TABLE commande FIELDS TERMINATED BY ',';
LOAD DATA LOCAL INFILE 'ligne.csv' INTO TABLE ligne FIELDS TERMINATED BY ',';


-- DESCRIBE client;
-- DESCRIBE article;
-- DESCRIBE commande;
-- DESCRIBE ligne;


-- ALTER TABLE commande DROP FOREIGN KEY fk_commande_client;
-- ALTER TABLE ligne DROP FOREIGN KEY fk_ligne_commande;
-- ALTER TABLE ligne DROP FOREIGN KEY fk_ligne_article;

-- ALTER TABLE commande ADD CONSTRAINT fk_commande_client FOREIGN KEY (idClient) 
-- REFERENCES client(idClient) ON DELETE CASCADE;
-- ALTER TABLE ligne ADD CONSTRAINT fk_ligne_commande FOREIGN KEY (idCommande)
-- REFERENCES commande(idCommande) ON DELETE CASCADE;
-- ALTER TABLE ligne ADD CONSTRAINT fk_ligne_article FOREIGN KEY (idArticle)
-- REFERENCES article(idArticle) ON DELETE CASCADE;

-- SHOW CREATE table commande;
-- SHOW CREATE table ligne;

-- DELETE FROM client WHERE LIKE "Mutz";
    
SELECT * FROM ligne;
SELECT * FROM commande;
SELECT * FROM client;
SELECT * FROM article;

SELECT nom FROM client
WHERE ville = 'Belfort' AND nom LIKE 'M%' OR ville = 'Belfort' AND nom LIKE 'E%' OR
ville = 'Belfort' AND nom LIKE 'D%' OR ville = 'Belfort' AND nom LIKE 'm%' OR ville = 'Belfort' AND nom LIKE 'e%' 
OR  ville = 'Belfort' AND nom LIKE 'd%'
ORDER BY nom ASC;

SELECT designation, prix FROM article
WHERE prix BETWEEN 6 AND 10
ORDER BY prix DESC;

SELECT client.nom, commande.dateCommande FROM client , commande
WHERE nom = 'Mutz' AND client.idClient = commande.idClient
ORDER BY dateCommande DESC;

SELECT client.nom, article.designation, article.prix, ligne.quantite, commande.idCommande
FROM client, commande, article, ligne
WHERE client.nom = 'Mutz' AND client.idClient = commande.idClient AND commande.idCommande = ligne.idCommande
AND ligne.idArticle = article.idArticle
ORDER BY commande.idCommande ASC;

SELECT client.nom, article.designation, commande.idCommande, article.prix * ligne.quantite AS prixtotal
FROM client, commande, article, ligne
WHERE client.nom = 'Mutz' AND client.idClient = commande.idClient AND commande.idCommande = ligne.idCommande 
AND ligne.idArticle = article.idArticle
ORDER BY prixtotal DESC;

SELECT client.nom, commande.idCommande, SUM(article.prix * ligne.quantite ) AS prix_total
FROM client, commande, article, ligne
WHERE client.nom = 'Mutz' AND client.idClient = commande.idClient AND commande.idCommande = ligne.idCommande
AND ligne.idArticle = article.idArticle
GROUP BY commande.idCommande
ORDER BY prix_total DESC;

SELECT client.nom, commande.idCommande, SUM(article.prix * ligne.quantite ) AS prix total HT, 
ROUND(SUM(article.prix * article.quantite) - SUM(article.prix * article.quantite) * (20/100)) AS TVA ,
ROUND(SUM(article.prix * article.quantite) + SUM(article.prix * article.quantite) * (20/100)) AS prix total TTC
FROM client, commande, article, ligne
WHERE client.idClient = commande.idClient AND commande.idCommande = ligne.idCommande
AND ligne.idArticle = article.idArticle
GROUP BY commande.idCommande
ORDER BY prix total TTC ASC;
DROP TABLE IF EXISTS article;
DROP TABLE IF EXISTS type_article;

CREATE TABLE type_article (
  id int(11) NOT NULL AUTO_INCREMENT,
  libelle varchar(50),
  PRIMARY KEY (id)
  -- UNIQUE KEY libelle (libelle)
);

INSERT INTO type_article VALUES (NULL,'Fourniture de bureau');
INSERT INTO type_article VALUES (NULL,'Mobilier');
INSERT INTO type_article VALUES (NULL,'Mobilier Jardin');
INSERT INTO type_article VALUES (NULL,'Arrosage');
INSERT INTO type_article VALUES (NULL,'Outils');
INSERT INTO type_article VALUES (NULL,'Divers');

CREATE TABLE article (
  id int(11) NOT NULL AUTO_INCREMENT,
  nom varchar(50),
  disponible tinyint(1) DEFAULT 0,
  type_article_id int(11),
  prix int(11),
  stock int(11),
  description varchar(500),
  image varchar(100),  -- photo
  PRIMARY KEY (id),
  -- UNIQUE KEY nom (nom),
  CONSTRAINT fk_article_type_article FOREIGN KEY (type_article_id) REFERENCES type_article (id)
) ;

INSERT INTO article(nom,disponible,prix,stock,type_article_id,image) VALUES
('Enveloppes (50p)',1,2.00, 3,1,NULL),
('Stylo noir',1,1.00,13,1,'stylo.jpeg'),
('Boite de rangement',1,3.00,12,1,'boites.jpeg'),
('Chaise',1,40.00,3,2,'chaise.jpeg'),
('Tables',1,200.00,3,2,'table.jpeg'),
('Salon de Jardin alu',1,149.00,3,3,'salonJardin2.jpg'),
('Table+6 fauteuils de Jardin',1,790.00,3,3,'tableFauteuilsJardin1.jpg'),
('Set Table + 4 bancs',1,229.00,3,3,'setTableChaises.jpg'),
('arrosoir bleu',1,13.50,3,4,'arrosoir1.jpg'),
('arrosoir griotte',1,9.90,3,4,'arrosoir2.jpg'),
('tuyau arrosage',1,31.90,3,4,'tuyauArrosage1.jpg'),
('tournevis',1,23.90,3,5,'lotTourneVis.jpg'),
('marteau menuisier',1,7.80,3,5,'marteau.jpg'),
('pince multiprise',1,21.80,3,5,'pinceMultiprise.jpg'),
('perceuse',1,149.80,3,5,'perceuse.jpg');

SELECT * FROM type_article;
SELECT * FROM article;
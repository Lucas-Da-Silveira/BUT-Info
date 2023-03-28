DROP TABLE IF EXISTS produits;
CREATE TABLE produits (
  id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  typeProduit_id int(10),
  nom varchar(250) ,
  prix float(5,2),
  photo varchar(50)
)  DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;



INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'salade', '2.00','salade.jpeg');
INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'choux', '2.50','choux.jpeg');
INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'pomme de terre', '1.50','pommeterre.jpeg');
INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'tomate', '1.50','tomate.jpeg');
INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'haricot', '6.50','haricot.jpeg');
INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'potiron', '3.00','potiron.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'poireau', 1.50,'poireau.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'fenouil', 3.00,'fenouil.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL,1, 'pissenlit', 5.00,'pissenlit.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'petit pois', 6.00,'petitpois.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'poivron', 3.00,'poivron.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 1, 'radis', 2.00,'radis.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'pomme', 3.00,'pomme.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'prune', 3.00,'prune.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'pruneau', 9.00,'pruneau.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'raisin', 3.00,'raison.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'rhubarbe', 3.00,'rhubarbe.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'pêche', 3.00,'peche.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'mirabelle', 3.00,'mirabelle.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'pastèque', 3.00,'pasteque.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'pamplemousse', 3.00,'pamplemousse.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'orange', 3.00,'orange.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'mandarine', 3.00,'mandarine.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'melon', 3.00,'melon.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'mangue', 3.00,'mangue.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'kiwi', 3.00,'kiwi.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'banane', 2.00,'banane.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'poire', 2.50,'poire.jpeg');
-- INSERT INTO produits (`id`, `typeProduit_id`, `nom`, `prix`,`photo`)VALUES (NULL, 2, 'cerise', 2.50,'cerise.jpeg');

DROP TABLE IF EXISTS typeProduits;
create table typeProduits
(
id int(10) not null PRIMARY KEY ,
libelle varchar(50) 
)  DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
insert into typeProduits values (1,"légume");
insert into typeProduits values (2,"fruit");
insert into typeProduits values (3,"autre");


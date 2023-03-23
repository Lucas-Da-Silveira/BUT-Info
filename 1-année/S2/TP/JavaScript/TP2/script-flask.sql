drop table if exists Contient, Commande, Prestation, Client, Carte;

create table Carte(
codeCarte int not null AUTO_INCREMENT,
carte  varchar(10),
remise int(3),
primary key (codeCarte)
) CHARACTER SET utf8 COLLATE utf8_bin;



create table Client(
codeClient int not null AUTO_INCREMENT,
civilite varchar(8),
nom varchar(15),
prenom varchar(15),
adresse varchar(50),
codePostal int(5),
ville varchar(15),
carte int,
primary key (codeClient),
foreign key (carte) references Carte(codeCarte)
) CHARACTER SET utf8 COLLATE utf8_bin;

create table Prestation(
codePrestation int not null AUTO_INCREMENT,
prestation varchar(30),
prix decimal(4,1),
primary key (codePrestation)
) CHARACTER SET utf8 COLLATE utf8_bin;

create table Commande(
numCommande int not null AUTO_INCREMENT,
prixTotal double,
dateCommande DATETIME,
codeClient int,
primary key (numCommande),
foreign key (codeClient) references Client(codeClient)
);

create table Contient(
numCommande int,
codePrestation int,
quantite int,
primary key (numCommande,codePrestation),
foreign key (numCommande) references Commande(numCommande),
foreign key (codePrestation) references Prestation(codePrestation)
);

insert into Carte values (1,'verte',10),
(2,'bleue',25),
(3,'orange',34),
(4,'rouge',50);

insert into Client values (1,'Monsieur','Bertin','Théophile','12 rue des près', 90000, 'Belfort', 1),
(2,'Monsieur','Bischoffe','Maxime','7 rue de la madeleine', 90150, 'Denney', 3),
(3,'Madame','Bitsch','Magali','8 rue des pervenches', 90400, 'Anjoutay', 2),
(4,'Madame','Briswalter','Melody','12 rue des moulinets', 90180, 'Banvillars', 2),
(5,'Monsieur','Concolato','Florian','7 rue des vergers', 90450, 'Baviliers', 1),
(6,'Madame','Fichter','Cécile','9 rue de la moselle',90100,'Delle',4),
(7,'Madame','Furster','Eline','12 rue des feuilles',90000,'Belfort',2),
(8,'Madame','Jacquot','Sophie','25 rue du piton',90150,'Etueffont',4);


insert into Prestation values(1,'raquettes',3.0),
(2,'batons par paires',3.5),
(3,'skis parboliques',3.25),
(4, 'skis de fond',4.5),
(5,'skating',6.3),
(6,'surf',7.5),
(7,'equipement pro combi',7.0);
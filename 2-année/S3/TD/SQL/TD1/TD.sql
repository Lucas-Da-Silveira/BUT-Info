DROP TABLE IF EXISTS JOURNAUX_UTILISATEURS, DROITS_DE_MENUS, ELEMENTS_DE_MENU, MENUS, DROITS_DE_GROUPES, DROITS, MOTS_DE_PASSE_UTILISATEURS, UTILISATEURS, GROUPES;

CREATE TABLE GROUPES(
    Id SERIAL PRIMARY KEY,
    Groupe VARCHAR(255)
);

CREATE TABLE UTILISATEURS(
    User_id SERIAL PRIMARY KEY,
    First_Name VARCHAR(255),
    Last_Name VARCHAR(255),
    Date_Created TIMESTAMP,
    Password VARCHAR(255),
    Group_Id INTEGER REFERENCES GROUPES(Id)
);

CREATE TABLE MOTS_DE_PASSE_UTILISATEURS(
    Id SERIAL PRIMARY KEY,
    User_id INTEGER REFERENCES UTILISATEURS(User_id),
    Date_Created TIMESTAMP,
    Password VARCHAR(255)
);

CREATE TABLE DROITS(
    Id SERIAL PRIMARY KEY,
    Right_name VARCHAR(255)
);

CREATE TABLE DROITS_DE_GROUPES(
    Id SERIAL PRIMARY KEY,
    Group_Id INTEGER REFERENCES GROUPES(Id),
    Right_Id INTEGER REFERENCES DROITS(Id)
);

CREATE TABLE MENUS(
    Id SERIAL PRIMARY KEY,
    Nom_Menu VARCHAR(255) NOT NULL,
    Ordre_Affichage INTEGER NOT NULL
);

CREATE TABLE ELEMENTS_DE_MENU(
    Id SERIAL PRIMARY KEY,
    Nom_Element VARCHAR(255) NOT NULL,
    Lien VARCHAR(255) NOT NULL,
    Menu_Id INTEGER REFERENCES MENUS(Id) ON DELETE CASCADE,
    Ordre_Affichage INTEGER NOT NULL
);

CREATE TABLE DROITS_DE_MENUS(
    Id SERIAL PRIMARY KEY,
    Group_Id INTEGER REFERENCES GROUPES(Id),
    Menu_Id INTEGER REFERENCES MENUS(Id),
    Element_Id INTEGER REFERENCES ELEMENTS_DE_MENU(Id)
);

CREATE TABLE JOURNAUX_UTILISATEURS(
    Id SERIAL PRIMARY KEY,
    User_Id INTEGER REFERENCES UTILISATEURS(User_id),
    Date_Time TIMESTAMP,
    Event VARCHAR(255)
);

/*CREATE SEQUENCE users_user_id_seq(
    OWNED BY users.user_id,
    START WITH 100
);*/

-- ALTER TABLE UTILISATEURS ALTER COLUMN user_Id;

SELECT * FROM UTILISATEURS;

SELECT
    u.first_name,
    u.last_name,
    g.groupe
FROM
    utilisateurs u
    INNER JOIN groupes g ON u.group_id = g.id;

SELECT * FROM DROITS_DE_GROUPES AS dg
INNER JOIN GROUPES g ON dg.Group_Id = g.Id;

SELECT g.Id, g.Groupe, COUNT(*) FROM DROITS_DE_GROUPES AS dg
INNER JOIN GROUPES g ON dg.Group_Id = g.Id
GROUP BY g.Id
HAVING COUNT(*) > 1;

--Afficher l'ensemble des utilisateurs et l'ensemeble des groupes associés

SELECT u.First_Name,u.Last_Name, g.GROUPE FROM GROUPES g
FULL JOIN UTILISATEURS u ON g.Id = u.Group_Id
ORDER BY g.GROUPE;





INSERT INTO GROUPES (Groupe) VALUES ('Administrateurs');
INSERT INTO GROUPES (Groupe) VALUES ('Éditeurs');
INSERT INTO GROUPES (Groupe) VALUES ('Visiteurs');
INSERT INTO GROUPES (Groupe) VALUES ('Externe');
INSERT INTO UTILISATEURS (First_Name, Last_Name, Date_Created, Password, Group_Id) VALUES ('Joseph', 'Azar', NOW(), 'joseph123', 1);
INSERT INTO UTILISATEURS (First_Name, Last_Name, Date_Created, Password, Group_Id) VALUES ('Milvyne', 'Perrolet', NOW(), 'milvyne123', 2);
INSERT INTO UTILISATEURS (First_Name, Last_Name, Date_Created, Password, Group_Id) VALUES ('Stephane', 'Domas', CURRENT_TIMESTAMP, 'vuejs123', NULL);
INSERT INTO UTILISATEURS (First_Name, Last_Name, Date_Created, Password, Group_Id) VALUES ('Karine', 'Deschinkel', CURRENT_TIMESTAMP, 'karine123', (SELECT Id FROM GROUPES WHERE Groupe = 'Visiteurs'));

INSERT INTO UTILISATEURS (First_Name, Last_Name, Date_Created, Password) VALUES ('Utilisateur', 'Anonyme', NOW(), 'anonyme123');


INSERT INTO MOTS_DE_PASSE_UTILISATEURS (User_Id, Date_Created, Password) VALUES (1, NOW(), 'josephSecurePass');
INSERT INTO MOTS_DE_PASSE_UTILISATEURS (User_Id, Date_Created, Password) VALUES (2, NOW(), 'milvyneSecurePass');
INSERT INTO MOTS_DE_PASSE_UTILISATEURS (User_Id, Date_Created, Password) VALUES (3, NOW(), 'anonymeSecurePass');

INSERT INTO DROITS (Right_name) VALUES ('Créer');
INSERT INTO DROITS (Right_name) VALUES ('Lire');
INSERT INTO DROITS (Right_name) VALUES ('Mettre à jour');
INSERT INTO DROITS (Right_name) VALUES ('Supprimer');


INSERT INTO DROITS_DE_GROUPES (Group_Id, Right_Id) VALUES (1, 1);
INSERT INTO DROITS_DE_GROUPES (Group_Id, Right_Id) VALUES (1, 2);
INSERT INTO DROITS_DE_GROUPES (Group_Id, Right_Id) VALUES (1, 3);
INSERT INTO DROITS_DE_GROUPES (Group_Id, Right_Id) VALUES (1, 4); -- Admins can do everything
INSERT INTO DROITS_DE_GROUPES (Group_Id, Right_Id) VALUES (2, 2); -- Éditeurs can read
INSERT INTO DROITS_DE_GROUPES (Group_Id, Right_Id) VALUES (2, 3); -- Éditeurs can update

INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (1, NOW(), 'Connexion réussie');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (2, NOW(), 'Connexion échouée');

INSERT INTO MENUS (Nom_Menu, Ordre_Affichage) VALUES ('Menu Principal', 1);
INSERT INTO MENUS (Nom_Menu, Ordre_Affichage) VALUES ('Paramètres', 2);

INSERT INTO ELEMENTS_DE_MENU (Nom_Element, Lien, Menu_Id, Ordre_Affichage) VALUES ('Accueil', '/accueil', 1, 1);
INSERT INTO ELEMENTS_DE_MENU (Nom_Element, Lien, Menu_Id, Ordre_Affichage) VALUES ('Contact', '/contact', 1, 2);

INSERT INTO ELEMENTS_DE_MENU (Nom_Element, Lien, Menu_Id, Ordre_Affichage) VALUES ('Compte', '/compte', 2, 1);

INSERT INTO DROITS_DE_MENUS (Group_Id, Menu_Id, Element_Id) VALUES (1, 1, 1);
INSERT INTO DROITS_DE_MENUS (Group_Id, Menu_Id, Element_Id) VALUES (1, 1, 2);
INSERT INTO DROITS_DE_MENUS (Group_Id, Menu_Id, Element_Id) VALUES (1, 2, 3);

INSERT INTO DROITS_DE_MENUS (Group_Id, Menu_Id, Element_Id) VALUES (2, 1, 1);


INSERT INTO MOTS_DE_PASSE_UTILISATEURS (User_Id, Date_Created, Password) VALUES (1, '2022-01-10 10:00:00', 'josephOldPass1');
INSERT INTO MOTS_DE_PASSE_UTILISATEURS (User_Id, Date_Created, Password) VALUES (1, '2022-06-15 10:00:00', 'josephOldPass2');
INSERT INTO MOTS_DE_PASSE_UTILISATEURS (User_Id, Date_Created, Password) VALUES (2, '2022-03-22 10:00:00', 'milvyneOldPass1');
INSERT INTO MOTS_DE_PASSE_UTILISATEURS (User_Id, Date_Created, Password) VALUES (2, '2022-08-30 10:00:00', 'milvyneOldPass2');

INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (1, '2022-01-10 10:15:00', 'Changement de mot de passe');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (1, '2022-05-12 09:00:00', 'Connexion réussie');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (1, '2022-06-15 11:00:00', 'Changement de mot de passe');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (1, '2022-12-25 18:30:00', 'Connexion réussie');

INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (2, '2022-03-22 14:20:00', 'Changement de mot de passe');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (2, '2022-07-08 10:45:00', 'Tentative de connexion échouée');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (2, '2022-08-30 16:00:00', 'Changement de mot de passe');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (2, '2022-09-15 10:00:00', 'Connexion réussie');
INSERT INTO JOURNAUX_UTILISATEURS (User_Id, Date_Time, Event) VALUES (3, '2022-11-05 20:30:00', 'Tentative de connexion échouée');



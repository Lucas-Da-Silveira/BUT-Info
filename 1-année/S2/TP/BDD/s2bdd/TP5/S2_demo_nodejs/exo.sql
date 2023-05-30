CREATE TABLE messages(
id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
titre VARCHAR(20),
content VARCHAR(255),
user VARCHAR(20),
date DATE
)CHARACTER SET utf8;
INSERT INTO messages VALUES (1,'message 1','contenu du message 1','pierre',now());
INSERT INTO messages VALUES (2,'message 2','contenu du message 2','paul',now());
INSERT INTO messages VALUES (3,'message 3','contenu du message 3','anthoine',now());
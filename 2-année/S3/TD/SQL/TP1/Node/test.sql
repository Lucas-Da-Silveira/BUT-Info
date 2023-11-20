SELECT password
FROM utilisateurs
WHERE first_name = 'Joseph' AND last_name = 'Azar';

SELECT user_id FROM utilisateurs
WHERE first_name = 'Stephane' AND last_name = 'Domas';

SELECT U.password FROM mots_de_passe_utilisateurs AS P
JOIn utilisateurs U ON P.user_id = U.user_id
WHERE U.user_id = 1;
import pymysql.cursors

mydb = pymysql.connect(    #pymysql.connect remplace mysql.connector
  host="localhost",   #localhost sur les machines perso.
  user="ldasilve",
  password="1603",
  database="BDD_ldasilve",
  charset='utf8mb4',                      # 2 attributs à ajouter
  cursorclass=pymysql.cursors.DictCursor  # 2 attributs à ajouter
)

mycursor = mydb.cursor()


nom="pelle"
prix=25.5
stock=7
disponible=1
image="no_photo.jpeg"
type_article_id=None     # en python None remplace NULL en SQL dans un tuple
tuple_insert = (nom,prix,stock,disponible,image,type_article_id)

sql = "INSERT INTO article(nom, prix, stock, disponible, image, type_article_id) VALUES (%s , %s, %s, %s, %s, %s);"
print(sql)

mycursor.execute(sql, tuple_insert)
mydb.commit()
print("Tuple ajouté avec succès dans la table article avec id = ", mycursor.lastrowid)

mydb.close()
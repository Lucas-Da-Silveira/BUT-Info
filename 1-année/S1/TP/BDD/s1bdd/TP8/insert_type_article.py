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


libelle="Jardinage"

tuple_insert = (libelle,)

sql ="INSERT INTO type_article(libelle) VALUES (%s);"
print(sql)

mycursor.execute(sql, tuple_insert)
mydb.commit()
print("Tuple ajouté avec succès dans la table article avec id = ", mycursor.lastrowid)


mydb.close()
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


nom="perceuse visseuse"
prix=139.9
stock=5
id_article=15
image="no_photo.jpeg"
tuple_update = (nom,prix,stock,image,id_article)

sql = "UPDATE article SET nom =%s , prix =%s , stock =%s , image =%s WHERE id = %s;"
print(sql)
mycursor.execute(sql, tuple_update)
mydb.commit()               # connection
print(mycursor.rowcount, "was updated.")


mydb.close()
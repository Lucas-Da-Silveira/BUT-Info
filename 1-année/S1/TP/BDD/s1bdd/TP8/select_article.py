#! /usr/bin/python
# -*- coding:utf-8 -*-

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

id_article="9"
int_id_article=int(id_article)
tuple_select = (int_id_article,)

sql ="SELECT id,nom,prix,stock FROM article WHERE id <= %s ORDER BY id"

mycursor.execute(sql, tuple_select)

print(u"\ntest fetchone \n")
myresult = mycursor.fetchone()
print(myresult)
print(u"\ntest 2 fetchone \n")
myresult = mycursor.fetchone()
print(myresult)


print(u"\ntest fetchall \n")
myresult = mycursor.fetchall()
for ligne in myresult:
    print(ligne)
    print("*** nom : ",ligne['nom']," , prix :",ligne['prix'])

print(u"\ntest 2 fetchall \n")

mycursor.execute(sql, tuple_select)

myresult = mycursor.fetchall()
for ligne in myresult:
    print(ligne)
    print("*** nom : ",ligne['nom']," , prix :",ligne['prix'])
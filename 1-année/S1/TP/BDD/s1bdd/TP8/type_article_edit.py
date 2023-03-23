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

libelle="Mobilier Intérieur"
id_type_article=2
tuple_update = (libelle,id_type_article)

sql ="UPDATE type_article SET libelle =%s WHERE id >= %s;"
print(sql)
mycursor.execute(sql, tuple_update)
mydb.commit()               # connection
print(mycursor.rowcount, "was updated.")


mydb.close()
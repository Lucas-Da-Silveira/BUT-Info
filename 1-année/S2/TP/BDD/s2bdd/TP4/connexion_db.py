from flask import g

import pymysql.cursors

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = pymysql.connect(
            host="localhost",
            # host="serveurmysql",
            user="ldasilve",
            password="1603",
            database="BDD_ldasilve",
            charset='utf8mb4',
            cursorclass=pymysql.cursors.DictCursor
        )
    return db

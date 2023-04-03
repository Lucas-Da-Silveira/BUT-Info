from flask import Flask, request, render_template, redirect, url_for, abort, flash, session, g

import pymysql.cursors

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        #
        db = g._database = pymysql.connect(
            #host="Lucasdslvra.mysql.pythonanywhere-services.com",
            host="localhost",
            #user="Lucasdslvra",
            user="ldasilve",
            #password="SAE235711131719",
            password="1603",
            #database="Lucasdslvra$default",
            database="BDD_ldasilve",
            charset='utf8mb4',
            cursorclass=pymysql.cursors.DictCursor
        )
    return db
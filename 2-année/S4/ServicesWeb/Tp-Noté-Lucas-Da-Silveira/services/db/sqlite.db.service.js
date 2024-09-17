var sqlite3 = require('sqlite3');
var mkdirp = require('mkdirp');

mkdirp.sync('services/db/datasources');

var db = new sqlite3.Database('services/db/datasources/users.db');

db.serialize(function () {
    db.run("CREATE TABLE IF NOT EXISTS users ( \
                id INTEGER PRIMARY KEY, \
                username TEXT UNIQUE, \
                hashed_password BLOB, \
                salt BLOB, \
                name TEXT, \
                email TEXT UNIQUE, \
                email_verified INTEGER \
  )");
    db.run("CREATE TABLE IF NOT EXISTS federated_credentials ( \
                id INTEGER PRIMARY KEY, \
                user_id INTEGER NOT NULL, \
                provider TEXT NOT NULL, \
                subject TEXT NOT NULL, \
                UNIQUE (provider, subject) \
  )");
    db.run("CREATE TABLE IF NOT EXISTS forgotten_password ( \
                id INTEGER PRIMARY KEY, \
                user_id INTEGER NOT NULL, \
                token TEXT,\
                datetime DATE \
        )")
});

module.exports = db;

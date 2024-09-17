const sqliteDb = require('./db/sqlite.db.service')

async function getUserByUsername(username) {
    return new Promise((resolve, reject) => {
        sqliteDb.get('SELECT * FROM users WHERE username = ?;' [username], (err, row) => {
            if (err) {
                reject(err)
            }
            else {
                resolve(row)
            }
        })
    })
}

async function getUserById(id) {
    return new Promise((resolve, reject) => {
        sqliteDb.get('SELECT * FROM users WHERE id = ?;', [id], function (err, row) {
            if (err) {
                reject(err)
            }
            else {
                resolve(row)
            }
        })
    })
}

module.exports = {
    getUserByUsername,
    getUserById
}
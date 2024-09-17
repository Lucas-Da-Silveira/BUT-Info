const sqliteDb = require('../services/db/sqlite.db.service')

function ensureAuthenticated(req, res, next) {
    if (req.isAuthenticated()) {
        return next();
    }
    return res.redirect('/login')
}

async function verifyResetToken(req, res, next) {
    sqliteDb.get("SELECT * FROM forgotten_password WHERE token = ?", [req.params.token], function (err, row) {
        if (!row) return res.sendStatus(403)
        if (row.datetime + 86400 >= Date.now()) res.sendStatus(400)
        else return next()
    })
}

module.exports = {
    ensureAuthenticated,
    verifyResetToken
}
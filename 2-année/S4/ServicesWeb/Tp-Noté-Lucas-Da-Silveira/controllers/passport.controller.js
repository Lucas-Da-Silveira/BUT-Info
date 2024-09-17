const passportController = require('passport');

const bcrypt = require('bcryptjs')

const GoogleStrategy = require('passport-google-oauth20');
const LocalStrategy = require('passport-local');

const sqliteDb = require('../services/db/sqlite.db.service')

passportController.use(new GoogleStrategy({
    clientID: process.env.GOOGLE_CLIENT_ID,
    clientSecret: process.env.GOOGLE_CLIENT_SECRET,
    callbackURL: '/auth/federated/google/redirect',
    scope: ['profile'],
    state: true,
}, (accessToken, refreshToken, profile, cb) => {
    console.log('BIEN');
    sqliteDb.get('SELECT * FROM federated_credentials WHERE provider = ? AND subject = ?;', ['google', profile.id], (err, row) => {
        if (err) return cb(err);
        if (!row) {
            sqliteDb.run('INSERT INTO users (name) VALUES (?)', [profile.displayName], function (err) {
                if (err) return cb(err);
                const id = this.lastID;
                sqliteDb.run('INSERT INTO federated_credentials (user_id, provider, subject) VALUES (?, ?, ?)', [id, 'google', profile.id], (err) => {
                  if (err) return cb(err);
                    const user = {
                        id: id,
                        name: profile.displayName
                    };

                    return cb(null, user)
                })
            })
        } else {
            sqliteDb.get('SELECT * FROM users WHERE id = ?;', [row.user_id], (err, row) => {
                if (err) return cb(err);
                if (!row) return cb(null, false)
                return cb(null, row)
            })
        }
    })
}));

passportController.use(new LocalStrategy(function verify(username, password, cb) {
    console.log('BIEN');
    sqliteDb.get('SELECT * FROM users WHERE username = ?;', [username], (err, row) => {
        if (err) return cb(err, false)
        if (!row) return cb("Nom d'utilisateur incorrect", false)
        else {
            if (bcrypt.compareSync(password, row.hashed_password)) return cb(null, row)
            else return cb("Mot de passe incorrect", false)
        }
    })
}));

passportController.serializeUser(function(user, cb) {
  process.nextTick(function() {
    cb(null, { id: user.id, name: user.name });
  });
});

passportController.deserializeUser(function(user, cb) {
  process.nextTick(function() {
    return cb(null, user);
  });
});

module.exports = passportController;
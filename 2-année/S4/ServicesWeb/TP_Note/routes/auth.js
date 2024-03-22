require('dotenv').config();
const express = require('express');
const passport = require('passport');
const GoogleStrategy = require('passport-google-oauth20');
let db = require('../db');

passport.use(new GoogleStrategy({
    clientID: process.env.GOOGLE_CLIENT_ID,
    clientSecret: process.env.GOOGLE_CLIENT_SECRET,
    callbackURL: '/auth/google/redirect',
    scope: ['profile'],
    state: true,
}, function verify(accessToken, refreshToken, profile, cb) {
    console.log('BIEN');
    db.get('SELECT * FROM federated_credentials WHERE provider = ? AND subject = ?', ['https://google.fr', profile.id], function(err, row) {
        if(err) {
            return cb(err);
        }
        if(!row) {
            db.run('INSERT INTO users (name) VALUES (?)', [profile.displayName], function(err) {
                if(err) {
                    return cb(err);
                }
                let id = this.lastID;
                db.run('INSERT INTO federated_credentials (user_id, provider, subject) VALUES (?, ?, ?)', [id, 'https://google.fr', profile.id], function(err) {
                    if(err) {
                        return cb(err);
                    }
                    let user = {
                        id: id,
                        name: profile.displayName
                    };

                    return cb(null, user);
                });
            });
        } else {
            db.get('SELECT * FROM users WHERE id = ?', [row.user_id], function(err, row) {
                if(err) {
                    return cb(err);
                }
                if(!row) {
                    return cb(null, false);
                }
                return cb(null, row);
            });
        }
    });
}));

passport.serializeUser(function(user, cb) {
    process.nextTick(function() {
        cb(null, {
            id: user.id,
            username: user.username,
            name: user.name
        });
    });
});

passport.deserializeUser(function(user, cb) {
    process.nextTick(function() {
        cb(null, user);
    });
});

const router = express.Router();

router.get('/login', (req, res, next) => {
    res.render('login');
});

router.get('/login/federated/google', passport.authenticate('google', {
    scope: ['profile']
}));

router.get('/auth/google/redirect', passport.authenticate('google'), (req, res) => {
    res.redirect('/');
});

router.post('/logout', (req, res) => {
    req.logout();
    res.redirect('/');
});

module.exports = router;
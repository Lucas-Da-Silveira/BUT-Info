require('dotenv').config();
const express = require('express');
const passport = require('passport');
const GoogleStrategy = require('passport-google-oauth20');
var db = require('../db');

passport.use(new GoogleStrategy({
    clientID: process.env.GOOGLE_CLIENT_ID,
    clientSecret: process.env.GOOGLE_CLIENT_SECRET,
    callbackURL: '/auth/google/redirect',
    scope: ['profile'],
    state: true
}, function (accessToken, refreshToken, profile, cb) {
    console.log('BIEN')
    db.get('SELECT * FROM federated_credentials WHERE provider = ? AND subject = ?', ['https://google.fr', profile.id], function (err, row) {
        if (err) {return cb(err);}

        if (!row) {
            db.run('INSERT INTO users (name) VALUES (?)', [profile.displayName], function (err) {

                if (err) {return cb(err);}
                var id = this.lastID;
                db.run('INSERT INTO federated_credentials (user_id, provider, subject) VALUES (?, ?, ?)', [id, 'https://google.fr', profile.id], function (err) {

                    if (err) {return cb(err);}
                     var user = {id:id, name: profile.displayName};
                        return cb(null, user);
                })
            });
        }else{
            db.get('SELECT * FROM users WHERE id =? ', [row.user_id], function (err, row) {
                if (err) {return cb(err);}
                if(!row) {return cb(null, false);}
                return  cb(null, row);
            })
        }
    })
}));
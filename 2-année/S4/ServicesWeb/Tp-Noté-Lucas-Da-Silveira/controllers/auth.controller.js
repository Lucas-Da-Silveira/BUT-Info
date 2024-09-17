const bcrypt = require('bcryptjs')

const sqliteDb = require('../services/db/sqlite.db.service')
const authService = require('../services/auth.service')

const nodemailer = require('nodemailer')
const transport = nodemailer.createTransport({
    host: process.env.MAIL_SMTP_SERVER,
    port: process.env.MAIL_SMTP_PORT,
    secure: true,
    auth: {
        user: process.env.MAIL_SMTP_USER,
        pass: process.env.MAIL_SMTP_PASS,
    }
})

function afterGoogleLogin(req, res) {
    res.redirect('/messaging');
}

function afterLocalLogin(req, res) {
    res.redirect('/messaging');
}

function register(req, res) {
    if (!req.body.username) return res.status(400).send("Please provide a username")
    if (!req.body.password) return res.status(400).send("Please provide password")
    if (!req.body.name) return res.status(400).send("Please provide a name")
    if (!req.body.email) return res.status(400).send("Please provide an email address")

    sqliteDb.get('SELECT * FROM users WHERE username = ? OR email = ?', [req.body.username, req.body.email], (err, row) => {
        if (err) return res.status(500).send(err)
        if (row) {
            return res.status(400).send("Username or email already taken")
        } else {
            const salt = bcrypt.genSaltSync(10);
            const hash = bcrypt.hashSync(req.body.password, salt)

            sqliteDb.run('INSERT INTO users (username, hashed_password, salt, name, email) VALUES (?, ?, ?, ?, ?)', [req.body.username, hash, salt, req.body.name, req.body.email], function (err) {
                if (err) {
                    console.log(err)
                    return res.status(500).send(err)
                }
                const user = {
                    id: this.lastID,
                    name: req.body.name,
                }
                console.log("created")

                req.login(user, (err) => {
                    console.log("logged")
                    if (err) return res.status(500).redirect('/login')
                    return res.sendStatus(201).redirect('/messaging')
                })
            })
        }
    })
}

function logout(req, res) {
    req.logout((err) => {
        if (err) return res.status(500).send("Fail to logout")
        return res.status(200).send('you\'ve been logged out');
    });
}

async function initForgotPassword(req, res) {
    if (!req.body.username) return res.sendStatus(400)
    const token = require('crypto').randomBytes(32).toString('hex');

    const user = await authService.getUserByUsername(req.body.username)
    if (!user.email) return res.status(400).send('Email address not set for user')

    sqliteDb.run("INSERT INTO forgotten_password (user_id, token, datetime) VALUES (?, ?, ?)", [req.body.userid, token, Date.now()], function (err) {
        if (err) return res.sendStatus(500)

        const resetEmail = {
            to: user.email,
            subject: 'WhatZap password reset',
            text: `
            You are receiving this because you (or someone else) have requested the reset of the password for your account.
          Please click on the following link, or paste this into your browser to complete the process:
          http://${req.headers.host}/forgot/${token}
          If you did not request this, please ignore this email and your password will remain unchanged.
            `
        }

        transport.sendMail(resetEmail)
        return res.redirect('/')
    })
}

function finalizeForgotPassword(req, res) {
    sqliteDb.get("SELECT * FROM forgotten_password WHERE token = ?", [req.params.token], async function (err, row) {
        if (err) return res.sendStatus(500)
        if (!row) return res.sendStatus(404)
        if (!req.body.password) res.sendStatus(400)
        else {
            const salt = bcrypt.genSaltSync(10)
            const hash = bcrypt.hashSync(req.body.password, salt)
            sqliteDb.run("UPDATE users SET hashed_password = ?, salt = ? WHERE id = ?", [hash, salt, row.user_id], async function (err) {
                const user = await authService.getUserById(row.user_id)
                const resetEmail = {
                    to: user.email,
                    subject: 'Your password has been changed',
                    text: `
      This is a confirmation that the password for your account "${user.email}" has just been changed.
    `,
                };
                sqliteDb.run('DELETE FROM forgotten_password WHERE token = ?', [req.params.token])
                await transport.sendMail(resetEmail)
                res.redirect('/')
            })


        }
    })
}

module.exports = {
    afterGoogleLogin,
    afterLocalLogin,
    register,
    logout,
    initForgotPassword,
    finalizeForgotPassword
};
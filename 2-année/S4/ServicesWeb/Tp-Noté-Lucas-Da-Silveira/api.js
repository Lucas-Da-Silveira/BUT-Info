const path = require('path')

const express = require('express');
const WhatZap = express();

require('dotenv').config();
const createError = require('http-errors');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const csrf = require('csurf');
const passport = require('passport');
const logger = require('morgan');

const SQLiteStore = require('connect-sqlite3')(session)

WhatZap.use(logger('dev'))
WhatZap.use(express.json())
WhatZap.use(express.urlencoded({extended: false}))
WhatZap.use(cookieParser())
WhatZap.use(session({
    secret: 'nathanBoschiS4',
    resave: false,
    saveUninitialized: false,
    store: new SQLiteStore({db: 'sessions.db', dir: 'services/db/datasources'})
}))
WhatZap.use(passport.authenticate('session'))
WhatZap.use((req, res, next) => {
    var msgs = req.session.messages || [];
    res.locals.messages = msgs;
    res.locals.hasMessages = !!msgs.length;
    req.session.messages = [];
    next();
});

WhatZap.set('views', path.join(__dirname, 'public', 'views'));
WhatZap.set('view engine', 'ejs');
WhatZap.use(express.static(path.join(__dirname, 'public', 'assets')))
/**
 * @swagger
 * tags:
 *    - name: Client
 *      description: Client views render
 */
const clientViewsRouter = require('./routers/clientViews.router')
WhatZap.use('/', clientViewsRouter)


/**
 * @swagger
 * tags:
 *    - name: Auth
 *      description: Authentication routes
 */
const authRouter = require('./routers/auth.router');
WhatZap.use('/auth', authRouter);


/**
 * @swagger
 * tags:
 *    - name: Messaging
 *      description: Messaging app routes
 */
const messagingRouter = require('./routers/messaging.router')
WhatZap.use('/', messagingRouter);


/**
 * @swagger
 * /api-docs:
 *     get:
 *         summary: Swagger Documentation for ouilleMessage API
 *         tags:
 *             - API
 */
const swaggerPlugin = require('./plugins/swagger.plugin');
swaggerPlugin(WhatZap);


WhatZap.use((req, res, next) => {
    res.status(404).send("Sorry can't find that!");
});

module.exports = WhatZap
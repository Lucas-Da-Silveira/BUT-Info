const express = require('express')
const clientViewsRouter = express.Router();

const authMiddleware = require('../middlewares/auth.middleware')
const authService = require('../services/auth.service')


/**
 * @swagger
 * /:
 *     get:
 *         summary: Home page
 *         tags:
 *             - Client
 */
clientViewsRouter.get('/', (req, res, next) => {
    res.render('home');
});

/**
 * @swagger
 * /login:
 *     get:
 *         summary: Login page
 *         tags:
 *             - Client
 */
clientViewsRouter.get('/login', (req, res) => {
    res.render('login')
})

/**
 * @swagger
 * /register:
 *     get:
 *         summary: Register page
 *         tags:
 *             - Client
 */
clientViewsRouter.get('/register', (req, res) => {
    res.render('register')
})

/**
 * @swagger
 * /messaging:
 *     get:
 *         summary: Messaging page
 *         description: Main app view - Messaging service (need to be authenticated)
 *         tags:
 *             - Client
 */
clientViewsRouter.get('/messaging', authMiddleware.ensureAuthenticated, (req, res) => {
    res.render('messaging', {
        userid: req.user.id
    })
})

/**
 * @swagger
 * /forgot:
 *     get:
 *         summary: Forgotten password ask view
 *         tags:
 *             - Client
 */
clientViewsRouter.get('/forgot', (req, res) => {
    res.render('forgot')
})

/**
 * @swagger
 * /forgot/{token}:
 *     get:
 *         summary: Forgotten password end View
 *         tags:
 *             - Client
 */
clientViewsRouter.get('/forgot/:token', authMiddleware.verifyResetToken, (req, res) => {
    res.render('forgotEnd', {
        token: req.params.token
    })
})

module.exports = clientViewsRouter
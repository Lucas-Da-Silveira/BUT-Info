const express = require('express');
const authRouter = express.Router();


const passport = require('../controllers/passport.controller');
const authController = require('../controllers/auth.controller');
const authMiddleware = require('../middlewares/auth.middleware')


/**
 * @swagger
 * /auth/federated/google:
 *     get:
 *         summary: Login with Google
 *         tags:
 *             - Auth
 */
authRouter.get('/federated/google', passport.authenticate('google', {
    scope: ['profile']
}));

/**
 * @swagger
 * /auth/federated/google/redirect:
 *     get:
 *         summary: Redirect after Google login
 *         tags:
 *             - Auth
 */
authRouter.get('/federated/google/redirect', passport.authenticate('google'), authController.afterGoogleLogin);

/**
 * @swagger
 * /auth/local:
 *     post:
 *         summary: Local login
 *         description: Login with username and password
 *         tags:
 *             - Auth
 *         requestBody:
 *             required: true
 *             content:
 *                 application/json:
 *                     schema:
 *                         type: object
 *                         properties:
 *                           username:
 *                             type: string
 *                           password:
 *                             type: string
 *                         required:
 *                           - username
 *                           - password
 */
authRouter.post('/local', passport.authenticate('local'), authController.afterLocalLogin);

/**
 * @swagger
 * /auth/local/register:
 *     post:
 *         summary: Register with local strategy
 *         description: Register with username, password, name and email
 *         tags:
 *             - Auth
 *         requestBody:
 *             description: The user you want to create
 *             required: true
 *             content:
 *                 application/json:
 *                     schema:
 *                         type: object
 *                         properties:
 *                           username:
 *                             type: string
 *                           password:
 *                             type: string
 *                           name:
 *                             type: string
 *                           email:
 *                             type: string
 *                         required:
 *                           - username
 *                           - password
 *                           - name
 *                           - email
 */
authRouter.post('/local/register', authController.register);

/**
 * @swagger
 * /auth/logout:
 *     post:
 *         summary: Logout
 *         description: Logout the user
 *         tags:
 *             - Auth
 */
authRouter.post('/logout', authMiddleware.ensureAuthenticated, authController.logout);

/**
 * @swagger
 * /auth/local/forgot:
 *     post:
 *         summary: Init a reset of forgotten password
 *         tags:
 *             - Auth
 *         requestBody:
 *             description: User
 *             required: true
 *             content:
 *                 application/json:
 *                     schema:
 *                         type: object
 *                         properties:
 *                           username:
 *                             type: string
 *                         required:
 *                           - username
 */
authRouter.post('/local/forgot', authController.initForgotPassword)

/**
 * @swagger
 * /auth/local/forgot/{token}:
 *     post:
 *         summary: Finalize a reset of forgotten password
 *         tags:
 *             - Auth
 *         parameters:
 *             - in: path
 *               name: token
 *               schema:
 *                   type: string
 *               required: true
 *         requestBody:
 *             description: New password
 *             required: true
 *             content:
 *                 application/json:
 *                     schema:
 *                         type: object
 *                         properties:
 *                           password:
 *                             type: string
 *                         required:
 *                           - password
 */
authRouter.post('/local/forgot/:token', authController.finalizeForgotPassword)


module.exports = authRouter;
const express = require('express');
const usersController = require('../controllers/users.controller');
const usersMiddleware = require('../middlewares/users.middleware');
const usersRouter = express.Router();

usersRouter.get('/', usersController.getUsers);

usersRouter.post('/register', usersMiddleware.validateUser, usersController.registerUser);
/**
 * @swagger
 * /register:
 *   post:
 *     summary: Inscription d'un utilisateur
 *     parameters:
 *       - in: body
 *         name: user
 *         description: Utilisateur à inscrire
 *         required: true
 *         schema:
 *           type: object
 *           properties:
 *             fullname:
 *               type: string
 *               description: Nom complet de l'utilisateur
 *             username:
 *               type: string
 *               description: Nom d'utilisateur unique
 *             password:
 *               type: string
 *               description: Mot de passe de l'utilisateur
 *     responses:
 *       201:
 *         description: Utilisateur inscrit avec succès
 *       400:
 *         description: Données d'inscription invalides
 */

usersRouter.post('/login', usersMiddleware.isAuthenticated, usersController.login)
/**
 * @swagger
 * /login:
 *   post:
 *     summary: Connexion d'un utilisateur
 *     parameters:
 *       - in: body
 *         name: credentials
 *         description: Identifiants de l'utilisateur
 *         required: true
 *         schema:
 *           type: object
 *           properties:
 *             username:
 *               type: string
 *               description: Nom d'utilisateur
 *             password:
 *               type: string
 *               description: Mot de passe de l'utilisateur
 *     responses:
 *       200:
 *         description: Connexion réussie
 *       401:
 *         description: Identifiants incorrects
 *       400:
 *         description: L'utilisateur est déjà connecté
 */

usersRouter.post('/logout', usersMiddleware.isAuthenticated, usersController.logoutUser);
/**
 * @swagger
 * /logout:
 *   post:
 *     summary: Déconnexion de l'utilisateur
 *     responses:
 *       200:
 *         description: Déconnexion réussie
 *       401:
 *         description: Non autorisé. Veuillez vous connecter.
 */

usersRouter.get('/info', usersMiddleware.isAuthenticated, usersController.getUserInfo);
/**
 * @swagger
 * /info:
 *   get:
 *     summary: Récupérer les informations de l'utilisateur connecté
 *     responses:
 *       200:
 *         description: Informations de l'utilisateur récupérées avec succès
 *       401:
 *         description: Non autorisé. Veuillez vous connecter.
 */

module.exports = usersRouter;

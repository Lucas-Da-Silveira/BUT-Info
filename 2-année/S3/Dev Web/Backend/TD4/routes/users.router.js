const express = require('express');
var router = express.Router();
const usersController = require('../controllers/users.controller');
const usersMiddlewares = require('../middlewares/users.middleware');

router.get("/home", (req, res) => {
    res.send("Home")
});

router.post("/",usersMiddlewares.validateUserInput ,usersController.saveUser);
 /**
 * @swagger
 * /users:
 *   post:
 *     description: Used to create a new user
 *     tags:
 *       - users
 *     parameters:
 *       - in: body
 *         name: user
 *         description: User data to create a new user
 *         schema:
 *           type: object
 *           required:
 *             - nom
 *             - prenom
 *           properties:
 *             nom:
 *               type: string
 *               minLength: 1
 *               maxLength: 45
 *               example: Dupont
 *             prenom:
 *               type: string
 *               minLength: 1
 *               maxLength: 45
 *               example: Jean
 *     responses:
 *       '200':
 *         description: User created successfully
 *       '400':
 *         description: Bad request
 *       '500':
 *         description: Internal server error
 */


router.post("/bulk");

router.get("/top",usersController.getTopUsers);
 /**
 * @swagger
 * /users/top:
 *   get:
 *     description: Used to get the user with the longest prenom
 *     tags:
 *       - users
 *     responses:
 *       '200':
 *         description: Successfully retrieved the user with the longest prenom
 *       '500':
 *         description: Internal server error
 */

router.get("/", usersController.getUsers);
/**
 * @swagger
 * /users/:
 *   get:
 *     description: Used to retrieve all users
 *     tags:
 *       - users
 *     responses:
 *       '200':
 *         description: A list of users
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 type: object
 *                 properties:
 *                   [Define user properties here, like id, name, etc.]
 *       '500':
 *         description: Internal server error
 */


router.get("/:uuid",usersController.getUsersById);
 /**
 * @swagger
 * /users/{uuid}:
 *   get:
 *     description: Used to get a specific user by UUID
 *     tags:
 *       - users
 *     parameters:
 *       - in: path
 *         name: uuid
 *         type: string
 *         required: true
 *         description: UUID of the user to get
 *     responses:
 *       '200':
 *         description: Successfully retrieved user
 *       '404':
 *         description: User not found
 *       '500':
 *         description: Internal server error
 */

router.put("/:uuid",usersMiddlewares.validateUserInput, usersController.updateUser);
/**
 * @swagger
 * /users/{uuid}:
 *   put:
 *     description: Used to update an existing user
 *     tags:
 *       - users
 *     parameters:
 *       - in: path
 *         name: uuid
 *         type: string
 *         required: true
 *         description: UUID of the user to update
 *       - in: body
 *         name: user
 *         description: User data with new values of properties
 *         schema:
 *           type: object
 *           required:
 *             - nom
 *             - prenom
 *           properties:
 *             nom:
 *               type: string
 *               minLength: 1
 *               maxLength: 45
 *               example: Dupont
 *             prenom:
 *               type: string
 *               minLength: 1
 *               maxLength: 45
 *               example: Jean
 *     responses:
 *       '200':
 *         description: User updated successfully
 *       '400':
 *         description: Bad request
 *       '404':
 *         description: User not found
 *       '500':
 *         description: Internal server error
 */

router.delete("/", usersMiddlewares.validateUserInput, usersController.deleteUser);
 /**
 * @swagger
 * /users:
 *   delete:
 *     description: Used to delete a user by UUID
 *     tags:
 *       - users
 *     parameters:
 *       - in: query
 *         name: uuid
 *         type: string
 *         required: true
 *         description: UUID of the user to delete
 *     responses:
 *       '200':
 *         description: User deleted successfully
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 [Define properties of the returned data after deletion]
 *       '400':
 *         description: Bad request - UUID missing or invalid
 *       '404':
 *         description: User not found
 *       '500':
 *         description: Internal server error
 */

router.delete("/bulk", usersController.deleteAllUser);

module.exports = router;
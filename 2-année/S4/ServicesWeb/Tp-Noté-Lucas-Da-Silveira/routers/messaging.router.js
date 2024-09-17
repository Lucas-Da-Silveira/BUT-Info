const express = require('express')
const messagingRouter = express.Router()

const messagingController = require('../controllers/messaging.controller')

/**
 * @swagger
 * /messages:
 *     post:
 *         summary: Add a new message
 *         tags:
 *             - Messaging
 *         requestBody:
 *             required: true
 *             content:
 *                 application/json:
 *                     schema:
 *                         type: object
 *                         summary: The message you want to send
 *                         properties:
 *                             message:
 *                                 type: string
 *                             userid:
 *                                 type: number
 *                         required:
 *                             - message
 *                             - userid
 */
messagingRouter.post('/messages', messagingController.addMessage)

/**
 * @swagger
 * /users:
 *     get:
 *         summary: Get all users that have sent a message
 *         tags:
 *             - Messaging
 */
messagingRouter.get('/users', messagingController.getAllUsers)

/**
 * @swagger
 * /messages/{mode}/{date}:
 *     get:
 *         summary: Get all messages before or after a date
 *         tags:
 *             - Messaging
 *         parameters:
 *             - in: path
 *               name: mode
 *               schema:
 *                   type: string
 *                   enum:
 *                       - after
 *                       - before
 *               required: true
 *               description: Select the filter mode (after or before)
 *             - in: path
 *               name: date
 *               schema:
 *                   type: string
 *               required: true
 *
 */
messagingRouter.get('/messages/:mode/:date', messagingController.getAllMessagesAfterOrBeforeDatetime)

/**
 * @swagger
 * /messages/{search}:
 *     get:
 *         summary: Search a message with given search str
 *         tags:
 *             - Messaging
 *         parameters:
 *             - in: path
 *               name: search
 *               schema:
 *                   type: string
 *               required: true
 *
 */
messagingRouter.get('/messages/:search', messagingController.searchMessage)


/**
 * @swagger
 * /messages/{username}:
 *     delete:
 *         summary: Remove history of a user - if username not set, remove all history
 *         tags:
 *             - Messaging
 *         parameters:
 *             - in: path
 *               name: username
 *               schema:
 *                   type: string
 *               required: false
 *
 */
messagingRouter.delete('/messages/:username?', messagingController.removeHistory)

/**
 * @swagger
 * /messages/{dateStart}/{dateEnd}/{username}:
 *     get:
 *         summary: Get all messages between two dates, for a user
 *         tags:
 *             - Messaging
 *         parameters:
 *             - in: path
 *               name: dateStart
 *               schema:
 *                   type: date
 *               required: true
 *             - in: path
 *               name: dateEnd
 *               schema:
 *                   type: date
 *               required: true
 *             - in: path
 *               name: username
 *               schema:
 *                   type: string
 *               required: true
 */
messagingRouter.get('/messages/:dateStart/:dateEnd/:username', messagingController.searchMessageBetweenDatesForAUser)


module.exports = messagingRouter
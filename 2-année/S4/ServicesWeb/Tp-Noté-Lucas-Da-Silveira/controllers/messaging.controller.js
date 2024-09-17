const Message = require('../services/db/mongo.db.service')
const messagingService = require('../services/messaging.service')
const authService = require('../services/auth.service')

async function addMessage(req, res) {
    const addedMessage = await messagingService.addMessage(req.user.id, req.body.message)
    if (!addedMessage) return res.sendStatus(500)
    else return res.status(201).send(addedMessage)
}

async function getAllUsers(req, res) {
    let allUsers = []
    await Message.distinct('author', (err, users) => {
        if (err) {
            return res.sendStatus(500)
        } else {
            for (const userId of users) {
                allUsers.push({
                    ...authService.getUserById(userId)
                })
            }
        }
    })
    return allUsers
}

function getAllMessagesAfterOrBeforeDatetime(req, res) {
    const specifiedDate = new Date(req.params.date)

    switch (req.params.mode) {
        case "after":
            Message.find({datetime: {$lt: specifiedDate}}, (err, messages) => {
                if (err) return res.sendStatus(500)
                else return res.status(200).send(messages)
            })
            break
        case "before":
            Message.find({datetime: {$gt: specifiedDate}}, (err, messages) => {
                if (err) return res.sendStatus(500)
                else return res.status(200).send(messages)
            })
            break
        default:
            return res.sendStatus(400)
    }
}

async function searchMessage(req, res) {
    const messages = await messagingService.searchMessages(req.params.search)
    if (!messages) return res.sendStatus(500)
    else return res.status(200).send(messages)
}

async function removeHistory(req, res) {
    if (req.params.username) {
        const user = await authService.getUserByUsername(req.params.username)
        await messagingService.removeHistoryOfUser(user.id)
        return res.sendStatus(200)
    }
    await messagingService.removeHistoryOfAllUsers()
    return res.sendStatus(200)
}

async function searchMessageBetweenDatesForAUser(req, res) {
    const { dateStart, dateEnd, username } = req.params
    const start = new Date(dateStart)
    const end = new Date(dateEnd)
    const user = await authService.getUserByUsername(username)
    if(!user) return res.send(404).send("Username not found")
    Message.find({datetime: {$gt: start, $lt: end}, authorId: {$eq: user.id}}, (err, messages) => {
        if(err) return res.sendStatus(500)
        return res.status(200).send(messages)
    })
}

module.exports = {
    addMessage,
    getAllUsers,
    getAllMessagesAfterOrBeforeDatetime,
    searchMessage,
    removeHistory,
    searchMessageBetweenDatesForAUser
}

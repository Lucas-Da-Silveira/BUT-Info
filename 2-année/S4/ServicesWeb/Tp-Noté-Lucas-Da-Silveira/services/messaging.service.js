const authService = require('./auth.service')
const sqliteDb = require('./db/sqlite.db.service')
const Message = require('./db/mongo.db.service')

async function addMessage(userid, message) {
    const user = authService.getUserById(userid)
    const newMessage = new Message({
        authorId: user.id,
        message: message
    })
    return await newMessage.save()
}

function searchMessages(contenuRecherche) {
    const regex = new RegExp(contenuRecherche, 'i'); // 'i' pour ignorer la casse

    return Message.find({message: regex}, (err, messages) => {
        if (err) {
            return null
        } else {
            return messages
        }
    });
}

function removeHistoryOfUser(userId) {
    Message.deleteMany({'authorId': userId}, (err) => {
        if (err) {
            console.error(err);
        } else {
            console.log("L'historique de chat de l'utilisateur " + userId + " a été supprimé avec succès.");
        }
    });
}

function removeHistoryOfAllUsers() {
    Message.deleteMany({}, (err) => {
        if (err) {
            console.error(err);
        } else {
            console.log("L'historique de chat de tous les utilisateurs a été supprimé avec succès.");
        }
    });
}


module.exports = {
    addMessage,
    searchMessages,
    removeHistoryOfUser,
    removeHistoryOfAllUsers
}
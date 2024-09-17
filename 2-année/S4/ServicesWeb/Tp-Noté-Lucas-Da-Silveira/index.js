require('dotenv').config();

const WhatZap = require('./api')
const server = require('http').createServer(WhatZap)
const socketServer = require('socket.io')(server);

const connectednames = []

const authService = require('./services/auth.service')
const messagingService = require('./services/messaging.service')

socketServer.sockets.on('connection', function(socket) {
    console.log("Socket connecté...")
    socket.on('new user', async (data, cb) => {
        if (connectednames.indexOf(data) !== -1) {
            cb(false);
        } else {
            cb(true);
            authService.getUserById(data).then((value) => {
                console.log(value)
                socket.user = value
                connectednames.push(socket.user.name)
                updateUsernames()
            })
        }
    })

    socket.on('send message', async (data) => {
        messagingService.addMessage(data.userid, data.message)
        socket.emit('new message', {msg: data.message, user: (await authService.getUserById(data.userid)).name})
    })

    socket.on('disconnect', (data) => {
        if(!socket.user) return;
        connectednames.splice(connectednames.indexOf(socket.user.name), 1)
        updateUsernames()
    })

    function updateUsernames() {
        socket.emit('usernames', connectednames);
    }
})


const port = normalizePort(process.env.PORT || '3000');
server.listen(port, () => {
    console.log(`Le serveur écoute sur le port ${port}`)
})


function normalizePort(val) {
    const port = parseInt(val, 10);

    if (isNaN(port)) {
        return val;
    }

    if (port >= 0) {
        return port;
    }

    return false;
}
const mongoose = require('mongoose')

mongoose.connect(process.env.MONGO_URI, {
    user: process.env.MONGO_USERNAME,
    pass: process.env.MONGO_PASSWORD
})

const Message = mongoose.model('Message', {
    authorId: Number,
    datetime: { type: Date, default: Date.now },
    message: String
})

module.exports = Message
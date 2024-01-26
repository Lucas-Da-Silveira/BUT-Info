require('dotenv').config();
module.exports = {
    //vous pouvez modifier cette valeur
    secret: process.env.AUTH_SECRET ||  "darth-vader"
};
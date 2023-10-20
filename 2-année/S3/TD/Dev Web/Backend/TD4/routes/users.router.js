const express = require('express');
var router = express.Router();
const usersController = require('../controllers/users.controller');
const usersMiddlewares = require('../middlewares/users.middleware');

router.get("/home", (req, res) => {
    res.send("Home")
});

router.post("/",usersMiddlewares.validateUserInput ,usersController.saveUser);

module.exports = router;
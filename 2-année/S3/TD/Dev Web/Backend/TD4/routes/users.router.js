const express = require('express');
var router = express.Router();
const usersController = require('../controllers/users.controller');

router.get("/home", (req, res) => {
    res.send("Home")
});

router.post("/", usersController.saveUser);

module.exports = router;
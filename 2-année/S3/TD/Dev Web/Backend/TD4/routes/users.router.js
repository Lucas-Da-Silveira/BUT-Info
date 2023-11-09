const express = require('express');
var router = express.Router();
const usersController = require('../controllers/users.controller');
const usersMiddlewares = require('../middlewares/users.middleware');

router.get("/home", (req, res) => {
    res.send("Home")
});

router.post("/",usersMiddlewares.validateUserInput ,usersController.saveUser);

router.get("/top",usersController.getTopUsers);
router.get("/", usersController.getUsers);
router.get("/:uuid",usersController.getUsersById);

router.put("/:uuid",usersMiddlewares.validateUserInput, usersController.updateUser);

router.delete("/", usersMiddlewares.validateUserInput, usersController.deleteUser);

module.exports = router;
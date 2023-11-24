const express = require('express');
var router = express.Router();
const itemsController = require('../controllers/items.controllers');
//const itemsMiddlewares = require('../middlewares/items.middlewares');


router.get("/home", (req, res) => {
    res.send("Home")
});
router.get("/items", itemsController.getItems);


module.exports = router;
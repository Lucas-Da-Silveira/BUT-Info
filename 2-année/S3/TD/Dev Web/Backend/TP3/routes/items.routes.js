const express = require('express');
var router = express.Router();
const itemsController = require('../controllers/items.controllers');
//const itemsMiddlewares = require('../middlewares/items.middlewares');


router.get("/home", (req, res) => {
    res.send("Home")
});
router.get("/items", itemsController.getItems);

router.get("/items/:id", itemsController.getItemsById);

router.get("/items/:id/promotion", itemsController.getItemsPromotion);
module.exports = router;
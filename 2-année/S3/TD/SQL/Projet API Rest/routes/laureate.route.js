const laureateRoute = require('express');
var app = laureateRoute.Router();

const laureatesController = require('../controllers/laureates.controllers');
//const laureatesMiddlewares = require('../middlewares/laureates.middlewares');


app.get('/laureates', laureatesController.listLaureates);
app.get('/laureates/:id', laureatesController.getLaureateById);
app.get('/multiple-prizes-laureates', laureatesController.getMultiplePrizesLaureates);

app.delete('/laureates/:id', laureatesController.deleteLaureateById);

app.put('/laureates/:id/:year/:category', laureatesController.updateMotivation);
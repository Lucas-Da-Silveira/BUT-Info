const laureateRoute = require('express');
var app = laureateRoute.Router();

const prizesController = require('../controllers/prizes.controllers');

app.get('/prize-categories', prizesController.getPrizeCategories);
app.get('/category-most-laureates', prizesController.getCategoryWithMostLaureates);
app.get('/laureates-count-by-year', prizesController.getLaureatesCountByYear);
app.get('/empty-years', prizesController.getEmptyYears);
app.get('/sorted-years-by-laureates', prizesController.getSortedYearsByLaureates);

const express = require('express');
const laureatesController = require('./controllers/laureates.controllers');
const prizesController = require('./controllers/prizes.controllers');

const app = express();
const port = 3000;

app.get('/laureates', laureatesController.listLaureates);
app.get('/laureates/:id', laureatesController.getLaureateById);
app.get('/multiple-prizes-laureates', laureatesController.getMultiplePrizesLaureates);

app.get('/prize-categories', prizesController.getPrizeCategories);
app.get('/category-most-laureates', prizesController.getCategoryWithMostLaureates);
app.get('/laureates-count-by-year', prizesController.getLaureatesCountByYear);
app.get('/empty-years', prizesController.getEmptyYears);
app.get('/sorted-years-by-laureates', prizesController.getSortedYearsByLaureates);

app.delete('/laureates/:id', laureatesController.deleteLaureateById);

app.put('/laureates/:id/:year/:category', laureatesController.updateMotivation);


app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
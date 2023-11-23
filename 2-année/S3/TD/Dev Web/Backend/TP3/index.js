const express = require('express');
const itemsRouters = require('./routes/items.routes');
const dotenv = require('dotenv');
dotenv.config();
const app = express();

app.use(express.json());


app.listen(process.env.PORT, () => {
    console.log(`Server is running on port ${process.env.PORT}`);
});
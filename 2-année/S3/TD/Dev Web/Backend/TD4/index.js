const express = require('express');
const usersRouter = require('./routes/users.router');
const dotenv = require('dotenv');
dotenv.config();
const app = express();


app.use(express.json());
app.use("/users", usersRouter);

app.listen(process.env.PORT, () => {
    console.log(`Le serveur ecoute sur port: ${process.env.PORT}`);
});
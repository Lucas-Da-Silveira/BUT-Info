const express = require('express');
const usersRouter = require('./routes/users.router');
const swaggerJsdoc = require("swagger-jsdoc");
const swaggerUi = require("swagger-ui-express");
const dotenv = require('dotenv');
dotenv.config();
const app = express();


app.use(express.json());

/** Swagger Initialization - START */
const swaggerOption = {
    swaggerDefinition: (swaggerJsdoc.Options = {
        info: {
            title: "Los pinguinos me la van a mascar",
            description: "API documentation",
            contact: {
                name: "Ldasilve",
            },
            servers: ["http://localhost:3000/"],
        },
    }),
    apis: ["index.js", "./routes/*.js"],
};

const swaggerDocs = swaggerJsdoc(swaggerOption);
app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerDocs));
/** Swagger Initialization - END */

app.use("/users", usersRouter);

app.listen(process.env.PORT, () => {
    console.log(`Le serveur ecoute sur port: ${process.env.PORT}`);
});
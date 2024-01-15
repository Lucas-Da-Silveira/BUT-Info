const express = require('express');
const bodyParser = require('body-parser');
const session = require('express-session');
const dotenv = require('dotenv');
const swaggerUi = require('swagger-ui-express');
const swaggerJsdoc = require('swagger-jsdoc');
const usersRouter = require('./routers/users.router');

dotenv.config();

const app = express();
const PORT = process.env.PORT || 3001;

app.use(bodyParser.json());

app.use(
    session({
        secret: process.env.SESSION_SECRET,
        resave: true,
        saveUninitialized: true,
        cookie: {
            maxAge: 2 * 60 * 60 * 1000,
        }
    })
)

app.use('/users', usersRouter);

const swaggerOptions = {
    swaggerDefinition: (swaggerJsdoc.Options = {
        info: {
            openapi: "2.0.0",
            title: "Partiel Node",
            description: "API documentation",
            contact: {
                name: "Lucas Da Silveira",
            },
            servers: ["http://localhost:3001"],
        },
    }),
    apis: ['./routers/users.router.js'],
};


const swaggerSpec = swaggerJsdoc(swaggerOptions);

app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));


app.listen(PORT, () => {
    console.log(`Serveur en écoute sur le port ${PORT}`);
});

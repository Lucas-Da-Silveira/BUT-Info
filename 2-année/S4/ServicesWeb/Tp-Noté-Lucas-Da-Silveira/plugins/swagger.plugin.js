const swaggerJsdoc = require('swagger-jsdoc');
const swaggerUi = require('swagger-ui-express');

const options = {
    definition: {
        openapi: '3.0.0',
        info: {
            title: 'ouilleMessage',
            version: 'alpha1.0',
            description: 'A simple messaging app',
        }
    },
    apis: ['./routers/*.router.js', './api.js', './index.js']
}

const specs = swaggerJsdoc(options);

module.exports = (app) => {
    app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(specs));
}
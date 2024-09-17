const express = require('express');
const keys = require('./config/keys');
const cors = require('cors');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cookieSession = require('cookie-session');
const passport = require('passport');
const swaggerUi = require('swagger-ui-express');
const YAML = require('yamljs');
const path = require('path');

// Connexion BDD
require('./models/User');
require('./models/Blog');

mongoose.connect(keys.mongoURI);

// Passport configuration
require('./services/passport');


const app = express();

// CORS configuration
const corsOptions = {
  origin: "http://localhost:3000",
  methods: "GET,PUT,DELETE,POST,HEAD,PATCH",
  credentials: true, // permet d'envoyer des cookies et sessions
  optionsSuccessStatus: 204
}
app.use(cors(corsOptions));


// COOKIES AND SESSIONS
app.use(bodyParser.json());
app.use(
  cookieSession({
    maxAge: 30 * 24 * 60 * 60 * 1000, // 30 jours
    keys: [keys.cookieKey]
  })
);
app.use(passport.initialize());
app.use(passport.session());


// SWAGGER DOC
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(YAML.load('./swagger/swaggerDoc.yaml')));

require('./routes/authRoutes')(app);
require('./routes/blogRoutes')(app);


app.get('/', (req, res) => {
  res.send('Salut!');
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Le serveur ecoute sur le port: `, PORT);
});
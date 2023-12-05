const express = require("express");
const path = require("path");
const session = require('express-session');

const app = express();



// allow the express server to process POST request rendered by the ejs files
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// allow the express server to read and render the static css file
app.use(express.static(path.join(__dirname, "public")));
app.set("view engine", "ejs");

// render the ejs views
app.set("views", path.join(__dirname, "views"));

// a port number to expose the server
const PORT = 3000;

app.get("/", (req, res) => {

});

app.get("/login", (req, res) => {

});

app.get("/welcome", (req, res) => {

});

app.post("/process_login", (req, res) => {

});

app.get("/logout", (req, res) => {

});

app.listen(PORT, () => console.log(`Le serveur ecoute sur le port: ${PORT}`));

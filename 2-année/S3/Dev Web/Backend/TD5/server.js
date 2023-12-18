const express = require('express')
const cookieParser = require('cookie-parser')

//setup express app
const app = express()

// let’s you use the cookieParser in your application
app.use(cookieParser());

//set a simple for homepage route
app.get('/', (req, res) => {
    res.send('bienvenue sur un simple serveur HTTP de cookies');
});


//a get route for adding a cookie
app.get('/setcookie', (req, res) => {
    res.cookie(`My Cookie`,`S32023`);
    res.send('Le cookie a été enregistré avec succès');
});



// delete the saved cookie
app.get('/deletecookie', (req, res) => {
    //show the saved cookies
    res.clearCookie('My Cookie');
    res.send('Le cookie a été supprimé avec succès');
});

// get the cookie incoming request
app.get('/getcookie', (req, res) => {
    //show the saved cookies
    console.log(req.cookies)
    res.send(req.cookies);
});


//server listening to port 8000
app.listen(3000, () => console.log('Le serveur ecoute sur le port 3000...'));

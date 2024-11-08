const express = require("express");
const path = require("path");
const session = require('express-session');

const app = express();

app.use(session({
    secret: 'monsecret',
    resave: false,
    saveUninitialized: true,
    cookie: { secure: false }
}));

// allow the express server to process POST request rendered by the ejs files
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// allow the express server to read and render the static css file
app.use(express.static(path.join(__dirname, "public")));
app.set("view engine", "ejs");

// render the ejs views
app.set("views", path.join(__dirname, "views"));

// a port number to expose the server
const PORT = 3001;

app.get("/", (req, res) => {
    let username = req.session.username;
    return res.render(("home"), {
        username
    });
});

app.get("/login", (req, res) => {
    let username = req.session.username;
    if(username){
        return res.redirect("/welcome");
    }
    let conx_echoue = req.query.msg ? true : false  ;
    if(conx_echoue){
        return res.render("login",{
            error: "Username ou Password incorrecte"
        });
    }else {
        return res.render("login")
    }

});

app.get("/welcome", (req, res) => {
    let username = req.session.username
    if(username){
        return res.render("welcome", {
            username
        });
    }
    return res.redirect("/login");
});

app.post("/process_login", (req, res) => {
    let {username, password} = req.body;
    let monUser={
        username:"admin",
        password:"admin"
    }
    if(username === monUser["username"] && password === monUser["password"]){
            req.session.username = username;
        return res.redirect("/welcome");
    }else {
        return res.redirect("/login?msg=fail")
    }
});

app.get("/logout", (req, res) => {
    req.session.destroy(err =>{
        if(err){
            return res.redirect("/welcome");
        }
        res.redirect("/login");
    });
});


app.listen(PORT, () => console.log(`Le serveur ecoute sur le port: ${PORT}`));

// VOTRE SERVEUR WEB
const express = require('express');
const path = require('path');

const app = express();
const PORT = 3000;

app.use(express.static(path.join(__dirname,'public')));

app.use(express.urlencoded({extended:true}));

app.get("/",(req,res)=>{
    res.sendFile(path.join(__dirname,'public',"index.html"));
});

app.get('/api/notes',(req,res)=>{
    let notesJSON = notes.loadNotes();
    res.send(notesJSON);
});

app.listen(PORT,() =>{
    console.log(`Server ecoute sur port ${PORT}`);
});

app.post("/api/notes",(req,res)=>{
    let title = req.body.title;
    let body = req.body.body;
    nots.addNote(title,body);
    res.json({"message":"note saaved"});
});
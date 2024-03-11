var http = require("http");
var events = require("events");

var server = http.createServer((req,res)=>{
    res.setHeader("Content-Type", "text/html");
    res.write("<h2>Bonjour !!!!</h2>");
    res.end();
})

server.listen(3000);
console.log("Le serveur ecoute sur le port 3000");
console.log(server instanceof events.EventEmitter);




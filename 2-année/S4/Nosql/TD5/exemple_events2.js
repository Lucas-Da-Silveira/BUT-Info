var events = require("events");

var obj1 = new events.EventEmitter();

obj1.emit("event1");

obj1.addListener("event1", function(){
   console.log("L'objet obj1 a recu un événement event1");
});



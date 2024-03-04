var events = require("events");

var obj1 = new events.EventEmitter();

obj1.addListener("event1", function(){
    console.log("1- L'objet obj1 a recu un événement event1");
});
obj1.addListener("event1", f = function(){
    console.log("2- L'objet obj1 a recu un événement event1");
});

obj1.emit("event1");
obj1.emit("event1");

obj1.removeListener("event1", f);
obj1.emit("event1");

obj1.removeAllListeners("event1");
obj1.emit("event1");




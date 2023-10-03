const items = require('./models/items');

items.copyCallback(error => {
    if(error){console.log("Erreur", error)}
    else{console.log("Precessus terminé")}
})

items.copyPromise()
    .then(()=> console.log(("Prcesus terminé")))
    .catch((error)=> console.log("Erreur", error))

items.capyAsync()
    .then(()=> console.log(("Prcesus terminé")))
    .catch((error)=> console.log("Erreur", error))

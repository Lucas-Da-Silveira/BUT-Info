// npm install prompt-sync
const prompt = require('prompt-sync')();



var param_db=require('./connexion_db.js')

var mysql = require('mysql');
console.log(param_db)
var db    = mysql.createConnection(param_db)

var titre = prompt('titre du message ?');
var content = prompt('contenu du message ?');
var user = prompt('user ?');




// INSERT

var date1= new Date();
var dateMysql=date1.getFullYear() + "-" + (1 + date1.getMonth()) + "-" + (date1.getDate()); // + " " + twoDigits(date1.getHours()) + ":" + twoDigits(date1.getMinutes()) + ":" + twoDigits(date1.getSeconds());


var donnees = {
  titre : titre,
  content : content,
  user: user,
  date : dateMysql
};


//var requete="INSERT INTO messages (titre,content,date) VALUES ('" + donnees['titre'] + "','"
//  + donnees['content'] + "','" + donnees['date'] + "');";

var requete="INSERT INTO messages (titre,content,date, user) VALUES (?,?,?,?);";

console.log(requete);
db.query(requete, [donnees['titre'], donnees['content'], donnees['date'] , donnees['user']],    
    // on passe un tableau et non un dictionnaire
    // callback
    function(err) {
      if (!err) {
          console.log("insert OK");
          process.exit(1);
        }   else  console.log(err);
      }
);

var param_db=require('./connexion_db.js')

var mysql = require('mysql');
var db    = mysql.createConnection(param_db)

// DELETE
if (process.argv.length <= 2)
{
    console.log('nombre de parametres : ' + process.argv.length)
    console.log('usage: deleteMessage id [id2 id3]' )
    process.exit(1);
}

var id = process.argv[2];

var list_id=[]
if (process.argv.length >= 3)    // https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Global_Objects/Array/Reduce#retirer_les_doublons_dun_tableau
    {
    list_id=process.argv.slice(2).reduce(function (acc, valCourante) {
        if(acc.indexOf(valCourante) === -1) {
          acc.push(valCourante);
        }
        return acc
    }, []);
    console.log(list_id)
    };







var requete="DELETE FROM messages WHERE id=?";

console.log(requete);

var requete2="DELETE FROM messages WHERE id IN (?);"


/*
db.query(requete, [id],        // on passe un tableau et non un dictionnaire
    // callback
    function(err) {
      if (!err) {
          console.log("DELETE OK");
          process.exit(1);
        }   else  console.log(err);
      }
);
*/

db.query(requete2, [list_id],        // on passe un tableau et non un dictionnaire
    // callback
    function(err) {
      if (!err) {
          console.log("DELETE OK");
          process.exit(1);
        }   else  console.log(err);
      }
);

var param_db=require('./connexion_db.js')

var mysql = require('mysql');
var db    = mysql.createConnection(param_db)


// UPDATE
if (process.argv.length != 7)
    {
    process.argv.forEach(function (val, index, array) {
        console.log(index + ': ' + val);
    });
    console.log('nombre de parametres : ' + process.argv.length)
    console.log('usage: updateMessage titre="le nouveau titre" message="le nouveau message" content="le nouveau contenu du message" user="utilisateur" id=1' )
    process.exit(1);
    }
else
    {
    const mesArgs = process.argv.slice(2);
    dtoParam=process.argv.slice(2).reduce((processArgs, val) => {
        let [key, value] = val.split('=');
        processArgs[key] = value;
        return processArgs;
        }, 
    {})
    //console.log(mesArgs)
    console.log(dtoParam)
    }


var date1= new Date();
var dateMysql=date1.getFullYear() + "-" + (1 + date1.getMonth()) + "-" + (date1.getDate()); 

dtoParam['date']= dateMysql
console.log(dtoParam)


var requete="UPDATE messages SET  titre=? ,content=? , date=?, user=? WHERE  id=? ;";

console.log(requete);
db.query(requete, [dtoParam['titre'], dtoParam['content'], dtoParam['dateMysql'], dtoParam['user'] , dtoParam['id']],        // on passe un tableau et non un dictionnaire
    // callback
    function(err) {
      if (!err) {
          console.log("update OK");
          process.exit(1);
        }   else  console.log(err);
      }
);


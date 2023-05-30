var mysql = require('mysql');
var db    = mysql.createConnection({
    
  host     : 'localhost',  
  user     : 'ldasilve',  
  password : '1603',
  database : 'BDD_ldasilve'
});


// SELECT 

db.query('SELECT * FROM messages;',
    // callback
  function(err, listeMessages) {
    if (!err) {
            
            console.log("listes des messsages :"+ listeMessages);

            console.log("\n******\nmesssage 1, titre :"+ 
                        listeMessages[0]['titre'] + 
                        ", date : " + listeMessages[0]['date'] +
                        "\n\n******\n");

            listeMessages.forEach((element) => {
                console.log(element)
            });
            console.log(JSON.stringify(listeMessages));
            process.exit(1);
        }
    else {
         console.log(err);
         process.exit(0);
       }
    }
);




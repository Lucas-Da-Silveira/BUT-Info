var param_db={
  host     : 'localhost',  
  user     : 'login',  
  password : 'secret',
  database : 'BDD_s2_tp',
};
var mysql = require('mysql');

module.exports = param_db               //mysql.createConnection(param_db)

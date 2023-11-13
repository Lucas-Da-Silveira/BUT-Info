const pool = require('./database/db');
const {rows} = require("pg/lib/defaults");

async function getUser(id){
    const client = await pool.connect();
    const rows = client.query("SELECT * FROM UTILISATEURS WHERE user_id = $1", [id])
    client.release();
    return rows
}

getUser(1).then((value) => {
    console.log(value);
})
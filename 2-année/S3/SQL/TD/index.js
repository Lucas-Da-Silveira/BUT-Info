const pool = require('./database/db');

async function getUser(id){
    const client = await pool.connect();
    const res = await client.query("SELECT * FROM utilisateurs WHERE User_id = $1", [id])
    client.release();
    return res.rows
}

/*
getUser(1).then((data) => {
    console.log(data);
    pool.end();
})
console.log("after getUser")*/


async function login(firtsname,lastname,password) {
    const client = await pool.connect();
    const res = await client.query("SELECT password FROM utilisateurs WHERE first_name = $1 AND last_name = $2", [firtsname, lastname])

    client.release();

    if (res.rows.length === 0) {
        return false
    }
    return res.rows[0].password === password
}

async function changePassword(firstname,lastname, password, new_password){
    const client = await pool.connect();
    const res = await client.query("SELECT password FROM utilisateurs WHERE first_name = $1 AND last_name = $2", [firstname, lastname])

    if (res.rows.length === 0){
        return false
    }
    if (res.rows[0].password !== password){
        return false
    }
    await client.query("UPDATE utilisateurs SET password = $1 WHERE first_name = $2 AND last_name = $3", [new_password, firstname, lastname])
    client.release();
    return true
}

changePassword("Joseph","Azar","joseph123","joseph1234").then((res) => {
    if (res){
        console.log("Password changed")
    }else{
        console.log("Password change failed")
    }
    pool.end();
});
login("Joseph","Azar","joseph1234").then((res) => {
    if (res){
        console.log("Login successful")
    }else{
        console.log("Login failed")
    }
    pool.end();
});



const pool = require('./database/connection');

async function getUser(id) {
    const client = await pool.connect();
    const res = await client.query("SELECT * FROM utilisateurs WHERE User_Id = $1", [id]);
    client.release();
    return res.rows;
}
async function login(firstname, lastname, password) {
    const client = await pool.connect();
    const res = await client.query("SELECT password FROM utilisateurs WHERE first_name = $1 AND last_name = $2;", [firstname, lastname]);

    client.release();

    if(res.rows.length === 0) {
        return false;
    }

    return password === res.rows[0].password;
}
async function getUserId(client, firstName, lastName) {
    const res = await client.query("SELECT User_id FROM UTILISATEURS WHERE First_Name = $1 AND Last_Name = $2", [firstName, lastName]);
    if(res.rowCount === 0) return -1;
    return res.rows[0]["user_id"];
}

async function hasPassword(client, user_id, pass) {
    const res = await client.query("SELECT P.password AS P, U.password AS U FROM mots_de_passe_utilisateurs AS P RIGHT JOIN utilisateurs U ON P.User_id = U.User_id WHERE U.User_id = $1", [user_id]);
    console.log(res.rows);

    if(res.rows[0]["U"] === pass) return false;

    for(let r = 0; r < res.rows.length; r++) {
        const line = res.rows[r];
        if(line["p"] === pass) return [true, ""];
    }
    return [false, res.rows[0]["u"]];
}

async function changePassword(firstname, lastname, newPassword) {
    var client = await pool.connect();
    var user_id = await getUserId(client, firstname, lastname);

    if (user_id === -1) {
        client.release();
        return false;
    }

    var has_password = await hasPassword(client, user_id, newPassword);
    var lastPassword = has_password[1];
    has_password = has_password[0];
    if (has_password) {
        client.release();
        return false;
    }

    await client.query("INSERT INTO mots_de_passe_utilisateurs (user_id, date_created, password) VALUES ($1, now(), $2)", [user_id, lastPassword]);
    await client.query("UPDATE utilisateurs SET password = $2 WHERE user_id = $1", [user_id, newPassword]);

    client.release();
    return true;
}

changePassword("Joseph", "Azar", "abcdef").then((res) => {
    console.log(res);
});
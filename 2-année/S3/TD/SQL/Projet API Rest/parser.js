const fs = require('fs');
const path = require('path');
const pool = require("./database/connection");
const filePath = path.join(__dirname, "./data/prize.json");

async function insertPrizes(){
    const client = await pool.connect();
    let data = fs.readFileSync(filePath, 'utf-8');
    data = JSON.parse(data);

    try {
        for (let prize of data.prizes) {
            const prizeTest = await client.query("INSERT INTO prizes(id, year, category) VALUES ($1, $2, $3)", [prize.id, prize.year, prize.category]);
            console.log(prizeTest);
        }
    } catch (error) {
        console.log(error);
    }
    client.release();
}

insertPrizes();
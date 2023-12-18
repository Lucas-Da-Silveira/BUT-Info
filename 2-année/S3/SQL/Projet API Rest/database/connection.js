const {Pool} = require('pg');
require('dotenv').config();

const credentials = {
    user: process.env.DB_USERNAME,
    host: process.env.DB_HOSTNAME,
    database: process.env.DB_DATABASE,
    port: process.env.DB_PORT,
    password: process.env.DB_PASSWORD
}

const pool = new Pool(credentials);

const query = async (sql, params) => {
    return await pool.query(sql, params);
};

module.exports = {
    pool,
    query
};
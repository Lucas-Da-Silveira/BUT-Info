const Sequelize = require('sequelize')

const sequelize = new Sequelize('postgres', 'ldasilve','1603',{
    dialect: 'postgres',
    port: 5432,
    host: 'localhost'
});

module.exports = sequelize
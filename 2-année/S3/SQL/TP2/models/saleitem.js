const sequelize = require("../database/db");
const {DataTypes} = require('sequelize');
const SaleItem = sequelize.define('saleitem', {
    id: {
        field: 'id',
        type: DataTypes.INTEGER,
        allowNull: false,
        unique: true,
        primaryKey: true,
        autoIncrement: true
    },
    quantity: {
        field: 'quantity',
        type: DataTypes.INTEGER,
        allowNull: false
    },
    price: {
        field: 'price',
        type: DataTypes.FLOAT,
        allowNull: false
    }
},
    {
        freezeTableName: true,
        timestamps: false
    });

module.exports = SaleItem;
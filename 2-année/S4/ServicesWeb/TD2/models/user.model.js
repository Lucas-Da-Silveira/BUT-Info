const {DataTypes} = require('sequelize');
module.exports = function (sequelize) {
    const User = sequelize.define('user', {
        id: {
            autoIncrement: true, primaryKey: true, type: DataTypes.INTEGER, allowNull: false
        },
        firstName: {type: DataTypes.STRING, notEmpty: true},
        lastName: {type: DataTypes.STRING, notEmpty: true},
        emailId: {type: DataTypes.STRING, validate: {isEmail: true}},
        password: {type: DataTypes.STRING, allowNull: false},
    }, {
        tableName: 'users'
    });
    return User;
}
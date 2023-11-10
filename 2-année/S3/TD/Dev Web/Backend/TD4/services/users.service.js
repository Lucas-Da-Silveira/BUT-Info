const fs = require('fs');
const path = require('path');
const {v4:uuid4} = require('uuid');
const filePath = path.join(__dirname, "..", "users.json")
const createUser = (prenom,nom,callBack) => {
    let users = [];
    try{
        const data = fs.readFileSync(filePath, 'utf-8');
        const dataStr = data.toString();
        users = JSON.parse(dataStr);
    }catch (error) {
        console.log(error);
    }
    const newUser = {
        id:uuid4(),
        nom : nom,
        prenom : prenom
        };
    users.push(newUser);

    try {
        fs.writeFileSync(filePath, JSON.stringify(users));
        callBack(null,"success");
    }
    catch (errorEcriture){
        callBack(errorEcriture, null);
    }
}

const getAllUsers = (callback) => {
    let users = [];
    try {
        const data = fs.readFileSync(filePath, 'utf-8');
        const dataStr = data.toString();
        users = JSON.parse(dataStr);
        callback(null, users);
    } catch (error) {
        console.log(error);
        callback(error, null);
    }
};

const getUserById = (uuid, callback) => {
    let users = [];
    try {
        const data = fs.readFileSync(filePath, 'utf-8');
        const dataStr = data.toString();
        users = JSON.parse(dataStr);
        const user = users.find(u => u.id === uuid)
        callback(null, user);
    } catch (error) {
        console.log(error);
        callback(error, null);
    }
}

const getTopUsers = (callback) => {
    let users = [];
    try {
        const data = fs.readFileSync(filePath, 'utf-8');
        const dataStr = data.toString();
        users = JSON.parse(dataStr);
        let user = null;
        let TopUsers = 0;
        users.forEach(u => {
            if(u.prenom.length > TopUsers){
                TopUsers = u.prenom.length;
                user = u;
            }
        })
        callback(null, user);
    } catch (error) {
        console.log(error);
        callback(error, null);
    }
}

const updateUser = (uuid, nom, prenom, callback) => {
    let users = [];
    try {
        const data = fs.readFileSync(filePath, 'utf-8');
        const dataStr = data.toString();
        users = JSON.parse(dataStr);
        const userIndex = users.findIndex(u => u.id === uuid);
        if(!userIndex < 0){
            return callback("User not found", null);
        }
        users[userIndex] = {id:uuid, nom:nom, prenom:prenom};
        try {
            fs.writeFileSync(filePath, JSON.stringify(users));
            callback(null, "Update success");
        } catch (writeError){
            console.log(writeError);
            callback(writeError, null);
        }
    }catch (error) {
        console.log(error);
        callback(error, null);
    }
}

const deleteUser = (uuid, callback) => {
    let users = [];
    try {
        const data = fs.readFileSync(filePath, 'utf-8');
        const dataStr = data.toString();
        users = JSON.parse(dataStr);
        const initLength = users.length;
        users = users.filter(user => user.id !== uuid);
        if(users.length === initLength) {
            return callback("User not found", null);
        }
        try{
            fs.writeFileSync(filePath, JSON.stringify(users));
            callback(null, "Delete success");
        }catch (ReadError){
            console.log(ReadError);
            callback("Internal error", null);
        }
    }catch (WriteError) {
        console.log(error);
        callback("internal error", null);
    }
}

const deleteAllUser = (callback) => {
    let users = [];
    try {
        const data = fs.readFileSync(filePath, 'utf-8');
        const dataStr = data.toString();
        users = JSON.parse(dataStr);
        if(users.length === 0) {
            return callback("User not found", null);
        }
        try{
            fs.writeFileSync(filePath, JSON.stringify([]));
            callback(null, "Delete success");
        }catch (ReadError){
            console.log(ReadError);
            callback("Internal error", null);
        }
    }catch (WriteError) {
        console.log(error);
        callback("internal error", null);
    }
}

module.exports = {
    createUser:createUser,
    getAllUsers: getAllUsers,
    getUserById: getUserById,
    getTopUsers: getTopUsers,
    updateUser: updateUser,
    deleteUser: deleteUser,
    deleteAllUser: deleteAllUser

}
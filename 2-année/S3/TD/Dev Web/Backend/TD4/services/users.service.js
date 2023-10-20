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

module.exports = {
    createUser:createUser
}
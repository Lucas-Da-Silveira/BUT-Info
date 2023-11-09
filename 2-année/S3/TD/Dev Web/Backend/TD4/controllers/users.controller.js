const userService = require('../services/users.service');

exports.saveUser = async (req, res) =>{
    const nom = req.body.nom;
    const prenom = req.body.prenom;
    console.log(nom, " ", prenom);
    userService.createUser(prenom, nom, (error, data) => {
        if(error){
            return res.status(500).send("internal errors");
        }
        return res.status(200).send("User created succesfully");
    });
}//Postman

exports.getUsers = (req, res) => {
    userService.getAllUsers((error,data)=>{
        if(error){
            return res.status(500).send("Internal error");
        }
        return res.status(200).json(data);
    });
}

exports.getUsersById = (req, res) => {
    const uuid = req.params.uuid;
    userService.getUserById(uuid, (error, data) => {
        if(error){
            return res.status(500).send("Internal error");
        }
        if(!data){
            return res.status(404).send("User not found");
        }
        return res.status(200).json(data);
    });
}

exports.getTopUsers = (req, res) => {
    userService.getTopUsers((error, data) => {
        if(error){
            return res.status(500).send("Internal error");
        }
        return res.status(200).json(data);
    });
}

exports.updateUser = (req, res) => {
    const uuid = req.params.uuid;
    const {nom,prenom} = req.body;
    userService.updateUser(uuid, nom, prenom,(error,data)=> {
        if(error){
            if(error === "User not found"){
                return res.status(404).send(error);
            }
            return res.status(500).send("Internal error");
        }
        return res.status(200).send(data);
    });
}

exports.deleteUser = (req, res) => {
    const uuid = req.query.uuid;
    if(!uuid){
        return res.status(400).send("uuid is required");
    }
    userService.deleteUser(uuid, (error, data) => {

    });
}
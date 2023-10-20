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
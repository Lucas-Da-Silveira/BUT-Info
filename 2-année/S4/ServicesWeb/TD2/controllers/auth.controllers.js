const db = require("../models");
const config = require("../config/auth.config");
const User = db.user;
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");
exports.signup = (req, res) => {
    //Enregistrer l'utilisateur dans la base de données
    User.create({
        firstName: req.body.firstName,
        lastName: req.body.lastName,
        emailId: req.body.emailId,
        password: bcrypt.hashSync(req.body.password, 8)
    })
        .then(user => {
            res.send({message: "L'utilisateur a été enregistré avec succès!"});
        })
        .catch(err => {
            res.status(500).send({message: err.message});
        });
};
exports.signin = (req, res) => {
    console.log(req.body);
    User.findOne({
        where: {
            emailId: req.body.emailId
        }
    })
        .then(user => {
            if (!user) {
                return res.status(404).send({message: "Utilisateur non trouvé."});
            }
            let passwordIsValid = bcrypt.compareSync(
                req.body.password,
                user.password
            );
            if (!passwordIsValid) {
                return res.status(401).send({
                    accessToken: null,
                    message: "Mot de passe incorrect!"
                });
            }
            let token = jwt.sign({id: user.id}, config.secret, {
                expiresIn: 86400  //24 heures
            });
            res.status(200).send({
                id: user.id,
                username: user.emailId,
                accessToken: token
            });
        })
        .catch(err => {
                res.status(500).send({message: err.message});
            }
        )
    ;
};
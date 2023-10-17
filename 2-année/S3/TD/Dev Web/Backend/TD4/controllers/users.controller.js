exports.saveUser = async (req, res) =>{
    const nom = req.body.nom;
    const prenom = req.body.prenom;
    console.log(nom, " ", prenom);
}//Postman
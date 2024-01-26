exports.allAccess = (req, res) => {
    res.status(200).send("Contenu public.");
};
exports.userAccess = (req, res) => {
    res.status(200).send("Contenu utilisateur.");
};
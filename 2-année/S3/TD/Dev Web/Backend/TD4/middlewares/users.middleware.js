const validator = require('validator');
exports.validateUserInput = (req, res, next) => {
    const {nom, prenom} = req.body;
    if (!nom || !prenom) {
        return res.status(400).send("Nom et prenom sont nulles");
    }
    if (!validator.isLength(nom, {min: 3}) || !validator.isAlpha(nom, 'en-US', {ignore: ' '})) {
        return res.status(400).send("Surname format incorrect");
    }
    if (!validator.isLength(prenom, {min: 3}) || !validator.isAlpha(prenom, 'en-US', {ignore: ' '})) {
        return res.status(400).send("Name format incorrect");
    }
    next();
}
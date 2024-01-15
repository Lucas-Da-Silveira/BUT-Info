const validateUser = (req, res, next) => {
  const { fullname, username, password } = req.body;

  if (fullname.length < 6 || username.length < 6 || password.length < 6) {
    return res.status(400).json({ error: 'Le fullname, le username et le password doivent contenir au moins 6 caractères.' });
  }

  const existingUser = users.find(user => user.username === username);
  if (existingUser) {
    return res.status(400).json({ error: 'Le username est déjà utilisé. Veuillez en choisir un autre.' });
  }

  next();
};
const checkUserLoggedIn = (req, res, next) => {
  if (req.session && req.session.user) {
    return res.status(400).json({ error: 'L\'utilisateur est déjà connecté.' });
  }
  next();
};

const isAuthenticated = (req, res, next) => {
  if (req.session && req.session.user) {
    return next();
  } else {
    return res.status(401).json({ error: 'Non autorisé. Veuillez vous connecter.' });
  }
};




module.exports = {
    validateUser,
    checkUserLoggedIn,
    isAuthenticated,
};

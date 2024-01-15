const userService = require('../services/users.service');

const getUsers = (req, res) => {
  const users = userService.getUsers();
  res.json(users);
};

const registerUser = (req, res) => {
  const { fullname, username, password } = req.body;
  const newUser = userService.registerUser(fullname, username, password);
  res.status(201).json(newUser);
};

const loginUser = (req, res) => {
  const { username, password } = req.body;
  const user = userService.loginUser(username, password);

  if (!user) {
    return res.status(401).json({ error: 'Identifiants incorrects.' });
  }

  req.session.user = user;

  res.json({ message: 'Connexion réussie.' });
};

const login = (req, res) => {
  const { username, password } = req.body;
  const user = users.find(user => user.username === username && user.password === password);

  if (!user) {
    return res.status(401).json({ error: 'Nom d\'utilisateur ou mot de passe incorrect.' });
  }

  // Crée une session lors de la connexion
  req.session.user = { username, fullname: user.fullname };
  res.status(200).json({ message: 'Connexion réussie.' });
};

const logoutUser = (req, res) => {
  req.session.destroy((err) => {
    if (err) {
      return res.status(500).json({ error: 'Erreur lors de la déconnexion.' });
    }
    res.status(200).json({ message: 'Déconnexion réussie.' });
  });
};

const getUserInfo = (req, res) => {
  const userInfo = req.session.user;
  res.json(userInfo);
};

module.exports = {
  getUsers,
  registerUser,
  loginUser,
  login,
  logoutUser,
    getUserInfo,
};

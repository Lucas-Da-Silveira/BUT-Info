const fs = require('fs');
const path = require('path');

const usersFilePath = path.join(__dirname, '../users.json');

const getUsers = () => {
  const usersData = fs.readFileSync(usersFilePath, 'utf-8');
  const users = JSON.parse(usersData);
  return users;
};

const saveUsers = (users) => {
  fs.writeFileSync(usersFilePath, JSON.stringify(users, null, 2), 'utf-8');
};

const registerUser = (fullname, username, password) => {
  const users = getUsers();
  const newUser = { fullname, username, password };
  users.push(newUser);
  saveUsers(users);
  return newUser;
};

const loginUser = (username, password) => {
  const users = getUsers();
  const user = users.find((u) => u.username === username && u.password === password);
  return user;
};

module.exports = {
  getUsers,
  registerUser,
  loginUser,
};

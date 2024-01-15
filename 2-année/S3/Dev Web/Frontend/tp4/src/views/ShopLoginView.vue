<template>
  <div class="login-container">
    <h1>Login</h1>

    <div class="input-group">
      <span>login</span>
      <input v-model="login">
    </div>

    <div class="input-group">
      <span>password</span>
      <input type="password" v-model="password">
    </div>

    <button @click="shopLogin({ login, password })" class="login-button">Login</button>

    <p v-if="shopUser" class="login-message">{{ shopUser }}</p>
  </div>
</template>

<script>

import {mapState, mapActions} from 'vuex'

export default {
  name: 'ShopLoginView',
  data: () => ({
    login: '',
    password: '',
  }),
  computed: {
    ...mapState('shop', ['shopUser'])
  },
  methods: {
    ...mapActions('shop', ['shopLogin'])
  }
}
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f8f8f8;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.input-group {
  margin-bottom: 15px;
}

.input-group span {
  display: block;
  margin-bottom: 5px;
}

.login-button {
  background-color: #3498db;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.login-button:hover {
  background-color: #2078b4; /* Change this to the desired hover color */
}

.login-message {
  margin-top: 20px;
  color: #27ae60;
}
</style>

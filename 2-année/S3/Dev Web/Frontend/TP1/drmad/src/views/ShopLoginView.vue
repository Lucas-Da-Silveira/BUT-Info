<template>
  <div>
    <h1>Login</h1>

    <span>login</span><input v-model="login">
    <span>password</span><input v-model="password">
    <button @click="shopLogin({login, password})">Login</button>
    <p v-if="shopUser">{{shopUser}}</p>
  </div>

</template>

<script>

import {mapState, mapActions} from 'vuex'
export default {
  name: 'ShopLoginView',
  data: () => ({
    login: '',
    password:'',
  }),
  computed: {
    ...mapState(['shopUser'])
  },
  methods: {
    ...mapActions(['shopLogin']),
  }
}
</script>

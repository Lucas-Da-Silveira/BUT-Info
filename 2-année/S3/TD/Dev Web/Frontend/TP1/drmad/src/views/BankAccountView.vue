<template>
  <div>
    <h1>Login</h1>

    <span>login</span><input v-model="login">
    <button @click="gatAccountAmount({login})">Login</button>
    <p v-if="accountAmont">{{accountAmont}}</p>
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
    ...mapState(['accountAmount'])
  },
  methods: {
    ...mapActions(['getAccountAmount']),
  }
}
</script>

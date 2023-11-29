<template>
  <div id="app">
    <NavBar @menu-clicked="handleMenuClicked" :buttons="buttonsList" />
    <img alt="Vue logo" src="./assets/logo.png">
    <h1>Welcome to DrMad app</h1>
    <router-view />
  </div>

</template>

<script>

import {mapActions} from 'vuex'
import NavBar from "@/components/NavBar.vue";

export default {
  name: 'App',
  components: {NavBar},

  data: () => ({
      buttonsList: [
        { text: 'Viruses', color: 'blue' },
        { text: 'Compte bancaire', color: 'green' },
        { text: 'Login', color: 'orange' }
      ]
  }),
  methods: {
    ...mapActions(['getAllViruses']),
    handleMenuClicked(index) {
      switch (index) {
        case 0:
          this.$router.push('/shop/items');
          break;
        case 1:
          this.$router.push('/bank/account');
          break;
        case 2:
          this.$router.push('/shop/login');
          break;
        default:
          break;
      }
    }
  },

  mounted() {
    this.getAllViruses()
  }
};
</script>

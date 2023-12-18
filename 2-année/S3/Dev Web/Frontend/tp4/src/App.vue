<template>
  <div id="app">
    <NavBar :titles="titles" @menu-clicked="goTo($event)"></NavBar>

    <h1>Welcome to DrMad app</h1>

    <router-view/>
  </div>
</template>

<script>

import NavBar from "@/components/NavBar";

export default {
  name: 'App',
  components: {NavBar},
  data: () => ({
    titles: [ {text:'Virus', color: 'blue'},
      {text:'Compte bancaire', color: 'red'},
      {text:'Login', color: 'green'},
    ],
    currentIndex: -1
  }),
  methods: {
    goTo(index) {
      if (index == 0) {
        this.$router.push('/shop/items')
      }
      else if (index == 1) {
        this.$router.push('/bank/account')
      }
      else if (index == 2) {
        this.$router.push('/shop/login')
      }
    }
  },
};
</script>

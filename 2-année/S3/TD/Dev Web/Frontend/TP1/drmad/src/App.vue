<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <h1>Welcome to DrMad app</h1>

    <router-view></router-view>
  </div>

</template>

<script>

import {mapActions} from 'vuex'

export default {
  name: 'App',

  data: () => ({
    //
  }),
  methods: {
    ...mapActions(['getAllViruses'])
  },
  mounted() {
    this.getAllViruses()
  }
};
</script>

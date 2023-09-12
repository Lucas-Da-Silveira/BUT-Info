<template>
  <div>
    <h1>Les virus</h1>
    <p>Le tableau dans le store : {{ viruses }}</p>
    <p>sous forme de liste avec certains champs</p>
    <ul>
      <li v-for="(virus, index) in viruses" :key="index">{{virus.name}} : {{virus.price}}</li>
    </ul>

  </div>
</template>

<script>

import {mapState} from 'vuex'
export default {
  name: 'VirusesView',
  data: () => ({

  }),
  computed: {
    ...mapState(['viruses'])
  }

}
</script>

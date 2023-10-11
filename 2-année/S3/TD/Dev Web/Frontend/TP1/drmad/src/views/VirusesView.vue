<template>
  <div>
    <h1>Les virus</h1>
    <p>Le tableau dans le store : {{ viruses }}</p>
    <p>sous forme de liste avec certains champs</p>
    <label for="filterprice">prix inférieur à : </label><input v-model="priceFilter" @input="convertToNumber" id="filterprice" >
    <ul>
      <li v-for="(virus, index) in filterVirusesByPrice" :key="index">{{virus.name}} : {{virus.price}}</li>
    </ul>

    <label>Filtrer par nom : </label><input v-model="nameFilter" placeholder="Nom virus" />
    <ul>
      <li v-for="virus in filterVirusesByName" :key="virus.id">
        {{ virus.name }}
      </li>
    </ul>

  </div>
</template>

<script>

import {mapState} from 'vuex'

export default {
  name: 'VirusesView',
  data: () => ({
    priceFilter: 0,
    nameFilter: '',
  }),
  computed: {
    ...mapState(['viruses']),
    filterVirusesByPrice() {
      if (this.priceFilter !== null && this.priceFilter > 0) {
        return this.viruses.filter(v => v.price < this.priceFilter);
      } else {
        return this.viruses;
      }
    },

    filterVirusesByName() {
      return this.viruses.filter(virus => {
        return virus.name.toLowerCase().includes(this.nameFilter.toLowerCase());
      });
    },
  },

  methods: {
    convertToNumber() {
      const numericValue = parseFloat(this.priceFilter);

      if (!isNaN(numericValue)) {
        this.priceFilter = numericValue;
      } else {
        this.priceFilter = null;
      }
    },
  }
}
</script>

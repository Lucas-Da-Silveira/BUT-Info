<template>
  <div>
    <h1>Les virus</h1>
<!--    <p>Le tableau dans le store : {{ viruses }}</p>-->
<!--    <p>sous forme de liste avec certains champs</p>-->

    <span>Filtres :</span><label for="filterpriceactive">par prix</label><input type="checkbox" v-model="filterPriceActive" id="filterpriceactive">
    <hr />
    <div v-if="filterPriceActive">
      <label for="filterprice">prix inférieur à : </label><input v-model="priceFilter" id="filterprice">
    <ul>
      <li v-for="(virus, index) in filterVirusesByPrice" :key="index">{{virus.name}} : {{virus.price}}</li>
    </ul>
      </div>

    <span>Filtres :</span><label for="filternameactive">par nom</label><input type="checkbox" v-model="filterNameActive" id="filternameactive">
    <hr />
    <div v-if="filterNameActive">
      <label for="filtername">nom contient : </label><input v-model="nameFilter" id="filtername">
    <ul>
      <li v-for="virus in filterVirusesByName" :key="virus.id">
        {{ virus.name }}
      </li>
    </ul>
    </div>

    <span>Filtres :</span><label for="filterstockactive">par stock</label><input type="checkbox" v-model="filterStockActive" id="filterstockactive">
    <hr />
    <div v-if="filterStockActive">
      <label for="filterstock">stock supérieur à 0 : </label><input v-model="stockFilter" id="filterstock" type="checkbox">
    <table v-if="filterVirusesByStock.length > 0">
      <thead>
      <tr>
        <th>Nom</th>
        <th>Prix</th>
        <th>Stock</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(virus, index) in filterVirusesByStock" :key="index">
        <td>{{ virus.name }}</td>
        <td>{{ virus.price }}</td>
        <td>{{ virus.stock }}</td>
      </tr>
      </tbody>
    </table>
  </div>
  </div>

</template>

<script>

import {mapState} from 'vuex'

export default {
  name: 'VirusesView',
  data: () => ({
    priceFilter: 0,
    nameFilter: '',
    stockFilter: false,
    filterPriceActive: false,
    filterNameActive: false,
    filterStockActive: false,
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
    filterVirusesByStock() {
      let filteredViruses = this.stockFilter
          ? this.viruses.filter(virus => virus.stock > 0)
          : this.viruses;

      filteredViruses.sort((a, b) => b.stock - a.stock);

      return filteredViruses;
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
  },
};
</script>

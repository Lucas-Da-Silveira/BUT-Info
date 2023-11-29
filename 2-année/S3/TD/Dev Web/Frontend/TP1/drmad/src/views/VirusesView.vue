<template>
  <div>
    <h1>Les virus</h1>
    <span>Filtres :</span>
    <hr />
    <label for="filterpriceactive">par prix</label><input type="checkbox" v-model="filterPriceActive" id="filterpriceactive">
    <label for="filternameactive">par nom</label><input type="checkbox" v-model="filterNameActive" id="filternameactive">
    <label for="filterstockactive">par stock</label><input type="checkbox" v-model="filterStockActive" id="filterstockactive">
    <hr />
    <table>
      <tr>
        <td v-if="filterPriceActive">
          <label for="filterprice">prix inférieur à : </label><input v-model="priceFilter" id="filterprice">
        </td>
        <td v-if="filterNameActive">
          <label for="filtername">nom contient : </label><input v-model="nameFilter" id="filtername">
        </td>
        <td v-if="filterStockActive">
          <label for="filterstock">en stock</label><input type="checkbox" v-model="stockFilter" id="filterstock">
        </td>
      </tr>
    </table>
    <hr/>

    <CheckedList :data="this.viruses" :fields="['nom', 'prix']" :itemCheck="false" :checked="[]" :itemButton="{}" :listButton="{}" />

    <table>
      <tr>
        <th>Nom</th><th>Prix</th>
      </tr>
      <tr v-for="(virus, index) in filterViruses" :key="index">
        <td>{{virus.name}}</td>
        <td>{{virus.price}}</td>
      </tr>
    </table>
  </div>


</template>

<script>

import {mapState} from 'vuex'
export default {
  name: 'VirusesView',
  components: {
    CheckedList: () => import('@/components/CheckedList.vue')
  },
  data: () => ({
    priceFilter: 0,
    nameFilter: '',
    stockFilter: true,
    filterPriceActive : false,
    filterNameActive : false,
    filterStockActive : false,
  }),
  computed: {
    ...mapState(['viruses']),
    filterVirusesByPrice() {
      // no active filter => get whole list
      if (!this.filterPriceActive) return this.viruses
      let price = parseInt(this.priceFilter)
      if (isNaN(price)) return []
      if (price > 0) return this.viruses.filter(v => v.price < price)
      return this.viruses
    },
    filterVirusesByName() {
      // no active filter => get whole list
      if (!this.filterNameActive) return this.viruses
      if (this.nameFilter) return this.viruses.filter(v => v.name.includes(this.nameFilter))
      return this.viruses
    },
    filterVirusesByStock() {
      // no active filter => get whole list
      if (!this.filterStockActive) return this.viruses
      if (this.stockFilter) return this.viruses.filter(v => v.stock > 0)
      return this.viruses
    },
    filterViruses() {
      let list = this.viruses
      if (this.filterPriceActive) {
        let price = parseInt(this.priceFilter)
        if ((!isNaN(price)) && (price > 0)) {
          list = list.filter(v => v.price < price)
        }
      }
      if (this.filterNameActive) {
        if (this.nameFilter) list = list.filter(v => v.name.includes(this.nameFilter))
      }
      if (this.filterStockActive) {
        if (this.stockFilter) list = list.filter(v => v.stock > 0)
      }
      return list
    }
  }
}
</script>

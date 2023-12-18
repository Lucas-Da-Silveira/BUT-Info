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
    <hr />

    <!-- version avec liste séparée : décommenter pour tester

    <p>Liste filtrée par prix</p>
    <ul>
      <li v-for="(virus, index) in filterVirusesByPrice" :key="index">{{virus.name}} : {{virus.price}}</li>
    </ul>
    <hr />
    <p>Liste filtrée par nom</p>
    <ul>
      <li v-for="(virus, index) in filterVirusesByName" :key="index">{{virus.name}} : {{virus.price}}</li>
    </ul>
    <hr />
    <p>Liste filtrée par stock</p>
    <table>
      <tr>
        <th>Nom</th><th>Prix</th>
      </tr>
      <tr v-for="(virus, index) in filterVirusesByStock" :key="index">
        <td>{{virus.name}}</td>
        <td>{{virus.price}}</td>
      </tr>
    </table>

    -->

    <!-- version avec filtre multi-critères -->
    <CheckedList :data="filterViruses"
                 :fields="['name', 'price']"
                 item-check
                 :item-button="{show: true, text:'Info'}"
                 :list-button="{show: true, text:'Select'}"
                 :checked="checked"
                 @checked-changed="changeSelection($event)"
                 @item-button-clicked="showVirusInfos($event)"
                 @list-button-clicked="showVirusNames()"
    >

    </CheckedList>
  </div>
</template>

<script>

import {mapState, mapActions} from 'vuex'
import CheckedList from "@/components/CheckedList";
export default {
  name: 'VirusesView',
  components: {CheckedList},
  data: () => ({
    priceFilter: 0,
    nameFilter: '',
    stockFilter: true,
    filterPriceActive : false,
    filterNameActive : false,
    filterStockActive : false,
    selected: []
  }),
  computed: {
    ...mapState(['viruses']),
    checked() {
      let tab = []
      this.filterViruses.forEach(v => {
        // find the index of virus v in this.viruses
        let idx = this.viruses.findIndex(el => el == v)
        // if idx is in selected, push true, else push false
        if (this.selected.includes(idx)) {
          tab.push(true)
        }
        else {
          tab.push(false)
        }
      })
      return tab
    },
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
  },
  methods: {
    ...mapActions(['getAllViruses']),
    changeSelection(idx) {
      // get the virus in the filtered list
      let v = this.filterViruses[idx]
      // search its index in this.viruses
      let i = this.viruses.findIndex(el => el == v)
      // if i is in selected, remove it
      let j = this.selected.findIndex(el => el === i)
      if (j !== -1) {
        this.selected.splice(j,1)
      }
      else {
        this.selected.push(i)
      }
    },
    showVirusInfos(idx) {
      let v = this.filterViruses[idx]
      let msg = v.name+ ", stock = "+v.stock+", for sell = "+v.sold
      alert(msg)
    },
    showVirusNames() {
      let msg = ""
      this.selected.forEach(idx => {
        msg += this.viruses[idx].name+" "
      })
      alert(msg)
    }
  },
  mounted() {
    this.getAllViruses()
  }
}
</script>

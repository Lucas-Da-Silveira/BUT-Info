<template>
  <div class="items-list-container">
    <h1>Les virus</h1>
    <div class="filters-container">
      <span>Filtres :</span>
      <hr />
      <div class="filter-checkboxes">
        <label for="filterpriceactive">par prix</label>
        <input type="checkbox" v-model="filterPriceActive" id="filterpriceactive">

        <label for="filternameactive">par nom</label>
        <input type="checkbox" v-model="filterNameActive" id="filternameactive">

        <label for="filterstockactive">par stock</label>
        <input type="checkbox" v-model="filterStockActive" id="filterstockactive">
      </div>
      <hr />

      <table>
        <tr>
          <td v-if="filterPriceActive">
            <label for="filterprice">prix inférieur à : </label>
            <input v-model="priceFilter" id="filterprice">
          </td>
          <td v-if="filterNameActive">
            <label for="filtername">nom contient : </label>
            <input v-model="nameFilter" id="filtername">
          </td>
          <td v-if="filterStockActive">
            <label for="filterstock">en stock</label>
            <input type="checkbox" v-model="stockFilter" id="filterstock">
          </td>
        </tr>
      </table>
      <hr />

      <CheckedList
        :data="filterViruses"
        :fields="['name', 'price', 'promotions']"
        item-check
        :item-button="{ show: true, text: 'Ajouter au panier' }"
        :list-button="{ show: true, text: 'Ajouter au panier (Tous)' }"
        :checked="checked"
        :item-amount="true"
        @checked-changed="changeSelection($event)"
        @item-button-clicked="addToCart($event)"
        @list-button-clicked="addToCartAll()"
      />
    </div>
  </div>
</template>

<script>
import {mapActions, mapState} from 'vuex';
import CheckedList from "@/components/CheckedList";

export default {
  name: 'ItemsList',
  components: { CheckedList },
  computed: {
    ...mapState('shop', ['viruses', 'shopUser']),
    checked() {
      let tab = [];
      this.filterViruses.forEach(v => {
        // Trouver l'index du virus v dans this.viruses
        let idx = this.viruses.findIndex(el => el === v);
        // Si l'index est dans selected, ajouter true, sinon ajouter false
        if (this.selected.includes(idx)) {
          tab.push(true);
        } else {
          tab.push(false);
        }
      });
      return tab;
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
  data() {
    return {
      priceFilter: 0,
      nameFilter: '',
      stockFilter: true,
      filterPriceActive : false,
      filterNameActive : false,
      filterStockActive : false,
      selected: [],
       itemAmountValues: [],
    };
  },
  methods: {
    ...mapActions('shop', ['getAllViruses', "addToBasket"]),
    changeSelection(idx) {
      let v = this.filterViruses[idx];
      let i = this.viruses.findIndex(el => el === v);
      let j = this.selected.findIndex(el => el === i);
      if (j !== -1) {
        this.selected.splice(j, 1);
      } else {
        this.selected.push(i);
      }
    },
    addToCart(id , amount)  {
      let vir = this.viruses[id];
      this.addToBasket({ item: this.viruses[id], amount });
      this.$store.dispatch('shop/addToBasket', { item: vir, amount });
    },
    addToCartAll() {
      this.selected.forEach(idx => {
        let vir = this.viruses[idx];
        let amount = this.itemAmountValues[idx];
        this.$store.dispatch('shop/addToBasket', { item: vir, amount });
      });
      this.selected = [];
    },
  },
  mounted() {
    this.getAllViruses();
    console.log(this.viruses);
  }
};
</script>

<style scoped>
.items-list-container {
  max-width: 800px;
  padding: 20px;
  margin-top: 50px;
  background-color: #f8f8f8;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.filters-container {
  text-align: center;
}

.filter-checkboxes {
  display: flex;
  justify-content: space-around;
  margin-bottom: 10px;
}
</style>
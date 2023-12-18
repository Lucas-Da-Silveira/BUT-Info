// index.js
import Vue from 'vue';
import Vuex from 'vuex';
import shopModule from './shop';
import bankModule from './bank';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    shop: shopModule,
    bank: bankModule,
  },
});

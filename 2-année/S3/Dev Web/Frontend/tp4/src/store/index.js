import Vue from 'vue';
import Vuex from 'vuex';
import shopStore from './shop';
import bankStore from './bank';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        shop: shopStore,
        bank: bankStore,
    },
});
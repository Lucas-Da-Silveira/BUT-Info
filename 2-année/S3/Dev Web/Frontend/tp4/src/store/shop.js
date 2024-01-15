import Vue from 'vue';
import Vuex from 'vuex';
import ShopService from '../services/shop.service';

Vue.use(Vuex);

const shopStore = {
    namespaced: true,
    state: () => ({
        viruses: [],
        shopUser: null,
    }),
    mutations: {
        updateViruses(state, viruses) {
            state.viruses = viruses;
        },
        updateShopUser(state, user) {
            state.shopUser = user;
        },
        updateBasket(state, newBasket) {
            // Mutation pour mettre à jour le panier dans le state
            state.shopUser.basket = newBasket;
        },
    },
    actions: {
        async shopLogin({ commit }, data) {
            console.log('login');
            let response = await ShopService.shopLogin(data);
            if (response.error === 0) {
                commit('updateShopUser', response.data);
            } else {
                console.log(response.data);
            }
        },
        async getAllViruses({ commit }) {
            console.log('récupération des viruses');
            let response = await ShopService.getAllViruses();
            if (response.error === 0) {
                commit('updateViruses', response.data);
            } else {
                console.log(response.data);
            }
        },
        async addToBasket({ commit, state }, { item, amount }) {
            // Action pour ajouter un élément au panier
            const updatedBasket = [...state.shopUser.basket.items];
            const existingItemIndex = updatedBasket.findIndex((basketItem) => basketItem.item === item._id);

            if (existingItemIndex !== -1) {
                // L'article existe déjà dans le panier, mettez à jour la quantité
                updatedBasket[existingItemIndex].amount += amount;
            } else {
                // Ajoutez un nouvel élément au panier
                updatedBasket.push({ item: item._id, amount });
            }

            // Mise à jour du panier dans le state
            commit('updateBasket', { items: updatedBasket });

            // Appel à un service (simulé ici) pour mettre à jour la BdD
            ShopService.updateBasketInDatabase(state.shopUser._id, { items: updatedBasket });
        },
        async updateBasketInDatabase(_, { userId, updatedBasket }) {
            // Service (simulé) pour mettre à jour le panier dans la BdD
            console.log('Updating basket in the database for user:', userId);
            console.log('New basket:', updatedBasket);
            // Ajoutez ici le code pour mettre à jour la BdD
        },
    },
};

export default shopStore;
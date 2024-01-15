import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import BankAccountService from '../services/bankaccount.service'

const bankStore = {
    namespaced: true,
    // state = les données centralisées
    state: () => ({
        accountAmount: 0,
        accountTransactions: [],
        accountNumberError: 0,
    }),
    // mutations = fonctions synchrones pour mettre à jour le state (!!! interdit de modifier directement le state)
    mutations: {
        updateAccountAmount(state, amount) {
            state.accountAmount = amount
        },
        updateAccountTransactions(state, transactions) {
            state.accountTransactions = transactions
        },
        updateAccountNumberError(state, error) {
            state.accountNumberError = error
        }
    },
    // actions = fonctions asynchrone pour mettre à jour le state, en faisant appel aux mutations, via la fonction commit()
    actions: {
        async getAccountAmount({commit}, number) {
            console.log('get account amount');
            let response = await BankAccountService.getAccountAmount(number)
            if (response.error === 0) {
                commit('updateAccountAmount', response.data)
                commit('updateAccountNumberError', 1)
            }
            else {
                console.log(response.data)
                commit('updateAccountNumberError', -1)
            }
        },
        async getAccountTransactions({commit}, number) {
            console.log('get account transactions');
            let response = await BankAccountService.getAccountTransactions(number)
            if (response.error === 0) {
                commit('updateAccountTransactions', response.data)
                commit('updateAccountNumberError', 1)
            }
            else {
                console.log(response.data)
                commit('updateAccountNumberError', -1)
            }
        },
    }
}

export default bankStore;
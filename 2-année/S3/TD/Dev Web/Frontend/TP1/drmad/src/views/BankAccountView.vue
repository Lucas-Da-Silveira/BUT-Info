<template>
  <div>
    <h1>Account amount</h1>

    <span>account number</span><input v-model="number">
    <button @click="getAccountAmount(number)">Get amount</button><button @click="getAccountTransactions(number)">Get transactions</button>
    <p>available amount: {{accountAmount}}</p>
    <hr />
    <p>passed transactions:
      <ul>
        <li v-for="trans in accountTransactions" :key="trans.id">
          {{trans.amount}}  {{formatDate(new Date(trans.date.$date))}}
        </li>

    </ul>
    </p>

  </div>

</template>

<script>

import {mapActions, mapState} from 'vuex'

export default {
  name: 'BankAccountView',
  data: () => ({
    number: '',
  }),
  computed: {
    ...mapState(['accountAmount', 'accountTransactions'])
  },
  methods: {
    ...mapActions(['getAccountAmount','getAccountTransactions']),

    formatDate(date) {
        const options = {year: 'numeric', month: '2-digit', day: '2-digit'};
        const dateParts = new Date(date).toLocaleDateString('fr-US', options).split('/');
        const timeParts = new Date(date).toLocaleTimeString('fr-US').split(':');

        return `${dateParts[1]}/${dateParts[0]}/${dateParts[2]} at ${timeParts.join(':')}`;
    },
  }
}
</script>

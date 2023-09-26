<template>
  <div>
    <h1>Bank Account</h1>

    <span>Account Amount</span><input v-model="inputAccount">
    <button @click="getAccountAmount({inputAccount})">Check account</button>
  <p v-if="accountAmount">{{accountAmount}}</p>
  </div>

</template>

<script>

import {mapState, mapActions} from 'vuex'
export default {
  name: 'BankAccountView',
  data: () => ({
    inputAccount: '',
  }),
  computed: {
    ...mapState(['accountAmount'])
  },
  methods: {
    ...mapActions(['getAccountAmount']),
  }
}
</script>

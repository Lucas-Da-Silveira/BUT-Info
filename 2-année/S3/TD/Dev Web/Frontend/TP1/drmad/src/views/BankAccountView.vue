<template>
  <div>
    <h1>Account data</h1>

    <span>account number</span><input v-model="number"><button @click="resetAccountNumber">Reset</button>
    <br />
    <button :disabled="!isAccountNumberValid" @click="getAccountAmount(number)">Get amount</button><button :disabled="!isAccountNumberValid" @click="getAccountTransactions(number)">Get transactions</button>
    <p v-if="accountNumberError===-1">invalid account number</p>
    <hr />
    <span>available amount : </span>
    <span v-if="accountNumberError === 1" >{{accountAmount}}</span>
    <span v-else></span>
    <hr />
    <p>passed transactions:</p>
    <div v-if="accountNumberError === 1">
      <ul>
        <li v-for="(trans,index) in accountTransactions" :key="index">{{trans.amount}} the {{convertDate(trans.date.$date)}}</li>
      </ul>
    </div>
    <span v-else></span>

  </div>

</template>

<script>

import {mapState, mapActions, mapMutations} from 'vuex'
export default {
  name: 'BankAccountView',
  data: () => ({
    number: '',
  }),
  computed: {
    ...mapState(['accountAmount', 'accountTransactions','accountNumberError']),
    isAccountNumberValid() {
      const rexp = RegExp('^[A-Za-z0-9]{22}-[0-9]{7}$','g')
      return rexp.test(this.number)
    }
  },
  methods: {
    ...mapActions(['getAccountAmount','getAccountTransactions']),
    ...mapMutations(['updateAccountNumberError']),
    convertDate(date) {
      let d = new Date(date)
      return d.getMonth()+"/"+d.getDate()+"/"+d.getFullYear()+" the "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds()
    },
    resetAccountNumber() {
      this.number = ''
      this.updateAccountNumberError(0)
    }
  }
}
</script>

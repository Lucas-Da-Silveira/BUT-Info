<template>
  <div>
    <table>
        <tr>
          <th v-for="(field, index) in fields" :key="index">{{field}}</th>
        </tr>
        <tr>
          <td v-for="(field, index) in fields" :key="index">{{data[field]}}</td>
        </tr>
    </table>
  </div>
</template>

<script>

export default {
  name: 'CheckedList',
  props: {
    data: Array, // les données sources
    fields: Array, // le tableau contenant le nom des champs
    itemCheck: Boolean, // s'il y a des case à cocher
    checked: Array, // le tableau des cases cochées
    itemButton: Object, // l'objet pour les boutons d'items
    listButton: Object, // l'objet pour le bouton de liste
  },
  data : () => {
    return {
    }
  }
}
</script>

<style scoped>
</style>
<template>
  <div>
    <p v-for="(item, indexRow) in data" :key="indexRow" class="checked-list-item">
      <input type="checkbox"
             v-if="itemCheck"
             :checked="checked[indexRow]"
             @click="$emit('checked-changed', indexRow)"
      >
      <span v-for="(field, indexCol) in fields" :key="indexCol" class="checked-list-field">
        {{ item[field] }}
      </span>
      <input v-if="itemAmount" type="number" v-model="itemAmountValues[indexRow]" class="item-amount-input"/>
      <button v-if="itemButton && itemButton.show" @click="handleItemButtonClick(indexRow)" class="item-button">{{ itemButton.text }}</button>
    </p>
    <button v-if="listButton && listButton.show" @click="handleListButtonClick" class="list-button">{{ listButton.text }}</button>
  </div>
</template>

<script>
export default {
  name: "CheckedList",
  props: {
    data: Array, // les données sources
    fields: Array, // le tableau contenant le nom des champs à afficher
    itemCheck: Boolean, // s'il y a des case à cocher
    checked: Array, // le tableau des cases cochées
    itemButton: Object, // l'objet pour les boutons d'items
    listButton: Object, // l'objet pour le bouton de liste
    itemAmount: Boolean,
  },
  data : () => {
    return {
      itemAmountValues: [],
    }
  },
  methods: {
    handleItemButtonClick(indexRow) {
      this.$emit('item-button-clicked', indexRow,);
    },
    handleListButtonClick() {
      const selectedItems = this.checked
          .map((isChecked, index) => {
            if (isChecked) {
              const itemAmountValue = this.itemAmountValues[index];
              return { index, itemAmountValue };
            }
            return null;
          })
          .filter(item => item !== null);

      this.$emit('list-button-clicked', selectedItems);
    },
  },
}
</script>

<style scoped>
.checked-list-item {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  display: flex;
  align-items: center;
}

.checked-list-field {
  margin-right: 10px;
}

.item-amount-input {
  width: 50px;
  margin-right: 10px;
}

.item-button,
.list-button {
  background-color: #3498db;
  color: #fff;
  border: none;
  padding: 8px 12px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.item-button:hover,
.list-button:hover {
  background-color: #2078b4;
}
</style>
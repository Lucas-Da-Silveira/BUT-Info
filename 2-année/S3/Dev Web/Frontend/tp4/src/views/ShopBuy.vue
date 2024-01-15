<script>
import ItemsList from '@/components/ItemsList.vue';
import BasketList from '@/components/BasketList.vue';

export default {
  name: "ShopBuy",
  components: {
    ItemsList,
    BasketList,
  },
};
</script>

<template>
  <div>
    <div class="left-panel">
      <ItemsList />
    </div>
    <div class="right-panel">
      <BasketList />
    </div>
  </div>
</template>

<style scoped>
.left-panel {
  float: left;
  width: 50%;
}

.right-panel {
  float: right;
  width: 50%;
}
</style>
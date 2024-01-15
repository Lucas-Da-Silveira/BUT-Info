<script>
export default {
  name: "ShopView"
}
</script>

<template>
  <div class="shop-container">
    <h1 class="shop-title">Boutique</h1>
    <router-view name="shopmain"></router-view>
  </div>
</template>

<style scoped>
.shop-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  background-color: #f8f8f8;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.shop-title {
  color: #333;
  font-size: 28px;
  margin-bottom: 20px;
}
</style>

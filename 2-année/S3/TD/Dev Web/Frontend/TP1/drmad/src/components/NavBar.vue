<template>
  <div>
    <button v-for="(button, index) in buttons" :key="index"  :style="{ backgroundColor: button.color }"  @click="handleClick(index)"> {{ button.text }} </button>
  </div>
</template>

<script>
export default {
  name: 'NavBar',
  props: { buttons: Array },
  methods: {
    handleClick(index) {
      this.$emit('menu-clicked', index);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
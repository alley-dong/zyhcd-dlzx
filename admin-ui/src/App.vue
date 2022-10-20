<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {

    }
  },
  components: {

  }
}
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.app-container {
  // background: #fff;
  padding-top: 1rem;
}
.box {
  width: 90%;
  border: 0.02rem solid #333;
  margin: auto;
  margin-top: 0.5rem;
  box-shadow: 0.15rem 0.15rem 5px #888888;
  background: #fff
}
</style>

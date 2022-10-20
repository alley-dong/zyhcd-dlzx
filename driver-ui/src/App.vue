<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {}
  },
  components: {},
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
html body {
  height: 100%;
  background: #fff;
}
.app-container {
  // background: #fff;
  padding-top: 1rem;
}
.my-underline {
  text-decoration: underline;
}
.box {
  width: 90%;
  border: 0.02rem solid #333;
  margin: auto;
  margin-top: 0.5rem;
  box-shadow: 0.15rem 0.15rem 5px #888888;
}
.left-title {
  text-align: left;
}
.van-cell {
  margin: 0.2rem 0;
}
</style>

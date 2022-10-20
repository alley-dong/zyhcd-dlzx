<template>
  <!-- <div class="header">
        <van-icon name="arrow-left" v-if="show" />
        <span>{{ $route.meta.title }}</span>
        <van-icon name="envelop-o" />
    </div> -->
  <van-nav-bar
    :title="$route.meta.title"
    :left-text="show ? '返回' : ''"
    :left-arrow="show"
    @click-left="$router.back(-1)"
  >
    <template #right>
      <router-link to="/message">
        <van-icon name="envelop-o" size="18" v-if="showRight" />
      </router-link>
      <van-button v-if="buttonShow" size="small" @click="handleClick">{{
        buttonText
      }}</van-button>
    </template>
  </van-nav-bar>
</template>

<script>
export default {
  props: {
    show: {
      type: Boolean,
      default: true,
    },
    buttonShow: {
      type: Boolean,
      default: false,
    },
    buttonText: {
      type: String,
      default: '新增',
    },
    buttonAccidentreport: {
      type: Boolean,
      default: false,
    },
    buttonAccidentreportText: {
      type: String,
      default: '事故上报',
    },
    showRight: {
      type: Boolean,
      default: false,
    },
  },
  created() {},
  methods: {
    handleClick() {
      this.$emit('handleClick')
    },
  },
}
</script>
<style lang="less">
.van-nav-bar {
  height: 48px;
  background: #113a8d;
  position: fixed;
  top: 0;
  width: 100%;
  bottom: 0;
  .van-icon {
    color: #fff;
  }
  .van-nav-bar__text {
    color: #fff;
  }
  .van-nav-bar__title {
    color: #fff;
  }
}
</style>
<style scoped lang="less">
.header {
  background: #113a8d;
  height: 48px;
  position: fixed;
  top: 0;
  width: 100%;
  bottom: 0;
}
</style>

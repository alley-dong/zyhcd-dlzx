<template>
  <div class="center">
    <Header :show="false"></Header>

    <!-- 头部 -->
    <!-- <header>
      <div class="beijin"></div>
      <div class="flex aic users pixed" style="width: 50%">
        <img
          :src="require('../../../assets/img/personal/tx.jpg')"
          class="imege"
        />
        <router-link v-if="!isLogin" to="/login" class="f16 fff ml-20"
          >立即登录</router-link
        >
        <span v-else class="f16 fff ml-20">用户 {{ username.slice(-4) }}</span>
      </div>
    </header> -->

    <!-- 主体 -->
    <!-- <main class="mt-10">
      <div class="bg-fff">
        <van-cell title="我的订单" isLink to="/order"></van-cell>
        <van-cell title="优惠卡" isLink></van-cell>
        <van-cell title="钱包" isLink></van-cell>
        <van-cell title="设置" isLink to="/my/set"></van-cell>
      </div>
    </main> -->
    <!-- <van-button type="primary">主要按钮</van-button> -->
    <div class="app-container">
      <van-cell-group>
        <van-cell title-style='text-align:left' value-class='value-left' title="姓名" :value="form.name" />
        <van-cell title-style='text-align:left' value-class='value-left' title="手机号" :value="form.contact" />
        <van-cell title-style='text-align:left' value-class='value-left' title="身份证号" :value="form.idCard" />
        <van-cell title-style='text-align:left' value-class='value-left' title="入职时间" :value="form.dntryTime" />
        <van-cell title-style='text-align:left' value-class='value-left' title="驾驶员类型" :value="form.driverTypeName" />
      
        <!-- <van-cell
          title="备注"
          value="描述信息描述信息描述信息描述信息描述信息描述信息"
        /> -->
      </van-cell-group>
    </div>
    <!-- <van-cell-group>
      <van-field v-model="value" label="姓名" />
    </van-cell-group> -->
    <div style="margin-top: 2rem;">
     <van-button v-show="showPwd" style="width: 70%;" color="linear-gradient(to right, #57C8B0, #14A98A)" round @click="$router.push('/editPassword')">修改密码</van-button>

    <van-button style="width: 70%;margin-top: 0.3rem;" color="linear-gradient(to right, #2862D9, #113a8d)" round @click="removeClick">退出登录</van-button>
    </div>
    <Footer page="my" />
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import { getToken, setToken, removeToken } from "@/utils/auth";
import { A01DriverInfo } from "@/api/api";

import { mapGetters } from "vuex";
export default {
  data() {
    return {
      value: "张三",
      form:{},
      showPwd:false
    };
  },
  mounted(){
    if(localStorage.getItem("pwdFlg")){
      this.showPwd = true;
    }
A01DriverInfo().then(res=>{
  // console.log(res);
  this.form = res.data
})
  },
  components: {
    Footer,
    Header,
  },

  computed: {
    // ...mapGetters(["username1", "isLogin"]),
  },
  methods:{
     removeClick() {
       let deviceType = localStorage.getItem("deviceType");
       console.log("deviceType",deviceType);
       try {
         if (deviceType == "android" || deviceType == "ios") {
           test.removeToken();
         }
       } catch (e) {
       }
       removeToken();
       this.$router.push("/login");
    },
  }
};
</script>

<style lang="less" scoped>
@import "./center.less";
</style>
<style lang="scss">
  .value-left{
    text-align: left;
  }
</style>
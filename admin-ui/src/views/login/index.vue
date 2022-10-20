<template>
  <header class="login">
    <!-- <van-icon name="point-gift-o" class="orange mt-30 logo"></van-icon> -->
    <div class="blue mt-30 logo">解放智慧物流公司</div>
    <div class>
      <!-- 手机号码 -->
      <!-- <div class="handset ml-20 mr-20 flex jc-sb pb-5">
        <input v-model="phone" class="f14" type="text" placeholder="手机号" />
        <input class="gray f12 bg-fff" type="button" value=" 获取验证码" />
      </div> -->
      <van-cell-group>
        <van-field
          v-model="form.username"
          label="账号"
          placeholder="请输入账号"
        />
        <van-field
          v-model="form.password"
          type="password"
          label="密码"
          placeholder="请输入密码"
        />
      </van-cell-group>
      <!-- 验证码 -->
      <!-- <div class="handset ml-20 mr-20 flex jc-sb mt-20 pb-5">
        <input v-model="smsCode" class="f14" placeholder="验证码" />
      </div> -->

      <!-- 登录 -->
      <button
        @click="login"
        class="butt ml-20 mr-20 fff"
        style="background: #113a8d"
      >
        登录
      </button>
    </div>
  </header>
</template>

<script>
import { constants } from "crypto";
export default {
  data() {
    return {
      isAndroid: false,
      isiOS: false,
      form: {
        username: undefined,
        password: undefined,
        code: "1",
        uuid: "7151b28cf6cd4006b33073919b4eb8db",

        token: null,
        deviceType: undefined,
      },
      /* 

      */
    };
  },
  mounted() {
    this.checkDevice();
  },
  methods: {
    checkDevice() {
      const u = navigator.userAgent;
      this.isAndroid =
        u.indexOf("Android") > -1 || u.indexOf("Adr") > -1 ? "android" : null;
      this.isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/) ? "ios" : null;
    },
    login() {
      // 发送请求,用post方式
      let url = "/login";
      // 请求参数
      let data = {
        phone: this.phone,
        smsCode: this.smsCode,
      };
      this.form.deviceType = this.isAndroid || this.isiOS || null;
      localStorage.setItem("deviceType", this.form.deviceType);
      this.$store
        .dispatch("Login", this.form)
        .then(() => {
          console.log(111);
          this.$router.push("/index");
        })
        .catch(() => {});
      //   this.$axios
      //     .post(url, data)
      //     .then((res) => {
      //       // 修改登陆状态
      //       this.$store.commit("updateLogin", true);
      //       // 把token存入store
      //       this.$store.commit("updateToken", res.user.token);
      //       // 把用户名存入store
      //       this.$store.commit("updateUsername", res.user.phone);
      //       this.$router.push("/my");
      //     })
      //     .catch((err) => {
      //       console.log("登陆失败");
      //     });
    },
  },
};
</script>

<style lang="less" scoped>
@import "./login.less";
.logo {
  // font-size: 100px !important;
  // margin-bottom: 150px;
  font-size: 0.8rem !important;
  margin-bottom: 2rem;
  padding-top: 1rem;
  font-weight: 600;
  margin-top: 0;
  // text-shadow: 0.3rem 0.2rem 0.1rem #888;
}
.blue {
  color: #113a8d;
}
</style>
<style lang="scss" scoped>
.login {
  background-image: url("../assets/bg.jpg");
  background-size: 100% 100%;
}
</style>
<style lang="scss">
.login {
  .van-cell-group {
    background-color: rgba(220, 38, 38, 0);
    .van-field__label {
      color: #fff;
      // font-weight: 600;
    }
    .van-cell {
      background-color: rgba(220, 38, 38, 0);
    }
  }
  input::-webkit-input-placeholder {
    color: #ffffff;
  }
  input {
    background-color: rgba(220, 38, 38, 0) !important;
      color: #fff;
  }
}
</style>
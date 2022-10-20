<template>
  <header class="login">
    <div class="blue mt-30 logo">解放智慧物流公司</div>
    <div class>
      <van-cell-group>
        <van-field
          v-model="form.contact"
          label="手机号"
          placeholder="请输入手机号"
        />
        <van-field
          v-model="form.password"
          type="password"
          label="密码"
          placeholder="请输入密码"
        />
      </van-cell-group>
      <!-- <div class="seePassword" @click="$router.push('/fpassword')">
        <span> 忘记密码 </span>
      </div> -->
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
// import { constants } from 'crypto'
// import { driverAuthLogin } from '@/api/login'
export default {
  data() {
    return {
      user_List: ["13591335661", "13709868863"],
      isAndroid: false,
      isiOS: false,
      form: {
        // contact: '18840920318',
        // password: '123',
        contact: undefined,
        password: undefined,
        token: null,
        deviceType: undefined,
      },
      /* 

      */
    };
  },
  mounted() {
    // console.log();
    // console.log();
    // let str = this.user_List[Math.floor(Math.random() * this.user_List.length)];
    // console.log(str);
    // this.form.contact = str;
    this.checkDevice();
    // this.getdriverAuthLogin()
  },
  methods: {
    checkDevice() {
      const u = navigator.userAgent;
      this.isAndroid =
        u.indexOf("Android") > -1 || u.indexOf("Adr") > -1 ? "android" : null;
      this.isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/) ? "ios" : null;
    },
    login() {
      this.form.deviceType = this.isAndroid || this.isiOS || null;
      localStorage.setItem("deviceType", this.form.deviceType);
      // // 发送请求,用post方式
      // let url = '/login'
      // // 请求参数
      // let data = {
      //   phone: this.phone,
      //   smsCode: this.smsCode,
      // }
      this.$store
        .dispatch("Login", this.form)
        .then(() => {
          console.log(this.test);
          this.$router.push("/index");
        })
        .catch(() => {});
    },
  },
};
</script>

<style lang="scss" scoped>
  .login {
    background-image: url("../login/bg.jpg");
    background-size: 100% 100%;
  }
</style>

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
.seePassword {
  text-align: right;
  color: #1989fa;
  font-size: 0.25rem;
  padding-right: 0.4rem;
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

<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-form @submit="onSubmit">
        <van-field
          v-model="form.oldPassword"
          :type="isType ? 'password' : 'text'"
          name="原密码"
          label="原密码"
          placeholder="原密码"
          :rules="[{ required: true, message: '请填写原密码' }]"
        />
        <van-field
          v-model="form.newPassword"
          :type="isType ? 'password' : 'text'"
          name="新密码"
          label="新密码"
          placeholder="新密码"
          :rules="[{ required: true, message: '请填写新密码' }]"
        />
        <van-field
          v-model="form.confirmPassword"
          :type="isType ? 'password' : 'text'"
          name="确认密码"
          label="确认密码"
          placeholder="确认密码"
          :rules="[{ required: true, message: '请填写确认密码' }]"
        />
        <div class="seePassword">
          <span @click="isType = !isType">{{
            isType ? "查看密码" : "隐藏密码"
          }}</span>
        </div>
        <div style="margin: 16px; margin-top: 0.5rem">
          <van-button round block type="info" native-type="submit">
            提交</van-button
          >
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {driverUpdatePassword} from '@/api/api'
import { Toast } from "vant";
export default {
  data() {
    return {
      isType: true,
      form: {
        oldPassword: undefined,
        newPassword: undefined,
        confirmPassword: undefined,
      },
    };
  },
  components: {
    Header,
  },
  methods: {
    onSubmit(values) {
      console.log("submit", values);
      console.log(this.form);
      if (this.form.newPassword != this.form.confirmPassword) {
        Toast("新密码与确认密码不一致");
        return;
      }
      driverUpdatePassword(this.form).then((res) => {
        Toast(res.msg);
        this.$router.push("/login");
      });
      // TODO:调接口改密码
    },
  },
};
</script>

<style lang="scss" scoped>
.seePassword {
  text-align: right;
  color: #1989fa;
  font-size: 0.25rem;
  padding-right: 0.4rem;
}
</style>
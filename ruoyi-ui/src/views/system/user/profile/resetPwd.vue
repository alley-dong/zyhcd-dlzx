<template>
  <el-form ref="form" :model="user" :rules="rules" label-width="80px">
    <!-- <el-form-item label="旧密码" prop="oldPassword">
      <el-input v-model="user.oldPassword" placeholder="请输入旧密码" type="password" />
    </el-form-item>    -->
    <el-form-item label="手机号" prop="phone">
      <el-input v-model="user.phone" placeholder="请输入手机号" type="text" />
    </el-form-item>
    <el-form-item label="新密码" prop="passwd">
      <el-input
        v-model="user.passwd"
        placeholder="请输入新密码"
        type="password"
      />
    </el-form-item>
    <!-- <el-form-item label="确认密码" prop="confirmPassword">
      <el-input
        v-model="user.confirmPassword"
        placeholder="请确认密码"
        type="password"
      />
    </el-form-item> -->
    <!-- <el-row :gutter="20">
  <el-col :span="6"><div class="grid-content bg-purple"></div></el-col> -->
    <el-form-item label="验证码" prop="smsCode">
      <el-row>
        <el-col :span="20">
          <el-input
            v-model="user.smsCode"
            placeholder="请输入验证码"
            type="text"
          />
        </el-col>
        <el-col :span="4">
          <el-button
            v-if="isSms"
            type="success"
            round
            plain
            size="small"
            style="width: 90%; margin-left: 8px; padding-left: 11px"
            @click="smsClick"
            >获取验证码</el-button
          >
          <el-button
            v-else
            type="success"
            round
            plain
            size="small"
            style="width: 90%; margin-left: 8px"
            >{{ time }}s</el-button
          >
        </el-col>
      </el-row>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
      <el-button type="danger" size="mini" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { findPassword, sendCode } from "@/api/system/user";
// import qs from 'qs' 
export default {
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.user.passwd !== value) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    return {
      test: "1test",
      isSms: true,
      time: 60,
      user: {
        phone: undefined,
        oldPassword: undefined,
        smsCode: undefined,
        passwd: undefined,
        confirmPassword: undefined,
      },
      // 表单校验
      rules: {
        phone: [{ required: true, message: "手机号不能为空", trigger: "blur" }],
        passwd: [
          { required: true, message: "新密码不能为空", trigger: "blur" },
          // {
          //   min: 6,
          //   max: 20,
          //   message: "长度在 6 到 20 个字符",
          //   trigger: "blur",
          // },
        ],
        smsCode: [
          { required: true, message: "验证码不能为空", trigger: "blur" },
          // { required: true, validator: equalToPassword, trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    smsClick() {
      if (!this.user.phone) {
        this.msgSuccess("请输入手机号");
        return;
      }
      let param = {phone : this.user.phone};
      sendCode(param).then((res) => {
        this.isSms = false;
        let _this = this;
        _this.time = 60;
        let time = setInterval(() => {
          if (_this.time == 1) {
            clearInterval(time);
            this.isSms = true;
          }
          _this.time--;
        }, 1000);
      });
    },
    submit() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log("this.user",this.user);
          let param ={
            phone:this.user.phone,
            smsCode:this.user.smsCode,
            passwd: encodeURIComponent(this.user.passwd),
          }
          findPassword(param).then((res) => {
            this.msgSuccess(res.msg);
          });
          // updateUserPwd(this.user.oldPassword, this.user.passwd).then(
          //   (response) => {
          //     this.msgSuccess("修改成功");
          //   }
          // );
        }
      });
    },
    close() {
      this.$store.dispatch("tagsView/delView", this.$route);
      this.$router.push({ path: "/index" });
    },
  },
};
</script>

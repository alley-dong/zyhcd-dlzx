<template>
  <div>
    <div
      style="
        width: 98%;
        margin: 0 auto;
        margin: 20px 10px 0 0;
        overflow: hidden;
      "
    >
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        :disabled="isDisabled"
        @click="submitClick()"
        >提交</el-button
      >
      <!-- <router-link :to="{ path: '/recruitmentPlan', name: 'recruitmentPlan' }"> -->
        <el-button type="primary" @click="$router.go(-1)" style="float: right; margin-right: 10px"
          >返回</el-button
        >
      <!-- </router-link> -->
    </div>
    <div class="wrap">
      <div class="text">招聘信息</div>
      <div class="line"></div>
    </div>
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
      style="width: 90%; margin: 0 auto; margin-top: 20px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item
            label="线路:"
            prop="routeName"
            :rules="[{ required: true, message: '线路为必填项' }]"
          >
            <el-input
              v-model="form.routeName"
              placeholder="请输入线路"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item
            label="招聘人数:"
            prop="number"
            :rules="[
              { required: true, message: '招聘人数为必填项' },
              { type: 'number', message: '招聘人数必须为数字值' },
            ]"
          >
            <el-input
              v-model.number="form.number"
              placeholder="请输入招聘人数"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item
            label="学历要求:"
            prop="education"
            :rules="[{ required: true, message: '学历要求为必填项' }]"
          >
            <el-input
              v-model="form.education"
              placeholder="请输入学历要求"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item
            label="年龄要求:"
            prop="age"
            :rules="[
              { required: true, message: '年龄要求为必填项' },
            ]"
          >
            <el-input
              v-model="form.age"
              placeholder="请输入年龄要求"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item
            label="驾驶要求:"
            prop="drivingRequirements"
            :rules="[{ required: true, message: '驾驶要求为必填项' }]"
          >
            <el-input
              v-model="form.drivingRequirements"
              placeholder="请输入驾驶要求"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item
            label="入职时间："
            prop="entryTime"
            :rules="[{ required: true, message: '入职时间为必填' }]"
          >
            <el-date-picker
              style="width: 100%"
              type="date"
              placeholder="请选择入职时间"
              v-model="form.entryTime"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item
            label="工资待遇："
            prop="wage"
            :rules="[{ required: true, message: '工资待遇为必填' }]"
          >
            <el-input
              v-model.number="form.wage"
              placeholder="请输入工资待遇"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item
            label="需求原因："
            prop="reason"
            :rules="[{ required: true, message: '需求原因为必填项' }]"
          >
            <el-input type="textarea" v-model="form.reason"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item
            label="工作职责："
            prop="duty"
            style="margin-bottom: 30px"
            :rules="[{ required: true, message: '工作职责为必填项' }]"
          >
            <el-input type="textarea" v-model="form.duty"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import { recruitmentPlanAdd } from "@/api/driver/recruitmentPlan";

export default {
  name: "addRecruitmentInfo",
  data() {
    return {
      isDisabled:false,
      form: {
        routeName: "",
        number: "",
        education: "",
        age: "",
        drivingRequirements: "",
        entryTime: "",
        wage: "",
        reason: "",
        duty: "",
      },
    };
  },
  methods: {
    onInput() {
      this.$forceUpdate();
    },
    // 点击提交
    submitClick() {
      this.isDisabled = true;
      recruitmentPlanAdd(this.form)
        .then((response) => {
          console.log(response);
          this.$message({
            message: "提交成功",
            type: "success",
          });
          this.$refs["form"].resetFields();
          this.$router.go(-1)
          // setTimeout(() => {
          // this.$router.push({
          //   path: "/recruitmentPlan",
          //   name: "recruitmentPlan",
          //   query: { num: "0" },
          // });
          // },1000)
        })
        .catch((err) => {
          console.log(err);
          this.isDisabled = false;
        });
    },
  },
};
</script>

<style scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
  width: 10%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
  font-weight: 400;
}
.line {
  width: 90%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
</style>

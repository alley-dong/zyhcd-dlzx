<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      style="width: 98%; margin: 0 auto; margin-top: 30px; overflow: hidden"
    >
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="$router.go(-1)"
        >返回</el-button
      >
    </el-form>
    <div class="wrap">
      <div class="text">公告信息</div>
      <div class="line"></div>
    </div>
    <el-form
      ref="form"
      :model="form"
      label-width="120px"
      style="width: 90%; margin: 0 auto; margin-top: 20px"
    >
      <el-form-item
        label="标题："
        prop="title"
        :rules="[{ required: true, message: '标题为必填项' }]"
      >
        <div>{{ form.title }}</div>
        <!-- <el-input v-model="form.title"></el-input> -->
      </el-form-item>
      <el-form-item
        label="公告截至时间："
      >
        <div>{{ form.deadline }}</div>
        <!-- <el-input v-model="form.title"></el-input> -->
      </el-form-item>
      <el-form-item
        label="通知人员："
        :rules="[{ required: true, message: '通知人员为必选项' }]"
      >
        <!-- <el-button style="float: left; margin-right:10px;" v-model="form.driverNameList" v-for="item in form.driverNameList">{{item}}</el-button> -->
        <div :style="{ width: '100%', display: 'flex', flexWrap: 'wrap' }">
          <div v-for="(item, index) in form.driverNameList" :key="index">
            <el-tag v-if="item" style="margin-right: 20px">{{ item }}</el-tag>
          </div>
        </div>
      </el-form-item>
      <el-form-item
        label="公告内容："
        prop="content"
        :rules="[{ required: true, message: '公告内容为必填项' }]"
      >
        <div v-html="form.content"></div>
        <!-- <el-input type="textarea" v-model="form.content"></el-input> -->
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { noticeManageDetails } from "@/api/driver/noticeManage";

export default {
  name: "checkNotice",
  data() {
    return {
      form: {
        title: "",
        content: "",
        driverNameList: [],
      },
    };
  },
  created() {
    this.getParams();
  },
  methods: {
    // 获取数据
    getParams() {
      //取到路由带过来的参数
      this.id = this.$route.query.id;
      if (!this.id) {
        return;
      }
      // var getId=this.id
      noticeManageDetails(this.id)
        .then((response) => {
          console.log(response);
          this.form = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  watch: {
    $route: "getParams",
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
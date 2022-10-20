<template>
  <div>
    <!-- 面试上传 -->
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm" :loading="saveLoading"
          >保存</el-button
        >
        <el-button @click="backClick">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">面试记录</div>
      <div class="line"></div>
    </div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="" prop="interviewRecord">
        <el-input
          v-model="formData.interviewRecord"
          type="textarea"
          placeholder="请输入"
          :autosize="{ minRows: 4, maxRows: 4 }"
          :style="{ width: '100%' }"
        ></el-input>
      </el-form-item>
    </el-form>
    <div class="wrap">
      <div class="text">面试结果</div>
      <div class="line"></div>
    </div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="" prop="interviewStatus">
        <el-radio-group v-model="formData.interviewStatus" size="medium">
          <el-radio
            v-for="(item, index) in field102Options"
            :key="index"
            :label="item.value"
            :disabled="item.disabled"
            >{{ item.label }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item label="" prop="interviewRemarks">
        <el-input
          v-model="formData.interviewRemarks"
          type="textarea"
          placeholder="请输入"
          :autosize="{ minRows: 4, maxRows: 4 }"
          :style="{ width: '100%' }"
        ></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { A11Info, A15InterviewReview } from "@/api/driver/interviewManage";
export default {
  data() {
    return {
      saveLoading: false,
      formData: {
        interviewRecord: undefined,
        interviewStatus: undefined,
        interviewRemarks: undefined,
      },
      field102Options: [
        {
          label: "通过",
          value: 3,
        },
        {
          label: "不通过",
          value: 2,
        },
        // {
        //   label: "储备",
        //   value: 4,
        // },
      ],
      rules: {
        interviewRecord: [],
      },
    };
  },
  mounted() {
    this.A11InfoData();
  },
  methods: {
    init() {
      this.A11InfoData();
    },
    // 返回
    backClick() {
      // this.$router.push({ path: "/interviewManage", name: "interviewManage" });
      this.$router.go(-1)

    },
    // // 基本信息
    A11InfoData() {
      A11Info(this.$route.params.id).then((res) => {
        console.log(res.data.interviewRecord);

        this.formData = res.data;
      });
    },
    // 保存
    submitForm() {
      this.saveLoading = true;
      this.formData.id = this.$route.params.id
      A15InterviewReview(this.formData).then((res) => {
        this.saveLoading = false;
this.backClick()
        // this.$router.push({
        //   path: `/basicInfo/${this.$route.params.id}`,
        //   query: { num: "1" },
        // });
      });
    },
  },
};
</script>


<style lang="scss" scoped>
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
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
</style>
<template>
  <div>
    <!-- 实操 -->
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm" :loading="saveLoading"
          >保存</el-button
        >
        <el-button @click="backClick">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">实操记录</div>
      <div class="line"></div>
    </div>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="191px"
        style="width: 80%; margin: auto"
      >
        <el-col :span="10">
          <el-form-item label="直线行驶" prop="driveStraightDrivingStatus">
            <el-radio-group
              v-model="formData.driveStraightDrivingStatus"
              size="medium"
            >
              <el-radio
                v-for="(item, index) in field101Options"
                :key="index"
                :label="item.value"
                :disabled="item.disabled"
                >{{ item.label }}</el-radio
              >
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item
            label-width="1px"
            label=""
            prop="driveStraightDrivingRemarks"
          >
            <el-input
              v-model="formData.driveStraightDrivingRemarks"
              placeholder="请输入"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item label="倒车入库" prop="driveParkingStatus">
            <el-radio-group v-model="formData.driveParkingStatus" size="medium">
              <el-radio
                v-for="(item, index) in field101Options"
                :key="index"
                :label="item.value"
                :disabled="item.disabled"
                >{{ item.label }}</el-radio
              >
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label-width="1px" label="" prop="driveParkingRemarks">
            <el-input
              v-model="formData.driveParkingRemarks"
              placeholder="请输入"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="10">
          <el-form-item
            label="简单的故障判断及故障处理"
            prop="driveEmergencyStatus"
          >
            <el-radio-group
              v-model="formData.driveEmergencyStatus"
              size="medium"
            >
              <el-radio
                v-for="(item, index) in field101Options"
                :key="index"
                :label="item.value"
                :disabled="item.disabled"
                >{{ item.label }}</el-radio
              >
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="14">
          <el-form-item label-width="1px" label="" prop="driveEmergencyRemarks">
            <el-input
              v-model="formData.driveEmergencyRemarks"
              placeholder="请输入"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <!-- <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item> -->
        </el-col>
      </el-form>
    </el-row>
    <div class="wrap">
      <div class="text">实操结果</div>
      <div class="line"></div>
    </div>
    <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
      <el-form-item label="" prop="driveStraightDrivingStatus">
        <el-radio-group v-model="formData.driveStatus" size="medium">
          <el-radio
            v-for="(item, index) in field101Options1"
            :key="index"
            :label="item.value"
            :disabled="item.disabled"
            >{{ item.label }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item label="" prop="driveStraightDrivingRemarks">
        <el-input
          v-model="formData.driveRemarks"
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
import { A11Info, A17PracticeReviewSave } from "@/api/driver/interviewManage";

export default {
  data() {
    return {
      saveLoading: false,
      formData: {
        driveStraightDrivingStatus: undefined,
        driveStraightDrivingRemarks: undefined,
        driveParkingStatus: undefined,
        driveParkingRemarks: undefined,
        driveEmergencyStatus: undefined,
        driveEmergencyRemarks: undefined,
        driveStatus: undefined,
        field109: undefined,
      },
      rules: {
        driveStraightDrivingStatus: [],
        driveStraightDrivingRemarks: [
          {
            required: true,
            message: "请输入",
            trigger: "blur",
          },
        ],
        driveParkingStatus: [],
        driveParkingRemarks: [
          {
            required: true,
            message: "请输入",
            trigger: "blur",
          },
        ],
        driveEmergencyStatus: [],
        driveEmergencyRemarks: [
          {
            required: true,
            message: "请输入",
            trigger: "blur",
          },
        ],
      },

      field101Options: [
        {
          label: "通过",
          value: 3,
        },
        {
          label: "不通过",
          value: 2,
        },

      
      ],
        field101Options1: [
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
    };
  },
  methods: {
    init() {
      this.A11InfoData();
    },
    // // 基本信息
    A11InfoData() {
      A11Info(this.$route.params.id).then((res) => {
        this.formData = res.data;
      });
    },
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
        this.saveLoading = true;
        // setTimeout(() => {
        // }, 2000);
        A17PracticeReviewSave(this.formData)
          .then((res) => {
            window.history.forward(1);
            this.saveLoading = false;
            // this.$router.push({
            //   path: `/basicInfo/${this.$route.params.id}`,
            //   query: { num: "3" },
            // });
            this.backClick()
          })
          .catch((err) => {
            this.saveLoading = false;
          });
      });
    },
    backClick() {
      this.$refs["elForm"].resetFields();
      this.$router.go(-1)
      // this.$router.push({ path: "/interviewManage", name: "interviewManage" });
    },
  },
  mounted() {
    this.init();
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
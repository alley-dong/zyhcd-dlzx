<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <!-- <el-button type="primary" @click="submitForm"> 保存 </el-button> -->
        <el-button @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">事故信息</div>
      <div class="line"></div>
    </div>

    <div style="margin-top:20px;">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="100px"
        >
          <el-col :span="16">
            <el-form-item label="违法车辆" prop="licensePlate">
              <el-input
                v-model="formData.licensePlate"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="违法地点" prop="wzdd">
              <el-input
                v-model="formData.wzdd"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="违法时间" prop="wzsj">
              <el-input
                v-model="formData.wzsj"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="违法行为" prop="wzxw">
              <el-input
                v-model="formData.wzxw"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="处罚" prop="wzfk">
              <el-input
                v-model="formData.wzfk"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="违法照片">
              <el-col :span="6" v-for="o in formData.fileList" :key="o">
                <el-card v-if="o.filePath" :body-style="{ padding: '0px' }">
                  <el-image :src="o.filePath"> </el-image>
                </el-card>
              </el-col>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="选择驾驶员：" prop="driverName">
              <el-select
                v-model="formData.driverName"
                placeholder="请选择选择驾驶员："
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in personList"
                  :key="index"
                  :label="item.name"
                  :value="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="处理违章截止时间" prop="deadline">
              <el-input
                v-model="formData.deadline"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>
  </div>
</template>

<script>
import { L05GetInfo } from "@/api/safetyManagement/violationOfTheDetails";
import {
  L06DriverList,
  L06Add
} from "@/api/safetyManagement/newDetailsOfViolations";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        licensePlate: null,
        wzdd: "",
        wzsj: "",
        wzxw: "",
        wzfk: "",
        driverName: "",
        deadline: ""
      },
      // 驾驶员下拉
      personList: [],
      rules: {},
      cardList: []
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getL05GetInfo();
    this.getL06DriverList();
  },
  mounted() {},
  methods: {
    //数据回显
    getL05GetInfo() {
      L05GetInfo(this.$route.params.id).then(res => {
        this.formData = res.data;
      });
    },
    //违章管理-通过的驾驶员下拉选
    getL06DriverList() {
      L06DriverList({}).then(res => {
        this.personList = res.data;
      });
    },

    // 提交
    submitForm() {
      L06Add(this.formData)
        .then(res => {
          this.$message({
            message: "新增成功",
            type: "success"
          });
        })
        .then(res => {
          this.$router.back();
        });
    },
    resetForm() {
      //   this.$refs["elForm"].resetFields();
      this.$router.go(-1);
    }
  }
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
.image {
  width: 100%;
  display: block;
}
</style>

<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button @click="resetForm">返回</el-button>
        <el-button
          type="primary"
          @click="submitForm"
          v-hasPermi="['person:safeTrafficTransgression:change']"
        >
          驳回
        </el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">基本信息</div>
      <div class="line"></div>
    </div>

    <div style="margin-top:20px;">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="130px"
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
            <el-form-item label="违法照片" prop="accidentTime">
              <el-col :span="6" v-for="o in formData.fileList" :key="o">
                <el-card v-if="o.type == 7" :body-style="{ padding: '0px' }">
                  <el-image v-if="o.filePath" :src="o.filePath" :preview-src-list="[o.filePath]"> </el-image>
                </el-card>
              </el-col>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="选择驾驶员" prop="driverName">
              <el-input
                v-model="formData.driverName"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
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
          <el-col :span="16">
            <el-form-item label="违法处理凭证" prop="accidentTime">
             <el-col :span="6" v-for="o in formData.voucherFileList" :key="o">
                <el-card v-if="o.type == 8" :body-style="{ padding: '0px' }">
                  <el-image v-if="o.filePath" :src="o.filePath" :preview-src-list="[o.filePath]"> </el-image>
                </el-card>
              </el-col>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="处理完成时间" prop="completeTime">
              <el-input
                v-model="formData.completeTime"
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
import {
  etcCardBox, //车牌号下拉
  cardEdit, //数据回显
  cardSave //数据保存
} from "@/api/etc/card";
import {
  L03SafeAccidentInfo,
  L02SafeAccidentCheck
} from "@/api/safetyManagement/trafficAccidentManagementExaminationAndApproval";
import { L05GetInfo } from "@/api/safetyManagement/violationOfTheDetails";
import { L10Change } from "@/api/safetyManagement/detailsOfViolationHaveBeenDealtWith";
export default {
  components: {},
  props: [],
  data() {
    return {
      // formData: {
      //  driverName:"",
      //  vehicleName:"",
      //  accidentPlace:"",
      //  accidentTime:"",
      //   id:"",
      //   status:null,
      //   reviewRemarks:"",
      //   userid:"",
      //   reviewUser:""
      // },
      formData: {
        licensePlate: null,
        wzdd: "",
        wzsj: "",
        wzxw: "",
        wzfk: "",
        driverName: "",
        deadline: "",
        completeTime: ""
      },

      rules: {},
      cardList: [],
      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      srcList: [
        "https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg",
        "https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg"
      ]
    };
  },
  computed: {},
  watch: {},
  created() {
    // this.etcCardBoxData();
    this.getL05GetInfo();
  },
  mounted() {},
  methods: {
    //数据回显
    getL05GetInfo() {
      L05GetInfo(this.$route.params.id).then(res => {
        this.formData = res.data;
      });
    },
    //补助管理-补助列表获取id
    // etcCardBoxData() {
    //   C01List({}).then((res) => {
    //       this.formData.id=res.rows[0].id
    // });
    // },
    // handleChange(val) {
    //     console.log(val,JSON.stringify(this.field101Options));
    //   for (const item of this.field101Options) {
    //     if (val == item.id) {
    //       console.log(item);

    //       console.log(item.licensePlate);
    //       this.formData.licensePlate = item.licensePlate ;
    //     }
    //   }
    // },
    // 提交
    submitForm() {
      L10Change(this.$route.params.id).then((res) => {
        this.$message({
          message: "驳回成功",
          type: "success"
        });
        this.$router.go(-1);
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

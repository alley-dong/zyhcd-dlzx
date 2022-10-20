<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button @click="resetback">返回</el-button>
        <el-button
          type="primary"
          @click="submitForm"
          v-hasPermi="['person:performanceRecord:edit']"
          >编辑</el-button
        >
      </div>
    </div>
    <div class="wrap">
      <div class="text">基本信息</div>
      <div class="line"></div>
    </div>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="110px"
      >
        <el-col :span="23">
          <el-form-item label="驾驶员姓名" prop="driverName">
            <el-input
              v-model="formData.driverName"
              placeholder="驾驶员姓名"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="23">
          <el-form-item label="	绩效名称" prop="driverName">
            <el-input
              v-model="formData.performanceName"
              placeholder="绩效名称"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="开始时间" prop="taskStartTime">
            <el-input
              v-model="formData.taskStartTime"
              placeholder="驾驶员姓名"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束时间" prop="taskEndTime">
            <el-input
              v-model="formData.taskEndTime"
              placeholder="结束时间"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="11" v-show="this.formData.taskStatus === '正常'">
          <el-form-item label="主车车牌号" prop="mainLicensePlate">
            <el-input
              v-model="formData.mainLicensePlate"
              placeholder="主车车牌号"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12" v-show="this.formData.taskStatus === '正常'">
          <el-form-item label="挂车车牌号" prop="hangLicensePlate">
            <el-input
              v-model="formData.hangLicensePlate"
              placeholder="挂车车牌号"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="23">
          <el-form-item label="对班司机姓名" prop="viceDriverName">
            <el-input
              v-model="formData.viceDriverName"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="23">
          <el-form-item label="绩效金额" prop="amount">
            <el-input
              v-model="formData.amount"
              placeholder="请输入绩效金额"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="23">
          <el-form-item label="任务状态" prop="taskStatus">
            <el-input
              v-model="formData.taskStatus"
              placeholder="任务状态"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
          <el-col :span="23">
            <el-form-item label="篷布补助" prop="tarpaulin">
              <el-input
                v-model="formData.tarpaulin"
                placeholder="请输入篷布补助金额"
                clearable
                :style="{ width: '100%' }"
                :disabled="true"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="1" style="line-height: 36px"> 元 </el-col>
        </el-col>
        <el-col :span="23">
          <el-form-item label="实际行驶公里数" prop="kilometre">
            <el-input
              v-model="formData.kilometre + '公里'"
              placeholder="实际行驶公里数"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import { G09GetInfo } from "@/api/performanceManagement/ViewLongDistancePerformanceRecords";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        id: undefined,
        driverName: undefined,
        performanceName: undefined,
        taskStartTime: undefined,
        taskEndTime: undefined,
        taskStatus: undefined,
        viceDriverName: undefined,
        amount: undefined,
        tarpaulin: undefined,
        mainLicensePlate: undefined,
        hangLicensePlate: undefined,
        kilometre: undefined,
      },
      rules: {
        name: [
          {
            required: true,
            message: "请选择绩效名称",
            trigger: "blur",
          },
        ],
        routeName: [
          {
            required: true,
            message: "请选择选择路线",
            trigger: "change",
          },
        ],
        chargeType: [
          {
            required: true,
            message: "请选择选择计费类型",
            trigger: "change",
          },
        ],
        vehicleTypeName: [
          {
            required: true,
            message: "请选择车辆类型",
            trigger: "change",
          },
        ],
        isVice: [
          {
            required: true,
            message: "请选择是否有对班司机",
            trigger: "change",
          },
        ],
        amount: [
          {
            required: true,
            message: "请输入绩效金额",
            trigger: "blur",
          },
        ],
          tarpaulin: [
          {
            required: true,
            message: "请输入篷布补助金额",
            trigger: "blur",
          },
        ],
      },
      routeNameOptions: [],
      vehicleTypeNameOptions: [],
      // chargeTypeOptions: [{
      //   "chargeType": "按天计算",
      //   "value": 1
      // }, {
      //   "chargeType": "按趟计算",
      //   "value": 2
      // }],
      isViceOptions: [
        {
          isVice: "是",
          value: 1,
        },
        {
          isVice: "否",
          value: 2,
        },
      ],
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getG01GetInfo();
  },
  mounted() {},
  methods: {
    // 查看
    getG01GetInfo() {
      G09GetInfo(this.$route.params.id).then((res) => {
        this.formData = res.data;
        this.formData.taskStatus =
          this.formData.taskStatus == 1 ? "异常" : "正常";
      });
    },
    submitForm(elForm) {
      // this.$refs['elForm'].validate(valid => {
      //   if (valid){

      //   }

      // })
      this.$router.push({
        path: "/editDocumentPerformanceRecord/" + this.$route.params.id,
      });
    },
    resetback() {
      this.$router.back();
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
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
}
</style
>>

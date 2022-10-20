<template>
  <div class="app-container">
    <div class="box">
      <div class="btn-group">
        <el-button @click="resetback">返回</el-button>
        <el-button
          type="primary"
          @click="submitForm"
          v-hasPermi="['person:performance:edit']"
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
        label-width="130px"
      >
        <el-col :span="24">
          <el-form-item label="绩效名称" prop="name">
            <el-input
              v-model="formData.name"
              placeholder="请输入绩效名称"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="选择路线" prop="routeName">
            <el-select
              v-model="formData.routeName"
              placeholder="请选择选择路线"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
              <el-option
                v-for="(item, index) in routeNameOptions"
                :key="index"
                :label="item.name"
                :value="item.name"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="选择计费类型" prop="chargeType">
            <el-select
              v-model="formData.chargeType"
              placeholder="请选择选择计费类型"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
              <el-option
                v-for="(item, index) in chargeTypeOptions"
                :key="index"
                :label="item.chargeType"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车辆类型" prop="vehicleTypeName">
            <el-select
              v-model="formData.vehicleTypeName"
              placeholder="请选择车辆类型"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
              <el-option
                v-for="(item, index) in vehicleTypeNameOptions"
                :key="index"
                :label="item.name"
                :value="item.name"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否有对班司机" prop="isVice">
            <el-select
              v-model="formData.isVice"
              placeholder="请选择是否有对班司机"
              clearable
              :style="{ width: '100%' }"
              :disabled="true"
            >
              <el-option
                v-for="(item, index) in isViceOptions"
                :key="index"
                :label="item.isVice"
                :value="item.value"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
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
        <el-col :span="1" style="line-height:36px"> 元 </el-col>

      </el-form>
    </el-row>
  </div>
</template>
<script>
import { G01GetInfo } from "@/api/performanceManagement/seeLongDistancePerformance";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        name: undefined,
        routeId: undefined,
        routeName: undefined,
        chargeType: undefined,
        vehicleType: undefined,
        vehicleTypeName: undefined,
        isVice: undefined,
        tarpaulin: undefined,
        amount: undefined
      },
      rules: {
        name: [
          {
            required: true,
            message: "请选择绩效名称",
            trigger: "blur"
          }
        ],
        routeName: [
          {
            required: true,
            message: "请选择选择路线",
            trigger: "change"
          }
        ],
        chargeType: [
          {
            required: true,
            message: "请选择选择计费类型",
            trigger: "change"
          }
        ],
        vehicleTypeName: [
          {
            required: true,
            message: "请选择车辆类型",
            trigger: "change"
          }
        ],
        isVice: [
          {
            required: true,
            message: "请选择是否有对班司机",
            trigger: "change"
          }
        ],
        amount: [
          {
            required: true,
            message: "请输入绩效金额",
            trigger: "blur"
          }
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
      chargeTypeOptions: [
        {
          chargeType: "按天计算",
          value: 1
        },
        {
          chargeType: "按趟计算",
          value: 2
        }
      ],
      isViceOptions: [
        {
          isVice: "是",
          value: 1
        },
        {
          isVice: "否",
          value: 2
        }
      ]
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
      G01GetInfo(this.$route.params.id).then(res => {
        this.formData = res.data;
      });
    },
    submitForm(elForm) {
      // this.$refs['elForm'].validate(valid => {
      //   if (valid){

      //   }

      // })
      this.$router.push({
        path: "/editorLongDistancePerformance/" + this.$route.params.id
      });
    },
    resetback() {
      this.$router.back();
    }
  }
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
}</style
>>

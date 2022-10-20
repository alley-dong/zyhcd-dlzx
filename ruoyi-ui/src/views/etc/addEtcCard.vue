<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm">保存</el-button>
        <el-button @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">新增</div>
      <div class="line"></div>
    </div>
    <div style="display: flex; margin-top: 30px">
      <el-row :gutter="15" style="width: 70%">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="121px"
        >
          <el-col :span="12">
            <!-- <el-form-item label="姓名" prop="name"> -->
            <el-form-item label="车牌号：" prop="licensePlate">
              <!-- <el-select
                v-model="formData.id"
                placeholder="请选择车牌号："
                @change="handleChange"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in field101Options"
                  :key="index"
                  :label="item.licensePlate"
                  :value="item.id"
                ></el-option>
              </el-select> -->
              <el-autocomplete
                class="inline-input"
                v-model="formData.licensePlate"
                :fetch-suggestions="querySearch"
                placeholder="请输入车牌号"
                @select="handleSelect"
                :style="{ width: '100%' }"
              >
                <template slot-scope="{ item }" :style="{ width: '100%' }">
                  <!-- <div class="name">{{ item.id }}</div> -->
                  <span class="addr">{{ item.licensePlate }}</span>
                </template>
              </el-autocomplete>
            </el-form-item>
            <el-form-item label="ETC卡号" prop="etcCardNumber">
              <el-input
                v-model="formData.etcCardNumber"
                placeholder="请输入ETC卡号"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
            <el-form-item label="订单号" prop="orderNumber">
              <el-input
                v-model="formData.orderNumber"
                placeholder="请输入订单号"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
            <el-form-item label="订单时间" prop="orderTime">
              <el-date-picker
                v-model="formData.orderTime"
                value-format="yyyy-MM-dd"
                type="date"
                :style="{ width: '100%' }"
                placeholder="请选择订单时间"
                clearable
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="订单状态" prop="status">
              <el-select
                v-model="formData.status"
                placeholder="请选择订单状态"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in field107Options"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="领取方式" prop="method">
              <el-input
                v-model="formData.method"
                placeholder="请输入领取方式"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
            <el-form-item label="OBU号" prop="obu">
              <el-input
                v-model="formData.obu"
                placeholder="请输入OBU号"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>
  </div>
</template>

<script>
import {
  addCard,
  etcCardBox, //车牌号下拉
} from "@/api/etc/card";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        licensePlate: undefined,
        etcCardNumber: undefined,
        orderNumber: undefined,
        status: null,
        orderTime: undefined,
        method: undefined,
        obu: undefined,
        id: undefined,
      },
      rules: {
        id: [
          {
            required: false,
            message: "请选择下拉选择",
            trigger: "change",
          },
        ],
        etcCardNumber: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
        orderNumber: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
        orderTime: [
          {
            required: false,
            message: "请选择时间选择",
            trigger: "change",
          },
        ],
        status: [
          {
            required: false,
            message: "请选择下拉选择",
            trigger: "change",
          },
        ],
        method: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
        obu: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
      },
      field101Options: [
        {
          label: "选项一",
          value: 1,
        },
        {
          label: "选项二",
          value: 2,
        },
      ],
      field107Options: [
        {
          label: "已发货",
          value: 1,
        },
        {
          label: "未发货",
          value: 2,
        },
      ],
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    this.etcCardBoxData();
  },
  methods: {
    querySearch(queryString, cb) {
      var restaurants = this.field101Options;
      var results = queryString
        ? restaurants.filter(this.createFilter1(queryString))
        : restaurants;
      cb(results);
    },
    handleSelect(item) {
      console.log(item);
      // this.addform.licensePlate = item.licensePlate;
      // this.updateform.frameNumber = item.frameNumber;
      // this.updateform.id = item.id;
      this.formData.licensePlate = item.licensePlate;
    },
    createFilter1(queryString) {
      return (restaurant) => {
        return (
          restaurant.licensePlate
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    etcCardBoxData() {
      etcCardBox({}).then((res) => {
        this.field101Options = res.data;
      });
    },
    handleChange(val) {
      console.log(val, JSON.stringify(this.field101Options));
      for (const item of this.field101Options) {
        if (val == item.id) {
          console.log(item);

          console.log(item.licensePlate);
          this.formData.licensePlate = item.licensePlate;
        }
      }
    },
    submitForm() {
      console.log(this.formData);

      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        // this.saveLoading = true;
        addCard(this.formData).then((res) => {
          this.resetForm();
        });
        // TODO 提交表单
      });
    },
    resetForm() {
      //   this.$refs["elForm"].resetFields();
      this.$router.go(-1);
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
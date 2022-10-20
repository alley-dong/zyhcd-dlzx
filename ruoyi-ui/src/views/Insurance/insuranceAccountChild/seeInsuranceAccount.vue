<template>
  <div class="app-container">
    <div style="width: 100%; margin: 20px 10px 20px 0; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="editClick"
        v-hasPermi="['pfservercar:googds:goodsinfo']"
        >编辑</el-button
      >
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="backClick"
        >返回</el-button
      >
    </div>
    <div class="wrap">
      <div class="text">保险信息</div>
      <div class="line"></div>
    </div>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-col :span="12">
          <el-form-item label="车牌号" prop="licensePlate">
            <!-- <el-input
              v-model="formData.licensePlate"
              placeholder="请输入车牌号"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input> -->
            <div :style="{ width: '100%' }">
              {{ formData.licensePlate }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="保险类型" prop="insuranceTypeName">
            <!-- <el-input
              v-model="formData.insuranceTypeName"
              placeholder="请输入保险类型"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <div :style="{ width: '100%' }">
              {{ formData.type | type }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="起保日期" prop="insuranceStartDate">
            <!-- <el-date-picker
              v-model="formData.insuranceStartDate"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请输入起保日期"
              clearable
            >
            </el-date-picker> -->
            <div :style="{ width: '100%' }">
              <!-- parseTime(scope.row.insuranceStartDate, "{y}-{m}-{d} ")  -->
              {{ parseTime(formData.insuranceStartDate, "{y}-{m}-{d} ") }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="终保日期" prop="insuranceEndDate">
            <!-- <el-input
              v-model="formData.insuranceEndDate"
              placeholder="请输入终保日期"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <div :style="{ width: '100%' }">
              <!-- parseTime(scope.row.insuranceStartDate, "{y}-{m}-{d} ")  -->
              {{ parseTime(formData.insuranceEndDate, "{y}-{m}-{d} ") }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="保险办理日期" prop="insuranceHandleTime">
            <!-- <el-date-picker
              v-model="formData.insuranceHandleTime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请输入保险办理日期"
              clearable
            >
            </el-date-picker> -->
            <div :style="{ width: '100%' }">
              <!-- parseTime(scope.row.insuranceStartDate, "{y}-{m}-{d} ")  -->
              {{ parseTime(formData.insuranceHandleTime, "{y}-{m}-{d} ") }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="保险项目" prop="field110">
            <!-- <el-input
              v-model="formData.field110"
              placeholder="请输入保险项目"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-table
            :data="checkItemDataList"
            style="margin: 20px 0; margin-top: 0"
          >
            <el-table-column
              label="险种"
              align="center"
              prop="insuranceItemName"
            />
            <el-table-column
              label="险种类型"
              align="center"
              prop="insuranceTypeName"
            />
            <el-table-column
              label="保额(元)"
              align="center"
              prop="insuranceQuota"
            />
            <el-table-column label="单车保费(元)" align="center" prop="insuranceCost" />
          </el-table>
        </el-col>
        <el-col :span="24">
          <el-form-item label="附件" prop="field110">
            <!-- <el-upload
              class="upload-demo"
              :action="uploadAction"
              :http-request="UploadFileGroups"
              :file-list="formData.fileUrl"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">jpg; 请上传保单照片</div>
            </el-upload> -->
            <div :style="{ width: '100%' }" v-if="formData.fileList">
              <img
                v-for="(item, index) in formData.fileList"
                :key="index"
                :src="item"
                alt=""
                srcset=""
                style="width: 200px; height: 190px; margin-right: 50px"
              />
            </div>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col> -->
      </el-form>
    </el-row>
  </div>
</template>
<script>
import { googdsH16Info } from "@/api/system/vehicle";
export default {
  components: {},
  props: [],
  data() {
    return {
      checkItemDataList: [],
      uploadAction: "https://jsonplaceholder.typicode.com/posts/",
      formData: {
        licensePlate: undefined,
        insuranceTypeName: undefined,
        insuranceStartDate: null,
        insuranceEndDate: undefined,
        insuranceHandleTime: null,
        fileUrl: [],
        field110: undefined,
      },
      rules: {
        licensePlate: [],
        insuranceTypeName: [],
        insuranceStartDate: [],
        insuranceEndDate: [],
        insuranceHandleTime: [],
        field110: [],
      },
    };
  },
  filters: {
    type: function (value) {
      switch (value) {
        case 1:
          //  新车保险 2车辆续保 3货物保险
          return "新车保险";
          break;
        case 2:
          //  新车保险 2车辆续保 3货物保险
          return "车辆续保";
          break;
        case 3:
          //  新车保险 2车辆续保 3货物保险
          return "货物保险";
          break;
        default:
          break;
      }
    },
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    googdsH16Info(this.$route.params.id).then((res) => {
      this.formData = res.data;
      this.checkItemDataList = res.data.cInsuranceVehicleItemList;
      console.log(this.checkItemDataList);
    });
  },
  methods: {
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
      });
    },
    UploadFileGroups(param) {
      //   let fd = new FormData();
      //   let _this = this;
      //   console.log("param.file", param.file);
      //   fd.append("file", param.file); // 要提交给后台的文件
      //   UploadFiles(fd)
      //     .then((response) => {
      //       _this.formData.fileUrl.push(response.msg);
      //       _this.formData = JSON.parse(JSON.stringify(_this.formData));
      //     })
      //     .catch((err) => {
      //       console.log(err);
      //     });
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    },
    editClick() {
      this.$router.push({
        path: "/editInsuranceAccount/" + this.$route.params.id,
        query: { vehicleId: this.$route.query.vehicleId },
      });
    },
    backClick() {
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
  display: flex;
}
.text {
  width: 100px;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  //   float: left;
  font-weight: 400;
}
.line {
  border-bottom: 3px solid #333399;
  height: 50%;
  flex: 1;
}
</style>

<template>
  <div class="app-container">
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm">保存</el-button>
        <el-button type="primary" @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">新增</div>
      <div class="line"></div>
    </div>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="120px"
      >
        <el-col :span="12">
          <el-form-item label="合同名称" prop="name">
            <el-input
              v-model="formData.name"
              placeholder="请输入合同名称"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户名称" prop="carrierName">
            <el-input
              v-model="formData.carrierName"
              placeholder="请输入客户名称"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="合同单价" prop="unitPrice">
            <el-input
              type="number"
              v-model="formData.unitPrice"
              placeholder="请输入合同单价"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="付款方式" prop="paymentMethod">
            <el-input
              v-model="formData.paymentMethod"
              placeholder="请输入付款方式"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="客户合同类型" prop="contractType">
            <el-select
              v-model="formData.contractType"
              placeholder="请输入客户合同类型"
              clearable
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="(item, index) in contractTypeOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="合同开始日期" prop="contractStartTime">
            <!-- <el-input
              v-model="formData.contractStartTime"
              placeholder="请输入合同开始日期"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <el-date-picker
              v-model="formData.contractStartTime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请选择合同开始日期"
              clearable
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="合同结束日期" prop="contractEndTiem">
            <!-- <el-input
              v-model="formData.contractEndTiem"
              placeholder="请输入合同结束日期"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <el-date-picker
              v-model="formData.contractEndTiem"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请选择合同结束日期"
              clearable
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="备注" prop="remarks">
            <el-input
              v-model="formData.remarks"
              type="textarea"
              placeholder="请输入备注"
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <!-- <el-form
            ref="form"
            :model="form"
            label-width="100px"
            style="float: left"
          > -->
          <el-form-item label="导入文件：">
            <!-- </el-form> -->
            <el-upload
              style="float: left; margin-left: 10px"
              drag
              ref="upload"
              class="upload-btn"
              action="string"
              accept=""
              :before-upload="onBeforeUploadImage"
              :http-request="UploadFile"
              :before-remove="handleRemove"
              multiple
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或
                <em>点击上传</em>
              </div>
              <!-- <div class="el-upload__tip" slot="tip">
              <a href="./template.xlsx" download="下载模板">下载模板</a>
            </div> -->
            </el-upload>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
// import { minioChooseBucketUpload } from "@/api/com";
import { CustomerContractAdd, CarriersList } from "@/api/partner";
import { UploadFiles } from "@/api/upLoad/persomUpload";

export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        name: undefined,
        carrierName: undefined,
        unitPrice: undefined,
        paymentMethod: undefined,
        contractType: "",
        contractStartTime: undefined,
        contractEndTiem: undefined,
        remarks: undefined,
        fileUrls: [],
      },
      rules: {
        name: [],
        carrierName: [],
        unitPrice: [],
        paymentMethod: [],
        contractType: [],
        contractStartTime: [],
        contractEndTiem: [],
        field108: [],
      },
      contractTypeOptions: [
        {
          label: "内部合同",
          value: 1,
        },
        {
          label: "外部合同",
          value: 2,
        },
      ],
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    // CarriersList({}).then((res) => {
    //   this.contractTypeOptions = res.rows;
    // });
  },
  methods: {
    handleChange(val) {
      for (const item of this.contractTypeOptions) {
        console.log(item, val);
        if (item.id == val) {
          this.formData.carrierName = item.name;
        }
      }
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    handleRemove(file, fileList) {
      console.log(this.formData.fileUrls);
      for (const [index, item] of this.formData.fileUrls.entries()) {
        if (item.name == file.name) {
          console.log(index);
          this.formData.fileUrls.splice(index, 1);
        }
      }

      console.log(file, fileList);
    },
    submitForm() {
      if (this.formData.fileUrls.map) {
        this.formData.fileUrls = this.formData.fileUrls.map((item) => item.src);
        // .join();
      }
      CustomerContractAdd(this.formData).then((res) => {
        // console.log(JSON.stringify(this.formData));
        this.$router.go(-1);
      });
    },
    resetForm() {
      this.$router.go(-1);
    },
    UploadFile(param) {
      let fd = new FormData();
      console.log("param.file", param);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          console.log("提交文件", response.msg);
          param.file.src = response.msg;
          this.formData.fileUrls.push(param.file);
          this.$message({
            message: "导入成功",
            type: "success",
          });
          this.dialogVisible1 = false;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style lang='scss' scoped>
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
.wrap {
  width: 100%;
  height: 40px;
  display: flex;
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
  // position: relative !important;
  border-bottom: 3px solid #333399;
  // height: 50%;
}
</style>

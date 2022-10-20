<template>
  <div class="app-container">
    <div style="width: 100%; margin: 20px 10px 20px 0; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="editClick"
        >提交</el-button
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
            <div>{{formData.licensePlate}}</div>
            <!-- <el-input
              v-model=""
              placeholder="请输入车牌号"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="保险类型" prop="insuranceTypeName">
            <div>{{formData.insuranceTypeName}}</div>
            <!-- <el-input
              v-model="formData.insuranceTypeName"
              placeholder="请输入保险类型"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="起保日期" prop="insuranceStartDate">
            <el-date-picker
              v-model="formData.insuranceStartDate"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请输入起保日期"
              clearable
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="终保日期" prop="insuranceEndDate">
            <el-input
              v-model="formData.insuranceEndDate"
              placeholder="请输入终保日期"
              clearable
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="保险办理日期" prop="insuranceHandleTime">
            <el-date-picker
              v-model="formData.insuranceHandleTime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请输入保险办理日期"
              clearable
            >
            </el-date-picker>
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
              label="保额"
              align="center"
              prop="insuranceQuota"
            />
            <el-table-column label="保费" align="center" prop="insuranceCost" />
          </el-table>
        </el-col>
        <el-col :span="24">
          <el-form-item label="附件" prop="field110">
            <el-upload
              class="upload-demo"
              :action="uploadAction"
              :http-request="UploadFileGroups"
              :file-list="fileUrls"
              :before-upload="beforeAvatarUpload"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">jpg; 请上传保单照片</div>
            </el-upload>
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
import { googdsE16List,E16SaveEdit } from "@/api/system/vehicle";
import { UploadFiles } from "@/api/upLoad/persomUpload";
export default {
  components: {},
  props: [],
  data() {
    return {
      fileUrls:[],
      checkItemDataList: [],
      uploadAction: "https://jsonplaceholder.typicode.com/posts/",
      formData: {
        licensePlate: undefined,
        insuranceTypeName: undefined,
        insuranceStartDate: null,
        insuranceEndDate: undefined,
        insuranceHandleTime: null,
        fileUrls: [],
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
  computed: {},
  watch: {},
  created() {},
  mounted() {
    googdsE16List({ id: this.$route.params.id }).then((res) => {
      this.formData = res.rows[0];
      this.formData.fileUrls = res.rows[0].fileUrls ? res.rows[0].fileUrls : [];
      this.checkItemDataList = res.rows[0].cInsuranceVehicleItemList;
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
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    UploadFileGroups(param) {
      let fd = new FormData();
      let _this = this;
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          console.log(response);

          _this.fileUrls.push(response.msg);
          // _this.formData = JSON.parse(JSON.stringify(_this.formData));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    },
    editClick() {
      this.formData.fileUrls = this.fileUrls.join()
      this.formData.id = this.$route.params.id

      console.log(this.formData);
      E16SaveEdit(this.formData).then().then(this.backClick())
      // ;
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

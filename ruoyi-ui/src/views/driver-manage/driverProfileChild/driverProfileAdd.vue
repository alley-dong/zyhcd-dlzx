<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm" :loading="saveLoading"
          >保存</el-button
        >
        <el-button type="primary" @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">驾驶员基本信息</div>
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
            <el-form-item label="*姓名">
              <el-input
                v-model="formData.name"
                placeholder="请输入姓名"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!-- <el-form-item label="性别" prop="sex"> -->
            <el-form-item label="*性别">
              <!-- <el-input
                v-model="formData.sex"
                placeholder="请输入性别"
                clearable
                :style="{ width: '100%' }"
              > -->
              <!-- -->
              <el-select
                v-model="formData.sex"
                placeholder="请选择性别"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in field101Options"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
              <!-- </el-input> -->
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生日期">
              <!-- <el-input
                v-model="formData.birthday"
                placeholder="请输入出生日期"
                clearable
                :style="{ width: '100%' }"
              > -->
              <el-date-picker
                @change="formData.birthday = dataChange(formData.birthday)"
                v-model="formData.birthday"
                type="date"
                placeholder="请输入出生日期"
                :style="{ width: '100%' }"
                clearable
              ></el-date-picker>
              <!-- </el-input> -->
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="*身份证号">
              <el-input
                v-model="formData.idCard"
                placeholder="请输入身份证号"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="民族">
              <el-input
                v-model="formData.nation"
                placeholder="请输入民族"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="政治面貌">
              <el-input
                v-model="formData.politicalAffiliation"
                placeholder="请输入政治面貌"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="婚姻状况">
              <el-input
                v-model="formData.maritalStatus"
                placeholder="请输入婚姻状况"
                clearable
                :style="{ width: '100%' }"
              >
                <!--<el-select v-model="formData.field101" placeholder="请选择下拉选择" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in field101Options" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select> -->
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学历">
              <el-input
                v-model="formData.education"
                placeholder="请输入学历"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="*联系方式">
              <!-- <el-form-item label="联系方式" prop="contact"> -->
              <el-input
                v-model="formData.contact"
                placeholder="请输入联系方式"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="紧急联系人电话">
              <el-input
                v-model="formData.urgentTel"
                placeholder="请输入紧急联系人电话"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="驾照类型">
              <el-input
                v-model="formData.drivingLicenseType"
                placeholder="请输入驾照类型"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="获取时间">
              <!-- <el-input
                v-model="formData.drivingLicenseTime"
                placeholder="请输入获取时间"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input> -->

              <el-date-picker
                @change="
                  formData.drivingLicenseTime = dataChange(
                    formData.drivingLicenseTime
                  )
                "
                v-model="formData.drivingLicenseTime"
                type="date"
                placeholder="请选择获取时间"
                :style="{ width: '100%' }"
                clearable
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="驾龄">
              <el-input
                v-model="formData.driveAge"
                placeholder="请输入驾龄"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="入职时间">
              <!-- <el-input
                v-model="formData.dntryTime"
                placeholder="请输入入职时间"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input> -->
              <el-date-picker
                v-model="formData.dntryTime"
                @change="formData.dntryTime = dataChange(formData.dntryTime)"
                type="date"
                placeholder="请输入入职时间"
                :style="{ width: '100%' }"
                clearable
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="从业资格证号">
              <el-input
                v-model="formData.qualification"
                placeholder="请输入从业资格证号"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="家庭地址">
              <el-input
                v-model="formData.address"
                type="textarea"
                placeholder="请输入家庭地址"
                :autosize="{ minRows: 4, maxRows: 4 }"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="驾驶员类型">
              <!-- <el-input
                v-model="formData.driverTypeId"
                placeholder="请输入驾驶员类型"
                clearable
                :style="{ width: '100%' }"
              >TODO:
              </el-input> -->
              <el-select
                v-model="formData.driverTypeId"
                placeholder="请选择驾驶员类型"
                clearable
                @change="handClick"
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in driverTypeList"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="员工类型">
              <!-- <el-input
                v-model="formData.staffType"
                placeholder="请输入员工类型"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input> -->
              <el-select
                v-model="formData.staffType"
                placeholder="请选择员工类型"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in employeeOptions"
                  :key="index"
                  :label="item.label"
                  :value="item.value"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
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

      <div style="flex: 1; margin: 50px">
        <el-upload
          class="avatar-uploader"
          :action="uploadAction"
          :show-file-list="false"
          :http-request="UploadFile"
        >
          <img
            v-if="formData.driverPhoto"
            :src="formData.driverPhoto"
            class="avatar"
          />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <div
          v-if="!formData.driverPhoto"
          style="line-height: 50px; width: 178px; text-align: center"
        >
          上传照片
        </div>
      </div>
    </div>
    <div class="wrap">
      <div class="text">上传附件</div>
      <div class="line"></div>
    </div>
    <el-upload
      ref="upload"
      :action="uploadAction"
      :http-request="UploadFileGroups"
      :file-list="fileUrl"
      :before-remove="handleRemove"
      style="margin: 15px 0 50px 37px"
    >
      <el-button size="small" type="primary" icon="el-icon-upload"
        >点击上传</el-button
      >
      <span style="color: #82848a; margin-left: 20px"
        >请上传身份证复印件、驾驶证复印件等其他相关材料</span
      >
    </el-upload>
    <div class="enclosure">
      <img src="" alt="" style="margin-right: 50px" />
      <img src="" alt="" />
    </div>
  </div>
</template>

<script>
import {
  A10DriverBox, //驾驶员类型下拉选
} from "@/api/driver/driverType";
import {
  A11Info,
  driverA24Add,
  A10Enclosure,
} from "@/api/driver/interviewManage";
import { UploadFiles } from "@/api/upLoad/persomUpload";
export default {
  data() {
    return {
      dialogImageUrl: "",
      // dialogVisible: false,
      driverTypeList: [],
      saveLoading: false,
      formData: {
        performanceType: "",
        name: "",
        sex: "",
        birthday: "",
        idCard: "",
        nation: "",
        politicalAffiliation: "",
        maritalStatus: "",
        education: "",
        contact: "",
        urgentTel: "",
        drivingLicenseType: "",
        drivingLicenseTime: "",
        driveAge: "",
        dntryTime: "",
        address: undefined,
        driverTypeId: undefined,
        driverPhoto: null,
        staffType: undefined,
        qualification: undefined,
        upload: null,
        fileUrl: [],
      },
      fileUrl: [],
      uploadAction: "https://jsonplaceholder.typicode.com/posts/",
      uploadfileList: [],
      field101Options: [
        {
          label: "男",
          value: 0,
        },
        {
          label: "女",
          value: 1,
        },
      ],
      employeeOptions: [
        {
          label: "正式工",
          value: 1,
        },
        {
          label: "外包工",
          value: 2,
        },
      ],
      rules: {
        name: [
          {
            required: true,
            message: "请输入姓名",
            trigger: "blur",
          },
        ],
        sex: [
          {
            required: true,
            message: "请输入性别",
            trigger: "blur",
          },
        ],
        birthday: [
          {
            required: true,
            message: "请输入出生日期",
            trigger: "blur",
          },
        ],
        idCard: [
          {
            required: true,
            message: "请输入身份证号",
            trigger: "blur",
          },
        ],
        nation: [
          {
            required: true,
            message: "请输入民族",
            trigger: "blur",
          },
        ],
        politicalAffiliation: [
          {
            required: true,
            message: "请输入政治面貌",
            trigger: "blur",
          },
        ],
        maritalStatus: [
          {
            required: true,
            message: "请输入婚姻状况",
            trigger: "blur",
          },
        ],
        education: [
          {
            required: true,
            message: "请输入学历",
            trigger: "blur",
          },
        ],
        contact: [
          {
            required: true,
            message: "请输入联系方式",
            trigger: "blur",
          },
        ],
        urgentTel: [
          {
            required: true,
            message: "请输入紧急联系人电话",
            trigger: "blur",
          },
        ],
        drivingLicenseType: [
          {
            required: true,
            message: "请输入驾照类型",
            trigger: "blur",
          },
        ],
        drivingLicenseTime: [
          {
            required: true,
            message: "请输入获取时间",
            trigger: "blur",
          },
        ],
        driveAge: [
          {
            required: true,
            message: "请输入驾龄",
            trigger: "blur",
          },
        ],
        dntryTime: [
          {
            required: true,
            message: "请输入入职时间",
            trigger: "blur",
          },
        ],
        address: [
          {
            required: true,
            message: "请输入家庭地址",
            trigger: "blur",
          },
        ],
        driverTypeId: [
          {
            required: true,
            message: "请输入驾驶员类型",
            trigger: "blur",
          },
        ],
        staffType: [
          {
            required: true,
            message: "请输入员工类型",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    // 接口初始化
    init() {
      this.A10DriverBoxData();
    },
    // 头像
    UploadFile(param) {
      let fd = new FormData();
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          // console.log(response.msg.substring(0,str.lastIndexOf('.')));
          this.formData.driverPhoto = response.msg;

          this.formData = JSON.parse(JSON.stringify(this.formData));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // // 基本信息
    A11InfoData() {
      A11Info(this.$route.params.id).then((res) => {
        this.formData = res.data;
      });
    },
    // 日期转换
    dataChange(val) {
      const d = new Date(val);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
    // 驾驶员下拉
    A10DriverBoxData() {
      let obj = {
        type: null,
      };
      A10DriverBox(obj).then((res) => {
        console.log(res);
        this.driverTypeList = res.data;
      });
    },
    handClick(val) {
      console.log(val);
      for (let item of this.driverTypeList) {
        if (val == item.id) {
          this.formData.performanceType = item.performanceType;
          // console.log(this.formData.performanceType);
        }
      }
    },
    // 上传图片
    // handleRemove(file, fileList) {
    //   console.log(file, fileList);
    // },
    // 上传图片
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    // 文件删除
    handleRemove(file, fileList) {
      console.log(this.fileUrl);
      for (const [index, item] of this.fileUrl.entries()) {
        if (item.name == file.name) {
          console.log(index);
          this.fileUrl.splice(index, 1);
        }
      }

      console.log(file, fileList);
    },
    UploadFileGroups(param) {
      let fd = new FormData();
      let _this = this;
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          // let index = response.msg.lastIndexOf(".");
          param.file.src = response.msg;
          _this.fileUrl.push(param.file);
          // _this.formData = JSON.parse(JSON.stringify(_this.formData));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    uploadBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2;
      if (!isRightSize) {
        this.$message.error("文件大小超过 2MB");
      }
      return isRightSize;
    },
    // 提交
    submitForm() {
      for (const item of this.fileUrl) {
        this.formData.fileUrl.push(item.src);
      }

      // A10Enclosure(this.formData);
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        this.saveLoading = true;
        // setTimeout(() => {
        //   this.saveLoading = false;
        // }, 2000);
        driverA24Add(this.formData)
          .then((res) => {
            this.saveLoading = false;
            // this.$router.replace{path:'driverProfile' }
            // this.$router.push({
            //   path: `/driverProfile`,
            //   redirect: "/driverProfile",
            // });
            this.$router.go(-1);
          })
          .catch((err) => {
            this.saveLoading = false;
          });
        // TODO 提交表单
      });
    },
    resetForm() {
      // this.$refs["elForm"].resetFields();
      this.$router.go(-1);
      // this.$router.push({
      //   path: `/driverProfile`,
      // });
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
.enclosure {
  display: flex;
  margin: 20px 60px;
  img {
    flex: 1;
    height: 300px;
  }
}
</style>
<style lang="scss">
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<template>
  <div class="app-container">
    <!-- 编辑 -->
    <div style="width: 100%; margin: 20px 10px 20px 0; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="editClick"
        >保存</el-button
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
              {{ formData.insuranceTypeName }}
            </div>
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
            <!-- <el-input
              v-model="formData.insuranceEndDate"
              placeholder="请输入终保日期"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <el-date-picker
              v-model="formData.insuranceEndDate"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请输入起保日期"
              clearable
            >
            </el-date-picker>
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
              :on-remove="handleRemove"
              :file-list="formData.fileUrl"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">jpg; 请上传保单照片</div>
            </el-upload>
            <!-- <div :style="{ width: '100%' }" v-if="formData.fileUrls">
              <img
                v-for="(item, index) in formData.fileUrls"
                :key="index"
                :src="item"
                alt=""
                srcset=""
                style="width: 200px; height: 190px; margin-right: 50px"
              />
            </div> -->
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
import { googdsH16Info, googdsH16EditSave } from "@/api/system/vehicle";
import { UploadFiles } from "@/api/upLoad/persomUpload";
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
  computed: {},
  watch: {},
  created() {},
  mounted() {
    googdsH16Info(this.$route.params.id).then((res) => {
      if (!res.data.fileList) {
        res.data.fileUrl = [];
      } else {
        let arr = []
        for (const [index,item] of res.data.fileList.entries()) {
          console.log();
          let name = item.substring(item.lastIndexOf('/')+1)
          arr.push({url:item,name,index})
        }
        res.data.fileUrl = arr
      }
      this.formData = res.data;
      this.checkItemDataList = res.data.cInsuranceVehicleItemList;
      console.log(this.checkItemDataList);
    });
  },
  methods: {
    handleRemove(file, fileList){
      console.log(file.index);
this.formData.fileUrl.splice(file.index,1)
for (const [index,item] of this.formData.fileUrl.entries()) {
  item.index = index
}
    },
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
      });
    },
    UploadFileGroups(param) {
      let fd = new FormData();
      let _this = this;
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          let name = response.msg.substring(response.msg.lastIndexOf('/')+2)
          _this.formData.fileUrl.push({url:response.msg,name});
          console.log(response.msg);
          _this.formData = JSON.parse(JSON.stringify(_this.formData));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    },
    editClick() {
        console.log(this.formData.fileUrl);
        let arr = []
        for (const item of this.formData.fileUrl) {
          arr.push(item.url)
        }
        this.formData.fileList=arr
        // this.formData.fileUrl = 
        // this.formData.list = arr
        delete this.formData.fileUrl
        this.formData.vehicleId = this.$route.query.vehicleId
      googdsH16EditSave(this.formData).then((res) => {
          this.backClick()
      });
      //   this.$router.push({ path: "/detailInsuranceAccount/" + this.$route.params.id });
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

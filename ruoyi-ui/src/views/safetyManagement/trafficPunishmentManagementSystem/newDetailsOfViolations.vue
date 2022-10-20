<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button @click="resetback">返回</el-button>
        <el-button
          type="primary"
          @click="submitForm('formData')"
          v-hasPermi="['person:safeTrafficTransgression:add']"
          >保存</el-button
        >
      </div>
    </div>
    <div class="wrap">
      <div class="text">基本信息</div>
      <div class="line"></div>
    </div>

    <el-row :gutter="15">
      <el-form
        ref="formData"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="150px"
      >
        <el-col :span="20">
          <el-form-item label="违法车辆" prop="licensePlate">
            <el-select
              v-model="formData.licensePlate"
              placeholder="请选择违法车辆"
              @change="addquotaRouterBoxData"
              clearable
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="(item, index) in field104Options"
                :key="index"
                :label="item.licensePlate"
                :value="item.licensePlate"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="违规地点" prop="wzdd">
            <el-input
              v-model="formData.wzdd"
              placeholder="请输入违规地点"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="违法时间" prop="wzsj">
            <el-date-picker
              v-model="formData.wzsj"
              type="datetime"
              format="yyyy-MM-dd HH:mm"
              value-format="yyyy-MM-dd HH:mm"
              :style="{ width: '100%' }"
              placeholder="请选择违法时间"
              clearable
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="违法行为" prop="wzxw">
            <el-input
              v-model="formData.wzxw"
              placeholder="请输入违法行为"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="处罚" prop="wzfk">
            <el-input
              v-model="formData.wzfk"
              placeholder="请输入处罚"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="违法照片" prop="field110">
            <el-upload
              ref="field110"
              :file-list="field110fileList"
              :action="field110Action"
              :before-upload="field110BeforeUpload"
              :http-request="UploadFileGroups"
              :on-remove="handleRemove"
              list-type="picture"
            >
              <el-button size="small" type="primary" icon="el-icon-upload"
                >点击上传</el-button
              >
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="选择驾驶员：" prop="driverId">
            <el-select
              v-model="formData.driverId"
              placeholder="请选择选择驾驶员："
              @change="handleChange"
              clearable
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="(item, index) in personList"
                :key="index"
                :label="item.name"
                :value="item.id"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="20">
          <el-form-item label="处理违章截止时间：" prop="deadline">
            <el-date-picker
              v-model="formData.deadline"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              :style="{ width: '100%' }"
              placeholder="请选择处理违章截止时间："
              clearable
            ></el-date-picker>
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
import {
  J02CarNumList,
  L06Add,
  L06DriverList
} from "@/api/safetyManagement/newDetailsOfViolations";
import { UploadFiles } from "@/api/upLoad/persomUpload";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        vehicleId: "", //车辆id
        licensePlate: "", //车牌号
        wzdd: "", //违章地点
        wzsj: "", //违章时间
        wzxw: "", //违章行为
        wzfk: "", //违章处罚
        driverId: "",
        driverName: "",
        deadline: "",
        photoUrls: []
      },

      rules: {
        licensePlate: [
          {
            required: true,
            message: "请选择违法车辆",
            trigger: "change"
          }
        ],
        wzdd: [
          {
            required: true,
            message: "请输入违规地点",
            trigger: "blur"
          }
        ],
        wzsj: [
          {
            required: true,
            message: "请选择违法时间",
            trigger: "change"
          }
        ],
        wzxw: [
          {
            required: true,
            message: "请输入违法行为",
            trigger: "blur"
          }
        ],
        wzfk: [
          {
            required: true,
            message: "请输入处罚",
            trigger: "blur"
          }
        ],
        driverId: [
          {
            required: true,
            message: "请选择选择驾驶员",
            trigger: "blur"
          }
        ],
        deadline: [
          {
            required: true,
            message: "请选择处理违章截止时间",
            trigger: "blur"
          }
        ],
        field111: [],
        field112: []
      },
      field110Action: "https://jsonplaceholder.typicode.com/posts/",
      field110fileList: [],
      field104Options: [],
      // 驾驶员下拉
      personList: []
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getL06DriverList();
  },
  mounted() {
    this.getJ02CarNumList();
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, this.field110fileList);
      for (const [index, item] of this.field110fileList.entries()) {
        if (item.url == file.url) {
          this.field110fileList.splice(index,1)
        }
      }
    },
    UploadFileGroups(param) {
      let fd = new FormData();
      let _this = this;
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then(response => {
          // param.file.src = response.msg;
          let name = response.msg.substring(response.msg.lastIndexOf('/')+1)
          _this.field110fileList.push({url:response.msg,name});
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleChange(val) {
      for (const item of this.personList) {
        if (item.id == val) {
          this.formData.driverName = item.name;
        }
      }
    },
    //违章管理-车牌号下拉选
    getJ02CarNumList() {
      J02CarNumList().then(res => {
        this.field104Options = res.data;
        // this.formData.licensePlate=res.data[0].licensePlate
        // this.formData.vehicleId=res.data[0].id
      });
    },
    addquotaRouterBoxData(val) {
      for (const item of this.field104Options) {
        if (val == item.licensePlate) {
          this.formData.vehicleId = item.id;
        }
      }
    },
    //违章管理-通过的驾驶员下拉选
    getL06DriverList() {
      L06DriverList({}).then(res => {
        this.personList = res.data;
      });
    },
    submitForm(formData) {
      this.$refs["formData"].validate(valid => {
        if (valid) {
          let arr = []
          for (const item of this.field110fileList) {
            arr.push(item.url)
          }
          this.formData.photoUrls = arr
          console.log(this.formData);
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
        }
      });
    },

    resetback() {
      this.$router.back();
    },
    field110BeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2;
      if (!isRightSize) {
        this.$message.error("文件大小超过 2MB");
      }
      return isRightSize;
    }
  }
};
</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}
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
.btn-group {
  display: inline-block;
}
</style>

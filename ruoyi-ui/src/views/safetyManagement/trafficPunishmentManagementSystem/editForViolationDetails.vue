<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button
          type="primary"
          @click="submitForm"
          v-hasPermi="['person:safeTrafficTransgression:edit']"
          >保存</el-button
        >
        <el-button @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">事故信息</div>
      <div class="line"></div>
    </div>

    <div style="margin-top:20px;">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="100px"
        >
          <el-col :span="16">
            <el-form-item label="违法车辆" prop="licensePlate">
              <el-input
                v-model="formData.licensePlate"
                show-word-limit
                clearable
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
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="违法时间" prop="wzsj">
              <el-date-picker
                v-model="formData.wzsj"
                format="yyyy-MM-dd HH:mm"
                type="datetime"
                value-format="yyyy-MM-dd HH:mm"
                :style="{ width: '100%' }"
                placeholder="请选择违法时间"
                clearable
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="违法行为" prop="wzxw">
              <el-input
                v-model="formData.wzxw"
                show-word-limit
                clearable
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
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="违法照片" prop="accidentTime">
              <!-- <el-col :span="6" v-for="o in formData.fileList" :key="o">
                <el-card v-if="o.filePath" :body-style="{ padding: '0px' }">
                  <el-image :src="o.filePath" :preview-src-list="srcList"> </el-image>
                </el-card>
              </el-col> -->
              <el-upload
                ref="field110"
                :file-list="field110fileList"
                :action="field110Action"
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
          <el-col :span="16">
            <el-form-item label="选择驾驶员：" prop="driverName">
              <el-select
                v-model="formData.driverName"
                placeholder="请选择选择驾驶员："
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in personList"
                  :key="index"
                  :label="item.name"
                  :value="item.name"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="处理违章截止时间" prop="deadline">
              <el-date-picker
                @change="formData.deadline = dataTime(formData.deadline)"
                v-model="formData.deadline"
                type="date"
                :style="{ width: '100%' }"
                placeholder="请输入处理违章截止时间"
              >
              </el-date-picker>

            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>
  </div>
</template>

<script>
import { L05GetInfo } from "@/api/safetyManagement/violationOfTheDetails";
import {
  L06DriverList,
  L09Edit
} from "@/api/safetyManagement/newDetailsOfViolations";
import { UploadFiles } from "@/api/upLoad/persomUpload";
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
        licensePlate: "",
        wzdd: "",
        wzsj: null,
        wzxw: "",
        wzfk: "",
        driverName: "",
        deadline: "",
        id: "",
        driverId: ""
      },
      // 驾驶员下拉
      personList: [],
      rules: {},
      cardList: [],
      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      srcList: [
        "https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg",
        "https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg"
      ],
      field110Action: "https://jsonplaceholder.typicode.com/posts/",
      field110fileList: []
    };
  },
  computed: {},
  watch: {},
  created() {
    // this.etcCardBoxData();
    this.getL05GetInfo();
    this.getL06DriverList();
  },
  mounted() {},
  methods: {
    handleRemove(file, fileList) {
      console.log(file, this.field110fileList);
      for (const [index, item] of this.field110fileList.entries()) {
        if (item.url == file.url) {
          this.field110fileList.splice(index, 1);
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
          let name = response.msg.substring(response.msg.lastIndexOf("/") + 1);
          _this.field110fileList.push({ url: response.msg, name });
        })
        .catch(err => {
          console.log(err);
        });
    },
    dataTime(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
    //数据回显
    getL05GetInfo() {
      L05GetInfo(this.$route.params.id).then(res => {
        this.formData = res.data;
        let arr = []
        for (const item of res.data.fileList) {
          let name = item.filePath.substring(item.filePath.lastIndexOf('/')+1)
          arr.push({url:item.filePath,name})
        }
        this.field110fileList = arr
        // field110fileList
      });
    },
    //违章管理-通过的驾驶员下拉选
    getL06DriverList() {
      L06DriverList({}).then(res => {
        this.personList = res.data;
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
        let arr = []
          for (const item of this.field110fileList) {
            arr.push(item.url)
          }
          this.formData.photoUrls = arr
      console.log("照片列表",this.field110fileList);
      L09Edit(this.formData).then((res) => {
        this.$message({
          message: "编辑成功",
          type: "success"
        });
        this.$router.go(-1);
      });
      // if(this.check.status === null){
      //   this.$message({
      //   message: '请选择审核是否通过',
      //   type: 'warning'
      // })
      // }else{
      //    L02SafeAccidentCheck(this.formData).then((res) => {
      //   this.resetForm();
      // });
      // }
      // L02SafeAccidentCheck(this.formData).then((res) => {
      //   this.resetForm();
      // });
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

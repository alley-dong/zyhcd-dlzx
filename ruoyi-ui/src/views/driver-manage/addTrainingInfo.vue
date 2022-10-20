<template>
  <div>
    <div style="width: 98%; margin: 20px 10px 0 0; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="saveClick"
        :loading="saveLoading"
        >保存</el-button
      >
      <!-- <router-link :to="{ path: '/trainingManage', name: 'trainingManage' }"> -->
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="backClick"
        >返回</el-button
      >
      <!-- localStorage.removeItem("selectTrainersIds") -->
      <!-- </router-link> -->
    </div>
    <div class="wrap">
      <div class="text">培训信息</div>
      <div class="line"></div>
    </div>
    <el-form
      ref="form"
      :model="form"
      label-width="160px"
      style="width: 90%; margin: 0 auto; margin-top: 20px"
    >
      <div style="overflow: hidden">
        <el-form-item
          label="标题:"
          prop="title"
          :rules="[{ required: false, message: '标题为必填项' }]"
        >
          <el-input v-model="form.title" placeholder="请输入标题"></el-input>
        </el-form-item>
      </div>
      <div style="overflow: hidden">
        <el-form-item
          label="设置阅读时长（分钟）:"
          prop="minDuration"
          style="width: 50%; float: left"
        >
          <el-input
            v-model="form.minDuration"
            type="number"
            placeholder="请输入阅读时长"
            oninput="value=value.replace(/[^0-9]/g,'')"
            >分钟</el-input
          >
        </el-form-item>
        <el-form-item
          label="培训截止时间："
          prop="trainingEndTime"
          style="width: 50%; float: left"
          :rules="[{ required: false, message: '培训截止时间为必填项' }]"
        >
          <el-date-picker
            type="date"
            placeholder="请选择培训截止时间"
            @change="form.trainingEndTime = TimeChange(form.trainingEndTime)"
            v-model="form.trainingEndTime"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
      </div>
      <div style="overflow: hidden">
        <el-form-item
          label="培训要求:"
          prop="education"
          style="width: 50%"
          :rules="[{ required: false, message: '培训要求为必填项' }]"
        >
          <el-input
            v-model="form.education"
            placeholder="请选择培训要求"
          ></el-input>
        </el-form-item>
      </div>
      <div style="overflow: hidden">
        <el-form-item
          label="培训参与人员:"
          style="float: left"
          :rules="[{ required: false, message: '培训参与人员为必填项' }]"
        >
          <div :style="{ width: '100%', display: 'flex', flexWrap: 'wrap' }">
            <div v-for="(item, index) in checkUsers" :key="index">
              <el-tag v-if="item" style="margin-right: 20px">{{ item }}</el-tag>
            </div>
          </div>
          <div :style="{ width: '100%', display: 'flex', flexWrap: 'wrap' }">
            <div v-for="(item, index) in checkGroups" :key="index">
              <el-tag v-if="item" style="margin-right: 20px">{{ item }}</el-tag>
            </div>
          </div>
        </el-form-item>


        <el-button
          type="primary"
          @click="selectGroupClick"
          style="float: right; margin-right: 10px"
          >选择群组</el-button
        >
        <el-button
          type="primary"
          @click="selectTrainersClick"
          style="float: right; margin-right: 10px"
          >选择培训参与人员</el-button
        >
      </div>
      <div style="overflow: hidden">
        <el-form-item label="上传视频:" style="float: left"></el-form-item>
        <el-upload
          class="avatar-uploader"
          style="float: left"
          action="http://115.29.205.180:8089/pfserver-person/personFile/minioChooseBucketUpload"
          v-bind:data="{ FoldPath: '上传目录', SecretKey: '安全验证' }"
          v-bind:on-progress="uploadVideoProcess"
          v-bind:on-success="handleVideoSuccess"
          v-bind:before-upload="beforeUploadVideo"
          :http-request="UploadFileGroups"
          v-bind:show-file-list="false"
        >
          <video
            v-if="videoForm.showVideoPath != '' && !videoFlag"
            v-bind:src="videoForm.showVideoPath"
            class="avatar video-avatar"
            controls="controls"
          >
            您的浏览器不支持视频播放
          </video>
          <i
            v-else-if="videoForm.showVideoPath == '' && !videoFlag"
            class="el-icon-plus avatar-uploader-icon"
          ></i>
          <el-progress
            v-if="videoFlag == true"
            type="circle"
            v-bind:percentage="videoUploadPercent"
            style="margin-top: 7px"
          ></el-progress>
        </el-upload>
      </div>
      <div>
        <el-form-item
          label="培训内容:"
          :rules="[{ required: false, message: '培训内容为必填项' }]"
        >
          <editor v-model="form.content" disabled :min-height="192" />
        </el-form-item>
      </div>
      <!-- <div>
        <el-form-item label="上传附件:">
          <el-upload
            style="float: left; margin-left: 10px"
            drag
            ref="upload"
            class="upload-btn"
            action="string"
            :file-list="fileList"
            :http-request="UploadFile1"
            :before-remove="handleRemove"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">
              <a href="./template.xlsx" download="下载模板">下载模板</a>
            </div>
          </el-upload>
        </el-form-item>
      </div> -->
    </el-form>
    <el-dialog :visible.sync="open" width="780px" append-to-body>
      <el-table :data="noticeList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="群组名称" align="center" prop="name" />
      </el-table>
      <div
        style="
          display: flex;
          align-items: center;
          justify-content: center;
          margin-top: 32px;
        "
      >
        <el-button type="primary" @click="saveDialogClick">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Editor from "@/components/Editor";
import { selectGroupList, training } from "@/api/driver/noticeManage";
import { UploadFiles } from "@/api/upLoad/persomUpload";

export default {
  name: "addTrainingInfo",
  components: {
    Editor,
  },
  data() {
    return {
      checkUsers:[],
      checkGroups:[],
      saveLoading: false,
      form: {
        title: "",
        minDuration: "",
        trainingEndTime: "",
        education: "",
        content: "",
        groupId: [],
      },
      fileList: [],
      state: false,
      // 选中数组
      ids: [],
      names: [],
      // 是否显示弹出层
      open: false,
      // 公告表格数据
      noticeList: [],
      // 总条数
      total: 0,
      videoFlag: false,
      //是否显示进度条
      videoUploadPercent: "",
      //进度条的进度，
      isShowUploadVideo: false,
      //显示上传按钮
      videoForm: {
        showVideoPath: "",
      },
    };
  },

  mounted() {
    if (localStorage.getItem("formTrainingInfo")) {
      this.form = JSON.parse(localStorage.getItem("formTrainingInfo"));
    }
    if (localStorage.getItem("videoPath")) {
      this.videoForm.showVideoPath = localStorage.getItem("videoPath");
    }

    if (localStorage.getItem("selectTrainersIds")) {
      this.form.driverId = localStorage
        .getItem("selectTrainersIds")
        .split(",");
    } else {
      this.form.driverId = [];
    }

    if (localStorage.getItem("selectTrainersNames")) {
      this.checkUsers = localStorage
        .getItem("selectTrainersNames")
        .split(",");
    } else {
      this.checkUsers = [];
    }

    if (localStorage.getItem("checkGroups")) {
      this.checkGroups = localStorage.getItem("checkGroups").split(",");
    }

    this.init();
  },
  methods: {
    dataHandle(val) {
      if (!val) {
        return [];
      }
      let arr = [];
      for (const item of val) {
        let obj = {};
        obj.name = item.split("pfserverperson/")[1];
        obj.src = item;
        arr.push(obj);
      }
      return arr;
    },
    handleRemove(file, fileList) {
      //TODO:
      // console.log(this.formData.fileUrls);
      for (const [index, item] of this.fileList.entries()) {
        if (item.name == file.name) {
          console.log(index);
          this.fileList.splice(index, 1);
        }
      }

      console.log(file, fileList);
    },
    UploadFile1(param) {
      let fd = new FormData();
      console.log("param.file", param);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          console.log("提交文件", response.msg);
          // param.file.src = response.msg;
          // this.formData.fileUrls.push(param.file);
          this.fileList.push(...this.dataHandle([response.msg]));
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
    init() {
      this.getList();
    },
    UploadFileGroups(param) {
      let fd = new FormData();
      let _this = this;
      // let url = 'https://ksv-video-publish.cdn.bcebos.com/8d105069f32416bd31ac82b1a0453fb7909fff8a.mp4?auth_key=1654650302-0-0-dec00278cefd14ceb846a7647c37d10b'
      this.state = true;
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          this.state = false;
          this.videoForm.showVideoPath = response.msg;
        })
        .catch((err) => {
          this.state = false;
          console.log(err);
        });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      console.log("selection",selection);
      this.ids = selection.map((item) => item.id);
      this.names = selection.map((item) => item.name);
    },
    // 返回
    backClick() {
      localStorage.removeItem("selectTrainersIds");
      localStorage.removeItem("checkGroups");
      localStorage.removeItem("selectTrainersNames");
      localStorage.removeItem("formTrainingInfo");
      localStorage.removeItem("videoPath");
      this.form = {
        title: "",
        minDuration: "",
        trainingEndTime: "",
        education: "",
        content: "",
        groupId: [],
      };
      // this.$router.push({
      //   path: `/trainingManage`,
      // });
      this.$router.go(-1);
    },
    // 选择群组列表
    getList() {
      selectGroupList({}).then((res) => {
        console.log(res);
        this.noticeList = res.rows;
        this.total = res.total;
      });
    },
    // 选择群组
    selectGroupClick() {
      this.open = true;
    },
    // 时间转换
    TimeChange(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      const day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      const hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      const min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      const sec = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
      return year + "-" + month + "-" + day + " ";
    },
    // 选择培训人员
    selectTrainersClick() {
      localStorage.setItem("formTrainingInfo", JSON.stringify(this.form));
      localStorage.setItem("videoPath", this.videoForm.showVideoPath);
      this.$router.push({
        path: `/selectTrainers`,
      });
    },
    beforeUploadVideo(file) {
      console.log(file.type);
      var fileSize = file.size / 1024 / 1024 < 100;
      if (
        [
          "video/mp4",
          "video/ogg",
          "video/flv",
          "video/avi",
          "video/wmv",
          "video/rmvb",
          "video/mov",
          "video/x-matroska",
        ].indexOf(file.type) == -1
      ) {
        this.$message.warning("请上传正确的视频格式");
        return false;
      }
      if (!fileSize) {
        this.$message.warning("视频大小不能超过100MB");
        return false;
      }
      this.isShowUploadVideo = false;
    },
    // 保存
    saveClick() {
      console.log(this.state);
      if (this.state) {
        this.$message({
          showClose: true,
          message: "请等待视频上传成功",
          type: "warning",
        });
        return;
      }
      this.form.videoUrl = this.videoForm.showVideoPath;
      console.log(this.form);
      this.saveLoading = true;
      console.log(this.fileList);
      let fileUrls_data = [];
      for (const item of this.fileList) {
        // fileUrls+=item.src+','
        fileUrls_data.push(item.src);
      }
      this.form.fileUrls = fileUrls_data
      training(this.form)
        .then((res) => {
          this.saveLoading = false;
          this.backClick();
        })
        .catch((err) => {
          this.saveLoading = false;
        });
    },
    saveDialogClick() {
      this.form.groupId = this.ids;
      console.log("this.names;",this.names)
      this.checkGroups = this.names;
      localStorage.setItem("checkGroups",this.checkGroups);
      this.open = false;
    },
    //进度条
    uploadVideoProcess(event, file, fileList) {
      this.videoFlag = true;
      this.videoUploadPercent = file.percentage.toFixed(0) * 1;
    },
    //上传成功回调
    handleVideoSuccess(res, file) {
      this.isShowUploadVideo = true;
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      if (res.Code == 0) {
        this.videoForm.showVideoPath = res.Data;
      } else {
        // layer.msg(res.Message);
      }
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
</style>

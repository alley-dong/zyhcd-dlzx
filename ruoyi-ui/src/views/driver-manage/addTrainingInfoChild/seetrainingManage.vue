<template>
  <div class="app-container">
    <!-- 查看 -->
    <div style="width: 98%; margin: 0 20px 20px; overflow: hidden">
      <!-- <router-link :to="{ path: '/trainingManage', name: 'trainingManage' }"> -->
      <el-button
        @click="$router.go(-1)"
        type="primary"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >
      <!-- </router-link> -->
    </div>
    <div class="top">
      <div class="check-peoper">选择人员</div>
      <div class="xian"></div>
    </div>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="160px"
      >
        <el-col :span="24">
          <el-form-item label="*标题" prop="title">
            <div :style="{ width: '100%' }">{{ formData.title }}</div>
            <!-- <el-input
              v-model="formData.title"
              placeholder="请输入单行文本标题"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*设置阅读时长（分钟）" prop="minDuration">
            <div :style="{ width: '100%' }">{{ formData.minDuration }}</div>

            <!-- <el-input
              v-model="formData.minDuration"
              placeholder="请输入单行文本设置阅读时长"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*培训截止时间" prop="trainingEndTime">
            <div :style="{ width: '100%' }">
              {{ formData.trainingEndTime | time }}
            </div>

            <!-- <el-input
              v-model="formData.trainingEndTime"
              placeholder="请输入单行文本培训截止时间"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="*培训要求" prop="education">
            <!-- <el-input
              v-model="formData.education"
              placeholder="请输入单行文本标题*培训要求"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <div :style="{ width: '100%' }">
              {{ formData.education }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="*视频" prop="videoUrl">
            <!-- <el-input
              v-model="formData.education"
              placeholder="请输入单行文本标题*培训要求"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <video
              v-if="formData.videoUrl"
              style="width: 440px; height: 350px"
              controls="controls"
              :src="formData.videoUrl"
            ></video>
            <!-- <div :style="{ width: '100%' }">
              {{ formData.videoUrl }}
            </div> -->
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="*培训参与人员" prop="driverNameList">
            <!-- <el-input
              v-model="formData.driverNameList"
              placeholder="请输入单行文本标题*培训参与人员"
              clearable
              :style="{ width: '100%' }"
            ></el-input> -->
            <div :style="{ width: '100%', display: 'flex' }">
              <div
                v-for="(item, index) in formData.driverNameList"
                :key="index"
              >
                <el-tag v-if="item" style="margin-right: 20px">{{
                  item
                }}</el-tag>
              </div>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="*培训内容" prop="content">
            <!-- <el-input
              v-model="formData.content"
              type="textarea"
              placeholder="请输入*培训内容"
              :autosize="{ minRows: 4, maxRows: 4 }"
              :style="{ width: '100%' }"
            ></el-input> -->
            <div :style="{ width: '100%' }" v-html="formData.content">
              <!-- {{ formData.content }} -->
            </div>
          </el-form-item>
          <!-- <el-col :span="24">
            <el-form-item label="附件" prop="content">
              <div class="my_upload">
                <el-upload
                  disabled
                  style="float: left; margin-left: 10px"
                  drag
                  ref="upload"
                  class="upload-btn"
                  action="string"
                  accept=".xls, .xlsx"
                  :file-list="fileList"
                  :on-preview="uploadPreview"
                  multiple
                >
                </el-upload>
              </div>
            </el-form-item>
          </el-col> -->
        </el-col>
      </el-form>
      <!-- <el-col :span="24"> -->
      <!-- <div style="display: flex; margin-top: 30px">
          <div>附件</div>
          <div class="my_upload">
            <el-upload
              disabled
              style="float: left; margin-left: 10px"
              drag
              ref="upload"
              class="upload-btn"
              action="string"
              accept=".xls, .xlsx"
              :file-list="fileList"
              :on-preview="uploadPreview"
              multiple
            >
            </el-upload>
          </div>
        </div> -->
      <!-- </el-col> -->
    </el-row>
  </div>
</template>

<script>
import { trainingGetInfoCK } from "@/api/driver/noticeManage";

import { formatDate } from "@/utils/index";
export default {
  components: {},
  props: [],
  filters: {
    time(val) {
      return formatDate(val, true);
    },
  },
  data() {
    return {
      fileList: [],
      formData: {
        title: undefined,
        minDuration: undefined,
        trainingEndTime: undefined,
        education: undefined,
        driverNameList: undefined,
        content: undefined,
      },
      rules: {
        title: [],
        minDuration: [],
        trainingEndTime: [],
        education: [],
        driverNameList: [],
        content: [],
      },
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    this.init();
  },
  methods: {
    uploadPreview(file) {
      // console.log(file);
      if (file.name.indexOf(".") == -1) {
        return;
      }
      let obj = {
        originalName: file.name,
        filename: file.name,
      };
      window.location.href =
        process.env.VUE_APP_BASE_API +
        `/pfserver-person/personFile/minioChooseBucketDownload?originalName=${obj.originalName}&filename=${obj.originalName}`;
    },
    // 数据处理
    dataHandle(val) {
      console.log(val);
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
    init() {
      this.trainingGetInfoCKData();
    },
    trainingGetInfoCKData() {
      trainingGetInfoCK(this.$route.params.id).then((res) => {
        console.log(res.data);
        this.formData = res.data;
        if (res.data.fileList) {
          this.fileList.push(...this.dataHandle(res.data.fileList));
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.top {
  display: flex;
  margin-bottom: 50px;
  .check-peoper {
    margin-right: 30px;
  }
  .xian {
    flex: 1;
    height: 3px;
    background-color: #333399;
    margin-top: 7px;
  }
}
</style>
<style lang="scss">
.my_upload {
  .el-upload-dragger {
    display: none;
  }
}
</style>
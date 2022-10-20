<template>
  <div>
    <el-button
      type="primary"
      style="float: right; margin: 0 20px 30px 0"
      @click="importClick()"
      >导入</el-button
    >

    <!-- 导入弹窗-->
    <el-dialog
      title="导入"
      :visible.sync="dialogVisible1"
      width="600px"
      :before-close="handleClose1"
    >
      <div style="width: 640px; height: 200px">
        <el-form
          ref="form"
          :model="form"
          label-width="100px"
          style="float: left"
        >
          <el-form-item label="导入文件："></el-form-item>
        </el-form>
        <el-upload
          style="float: left; margin-left: 10px"
          drag
          ref="upload"
          class="upload-btn"
          action="string"
          accept=".xls, .xlsx"
          :before-upload="onBeforeUploadImage"
          :http-request="UploadFile"
          multiple
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">
            <a href="./template.xlsx" download="下载模板.xlsx">下载模板</a>
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button>导入</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible1: false,
    };
  },
  methods: {
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
  },
};
</script>

<style lang="sass" scoped>
</style>
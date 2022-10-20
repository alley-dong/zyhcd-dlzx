<template>
  <div>
    <!-- <el-button
      type="primary"
      style="float: right; margin: 0 20px 30px 0"
      @click="importClick()"
      >导入</el-button
    > -->

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
            <a href="/carTemplate.xls" download="下载模板.xls">下载模板</a>
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadClick">导入</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible1: true,
      upfile: null,
      form: {},
    };
  },
  // mounted() {
  //   this.dialogVisible1 = false;
  // },
  methods: {
    // 导入弹窗关闭

    // //导入按钮
    // importClick() {
    //   this.dialogVisible1 = true;
    // },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
      this.$emit("on-close", false);
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },
    uploadClick() {
      if (this.upfile) {
        // console.log(this.upfile);
        // let fd = new FormData();
        // fd.append("file", this.upfile); // 要提交给后台的文件
        // UploadFiles(fd)
        //   .then((response) => {
        //     console.log("提交文件", response);
        //     this.$message({
        //       message: "导入成功",
        //       type: "success",
        //     });
        //     this.dialogVisible1 = false;
        //   })
        //   .catch((err) => {
        //     console.log(err);
        //   });
        this.dialogVisible1 = false;
        this.$emit("on-upload", this.upfile, false);
      } else {
        this.$message({
          message: "请选择文件",
          type: "error",
        });
      }
    },
  },
};
</script>

<style lang="sass" scoped>
</style>
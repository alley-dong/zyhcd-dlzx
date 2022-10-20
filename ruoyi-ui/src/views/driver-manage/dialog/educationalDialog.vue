<template>
  <div>
    <el-dialog
      title="教育经历"
      :visible.sync="dialogVisible"
      width="60%"
      :before-close="handleClose"
    >
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="就读学校" prop="school">
          <el-input
            v-model="formData.school"
            placeholder="请输入就读学校"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="专业" prop="major">
          <el-input
            v-model="formData.major"
            placeholder="请输入专业"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker
            v-model="formData.startTime"
            @change="startChange"
            type="date"
            placeholder="请选择开始时间"
            :style="{ width: '100%' }"
            clearable
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="finshTime">
          <el-date-picker
            @change="endChange"
            v-model="formData.finshTime"
            type="date"
            placeholder="请选择结束时间"
            :style="{ width: '100%' }"
            clearable
          ></el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  props:['educationalDialogData'],
  inheritAttrs: true,
  components: {},
  data() {
    return {
      formData: {
        school: undefined,
        major: undefined,
        startTime: null,
        finshTime: null,
      },
      dialogVisible: true,
      rules: {
        school: [{ required: true, message: "请输入就读学校", trigger: "blur" }],
        major: [{ required: true, message: "请输入专业", trigger: "blur" }],
        startTime: [{ required: true, message: "请选择开始时间", trigger: "blur" }],
        finshTime: [{ required: true, message: "请选择结束时间", trigger: "blur" }],
      },
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    console.log(this.educationalDialogData);
    if ( this.educationalDialogData) {
      this.formData = this.educationalDialogData
      
    }
  },
  methods: {
    startChange(val) {
      // console.log(val);
      this.formData.startTime = this.dataChange(val)
    },
    endChange(val) {
      // console.log(val);
      this.formData.finshTime = this.dataChange(val)
    },
    onOpen() {},
    handleClose() {
        this.$emit("educationalDialogClick");

    },
    close() {
        this.$emit("educationalDialogClick");

    },
    // 日期转换
    dataChange(val) {
      const d = new Date(val);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
    handelConfirm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        this.$emit("educationalDialogClick", this.formData,this.educationalDialogData);
        this.close();
      });
    },
  },
};
</script>
<style>
</style>

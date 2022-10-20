<template>
  <div>
    <el-dialog
      title="驾驶经历"
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
        <el-form-item label="工作单位:" prop="workUnit">
          <el-input
            v-model="formData.workUnit"
            placeholder="请输入工作单位"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="职位及岗位:" prop="position">
          <el-input
            v-model="formData.position"
            placeholder="请输入职位及岗位"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="开始时间:" prop="startTime">
          <el-date-picker
            v-model="formData.startTime"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            :style="{ width: '100%' }"
            placeholder="请选择开始时间:"
            clearable
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间:" prop="finshTime">
          <el-date-picker
            v-model="formData.finshTime"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            :style="{ width: '100%' }"
            placeholder="请选择结束时间:"
            clearable
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="驾驶路线:" prop="route">
          <el-input
            v-model="formData.route"
            placeholder="请输入驾驶路线"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
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
  props: ["drivingExperienceDialogData"],
  inheritAttrs: false,
  components: {},
  data() {
    return {
      dialogVisible: true,
      formData: {
        workUnit: undefined,
        position: undefined,
        startTime: null,
        finshTime: null,
        route: undefined,
      },
      rules: {
        workUnit: [{ required: true, message: "请输入工作单位", trigger: "blur" }],
        position: [{ required: true, message: "请输入职位及岗位", trigger: "blur" }],
        startTime: [{ required: true, message: "请输入开始时间", trigger: "blur" }],
        finshTime: [{ required: true, message: "请输入结束时间", trigger: "blur" }],
        route: [{ required: true, message: "请输入驾驶路线", trigger: "blur" }],
      },
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
      console.log(this.drivingExperienceDialogData);
       if ( this.drivingExperienceDialogData) {
      this.formData = this.drivingExperienceDialogData
      
    }
  },
  methods: {
    onOpen() {},
    startChange(val) {
      // console.log(val);
      this.formData.startTime = this.dataChange(val);
    },
    endChange(val) {
      // console.log(val);
      this.formData.finshTime = this.dataChange(val);
    },
    dataChange(val) {
      const d = new Date(val);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
    handleClose() {
      this.$emit("DrivingExperienceDialogClick");
    },
    close() {
      this.$emit("DrivingExperienceDialogClick");
    },
    handelConfirm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        this.$emit(
          "DrivingExperienceDialogClick",
          this.formData,
          this.drivingExperienceDialogData == null
        );
        this.close();
      });
    },
  },
};
</script>
<style>
</style>

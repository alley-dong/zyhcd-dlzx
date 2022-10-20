<template>
  <div>
    <el-dialog
      title="交通事故经历"
      :visible.sync="dialogVisible"
      width="70%"
      :before-close="handleClose"
    >
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="260px"
      >
        <el-form-item label="事故时间" prop="accidentTime">
          <el-date-picker
            v-model="formData.accidentTime"
            @change="startChange"
            type="date"
            placeholder="请选择事故时间"
            :style="{ width: '100%' }"
            clearable
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="事故地点" prop="place">
          <el-input
            v-model="formData.place"
            placeholder="请输入事故地点"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="是否造成人员受伤（详细说明）" prop="remarks">
          <el-input
            v-model="formData.remarks"
            placeholder="请输入是否造成人员受伤（详细说明）"
            clearable
            :style="{ width: '100%' }"
          ></el-input>
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
  inheritAttrs: false,
  components: {},
  props: ["trafficAccidentDataList"],
  data() {
    return {
      formData: {
        accidentTime: null,
        place: "",
        remarks: "",
      },
      dialogVisible: true,
      rules: {
        accidentTime: [{ required: true, message: "请选择事故时间", trigger: "blur" }],
        place: [{ required: true, message: "请输入事故地点", trigger: "blur" }],
        remarks: [{ required: true, message: "请输入是否造成人员受伤(详细说明)", trigger: "blur" }],
      },
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    if ( this.trafficAccidentDataList) {
      this.formData = this.trafficAccidentDataList
      
    }
  },
  methods: {
    startChange(val) {
      // console.log(val);
      this.formData.accidentTime = this.dataChange(val);
    },
  
    onOpen() {},
    handleClose() {
      this.$emit("trafficAccidentClick");
    },
    close() {
      this.$emit("trafficAccidentClick");
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
        this.$emit(
          "trafficAccidentClick",
          this.formData,
          this.trafficAccidentDataList
        );
        this.close();
      });
    },
  },
};
</script>
<style>
</style>

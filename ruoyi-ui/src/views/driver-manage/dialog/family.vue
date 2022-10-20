<template>
  <div>
    <el-dialog
      title="家庭状况"
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
        <el-form-item label="关系" prop="relation">
          <el-input
            v-model="formData.relation"
            placeholder="请输入关系"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input
            v-model="formData.name"
            placeholder="请输入姓名"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="tel">
          <el-input
            v-model="formData.tel"
            placeholder="请输入联系方式"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="岗位及职务" prop="position">
          <el-input
            v-model="formData.position"
            placeholder="请输入岗位及职务"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="工作单位" prop="workUnit">
          <el-input
            v-model="formData.workUnit"
            placeholder="请输入工作单位"
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
  inheritAttrs: false,
  components: {},
  props: ["familyDialogData"],
  data() {
    return {
      dialogVisible: true,
      formData: {
        relation: undefined,
        name: undefined,
        tel: undefined,
        position: undefined,
        workUnit: undefined,
      },
      rules: {
        relation: [{ required: true, message: "请输入关系", trigger: "blur" }],
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
        tel: [{ required: true, message: "请输入联系方式", trigger: "blur" }],
        position: [{ required: true, message: "请输入岗位及职务", trigger: "blur" }],
        workUnit: [{ required: true, message: "请输入工作单位", trigger: "blur" }],
      },
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    console.log(this.familyDialogData);
    if (this.familyDialogData) {
      this.formData = this.familyDialogData;
    }
  },
  methods: {
    onOpen() {},
    handleClose() {
      this.$emit("familyDialogClick");
    },
    close() {
      this.$emit("familyDialogClick");
    },
    handelConfirm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        this.$emit(
          "familyDialogClick",
          this.formData,
          this.familyDialogData == null
        );
        this.close();
      });
    },
  },
};
</script>
<style>
</style>

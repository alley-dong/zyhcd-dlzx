<template>
  <div>
    <el-dialog
      title="提示"
      :visible.sync="visible"
      width="30%"
      :before-close="handleClose"
    >
      <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
        <el-form-item :label="title + '：'" prop="workUnit">
          <el-input
            v-model="formData.name"
            :placeholder="`请输入${title}：`"
            clearable
            :disabled="title=='我方名称'"
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="责任比例%：" prop="position">
          <el-input
            v-model="formData.proportion"
            placeholder="请输入责任比例："
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="赔付金额:" prop="startTime">
          <el-input
            v-model="formData.compensateMoney"
            placeholder="请输入赔付金额："
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="赔付时间:" prop="finshTime">
          <el-date-picker
            v-model="formData.compensateTime"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            :style="{ width: '100%' }"
            placeholder="请选择赔付时间:"
            clearable
          ></el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="驾驶路线：" prop="route">
          <el-input
            v-model="formData.route"
            placeholder="请输入驾驶路线："
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item> -->
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
  props: ["visible", "title", "row", "data_fn"],
  data() {
    return {
      formData: {
        name: undefined,
        proportion: undefined,
        compensateMoney: null,
        compensateTime: null,
      },
    };
  },
  mounted() {
    console.log(this.$props.row);
    if (this.$props.row) {
        this.formData = this.$props.row;
    }
  },
  methods: {
    handleClose(done) {
      // this.$confirm("确认关闭？")
      //   .then((_) => {
      //     done();
          
      //   })
      //   .catch((_) => {});
      this.$emit("update:visible", false);
    },
    close() {
      this.$emit("update:visible", false);
    },
    handelConfirm() {
      console.log();
      this.$props.data_fn(this.formData);
      
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
<template>
  <div>
    <el-button
      type="cyan"
      icon="el-icon-search"
      size="mini"
      @click="handleFilter"
      >{{ oneButton }}</el-button
    >
    <el-button
      type="primary"
      icon="el-icon-plus"
      size="mini"
      v-if="!twoButtonState"
      @click="handleAdd"
      v-hasPermi="addHasPermi"
      >{{ twoButton }}</el-button
    >
    <el-button
      type="primary"
      size="mini"
      v-if="backButton"
      @click="handleBack"
      >{{ backButtonState }}</el-button
    >
    <el-button
      type="warning"
      icon="el-icon-download"
      size="mini"
      v-if="leadingIn"
      @click="handleImportant"
      v-hasPermi="importantHasPermi"
      >{{ threeButton }}</el-button
    >
  </div>
</template>

<script>
export default {
  props: {
    addHasPermi: {
      type: Array,
      default: [],
    },
    // backHasPermi: {
    //   type: Array,
    //   default: [],
    // },
    importantHasPermi: {
      type: Object,
      default: []
    },
    // 基础类型检测, null意味着任何类型都行
    oneButton: {
      type: String,
      default: "查询",
    },
    backButtonState: {
      type: String,
      default: "返回",
    },
    // 多种类型
    twoButton: {
      type: String,
      default: "新增",
    },
    //
    threeButton: {
      type: String,
      default: "导入",
    },
    leadingIn: {
      type: Boolean,
      default: false,
    },
    backButton: {
      type: Boolean,
      default: false,
    },
    twoButtonState: {
      type: Boolean,
      default: false,
    },
  },
  mounted() {
  },
  methods: {
    handleFilter() {
      this.$emit("handleFilter", this.childValue);
    },
    handleAdd() {
      this.$emit("handleAdd", this.childValue);
    },
    handleImportant() {
      this.$emit("handleImportant", this.childValue);
    },
    handleBack() {
      this.$emit("handleBack", this.childValue);
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
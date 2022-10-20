<template>
  <div class="app-container">
    <div style="width: 98%; margin: 0 20px 20px; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="saveClick"
        >提交</el-button
      >
      <el-button
        type="primary"
        @click="cancelClick"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >
    </div>
    <div class="wrap">
      <div class="text">选择保险项目</div>
      <div class="line"></div>
    </div>
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="车辆类别" prop="vehicleTypeName">
        <!-- <el-input
          v-model="queryParams.inspectDate"
          placeholder="请输入检车日期"
          clearable
        /> -->
        <el-select
          clearable
          v-model="queryParams.vehicleTypeName"
          placeholder="请选择"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      ref="multipleTable"
      :data="driverList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column> -->

      <el-table-column label="车辆类别" align="center" prop="vehicleTypeName" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="车型" align="center" prop="vehicleModelName" />
    </el-table>

    <!-- <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->
    <!-- 
    <el-dialog :visible.sync="open" @close="close" title="Dialog Titile">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="状态" prop="mobile">
          <el-select
            v-model="formData.vehicleStatus"
            placeholder="请输入状态"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in options"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import { vehicleList08 } from "@/api/system/vehicle";

import { formatDate } from "@/utils/index";

export default {
  name: "Driver",
  data() {
    return {
      rules: {
        status: [],
      },
      saveLoading: false,

      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 驾驶员-司机档案表格数据
      driverList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        // pageNum: 1,
        // pageSize: 10,
        vehicleTypeName: null,
        licensePlate: null,
      },
      formData: {
        id: null,
        vehicleTypeName: null,
      },
      options: [
        {
          value: "主车",
          // value: 1,
          label: "主车",
        },
        {
          value: "挂车",
          // value: 2,
          label: "挂车",
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      checkList: [],
      dDriverExamAddList: {},
    };
  },
  filters: {
    time(val) {
      console.log(val);
      return formatDate(val, true);
    },

    state(val) {
      switch (val) {
        //   车辆状态 1运输中 2维修中 3请假中 4空闲中
        case 1:
          return "运输中";
          break;
        case 2:
          return "维修中";
          break;
        case 3:
          return "请假中";
          break;
        case 4:
          return "空闲中";
          break;
        default:
          return "全部";
          break;
      }
    },
  },
  created() {},
  mounted() {
    this.getList();
  },
  methods: {
    //   取消
    close() {
      this.open = false;
      this.formData.vehicleStatus = null;
    },
    saveClick() {
      //   localStorage.removeItem("selectTrainersIds");
      //   let driverId = localStorage.getItem("selectTrainersIds");
      localStorage.setItem("insuranceVehicleNames", JSON.stringify(this.ids));
      this.cancelClick();
    },
    cancelClick() {
      this.$router.go(-1);
    },
    //   确定
    handelConfirm() {},
    // 查看年检记录
    seeClick(row) {
      console.log(row.id);
      this.open = true;
      this.formData.id = row.id;
      this.formData.vehicleStatus = row.vehicleStatus;
    },
    //   返回
    // cancelClick() {
    //   this.$router.push({ path: `/paperManage`, name: "paperManage" });
    // },
    // 多选框选中数据
    handleSelectionChange(selection) {
        this.ids = selection
    //   this.ids = selection.map((item) => item.licensePlate);
      console.log(this.ids);
    },

    /** 保险管理货物保险选择车辆保险列表*/
    getList() {
      this.loading = true;
      vehicleList08(this.queryParams).then((response) => {
        console.log(response);
        this.driverList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 搜索按钮操作 */
    handleQuery() {
      // this.queryParams.pageNum = 1;
      this.getList();
    },
  },
};
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
  display: flex;
}
.text {
  width: 100px;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  //   float: left;
  font-weight: 400;
}
.line {
  border-bottom: 3px solid #333399;
  height: 50%;
  flex: 1;
}
</style>
<template>
  <div class="app-container">
    <!-- <div style="width: 98%; margin: 0 20px 20px; overflow: hidden"> -->
    <!-- <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="saveClick"
        :loading="saveLoading"
        >提交</el-button
      >
      <el-button
        type="primary"
        @click="cancelClick"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >  </div> -->
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

      <!-- <el-form-item label="检车日期" prop="inspectDate">
 
        <el-date-picker
          @change="
            queryParams.inspectDate = formatDate(queryParams.inspectDate)
          "
          v-model="queryParams.inspectDate"
          type="date"
          placeholder="选择日期"
          size="small"
        >
        </el-date-picker>
      </el-form-item> -->

      <el-form-item>
        <!-- <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        > -->

        <buttonGroup @handleFilter="handleQuery" twoButtonState />
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      ref="multipleTable"
      :data="driverList"
      @selection-change="handleSelectionChange"
      :row-class-name="tableRowClassName"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>

      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="注册日期" align="center" prop="registerDate" />
      <el-table-column
        label="下次检车日期"
        align="center"
        prop="nextInspectDate"
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="250px"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            @click="seeClick(scope.row)"
            style="margin-right: 15px"
            v-hasPermi="['pfservercar:vehicleInspectRecord:infolist']"
            >查看年检记录</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { vehicleInspectRecordList } from "@/api/car/vehicle";

import { formatDate } from "@/utils/index";
import buttonGroup from "@/views/components/com/buttonGroup";

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
        pageNum: 1,
        pageSize: 10,
        status: null,
        licensePlate: null,
        inspectDate: null,
      },
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
        //   1未完成 2已完成 3超期未完成 4超期已完成
        case 1:
          return "未完成";
          break;
        case 2:
          return "已完成";
          break;
        case 3:
          return "超期未完成";
          break;
        case 4:
          return "超期已完成";
          break;
        default:
          return "全部";
          break;
      }
    },
  },
  created() {},
  components: {
    buttonGroup,
  },
  mounted() {
    this.getList();
  },
  methods: {
    formatDate(val) {
      return formatDate(val, true);
    },
    // 查看年检记录
    seeClick(row) {
      let {id,licensePlate,registerDate} = row
      this.$router.push({ path: `/lookYearlyInspection/${id}`,query:{licensePlate,registerDate} }); //TODO:
    },
    // 状态标识

    tableRowClassName({ row, rowIndex }) {
      console.log(row);
      switch (row.outTimeFlag) {
        case 2:
          return "error-row";
          break;
        default:
          break;
      }
    },
    //   返回
    // cancelClick() {
    //   this.$router.push({ path: `/paperManage`, name: "paperManage" });
    // },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
    },

    /** 查询驾驶员-司机档案列表 */
    getList() {
      this.loading = true;
      vehicleInspectRecordList(this.queryParams).then((response) => {
        //TODO:
        console.log(response);
        this.driverList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
  },
};
</script>

<style >
.el-table .warning-row {
  background: yellow;
}
.small-padding {
  background: #fff;
}
.el-table .success-row {
  background: blue;
}
.el-table .error-row {
  background: red;
}
</style>
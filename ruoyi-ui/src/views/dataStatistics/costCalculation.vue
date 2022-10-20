<template>
  <div class="app-container">
    <!-- 承运商成本核算TODO: -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="开始时间" prop="taskStartTime">
        <el-date-picker
          v-model="queryParams.taskStartTime"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '199px' }"
          placeholder="请选择开始时间"
          clearable
          @keyup.enter.native="handleQuery"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="taskEndTime">
        <el-date-picker
          v-model="queryParams.taskEndTime"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '199px' }"
          placeholder="请选择结束时间"
          clearable
          @keyup.enter.native="handleQuery"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="线路" prop="routeId">
        <el-select v-model="queryParams.routeId" placeholder="请选择线路">
          <el-option
            v-for="item in routerOptions"
            :key="item.routeId"
            :value="item.routeId"
            :label="item.routeName"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <buttonGroup
        @handleFilter="handleQuery"
        twoButtonState
        leadingIn
        threeButton="导出"
        style="float: right"
        @handleImportant="handleImportant"
        :importantHasPermi="['pfservercar:transportTask:export']"
      />
    </el-form>
    <div class="costAccounting">
      <span>合计费用：{{ totalCost }} </span>
    </div>
    <el-table v-loading="loading" :data="repairList">
      <el-table-column label="序号" align="center" type="index" width="50">
      </el-table-column>
      <el-table-column label="运单号" align="center" prop="id" />
      <el-table-column label="业务" align="center" prop="businessUnitName" />
      <el-table-column label="线路" align="center" prop="routeName" />
      <el-table-column label="承运商" align="center" prop="servprovName" />
      <el-table-column label="运费（元）" align="center" prop="totalCost" />
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
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  transportTaskN05TaskList,
  transportTaskN05RouteName,
} from "@/api/dataStatistics/index";
export default {
  components: {
    buttonGroup,
  },
  data() {
    return {
      totalCost: undefined,
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
      // 维修保养-维修保养单表格数据
      repairList: [],
      //   下拉
      repairListOption: [],
      routerOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskStartTime: undefined,
        taskEndTime: undefined,
        routeId: undefined,
      },
    };
  },
  created() {
    this.getList(true);
    transportTaskN05RouteName().then((res) => {
      this.routerOptions = res.rows;
    });
  },
  methods: {
    /** 查询列表 */
    getList(bool) {
      this.loading = true;
      transportTaskN05TaskList(this.queryParams).then((response) => {
        let num = 0;
        for (const item of response.rows) {
          // item.total = item.unitPrice * item.amount;
          num += item.totalCost;
        }
        this.totalCost = num;
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 导出
    handleImportant() {
      this.download(
        "/pfserver-car/transportTask/N05Export",
        {
          ...this.queryParams,
        },
        `transportTask.xlsx`
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 查看
    handleClick(row) {
      console.log("查看", row);
      this.$router.push("/detailInformation/" + row.month);
    },
  },
};
</script>

<style lang="scss" >
.costAccounting {
  margin-bottom: 25px;
  span {
    margin-right: 30px;
  }
}
.el-input--medium .el-input__inner {
  height: 32px;
}
</style>
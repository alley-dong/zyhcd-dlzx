<template>
  <div class="app-container">
    <!-- 数据统计 -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="日期选择" prop="month">
        <el-date-picker
          v-model="queryParams.month"
          format="yyyy-MM"
          value-format="yyyy-MM"
          type="month"
          :style="{ width: '100%' }"
          placeholder="请选择日期选择"
          clearable
        ></el-date-picker>
      </el-form-item>
      <buttonGroup
        @handleFilter="handleQuery"
        twoButtonState
        style="float: right"
      />
    </el-form>
    <el-table 
    border
    v-loading="loading" :data="repairList">
      <el-table-column label="序号" align="center" type="index" width="50">
      </el-table-column>
      <el-table-column label="统计月份" align="center" prop="month">
        <template slot-scope="scope">
          {{ scope.row.month }}
          <!-- {{scope.row.month.substring(0,scope.row.month.lastIndexOf('-'))}} -->
        </template>
      </el-table-column>
      <!-- str.substring(0,str.lastIndexOf('-')) -->
      <el-table-column label="车辆数" align="center" prop="number" />
      <el-table-column
        label="月度行驶总里程（公里）"
        align="center"
        prop="lastMonthEndMileage"
      />
      <el-table-column
        label="月度加油量（升）"
        align="center"
        prop="monthFuelVolume"
      />
      <el-table-column
        label="月度尿素领用量（升）"
        align="center"
        prop="monthUreaVolume"
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="200"
      >
        <template slot-scope="scope">
          <el-button size="small"
            v-hasPermi="['car:vehicleConsumeMonth:N03List']"
           type="primary" @click="handleClick(scope.row)"
            >详情</el-button
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
import buttonGroup from "@/views/components/com/buttonGroup";
import { vehicleConsumeMonthN01List } from "@/api/dataStatistics/index";
export default {
  components: {
    buttonGroup,
  },
  data() {
    return {
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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        month: null,
      },
    };
  },
  created() {
    this.getList(true);
  },
  methods: {
    /** 查询列表 */
    getList(bool) {
      this.loading = true;
      vehicleConsumeMonthN01List(this.queryParams).then((response) => {
        if (bool) {
          this.repairListOption = response.rows;
        }
        for (const item of response.rows) {
          item.total = item.unitPrice * item.amount;
        }
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 查看
    handleClick(row) {
      console.log("查看", row);
      this.$router.push(
        "/detailInformation/" +
          row.month
      );
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
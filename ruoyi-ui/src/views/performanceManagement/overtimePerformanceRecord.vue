<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="queryParams.bTime"
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="选择月"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="queryParams.eTime"
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="选择月"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="驾驶员姓名" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入驾驶员姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <!-- <el-form-item label="任务状态">
        <el-select
          v-model="queryParams.taskStatus"
          clearable
          placeholder="请选择"
        >
          <el-option
            v-for="item in taskStatus"
            :key="item.value"
            :label="item.taskStatus"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item> -->
      <!-- <buttonGroup
        @handleFilter="handleQuery"
        @handleAdd="handleAdd"
        style="float: right"
       
      /> -->
      <div style="float:right">
        <el-button
          size="small"
          icon="el-icon-search"
          @click="handleQuery"
          plain
          type="cyan"
          >查询</el-button
        >
        <!-- <el-button
          size="small"
          type="primary"
          @click="handleAdd"
          v-hasPermi="['system:repair:remove']"
          >新增</el-button
        > -->
      </div>

      <el-table v-loading="loading" :data="repairList" border>
        <el-table-column
          label="加班时间"
          align="center"
          prop="performanceDate"
        />
        <el-table-column label="驾驶员姓名" align="center" prop="driverName" />
        <!-- <el-table-column label="开始时间" align="center" prop="taskStartTime" />
        <el-table-column label="结束时间" align="center" prop="taskEndTime" /> -->
        <!-- <el-table-column label="任务状态"
                         align="center"
                         prop="taskStatus" /> -->
        <el-table-column
          label="主车车牌号"
          align="center"
          prop="mainLicensePlate"
        >
          <!-- <template slot-scope="scope">
            <span v-if="scope.row.taskStatus === 1">异常</span>
            <span v-if="scope.row.taskStatus === 2">正常</span>
          </template> -->
        </el-table-column>
        <el-table-column
          label="挂车车牌号"
          align="center"
          prop="hangLicensePlate"
        />
        <el-table-column label="加班费" align="center" prop="amount" />

        <!-- <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="small"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:repair:edit']"
              plain
              type="primary"
              >查看</el-button
            >
          </template>
        </el-table-column> -->
      </el-table>

      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-form>
  </div>
</template>

<script>
import { G14List } from "@/api/performanceManagement/overtimePerformanceRecord";

export default {
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
      // 绩效
      repairList: [],
      // 任务状态
      taskStatus: [
        {
          taskStatus: "正常",
          value: 2
        },
        {
          taskStatus: "异常",
          value: 1
        }
      ],
      //   下拉
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bTime: "",
        eTime: "",
        driverName: ""
      },
      // 绩效下拉
      options: []
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    //   查询列表
    getList(bool) {
      this.loading = true;
      G14List(this.queryParams).then(response => {
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 新增
    handleAdd() {
      this.$router.push("/addLongDistancePerformance");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 查看
    handleUpdate(row) {
      this.$router.push({
        path: "/ViewLongDistancePerformanceRecords/" + row.id
      });
    }
    // handleRemove(row) {
    //   tRouteP01RouteDelete(row.id).then(res => {
    //     this.getList();
    //   });
    // }
  }
};
</script>

<style lang="scss" scoped></style>

<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="绩效名称">
        <el-select v-model="queryParams.performanceName" clearable placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.name"
          >
          </el-option>
        </el-select>
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
      <el-form-item
        label="开始时间"
        prop="taskStartTime"
        label-position="right"
      >
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.taskStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
          @keyup.enter.native="handleQuery"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="taskEndTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 198px"
          v-model="queryParams.taskEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
          @keyup.enter.native="handleQuery"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="任务状态">
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
      </el-form-item>
      <div style="float: right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
          >查询</el-button
        >
      </div>

      <el-table v-loading="loading" :data="repairList" border>
        <el-table-column label="驾驶员姓名" align="center" prop="driverName" />
        <el-table-column
          label="绩效名称"
          align="center"
          prop="performanceName"
        />
        <el-table-column label="开始时间" align="center" prop="taskStartTime" />
        <el-table-column label="结束时间" align="center" prop="taskEndTime" />
        <!-- <el-table-column label="任务状态"
                         align="center"
                         prop="taskStatus" /> -->
        <el-table-column label="任务状态" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.taskStatus === 1">异常</span>
            <span v-if="scope.row.taskStatus === 2">正常</span>
          </template>
        </el-table-column>
        <el-table-column label="绩效金额" align="center" prop="amount" />
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="small"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['person:performanceRecord:getInfo']"
              type="primary"
              >查看</el-button
            >
            <!-- <el-button size="small"
                       type="danger"
                       @click="handleRemove(scope.row)"
                       v-hasPermi="['system:repair:remove']">删除</el-button> -->
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
    </el-form>
  </div>
</template>

<script>
// import buttonGroup from "@/views/components/com/buttonGroup";
import {
  tRouteP01RouteList,
  tRouteP01RouteDelete,
} from "@/api/taskManagement/index";
import {
  G08List,
  commonG08List,
} from "@/api/performanceManagement/documentPerformanceRecord";
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
          value: 2,
        },
        {
          taskStatus: "异常",
          value: 1,
        },
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
        taskStatus: "",
        driverName: "",
        performanceName: "",
        taskStartTime: undefined,
        taskEndTime: undefined,
      },
      // 绩效下拉
      options: [],
    };
  },
  mounted() {
    this.getList();
    this.getcommonG08List();
  },
  methods: {
    // 绩效下拉
    getcommonG08List() {
      commonG08List({}).then((res) => {
        this.options = res.data;
      });
    },
    //   查询列表
    getList(bool) {
      this.loading = true;
      G08List(this.queryParams).then((response) => {
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
      if (row.taskStatus == 1) {
        this.$router.push({
          path: "/detailDocumentPerformanceRecord/" + row.id,
        });
        return;
      }
      this.$router.push({
        path: "/ViewLongDistancePerformanceRecords/" + row.id,
      });
    },
    handleRemove(row) {
      tRouteP01RouteDelete(row.id).then((res) => {
        this.getList();
      });
    },
  },
};
</script>

<style lang="scss" scoped></style>

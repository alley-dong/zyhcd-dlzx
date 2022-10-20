<template>
  <div class="app-container">
    <!-- 任务管理 -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="运单号" prop="shipmentXid">
        <el-input
          v-model="queryParams.shipmentXid"
          placeholder="请输入运单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="线路" prop="routeId">
        <el-select
          v-model="queryParams.routeId"
          placeholder="请选择线路"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option
            v-for="item in option"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="主车车牌号" prop="mainLicensePlate">
        <el-input
          v-model="queryParams.mainLicensePlate"
          placeholder="请输入主车车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="挂车车牌号" prop="hangLicensePlate">
        <el-input
          v-model="queryParams.hangLicensePlate"
          placeholder="请输入挂车车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <div class="myTime">
        <el-form-item label="开始时间" prop="taskStartTime">
          <el-date-picker
            v-model="queryParams.taskStartTime"
            type="date"
            placeholder="请选择开始时间"
            style="width: 199px; height: 32px"
            value-format="yyyy-MM-dd hh:mm:ss"
            @keyup.enter.native="handleQuery"
            clearable
          ></el-date-picker>

          <!-- <el-date-picker
            v-model="queryParams.taskStartTime"
            type="datetime"
            placeholder="选择开始时间"
            @keyup.enter.native="handleQuery"
          >
          </el-date-picker> -->
        </el-form-item>
        <el-form-item label="结束时间" prop="taskEndTime">
          <el-date-picker
            v-model="queryParams.taskEndTime"
            type="date"
            placeholder="请选择结束时间"
            style="width: 199px; height: 32px"
            value-format="yyyy-MM-dd hh:mm:ss"
            @keyup.enter.native="handleQuery"
            clearable
          ></el-date-picker>
          <!-- <el-date-picker
            v-model="queryParams.taskEndTime"
            :picker-options="{ selectableRange: '00:00:00-23:59:59' }"
            type="datetime"
            placeholder="选择结束时间"
            @keyup.enter.native="handleQuery"
          >
          </el-date-picker> -->
        </el-form-item>
      </div>
      <el-form-item label="任务状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择任务状态"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option
            v-for="item in statusOption"
            :key="item.dictValue"
            :label="item.dictLabel"
            :value="item.dictValue"
          />
        </el-select>
      </el-form-item>
      <buttonGroup
        @handleFilter="handleQuery"
        twoButtonState
        style="float: right"
      />

      <el-table 
      border
      v-loading="loading" :data="repairList">
        <el-table-column label="运单号" align="center" prop="shipmentXid" />
        <el-table-column label="线路名称" align="center" prop="routeName" />
        <el-table-column label="运单类型" align="center" prop="shipmentType">
          <template slot-scope="scope">
            {{ scope.row.shipmentType == 1 ? "长途" : "短途" }}
          </template>
        </el-table-column>
        <el-table-column
          label="主车车牌号"
          align="center"
          prop="mainLicensePlate"
        />
        <el-table-column
          label="挂车车牌号"
          align="center"
          prop="hangLicensePlate"
        />
        <el-table-column label="驾驶员" align="center" prop="driverName" />
        <el-table-column
          label="同步任务时间"
          align="center"
          prop="createTime"
        />
        <el-table-column label="任务状态" align="center" prop="status">
          <template slot-scope="scope">
            {{ scope.row.status == 1 ? "进行中" : "已结束" }}
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="small"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['pfservercar:transportTask:info']"
              type="primary"
              >任务详情</el-button
            >
            <!-- <el-button
              size="small"
              type="danger"
              @click="handleRemove(scope.row)"
              v-hasPermi="['system:repair:remove']"
              >删除</el-button
            > -->
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
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  transportTaskP04TransportTaskList,
  tRouteP01RouteDelete,
} from "@/api/taskManagement/index";
import { routerBox } from "@/api/card/fuelration";

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
      //   线路下拉
      option: [],
      //   状态下拉
      statusOption: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "进行中",
          value: 1,
        },
        {
          label: "已结束",
          value: 2,
        },
      ],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        shipmentXid: undefined,
        routeId: undefined,
        mainLicensePlate: undefined,
        hangLicensePlate: undefined,
        taskStartTime: undefined,
        taskEndTime: undefined,
        status: undefined,
      },
    };
  },
  mounted() {
    this.getList();
    this.getDicts("t_transport_task_status").then((response) => {
      this.statusOption = response.data;
    });
    routerBox({}).then((res) => {
      this.option = res.data;
    });
  },
  methods: {
    //   查询列表
    getList(bool) {
      this.loading = true;
      transportTaskP04TransportTaskList(this.queryParams).then((response) => {
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
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleUpdate(row) {
      console.log("任务详情");
      console.log(row.id, row.shipmentType);
      this.$router.push({
        path: "/detailTasksManagement/" + row.id,
        query: { shipmentType: row.shipmentType },
      });
    },
    // handleRemove(row) {
    //   console.log('删除');
    //   // tRouteP01RouteDelete(row.id).then((res) => {
    //   //   this.getList();
    //   // });
    // },
  },
};
</script>

<style lang="scss" >
.myTime {
  .el-input--medium .el-input__inner {
    height: 32px;
  }
}
</style>
<template>
  <div class="app-container">
    <!-- 异常情况 -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="上报人" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入运单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="异常类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择异常类型"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option
            v-for="item in abnormalOption"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
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
      <el-form-item label="审批状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择审批状态"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        >
          <el-option
            v-for="item in statusOption"
            :key="item.value"
            :label="item.label"
            :value="item.value"
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
        <el-table-column label="上报人" align="center" prop="createBy" />
        <el-table-column label="上报时间" align="center" prop="createTime" />
        <el-table-column label="运单号" align="center" prop="shipmentXid" />
        <el-table-column label="任务线路" align="center" prop="routeName" />
        <el-table-column label="异常类型" align="center" prop="typeEnum" />
        <el-table-column label="审批状态" align="center" prop="statusEnum" />
        <!-- <el-table-column label="加班费" align="center" prop="overtimeAmount" />
        <el-table-column label="到货起点" align="center" prop="origin" />
        <el-table-column label="倒货终点" align="center" prop="destination" /> -->
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.status == 1"
              size="small"
              @click="handleUpdate(scope.row)"
              plain
              type="primary"
              v-hasPermi="['pfservercar:transportException:check']"
              >审批</el-button
            >
            <el-button
              v-else
              size="small"
              type="primary"
              @click="handleRemove(scope.row)"
              v-hasPermi="['pfservercar:transportException:info']"
              >查看</el-button
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
    </el-form>
  </div>
</template>

<script>
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  transportExceptionP06TransportTaskExceptionList,
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
      //   异常下拉
      abnormalOption: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "运行异常",
          value: 1,
        },
        {
          label: "任务异常",
          value: 2,
        },
      ],
      //   线路下拉
      option: [],
      //   状态下拉
      statusOption: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "待审核",
          value: 1,
        },
        {
          label: "已通过",
          value: 2,
        },
        {
          label: "已拒绝",
          value: 3,
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
    // this.getDicts("t_transport_task_status").then((response) => {
    //   this.statusOption = response.data;
    // });
    routerBox({}).then((res) => {
      this.option = res.data;
    });
  },
  methods: {
    //   查询列表
    getList(bool) {
      this.loading = true;
      transportExceptionP06TransportTaskExceptionList(this.queryParams).then(
        (response) => {
          for (const item of response.rows) {
            item.total = item.unitPrice * item.amount;
          }
          this.repairList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
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
      console.log("审批");
      console.log(row.id, row.shipmentType);
      this.$router.push({
        path: "/detailAbnormalSituation/" + row.id,
      });
    },
    handleRemove(row) {
      console.log('查看');
     this.$router.push({
        path: "/editAbnormalSituation/" + row.id,
      });
    },
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

<template>
  <div class="app-container">
    <!-- 任务成本核算TODO: -->
    <el-form label-width="100px">
      <el-form-item label="菜单">
        <el-cascader
          v-model="checkOptions_id"
          @change="checkOptions_Change"
          :options="checkOptions"
          :props="checkProps"
          collapse-tags
          clearable
        ></el-cascader>
      </el-form-item>
    </el-form>

    <!-- <div >
      
     
    </div> -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker
          v-model="queryParams.startTime"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '199px' }"
          placeholder="请选择开始时间"
          clearable
          @keyup.enter.native="handleQuery"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="finshTime">
        <el-date-picker
          v-model="queryParams.finshTime"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '199px' }"
          placeholder="请选择结束时间"
          clearable
          @keyup.enter.native="handleQuery"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="车牌号" prop="mainLicensePlate">
        <el-input
          v-model="queryParams.mainLicensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务" prop="businessType">
        <el-input
          v-model="queryParams.businessType"
          placeholder="请输入业务"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
        :importantHasPermi="['pfservercar:cost:export']"
      />
    </el-form>

    <div class="costAccounting">
      <span>合计费用：{{ totalCost }} </span>
      <span>合计收入：{{ taskCost }}</span>
      <span>盈亏：{{ profitAndLoss }}</span>
    </div>
    <el-table v-loading="loading" :data="repairList">
      <el-table-column label="序号" align="center" type="index" width="50">
      </el-table-column>
      <!-- <el-table-column label="运单号" align="center" prop="shipmentXid" />
      <el-table-column label="线路" align="center" prop="routeName" />
      <el-table-column label="业务" align="center" prop="businessType" />
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
      <el-table-column label="驾驶员工资" align="center" prop="driverPay" />
      <el-table-column label="管理人员薪酬" align="center" prop="adminPay" />
      <el-table-column label="外包服务费" align="center" prop="serviceCost" />
      <el-table-column label="燃料费用" align="center" prop="fuelCost" />
      <el-table-column label="ETC费用" align="center" prop="etcCost" />
      <el-table-column label="检车费" align="center" prop="inspectionFee" />
      <el-table-column label="保险费" align="center" prop="insurance" />
      <el-table-column label="维保费用" align="center" prop="maintenanceCost" />
      <el-table-column label="轮胎费用" align="center" prop="trieCost" />
      <el-table-column label="尿素费用" align="center" prop="ureaCost" />
      <el-table-column
        label="低值易耗品费用"
        align="center"
        prop="consumablesCost"
      />
      <el-table-column
        label="折旧费用"
        align="center"
        prop="depreciationCost"
      />
      <el-table-column
        label="信息系统服务费"
        align="center"
        prop="systemCost"
      />
      <el-table-column label="费用合计" align="center" prop="totalCost" />
      <el-table-column label="计费方式" align="center" prop="feeType" />
      <el-table-column label="任务收入" align="center" prop="taskCost" />
      <el-table-column label="盈亏" align="center" prop="profitAndLoss" /> -->
      <el-table-column v-for="(item,index) in checkOptions1" :key="index" :label="item.label" align="center" :prop="item.value" />
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
  costN04List,
  transportTaskN05RouteName,
} from "@/api/dataStatistics/index";
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
      routerOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        startTime: undefined,
        finshTime: undefined,
        mainLicensePlate: undefined,
        businessType: undefined,
        routeId: undefined,
      },
      totalCost: 0,
      taskCost: 0,
      profitAndLoss: 0,
      checkProps: { multiple: true },
      checkOptions_id: [],
      checkOptions1: [],
      checkOptions: [
        { label: "运单号", value: "shipmentXid" },
        { label: "线路", value: "routeName" },
        { label: "业务", value: "businessType" },
        { label: "主车车牌号", value: "mainLicensePlate" },
        { label: "挂车车牌号 ", value: "hangLicensePlate" },
        { label: "驾驶员工资 ", value: "driverPay" },
        { label: "管理人员薪酬", value: "adminPay" },
        { label: "外包服务费", value: "serviceCost" },
        { label: "燃料费用", value: "fuelCost" },
        { label: "ETC费用", value: "etcCost" },
        { label: "检车费", value: "inspectionFee" },
        { label: "保险费", value: "insurance" },
        { label: "维保费用", value: "maintenanceCost" },
        { label: "轮胎费用 ", value: "trieCost" },
        { label: "尿素费用 ", value: "ureaCost" },
        { label: "低值易耗品费用", value: "consumablesCost" },
        { label: "折旧费用", value: "depreciationCost" },
        { label: "信息系统服务费", value: "systemCost" },
        { label: "费用合计", value: "totalCost" },
        { label: "计费方式", value: "feeType" },
        { label: "任务收入", value: "taskCost" },
        { label: "盈亏", value: "profitAndLoss" },
      ],
    };
  },
  created() {
    this.getList(true);
    transportTaskN05RouteName().then((res) => {
      this.routerOptions = res.rows;
    });
    for (const item of this.checkOptions) {
      this.checkOptions_id.push(item.value)
    }
    this.checkOptions1 = this.checkOptions
  },
  methods: {
    checkOptions_Change(val) {
      console.log(val);
      let arr = [];
      for (const item of val) {
        for (const data of this.checkOptions) {
          if (item[0] == data.value) {
            arr.push(data);
          }
        }
      }
      this.checkOptions1 = arr;
    },
    /** 查询列表 */
    getList(bool) {
      this.loading = true;
      costN04List(this.queryParams).then((response) => {
        if (bool) {
          this.repairListOption = response.rows;
        }
        let totalCost = 0,
          taskCost = 0,
          profitAndLoss = 0;
        for (const item of response.rows) {
          item.profitAndLoss =
            item.profitStatus == 2 ? ~item.profitAndLoss : item.profitAndLoss;
          item.total = item.unitPrice * item.amount;
          totalCost += item.totalCost;
          taskCost += item.taskCost;
          profitAndLoss += item.profitAndLoss;
        }
        this.totalCost = totalCost;
        this.taskCost = taskCost;
        this.profitAndLoss = profitAndLoss;
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 导出
    handleImportant() {
      this.download(
        "/pfserver-car/cost/N04Export",
        {
          ...this.queryParams,
        },
        `cost.xlsx`
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
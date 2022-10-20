<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="106px"
    >
      <el-form-item label="工资日期" label-position="right">
        <el-date-picker
          clearable
          size="small"
          v-model="queryParams.vacationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="审批状态">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择审批状态"
          clearable
        >
          <el-option
            v-for="item in status"
            :key="item.value"
            :label="item.value"
            :value="item.label"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="下发状态">
        <el-select
          v-model="queryParams.salaryStatus"
          placeholder="请选下发状态"
          clearable
        >
          <el-option
            v-for="item in IssuedState"
            :key="item.value"
            :label="item.value"
            :value="item.label"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="float: right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
          >查询</el-button
        >
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleacCounting"
          v-hasPermi="['pfserverparts:salaryRecords:wageaccounting']"
          >工资核算</el-button
        >
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="工资日期" align="center" prop="vacationDate">
        <template slot-scope="scope">
          <span>{{
            scope.row.vacationDate.substring(
              0,
              scope.row.vacationDate.lastIndexOf("-")
            )
          }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工资总额" align="center" prop="totalAmount" />
      <!-- <el-table-column  label="审核状态" align="center" prop="status" /> -->
      <el-table-column label="审核状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">待审核</span>
          <span v-else-if="scope.row.status === 2">已通过</span>
          <span v-else-if="scope.row.status === 3">已拒绝</span>
          <span v-else-if="scope.row.status === 4">已保存</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <!-- <el-table-column  label="工资单"   align="center" prop="salaryStatus"/> -->
      <el-table-column label="工资单" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.salaryStatus == 1">未下发</span>
          <span v-else-if="scope.row.salaryStatus == 2">已下发</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="300px"
      >
        <template slot-scope="scope">
          <el-button
            size="small "
            type="primary"
            icon="el-icon-edit"
            @click="handleClickapproval(scope.row)"
            style="color: #fff"
            >查看明细</el-button
          >

          <el-button
            size="small "
            type="primary"
            v-if="scope.row.status == 1"
            v-hasPermi="['pfserverparts:salaryRecords:review']"
            @click="handlePayrollaccounting(scope.row)"
            >审批</el-button
          >
          <el-button
            size="small "
            type="primary"
            v-if="scope.row.salaryStatus == 1"
            @click="downClick(scope.row)"
            v-hasPermi="['pfserverparts:salaryRecords:issue']"
            >下发工资单</el-button
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
import {
  salaryRecordsC07List,
  C09WageAc,
  C09Issue,
} from "@/api/payment/salary";

export default {
  name: "Card",
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
      // 薪酬管理列表
      cardList: [],
      // 充电车列表
      ChargingCard: [],
      // 审批状态
      status: [
        { label: "1", value: "待审批" },
        { label: "2", value: "已通过" },
        { label: "3", value: "已拒绝" },
        { label: "4", value: "已保存" },
      ],
      // 下发状态
      IssuedState: [
        { label: 2, value: "已下发" },
        { label: 1, value: "未下发" },
      ],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vacationDate: "",
        salaryStatus: "",
        status: "",
        vactionTime: "",
      },

      // 新增定额参数
      addform: {
        routeId: "",
        routeName: "",
        vehicleModel: "",
        vehicleModelName: "",
        quota: "",
        name: "",
      },
      // 表单参数
      form: {
        licensePlate: undefined,
        etcCardNumber: undefined,
        orderNumber: undefined,
        status: null,
        orderTime: undefined,
        method: undefined,
        obu: undefined,
        id: undefined,
      },

      vehicleOptions: [],
      // 线路下拉
      routerOptions: [],

      // 表单校验
      rules: {},
      centerDialogVisible: false,
      //导入弹窗
      dialogVisible1: false,
      diaTitle1: "导入",
      vehicleModelNamelist: [],
      oilslist: [],
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    downClick(row) {
      let id = row.id;
      C09Issue(id).then((res) => {
        this.msgSuccess(res.msg);
        this.getList();
      });
    },
    // 审批
    handlePayrollaccounting(row) {
      this.$router.push({
        path: "/approvalOfSalaryDetails/",
        query: { id: row.id, vactionTime: row.vactionTime },
      });
    },
    cancelDialog() {
      this.centerDialogVisible = true;
    },
    handleChange(val) {
      console.log(val, JSON.stringify(this.vehicleModelNamelist));
      for (const item of this.vehicleModelNamelist) {
        if (val == item.id) {
          console.log(item);
          console.log(item.licensePlate);
          this.form.licensePlate = item.licensePlate;
        }
      }
    },
    /** 薪酬管理列表 */
    getList() {
      this.loading = true;
      salaryRecordsC07List(this.queryParams).then((response) => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 工资核算
    handleacCounting() {
      C09WageAc({}).then((res) => {
        this.getList();
      });
    },
    // 查看明细
    handleClickapproval(row) {
      console.log(row.status);
      // row.status
      switch (row.status) {
        case 1:
          // 待审核
          return this.$router.push({
            path: "/detailsReviewed/",
            query: { id: row.id },
          });
          break;
        case 2:
          //  已通过
          return this.$router.push({
            path: "/approvedDetails/",
            query: { id: row.id },
          });
          break;
        case 3:
          //  已拒绝
          return this.$router.push({
            path: "/rejectedDetails/",
            query: { id: row.id, vacationDate: row.vacationDate },
          });
          break;
        case 4:
          // 已保存
          return this.$router.push({
            path: "/payrollAccounting/",
            query: { id: row.id },
          });
          break;
        default:
          break;
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.addform = {
        licensePlate: null,
        oilCardNumber: null,
        oils: null,
        cardTime: null,
        dueTime: null,
        id: null,
      };
      this.resetForm("addform");
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

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加油卡定额管理";
    },
    //导入按钮
    importClick() {
      this.dialogVisible1 = true;
    },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["addform"].validate((valid) => {
        if (valid) {
          if (this.addform.id != null) {
            updateQuota(this.addform).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuota(this.addform).then((response) => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
  },
};
</script>
<style scoped>
.textColor {
  color: red;
}
</style>

<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="106px"
    >
      <el-form-item
        >{{ this.longDate }}驾驶员工资明细（长途驾驶员）</el-form-item
      >
      <el-form-item style="float: right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
          >返回</el-button
        >
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleCheckagain"
          >重新核算</el-button
        >
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="cardList"
      border
      :summary-method="getSummaries"
      show-summary
    >
      <el-table-column label="驾驶员姓名" align="center" prop="driverName" />
      <el-table-column label="底薪" align="center" prop="basicSalary" />
      <el-table-column
        label="工龄工资"
        align="center"
        prop="wageForSeniority"
      />
      <el-table-column label="绩效工资" align="center" prop="meritSalary" />
      <el-table-column label="加班补助" align="center" prop="overtimePay" />
      <el-table-column label="篷布补助" align="center" prop="tarpaulin" />
      <el-table-column
        label="异地等待装卸货补助"
        align="center"
        prop="waitPay"
      />
      <el-table-column label="流量补助" align="center" prop="flowPay" />
      <el-table-column label="其他补助" align="center" prop="otherPay" />
      <el-table-column label="奖励" align="center" prop="rewardFee" />
      <el-table-column label="罚款" align="center" prop="penaltyFee" />
      <el-table-column label="实发工资" align="center" prop="salary" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <!-- <el-table-column label="操作"     align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">       
           <el-button
            size="small "
            type="primary"
            icon="el-icon-edit"
            @click="handleClickapproval(scope.row)"
            v-hasPermi="['system:card:edit']"
            style="color:#fff"
          >编辑</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    <!-- 短期 -->
    <el-form style="margin-top: 30px">
      <el-form-item
        >{{ this.longDate }}驾驶员工资明细（短途驾驶员）</el-form-item
      >
    </el-form>
    <el-table
      v-loading="loading"
      :data="scardList"
      border
      style="margin-top: 10px"
      :summary-method="getSummaries1"
      show-summary
    >
      <el-table-column label="驾驶员姓名" align="center" prop="driverName" />
      <el-table-column label="底薪" align="center" prop="basicSalary" />
      <el-table-column
        label="工龄工资"
        align="center"
        prop="wageForSeniority"
      />
      <el-table-column label="绩效工资" align="center" prop="meritSalary" />
      <el-table-column label="加班补助" align="center" prop="overtimePay" />
      <!-- <el-table-column
        label="异地等待装卸货补助"
        align="center"
        prop="waitPay"
      />
      <el-table-column label="流量补助" align="center" prop="flowPay" /> -->
      <el-table-column label="其他补助" align="center" prop="otherPay" />
      <el-table-column label="奖励" align="center" prop="rewardFee" />
      <el-table-column label="罚款" align="center" prop="penaltyFee" />
      <el-table-column label="实发工资" align="center" prop="salary" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <!-- <el-table-column label="操作"     align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">       
           <el-button
            size="small "
            type="primary"
            icon="el-icon-edit"
            @click="handleClickapproval(scope.row)"
            v-hasPermi="['system:card:edit']"
            style="color:#fff"
          >查看明细</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <!-- 审核结果 -->
    <el-form ref="elForm" style="margin-top: 30px">
      <div class="wrap">
        <div class="text">审批记录</div>
        <div class="line"></div>
      </div>
      <el-table :data="auditResult" border>
        <el-table-column label="审批级别" align="center" prop="typeEnum" />
        <el-table-column label="审批时间" align="center" prop="reviewTime" />
        <el-table-column label="审批角色" align="center" prop="roleName" />
        <el-table-column label="审批人姓名" align="center" prop="userName" />
        <!-- <el-table-column label="审批状态" align="center" >
          <template slot-scope="scope">
              <span v-for="scope.row.status === 1">待审核</span>
          </template>
      <el-table-column/> -->
        <el-table-column label="审核状态" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">待审核</span>
            <span v-else-if="scope.row.status === 2">已审核</span>
            <span v-else-if="scope.row.status === 3">已驳回</span>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column
          label="备注"
          align="center"
          class-name="small-padding fixed-width"
          prop="reviewRemarks"
        >
        </el-table-column>
      </el-table>
    </el-form>
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
import { C06Detail } from "@/api/payment/payrollAccounting";
import { sysExamineTaskList } from "@/api/payment/subsidySee";
import { C09Revoke } from "@/api/payment/detailsReviewed";
// import {C10Export} from '@/api/payment/approvedDetails'
import { C09WageAc } from "@/api/payment/salary";
export default {
  name: "Card",
  data() {
    return {
      // 长途日期
      longDate: "",
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
      // 长途驾驶员列表
      cardList: [],
      // 短途驾驶员列表
      scardList: [],
      // 审核
      auditResult: [],
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
        { label: "1", value: "已下发" },
        { label: "2", value: "未下发" },
      ],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: "",
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
  created() {
    this.getList();
    this.getsysExamineTaskList();
  },

  methods: {
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = "合计";
        } else if (index >= 1 && index <= 11) {
          const values = data.map((item) => Number(item[column.property]));
          if (!values.every((value) => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                // 保存了两位小数点
                return Math.floor((prev + curr) * 100) / 100;
              } else {
                // 保存了两位小数点
                return Math.floor(prev * 100) / 100;
              }
            }, 0);
          } else {
            sums[index] = "N/A";
          }
        } else {
          sums[index] = "";
        }
      });
      return sums;
    },

    getSummaries1(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        // let num =
        if (index === 0) {
          sums[index] = "合计";
        }
        // index >= 3 || index <= 11 是你需要计算的列
        // 当然也可以指定一个或者多个列
        // index === 2 || index === 3 这样值求和第二个与第三个列
        else if (index >= 1 && index <= 8) {
          const values = data.map((item) => Number(item[column.property]));
          if (!values.every((value) => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                // 保存了两位小数点
                return Math.floor((prev + curr) * 100) / 100;
              } else {
                // 保存了两位小数点
                return Math.floor(prev * 100) / 100;
              }
            }, 0);
          } else {
            sums[index] = "N/A";
          }
        } else {
          sums[index] = "";
        }
      });
      return sums;
    },
    // 获取审批记录
    getsysExamineTaskList() {
      var list = {
        relationId: this.$route.query.id,
        type: 10,
      };
      sysExamineTaskList(list).then((res) => {
        this.auditResult = res.data.sysExamineUserTaskList;
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
    /** 驾驶员列表管理列表 */
    getList() {
      this.loading = true;
      C06Detail(this.$route.query.id).then((response) => {
        // 长途
        this.cardList = response.data.LongRecord;
        // 短途
        this.scardList = response.data.ShortRecord;
        this.loading = false;
        // console.log("时间"+this.cardList[0].vactionTime)
        var timeArr = this.cardList[0].vactionTime
          .replace(" ", ":")
          .replace(/\:/g, "-")
          .split("-");

        var nian = timeArr[0];
        var yue = timeArr[1];
        this.longDate = nian + "-" + yue;
        //  console.log(nian + '-' + yue );
        //  console.log(this.longDate)
      });
    },
    // 重新核算
    handleCheckagain() {
      // console.log(this.cardList[0].id)query:{id:row.id,vacationDate:row.vacationDate}}

      // 撤销
      C09Revoke(this.$route.query.id).then((res) => {
        C09WageAc({}).then((res) => {
          // this.$router.push({
          //   path: "/payrollAccounting/",
          //   query: {
          //     id: this.$route.query.id,
          //     vacationDate: this.longDate,
          //     status: 3,
          //   },
          // });
          this.$router.push('/salaryAdministration/salary')
        });
      });

      // 核算
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
    /** 返回 */
    handleQuery() {
      this.$router.back();
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
<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
  width: 10%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
  font-weight: 400;
}
.line {
  width: 90%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
</style>
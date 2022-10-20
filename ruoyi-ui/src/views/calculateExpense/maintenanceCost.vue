<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="106px"
    >
      <el-form-item label="	维修单号" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入维修单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="	主车车牌号" prop="mainLicensePlate">
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
      <el-form-item label="开始时间" prop="startTime" label-position="right">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="finishTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 198px"
          v-model="queryParams.finishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item v-if="isAdmin" label="维修服务站">
        <el-select
          v-model="queryParams.garageName"
          clearable
          placeholder="请选择维修服务站"
        >
          <el-option
            v-for="item in H02RGarageList"
            :key="item.id"
            :label="item.name"
            :value="item.name"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="维保类别">
        <el-select
          v-model="queryParams.type"
          clearable
          placeholder="请选择维保类别"
        >
          <el-option
            v-for="item in typeOption"
            :key="item.dictValue"
            :label="item.dictLabel"
            :value="item.dictValue"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="维保类型">
        <el-select
          v-model="queryParams.repairType"
          clearable
          placeholder="请选择维保类型"
        >
          <el-option
            v-for="item in typeOption1"
            :key="item.dictValue"
            :label="item.dictLabel"
            :value="item.dictValue"
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
        <!-- <el-button type="cyan" icon="el-icon-search" size="small "  @click="importClick()">导入</el-button> -->
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="small "
          @click="handleExport"
          v-hasPermi="['pfservercar:repairinspection:export']"
          >导出</el-button
        >
      </el-form-item>
    </el-form>
    <el-form label-width="100px" class="textColor">
      <el-form-item style="display: inline-block" label="合计金额:">{{
        totalCost
      }}</el-form-item>
      <!-- <el-form-item style="display:inline-block" label="充电次数:" prop="licensePlate">{{ChargingCard.rechargeNumber}}</el-form-item>
      <el-form-item style="display:inline-block" label="充电电量:" prop="licensePlate">{{ChargingCard.rechargeElectricityTotal+"KWh"}}</el-form-item>
      <el-form-item style="display:inline-block" label="充电金额:" prop="licensePlate">{{ChargingCard.rechargeAmountTotal+"元"}}</el-form-item>
      <el-form-item style="display:inline-block" label="持续时长:" prop="licensePlate">{{ChargingCard.rechargeDurationTotal+"分钟"}}</el-form-item>   -->
    </el-form>
    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="维修单号" align="center">
        <template slot-scope="scope">
          {{ scope.row.repair.code }}
        </template>
      </el-table-column>
      <!-- <el-table-column label="维修时间" align="center" prop="completeTime" /> -->
      <el-table-column label="维修时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.repair.completeTime }}
        </template>
      </el-table-column>
      <el-table-column
        label="主车车牌号"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.repair.mainLicensePlate }}
        </template>
      </el-table-column>
      <el-table-column
        label="挂车车牌号"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.repair.hangLicensePlate }}
        </template>
      </el-table-column>
      <el-table-column label="维修服务站" align="center" prop="garageName" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column
        label="维保类别"
        align="center"
      >
        <template slot-scope="scope">
          {{ typeFormat(scope.row.repair.type) }}
        </template>
      </el-table-column>
      <el-table-column
        label="维保类型"
        align="center"
      >
        <template slot-scope="scope">
          {{ typeFormat1(scope.row.repair.repairType) }}
        </template>
      </el-table-column>
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="单价（元）" align="center" prop="unitPrice" />
      <el-table-column label="工时数" align="center" prop="duration" />
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="金额（元）" align="center" prop="amount" />
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 导入弹窗-->
    <!-- <el-dialog
      :title="diaTitle1"
      :visible.sync="dialogVisible1"
      width="600px"
      :before-close="handleClose1"
    >
      <div style="width:640px; height:200px;">
        <el-form
          ref="form"
          :model="form"
          label-width="100px"
          style="float:left;"
        >
          <el-form-item label="导入文件："></el-form-item>
        </el-form>
        <el-upload
          style="float:left; margin-left:10px;"
          drag
          ref="upload"
          class="upload-btn"
          action="string"
          accept=".xls, .xlsx"
          multiple
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div class="el-upload__tip" slot="tip">
            <a href="./template.xlsx" download="下载模板.xlsx">下载模板</a>
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadClick">导入</el-button>
      </span>
    </el-dialog> -->
    <!-- <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->
  </div>
</template>

<script>
import {
  FuelRechargeList,
  I11FuelRechargeImport,
} from "@/api/card/recordOfCharging";
import { formatDate } from "@/utils/index";
import { M01List, H02RGarage } from "@/api/calculateExpense/maintenanceCost";
export default {
  name: "Card",
  data() {
    return {
      isAdmin: 0,
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
      totalCost: "",
      cardList: [],
      // 充电车列表
      ChargingCard: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        code: undefined,
        mainLicensePlate: undefined,
        hangLicensePlate: undefined,
        startTime: this.parseTime(new Date(), "{y}-{m}") + "-01",
        finishTime: this.parseTime(new Date(), "{y}-{m}-{d}"),
        garageName: undefined,
        name: undefined,
        type: undefined,
        repairType: undefined,
      },
      typeOption: [
        {
          dictLabel: "主车维保",
          dictValue: 1,
        },
        {
          dictLabel: "挂车维保",
          dictValue: 2,
        },
      ],
      typeOption1: [
        {
          dictLabel: "正常维修",
          dictValue: 1,
        },
        {
          dictLabel: "事故维修",
          dictValue: 2,
        },
        {
          dictLabel: "保养",
          dictValue: 3,
        },
      ],
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
      // 维修服务站下拉
      H02RGarageList: [],
      // 表单校验
      rules: {},
      //导入弹窗
      dialogVisible1: false,
      diaTitle1: "导入",
      vehicleModelNamelist: [],
      oilslist: [],
    };
  },
  filters: {
    time(value) {
      return formatDate(value, false);
    },
  },
  created() {
    this.getList();
    this.getH02RGarage();
  },
  methods: {
    typeFormat(row) {
      return this.selectDictLabel(this.typeOption, row);
    },
    typeFormat1(row) {
      return this.selectDictLabel(this.typeOption1, row);
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
    getH02RGarage() {
      H02RGarage({}).then((res) => {
        this.H02RGarageList = res.data;
      });
    },
    /** 维修保养费用列表 */
    getList() {
      this.loading = true;
      M01List(this.queryParams).then((response) => {
        console.log(response);
        this.cardList = response.rows[0].list;
        this.totalCost = response.rows[0].totalCost;
        this.total = response.total;

        this.isAdmin = response.rows[0].isAdmin;
        this.loading = false;
      });
    },
    // // 异常记录
    // handleExceptionRecord() {
    //   this.$router.push({ path: "/exceptionRecord/" });
    // },
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
    // importClick() {
    //   this.dialogVisible1 = true;
    // },
    // // 导入弹窗关闭
    // handleClose1() {
    //   this.dialogVisible1 = false;
    // },
    // uploadClick() {
    //   if (this.upfile) {
    //     console.log(this.upfile);
    //     let fd = new FormData();
    //     fd.append("file", this.upfile); // 要提交给后台的文件
    //     I11FuelRechargeImport(fd)
    //       .then(response => {
    //         console.log("提交文件", response);
    //         this.$message({
    //           message: "导入成功",
    //           type: "success"
    //         });
    //         this.dialogVisible1 = false;
    //       })
    //       .catch(err => {
    //         console.log(err);
    //       });
    //   } else {
    //     this.$message({
    //       message: "请导入文件"
    //     });
    //   }
    // },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "/pfserver-car/repair/inspection/M01Export",
        {
          ...this.queryParams,
        },
        `repair_inspection.xlsx`
      );
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

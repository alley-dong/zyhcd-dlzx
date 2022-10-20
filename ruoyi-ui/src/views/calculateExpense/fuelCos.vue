<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="106px"
    >
      <el-form-item label="	车牌号">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" label-position="right">
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
      <el-form-item label="结束时间" label-position="right">
        <el-date-picker
          clearable
          size="small"
          
          style="width: 200px"
          v-model="queryParams.overTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        
        </el-date-picker>
      </el-form-item>
      <el-form-item label="油品">
        <el-select v-model="queryParams.oils" placeholder="请选择" clearable>
          <el-option
            v-for="item in oilslist"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="开始时间" prop="firstTime" label-position="right">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.transactionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="结束时间" prop="finishTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 198px"
          v-model="queryParams.transactionEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item> -->
      <el-form-item style="float: right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
          >查询</el-button
        >
        <el-button
          type="warning"
          icon="el-icon-download"
          size="small "
          @click="importClick()"
          v-hasPermi="['pfservercar:cost:import']"
          >导入</el-button
        >
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="small "
          @click="handleExport"
          v-hasPermi="['pfservercar:cost:export']"
          >导出</el-button
        >
      </el-form-item>
    </el-form>
    <el-form label-width="100px" class="textColor">
      <el-form-item style="display: inline-block" label="含税(元):">{{
        totalCost.taxAmountTotal
      }}</el-form-item>
      <el-form-item style="display: inline-block" label="不含税(元):">{{
        totalCost.amountTotal
      }}</el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column
        label="车牌号（主车）"
        align="center"
        prop="licensePlate"
      />
      <el-table-column label="油品" align="center" prop="oils" />
      <el-table-column label="油量（L）" align="center" prop="oilQuantity" />
      <el-table-column
        label="单价（元含税）"
        align="center"
        prop="taxUnitPrice"
      />
      <el-table-column label="加油金额（含税）" align="center" prop="taxAmount" />
      <el-table-column label="交易时间" align="center" prop="transactionTime" />
      <el-table-column
        label="单价（元 不含税）"
        align="center"
        prop="unitPrice"
      />
      <el-table-column
        label="加油金额（元不含税）"
        align="center"
        prop="amount"
      />
      <el-table-column label="税率" align="center" prop="taxRate">
        <template slot-scope="scope">
          <span>{{ scope.row.taxRate + "%" }}</span>
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
    <!-- 导入弹窗-->
    <el-dialog
      :title="diaTitle1"
      :visible.sync="dialogVisible1"
      width="600px"
      :before-close="handleClose1"
    >
      <div style="width: 640px; height: 200px">
        <el-form
          ref="form"
          :model="form"
          label-width="100px"
          style="float: left"
        >
          <el-form-item label="导入文件："></el-form-item>
        </el-form>
        <el-upload
          style="float: left; margin-left: 10px"
          drag
          ref="upload"
          class="upload-btn"
          action="string"
          accept=".xls, .xlsx"
          :before-upload="onBeforeUploadImage"
          :http-request="UploadFile"
          multiple
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div class="el-upload__tip" slot="tip">
            <!-- <a href="/燃料费导入模板.xlsx" download="下载模板.xlsx">下载模板</a> -->
            <a href="/fuelFee.xls" download="燃料费导入模板.xls">下载模板</a>
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadClick">导入</el-button>
      </span>
    </el-dialog>
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
import {
  M06FuelCostList,
  M06FuelCostPullDown,
  M06FuelCostImportn,
} from "@/api/calculateExpense/fuelCos";
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
      totalCost: "",
      cardList: [],
      // 充电车列表
      ChargingCard: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // cardList
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        // pageSize: 10,
        licensePlate: "",
        oils: "",
        oilQuantity: "",
        amount: "",
        transactionTime: "",
        unitPrice: "",
        taxRate: "",
        startTime:this.parseTime(new Date(), "{y}-{m}") + "-01",
        overTime:this.parseTime(new Date(), "{y}-{m}-{d}"),
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
      //导入弹窗
      dialogVisible1: false,
      diaTitle1: "导入",
      vehicleModelNamelist: [],
      // 油品下拉
      oilslist: [],
    };
  },
  created() {
    this.getList();
    this.getM06FuelCostPullDown();
  },
  methods: {
    getM06FuelCostPullDown() {
      M06FuelCostPullDown().then((res) => {
        this.oilslist = res.rows;
      });
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
    /** 费用统计-燃料费用统计列表*/
    getList() {
      this.loading = true;
      M06FuelCostList(this.queryParams).then((response) => {
        console.log(response);
        this.cardList = response.rows[0].list;
        this.totalCost = response.rows[0];
        this.total = response.total;
        this.loading = false;
      });
    },
    // 异常记录
    handleExceptionRecord() {
      this.$router.push({ path: "/exceptionRecord/" });
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
      this.$refs.upload.clearFiles()
      this.upfile = null;
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    uploadClick() {
      if (this.upfile) {
        console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件
        M06FuelCostImportn(fd)
          .then((response) => {
            console.log("提交文件", response);
            this.$message({
              message: "导入成功",
              type: "success",
            });
            this.$refs.upload.clearFiles()
            this.upfile = null;
            this.dialogVisible1 = false;
            this.getList();
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.$message({
          message: "请导入文件",
        });
      }
    },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "/pfserver-car/fuelCost/M06FuelCostExport",
        {
          ...this.queryParams,
        },
        `system_fuelCost.xlsx`
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

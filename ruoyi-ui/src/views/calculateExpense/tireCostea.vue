<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="106px"
    >
      <el-form-item label="	车牌号" prop="code">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="规格" label-width="50px">
        <el-select
          v-model="queryParams.specification"
          clearable
          placeholder="请选择规格"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.specification"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item> -->
      <el-form-item
        label="选择开始时间"
        prop="startTime"
        label-position="right"
      >
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
      <!-- <el-form-item label="油品">
        <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="折旧月份" prop="firstTime" label-position="right">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.transactionTime"
          type="month"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="结束时间" prop="finshTime">
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
      <el-form-item style="float:right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="getList"
          >查询</el-button
        >
        <!-- <el-button type="cyan" icon="el-icon-search" size="small "  @click="importClick()">导入</el-button> -->
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="small "
          @click="handleExport"
          v-hasPermi="['parts:goodsReceivingRecord:export']"
          >导出</el-button
        >
      </el-form-item>
    </el-form>
    <el-form label-width="150px" class="textColor">
      <el-form-item style="display:inline-block" label="合计金额（元）:">{{
        totalCost
      }}</el-form-item>
      <!-- <el-form-item style="display:inline-block" label="营运年检费合计(元):" >{{totalCost.totalOperateInspectCost}}</el-form-item>
      <el-form-item style="display:inline-block" label="复检费合计(元):" >{{totalCost.totalReviewCost}}</el-form-item>
      <el-form-item style="display:inline-block" label="检车费合计(元):" >{{totalCost.allTotalCost}}</el-form-item> -->
    </el-form>
    <!-- <el-form-item label-width="150px" class="textColor">
      <el-form-item label="车辆年检费合计(元):" >{{totalCost.totalVehiceInspectCost}}</el-form-item>
      <el-form-item label="营运年检费合计(元):" >{{totalCost.totalOperateInspectCost}}</el-form-item>
      <el-form-item label="复检费合计(元):" >{{totalCost.totalReviewCost}}</el-form-item>
      <el-form-item label="检车费合计(元):" >{{totalCost.allTotalCost}}</el-form-item>
    </el-form-item> -->
    <el-table :data="cardList" border>
      <el-table-column label="序号" type="index" align="center" />
      <el-table-column
        label="车牌号(主车)"
        align="center"
        prop="licensePlate"
      />
      <el-table-column label="规格" align="center" prop="specification" />
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="单价（元）" align="center" prop="unitPrice" />
      <el-table-column label="金额（元）" align="center" prop="insuranceCostTotal" />
      <el-table-column label="领用时间" align="center" prop="receiveTime" />
      <!-- <el-table-column label="备注" align="center" prop="" /> -->
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
// import { M08List } from "@/api/calculateExpense/checkThefare";
import { M10List,M11Info } from "@/api/calculateExpense/costOfurea";
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
      queryParams: {
        pageNum:1,
        pageSize:10,
        licensePlate: undefined,
        specification: undefined,
        number: undefined,
        unitPrice: undefined,
        amountSum: undefined,
        reviewTime: undefined,
        startTime:this.parseTime(new Date(), "{y}-{m}") + "-01",
        finishTime:this.parseTime(new Date(), "{y}-{m}-{d}"),
        goodsType: 3,
      },
      options: [
        {
          value: "1",
          specification: "175"
        }
      ],
      // 新增定额参数
      addform: {
        routeId: "",
        routeName: "",
        vehicleModel: "",
        vehicleModelName: "",
        quota: "",
        name: ""
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
        id: undefined
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
      oilslist: []
    };
  },
  created() {
    this.getList();
    M11Info().then(res=>{
      console.log(res);
    })
  },
  methods: {
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
    /** 尿素费用列表*/
    getList() {
      this.loading = true;
      M10List(this.queryParams).then(response => {
        console.log(response);
        this.cardList = response.rows[0].list;
        this.totalCost = response.rows[0].amountSum;
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
        id: null
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
        "/pfserver-parts/parts/goodsReceivingRecord/M11Export",
        {
          ...this.queryParams
        },
        `system_goodsReceivingRecord.xlsx`
      );
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["addform"].validate(valid => {
        if (valid) {
          if (this.addform.id != null) {
            updateQuota(this.addform).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuota(this.addform).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    }
  }
};
</script>
<style scoped>
.textColor {
  color: red;
}
</style>

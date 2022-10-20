<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
    >
      <el-form-item label="	车牌号" prop="licensePlate">
        <el-select
          v-model="queryParams.licensePlates"
          multiple
          placeholder="请选择"
        >
          <el-option
            v-for="item in licensePlateList"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="交易时间" prop="firstTime" label-position="right">
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
      </el-form-item >
      <el-form-item label="油品">
        <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
      </el-form-item> -->
      <el-form-item
        label="	折旧月份起始日期"
        prop="beginDepreciationMonth"
        label-position="right"
      >
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.beginDepreciationMonth"
          type="month"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item
        label="折旧月份结束日期"
        prop="endDepreciationMonth"
        label-position="right"
      >
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.endDepreciationMonth"
          type="month"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
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
      <el-form-item style="float:right">
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
          v-hasPermi="['depreciation:cost:import']"
          >导入</el-button
        >
        <el-button
          type="info"
          icon="el-icon-upload2"
          size="small "
          @click="handleExport"
          v-hasPermi="['depreciation:cost:export']"
          >导出</el-button
        >
      </el-form-item>
    </el-form>
    <el-form label-width="150px" class="textColor" style="float:left">
      <el-form-item style="display:inline-block" label="合计折旧金额(元):">{{
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
    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="序号" type="index" align="center" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="折旧费" align="center" prop="depreciationCost" />
      <el-table-column
        label="折旧月份"
        align="center"
        prop="depreciationMonth"
      />
      <!-- <el-table-column label="营运年检费用" align="center" prop="operateInspectCost" />
      <el-table-column label="复检费" align="center" prop="reviewCost" />
      <el-table-column label="合计费用" align="center" prop="totalCost" />
      <el-table-column label="备注" align="center" prop="" /> -->
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
            <a href="/depreciationFee.xls" download="折旧费费导入模板.xls">下载模板</a>
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
  M09list,
  M09export,
  M09DepreciationCostPullDown,
  M09CostImport
} from "@/api/calculateExpense/depreciationExpense";
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
      totalCost: {
        totalVehiceInspectCost: "",
        totalOperateInspectCost: "",
        totalReviewCost: "",
        allTotalCost: ""
      },
      cardList: [],
      // 充电车列表
      ChargingCard: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licensePlates: undefined,
       
        licensePlate: undefined,
        beginDepreciationMonth: this.parseTime(new Date(), "{y}-{m}"),
        endDepreciationMonth: this.parseTime(new Date(), "{y}-{m}"),
      },

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
      oilslist: [],
      // 车辆下拉
      licensePlateList: []
    };
  },
  created() {
    this.getList();
    this.getM09DepreciationCostPullDown();
  },
  methods: {
    // 车牌号下拉
    getM09DepreciationCostPullDown() {
      M09DepreciationCostPullDown({}).then(res => {
        this.licensePlateList = res.rows;
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
    /** 折旧列表*/
    getList() {
      this.loading = true;
      M09list(this.queryParams).then(response => {
        // console.log(response)
        this.cardList = response.rows[0].list;
        this.totalCost = response.rows[0].bigDecimal;
        // console.log(totalCosts);
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
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },
    uploadClick() {
      if (this.upfile) {
        console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件

        M09CostImport(fd)
          .then(response => {
            console.log("提交文件", response);
            this.$message({
              message: "导入成功",
              type: "success"
            });
            this.dialogVisible1 = false;
            this.getList();
            this.$refs.upload.clearFiles()
            this.upfile = null;
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.$message({
          message: "请导入文件"
        });
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "/pfserver-car/depreciationCost/M09export",
        {
          ...this.queryParams
        },
        `system_depreciationCost.xlsx`
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

<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="油卡卡号" prop="oilCardNumber">
        <el-input
          v-model="queryParams.oilCardNumber"
          placeholder="请输入油卡卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="firstTime">
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
      </el-form-item>
      <el-form-item label="结束时间" prop="finishTime">
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
      </el-form-item>
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
          v-hasPermi="['pfservercar:record:import']"
          >导入</el-button
        >
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleExceptionRecord"
          >异常记录</el-button
        >
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="卡号" align="center" prop="oilCardNumber" />
      <el-table-column label="金额" align="center" prop="consume" />
      <el-table-column label="余额" align="center" prop="surplus" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column
        label="交易状态"
        align="center"
        prop="transactionStatus"
      />
      <el-table-column label="交易时间" align="center" prop="adminShowTransactionTime" />
      <el-table-column
        label="交易地点"
        align="center"
        prop="transactionAddress"
      />
      <el-table-column label="商品类别" align="center" prop="productType" />
      <el-table-column label="商品名称" align="center" prop="productName" />
      <el-table-column label="油量" align="center" prop="oilQuantity" />
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
      <el-row>
        <el-col :span="24">
          <el-form
            ref="form"
            :model="form"
            label-width="100px"
            style="float:left;"
          >
            <el-form-item label="线路名称:" prop="routeName">
              <el-select
                v-model="form.routeName"
                placeholder="请选择路线："
                @change="quotaRouterBoxData"
                clearable
                :style="{ width: '360px', 'margin-left': '10px' }"
              >
                <el-option
                  v-for="(item, index) in routerOptions"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="24">
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
                <a href="/fuelUsageRecord.xlsx" download="燃料使用记录.xlsx"
                  >下载模板</a
                >
              </div>
            </el-upload>
          </div>
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadClick">导入</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  PlantsAbnormalRecordList,
  PlantsRecordList,
  I05OilPlantsRecordImport
} from "@/api/card/fuelrecord";
import { routerBox } from "@/api/card/fuelration";
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
      // 油卡定额管理表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        // pageNum: 1,
        // pageSize: 10,
        licensePlate: "",
        oilCardNumber: "",
        transactionTime: "",
        transactionEndTime: ""
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
      //导入弹窗
      dialogVisible1: false,
      diaTitle1: "导入",
      // 表单校验
      rules: {},

      vehicleModelNamelist: [],
      oilslist: []
    };
  },
  created() {
    this.getList();
    this.routerselect();
  },
  methods: {
    // 路线下拉
    routerselect() {
      routerBox({}).then(res => {
        this.routerOptions = res.data;
      });
    },
    quotaRouterBoxData(val) {
      // console.log(val);
      for (const item of this.routerOptions) {
        if (val == item.id) {
          this.addform.routeId = item.id;
        }
      }
      // console.log(this.addform.routeId);
    },
    handleChange(val) {
      // console.log(val, JSON.stringify(this.vehicleModelNamelist));
      for (const item of this.vehicleModelNamelist) {
        if (val == item.id) {
          console.log(item);
          console.log(item.licensePlate);
          this.form.licensePlate = item.licensePlate;
        }
      }
    },
    /** 查询油料记录管理列表 */
    getList() {
      this.loading = true;
      PlantsRecordList(this.queryParams).then(response => {
        this.cardList = response.rows;
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
    importClick() {
      this.dialogVisible1 = true;
    },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    uploadClick() {
      if (this.upfile) {
        // console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件
        fd.append("routeId", this.addform.routeId); // 要提交给后台的文件
        // console.log(this.addform.routeId);
        I05OilPlantsRecordImport(fd)
          .then(response => {
            console.log("提交文件", response);
            this.$message({
              message: response.msg,
              type: "success"
            });
            this.dialogVisible1 = false;
            this.getList();
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
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
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

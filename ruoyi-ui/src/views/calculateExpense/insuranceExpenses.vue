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
         <!-- <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <!-- TODO:下面为多选 -->
        <el-select
          v-model="queryParams.licensePlates"
          multiple
          placeholder="请选择"
        >
          <el-option
            v-for="item in licensePlateList"
            :key="item.id"
            :label="item.licensePlate"
            :value="item.licensePlate"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="保险种类">
        <el-select
          placeholder="请选择保险种类"
          clearable
          v-model="queryParams.typeId"
        >
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="firstTime" label-position="right">
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
          v-model="queryParams.finshTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
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
          v-hasPermi="['car:insuranceVehicle:export']"
          >导出</el-button
        >
      </el-form-item>
    </el-form>
    <el-form label-width="100px" class="textColor">
      <el-form-item style="display: inline-block" label="合计金额:">{{
        totalCost
      }}</el-form-item>
    </el-form>
    <el-table :data="cardList" border>
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="保险类型" align="center" prop="type">
        <template slot-scope="scope">
          <div>
            {{ scope.row.type | type }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        label="保险种类"
        align="center"
        prop="insuranceTypeName"
      />
      <el-table-column label="保费（元）" align="center" prop="insuranceCost" />
      <el-table-column
        label="起保日期"
        align="center"
        prop="insuranceStartDate"
      />
      <el-table-column
        label="终保日期"
        align="center"
        prop="insuranceEndDate"
      />
      <el-table-column
        label="办理保险日期"
        align="center"
        prop="insuranceHandleTime"
      />
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
import { M09DepreciationCostPullDown } from "@/api/calculateExpense/depreciationExpense";
import { M05List } from "@/api/calculateExpense/insuranceExpenses";
import { insuranceTypeList } from "@/api/system/vehicle";
import { listVehicle } from "@/api/car/vehicle";
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
      licensePlateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 保险类型
      options: [
        // 新车保险、车辆续保、货物保险
        {
          value: 1,
          insuranceTypeName: "新车保险",
        },
        {
          value: 2,
          insuranceTypeName: "车辆续保",
        },
        {
          value: 3,
          insuranceTypeName: "货物保险",
        },
      ],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        // pageSize: 10,
        typeId: undefined,
        startTime: this.parseTime(new Date(), "{y}-{m}") + "-01",
        finshTime: this.parseTime(new Date(), "{y}-{m}-{d}"),
        licensePlates: undefined,
        insuranceTypeId: undefined,
        insuranceStartDate: undefined,
        insuranceTypeName: undefined,
        insuranceCost: undefined,
        insuranceHandleTime: undefined,
        insuranceEndDate: undefined,
        value: undefined,
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
      oilslist: [],
    };
  },
  created() {
    listVehicle({}).then((res) => {
      this.licensePlateList = res.rows;
    });
    insuranceTypeList({}).then((res) => {
      this.options = res.rows;
    });
    this.getList();
  },
  filters: {
    type(value) {
      switch (value) {
        case 1:
          return "新车保险";
          break;
        case 2:
          return "车辆续保";
          break;
        case 3:
          return "货物保险";
          break;
        default:
          break;
      }
    },
  },
  methods: {
    /** 费用统计-保险费/统计总费用 */
    getList() {
      // this.loading = true;
      M05List(this.queryParams).then((response) => {
        // console.log(response)
        this.cardList = response.rows[0].list;
        this.totalCost = response.rows[0].totalCost;
        this.total = response.total;
        // this.loading = false;
      });
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
        "/pfserver-car/car/insuranceVehicle/M05Export",
        {
          ...this.queryParams,
        },
        `system_insuranceVehicle.xlsx`
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

<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="106px"
    >
      <el-form-item label="开始时间" prop="bTime" label-position="right">
        <el-date-picker
          clearable
          size="small"
          v-model="queryParams.bTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="eTime">
        <el-date-picker
          clearable
          size="small"
          v-model="queryParams.eTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="姓名" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item style="float:right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
          >查询</el-button
        >
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="姓名" align="center" prop="createBy" />
      <!-- <el-table-column label="补助类型" align="center" prop="type" /> -->
      <el-table-column label="补助类型" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type == 1">异地装卸货</span>
          <span v-if="scope.row.type == 2">加班</span>
        </template>
      </el-table-column>
      <el-table-column label="合计天数" align="center">
        <template slot-scope="scope">
          <a
            @click="cancelDialog(scope.row)"
            style="color:blue;cursor:pointer"
            >{{ scope.row.days }}</a
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="补助明细"
      :visible.sync="centerDialogVisible"
      width="40%"
      center
    >
      <!-- <div v-if="cardList.length > 0">加班时间:{{  }}</div> -->
      <div>
        加班时间：
        <div v-for="(item, index) in arrList" :key="index">
          {{ item.subsidyStartDate + ' - ' +  item.subsidyEndDate }}
        </div>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="centerDialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
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
import { C05List } from "@/api/payment/ancillarySstimator";

export default {
  name: "Card",
  data() {
    return {
      arrList: [],
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
        bTime: "",
        bTime: "",
        createBy: ""
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
      centerDialogVisible: false,
      //导入弹窗
      dialogVisible1: false,
      diaTitle1: "导入",
      vehicleModelNamelist: [],
      oilslist: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    cancelDialog(row) {
      console.log(row);
      this.centerDialogVisible = true;
      let arr = row.dSubsidyList;
      this.arrList = arr;
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
    /** 补助管理-补助统计及明细列表 */
    getList() {
      this.loading = true;
      C05List(this.queryParams).then(response => {
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

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
      <el-form-item label="异常类型" prop="dictLabel">
        <el-select
          v-model="queryParams.exceptionType"
          placeholder="请选择异常类型："
          clearable
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in routerOptionsList"
            :key="index"
            :label="item.dictLabel"
            :value="item.dictSort"
          ></el-option>
        </el-select>
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
      <!-- <el-table-column label="异常类型" align="center" prop="exceptionType" /> -->
      <el-table-column label="异常类型" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.exceptionType == 1">非定点加油站</span>
          <span v-if="scope.row.exceptionType == 2">24小时内连续加油</span>
        </template>
      </el-table-column>
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
      <!-- <el-table-column label="交易时间" align="center" prop="transactionTime" /> -->
      <el-table-column label="交易时间" align="center" prop="adminShowTransactionTime">
        <template slot-scope="scope">
          {{ scope.row.adminShowTransactionTime | time }}
        </template>
      </el-table-column>
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
  </div>
</template>

<script>
import { formatDate } from "@/utils/index";
import { PlantsAbnormalRecordList } from "@/api/card/exceptionRecord";
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
        pageNum: 1,
        pageSize: 10,
        licensePlate: "",
        oilCardNumber: "",
        exceptionType: ""
      },
      //异常类型数据
      routerOptionsList: [],
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
    this.getExceptionList();
  },
  filters: {
    time(value) {
      return formatDate(value, false);
    }
  },
  methods: {
    // 是否标红
    tableRowClassName(row) {
      console.log(row.row.status);
      switch (row.row.status) {
        case 1:
          return "error-row";
          break;
        default:
          break;
      }
    },
    //异常下列表
    getExceptionList() {
      this.getDicts("f_oil_card_record_exception_type").then(response => {
        this.routerOptionsList = response.data;
      });
    },
    //查询
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 异常记录-油料使用异常记录列表*/
    getList() {
      this.loading = true;
      PlantsAbnormalRecordList(this.queryParams).then(response => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    }
  }
};
</script>
<style lang="scss">
.el-table .error-row {
  background: #ff4949;
}
</style>

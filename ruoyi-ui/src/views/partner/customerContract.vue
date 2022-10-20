<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="合同名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入合同名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="合同结束日期" prop="contractEndTiem">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.contractEndTiem"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择合同结束日期"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item style="float:right">
        <!-- <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        > -->
        <buttonGroup
          @handleFilter="handleQuery"
          :addHasPermi="['pfserverperson:contract:add']"
          @handleAdd="handleAdd()"
        />
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:contract:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:contract:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:contract:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:contract:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row> -->

    <el-table
    border
      v-loading="loading"
      :data="contractList"
      @selection-change="handleSelectionChange"
      :row-class-name="tableRowClassName"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="合同类型 1客户合同 2承运商合同" align="center" prop="type" />
     
      <el-table-column label="预警状态 1正常 2预警" align="center" prop="flag" /> -->
      <el-table-column
        width="120"
        label="合同名称"
        align="center"
        prop="name"
      />
      <el-table-column
        width="120"
        label="客户名称"
        align="center"
        prop="carrierName"
      />
      <el-table-column
        width="80"
        label="合同单价"
        align="center"
        prop="unitPrice"
      />
      <el-table-column
        width="80"
        label="付款方式"
        align="center"
        prop="paymentMethod"
      />
      <el-table-column
        label="合同开始日期"
        align="center"
        prop="contractStartTime"
        width="120"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.contractStartTime, "{y}-{m}-{d}")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="合同结束日期"
        align="center"
        prop="contractEndTiem"
        width="120"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.contractEndTiem, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="承运商ID 关联p_carrier表id" align="center" prop="carrierId" /> -->
      <el-table-column
        label="客户类型"
        align="center"
        prop="contractType"
        width="80"
        :formatter="contractTypeFormat"
      />
      <el-table-column label="备注" align="center" prop="remarks" />
      <!-- <el-table-column label="删除状态 1正常 2删除" align="center" prop="deleteFlag" /> -->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="350"
        style=""
      >
        <template slot-scope="scope">
          <el-button
            class="button"
            @click="handleUpdate(scope.row)"
            type="primary"
            plain
            style="min-width: 60px"
            size="small"
            v-hasPermi="['pfserverperson:contract:edit']"
            >修改</el-button
          >
          <el-button
            class="button"
            @click="handleClick(scope.row)"
            type="primary"
            style="min-width: 60px"
            size="small"
            v-hasPermi="['pfserverperson:contract:info']"
            >查看</el-button
          >
          <el-button
            class="button"
            @click="handleDelete(scope.row)"
            type="danger"
            style="min-width: 60px"
            size="small"
            v-hasPermi="['pfserverperson:contract:delete']"
            >删除</el-button
          >
          <el-button
            v-if="scope.row.flag == 1"
            class="button"
            @click="handleSign(scope.row)"
            type="warning"
            style="min-width: 60px"
            size="small"
            v-hasPermi="['pfserverperson:contract:status']"
            >标记</el-button
          >
          <el-button
            v-if="scope.row.flag == 2"
            class="button"
            @click="handleSign(scope.row)"
            type="warning"
            style="min-width: 60px"
            size="small"
            v-hasPermi="['pfserverperson:contract:status']"
            >取消预警</el-button
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
// import { listContract, getContract, delContract, addContract, updateContract } from "@/api/system/contract";
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  customerContractList,
  CustomerContractDelete,
  CustomerContractStatus,
} from "@/api/partner";

export default {
  name: "Contract",
  components: {
    buttonGroup,
  },
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
      // 合作方管理-合同表格数据
      contractList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 客户类型 1内部合同 2外部合同字典
      contractTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        contractEndTiem: null,
      },

      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.getDicts("p_clientele_type").then((response) => {
      this.contractTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询合作方管理-合同列表 */
    getList() {
      this.loading = true;
      customerContractList(this.queryParams).then((response) => {
        this.contractList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 客户类型 1内部合同 2外部合同字典翻译
    contractTypeFormat(row, column) {
      return this.selectDictLabel(this.contractTypeOptions, row.contractType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$router.push({
        path: "/addCustomerContract",
      });
    },
    handleClick(row) {
      this.$router.push({
        path: "/detailCustomerContract/" + row.id,
      });
    },
    // 标记
    handleSign(row) {
      let { id, flag } = row;
      flag = (flag & 1) + 1;
      CustomerContractStatus({ id, flag }).then((res) => {
        this.getList();
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.$router.push({
        path: "/editCustomerContract/" + row.id,
      });
      // this.reset();
      // const id = row.id || this.ids;
      // getContract(id).then((response) => {
      //   this.open = true;
      //   this.title = "修改合作方管理-合同";
      // });
    },
    tableRowClassName({ row, rowIndex }) {
      switch (row.mathMonth) {
        case 1:
          return "error-row";
          break;
        case 2:
          return "warning-row";
          break;
        case 3:
          return "success-row ";
          break;
        default:
          break;
      }
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除合同名称为"' + row.name + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return CustomerContractDelete(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/contract/export",
        {
          ...this.queryParams,
        },
        `system_contract.xlsx`
      );
    },
  },
};
</script>

<style lang="scss" >
.el-table {
  color: #333;
}
.el-table .warning-row {
  background: #f2f23ad9;
}

.el-table .success-row {
  background: #1890ff;
  color: #fff;
}
.el-table .error-row {
  background: #ff4949;
}
.small-padding {
  background: #fff;
}
</style>
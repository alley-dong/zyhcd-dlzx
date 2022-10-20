<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="物品类型" prop="goodsName">
        <!-- <el-input
          v-model="queryParams.name"
          placeholder="请输入物品"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          v-model="queryParams.goodsTypeId"
          placeholder="请选择物品类型"
          clearable
          size="small"
        >
          <el-option
            v-for="item in repairListOption"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="开始日期" prop="reviewTime">
        <el-date-picker
          v-model="queryParams.startTime"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '100%' }"
          placeholder="请选择开始日期"
          clearable
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="reviewTime">
        <el-date-picker
          v-model="queryParams.finishTime"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '100%' }"
          placeholder="请选择结束日期"
          clearable
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="领用人" prop="name">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入领用人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="status">
        <!-- <el-input
          v-model="queryParams.name"
          placeholder="请输入审批状态"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          v-model="queryParams.status"
          placeholder="请选择审批状态"
          clearable
          size="small"
        >
          <el-option
            v-for="item in option"
            :key="item.id"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>

      <!-- <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item> -->
      <buttonGroup
        @handleFilter="handleQuery"
        twoButtonState
        style="float: right"
      />
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:repair:add']"
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
          v-hasPermi="['system:repair:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleClick"
          v-hasPermi="['system:repair:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:repair:export']"
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
      :data="repairList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="物品类型" align="center" prop="goodsTypeName" />
      <el-table-column label="领用物品" align="center" prop="goodsName">
        <template slot-scope="scope">
          <div
            v-if="scope.row.goodsType == 5"
            @click="handleUpdate(scope.row)"
            style="text-decoration: underline; color: #46a6ff; cursor: pointer"
          >
            备件
          </div>
          <div v-else>{{ scope.row.goodsName }}</div>
        </template>
      </el-table-column>
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="领用人" align="center" prop="name" />
      <el-table-column label="申请日期" align="center" prop="createTime" />
      <el-table-column label="领用日期" align="center" prop="receiveTime" />

      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status | status }}
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['parts:goodsReceivingRecord:list']"
            type="primary"
            >查看</el-button
          >
          <el-button
            v-if="scope.row.status == 1"
            size="small"
            @click="handleRemove(scope.row)"
            type="primary"
            plain
            v-hasPermi="['parts:goodsReceivingRecord:edit']"
            >审批</el-button
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

    <!-- 添加或修改维修保养-维修保养单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <!--   <el-form-item label="物品编号" prop="code">
          <el-input
            v-model="form.code"
            placeholder="请输入物品编号"
            clearable
          />
        </el-form-item> -->

        <el-form-item label="物品类别" prop="checkId">
          <el-select
            v-model="form.checkId"
            placeholder="请选择物品类别"
            @change="handleChange"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in repairListOption"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>

        <!-- <el-form-item label="物品名称" prop="name">
          <el-input
            v-model="form.name"
            placeholder="请输入物品名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="规格" prop="specification">
          <el-input
            v-model="form.specification"
            placeholder="请输入规格"
            clearable
          />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" clearable />
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input
            v-model="form.unitPrice"
            placeholder="请输入单价"
            clearable
          />
        </el-form-item>
        <el-form-item label="供应商" prop="supplier">
          <el-input
            v-model="form.supplier"
            placeholder="请输入供应商"
            clearable
          />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <!-- <el-button @click="cancel">取 消</el-button> -->
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listRepair, getRepair, delRepair, addRepair, updateRepair } from "@/api/system/repair";
import {
  goodsReceivingRecordK17List,
  goodsK02EditSave1,
  goodsCheckK14Delete,
  goodsK02AddList,
  goodsK02EditSave,
  goodsCheckK14AddGetInfo,
} from "@/api/stock/index";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "Repair",
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
      // 维修保养-维修保养单表格数据
      repairList: [],
      //   下拉
      repairListOption: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsTypeId:undefined,
        goodsName: null,
        reviewTime: null,
        startTime: undefined,
        createBy:undefined,
        finishTime: undefined,
        licensePlate: null,
        name: null,
        status: null,
      },
      option: [
        // {
        //   label: "全部",
        //   value: null,
        // },
        {
          label: "待审核",
          value: 1,
        },
        {
          label: "已通过",
          value: 2,
        },
        /*   { text: "已拒绝", value: 3 },
        { text: "已领取", value: 4 },
        { text: "已撤回", value: 5 }, */
        {
          label: "已拒绝",
          value: 3,
        },
        {
          label: "已领取",
          value: 4,
        },
        {
          label: "已撤回",
          value: 5,
        },
      ],
      // 表单参数
      form: {
        // id: null,
        checkId: null,
        // type: null,
      },
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList(true);
  },
  filters: {
    repairStatus: function (value) {
      switch (value) {
        case 1:
          return "未开始";
          break;
        case 2:
          return "进行中";
          break;
        case 3:
          return "已完成";
          break;

        default:
          break;
      }
    },
    status: function (value) {
      switch (value) {
        case 1:
          return "待审核";
        case 2:
          return "已通过";
        case 3:
          return "已拒绝";
        case 4:
          return "已领取";
        case 5:
          return "已撤回";
        default:
          break;
      }
    },
  },
  mounted() {
    goodsCheckK14AddGetInfo({}).then((res) => {
      this.repairListOption = res.rows;
    });
  },
  methods: {
    // 选择
    handleChange(val) {
      for (const item of this.repairListOption) {
        if (val === item.id) {
          this.form.goodsTypeName = item.name;
        }
      }
    },
    /** 查询维修保养-维修保养单列表 */
    getList(bool) {
      this.loading = true;
      goodsReceivingRecordK17List(this.queryParams).then((response) => {
        // if (bool) {
        //   this.repairListOption = response.rows;
        // }
        for (const item of response.rows) {
          item.total = item.unitPrice * item.amount;
        }
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    handleClick() {
      console.log("新增");
      this.open = true;
    },
    // 表单重置
    reset() {
      this.form = {};
      this.resetForm("form");
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
    // 禁用
    handleIsClick(row) {
      console.log();

      goodsK02EditSave({
        id: row.id,
        name: row.name,
        status: (row.status & 1) + 1,
      }).then((res) => {
        this.getList();
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "新增";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log("查看");
      // 审批
      // this.reset();
      // this.$router.push("/detailStockManage/" + row.id);
      //   this.$router.push({path:"/seeInventoryManagement",query:{form:this.form}});
      this.$router.push({
        path: "/seeCollectionManagement/" + row.id,
        query: { name: row.goodsTypeName },
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log(this.form);
      if (!this.form.checkId) {
        this.$message({
          message: "请选择物品类别",
          type: "warning",
        });
        return;
      }
      this.$router.push({
        path: "/addInventoryManagement",
        query: { form: this.form },
      });
    },
    /** 删除按钮操作 */
    handleRemove(row) {
      // 审批
      console.log("审批");
      this.$router.push({
        path: "/editCollectionManagement/" + row.id,
        query: { name: row.goodsTypeName },
      });

      //   const id = row.id || this.ids;
      //   goodsCheckK14Delete(id).then((res) => {
      //     this.getList();
      //   });
      // this.$router.push("/lookUp/" + id);
      // console.log("查看");
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/repair/export",
        {
          ...this.queryParams,
        },
        `system_repair.xlsx`
      );
    },
  },
};
</script>
<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="开始时间" prop="beginTimes">
        <el-date-picker
          v-model="queryParams.beginTimes"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '100%' }"
          placeholder="请选择开始时间"
          clearable
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTimes">
        <el-date-picker
          v-model="queryParams.endTimes"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间"
          clearable
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="盘点单号" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入盘点单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <!-- <el-input
          v-model="queryParams.name"
          placeholder="请输入状态"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态"
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
        @handleAdd="handleAdd"
        style="float: right"
        :addHasPermi="['parts:goods:add']"
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
      v-loading="loading"
      :data="repairList"
      border
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="盘点单号" align="center" prop="code" />
      <el-table-column label="盘点时间" align="center" prop="createTime" />
      <el-table-column label="物品类型" align="center" prop="goodsTypeName" />
      <el-table-column label="盘点人员" align="center" prop="createBy" />

      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status == 1 ? "未提交" : "已提交" }}
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
            v-hasPermi="['parts:goods:edit']"
            type="primary"
            >查看</el-button
          >
          <!-- <el-button
            size="small"
            type="danger"
            @click="handleRemove(scope.row)"
            v-hasPermi="['parts:goods:remove']"
            >删除</el-button
          > -->
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
  goodsCheckK14List,
  goodsK02EditSave1,
  goodsCheckK14Delete,
  goodsK02AddList,
  goodsK02EditSave,
  goodsCheckK14AddGetInfo,
  goodsCheckK14Add,
} from "@/api/stock/index";
// import { goodsCheckK14Add } from "@/api/stock/child/index";
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
        beginTimes: null,
        endTimes: null,
        code: null,
        status: null,
      },
      option: [
        // {
        //   label: "全部",
        //   value: null,
        // },
        {
          label: "未提交",
          value: 1,
        },
        {
          label: "已提交",
          value: 2,
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
          break;
        case 2:
          return "已通过";
          break;
        case 3:
          return "未通过";
          break;
        case 4:
          return "已确认";
          break;
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
      goodsCheckK14List(this.queryParams).then((response) => {
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
      // 审批
      // this.reset();
      // this.$router.push("/detailStockManage/" + row.id);
      let { goodsTypeName, id, checkId,status } = row;
      if (status==2) {
          this.$router.push({
        path: "/seeInventoryManagement",
        query: { goodsTypeName, id, checkId },
      });
      }else{
           this.$router.push({
        path: "/addInventoryManagement/"+id,
        query: { goodsTypeName, id, checkId },
      });
      }
      // console.log(row);
   
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
      let obj = {};
      obj.id = this.form.checkId;
      obj.name = this.form.goodsTypeName;
      goodsCheckK14Add(obj).then((res) => {
        console.log(res, "res");
        if (res.code != 200) {
          this.$message({
            message: res.msg,
            type: "warning",
          });
          return;
        }
        this.$router.push({
          path: "/addInventoryManagement/" + res.msg, //TODO:留
          // path: "/addInventoryManagement/" + res.data.msg, //TODO:删了
          query: {
            goodsTypeName: this.form.goodsTypeName,
            checkId: this.form.checkId,
          },
        });
      });
    },
    /** 删除按钮操作 */
    handleRemove(row) {
      const id = row.id || this.ids;
      // goodsCheckK14Delete(id).then((res) => {
      //   this.getList();
      // });
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          goodsCheckK14Delete(id).then((response) => {
            that.getList();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
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
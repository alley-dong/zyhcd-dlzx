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
      <el-form-item label="物品类别" prop="goodsTypeName">
        <!-- <el-input
          v-model="queryParams.name"
          placeholder="请输入物品类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          v-model="queryParams.goodsTypeName"
          placeholder="请选择物品类别"
          clearable
          size="small"
        >
          <el-option
            v-for="item in repairListOption"
            :key="item.id"
            :label="item.name"
            :value="item.name"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="物品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入物品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <!-- <el-form-item label="报废类型" prop="type"> -->
      <!-- <el-input
          v-model="queryParams.name"
          placeholder="请输入物品类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
      <!-- <el-select
          v-model="queryParams.inventoryType"
          placeholder="请选择报废类型"
          clearable
          size="small"
        >
          <el-option
            v-for="item in option"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item> -->
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
        @handleAdd="handleClick"
         leadingIn
        threeButton="导出"
        @handleImportant="handleImportant"
        :addHasPermi="['parts:goodsInventory:add']"
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
      v-loading="loading"
      :data="repairList"
      @selection-change="handleSelectionChange"
      border
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="报废单号" align="center" prop="code" />
      <el-table-column label="报废时间" align="center" prop="inventoryTime" />
      <el-table-column label="物品类别" align="center" prop="goodsTypeName" />
      <el-table-column label="物品编号" align="center" prop="goodsCode" />
      <el-table-column label="物品名称" align="center" prop="goodsName" />
      <el-table-column label="规格" align="center" prop="specification" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="单价（元）" align="center" prop="unitPrice" />
      <el-table-column label="报废数量" align="center" prop="inventoryNumber" />
      <!-- TODO:金额 -->
      <el-table-column label="金额（元）" align="center" prop="total" />
      <!-- <el-table-column label="报废类别" align="center" prop="inventoryType">
        <template slot-scope="scope">
          {{ scope.row.inventoryType | inventoryType }}
        </template>
      </el-table-column> -->
      <el-table-column label="供应商" align="center" prop="supplier" />
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="操作员" align="center" prop="createBy" />

      <!-- <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status == 1 ? "启用" : "禁用" }}
        </template>
      </el-table-column> -->

      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <!-- <el-table-column label="申请时间" align="center" prop="completeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column> -->

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="200"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['parts:goodsInventory:edit']"
            type="primary"
            >查看</el-button
          >
          <!-- <el-button
            size="small"
            type="danger"
            @click="handleRemove(scope.row)"
            v-hasPermi="['parts:goodsInventory:remove']"
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
        <el-form-item label="物品编号" prop="code">
          <el-input
            v-model="form.code"
            placeholder="请输入物品编号"
            clearable
          />
        </el-form-item>

        <el-form-item label="物品类别" prop="goodsTypeId">
          <el-select
            v-model="form.goodsTypeId"
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

        <el-form-item label="物品名称" prop="name">
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
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <!-- <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button> -->
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listRepair, getRepair, delRepair, addRepair, updateRepair } from "@/api/system/repair";
import {
  goodsInventoryK11List,
  goodsInventoryK11Delete,
  goodsTypeK01List,
} from "@/api/stock/index";
import buttonGroup from "@/views/components/com/buttonGroup";
// import { goodsInventoryK11List } from "@/api/stock/child/index";
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
        goodsName: null,
        inventoryType: undefined,
        // inventoryType:null,
        beginTimes: null,
        endTimes: null,
        goodsTypeName: null,
      },
      option: [
        /* 
        手动
领用
盘点
报废
        */
        {
          label: "全部",
          value: null,
        },
        {
          label: "正点报废",
          value: 1,
        },
        // {
        //   label: "领用",
        //   value: 2,
        // },
        {
          label: "盘点报废",
          value: 3,
        },
        //   {
        //   label: "报废",
        //   value: 4,
        // },
      ],
      // 表单参数
      form: {
        // id: null,
        name: null,
        code: null,
        goodsTypeId: null,
        specification: null,
        unit: null,
        unitPrice: null,
        supplier: null,
        // type: null,
      },
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList(true);
    this.getListOption();
  },
  filters: {
    inventoryType: function (value) {
      switch (value) {
        case 1:
          return "正点报废";
          break;
        // case 2:
        //   return "领用";
        //   break;
        case 3:
          return "盘点报废";
          break;
        // case 4:
        //   return "报废";
        //   break;
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
  methods: {
        handleImportant() {
      this.download(
        "/pfserver-parts/parts/goodsInventory/goodsInventoryScrapListExport",
        {
          ...this.queryParams,
        },
        `报废记录.xlsx`
      );
    },
    // 选择
    handleChange(val) {
      for (const item of this.repairListOption) {
        if (val === item.id) {
          this.form.goodsTypeName = item.name;
        }
      }
    },
    getListOption() {
      goodsTypeK01List({ status: 1 }).then((response) => {
        this.repairListOption = response.rows;
      });
    },
    /** 查询维修保养-维修保养单列表 */
    getList(bool) {
      this.loading = true;
      goodsInventoryK11List(this.queryParams).then((response) => {
        for (const item of response.rows) {
          // if (item.unitPrice && item.inventoryNumber) {
          item.total = item.unitPrice * item.inventoryNumber;
          //   continue;
          // }
          // if (item.unitPrice) {
          //   item.total = item.unitPrice;
          // }
          // if (item.inventoryNumber) {
          //   item.total = item.inventoryNumber;
          // }
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
      this.$router.push("/addScrapManagement");
    },
    // 表单重置
    reset() {
      this.form = {
        name: null,
        code: null,
        goodsTypeId: null,
        specification: null,
        unit: null,
        unitPrice: null,
        supplier: null,
      };
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

    /** 修改按钮操作 */
    handleUpdate(row) {
      // 审批
      // this.reset();
      // this.open = true;
      const id = row.id || this.ids;
      this.$router.push("/editScrapManagement/" + id);

      // goodsK02EditSave1(id).then((response) => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "编辑";
      // });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            goodsK02EditSave(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.reset();
            });
          } else {
            goodsK02AddList(this.form).then((response) => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.reset();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleRemove(row) {
      const id = row.id || this.ids;
      // goodsInventoryK11Delete(id).then((res) => {
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
          goodsInventoryK11Delete(id).then((response) => {
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
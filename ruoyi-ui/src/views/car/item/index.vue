<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保险ID" prop="insuranceId">
        <el-input
          v-model="queryParams.insuranceId"
          placeholder="请输入保险ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保险类型ID" prop="insuranceTypeId">
        <el-input
          v-model="queryParams.insuranceTypeId"
          placeholder="请输入保险类型ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保险类型名称" prop="insuranceTypeName">
        <el-input
          v-model="queryParams.insuranceTypeName"
          placeholder="请输入保险类型名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保险项ID" prop="insuranceItemId">
        <el-input
          v-model="queryParams.insuranceItemId"
          placeholder="请输入保险项ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保险项名称" prop="insuranceItemName">
        <el-input
          v-model="queryParams.insuranceItemName"
          placeholder="请输入保险项名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保额" prop="insuranceQuota">
        <el-input
          v-model="queryParams.insuranceQuota"
          placeholder="请输入保额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保费" prop="insuranceCost">
        <el-input
          v-model="queryParams.insuranceCost"
          placeholder="请输入保费"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除状态 1正常 2删除" prop="deleteFlag">
        <el-input
          v-model="queryParams.deleteFlag"
          placeholder="请输入删除状态 1正常 2删除"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['car:item:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['car:item:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['car:item:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['car:item:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="itemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="保险ID" align="center" prop="insuranceId" />
      <el-table-column label="保险类型ID" align="center" prop="insuranceTypeId" />
      <el-table-column label="保险类型名称" align="center" prop="insuranceTypeName" />
      <el-table-column label="保险项ID" align="center" prop="insuranceItemId" />
      <el-table-column label="保险项名称" align="center" prop="insuranceItemName" />
      <el-table-column label="保额" align="center" prop="insuranceQuota" />
      <el-table-column label="保费" align="center" prop="insuranceCost" />
      <el-table-column label="删除状态 1正常 2删除" align="center" prop="deleteFlag" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['car:item:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['car:item:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改车辆管理-车辆保险保险项对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="保险ID" prop="insuranceId">
          <el-input v-model="form.insuranceId" placeholder="请输入保险ID" />
        </el-form-item>
        <el-form-item label="保险类型ID" prop="insuranceTypeId">
          <el-input v-model="form.insuranceTypeId" placeholder="请输入保险类型ID" />
        </el-form-item>
        <el-form-item label="保险类型名称" prop="insuranceTypeName">
          <el-input v-model="form.insuranceTypeName" placeholder="请输入保险类型名称" />
        </el-form-item>
        <el-form-item label="保险项ID" prop="insuranceItemId">
          <el-input v-model="form.insuranceItemId" placeholder="请输入保险项ID" />
        </el-form-item>
        <el-form-item label="保险项名称" prop="insuranceItemName">
          <el-input v-model="form.insuranceItemName" placeholder="请输入保险项名称" />
        </el-form-item>
        <el-form-item label="保额" prop="insuranceQuota">
          <el-input v-model="form.insuranceQuota" placeholder="请输入保额" />
        </el-form-item>
        <el-form-item label="保费" prop="insuranceCost">
          <el-input v-model="form.insuranceCost" placeholder="请输入保费" />
        </el-form-item>
        <el-form-item label="删除状态 1正常 2删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除状态 1正常 2删除" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listItem, getItem, delItem, addItem, updateItem } from "@/api/car/item";

export default {
  name: "Item",
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
      // 车辆管理-车辆保险保险项表格数据
      itemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        insuranceId: null,
        insuranceTypeId: null,
        insuranceTypeName: null,
        insuranceItemId: null,
        insuranceItemName: null,
        insuranceQuota: null,
        insuranceCost: null,
        deleteFlag: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询车辆管理-车辆保险保险项列表 */
    getList() {
      this.loading = true;
      listItem(this.queryParams).then(response => {
        this.itemList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        insuranceId: null,
        insuranceTypeId: null,
        insuranceTypeName: null,
        insuranceItemId: null,
        insuranceItemName: null,
        insuranceQuota: null,
        insuranceCost: null,
        deleteFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆管理-车辆保险保险项";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getItem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆管理-车辆保险保险项";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateItem(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addItem(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除车辆管理-车辆保险保险项编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delItem(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('car/item/export', {
        ...this.queryParams
      }, `car_item.xlsx`)
    }
  }
};
</script>
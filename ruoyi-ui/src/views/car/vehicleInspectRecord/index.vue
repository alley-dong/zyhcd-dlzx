<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检车日期" prop="inspectDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.inspectDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择检车日期">
        </el-date-picker>
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
          v-hasPermi="['pfservercar:vehicleInspectRecord:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pfservercar:vehicleInspectRecord:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pfservercar:vehicleInspectRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pfservercar:vehicleInspectRecord:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vehicleInspectRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="车辆id" align="center" prop="truckId" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="检车日期" align="center" prop="inspectDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="注册日期" align="center" prop="registerDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆年检费用" align="center" prop="vehiceInspectCost" />
      <el-table-column label="运营年检费用" align="center" prop="operateInspectCost" />
      <el-table-column label="复检费用" align="center" prop="reviewCost" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:vehicleInspectRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:vehicleInspectRecord:remove']"
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

    <!-- 添加或修改车辆管理-车辆年检记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆id" prop="truckId">
          <el-input v-model="form.truckId" placeholder="请输入车辆id" />
        </el-form-item>
        <el-form-item label="车牌号" prop="licensePlate">
          <el-input v-model="form.licensePlate" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="检车日期" prop="inspectDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.inspectDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检车日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="注册日期" prop="registerDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.registerDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择注册日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆年检费用" prop="vehiceInspectCost">
          <el-input v-model="form.vehiceInspectCost" placeholder="请输入车辆年检费用" />
        </el-form-item>
        <el-form-item label="运营年检费用" prop="operateInspectCost">
          <el-input v-model="form.operateInspectCost" placeholder="请输入运营年检费用" />
        </el-form-item>
        <el-form-item label="复检费用" prop="reviewCost">
          <el-input v-model="form.reviewCost" placeholder="请输入复检费用" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="删除状态 0删除 1未删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除状态 0删除 1未删除" />
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
import { listVehicleInspectRecord, getVehicleInspectRecord, delVehicleInspectRecord, addVehicleInspectRecord, updateVehicleInspectRecord } from "@/api/car/vehicleInspectRecord";

export default {
  name: "VehicleInspectRecord",
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
      // 车辆管理-车辆年检记录表格数据
      vehicleInspectRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licensePlate: null,
        inspectDate: null,
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
    /** 查询车辆管理-车辆年检记录列表 */
    getList() {
      this.loading = true;
      listVehicleInspectRecord(this.queryParams).then(response => {
        this.vehicleInspectRecordList = response.rows;
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
        truckId: null,
        licensePlate: null,
        inspectDate: null,
        registerDate: null,
        vehiceInspectCost: null,
        operateInspectCost: null,
        reviewCost: null,
        remarks: null,
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
      this.title = "添加车辆管理-车辆年检记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVehicleInspectRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆管理-车辆年检记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVehicleInspectRecord(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVehicleInspectRecord(this.form).then(response => {
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
      this.$confirm('是否确认删除', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delVehicleInspectRecord(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('pfservercar/vehicleInspectRecord/export', {
        ...this.queryParams
      }, `pfservercar_vehicleInspectRecord.xlsx`)
    }
  }
};
</script>
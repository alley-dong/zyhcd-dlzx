<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="线路名称" prop="routeName">
        <el-input
          v-model="queryParams.routeName"
          placeholder="请输入线路名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态 1待审核  2拒绝 3通过" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
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
          v-hasPermi="['person:dRecruitPlan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['person:dRecruitPlan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['person:dRecruitPlan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['person:dRecruitPlan:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dRecruitPlanList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="线路ID" align="center" prop="routeId" />
      <el-table-column label="线路名称" align="center" prop="routeName" />
      <el-table-column label="招聘人数" align="center" prop="number" />
      <el-table-column label="年龄" align="center" prop="age" />
      <el-table-column label="驾驶要求" align="center" prop="drivingRequirements" />
      <el-table-column label="入职时间" align="center" prop="entryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.entryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工资福利" align="center" prop="wage" />
      <el-table-column label="审核状态" align="center" prop="status" />
      <el-table-column label="审核时间" align="center" prop="reviewTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reviewTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['person:dRecruitPlan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['person:dRecruitPlan:remove']"
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

    <!-- 添加或修改驾驶员-招聘计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="线路ID" prop="routeId">
          <el-input v-model="form.routeId" placeholder="请输入线路ID" />
        </el-form-item>
        <el-form-item label="线路名称" prop="routeName">
          <el-input v-model="form.routeName" placeholder="请输入线路名称" />
        </el-form-item>
        <el-form-item label="招聘人数" prop="number">
          <el-input v-model="form.number" placeholder="请输入招聘人数" />
        </el-form-item>
        <el-form-item label="学历要求" prop="education">
          <el-input v-model="form.education" placeholder="请输入学历要求" />
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="form.age" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="驾驶要求" prop="drivingRequirements">
          <el-input v-model="form.drivingRequirements" placeholder="请输入驾驶要求" />
        </el-form-item>
        <el-form-item label="入职时间" prop="entryTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.entryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工资福利" prop="wage">
          <el-input v-model="form.wage" placeholder="请输入工资福利" />
        </el-form-item>
        <el-form-item label="需求原因" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入需求原因" />
        </el-form-item>
        <el-form-item label="工作职责" prop="duty">
          <el-input v-model="form.duty" placeholder="请输入工作职责" />
        </el-form-item>
        <el-form-item label="审核状态 1待审核  2拒绝 3通过">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核时间" prop="reviewTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.reviewTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核人" prop="reviewUser">
          <el-input v-model="form.reviewUser" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="审核备注" prop="reviewRemarks">
          <el-input v-model="form.reviewRemarks" placeholder="请输入审核备注" />
        </el-form-item>
        <el-form-item label="删除状态 1正常 2删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除状态 1正常 2删除" />
        </el-form-item>
        <el-form-item label="创建人" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="更新人" prop="updateUser">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.updateUser"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择更新人">
          </el-date-picker>
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
import { listDRecruitPlan, getDRecruitPlan, delDRecruitPlan, addDRecruitPlan, updateDRecruitPlan } from "@/api/person/dRecruitPlan";

export default {
  name: "DRecruitPlan",
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
      // 驾驶员-招聘计划表格数据
      dRecruitPlanList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routeName: undefined,
        status: undefined
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
    /** 查询驾驶员-招聘计划列表 */
    getList() {
      this.loading = true;
      listDRecruitPlan(this.queryParams).then(response => {
        this.dRecruitPlanList = response.rows;
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
        routeId: null,
        routeName: null,
        number: null,
        education: null,
        age: null,
        drivingRequirements: null,
        entryTime: null,
        wage: null,
        reason: null,
        duty: null,
        status: 0,
        reviewTime: null,
        reviewUser: null,
        reviewRemarks: null,
        deleteFlag: null,
        createUser: null,
        createTime: null,
        updateUser: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.page = 1;
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
      this.title = "添加驾驶员-招聘计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDRecruitPlan(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改驾驶员-招聘计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDRecruitPlan(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDRecruitPlan(this.form).then(response => {
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
      this.$confirm('是否确认删除驾驶员-招聘计划编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDRecruitPlan(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('person/dRecruitPlan/export', {
        ...this.queryParams
      }, `person_dRecruitPlan.xlsx`)
    }
  }
};
</script>
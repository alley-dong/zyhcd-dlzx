<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="状态 1进行中 2未通过 3已通过 4储备" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态 1进行中 2未通过 3已通过 4储备" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="司机姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入司机姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件号码" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入证件号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="contact">
        <el-input
          v-model="queryParams.contact"
          placeholder="请输入联系方式"
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
          v-hasPermi="['system:driver:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:driver:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:driver:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:driver:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="driverList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="状态 1进行中 2未通过 3已通过 4储备" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="司机姓名" align="center" prop="name" />
      <el-table-column label="司机性别" align="center" prop="sex" />
      <el-table-column label="出生日期" align="center" prop="birthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.birthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="证件号码" align="center" prop="idCard" />
      <el-table-column label="民族" align="center" prop="nation" />
      <el-table-column label="政治面貌" align="center" prop="politicalAffiliation" />
      <el-table-column label="婚姻状况" align="center" prop="maritalStatus" />
      <el-table-column label="学历" align="center" prop="education" />
      <el-table-column label="联系方式" align="center" prop="contact" />
      <el-table-column label="紧急联系人电话" align="center" prop="urgentTel" />
      <el-table-column label="驾照类型" align="center" prop="drivingLicenseType" />
      <el-table-column label="驾照获取时间" align="center" prop="drivingLicenseTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.drivingLicenseTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="驾龄" align="center" prop="driveAge" />
      <el-table-column label="入职时间" align="center" prop="dntryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dntryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="家庭地址" align="center" prop="address" />
      <el-table-column label="驾驶员类型" align="center" prop="driverTypeId" />
      <el-table-column label="绩效类型 1长途 2短途" align="center" prop="performanceType" />
      <el-table-column label="员工类型 1正式工 1合同工" align="center" prop="staffType" />
      <el-table-column label="面试记录" align="center" prop="interviewRecord" />
      <el-table-column label="面试状态 1进行中 2未通过 3已通过 4储备" align="center" prop="interviewStatus" />
      <el-table-column label="面试备注" align="center" prop="interviewRemarks" />
      <el-table-column label="笔试状态 1进行中 2未通过 3已通过 4储备" align="center" prop="examStatus" />
      <el-table-column label="笔试备注" align="center" prop="examRemarks" />
      <el-table-column label="实操-直线行驶状态 1进行中 2未通过 3已通过" align="center" prop="driveStraightDrivingStatus" />
      <el-table-column label="实操-直线行驶备注" align="center" prop="driveStraightDrivingRemarks" />
      <el-table-column label="实操-倒车入库状态 1进行中 2未通过 3已通过" align="center" prop="driveParkingStatus" />
      <el-table-column label="实操-倒车入库备注" align="center" prop="driveParkingRemarks" />
      <el-table-column label="实操-故障处理状态 1进行中 2未通过 3已通过" align="center" prop="driveEmergencyStatus" />
      <el-table-column label="实操-故障处理备注" align="center" prop="driveEmergencyRemarks" />
      <el-table-column label="实操状态 1进行中 2未通过 3已通过 4储备" align="center" prop="driveStatus" />
      <el-table-column label="实操备注" align="center" prop="driveRemarks" />
      <el-table-column label="创建人" align="center" prop="createUser" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:driver:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:driver:remove']"
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

    <!-- 添加或修改驾驶员-司机档案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="状态 1进行中 2未通过 3已通过 4储备" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态 1进行中 2未通过 3已通过 4储备">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="司机姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入司机姓名" />
        </el-form-item>
        <el-form-item label="司机性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择司机性别">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.birthday"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="证件号码" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-input v-model="form.nation" placeholder="请输入民族" />
        </el-form-item>
        <el-form-item label="政治面貌" prop="politicalAffiliation">
          <el-input v-model="form.politicalAffiliation" placeholder="请输入政治面貌" />
        </el-form-item>
        <el-form-item label="婚姻状况">
          <el-radio-group v-model="form.maritalStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="学历" prop="education">
          <el-input v-model="form.education" placeholder="请输入学历" />
        </el-form-item>
        <el-form-item label="联系方式" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="紧急联系人电话" prop="urgentTel">
          <el-input v-model="form.urgentTel" placeholder="请输入紧急联系人电话" />
        </el-form-item>
        <el-form-item label="驾照类型" prop="drivingLicenseType">
          <el-select v-model="form.drivingLicenseType" placeholder="请选择驾照类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="驾照获取时间" prop="drivingLicenseTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.drivingLicenseTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择驾照获取时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="驾龄" prop="driveAge">
          <el-input v-model="form.driveAge" placeholder="请输入驾龄" />
        </el-form-item>
        <el-form-item label="入职时间" prop="dntryTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.dntryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="家庭地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入家庭地址" />
        </el-form-item>
        <el-form-item label="驾驶员类型" prop="driverTypeId">
          <el-input v-model="form.driverTypeId" placeholder="请输入驾驶员类型" />
        </el-form-item>
        <el-form-item label="绩效类型 1长途 2短途" prop="performanceType">
          <el-select v-model="form.performanceType" placeholder="请选择绩效类型 1长途 2短途">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="员工类型 1正式工 1合同工" prop="staffType">
          <el-select v-model="form.staffType" placeholder="请选择员工类型 1正式工 1合同工">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="面试记录" prop="interviewRecord">
          <el-input v-model="form.interviewRecord" placeholder="请输入面试记录" />
        </el-form-item>
        <el-form-item label="面试状态 1进行中 2未通过 3已通过 4储备">
          <el-radio-group v-model="form.interviewStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="面试备注" prop="interviewRemarks">
          <el-input v-model="form.interviewRemarks" placeholder="请输入面试备注" />
        </el-form-item>
        <el-form-item label="笔试状态 1进行中 2未通过 3已通过 4储备">
          <el-radio-group v-model="form.examStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="笔试备注" prop="examRemarks">
          <el-input v-model="form.examRemarks" placeholder="请输入笔试备注" />
        </el-form-item>
        <el-form-item label="实操-直线行驶状态 1进行中 2未通过 3已通过">
          <el-radio-group v-model="form.driveStraightDrivingStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="实操-直线行驶备注" prop="driveStraightDrivingRemarks">
          <el-input v-model="form.driveStraightDrivingRemarks" placeholder="请输入实操-直线行驶备注" />
        </el-form-item>
        <el-form-item label="实操-倒车入库状态 1进行中 2未通过 3已通过">
          <el-radio-group v-model="form.driveParkingStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="实操-倒车入库备注" prop="driveParkingRemarks">
          <el-input v-model="form.driveParkingRemarks" placeholder="请输入实操-倒车入库备注" />
        </el-form-item>
        <el-form-item label="实操-故障处理状态 1进行中 2未通过 3已通过">
          <el-radio-group v-model="form.driveEmergencyStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="实操-故障处理备注" prop="driveEmergencyRemarks">
          <el-input v-model="form.driveEmergencyRemarks" placeholder="请输入实操-故障处理备注" />
        </el-form-item>
        <el-form-item label="实操状态 1进行中 2未通过 3已通过 4储备">
          <el-radio-group v-model="form.driveStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="实操备注" prop="driveRemarks">
          <el-input v-model="form.driveRemarks" placeholder="请输入实操备注" />
        </el-form-item>
        <el-form-item label="创建人" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="更新人" prop="updateUser">
          <el-input v-model="form.updateUser" placeholder="请输入更新人" />
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
import { listDriver, getDriver, delDriver, addDriver, updateDriver } from "@/api/person/driver";

export default {
  name: "Driver",
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
      // 驾驶员-司机档案表格数据
      driverList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态 1进行中 2未通过 3已通过 4储备字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: null,
        name: null,
        idCard: null,
        contact: null,
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
    this.getDicts("d_Driver_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询驾驶员-司机档案列表 */
    getList() {
      this.loading = true;
      listDriver(this.queryParams).then(response => {
        this.driverList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态 1进行中 2未通过 3已通过 4储备字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
        status: null,
        name: null,
        sex: null,
        birthday: null,
        idCard: null,
        nation: null,
        politicalAffiliation: null,
        maritalStatus: "0",
        education: null,
        contact: null,
        urgentTel: null,
        drivingLicenseType: null,
        drivingLicenseTime: null,
        driveAge: null,
        dntryTime: null,
        address: null,
        driverTypeId: null,
        performanceType: null,
        staffType: null,
        interviewRecord: null,
        interviewStatus: 0,
        interviewRemarks: null,
        examStatus: 0,
        examRemarks: null,
        driveStraightDrivingStatus: 0,
        driveStraightDrivingRemarks: null,
        driveParkingStatus: 0,
        driveParkingRemarks: null,
        driveEmergencyStatus: 0,
        driveEmergencyRemarks: null,
        driveStatus: 0,
        driveRemarks: null,
        createUser: null,
        createTime: null,
        updateUser: null,
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
      this.title = "添加驾驶员-司机档案";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDriver(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改驾驶员-司机档案";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDriver(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDriver(this.form).then(response => {
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
      this.$confirm('是否确认删除驾驶员-司机档案编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDriver(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/driver/export', {
        ...this.queryParams
      }, `system_driver.xlsx`)
    }
  }
};
</script>
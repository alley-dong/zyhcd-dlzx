<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <!-- <el-form-item label="保险类型" prop="type">
        <el-select
          style="width: 200px"
          v-model="queryParams.type"
          placeholder="请选择保险类型"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          style="width: 200px"
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <!-- <el-form-item label="保险种类" prop="type">
        <el-select
          v-model="queryParams.type"
          style="width: 200px"
          placeholder="请选择保险种类"
          clearable
          size="small"
        >
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item label="创建时间" prop="sTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.sTime"
          type="date"
          value-format="yyyy-MM-dd "
          placeholder="选择创建时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="fTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.fTime"
          type="date"
          value-format="yyyy-MM-dd "
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <!-- <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        > -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:vehicle:add']"
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
          v-hasPermi="['system:vehicle:edit']"
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
          v-hasPermi="['system:vehicle:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:vehicle:export']"
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
      :data="vehicleList"
      :row-class-name="tableRowClassName"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>

      <!-- <el-table-column
        label="保险类型"
        align="center"
        prop="type"
        :formatter="typeFormat"
      /> -->
      <!-- TODO: -->
      <el-table-column label="车牌号/车架号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.licensePlate }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="合计保费" align="center" prop="totalPremium" />
      <el-table-column label="审核状态" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.status | status }}</span>
        </template>
      </el-table-column> -->
      <el-table-column
        label="起保日期"
        align="center"
        prop="insuranceStartDate"
        width="180"
      >
        <template slot-scope="scope">
          <!-- {h}:{i}:{s} -->
          <span v-if="scope.row.insuranceStartDate">{{ parseTime(scope.row.insuranceStartDate, "{y}-{m}-{d} ") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="终保日期"
        align="center"
        prop="insuranceEndDate"
        width="180"
      >
        <template slot-scope="scope">
          <!-- {h}:{i}:{s} -->
          <span v-if="scope.row.insuranceEndDate">{{ parseTime(scope.row.insuranceEndDate, "{y}-{m}-{d} ") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="保险办理日期"
        align="center"
        prop="insuranceHandleTime"
        width="180"
      >
        <template slot-scope="scope">
          <!-- {h}:{i}:{s} -->
          <span v-if="scope.row.insuranceHandleTime">{{
            parseTime(scope.row.insuranceHandleTime, "{y}-{m}-{d} ")
          }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- <el-button
            v-if="scope.row.status === 1"
            @click="handleApproval(scope.row.id)"
            >审批</el-button
          > -->
          <el-button
          v-hasPermi="['pfservercar:googds:list']"
           type="primary" size="small" @click="handleDelete(scope.row)">查看</el-button>
          <!-- <el-button
            v-if="scope.row.status == 3"
            @click="handleDelete(scope.row)"
            >编辑</el-button
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

    <!-- 添加或修改车辆管理-车辆保险对话框 -->
    <!-- <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
       
        <el-form-item label="车架号" prop="frameNumber">
          <el-input v-model="form.frameNumber" placeholder="请输入车架号" />
        </el-form-item>
        <el-form-item label="车牌号" prop="licensePlate">
          <el-input v-model="form.licensePlate" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="合计保费" prop="totalPremium">
          <el-input v-model="form.totalPremium" placeholder="请输入合计保费" />
        </el-form-item>
        <el-form-item label="审核状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核时间" prop="reviewTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.reviewTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审核时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核人" prop="reviewUser">
          <el-input v-model="form.reviewUser" placeholder="请输入审核人" />
        </el-form-item>
        <el-form-item label="审核备注" prop="reviewRemarks">
          <el-input v-model="form.reviewRemarks" placeholder="请输入审核备注" />
        </el-form-item>
        <el-form-item label="删除状态 1正常 2删除" prop="deleteFlag">
          <el-input
            v-model="form.deleteFlag"
            placeholder="请输入删除状态 1正常 2删除"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import {
  vehicleE14List,
} from "@/api/system/vehicle";

export default {
  name: "Vehicle",
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
      // 车辆管理-车辆保险表格数据
      vehicleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 保险类型字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: undefined,
        frameNumber: null,
        sTime: null,
        fTime: null,
        licensePlate: null,
      },
      statusOptions: [
        {
          dictValue: null,
          dictLabel: "全部",
        },
        {
          dictValue: 1,
          dictLabel: "车辆保险",
        },
        {
          dictValue: 2,
          dictLabel: "货物保险",
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.getDicts("c_insurance_vehicle_insuranceType").then((response) => {
      this.typeOptions = response.data;
    });
  },
  filters: {
    status: function (state) {
      switch (state) {
        case 1:
          return "待审核";

          break;
        case 2:
          return "已通过";

          break;
        case 3:
          return "已驳回";

          break;
        default:
          return "全部";

          break;
      }
    },
  },
  methods: {
        tableRowClassName({ row, rowIndex }) {
          console.log(row);
      switch (row.outTimeFlag) {
        case 1:
          return "error-row";
          break;
        case "2":
          return "warning-row";
          break;
        case "3":
          return "success-row ";
          break;
        default:
          break;
      }
    },
    /** 查询车辆管理-车辆保险列表 */
    getList() {
      this.loading = true;
      vehicleE14List(this.queryParams).then((response) => {
        console.log(response);
        this.vehicleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 保险类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
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
        type: null,
        frameNumber: null,
        licensePlate: null,
        remarks: null,
        totalPremium: null,
        status: 0,
        reviewTime: null,
        reviewUser: null,
        reviewRemarks: null,
        deleteFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        endTime: null,
      };
      this.resetForm("form");
    },
    // 审批
    handleApproval(id) {
      this.$router.push({ path: "/editInsurance/" + id });
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
      //   this.reset();
      //   this.open = true;
      //   this.title = "添加车辆管理-车辆保险";
      this.$router.push({ path: "/addInsurance" });
    },
    /** 修改按钮操作 */
    // handleUpdate(row) {
    //   this.reset();
    //   const id = row.id || this.ids;
    //   getVehicle(id).then((response) => {
    //     this.form = response.data;
    //     this.open = true;
    //     this.title = "修改车辆管理-车辆保险";
    //   });
    // },
    /** 提交按钮 */
    // submitForm() {
    //   this.$refs["form"].validate((valid) => {
    //     if (valid) {
    //       if (this.form.id != null) {
    //         updateVehicle(this.form).then((response) => {
    //           this.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       } else {
    //         addVehicle(this.form).then((response) => {
    //           this.msgSuccess("新增成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       }
    //     }
    //   });
    // },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$router.push({ path: "/detailInsuranceAccount/" + row.id });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/vehicle/export",
        {
          ...this.queryParams,
        },
        `system_vehicle.xlsx`
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
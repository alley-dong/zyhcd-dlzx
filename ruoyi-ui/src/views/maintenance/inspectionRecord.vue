<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
     <el-form-item label="定检月份" prop="inspectDate">
        <el-date-picker v-model="queryParams.inspectDate" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
          :style="{width: '100%'}" placeholder="请选择定检月份" clearable></el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="挂车车牌号" prop="hangLicensePlate">
        <el-input
          v-model="queryParams.hangLicensePlate"
          placeholder="请输入挂车车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
            <el-table-column label="序号" type="index" width="50" align="center"></el-table-column>
      <el-table-column label="定检月份" align="center" prop="inspectDate" >
          <template slot-scope="scope">
          {{ scope.row.inspectDate.substring(0,scope.row.inspectDate.lastIndexOf('-')) }}
        </template>
      </el-table-column>
      <el-table-column label="定检车辆数" align="center" prop="totalNumber" />
      <el-table-column
        label="完成的数"
        align="center"
        prop="completeNumber"
      />
 <el-table-column
        label="未完成的数"
        align="center"
        prop="incompleteNumber"
      />

      <!-- <el-table-column label="维保类别" align="center" prop="type">
        <template slot-scope="scope">
          {{ scope.row.type == 1 ? "主车维保" : "挂车维保" }}
        </template>
      </el-table-column> -->
    
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="handleClick(scope.row)"
            v-hasPermi="['car:vehicleInspect:list']"
            >查看</el-button
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
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listRepair, getRepair, delRepair, addRepair, updateRepair } from "@/api/system/repair";
import { vehicleInspectMonthlyH22List } from "@/api/maintenance";
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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        inspectDate: null,
      },
      option: [
        {
          label: "待审核",
          value: 1,
        },
        {
          label: "已通过",
          value: 2,
        },
        {
          label: "未通过",
          value: 3,
        },
        {
          label: "已确认",
          value: 4,
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
  methods: {
    /** 查询维修保养-维修保养单列表 */
    getList() {
      this.loading = true;
      vehicleInspectMonthlyH22List(this.queryParams).then((response) => {
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
    // 表单重置
    reset() {
      this.form = {
        id: null,
        code: null,
        type: null,
        repairStatus: 0,
        driverId: null,
        driverName: null,
        mainVehicleId: null,
        mainLicensePlate: null,
        mainVehicleModelName: null,
        hangVehicleId: null,
        hangLicensePlate: null,
        hangVehicleModel: null,
        hangVehicleModelName: null,
        mileage: null,
        routeId: null,
        routeName: null,
        accidentLocation: null,
        totalMass: null,
        roadCondition: null,
        weatherCondition: null,
        repairType: null,
        isOther: null,
        accidentId: null,
        accidentTime: null,
        accidentVideo: null,
        garageId: null,
        garageName: null,
        completeTime: null,
        status: null,
        reviewTime: null,
        reviewUser: null,
        reviewRemarks: null,
        deleteFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        repairDuration: null,
        garageStatus: 0,
        garageReviewTime: null,
        garageReviewUser: null,
        garageReviewRemarks: null,
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
    /** 新增按钮操作 */
    handleAdd() {
        console.log('新增');
        this.$router.push({
          path:'applyMaintenance'
        })
    //   this.reset();
    //   this.open = true;
    //   this.title = "添加维修保养-维修保养单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // 审批
      // this.reset();
      const id = row.id || this.ids;
      this.$router.push('/approvalServiceStation/'+id)
      // getRepair(id).then((response) => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改维修保养-维修保养单";
      // });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateRepair(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRepair(this.form).then((response) => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleClick(row) {
      const id = row.id || this.ids;
      this.$router.push('/detailInspectionRecord/'+id)
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
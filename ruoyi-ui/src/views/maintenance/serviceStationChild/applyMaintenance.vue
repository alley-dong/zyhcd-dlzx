<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="维修单号" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入维修单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主车车牌号" prop="mainLicensePlate">
        <el-input
          v-model="queryParams.mainLicensePlate"
          placeholder="请输入主车车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="挂车车牌号" prop="hangLicensePlate">
        <el-input
          v-model="queryParams.hangLicensePlate"
          placeholder="请输入挂车车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="开始时间:" prop="startTime">
          <el-date-picker
            v-model="formData.startTime"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            :style="{ width: '100%' }"
            placeholder="请选择开始时间:"
            clearable
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间:" prop="finshTime">
          <el-date-picker
            v-model="formData.finshTime"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            :style="{ width: '100%' }"
            placeholder="请选择结束时间:"
            clearable
          ></el-date-picker>
        </el-form-item> -->
      <el-form-item label="维修服务站" prop="garageName">
        <el-select
          v-model="queryParams.garageName"
          placeholder="请选择维修服务站"
          clearable
          size="small"
        >
          <el-option
            v-for="item in option"
            :key="item.id"
            :label="item.name"
            :value="item.name"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
        @handleBack="handleBack"
        @handleImportant="handleImportant"
        leadingIn
        backButton
        twoButtonState
        threeButton='导出'
        style="float: right"
        :importantHasPermi="['pfservercar:repairisnew:infoexport']"
      />
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleBack"
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
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="维修单号" align="center" prop="code" />
       <el-table-column
        label="维修时间"
        align="center"
        prop="completeTime"
        width="180"
      >
        <!-- <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, "{y}-{m}-{d} {h}:{i}:{s}") }}</span>
        </template> -->
      </el-table-column>
      <el-table-column
        label="主车车牌号"
        align="center"
        prop="mainLicensePlate"
      />
      <el-table-column
        label="挂车车牌号"
        align="center"
        prop="hangLicensePlate"
      />
      <!-- <el-table-column label="维保类别" align="center" prop="type">
        <template slot-scope="scope">
          {{ scope.row.type == 1 ? "主车维保" : "挂车维保" }}
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="维保类型" align="center" prop="repairType">
        <template slot-scope="scope">
          {{ scope.row.repairType == 1 ? "主车维保" : "挂车维保" }}
        </template>
      </el-table-column> -->
      <el-table-column label="维修服务站" align="center" prop="garageName" />
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <!-- <el-table-column label="申请时间" align="center" prop="completeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="单价（元）" align="center" prop="unitPrice" />
      <el-table-column label="工时数" align="center" prop="duration" />
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="金额（元）" align="center" prop="amount" />
     
      <!-- <el-table-column label="审核状态 " align="center" prop="status">
        <template slot-scope="scope">
          <span>{{ scope.row.status | status }}</span>
        </template>
      </el-table-column> -->
      <!-- 1未开始 2进行中 3已完成 -->
      <!-- <el-table-column label="维修状态" align="center" prop="repairStatus">
        <template slot-scope="scope">
          <span>{{ scope.row.repairStatus | repairStatus }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status == 1"
            size="small"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:repair:edit']"
            plain
            type="primary"
            >审批</el-button
          >
          <el-button
            v-else
            size="small"
            type="primary"
            @click="handleClick(scope.row)"
            v-hasPermi="['system:repair:remove']"
            >查看</el-button
          >
        </template>
      </el-table-column> -->
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
import { repairH06AddList,garageCarH15List } from "@/api/maintenance";
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
        code: undefined,
        mainLicensePlate: undefined,
        hangLicensePlate: undefined,
        garageName: undefined,
        name: undefined,
      },
      option: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getOption();
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
      repairH06AddList(this.queryParams).then((response) => {
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getOption() {
      garageCarH15List({}).then((response) => {
        this.option = response.rows;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
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
    handleBack() {
    //   console.log("新增");
    this.$router.go(-1)
      //   this.reset();
      //   this.open = true;
      //   this.title = "添加维修保养-维修保养单";
    },
    handleImportant() {
      console.log("导入");
       this.download(
        "/pfserver-car/car/repair/H06Export",
        {
          ...this.queryParams,
        },
        `applyMaintenance.xlsx`
      );
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // 审批
      // this.reset();
      const id = row.id || this.ids;
      this.$router.push("/approval/" + id);
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
      this.$router.push("/lookUp/" + id);
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

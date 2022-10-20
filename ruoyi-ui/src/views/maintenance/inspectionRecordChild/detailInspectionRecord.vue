<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
     <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
           <el-form-item label="定检人" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入定检人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
            <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态"
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
      </el-form-item>
      <buttonGroup
        @handleFilter="handleQuery"
        twoButtonState
        backButton
        @handleBack="handleBack"
        style="float: right"
      />
    </el-form>

    <el-table
      v-loading="loading"
      :data="repairList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="序号" type="index" width="50" align="center"></el-table-column>
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="定检人" align="center" prop="driverName" />
      <el-table-column label="定检时间" align="center" prop="inspectDate" />


      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status != 1 ? "已完成" : "未完成" }}
        </template>
      </el-table-column>
    
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- v-if="scope.row.status == 1" -->
          <el-button
            size="small"
            type="primary"
            @click="handleClick(scope.row)"
            v-hasPermi="['car:vehicleInspectMonthlyItem:list']"
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
import { vehicleInspectMonthlyItemH23List } from "@/api/maintenance";
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
        licensePlate: null,
        monthlyId: null,
        status: null,
        driverName:undefined,//定检人
      },
      option: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "未完成",
          value: 1,
        },
        {
          label: "已完成",
          value: 2,
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
  mounted () {
    this.queryParams.monthlyId = this.$route.params.id 
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
      vehicleInspectMonthlyItemH23List(this.queryParams).then((response) => {
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
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
    handleBack(){
        this.$router.go(-1)
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
      this.$router.push('/seeInspectionRecord/'+id)
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
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
      <!-- <el-form-item label="挂车车牌号" prop="hangLicensePlate">
        <el-input
          v-model="queryParams.hangLicensePlate"
          placeholder="请输入挂车车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="审核状态" prop="status">
        <el-select
          v-model="queryParams.garageStatus"
          placeholder="请选择审核状态"
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

    <el-table
    border
      v-loading="loading"
      :data="repairList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="下发订单时间" align="center" prop="createTime" />
      <el-table-column label="维修单号" align="center" prop="code" />
      <el-table-column
        label="主车车牌号"
        align="center"
        prop="mainLicensePlate"
        width="100"
      />
      <el-table-column
        label="挂车车牌号"
        align="center"
        prop="hangLicensePlate"
        width="100"
      />
      <!-- <el-table-column label="维保类别" align="center" prop="type">
        <template slot-scope="scope">
          {{ scope.row.type == 1 ? "主车维保" : "挂车维保" }}
        </template>
      </el-table-column> -->
      <el-table-column
        label="维保类型"
        width="100"
        align="center"
        prop="repairType"
      >
        <template slot-scope="scope">
          {{ scope.row.type == 1 ? "主车维保" : "挂车维保" }}
        </template>
      </el-table-column>
      <el-table-column
        label="维修服务站"
        width="120"
        align="center"
        prop="garageName"
      />
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
        label="审批状态"
        width="100"
        align="center"
        prop="status"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.garageStatus | garageStatus }}</span>
        </template>
      </el-table-column>
      <!-- 1未开始 2进行中 3已完成 -->
      <el-table-column
        label="维修状态"
        width="100"
        align="center"
        prop="repairStatus"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.repairStatus | repairStatus }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:repair:edit']"
            plain
            type="primary"
            >审批</el-button
          > -->
          <el-button
            size="small"
            type="primary"
            @click="handleClick(scope.row)"
            v-hasPermi="['pfservercar:repair:info']"
            >查看</el-button
          >
          <el-button
            size="small"
            v-if="
              scope.row.repairStatus == 2 &&
              scope.row.garageStatus == 4
            "
            v-hasPermi="['pfservercar:repairgaragestation:notnewedit']"
            @click="handleUpdate(scope.row)"
            type="success"
            >重新提交</el-button
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
import { repairItemH09List } from "@/api/maintenance";
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
        code: null,
        mainLicensePlate: null,
        hangLicensePlate: null,
        garageStatus: null,
      },
      option: [
        {
          label: "未提交",
          value: 1,
        },
        {
          label: "待审核",
          value: 2,
        },
        {
          label: "已通过",
          value: 3,
        },
        {
          label: "已拒绝",
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
    garageStatus: function (value) {
      switch (value) {
        case 1:
          return "未提交";
          break;
        case 2:
          return "待审核";
          break;
        case 3:
          return "已通过";
          break;
        case 4:
          return "已拒绝";
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
      repairItemH09List(this.queryParams).then((response) => {
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
      console.log("新增");
      this.$router.push({
        path: "applyMaintenance",
      });
      //   this.reset();
      //   this.open = true;
      //   this.title = "添加维修保养-维修保养单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // 审批
      // this.reset();
      const id = row.id || this.ids;
      this.$router.push({
        path: "/subRepairOrder/" + id,
        query: { id: row.garageId, status: row.status },
      });
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
    handleClick(row) {
      const id = row.id || this.ids;
      switch (row.garageStatus) {
        case 1:
          this.$router.push({
            path: "/subRepairOrder/" + id,
            query: { id: row.garageId, status: row.status },
          });
          break;

        default:
          this.$router.push({path:"/seeRepairOrder/" + id,query:{status: row.status }});
          break;
      }
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

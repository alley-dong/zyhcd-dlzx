<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <!-- <el-form-item label="上报人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入上报人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="审批状态"
          clearable
          size="small"
        >
          <el-option
            v-for="item in option2"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      -->
      <el-form-item label="维修项" prop="createBy">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入维修项"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="选择开始时间"
        prop="startTime"
        label-position="right"
      >
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item
        label="选择结束时间"
        prop="finishTime"
        label-position="right"
      >
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.finishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <buttonGroup
        @handleFilter="handleQuery"
        @handleImportant="handleImportant"
        threeButton="导出"
        leadingIn
        style="float: right"
        twoButtonState
      />
    </el-form>

    <el-table
      border
      v-loading="loading"
      :data="repairList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column label="维修项" align="center" prop="name" />
      <el-table-column label="维保类别" align="center" prop="type">
        <template slot-scope="scope">
          {{ scope.row.type == 1 ? "主车维保" : "挂车维保" }}
        </template>
      </el-table-column>
      <el-table-column label="维修频次" align="center" prop="statistics">
        <template slot-scope="scope">
          {{ scope.row.statistics }}
        </template>
      </el-table-column>
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
            v-hasPermi="['car:vehicleInspect:edit']"
            plain
            type="primary"
            >审批</el-button
          >
          <el-button
            v-else
            size="small"
            type="primary"
            @click="handleClick(scope.row)"
            v-hasPermi="['car:vehicleInspect:list']"
            >查看</el-button
          >
        </template>
      </el-table-column> -->
    </el-table>

    <!-- <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->

    <!-- 添加或修改维修保养-维修保养单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="维修服务站名称" prop="name">
          <el-input
            v-model="form.name"
            placeholder="请输入维修服务站名称"
            clearable
          />
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入电话" clearable />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" clearable />
        </el-form-item>
        <el-form-item label="定点维修站" prop="isFixed">
          <el-select
            v-model="form.isFixed"
            placeholder="请选择定点维修站"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in option"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
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
// import { listRepair, getRepair, delRepair, addRepair, updateRepair } from "@/api/system/repair";
import {
  repairItemH18List,
  garageCarH15Add,
  garageCarH15Info,
  garageCarH15EditSave,
  garageCarH15Delete,
} from "@/api/maintenance";
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
        // pageSize: 10,
        name: null,
         startTime: this.parseTime(new Date(),'{y}-{m}')+'-01',
        finishTime: this.parseTime(new Date(),'{y}-{m}-{d}'),
      },
      option: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "主车定检",
          value: 1,
        },
        {
          label: "挂车定检",
          value: 2,
        },
      ],
      option2: [
        {
          label: "全部",
          value: null,
        },
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
      form: {
        // id: null,
        name: null,
        tel: null,
        address: null,
        isFixed: null,
      },
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  filters: {
    // status: function (value) {
    //   switch (value) {
    //     case 1:
    //       return "待审核";
    //       break;
    //     case 2:
    //       return "已通过";
    //       break;
    //     case 3:
    //       return "已拒绝";
    //       break;

    //     default:
    //       break;
    //   }
    // },
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
      console.log("导出");
      this.download(
        "/pfserver-car/car/repairItem/H18Export",
        {
          ...this.queryParams,
        },
        `维修项统计.xlsx`
      );
    },
    /** 查询维修保养-维修保养单列表 */
    getList() {
      this.loading = true;
      repairItemH18List(this.queryParams).then((response) => {
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 明细
    detailClick(row) {
      console.log(row, "明细");
      this.$router.push({ path: `/detailAServicStation/${row.id}` });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    handleClick(row) {
      const id = row.id || this.ids;

      this.$router.push({ path: "/seeCarRegularInspection/" + id });
    },
    // 表单重置
    reset() {
      this.form = {
        // id: null,
        name: null,
        type: null,
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
      this.reset();
      this.open = true;
      this.title = "添加维修保养-维修保养单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // 审批
      // this.reset();
      const id = row.id || this.ids;
      this.$router.push({
        path: "/editCarRegularInspection/" + row.id,
        query: { vehicleId: row.vehicleId },
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            garageCarH15EditSave(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.reset();
            });
          } else {
            garageCarH15Add(this.form).then((response) => {
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
      garageCarH15Delete(id).then((res) => {
        this.getList();
      });
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
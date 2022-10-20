<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="档案编号" prop="identifier">
        <el-input
          v-model="queryParams.identifier"
          placeholder="请输入档案编号"
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
      <el-form-item label="品牌型号" prop="brand">
        <el-input
          v-model="queryParams.brand"
          placeholder="请输入品牌型号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆类别" prop="vehicleTypeName">
        <el-input
          v-model="queryParams.vehicleTypeName"
          placeholder="请输入车辆类别"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否需要定检" prop="isInspect">
        <el-select v-model="queryParams.isInspect" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <!-- <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        > -->
        <buttonGroup
          @handleFilter="handleFilter"
          :leadingIn="true"
          @handleAdd="handleAdd()"
          @handleImportant="handleImportant"
          :addHasPermi="['pfservercar:vehicle:add']"
          :importantHasPermi="['pfservercar:vehicle:import']"
        />
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="vehicleList"
      @selection-change="handleSelectionChange"
      border
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号"  align="center" width="50"> </el-table-column>
      <el-table-column label="档案编号" align="center"  prop="identifier" />
      <el-table-column label="车牌号" align="center"  prop="licensePlate" />
      <el-table-column label="车辆类别" align="center" prop="vehicleTypeName" />
      <el-table-column label="品牌型号" align="center" prop="brand" />
      <!-- <el-table-column label="型号"  align="center" prop="vehicleModel" /> -->
      <el-table-column label="车辆类型" align="center" prop="vehicleModelName" />

      <el-table-column
        label="是否需要定检"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.isInspect == 1 ? "是" : "否" }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="行驶证注册日期
"
        align="center"
        prop="registerDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      
      <el-table-column
        label="车辆状态"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.disable == 1 ? "启用" : "禁用" }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="200"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            @click="handleUpdate(scope.row.id)"
            v-hasPermi="['pfservercar:vehicle:info']"
            >查看</el-button
          >
          <el-button
            type="danger"
            size="small"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:vehicle:delete']"
            >删除</el-button
          >   
           <el-button
          v-if="scope.row.disable == 1"
            size="small"
            @click="handleIsClick(scope.row)"
            plain
            type="danger"
            >禁用</el-button
          >
            <el-button
          v-else
            size="small"
            @click="handleIsClick(scope.row)"
            plain
            type="success"
            >启用</el-button
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
    <leadingDialog
      v-if="dialog"
      @on-close="handleClose"
      @on-upload="handleUpload"
    />
  </div>
</template>

<script>
import {
  listVehicle,
  getVehicle,
  delVehicle,
  addVehicle,
  updateVehicle,
  vehicleImport,
  D01Disable
} from "@/api/car/vehicle";
import buttonGroup from "@/views/components/com/buttonGroup";
import leadingDialog from "./com/demo";
export default {
  name: "Vehicle",
  data() {
    return {
      dialog: false,
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
      // 车辆档案表格数据
      vehicleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        identifier: null,
        licensePlate: null,
        brand: null,
        isInspect: null,
        vehicleTypeName: null,
      },
      options: [
        {
          value: null,
          label: "全部",
        },
        {
          value: 1,
          label: "是",
        },
        {
          value: 2,
          label: "否",
        },
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
  },
  components: {
    buttonGroup,
    leadingDialog,
  },
  created() {
    this.getList();
  },
  methods: {
        // 禁用
    handleIsClick(row) {
        console.log();
        D01Disable(row.id).then(res=>{
            this.getList()
        })
    },
    /** 查询车辆档案列表 */
    getList() {
      this.loading = true;
      listVehicle(this.queryParams).then((response) => {
        this.vehicleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // handleFilter() {},
    // addClick() {},
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        identifier: null,
        licensePlate: null,
        brand: null,
        driveForm: null,
        horsepower: null,
        engineNumber: null,
        frameNumber: null,
        gearbox: null,
        engine: null,
        fuelType: null,
        rearAxle: null,
        manufacturer: null,
        mainTankVolume: null,
        viceTankVolume: null,
        totalMass: null,
        approvedLoad: null,
        curbWeight: null,
        distributor: null,
        purchaseDate: null,
        purchasePrice: null,
        purchaseTax: null,
        originalPrice: null,
        gpsCost: null,
        inspectCost: null,
        scrapDate: null,
        registerDate: null,
        isInspect: null,
        inspectDate: null,
        nextInspectDate: null,
        deleteFlag: null,
        createUser: null,
        createTime: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleFilter() {
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
      this.$router.push({ path: "/vehicleFilesAdd" });
    },
    /** 修改按钮操作 */
    handleUpdate(id) {
      console.log(id);
      this.$router.push({ path: "/vehicleFilesDetail/" + id });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateVehicle(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVehicle(this.form).then((response) => {
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
      const ids = row.id;
      this.$confirm(
        '是否确认删除',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delVehicle(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    // 导入
    handleImportant() {
      this.dialog = true;
    },
    // 弹窗关闭
    handleClose(bool) {
      this.dialog = false;
      console.log(bool);
    },
    // 弹窗保存
    handleUpload(val, bool) {
      this.dialog = false;
      console.log(val, bool);
      let fd = new FormData();
      fd.append("file", val); // 要提交给后台的文件
      vehicleImport(fd)
        .then((response) => {
          console.log("提交文件", response);
          this.$message({
            message: "导入成功",
            type: "success",
          });
          this.getList();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "car/vehicle/export",
        {
          ...this.queryParams,
        },
        `car_vehicle.xlsx`
      );
    },
  },
};
</script>
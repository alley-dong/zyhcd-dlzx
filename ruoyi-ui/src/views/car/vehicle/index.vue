<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="是否需要定检 1是 2否
" prop="isInspect">
        <el-input
          v-model="queryParams.isInspect"
          placeholder="请输入是否需要定检 1是 2否
"
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
          v-hasPermi="['car:vehicle:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['car:vehicle:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['car:vehicle:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['car:vehicle:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vehicleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="档案编号" align="center" prop="identifier" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="品牌型号" align="center" prop="brand" />
      <el-table-column label="行驶证注册日期
" align="center" prop="registerDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否需要定检 1是 2否
" align="center" prop="isInspect" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['car:vehicle:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['car:vehicle:remove']"
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

    <!-- 添加或修改车辆档案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="档案编号" prop="identifier">
          <el-input v-model="form.identifier" placeholder="请输入档案编号" />
        </el-form-item>
        <el-form-item label="车牌号" prop="licensePlate">
          <el-input v-model="form.licensePlate" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="品牌型号" prop="brand">
          <el-input v-model="form.brand" placeholder="请输入品牌型号" />
        </el-form-item>
        <el-form-item label="驱动形式
" prop="driveForm">
          <el-input v-model="form.driveForm" placeholder="请输入驱动形式
" />
        </el-form-item>
        <el-form-item label="马力
" prop="horsepower">
          <el-input v-model="form.horsepower" placeholder="请输入马力
" />
        </el-form-item>
        <el-form-item label="发动机号
" prop="engineNumber">
          <el-input v-model="form.engineNumber" placeholder="请输入发动机号
" />
        </el-form-item>
        <el-form-item label="车架号
" prop="frameNumber">
          <el-input v-model="form.frameNumber" placeholder="请输入车架号
" />
        </el-form-item>
        <el-form-item label="变速箱
" prop="gearbox">
          <el-input v-model="form.gearbox" placeholder="请输入变速箱
" />
        </el-form-item>
        <el-form-item label="发动机
" prop="engine">
          <el-input v-model="form.engine" placeholder="请输入发动机
" />
        </el-form-item>
        <el-form-item label="燃油种类
" prop="fuelType">
          <el-select v-model="form.fuelType" placeholder="请选择燃油种类
">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="后桥
" prop="rearAxle">
          <el-input v-model="form.rearAxle" placeholder="请输入后桥
" />
        </el-form-item>
        <el-form-item label="制造厂商
" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造厂商
" />
        </el-form-item>
        <el-form-item label="主油箱容积
" prop="mainTankVolume">
          <el-input v-model="form.mainTankVolume" placeholder="请输入主油箱容积
" />
        </el-form-item>
        <el-form-item label="副油箱容积
" prop="viceTankVolume">
          <el-input v-model="form.viceTankVolume" placeholder="请输入副油箱容积
" />
        </el-form-item>
        <el-form-item label="车总质量
" prop="totalMass">
          <el-input v-model="form.totalMass" placeholder="请输入车总质量
" />
        </el-form-item>
        <el-form-item label="核定载质量
" prop="approvedLoad">
          <el-input v-model="form.approvedLoad" placeholder="请输入核定载质量
" />
        </el-form-item>
        <el-form-item label="整备质量
" prop="curbWeight">
          <el-input v-model="form.curbWeight" placeholder="请输入整备质量
" />
        </el-form-item>
        <el-form-item label="购车服务站
" prop="distributor">
          <el-input v-model="form.distributor" placeholder="请输入购车服务站
" />
        </el-form-item>
        <el-form-item label="购车日期
" prop="purchaseDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.purchaseDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择购车日期
">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="购车金额
" prop="purchasePrice">
          <el-input v-model="form.purchasePrice" placeholder="请输入购车金额
" />
        </el-form-item>
        <el-form-item label="购置税
" prop="purchaseTax">
          <el-input v-model="form.purchaseTax" placeholder="请输入购置税
" />
        </el-form-item>
        <el-form-item label="车辆原值
" prop="originalPrice">
          <el-input v-model="form.originalPrice" placeholder="请输入车辆原值
" />
        </el-form-item>
        <el-form-item label="GPS费用
" prop="gpsCost">
          <el-input v-model="form.gpsCost" placeholder="请输入GPS费用
" />
        </el-form-item>
        <el-form-item label="检车落籍费用
" prop="inspectCost">
          <el-input v-model="form.inspectCost" placeholder="请输入检车落籍费用
" />
        </el-form-item>
        <el-form-item label="强制报废日期
" prop="scrapDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.scrapDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择强制报废日期
">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="行驶证注册日期
" prop="registerDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.registerDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择行驶证注册日期
">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否需要定检 1是 2否
" prop="isInspect">
          <el-input v-model="form.isInspect" placeholder="请输入是否需要定检 1是 2否
" />
        </el-form-item>
        <el-form-item label="检车日期
" prop="inspectDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.inspectDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检车日期
">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="下次检车日期
" prop="nextInspectDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.nextInspectDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择下次检车日期
">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除状态 0删除 1未删除
" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除状态 0删除 1未删除
" />
        </el-form-item>
        <el-form-item label="创建人
" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入创建人
" />
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
import { listVehicle, getVehicle, delVehicle, addVehicle, updateVehicle } from "@/api/car/vehicle";

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
    /** 查询车辆档案列表 */
    getList() {
      this.loading = true;
      listVehicle(this.queryParams).then(response => {
        this.vehicleList = response.rows;
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
        createTime: null
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
      this.title = "添加车辆档案";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVehicle(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆档案";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVehicle(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVehicle(this.form).then(response => {
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
      this.$confirm('是否确认删除车辆档案编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delVehicle(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('car/vehicle/export', {
        ...this.queryParams
      }, `car_vehicle.xlsx`)
    }
  }
};
</script>
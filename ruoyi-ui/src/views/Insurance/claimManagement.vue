<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="开始时间" prop="payTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.payTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="payEndTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.payEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车牌号" prop="vehicleName">
        <el-input
          v-model="queryParams.vehicleName"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="驾驶员" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入驾驶员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item style="float: right">
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
          @handleFilter="handleQuery"
          @handleAdd="handleAdd"
          :addHasPermi="['car:insuranceClaims:add']"
          style="float: right"
        />
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:claims:add']"
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
          v-hasPermi="['system:claims:edit']"
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
          v-hasPermi="['system:claims:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:claims:export']"
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
      :data="claimsList"
      @selection-change="handleSelectionChange"
      :row-class-name="tableRowClassName"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column
        label="事故时间"
        align="center"
        prop="accidentTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.accidentTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事故车牌号" align="center" prop="vehicleName" />
      <el-table-column label="驾驶员" align="center" prop="driverName" />
      <el-table-column
        label="事故地点"
        align="center"
        prop="accidentLocation"
      />
      <el-table-column
        label="发起理赔时间"
        align="center"
        prop="payTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="理赔结束时间"
        align="center"
        prop="payEndTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payEndTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      
      <!-- <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="车辆ID" align="center" prop="vehicleId" />
      <el-table-column label="事故ID" align="center" prop="accidentId" /> -->
      <!-- <el-table-column
        label="事故时间"
        align="center"
        prop="accidentTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.accidentTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="事故地点"
        align="center"
        prop="accidentLocation"
      /> -->
      <!-- <el-table-column label="司机ID" align="center" prop="driverId" />

      <el-table-column
        label="删除状态 1正常 2删除"
        align="center"
        prop="deleteFlag"
      /> -->
      <el-table-column
        label="操作"
        align="center"
        width="200"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            v-hasPermi="['car:insuranceClaims:detail']"
            @click="handleSeeClick(scope.row)"
            >查看</el-button
          >
          <el-button
            size="small"
            type="primary"
            v-hasPermi="['car:insuranceClaims:edit']"
            plain
            @click="handleUpdate(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            v-hasPermi="['car:insuranceClaims:delete']"
            @click="handleDelete(scope.row)"
            >删除</el-button
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

    <!-- 添加或修改车辆管理-理赔对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="车牌号" prop="vehicleName">
          <el-autocomplete
            class="inline-input"
            v-model="form.vehicleName"
            :fetch-suggestions="querySearch"
            placeholder="请输入内容"
            @select="handleSelect"
            :style="{ width: '100%' }"
          >
            <template slot-scope="{ item }" :style="{ width: '100%' }">
              <span class="addr">{{ item.vehicleName }}</span>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="事故时间" prop="accidentTime">
          <el-select
            :disabled="!form.vehicleName"
            v-model="form.accidentTime"
            placeholder="请选择"
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in options1"
              :key="item.accidentTime"
              :label="item.accidentTime"
              :value="item.accidentTime"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发起赔付时间" prop="payTime">
          <el-date-picker
            clearable
            v-model="form.payTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择赔付时间"
            :style="{ width: '100%' }"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="理赔结束时间" prop="payEndTime">
          <el-date-picker
            clearable
            v-model="form.payEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择赔付时间"
            :style="{ width: '100%' }"
          >
          </el-date-picker>
        </el-form-item>

        <!-- <el-form-item label="赔付方姓名" prop="payer">
          <el-input v-model="form.payer" placeholder="请输入赔付方姓名" />
        </el-form-item>
        <el-form-item label="赔付金额" prop="payAmount">
          <el-input v-model="form.payAmount" placeholder="请输入赔付金额" />
        </el-form-item>
        <el-form-item label="赔付时间" prop="payTime">
          <el-date-picker
            clearable
            v-model="form.payTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择赔付时间"
            :style="{ width: '100%' }"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车牌号" prop="vehicleName">
          <el-autocomplete
            class="inline-input"
            v-model="form.vehicleName"
            :fetch-suggestions="querySearch"
            placeholder="请输入内容"
            @select="handleSelect"
            :style="{ width: '100%' }"
          >
            <template slot-scope="{ item }" :style="{ width: '100%' }">
              <span class="addr">{{ item.vehicleName }}</span>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="事故时间" prop="accidentTime"> -->
          <!-- <el-date-picker
            clearable
            v-model="form.accidentTime"
            type="date"
            value-format="yyyy-MM-dd"
            :style="{ width: '100%' }"
            placeholder="选择事故时间"
          > -->
          <!-- </el-date-picker> -->
          <!-- <el-select
            :disabled="!form.vehicleName"
            v-model="form.accidentTime"
            placeholder="请选择"
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in options1"
              :key="item.accidentTime"
              :label="item.accidentTime"
              :value="item.accidentTime"
            >
            </el-option>
          </el-select>
        </el-form-item> -->
        <!-- <el-form-item label="删除状态 1正常 2删除" prop="deleteFlag">
          <el-input
            v-model="form.deleteFlag"
            placeholder="请输入删除状态 1正常 2删除"
          />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="saveLoading" type="primary" @click="submitForm"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listClaims, getClaims, delClaims, addClaims, updateClaims } from "@/api/system/claims";
import {
  insuranceClaimsE18List,
  commonSelectBoxE18ShiGuTimeList, //事故时间下拉选
  commonSelectBoxE18ShiGuCarNumList, //事故车牌号下拉选
  insuranceClaimsE18GetInfo,
  insuranceClaimsE18Add,
  insuranceClaimsE18Edit,
  insuranceClaimsE18Delete,
} from "@/api/claimManagement";
import buttonGroup from "@/views/components/com/buttonGroup";

export default {
  name: "Claims",
  data() {
    return {
      saveLoading: false,
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
      // 车辆管理-理赔表格数据
      claimsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        payer: null,
        payEndTime: null,
        vehicleName: null,
        driverName: null,
        payTime: null,
      },
      //   options: [],
      options1: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  components: {
    buttonGroup,
  },
  created() {
    this.getList();
  },
  mounted() {
    this.init();
  },
  methods: {
    tableRowClassName({ row, rowIndex }) {
      switch (row.colorFlag) {
        case 1:
          return "error-row";
          break;
        // case 2:
        //   return "warning-row";
        //   break;
        // case 3:
        //   return "success-row ";
        //   break;
        default:
          break;
      }
    },
    handleSeeClick(row) {
      console.log("查看");
      this.$router.push("/seeClaimManagement/" + row.id);
    },
    //   初始化
    init() {
      //   this.commonSelectBoxE18ShiGuTimeDataList();
      //   this.commonSelectBoxE18ShiGuCarNumDataList();
    },
    commonSelectBoxE18ShiGuTimeDataList() {
      let obj = {
        vehicleName: this.form.vehicleName,
      };
      commonSelectBoxE18ShiGuTimeList(obj).then((res) => {
        if(res.data.length === 0) {
          this.form.accidentTime = ''
        }
        this.options1 = res.data;
      });
    },
    // commonSelectBoxE18ShiGuCarNumDataList() {
    //   let obj = {
    //     vehicleName: this.form.vehicleName,
    //   };
    //   commonSelectBoxE18ShiGuCarNumList(obj).then((res) => {
    //     console.log(res);
    //     return res.data;
    //   });
    // },

    handleSelect(item) {
      console.log(item);
      this.form.vehicleName = item.vehicleName;
      this.commonSelectBoxE18ShiGuTimeDataList();
    },
    querySearch(queryString, cb) {
      let obj = {
        vehicleName: this.form.vehicleName,
      };
      commonSelectBoxE18ShiGuCarNumList(obj).then((res) => {
        cb(res.data);
      });
      // 调用 callback 返回建议列表的数据
    },
    // createFilter(queryString) {
    //   return (restaurant) => {
    //     return (
    //       restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
    //       0
    //     );
    //   };
    // },
    /** 查询车辆管理-理赔列表 */
    getList() {
      this.loading = true;
      insuranceClaimsE18List(this.queryParams).then((response) => {
        this.claimsList = response.rows;
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
        vehicleId: null,
        vehicleName: null,
        accidentId: null,
        accidentTime: null,
        accidentLocation: null,
        driverId: null,
        driverName: null,
        payer: null,
        payAmount: null,
        payTime: null,
        deleteFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
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
      this.title = "添加车辆管理-理赔";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      insuranceClaimsE18GetInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆管理-理赔";
      });
    },
    /** 提交按钮 */
    submitForm() {
      console.log('.this.form', this.form)
      this.saveLoading = true;
      setTimeout(() => {
        this.saveLoading = false;
      }, 1000);
      this.$refs["form"].validate((valid) => {
        //   debugger
        if (valid) {
          if (this.form.id != null) {
            insuranceClaimsE18Edit(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            insuranceClaimsE18Add(this.form).then((response) => {
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
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return insuranceClaimsE18Delete(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/claims/export",
        {
          ...this.queryParams,
        },
        `system_claims.xlsx`
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

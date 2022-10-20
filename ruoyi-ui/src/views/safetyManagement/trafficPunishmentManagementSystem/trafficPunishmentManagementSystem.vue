<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="form"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
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
      <el-form-item label="驾驶员" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入驾驶员"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态："
          clearable
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in routerOptions"
            :key="index"
            :label="item.value"
            :value="item.label"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="float:right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
          >查询</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="small "
          @click="handleAdd"
          v-hasPermi="['person:safeTrafficTransgression:add']"
          >新增</el-button
        >
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="违章车辆" align="center" prop="licensePlate" />
      <el-table-column label="违法时间" align="center" prop="wzsj" />
      <el-table-column label="驾驶员" align="center" prop="driverName" />
      <el-table-column
        label="处理违章截止时间"
        align="center"
        prop="deadline"
      />
      <el-table-column
        label="处理完成时间"
        align="center"
        prop="completeTime"
      />
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">未处理</span>
          <span v-else-if="scope.row.status === 2">已处理</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-row>
            <el-col :span="12">
              <el-button
                size="small "
                type="primary"
                v-hasPermi="['person:safeTrafficTransgression:getInfo']"
                v-show="scope.row.driverName == ''"
                @click="handlExamine1(scope.row)"
              >
                <span>查看</span>
              </el-button>
              <el-button
                size="small "
                type="primary"
                v-hasPermi="['person:safeTrafficTransgression:getInfo']"
                v-show="scope.row.driverName != '' && scope.row.status == 1"
                @click="handleUpdate(scope.row)"
              >
                <span>查看</span>
              </el-button>
              <el-button
                size="small "
                type="primary"
                v-hasPermi="['person:safeTrafficTransgression:getInfo']"
                v-show="scope.row.driverName != '' && scope.row.status == 2"
                @click="handlEprocessed(scope.row)"
              >
                <span>查看</span>
              </el-button>
            </el-col>
            <el-col :span="12">
              <el-button
                v-if="scope.row.status == 1"
                size="small "
                type="danger"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:transgression:remove']"
              >
                删除
              </el-button>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getL05List"
    />
  </div>
</template>

<script>
// import {
//   addcarlist //车辆下拉
// } from "@/api/card/oilCardManagement";
// import {
//   // vehicleModelBox,
//   updateQuota,
//   geteQuota,
//   delfuelQuota
// } from "@/api/card/fuelration";
import {
  L05List,
  L05Delete
} from "@/api/safetyManagement/trafficPunishmentManagementSystem";
export default {
  name: "Card",
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
      // 油卡定额管理表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licensePlate: "",
        driverName: "",
        status: ""
      },
      // 新增定额参数
      form: {
        routeId: "",
        routeName: "",
        vehicleModel: "",
        vehicleModelName: "",
        quota: "",
        name: "",
        id: ""
      },
      vehicleOptions: [],
      // 状态
      routerOptions: [
        { label: "2", value: "已处理" },
        { label: "1", value: "未处理" }
      ],

      // 表单校验
      rules: {},

      vehicleModelNamelist: [],
      oilslist: []
    };
  },
  created() {
    // this.getvehicleModelName();
    // this.getoils();
    // this.addvehicleModelBox();
    this.getL05List();
  },
  methods: {
    //违章管理-违章列表
    getL05List() {
      this.loading = true;
      L05List(this.queryParams).then(res => {
        this.cardList = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },

    //车型下拉选
    // addvehicleModelBox() {
    //   vehicleModelBox({}).then(res => {
    //     this.vehicleOptions = res.data;
    //   });
    // },
    // addvehicleModelBoxChange(val) {
    //   for (const item of this.vehicleOptions) {
    //     if (val == item.name) {
    //       console.log(item.id);
    //       this.form.vehicleModel = item.id;
    //     }
    //   }
    // },
    // 车辆列表
    // getvehicleModelName() {
    //   addcarlist({}).then(res => {
    //     this.vehicleModelNamelist = res.data;
    //     console.log("车牌号" + this.vehicleModelNamelist);
    //   });
    // },
    // handleChange(val) {
    //   console.log(val, JSON.stringify(this.vehicleModelNamelist));
    //   for (const item of this.vehicleModelNamelist) {
    //     if (val == item.id) {
    //       console.log(item);
    //       console.log(item.licensePlate);
    //       this.form.licensePlate = item.licensePlate;
    //     }
    //   }
    // },
    // 油卡下拉
    // getoils() {
    //   this.getDicts("f_oil_type").then(response => {
    //     this.oilslist = response.data;
    //     console.log(this.oilslist);
    //   });
    // },

    // handleoilsChange(val) {
    //   console.log(val, JSON.stringify(this.oilslist));
    //   for (const item of this.oilslist) {
    //     if (val == item.id) {
    //       this.form.dictLabel = item.dictLabel;
    //     }
    //   }
    // },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        licensePlate: null,
        oilCardNumber: null,
        oils: null,
        cardTime: null,
        dueTime: null,
        id: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      // this.getList();
      this.getL05List();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.id);
    //   this.single = selection.length !== 1;
    //   this.multiple = !selection.length;
    // },
    /** 新增按钮操作 */
    handleAdd() {
      this.$router.push({ path: "/newDetailsOfViolations" });
    },
    /** 已派发查看 */
    handleUpdate(row) {
      // this.reset();
      this.$router.push({ path: "/violationOfTheDetails/" + row.id });
    },
    // 未派发查看
    handlExamine1(row) {
      // this.reset();
      this.$router.push({ path: "/didNotPay/" + row.id });
    },
    // 已处理
    handlEprocessed(row) {
      this.$router.push({
        path: "/detailsOfViolationHaveBeenDealtWith/" + row.id
      });
    },
    /** 提交按钮 */

    // submitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       if (this.form.id != null) {
    //           updateQuota(this.form).then(response => {
    //           this.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       } else {
    //         addQuota(this.form).then(response => {
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
      const id = row.id;
      const licensePlate = row.licensePlate;
      this.$confirm(
        '是否确认删除违章车辆"' + licensePlate + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return L05Delete(id);
        })
        .then(() => {
          this.getL05List();
          this.msgSuccess("删除成功");
        });
    }
  }
};
</script>
<style scoped>
.el-button + .el-button {
  margin-left: 0px;
}
</style>

<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="计划日期" prop="routeName">
        <el-date-picker
          v-model="queryParams.planDate"
          value-format="yyyy-MM"
          type="month"
          placeholder="选择日期"
        >
        </el-date-picker>
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
          v-hasPermi="['pfservercar:plan:add']"
          >生成燃料计划</el-button
        >
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="计划日期" align="center" prop="planDate" />
      <el-table-column label="车辆数" align="center" prop="carQuantity" />
      <el-table-column
        label="油料总金额(元)"
        align="center"
        prop="fuelPriceTotal"
      />
      <el-table-column
        label="空放总金额(元)"
        align="center"
        prop="ballastQuotaPriceTotal"
      />
      <el-table-column
        label="倒货总金额(元)"
        align="center"
        prop="pourQuotaPriceTotal"
      />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small "
            type="primary"
            @click="handleClick(scope.row)"
            v-hasPermi="['pfservercar:plan:carList']"
            style="color:#fff"
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

    <!-- 添加或修改油卡管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="addform" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="计划日期" prop="planDate">
          <el-date-picker
            clearable
            size="small"
            style="width: 100%"
            v-model="addform.planDate"
            type="month"
            value-format="yyyy-MM"
            placeholder="选择计划日期"
          >
          </el-date-picker>
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
import {
  RoutList,
  PlantsRouteAdd,
  PlantsRouteList,
  PlantsRouteEditSave,
  CardInfo,
  delectCardInfo
} from "@/api/card/fuelPoint";
import { FuelOilPlanList, FuelOilPlanAdd } from "@/api/card/fuelUsePlan";
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
      // 燃油使用计划-燃油使用计划列表
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planned: "",
        planDate: ""
      },
      // 新增生成燃油计划
      addform: {
        planDate: ""
      },
      // 表单参数
      form: {},

      vehicleOptions: [],
      // 线路下拉
      routerOptions: [],

      // 表单校验
      rules: {},

      vehicleModelNamelist: [],
      oilslist: []
    };
  },
  created() {
    this.routerselect();
    this.getList();
  },

  methods: {
    // 路线下拉
    routerselect() {
      RoutList({}).then(res => {
        this.routerOptions = res.data;
      });
    },

    addquotaRouterBoxData(val) {
      console.log(val);
      for (const item of this.routerOptions) {
        if (val == item.name) {
          this.addform.routeId = item.id;
        }
      }
    },
    /** 查询燃油使用计划-燃油使用计划列表  */
    getList() {
      this.loading = true;
      FuelOilPlanList(this.queryParams).then(response => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      // this.reset();
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    /** 生成燃料计划 */
    handleAdd(row) {
      this.open = true;
      this.title = "选择计划日期";
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["addform"].validate(valid => {
        if (valid) {
          if (this.addform.id != null) {
            PlantsRouteEditSave(this.addform).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            FuelOilPlanAdd(this.addform).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    // 查看
    handleClick(row) {
      // console.log(row);
      const planDate = row.planDate;
      console.log(planDate);
      this.$router.push({ path: "/checkYourFuelUsagePlan/" + planDate });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除油卡管理编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delectCardInfo(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    }
  }
};
</script>

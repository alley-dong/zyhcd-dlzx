<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="form"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item
        label="定额名称"
        prop="name"
        :rules="[{ max: 20, message: '最大长度为20个字符', trigger: 'blur' }]"
      >
        <el-input
          v-model="queryParams.name"
          placeholder="请输入定额名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线路" prop="routeName">
        <el-select
          v-model="queryParams.routeName"
          placeholder="请选择路线："
          @change="quotaRouterBoxData"
          clearable
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in routerOptions"
            :key="index"
            :label="item.name"
            :value="item.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="float: right">
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
          v-hasPermi="['pfservercar:quota:add']"
          >新增</el-button
        >
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="cardList"
      @selection-change="handleSelectionChange"
      border
      :row-class-name="tableRowClassName"
    >
      <el-table-column label="定额名称" align="center" prop="name" />
      <el-table-column label="线路" align="center" prop="routeName" />
      <el-table-column label="车型" align="center" prop="vehicleModelName" />
      <el-table-column label="油料类型" align="center" prop="fuelTypeEnum" />
      <el-table-column label="油料定额（升）" align="center" prop="quota" />
      <el-table-column label="空放定额（升）" align="center" prop="ballastQuota" />
      <el-table-column label="倒货定额（升）" align="center" prop="pourQuota" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small "
            type="primary"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:quota:edit']"
            style="color: #fff"
            >修改</el-button
          >
          <el-button
            size="small "
            type="danger"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:quota:delete']"
            style="color: #fff"
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

    <!-- 添加或修改油卡管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="定额名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入定额名称" />
        </el-form-item>
        <el-form-item label="线路ID" prop="routeId" hidden>
          <el-input v-model="form.routeId" placeholder="请输入线路ID" />
        </el-form-item>
        <el-form-item label="线路名称" prop="routeName">
          <el-select
            v-model="form.routeName"
            placeholder="请选择路线："
            @change="addquotaRouterBoxData"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in routerOptions"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆ID" prop="vehicleModel" hidden>
          <el-input v-model="form.vehicleModel" placeholder="请输入车辆ID" />
        </el-form-item>
        <el-form-item label="车型名称" prop="vehicleModelName">
           <el-autocomplete
              class="inline-input"
              v-model="form.vehicleModelName"
              :fetch-suggestions="querySearch"
              @select="handleSelect"
              placeholder="请输入车辆类型"
              @change="openBlur(form.vehicleModelName)"
              :style="{ width: '100%' }"
            >
              <template slot-scope="{ item }" :style="{ width: '100%' }">
                <span class="addr">{{ item.name }}</span>
              </template>
            </el-autocomplete>
          <!-- <el-select
            v-model="form.vehicleModelName"
            placeholder="请选择车辆型号："
            @change="addvehicleModelBoxChange"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in vehicleOptions"
              :key="index"
              :label="item.name"
              :value="item.name"
            ></el-option>
          </el-select> -->
        </el-form-item>

        <el-form-item label="油料类型" prop="fuelType">
          <el-select
            v-model="form.fuelType"
            placeholder="请选择油料类型："
            @change="addYouliaoData"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in youliaoOptions"
              :key="index"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="油料定额"
          prop="quota"
          :rules="[{ type: 'number', message: '油料定额必须为数字值' }]"
        >
          <el-input v-model.number="form.quota" placeholder="请输入油料定额" />
        </el-form-item>
        <el-form-item
          label="空放定额"
          prop="ballastQuota"
          :rules="[{ type: 'number', message: '空放定额必须为数字值' }]"
        >
          <el-input v-model.number="form.ballastQuota" placeholder="请输入空放定额" />
        </el-form-item>
        <el-form-item
          label="倒货定额"
          prop="pourQuota"
          :rules="[{ type: 'number', message: '倒货定额必须为数字值' }]"
        >
          <el-input v-model.number="form.pourQuota" placeholder="请输入倒货定额" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="btn_loading" @click="submitForm"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  addcarlist, //车辆下拉
} from "@/api/card/oilCardManagement";
import {
  routerBox,
  PlantsMoneyList,
  addQuota,
  vehicleModelBox,
  updateQuota,
  geteQuota,
  delfuelQuota,
} from "@/api/card/fuelration";
import { Message } from 'element-ui'
export default {
  name: "Card",
  data() {
    return {
      btn_loading: false,
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
        routeId: "",
        vehicleModelName: "",
        name: "",
      },
      // 新增定额参数
      form: {
        routeId: "",
        routeName: "",
        vehicleModel: "",
        vehicleModelName: "",
        quota: "",
        name: "",
        id: "",
        ballastQuota: '',
        pourQuota: '',
        fuelType: null,
      },
      vehicleOptions: [],
      // 线路下拉
      routerOptions: [],

      youliaoOptions:[],

      // 表单校验
      rules: {},

      vehicleModelNamelist: [],
      oilslist: [],
    };
  },
  created() {
    this.getList();
    this.getvehicleModelName();
    this.getoils();
    this.routerselect();
    this.addvehicleModelBox();
    this.getDicts("f_fuel_quota_type").then((response) => {
      this.youliaoOptions = response.data;
      console.log("this.youliaoOptions",this.youliaoOptions)
      console.log("this.response",response)
    });
  },
  methods: {
        handleSelect(item) {
      console.log(item);
          this.form.vehicleModel = item.id;
          this.form.vehicleModelName = item.name;
    },
    querySearch(queryString, cb) {
      var restaurants = this.vehicleOptions;
      console.log(this.createFilter(queryString));
      //   var results = this.createFilter(queryString);
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return restaurant => {
        return (
          restaurant.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    // 路线下拉
    routerselect() {
      routerBox({}).then((res) => {
        this.routerOptions = res.data;
      });
    },
    quotaRouterBoxData(val) {
      for (const item of this.routerOptions) {
        if (val == item.id) {
          this.queryParams.routeName = item.routeName;
        }
      }
    },
    addquotaRouterBoxData(val) {
      // console.log(val);
      for (const item of this.routerOptions) {
        if (val == item.id) {
          console.log(item.id);
          // this.form.routeName = item.routeName;
          this.form.routeName = item.name;
          this.form.routeId = item.id;
        }
      }
    },

    openBlur(name){
      var restaurants = this.vehicleOptions;
      var results = name
        ? restaurants.filter(this.createFilter(name))
        : restaurants;
      if(results.length > 0){

      } else {
        this.form.vehicleModelName = "";
        Message({message: '请在选项中选择名称',type: 'error'})
      }
    },

    addYouliaoData(val) {
      console.log("val",val);
      for (const item of this.youliaoOptions) {
        if (val == item.dictValue) {
          console.log(item.dictValue);
          this.form.fuelType = item.dictValue;
        }
      }
    },
    //车型下拉选
    addvehicleModelBox() {
      vehicleModelBox({}).then((res) => {
        this.vehicleOptions = res.data;
      });
    },
    addvehicleModelBoxChange(val) {
      for (const item of this.vehicleOptions) {
        if (val == item.name) {
          console.log(item.id);
          this.form.vehicleModel = item.id;
        }
      }
    },
    // 车辆列表
    getvehicleModelName() {
      addcarlist({}).then((res) => {
        this.vehicleModelNamelist = res.data;
        console.log("车牌号" + this.vehicleModelNamelist);
      });
    },
    handleChange(val) {
      console.log(val, JSON.stringify(this.vehicleModelNamelist));
      for (const item of this.vehicleModelNamelist) {
        if (val == item.id) {
          console.log(item);
          console.log(item.licensePlate);
          this.form.licensePlate = item.licensePlate;
        }
      }
    },
    /** 查询油卡定额管理列表 */
    getList() {
      this.loading = true;
      PlantsMoneyList(this.queryParams).then((response) => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 油卡下拉
    getoils() {
      this.getDicts("f_oil_type").then((response) => {
        this.oilslist = response.data;
        console.log(this.oilslist);
      });
    },

    handleoilsChange(val) {
      console.log(val, JSON.stringify(this.oilslist));
      for (const item of this.oilslist) {
        if (val == item.id) {
          this.form.dictLabel = item.dictLabel;
        }
      }
    },
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
        id: null,
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
      this.title = "添加油卡定额管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.open = true;
      this.title = "修改油料定额管理";
      geteQuota(row.id).then((response) => {
        this.form = response.data;
        console.log("线路" + response.data.routeName);
        this.addYouliaoData(response.data.fuelType);
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        this.btn_loading = true;
        setTimeout(() => {
          this.btn_loading = false;
        }, 1000);
        if (valid) {
          if (this.form.id != null) {
            updateQuota(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuota(this.form).then((response) => {
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
      const name = row.name;
      this.$confirm('是否确认删除', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delfuelQuota(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    tableRowClassName(row) {
      console.log(row);
      switch (row.mathDays) {
        case "1":
          return "error-row";
          break;
        case "2":
          return "warning-row";
          break;
        case "3":
          return "success-row ";
          break;
        default:
          break;
      }
    },
  },
};
</script>
<style lang="scss">
.el-table .error-row {
  background: #ff4949;
}
</style>

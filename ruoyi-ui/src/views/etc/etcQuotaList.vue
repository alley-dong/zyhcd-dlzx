<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
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
      <el-form-item label="线路名称" prop="routeName">
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

      <el-button
        size="small "
        type="primary"
        icon="el-icon-plus"
        style="float: right; margin-right: 10px"
        @click="handleAdd"
        v-hasPermi="['car:etcCard:add']"
        >新增</el-button
      >
      <el-button
        type="cyan"
        icon="el-icon-search"
        size="small "
        style="float: right; margin-right: 10px"
        @click="handleQuery"
        >查询</el-button
      >
    </el-form>
    <el-table
      v-loading="loading"
      border
      :data="quotaList"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column
        label="定额名称"
        align="center"
        prop="name"
      ></el-table-column>
      <el-table-column label="线路名称" align="center" prop="routeName" />
      <el-table-column label="车辆类型" align="center" prop="vehicleModelName" />
      <el-table-column label="ETC定额" align="center" prop="quota" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small "
            type="primary"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['car:etcCard:edit']"
            >修改</el-button
          >
          <el-button
            size="small "
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['car:etcCard:delete']"
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

    <!-- 添加或修改ETC管理-ETC定额对话框 -->
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
              :value="item.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆ID" prop="vehicleModel" hidden>
          <el-input v-model="form.vehicleModel" placeholder="请输入车辆ID" />
        </el-form-item>
        <el-form-item label="车型名称" prop="vehicleModelName">
          <el-select
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
          </el-select>
        </el-form-item>

        <el-form-item
          label="ETC定额"
          prop="quota"
          :rules="[{ required: true, type: 'number', message: '请输入数字' }]"
        >
          <el-input v-model.number="form.quota" placeholder="请输入ETC定额" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="btn_loading" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listQuota,
  getQuota,
  addQuota,
  updateQuota,
  delQuota,
  routerBox,
  vehicleModelBox,
  G02ZhuCarList
} from "@/api/etc/quota";

export default {
  name: "Quota",
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
      // ETC管理-ETC定额表格数据
      quotaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        routeName: null,
        name: null
      },
      // 表单参数
      form: {},
      routerOptions: [],
      vehicleOptions: [],
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
    this.quotaRouterBox();
    this.addvehicleModelBox();
  },
  mounted() {
    // 获取路线
    this.getG02ZhuCarList();
  },
  methods: {
    // 车型下拉
    getG02ZhuCarList() {
      G02ZhuCarList({}).then(res => {
        this.vehicleOptions = res.data;
      });
    },
    //路线下拉选
    quotaRouterBox() {
      routerBox({}).then(res => {
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

    addvehicleModelBoxChange(val) {
      for (const item of this.vehicleOptions) {
        if (val == item.name) {
          console.log(item.id);
          this.form.vehicleModel = item.id;
        }
      }
    },
    // addvehicleModelBoxData(val) {
    //   for (const item of this.vehicleOptions) {
    //     if (val == item.name) {
    //       this.form.vehicleModel = item.id;
    //     }
    //   }
    // },
    // addvehicleModelBoxData(val){
    //   for(const item of this.vehicleOptions){
    //     if (val == item.id) {
    //       this
    //     }
    //   }
    // },
    addquotaRouterBoxData(val) {
      // console.log(val);
      for (const item of this.routerOptions) {
        if (val == item.name) {
          console.log(item.id);
          // this.form.routeName = item.routeName;
          this.form.routeId = item.id;
        }
      }
    },
    /** 查询ETC管理-ETC定额列表 */
    getList() {
      this.loading = true;
      listQuota(this.queryParams).then(response => {
        this.quotaList = response.rows;
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
        routeId: null,
        routeName: null,
        vehicleModel: null,
        vehicleModelName: null,
        name: null,
        quota: null,
        deleteFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加ETC管理-ETC定额";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getQuota(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改ETC管理-ETC定额";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
             this.btn_loading = true;
        setTimeout(() => {
          this.btn_loading = false;
        }, 1000);
          // this.form.quota = parseFloat(this.form.quota)
          if (this.form.id != null) {
            updateQuota(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuota(this.form).then(response => {
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
      this.$confirm(
        '是否确认删除定额名称为"' + row.name + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(function() {
          return delQuota(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/quota/export",
        {
          ...this.queryParams
        },
        `system_quota.xlsx`
      );
    }
  }
};
</script>

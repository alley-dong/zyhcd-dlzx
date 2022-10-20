<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item
        label="定点名称"
        prop="name"
        :rules="[{ max: 20, message: '最大长度为20个字符', trigger: 'blur' }]"
      >
        <el-input
          v-model="queryParams.name"
          placeholder="请输入定点名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="线路" prop="routeName">
        <el-select
          v-model="queryParams.routeName"
          placeholder="请选择路线："
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
          size="small "
          icon="el-icon-plus"
          @click="handleAdd"
          v-hasPermi="['pfservercar:place:add']"
          >新增</el-button
        >
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="定点名称" align="center" prop="name" />
      <el-table-column label="线路" align="center" prop="routeName" />
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
            v-hasPermi="['pfservercar:place:info']"
            style="color:#fff"
            >查看</el-button
          >
          <el-button
            size="small "
            type="primary"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:place:edit']"
            style="color:#fff"
            >编辑</el-button
          >
          <el-button
            size="small "
            type="danger"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:place:delete']"
            style="color:#fff"
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
      <el-form ref="addform" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="定点名称" prop="name">
          <el-input v-model="addform.name" placeholder="请输入定点名称" />
        </el-form-item>
        <el-form-item label="线路名称" prop="routeName">
          <el-select
            v-model="addform.routeName"
            placeholder="请选择路线："
            @change="addquotaRouterBoxData"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in routerOptions"
              :key="item.id"
              :label="item.name"
              :value="item.name"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="btn_loading"  @click="submitForm">确 定</el-button>
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
        name: ""
      },
      // 新增定额参数
      addform: {
        routeId: "",
        name: ""
      },
      // 表单参数
      form: {
        routeId: "",
        name: ""
      },

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
    /** 查询定点管理-定点列表 */
    getList() {
      this.loading = true;
      PlantsRouteList(this.queryParams).then(response => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.open = true;
      this.title = "添加燃料定点管理";
    },
    // 表单重置
    reset() {
      this.addform = {
        routeId: null,
        name: null
      };
      this.resetForm("addform");
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.reset();
      this.open = true;
      this.title = "修改燃料定点管理";
      CardInfo(row.id).then(res => {
        this.addform = res.data;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["addform"].validate(valid => {
           this.btn_loading = true;
        setTimeout(() => {
          this.btn_loading = false;
        }, 1000);
        if (valid) {
          if (this.addform.id != null) {
            PlantsRouteEditSave(this.addform).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            PlantsRouteAdd(this.addform).then(response => {
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
      this.$router.push({ path: "/see/" + row.id });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
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

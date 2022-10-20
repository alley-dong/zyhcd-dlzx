<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <el-form-item label="维修服务站名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入维修服务站名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <!-- <el-form-item label="维保类别" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择维保类别"
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
      </el-form-item> -->
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
        @handleAdd="handleClick"
        style="float: right"
        :addHasPermi="['pfservercar:garageCar:add']"
      />
    </el-form>

    <el-table
    border
      v-loading="loading"
      :data="repairList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="维修服务站名称" align="center" prop="name" />
      <el-table-column label="电话" align="center" prop="tel" />
      <el-table-column label="地点" align="center" prop="address" />

      <el-table-column label="定点维修站" align="center" prop="isFixed">
        <template slot-scope="scope">
          {{ scope.row.isFixed == 1 ? "是" : "否" }}
        </template>
      </el-table-column>

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:garageCar:edit']"
            plain
            type="primary"
            >编辑</el-button
          >
          <el-button
            size="small"
            @click="detailClick(scope.row)"
            v-hasPermi="['pfservercar:garagerepairitem:list']"
            plain
            type="success"
            >维修名细</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleRemove(scope.row)"
            v-hasPermi="['pfservercar:garageCar:delete']"
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
        <el-button type="primary" :loading="btn_loading" @click="submitForm"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listRepair, getRepair, delRepair, addRepair, updateRepair } from "@/api/system/repair";
import {
  garageCarH15List,
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
        name: null,
      },
      option: [
        // {
        //   label: "全部",
        //   value: null,
        // },
        {
          label: "是",
          value: 1,
        },
        {
          label: "否",
          value: 2,
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
        /* 
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
          break; */
      }
    },
  },
  methods: {
    /** 查询维修保养-维修保养单列表 */
    getList() {
      this.loading = true;
      garageCarH15List(this.queryParams).then((response) => {
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
    handleClick() {
      console.log("新增");
      this.open = true;
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
      this.title = "新增维修服务站";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // 审批
      // this.reset();
      this.open = true;
      const id = row.id || this.ids;
      garageCarH15Info(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改维修服务站";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.btn_loading = true;
          setTimeout(() => {
            this.btn_loading = false;
          }, 1000);
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
      // garageCarH15Delete(id).then((res) => {
      //   this.getList();
      // });
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return garageCarH15Delete(id);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
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

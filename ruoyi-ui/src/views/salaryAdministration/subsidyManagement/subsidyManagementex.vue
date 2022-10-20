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
        label="驾驶员姓名"
        prop="createBy"
        label-width="auto"
        :rules="[{ max: 20, message: '最大长度为20个字符', trigger: 'blur' }]"
      >
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入驾驶员姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择："
          clearable
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in approval"
            :key="index"
            :label="item.name"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="补助类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择补助类型："
          clearable
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in subsidy"
            :key="index"
            :label="item.name"
            :value="item.value"
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
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="驾驶员姓名" align="center" prop="createBy" />
      <!-- <el-table-column label="补助类型" align="center" prop="type" /> -->
      <el-table-column label="补助类型" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type === 1">异地装卸货</span>
          <span v-else-if="scope.row.type === 2">加班</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="申请时间" align="center" prop="createTime" />
      <!-- <el-table-column label="审核状态" align="center" prop="status" /> -->
      <el-table-column label="审核状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">待审核</span>
          <span v-else-if="scope.row.status === 2">已通过</span>
          <span v-else-if="scope.row.status === 3">已拒绝</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            v-show="scope.row.status == 1"
            size="small"
            type="primary"
            @click="handleClickapproval(scope.row)"
            v-hasPermi="['person:subsidy:getInfo']"
            style="color:#fff"
            >审批</el-button
          >
          <el-button
            v-show="scope.row.status == 2"
            size="small"
            type="primary"
            @click="handlesee(scope.row)"
            v-hasPermi="['person:subsidy:getInfo']"
            style="color:#fff;margin:0"
            >查看</el-button
          >
          <el-button
            v-show="scope.row.status == 3"
            size="small"
            type="primary"
            @click="handlesee(scope.row)"
            v-hasPermi="['person:subsidy:getInfo']"
            style="color:#fff;margin:0"
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
import { C01List } from "@/api/payment/subsidyManagementex";
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
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createBy: "",
        type: "",
        status: ""
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
      //审批下拉
      approval: [
        {
          value: "1",
          name: "待审核"
        },
        {
          value: "2",
          name: "已通过"
        },
        {
          value: "3",
          name: "已拒绝"
        }
      ],
      //补助类型下拉
      subsidy: [
        {
          value: 2,
          name: "加班补助"
        },
        {
          value: 1,
          name: "异地装卸货物补助"
        }
      ],
      // 表单校验
      rules: {},
      vehicleModelNamelist: [],
      oilslist: []
    };
  },
  created() {
    this.getList();
  },

  methods: {
    /** 查询补助管理-补助列表列表 */
    getList() {
      this.loading = true;
      C01List(this.queryParams).then(response => {
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
    // 审核
    handleClickapproval(row) {
      this.$router.push({ path: "/examineAndApprove/" + row.id });
    },
    // 查看
    handlesee(row) {
      this.$router.push({ path: "/subsidySee/" + row.id });
    }
    // /** 删除按钮操作 */
    // handleDelete(row) {
    //   const ids = row.id || this.ids;
    //   this.$confirm('是否确认删除定点名称"' + row.name + '"的数据项?', "警告", {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消",
    //       type: "warning"
    //     }).then(function() {
    //       return delectCardInfo(ids);
    //     }).then(() => {
    //       this.getList();
    //       this.msgSuccess("删除成功");
    //     })
    // },
  }
};
</script>
<style scoped></style>

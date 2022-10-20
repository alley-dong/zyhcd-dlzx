<template>
  <div class="app-container">
    <!-- 线路管理 -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="线路名称" prop="goodsTypeName">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入线路名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <buttonGroup
        @handleFilter="handleQuery"
        twoButtonState
        style="float: right"
      />

      <el-table v-loading="loading" border :data="repairList">
        <!-- <el-table-column type="selection" width="55" align="center" /> -->
        <el-table-column label="线路编码" align="center" prop="id" />
        <el-table-column label="线路名称" align="center" prop="name" />
        <el-table-column label="线路里程" align="center" prop="mileage" />
        <el-table-column label="创建时间" align="center" prop="createTime" />

        <!-- <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status == 1 ? "启用" : "禁用" }}
        </template>
      </el-table-column> -->

        <!-- <el-table-column label="主键" align="center" prop="id" /> -->
        <!-- <el-table-column label="申请时间" align="center" prop="completeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
        <!-- <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column> -->

        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="small"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['pfservercar:route:edit']"
              plain
              type="primary"
              >编辑</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="handleRemove(scope.row)"
              v-hasPermi="['pfservercar:route:delete']"
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
    </el-form>
  </div>
</template>

<script>
import buttonGroup from "@/views/components/com/buttonGroup";
import { tRouteP01RouteList,tRouteP01RouteDelete } from "@/api/taskManagement/index";
export default {
  components: {
    buttonGroup,
  },
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
      // 维修保养-维修保养单表格数据
      repairList: [],
      //   下拉
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
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    //   查询列表
    getList(bool) {
      this.loading = true;
      tRouteP01RouteList(this.queryParams).then((response) => {
        for (const item of response.rows) {
          item.total = item.unitPrice * item.amount;
        }
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    handleUpdate(row) {
      console.log("修改");
      this.$router.push('/editLineManagement/'+row.id)
    },
    handleRemove(row) {
      tRouteP01RouteDelete(row.id).then((res) => {
        this.getList();
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
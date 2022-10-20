<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="绩效名称" prop="goodsTypeName">
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
        @handleAdd="handleAdd"
        twoButtonState
        style="float: right"
      />
      
      <el-table v-loading="loading" :data="repairList">
        <el-table-column label="绩效名称" align="center" prop="name" />
        <el-table-column label="详细" align="center" prop="routeName" />
        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              size="small"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:repair:edit']"
              plain
              type="primary"
              >编辑</el-button
            >
            <el-button
              size="small"
              type="danger"
              @click="handleRemove(scope.row)"
              v-hasPermi="['system:repair:remove']"
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
import {G01Listt} from "@/api/performanceManagement/performanceManagement";
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
      // 绩效
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
        goodsTypeName: null,
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
      G01Listt(this.queryParams).then((response) => {
        // for (const item of response.rows) {
        //   item.total = item.unitPrice * item.amount;
        // }
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 新增
    handleAdd(){
       this.$router.push('/addLongDistancePerformance')
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
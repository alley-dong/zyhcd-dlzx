<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="群组名称：">
        <el-input v-model="listQuery.name" placeholder="请输入群组名称" />
      </el-form-item>
      <!-- <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="handleFilter"
        >查询</el-button
      >
      <div>
        <router-link :to="{ path: '/addGroupInfo' }">
          <el-button type="primary" style="float: right; margin: 0 10px 20px 0"
            >新增</el-button
          >
        </router-link>
      </div> -->

      <buttonGroup
        :addHasPermi="['person:dDriverGroup:add']"
        style="float: right"
        @handleFilter="handleFilter"
        @handleAdd="$router.push({ path: '/addGroupInfo' })"
      />
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column
        type="index"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column label="群组名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建日期" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | data }}</span>
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
            type="primary"
            style="margin-right: 15px"
            plain
            @click="editClick(scope.row)"
            v-hasPermi="['person:dDriverGroup:edit']"
            >编辑</el-button
          >
          <el-button
            type="danger"
            @click="deleteClick(scope.row.id)"
            size="small"
            v-hasPermi="['person:dDriverGroup:delete']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.pageNum"
      :limit.sync="listQuery.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { groupManageList, groupManageDelete } from "@/api/driver/groupManage";
import buttonGroup from "@/views/components/com/buttonGroup";

export default {
  name: "groupManage",
  watch: {
    $route: "getList",
  },
  components: {
    buttonGroup,
  },
  data() {
    return {
      total: 10,
      listLoading: false,
      tableData: [],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: "",
        driver: [],
      },
    };
  },
  created() {
    this.getList();
  },
  //格式化时间戳
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      const day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      const hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      const min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      const sec = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
      return (
        year + "-" + month + "-" + day + " " + hours + ":" + min + ":" + sec
      );
    },
  },
  methods: {
    //显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },
    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    // 获取table的表格数据
    getList() {
      this.listLoading = true;
      groupManageList(this.listQuery)
        .then((response) => {
          console.log(response);
          this.total = response.total;
          this.tableData = response.rows;
          this.listLoading = false;
        })
        .catch((err) => {
          this.listLoading = false;
          console.log(err);
        });
    },
    // 点击编辑按钮
    editClick(row) {
      this.$router.push({ path: "/editGroupInfo", query: { id: row.id } });
    },
    // 删除
    deleteClick(id) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          groupManageDelete(id)
            .then((response) => {
              console.log(response);
              that.getList();
              that.$message({
                message: "删除成功",
                type: "success",
              });
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch(function () {});
    },
  },
};
</script>

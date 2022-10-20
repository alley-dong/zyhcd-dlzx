<template>
  <div class="app-container">
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="标题：">
        <el-input v-model="listQuery.title" placeholder="请输入标题" />
      </el-form-item>
      <el-form-item label="状态：">
        <el-select v-model="listQuery.status" placeholder="请选择状态">
          <el-option
            v-for="item in status"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <buttonGroup
        :addHasPermi="['person:training:add']"
        @handleFilter="handleFilter"
        @handleAdd="addClick()"
        style="float: right"
      />

      <!-- <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="handleFilter"
        >查询</el-button
      > -->
      <div>
        <!-- <router-link
          :to="{ path: '/addTrainingInfo', name: 'addTrainingInfo' }"
        >
          <el-button
            type="primary"
            size="mini"
            style="float: right; margin: 0 10px 20px 0"
            >+新增</el-button
          >
        </router-link> -->
      </div>
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column label="标题" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createBy }}</span>
        </template>
      </el-table-column>
      <el-table-column label="培训截止时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.trainingEndTime | data }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布培训时间" align="center">
        <template slot-scope="scope">
            <span v-if="scope.row.updateTime">{{ scope.row.updateTime | data }}</span>
          <span v-else>{{ scope.row.updateTime}}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 2">已发布</span>
          <span v-else-if="scope.row.status === 1">未发布</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="400px"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="seeClick(scope.row.id)"
            v-if="scope.row.status === 1 || scope.row.status === 2"
            v-hasPermi="['person:training:getInfoCk']"
            >查看</el-button
          >
          <el-button
            size="small"
            type="warning"
            @click="trainingChangeId(scope.row.id)"
            v-if="scope.row.status === 2"
            v-hasPermi="['person:training:change']"
            >取消发布</el-button
          >
          <el-button
            size="small"
            type="success"
            @click="trainClick(scope.row)"
            v-if="scope.row.status === 1 || scope.row.status === 2"
            v-hasPermi="['person:training:listRead']"
            >培训统计</el-button
          >
          <el-button
            size="small"
            type="primary"
            plain
            @click="editClick(scope.row.id)"
            v-if="scope.row.status === 1"
            v-hasPermi="['person:training:edit']"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="warning"
            @click="trainingChangeId(scope.row.id)"
            v-if="scope.row.status === 1"
            v-hasPermi="['person:training:change']"
            >发布</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="trainingChangeDel(scope.row.id)"
            v-if="scope.row.status === 1"
            v-hasPermi="['person:training:delete']"
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
import {
  trainingManageList,
  trainingChange,
  trainingDelete,
} from "@/api/driver/trainingManage";

import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "trainingManage",
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
        title: "",
        status: null,
      },
      status: [
        {
          value: "2",
          label: "已发布",
        },
        {
          value: "1",
          label: "未发布",
        },
      ],
    };
  },
  created() {
    this.getList();
  },
  watch: {
    $route: "getList",
  },
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
    // 发布状态修改
    trainingChangeId(id) {
      trainingChange(id).then((res) => {
        this.getList();
      });
    },
    // 删除
    trainingChangeDel(id) {
         let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          trainingDelete(id).then((response) => {
            that.getList();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      // trainingDelete(id).then((res) => {
      //   this.getList();
      // });
    },
    // 培训统计
    trainClick(row) {
      this.$router.push({ path: `/trainingStatistics`,query:{id:row.id} });
    },
    addClick() {
      this.$router.push({ path: `/addTrainingInfo` });
    },
    seeClick(id) {
      console.log(id);
      this.$router.push(`/seetrainingManage/${id}`);
    },
    //显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },
    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    editClick(id) {
      this.$router.push(`/trainingInfoModify/${id}`);
    },
    // 获取table的表格数据
    getList() {
      console.log(".11111是否被调用");
      this.listLoading = true;
      trainingManageList(this.listQuery)
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
  },
};
</script>

<style scoped>
</style>
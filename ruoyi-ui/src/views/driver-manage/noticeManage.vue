<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="标题：">
        <el-input v-model="listQuery.title" placeholder="请输入标题" />
      </el-form-item>
      <buttonGroup
        @handleFilter="handleFilter"
        style="float: right"
        @handleAdd="$router.push({ path: '/addNotice' })"
        :addHasPermi="['person:notice:add']"
      />

      <!-- <router-link :to="{path:'/addNotice'}"> 
                <el-button type="primary" style="float: right; margin-right:10px;">新增</el-button>
            </router-link>
            <el-button type="primary" style="float: right;margin-right: 10px;" @click="handleFilter">查询</el-button> -->
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
      <el-table-column label="发布时间" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.updateTime">{{ scope.row.updateTime | data }}</span>
          <span v-else>{{ scope.row.updateTime}}</span>
        </template>
      </el-table-column>
       <el-table-column label="截至时间" align="center">
        <template slot-scope="scope">
          <span >{{ scope.row.deadline}}</span>
        </template>
      </el-table-column>
        <el-table-column label="发布状态" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.status === 1?'未发布':'已发布' }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="500px"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="warning"
            @click="trainingChangeId(scope.row.id)"
            v-if="scope.row.status === 1"
            >发布</el-button
          >
          <el-button
            size="small"
            type="primary"
            @click="checkClick(scope.row)"
            style="margin-right: 10px"
            v-hasPermi="['person:notice:getInfoCk']"
            >查看</el-button
          >
          <el-button
            size="small"
            plain
            type="primary"
            @click="editClick(scope.row)"
            style="margin-right: 10px"
            v-if="scope.row.status === 1"
            v-hasPermi="['person:notice:edit']"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="deleteClick(scope.row.id)"
            style="margin-right: 10px"
            v-hasPermi="['person:notice:delete']"
            >删除</el-button
          >
          <el-button
            size="small"
            type="success"
            @click="listClick(scope.row)"
            v-hasPermi="['person:notice:listRead']"
            >已读列表</el-button
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
  noticeManageList,
  noticeDelete,
  noticeA42Change,
} from "@/api/driver/noticeManage";
import buttonGroup from "@/views/components/com/buttonGroup";

export default {
  name: "noticeManage",
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
    trainingChangeId(id) {
      noticeA42Change(id).then((response) =>{
        this.getList()
      });
    },
    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    //列表显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },
    // 获取table的表格数据
    getList() {
      console.log(".11111是否被调用");
      this.listLoading = true;
      noticeManageList(this.listQuery)
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
          noticeDelete(id)
            .then((response) => {
              console.log(response);
              that.getList();
              that.$message({
                message: "删除成功",
                type: "success",
              });
            })
            .catch((err) => {
              that.listLoading = false;
              console.log(err);
            });
        })
        .catch(function () {});
    },
    // 点击查看按钮
    checkClick(row) {
      this.$router.push({ path: "/checkNotice", query: { id: row.id } });
    },
    // 点击已读列表按钮
    listClick(row) {
      this.$router.push({ path: "/readedList", query: { id: row.id } });
    },
    // 点击编辑按钮
    editClick(row) {
      this.$router.push({ path: "/editNotice", query: { id: row.id } });
    },
  },
};
</script>

<style scoped>
</style>

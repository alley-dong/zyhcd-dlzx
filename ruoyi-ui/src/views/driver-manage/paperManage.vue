<template>
  <div class="app-container">
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="margin-top: 30px"
    >
      <el-button
        type="primary"
        size="mini"
        style="float: right; margin: 20px 10px 20px 0"
        @click="addClick"
        v-hasPermi="['person:dExamPaperProblem:add']"
        >+新增试卷</el-button
      >
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto; margin-bottom: 30px"
    >
      <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column label="试卷名称" align="center">
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
            v-hasPermi="['person:dExamPaper:getInfo']"
            @click="seeClick(scope.row.id)"
            size="small"
            type="primary"
            >查看试卷</el-button
          >
          <el-button
            v-hasPermi="['pfserverperson:driver:list']"
            @click="setClick(scope.row.id)"
            size="small"
            type="primary"
            plain
            >配置试卷</el-button
          >
          <el-button
            @click="removeClick(scope.row.id)"
            size="small"
            type="danger"
            v-hasPermi="['person:dExamPaper:delete']"
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
import { paperManageList, dExamPaperDelete } from "@/api/driver/paperManage";

export default {
  name: "paperManage",
  data() {
    return {
      listLoading: false,
      tableData: [],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
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
      return year + "-" + month + "-" + day + " " + hours + ":" + min;
    },
  },
  methods: {
    // 查看试卷
    seeClick(id) {
      this.$router.push(`/paperManageWrite/${id}`);
    },
    // 配置试卷
    setClick(id) {
      this.$router.push(`/paperManageDetail/${id}`);
    },
    // 删除
    removeClick(id) {
         let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          dExamPaperDelete(id)
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
      console.log(id);
      // dExamPaperDelete(id).then((res) => this.getList());
    },
    // 新增
    addClick() {
      this.$router.push(`/addPaperManage`);
    },
    //显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },
    // 获取table的表格数据
    getList() {
      console.log(".11111是否被调用");
      this.listLoading = true;
      paperManageList(this.listQuery)
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
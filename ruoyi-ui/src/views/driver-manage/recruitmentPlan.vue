<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="线路：">
        <el-input v-model="listQuery.routeName" placeholder="线路" />
      </el-form-item>
      <el-form-item label="审核状态：">
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
      <!-- <el-button type="primary" style="float: right;margin-right: 10px;" @click="handleFilter">查询</el-button>
            <div>
                <router-link :to="{path:'/addRecruitmentInfo'}"> 
                    <el-button type="primary" style="float: right;margin:0 10px 20px 0;">新增</el-button>
                </router-link>
            </div> -->
            
      <buttonGroup
        @handleFilter="handleFilter"
        @handleAdd="handleAdd()"
        :addHasPermi="['pfserverperson:dRecruitPlan:add']"
        style="float: right"
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
        :index="typeIndex"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column label="线路" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.routeName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="招聘人数" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.number }}</span>
        </template>
      </el-table-column>
      <el-table-column label="驾照要求" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.drivingRequirements }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工资待遇" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.wage }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄要求" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.age }}</span>
        </template>
      </el-table-column>
      <el-table-column label="要求入职日期" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.entryTime | data }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请日期" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | data }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">待审核</span>
          <span v-else-if="scope.row.status === 2">拒绝</span>
          <span v-else-if="scope.row.status === 3">通过</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        class-name="small-padding fixed-width"
        width="200px"
        align="center"
      >
        <template slot-scope="scope">
          <el-button
            style="min-width: 60px"
            type="primary"
            v-if="scope.row.status === 1"
            @click="examineClick(scope.row)"
            plain
            size="small"
          v-hasPermi="['pfserverperson:dRecruitPlan:check']"
            >审核</el-button
          >
          <el-button
            style="min-width: 60px"
            type="primary"
            v-if="scope.row.status == 3||scope.row.status == 2"
            @click="checkClick(scope.row)"
            size="small"
            v-hasPermi="['pfserverperson:dRecruitPlan:delete']"
            >查看</el-button
          >
          <el-button
            style="min-width: 60px"
            type="primary"
            v-if="scope.row.status == 2"
            @click="editClick(scope.row)"
            plain
            size="small"
            v-hasPermi="['pfserverperson:dRecruitPlan:edit']"
            >编辑</el-button
          >
          <el-button
            style="min-width: 60px"
            type="danger"
            v-if="scope.row.status == 1 "
            @click="deleteClick(scope.row.id)"
            size="small"
          v-hasPermi="['pfserverperson:dRecruitPlan:delete']"
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
  recruitmentPlanList,
  recruitmentPlanDelete,
} from "@/api/driver/recruitmentPlan";

import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "recruitmentPlan",
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
        routeName: "",
        status: null,
      },
      status: [
        {
          value: 1,
          label: "待审核",
        },
        {
          value: 2,
          label: "拒绝",
        },
        {
          value: 3,
          label: "通过",
        },
        {
          value: null,
          label: "全部",
        },
      ],
    };
  },
  //格式化时间戳
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
  },

  created() {
    this.getList();
  },
  watch: {
    $route: "getList",
  },
  methods: {
    //列表显示序号
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
      recruitmentPlanList(this.listQuery)
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
    // 新增
    handleAdd() {
      this.$router.push({ path: "/addRecruitmentInfo" });
    },
    // 点击查看按钮
    checkClick(row) {
      this.$router.push({
        path: "/recruitmentPlanCheck",
        query: { id: row.id },
      });
    },
    // 点击编辑按钮
    editClick(row) {
      this.$router.push({
        path: "/recruitmentPlanEdit",
        query: { id: row.id },
      });
    },
    // 点击审核按钮
    examineClick(row) {
      this.$router.push({
        path: "/recruitmentPlanExamine",
        query: { id: row.id },
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
          recruitmentPlanDelete(id)
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
  },
};
</script>
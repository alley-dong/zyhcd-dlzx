<template>
  <div class="app-container">
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <div>
        <el-form-item label="姓名：">
          <el-input
            v-model="listQuery.name"
            style="width: 150px"
            placeholder="姓名"
          />
        </el-form-item>
        <el-form-item label="联系电话：">
          <el-input
            v-model="listQuery.contact"
            style="width: 200px"
            placeholder="联系电话"
          />
        </el-form-item>
        <el-form-item label="开始时间：">
          <el-date-picker
            @change="listQuery.startTime = dataChange(listQuery.startTime)"
            type="date"
            placeholder="选择日期"
            v-model="listQuery.startTime"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间：">
          <el-date-picker
            @change="listQuery.finishTime = dataChange(listQuery.finishTime)"
            type="date"
            placeholder="选择日期"
            v-model="listQuery.finishTime"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="状态：">
          <el-select
            v-model="listQuery.disableStatus"
            placeholder="状态"
            clearable
          >
            <el-option label="启用" :value="1"> </el-option>

            <el-option label="禁用" :value="2"> </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-button
          type="primary"
          style="float: right; margin-right: 10px"
          @click="interviewManageListData"
          >查询</el-button
        >
        <el-button
          type="primary"
          style="float: right; margin-right: 10px"
          @click="handleAdd"
          >新增</el-button
        > -->
      </div>
      <div>
        <el-form-item label="身份证号：">
          <el-input v-model="listQuery.idCard" placeholder="身份证号" />
        </el-form-item>
        <buttonGroup
          @handleFilter="interviewManageListData"
          style="float: right"
          :addHasPermi="['pfserverperson:driver:driverfileadd']"
          @handleAdd="handleAdd()"
        />
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
      <el-table-column label="姓名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.sex == 0 ? "男" : "女" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.contact }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.idCard }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入职时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.dntryTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.disableStatus === 1">启用</span>
          <span v-else-if="scope.row.disableStatus === 2">禁用</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="300px"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            v-hasPermi="['pfserverperson:driver:info']"
            type="primary"
            @click="seeClick(scope.row)"
            >查看</el-button
          >
          <el-button
            size="small"
            v-hasPermi="['pfserverperson:driver:delete']"
            type="danger"
            @click="tableDel(scope.row.id)"
            >删除</el-button
          >
          <el-button
            size="small"
            type="primary"
            plain
            @click="resetPassword(scope.row.id)"
            v-hasPermi="['pfserverperson:driver:resetPassword']"
            >重置密码</el-button
          >
          <el-button
            size="small"
            @click="disableClick(scope.row)"
            plain
            v-hasPermi="['pfserverperson:driver:disableStatusSave']"
            :type="scope.row.disableStatus === 2 ? 'success' : 'danger'"
            >{{ scope.row.disableStatus === 2 ? "启用" : "禁用" }}</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.pageNum"
      :limit.sync="listQuery.pageSize"
      @pagination="interviewManageListData"
    />
  </div>
</template>

<script>
import {
  driverA24List,
  A11DriverEducationDelete,
  interviewManageDelete,
  A24ResetPasswordSave,
  A24DisableStatusSave,
} from "@/api/driver/interviewManage";
import buttonGroup from "@/views/components/com/buttonGroup";

export default {
  name: "driverProfile",
  components: {
    buttonGroup,
  },
  mounted() {
    this.init();
  },
  watch: {
    // data(newValue, oldValue) {

    // }
    $route: "init",
  },
  data() {
    return {
      total: 0,
      listLoading: false,
      tableData: [{}],
      status: [
        {
          value: "1",
          label: "启用",
        },
        {
          value: "2",
          label: "禁用",
        },
      ],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        contact: undefined,
        dntryTime: undefined,
        disableStatus: undefined,
        startTime: undefined,
        finishTime: undefined,
        idCard: undefined,
      },
    };
  },
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
  },
  methods: {
    init() {
      this.interviewManageListData();
    },
    // 禁用
    disableClick(val) {
      console.log("禁用", val.disableStatus);
      let obj = {
        id: val.id,
        disableStatus: (val.disableStatus & 1) + 1,
      };
      A24DisableStatusSave(obj).then((res) => {
        this.interviewManageListData();
      });
    },
    // 重置密码
    resetPassword(id) {
      this.$confirm(
        "您确定要重置该用户密码？重置后默认密码为123456！",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        }
      )
        .then(() => {
          let data = {
            id,
            password: "123456",
          };
          A24ResetPasswordSave(data).then((response) => {
            this.msgSuccess("重置成功");
          });
        })
        .catch(() => {});
    },
    // 查看
    seeClick(row) {
      this.$router.push({
        path: "/driverProfileDetail/" + row.id,
        query: { name: row.name },
      });
    },
    // 日期转换
    dataChange(val) {
      const d = new Date(val);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
    // 列表的删除
    tableDel(val) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          interviewManageDelete(val).then((response) => {
            that.interviewManageListData();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      // interviewManageDelete(val).then((res) => this.interviewManageListData());
    },
    // 点新增
    handleAdd() {
      this.$router.push("/driverProfileAdd");
    },
    // 请求列表
    interviewManageListData() {
      driverA24List(this.listQuery).then((res) => {
        console.log(res.rows);
        this.tableData = res.rows;
        this.total = res.total;
      });
    },
    //显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },
  },
};
</script>

<style scoped>
</style>
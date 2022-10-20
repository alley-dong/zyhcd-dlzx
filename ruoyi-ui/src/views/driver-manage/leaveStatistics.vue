<template>
  <div class="leaveStatistics">
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width:98%; margin:0 auto; margin-top:30px;"
    >
      <el-form-item label="开始日期：">
        <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="listQuery.vStartTime"></el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期：">
        <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="listQuery.vEndTime"></el-date-picker>
      </el-form-item>
      <el-form-item label="姓名：">
        <el-input v-model="listQuery.driverName" placeholder="请输入姓名" />
      </el-form-item>
      <el-button type="primary" style="float: right; margin:0 20px 20px 0;" @click="handleFilter">查询</el-button>
    </el-form>
    <el-table :data="tableData" border style="width: 98%; margin:0 auto;">
      <el-table-column label="姓名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.driverName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="请假天数" align="center">
        <template scope="scope">
          <div
            style="text-decoration:underline;cursor:pointer;"
            @click="windowClick(scope.row)"
          >{{ scope.row.days }}</div>
        </template>
      </el-table-column>
    </el-table>
    <!-- 点击出来弹窗 -->
    <el-dialog
      :title="diaTitle"
      :visible.sync="dialogVisible"
      width="400px"
      :before-close="handleClose"
    >
      <div class="dialog" v-for="item in dVacationList" :key="item.id">
        <div class="text">{{item.vStartTime}} - {{item.vEndTime}}</div>
        <div class="text" style="margin-left:20px;">{{item.typeEnum}}</div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { leaveStatisticsList } from "@/api/driver/attendanceManage";

export default {
  name: "leaveStatistics",
  data() {
    return {
      listQuery: {
        vStartTime: this.parseTime(new Date(),'{y}-{m}')+'-01',
        vEndTime: this.parseTime(new Date(),'{y}-{m}-{d}'),
        driverName: ""
      },
      tableData: [],
      diaTitle: "请假明细",
      dialogVisible: false,
      dVacationList: {
        vStartTime: "",
        vEndTime: "",
        type: ""
      }
    };
  },
  created() {
    this.getList();
  },
  filters: {
    data(val) {
      switch (val) {
        case 1:
          return "事假";
          break;
        case 2:
          return "病假";
        default:
          break;
      }
    }
  },
  methods: {
    // 查询
    handleFilter() {
      this.getList();
    },
    handleClose() {
      this.dialogVisible = false;
    },
    // 获取table的表格数据
    getList() {
      console.log(".11111是否被调用");
      this.listLoading = true;
      leaveStatisticsList(this.listQuery)
        .then(response => {
          console.log(response);
          this.tableData = response.rows;
        })
        .catch(err => {
          console.log(err);
        });
    },
    windowClick(row) {
      this.diaTitle = "请假明细";
      this.dialogVisible = true;
      /* 
      vStartTime
      EndTime 
      */
      for (const item of row.dVacationList) {
        let { vStartTime, vEndTime } = item; //解构赋值
        item.vStartTime = vStartTime ? vStartTime.replaceAll("-", ".") : null;
        item.vEndTime = vEndTime ? vEndTime.replaceAll("-", ".") : null;
      }
      this.dVacationList = row.dVacationList;
    }
  }
};
</script>

<style lang="scss" scoped>
.text {
  height: 40px;
  line-height: 40px;
}
.dialog {
  display: flex;
  div {
    flex: 1;
  }
}
</style>
<style lang="scss">
.leaveStatistics {
  .el-dialog {
    min-height: 50%;
  }
}
</style>
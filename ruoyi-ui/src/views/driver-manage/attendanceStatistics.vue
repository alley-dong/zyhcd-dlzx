<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="考勤月份：">
        <el-date-picker
          type="month"
          value-format="yyyy-MM"
          placeholder="选择日期"
          v-model="listQuery.monthTime"
        ></el-date-picker>
      </el-form-item>
      <buttonGroup
        style="float: right"
        @handleFilter="handleFilter"
        twoButtonState
      />
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="cardlist"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column label="考勤月份" align="center" prop="month">
        <!-- <template slot-scope="scope">
                    <span>{{ scope.row.time | data }}</span>
                </template> -->
      </el-table-column>
      <el-table-column label="考勤司机" align="center" prop="totalMouth">
        <!-- <template slot-scope="scope">
                    <span>{{ scope.row.driver }}</span>
                </template> -->
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button size="small" type="primary" @click="handleCheck(scope.row)"
            >查看</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { A53List } from "@/api/driver/attendanceStatistics";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "attendanceStatistics",
  components: {
    buttonGroup,
  },
  data() {
    return {
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        monthTime: null,
      },
      // 考勤统计-列表显示当月有多少考勤人数
      cardlist: [],
      listLoading: false,
      tableData: [{}],
    };
  },
  created() {
    this.getA53List();
  },
  methods: {
    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getA53List();
    },
    getA53List() {
      A53List(this.listQuery).then((res) => {
        this.cardlist = res.rows;
      });
    },
    handleCheck(row) {
      console.log(row.month);
      this.$router.push({
        path: "/attendanceStatisticsCheck",
        query: { month: row.month },
      });
    },
  },
  //格式化时间戳
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      return year + "-" + month;
    },
  },
};
</script>

<style scoped>
</style>
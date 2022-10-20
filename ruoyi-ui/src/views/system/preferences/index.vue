<template>
  <div class="app-container">
    <!-- 参数设置 -->
    <!-- <el-form :inline="true" class="demo-form-inline" :model="listQuery">
      <el-form-item label="费用名称：">
        <el-input v-model="listQuery.roleName" placeholder="请输入费用名称" />
      </el-form-item>
      <el-button
        type="primary"
        style="float: right; margin: 0 20px 20px 0"
        @click="handleFilter"
        >查询</el-button
      >
    </el-form> -->
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column label="费用名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单价/公里（元）" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.price }}</span>
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
            type="primary"
            @click="handleUpdate(scope.row)"
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.pageNum"
      :limit.sync="listQuery.pageSize"
      @pagination="getList"
    /> -->
    <el-dialog title="编辑" :visible.sync="open" width="780px" append-to-body>
      <el-form ref="form" :model="form" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="单价/公里（元）">
              <el-input v-model="input" placeholder="请输入单价/公里（元）" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  statisticsConfigB08Info,
  statisticsConfigB08Edit,
} from "@/api/system/preferences";
export default {
  data() {
    return {
      total: 10,
      listLoading: true,
      tableData: [],
      open: false,
      key: undefined,
      input: "",
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        // type: "",
        type: null,
        // cTime: "",
      },
      // 表单参数
      form: {
        adminPay: undefined,
      },
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      statisticsConfigB08Info().then((res) => {
        let arr = [];
        let myarr = [
          {
            key: "fuelCost",
            name: "燃料费用",
          },
          {
            key: "driverPay",
            name: "驾驶员工资",
          },
          {
            key: "adminPay",
            name: "管理人员薪酬",
          },
          {
            key: "serviceCost",
            name: "外包服务费",
          },
          {
            key: "ureaCost",
            name: "尿素费用",
          },
          {
            key: "inspectionFee",
            name: "检车费",
          },
          {
            key: "insurance",
            name: "保险费",
          },
          {
            key: "maintenanceCost",
            name: "维保费用",
          },
          {
            key: "trieCost",
            name: "轮胎费用",
          },
          {
            key: "consumablesCost",
            name: "低值易耗品费用",
          },
          {
            key: "depreciationCost",
            name: "折旧费用",
          },
          {
            key: "systemCost",
            name: "信息系统服务费",
          },
        ];
        for (const key in res.data) {
          let obj = {};
          for (const item of myarr) {
            if (item.key == key) {
              obj.name = item.name;
              obj.price = res.data[key];
              obj.key = key;
              arr.push(obj);
            }
          }
        }
        console.log(arr);
        this.tableData = [...arr];
        this.listLoading = false;
      });
    },
    handleUpdate(row) {
      console.log(row);
      this.open = true;
      this.form = row;
      this.key = row.key;
      this.input = row.price;
    },
    submitForm() {
      this.form[this.key] = this.input;
      statisticsConfigB08Edit(this.form).then((res) => {
        this.input = "";
        this.getList();
        this.open = false;
      });
    },
  },
};
</script>

<style scoped>
</style>
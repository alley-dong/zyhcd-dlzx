<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm">返回</el-button>
        <el-button @click="resetForm" v-hasPermi="['person:vacationDay:export']"
          >导出</el-button
        >
      </div>
    </div>

    <el-form>
      <div class="wrap">
        <div class="text">考勤统计</div>
        <div class="line"></div>
      </div>
      <div style="text-align:center;margin:10px 0 20px 0"> {{$route.query.month}}</div>
      <el-table :data="cardList" border>
        <el-table-column label="序号" align="center" type="index" />
        <el-table-column label="司机名字" align="center" prop="driverName" />

        <el-table-column v-for="(o,i) in 31" :label="o" :key="o">
          <template slot-scope="scope">
            {{scope.row.dVacationDayList[i]}}
            <!-- <span v-if="scope.row.status === 1">在家</span>
            <span v-else-if="scope.row.status === 2">在途</span>
            <span v-else>请假</span> -->
          </template>
        </el-table-column>

        <!-- <el-table-column label="1" align="center" prop="status" />
        <el-table-column label="2" align="center" prop="status" />
        <el-table-column label="3" align="center" prop="status" />
        <el-table-column label="4" align="center" prop="status" />
        <el-table-column label="5" align="center" prop="status" />
        <el-table-column label="6" align="center" prop="status" />
        <el-table-column label="7" align="center" prop="status" />
        <el-table-column label="8" align="center" prop="status" />
        <el-table-column label="9" align="center" prop="status" />
        <el-table-column label="10" align="center" prop="status" />
        <el-table-column label="11" align="center" prop="status" />
        <el-table-column label="12" align="center" prop="status" />
        <el-table-column label="13" align="center" prop="status" />
        <el-table-column label="14" align="center" prop="status" /> -->
        <el-table-column label="在途" align="center" prop="way" />
        <el-table-column label="在家" align="center" prop="atHome" />
        <el-table-column label="请假" align="center" prop="leave" />
        <el-table-column
          label="实际出勤"
          align="center"
          prop="totalAttendance"
        />

        <!-- <el-table-column label="审批状态" align="center" >
          <template slot-scope="scope">
              <span v-for="scope.row.status === 1">待审核</span>
          </template>
      <el-table-column/> -->
        <!-- <el-table-column label="审核状态" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">待审核</span>
            <span v-else-if="scope.row.status === 2">已审核</span>
            <span v-else-if="scope.row.status === 3">已驳回</span>
            <span v-else>-</span>
          </template>
        </el-table-column> -->
        <!-- <el-table-column
          label="备注"
          align="center"
          class-name="small-padding fixed-width"
          prop="reviewRemarks"
        >
        </el-table-column> -->
      </el-table>
    </el-form>
  </div>
</template>

<script>
import {
  etcCardBox, //车牌号下拉
  cardEdit, //数据回显
  cardSave //数据保存
} from "@/api/etc/card";

import { A54List } from "@/api/driver/attendanceStatistics";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        id: "",
        radio: null,
        status: null,
        reviewRemarks: ""
      },
      // 数据列表
      cardList: [],
      // 备注
      input: "",
      rules: {},
      // 补助详情数据
      GetInfo: []
    };
  },
  computed: {},
  watch: {},
  created() {
    this.getA54List();
  },
  mounted() {},
  methods: {
    // 获取司机
    getA54List() {
      var monthform = {
        vacationDate: this.$route.query.month
        // vacationDate:"2020-12-12"
      };
      A54List(monthform).then(res => {
        // console.log(res.rows);
//         res ={
//     "total": 7,
//     "rows": [
//         {
//             "driverName": "陶立峰",
//             "way": 9,
//             "atHome": 0,
//             "leave": 0,
//             "totalAttendance": 9,
//             "dVacationDayList": [
//                 {
//                     "day": "12",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "14",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "20",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "24",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "01",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "19",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "09",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "10",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "21",
//                     "detail": "在途"
//                 }
//             ]
//         },
//         {
//             "driverName": "闫志富",
//             "way": 0,
//             "atHome": 9,
//             "leave": 0,
//             "totalAttendance": 9,
//             "dVacationDayList": [
//                 {
//                     "day": "06",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "02",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "16",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "01",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "12",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "24",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "22",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "05",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "06",
//                     "detail": "在家"
//                 }
//             ]
//         },
//         {
//             "driverName": "王国东",
//             "way": 0,
//             "atHome": 9,
//             "leave": 0,
//             "totalAttendance": 9,
//             "dVacationDayList": [
//                 {
//                     "day": "21",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "01",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "23",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "22",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "20",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "09",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "11",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "10",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "14",
//                     "detail": "在家"
//                 }
//             ]
//         },
//         {
//             "driverName": "张大勇",
//             "way": 0,
//             "atHome": 9,
//             "leave": 0,
//             "totalAttendance": 9,
//             "dVacationDayList": [
//                 {
//                     "day": "19",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "17",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "14",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "12",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "21",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "01",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "24",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "20",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "13",
//                     "detail": "在家"
//                 }
//             ]
//         },
//         {
//             "driverName": "郝运新",
//             "way": 0,
//             "atHome": 9,
//             "leave": 0,
//             "totalAttendance": 9,
//             "dVacationDayList": [
//                 {
//                     "day": "05",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "04",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "03",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "21",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "09",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "02",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "01",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "24",
//                     "detail": "在家"
//                 },
//                 {
//                     "day": "23",
//                     "detail": "在家"
//                 }
//             ]
//         },
//         {
//             "driverName": "周凤武",
//             "way": 9,
//             "atHome": 0,
//             "leave": 0,
//             "totalAttendance": 9,
//             "dVacationDayList": [
//                 {
//                     "day": "01",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "07",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "06",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "05",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "23",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "04",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "21",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "24",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "22",
//                     "detail": "在途"
//                 }
//             ]
//         },
//         {
//             "driverName": "测试驾驶员1",
//             "way": 9,
//             "atHome": 0,
//             "leave": 0,
//             "totalAttendance": 9,
//             "dVacationDayList": [
//                 {
//                     "day": "24",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "22",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "11",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "03",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "09",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "23",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "21",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "01",
//                     "detail": "在途"
//                 },
//                 {
//                     "day": "20",
//                     "detail": "在途"
//                 }
//             ]
//         }
//     ],
//     "code": 200,
//     "msg": "查询成功"
// }
        for (let item of res.rows) {
    let arr = new Array(31)
    for (let i = 0; i < item.dVacationDayList.length; i++) {
      // [{"day":"24","detail":"在途"},{"day":"25","detail":"在家"}]
        arr[(item.dVacationDayList[i].day)-1] = item.dVacationDayList[i].detail
    }
    item.dVacationDayList = arr
    console.log(arr);
}
        this.cardList = res.rows;
      });
    },

    handleChange(val) {
      console.log(val, JSON.stringify(this.field101Options));
      for (const item of this.field101Options) {
        if (val == item.id) {
          console.log(item);

          console.log(item.licensePlate);
          this.formData.licensePlate = item.licensePlate;
        }
      }
    },
    //提交
    submitForm() {
      this.$router.back()
      // if(this.formData.status === null){
      //   this.$message({
      //   message: '请选择审核是否通过',
      //   type: 'warning'
      // })
      // }else{
      //    C02check(this.formData).then((res) => {
      //   this.resetForm();
      // });
      // }
    },
    resetForm() {
      //   this.$refs["elForm"].resetFields();
      // this.download(
      //   "/pfserver-person/person/vacationDay/A54Export",
      //   {
      //     ...this.queryParams
      //   },
      //   `system_vacationDay.xlsx`
      // );
      // TODO:等后端出接口
       window.location.href =
        process.env.VUE_APP_BASE_API +
        `/pfserver-person/person/vacationDay/vacationDayExport?vacationDate=${this.$route.query.month}`;
    
    }
  }
};
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
  width: 10%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
  font-weight: 400;
}
.line {
  width: 90%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
</style>

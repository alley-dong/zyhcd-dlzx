<template>
  <div>
    <div
      style="
        width: 98%;
        margin: 0 auto;
        margin: 20px 10px 0 0;
        overflow: hidden;
      "
    >
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="submitClick()"
        >提交</el-button
      >
      <!-- <router-link :to="{ path: '/recruitmentPlan', name: 'recruitmentPlan' }"> -->
        <el-button @click="backClick"  type="primary" style="float: right; margin-right: 10px"
          >返回</el-button
        >
      <!-- </router-link> -->
    </div>
    <div class="wrap">
      <div class="text">招聘信息</div>
      <div class="line"></div>
    </div>
    <!-- 详细信息 -->
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
      style="width: 90%; margin: 0 auto; margin-top: 20px"
    >
      <div>
        <el-form-item
          label="选择路线："
          prop="routeName"
          style="width: 50%; float: left"
        >
          <el-input v-model="form.routeName"></el-input>
        </el-form-item>
        <el-form-item
          label="招聘人数："
          prop="number"
          style="width: 50%; float: left"
        >
          <el-input v-model="form.number"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item
          label="学历要求："
          prop="education"
          style="width: 50%; float: left"
        >
          <el-input v-model="form.education"></el-input>
        </el-form-item>
        <el-form-item
          label="年龄要求："
          prop="age"
          style="width: 50%; float: left"
        >
          <el-input v-model="form.age"></el-input>
        </el-form-item>
      </div>
      <div>
        <el-form-item
          label="驾照要求："
          prop="drivingRequirements"
          style="width: 50%; float: left"
        >
          <el-input
            v-model="form.drivingRequirements"
            style="z-index: 999"
          ></el-input>
        </el-form-item>
      </div>
      <div style="overflowx: hidden">
        <el-form-item
          label="入职时间："
          prop="entryTime"
          style="width: 50%; float: left"
        >
          <el-date-picker
            style="width: 100%"
            type="date"
            v-model="form.entryTime"
          ></el-date-picker>
        </el-form-item>
        <el-form-item
          label="工资待遇："
          prop="wage"
          style="width: 50%; float: left"
        >
          <el-input v-model="form.wage"></el-input>
        </el-form-item>
      </div>
      <div style="overflow: hidden">
        <el-form-item
          label="需求原因："
          prop="reason"
          style="width: 100%; float: left"
        >
          <el-input type="textarea" v-model="form.reason"></el-input>
        </el-form-item>
      </div>
      <div style="overflow: hidden">
        <el-form-item
          label="工作职责："
          prop="duty"
          style="width: 100%; float: left"
        >
          <el-input type="textarea" v-model="form.duty"></el-input>
        </el-form-item>
      </div>
    </el-form>
    <div class="wrap">
      <div class="text">审批记录</div>
      <div class="line"></div>
    </div>
    <!-- table列表 -->
    <el-table border :data="tableData" style="width: 98%; margin: 0 auto">
      <el-table-column label="审批级别" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.level+'级审批' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reviewTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批角色" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批人姓名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">待审核</span>
          <span v-else-if="scope.row.status === 3">拒绝</span>
          <span v-else-if="scope.row.status === 2">通过</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reviewRemarks }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {
  RecruitmentPlanEdit,
  recruitmentPlanEdit,
} from "@/api/driver/recruitmentPlan";
import { queryExamineConfigProcessList } from "@/api/com";

export default {
  name: "approval",
  data() {
    return {
      tableData: [{}],
      form: {
        routeName: null,
        number: "",
        education: "",
        age: "",
        drivingRequirements: "",
        entryTime: null,
        wage: "",
        textarea: "",
        duty: "",
      },
    };
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
      return year + "." + month + "." + day + " " + hours + ":" + min;
    },
  },
  created() {
    this.getParams();
    this.queryExamineConfigProcessDataList()
  },
  methods: {
    // 获取数据
    getParams() {
      //取到路由带过来的参数
      this.id = this.$route.query.id;
      var getId = this.id;
      RecruitmentPlanEdit(getId)
        .then((response) => {
          console.log(response);
          this.form = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 审批
    queryExamineConfigProcessDataList() {
      let obj = {
        // type: 6,
        relationId:this.$route.query.id
      };
      queryExamineConfigProcessList(obj).then((res) => {
        // debugger
        this.tableData = res.data.sysExamineUserTaskList
      });
    },
    backClick(){
      this.$router.go(-1)
    },
    // 点击提交按钮
    submitClick() {
      recruitmentPlanEdit(this.form)
        .then((response) => {
          console.log(".response", response);
          this.$message({
            message: "提交成功",
            type: "success",
          });
          setTimeout(() => {
            // this.$router.push({
            //   path: "/recruitmentPlan",
            //   name: "recruitmentPlan",
            // });
            this.backClick()
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  watch: {
    $route: "getParams",
  },
};
</script>

<style scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
  display: flex;
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
</style>
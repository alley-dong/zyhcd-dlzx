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
      <el-button type="primary" style="float: right; margin-right: 10px"
      @click="handleClick"
        >提交</el-button
      >
      <!-- <router-link :to="{ path: '/recruitmentPlan', name: 'recruitmentPlan' }"> -->
        <el-button @click="$router.go(-1)" type="primary" style="float: right; margin-right: 10px"
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
      style="width: 80%; margin: 0 auto; margin-top: 20px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="线路：" prop="routeName">
            <div>{{ form.routeName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="招聘人数：" prop="number">
            <div>{{ form.number }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="学历要求：" prop="education">
            <div>{{ form.education }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="年龄要求：" prop="age">
            <div>{{ form.age }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="驾照要求：" prop="drivingRequirements">
            <div>{{ form.drivingRequirements }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="入职时间：" prop="entryTime">
            <div>{{ form.entryTime }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工资待遇：" prop="wage">
            <div>{{ form.wage }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="需求原因：" prop="reason">
            <div>{{ form.reason }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="工作职责：" prop="duty">
            <div>{{ form.duty }}</div>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <div class="wrap">
      <div class="text">审批记录</div>
      <div class="line"></div>
    </div>
    <!-- table列表 -->
    <el-table
      border
      :data="tableData"
      style="width: 90%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <el-table-column label="审批级别" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.level + "级审批" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reviewTime | data }}</span>
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

    <div class="wrap">
      <div class="text">审核结果</div>
      <div class="line"></div>
    </div>
    <div style="width: 85%; margin: 0 auto">
      <div style="margin-top: 20px">
        <el-radio v-model="formData.status" :label="1">通过</el-radio>
        <el-radio v-model="formData.status" :label="2">拒绝</el-radio>
      </div>
      <el-input
        type="textarea"
        v-model="formData.reviewRemarks"
        placeholder="请输入原因"
        style="margin: 20px 0 40px 0"
      ></el-input>
    </div>
  </div>
</template>

<script>
import { getRecruitmentPlanDetails,A07check } from "@/api/driver/recruitmentPlan";
import { getSysExamineTaskList } from "@/api/com";
export default {
  name: "approval",
  data() {
    return {
      tableData:[],
      form: {
        routeName: "",
        number: "",
        education: "",
        age: "",
        drivingRequirements: "",
        entryTime: "",
        wage: "",
        textarea: "",
        duty: "",
      },
      formData: {
        relationId: undefined,
        status: undefined,
        reviewRemarks: undefined,
      },
      radio: "1",
    };
  },
  created() {
    this.getParams();
    let obj = {
      relationId: this.$route.query.id,
    };
    getSysExamineTaskList(obj).then((res) => {
      // debugger
      console.log('.res', res)
      this.tableData = res.data.sysExamineUserTaskList;
    });
  },
  methods: {
    // 获取数据
    getParams() {
      //取到路由带过来的参数
      this.id = this.formData.relationId = this.$route.query.id;
      var getId = this.id;
      getRecruitmentPlanDetails(getId)
        .then((response) => {
          console.log(response);
          this.form = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleClick(){
        A07check(this.formData).then().then(this.$router.go(-1))
    }
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

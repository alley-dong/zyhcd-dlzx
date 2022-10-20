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
        @click="handleClick"
        >提交</el-button
      >
      <!-- <router-link
        :to="{ path: '/attendanceManage', name: 'attendanceManage' }"
      > -->
        <el-button @click="$router.go(-1)" type="primary" style="float: right; margin-right: 10px"
          >返回</el-button
        >
      <!-- </router-link> -->
    </div>
    <div class="wrap">
      <div class="text">请假信息</div>
      <div class="line"></div>
    </div>
    <!-- 详细信息 -->
    <el-form
      ref="form"
      :model="form"
      label-width="100px"
      style="width: 90%; margin: 0 auto; margin-top: 20px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="申请人：" prop="driverName">
            <div>{{ form.driverName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="请假类型：" prop="type">
            <div>{{ form.typeEnum }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="开始时间：" prop="vStartTime">
            <div>{{ form.vStartTime }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束时间：" prop="vEndTime">
            <div>{{ form.vEndTime }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="请假时长：" prop="duration">
            <div>{{ form.duration }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="请假原因：" prop="remarks">
            <div>{{ form.remarks }}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="图片：">
            <div v-if="form.fileList&&form.fileList.length!==0">

            <img v-for="(item,index) in form.fileList" :key="index" style="width: 150px;"  :src="item" />
            </div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="申请时间：" prop="createTime">
            <div>{{ form.createTime }}</div>
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
        <el-radio v-model="fromData.status" label="1">通过</el-radio>
        <el-radio v-model="fromData.status" label="2">不通过</el-radio>
      </div>
      <el-input
        v-model="fromData.reviewRemarks"
        type="textarea"
        placeholder="请输入原因"
        style="margin: 20px 0 40px 0"
      ></el-input>
    </div>
  </div>
</template>

<script>
import {
  getAttendanceManageDetails,
  vacationA51ClockInCheck,
} from "@/api/driver/attendanceManage";
import { getSysExamineTaskList } from "@/api/com";
export default {
  name: "approval",
  data() {
    return {
      form: {
        tableData: [],
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
      fromData: {
        status: null,
        id: null,
        reviewRemarks: undefined,
      },
      type: [
        {
          value: 1,
          label: "事假",
        },
        {
          value: 2,
          label: "病假",
        },
      ],
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
  filters: {
    state: function (value) {
      switch (value) {
        case 1:
          return "事假";
          break;
        case 2:
          return "病假";
          break;
        default:
          break;
      }
    },
  },
  methods: {
    // 获取数据
    getParams() {
      //取到路由带过来的参数
      this.id = this.fromData.id = this.$route.query.id;
      var getId = this.id;
      getAttendanceManageDetails(getId)
        .then((response) => {
          console.log(response);
          this.form = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleClick() {
      vacationA51ClockInCheck(this.fromData).then((res) => {
        if (res.code == "200") {
          this.$message({
            message: res.msg,
            type: "success",
          });
        } else {
          this.$message.error(res.msg);
        }
        this.$router.go(-1);
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

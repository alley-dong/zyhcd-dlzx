<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button
          type="primary"
          @click="submitForm"
          v-hasPermi="['person:subsidy:check']"
          >提交</el-button
        >
        <el-button @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">申请信息</div>
      <div class="line"></div>
    </div>
    <div style="display: flex; margin-top: 30px">
      <el-row :gutter="15" style="width: 70%">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="121px"
        >
          <el-form-item label="申请人" prop="createBy">
            {{ GetInfo.createBy }}
          </el-form-item>
          <el-form-item label="补助类型" prop="type">
            {{ (GetInfo.type === 1 ? "异地装卸货" : "加班") }}
          </el-form-item>
          <el-form-item label="等待日期" prop="daysStr">
            {{ GetInfo.daysStr }}
          </el-form-item>
          <el-form-item label="申请原因" prop="remarks">
            {{ GetInfo.remarks }}
          </el-form-item>
          <el-form-item
            label="申请时间"
            prop="status"
            v-model="formData.status"
          >
            {{ GetInfo.createTime }}
          </el-form-item>
        </el-form>
      </el-row>
    </div>

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

    <el-form ref="elForm">
      <!-- 审核结果 -->
      <div class="wrap">
        <div class="text">审核结果</div>
        <div class="line"></div>
      </div>
      <div style="width:85%; margin:0 auto;">
        <div style="margin-top:20px;">
          <el-radio v-model="formData.status" label="1">通过</el-radio>
          <el-radio v-model="formData.status" label="2">不通过</el-radio>
        </div>
        <el-input
          type="textarea"
          v-model="formData.reviewRemarks"
          placeholder="请输入原因"
          style="margin:20px 0 40px 0;"
        ></el-input>
      </div>
    </el-form>

  </div>
</template>

<script>
// import {
//   etcCardBox, //车牌号下拉
//   cardEdit, //数据回显
//   cardSave //数据保存
// } from "@/api/etc/card";
import { C01GetInfo, C02check } from "@/api/payment/examineAndApprove";
import { getSysExamineTaskList } from "@/api/com";
import { C01List } from "@/api/payment/subsidyManagementex";
export default {
  components: {},
  props: [],
  data() {
    return {
      tableData: [],
      formData: {
        id: "",
        status: null,
        reviewRemarks: ""
      },
      rules: {},

      // 补助详情数据
      GetInfo: []
    };
  },
  computed: {},
  watch: {},
  created() {
    this.etcCardInfo();
    let obj = {
      relationId: this.$route.params.id,
    };
    getSysExamineTaskList(obj).then((res) => {
      // debugger
      console.log('.res', res)
      this.tableData = res.data.sysExamineUserTaskList;
    });
  },
  mounted() {},
  methods: {
    //数据回显
    etcCardInfo() {
      C01GetInfo(this.$route.params.id).then(res => {
        this.GetInfo = res.data;
        this.formData.id = this.$route.params.id;
      });
    },
    // handleChange(val) {
    //   console.log(val, JSON.stringify(this.field101Options));
    //   for (const item of this.field101Options) {
    //     if (val == item.id) {
    //       console.log(item);

    //       console.log(item.licensePlate);
    //       this.formData.licensePlate = item.licensePlate;
    //     }
    //   }
    // },
    //提交
    submitForm() {
      if (this.formData.status === null) {
        this.$message({
          message: "请选择审核是否通过",
          type: "warning"
        });
      } else {
        C02check(this.formData).then(res => {
          this.$message({
            message: res.msg,
            type: "warning"
          });
          this.resetForm();
        });
      }
    },
    resetForm() {
      //   this.$refs["elForm"].resetFields();
      this.$router.go(-1);
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

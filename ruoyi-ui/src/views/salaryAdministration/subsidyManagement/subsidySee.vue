<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <!-- <el-button type="primary" @click="submitForm">保存</el-button> -->
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
          <el-form-item label="补助类型" prop="etcCardNumber">
            {{ (GetInfo.type === 1 ? "异地装卸货" : "加班") }}
          </el-form-item>
          <el-form-item label="等待日期" prop="orderNumber">
            {{ GetInfo.daysStr }}
          </el-form-item>
          <el-form-item label="申请原因" prop="orderTime">
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

    <el-form ref="elForm">
      <div class="wrap">
        <div class="text">审批记录</div>
        <div class="line"></div>
      </div>
      <el-table :data="cardList" border>
        <el-table-column label="审批级别" align="center" prop="typeEnum" />
        <el-table-column label="审批时间" align="center" prop="reviewTime" />
        <el-table-column label="审批角色" align="center" prop="roleName" />
        <el-table-column label="审批人姓名" align="center" prop="userName" />
        <!-- <el-table-column label="审批状态" align="center" >
          <template slot-scope="scope">
              <span v-for="scope.row.status === 1">待审核</span>
          </template>
      <el-table-column/> -->
        <el-table-column label="审核状态" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">待审核</span>
            <span v-else-if="scope.row.status === 2">已审核</span>
            <span v-else-if="scope.row.status === 3">已驳回</span>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column
          label="备注"
          align="center"
          class-name="small-padding fixed-width"
          prop="reviewRemarks"
        >
        </el-table-column>
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
import { C01GetInfo, C02check } from "@/api/payment/examineAndApprove";
import { C01List } from "@/api/payment/subsidyManagementex";
import { sysExamineTaskList } from "@/api/payment/subsidySee";
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
    this.etcCardBoxData();
    this.etcCardInfo();
    this.getsysExamineTaskList();
  },
  mounted() {},
  methods: {
    // 获取审批记录
    getsysExamineTaskList() {
      var list = {
        relationId: this.$route.params.id
      };
      sysExamineTaskList(list).then(res => {
        this.cardList = res.data.sysExamineUserTaskList;
      });
    },

    //数据回显
    etcCardInfo() {
      C01GetInfo(this.$route.params.id).then(res => {
        this.GetInfo = res.data;
        id: this.$route.params.id;
        console.log("GetInfo.type",this.GetInfo.type)
      });
    },
    //补助管理-补助列表获取id
    etcCardBoxData() {
      C01List({}).then(res => {
        this.formData.id = res.rows[0].id;
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
      if (this.formData.status === null) {
        this.$message({
          message: "请选择审核是否通过",
          type: "warning"
        });
      } else {
        C02check(this.formData).then(res => {
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

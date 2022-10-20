<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container leave">
      <van-cell-group>
        <van-cell
          title="线路："
          :value="form.routeName"
          title-class="left-title"
        />
        <van-cell
          title="招聘人数："
          :value="form.number"
          title-class="left-title"
        />
        <van-cell
          title="学历要求："
          :value="form.education"
          title-class="left-title"
        />
        <van-cell
          title="年龄要求："
          :value="form.age"
          title-class="left-title"
        />
        <van-cell
          title="驾照要求："
          :value="form.drivingRequirements"
          title-class="left-title"
        />
        <van-cell
          title="入职时间："
          :value="form.entryTime"
          title-class="left-title"
        />
        <van-cell
          title="工资待遇："
          :value="form.wage"
          title-class="left-title"
        />
        <van-cell
          title="需求原因："
          :value="form.reason"
          title-class="left-title"
        />
        <van-cell
          title="工作职责："
          :value="form.duty"
          title-class="left-title"
        />
      </van-cell-group>
    </div>
    <div
      v-for="item in tableData2"
      :key="item.id"
      style="border-bottom: 0.05rem solid"
    >
      <!-- <van-cell
          title="审批级别："
          :value="item.level"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批时间："
          :value="item.reviewTime"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批角色："
          :value="item.roleName"
          title-class="title-class"
          value-class="value-class"
        /> -->
      <van-cell
        title="审批人姓名："
        :value="item.userName"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="审批状态："
        :value="item.statusEnum"
        title-class="title-class"
        value-class="value-class"
      />
      <!-- <van-cell
          title="备注："
          :value="item.reviewRemarks"
          title-class="title-class"
          value-class="value-class"
        /> -->
    </div>
    <van-field v-model="text" label="备注" placeholder="请输入备注" />
    <van-row style="background: #fff; padding: 1rem 0">
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(1)"
          >同意</van-button
        ></van-col
      >
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(2)"
          >拒绝</van-button
        ></van-col
      >
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { getRecruitmentPlanDetails } from "@/api/approvalRecord";
import { sysExamineTaskList } from "@/api/com";
import { A07check } from "@/api/myAgent";
import { Toast } from "vant";
export default {
  data() {
    return {
      form: {},
      tableData2: [],
      text: undefined,
    };
  },
  filters: {
    status: function (value) {
      switch (value) {
        case 1:
          return "待审批";
          break;
        case 2:
          return "已拒绝";
          break;
        case 3:
          return "已通过";
          break;
        case 4:
          return "已确认";
          break;
        case 5:
          return "已撤销";
          break;

        default:
          break;
      }
    },
  },
  methods: {
    handleAddClick(status) {
      A07check({
        relationId: this.$route.params.id,
        reviewRemarks: this.text,
        status,
      }).then((res) => {
        if(status == 1){
          Toast("审批通过");
        } else {
          Toast("审批拒绝");
        }
        this.$router.go(-1);
      });
    },
  },
  mounted() {
    getRecruitmentPlanDetails(this.$route.params.id).then((res) => {
      console.log(res);
      this.form = res.data;
    });
    sysExamineTaskList({ relationId: this.$route.params.id }).then((res) => {
      console.log(res.data.sysExamineUserTaskList);
      this.tableData2 = res.data.sysExamineUserTaskList;
    });
  },
  components: {
    Header,
  },
};
</script>

<style lang="scss" scoped>
.title-class {
  text-align: left;
}
.left-title {
  text-align: left;
}
</style>
<style lang="scss" >
.leave {
  .van-cell {
    border-bottom: 0.02rem solid #dcdcdc;
  }
}
</style>
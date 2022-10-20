<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <div style="margin: 0.5rem 0; font-weight: 600">{{mounth}}</div>

      <van-cell
        title="姓名："
        :value="form.driverName"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="底薪："
        :value="form.basicSalary"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="工龄工资："
        :value="form.wageForSeniority"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="绩效工资："
        :value="form.meritSalary"
        title-class="title-class"
        value-class="value-class"
      /><van-cell
        title="加班补助："
        :value="form.overtimePay"
        title-class="title-class"
        value-class="value-class"
      /><van-cell
      v-if="form.type==1"
        title="异地等待装卸货补助："
        :value="form.waitPay"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="奖励："
        :value="form.rewardFee"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="罚款："
        :value="form.penaltyFee"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
      v-if="form.type==1"
        title="流量补助："
        :value="form.flowPay"
        title-class="title-class"
        value-class="value-class"
      />
          <van-cell
        title="其他补助："
        :value="form.otherPay"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="合计："
        :value="form.salary"
        title-class="title-class"
        value-class="value-class"
      />
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { salaryG02Info } from "@/api/salary/index";
import {  dateReplace } from "@/components/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      mounth:undefined,
      form: {},
    };
  },
  mounted() {
    this.mounth= this.$route.query.mounth
  //   =  
    salaryG02Info(this.$route.params.id).then((res) => {
      console.log(res);
      this.form = res.data;
    });
  },
};
</script>

<style lang="scss" scoped>
.title-class {
  text-align: left;
  margin-right: 0.2rem;
}
.value-class {
  text-align: left;
}
</style>
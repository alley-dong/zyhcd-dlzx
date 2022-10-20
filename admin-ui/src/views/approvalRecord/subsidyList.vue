<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-form>
        <van-field
          readonly
          label="补助类型:"
          :value="form.type == 1 ? '异地等待装卸货补助' : '加班补助'"
        />
      </van-form>
      <van-form>
        <!-- TODO:等待日期为多个 -->
        <van-row>
          <van-col span="6">
            <van-field readonly label="等待日期:" />
          </van-col>
          <div v-if="form.daysStr">
            <van-col
              span="18"
              v-for="(item, index) in form.daysStr.split(',')"
              :key="index"
            >
              <van-field readonly :value="item" />
            </van-col>
          </div>
        </van-row>
      </van-form>
      <van-form>
        <van-field readonly label="申请原因:" :value="form.remarks" />
      </van-form>
      <van-form>
        <van-field readonly label="申请日期:" :value="form.createTime" />
      </van-form>
      <van-form>
        <van-field readonly label="状态:" :value="form.status | status" />
      </van-form>
      <div
        v-for="item in tableData2"
        :key="item.id"
        style="border-bottom: 0.05rem solid"
      >
        <van-cell
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
        />
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
        <van-cell
          title="备注："
          :value="item.reviewRemarks"
          title-class="title-class"
          value-class="value-class"
        />
      </div>
       <div
      v-for="item in tableData"
      :key="item.id"
      style="border-bottom: 0.05rem solid"
    >
      <van-cell
        title="抄送人姓名："
        :value="item.userName"
        title-class="title-class"
        value-class="value-class"
      />
       </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { driverSubsidyF07GetInfo } from "@/api/approvalRecord";
import { sysExamineTaskList } from "@/api/com";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form: {},
      tableData2: [],
      tableData: [],
    };
  },
  filters: {
    status: function (value) {
      switch (value) {
        case 1:
          return "待审核";
          break;
        case 2:
          return "已通过";
          break;
        case 3:
          return "已拒绝";
          break;
        default:
          break;
      }
    },
  },
  methods: {
    handleClick() {
      driverSubsidyF04Change(this.$route.params.id).then((res) => {
        this.$router.go(-1);
      });
    },
  },
  mounted() {
    driverSubsidyF07GetInfo({ id: this.$route.params.id }).then((res) => {
      this.form = res.data;
    });
    sysExamineTaskList({ relationId: this.$route.params.id }).then((res) => {
      console.log(res.data.sysExamineUserTaskList);
      this.tableData2 = res.data.sysExamineUserTaskList;
      this.tableData = res.data.sysDuplicateUserTaskList;
    });
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
.title-class{
  text-align: left;
}
.value-class{
  text-align: left;
}
</style>

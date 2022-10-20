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
      <van-row>
        <div v-for="item in tableData2" :key="item.id">
          <van-col span="24">
            <van-cell
              title="审批人姓名："
              :value="item.userName"
              title-class="title-class"
              value-class="value-class"
          /></van-col>
          <van-col span="24">
            <van-cell
              title="审批状态："
              :value="item.statusEnum"
              title-class="title-class"
              value-class="value-class"
          /></van-col>
        </div>
      </van-row>
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
import { driverSubsidyF07GetInfo } from "@/api/approvalRecord";
import { subsidyC02check } from "@/api/myAgent/index";
import { sysExamineTaskList } from "@/api/com";
import { Toast } from "vant";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form: {},
      text: undefined,
      tableData2: [],
    };
  },
  methods: {
    handleAddClick(status) {
      subsidyC02check({
        id: this.$route.params.id,
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
    handleClick() {
      driverSubsidyF04Change(this.$route.params.id).then((res) => {
        this.$router.go(-1);
      });
    },
  },
  filters: {
    status: function(value) {
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
  mounted() {
    driverSubsidyF07GetInfo({ id: this.$route.params.id }).then((res) => {
      this.form = res.data;
    });
    sysExamineTaskList({ relationId: this.$route.params.id }).then((res) => {
      console.log('.tableData2', res)
      this.tableData2 = res.data.sysExamineUserTaskList;
    });
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

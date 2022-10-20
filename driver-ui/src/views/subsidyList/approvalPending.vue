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
      <van-form v-if="form.daysStr">
        <!--   -->
        <van-field v-for="(item, index) in form.daysStr.split(',')" :key="index" readonly :label="index==0?'等待日期:':' '" :value="item" />
      </van-form>
      <van-form>
        <van-field readonly label="申请原因:" :value="form.remarks" />
      </van-form>
      <van-form>
        <van-field readonly label="申请日期:" :value="form.createTime" />
      </van-form>
      <van-row>
        <div v-for="item in processList" :key="item.id">
          <van-col span="18">
            <van-form>
              <van-field readonly label="审批人:" :value="item.userName" /> </van-form
          ></van-col>
          <van-col span="6">
            <div style="margin-top: 20px"><span>{{item.statusEnum}}</span></div>
          </van-col>
          <van-form>
            <van-field readonly label="抄送人:" :value="item.ccUserName" />
          <!-- <van-field readonly label="备注:" :value="item.remark"  /> -->
          </van-form>
          
        </div>
        <div style="margin: 16px" v-if="form.status == 1">
          <van-button
            round
            block
            type="info"
            @click="handleClick"
            native-type="submit"
            >撤销</van-button
          >
        </div>
        <div style="margin: 16px" v-if="form.status == 3">
          <van-button
                  round
                  block
                  type="info"
                  @click="reClick"
                  native-type="submit"
          >重新提交</van-button
          >
        </div>
      </van-row>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  driverSubsidyF07GetInfo,
  driverSubsidyF04Change,
} from "@/api/subsidyList/subsidy";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessList,
  getSysExamineTaskDriverList,
  getSysExamineTaskList
} from "@/api/api";
export default {
  components: {
    Header,
  },
  data() {
    return {
      processList: [],
      form: {},
    };
  },
  methods: {
    handleClick() {
      driverSubsidyF04Change({ id: this.$route.params.id }).then((res) => {
        this.$router.go(-1);
      });
    },

    reClick() {
      this.$router.push({
        path: "/subsidyRemote",
        query: {id: this.$route.params.id},
      });
    },
  },
  mounted() {
    driverSubsidyF07GetInfo({ id: this.$route.params.id }).then((res) => {
      this.form = res.data;
      // console.log("this.form.status",this.form.status)
      console.log("this.form",this.form)
    });
    getSysExamineTaskList({ relationId: this.$route.params.id, loginType:1 }).then(
      (res) => {
        console.log('审核状态',res);
        this.processList = res.data.sysExamineUserTaskList;
      }
    );
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

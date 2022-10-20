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
        <!-- <van-field readonly label="等待日期:" value="2020.09.10" /> -->
        <van-field v-for="(item, index) in form.daysStr.split(',')" :key="index" readonly :label="index==0?'等待日期:':' '" :value="item" />
      </van-form>
      <van-form>
        <van-field readonly label="申请原因:" :value="form.remarks" />
      </van-form>
      <van-form>
        <van-field readonly label="申请日期:" :value="form.createTime" />
        <van-field readonly label="备注:" :value="form.reviewRemarks"  />
      </van-form>
      <van-row>
           <div v-for="item in processList" :key="item.id">
        <van-col span="18">
          <van-form>
            <van-field readonly label="审批人:" :value="item.userName" /> </van-form
        ></van-col>
        <van-col span="6">
          <div style="margin-top: 20px"><span>已通过</span></div>
        </van-col>
        <van-form>
          <van-field readonly label="抄送人:" :value="item.ccUserName" />
        <!-- <van-field readonly label="备注:" :value="item.remark"  /> -->
        </van-form>
        </div>
      </van-row>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { driverSubsidyF07GetInfo } from "@/api/subsidyList/subsidy";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessList,
} from "@/api/api";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form: {},
      processList: [],
    };
  },
  methods: {},
  mounted() {
    driverSubsidyF07GetInfo({ id: this.$route.params.id }).then((res) => {
      this.form = res.data;
    });
     queryExamineConfigProcessList({ type: 2, id: this.$route.params.id }).then(
      (res) => {
        console.log(res);
        this.processList = res.rows;
      }
    );
  },
};
</script>
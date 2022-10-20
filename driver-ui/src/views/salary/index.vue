<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-row style="margin-top: 20px">
        <!-- <van-col span="12"
          ><div class="my-underline" @click="openDate = true">
            时间
          </div></van-col
        > -->
         <van-col span="12">
            <van-field
            is-link
            arrow-direction="down"
            label="时间："
            v-model="mounth_name"
            label-width="auto"
            readonly
            placeholder="请选择"
            @click="openDate = true"
          />
        </van-col>
      </van-row>
      <div
        class="box"
        v-for="item in dataList"
        :key="item.id"
        style="display: flex"
        @click="handleClick(item.id, item.vactionTime)"
      >
        <div style="font-weight: 600">
          {{ item | dateReplace }}
          <!-- 2020年9月份工资 -->
        </div>
        <div style="font-size: 0.3rem; line-height: 0.35rem">
          工资总额：{{ item.salary }}
        </div>
      </div>
    </div>
    <!-- <van-calendar v-model="openDate" @confirm="onConfirm" /> -->
    <!-- <van-calendar v-model="openDate" :min-date="new Date(2000, 0, 1)" :max-date="new Date(2010, 0, 1)" /> -->
    <van-action-sheet v-model="openDate">
      <!-- <div class="content">内容</div> -->
      <van-datetime-picker
        v-model="currentDate"
        @confirm="onConfirm"
        @cancel="openDate = false"
        type="year-month"
        title="选择年月"
        :min-date="minDate"
        :max-date="maxDate"
      />
      <!-- :formatter="formatter" -->
    </van-action-sheet>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import { formatDate, timeFn, dateReplace } from "@/components/index";
import { salaryG01List } from "@/api/salary/index";
export default {
  filters: {
    dateReplace: function (value) {
      
      return value.vactionTime.replace('-','年')+'月份工资'
    },
  },
  mounted() {
    let now = formatDate(new Date(), "yyyy");
    (this.minDate = new Date(now - 4, 0, 1)),
      (this.maxDate = new Date(now+4, 11, 1)),
      this.salaryG01dataList();
  },
  data() {
    return {
      mounth_name:undefined,
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      currentDate: new Date(),
      openDate: false,
      date: undefined,
      dataList: [],
    };
  },
  methods: {
    salaryG01dataList(data = {}) {
      salaryG01List(data).then((res) => {
        this.dataList = res.data;
      });
    },
    onConfirm(date) {
      this.openDate = false;

      let obj = {
        vactionTime: formatDate(this.currentDate, "yyyy-MM"),
      };
      this.mounth_name = obj.vactionTime
      this.salaryG01dataList(obj);
    },
    handleClick(id, mounth) {
      this.$router.push({ path: "/salaryDetail/" + id, query: { mounth:mounth.replace('-','年')+'月份工资' } });
    },
  },
  components: {
    Header,
  },
};
</script>

<style lang="scss" scoped>
.box {
  div {
    flex: 1;
    margin: 0.5rem 0;
  }
}
</style>
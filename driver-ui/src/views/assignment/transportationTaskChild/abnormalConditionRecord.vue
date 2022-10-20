<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row type="flex" justify="space-between">
        <van-col span="10">
          <van-field
            readonly
            clickable
            :value="query.createTimeModel"
            label="上报时间:"
            placeholder="点击选择时间"
            @click="showTime = true"
            label-width="1.2rem"
          />
          <!-- <van-popup v-model="showTime" position="bottom">
            <van-datetime-picker
              type="time"
              @confirm="onConfirmTime"
              @cancel="showTime = false"
            />
          </van-popup> -->
        </van-col>
        <van-col span="14">
          <van-field
            :value="stateValue"
            label="状态："
            label-width="auto"
            placeholder="请选择状态"
            @click="showState = true"
          />
          <van-popup v-model="showState" position="bottom">
            <van-picker
              show-toolbar
              :columns="listState"
              @confirm="onConfirmState"
              @cancel="showState = false"
            />
          </van-popup>
        </van-col>
      </van-row>

      <div class="box" v-for="o in list" :key="o" @click="handleClick(o)">
        <van-cell-group>
          <van-cell
            :title="o.typeEnum"
            :value="o.statusEnum"
            :border="false"
          />
          <div
            style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
          >
            <div class="text">上报时间：{{ o.createTime }}</div>
            <div class="text">异常原因： {{ o.remarks }}</div>
          </div>
        </van-cell-group>
      </div>
    </div>
    <van-action-sheet v-model="showTime">
      <van-datetime-picker
        @confirm="onConfirmTime"
        @cancel="showTime = false"
        type="year-month"
        title="选择年月"
        :min-date="minDate"
        :max-date="maxDate"
      />
    </van-action-sheet>
    <!-- <van-calendar v-model="showTime" @confirm="onConfirmTime" @cancel="showTime = false"/> -->
  </div>
</template>

<script>
import Header from "@/components/Header";
import { formatDate } from "@/components/index";
import { transportM06TransportTaskExceptionList } from "@/api/assignment/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      // 状态值
      stateValue: "",
      list: [],
      query: {
        createTimeModel: undefined,
        status: undefined,
      },
      // 状态列表
      listState: [
        { text: "待审核", value: 1 },
        { text: "已通过", value: 2 },
        { text: "已驳回", value: 3 },
        { text: "已撤回", value: 4 },
      ],
      // 时间
      showTime: false,
      // 状态
      showState: false,
      open: false,
      openDate: false,
      date: undefined,
    };
  },
  mounted() {
    let now = formatDate(new Date(), "yyyy");
    this.minDate = new Date(now - 4, 0, 1);
    this.maxDate = new Date(now, 11, 1);
    this.getList()
  },
  methods: {
    getList(){
transportM06TransportTaskExceptionList(this.query).then((res) => {
      console.log(res);
      this.list = res.rows;
    });
    },
    handleClick(o) {
      this.$router.push({path:"/applicationDetails/" + this.$route.params.id,query:{id:o.id,typeEnum:o.statusEnum}});
    },
    onConfirm() {},
    // 时间选择
    onConfirmTime(date) {
      // this.createTimeModel = `${date.getMonth() + 1}/${date.getDate()}`
      this.query.createTimeModel = formatDate(date, "yyyy-MM");
      this.showTime = false;
    this.getList()
    },
    // 状态
    onConfirmState(state) {
      console.log(state);
      this.stateValue = state.text;
      this.query.status = state.value;
      this.showState = false;
    this.getList()
    },

    // // 跳转详情
    // handClick(o) {
    //   switch (o) {
    //     case '待审批':
    //       this.$router.push('/approvalPending')
    //       break
    //     case '已通过':
    //       this.$router.push('/alreadyPassed')
    //       break
    //     case '已拒绝':
    //       this.$router.push('/refused')
    //       break
    //     case '已撤销':
    //       this.$router.push('/undone')
    //       break
    //     default:
    //       break
    //   }
    // },
  },
};
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
.box {
  width: 90%;
  border: 1px solid #ccc;
  margin: auto;
  margin-top: 0.5rem;
}
</style>
s

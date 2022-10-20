<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row type="flex" justify="space-between">
        <van-col span="24">
          <van-field
            clearable
            :value="valueState"
            label="任务状态："
            label-width="auto"
            placeholder="选择任务状态:"
            readonly
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

      <div class="box" v-for="o in list"  @click="handClick(o)" :key="o">
        <van-cell-group>
          <van-cell
            :title="'运单号：'+o.shipmentXid"
            :value="temp"
            :border="false"
          />
          <div
            style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
          >
            <div class="text">倒货起点：{{o.origin}}</div>
            <div class="text">倒货终点： {{o.destination}}</div>
            <div class="text">同步任务时间： {{o.taskStartTime}}</div>
          </div>
        </van-cell-group>
      </div>
    </div>
    <!-- <van-calendar v-model="openDate" @confirm="onConfirm" /> -->
  </div>
</template>

<script>
import Header from "@/components/Header";
import { transportTaskTaskOvertimeList } from "@/api/workOvertimeTaskList/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      temp:"",
      // 状态
      valueState: "",
      // 状态列表
      listState: [
        { text: "全部", value: null },
        { text: "进行中", value: 1 },
        { text: "已结束", value: 2 },
      ],
      // 状态
      showState: false,
      date: undefined,
      queryParams: {
        status: undefined,
      },
      list: [],
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      transportTaskTaskOvertimeList(this.queryParams).then((res) => {
        this.list = res.rows;
      });
    },
    // 状态
    onConfirmState(state) {
      this.valueState = state.text;
      this.queryParams.status = state.value;
      this.showState = false;
    this.getList();
    },
    // 跳转详情
    handClick(row) {
      this.$router.push("/taskdetail/"+row.id);
    },
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

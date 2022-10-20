<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      :buttonShow="true"
      @handleClick="handleClick"
      buttonText="申请补助"
    ></Header>
    <div class="app-container">
      <van-row type="flex" justify="space-between">
        <van-col span="10">
          <van-field
            clearable
            :value="value"
            label="类型："
            label-width="auto"
            readonly
            placeholder="选择类型"
            @click="showPicker = true"
          />
          <van-popup v-model="showPicker" position="bottom">
            <van-picker
              show-toolbar
              :columns="listItems"
              @confirm="onConfirm"
              @cancel="showPicker = false"
            />
          </van-popup>
        </van-col>
        <van-col span="14">
          <van-field
            :value="stateValue"
            label="状态："
            readonly
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

      <div class="box" v-for="o in list" :key="o.id" @click="handClick(o)">
        <van-cell-group>
          <van-cell
            :title="o.type == 1 ? '异地等待装卸货补助' : '加班补助'"
            :value="o.status | status"
            :border="false"
            
          />
          <div
            style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
          >
            <div class="text" style="display: flex">
              {{o.type == 1?'等待日期：':'加班日期' }}
              <div v-if="o.daysStr">
                <div v-for="(item, index) in o.daysStr.split(',')" :key="index">
                  {{item}}
                </div>
              </div>
            </div>
            <div class="text">
              申请日期：{{o.createTime}}
            </div>
          </div>
        </van-cell-group>
      </div>
    </div>
    <van-calendar v-model="openDate" @confirm="onConfirm" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { F01List } from "@/api/subsidyList/subsidy";

export default {
  components: {
    Header,
  },
  data() {
    return {
      // 物品值
      value: "",
      // 状态值
      stateValue: "",
      // 物品列表
      listItems: [
        { text: "全部", value: null },
        { text: "异地等待装卸货", value: 1 },
        { text: "加班补助", value: 2 },
      ],
      // 状态列表
      listState: [
        { text: "全部", value: null },
        { text: "待审核", value: 1 },
        { text: "已通过", value: 2 },
        { text: "已驳回", value: 3 },
        { text: "已撤回", value: 4 },
      ],
      list: [],
      showPicker: false,
      showCalendar: false,
      showState: false,
      open: false,
      openDate: false,
      date: undefined,
      queryParams: {
        type: undefined,
        status: undefined,
      },
    };
  },
  mounted() {
    this.getList();
  },
  filters: {
    // 状态 待审核 2已通过 3已拒绝 4已撤销
    status: function (value) {
      switch (value) {
        case 1:
          return "待审核";
        case 2:
          return "已通过";
        case 3:
          return "已拒绝";
        case 4:
          return "已撤销";
        default:
          break;
      }
    },
  },
  methods: {
    getList() {
      F01List(this.queryParams).then((res) => {
        this.list = res.rows;
      });
    },
    // 领用物品点击完成按钮时触发
    onConfirm(value) {
      this.value = value.text;
      this.queryParams.type = value.value;
      this.showPicker = false;
      this.getList();
    },
    // 时间选择
    onConfirmTime(date) {
      this.timeValue = `${date.getMonth() + 1}/${date.getDate()}`;
      this.showCalendar = false;
    },
    // 状态
    onConfirmState(state) {
      this.stateValue = state.text;
      this.queryParams.status = state.value;
      this.showState = false;
      this.getList();
    },
    // 跳转申请补助
    handleClick() {
      // console.log(555)
      this.$router.push("/subsidyRemote");
    },
    // 跳转详情
    handClick(o) {
      console.log(o);
      this.$router.push("/approvalPending/"+o.id);
      // switch (o.status) {
      //   case 1:
      //     this.$router.push("/approvalPending/"+o.id);
      //     break;
      //   case 2:
      //     this.$router.push("/alreadyPassed/"+o.id);
      //     break;
      //   case 3:
      //     this.$router.push("/refused/"+o.id);
      //     break;
      //   case 4:
      //     this.$router.push("/undone/"+o.id);
      //     break;
      //   default:
      //     break;
      // }
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

<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row type="flex" justify="space-between">
        <van-col span="10">
          <van-field
            clearable
            :value="value"
            label="物品："
            label-width="auto"
            placeholder="选择物品"
            @click="showPicker = true"
            readonly
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
            clickable
            :value="queryParams.beginTimes"
            label="领用时间："
            label-width="auto"
            placeholder="请选择领用时间"
            @click="showCalendar = true"
            readonly
          />
          <van-action-sheet v-model="showCalendar">
            <!-- <div class="content">内容</div> -->
            <van-datetime-picker
              v-model="currentDate"
              @confirm="onConfirmTime"
              type="year-month"
              title="选择年月"
              :min-date="minDate"
              :max-date="maxDate"
              @cancel="handleCancel"
            />
            <!-- :formatter="formatter" -->
          </van-action-sheet>
          <!-- <van-calendar v-model="showCalendar" @confirm="onConfirmTime" /> -->
        </van-col>
        <van-col span="12">
          <van-field
            :value="stateValue"
            label="状态："
            label-width="auto"
            placeholder="请选择状态"
            @click="showState = true"
            readonly
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
      <div
        v-infinite-scroll="loadMore"
        infinite-scroll-disabled="loading"
        infinite-scroll-distance="10"
      >
        <div class="box" v-for="o in list" :key="o.id" @click="handClick(o)">
          <van-cell-group>
            <van-cell
              :title="o.goodsName ? o.goodsName : o.goodsTypeName"
              :value="o.statusEnum"
              :border="false"
            />
            <div
              style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
            >
              <div class="text" v-if="o.number">数量:{{ o.number }}</div>
              <div class="text">车牌号:{{ o.licensePlate }}</div>
              <div class="text">领取日期:{{ o.receiveTime }}</div>
              <div class="text">申请日期{{ o.createTime }}</div>
            </div>
          </van-cell-group>
        </div>
      </div>
    </div>
    <!-- <van-calendar v-model="openDate" @confirm="onConfirm1" /> -->
  </div>
</template>

<script>
import Header from "@/components/Header";
import { formatDate } from "@/components/index";
import {
  goodsReceivingRecordL08List, //列表
  goodsReceivingRecordT01GoodsList, //物品下拉
} from "@/api/articlesUsingRegistrationList/index";
import { InfiniteScroll } from "mint-ui";
import Vue from "vue";
Vue.use(InfiniteScroll);

export default {
  components: {
    Header,
  },
  data() {
    return {
      is_down:false,
      loading: false,
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      currentDate: new Date(),
      // 物品值
      value: "",
      // 时间值
      timeValue: "",
      // 状态值
      stateValue: "",
      // 物品列表
      listItems: [],
      // 状态列表
      listState: [
        { text: "全部", value: null },
        { text: "待审核", value: 1 },
        { text: "已通过", value: 2 },
        { text: "已驳回", value: 3 },
        { text: "已领取", value: 4 },
        { text: "已撤回", value: 5 },
      ],
      list: [],
      // 状态
      // 城市值
      showPicker: false,
      showCalendar: false,
      showState: false,
      open: false,
      openDate: false,
      date: undefined,
      queryParams: {
        pageSize: 10,
        pageNum: 0,
        status: undefined,
        beginTimes: undefined,
        goodsId: undefined,
      },
    };
  },
  mounted() {
    this.$route.meta.title = "领用记录";
    let now = formatDate(new Date(), "yyyy");
    (this.minDate = new Date(now - 4, 0, 1)),
      (this.maxDate = new Date(now + 4, 11, 1));
    // 物品领用
    goodsReceivingRecordT01GoodsList({}).then((res) => {
      this.listItems = this.data_fn(res.rows);
    });
  },
  methods: {
    handleCancel() {
      this.showCalendar = false;
      this.queryParams.pageNum = 1;
      this.queryParams.beginTimes = undefined
      this.is_down = false;
    this.getList();
    },
    loadMore() {
      this.loading = true;
      console.log("到底了");
      // setTimeout(() => {
      // let last = this.list[this.list.length - 1];
      // for (let i = 1; i <= 10; i++) {
      //   this.list.push(last + i);
      // }
      this.queryParams.pageNum++;
      this.loading = false;
      this.is_down = false;
      this.getList();
      // }, 2500);
    },
    getList() {
       if (this.is_down) {
        return
      }
      goodsReceivingRecordL08List(this.queryParams).then((res) => {
        // this.list = res.rows;

        console.log("this.queryParams.pageNum",this.queryParams.pageNum)
        // debugger
        if (this.queryParams.pageNum <= 1) {
          this.list = res.rows;
        } else this.list = [...this.list, ...res.rows];
       if (res.rows.length==0) {
          this.queryParams.pageNum--
          this.is_down = true
        }
      });
    },
    // 数据处理
    data_fn(list) {
      let arr = [];
      arr.push({ text: "全部", goodsTypeId: null });
      for (const item of list) {
        let obj = {};
        obj.text = item.name;
        obj.goodsTypeId = item.id;
        arr.push(obj);
      }
      return arr;
    },
    // 领用物品点击完成按钮时触发
    onConfirm(value) {
      this.value = value.text;
      this.queryParams.goodsId = value.goodsTypeId;
      this.showPicker = false;
      this.queryParams.pageNum = 1;
      this.is_down = false;
      this.getList();
    },
    // 时间选择
    onConfirmTime(date) {
      this.queryParams.beginTimes = formatDate(this.currentDate, "yyyy-MM");
      // this.timeValue = `${date.getMonth() + 1}/${date.getDate()}`;
      this.showCalendar = false;
      this.queryParams.pageNum = 1;
      this.is_down = false;
      this.getList();
    },
    // 状态
    onConfirmState(state) {
      this.stateValue = state.text;
      this.queryParams.status = state.value;
      this.showState = false;
      this.queryParams.pageNum = 1;
      this.is_down = false;
      this.getList();
    },
    // 跳转详情
    handClick(o) {
      //1尿素 2工作餐 3轮胎 4 低值易耗品 5备件
      switch (o.goodsType) {
        case 1:
          this.$router.push({
            path: "/ureaDetail/" + o.id,
            query: { id: o.id, type: 1 },
          });
          break;
        case 5:
          this.$router.push({
            path: "/replacementPartDetail/" + o.id,
            query: { id: o.id, type: 1 },
          });
          break;
        case 2:
          this.$router.push({
            path: "/workingDdinnerDetail/" + o.id,
            query: { id: o.id, type: 1 },
          });
          break;

        // case 3:
        //   this.$router.push({
        //     path: "/tyreDetail/" + o.id,
        //     query: { id: o.id, type: 1 },
        //   });
        //   break;
        default:
          this.$router.push({
            path: "/lowPricedAndEasilyWornArticlesDetail/" + o.id,
            query: { id: o.id, type: 1 },
          });
          // setTimeout(() => {
          this.$route.meta.title = o.goodsTypeName;
          // }, 300);
          break;
      }
      // this.$router.push({
      //   path: "/callDetailRecording/" + o.id,
      //   query: { type: 1 },
      // });
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

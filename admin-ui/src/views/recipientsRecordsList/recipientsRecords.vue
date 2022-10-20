<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row type="flex" justify="space-between">
        <van-col span="12">
          <van-field
            clearable
            :value="value"
            is-link
            arrow-direction="down"
            label="物品："
            label-width="auto"
            placeholder="选择物品"
            readonly
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
        <van-col span="12">
          <!-- <van-field
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
          </van-popup> -->
            <van-search
            v-model="queryParams.createBy"
            shape="round"
            style="padding: 5px"
            placeholder="请输入领用人"
            @search="onSearch"
          />
        </van-col>
        <van-col span="24">
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
              @cancel="showCalendar = false"

            />
            <!-- :formatter="formatter" -->
          </van-action-sheet>
          <!-- <van-calendar v-model="showCalendar" @confirm="onConfirmTime" /> -->
        </van-col>
      </van-row>

      <div class="box" v-for="item in list" :key="item.id" @click="handClick(item)">
        <van-cell-group>
          <van-row>
            <van-col :span="12"><van-field :label="item.goodsName"></van-field></van-col>
            <van-col :span="12"
              ><van-field
                :label="item.number?'数量:':''"
                :value="item.number"
                label-width="auto"
              ></van-field
            ></van-col>
            <van-col :span="12"
              ><van-field
                label="车牌号:"
                :value="item.licensePlate"
                label-width="auto"
              ></van-field
            ></van-col>
            <van-col :span="12"
              ><van-field
                label="领用人:"
                :value="item.createBy"
                label-width="auto"
              ></van-field
            ></van-col>
            <van-col :span="24"
              ><van-field
                label="申请日期:"
                :value="item.createTime"
                label-width="auto"
              ></van-field
            ></van-col>
            <van-col :span="24"
              ><van-field
                label="领取日期:"
                :value="item.receiveTime"
                label-width="auto"
              ></van-field
            ></van-col>
          </van-row>
        </van-cell-group>
      </div>
    </div>
    <!-- <van-calendar v-model="openDate" @confirm="onConfirm1" /> -->
  </div>
</template>

<script>
import Header from '@/components/Header'
import { T01GoodsList,T01AdminGGoodsReceivingList } from '@/api/recipientsRecordsList/recipientsRecords'
import { formatDate } from "@/components/index";
// import Vue from "vue";
// import {Toast} from "vant";
// Vue.use(Toast);
export default {
  components: {
    Header,
  },
  data() {
    return {
      minDate: new Date(1970, 0, 1),
      maxDate: new Date(2060, 10, 1),
      currentDate: new Date(),
      // 物品值
      value: '',
      // 时间值
      timeValue: '',
      // 状态值
      stateValue: '',
      // 物品列表
      listItems: [],
      // 状态列表
      listState: [
        { text: '全部', value: null },
        { text: '待审核', value: 1 },
        { text: '已通过', value: 2 },
        { text: '已拒绝', value: 3 },
        { text: '已领取', value: 4 },
        { text: '已撤回', value: 5 },
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
        createBy: undefined,
        beginTimes: undefined,
        goodsId: undefined,
      },
    }
  },
  mounted() {
    // let now = formatDate(new Date(), 'yyyy')
    // ;(this.minDate = new Date(now - 4, 0, 1)),
    //   (this.maxDate = new Date(now, 11, 1))
    // 物品领用
    T01GoodsList({}).then((res) => {
      this.listItems = this.data_fn(res.rows)
      this.listItems.unshift({text:'全部',goodsTypeId:null})
    })
    this.getList()
  },
  methods: {
    getList() {
      T01AdminGGoodsReceivingList(this.queryParams).then((res) => {
        this.list = res.rows
      })
    },
      onSearch(val) {
      console.log(val);
      this.queryParams.createBy = val;
      this.getList();
    },
    // 数据处理
    data_fn(list) {
      let arr = []
      for (const item of list) {
        let obj = {}
        obj.text = item.name
        obj.goodsTypeId = item.id
        arr.push(obj)
      }
      return arr
    },
    // 领用物品点击完成按钮时触发
    onConfirm(value) {
      this.value = value.text
      this.queryParams.goodsId = value.goodsTypeId
      this.showPicker = false
      this.getList()
    },
    
    
    // 时间选择
    onConfirmTime(date) {
      this.queryParams.beginTimes = formatDate(this.currentDate, 'yyyy-MM')
      // this.timeValue = `${date.getMonth() + 1}/${date.getDate()}`;
      this.showCalendar = false
      this.getList()
    },
    // 状态
    onConfirmState(state) {
      this.stateValue = state.text
      this.queryParams.status = state.value
      this.showState = false
      this.getList()
    },
    // 跳转详情
    handClick(o) {
      this.$router.push({
        path: '/recordDetails/' + o.id,
      })
      // this.$router.push(
      //       "/approvalRecord/collectionManagement/" + o.id
      //     );
    },
  },
}
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

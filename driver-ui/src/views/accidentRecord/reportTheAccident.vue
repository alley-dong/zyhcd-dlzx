<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      buttonShow
      buttonText="事故上传"
      :buttonAccidentreport="true"
      @handleClick="handleClickbuttonAccidentreport"
    ></Header>
    <div class="app-container">
      <van-row type="flex" justify="space-between">
        <van-col span="10">
          <van-field
            is-link
            readonly
            arrow-direction="down"
            :value="stateValue"
            label="状态："
            label-width="auto"
            placeholder="请选择"
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
        <van-col span="14">
          <van-field
            is-link
            readonly
            arrow-direction="down"
            :value="queryParams.beginTime"
            label="事故发生时间："
            label-width="auto"
            placeholder="请选择"
            @click="showCalendar = true"
          />
          <van-action-sheet v-model="showCalendar">
            <!-- <div class="content">内容</div> -->
            <van-datetime-picker
              v-model="currentDate"
              @confirm="onConfirmTime"
        @cancel="handleCancel"
              type="year-month"
              title="选择年月"
              :min-date="minDate"
              :max-date="maxDate"
            />
            <!-- :formatter="formatter" -->
          </van-action-sheet>
          <!-- <van-calendar v-model="showCalendar" @confirm="onConfirmTime" /> -->
        </van-col>
      </van-row>
      <div
        class="box"
        v-for="(item, index) in getList1"
        :key="index"
        @click="handClick(item.id)"
        style="cursor:pointer;"
      >
        <van-field label="状态:" :value="item.status | values" readonly />
        <van-field
          label="事故上报日期:"
          :value="item.accidentTime"
          readonly
          label-width="auto"
        />
        <van-field label="车牌号:" :value="item.vehicleName" readonly />
        <van-field label="驾驶员:" :value="item.driverName" readonly />
        <van-field
          label="事故发生时间:"
          :value="item.createTime"
          readonly
          label-width="auto"
        />
        <van-field label="事故地点:" :value="item.accidentPlace" readonly />
      </div>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import { J01SafeAccidentList } from '@/api/reportTheAccident/reportTheAccident'
import { formatDate } from '@/components/index'

export default {
  components: {
    Header,
  },
  data() {
    return {
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      currentDate: new Date(),
      // 时间值
      timeValue: '',
      // 状态值
      stateValue: '',
      // 状态列表
      listState: [
        { text: '全部', value: null },
        { text: '待审核', value: 1 },
        { text: '已通过', value: 2 },
        { text: '已驳回', value: 3 },
      ],
      showState: false,
      showCalendar: false,
      queryParams: {
        status: undefined,
        beginTime: undefined,
      },
      getList1: [],
    }
  },
  mounted() {
    this.getList()
    let now = formatDate(new Date(), 'yyyy')
    ;(this.minDate = new Date(now - 4, 0, 1)),
      (this.maxDate = new Date(now+4, 11, 1))
  },
  filters: {
    values(status) {
      switch (status) {
        case 1:
          return '待审核'
        case 2:
          return '已通过'
        case 3:
          return '已驳回'
        default:
          break
      }
    },
  },
  methods: {
    handleCancel(){
      this.showCalendar = false
      this.queryParams.beginTime =undefined
    this.getList()
    },
    handleClickbuttonAccidentreport() {
      this.$router.push('/theAccidentReport')
    },
    // 交通事故-事故记录列表
    getList() {
      J01SafeAccidentList(this.queryParams).then((res) => {
        // console.log(res.rows)
        this.getList1 = res.rows
      })
    },
    // 时间选择
    onConfirmTime() {
      this.queryParams.beginTime = formatDate(this.currentDate, 'yyyy-MM')
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
    handClick(id) {
      this.$router.push('/incidentRecordDetails/' + id)
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

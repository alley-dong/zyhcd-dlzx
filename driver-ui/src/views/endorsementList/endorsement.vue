<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-row style="margin-top: 20px">
        <van-col span="12">
          <van-field
            is-link
            arrow-direction="down"
            label="状态："
            v-model="status_name"
            readonly
            label-width="auto"
            placeholder="请选择"
            @click="open = true"
          />
          <!-- <div @click="open = true">状态</div>-->
        </van-col>
        <!-- <van-col span="12"><div @click="open = true">状态</div></van-col> -->
        <!-- <van-col span="12"><div @click="openDate = true">时间</div></van-col> -->
      </van-row>

      <div
        class="box"
        v-for="(item, index) in getList"
        :key="index"
        @click="handClick(item.id)"
        style="cursor: pointer"
      >
        <van-field label="状态:" :value="item.status | values" readonly />
        <van-field label="违法车辆:" :value="item.licensePlate" readonly />
        <van-field label="违法地点:" :value="item.wzdd" readonly />
        <van-field label="违法时间:" :value="item.wzsj" readonly />
        <!-- <van-field
          label="事故发生时间:"
          :value="item.accidentTime"
          readonly
          label-width="auto"
        />
        <van-field label="事故地点:" :value="item.accidentPlace" readonly /> -->
      </div>
    </div>
    <van-action-sheet
      v-model="open"
      :actions="actions"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect"
    />
    <van-calendar v-model="openDate" @confirm="onConfirm" />
  </div>
</template>

<script>
import Header from '@/components/Header'
import { K01List } from '@/api/endorsementList/endorsement'
import { formatDate } from '@/components/index'
import { Toast } from 'vant'
export default {
  components: {
    Header,
  },
  data() {
    return {
      status_name:undefined,
      open: false,
      openDate: false,
      date: undefined,
      actions: [
        { name: '全部', value: null },
        { name: '未处理', value: 1 },
        { name: '已处理', value: 2 },
      ],
      getList: [],
      queryParams: {
        status: undefined,
      },
    }
  },
  mounted() {
    this.getK01List()
  },
  filters: {
    values(status) {
      switch (status) {
        case 1:
          return '未处理'
        case 2:
          return '已处理'
        // case 3:
        //   return '已驳回'
        default:
          break
      }
    },
  },
  methods: {
    // handleClickbuttonAccidentreport() {
    //   this.$router.push("/theAccidentReport");
    // },
    // 违章-违章列表App
    getK01List() {
      K01List(this.queryParams).then((res) => {
        console.log(res.rows)
        this.getList = res.rows
        for (const item of res.rows) {
          item.wzsj = formatDate(item.wzsj, 'yyyy-MM-dd')
        }
      })
    },
    // 跳转详情
    handClick(id) {
      this.$router.push('/violationOfTheDetails/' + id)
    },
    //   下拉取消
    onCancel() {
      console.log(this.actions)
      Toast('取消')
    },
    // 选择下拉
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.open = false
      Toast(item.name)
      this.queryParams.status = item.value
      this.status_name = item.name
      this.getK01List()
    },
    formatDate(date) {
      return `${date.getMonth() + 1}/${date.getDate()}`
    },
    onConfirm(date) {
      this.openDate = false
      console.log(date)
      this.date = this.formatDate(date)
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

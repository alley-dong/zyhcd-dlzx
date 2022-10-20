<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row style="margin-top: 20px">
        <van-col span="12">
          <van-field
            is-link
            arrow-direction="down"
            label="状态："
            label-width="auto"
            placeholder="请选择"
            readonly
            v-model="state_name"
            @click="open = true"
          />

          <!-- <div @click="open = true">状态</div> -->
        </van-col>
        <van-col span="12">
          <van-field
            is-link
            arrow-direction="down"
            label="时间"
            v-model="queryParams.bTime"
            readonly
            label-width="auto"
            placeholder="请选择"
            @click="openDate = true"
          />
          <!-- <div @click="openDate = true">时间</div> -->
        </van-col>
      </van-row>

      <div class="box" v-for="o in list" @click="handClick(o)" :key="o.id">
        <van-cell-group>
          <van-cell
            :title="o.title"
            :value="o.status | status"
            :border="false"
          />
          <div
            style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
          >
            <div class="text">发布时间：{{ o.createTime }}</div>
            <div class="text">发布人： {{ o.createBy }}</div>
          </div>
        </van-cell-group>
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
    <!-- <van-calendar v-model="openDate" @confirm="onConfirm" /> -->
    <van-action-sheet v-model="openDate">
      <van-datetime-picker
        @cancel="openDate = false"
        v-model="currentDate"
        @confirm="onConfirm"
        type="year-month"
        title="选择年月"
        :min-date="minDate"
        :max-date="maxDate"
      />
    </van-action-sheet>
  </div>
</template>

<script>
import Header from '@/components/Header'
import { Toast } from 'vant'
import { driverNoticeD01List } from '@/api/announcement/index'
import { formatDate } from '@/components/index'
export default {
  components: {
    Header,
  },
  data() {
    return {
      state_name:undefined,
      open: false,
      openDate: false,
      date: undefined,
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      currentDate: new Date(),
      list: [],
      actions: [
        { name: '全部', value: null },
        { name: '未读', value: 1 },
        { name: '已读', value: 2 },
      ],
      queryParams: {
        bTime: undefined,
        status: undefined,
      },
    }
  },
  filters: {
    status: function(value) {
      switch (value) {
        case 1:
          return '未读'
        case 2:
          return '已读'

        default:
          // return '-'
          break
      }
    },
  },
  mounted() {
    let now = formatDate(new Date(), 'yyyy')
    ;(this.minDate = new Date(now - 4, 0, 1)),
      (this.maxDate = new Date(now + 4, 11, 1)),
      this.getList()
  },
  methods: {
    getList() {
      driverNoticeD01List(this.queryParams).then((res) => {
        // console.log(res)
        this.list = res.rows
      })
    },
    // 跳转详情
    handClick(o) {
      this.$router.push('/announcementDetails/' + o.noticeId)
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
      this.state_name = item.name
      this.queryParams.status = item.value
      this.getList()
    },
    formatDate(date) {
      return `${date.getMonth() + 1}/${date.getDate()}`
    },
    onConfirm(date) {
      this.openDate = false
      console.log(date)
      this.queryParams.bTime = formatDate(date, 'yyyy-MM')
      this.getList()
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

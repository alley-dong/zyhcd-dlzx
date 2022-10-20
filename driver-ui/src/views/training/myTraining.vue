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
            v-model="status_name"
            readonly
            label-width="auto"
            placeholder="请选择"
            @click="open = true"
          />
          <!-- <div @click="open = true">状态</div>-->
        </van-col>
        <van-col span="12">
          <van-field
            is-link
            arrow-direction="down"
            label="时间"
            v-model="queryParams.bTime "
            readonly
            label-width="auto"
            placeholder="请选择"
            @click="openDate = true"
          />

          <!-- <div @click="openDate = true">时间</div> -->
        </van-col>
      </van-row>

      <div class="box" v-for="o in list" :key="o.id"  @click="handClick(o)">
        <van-cell-group>
          <van-cell
            :title="o.title"
            :value="o.status | status"
            :border="false"
           
          />
          <div
            style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
          >
            <div class="text">发布培训时间：{{ o.createTime }}</div>
            <div class="text">培训截止时间：{{ o.trainingEndTime }}</div>
            <div class="text">发起人：{{ o.createBy }}</div>
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
        v-model="currentDate"
        @cancel="openDate = false"
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
import { driverTrainingC01List } from '@/api/training/myTraining'
import Header from '@/components/Header'
import { Toast } from 'vant'
import { formatDate } from '@/components/index'
export default {
  components: {
    Header,
  },
  data() {
    return {
      open: false,
      status_name:undefined,
      currentDate: new Date(),
      openDate: false,
      date: undefined,
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      list: [],
      queryParams: {
        status: undefined,
        bTime: undefined,
      },
      // 状态 1未完成 2已完成 3超期未完成 4超期已完成
      actions: [
        { name: '全部', value: null },
        { name: '未完成', value: 1 },
        { name: '已完成', value: 2 },
        { name: '超期未完成', value: 3 },
        { name: '超期已完成', value: 4 },
      ],
    }
  },
  filters: {
    status: function(value) {
      switch (value) {
        case 1:
          return '未完成'
        case 2:
          return '已完成'
        case 3:
          return '超期未完成'
        case 4:
          return '超期已完成'

        default:
          break
      }
    },
  },
  created() {
    let now = formatDate(new Date(), 'yyyy')
    ;(this.minDate = new Date(now - 4, 0, 1)),
      (this.maxDate = new Date(now+4, 11, 1)),
      this.getList()
  },
  methods: {
    // 列表
    getList() {
      driverTrainingC01List(this.queryParams).then((res) => {
        console.log(res.data)
        this.list = res.rows
      })
    },
    // 跳转详情
    handClick(o) {
      this.$router.push('/trainingDetails/' + o.trainingId)
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
      this.getList()
    },
    formatDate(date) {
      return `${date.getMonth() + 1}/${date.getDate()}`
    },
    onConfirm(date) {
      this.openDate = false
      this.queryParams.bTime = formatDate(date, 'yyyy-MM')
      console.log(this.date)
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

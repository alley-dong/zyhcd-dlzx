<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      :buttonShow="true"
      @handleClick="handClick"
      buttonText="领用记录"
      >></Header
    >
    <div class="app-container">
      <van-cell-group>
        <van-cell title="请选择领用的物品类型" />
        <van-field
          is-link
          arrow-direction
          readonly
          v-for="item in list"
          :key="item.id"
          :value="item.name"
          @click="handleType(item.name, item.id)"
        ></van-field>
      </van-cell-group>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import { L01GoodTypeList } from '@/api/articlesUsingRegistrationList/index'
export default {
  components: {
    Header,
  },
  data() {
    return {
      // 类别列表
      list: [],
    }
  },
  mounted() {
          this.$route.meta.title = "物品领用"

    this.getList()
  },
  methods: {
    handleType(name, id) {
      switch (name) {
        case '尿素':
          this.$router.push('/urea/' + id)
          break
        case '轮胎':
          this.$router.push('/tyre/' + id)
          break
        // case '低值易耗品':
        //   break
        case '备件':
          this.$router.push('/replacementPart/' + id)
          break
        case '工作餐':
          this.$router.push('/workingDdinner/' + id)
          break
        default:
          console.log(name);
          this.$router.push('/lowPricedAndEasilyWornArticles/' + id)
          this.$route.meta.title = name
          break
      }
    },
    // 物品类别
    getList() {
      L01GoodTypeList({}).then((res) => {
        this.list = res.rows
      })
    },
    // 跳转领用记录
    handClick() {
      this.$router.push('/recipientsRecords')
    },
  },
}
</script>

<style scoped>
.yanse:hover {
  background: pink;
}
</style>

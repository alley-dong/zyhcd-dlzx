<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-cell-group>
        <van-field v-model="value" label="车牌号" placeholder="请输入车牌号" />
      </van-cell-group>
      <van-cell
        v-for="(item, index) in computedList"
        :key="index"
        @click="handleClick(item)"
      >
        {{ item.licensePlate }}
      </van-cell>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import { J02CarVehicleNameList } from '@/api/reportTheAccident/theAccidentReport'
export default {
  components: {
    Header,
  },
  data() {
    return {
      value: '',
      carList: [],
    }
  },
  mounted() {
    this.getJ02CarVehicleNameList()
  },

  methods: {
    // 车牌号下拉
    getJ02CarVehicleNameList() {
      J02CarVehicleNameList({}).then((res) => {
        this.carList = res.data
      })
    },
    handleClick(value) {
      this.value = value.licensePlate
      let { licensePlate, id } = value
      this.$store.dispatch('AccidentCar', { licensePlate, id })
      this.$router.back()
    },
  },

  computed: {
    computedList() {
      return this.carList.filter((item) =>
        item.licensePlate.includes(this.value)
      )
    },
  },
}
</script>

<style lang="sass" scoped></style>

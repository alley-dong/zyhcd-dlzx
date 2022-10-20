<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row>
        <van-col span="15"
          ><van-form>
            <van-field
              clickable
              is-link
              arrow-direction="down"
              :value="value"
              label="领用物品:"
              placeholder="请选择"
              @click="showPicker = true"
            />
            <van-popup v-model="showPicker" position="bottom">
              <van-picker
                show-toolbar
                :columns="listItems"
                @confirm="onConfirm"
                @cancel="showPicker = false"
              />
            </van-popup> </van-form
        ></van-col>
        <van-col span="9">
          <van-form>
            <van-field :value="0" label="库存:" label-width="auto" />
          </van-form>
        </van-col>
      </van-row>
      <van-form>
        <van-field
          clearable
          readonly
          name="picker"
          :value="value"
          label="规格："
          placeholder=""
        />
      </van-form>
      <van-form>
        <van-field readonly name="picker" :value="value" label="单位：" />
      </van-form>
      <van-form>
        <van-field
          is-link
          arrow-direction="down"
          readonly
          clickable
          :value="form.vehicleName"
          label="车牌号:"
          placeholder="请选择车牌号"
          @click="handleSearch"
        />
        <van-popup v-model="showCar" position="bottom">
          <van-picker
            show-toolbar
            :columns="carList"
            @confirm="onConfirmCar"
            @cancel="showCar = false"
          />
        </van-popup>
      </van-form>
      <van-form>
        <van-field
          is-link
          arrow-direction="down"
          clickable
          readonly
          :value="lineValue"
          label="线路:"
          placeholder="请选择线路"
          @click="showLine = true"
        />
        <van-popup v-model="showLine" position="bottom">
          <van-picker
            show-toolbar
            :columns="lineList"
            @confirm="onConfirmLine"
            @cancel="showLine = false"
          />
        </van-popup>
      </van-form>
      <van-form>
        <van-field
          clearable
          name="picker"
          value="value"
          label="数量："
          placeholder="请输入领用数量"
        />
      </van-form>
      <van-form>
        <van-field readonly name="picker" value="李四" label="领用人" />
      </van-form>
      <van-form>
        <van-field readonly name="picker" value="张三" label="审批人" />
      </van-form>
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
      // 物品值
      value: '',
      // 车牌号
      carValue: '',
      // 线路列表
      lineValue: '',
      // 物品列表
      listItems: ['杭州', '宁波', '温州', '嘉兴', '湖州'],
      // 车牌号下拉
      carList: ['1111', '2222'],
      // 线路
      lineList: ['线路1', '线路2'],
      form: {
        vehicleName: '',
      },
      showPicker: false,
      showCar: false,
      showLine: false,
    }
  },
  mounted() {
    this.getList()
    // 车牌号
    this.form.vehicleName = this.$store.state.AccidentCar.licensePlate
    this.form.vehicleId = this.$store.state.AccidentCar.id
  },
  methods: {
    // 车牌号搜索
    handleSearch() {
      this.$router.push('/theAccidentReportSearch')
    },
    getList() {
      L01GoodTypeList({}).then((res) => {
        console.log(res.rows.name)
      })
    },
    // 物品
    onConfirm(value) {
      this.value = value
      this.showPicker = false
    },
    // 车牌号触发事件
    onConfirmCar(car) {
      this.carValue = car
      this.showCar = false
    },
    // 线路
    onConfirmLine(line) {
      this.lineValue = line
      this.showLine = false
    },
  },
}
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

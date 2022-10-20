<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-form>
        <van-field readonly label="运单号:" :value="form.shipmentXid" />
      </van-form>
      <!--<van-form>
        <van-field readonly label="干线运单号:" :value="form.arteryShipmentXid" />
      </van-form>-->
      <van-form>
        <van-field readonly label="主车车牌号:" :value="form.mainLicensePlate" />
      </van-form>
      <van-form>
        <van-field readonly label="挂车车牌号:" :value="form.hangLicensePlate" />
      </van-form>
      <van-form>
        <van-field readonly label="驾驶员:" :value="form.driverName" />
      </van-form>
      <van-form>
        <van-field readonly label="油料费:" :value="form.oilAmount" />
      </van-form>
      <van-form>
        <van-field readonly label="加班费:" :value="form.overtimeAmount" />
      </van-form>
      <van-form>
        <van-field readonly label="倒货起点:" :value="form.origin" />
      </van-form>
      <van-form>
        <van-field readonly label="倒货终点:" :value="form.destination" />
      </van-form>
      <!--<van-form>
        <van-field readonly label="任务状态:" :value="form.statusEnum" />
      </van-form>-->
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import { transportTaskTaskOvertimeInfo } from "@/api/workOvertimeTaskList/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form:{}
    }
  },
  mounted () {
   transportTaskTaskOvertimeInfo({id:this.$route.params.id}).then(res=>{
     this.form = res.data
   })
  },
  methods: {},
}
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-cell-group>
        <van-field
          v-model="value"
          @input="handleInput"
          label="车牌号"
          placeholder="请输入车牌号"
        />
      </van-cell-group>
      <van-cell
        v-for="(item, index) in dataList"
        :key="index"
        @click="handleClick(item)"
        :value="item.licensePlate"
      />

      <!-- :value="item.licensePlate + '1'" -->
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  inspectI02GetInfo,
  inspectI03GetInfo,
} from "@/api/carRegularInspection/index";
import { repairH06LicensePlate } from "@/api/maintenance/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      value: undefined,
      dataList: [],
      dataList1: [],
    };
  },
  props: {
    type: {
      type: Number,
      default: 1,
    },
    licensePlat: {},
  },
  computed: {
    //   dataList(val) {
    //       console.log(val);
    //       return this.dataList.filter((e)=>e.indexOf('1')>=0)
    //   }
  },
  methods: {
    debounce(fn, wait) {
      var timer = null;
      return function () {
        if (timer !== null) {
          clearTimeout(timer);
        }
        timer = setTimeout(fn, wait);
      };
    },

    handle(val) {
      this.dataList = this.dataList1.filter(
        (e) => e.licensePlate.indexOf(val) >= 0
      );
    },

    handleClick(val) {
      let { licensePlate, id } = val;
      //   this.$store.dispatch("SetVehicleId", { licensePlate, vehicleId: id });
      //   this.$router.go(-1);
      this.$emit("licensePlateClick",val);
    },
    handleInput(val) {
      this.debounce(this.handle(val), 300);
    },
  },
  mounted() {
    console.log(this.$props.licensePlat);
    // let vehicleType = this.$store.state.type == 1 ? "a" : "b";
    // let vehicleType = this.$props.type == 1 ? "a" : "b";
    // inspectI02GetInfo({
    //   vehicleType,
    // }).then((res) => {
    //   this.dataList = this.dataList1 = res.data;
    // });
    //   主车 挂车下拉
    repairH06LicensePlate({}).then((res) => {
      for (const item of res.data.main) {
        item.name = item.licensePlate;
      }
      for (const item of res.data.hang) {
        item.name = item.licensePlate;
      }
      this.dataList = this.dataList1 =
        this.$props.licensePlat == 1 ? res.data.main : res.data.hang;
      //   this.actions = res.data.main;
      //   this.actionsHang = res.data.hang;
    });
  },
};
</script>

<style lang="sass" scoped>
</style>
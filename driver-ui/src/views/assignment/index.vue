<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      buttonShow
      @handleClick="addClick"
      buttonText="任务记录"
    ></Header>
    <div class="app-container">
      <div class="box" @click="handleClick(item.id)" v-for="item in tableList" :key="item.id">
        <van-cell :title="`运单类型：${item.shipmentTypeEnum}`" :border="false" :value="item.statusEnum" />
        <div style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem">
          <div class="text">同步任务时间：{{item.createTime}}</div>
          <div class="text">任务线路：{{item.routeName}}</div>
          <div class="text">运单号： {{item.shipmentXid}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import {transportTaskM01TransportTaskList} from '@/api/assignment/index'
export default {
  components: {
    Header,
  },
  data() {
    return {
      tableList: []
    }
  },
  
  mounted () {
    transportTaskM01TransportTaskList({}).then(res=>{
      this.tableList = res.rows
    })
  },
  methods: {
    addClick() {
      console.log("新增");
      this.$router.push('/missionRecords')
    },
    handleClick(id){
      this.$router.push({path:'/transportationTask/'+id})

    }
  },
};
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
</style>
<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <div
        class="box"
        v-for="item in tableList"
        :key="item.id"
        @click="handleClick(item.id)"
      >
        <van-cell
          :title="`运单类型：${item.shipmentTypeEnum}`"
          :border="false"
          :value="item.statusEnum"
        />
        <div style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem">
          <div class="text">指派任务时间：{{ item.createTime }}</div>
          <div class="text">任务线路：{{ item.routeName }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import { transportM01TransportTaskRecordList } from "@/api/assignment/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      tableList: [],
    };
  },
  mounted() {
    transportM01TransportTaskRecordList({}).then((res) => {
        console.log(1111);
     
      this.tableList = res.rows;
    });
  },
  methods: {
    handleClick(id) {
      this.$router.push("/missionRecordsDetail/" + id);
    },
  },
};
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
</style>
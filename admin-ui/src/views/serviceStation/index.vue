<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      :buttonShow="true"
      @handleClick="handleAdd"
    ></Header>
    <div class="app-container">
      <van-row>
        <van-col :span="24">
          <van-search
            v-model="queryParams.name"
            shape="round"
            style="padding: 5px"
            placeholder="请输入维修服务站"
            @search="onSearch"
          />
        </van-col>
      </van-row>
      <div
        class="box"
        v-for="(item, index) in list"
        :key="index"
        @click="handClick(item)"
        style="cursor: pointer"
      >
        <van-row>
          <van-col :span="24">
            <van-field
              label="名称:"
              :value="item.name"
              readonly
              label-width="auto"
          /></van-col>
          <!-- <van-col :span="8">
            <van-field
              :value="item.status == 1 ? '未提交' : '已提交'"
              readonly
              label-width="auto"
          /></van-col> -->
          <van-col :span="24">
            <van-field
              label="电话:"
              :value="item.tel"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="地址:"
              :value="item.address"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="定点维修:"
              :value="item.isFixed == 1 ? '是' : '否'"
              readonly
              label-width="auto"
          /></van-col>
        </van-row>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { garageCarH15List } from "@/api/serviceStation";
export default {
  components: {
    Header,
  },
  data() {
    return {
      queryParams: {
        name: undefined,
      },
      list: [],
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    getList() {
      garageCarH15List(this.queryParams).then((res) => {
        console.log(res);
        this.list = res.rows;
      });
    },
    onSearch(val) {
      console.log(val);
      this.queryParams.name = val;
      this.getList();
    },
    handleAdd() {
        this.$router.push('/serviceStationAdd')
    },
    handClick(row) {},
  },
};
</script>

<style lang="scss" scoped>
</style>
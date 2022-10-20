<template>
  <div>
    <Header :show="true" :showRight="false"></Header>

    <div class="app-container">
      <van-row>
        <van-col :span="24">
          <van-search
            v-model="queryParams.goodName"
            shape="round"
            style="padding: 5px"
            placeholder="请输入物品名称"
            @search="onSearch"
          />
        </van-col>
      </van-row>
      <van-field readonly label="物品类别:" :value="goodsTypeName"> </van-field>
      <div
        class="box"
        v-for="(item, index) in dataList"
        :key="index"
        style="cursor: pointer"
      >
        <van-row>
          <van-col :span="24">
            <van-field
              label="物品编码:"
              :value="item.goodsCode"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              label="物品名称"
              :value="item.goodsName"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              label="规格:"
              :value="item.specification"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="当前库存:"
              :value="item.currentQuantity"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="盘点数量:"
              @blur="handleBlur(item)"
              v-model="item.checkQuantity"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              label="盈亏:"
              :value="item.status | status"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              label="差异数量:"
              :value="item.difference"
              readonly
              label-width="auto"
          /></van-col>
        </van-row>
      </div>
    </div>
    <van-row style="margin-top:1rem">
      <van-col :span="12">
        <van-button type="primary" @click="$router.go(-1)">保存</van-button>
      </van-col>
      <van-col :span="12">
        <van-button type="primary" @click="handleClick">提交</van-button>
      </van-col>
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Vue from "vue";
import { Search,Toast } from "vant";
Vue.use(Search);
import {
  T03AdminGGoodsCheckInfo,
  T05AdminUpdateCheckItem,
  T04AdminSubmitCheckItem,
} from "@/api/checkList";
export default {
  components: {
    Header,
  },

  data() {
    return {
      value: "",
      valueCalendar: "",
      wvalue: "",
      columns: ["杭州", "宁波", "温州", "嘉兴", "湖州"],
      showPicker: false,
      showCalendar: false,
      show: false,
      dataList: [],
      goodsTypeName: undefined,
      queryParams: {
        id: undefined,
        goodName: undefined,
      },
      form: {},
    };
  },
  filters: {
    status: function (value) {
      switch (value) {
        case 1:
          return "盘盈";
        case 2:
          return "盘亏";
        case 3:
          return "--";
        default:
          break;
      }
    },
  },
  mounted() {
    console.log();

    this.queryParams.id = this.$route.params.id;
    this.getList();
  },
  methods: {
    handleClick() {
       for (const item of this.dataList) {
        if (item.checkQuantity == "") {
          // item.checkQuantity = null
          Toast.fail("盘点数量为必填项");
          return;
        }
      }
      let obj = {};
      obj.id = this.$route.params.id;
      obj.gGoodsCheckItems = this.dataList;
      T04AdminSubmitCheckItem(obj).then((res) => {
        Toast.fail(res.msg);
        this.$router.go(-1);
      });
    },
    handleBlur(val) {
      console.log(val);
      T05AdminUpdateCheckItem(val).then((res) => {
        this.getList();
      });
    },
    getList() {
      T03AdminGGoodsCheckInfo(this.queryParams).then((res) => {
        console.log(res);
        this.goodsTypeName = res.data.goodsTypeName;
        this.dataList = res.data.gGoodsCheckItems;
      });
    },
    // 手机号搜索
    onSearch(val) {
      console.log(val);
      this.queryParams.goodName = val;
      this.getList();
    },
  },
};
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
</style>

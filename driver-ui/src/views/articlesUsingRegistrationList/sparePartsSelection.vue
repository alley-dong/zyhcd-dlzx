<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-search
        v-model="query.goodsName"
        shape="round"
        placeholder="请输入搜索关键词"
        @search="onSearch"
        @cancel="onCancel"
      />
      <el-table :data="listItems" style="width: 100%" border>
        <el-table-column prop="name" align="center" label="备件名称">
        </el-table-column>
        <el-table-column prop="amount" align="center" width="50px" label="库存">
        </el-table-column>
        <el-table-column label="选择数量" align="center">
          <template slot-scope="scope">
            <van-stepper
              input-width="0.4rem"
              v-model="scope.row.number"
              :max="scope.row.amount"
            />
          </template>
          <!-- <van-stepper v-model="form.gGoodsReceivingRecordItems[0].number" /> -->
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="small"  @click.stop="handleClick(scope.row)">加入</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import Header from "@/components/Header";
import { goodsReceivingRecordL07GetGoodList } from "@/api/articlesUsingRegistrationList/index";
import Vue from "vue";
import { Search } from "vant";
Vue.use(Search);
export default {
  components: {
    Header,
  },
  data() {
    return {
      listItems: [],
      data_ListItems: [],
      query: {
        goodsTypeId: undefined,
        goodsName: undefined,
      },
    };
  },
  methods: {
    onSearch(val) {
      // Toast(val);
      // console.log(this.query.goodsTypeName);
      this.getList();
    },
    onCancel() {
      // Toast('取消');
      // console.log('1111');
      // console.log(this.query.goodsTypeName);
    },
    getList() {
      goodsReceivingRecordL07GetGoodList(this.query).then((res) => {
        let arr = [];
        let list = JSON.parse(localStorage.getItem("spareListItems"));
        if (list && list.length != 0) {
          let id_list = [];
          for (const data of list) {
            id_list.push(data.id);
          }
          for (const item of res.rows) {
            if (id_list.indexOf(item.id) < 0) {
              arr.push(item);
            }
          }
        } else {
          arr = res.rows;
        }

        this.listItems = arr;
      });
    },
    handleClick(val) {
      //replacementPartList
      let replacementPartList = JSON.parse(
        localStorage.getItem("spareListItems")
      );
      if (replacementPartList.length > 0) {
        replacementPartList.push(val);
        localStorage.setItem(
          "spareListItems",
          JSON.stringify(replacementPartList)
        );
      } else {
        localStorage.setItem("spareListItems", JSON.stringify([val]));
      }
      this.$router.go(-1);
      // this.$router.push("/replacementPart/" + this.$route.query.goodsTypeId);
    },
  },
  mounted() {
    this.query.goodsTypeId = this.$route.query.goodsTypeId;
    this.getList();
    let spareListItems = JSON.parse(localStorage.getItem("spareListItems"));
    if (spareListItems) {
      this.data_ListItems = spareListItems;
    }
  },
};
</script>

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
        <van-col :span="12">
          <van-field
            readonly
            clickable
            name="calendar"
            :value="valueCalendar"
            label="盘点时间:"
            label-width="auto"
            placeholder="点击选择"
            @click="showCalendar = true"
          />
<!--          <van-calendar v-model="showCalendar" @confirm="onConfirmTime" />-->
          <van-action-sheet v-model="showCalendar">
            <van-datetime-picker
                    v-model="currentDate"
                    @confirm="onConfirmTime"
                    type="year-month"
                    title="选择年月"
                    :min-date="minDate"
                    :max-date="maxDate"
                    @cancel="onClearTime"
            />
          </van-action-sheet>
        </van-col>
        <van-col :span="12">
          <van-search
            v-model="queryParams.code"
            shape="round"
            clearable
            style="padding: 5px"
            placeholder="请输入盘点单号"
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
          <van-col :span="16">
            <van-field
              label="盘点单号:"
              :value="item.code"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="8">
            <van-field
              :value="item.status == 1 ? '未提交' : '已提交'"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="盘点时间:"
              :value="item.createTime"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="物品类别:"
              :value="item.goodsTypeName"
              readonly
              label-width="auto"
          /></van-col>
          <van-col :span="24">
            <van-field
              label="盘点人员:"
              :value="item.createBy"
              readonly
              label-width="auto"
          /></van-col>
        </van-row>
      </div>

      <van-popup v-model="show" position="bottom" style="height: 200px">
        <van-field
          readonly
          clickable
          is-link
          arrow-direction="down"
          name="picker"
          :value="value"
          label="物品类型:"
          placeholder="点击选择"
          @click="showPicker = true"
          style="margin-top: 20px"
        />
        <van-popup v-model="showPicker" position="bottom">
          <van-picker
            show-toolbar
            :columns="columns"
            @confirm="onConfirm"
            @cancel="showPicker = false"
          />
        </van-popup>
        <van-row style="margin-top: 40px">
          <van-col span="12"
            ><van-button type="default" @click="show = false" size="small"
              >返回</van-button
            ></van-col
          >
          <van-col span="12"
            ><van-button type="default" @click="routerClick" size="small"
              >确定</van-button
            ></van-col
          >
        </van-row>
      </van-popup>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import Vue from "vue";
import { Search, Toast } from "vant";
import { T03AdminGGoodsCheckList, T03AdminInsertCheck } from "@/api/checkList";
Vue.use(Search);
import { formatDate } from "@/components/index";
import { goodsCheckK14AddGetInfo } from "@/api/recipientsRecordsList/recipientsRecords";
export default {
  components: {
    Header,
  },

  data() {
    return {
      value: "",
      valueCalendar: "",
      wvalue: "",
      data: undefined,
      columns: [
        //  { text: '全部', value: null },
      ],
      showPicker: false,
      showCalendar: false,
      currentDate: new Date(),
      minDate: new Date(1970, 0, 1),
      maxDate: new Date(2060, 10, 1),
      show: false,
      list: [],
      queryParams: {
        beginTimes: undefined,
        code: undefined,
      },
    };
  },
  mounted() {
    this.getList();
    goodsCheckK14AddGetInfo({}).then((res) => {
      this.columns = this.data_fn(res.rows);
    });
  },
  methods: {
    routerClick() {
      if (!this.data) {
        Toast.fail("请选择物品");
        return;
      }
      let { text: name, goodsTypeId: id } = this.data;
      T03AdminInsertCheck({ name, id }).then((res) => {
       
        console.log(res);
        this.$router.push({
          path: "/check/add/" + res.msg,
        });
      });
    },
    // 获取列表
    getList() {
      T03AdminGGoodsCheckList(this.queryParams).then((res) => {
        this.list = res.rows;
      });
    },
    // 数据处理
    data_fn(list) {
      let arr = [];
      for (const item of list) {
        let obj = {};
        obj.text = item.name;
        obj.goodsTypeId = item.id;
        arr.push(obj);
      }
      return arr;
    },
    // 新增
    handleAdd() {
      this.show = true;
    },
    // 手机号搜索
    onSearch(val) {
      console.log(val);
      this.queryParams.code = val;
      this.getList();
    },
    // 时间
    onConfirmTime(date) {
      this.valueCalendar = formatDate(date, "yyyy-MM");
      this.queryParams.beginTimes = formatDate(date, "yyyy-MM");
      this.showCalendar = false;
      this.getList();
    },
    onClearTime(date) {
      this.valueCalendar = "";
      this.queryParams.beginTimes = undefined;
      this.showCalendar = false;
      this.getList();
    },
    // 资料按钮
    handleCliCkmaterial() {
      this.$router.push("/material");
    },
    handClick(row) {
      let id = row.id;
      if (row.status == 1) {
        this.$router.push("/edit/" + id);
      } else {
        this.$router.push("/see/" + id);
      }
      // 编辑
      // 查看
    },
    onConfirm(value) {
      this.value = value.text;
      this.showPicker = false;
      // 拿到名字和id TODO: 跳新增
      console.log(value);
      this.data = value;
    },
  },
};
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
</style>

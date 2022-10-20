<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      buttonShow
      @handleClick="show = true"
      buttonText="新增"
    ></Header>
    <div class="app-container">
      <!-- <div style="margin-top: 20px">我的培训</div> -->
      <!-- <div>
        <div>状态</div>
        <div>时间</div>
      </div> -->
      <van-row style="margin-top: 20px">
        <!-- <van-col span="12"
          ><div class="my-underline" @click="openDate = true">
            定检日期
          </div></van-col
        > -->
         <van-col span="12">
          <van-field
            is-link
            arrow-direction="down"
            label="定检日期："
            v-model="query.inspectDate"
            label-width="auto"
            placeholder="请选择"
            @click="openDate = true"
          />
        </van-col>
        <!-- <van-col span="12"
          ><div class="my-underline" @click="open = true">状态</div></van-col
        > -->
        <van-col span="12">
          <van-field
            is-link
            arrow-direction="down"
            label="状态："
            v-model="status_name"
            label-width="auto"
            placeholder="请选择"
            @click="open = true"
          />
        </van-col>
      </van-row>
      <div
        class="box"
        v-for="(item, index) in dataList"
        @click="handleClick(item.id, item.status)"
        :key="index"
      >
        <van-cell
          :title="item.type == 1 ? '主车定检' : '挂车定检'"
          :border="false"
          :value="item.status | status"
          title-style="font-weight:600"
          title-class="left-title"
        />
        <div style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem">
          <div class="text">车牌号：{{ item.licensePlate }}</div>
          <div class="text">定检日期： {{ item.createTime }}</div>
        </div>
      </div>
    </div>
    <van-action-sheet
      v-model="open"
      :actions="actions"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect"
    />
    <van-action-sheet v-model="openDate">
      <van-datetime-picker
        v-model="currentDate"
        @confirm="onConfirm"
        type="year-month"
        title="选择年月"
        @cancel="openDate = false"
        :min-date="minDate"
        :max-date="maxDate"
      />
    </van-action-sheet>
    <van-action-sheet v-model="show" :actions="orActions" @select="onSelect" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import { vehicleInspectI01List } from "@/api/carRegularInspection/index";
import { formatDate } from "@/components/index";
export default {
  filters: {
    status: function (value) {
      switch (value) {
        case 1:
          return "待审批";
          break;
        case 2:
          return "已通过";
          break;
        case 3:
          return "已驳回";
          break;
        // case 4:
        //   return "已撤回";
        //   break;
        default:
          break;
      }
    },
  },
  components: {
    Header,
  },
  mounted() {
    let now = formatDate(new Date(), "yyyy");
    this.minDate = new Date(now - 4, 0, 1);
    this.maxDate = new Date(now+4, 11, 1);
    this.vehicleInspectI01DataList();
  },
  data() {
    return {
      show: false,
      open: false,
      openDate: false,
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      currentDate: new Date(),
      dataList: [],
      query: {
        inspectDate: undefined,
        status: undefined,
      },
      orActions: [{ name: "主车定检" }, { name: "挂车定检" }],
      actions: [
        {
          name: "待审批",
          value: 1,
        },
        {
          name: "已通过",
          value: 2,
        },
        {
          name: "已驳回",
          value: 3,
        },
      ],
    };
  },
  methods: {
    handleClick(id, status) {
      console.log(id);
      this.$router.push({
        path: "/detailCarRegularInspection/" + id,
        query: {
          status,
        },
      });
    },
    vehicleInspectI01DataList() {
      vehicleInspectI01List(this.query).then((res) => {
        this.dataList = res.rows;
      });
    },
    //新增
    // addClick() {
    //   console.log("新增");
    // },
    //   下拉取消
    onCancel() {
      console.log(this.actions);
      Toast("取消");
    },
    // 选择下拉
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.open = this.show = false;
      switch (item.name) {
        case "主车定检":
          this.$router.push("/mainVehicleAdd");
          return;
        case "挂车定检":
          this.$router.push("/trailerAdd");
          return;
        default:
          break;
      }
      this.query.status = item.value;
      this.status_name = item.name;
      this.vehicleInspectI01DataList();
      Toast(item.name);
    },
    // formatDate(date) {
    //   return `${date.getMonth() + 1}/${date.getDate()}`;
    // },
    onConfirm(date) {
      this.openDate = false;

      this.query.inspectDate = formatDate(date, "yyyy-MM");
      this.vehicleInspectI01DataList();
      // let obj = {
      //   vactionTime: formatDate(this.currentDate, "yyyy-MM"),
      // };
      // this.salaryG01dataList(obj);
    },
  },
};
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
// .box {
//   width: 90%;
//   border: 1px solid #ccc;
//   margin: auto;
//   margin-top: 0.5rem;
// }
</style>
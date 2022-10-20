<template>
  <div>
    <!-- <Header :show="true" :showRight='false'>></Header> -->
    <Header
      :show="true"
      :showRight="false"
      buttonShow
      @handleClick="addClick"
      buttonText="我要请假"
    ></Header>
    <div class="app-container">
      <!-- <div style="margin-top: 20px">我的培训</div> -->
      <!-- <div>
        <div>状态</div>
        <div>时间</div>
      </div> -->
      <van-row style="margin-top: 20px">
        <!-- <van-col span="7"
          ><div @click="open = true">
            审批状态<van-icon name="arrow-down" />
          </div></van-col
        >
          <van-col span="5"
          ><div style="line-height: 0.35rem">
            {{queryParams.name}}
          </div></van-col> -->
        <van-col span="12">
          <van-field
            is-link
            arrow-direction="down"
            label="审批状态："
            readonly
            v-model="queryParams.name"
            label-width="auto"
            placeholder="请选择"
            @click="open = true"
          />
        </van-col>
        <!-- <van-col span="12"><div @click="openDate = true">时间</div></van-col> -->
      </van-row>
      <div

      >
        <div
          class="box"
          v-for="item in dataList"
          :key="item.id"
          @click="handleClick(item)"
        >
          <van-cell
            :title="item.driverName + '提交的请假'"
            :border="false"
            :value="item.createTime"
          />
          <div
            style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
          >
            <div class="text">请假类型：{{ item.typeEnum }}</div>
            <div class="text">开始时间：{{ item.vStartTime }}</div>
            <div class="text">
              结束时间：{{ item.vEndTime }}
              <span
                style="
                  float: right;
                  margin-right: 16px;
                  font-size: 0.3rem;
                  margin-top: -10px;
                "
                >{{ item.status | status }}</span
              >
            </div>
            <!-- <div class="text">发起人： 杨宁</div> -->
          </div>
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
    <van-calendar v-model="openDate" @confirm="onConfirm" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { vacationE01VacationList } from "@/api/leave";
import { Toast } from "vant";
import { InfiniteScroll } from "mint-ui";
import Vue from "vue";
Vue.use(InfiniteScroll);
export default {
  components: {
    Header,
  },
  filters: {
    type: function (value) {
      switch (value) {
        case 1:
          return "事假";
          break;
        case 2:
          return "病假";
          break;
        default:
          break;
      }
    },
    status: function (value) {
      switch (value) {
        case 1:
          return "待审批";
          break;
        case 2:
          return "已通过";
          break;
        case 3:
          return "已拒绝";
          break;
        case 4:
          return "已撤回";
          break;
        default:
          break;
      }
    },
  },
  data() {
    return {
      open: false,
      openDate: false,
      date: undefined,
      loading: false,
      actions: [
        { label: null, name: "全部" },
        { label: "1", name: "待审批" },
        { label: "2", name: "已通过" },
        { label: "3", name: "已拒绝" },
        { label: "4", name: "已撤回" },
      ],
      dataList: [],
      queryParams: {
        // pageSize:10,
        // pageNum:0,
        status: undefined,
        name: "全部",
      },
    };
  },
  mounted() {
      this.getList();
  },
  methods: {

    getList() {
      vacationE01VacationList(this.queryParams).then((res) => {
        this.dataList = res.rows;
      });
    },
    //   点击
    handleClick(row) {
      this.$router.push("/leaveDetail/" + row.id);
    },
    //   我要请假
    addClick() {
      this.$router.push("/leaveAdd");
      console.log("请假");
    },
    //   下拉取消
    onCancel() {
      console.log(this.actions);
      Toast("取消");
    },
    // 选择下拉
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.open = false;
      this.queryParams.status = item.label;
      this.queryParams.name = item.name;
      // this.queryParams.pageNum = 1
      Toast(item.name);
      this.is_down = false
      this.getList();
    },
    formatDate(date) {
      return `${date.getMonth() + 1}/${date.getDate()}`;
    },
    onConfirm(date) {
      this.openDate = false;
      console.log(date);
      this.date = this.formatDate(date);
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
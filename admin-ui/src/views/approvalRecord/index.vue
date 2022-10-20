<template>
  <div>
    <!-- <Header :show="true" :showRight='false'>></Header> -->
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <!-- <div style="margin-top: 20px">我的培训</div> -->
      <!-- <div>
        <div>状态</div>
        <div>时间</div>
      </div> -->
      <van-row style="margin-top: 20px">
        <van-col span="12"
          ><div @click="open1 = true" class="my-underline">
            申请类型
          </div></van-col
        >
        <van-col span="12"
          ><div @click="open = true" class="my-underline">
            审批状态
          </div></van-col
        >
      </van-row>

      <div
        v-for="item in dataList"
        :key="item.id"
        @click="handleClick(item)"
      >
      <div class="box" v-if="item.type!=10">
        <van-row>
          <van-col :span="18">
            <van-cell
              :title="item.typeEnum"
              :border="false"
              label-width="auto"
              title-class="left-title"
            />
          </van-col>
          <van-col
            :span="6"
            style="line-height: 1rem; font-size: 0.3rem; font-weight: 600"
          >
            {{ item.status | status }}
          </van-col>
        </van-row>
        <div style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem">
          <div class="text">申请人：{{ item.createBy }}</div>
          <div class="text">申请时间：{{ item.createTime }}</div>
          <!-- <div class="text">
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
          </div> -->
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
      @select="onSelect($event, 'status')"
    />
    <van-action-sheet
      v-model="open1"
      :actions="actions1"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect($event, 'type')"
    />
    <van-calendar v-model="openDate" @confirm="onConfirm" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { taskQ01ExamineList,R01SendDuplicateCount } from "@/api/approvalRecord";
import { Toast } from "vant";
import {
  Q01ReadStatus
} from "@/api/index";
export default {
  components: {
    Header,
  },
  filters: {
    type: function (value) {
      switch (value) {
        case 1:
          return "事假";
        case 2:
          return "病假";
        default:
          break;
      }
    },
    status: function (value) {
      switch (value) {
        // case 1:
        //   return "待审批";
        case 2:
          return "已通过";
        case 3:
          return "已拒绝";
        // case 4:
        //   return "已撤回";
        default:
          break;
      }
    },
  },
  data() {
    return {
      open: false,
      open1: false,
      openDate: false,
      date: undefined,
      actions: [
        //   { name: "选项一" },
        //   { name: "选项二" },
        //   { 2已通过 3已驳回 }
        { label: null, name: "全部" },
        { label: 2, name: "已通过" },
        { label: 3, name: "已拒绝" },
      ],
      /*
      请假审批 2
      补助审批 1
      维修保养驾驶员审批 7
      维修保养维修站审批 8
      车辆定检审批 13
      运输任务 9
      薪酬 10
      交通事故上报审批 4
      物品领用审批 12
      招聘计划审批 6
      车辆保险审批 11
       */
      actions1: [
        { label: null, name: "全部" },
        { name: "请假审批", label: 2 },
        { name: "补助审批", label: 1 },
        { name: "维修保养驾驶员审批", label: 7 },
        { name: "维修保养维修站审批", label: 8 },
        { name: "车辆定检审批", label: 13 },
        { name: "运输任务", label: 9 },
        // { name: "薪酬", label: 10 },
        { name: "交通事故上报审批", label: 4 },
        { name: "物品领用审批", label: 12 },
        { name: "招聘计划审批", label: 6 },
        { name: "车辆保险审批", label: 11 },
      ],
      dataList: [],
      queryParams: {
        type: undefined,
        status: undefined,
      },
    };
  },
  mounted() {
    setTimeout(() => {
      this.getList();
    }, 300);
  },
  methods: {
    getList() {
      taskQ01ExamineList(this.queryParams).then((res) => {
        console.log(res);
        this.dataList = res.rows;
      });
    },
    //   点击
    handleClick(row) {
      console.log(row.type);
      Q01ReadStatus({id:row.id}).then(res=>{

      switch (row.type) {
        case 1:
          this.$router.push("/approvalRecord/subsidyList/" + row.relationId);
          break;
        case 2:
          this.$router.push("/approvalRecord/leave/" + row.relationId);
          break;
        case 7:
        case 8:
          this.$router.push({path:"/approvalRecord/adopt/" + row.relationId,query:{type:row.type}});
          break;
        case 4:
          this.$router.push("/approvalRecord/accident/" + row.relationId);
          break;
        case 6:
          this.$router.push(
            "/approvalRecord/recruitmentPlanExamine/" + row.relationId
          );
          break;
        case 9:
          this.$router.push(
            "/approvalRecord/abnormalSituation/" + row.relationId
          );
          break;
        case 10:
          this.$router.push(
            "/approvalRecord/approved/" + row.relationId
          );
          break;
          // 车辆
            case 11:
          this.$router.push(
            "/approvalRecord/insurance/" + row.relationId
          );
          break;
        case 12:
          this.$router.push(
            "/approvalRecord/collectionManagement/" + row.relationId
          );
          break;
           case 13:
          this.$router.push(
            "/approvalRecord/carRegularInspection/" + row.relationId
          );
          break;
        default:
          break;
      }
      })
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
    onSelect(item, name) {
      console.log(item);
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.queryParams[name] = item.label;
      this.open = false;
      console.log(this.queryParams);
      Toast(item.name);
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
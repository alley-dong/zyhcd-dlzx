<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      buttonShow
      @handleClick="addClick"
      buttonText="发起申请"
    ></Header>
    <div class="app-container">
      <!-- <div style="margin-top: 20px">我的培训</div> -->
      <!-- <div>
        <div>状态</div>
        <div>时间</div>
      </div> -->
      <van-row style="margin-top: 20px">
         <van-col span="12">
          <van-field
            is-link
            readonly
            arrow-direction="down"
            label="维保类型："
            v-model="repairStatus_name"
            label-width="auto"
            placeholder="请选择"
            @click="open = true"
          />
          <!-- <div @click="open = true">状态</div>-->
        </van-col>
         <van-col span="12">
          <van-field
            is-link
            readonly
            arrow-direction="down"
            label="状态："
            v-model="status_name"
            label-width="auto"
            placeholder="请选择"
            @click="openState = true"
          />
          <!-- <div @click="open = true">状态</div>-->
        </van-col>
       <van-col span="12">
          <van-field
            is-link
            readonly
            arrow-direction="down"
            label="维修状态："
            v-model="repairType_name"
            label-width="auto"
            placeholder="请选择"
            @click="openRepair = true"
          />
          <!-- <div @click="open = true">状态</div>-->
        </van-col>
        <!-- <van-col span="4"><div @click="open = true">状态</div></van-col>
        <van-col span="1"><van-icon name="arrow-down" /> </van-col>
        <van-col span="3">{{ status_name }} </van-col> -->
        <!-- <van-col span="8"
          ><div  @click="openRepair = true">
            维修状态
          </div></van-col
        > -->
        <!-- <van-col span="4"><div @click="open = true">维修状态</div></van-col>
        <van-col span="1"><van-icon name="arrow-down" /> </van-col>
        <van-col span="3">{{ repairStatus_name }} </van-col> -->
      </van-row>
      <div
        class="box"
        v-for="item in dataList"
        :key="item.id"
        @click="handleClick(item.id, item.status, item.repairStatus)"
      >
        <van-cell
          :title="item.type | type"
          title-style="font-weight:600"
          title-class="left-title"
          :border="false"
          :value="item.status | status"
        />
        <div style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem">
          <div class="text">维保类型：{{ item.repairType | repairType }}</div>
          <div class="text">主车车牌号： {{ item.mainLicensePlate }}</div>
          <div class="text">挂车车牌号： {{ item.hangLicensePlate }}</div>
          <div class="text">提交时间： {{ item.createTime }}</div>
          <div class="text">维修单号： {{ item.code }}</div>
        </div>
      </div>
    </div>
    <van-action-sheet
      v-model="open"
      :actions="actions"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect($event, 'repairType', 'repairStatus_name')"
    />
    <van-action-sheet
      v-model="openState"
      :actions="actionsState"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect($event, 'status', 'status_name')"
    />
    <van-action-sheet
      v-model="openRepair"
      :actions="actionsRepair"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect($event, 'repairStatus', 'repairType_name')"
    />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import { repairH01RepairList,repairH01Info } from "@/api/maintenance/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      open: false,
      openState: false,
      openRepair: false,
      actionsState: [
        { name: "全部", value: null },
        { name: "待审批", value: 1 },
        { name: "已通过", value: 2 },
        { name: "已拒绝", value: 3 },
        { name: "已确认", value: 4 },
      ],
      actions: [
        { name: "全部", value: null },
        { name: "正常维修", value: 1 },
        { name: "事故维修", value: 2 },
        { name: "保养", value: 3 },
      ],
      actionsRepair: [
        { name: "全部", value: null },
        { name: "未开始", value: 1 },
        { name: "进行中", value: 2 },
        { name: "已完成", value: 3 },
      ],
      dataList: [],
      repairType_name: "全部",
      status_name: "全部",
      repairStatus_name: "全部",
      form: {
        repairType: undefined,
        status: undefined,
        repairStatus: undefined,
      },
    };
  },
  filters: {
    type: function (value) {
      switch (value) {
        case 1:
          return "主车维保";
          break;
        case 2:
          return "挂车维保";
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
          return "已确认";
          break;
        // case 5:
        //   return "已撤销";
        //   break;

        default:
          break;
      }
    },
    repairType: function (value) {
      switch (value) {
        case 1:
          return "正常维修";
          break;
        case 2:
          return "事故维修";
          break;
        case 3:
          return "保养";
          break;

        default:
          break;
      }
    },
  },
  mounted() {
    this.getList();
  },
  methods: {
    handleClick(id, status, repairStatus) {


        repairH01Info({ id: id }).then((res) => {
            console.log("test123456",res);
            let check = res.data.status;
            status = check;
            let repair = res.data.repairStatus;
            repairStatus = repair;
            if (check == 4) {
                this.$router.push("/maintenanceDetail/" + id);
            }else{
                this.$router.push({
                    path: "/maintenanceAdopt/" + id,
                    query: { status, repairStatus },
                });
            }
        });
    },
    // 新增
    addClick() {
      this.$router.push("/maintenanceAdd");
    },
    // 获取列表
    getList() {
      repairH01RepairList(this.form).then((res) => {
        console.log(res);
        this.dataList = res.rows;
      });
    },
    //   下拉取消
    onCancel() {
      console.log(this.actions);
      Toast("取消");
    },
    // 选择下拉
    onSelect(item, name, name1) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.open = false;
      this.form[name] = item.value;
      this[name1] = item.name;
      this.getList();
      Toast(item.name);
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
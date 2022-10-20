<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container leave">
      <!-- 运输任务 -->
      <van-cell-group>
        <div class="top-text">任务信息：</div>
        <van-cell
          title="申请人："
          :value="form.driverName"
          title-class="left-title"
        />
        <van-cell
          title="异常类型："
          :value="form.typeEnum"
          title-class="left-title"
        />
        <van-cell
          title="运  单  号："
          :value="form.shipmentXid"
          title-class="left-title"
        />
        <van-cell
          title="任务线路："
          :value="form.routeName"
          title-class="left-title"
        />
        <van-cell
          title="异常情况描述："
          :value="form.remarks"
          title-class="left-title"
        />
        <!-- <van-cell
          title="图片："
          :value="form.consignee"
          title-class="left-title"
        /> -->
          <div class="upload-img">
          <div class="text">上传图片：</div>
          <div v-if="form.taskExceptionFileList && form.taskExceptionFileList !== ''">
            <van-image
              v-for="(item, index) in form.taskExceptionFileList"
              :key="index"
              @click="handleClick(form.taskExceptionFileList)"
              width="100"
              height="100"
              :src="item"
            />
          </div>
        </div>
        <van-cell
          title="申请时间："
          :value="form.createTime"
          title-class="left-title"
        />
    <!--     <van-cell
          title="主车车牌号："
          :value="form.mainLicensePlate"
          title-class="left-title"
        />
        <van-cell
          title="主车车型："
          :value="form.mainVehicleModelName"
          title-class="left-title"
        />
        <van-cell
          title="挂车车牌号："
          :value="form.hangLicensePlate"
          title-class="left-title"
        />
        <van-cell
          title="挂车车型："
          :value="form.hangVehicleModelName"
          title-class="left-title"
        />
        <van-cell
          title="司机姓名："
          :value="form.driverName"
          title-class="left-title"
        />
        <van-cell
          title="副司机姓名："
          :value="form.viceDriverName"
          title-class="left-title"
        />
        <van-cell
          title="计划提货时间："
          :value="form.pickTime"
          title-class="left-title"
        />
        <van-cell
          title="计划送达时间："
          :value="form.planDeliveryTime"
          title-class="left-title"
        />
        <van-cell
          title="总数量："
          :value="form.totalCount"
          title-class="left-title"
        />
        <van-cell
          title="总重量："
          :value="form.totalWeight"
          title-class="left-title"
        />
        <van-cell
          title="总体积："
          :value="form.totalVolume"
          title-class="left-title"
        />
        <van-cell
          title="总包装数量："
          :value="form.totalPackCount"
          title-class="left-title"
        /> -->
        <!-- <div class="upload-img">
          <div class="text">上传图片：</div>
          <div v-if="form.fileList && form.fileList !== ''">
            <van-image
              v-for="(item, index) in form.fileList.split(',')"
              :key="index"
              @click="handleClick(form.fileList)"
              width="100"
              height="100"
              :src="form.fileList"
            />
          </div>
        </div> -->
        <!-- <van-cell
          title="备注"
          value="描述信息描述信息描述信息描述信息描述信息描述信息"
        /> -->
        <!-- <div class="upload-img">
          <div class="text">审批人：</div>
          <div style="float: right; flex: 1; text-align: right">
            {{ form.reviewUser }}
            <van-tag type="primary" style="margin-left: 0.3rem">{{
              form.statusEnum
            }}</van-tag>
          </div>
        </div>
        <van-cell
          title="申请时间："
          title-class="left-title"
          :value="form.vEndTime"
        />
        <van-cell
          title="抄送人"
          :value="form.remarks"
          title-class="left-title"
        />
        <van-cell title="备注" :value="form.remarks" title-class="left-title" /> -->
      </van-cell-group>
      <!-- <van-cell-group>
        <div class="top-text">订单信息</div>
        <van-cell
          title="订单编号："
          :value="form.code"
          title-class="left-title"
        />
        <van-cell
          title="业务类型："
          :value="form.type"
          title-class="left-title"
        />
        <van-cell
          title="零件编号："
          :value="form.repairType"
          title-class="left-title"
        />
        <van-cell
          title="零件名称："
          :value="form.isOther"
          title-class="left-title"
        />
        <van-cell
          title="零件类型："
          :value="form.isOther"
          title-class="left-title"
        />
        <van-cell
          title="零件数量："
          :value="form.isOther"
          title-class="left-title"
        />
      </van-cell-group> -->
    </div>
    <div style="background:#fff">
        <div class="top-text">审批信息</div>
      <div
        v-for="item in tableData2"
        :key="item.id"
        style="border-bottom: 0.05rem solid"
      >
        <!-- <van-cell
          title="审批级别："
          :value="item.level"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批时间："
          :value="item.reviewTime"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批角色："
          :value="item.roleName"
          title-class="title-class"
          value-class="value-class"
        /> -->
        <van-cell
          title="审批人姓名："
          :value="item.userName"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批状态："
          :value="item.statusEnum"
          title-class="title-class"
          value-class="value-class"
        />
        <!-- <van-cell
          title="备注："
          :value="item.reviewRemarks"
          title-class="title-class"
          value-class="value-class"
        /> -->
      </div>
      <div v-for="item in tableData1" :key="item.id">
         <van-cell
          title="抄送人姓名："
          :value="item.userName"
          title-class="title-class"
          value-class="value-class"
        />
      </div>
    </div>
    <!-- <van-button
      v-if="form.status == 1"
      round
      type="info"
      @click="toClick"
      style="margin-bottom: 1rem; width: 50%"
      >撤销</van-button
    > -->
      <van-field v-model="text" label="备注" placeholder="请输入备注" />
    <van-row style="background: #fff; padding: 1rem 0">
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(1)"
          >同意</van-button
        ></van-col
      >
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(2)"
          >拒绝</van-button
        ></van-col
      >
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { ImagePreview } from "vant";
import { P08TransportTaskExceptionInfo } from "@/api/approvalRecord";
import { sysExamineTaskList } from "@/api/com";
import { P07TransportTaskExceptionCheck } from "@/api/myAgent";
import { Toast } from "vant";
export default {
  data() {
    return {
      text: undefined,
      form: {
        leaveType: "请选择请假类型",
        type: undefined,
        vStartTime: "请选择开始时间",
        vEndTime: "请选择结束时间",
        duration: "由开始结束时间计算得",
        remarks: "由开始结束时间计算得",
        fileList: [],
        fileUrls: undefined,
      },
        tableData2: [],
        tableData1:[]
    };
  },
  filters: {
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
        status: function (value) {
      switch (value) {
        case 1:
          return "待审批";
          break;
        case 2:
          return "已拒绝";
          break;
        case 3:
          return "已通过";
          break;
        case 4:
          return "已确认";
          break;
        case 5:
          return "已撤销";
          break;

        default:
          break;
      }
    },
  },
  methods: {
    // toClick() {
    //   vacationE03UndoVacation({ id: this.$route.params.id }).then((res) => {
    //     console.log(res);
    //     this.$router.go(-1);
    //   });
    // },
    handleAddClick(status) {
      P07TransportTaskExceptionCheck({
        id: this.$route.params.id,
        reviewRemarks: this.text,
        status,
      }).then((res) => {
        if(status == 1){
          Toast("审批通过");
        } else {
          Toast("审批拒绝");
        }
        this.$router.go(-1);
      });
    },
    handleClick(url) {
      ImagePreview([url]);
    },
  },
  mounted() {
    P08TransportTaskExceptionInfo({ id: this.$route.params.id }).then(
      (res) => {
        console.log(res);
        this.form = res.data;
      }
    );
    sysExamineTaskList({ relationId: this.$route.params.id }).then((res) => {
      console.log(res.data.sysExamineUserTaskList);
      this.tableData2 = res.data.sysExamineUserTaskList;
      this.tableData1 = res.data.sysDuplicateUserTaskList;
    });
  },
  components: {
    Header,
  },
};
</script>

<style lang="scss" scoped>
.upload-img {
  display: flex;
  text-align: left;
  padding: 0.2rem 0.32rem;
  border-bottom: 0.02rem solid #dcdcdc;
  .text {
    // width: 10em;
    width: 8.7em;
    font-size: 0.28rem;
    margin-right: 0.24rem;
  }
}
.remarks-class {
  text-align: left;
}
.left-title {
  text-align: left;
}
.title-class {
  text-align: left;
}
.top-text {
  font-weight: 600;
  text-align: left;
  height: 1rem;
  line-height: 1.3rem;
  padding-left: 0.5rem;
}
</style>
<style lang="scss" >
.leave {
  .van-cell {
    border-bottom: 0.02rem solid #dcdcdc;
  }
}
</style>
<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container leave">
      <!-- 车辆保险 -->
      <van-cell-group>
        <div class="top-text">保险信息：</div>
        <van-cell
          title="保险类型："
          :value="formData.type | type"
          title-class="left-title"
        />
        <van-cell
          title="车牌号"
          :value="formData.licensePlate"
          title-class="left-title"
        />
        <van-cell
          title="备注："
          :value="formData.remarks"
          title-class="left-title"
        />
        <!-- <div v-for="item in tableData" :key="item.id">
          <van-cell
            title="保险类型："
            :value="item.driverName"
            title-class="left-title"
          />
        </div> -->
      </van-cell-group>
      <van-cell-group>
        <div class="top-text">保险项目：</div>
        <div v-for="item in formData.cInsuranceVehicleItems" :key="item.id">
          <van-cell
            title="险种"
            :value="item.insuranceItemName"
            title-class="left-title"
          />
          <van-cell
            title="险种类型"
            :value="item.insuranceTypeName"
            title-class="left-title"
          />
          <van-cell
            title="保额"
            :value="item.insuranceQuota"
            title-class="left-title"
          />
          <van-cell
            title="保费"
            :value="item.insuranceCost"
            title-class="left-title"
          />
        </div>
        <!-- <div v-for="item in tableData" :key="item.id">
          <van-cell
            title="保险类型："
            :value="item.driverName"
            title-class="left-title"
          />
        
          <van-cell
            title="备注："
            :value="item.remarks"
            title-class="left-title"
          />
        </div> -->
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
    <div style="background: #fff">
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

      <!-- <div class="repair" style="display: flex; margin-top: 0.5rem">
          <div class="repair-text">审批人：</div>
          <div style="font-size: 0.25rem">
            <div>
              维保认定员 <span style="margin-left: 0.5rem">{{ name }}</span>
            </div>
            <div style="margin-top: 0.4rem">
              维保认定员 <span style="margin-left: 0.5rem">{{ name }}</span>
            </div>
            <div style="margin-top: 0.4rem">
              维保认定员 <span style="margin-left: 0.5rem">{{ name }}</span>
            </div>
          </div>
        </div>
        <div class="repair" style="display: flex; margin-top: 0.5rem">
          <div class="repair-text">抄送人：</div>
          <div style="font-size: 0.25rem">
            <div>调度</div>
          </div>
        </div> -->
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
import { insuranceVehicleE02GetInfo } from "@/api/approvalRecord";
import { sysExamineTaskList } from "@/api/com";
import { insuranceVehicleListE03Check } from "@/api/myAgent";
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
      formData: {},
      tableData2: [],
      tableData: [],
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
    type: function (value) {
      console.log(value);
      // 保险类型 1新车保险 2车辆续保 3货物保险
      switch (value) {
        case 1:
          return "新车保险";
        case 2:
          return "车辆续保";
        case 3:
          return "货物保险";
       

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
      insuranceVehicleListE03Check({
        relationId: this.$route.params.id,
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
    insuranceVehicleE02GetInfo(this.$route.params.id).then((res) => {
      console.log(res);
      this.formData = res.data;
      // this.checkItemDataList = res.data.cInsuranceVehicleItems;
    });
    sysExamineTaskList({ relationId: this.$route.params.id }).then((res) => {
      console.log(res.data.sysExamineUserTaskList);
      this.tableData2 = res.data.sysExamineUserTaskList;
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
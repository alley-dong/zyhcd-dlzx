<template>
  <div>
    <!-- 物品领用 -->
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container leave">
      <div v-if="!tableData">
        <van-cell
          title="领用物品"
          :value="form.goodsName"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="规格"
          :value="form.specification"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="单位"
          :value="form.unit"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="车牌号"
          :value="form.licensePlate"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="线路"
          v-if="form.goodsTypeName == '尿素'"
          :value="form.routeName"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          v-if="form.goodsTypeName != '工作餐'"
          title="数量"
          :value="form.number"
          title-class="title-class"
          value-class="value-class"
        />
        <div class="my-style">
        <van-row style="background: #fff">
          <van-col :span="11">
            <van-cell
              title="领用日期"
              title-class="title-class"
              value-class="value-class"
          /></van-col>
          <van-col v-if="form.goodsTypeName == '工作餐'" :span="18">
            <van-cell
              v-for="(item, index) in form.gGoodsReceivingRecordDates"
              :key="index"
              :value="item.receiveDate"
              title-class="title-class"
              value-class="value-class"
              input-align="right"
          /></van-col>
          <van-col :span="13">
            <van-cell
              :value="form.receiveTime"
              title-class="title-class"
              value-class="value-class"
              input-align="right"
            />
          </van-col>
        </van-row>
        </div>
        <van-cell
          title="领用人"
          :value="form.createBy"
          title-class="title-class"
          value-class="value-class"
        />
        <!-- <van-cell
          title="领用数量"
          v-if="form.goodsTypeName == '工作餐'"
          :value="form.iNumber"
          title-class="title-class"
          value-class="value-class"
        /> -->
        <van-cell
          title="申请原因"
          v-if="form.goodsTypeName == '工作餐'"
          :value="form.remarks"
          title-class="title-class"
          value-class="value-class"
        />
        <!-- <van-cell
          title="领用原因"
          v-if="form.goodsTypeName != '工作餐' && form.goodsTypeName != '尿素'"
          :value="form.remarks"
          title-class="title-class"
          value-class="value-class"
        /> -->
        <!-- <van-cell
          title="审批人"
          :value="form.reviewUser"
          title-class="title-class"
          value-class="value-class"
        /> -->
      </div>
      <div v-if="tableData">
        <div v-for="item in tableData" :key="item.id">
          <van-cell
            title="物品"
            :value="item.goodsName"
            title-class="title-class"
            value-class="value-class"
          />
              <div class="my-style">
        <van-row style="background: #fff">
          <van-col :span="11">
            <van-cell
              title="领用日期"
              title-class="title-class"
              value-class="value-class"
          /></van-col>
          <van-col :span="13">
            <van-cell
              :value="form.receiveTime"
              title-class="title-class"
              value-class="value-class"
              input-align="right"
            />
          </van-col>
        </van-row>
        </div>
          <van-cell
            title="车牌号"
            :value="form.licensePlate"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="领用数量"
            :value="item.number"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="领用人"
            :value="form.createBy"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="零件用途"
            :value="form.type == 1 ? '维修' : '换装'"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="申请日期"
            :value="form.createTime"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="备注"
            :value="form.remarks"
            title-class="title-class"
            value-class="value-class"
          />
        </div>
      </div>
    </div>
    <!-- <div
      v-for="item in tableData2"
      :key="item.id"
      style="border-bottom: 0.05rem solid"
    >
      <van-cell
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
      />
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
      <van-cell
        title="备注："
        :value="item.reviewRemarks"
        title-class="title-class"
        value-class="value-class"
      />
    </div> -->
  </div>
</template>

<script>
import Header from "@/components/Header";
import { T01AdminGGoodsReceivingInfo } from "@/api/approvalRecord";
import { sysExamineTaskList } from "@/api/com";
import { ImagePreview } from "vant";
export default {
  data() {
    return {
      form: {},
      tableData2: [],
      tableData: undefined,
    };
  },
  filters: {
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
    handleClick(item) {
      ImagePreview([item]);
    },
  },
  mounted() {
    T01AdminGGoodsReceivingInfo({ id: this.$route.params.id }).then((res) => {
      console.log(res);
      this.form = res.data;
      if (res.data.goodsType == 5) {
        this.tableData = res.data.gGoodsReceivingRecordItems;
      }
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
.title-class {
  text-align: left;
}
.left-title {
  text-align: left;
}
.right {
  text-align: right;
}
.value-class{
  text-align: left;
}
</style>
<style lang="scss" >
.leave {
  .van-cell {
    border-bottom: 0.02rem solid #dcdcdc;
  }
  .van-field__control--custom {
    flex-wrap: wrap;
  }
}
</style>
<style lang="scss">
  .my-style{
    .van-cell__value--alone {
    color: #969799;
    // text-align: right;
}
  }
</style>
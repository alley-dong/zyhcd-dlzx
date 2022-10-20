<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container leave">
      <van-cell
        title="驾驶员"
        :value="form.driverName"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="车牌号"
        :value="form.vehicleName"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="事故地点"
        :value="form.accidentPlace"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="事故时间"
        :value="form.accidentTime"
        title-class="title-class"
        value-class="value-class"
      />
      <van-field name="uploader" label="事故照片">
        <template #input>
          <van-image
            v-for="(item, index) in form.accidentFileList"
            :key="index"
            width="100"
            height="100"
            @click="handleClick(item)"
            style="margin-right: 0.1rem"
            :src="item"
          />

        </template>
      </van-field>
      <van-field name="uploader" label="双方驾驶证（正本、副本）：">
        <template #input>
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.oneSideDriveLicenceCardJust)"
            :src="form.oneSideDriveLicenceCardJust"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.oneSideDriveLicenceCardAgainst)"
            :src="form.oneSideDriveLicenceCardAgainst"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.theOtherDriveLicenceCardJust)"
            :src="form.theOtherDriveLicenceCardJust"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.theOtherDriveLicenceCardAgainst)"
            :src="form.theOtherDriveLicenceCardAgainst"
            style="margin-right: 0.1rem"
          />
        </template>
      </van-field>
      <van-field name="uploader" label="双方行驶证（正本、副本）：">
        <template #input>
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.oneSideTravelLicenceCardJust)"
            :src="form.oneSideTravelLicenceCardJust"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.oneSideTravelLicenceCardAgainst)"
            :src="form.oneSideTravelLicenceCardAgainst"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.theOtherTravelLicenceCardJust)"
            :src="form.theOtherTravelLicenceCardJust"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.theOtherTravelLicenceCardAgainst)"
            :src="form.theOtherTravelLicenceCardAgainst"
            style="margin-right: 0.1rem"
          />
        </template>
      </van-field>
      <van-field name="uploader" label="驾驶员身份证（正面、背面）：">
        <template #input>
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.driverIdCardJust)"
            :src="form.driverIdCardJust"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.driverIdCardAgainst)"
            :src="form.driverIdCardAgainst"
            style="margin-right: 0.1rem"
          />
        </template>
      </van-field>
      <van-field name="uploader" label="车辆被保险人身份证（正面、背面）：">
        <template #input>
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.carTheInsuredIdCardJust)"
            :src="form.carTheInsuredIdCardJust"
            style="margin-right: 0.1rem"
          />
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.carTheInsuredIdCardAgainst)"
            :src="form.carTheInsuredIdCardAgainst"
            style="margin-right: 0.1rem"
          />
        </template>
      </van-field>
      <van-field name="uploader" label="事故责任认定书：">
        <template #input>
          <van-image
            width="100"
            height="100"
            @click="handleClick(form.liabilityStatement)"
            :src="form.liabilityStatement"
            style="margin-right: 0.1rem"
          />
        </template>
      </van-field>
            <van-field name="uploader" label="其他：">
        <template #input>
          <van-image
            v-for="(item, index) in form.partsFilesList"
            :key="index"
            width="100"
            height="100"
            @click="handleClick(item)"
            style="margin-right: 0.1rem"
            :src="item"
          />

        </template>
      </van-field>
    </div>
    <div
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
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { L03SafeAccidentInfo } from "@/api/approvalRecord";
import { sysExamineTaskList } from "@/api/com";
import { ImagePreview } from "vant";
export default {
  data() {
    return {
      form: {},
      tableData2: [],
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
    L03SafeAccidentInfo(this.$route.params.id).then((res) => {
      console.log(res);
      this.form = res.data;
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
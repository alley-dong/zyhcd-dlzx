<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <van-row type="flex" justify="center" style="margin-top: 60px">
      <van-col span="24">
        <van-field
          label="车牌号:"
          value=""
          readonly
          v-model="form.vehicleName"
        />
        <van-field
          label="驾驶员:"
          value=""
          readonly
          v-model="form.driverName"
        />
        <van-field
          label="事故发生时间:"
          value=""
          readonly
          label-width="auto"
          v-model="form.accidentTime"
        />
        <van-field
          label="事故地点:"
          value=""
          readonly
          v-model="form.accidentPlace"
        />
        <van-field label="事故照片:" value="" readonly />
        <van-image
          v-for="(item, index) in this.form.accidentFileList"
          :key="index"
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="item"
        />

        <van-field
          label="双方驾驶证（正本、副本）:"
          value=""
          readonly
          label-width="auto"
          v-if="form.oneSideDriveLicenceCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          v-if="form.oneSideDriveLicenceCardJust"
          :src="form.oneSideDriveLicenceCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.oneSideDriveLicenceCardAgainst"
          v-if="form.oneSideDriveLicenceCardAgainst"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.theOtherDriveLicenceCardJust"
          v-if="form.theOtherDriveLicenceCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.theOtherDriveLicenceCardAgainst"
          v-if="form.theOtherDriveLicenceCardAgainst"
        />
        <van-field
          label="双方行驶证（正本、副本）:"
          value=""
          readonly
          label-width="auto"
          v-if="form.oneSideTravelLicenceCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.oneSideTravelLicenceCardJust"
          v-if="form.oneSideTravelLicenceCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.oneSideTravelLicenceCardAgainst"
          v-if="form.oneSideTravelLicenceCardAgainst"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.theOtherTravelLicenceCardJust"
          v-if="form.theOtherTravelLicenceCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.theOtherTravelLicenceCardAgainst"
          v-if="form.theOtherTravelLicenceCardAgainst"
        />
        <van-field
          label="驾驶员身份证（正面、背面）:"
          value=""
          readonly
          label-width="auto"
          v-if="form.driverIdCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.driverIdCardJust"
          v-if="form.driverIdCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.driverIdCardAgainst"
          v-if="form.driverIdCardAgainst"
        />
        <van-field
          label="车辆被保险人身份证（正面、背面）:"
          value=""
          readonly
          label-width="auto"
          v-if="form.carTheInsuredIdCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.carTheInsuredIdCardJust"
          v-if="form.carTheInsuredIdCardJust"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.carTheInsuredIdCardAgainst"
          v-if="form.carTheInsuredIdCardAgainst"
        />
        <van-field
          label="事故责任认定书:"
          value=""
          readonly
          label-width="auto"
          v-if="form.liabilityStatement"
        />
        <van-image
          width="1.5rem"
          height="2rem"
          fit="contain"
          :src="form.liabilityStatement"
          v-if="form.liabilityStatement"
        />
        <van-field
          label="其他（对方保单、营业执照等）:"
          readonly
          label-width="auto"
          v-if="form.partsFilesList"
        />
        <van-image
                v-for="(item, index) in this.form.partsFilesList"
                :key="index"
                width="1.5rem"
                height="2rem"
                fit="contain"
                :src="item"
        />


        <van-field
          label="事故上报日期:"
          value=""
          readonly
          label-width="auto"
          v-model="form.createTime"
        />
        <van-field
          v-show="form.statusEnum === '已通过' || form.statusEnum === '已驳回'"
          label="备注:"
          value=""
          readonly
          v-model="form.reviewRemarks"
        />

        <div v-for="item in processList" :key="item.id">
          <van-field
                  label="审批状态:"
                  value=""
                  readonly
                  v-model="item.statusEnum"
          />
          <van-field
            label="审批人:"
            value=""
            readonly
            v-model="item.userName"
          />
          <van-field
            label="抄送人:"
            value=""
            readonly
            v-model="item.ccUserName"
          />
        </div>

        <van-button
          v-show="form.statusEnum === '已驳回'"
        round
      type="info"
      style="margin-bottom:1rem;width:50%"
          @click="handCom(form.id)"
        >
          再次提交
        </van-button>
      </van-col>
    </van-row>
  </div>
</template>

<script>
import Header from '@/components/Header'
import {
  J02SafeAccidentInfo,
  queryExamineConfigProcessList,
} from '@/api/reportTheAccident/incidentRecordDetails'
import {
  getSysExamineTaskList
} from "@/api/api";
export default {
  components: {
    Header,
  },
  data() {
    return {
      processList:[],
      form: {},
    }
  },
  mounted() {
    this.geJ02SafeAccidentInfo()
    this.getSysExamineTaskList()
  },
  methods: {
    // 详情列表
    geJ02SafeAccidentInfo() {
      let id = {
        id: this.$route.params.id,
      }
      J02SafeAccidentInfo(id).then((res) => {
        this.form = res.data
      })
    },
    //  
    getSysExamineTaskList() {
      getSysExamineTaskList({
        relationId: this.$route.params.id,
        loginType:1
    }).then((res) => {
        console.log(res)
        this.processList = res.data.sysExamineUserTaskList
        console.log("this.processList",this.processList)
      })
    },
    // 再次提交
    handCom(id) {
      this.$router.push('/reportAgain/' + id)
    },
  },
}
</script>

<style lang="scss" scoped>
.van-image {
  border: 1px solid #668b8b;
}
.text {
  margin-bottom: 0.2rem;
}
.box {
  width: 90%;
  border: 1px solid #ccc;
  margin: auto;
  margin-top: 0.5rem;
}
.preview-cover {
  position: absolute;
  bottom: 0;
  box-sizing: border-box;
  width: 100%;
  padding: 4px;
  color: #fff;
  font-size: 12px;
  text-align: center;
  background: rgba(0, 0, 0, 0.3);
}
</style>

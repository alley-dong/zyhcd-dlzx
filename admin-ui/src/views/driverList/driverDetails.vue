<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-image width="100" height="100" :src="form.driverPhoto" />
      <van-cell-group>
        <van-field readonly style="font-weight:600" label="基本信息" />
        <van-field
          readonly
          v-model="form.name"
          label="姓名:"
          label-width="auto"
        />
        <van-field
          readonly
          v-model="form.sex == 0 ? '男' : '女'"
          label="性别："
          label-width="auto"
        />
        <van-field
          readonly
          v-model="form.contact"
          label="手机号："
          label-width="auto"
        />
        <van-field
          readonly
          v-model="form.idCard"
          label="身份证"
          label-width="auto"
        />
        <van-row>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.birthday"
              label="出生日期:"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.nation"
              label="民族:"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.politicalAffiliation"
              label="政治面貌:"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.maritalStatus"
              label="婚姻状况:"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.education"
              label="学历:"
              label-width="auto"
          /></van-col>
        
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.drivingLicenseType"
              label="驾照类型:"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.driveAge"
              label="驾龄:"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.drivingLicenseTime"
              label="获取时间:"
              label-width="auto"
          /></van-col>
          <van-col :span="12">
            <van-field
              readonly
              v-model="form.dntryTime"
              label="入职时间:"
              label-width="auto"
          /></van-col>
        </van-row>
        <!-- todo -->
       
        <van-col :span="24">
          <van-field
            readonly
            v-model="form.address"
            label="家庭住址:"
            label-width="auto"
        /></van-col>
          <van-col :span="24">
          <van-field
            readonly
            v-model="form.staffType == 1 ? '正式工' : '劳务工'"
            label="员工类型:"
            label-width="auto"
        /></van-col>
          <van-col :span="24">
            <van-field
              readonly
              v-model="form.urgentTel"
              label="紧急联系人电话:"
              label-width="auto"
          /></van-col>
         <van-col :span="24">
          <van-field
            readonly
            v-model="form.qualification"
            label="从业资格证号:"
            label-width="auto"
        /></van-col>
        <van-col :span="24">
          <van-field
            readonly
            v-model="form.driverTypeName"
            label="驾驶员类型:"
            label-width="auto"
        /></van-col>
      
        <!-- TODO: -->
        <div>
          <van-col :span="24">
            <van-field style="font-weight:600" label="教育经历" label-width="auto"
          /></van-col>
          <div v-for="item in educationList" :key="item.id">
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.school"
                label="学校:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.major"
                label="专业:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.startTime + '-' + item.finshTime"
                label="时间:"
                label-width="auto"
            /></van-col>
          </div>
        </div>
        <div>
          <van-col :span="24">
            <van-field style="font-weight:600" label="驾驶经历" label-width="auto"
          /></van-col>
          <div v-for="item in A11DriverDriverDataList" :key="item.id">
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.workUnit"
                label="工作单位:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.position"
                label="职务及岗位:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.startTime"
                label="入职时间:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.finshTime"
                label="离职时间:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.route"
                label="驾驶路线:"
                label-width="auto"
            /></van-col>
          </div>
        </div>
        <div>
          <van-col :span="24">
            <van-field label="交通事故经历" style="font-weight:600" label-width="auto"
          /></van-col>
          <div v-for="item in trafficAccidentData" :key="item.id">
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.accidentTime"
                label="时间:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.place"
                label="地点:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.remarks"
                label="是否造成人员受伤:"
                label-width="auto"
            /></van-col>
          </div>
        </div>
        <div>
          <van-col :span="24">
            <van-field label="家庭状况" style="font-weight:600" label-width="auto"
          /></van-col>
          <div v-for="item in FamilyStatusDataList" :key="item.id">
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.relation"
                label="关系:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.name"
                label="姓名:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.tel"
                label="联系方式:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.position"
                label="职务及岗位:"
                label-width="auto"
            /></van-col>
            <van-col :span="24">
              <van-field
                readonly
                v-model="item.workUnit"
                label="工作单位:"
                label-width="auto"
            /></van-col>
          </div>
        </div>
      </van-cell-group>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  info,
  educationExperienceList,
  A11DriverDriverList,
  A11DriverAccidentList,
  A11DriverFamilyList,
} from "@/api/driverList/index";

export default {
  components: {
    Header,
  },
  data() {
    return {
      educationList: [],
      trafficAccidentData: [], //交通事故经历table
      A11DriverDriverDataList: [], //驾驶经历列表
      FamilyStatusDataList: [], //家庭经历table
      form: {
        status: "",
        name: "",
        sex: "",
        birthday: "",
        idCard: "",
        nation: "",
        politicalAffiliation: "",
        maritalStatus: "",
        education: "",
        contact: "",
        urgentTel: "",
        drivingLicenseType: "",
        drivingLicenseTime: "",
        driveAge: "",
        dntryTime: "",
        address: "",
        driverTypeId: "",
        performanceType: "",
        staffType: "",
        fileUrl: "",
        driveRemarks: "",
      },
    };
  },
  mounted() {
    this.getinfo();
    educationExperienceList(this.$route.params.id).then((res) => {
      this.educationList = res.data;
    });
    A11DriverDriverList(this.$route.params.id).then((res) => {
      console.log(res);
      this.A11DriverDriverDataList = res.data;
    });
    A11DriverAccidentList(this.$route.params.id).then((res) => {
      this.trafficAccidentData = res.data;
    });

    A11DriverFamilyList(this.$route.params.id).then((res) => {
      console.log(res);
      this.FamilyStatusDataList = res.data;
    });
  },
  methods: {
    getinfo() {
      info(this.$route.params.id).then((res) => {
        this.form = res.data;
      });
    },
  },
};
</script>

<style scoped>
.app-container {
  position: relative;
}
.van-image {
  position: absolute;
  top: 1.2rem;
  right: 0;
  left: 5.4rem;
  bottom: 0;
  z-index: 9999;
}
</style>

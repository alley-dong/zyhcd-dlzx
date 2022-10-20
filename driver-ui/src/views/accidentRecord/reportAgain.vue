<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row type="flex" justify="center">
        <van-col span="24">
          <van-field
            readonly
            clickable
            is-link
            arrow-direction
            :value="form.vehicleName"
            label="车牌号"
            placeholder="点击车牌号"
            @click="handleSearch"
          />
          <van-field
            label="驾驶员:"
            value=""
            readonly
            v-model="form.driverName"
          />
          <van-field
            readonly
            clickable
            name="datetimePicker"
            :value="form.accidentTime"
            label-width="auto"
            label="事故发生时间:"
            placeholder="点击选择时间"
            @click="showPickerTime = true"
          />
          <van-popup v-model="showPickerTime" position="bottom">
            <van-datetime-picker
              type="datetime"
              @confirm="onConfirmTime"
              @cancel="showPickerTime = false"
            />
          </van-popup>
          <van-field label="事故地点:" :value="form.accidentPlace">
            <template #right-icon>
              <van-icon name="location-o" @click="handClickLocation" />
            </template>
          </van-field>

          <van-field label="事故照片:" />
          <van-field>
            <template #input>
              <van-uploader
                v-model="accidentPhotosList"
                :after-read="accidentPhotos"
              />
            </template>
          </van-field>

          <van-field label="双方驾驶证（正本、副本）:" label-width="auto" />
          <van-field>
            <template #input>
              <van-uploader
                v-model="driversPhotosList"
                :after-read="driversPhotos"
                :max-count="4"
              />
            </template>
          </van-field>

          <van-field label="双方行驶证（正本、副本）:" label-width="auto" />
          <van-field>
            <template #input>
              <van-uploader
                v-model="driversPhotosList1"
                :after-read="driversPhotos1"
                :max-count="4"
              />
            </template>
          </van-field>
          <van-field label="驾驶员身份证（正面、背面）:" label-width="auto" />
          <van-field>
            <template #input>
              <van-uploader
                v-model="driverIdentityCardPhotosList"
                :after-read="driverIdentityCardPhotos"
                :max-count="2"
              />
            </template>
          </van-field>

          <van-field
            label="车辆被保险人身份证（正面、背面）:"
            label-width="auto"
          />
          <van-field>
            <template #input>
              <van-uploader
                v-model="recognizeePhotosList"
                :after-read="recognizeePhotos"
                :max-count="2"
              />
            </template>
          </van-field>

          <van-field label="事故责任认定书:" label-width="auto" />
          <van-field>
            <template #input>
              <van-uploader
                v-model="accidentLiabilityList"
                :after-read="accidentLiabilityPhotos"
                :max-count="1"
              />
            </template>
          </van-field>

          <van-field label="其他（对方保单、营业执照等）:" label-width="auto" />
          <van-field>
            <template #input>
              <van-uploader v-model="otherList" :after-read="otherPhotos" />
            </template>
          </van-field>

          <div v-for="(item,index) in processList" :key="item.id">
            <div v-if="processList[index].sysUserList != null">
              <van-field
                      clickable
                      is-link
                      arrow-direction="down"
                      :value="processList[index].userName"
                      label="选择审批人:"
                      placeholder="请选择"
                      label-width="auto"
                      @click="processList[index].spLevel = true"
              />
              <van-popup v-model="processList[index].spLevel" position="bottom">
                <van-picker
                        show-toolbar
                        value-key="nickName"
                        :columns="processList[index].sysUserList"
                        @confirm="onConfirmSpr"
                        @cancel="processList[index].spLevel = false"
                />
              </van-popup>
            </div>
            <van-cell v-if="processList[index].sysUserList == null"
                      :value="processList[index].userName"
                      title-class="left-title"
                      value-class="text-cell"
            >
              <template #title>
                <span>审批人： </span>
              </template>
            </van-cell>
            <van-cell v-if="processList[index].sysCcUserList == null"
                      :value="processList[index].ccUserName"
                      title-class="left-title"
                      value-class="text-cell"
            >
              <template #title>
                <span>抄送人： </span>
              </template>
            </van-cell>
            <div v-if="processList[index].sysCcUserList != null">
              <van-field
                      clickable
                      is-link
                      arrow-direction="down"
                      :value="processList[index].ccUserName"
                      label="选择抄送人:"
                      placeholder="请选择"
                      label-width="auto"
                      @click="processList[index].ccLevel = true"
              />
              <van-popup v-model="processList[index].ccLevel" position="bottom">
                <van-picker
                        show-toolbar
                        value-key="nickName"
                        :columns="processList[index].sysCcUserList"
                        @confirm="onConfirmCcr"
                        @cancel="processList[index].ccLevel = false"
                />
              </van-popup>
            </div>
          </div>

          <van-button
           round
      type="info"
      style="margin-bottom:1rem;width:50%"
            @click="handCom()"
          >
            提交
          </van-button>
        </van-col>
      </van-row>
    </div>
  </div>
</template>
<script>
import { formatDate } from '@/components/index'
import Header from '@/components/Header'
import Vue from 'vue'
import { DatetimePicker } from 'vant'
Vue.use(DatetimePicker)
import { J02SafeAccidentAgainReport } from '@/api/reportTheAccident/theAccidentReport'
import {J02SafeAccidentInfo} from '@/api/reportTheAccident/incidentRecordDetails'
import { upload } from '@/components/upload'
import { queryExamineConfigProcessList } from "@/api/api";
import { Toast } from "vant";

export default {
  components: {
    Header,
  },
  data() {
    return {
      processList: [],
      valueTime: '',
      carLicensePlate: [],
      // 车辆id
      carId: [],
      showPicker: false,
      showPickerTime: false,

      // 事故照片
      accidentPhotosList: [],
      accidentPhotosStatus: [],
      // 一方行驶证
      driversPhotosList: [],
      driversPhotosStatus: [],
      // 另一方
      driversPhotosList1: [],
      driversPhotosStatus1: [],
      // 驾驶员身份证
      driverIdentityCardPhotosList: [],
      driverIdentityCardPhotosStatus: [],
      // 车辆被保险人身份证（正面、背面）
      recognizeePhotosList: [],
      recognizeePhotosStatus: [],
      // 事故责任认定书
      accidentLiabilityList: [],
      accidentLiabilityStatus: [],
      // 其他照片
      otherList: [],
      otherStatus: [],
      form: {
        id: '',
        vehicleId: '',
        vehicleName: '',
        accidentTime: '',
        accidentPlace: '',
        longitude: '',
        latitude: '',
        oneSideDriveLicenceCardJust: '',
        oneSideDriveLicenceCardAgainst: '',
        oneSideTravelLicenceCardJust: '',
        oneSideTravelLicenceCardAgainst: '',
        theOtherDriveLicenceCardJust: '',
        theOtherDriveLicenceCardAgainst: '',
        theOtherTravelLicenceCardJust: '',
        theOtherTravelLicenceCardAgainst: '',
        driverIdCardJust: '',
        driverIdCardAgainst: '',
        carTheInsuredIdCardJust: '',
        carTheInsuredIdCardAgainst: '',
        liabilityStatement: '',
        accidentPhotos: [],
        partsPhotos: [],
      },
    }
  },
  created() {

    let id = {
      id: this.$route.params.id,
    }
    J02SafeAccidentInfo(id).then((res) => {

      res.data.accidentFileList.forEach(e=>{
        let obj = {
          url:e
        }
        this.accidentPhotosList.push(obj)
      })

      this.driversPhotosList.push({url:res.data.oneSideDriveLicenceCardJust})
      this.driversPhotosList.push({url:res.data.oneSideDriveLicenceCardAgainst})
      this.driversPhotosList.push({url:res.data.theOtherDriveLicenceCardJust})
      this.driversPhotosList.push({url:res.data.theOtherDriveLicenceCardAgainst})
      this.driversPhotosList1.push({url:res.data.oneSideTravelLicenceCardJust})
      this.driversPhotosList1.push({url:res.data.oneSideTravelLicenceCardAgainst})
      this.driversPhotosList1.push({url:res.data.theOtherTravelLicenceCardJust})
      this.driversPhotosList1.push({url:res.data.theOtherTravelLicenceCardAgainst})

      this.driverIdentityCardPhotosList.push({url:res.data.driverIdCardJust});
      this.driverIdentityCardPhotosList.push({url:res.data.driverIdCardAgainst});

      this.recognizeePhotosList.push({url:res.data.carTheInsuredIdCardJust});
      this.recognizeePhotosList.push({url:res.data.carTheInsuredIdCardAgainst});

      this.accidentLiabilityList.push({url:res.data.liabilityStatement});

      res.data.partsFilesList.forEach(e=>{
        let obj = {
          url:e
        }
        this.otherList.push(obj)
      })

      this.form = res.data
      this.form.id = this.$route.params.id;
      // 地图
      if(this.$store.state.map_Value.lng){
        this.form.accidentPlace = this.$store.state.map_Value.name;
        this.form.longitude = this.$store.state.map_Value.lng;
        this.form.latitude = this.$store.state.map_Value.lat;
      }
      // 车牌号
      if(this.$store.state.AccidentCar.licensePlate){
        this.form.vehicleName = this.$store.state.AccidentCar.licensePlate;
        this.form.vehicleId = this.$store.state.AccidentCar.id;
      }
    })

    // 审批人
    queryExamineConfigProcessList({
      type: 4,
      relationId:this.$route.params.id
    }).then((res) => {
      this.processList = res.rows;
      this.processList.forEach(res=>{
        this.$set(res,'spLevel',false)
        this.$set(res,'ccLevel',false)
      })
    });
  },
  mounted() {

  },
  methods: {
    // 车牌号搜索
    handleSearch() {
      this.$router.push('/theAccidentReportSearch')
    },
    // 事故照片
    accidentPhotos(file, detail) {
      this.accidentPhotosStatus[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.accidentPhotosList.length--
        let obj = {}
        obj.url = res
        this.accidentPhotosList.push(obj)
        this.accidentPhotosStatus[detail.index] = true
      })
    },
    // 双方行驶证照片
    driversPhotos(file, detail) {
      file.status = 'uploading';
      file.message = '上传中...';
      this.driversPhotosStatus[detail.index] = false

      upload(file, detail).then((res) => {
        this.driversPhotosList.length--
        let obj = {}
        obj.url = res
        this.driversPhotosList.push(obj)
        this.driversPhotosStatus[detail.index] = true
      })
    },
    // 另一方方行驶证照片
    driversPhotos1(file, detail) {
      file.status = 'uploading';
      file.message = '上传中...';
      this.driversPhotosStatus1[detail.index] = false
      upload(file, detail).then((res) => {
        this.driversPhotosList1.length--
        let obj = {}
        obj.url = res
        this.driversPhotosList1.push(obj)
        this.driversPhotosStatus1[detail.index] = true
      })
    },
    // 驾驶员身份证（正面、背面）
    driverIdentityCardPhotos(file, detail) {
      file.status = 'uploading';
      file.message = '上传中...';
      this.driverIdentityCardPhotosStatus[detail.index] = false
      upload(file, detail).then((res) => {
        this.driverIdentityCardPhotosList.length--
        let obj = {}
        obj.url = res
        this.driverIdentityCardPhotosList.push(obj)
        this.driverIdentityCardPhotosStatus[detail.index] = true
      })
    },
    // 车辆被保险人身份证（正面、背面）
    recognizeePhotos(file, detail) {
      file.status = 'uploading';
      file.message = '上传中...';
      this.recognizeePhotosStatus[detail.index] = false
      upload(file, detail).then((res) => {
        this.recognizeePhotosList.length--
        let obj = {}
        obj.url = res
        this.recognizeePhotosList.push(obj)
        this.recognizeePhotosStatus[detail.index] = true
      })
    },
    // 事故责任认定书
    accidentLiabilityPhotos(file, detail) {
      file.status = 'uploading';
      file.message = '上传中...';
      this.accidentLiabilityStatus[detail.index] = false
      upload(file, detail).then((res) => {
        this.accidentLiabilityList.length--
        let obj = {}
        obj.url = res
        this.accidentLiabilityList.push(obj)
        this.accidentLiabilityStatus[detail.index] = true
      })
    },
    // 其他照片
    otherPhotos(file, detail) {
      file.status = 'uploading';
      file.message = '上传中...';
      this.otherStatus[detail.index] = false
      upload(file, detail).then((res) => {
        this.otherList.length--
        let obj = {}
        obj.url = res
        this.otherStatus[detail.index] = true
        this.otherList.push(obj)

      })
    },

    // 审批人
    onConfirmSpr(value) {
      this.processList.forEach(res=>{
        if(res.level == value.level){
          res.userName = value.nickName;
          res.userId = value.userId;
          res.spLevel=false;
        }
      })
    },

    // 抄送人
    onConfirmCcr(value) {
      this.processList.forEach(res=>{
        if(res.level == value.level){
          res.ccUserName = value.nickName;
          res.ccUserId = value.userId;
          res.ccLevel=false;
        }
      })
    },

    // 定位
    handClickLocation() {
      this.$router.push('/locationOfMalfunction')
    },
    // 时间下拉
    onConfirmTime(time) {
      this.form.accidentTime = formatDate(time, false)
      this.showPickerTime = false
    },
    // 车牌号下拉
    onConfirm(value) {
      this.carLicensePlate = value
      this.showPicker = false
    },

    // 提交
    handCom() {

      if (this.driversPhotosList.length < 4) {
        Toast("双方驾驶证（正本、副本）为必填(4张)");
        return;
      }
      if (this.driversPhotosList1.length < 4) {
        Toast("双方行驶证（正本、副本）为必填(4张)");
        return;
      }
      if (this.driverIdentityCardPhotosList.length < 2) {
        Toast("驾驶员身份证（正面、背面）为必填(2张)");
        return;
      }
      if (this.recognizeePhotosList.length < 2) {
        Toast("车辆被保险人身份证（正面、背面）为必填(2张)");
        return;
      }
      if (this.accidentLiabilityList.length < 1) {
        Toast("事故责任认定书为必填");
        return;
      }
      let acc = false;
      this.accidentPhotosStatus.forEach(r=>{
        if(!r){
          acc = true
        }
      })
      if(acc){
        Toast("事故照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }
      let drP = false;
      this.driversPhotosStatus.forEach(r=>{
        if(!r){
          drP = true
        }
      })
      if(drP){
        Toast("双方驾驶证照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }
      let drP1 = false;
      this.driversPhotosStatus1.forEach(r=>{
        if(!r){
          drP1 = true
        }
      })
      if(drP1){
        Toast("双方行驶证照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }
      let dic =false;
      this.driverIdentityCardPhotosStatus.forEach(r=>{
        if(!r){
          dic = true
        }
      })
      if(dic){
        Toast("驾驶员身份证照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }
      let rgp = false;
      this.recognizeePhotosStatus.forEach(r=>{
        if(!r){
          rgp = true;
        }
      })
      if(rgp){
        Toast("车辆被保险人照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }
      let al = false;
      this.accidentLiabilityStatus.forEach(r=>{
        if(!r){
          al = true
        }
      })
      if(al){
        Toast("责任事故认定照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }
      let oth = false;
      this.otherStatus.forEach(r=>{
        if(!r){
          oth = true;
        }
      })
      if(oth){
        Toast("其他照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }

      this.form.accidentPhotos = [];
      this.accidentPhotosList.forEach((item) => {
        this.form.accidentPhotos.push(item.url)
      })
      this.form.oneSideDriveLicenceCardJust = this.driversPhotosList[0].url
      this.form.oneSideDriveLicenceCardAgainst = this.driversPhotosList[1].url
      this.form.oneSideTravelLicenceCardJust = this.driversPhotosList[2].url
      this.form.oneSideTravelLicenceCardAgainst = this.driversPhotosList[3].url

      this.form.theOtherDriveLicenceCardJust = this.driversPhotosList1[0].url
      this.form.theOtherDriveLicenceCardAgainst = this.driversPhotosList1[1].url
      this.form.theOtherTravelLicenceCardJust = this.driversPhotosList1[2].url
      this.form.theOtherTravelLicenceCardAgainst = this.driversPhotosList1[3].url

      this.form.driverIdCardJust = this.driverIdentityCardPhotosList[0].url
      this.form.driverIdCardAgainst = this.driverIdentityCardPhotosList[1].url

      this.form.carTheInsuredIdCardJust = this.recognizeePhotosList[0].url
      this.form.carTheInsuredIdCardAgainst = this.recognizeePhotosList[1].url

      this.form.liabilityStatement = this.accidentLiabilityList[0].url

      this.form.partsPhotos = [],
      this.otherList.forEach((item) => {
        this.form.partsPhotos.push(item.url)
      })

      let check = false;
      this.form.sysExamineConfigProcessList = []
      this.processList.forEach(obj=>{
        let people = {
          level: obj.level,
          roleId : obj.roleId,
          roleName :  obj.roleName,
          userId :  obj.userId,
          userName : obj.userName,
          ccRoleId :  obj.ccRoleId,
          ccRoleName :  obj.ccRoleName,
          ccUserId :  obj.ccUserId,
          ccUserName :  obj.ccUserName,
        };
        if(obj.userId == null){
          check = true;
        }
        this.form.sysExamineConfigProcessList.push(people);
      });
      if(check){
        Toast("请选择审批人");
        this.form.sysExamineConfigProcessList = [];
        return;
      }

      J02SafeAccidentAgainReport(this.form).then((res) => {
        Toast(res.msg);
        this.$router.push("/reportTheAccident");
      })
    },
  },
}
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
.box {
  width: 90%;
  border: 1px solid #ccc;
  margin: auto;
  margin-top: 0.5rem;
}
</style>

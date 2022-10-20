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
            label="车牌号:"
            placeholder="点击车牌号"
            @click="handleSearch"
          />
          <van-field label="驾驶员:" :value="name" readonly />

          <!-- <van-datetime-picker
          v-model="currentDate"
          type="datetime"
          title="选择完整时间"
        /> -->
          <van-field label="事故地点:" :value="form.accidentPlace">
            <template #right-icon>
              <van-icon name="location-o" @click="handClickLocation" />
            </template>
          </van-field>
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
              title="选择年月日时分"
              type="datetime"
              v-model="currentDate"
              :min-date="minDate"
              :max-date="maxDate"
              :formatter="formatter"
              @confirm="onConfirmTime"
              @cancel="showPickerTime = false"
            />
          </van-popup>
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
           :disabled="isDisabled"
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
import { formatDate } from "@/components/index";
import Header from "@/components/Header";
import Vue from "vue";
import { DatetimePicker, Toast } from "vant";
Vue.use(DatetimePicker);
import { J02SafeAccidentReport } from "@/api/reportTheAccident/theAccidentReport";
import { upload } from "@/components/upload";
import { queryExamineConfigProcessList } from "@/api/api";
import Cookies from "js-cookie";

export default {
  components: {
    Header,
  },
  data() {
    return {
      isDisabled: false,
      minDate: new Date(2020, 0, 1),
      maxDate: new Date(2025, 10, 1),
      currentDate: new Date(),
      name: undefined,
      valueTime: "",
      carLicensePlate: [],
      // 车辆id
      carId: [],
      showPicker: false,
      showPickerTime: false,

      processList:[],

      // 事故照片
      accidentPhotosList: [],
      accidentPhotosStatus:[],
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
        vehicleId: "",
        vehicleName: "",
        accidentTime: "",
        accidentPlace: "",
        longitude: "",
        latitude: "",
        oneSideDriveLicenceCardJust: "", 
        oneSideDriveLicenceCardAgainst: "", 
        oneSideTravelLicenceCardJust: "", 
        oneSideTravelLicenceCardAgainst: "", 
        theOtherDriveLicenceCardJust: "", 
        theOtherDriveLicenceCardAgainst: "", 
        theOtherTravelLicenceCardJust: "",
        theOtherTravelLicenceCardAgainst: "",
        driverIdCardJust: "",
        driverIdCardAgainst: "",
        carTheInsuredIdCardJust: "",
        carTheInsuredIdCardAgainst: "",
        liabilityStatement: "",
        accidentPhotos: [],
        partsPhotos: [],
        sysExamineConfigProcessList:[]
      },
      approval: {
        roleName: "",
        userName: "",
      },
    };
  },

  mounted() {
    let now = formatDate(new Date(), "yyyy");
    (this.minDate = new Date(now - 3, 0, 1)),
      (this.maxDate = new Date(now + 3, 11, 1));
    this.name = Cookies.get("username1");

    // 审批人
    queryExamineConfigProcessList({
      type: 4,
    }).then((res) => {

      this.processList = res.rows;
      this.processList.forEach(res=>{
        this.$set(res,'spLevel',false)
        this.$set(res,'ccLevel',false)
      })
      /*res.rows.forEach((item) => {
        return (this.approval = item);
      });*/
    });

  },
  methods: {
    // 修改时间格式
    formatter(type, val) {
      console.log('.type, val', type, val)
      if (type === 'year') {
        return `${val}年`;
      } else if (type === 'month') {
        return `${val}月`;
      } else if (type === 'day') {
        return `${val}日`;
      } else if (type === 'hour') {
        return `${val}时`;
      } else if (type === 'minute') {
        return `${val}分`;
      }
      return val;
    },
    // 车牌号搜索
    handleSearch() {
      this.pushTempData()
      this.$router.push("/theAccidentReportSearch");
    },

    pushTempData(){
      let formPage = {
        accidentPhotosList:this.accidentPhotosList,
        driversPhotosList:this.driversPhotosList,
        driversPhotosList1: this.driversPhotosList1,
        driverIdentityCardPhotosList: this.driverIdentityCardPhotosList,
        recognizeePhotosList: this.recognizeePhotosList,
        accidentLiabilityList: this.accidentLiabilityList,
        otherList: this.otherList,
        accidentTime: this.form.accidentTime,
        processList:this.processList
      }
      localStorage.setItem("formPage",JSON.stringify(formPage))
    },

    // 事故照片
    accidentPhotos(file, detail) {
      this.accidentPhotosStatus[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.accidentPhotosList.length--;
        let obj = {};
        obj.url = res;
        this.accidentPhotosList.push(obj);
        this.accidentPhotosStatus[detail.index] = true
      }).catch(e=>{
        this.accidentPhotosList.length--;
        this.accidentPhotosStatus[detail.index] = true
      });
    },
    // 双方行驶证照片
    driversPhotos(file, detail) {
      this.driversPhotosStatus[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.driversPhotosList.length--;
        let obj = {};
        obj.url = res;
        this.driversPhotosList.push(obj);
        this.driversPhotosStatus[detail.index] = true
        
      }).catch(e=>{
        this.driversPhotosList.length--;
        this.driversPhotosStatus[detail.index] = true
      });
    },
    // 另一方方行驶证照片
    driversPhotos1(file, detail) {
      this.driversPhotosStatus1[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.driversPhotosList1.length--;
        let obj = {};
        obj.url = res;
        this.driversPhotosList1.push(obj);
        this.driversPhotosStatus1[detail.index] = true
      }).catch(e=>{
        this.driversPhotosList1.length--;
        this.driversPhotosStatus1[detail.index] = true
      });
    },
    // 驾驶员身份证（正面、背面）
    driverIdentityCardPhotos(file, detail) {
      this.driverIdentityCardPhotosStatus[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.driverIdentityCardPhotosList.length--;
        let obj = {};
        obj.url = res;
        this.driverIdentityCardPhotosList.push(obj);
        this.driverIdentityCardPhotosStatus[detail.index] = true
      }).catch(e=>{
        this.driverIdentityCardPhotosList.length--;
        this.driverIdentityCardPhotosStatus[detail.index] = true
      });
    },
    // 车辆被保险人身份证（正面、背面）
    recognizeePhotos(file, detail) {
      this.recognizeePhotosStatus[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.recognizeePhotosList.length--;
        let obj = {};
        obj.url = res;
        this.recognizeePhotosList.push(obj);
        this.recognizeePhotosStatus[detail.index] = true
      }).catch(e=>{
        this.recognizeePhotosList.length--;
        this.recognizeePhotosStatus[detail.index] = true
      });
    },
    // 事故责任认定书
    accidentLiabilityPhotos(file, detail) {
      file.status = 'uploading';
      file.message = '上传中...';
      this.accidentLiabilityStatus[detail.index] = false
      upload(file, detail).then((res) => {
        this.accidentLiabilityList.length--;
        let obj = {};
        obj.url = res;
        this.accidentLiabilityList.push(obj);
        console.log("this.accidentLiabilityList",this.accidentLiabilityList)
        this.form.liabilityStatement = this.accidentLiabilityList[0].url;
        this.accidentLiabilityStatus[detail.index] = true
      }).catch(e=>{
        this.accidentLiabilityList.length--;
        this.accidentLiabilityStatus[detail.index] = true
      });
    },
    // 其他照片
    otherPhotos(file, detail) {
      this.otherStatus[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.otherList.length--;
        let obj = {};
        obj.url = res;
        this.otherList.push(obj);
        this.otherStatus[detail.index] = true
      }).catch(e=>{
        this.otherList.length--;
        this.otherStatus[detail.index] = true
      });
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
      this.pushTempData()
      this.$router.push("/locationOfMalfunction");
    },
    // 时间下拉
    onConfirmTime(time) {
      this.form.accidentTime = formatDate(time, false);

      this.showPickerTime = false;
    },
    // 车牌号下拉
    onConfirm(value) {
      this.carLicensePlate = value;
      this.showPicker = false;
      // if(){

      // }
    },

    // 提交
    handCom() {
      /*if (this.driversPhotosList.length < 4) {
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
      }*/

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

        if(this.driversPhotosList.length >= 4){
          this.form.oneSideDriveLicenceCardJust = this.driversPhotosList[0].url;
          this.form.oneSideDriveLicenceCardAgainst = this.driversPhotosList[1].url;
          this.form.theOtherDriveLicenceCardJust = this.driversPhotosList[2].url;
          this.form.theOtherDriveLicenceCardAgainst = this.driversPhotosList[3].url;
        }
      if(this.driversPhotosList.length == 3){
        this.form.oneSideDriveLicenceCardJust = this.driversPhotosList[0].url;
        this.form.oneSideDriveLicenceCardAgainst = this.driversPhotosList[1].url;
        this.form.theOtherDriveLicenceCardJust = this.driversPhotosList[2].url;
      }
      if(this.driversPhotosList.length == 2){
        this.form.oneSideDriveLicenceCardJust = this.driversPhotosList[0].url;
        this.form.oneSideDriveLicenceCardAgainst = this.driversPhotosList[1].url;
      }
      if(this.driversPhotosList.length == 1){
        this.form.oneSideDriveLicenceCardJust = this.driversPhotosList[0].url;
      }

      if(this.driversPhotosList1.length >= 4){
        this.form.oneSideTravelLicenceCardJust = this.driversPhotosList1[0].url;
        this.form.oneSideTravelLicenceCardAgainst = this.driversPhotosList1[1].url;
        this.form.theOtherTravelLicenceCardJust = this.driversPhotosList1[2].url;
        this.form.theOtherTravelLicenceCardAgainst = this.driversPhotosList1[3].url;
      }
      if(this.driversPhotosList1.length == 3){
        this.form.oneSideTravelLicenceCardJust = this.driversPhotosList1[0].url;
        this.form.oneSideTravelLicenceCardAgainst = this.driversPhotosList1[1].url;
        this.form.theOtherTravelLicenceCardJust = this.driversPhotosList1[2].url;
      }
      if(this.driversPhotosList1.length == 2){
        this.form.oneSideTravelLicenceCardJust = this.driversPhotosList1[0].url;
        this.form.oneSideTravelLicenceCardAgainst = this.driversPhotosList1[1].url;
      }
      if(this.driversPhotosList1.length == 1){
        this.form.oneSideTravelLicenceCardJust = this.driversPhotosList1[0].url;
      }
      if(this.driverIdentityCardPhotosList.length == 2){
        this.form.driverIdCardJust = this.driverIdentityCardPhotosList[0].url;
        this.form.driverIdCardAgainst = this.driverIdentityCardPhotosList[1].url;
      }
      if(this.driverIdentityCardPhotosList.length == 1){
        this.form.driverIdCardJust = this.driverIdentityCardPhotosList[0].url;
      }
      if(this.recognizeePhotosList.length == 2){
        this.form.carTheInsuredIdCardJust = this.recognizeePhotosList[0].url;
        this.form.carTheInsuredIdCardAgainst = this.recognizeePhotosList[1].url;
      }
      if(this.recognizeePhotosList.length == 1){
        this.form.carTheInsuredIdCardJust = this.recognizeePhotosList[0].url;
      }

      let check = false;
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
      this.accidentPhotosList.forEach((item) => {
        this.form.accidentPhotos.push(item.url);
      });
      this.otherList.forEach((item) => {
        this.form.partsPhotos.push(item.url);
      });
      this.isDisabled = true
      J02SafeAccidentReport(this.form).then((res) => {
        // this.accidentPhotosList.length--;
        // this.otherList.length--;
        localStorage.removeItem("formPage")
        this.$store.dispatch('AccidentCar', {})
        this.$store.dispatch('SetData', {})
        console.log(res);
        Toast(res.msg);
        this.$router.go(-1);
      }).catch(e=>{
        this.form.sysExamineConfigProcessList = [];
        this.isDisabled = false
      });
    },
  },
  beforeRouteEnter(to, from, next) {
    console.log(from, '组件独享守卫beforeRouteEnter第二个参数');
    next(vm => {
      //因为当钩子执行前，组件实例还没被创建
      // vm 就是当前组件的实例相当于上面的 this，所以在 next 方法里你就可以把 vm 当 this 来用了。

      let path = from.path;
      if(path == "/theAccidentReportSearch" || path == "/locationOfMalfunction"){
        // 地图
        vm.form.accidentPlace = vm.$store.state.map_Value.name;
        vm.form.longitude = vm.$store.state.map_Value.lng;
        vm.form.latitude = vm.$store.state.map_Value.lat;
        // 车牌号
        vm.form.vehicleName = vm.$store.state.AccidentCar.licensePlate;
        vm.form.vehicleId = vm.$store.state.AccidentCar.id;
        if(localStorage.getItem("formPage")){
          let temp = JSON.parse(localStorage.getItem("formPage"));
          vm.form.accidentTime = temp.accidentTime;
          vm.accidentPhotosList = temp.accidentPhotosList;
          vm.driversPhotosList = temp.driversPhotosList;
          vm.driversPhotosList1 = temp.driversPhotosList1;
          vm.driverIdentityCardPhotosList = temp.driverIdentityCardPhotosList;
          vm.recognizeePhotosList = temp.recognizeePhotosList;
          vm.accidentLiabilityList = temp.accidentLiabilityList;
          vm.otherList = temp.otherList;
        }
      }
    });
  },
};
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

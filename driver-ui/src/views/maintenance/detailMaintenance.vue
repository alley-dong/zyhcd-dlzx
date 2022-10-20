<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-cell
        :value="form.mainLicensePlate"
        title-class="left-title"
        is-link
        @click="leaveClick('open', 1)"
      >
        <template #title>
          <span>主车车牌号： </span>
        </template>
      </van-cell>
      <van-cell
        :value="form.mainVehicleModelName"
        title-class="left-title"
        value-class="text-cell"
      >
        <template #title>
          <span>主车车型： </span>
        </template>
      </van-cell>
      <van-cell
        :value="form.hangLicensePlate"
        title-class="left-title"
        is-link
        @click="leaveClick('open', 2)"
      >
        <template #title>
          <span>挂车车牌号： </span>
        </template>
      </van-cell>
      <van-cell
        :value="form.hangVehicleModelName"
        title-class="left-title"
        value-class="text-cell"
      >
        <template #title>
          <span>挂车车型： </span>
        </template>
      </van-cell>
      <van-field
        v-model="form.mileage"
        label="故障时公里数"
        placeholder="请输入故障时公里数"
      />
      <van-cell
        :value="form.routeName"
        title-class="left-title"
        is-link
        @click="leaveClick('openRout')"
      >
        <template #title>
          <span>行驶路线： </span>
        </template>
      </van-cell>
      <van-field
        v-model="form.accidentLocation"
        rows="2"
        autosize
        label="发生故障地点"
        type="textarea"
        maxlength="50"
        placeholder="请输入发生故障地点"
        show-word-limit
      />
      <van-field
        v-model="form.totalMass"
        label="故障时荷载/总质量"
        placeholder="请输入故障时荷载/总质量"
      />
      <van-cell
        :value="form.roadConditionName || form.road"
        title-class="left-title"
        is-link
        @click="leaveClick('openRoad')"
      >
        <template #title>
          <span>故障时路况： </span>
        </template>
      </van-cell>
      <!-- 天气 -->
      <van-cell
        :value="form.weatherConditionName || form.weather"
        title-class="left-title"
        is-link
        @click="leaveClick('openWeather')"
      >
        <template #title>
          <span>故障时天气： </span>
        </template>
      </van-cell>
      <div class="head-type">维保类型</div>
      <div class="repair">
        <div class="repair-text" >
          <van-field
                label="维修保养项"
                v-model="selName"
                placeholder="请输入维修保养项"
                @change="dingWei(selName)"
          />
        </div>
        <van-checkbox-group
          v-for="(item, index) in dataList"
          :key="item.id"
          v-model="item.itemId"
        >
          <van-cell-group>
            <div>
              <van-cell
                title-class="left-title"
                :id="item.itemName"
                clickable
                :title="item.itemName"
              >
                <template #right-icon>
                  <van-checkbox :name="item.id" ref="checkboxes" />
                </template>
              </van-cell>
              <van-field v-model="item.remarks" placeholder="请输入维保原因" />
              <div
                @click="numClick(index)"
                style="text-align: left; padding-left: 0.3rem"
              >
                <van-uploader
                  v-model="item.files"
                  :before-read="bfClick"
                  :after-read="handleAfterRead"
                  :max-count="5"
                />
              </div>
            </div>
          </van-cell-group>
        </van-checkbox-group>
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
        <!-- <div class="repair" style="display: flex; margin-top: 0.5rem">
          <div class="repair-text">审批人：</div>
          <div style="font-size: 0.25rem">
            <div>
              维保认定员 <span style="margin-left: 0.5rem">一级审批</span>
            </div>
            <div style="margin-top: 0.4rem">
              维保认定员 <span style="margin-left: 0.5rem">一级审批</span>
            </div>
            <div style="margin-top: 0.4rem">
              维保认定员 <span style="margin-left: 0.5rem">一级审批</span>
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
    </div>
    <van-button
      round
      type="info"
      @click="handleClick"
      :disabled="isDisabled"
      style="margin-top: 0.5rem"
      >提交申请</van-button
    >

    <!-- 路线 -->
    <van-action-sheet
      v-model="openRout"
      :actions="RoutList"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="handleSelect($event, 'routeId', 'routeName')"
    />
    <!-- 路况 -->
    <van-action-sheet
      v-model="openRoad"
      :actions="roadList"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="handleSelect($event, 'roadCondition', 'roadConditionName')"
    />
    <!-- 天气 -->
    <van-action-sheet
      v-model="openWeather"
      :actions="listrWeather"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="handleSelect($event, 'weatherCondition', 'weatherConditionName')"
    />
    <LicensePlate
      v-if="open"
      @licensePlateClick="licensePlateClick"
      style="
        position: absolute;
        top: 0;
        background: #fff;
        z-index: 999;
        width: 100%;
      "
      :style="{ height: max_height / 10 + 'rem' }"
      :type="formData.category_type"
      :licensePlat="licensePlat"
    />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Form, Toast } from "vant";
import { minioChooseBucketUpload } from "@/api/api";
import { queryExamineConfigProcessList } from "@/api/api";
import {
  repairH08RepairItemInfo,
  repairH08Accident,
  commonJ04RoutList,
  repairH01Add,
  repairH01Info,
} from "@/api/maintenance/index";
import LicensePlate from "@/views/maintenance/licensePlate";
export default {
  components: {
    Header,
    LicensePlate,
  },
  data() {
    return {
      isDisabled:false,
      uploadStatus:false,
      max_height: undefined,
      open: false,
      openAccident: false,
      openRoad: false,
      openWeather: false,
      list: ["a", "b"],
      result: [],
      dataList: [],
      RoutList: [],
      roadList: [],
      listrWeather: [],
      licensePlat: undefined,
      data_index: undefined,
      category_type: undefined,
      vehicleId: undefined,
      dataList_detail: [],
      processList: [],
      formData: undefined,
      openRout: false,
      actions: [
        {
          name: "是",
          value: 1,
        },
        {
          name: "否",
          value: 2,
        },
      ],
      actions1: [],
      repairForm: undefined,
      selName:'',
      form: {
        // repairType: 1,
        isOther: undefined,
        isOtherName: undefined,
        accidentVideo: undefined,
        accidentId: undefined,
        accidentLocation: undefined,
        mainVehicleId: undefined,
        mainLicensePlate: "请选择主车车牌",
        mainVehicleModelName: "请先选择主车车牌",
        mainVehicleModel: undefined,
        hangVehicleId: undefined,
        hangLicensePlate: "请选择挂车车牌",
        hangVehicleModelName: "请先选择挂车车牌",
        hangVehicleModel: undefined,
        mileage: undefined,
        routeId: undefined,
        accidentLocation: undefined,
        totalMass: undefined,
        routeName: "请选择行驶路线",
        roadCondition: undefined,
        roadConditionName: "请选择故障时路况",
        weatherCondition: undefined,
        weatherConditionName: "请选择天气路况",
        sysExamineConfigProcessList:[]
      },
    };
  },
  methods: {
    // 选择行驶路线
    handleSelect(val, name1, name2) {
      this.onCancel();
      this.form[name1] = val.id;
      this.form[name2] = val.name;
      Toast(val.name);
    },
    licensePlateClick(val) {
      console.log(val);
      this.open = false;
      if (this.licensePlat == 1) {
        this.form.mainVehicleId = val.id;
        this.form.mainLicensePlate = val.licensePlate;
        this.form.mainVehicleModelName = val.vehicleModelName;
        this.form.mainVehicleModel = val.vehicleModel;
        // debugger;
      } else {
        this.form.hangVehicleId = val.id;
        this.form.hangLicensePlate = val.licensePlate;
        this.form.hangVehicleModelName = val.vehicleModelName;
        this.form.hangVehicleModel = val.vehicleModel;
      }
    },
    leaveClick(name, type) {
      console.log(1);
      this[name] = true;
      this.licensePlat = type;
    },
    toggle(index) {
      this.$refs.checkboxes[index].toggle();
    },
    // 校验
    rules() {
      let arr = [
        "请选择主车车牌",
        "请先选择主车车牌",
        "请选择挂车车牌",
        "请先选择挂车车牌",
        "请选择行驶路线",
        "请选择故障时路况",
        "请选择天气路况",
      ];
      let arr1 = ["mileage", "accidentLocation", "totalMass"];
      for (const key in this.form) {
        // console.log(key);
        if (arr.indexOf(this.form[key]) >= 0) {
          Toast(this.form[key]);
          return true;
        }
        switch (key) {
          case "mileage":
            if (!this.form[key]) {
              Toast("请输入故障时公里数");
              return true;
            }
            continue;
          case "accidentLocation":
            if (!this.form[key]) {
              Toast("请输入发生故障地点");
              return true;
            }
            continue;
          case "totalMass":
            if (!this.form[key]) {
              Toast("请输入故障时荷载/总质量");
              return true;
            }
            continue;
        }
      }
    },

    numClick(index) {
      if(this.uploadStatus){
        return false
      }
      this.data_index = index;
    },
    // 提交申请
    handleClick() {
      if(this.uploadStatus){
        Toast("有图片在上传中，请等待上传完毕再提交");
        return;
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

      let form = JSON.parse(JSON.stringify(this.form));
      let dataList = JSON.parse(JSON.stringify(this.dataList));

      for (const item of dataList) {
        if (item.itemId) {
          item.itemId = item.itemId[0];
        }
        if (item.files) {
          let arr = [];
          for (const data of item.files) {
            arr.push(data.url);
          }
          item.fileList = arr;
        }
      }

      let data_arr = [];
      for (const item of dataList) {
        if (item.itemId) {
          if (!item.remarks) {
            //与图片
            Toast("勾选维修保养项原因为必填");
            this.form.sysExamineConfigProcessList = [];
            return;
          }
          if (!item.files) {
            Toast("勾选维修保养项图片为必填");
            this.form.sysExamineConfigProcessList = [];
            return;
          }
          delete item.files;
          // debugger;
          data_arr.push(item);
        }
      }
      let arr = [
        "请选择主车车牌",
        "请先选择主车车牌",
        "请选择挂车车牌",
        "请先选择挂车车牌",
        "请选择行驶路线",
        "请选择故障时路况",
        "请选择天气路况",
      ];
      for (const key in form) {
        if (arr.indexOf(form[key]) >= 0) {
          form[key] = undefined;
        }
      }

      let obj = {
        ...this.repairForm,
        ...form,
        repairItemDate: data_arr,
      };

      this.isDisabled = true
      repairH01Add(obj).then((res) => {
        localStorage.removeItem("accidentVideo");
        // this.$router.go(-1)
        this.$router.push("/maintenance");
      }).catch(e=>{
        this.form.sysExamineConfigProcessList = [];
        this.isDisabled = false
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

    bfClick(){
      if(this.uploadStatus){
        Toast("上一张图片过大，请耐心等待上一张图片上传完毕");
        return false
      }
      return true
    },

    // 上传
    handleAfterRead(file) {
      this.uploadStatus = true;
      file.status = 'uploading';
      file.message = '上传中...';
      let fd = new FormData();
      fd.append("file", file.file); // 要提交给后台的文件
      minioChooseBucketUpload(fd)
        .then((response) => {
          this.dataList[this.data_index].files.length--;
          let obj = {};
          console.log("提交文件", response.msg);
          obj.url = response.msg;
          this.dataList[this.data_index].files.push(obj);
          this.uploadStatus = false;
          file.status = 'done';
          file.message = '上传完成';
        })
        .catch((err) => {
          console.log(err);
          this.uploadStatus = false;
        });
    },
    //   上传
    afterRead(file) {
      // 此时可以自行将文件上传至服务器
      console.log(file);
      let fd = new FormData();
      fd.append("file", file.file); // 要提交给后台的文件
      minioChooseBucketUpload(fd)
        .then((response) => {
          let obj = {};
          console.log("提交文件", response.msg);
          obj.url = response.msg;
          this.form.accidentVideo = [obj];
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 选择
    onSelect(val, name1, name2) {
      this.form[name1] = val.value;
      this.form[name2] = val.name;
      Toast(val.name);
    },
    //   下拉取消
    onCancel() {
      this.openRout = false;
    },

    dingWei(name) {
      if(!name){
        return;
      }
      var PageId = document.getElementById(name)
      if(!PageId){
        Toast("没有该保养项");
        return
      }
      window.scrollTo({
        'top': PageId.parentElement.parentElement.offsetTop-60,
        'behavior': 'smooth'
      })
    }

  },
  mounted() {
    // 路况下拉
    this.getDicts("road_condition").then((res) => {
      for (const item of res.data) {
        let obj = {};
        obj.name = item.dictLabel;
        obj.id = item.dictValue;
        this.roadList.push(obj);
      }
    });
    // 天气
    this.getDicts("weather_condition").then((res) => {
      console.log(res);
      for (const item of res.data) {
        let obj = {};
        obj.name = item.dictLabel;
        obj.id = item.dictValue;
        this.listrWeather.push(obj);
      }
    });
    commonJ04RoutList({}).then((res) => {
      this.RoutList = res.data;
    });
    console.log(document.body.scrollHeight);
    this.max_height = document.body.scrollHeight;
    this.formData = JSON.parse(localStorage.getItem("repairForm"));
    this.formData.category_type = this.formData.category;
    queryExamineConfigProcessList({
      type: 7,
      relationId: this.$route.query.id
    }).then((res) => {
      console.log(res);
      this.processList = res.rows;
      this.processList.forEach(res=>{
        this.$set(res,'spLevel',false)
        this.$set(res,'ccLevel',false)
      })
    });
    if (this.$route.query.id) {
      repairH01Info({ id: this.$route.query.id }).then((res) => {
        console.log(res);
        this.form = res.data;
        this.form.sysExamineConfigProcessList = [],
        this.form.isOtherName = res.data.isOther == 1 ? "是" : "否";
        if(res.data.isOther != 1){
          this.form.accidentVideo = undefined
        }
        if (res.data.repairItemDate) {
          for (const item of res.data.repairItemDate) {
            item.files = [{ url: item.files }];
          }
          this.dataList_detail = res.data.repairItemDate;
        } else {
          this.dataList_detail = [];
        }
        console.log(this.dataList, this.dataList_detail);
        for (const item of this.dataList) {
          for (const data of this.dataList_detail) {
            if (item.id == data.itemId) {
              item.files = data.files;
              item.itemName = data.itemName;
              item.remarks = data.remarks;
              item.itemId = [item.id];
            }
          }
        }
        this.dataList = JSON.parse(JSON.stringify(this.dataList));
        // = res.data.repairItemDate ? res.data.repairItemDate : [];
      });
    }
    this.form.type = parseInt(this.$route.params.id);
    // console.log(this.$route.params.id);
    this.vehicleId = this.$route.params.id;
    this.repairForm = JSON.parse(localStorage.getItem("repairForm"));
    // mainVehicleId
    // console.log(this.repairForm.mainVehicleId);
    repairH08RepairItemInfo({ status: this.$route.params.id }).then((res) => {
      let arr = [];
      for (const item of res.data) {
        let obj = {};
        obj.files = undefined;
        obj.itemId = undefined;
        obj.remarks = undefined;
        obj.itemName = item.name;
        obj.id = item.id;
        arr.push(obj);
      }
      this.dataList = arr;
    });
    repairH08Accident({ id: this.repairForm.mainVehicleId }).then((res) => {
      console.log(res);
      let arr = [];
      for (const item of res.data) {
        let obj = {};
        obj.value = item.id;
        obj.name = item.contentBox;
        arr.push(obj);
      }
      this.actions1 = arr;
    });
  },
};
</script>

<style lang="scss" scoped>
.head-type {
  text-align: left;
  height: 1rem;
  line-height: 1rem;
  margin-left: 0.5rem;
  font-weight: 600;
  font-size: 0.3rem;
}
.repair-text {
  text-align: left;
  font-size: 0.28rem;
  margin-left: 0.82rem;
  font-weight: 600;
}
.click-disable{
  pointer-events: none;
}
</style>

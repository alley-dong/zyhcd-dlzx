<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-cell
        :value="form.repairName"
        title-class="left-title"
        is-link
        @click="leaveClick('maintenance')"
      >
        <template #title>
          <span>维保类型： </span>
        </template>
      </van-cell>
      <van-cell
        :value="form.isOtherName"
        title-class="left-title"
        is-link
        @click="leaveClick('isAllopatry')"
      >
        <template #title>
          <span>是否是异地维保： </span>
        </template>
      </van-cell>
      <van-row v-if="form.isOther == 1">
        <van-col
          span="12"
          style="height: 0.9rem; line-height: 0.9rem; font-size: 0.25rem"
          >上传视频：</van-col
        >
        <van-col span="12">
          <van-uploader accept="*" :after-read="afterRead">
            <van-button icon="plus" type="primary">上传视频</van-button>
          </van-uploader></van-col
        >
        <van-col span="24">
          <video
            v-if="form.accidentVideo"
            style="width: 80%"
            controls="controls"
            :src="form.accidentVideo"
          ></video
        ></van-col>
      </van-row>
      <van-cell
        :value="form.categoryName"
        title-class="left-title"
        is-link
        @click="leaveClick('category')"
      >
        <!-- TODO:维保类别字段？ -->
        <template #title>
          <span>维保类别： </span>
        </template>
      </van-cell>
      <!-- <van-cell
        v-if="form.repairType == 2 && vehicleId == 1"
        :value="form.accidentLocation"
        title-class="left-title"
        is-link
        @click="openAccident = true"
      >
        <template #title>
          <span>选择事故： </span>
        </template>
      </van-cell> -->
      <!-- 路线 -->
      <van-action-sheet
        v-model="maintenance"
        :actions="maintenanceList"
        cancel-text="取消"
        close-on-click-action
        @cancel="onCancel"
        @select="handleSelect($event, 'repairType', 'repairName')"
      />
      <van-action-sheet
        v-model="isAllopatry"
        :actions="isAllopatryList"
        cancel-text="取消"
        close-on-click-action
        @cancel="onCancel"
        @select="handleSelect($event, 'isOther', 'isOtherName')"
      />
      <van-action-sheet
        v-model="category"
        :actions="categoryList"
        cancel-text="取消"
        close-on-click-action
        @cancel="onCancel"
        @select="handleSelect($event, 'category', 'categoryName')"
      />
    </div>
    <van-action-sheet
      v-model="openAccident"
      :actions="actions1"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect($event, 'accidentId', 'accidentLocation')"
    />
    <van-button
      round
      type="info"
      @click="handleClick"
      style="margin-top: 0.5rem; width: 80%"
      >下一步</van-button
    >
  </div>
</template>

<script>
import Header from "@/components/Header";
import { minioChooseBucketUpload } from "@/api/api";
import {
  repairH06LicensePlate,
  commonJ04RoutList,
  repairH01Info,
  repairH08Accident,
} from "@/api/maintenance/index";
import { Toast } from "vant";
export default {
  data() {
    return {
      maintenance: false,
      isAllopatry: false,
      category: false,
      isUpLoad: false,
      openAccident: false,
      actions1: [],
      maintenanceList: [
        { name: "正常维修", id: 1 },
        { name: "事故维修", id: 2 },
        { name: "保养", id: 3 },
      ],
      isAllopatryList: [
        { name: "是", id: 1 },
        { name: "否", id: 2 },
      ],
      categoryList: [
        { name: "主车维保", id: 1 },
        { name: "挂车维保", id: 2 },
      ],
      form: {
        repairType: undefined,
        repairName: "请选择维保类型",
        isOther: undefined,
        isOtherName: "请选择是否是异地维保",
        category: undefined,
        categoryName: "请选择维保类别",
        accidentVideo: undefined,
        accidentLocation: undefined,
      },
      initData:{
        repairType: "",
        repairName: "",
        isOther: "",
        isOtherName: "",
        category: "",
        categoryName: "",
        accidentVideo: undefined,
        accidentLocation: "",
      }
    };
  },
  mounted() {
    if (this.$route.query.id) {
      repairH01Info({ id: this.$route.query.id }).then((res) => {
        this.form.repairType = res.data.repairType
        this.initData.repairType = res.data.repairType;
        this.maintenanceList.forEach((val)=>{
          if(val.id == this.form.repairType){
            this.form.repairName = val.name
            this.initData.repairName = val.name
          }
        })
        this.form.isOther = res.data.isOther
        this.initData.isOther = res.data.isOther;
        this.isAllopatryList.forEach((val) => {
          if(val.id == this.form.isOther){
            this.form.isOtherName = val.name
            this.initData.isOtherName = val.name
          }
        })
        this.form.category = res.data.type
        this.initData.category = res.data.type
        this.categoryList.forEach((val) => {
          if(val.id == this.form.category){
            this.form.categoryName = val.name
            this.initData.categoryName = val.name
          }
        })
        if(this.form.isOther == 1){
          this.form.accidentVideo = res.data.accidentVideo
          this.initData.accidentVideo = res.data.accidentVideo
        }
      });
    }

    // repairH08Accident({ id: this.repairForm.mainVehicleId }).then((res) => {
    //   console.log(res);
    //   let arr = [];
    //   for (const item of res.data) {
    //     let obj = {};
    //     obj.value = item.id;
    //     obj.name = item.contentBox;
    //     arr.push(obj);
    //   }
    //   this.actions1 = arr;
    // });
  },
  methods: {
    afterRead(file) {
      let fd = new FormData();
      fd.append("file", file.file); // 要提交给后台的文件
      this.isUpLoad = true;
      minioChooseBucketUpload(fd)
        .then((response) => {
          let obj = {};
          console.log("提交文件", response.msg);
          obj.url = response.msg;
          this.form.accidentVideo = obj.url;
          this.isUpLoad = false;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleSelect(val, name1, name2) {
      this.reset();
      this.form[name1] = val.id;
      this.form[name2] = val.name;
      Toast(val.name);
    },
    // 关闭弹窗
    reset() {
      this.maintenance = this.isAllopatry = this.category = false;
      //  = this.openRoad = this.openWeather = false;
    },
    leaveClick(name) {
      this[name] = true;
    },
    //   下拉取消
    onCancel() {
      console.log(this.actions);
      Toast("取消");
    },
    handleClick() {
      if (this.isUpLoad) {
        Toast("请等待视频上传完成");
        return;
      }
      if (this.form.isOther == 1 && !this.form.accidentVideo) {
        Toast("请上传视频");
        return;
      }
      let list = ["请选择维保类型", "请选择是否是异地维保", "请选择维保类别"];
      for (const key in this.form) {
        if (list.indexOf(this.form[key]) > -1) {
          Toast(this.form[key]);
          return;
        }
      }
      console.log(this.form);
      if(this.form.isOther == 2){
        this.form.accidentVideo = undefined
      }
      // debugger
      localStorage.setItem("repairForm", JSON.stringify(this.form));

      if (this.$route.query.id && this.form.repairType == this.initData.repairType
              && this.form.repairName == this.initData.repairName
              && this.form.isOther == this.initData.isOther
              && this.form.isOtherName == this.initData.isOtherName
              && this.form.category == this.initData.category
              && this.form.categoryName == this.initData.categoryName
              && this.form.accidentVideo == this.initData.accidentVideo) {
        this.$router.push({
          path: "/detailMaintenance/" + this.form.category,
          query: { id: this.$route.query.id },
        });
      } else {
        this.$router.push("/detailMaintenance/" + this.form.category);
      }
    },
  },
  components: {
    Header,
  },
};
</script>

<style lang="scss" scoped>
</style>
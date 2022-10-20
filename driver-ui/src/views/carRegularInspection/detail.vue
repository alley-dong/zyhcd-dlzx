<template>
  <div>
    <!-- 挂车定检 -->
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container car-detail">
      <van-cell
        title="车牌号"
        is-link
        :value="form.licensePlate"
        @click="URLClick(2)"
        title-class="left-title"
      />
      <div style="font-weight: 600; margin-top: 0.2rem">
        {{ form.type == 1 ? "主车" : "挂车" }}定检
      </div>
      <div style="font-weight: 600; margin-top: 0.2rem">点检内容</div>
      <!-- -->
      <van-radio-group
        v-for="(item, index) in problemList"
        :key="index"
        v-model="item.status"
        disabled
      >
        <van-cell class="text" :title="index + 1 + '、' + item.name" />
        <van-cell
          class="text"
          title="检车结果："
          :value="item.status == 1 ? '正常' : '维修'"
        />
        <!-- <van-cell-group>
          <van-cell clickable title="正常" @click="toggle(index)" class="text">
            <template #right-icon>
              <van-radio
                shape="square"
                icon-size="16"
                :name="1"
                ref="checkboxes"
              />
            </template>
          </van-cell>
          <van-cell
            clickable
            title="需维修"
            @click="toggle(index)"
            class="text"
          >
            <template #right-icon>
              <van-radio
                shape="square"
                icon-size="16"
                :name="2"
                ref="checkboxes"
              />
            </template>
          </van-cell>
        </van-cell-group> -->
        <div @click="numClick(index)">
          <van-field name="uploader" label="文件上传">
            <template #input>
              <van-image
                v-for="(data, index) in item.files"
                :key="index"
                @click="showClick(data)"
                width="100"
                height="100"
                :src="data"
                style="    margin: 0 0.2rem 0.2rem 0;"
              />
            </template>
          </van-field>
        </div>
      </van-radio-group>
    </div>
    <van-cell title-class="left-title" title="定检日期" :value="form.createTime" />
    <van-cell title-class="left-title" title="备注" :value="form.remark" />
    <div v-for="item in processList" :key="item.id">
      <div class="upload-img">
        <div class="text">审批人：</div>
        <div style="float: right; flex: 1; text-align: right">
          {{ item.userName }}
          <van-tag type="primary" style="margin-left: 0.3rem">{{
            item.statusEnum
          }}</van-tag>
        </div>
      </div>
      <van-cell
        title-class="left-title"
        title="抄送人"
        :value="item.ccUserName"
      />
    </div>
    <van-button
      round
      v-if="buttonShow"
      type="info"
      @click="submitClick"
      style="margin-bottom: 1rem; width: 50%"
      >{{ sub_text }}</van-button
    >
  </div>
</template>

<script>
import Header from "@/components/Header";
import { minioChooseBucketUpload,getSysExamineTaskList } from "@/api/api";
import {
  inspectI02Add,
  inspectI04List,
} from "@/api/carRegularInspection/index";
import { Toast } from "vant";
import { ImagePreview } from "vant";
import { queryExamineConfigProcessList } from "@/api/reportTheAccident/incidentRecordDetails";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form: {
        licensePlate: "请选择车牌号",
        vehicleId: undefined,
      },
      processList: undefined,
      buttonShow: false,
      //  uploader: [{ url: 'https://img.yzcdn.cn/vant/leaf.jpg' }],
      answer: undefined,
      problemList: [],
      problemList1: [],
      url_index: undefined,
      sub_text: "提交",
    };
  },
  mounted() {
    this.getqueryExamineConfigProcessList();
    if (this.$route.query.status == 3) {
      this.buttonShow = true;
      this.sub_text = "重新提交";
    }
    console.log(this.$store.state);
    // switch (this.$store.state.type) {
    //   case 2:
    //     // 主车
    //     if (!this.$store.state.vehicle) break;
    //     this.problemList = JSON.parse(localStorage.getItem("problemList"));
    //     this.form.vehicleId = this.$store.state.vehicle.vehicleId;
    //     this.form.licensePlate = this.$store.state.vehicle.licensePlate;
    //     console.log(this);
    //     return;

    // }
    // this.getDicts("c_vehicle_inspect_main").then((res) => {
    //   console.log(res);
    //   // if (localStorage.getItem("problemList")) {
    //   //   return
    //   // }
    //   for (const item of res.data) {
    //     let obj = {};
    //     obj.itemType = item.dictValue;
    //     obj.name = item.dictLabel;
    //     obj.status = undefined;
    //     obj.files = [];
    //     this.problemList.push(obj);
    //   }
    // });
    inspectI04List(this.$route.params.id).then((res) => {
      console.log(res.data);

      this.form = res.data;
      for (const item of res.data.cVehicleInspectItemList) {
        let arr = [];
        if (item.files != "") {
          let fileList = item.files.split(",");
          fileList.length--;
          for (const data of fileList) {
            arr.push(data);
          }
        }
        item.files = arr;
      }
      this.problemList = res.data.cVehicleInspectItemList;
    });
  },
  filters: {
    status: function (value) {
      switch (value) {
        case 1:
          return "待审批";
          break;
        case 2:
          return "已通过";
          break;
        case 3:
          return "已驳回";
          break;
        // case 4:
        //   return "已撤回";
        //   break;
        default:
          break;
      }
    },
  },
  methods: {
    //  
    getqueryExamineConfigProcessList() {
      getSysExamineTaskList({
        relationId: this.$route.params.id,
        loginType:1
      }).then((res) => {
        console.log(res);
        this.processList = res.data.sysExamineUserTaskList;
      });
    },
    showClick(file) {
      ImagePreview([file]);
    },
    remove_problemList() {
      localStorage.removeItem("problemList");
    },
    numClick(index) {
      console.log(index);
      this.url_index = index;
    },
    submitClick() {
      if (this.form.type == 1) {
        this.$router.push({
          path: "/mainVehicleAdd",
          query: { id: this.$route.params.id },
        }); //主车
      } else {
        this.$router.push({
          path: "/trailerAdd",
          query: { id: this.$route.params.id },
        }); //挂车
      }
      // this.check();
      // inspectI02Add({
      //   ...this.form,
      //   cVehicleInspectItemList: this.problemList1,
      // });
      // this.$router.go(-1);
    },
    toggle(index) {
      // this.$refs.checkboxes[index].toggle();
    },
    check() {
      if (this.form.licensePlate == "请选择车牌号") {
        Toast("请选择车牌号");
      }
      let arr = JSON.parse(JSON.stringify(this.problemList));
      for (const item of arr) {
        if (!item.status) {
          Toast("请选择维修状态");
          return;
        }
        if (item.files.length == 0) {
          Toast("请选择图片");
          return;
        }
        let arr = "";
        for (const data of item.files) {
          arr += data.url + ",";
        }
        item.files = arr;
      }
      this.problemList1 = arr;
    },
    // 跳转
    URLClick(val) {
      // localStorage.setItem("problemList", JSON.stringify(this.problemList));
      // this.$store.dispatch("CarNumber", val);
      // this.$router.push("/search");
    },
    // 上传
    readClick(file, detail) {
      let fd = new FormData();
      console.log("param.file", file);
      fd.append("file", file.file); // 要提交给后台的文件
      minioChooseBucketUpload(fd)
        .then((response) => {
          this.problemList[this.url_index].files.splice(
            this.problemList[this.url_index].files.indexOf(file),
            1
          );
          let obj = {};
          console.log("提交文件", response.msg);
          obj.url = response.msg;
          console.log(this.problemList[this.url_index]);
          this.problemList[this.url_index].files.push(obj);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.text {
  text-align: left;
  font-size: 0.25rem;
}
.upload-img {
  display: flex;
  text-align: left;
  padding: 0.2rem 0.32rem;
  .text {
    // width: 10em;
    width: 8.7em;
    font-size: 0.28rem;
    margin-right: 0.24rem;
  }
}
</style>

<style lang="scss">
.car-detail {
  .van-field__control:read-only {
    display: flex;
    flex-wrap: wrap;
  }
}
</style>
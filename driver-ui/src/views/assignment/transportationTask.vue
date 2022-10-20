<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-tabs v-model="activeName">
        <van-tab title="任务管理" name="a">
          <div class="container">
            <div class="div" @click="show = true">
              <div class="img">
                <img src="@/assets/img/点检.png" alt="" srcset="" />
              </div>
              运输点检
            </div>
            <div class="div" @click="handleRouter">
              <div class="img">
                <img src="@/assets/img/异常.png" alt="" srcset="" />
              </div>
              异常上报
            </div>
          </div>
          <van-cell
            title="实际出发时间："
            :value="taskForm.taskStartDate"
            title-class="title-class"
            value-class="value-class"
          />
          <van-field
            name="uploader"
            colon
            label-align="right"
            label-width="3.2rem"
            label="装货图片"
          >
            <template #input>
              <van-uploader
                v-model="loadingFileList"
                :after-read="readClick"
                multiple
              />
            </template>
          </van-field>
          <van-cell
            title="实际到达时间："
            :value="taskForm.taskEndDate"
            title-class="title-class"
            value-class="value-class"
          />
          <van-field
            name="uploader"
            colon
            label-align="right"
            label-width="3.2rem"
            label="卸货图片"
          >
            <template #input>
              <van-uploader
                v-model="unloadFilesList"
                :after-read="readClick1"
                multiple
              />
            </template>
          </van-field>
          <van-button
            round
            type="info"
            style="margin: 1rem 0; width: 50%"
            @click="handleCom"
            >提交</van-button
          >
        </van-tab>
        <van-tab title="任务详情" name="b">
          <van-cell
            title="运单号："
            :value="taskForm.shipmentXid"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="运单类型："
            :value="taskForm.shipmentTypeEnum"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="客户名称："
            :value="taskForm.customerName"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="线路名称："
            :value="taskForm.routeName"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="发货联系人："
            :value="taskForm.consignor"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="发货联系电话："
            :value="taskForm.consignorTel"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="收货联系人："
            :value="taskForm.consignee"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="收货联系电话："
            :value="taskForm.consigneeTel"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="主车车牌号："
            :value="taskForm.mainLicensePlate"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="挂车车牌号："
            :value="taskForm.hangLicensePlate"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="司机姓名："
            :value="taskForm.driverName"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="副司机姓名："
            :value="taskForm.viceDriverName"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="计划提货时间："
            :value="taskForm.pickTime"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="计划送达时间："
            :value="taskForm.planDeliveryTime"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="总数量："
            :value="taskForm.totalCount"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="总重量："
            :value="taskForm.totalWeight"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="总体积："
            :value="taskForm.totalVolume"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="总包装数量："
            :value="taskForm.totalPackCount"
            title-class="title-class"
            value-class="value-class"
          />
          <div
            v-for="item in taskForm.tTransportTaskPartsVmList"
            :key="item.id"
          >
            <van-cell
              title="订单编号："
              :value="item.orderOn"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="业务类型："
              :value="item.type"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="零件编号："
              :value="item.partsCode"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="零件名称："
              :value="item.partsName"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="零件类型："
              :value="item.partsType"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="零件数量："
              :value="item.partsCount"
              title-class="title-class"
              value-class="value-class"
            />
          </div>
          <div
            v-for="item in taskForm.tTransportTaskShipmentStopVmList"
            :key="item.id"
          >
            <van-cell
              title="站点编号："
              :value="item.shipmentStopXid"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="站点名称："
              :value="item.shipmentStopName"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="停靠地址："
              :value="item.address"
              title-class="title-class"
              value-class="value-class"
            />
          </div>

          <van-cell
            title="计划离开时间："
            :value="taskForm.pickTime"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="计划到达时间："
            :value="taskForm.planDeliveryTime"
            title-class="title-class"
            value-class="value-class"
          />

          <div style="border-top: 0.06rem solid #333">
            <van-cell
              title="定点加油站："
              title-style="font-weight:600;text-align:left"
            />
            <div v-for="item in taskForm.fFuelPlaceItems" :key="item.id">
              <van-cell
                title="加油站名称："
                :value="item.name"
                title-class="title-class"
                value-class="value-class"
              />
              <van-cell
                title="位置："
                :value="item.address"
                title-class="title-class"
                value-class="value-class"
              />
            </div>
          </div>
        </van-tab>
      </van-tabs>
    </div>
    <van-action-sheet v-model="show" :actions="actions" @select="onSelect" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { upload } from "@/components/upload";
import { Toast } from "vant";
import {
  transportTaskM03TransportTaskInfo,
  TransportTaskUpload,
} from "@/api/assignment/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      taskForm: {},
      activeName: "a",
      loadingFileList: [],
      unloadFilesList: [],
      show: false,
      actions: [
        { name: "发车点检", value: 1 },
        { name: "在途点检", value: 2 },
        { name: "归队点检", value: 3 },
      ],
    };
  },
  mounted() {
    this.$route.meta.title = "运输任务";
    transportTaskM03TransportTaskInfo({ id: this.$route.params.id }).then(
      (res) => {
        this.taskForm = res.data;

        let arr = [];
        let arr1 = [];

        if (res.data.loadingFileList) {
          for (const item of res.data.loadingFileList) {
            arr.push({ url: item });
          }
          this.loadingFileList = arr;
        }
        if (res.data.unloadFilesList) {
          for (const item of res.data.unloadFilesList) {
            arr1.push({ url: item });
          }
          this.unloadFilesList = arr1;
        }
      }
    );
  },
  methods: {
    handleCom() {
      let { loadingFileList, unloadFilesList } = this;
      let arr = [],
        arr1 = [];
      for (const item of loadingFileList) {
        arr.push(item.url);
      }
      for (const item of unloadFilesList) {
        arr1.push(item.url);
      }
      loadingFileList = arr;
      unloadFilesList = arr1;
         TransportTaskUpload({
            id: this.$route.params.id,
            unloadFilesList,
            loadingFileList
          }).then((res) => {
            console.log(res);
            Toast(res.msg);
          });
      // console.log(loadingFileList, unloadFilesList);
    },
    handleRouter() {
      this.$router.push("/exceptionReporting/" + this.$route.params.id);
    },
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      let { name, value } = item;
      this.show = false;
      this.$router.push({
        path: "/spotCheck/" + this.$route.params.id,
        query: { name, value },
      });
      this.$route.meta.title = name;
      Toast(item.name);
    },
    readClick(file, detail) {
      // this.loadingFileList.splice(this.loadingFileList.indexOf(file), 1);
      this.loadingFileList.length--;
      upload(file, detail).then((res) => {
        let obj = {};
        obj.url = res;
        this.loadingFileList.push(obj);
        console.log(this.loadingFileList);
        //   TransportTaskUpload({
        //     id: this.$route.params.id,
        //     unloadFilesList: [res],
        //   }).then((res) => {
        //     console.log(res);
        //     Toast(res.msg);
        //   });
      });
    },
    readClick1(file, detail) {
      this.unloadFilesList.length--;
      upload(file, detail).then((res) => {
        let obj = {};
        obj.url = res;
        this.unloadFilesList.push(obj);
        console.log(this.unloadFilesList);
        // TransportTaskUpload({
        //   id: this.$route.params.id,
        //   loadingFileList: [res],
        // }).then((res) => {
        //   console.log(res);
        //   Toast(res.msg);
        // });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  border: 0.03rem solid #c8c9cc;
  border-left: 0;
  border-right: 0;
  margin-top: 0.2rem;
  .div {
    flex: 1;
    // height: 1.5rem;
    margin: 0.5rem;
    .img {
      height: 1rem;
      margin-bottom: 0.3rem;
      img {
        width: 1rem;
      }
    }
  }
}
.title-class {
  text-align: right;
  margin-right: 0.7rem;
}
.value-class {
  text-align: left;
}
</style>
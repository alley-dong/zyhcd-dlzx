<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-tabs v-model="active">
        <van-tab title="任务详情">
          <div>
            <div class="top-text">时间详情</div>
            <van-cell
              title="任务状态："
              :value="form.statusEnum"
              title-class="title-class"
              value-class="value-class"
            />

            <van-cell
              title="开始时间："
              :value="form.taskStartTime"
              title-class="title-class"
              value-class="value-class"
            />
            <div class="upload-img">
              <div class="text">装货图片：</div>
              <div>
                <div v-for="(item, index) in form.loadingFileList" :key="index">
                  <!-- taskExceptionFileList -->
                  <van-image
                    @click="handleClick(item.files)"
                    width="100"
                    height="100"
                    :src="item.files"
                  />
                </div>
              </div>
            </div>
            <van-cell
              title="结束时间："
              :value="form.taskEndTime"
              title-class="title-class"
              value-class="value-class"
            />
            <div class="upload-img">
              <div class="text">卸货图片：</div>
              <div>
                <div v-for="(item, index) in form.unloadFilesList" :key="index">
                  <!-- taskExceptionFileList -->
                  <van-image
                    @click="handleClick(item.files)"
                    width="100"
                    height="100"
                    :src="item.files"
                  />
                </div>
              </div>
            </div>

            <div class="xian"></div>
            <div class="top-text">任务详情</div>
            <van-cell
              title="运单号："
              :value="form.shipmentXid"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="线路名称："
              :value="form.routeName"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="运单类型："
              :value="form.typeEnum"
              title-class="title-class"
              value-class="value-class"
            />
            <!-- <van-cell
              title="客户名称："
              :value="form.typeEnum"
              title-class="title-class"
              value-class="value-class"
            /> -->
            <van-cell
              title="客户名称："
              :value="form.customerName"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="发货联系人："
              :value="form.consignor"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="发货联系电话："
              :value="form.consignorTel"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="收货联系人："
              :value="form.consignee"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="收货联系电话："
              :value="form.consigneeTel"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="主车车牌号："
              :value="form.mainLicensePlate"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="挂车车牌号："
              :value="form.hangLicensePlate"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="司机姓名："
              :value="form.driverName"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="副司机姓名："
              :value="form.viceDriverName"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="计划提货时间："
              :value="form.pickTime"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="计划送达时间："
              :value="form.planDeliveryTime"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="总数量："
              :value="form.totalCount"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="总重量："
              :value="form.totalWeight"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="总体积："
              :value="form.totalVolume"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="总包装数量："
              :value="form.totalPackCount"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="站点编号："
              :value="form.shipmentStopXid"
              title-class="title-class"
              value-class="value-class"
            />
            <van-cell
              title="站点名称："
              :value="form.shipmentStopName"
              title-class="title-class"
              value-class="value-class"
            />
            <div v-for="item in form.tTransportTaskPartsVmList" :key="item.id">
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
                :value="item.partsTypeEnum"
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
            <div class="xian"></div>
            <div class="top-text">定点加油站：</div>
            <div v-for="item in form.fFuelPlaceItems" :key="item.id">
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
        <van-tab title="点检记录">
          <div
            class="box"
            v-for="item in dataList"
            @click="handlerotClick(item)"
            :key="item.id"
          >
            <van-cell
              :title="item.typeEnum"
              title-style="text-align:left;font-weight:600"
              :border="false"
            />
            <div
              style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem"
            >
              <div class="text1">点检时间：{{ item.createTime }}</div>
            </div>
          </div></van-tab
        >
        <van-tab title="异常状况" v-if="transportForm">
          <van-cell
            title="异常情况描述："
            :value="transportForm.remarks"
            title-class="title-class"
            value-class="value-class" />
          <div class="upload-img">
            <div class="text">上传照片：</div>
            <div
              v-for="(item, index) in transportForm.taskExceptionFileList"
              :key="index"
            >
              <!-- taskExceptionFileList -->
              <van-image
                @click="handleClick(item)"
                width="100"
                height="100"
                :src="item"
              />
            </div>
          </div>
          <van-cell
            title="审批人："
            :value="transportForm.reviewUser"
            title-class="title-class"
            value-class="value-class" />
          <van-cell
            title="状态："
            :value="transportForm.statusEnum"
            title-class="title-class"
            value-class="value-class"
        /></van-tab>
      </van-tabs>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import {
  transportTaskM03TransportTaskInfo,
  transportTaskM15TaskInspectList,
  transportTaskM07TransportTaskExceptionInfo,
} from "@/api/assignment/index";
import { ImagePreview } from "vant";

export default {
  components: {
    Header,
  },
  filters: {
    status: function (value) {
      return value;
    },
  },
  methods: {
    handlerotClick(item) {
      // console.log(row);
      let { typeEnum: name, type:value } = item;
      console.log(name);
      this.show = false;
      this.$router.push({
        path: "/spotCheck/" + item.id,
        query: { name, value ,bool:true},
      });
      this.$route.meta.title = name;
      Toast(name);
    },
    // 查看图片
    handleClick(url) {
      ImagePreview([url]);
    },
  },
  mounted() {
    transportTaskM03TransportTaskInfo({ id: this.$route.params.id }).then(
      (res) => {
        console.log(res);

        this.form = res.data;
      }
    );
    transportTaskM15TaskInspectList({ id: this.$route.params.id }).then(
      (res) => {
        console.log(res);
        this.dataList = res.rows;
      }
    );
    transportTaskM07TransportTaskExceptionInfo({
      id: this.$route.params.id,
    }).then((res) => {
      console.log(res);
      this.transportForm = res.data;
    });
  },
  data() {
    return {
      active: 0,
      form: {},
      dataList: [],
      transportForm: {},
    };
  },
};
</script>

<style lang="scss" scoped>
.top-text {
  font-weight: 800;
  text-align: left;
  height: 1rem;
  line-height: 1.3rem;
  padding-left: 0.5rem;
}
.upload-img {
  display: flex;
  //   text-align: left;
  text-align: left;
  padding: 0.2rem 0.32rem;
  .text {
    // width: 10em;
    width: 10.9em;
    font-size: 0.28rem;
    margin-right: 0.7rem;
  }
}
.xian {
  width: 100%;
  height: 0.1rem;
  background: #333;
}
.title-class {
  text-align: left;
  margin-right: 0.7rem;
}
.value-class {
  text-align: left;
}
.text1 {
  margin-bottom: 0.2rem;
}
</style>
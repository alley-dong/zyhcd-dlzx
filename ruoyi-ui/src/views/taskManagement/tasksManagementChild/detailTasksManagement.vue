<template>
  <div class="app-container">
    <!-- 任务管理 详情 -->
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <!-- <el-button type="primary" @click="editClick">提交</el-button> -->
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">任务信息</div>
      <div class="xian"></div>
    </div>
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" size="medium" label-width="110px">
        <el-col :span="12">
          <el-form-item label="运单号" prop="shipmentXid">
            <div>{{ formData.shipmentXid }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="线路名称" prop="routeName">
            <div>{{ formData.routeName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="运单类型" prop="shipmentType">
            <div>{{ formData.shipmentType == 1 ? "长途" : "短途" }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户名称" prop="customerName">
            <div>{{ formData.customerName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发货联系人" prop="consignor">
            <div>{{ formData.consignor }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发货联系电话" prop="consignorTel">
            <div>{{ formData.consignorTel }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="收货联系人" prop="consignee">
            <div>{{ formData.consignee }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="收货联系电话" prop="consigneeTel">
            <div>{{ formData.consigneeTel }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主车车牌号" prop="mainLicensePlate">
            <div>{{ formData.mainLicensePlate }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主车车型" prop="mainVehicleModelName">
            <div>{{ formData.mainVehicleModelName }}</div>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="挂车车牌号" prop="hangLicensePlate">
            <div>{{ formData.hangLicensePlate }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="挂车车型" prop="hangVehicleModelName">
            <div>{{ formData.hangVehicleModelName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="司机姓名" prop="driverName">
            <div>{{ formData.driverName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="副司机姓名" prop="viceDriverName">
            <div>{{ formData.viceDriverName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划提货时间" prop="pickTime">
            <div>{{ formData.pickTime }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="计划送达时间" prop="planDeliveryTime">
            <div>{{ formData.planDeliveryTime }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总数量(个)" prop="totalCount">
            <div>{{ formData.totalCount }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总重量(千克)" prop="totalWeight">
            <div>{{ formData.totalWeight }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总体积(立方米)" prop="totalVolume">
            <div>{{ formData.totalVolume }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总包装数量(个)" prop="totalPackCount">
            <div>{{ formData.totalPackCount }}</div>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <div class="border">
      <div class="text">站点信息</div>
      <div class="xian"></div>
    </div>
    <el-table :data="formData.tTransportTaskShipmentStopVmList">
      <el-table-column label="站点编号" align="center" prop="shipmentStopXid" />
      <el-table-column
        label="站点名称"
        align="center"
        prop="shipmentStopName"
      />
      <!-- <el-table-column label="业务类型" align="center" prop="shipmentType">
          <template slot-scope="scope">
            {{ scope.row.shipmentType == 1 ? "长途" : "短途" }}
          </template>
        </el-table-column> -->
      <el-table-column
        label="计划到达时间"
        align="center"
        prop="plannedArrival"
      />
      <el-table-column
        label="计划离开时间"
        align="center"
        prop="plannedDeparture"
      />
      <el-table-column label="停靠地址" align="center" prop="address" />
    </el-table>
    <div class="border">
      <div class="text">订单信息</div>
      <div class="xian"></div>
    </div>
    <el-table :data="dataList">
      <el-table-column label="订单编号" align="center" prop="orderOn" />
      <el-table-column label="业务类型" align="center" prop="typeEnum" />
      <!-- <el-table-column label="业务类型" align="center" prop="shipmentType">
          <template slot-scope="scope">
            {{ scope.row.shipmentType == 1 ? "长途" : "短途" }}
          </template>
        </el-table-column> -->
      <el-table-column label="零件编号" align="center" prop="partsCode" />
      <el-table-column label="零件名称" align="center" prop="partsName" />
      <el-table-column label="零件类型" align="center" prop="partsTypeEnum" />
      <el-table-column label="零件数量" align="center" prop="partsCount" />
    </el-table>
    <div class="border">
      <div class="text">托盘信息</div>
      <div class="xian"></div>
    </div>
    <el-table :data="formData.tTransportTaskTrayVmList">
      <el-table-column label="运单编号" align="center" prop="shipmentXid" />
      <!-- <el-table-column label="业务类型" align="center" prop="typeEnum" /> -->
      <!-- <el-table-column label="业务类型" align="center" prop="shipmentType">
          <template slot-scope="scope">
            {{ scope.row.shipmentType == 1 ? "长途" : "短途" }}
          </template>
        </el-table-column> -->
      <el-table-column
        label="托盘名称"
        align="center"
        prop="tranHandlingUnitName"
      />
      <el-table-column label="托盘数量" align="center" prop="shipUnitCount" />
    </el-table>
    <div v-if="isBool">
      <div class="border">
        <div class="text">任务详情</div>
        <div class="xian"></div>
      </div>
      <el-table :data="tableList">
        <el-table-column label="出发/到达时间" align="center" prop="time" />
        <el-table-column label="装卸货图片" align="center" prop="imges">
          <template slot-scope="scope">
            <div
              v-for="(item, index) in scope.row.imges"
              :key="index"
              style="width: 100px; height: 100px;display:inline-block;margin-right:20px"
            >
              <!-- <img :src="item" alt="" style="width:100%;height:100%" /> -->
               <el-image 
    style="width:100%;height:100%"
    :src="item" 
    :preview-src-list="scope.row.imges">
  </el-image>
            </div>
            <!-- {{ scope.row.shipmentType == 1 ? "长途" : "短途" }} -->
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import { transportTaskP04TransportTaskInfo } from "@/api/taskManagement/index";
export default {
  components: {},
  props: [],
  data() {
    return {
      tableList: [
        // {
        //   time: "2020.09.16 14:25",
        //   imges: [
        //     "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3791918726,2864900975&fm=26&gp=0.jpg",
        //     "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3791918726,2864900975&fm=26&gp=0.jpg"
        //   ]
        // }
      ],
      isBool: false,
      dataList: [],
      formData: {
        shipmentXid: undefined,
        routeName: undefined,
        shipmentType: undefined,
        customerName: undefined,
        consignor: undefined,
        consignorTel: undefined,
        consignee: undefined,
        consigneeTel: undefined,
        mainLicensePlate: undefined,
        mainVehicleModelName: undefined,
        hangLicensePlate: undefined,
        hangVehicleModelName: undefined,
        driverName: undefined,
        viceDriverName: undefined,
        pickTime: undefined,
        planDeliveryTime: undefined,
        totalCount: undefined,
        totalWeight: undefined,
        totalVolume: undefined,
        totalPackCount: undefined
      }
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    console.log(this.$route.params.id, this.$route.query.shipmentType);
    if (this.$route.query.shipmentType == 1) {
      this.isBool = true;
    }
    transportTaskP04TransportTaskInfo({ id: this.$route.params.id }).then(
      res => {
        this.formData = res.data;
        this.dataList = res.data.tTransportTaskPartsVmList;
        /* 
        loadingFileList	装货照片
        unloadFilesList 	卸货照片
        taskStartTime 		任务开始时间
        taskEndTime 		任务结束时间
        */
        let arr = [];
        let { loadingFileList, unloadFilesList, taskStartDate, taskEndDate } = res.data;
        arr.push({time:taskStartDate,imges:loadingFileList});
        arr.push({time:taskEndDate,imges:unloadFilesList});
        this.tableList = arr
        // console.log(); //
      }
    );
  },
  methods: {
    backClick() {
      this.$router.go(-1);
    },
    editClick() {
      console.log("修改");
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    }
  }
};
</script>
<style lang="scss" scoped>
.border {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  .text {
    width: 80px;
    text-align: right;
    line-height: 40px;
    margin-right: 30px;
  }
  .xian {
    flex: 1;
    border-bottom: 3px solid #333399;
    height: 50%;
  }
}
</style>
<style></style>

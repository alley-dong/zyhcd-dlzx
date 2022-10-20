<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <!-- <el-button type="primary" @click="submitForm"
          >审核</el-button
        > -->
        <el-button @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">事故信息</div>
      <div class="line"></div>
    </div>

    <div style="margin-top:20px;">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="100px"
        >
          <el-col :span="12">
            <el-form-item label="驾驶员" prop="driverName">
              <el-input
                v-model="formData.driverName"
                placeholder="请输入驾驶员"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车牌号" prop="vehicleName">
              <el-input
                v-model="formData.vehicleName"
                placeholder="请输入车牌号"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="事故地点" prop="accidentPlace">
              <el-input
                v-model="formData.accidentPlace"
                placeholder="请输入事故地点"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="事故时间" prop="accidentTime">
              <el-input
                v-model="formData.accidentTime"
                placeholder="请输入事故时间"
                show-word-limit
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>

    <el-row>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="200px"
      >
        <el-form-item label="事故照片:"> </el-form-item>
        <el-col
          :span="3"
          :push="3"
          v-for="(item, index) in formData.accidentFileList"
          :key="index"
        >
          <el-image :src="item" style="width: 150px; height: 150px" fit="fit" v-if="item">
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <el-row>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="200px"
      >
        <el-form-item label="双方驾驶证（正本、副本）:"> </el-form-item>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.oneSideDriveLicenceCardJust"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.oneSideDriveLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.oneSideDriveLicenceCardAgainst"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.oneSideDriveLicenceCardAgainst"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.theOtherDriveLicenceCardJust"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.theOtherDriveLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.theOtherDriveLicenceCardAgainst"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.theOtherDriveLicenceCardAgainst"
          >
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <el-row>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="200px"
      >
        <el-form-item label="双方行驶证（正本、副本）:"> </el-form-item>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.oneSideTravelLicenceCardJust"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.oneSideTravelLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.oneSideTravelLicenceCardAgainst"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.oneSideTravelLicenceCardAgainst"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.theOtherTravelLicenceCardJust"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.theOtherTravelLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.theOtherTravelLicenceCardAgainst"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.theOtherTravelLicenceCardAgainst"
          >
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <el-row>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="auto"
      >
        <el-form-item label="驾驶员身份证（正面、背面）："> </el-form-item>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.driverIdCardJust"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.driverIdCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.driverIdCardAgainst"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.driverIdCardAgainst"
          >
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <el-row>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="auto"
      >
        <el-form-item label="车辆被保险人身份证（正面、背面）：">
        </el-form-item>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.carTheInsuredIdCardJust"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.carTheInsuredIdCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.carTheInsuredIdCardAgainst"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.carTheInsuredIdCardAgainst"
          >
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <el-row>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="200px"
      >
        <el-form-item label="事故责任认定书:"> </el-form-item>
        <el-col :span="3" :push="3" >
          <el-image
            :src="formData.liabilityStatement"
            fit="fit"
            style="width: 150px; height: 150px"
            v-if="formData.liabilityStatement"
          >
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <el-row>
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="200px"
      >
        <el-form-item label="其他"> </el-form-item>
        <el-col
          :span="3"
          :push="3"
          v-for="(item, index1) in formData.partsFilesList"
          :key="index1"
        >
          <el-image :src="item" fit="fit" style="width: 150px; height: 150px" v-if="item">
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <el-form ref="elForm">
      <div class="wrap">
        <div class="text">审批记录</div>
        <div class="line"></div>
      </div>
      <el-table :data="cardList" border>
        <el-table-column label="审批级别" align="center" prop="typeEnum" />
        <el-table-column label="审批时间" align="center" prop="reviewTime" />
        <el-table-column label="审批角色" align="center" prop="roleName" />
        <el-table-column label="审批人姓名" align="center" prop="userName" />
        <!-- <el-table-column label="审批状态" align="center" >
          <template slot-scope="scope">
              <span v-for="scope.row.status === 1">待审核</span>
          </template>
      <el-table-column/> -->
        <el-table-column label="审核状态" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.status === 1">待审核</span>
            <span v-else-if="scope.row.status === 2">已审核</span>
            <span v-else-if="scope.row.status === 3">已驳回</span>
            <span v-else>-</span>
          </template>
        </el-table-column>
        <el-table-column
          label="备注"
          align="center"
          class-name="small-padding fixed-width"
          prop="reviewRemarks"
        >
        </el-table-column>
      </el-table>
    </el-form>
  </div>
</template>

<script>
import {
  etcCardBox, //车牌号下拉
  cardEdit, //数据回显
  cardSave //数据保存
} from "@/api/etc/card";
import {
  L03SafeAccidentInfo,
  L02SafeAccidentCheck
} from "@/api/safetyManagement/trafficAccidentManagementExaminationAndApproval";
import { sysExamineTaskList } from "@/api/payment/subsidySee";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        createTime: "",
        id: "",
        vehicleId: "",
        vehicleName: "",
        driverId: "",
        driverName: "",
        accidentTime: "",
        accidentPlace: "",
        longitude: "",
        latitude: "",
        status: "",
        reviewTime: "",
        reviewUser: "",
        reviewRemarks: "",
        oneSideDriveLicenceCardJust: "",
        oneSideDriveLicenceCardAgainst: "",
        oneSideTravelLicenceCardJust: "",
        oneSideTravelLicenceCardAgainst: "",
        theOtherDriveLicenceCardAgainst: "",
        theOtherTravelLicenceCardJust: "",
        theOtherTravelLicenceCardAgainst: "",
        driverIdCardJust: "",
        driverIdCardAgainst: "",
        carTheInsuredIdCardAgainst: "",
        liabilityStatement: [],
        partsFilesList: [],
        accidentFileList: ""
      },

      rules: {},

      cardList: []
    };
  },
  computed: {},
  watch: {},
  created() {
    // this.etcCardBoxData();
    this.getL01SafeAccidentList();
    this.getsysExamineTaskList();
  },
  mounted() {},
  methods: {
    //数据回显
    getL01SafeAccidentList() {
      L03SafeAccidentInfo(this.$route.params.id).then(res => {
        this.formData = res.data;
      });
    },
    // 获取审批记录
    getsysExamineTaskList() {
      var list = {
        relationId: this.$route.params.id,
        type: 4
      };
      sysExamineTaskList(list).then(res => {
        this.cardList = res.data.sysExamineUserTaskList;
      });
    },
    //补助管理-补助列表获取id
    // etcCardBoxData() {
    //   C01List({}).then((res) => {
    //       this.formData.id=res.rows[0].id
    // });
    // },
    // handleChange(val) {
    //     console.log(val,JSON.stringify(this.field101Options));
    //   for (const item of this.field101Options) {
    //     if (val == item.id) {
    //       console.log(item);

    //       console.log(item.licensePlate);
    //       this.formData.licensePlate = item.licensePlate ;
    //     }
    //   }
    // },
    // 提交
    submitForm() {
      // if(this.check.status === null){
      //   this.$message({
      //   message: '请选择审核是否通过',
      //   type: 'warning'
      // })
      // }else{
      //    L02SafeAccidentCheck(this.formData).then((res) => {
      //   this.resetForm();
      // });
      // }
      // L02SafeAccidentCheck(this.formData).then((res) => {
      //   this.resetForm();
      // });
    },
    resetForm() {
      //   this.$refs["elForm"].resetFields();
      this.$router.go(-1);
    }
  }
};
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
  width: 10%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
  font-weight: 400;
}
.line {
  width: 90%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
.image {
  width: 100%;
  display: block;
}
.imgDiv{
  width: 150px;
  height: 150px;
  position: relative;
  margin-right: 10px;
  background: #F5F7FA;
}
</style>
<style>
  .imgDiv .el-image{
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
    margin: auto;
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
  }
  .imgDiv .el-image img{
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
    margin: auto;
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
  }
</style>

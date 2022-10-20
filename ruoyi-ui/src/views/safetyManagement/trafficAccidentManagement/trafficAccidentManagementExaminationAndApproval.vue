<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button
          type="primary"
          @click="submitForm"
          v-hasPermi="['pfservercar:vacation:check']"
          >审核</el-button
        >
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
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.oneSideDriveLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.oneSideDriveLicenceCardAgainst"
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.oneSideDriveLicenceCardAgainst"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.theOtherDriveLicenceCardJust"
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.theOtherDriveLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.theOtherDriveLicenceCardAgainst"
            style="width: 150px; height: 150px"
            fit="fit"
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
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.oneSideTravelLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.oneSideTravelLicenceCardAgainst"
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.oneSideTravelLicenceCardAgainst"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.theOtherTravelLicenceCardJust"
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.theOtherTravelLicenceCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.theOtherTravelLicenceCardAgainst"
            style="width: 150px; height: 150px"
            fit="fit"
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
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.driverIdCardJust"
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.driverIdCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.driverIdCardAgainst"
            style="width: 150px; height: 150px"
            fit="fit"
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
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.carTheInsuredIdCardJust"
            style="width: 150px; height: 150px"
            fit="fit"
            v-if="formData.carTheInsuredIdCardJust"
          >
          </el-image>
        </el-col>
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.carTheInsuredIdCardAgainst"
            style="width: 150px; height: 150px"
            fit="fit"
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
        <el-col :span="3" :push="3">
          <el-image
            :src="formData.liabilityStatement"
            style="width: 150px; height: 150px"
            fit="fit"
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
          <el-image :src="item" style="width: 150px; height: 150px" fit="fit" v-if="item">
          </el-image>
        </el-col>
      </el-form>
    </el-row>

    <div class="wrap">
      <div class="text">审批记录</div>
      <div class="line"></div>
    </div>
    <!-- table列表 -->
    <el-table
      border
      :data="tableData"
      style="width: 90%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <el-table-column label="审批级别" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.level + "级审批" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reviewTime | data }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批角色" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批人姓名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">待审核</span>
          <span v-else-if="scope.row.status === 3">拒绝</span>
          <span v-else-if="scope.row.status === 2">通过</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reviewRemarks }}</span>
        </template>
      </el-table-column>
    </el-table>


    <el-form ref="elForm">
      <!-- 审核结果 -->
      <div class="wrap">
        <div class="text">申请信息</div>
        <div class="line"></div>
      </div>
      <div style="width:85%; margin:0 auto;">
        <div style="margin-top:20px;">
          <el-radio v-model="radio" label="1">通过</el-radio>
          <el-radio v-model="radio" label="2">不通过</el-radio>
        </div>
        <el-input
          type="textarea"
          v-model="formData.reviewRemarks"
          placeholder="请输入原因"
          style="margin:20px 0 40px 0;"
        ></el-input>
      </div>
    </el-form>
  </div>
</template>

<script>
import {
  etcCardBox, //车牌号下拉
  cardEdit, //数据回显
  cardSave //数据保存
} from "@/api/etc/card";
import { getSysExamineTaskList } from "@/api/com";
import {
  L03SafeAccidentInfo,
  L02SafeAccidentCheck
} from "@/api/safetyManagement/trafficAccidentManagementExaminationAndApproval";
export default {
  components: {},
  props: [],
  data() {
    return {
      tableData:[],
      formData: {
        // driverName: "",
        // vehicleName: "",
        // accidentPlace: "",
        // accidentTime: "",
        // id: "",
        // status: "",
        // reviewRemarks: "",
        // userid: "",
        // reviewUser: "",
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
      radio: "1",

      rules: {},

      cardList: []
    };
  },
  computed: {},
  watch: {},
  created() {
    // this.etcCardBoxData();
    this.getL01SafeAccidentList();

    let obj = {
      relationId: this.$route.params.id,
    };
    getSysExamineTaskList(obj).then((res) => {
      // debugger
      console.log('.res', res)
      this.tableData = res.data.sysExamineUserTaskList;
    });
  },
  mounted() {},
  methods: {
    //数据回显
    getL01SafeAccidentList() {
      L03SafeAccidentInfo(this.$route.params.id).then(res => {
        this.formData = res.data;
        this.check.id = res.data.id;
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
      this.formData.status = this.radio;
      L02SafeAccidentCheck(this.formData).then(res => {
        this.resetForm();
        this.$message({
          message: res.msg,
          type: res.msg == "操作成功" ? "success" : "warning"
        });
      });
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
</style>

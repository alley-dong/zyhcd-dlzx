<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick()">返回</el-button>
        <!--size="small"  -->
        <!-- <el-button type="primary" @click="editClick()">编辑</el-button> -->
      </div>
    </div>
    <div class="border">
      <div class="text">基本信息</div>
      <div class="xian"></div>
    </div>
    <div class="box">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          style="margin-left: 120px"
          :model="formData"
          size="medium"
          label-width="150px"
        >
          <el-col :span="12">
            <el-form-item label="主车车牌号">
              <div style="text-align:left">{{ formData.mainLicensePlate }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主车车型">
              <div style="text-align:left">{{ formData.mainVehicleModelName }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车牌号">
              <div style="text-align:left">{{ formData.hangLicensePlate }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车型">
              <div style="text-align:left">{{ formData.hangVehicleModelName }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="故障时公里数">
              <div style="text-align:left">{{ formData.mileage }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行驶路线">
              <div style="text-align:left">{{ formData.routeName }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发生故障地点">
              <div style="text-align:left">{{ formData.accidentLocation }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="故障时荷载/总质量">
              <div style="text-align:left">{{ formData.totalMass }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="故障时路况">
              <div style="text-align:left">{{ formData.road }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="	故障时天气">
              <div style="text-align:left">{{ formData.weather }}</div>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>
    <div class="border">
      <div class="text">维修信息</div>
      <div class="xian"></div>
    </div>
    <div class="box">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          style="margin-left: 120px"
          :model="formData"
          size="medium"
          label-width="150px"
        >
          <el-col :span="24">
            <el-form-item label="维修单号">
              <div style="text-align:left">{{ formData.code }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类别">
              <div style="text-align:left">{{ formData.type == 1 ? "主车维保" : "挂车维保" }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类型">
              <div style="text-align:left">{{ formData.repairType | repairType }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否是异地维修">
              <div style="text-align:left">{{ formData.isOther == "1" ? "是" : "否" }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="视频">
              <div>
                <video
            v-if="formData.accidentVideo"
                  style="width: 440px; height: 350px"
                  controls="controls"
                  :src="formData.accidentVideo"
                ></video>
              </div>
            </el-form-item>
          </el-col>
          <div v-for="item in formData.repairItemDate" :key="item.id">
            <el-col :span="24">
              <el-form-item label="维修保养项">
                <div style="text-align:left">{{ item.itemName }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="维修原因">
                <div style="text-align:left">{{ item.remarks }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="图片">
                <div style="text-align: left">
                  <img
                    v-for="im in item.fileList"
                    :src="im"
                    alt=""
                    srcset=""
                    style="width:200px;height:200px;margin-right:20px"
                  />
                </div>
              </el-form-item>
            </el-col>
          </div>

          <el-col :span="24">
            <el-form-item label="维修服务站">
              <div style="text-align:left">{{ formData.garageName }}</div>
            </el-form-item>
          </el-col>
          <!-- <el-col :span="24">
            <el-form-item label="车辆维修单">
              <div>{{ formData.remarks }}</div>
            </el-form-item>
          </el-col> -->
          <!--TODO:  车辆维修单-->
          <!-- <el-col :span="24">
            <el-form-item label="车辆维修单">
              <div>
                <img
                  v-for="(item, index) in formData.remarks"
                  :key="index"
                  :src="item"
                  alt=""
                  srcset=""
                  style="width: 200px; height: 200px; margin-right: 20px"
                />
              </div>
            </el-form-item>
          </el-col> -->
        </el-form>
      </el-row>
    </div>
    <div class="border">
      <div class="text">维修明细</div>
      <div class="xian"></div>
    </div>
    <el-table
      border
      :data="tableData1"
      style="width: 98%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <el-table-column label="车型" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.vehicleModelName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单位" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.unit }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单价（元）" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.unitPrice }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工时数" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.duration }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.number }}</span>
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.amount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.remarks }}</span>
        </template>
      </el-table-column>
      <!--TODO:  统计-->
      <el-table-column label="统计" align="center">
        <template slot-scope="scope">
          <span
            >本月第{{ scope.row.monthNumber }}次，本年第{{
              scope.row.yearNumber
            }}次</span
          >
        </template>
      </el-table-column>
    </el-table>
    <!--<el-table
      border
      :data="tableData2"
      style="width: 98%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <el-table-column label="车型" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.vehicleModelName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单位" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.unit }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单价（元）" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.unitPrice }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工时数" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.duration }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.number }}</span>
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.amount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.remarks }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <span> 新增</span>
        </template>
      </el-table-column>
      &lt;!&ndash;TODO:  统计&ndash;&gt;
      <el-table-column label="统计" align="center">
        <template slot-scope="scope">
          <span
            >本月第{{ scope.row.monthNumber }}次，本年第{{
              scope.row.yearNumber
            }}次</span
          >
        </template>
      </el-table-column>
    </el-table>-->
    <div style="margin-bottom: 10px">备注：{{ formData.reviewRemarks }}</div>
    <!--<div style="margin-bottom: 20px">
      合计金额：
      {{ total + total2 }}
    </div>-->


    <div class="border">
      <div class="text">上传附件</div>
      <div class="xian"></div>
    </div>
    <div v-if="formData.fileList" style="text-align:left">
      <img
        v-for="(item, index) in formData.fileList"
        :key="index"
        :src="item"
        style="width:200px;height:200px;margin-right:20px"
      />
    </div>

    <div class="border">
      <div class="text">审批记录</div>
      <div class="xian"></div>
    </div>
    <el-table
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <el-table-column label="审批级别" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.level + "级审批" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reviewTime }}</span>
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
          <span v-else-if="scope.row.status ===2">通过</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.reviewRemarks }}</span>
        </template>
      </el-table-column>
    </el-table>
    <!-- <div class="border">
      <div class="text">审核结果</div>
      <div class="xian"></div>
    </div>
    <div class="box" style="margin-left: 55px">
      <el-row :gutter="15">
        <el-col :span="24" style="margin-bottom: 20px">
          <el-radio-group v-model="formData1.status">
            <el-radio
              v-for="(item, index) in field101Options"
              :key="index"
              :label="item.value"
              :disabled="item.disabled"
              >{{ item.label }}</el-radio
            >
          </el-radio-group>
        </el-col>
        <el-col :span="24" style="margin-bottom: 20px">
          <div style="display: flex">
            <div style="line-height: 36px; margin-right: 20px">维修服务站</div>
            <div>
              <el-select
                v-model="formData1.userId"
                placeholder="请选择维修服务站"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="item in option"
                  :key="item.id"
                  :value="item.id"
                  :label="item.name"
                >
                </el-option>
              </el-select>
            </div>
          </div>
        </el-col>
        <el-col :span="24" style="margin-bottom: 20px">
          <el-input
            v-model="formData1.reviewRemarks"
            type="textarea"
            placeholder="请输入"
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-col>
      </el-row> -->
    <!-- </div> -->
  </div>
</template>

<script>
import {
  repairInfo,
  H10NotSubmittedList,
  H10NotSubmittedIsNewList,
  //   repairCheck,
} from "@/api/maintenance";
import { queryExamineConfigProcessList } from "@/api/com";
export default {
  data() {
    return {
      total2: undefined,
      tableData: [{}],
      tableData1: [{}],
      tableData2: [{}],
      total: undefined,
      formData: {
        mainLicensePlate: undefined,
        mainVehicleModelName: undefined,
        hangLicensePlate: undefined,
        hangVehicleModelName: undefined,
        mileage: undefined,
        routeName: undefined,
        accidentLocation: undefined,
        totalMass: undefined,
        roadCondition: undefined,
        weatherCondition: undefined,
      },
      formData1: {
        relationId: undefined,
        status: undefined,
        userId: undefined,
        reviewRemarks: undefined,
      },
      option: [],
      field101Options: [
        {
          label: "通过",
          value: 1,
        },
        {
          label: "不通过",
          value: 2,
        },
      ],
      rules: {
        mainLicensePlate: [],
      },
    };
  },
  filters: {
    repairType: function (value) {
      switch (value) {
        case 1:
          return "正常维修";
          break;
        case 2:
          return "事故维修";
          break;
        case 3:
          return "保养";
          break;
        default:
          break;
      }
    },
  },
  mounted() {
    this.formData1.relationId = this.$route.params.id;
    repairInfo(this.$route.params.id).then((res) => {
      this.formData = res.data;
    });
    H10NotSubmittedList(this.$route.params.id).then((res) => {
      console.log(res.data.detailedList);
      this.tableData1 = res.data.notSubmittedList;
      this.total = res.data.totalCost;
    });
    H10NotSubmittedIsNewList(this.$route.params.id).then((res) => {
      console.log(res.data.detailedList);
      this.tableData2 = res.data.notSubmittedIsNewList;
        this.total2 = res.data.totalCost;
    });
    this.queryExamineConfigProcessDataList();
  },
  methods: {
    // 审批
    queryExamineConfigProcessDataList() {
      let obj = {
        type: 8,
        relationId: this.$route.params.id,
      };
      queryExamineConfigProcessList(obj).then((res) => {
        // debugger
        this.tableData = res.data.sysExamineUserTaskList;
      });
    },
    backClick() {
      console.log("返回");
      this.$router.go(-1);
    },

    // editClick() {
    //   console.log("编辑");
    //   repairCheck(this.formData1).then((res) => {
    //     this.backClick();
    //   });
    // },
  },
};
</script>

<style lang="scss" scoped>
.border {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  .text {
    width: 120px;
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

<style lang="scss">
.box {
  .el-form-item__label {
    font-size: 16px;
  }
  .el-form-item__content {
    font-size: 16px;
  }
  .el-radio__inner {
    width: 18px;
    height: 18px;
  }
  .el-radio__label {
    font-size: 16px;
  }
}
</style>

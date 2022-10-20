<template>
  <div class="app-container">
    <div class="box">
      <div class="btn-group">
        <!-- <el-button type="primary" @click="submitForm">保存</el-button> -->
        <el-button type="primary" @click="$router.go(-1)">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">基本信息</div>
      <div class="line"></div>
    </div>
    <div style="display: flex; margin-top: 30px">
      <el-row :gutter="15" style="width: 100%">
        <el-form
          ref="elForm"
          :model="formData"
          size="medium"
          label-width="121px"
        >
          <el-col :span="12">
            <el-form-item label="主车车牌号" prop="orderNumber">
              {{ formData.mainLicensePlate }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主车车型" prop="orderTime">
              {{ formData.mainVehicleModelName }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车牌号" prop="status">
              {{ formData.hangLicensePlate }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车型" prop="status">
              {{ formData.hangVehicleModelName }}
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="故障时公里数" prop="status">
              {{ formData.mileage }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行驶路线" prop="status">
              {{ formData.routeName }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发生故障地点" prop="status">
              {{ formData.accidentLocation }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="故障时荷载/总质量" prop="status">
              {{ formData.totalMass }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="故障时路况" prop="status">
              {{ formData.road }}
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="故障时天气" prop="status">
              {{ formData.weather }}
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>
    <div class="wrap">
      <div class="text">维修信息</div>
      <div class="line"></div>
    </div>
    <div style="display: flex; margin-top: 30px">
      <el-row :gutter="15" style="width: 100%">
        <el-form
          ref="elForm"
          :model="formData"
          size="medium"
          label-width="121px"
        >
          <el-col :span="24">
            <el-form-item label="维修单号" prop="status">
              {{ formData.code }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类别" prop="status">
              {{ formData.type == 1 ? "主车维保" : "挂车维保" }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类型" prop="status">
              {{ formData.repairType | repairType }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否是异地维修" prop="status">
              {{ formData.isOther == 1 ? "是" : "否" }}
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
              <el-form-item label="维修保养项" prop="status">
                {{ item.itemName }}
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="维修原因" prop="status">
                {{ item.remarks }}
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="图片" prop="status">
                  <img
                    v-for="im in item.fileList"
                    :src="im"
                    alt=""
                    srcset=""
                    style="width: 200px; height: 200px; margin-right: 20px"
                  />
              </el-form-item>
            </el-col>
          </div>
          <el-col :span="24">
            <el-form-item label="维修服务站" prop="status">
              {{ formData.garageName }}
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="车辆维修单" prop="status">
              <!-- v-for="(data, index) in item.files" -->
              <img
                v-if="formData.repairDetialPhoto"
                :src="formData.repairDetialPhoto"
                alt=""
                srcset=""
                style="width: 200px; height: 200px; margin-right: 20px"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="维修完成时间" prop="status">
              {{ formData.completeTime }}
            </el-form-item>
          </el-col>
          <el-col :span="24" v-if="formData.repairDuration">
            <el-form-item label="维修时长" prop="status">
              {{
                Math.floor(formData.repairDuration / 60) +
                "时" +
                (formData.repairDuration -
                  Math.floor(formData.repairDuration / 60) * 60) +
                "分"
              }}
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>
    <div class="wrap">
      <div class="text">维修明细</div>
      <div class="line"></div>
    </div>
    <el-table border :data="educationList" style="width: 98%; margin: 0 auto">
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
    </el-table>
    <div style="margin: 10px 0">合计金额：{{ totalCost }}</div>
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
          &lt;!&ndash; <span>{{ scope.row.remarks }}</span> &ndash;&gt;
          <span> 新增</span>
        </template>
      </el-table-column>-->
      <!--TODO:  统计-->
      <!-- <el-table-column label="统计" align="center">
        <template slot-scope="scope">
          <span
            >本月第{{ scope.row.monthNumber }}次，本年第{{
              scope.row.yearNumber
            }}次</span
          >
        </template>
      </el-table-column> -->
<!--    </el-table>-->
    <div style="margin: 10px 0">备注：{{ formData.reviewRemarks }}</div>

    <div class="wrap">
      <div class="text">上传附件</div>
      <div class="line"></div>
    </div>
    <div v-if="formData.fileList" style="text-align:left">
      <img
        v-for="(item, index) in formData.fileList"
        :key="index"
        :src="item"
        style="width:200px;height:200px;margin-right:20px"
      />
    </div>

  </div>
</template>

<script>
import {
  repairH03Info,
  H10NotSubmittedList,
  H10NotSubmittedIsNewList,
} from "@/api/maintenance/index";
export default {
  filters: {
    repairType: function (value) {
      switch (value) {
        case 1:
          return "正常维修";
          break;
        case 3:
          return "保养";
          break;
        case 2:
          return "事故维修";
          break;

        default:
          break;
      }
    },
  },
  data() {
    return {
      formData: {},
      educationList: [],
      tableData2: [],
      total2: undefined,
      totalCost: undefined,
    };
  },
  mounted() {
    repairH03Info(this.$route.params.id).then((res) => {
      this.formData = res.data;
      // this.formData.repairDuration = 111
      //   this.totalCost = res.data.totalCost
    });
    H10NotSubmittedIsNewList(this.$route.params.id).then((res) => {
      console.log(res.data.detailedList);
      this.tableData2 = res.data.notSubmittedIsNewList;
      this.total2 = res.data.totalCost;
    });
    H10NotSubmittedList(this.$route.params.id).then((res) => {
      this.educationList = res.data.notSubmittedList;
      this.totalCost = res.data.totalCost;
    });
  },
};
</script>

<style lang="scss" scoped>
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
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
</style>

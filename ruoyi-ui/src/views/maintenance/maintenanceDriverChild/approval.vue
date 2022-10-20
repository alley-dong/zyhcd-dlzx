<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick()">返回</el-button>
        <el-button type="primary" @click="editClick()">提交</el-button>
        <!--size="small"  -->
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
            <el-form-item label="主车车牌号：">
              <div class="text-lf">{{ formData.mainLicensePlate }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主车车型：">
              <div class="text-lf">{{ formData.mainVehicleModelName }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车牌号：">
              <div class="text-lf">{{ formData.hangLicensePlate }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车型：">
              <div class="text-lf">{{ formData.hangVehicleModelName }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="故障时公里数：">
              <div class="text-lf">{{ formData.mileage }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行驶路线：">
              <div class="text-lf">{{ formData.routeName }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发生故障地点：">
              <div class="text-lf">{{ formData.accidentLocation }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="故障时荷载/总质量：">
              <div class="text-lf">{{ formData.totalMass }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="当前路况：">
              <div class="text-lf">{{ formData.road }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="	故障时天气：">
              <div class="text-lf">{{ formData.weather }}</div>
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
            <el-form-item label="维修单号：">
              <div class="text-lf">{{ formData.code }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类别：">
              <div class="text-lf">
                {{ formData.type == 1 ? "主车维保" : "挂车维保" }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类型：">
              <div class="text-lf">{{ formData.repairType | repairType }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否是异地维修：">
              <div class="text-lf">
                {{ formData.isOther == "1" ? "是" : "否" }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="视频：">
              <div class="text-lf">
                <video
                  v-if="formData.accidentVideo"
                  style="width: 440px; height: 350px"
                  controls="controls"
                  :src="formData.accidentVideo"
                ></video>
              </div>
            </el-form-item>
          </el-col>
                        <el-col :span="24" v-if="formData.garageName">
            <el-form-item label="维修服务站：">
              <div class='text-lf'>{{ formData.garageName }}</div>
            </el-form-item>
          </el-col>
          <div v-for="item in formData.repairItemDate" :key="item.id">
            <el-col :span="24">
              <el-form-item label="维修保养项：">
                <div class="text-lf">{{ item.itemName }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="维修原因：">
                <div class="text-lf">{{ item.remarks }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="图片：">
                <div class="text-lf">
                  <!-- v-for="(item, index) in formData.remarks"
                  :key="index" -->
                  <img
                    v-for="(imge, index) in item.fileList"
                    v-if="imge"
                    :src="imge"
                    alt=""
                    srcset=""
                    style="width: 200px; height: 200px; margin-right: 20px"
                  />
                </div>
              </el-form-item>
            </el-col>

          </div>
        </el-form>
      </el-row>
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
    <div class="border">
      <div class="text">审核结果</div>
      <div class="xian"></div>
    </div>
    <div class="box" style="margin-left: 55px">
      <el-row :gutter="15">
        <el-col :span="24" style="margin-bottom: 20px; text-align: left">
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
        <el-col
          v-if="status_show && formData.isOther == 2"
          :span="24"
          style="margin-bottom: 20px"
        >
          <div style="display: flex">
            <div style="line-height: 36px; margin-right: 20px">维修服务站</div>
            <div>
              <el-select
                v-model="formData1.garageId"
                placeholder="请选择维修服务站"
                @change="handleChange"
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
      </el-row>
    </div>
  </div>
</template>

<script>
import { repairInfo, commonSelectBox, repairCheck,commonSelectBoxList } from "@/api/maintenance";
import { queryExamineConfigProcessList } from "@/api/com";
export default {
  data() {
    return {
      tableData: [{}],
      status_show: false,
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
        garageName: undefined,
        garageId: undefined,
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
    commonSelectBoxList({}).then((res) => {
      this.option = res.data;
    });
    this.queryExamineConfigProcessDataList();
  },
  methods: {
    handleChange(val) {
      for (const item of this.option) {
        if (item.id == val) {
          this.formData1.garageName = item.name;
        }
      }
    },
    // 审批
    queryExamineConfigProcessDataList() {
      let obj = {
        type: 7,
        relationId: this.$route.params.id,
      };
      queryExamineConfigProcessList(obj).then((res) => {
        // debugger
        this.tableData = res.data.sysExamineUserTaskList;
        for (const item of res.data.sysExamineUserTaskList) {
          if (item.level == 1 && item.status == 1) {
            this.status_show = true;
          }
        }
      });
    },
    backClick() {
      console.log("返回");
      this.$router.go(-1);
    },
    editClick() {
      console.log("编辑");
      if ((this.status_show && this.formData.isOther == 2)&& this.formData1.status==1) {
        if (!this.formData1.garageId) {
          this.$message({
            message: "请选择维修服务站",
            type: "warning",
          });
          return;
        }
      }
      repairCheck(this.formData1).then((res) => {
        this.$message({
          message: res.msg,
          type: "success",
        });
        this.backClick();
      });
    },
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
.text-lf {
  text-align: left;
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

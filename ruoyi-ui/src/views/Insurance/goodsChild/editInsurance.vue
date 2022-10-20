<template>
  <div class="app-container">
    <div style="width: 100%; margin: 20px 10px 20px 0; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="editClick"
        >提交</el-button
      >
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="backClick"
        >返回</el-button
      >
    </div>
    <div class="wrap">
      <div class="text">保险信息</div>
      <div class="line"></div>
    </div>
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" size="medium" label-width="100px">
        <el-col :span="24">
          <el-form-item label="保险类型">
            <div>
              <!-- {{ formData.type  }} -->
              {{ formData.type | type }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="车牌号">
            <div>
              <el-tag
                v-for="item in formData.cInsuranceVehicleGoogds"
                :key="item.id"
                >{{ item.licensePlate }}</el-tag
              >
            </div>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="12">
          <el-form-item label="起保日期">
            <div>
              {{ formData.insuranceStartDate }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="终保日期">
            <div>
              {{ formData.insuranceEndDate }}
            </div>
          </el-form-item>
        </el-col> -->
        <el-col :span="24">
          <el-form-item label="保险项目">
            <el-table
              show-summary
              :summary-method="getSummaries"
              :data="checkItemDataList"
            >
              <el-table-column
                label="险种"
                align="center"
                prop="insuranceItemName"
              />
              <el-table-column
                label="险种类型"
                align="center"
                prop="insuranceTypeName"
              />
              <el-table-column
                label="保额(元)"
                align="center"
                prop="insuranceQuota"
              />
              <el-table-column
                label="单车保费(元)"
                align="center"
                prop="insuranceCost"
              />
              <el-table-column label="车辆数" align="center">
                <template slot-scope="scope">
                  {{ formData.cInsuranceVehicleGoogds.length }}
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="备注">
            <div>{{ formData.remarks }}</div>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>

    <div class="wrap">
      <div class="text">审批记录</div>
      <div class="line"></div>
    </div>
    <!-- TODO: -->
    <el-table :data="checkItemDataList1">
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
    <div class="wrap">
      <div class="text">审核结果</div>
      <div class="line"></div>
    </div>
    <!-- TODO: -->
    <div style="margin: 15px 0">
      <el-radio v-model="formData1.status" :label="1">通过</el-radio>
      <el-radio v-model="formData1.status" :label="2">拒绝</el-radio>
    </div>
    <el-input
      v-model="formData1.reviewRemarks"
      type="textarea"
      placeholder="请输入原因"
      :autosize="{ minRows: 4, maxRows: 4 }"
      :style="{ width: '100%' }"
    ></el-input>
  </div>
</template>

<script>
import { googdsE11Info, E03EditSave } from "@/api/system/vehicle";
import { queryExamineConfigProcessList } from "@/api/com";
export default {
  data() {
    return {
      radio: null,
      formData: {
        type: null,
        licensePlateName: null,
        insuranceStartDate: null,
        insuranceEndDat: null,
        checkItemDataList: null,
        remarks: null,
      },
      formData1: {
        reviewRemarks: null,
        status: null,
      },
      checkItemDataList: [],
      checkItemDataList1: [],
    };
  },
  mounted() {
    this.init();
  },
  filters: {
    type: function (value) {
      console.log(value);
      // 保险类型 1新车保险 2车辆续保 3货物保险
      switch (value) {
        case 1:
          return "新车保险";
        case 2:
          return "车辆续保";
        case 3:
          return "货物保险";

        default:
          break;
      }
    },
  },
  methods: {
    init() {
      this.insuranceVehicleDataList();
      this.queryExamineConfigProcessDataList();
    },
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = "合计";
          return;
        }
        const values = data.map((item) => Number(item[column.property]));
        console.log(values, index);
        // debugger
        if (index == 3) {
          console.log(values[0]);
          if (values[0]) {
            let sum = 0;
            for (const item of values) {
              item ? (sum += item) : (sum += 0);
            }
            return (sums[index] = sum*this.formData.cInsuranceVehicleGoogds.length + "元");
          } else {
            console.log(sums[index]);
            return (sums[index] = "0元");
          }
        }
      });
      return sums;
    },
    insuranceVehicleDataList() {
      // let obj = {
      //   id: this.$route.params.id,
      // };
      googdsE11Info(this.$route.params.id).then((res) => {
        console.log(res);
        this.formData = res.data;
        this.checkItemDataList = res.data.cInsuranceVehicleItems;
      });
    },
    queryExamineConfigProcessDataList() {
      let obj = {
        // type: 11,
        relationId: this.$route.params.id,
      };
      queryExamineConfigProcessList(obj).then((res) => {
        // debugger
        this.checkItemDataList1 = res.data.sysExamineUserTaskList;
      });
    },
    editClick() {
      this.formData1.id = this.formData.id
      E03EditSave(this.formData1).then().then(this.backClick());
    },
    backClick() {
      this.$router.go(-1);
    },
  },
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
</style>
<template>
  <div class="app-container">
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm">保存</el-button>
        <el-button type="primary" @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">申请信息</div>
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
            <el-form-item label="维保类别" prop="createBy">
              {{ formData.type == 1 ? "主车维保" : "挂车维保" }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维修类型" prop="etcCardNumber">
              {{ formData.repairType | repairType }}
            </el-form-item>
          </el-col>
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
          <el-col :span="12">
            <el-form-item label="故障时公里数" prop="status">
              {{ formData.mileage }}
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="天气环境" prop="status">
              {{ formData.weather }}
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
              v-if="item.files"
                  :src="item.files"
                  alt=""
                  srcset=""
                  style="width: 200px; height: 200px; margin-right: 20px"
                />
              </el-form-item>
            </el-col>
          </div>
        </el-form>
      </el-row>
    </div>
    <div class="wrap">
      <div class="text">维修明细</div>
      <div class="line"></div>
    </div>
    <el-form :inline="true" class="demo-form-inline">
      <el-button
        type="primary"
        style="float: right; margin: 0 20px 10px 0"
        @click="educationClick"
        size="small"
        >新增</el-button
      >
    </el-form>
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

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="editEducationClick(scope.row)"
            plain
            type="primary"
            >编辑</el-button
          >
          <el-button
            size="small"
            @click="delEducationClick(scope.row.id)"
            type="danger"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">合计金额：{{ totalCost }}</div>

    <el-dialog :visible.sync="open" title="新增">
      <el-form
        ref="elForm"
        :model="formDataAdd"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="车型" prop="vehicleModel">
          <el-input
            v-model="formDataAdd.vehicleModelName"
            placeholder="请输入车型"
            clearable
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input
            v-model="formDataAdd.name"
            placeholder="请输入名称"
            clearable
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input
            v-model="formDataAdd.unit"
            placeholder="请输入单位"
            clearable
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input
            v-model="formDataAdd.unitPrice"
            type="number"
            placeholder="请输入单价"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="工时数" prop="duration">
          <el-input
            v-model="formDataAdd.duration"
            type="number"
            placeholder="请输入工时数"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input
            v-model="formDataAdd.number"
            placeholder="请输入数量"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input
            v-model="formDataAdd.remarks"
            type="textarea"
            placeholder="请输入备注"
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
    <el-row :gutter="15" style="width: 100%">
      <el-col :span="2"> 备注 </el-col>
      <el-col :span="10">
        <el-input
          v-model="remarks"
          type="textarea"
          placeholder="请输入备注"
          :autosize="{ minRows: 4, maxRows: 4 }"
          :style="{ width: '100%' }"
        ></el-input>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  repairH03Info,
  H10NotSubmittedList,
  repairH10NotSubmittedAdd,
  commonSelectBoxH10VehicleModelByGarageId,
  repairH10NotSubmittedEditSave,
  repairH10NotSubmittedDelete,
  repairH02ReviewTwoLevel
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
      remarks: "", //请输入备注
      formData: {},
      totalCost: undefined,
      open: false,
      educationList: [],
      formDataAdd: {
          repairId:undefined,
        vehicleModel: undefined,
        name: undefined,
        unit: undefined,
        unitPrice: undefined,
        duration: undefined,
        number: undefined,
        remarks: undefined,
      },
      rules: {
        vehicleModel: [],
        name: [],
        unit: [],
        unitPrice: [],
        duration: [],
        number: [],
        remarks: [],
      },
      vehicleModelOptions: [],
    };
  },
  mounted() {
      this.formDataAdd.repairId =  this.$route.params.id
    repairH03Info(this.$route.params.id).then((res) => {
      this.formData = res.data;
      //   this.totalCost = res.data.totalCost
    });
    this.getList();
    commonSelectBoxH10VehicleModelByGarageId(this.$route.query.garageId).then(
      (res) => {
        this.vehicleModelOptions = res.data;
        //   this.totalCost = res.data.totalCost
      }
    );
  },
  methods: {
        handleChange2(val) {
      for (const item of this.vehicleModelOptions) {
        if (val == item.vehicleModel) {
          this.formDataAdd.vehicleModelName = item.vehicleModelName;
          // name = item.vehicleModelName;
        }
      }
     
    },
    getList() {
      H10NotSubmittedList(this.$route.params.id).then((res) => {
        this.educationList = res.data.notSubmittedList;
        this.totalCost = res.data.totalCost;
      });
    },
    handleChange(val) {
      for (const item of this.vehicleModelOptions) {
        if (item.vehicleModel == val) {
          this.formDataAdd.vehicleModelName = item.vehicleModelName;
        }
      }
    },
    //   确定
    handelConfirm() {
      this.formDataAdd.garageName = this.formData.garageName;
      this.formDataAdd.garageId = this.formData.garageId;
      console.log("确定");
      if (this.formDataAdd.id) {
        repairH10NotSubmittedEditSave(this.formDataAdd).then((res) => {
          this.getList();
        });
      } else {
        repairH10NotSubmittedAdd(this.formDataAdd).then((res) => {
          this.getList();
        });
      }
      this.open = false;
    },
    editEducationClick(row) {
      this.formDataAdd = JSON.parse(JSON.stringify(row)) ;
      this.open = true;
    },
    delEducationClick(id) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          repairH10NotSubmittedDelete(id).then((response) => {
            console.log(response);
            that.getList();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
    },
    submitForm() {
        repairH02ReviewTwoLevel({
            id:this.$route.params.id,
            reviewRemarks:this.remarks
        }).then(this.resetForm())
    },
    resetForm() {
      this.$router.go(-1);
    },
    // 新增
    educationClick() {
      console.log("新增");
      this.open = true;
    },
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

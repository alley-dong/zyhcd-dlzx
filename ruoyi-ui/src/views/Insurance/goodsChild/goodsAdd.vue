<template>
  <div class="app-container">
    <div style="width: 100%; margin: 20px 10px 20px 0; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        :disabled="isDisabled"
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
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="车牌号" prop="field102">
        <el-button type="primary" @click="carClick" size="medium">
          选择保险车辆
        </el-button>
      </el-form-item>
      <el-form-item v-if="dynamicTags && dynamicTags.length > 0">
        <el-tag
          :key="tag.id"
          v-for="tag in dynamicTags"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)"
        >
          {{ tag.licensePlate }}
        </el-tag>
      </el-form-item>
      <el-form-item label="险种" prop="field103">
        <el-button type="primary" @click="planClick" size="medium">
          选择险种
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-table
          ref="multipleTable"
          :data="cInsuranceVehicleItemList"
          show-summary
          :summary-method="getSummaries"
        >
          <el-table-column type="selection" width="55" align="center" />
          <!-- <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column> -->

          <el-table-column label="险种类别" align="center" prop="typeName" />
          <el-table-column label="险种" align="center" prop="name" />
          <el-table-column label="保额(元)" align="center" prop="insuranceQuota" />
          <el-table-column label="单车保费(元)" align="center" prop="insuranceCost" />
          <el-table-column label="车辆数" align="center" prop="carNum" />
          <el-table-column
            label="操作"
            align="center"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="small"
                type="primary"
                plain
                @click="handleUpdate(scope.row)"
                >修改</el-button
              >
              <el-button size="small" type="danger" @click="handleDelete(scope)"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
          v-model="formData.remarks"
          type="textarea"
          placeholder="请输入备注"
          :autosize="{ minRows: 4, maxRows: 4 }"
          :style="{ width: '100%' }"
        ></el-input>
      </el-form-item>
    </el-form>

    <el-dialog :visible.sync="open1" title="编辑">
      <el-form
        ref="elForm"
        :model="dialogFormData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="险种" prop="insuranceItemName">
          <div :style="{ width: '100%' }">
            {{ dialogFormData.typeName }}
          </div>
          <!-- <el-input
            v-model="formData.insuranceItemName"
            placeholder="请输入险种"
            :style="{ width: '100%' }"
          > -->
          <!-- </el-input> -->
        </el-form-item>
        <el-form-item label="险种类型" prop="insuranceTypeName">
          <!-- <el-input
            v-model="formData.insuranceTypeName"
            placeholder="请输入险种类型"
            :style="{ width: '100%' }"
          > -->
          <!-- </el-input> -->
          <div :style="{ width: '100%' }">
            {{ dialogFormData.name }}
          </div>
        </el-form-item>
        <el-form-item label="保额(元)" prop="insuranceQuota">
          <el-input
            v-model="dialogFormData.insuranceQuota"
            placeholder="请输入保额(元)"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="单车保费(元)" prop="insuranceCost">
          <el-input
            v-model="dialogFormData.insuranceCost"
            placeholder="请输入单车保费(元)"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="dialogSaveClick">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { vehicleE07VehicleAdd } from "@/api/system/vehicle";
export default {
  components: {},
  props: [],
  data() {
    return {
      isDisabled: false,
      sum: undefined,
      open1: false,
      dynamicTags: [],
      inputVisible: false,
      inputValue: "",
      dialogFormData: {
        insuranceItemName: "",
        insuranceTypeName: "",
        insuranceQuota: "",
        insuranceCost: "",
      },
      cInsuranceVehicleItemList: [],
      formData: {
        remarks: undefined,
        cVehicleList: null, //车牌号
        cInsuranceVehicleItemList: null, //
      },
      tableData: [],
      rules: {
        remarks: [],
      },
    };
  },
  computed: {},
  watch: {},
  created() {},
  updated() {
    let num = 0;
    for (const item of this.cInsuranceVehicleItemList) {
      console.log(
        Number(item.insuranceCost) != "NaN" && Number(item.carNum) != "NaN"
      );
      if (Number(item.insuranceCost)) {
        if (Number(item.carNum) != "NaN") {
          num += item.insuranceCost * item.carNum;
        }
      }
    }
    this.sum = num;
    console.log(num);
  },
  mounted() {
    console.log(1);
    // 更新车数量

    let coverageDataLists = localStorage.getItem("coverageDataList"); //险种
    let insuranceVehicleNamesList = localStorage.getItem(
      "insuranceVehicleNames"
    ); //车牌号
    if (coverageDataLists) {
      this.cInsuranceVehicleItemList = JSON.parse(coverageDataLists);
      // this.tableData = coverageDataLists;
    }
    if (insuranceVehicleNamesList) {
      this.dynamicTags = JSON.parse(insuranceVehicleNamesList);
      this.updateNum();
      console.log(this.cInsuranceVehicleItemList);

      // this.tableData = insuranceVehicleNamesList;
    }
  },

  methods: {
    //
    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = "合计";
          return;
        }
        const values = data.map((item) => Number(item[column.property]));
        // console.log(values, index);
        // debugger
        if (index == 4) {
          // console.log(values[0]);
          if (values[0]) {
            let sum = 0;
            for (const item of values) {
              console.log(values, item);
              item ? (sum += item) : (sum += 0);
            }
            return (sums[index] = this.sum + "元");
          } else {
            // console.log(sums[index]);
            return (sums[index] = "0元");
          }
        }
      });
      return sums;
    },
    editClick() {
      // this.dynamicTags.map((item) => {
      //   id, licensePlate;
      // });

      this.formData.cVehicleList = this.dynamicTags.map((item) => {
        let { id, licensePlate } = item;
        return { vehicleId: id, licensePlate };
      });
      // for (const item of this.cInsuranceVehicleItemList) {
      //   // createTime updateTime
      //   delete item.createTime;
      //   delete item.updateTime;
      // }

      this.formData.cInsuranceVehicleItemList = this.cInsuranceVehicleItemList.map(
        (item) => {
          let {
            id: insuranceItemId,
            name: insuranceItemName,
            typeName: insuranceTypeName,
            typeId: insuranceTypeId,
            insuranceQuota,
            insuranceCost,
          } = item;
          return {
            insuranceTypeId,
            insuranceTypeName,
            insuranceItemId,
            insuranceItemName,
            insuranceQuota,
            insuranceCost,
          };
        }
      );
      this.formData.totalPremium = this.sum;
      // debugger;
      this.isDisabled = true;
      vehicleE07VehicleAdd(this.formData).then((res) => {
        localStorage.removeItem("coverageDataList");
        localStorage.removeItem("insuranceVehicleNames");
        this.backClick();
      }).catch(e=>{
        this.isDisabled = false;
      });
      console.log(this.formData);
      // TODO:
    },
    updateNum() {
      this.cInsuranceVehicleItemList.map((item) => {
        console.log(item);
        item.carNum = this.dynamicTags.length ? this.dynamicTags.length : 0;
      });
      this.cInsuranceVehicleItemList = JSON.parse(
        JSON.stringify(this.cInsuranceVehicleItemList)
      );
    },
    backClick() {
      this.$router.go(-1);
    },
    carClick() {
      localStorage.setItem(
        "insuranceVehicleNames",
        JSON.stringify(this.dynamicTags)
      );
      this.$router.push({ path: "/insuranceVehicle" });
    },
    planClick() {
      localStorage.setItem(
        "coverageDataList",
        JSON.stringify(this.cInsuranceVehicleItemList)
      );
      this.$router.push({ path: "/coverage" });
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      this.updateNum();
    },
    handleUpdate(val) {
      console.log(val);
      this.dialogFormData = val;
      this.open1 = true;
    },
    handleDelete(val) {
      console.log(val.$index);
      this.cInsuranceVehicleItemList.splice(val.$index, 1);
    },
    // 表格弹出保存
    dialogSaveClick() {
      console.log(this.dialogFormData);

      this.open1 = false;
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
<style>
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>

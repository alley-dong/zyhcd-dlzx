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
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-col :span="24">
          <el-form-item label="保险类型" prop="type">
            <el-select
              v-model="formData.type"
              placeholder="请输入保险类型"
              @change="type_change"
              clearable
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="dict in typeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车架号" prop="typeName">
            <el-autocomplete
              class="inline-input"
              v-model="formData.frameNumber"
              :fetch-suggestions="querySearch"
              @select="handleSelect"
              placeholder="请输入内容"
            >
              <template slot-scope="{ item }" :style="{ width: '100%' }">
                <!-- <div class="name">{{ item.id }}</div> -->
                <span class="addr">{{ item.frameNumber }}</span>
              </template>
            </el-autocomplete>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车牌号" prop="field105">
            <el-autocomplete
            filterable 
              class="inline-input"
              v-model="formData.licensePlate"
              :fetch-suggestions="querySearch1"
              placeholder="请输入内容"
              @select="handleSelect"
            >
              <template slot-scope="{ item }" :style="{ width: '100%' }">
                <!-- <div class="name">{{ item.id }}</div> -->
                <span class="addr">{{ item.licensePlate }}</span>
              </template>
            </el-autocomplete>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="保险项目" prop="type">
            <el-button type="primary" @click="checkClcik">选择险种</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <el-table
      show-summary
      :summary-method="getSummaries"
      :data="checkItemDataList"
    >
      <el-table-column label="险种" align="center" prop="insuranceItemName" />
      <el-table-column
        label="险种类型"
        align="center"
        prop="insuranceTypeName"
      />
      <el-table-column label="保额(元)" align="center" prop="insuranceQuota" />
      <el-table-column label="保费(元)" align="center" prop="insuranceCost" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
      style="margin-top: 30px"
    >
      <el-form-item label="备注">
        <el-input
          v-model="formData.remarks"
          type="textarea"
          placeholder="请输入备注"
          :autosize="{ minRows: 4, maxRows: 4 }"
          :style="{ width: '100%' }"
        ></el-input>
      </el-form-item>
    </el-form>
    <el-dialog
      v-bind="$attrs"
      v-on="$listeners"
      @open="onOpen"
      @close="onClose"
      :visible.sync="open"
      title="选择险种"
    >
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          :model="formData1"
          :rules="rules"
          size="medium"
          label-width="100px"
        >
          <el-col :span="10">
            <el-form-item label="保险类别" prop="name">
              <el-select
                v-model="formData1.typeId"
                placeholder="请选择保险类别"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in field102Options"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="险种" prop="typeName">
              <el-input
                v-model="formData1.name"
                placeholder="请输入险种"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>


          <el-col :span="4">
            <el-button @click="queryClick">查询</el-button>
          </el-col>
        </el-form>
      </el-row>

      <el-table :data="itemList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="险种类型名称" align="center" prop="typeName" />
        <el-table-column label="险种名称" align="center" prop="name" />
      </el-table>
      <div slot="footer">
        <!-- <el-button @click="close">取消</el-button> -->
        <el-button type="primary" @click="handelConfirm">保存</el-button>
      </div>
    </el-dialog>

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
            {{ dialogFormData.insuranceItemName }}
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
            {{ dialogFormData.insuranceTypeName }}
          </div>
        </el-form-item>
        <el-form-item label="保额">
          <el-input
            v-model="dialogFormData.insuranceQuota"
            placeholder="请输入保额"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="保费" prop="insuranceCost">
          <el-input
            v-model="dialogFormData.insuranceCost"
            placeholder="请输入保费"
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
import {
  insuranceVehicleE02Box,
  insuranceTypeList,
  insuranceVehicleAdd,
  insuranceVehicleE02InsuranceTypeList,
  insuranceVehicleE02Judge,
} from "@/api/system/vehicle";
export default {
  inheritAttrs: false,

  data() {
    return {
      isDisabled: false,
      open: false,
      open1: false,
      restaurants: [],
      formData: {
        type: null,
        // frameName: null,
        licensePlate: null,
        // licensePlateName: null,
        remarks: null,
        vehicleId: undefined,
        frameNumber: undefined,
      },
      dialogFormData: {
        insuranceItemName: "",
        insuranceTypeName: "",
        insuranceQuota: "",
        insuranceCost: "",
      },
      itemList: [],
      checkItemList: [],
      checkItemDataList: [],
      formData1: {
        name: null,
        typeId: null,
      },

      rules: {
        type: [],
        typeName: [],
        field105: [],
        name: [],
        insuranceItemName: [],
        insuranceTypeName: [],
        insuranceQuota: [],
        insuranceCost: [],
        remarks: [
          {
            required: true,
            message: "请输入备注",
            trigger: "blur",
          },
        ],
      },
      typeOptions: [
        {
          dictValue: 1,
          dictLabel: "新车保险",
        },
        {
          dictValue: 2,
          dictLabel: "车辆续保",
        },
      ],
      field102Options: [],
    };
  },
  created() {
    // this.getDicts("c_insurance_vehicle_insuranceType").then((response) => {
    //   this.typeOptions = response.data;
    // });
    insuranceVehicleE02Box({}).then((res) => {
      this.restaurants = res.data;
    });
  },
  mounted() {},
  methods: {
    insuranceVehicleE02Judge_fn(type, id) {
      if (type && id) {
        insuranceVehicleE02Judge({ type, id }).then((res) => {
          // debugger
          this.checkItemDataList = res.data ? res.data : [];
        });
      }
    },
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
        console.log(values, index);
        // debugger
        if (index == 3) {
          console.log(values[0]);
          if (values[0]) {
            let sum = 0;
            for (const item of values) {
              item ? (sum += item) : (sum += 0);
            }
            return (sums[index] = sum + "元");
          } else {
            console.log(sums[index]);
            return (sums[index] = "0元");
          }
        }
      });
      return sums;
    },
    //   编辑
    handleUpdate(row) {
      console.log(row);
      // this.dialogFormData = row;
      this.dialogFormData = JSON.parse(JSON.stringify(row));
      TODO: this.open1 = true;
      //   console.log(id);
    },
    // 删除
    handleDelete(row) {
      console.log(row.$index);
      this.checkItemDataList.splice(row.$index, 1);
    },
    // 表格弹出保存
    dialogSaveClick() {
      console.log(this.dialogFormData);
      for (const [index, item] of this.checkItemDataList.entries()) {
        //TODO:
        console.log();
        if (item.id == this.dialogFormData.id) {
          console.log(index);
          this.checkItemDataList[index] = this.dialogFormData;
        }
      }
      this.checkItemDataList = JSON.parse(
        JSON.stringify(this.checkItemDataList)
      );
      this.open1 = false;
    },
    vehicleDataList(id) {
      console.log(id);
      let obj = {
        id,
      };
      insuranceVehicleE02Box(obj).then((res) => {
        debugger;
        this.checkItemDataList.push(...res.data);
      });
    },
    onOpen() {},
    onClose() {
      this.$refs["elForm"].resetFields();
    },
    close() {
      this.$emit("update:visible", false);
    },
    handelConfirm() {
      this.open = false;
      let dataList = JSON.parse(JSON.stringify(this.checkItemList));
      for (const item of dataList) {
        item.insuranceItemName = item.name;
        item.insuranceTypeName = item.typeName;
        // delete item.name;
        // delete item.typeName;
        item.insuranceQuota = null;
        item.insuranceCost = null;
      }
      this.checkItemDataList.push(...dataList);
      // for (const item of this.checkItemList) {
      //   this.vehicleDataList(item.id);
      // }
    },
    // 查询
    queryClick() {
      console.log(this.formData1);
      // this.insuranceTypeItemListData("itemList");
      this.insuranceVehicleE02InsuranceTypeListData();
    },
    // 选择险种
    checkClcik() {
      this.open = true;
      this.insuranceTypeItemListData("field102Options");
      this.insuranceVehicleE02InsuranceTypeListData();
    },
    // 表格选中
    handleSelectionChange(val) {
      console.log(val);
      this.checkItemList = val;
    },
    // 选择险种查询
    insuranceTypeItemListData(name, bool) {
      insuranceTypeList(this.formData1).then((res) => {
        console.log(res.rows, this.checkItemDataList);
        if (bool && this.checkItemDataList.length !== 0) {
          for (const item of this.checkItemDataList) {
            let index = res.rows.findIndex(
              (_) => _.id !== item.id && _.name !== item.name
            );
            res.rows.splice(index, 1);
          }
        }
        this[name] = res.rows;
      });
    },
    insuranceVehicleE02InsuranceTypeListData() {
      console.log(JSON.stringify(this.checkItemDataList));
      insuranceVehicleE02InsuranceTypeList(this.formData1).then((res) => {
        this["itemList"] = res.rows;
      });
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants;
      console.log(this.createFilter(queryString));
      //   var results = this.createFilter(queryString);
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    querySearch1(queryString, cb) {
      var restaurants = this.restaurants;
      console.log('restaurants', restaurants);
      console.log('.this.createFilter1(queryString)', this.createFilter1(queryString))
      //   var results = this.createFilter(queryString);
      // var results = queryString
      //   ? restaurants.filter(this.createFilter1(queryString))
      //   : restaurants;
      var results = queryString ? restaurants.filter(this.createFilter1(queryString)) : restaurants;
        console.log('.cb', results)
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter1(queryString, restaurant) {
      return (restaurant) => {
        return (
          restaurant.licensePlate
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) !== -1
        );
      };
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (
          restaurant.frameNumber
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) !== -1
        );
      };
    },
    handleSelect(item) {
      console.log(item);
      this.formData.frameNumber = item.frameNumber;
      this.formData.vehicleId = item.id;
      this.formData.licensePlate = item.licensePlate;
      if (this.formData.type == 2) {
        this.insuranceVehicleE02Judge_fn(this.formData.type, item.id);
      }else{
          this.checkItemDataList = [];

      }
    },
    type_change() {
      if (this.formData.type == 2) {
        this.insuranceVehicleE02Judge_fn(
          this.formData.type,
          this.formData.vehicleId
        );
      }else{
          this.checkItemDataList = [];

      }
    },
    // handleSelect1(item) {
    //   console.log(item);
    //   this.formData.licensePlate = item.licensePlate;
    //   this.formData.vehicleId = item.id;
    // },
    backClick() {
      this.$router.go(-1);
    },
    editClick() {
      for (const item of this.checkItemDataList) {
        item.insuranceItemId = item.id
        item.insuranceTypeId = item.typeId
        delete item.id
      }
      this.formData.cInsuranceVehicleItems = [...this.checkItemDataList];
      console.log('.this.formData', this.formData)
      this.isDisabled = true;
      insuranceVehicleAdd(this.formData).then((res) => this.backClick()).catch(e=>{
        this.isDisabled = false;
      });
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

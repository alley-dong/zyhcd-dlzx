<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <el-button type="primary" @click="editClick">提交</el-button>
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">新增</div>
      <div class="xian"></div>
    </div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="物品类别" prop="goodsTypeId">
        <el-select
          v-model="formData.goodsTypeId"
          placeholder="请选择物品类别"
          @change="goodsTypeChange"
          clearable
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in goodsTypeIdOptions"
            :key="index"
            :label="item.name"
            :value="item.id"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物品编号" prop="goodsCode">
        <el-select
          v-model="formData.goodsCode"
          placeholder="请选择物品编号"
          @change="codeChange"
          clearable
          :disabled="!formData.goodsTypeId"
          :style="{ width: '100%' }"
        >
          <el-option
            v-for="(item, index) in goodsCodeOptions"
            :key="index"
            :label="item.code"
            :value="item.code"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物品名称" prop="goodsName">
        <div>
          {{ formData.goodsName }}
        </div>
      </el-form-item>
      <el-form-item label="规格" prop="specification">
        <div>
          {{ formData.specification }}
        </div>
      </el-form-item>
      <el-form-item label="单位" prop="unit">
        <div>
          {{ formData.unit }}
        </div>
      </el-form-item>
      <el-form-item label="单价" prop="unitPrice">
        <div>
          {{ formData.unitPrice }}
        </div>
      </el-form-item>
      <el-form-item label="供应商" prop="supplier">
        <div>
          {{ formData.supplier }}
        </div>
      </el-form-item>
      <el-form-item label="报废时间" prop="inventoryTime">
        <el-date-picker
          v-model="formData.inventoryTime"
        type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
          :style="{ width: '100%' }"
          placeholder="请选择报废时间"
          clearable
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="报废数量" prop="inventoryNumber">
        <el-input
          v-model="formData.inventoryNumber"
          placeholder="请输入报废数量"
          clearable
          :style="{ width: '100%' }"
        >
        </el-input>
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
  </div>
</template>

<script>
import { goodsTypeK01List,goodsK02List,goodsInventoryK12Add } from "@/api/stock/index";
export default {
  data() {
    return {
      formData: {
        goodsTypeId: undefined,
        goodsTypeName: undefined,
        goodsCode: undefined,
        inventoryTime: null,
        inventoryNumber: undefined,
        remarks: undefined,
        goodsName: undefined,
      },
      rules: {
        goodsTypeId: [],
        goodsCode: [],
        inventoryTime: [],
        inventoryNumber: [],
        remarks: [],
      },
      goodsTypeIdOptions: [],
      goodsCodeOptions: [
        {
          label: "选项一",
          value: 1,
        },
        {
          label: "选项二",
          value: 2,
        },
      ],
    };
  },
  mounted() {
    // 物品类别下拉请求
    goodsTypeK01List({status:1}).then((res) => {
      this.goodsTypeIdOptions = res.rows;
    });

    
  },
  methods: {
    // 物品类别下拉
    goodsTypeChange(val) {
      for (const item of this.goodsTypeIdOptions) {
        if (item.id == val) {
          this.formData.goodsTypeName = item.name;
        }
      }
      this.formData.goodsCode = this.formData.goodsName = this.formData.specification = this.formData.unit = this.formData.unitPrice = this.formData.supplier = undefined;
      console.log(val);
      this.codeOptation(val)
    },
    codeOptation(id){
      goodsK02List({goodsTypeId:id}).then(res=>{
        this.goodsCodeOptions = res.rows
      })
    },
    // 物品编号下拉
    codeChange(val) {
      for (const item of this.goodsCodeOptions) {
        if (item.code == val) {
          this.formData.goodsName = item.name;
          this.formData.specification = item.specification;
          this.formData.unit = item.unit;
          this.formData.unitPrice = item.unitPrice;
          this.formData.supplier = item.supplier;
          this.formData.goodsId = item.id;
        }
      }
    },
    backClick() {
      console.log("返回");
      this.$router.go(-1)
    },
    editClick() {
      console.log("新增");
      goodsInventoryK12Add(this.formData).then(res=>{
        this.backClick()
      })
    },
  },
};
</script>

<style lang="scss" scoped>
</style>

<style lang="scss" scoped>
.border {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  .text {
    width: 50px;
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
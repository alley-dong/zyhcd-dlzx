<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <!-- <el-button type="primary" @click="editClick">提交</el-button> -->
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">查看</div>
      <div class="xian"></div>
    </div>
    <el-form
      v-if="!tableData"
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="领用物品" prop="goodsName">
        <div>
          {{ formData.goodsName }}
        </div>
      </el-form-item>
      <el-form-item label="规格" prop="specification	">
        <div>
          {{ formData.specification }}
        </div>
      </el-form-item>
      <el-form-item label="单位" prop="unit">
        <div>
          {{ formData.unit }}
        </div>
      </el-form-item>
      <el-form-item label="车牌号" prop="licensePlate	">
        <div>
          {{ formData.licensePlate }}
        </div>
      </el-form-item>
      <!-- TODO:尿素 -->
      <el-form-item v-if="name == '尿素'" label="线路" prop="routeName">
        <div>
          {{ formData.routeName }}
        </div>
      </el-form-item>
      <el-form-item v-if="name != '工作餐'" :label="number_name" prop="number">
        <div>
          {{ formData.number }}
        </div>
      </el-form-item>
      <!-- TODO:工作餐 -->

      <el-form-item v-if="name == '工作餐'" label="领用日期" prop="supplier">
        <div v-for="(item, index) in formData.receiveDateLists" :key="index">
          {{ item }}
        </div>
      </el-form-item>

      <el-form-item label="领用人" prop="name">
        <div>
          {{ formData.createBy }}
        </div>
      </el-form-item>
      <!-- TODO:工作餐 -->
      <el-form-item v-if="name == '工作餐'" label="领用数量" prop="iNumber">
        <div>
          {{   formData.receiveDateLists.length || formData.number }}
        </div>
      </el-form-item>
      <!-- TODO:工作餐 -->
      <el-form-item v-if="name != '尿素'"  label="申请原因" prop="remarks">
        <div>
          {{ formData.remarks }}
        </div>
      </el-form-item>
      <!--  TODO:轮胎 低值易耗品-->
      <!-- <el-form-item
        v-if="name != '工作餐' && name != '尿素'&&name != '轮胎'"
        label="领用原因"
        prop="remarks"
      >
        <div>
          {{ formData.remarks }}
        </div>
      </el-form-item> -->
         <el-form-item label="申请日期" prop="name">
        <div>
          {{ formData.createTime }}
        </div>
      </el-form-item>
        <el-form-item v-if="name == '轮胎'" label="领取日期" prop="name">
        <div>
          {{ formData.receiveTime }}
        </div>
      </el-form-item>
      <!-- <el-form-item label="审批人" prop="reviewUser">
        <div>
          {{ formData.reviewUser }}
        </div>
      </el-form-item> -->
    </el-form>

    <el-table v-if="tableData" :data="tableData">
      <el-table-column label="物品" align="center" prop="iGoodsName" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="领用数量" align="center" prop="iNumber" />
      <el-table-column label="领用人" align="center" prop="name" />
      <el-table-column label="零件用途" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.type == 1 ? "维修" : "换装" }}
        </template>
      </el-table-column>
      <el-table-column label="申请日期" align="center" prop="createTime" />
      <el-table-column label="备注" align="center" prop="remarks" />
    </el-table>
    <!-- <div class="border">
      <div class="text">审核结果</div>
      <div class="xian"></div>
    </div>
    <div style="width: 85%; margin: 0 auto">
      <div style="margin-top: 20px">
        <el-radio v-model="formData1.status" :label="1">通过</el-radio>
        <el-radio v-model="formData1.status" :label="2">拒绝</el-radio>
      </div>
      <el-input
        type="textarea"
        v-model="formData1.reviewRemarks"
        placeholder="请输入原因"
        style="margin: 20px 0 40px 0"
      ></el-input>
    </div> -->
    <div class="border">
      <div class="text">审批记录</div>
      <div class="xian"></div>
    </div>
    <!-- table列表 -->
    <el-table
      border
      :data="tableData1"
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
  </div>
</template>

<script>
import {
  goodsReceivingRecordK19GetInfo,
  goodsReceivingRecordK18List,
  goodsReceivingRecordK21List,
  goodsReceivingRecordK20GetInfo,
  goodsReceivingRecordK18ListGetInfo,
} from "@/api/stock/index";
// import { goodsTypeK01List } from "@/api/stock/child/index";
import { queryExamineConfigProcessList } from "@/api/com";

export default {
  data() {
    return {
      name: null,
      formData: {},
      rules: {},
      tableData: null,
      tableData1: [],
      number_name:'数量',
      remarks_name:'领用原因',
      formData1: {
        status: undefined,
        reviewRemarks: undefined,
      },
    };
  },
  mounted() {
    // goodsTypeK01List()
    // console.log(this.$route.query.name);
    this.name = this.$route.query.name;
    switch (this.name) {
      case "工作餐":
        goodsReceivingRecordK19GetInfo(this.$route.params.id).then((res) => {
          this.formData = res.rows[0];
          // debugger
        });
        break;

      case "尿素":
        goodsReceivingRecordK18List(this.$route.params.id).then((res) => {
          this.formData = res.data;
        });
        break;
      // case "低值易耗品":
      // case "轮胎":
      //   goodsReceivingRecordK20GetInfo(this.$route.params.id).then((res) => {
      //     this.formData = res.data;

      //   });
      //   break;
      case "备件":
        goodsReceivingRecordK21List(this.$route.params.id).then((res) => {
          this.tableData = res.rows;
        });
        break;
      default:
        this.number_name = '领用数量'
        this.remarks_name = '申请原因'
        goodsReceivingRecordK20GetInfo(this.$route.params.id).then((res) => {
          this.formData = res.data;
        });
        break;
    }
    this.queryExamineConfigProcessDataList();
  },
  methods: {
    backClick() {
      // 返回
      this.$router.go(-1);
    },
    // 审批
    queryExamineConfigProcessDataList() {
      let obj = {
        // type: 6,
        relationId: this.$route.params.id,
      };
      queryExamineConfigProcessList(obj).then((res) => {
        // debugger
        this.tableData1 = res.data.sysExamineUserTaskList;
      });
    },
    // editClick() {
    //   // 修改
    //   goodsReceivingRecordK18ListGetInfo(this.formData1).then(res=>{
    //     this.backClick()
    //   })
    // },
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
    width: 80px;
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

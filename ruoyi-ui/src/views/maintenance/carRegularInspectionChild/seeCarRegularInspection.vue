<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick()">返回</el-button>
        <!-- <el-button type="primary" @click="editClick()">提交</el-button> -->
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">申请信息</div>
      <div class="xian"></div>
    </div>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-col :span="12">
          <el-form-item label="上报人" prop="createBy">
            <!-- <el-input v-model="formData.createBy" placeholder="请输入上报人" clearable :style="{width: '100%'}"> -->
            <!-- </el-input> -->
            <div>{{ formData.createBy }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="上报时间" prop="createTime">
            <!-- <el-input v-model="formData.createTime" placeholder="请输入上报时间" clearable :style="{width: '100%'}"> -->
            <!-- </el-input> -->
            <div>{{ formData.createTime }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车牌号" prop="field103">
            <!-- <el-input v-model="formData.field103" placeholder="请输入任务编号" clearable :style="{width: '100%'}"> -->
            <!-- </el-input> -->
            <div>{{ formData.licensePlate }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="定检类型" prop="type">
            <!-- <el-input v-model="formData.type" placeholder="请输入定检类型" clearable :style="{width: '100%'}"> -->
            <!-- </el-input> -->
            <div>{{ formData.type == 1 ? "主车" : "挂车" }}</div>
          </el-form-item>
        </el-col>
        <!-- <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm">提交</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-col> -->
      </el-form>
    </el-row>

    <el-table
      :data="dataList"
      style="width: 90%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column label="点检内容" align="center" prop="name" />
      <el-table-column label="点检结果" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status == 1 ? "正常" : "维修" }}
        </template>
      </el-table-column>
      <el-table-column label="点检图片" align="center">
        <template slot-scope="scope">
          <!-- <img v-for="(item,index) in scope.row.files" style="width:100px;height:50px" :key="index" :src="item" alt="" /> -->
          <el-image
            v-for="(item, index) in scope.row.files"
            :key="index"
            style="width: 100px; height: 50px"
            :src="item"
            :preview-src-list=" scope.row.files"
          >
          </el-image>
        </template>
      </el-table-column>
    </el-table>

    <div class="border">
      <div class="text">审批记录</div>
      <div class="xian"></div>
    </div>
    <!-- table列表 -->
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
          <span>{{scope.row.statusEnum}}</span>
          <!-- <span v-if="scope.row.status == 1">待审核</span>
          <span v-else-if="scope.row.status == 2">拒绝</span>
          <span v-else-if="scope.row.status == 3">通过</span>
          <span v-else>-</span> -->
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
  vehicleInspectH20List,
  vehicleInspectE20Review,
} from "@/api/maintenance";
import { queryExamineConfigProcessList } from "@/api/com";
export default {
  data() {
    return {
      formData: {
        createBy: undefined,
        createTime: undefined,
        field103: undefined,
        type: undefined,
      },
      tableData: [],
      dataList: [],
      formData1: {
        id: undefined,
        status: undefined,
        reviewRemark: undefined,
      },
      rules: {
        createBy: [],
        createTime: [],
        field103: [],
        type: [],
      },
    };
  },
  methods: {
    // 审批
    queryExamineConfigProcessDataList() {
      let obj = {
        // type: 5,
        relationId: this.$route.params.id,
      };
      queryExamineConfigProcessList(obj).then((res) => {
        // debugger
        this.tableData = res.data.sysExamineUserTaskList;
      });
    },
    // 提交
    editClick() {
      vehicleInspectE20Review(this.formData1).then((res) => {
        this.backClick();
      });
    },
    // 返回
    backClick() {
      this.$router.go(-1);
    },
  },
  mounted() {
    this.formData1.id = this.$route.params.id;
    this.queryExamineConfigProcessDataList();
    vehicleInspectH20List({ id: this.$route.params.id }).then((res) => {
      console.log(res);
      this.formData = res.data;
      this.dataList = res.data.cVehicleInspectItemList;
      for (const item of res.data.cVehicleInspectItemList) {
        if (item.files != "") {
          let arr = item.files.split(",");
          arr.length--;
          item.files = arr;
        } else {
          item.files = [];
        }
      }
    });
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

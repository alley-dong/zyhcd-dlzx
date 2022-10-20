<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick()">返回</el-button>
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">查看</div>
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
          <el-form-item label="上报人" prop="driverName">
            <!-- <el-input v-model="formData.createBy" placeholder="请输入上报人" clearable :style="{width: '100%'}"> -->
            <!-- </el-input> -->
            <div>{{ formData.driverName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="上报时间" prop="inspectDate">
            <!-- <el-input v-model="formData.inspectDate" placeholder="请输入上报时间" clearable :style="{width: '100%'}"> -->
            <!-- </el-input> -->
            <div>{{ formData.inspectDate }}</div>
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
            <div>{{ formData.type == 1 ? "主车定检" : "挂车定检" }}</div>
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
          <el-image
            style="width: 100%; height: 100%"
            :src="scope.row.fileList[0]"
            :preview-src-list="scope.row.fileList"
          >
          </el-image>
          <!-- <img v-for="(item,index) in scope.row.fileList" :key="index" :src="item" alt="" /> -->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {
  vehicleInspectMonthlyItemH24Info,
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
        this.tableData = res.rows;
      });
    },

    // 返回
    backClick() {
      this.$router.go(-1);
    },
  },
  mounted() {
    this.formData1.id = this.$route.params.id;

    vehicleInspectMonthlyItemH24Info(this.$route.params.id).then((res) => {
      console.log(res);
      this.formData = res.data;
      for (const item of res.data.cVehicleInspectItemList) {
        // item.files = 'https://pics4.baidu.com/feed/810a19d8bc3eb1356124203531bc8bdbff1f4483.jpeg?token=c5d030c784027b41dd5276464a291d7d&s=F29B15C53C531AD8589591270300E059,http://115.29.205.180:8089/pfserver-person/personFile/minio/view/pfserverperson/发动机.jpg,'
        if (item.files) {
          let arr = item.files.split(",");
          arr.length--;
          item.fileList = arr;
        }
      }
      this.dataList = res.data.cVehicleInspectItemList;
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

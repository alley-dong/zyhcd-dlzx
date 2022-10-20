<template>
  <div class="app-container">
    <!-- 任务异常审批 -->
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">上报信息</div>
      <div class="xian"></div>
    </div>
    <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="130px"
    >
      <el-col :span="12">
        <el-form-item label="申请人" prop="driverName">
          <div>{{ formData.driverName }}</div>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="异常类型" prop="type">
          <div>{{ formData.type == 1 ? "运行异常" : "任务异常" }}</div>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="运单号" prop="shipmentXid">
          <div>{{ formData.shipmentXid }}</div>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="任务线路" prop="routeName">
          <div>{{ formData.routeName }}</div>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item label="异常情况描述" prop="remarks">
          <div>{{ formData.remarks }}</div>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item label="图片" prop="taskExceptionFileList">
          <!-- <div>{{ formData.taskExceptionFileList }}</div> -->
          <img
            v-for="(item, index) in formData.taskExceptionFileList"
            :key="index"
            :src="item"
            alt=""
            style="width: 200px; height: 200px; margin-right: 20px"
          />
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item label="申请时间" prop="createTime">
          <div>{{ formData.createTime }}</div>
        </el-form-item>
      </el-col>
    </el-form>
    <div class="border">
      <div class="text">审核记录</div>
      <div class="xian"></div>
    </div>

    <el-table :data="checkItemDataList1">
      <el-table-column
        label="审核时间"
        align="center"
        prop="reviewTime"
      />
      <el-table-column label="审核人" align="center" prop="userName" />
      <el-table-column label="审核结果" align="center" prop="statusEnum" />
      <el-table-column label="备注" align="center" prop="reviewRemarks" />
    </el-table>
  </div>
</template>

<script>
import { transportExceptionP08TransportTaskExceptionInfo } from "@/api/taskManagement/index";
import { queryExamineConfigProcessList } from "@/api/com";
export default {
  data() {
    return {
      formData: {
        driverName: undefined,
        type: undefined,
        shipmentXid: undefined,
        routeName: null,
        remarks: undefined,
        taskExceptionFileList: undefined,
        createTime: undefined,
      },
      checkItemDataList1:[],
      fromData1: {
        status: undefined,
        reviewRemarks: undefined,
      },
      rules: {
        driverName: [],
        type: [],
        shipmentXid: [],
        routeName: [],
        remarks: [],
        taskExceptionFileList: [],
        createTime: [],
      },
    };
  },
  mounted() {
    this.formData.id = this.$route.params.id;
    transportExceptionP08TransportTaskExceptionInfo({
      id: this.formData.id,
    }).then((res) => {
      console.log(res);
      this.formData = res.data;
    });
    let obj = {
      // type: 9,
      relationId:this.$route.params.id
    };
    queryExamineConfigProcessList(obj).then((res) => {
      debugger
      this.checkItemDataList1 = res.data.sysExamineUserTaskList;
    });
  },
  methods: {
    backClick() {
      this.$router.go(-1);
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
    width: 100px;
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
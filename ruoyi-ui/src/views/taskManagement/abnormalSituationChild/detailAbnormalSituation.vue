<template>
  <div class="app-container">
    <!-- 任务异常查看 -->
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <el-button type="primary" @click="editClick">提交</el-button>
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
      <div class="text">审批记录</div>
      <div class="xian"></div>
    </div>
    <!-- table列表 -->
    <el-table
      border
      :data="tableData"
      style="width: 90%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
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

    <div class="border">
      <div class="text">审核结果</div>
      <div class="xian"></div>
    </div>

    <div style="width: 85%; margin: 0 auto">
      <div style="margin-top: 20px">
        <el-radio v-model="fromData1.status" :label='1'>通过</el-radio>
        <el-radio v-model="fromData1.status" :label='2'>不通过</el-radio>
      </div>
      <el-input
        v-model="fromData1.reviewRemarks"
        type="textarea"
        placeholder="请输入原因"
        style="margin: 20px 0 40px 0"
      ></el-input>
    </div>
  </div>
</template>

<script>
import {
  transportExceptionP08TransportTaskExceptionInfo,
  transportExceptionP07TransportTaskExceptionCheck,
} from "@/api/taskManagement/index";
import { getSysExamineTaskList } from "@/api/com";
export default {
  data() {
    return {
      tableData:[],
      formData: {
        driverName: undefined,
        type: undefined,
        shipmentXid: undefined,
        routeName: null,
        remarks: undefined,
        taskExceptionFileList: undefined,
        createTime: undefined,
      },
      fromData1: {
        id:undefined,
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

  created(){
    let obj = {
      relationId: this.$route.params.id,
    };
    getSysExamineTaskList(obj).then((res) => {
      // debugger
      console.log('.res', res)
      this.tableData = res.data.sysExamineUserTaskList;
    });
  },
  mounted() {
    this.formData.id = this.$route.params.id;
    this.fromData1.id = this.$route.params.id;
    transportExceptionP08TransportTaskExceptionInfo({
      id: this.formData.id,
    }).then((res) => {
      console.log(res);
      this.formData = res.data;
    });
  },
  methods: {
    backClick() {
      this.$router.go(-1);
    },
    editClick() {
      console.log("提交");
      transportExceptionP07TransportTaskExceptionCheck(this.fromData1).then(res=>{
        this.$message(res.msg);
          this.backClick()
      })
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

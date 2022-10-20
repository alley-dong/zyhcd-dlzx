<template>
  <div class="app-container">
    <div style="height: 35px">
      <el-button
        @click="$router.back()"
        type="primary"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >
    </div>
    <div>
      <div class="wrap">
        <div class="text">基础信息</div>
        <div class="line"></div>
      </div>
      <el-table border :data="educationList" style="width: 98%; margin: 0 auto">
        <el-table-column label="事故时间" align="center">
          <template slot-scope="scope">
            <span>{{ form.accidentTime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="事故车牌号" align="center">
          <template slot-scope="scope">
            <span>{{ form.vehicleName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="驾驶员" align="center">
          <template slot-scope="scope">
            <span>{{ form.driverName }} </span>
          </template>
        </el-table-column>
        <el-table-column label="事故地点" align="center">
          <template slot-scope="scope">
            <span>{{ form.accidentLocation }}</span>
          </template>
        </el-table-column>
        <el-table-column label="发起理赔时间" align="center">
          <template slot-scope="scope">
            <span>{{ form.payTime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="理赔结束时间" align="center">
          <template slot-scope="scope">
            <span>{{ form.payEndTime }}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button @click="editEducationClick(scope.row)" type="primary"
            >编辑</el-button
          >
          <el-button @click="delEducationClick(scope.row.id)" type="danger"
            >删除</el-button
          >
        </template>
      </el-table-column> -->
      </el-table>
    </div>
    <div>
      <div class="wrap">
        <div class="text">我方</div>
        <div class="line"></div>
      </div>
      <!-- <el-form :inline="true" class="demo-form-inline" :model="listQuery">
        <el-button
          type="primary"
          style="float: right; margin: 0 20px 10px 0"
          @click="DrivingExperienceAdd"
          >新增</el-button
        >
      </el-form> -->
      <el-table
        border
        :data="form.onOurPartList"
        style="width: 98%; margin: 0 auto"
      >
        <el-table-column label="我方名称" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="责任比例" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.proportion }}</span>
          </template>
        </el-table-column>
        <el-table-column label="赔付金额" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.compensateMoney }}</span>
          </template>
        </el-table-column>
        <el-table-column label="赔付时间" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.compensateTime }}</span>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              @click="editEducationClick(scope.row, '我方名称')"
              type="primary"
              >编辑</el-button
            >
            <!-- <el-button @click="delEducationClick(scope.row.id)" type="danger"
              >删除</el-button
            > -->
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <div class="wrap">
        <div class="text">对方</div>
        <div class="line"></div>
      </div>
      <el-form :inline="true" class="demo-form-inline">
        <el-button
          type="primary"
          style="float: right; margin: 0 20px 10px 0"
          @click="DrivingExperienceAdd('对方名称')"
          >新增</el-button
        >
      </el-form>
      <el-table
        border
        :data="form.oppositeSideList"
        style="width: 98%; margin: 0 auto"
      >
        <el-table-column label="对方名称" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="责任比例" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.proportion }}</span>
          </template>
        </el-table-column>
        <el-table-column label="赔付金额" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.compensateMoney }}</span>
          </template>
        </el-table-column>
        <el-table-column label="赔付时间" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.compensateTime }}</span>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              @click="editEducationClick(scope.row, '对方名称')"
              type="primary"
              >编辑</el-button
            >
            <el-button
              @click="delEducationClick(scope.row.id, '对方名称')"
              type="danger"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <div class="wrap">
        <div class="text">第三方</div>
        <div class="line"></div>
      </div>
      <el-form :inline="true" class="demo-form-inline">
        <el-button
          type="primary"
          style="float: right; margin: 0 20px 10px 0"
          @click="DrivingExperienceAdd('第三方名称')"
          >新增</el-button
        >
      </el-form>
      <el-table
        border
        :data="form.thirdPartyList"
        style="width: 98%; margin: 0 auto"
      >
        <el-table-column label="第三方名称" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="责任比例" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.proportion }}</span>
          </template>
        </el-table-column>
        <el-table-column label="赔付金额" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.compensateMoney }}</span>
          </template>
        </el-table-column>
        <el-table-column label="赔付时间" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.compensateTime }}</span>
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          align="center"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button
              @click="editEducationClick(scope.row, '第三方名称')"
              type="primary"
              >编辑</el-button
            >
            <el-button
              @click="delEducationClick(scope.row.id, '第三方名称')"
              type="danger"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <myDialog
      ref="myDialog"
      v-if="dialogVisible"
      :visible.sync="dialogVisible"
      :title="title"
      :row="row"
      :data_fn="data_fn"
    />
  </div>
</template>

<script>
import myDialog from "./dialog/index";
import {
  E18GetInfo,
  E18ParticipantAdd,
  E18ParticipantEditSave,
  E18DeleteParticipant,
  E18ParticipantInfo,
} from "@/api/claimManagement";
export default {
  components: {
    myDialog,
  },
  data() {
    return {
      educationList: [{}],
      dialogVisible: false,
      title: undefined,
      row: undefined,
      form: {},
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      E18GetInfo(this.$route.params.id).then((res) => {
        console.log(res);
        this.form = res.data;
      });
    },
    editEducationClick(row, title) {
      E18ParticipantInfo(row.id).then((res) => {
        this.row = res.data;
        this.dialogVisible = true;
        this.title = title;
        // debugger
      });
    },
    delEducationClick(id, title) {
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          //   switch (title) {
          //     case "我方名称":
          //       console.log("我方名称删除");
          //       break;
          //     case "对方名称":
          //       console.log("对方名称删除");
          //       break;
          //     case "第三方名称":
          //       console.log("第三方名称删除");
          //       break;
          //     default:
          //       break;
          //   }
          return E18DeleteParticipant(id);
          //   return A11DriverEducationDelete(id);
        })
        .then(() => {
          //   this.educationExperienceListData();
          this.msgSuccess("删除成功");
          this.init();
        });
      // A11DriverEducationDelete(id).then((res) => {
      //   this.educationExperienceListData();
      // });
    },
    data_fn(data) {
      console.log(data);
      data.insuranceClaimsId = this.$route.params.id;
      switch (this.title) {
        case "我方名称":
          data.userType = 1;
          if (this.row) {
            E18ParticipantEditSave(data).then((res) => {
              this.msgSuccess(res.msg);
              this.init();
              this.$refs.myDialog.close()
            });
          }
          break;
        case "对方名称":
          data.userType = 2;
          if (this.row) {
            E18ParticipantEditSave(data).then((res) => {
              this.msgSuccess(res.msg);
              this.init();
              this.$refs.myDialog.close()
            });
          } else {
            E18ParticipantAdd(data).then((res) => {
              this.msgSuccess(res.msg);
              this.init();
              this.$refs.myDialog.close()
            });
          }
          break;
        case "第三方名称":
          data.userType = 3;
          if (this.row) {
            E18ParticipantEditSave(data).then((res) => {
              this.msgSuccess(res.msg);
              this.init();
              this.$refs.myDialog.close()
            });
          } else {
            E18ParticipantAdd(data).then((res) => {
              this.msgSuccess(res.msg);
              this.init();
              this.$refs.myDialog.close()
            });
          }
          break;
        default:
          break;
      }
    },
    DrivingExperienceAdd(title) {
      this.dialogVisible = true;
      this.title = title;
      this.row = undefined;
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
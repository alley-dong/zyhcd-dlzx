<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container leave">
      <van-cell-group>
        <van-cell
          title="请假类型："
          :value="form.typeEnum"
          title-class="left-title"
        />
        <van-cell
          title="开始时间："
          :value="form.vStartTime"
          title-class="left-title"
        />
        <van-cell
          title="结束时间："
          :value="form.vEndTime"
          title-class="left-title"
        />
        <van-cell
          title="请假时长："
          :value="form.duration"
          title-class="left-title"
        />
        <van-cell
          title="请假事由："
          :value="form.remarks"
          title-class="left-title"
          :value-class="form.remarks.length > 12 ? 'remarks-class' : ''"
        />
        <div class="upload-img">
          <div class="text">上传图片：</div>
          <div v-if="form.fileList && form.fileList !== ''">
            <van-image
              v-for="(item, index) in form.fileList"
              :key="index"
              @click="handleClick(form.fileList)"
              width="100"
              height="100"
              :src="form.fileList"
            />
          </div>
        </div>
        <!-- <van-cell
          title="备注"
          value="描述信息描述信息描述信息描述信息描述信息描述信息"
        /> -->
        <!-- <div class="upload-img">
          <div class="text">审批人：</div>
          <div style="float: right; flex: 1; text-align: right">
            {{ form.reviewUser }}
            <van-tag type="primary" style="margin-left: 0.3rem">{{
              form.statusEnum
            }}</van-tag>
          </div>
        </div>
        <van-cell
          title="申请时间："
          title-class="left-title"
          :value="form.vEndTime"
        />
        <van-cell
          title="抄送人"
          :value="form.remarks"
          title-class="left-title"
        />
        <van-cell title="备注" :value="form.remarks" title-class="left-title" /> -->
      </van-cell-group>
    </div>
    <div >
      <div
        v-for="item in tableData2"
        :key="item.id"
        style="border-bottom: 0.05rem solid"
      >
        <!-- <van-cell
          title="审批级别："
          :value="item.level"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批时间："
          :value="item.reviewTime"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批角色："
          :value="item.roleName"
          title-class="title-class"
          value-class="value-class"
        /> -->
        <van-cell
          title="审批人姓名："
          :value="item.userName"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="审批状态："
          :value="item.statusEnum"
          title-class="title-class"
          value-class="value-class"
        />
        <!-- <van-cell
          title="备注："
          :value="item.reviewRemarks"
          title-class="title-class"
          value-class="value-class"
        /> -->
      </div>
    </div>
    <van-field v-model="text" label="备注" placeholder="请输入备注" />
    <van-row style="background: #fff; padding: 1rem 0">
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(1)"
          >同意</van-button
        ></van-col
      >
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(2)"
          >拒绝</van-button
        ></van-col
      >
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { ImagePreview } from "vant";
import { vacationE02VacationDetail } from "@/api/approvalRecord";
import { sysExamineTaskList } from "@/api/com";
import { vacationA51ClockInCheck } from "@/api/myAgent";
import { Toast } from "vant";
export default {
  data() {
    return {
      text: undefined,
      form: {
        // leaveType: "请选择请假类型",
        // type: undefined,
        // vStartTime: "请选择开始时间",
        // vEndTime: "请选择结束时间",
        // duration: "由开始结束时间计算得",
        remarks: "",
        // fileList: undefined,
        // fileUrls: undefined,
      },
        tableData2: [],
    };
  },
  filters: {
    status: function (value) {
      switch (value) {
        case 1:
          return "待审批";
          break;
        case 2:
          return "已拒绝";
          break;
        case 3:
          return "已通过";
          break;
        case 4:
          return "已确认";
          break;
        case 5:
          return "已撤销";
          break;

        default:
          break;
      }
    },
  },
  methods: {
    // toClick() {
    //   vacationE03UndoVacation({ id: this.$route.params.id }).then((res) => {
    //     console.log(res);
    //     this.$router.go(-1);
    //   });
    // },
    handleAddClick(status) {
      vacationA51ClockInCheck({
        id: this.$route.params.id,
        reviewRemarks: this.text,
        status,
      }).then((res) => {
        if(status == 1){
          Toast("审批通过");
        } else {
          Toast("审批拒绝");
        }
        this.$router.go(-1);
      });
    },
    handleClick(url) {
      ImagePreview([url]);
    },
  },
  mounted() {
    vacationE02VacationDetail({id:this.$route.params.id}).then((res) => {
      console.log(res);
      this.form = res.data;
      this.form.remarks = this.form.remarks || ''
      // debugger
    });
    sysExamineTaskList({ relationId: this.$route.params.id }).then((res) => {
      this.tableData2 = res.data.sysExamineUserTaskList;
    });
  },
  components: {
    Header,
  },
};
</script>

<style lang="scss" scoped>
.upload-img {
  display: flex;
  text-align: left;
  padding: 0.2rem 0.32rem;
  border-bottom: 0.02rem solid #dcdcdc;
  .text {
    // width: 10em;
    width: 8.7em;
    font-size: 0.28rem;
    margin-right: 0.24rem;
  }
}
.remarks-class {
  text-align: left;
}
.left-title {
  text-align: left;
}
.title-class {
  text-align: left;
}
</style>
<style lang="scss" >
.leave {
  .van-cell {
    border-bottom: 0.02rem solid #dcdcdc;
  }
}
</style>
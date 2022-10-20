<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
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
          :value="form.duration+'天'"
          title-class="left-title"
        />
        <van-cell
          title="请假事由："
          :value="form.remarks"
          title-class="left-title"
          :value-class="form.remarks.length > 12 ? 'remarks-class' : ''"
        />
        <div class="upload-img">
          <div class="text">图片：</div>
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
                <van-cell
          title="申请时间："
          title-class="left-title"
          :value="form.createTime"
        />
        <van-cell  title="备注" :value="form.reviewRemarks" title-class="left-title" style="border-bottom:1px solid #ccc"/>
        <!-- <van-cell
          title="备注"
          value="描述信息描述信息描述信息描述信息描述信息描述信息"
        /> -->
        <div v-for="item in processList" :key="item.id" style="border-bottom:1px solid #ccc">
        <div class="upload-img">
          <div class="text">审批人：</div>
          <div style="float: right; flex: 1; text-align: right">
            {{ item.userName }}
            <van-tag type="primary" style="margin-left: 0.3rem">{{
              item.statusEnum
            }}</van-tag>
          </div>
        </div>

        <van-cell
          title="抄送人"
          :value="item.ccUserName"
          title-class="left-title"
        />
        </div>

      </van-cell-group>
    </div>
    <van-button
      v-if="form.status == 1"
      round
      type="info"
      @click="toClick"
      style="margin:0.2rem 0 1rem 0 ; width: 50%"
      >撤销</van-button
    >
    <van-button
      v-if="form.statusEnum=='已拒绝'"
      round
      type="info"
      @click="editClick"
      style="margin:0.2rem 0 1rem 0 ; width: 50%"
      >再次提交</van-button
    >
  </div>
</template>

<script>
import Header from "@/components/Header";
import { ImagePreview } from "vant";
import {
  vacationE02VacationDetail,
  vacationE03UndoVacation,
} from "@/api/leave/index";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessList,
  getSysExamineTaskList,
} from "@/api/api";
export default {
  data() {
    return {
      processList:[],
      form: {
        leaveType: "请选择请假类型",
        type: undefined,
        vStartTime: "请选择开始时间",
        vEndTime: "请选择结束时间",
        duration: "由开始结束时间计算得",
        remarks: "由开始结束时间计算得",
        fileList: [],
        fileUrls: undefined,
      },
    };
  },
  methods: {
    editClick(){
this.$router.push('/leaveEdit/'+this.$route.params.id)
    },
    toClick() {
      vacationE03UndoVacation({ id: this.$route.params.id }).then((res) => {
        console.log(res);
        this.$router.go(-1);
      });
    },
    handleClick(url) {
      ImagePreview([url]);
    },
  },
  mounted() {
    vacationE02VacationDetail({ id: this.$route.params.id }).then((res) => {
      console.log(res);
      this.form = res.data;
    });
    getSysExamineTaskList({ relationId: this.$route.params.id, loginType:1 }).then(
      (res) => {
        console.log(res);
        this.processList = res.data.sysExamineUserTaskList;
      }
    );
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
</style>
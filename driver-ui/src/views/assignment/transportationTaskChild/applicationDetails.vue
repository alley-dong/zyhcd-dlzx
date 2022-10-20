<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-field
        v-model="form.remarks"
        rows="2"
        autosize
        :label="form.type == 1 ? '异常情况描述:' : '任务异常情况说明'"
        type="textarea"
        placeholder="请输入留言"
        label-width="auto"
        readonly
        show-word-limit
      />
      <van-cell-group>
        <van-field
          v-if="form.type == 2"
          disabled
          v-model="form.kilometre"
          label="实际行驶公里数"
          placeholder="请输入实际行驶公里数"
        />
      </van-cell-group>
      <!-- <van-field name="uploader" label="文件上传">
        <template #input>
        
          <van-image
          v-for="item in form.taskExceptionFileList"
          :key="index"
            @click="handleClick(item.files)"
            width="100"
            height="100"
            :src="item.files"
          />
        </template>
      </van-field> -->
      <div class="upload-img">
        <div class="text">上传照片：</div>
        <div v-for="(item, index) in form.taskExceptionFileList" :key="index">
          <van-image

            @click="handleClick(form.taskExceptionFileList)"
            width="100"
            height="100"
            :src="item"
          />
        </div>
      </div>
      <van-form>
        <van-field readonly label="上报时间:" :value="form.createTime" />
      </van-form>
      <!-- <van-row>
        <van-col span="18">
          <van-form>
            <van-field readonly label="审批人:" value="调度" /> </van-form
        ></van-col>
        <van-col span="6">
          <div style="margin-top: 20px">
            <span>{{ form.status | status }}</span>
          </div>
        </van-col>
        <van-col span="24">
          <van-form> <van-field readonly value="部门经理" /> </van-form
        ></van-col>
        <van-col span="6">
          <div style="margin-top: 20px"><span>待审批</span></div>
        </van-col>
        <van-form>
          <van-field readonly label="抄送人:" value="人事" />
        </van-form>
      </van-row> -->
      <van-row>
        <div v-for="item in processList" :key="item.id">
          <van-col span="18">
            <van-cell
              :value="item.userName"
              title-class="left-title"
              value-class="text-cell"
            >
              <template #title>
                <span>审批人： </span>
              </template>
            </van-cell>
          </van-col>
          <van-col span="6">
            <div
              style="height: 1.3rem; line-height: 1.3rem; font-size: 0.28rem"
            >
              {{ item.statusEnum }}
            </div>
          </van-col>
          <van-col span="24">
            <van-cell
              :value="item.ccUserName"
              title-class="left-title"
              value-class="text-cell"
            >
              <template #title>
                <span>抄送人： </span>
              </template>
            </van-cell>
          </van-col>
        </div>
      </van-row>
      <div style="margin: 16px">
        <van-button
          v-if="form.status == 1"
          @click="removeClick"
          round
          block
          type="info"
          native-type="submit"
          >撤销</van-button
        >
        <van-button
          v-if="form.status == 3"
          round
          block
          @click="subClick"
          type="info"
          native-type="submit"
          >再次申请</van-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { ImagePreview } from "vant";
import {
  transportTaskM07TransportTaskExceptionInfo,
  transportTaskM08RevocationTaskException,
} from "@/api/assignment/index";
import { queryExamineConfigProcessList,getSysExamineTaskList } from "@/api/api";
import { Toast } from "vant";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form: {},
      processList: [],
      typeEnum: null,
    };
  },
  filters: {
    status: function (value) {
      switch (value) {
        // 审核状态 1待审核 2已审核 3已驳回 4已撤回
        case 1:
          return "待审核";
        case 2:
          return "已审核";
        case 3:
          return "已驳回";
        case 4:
          return "已撤回";

        default:
          break;
      }
    },
  },
  methods: {
    handleClick(url) {
      ImagePreview([url]);
    },
    removeClick() {
      transportTaskM08RevocationTaskException({
        id: this.$route.query.id,
      }).then((res) => {
        Toast(res.msg);
        this.$router.go(-1);
      });
    },
    subClick() {
      this.$router.push({
        path: "/andExceptionReporting/" + this.$route.params.id,
        query: { id: this.$route.query.id },
      });
    },
  },
  mounted() {
    (this.typeEnum = this.$route.query.typeEnum),

    getSysExamineTaskList({ relationId: this.$route.query.id,loginType: 1 }).then((res) => {
        this.processList = res.data.sysExamineUserTaskList;
        console.log("res",res)
    });
    transportTaskM07TransportTaskExceptionInfo({
      id: this.$route.query.id,
    }).then((res) => {
      console.log(res);
      this.form = res.data;
    });
  },
};
</script>

<style lang="scss" scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}

.upload-img {
  display: flex;
  text-align: left;
  // text-align: right;
  padding: 0.2rem 0.32rem;
  .text {
    // width: 10em;
    //  width: 10.9em;
    font-size: 0.28rem;
    margin-right: 0.7rem;
  }
}
</style>

<template>
  <div>
    <!-- 我要请假 -->
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-cell
        :value="form.leaveType"
        title-class="left-title"
        is-link
        @click="leaveClick"
      >
        <template #title>
          <span>请假类型： </span>
        </template>
      </van-cell>
      <van-cell
        title-class="left-title"
        :value="form.vStartTime"
        is-link
        @click="timeClick('vStartTime')"
      >
        <template #title>
          <span>开始时间： </span>
        </template>
      </van-cell>
      <van-cell
        :value="form.vEndTime"
        title-class="left-title"
        is-link
        @click="timeClick('vEndTime')"
      >
        <template #title>
          <span>结束时间： </span>
        </template>
      </van-cell>
      <van-cell
        :value="form.duration + '天'"
        title-class="left-title"
        is-link
        value-class="text-cell"
      >
        <template #title>
          <span>请假时长： </span>
        </template>
      </van-cell>
      <div class="leave-field">
        <!-- <van-field
        v-model="value1"
          label-align="right"
        label="请假时长："
        placeholder="请输入请假时长"
      /> -->
        <van-field
          input-align="left"
          v-model="form.remarks"
          arrow-direction="right"
          label-align="left"
          rows="2"
          autosize
          label="请假事由："
          type="textarea"
          maxlength="50"
          placeholder="请输入请假事由"
          show-word-limit
        />
      </div>
      <div class="upload-img">
        <div class="text">上传图片：</div>
        <div>
          <van-uploader
            :after-read="readClick"
            v-model="form.fileList"
            :max-count="1"
            multiple
          />
        </div>
      </div>
      <div v-for="item in processList" :key="item.id">
        <van-cell
          :value="item.userName"
          title-class="left-title"
          value-class="text-cell"
        >
          <template #title>
            <span>审批人： </span>
          </template>
        </van-cell>
        <van-cell
          :value="item.ccUserName"
          title-class="left-title"
          value-class="text-cell"
        >
          <template #title>
            <span>抄送人： </span>
          </template>
        </van-cell>
      </div>
    </div>
    <van-button
      round
      type="info"
      @click="submitClick"
      style="margin-bottom: 1rem; width: 50%"
      >提交</van-button
    >
    <van-action-sheet
      v-model="open"
      :actions="actions"
      cancel-text="取消"
      close-on-click-action
      @cancel="onCancel"
      @select="onSelect"
    />
    <van-calendar v-model="openDate" @confirm="onConfirm" />
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import { formatDate, timeFn } from "@/components/index";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessList,
} from "@/api/api";
import {
  E04VacationEditSave,
  vacationE03VacationTypeList,
  vacationE02VacationDetail,
} from "@/api/leave/index";
export default {
  data() {
    return {
      processList: [],
      open: false,
      openDate: false,
      dataName: undefined,
      actions: [
        // {
        //   name: "事假",
        //   value: 1,
        // },
        // {
        //   name: "病假",
        //   value: 2,
        // },
      ],

      value: undefined,
      form: {
        leaveType: "请选择请假类型",
        type: undefined,
        vStartTime: "请选择开始时间",
        vEndTime: "请选择结束时间",
        duration: "由开始结束时间计算得",
        remarks: undefined,
        fileList: [],
        fileUrls: undefined,
        isSalary: undefined,
      },
    };
  },
  methods: {
    readClick(file, detail) {
      let fd = new FormData();
      console.log("param.file", file);
      fd.append("file", file.file); // 要提交给后台的文件
      minioChooseBucketUpload(fd)
        .then((response) => {
          this.form.fileList.splice(this.form.fileList.indexOf(file), 1);
          let obj = {};
          console.log("提交文件", response.msg);
          obj.url = response.msg;
          this.form.fileList.push(obj);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //   下拉取消
    onCancel() {
      console.log(this.actions);
      Toast("取消");
    },
    // 选择下拉
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.open = false;
      //   请假类型(1.事假 2.病假)
      this.form.type = item.id;
      this.form.leaveType = item.name;
      this.form.isSalary = item.isSalary;

      Toast(item.name);
    },
    formatDate(date) {
      return `${date.getMonth() + 1}/${date.getDate()}`;
    },
    onConfirm(date) {
      this.form[this.dataName] = formatDate(date, "yyyy-MM-dd");
      if (timeFn(this.form.vStartTime, this.form.vEndTime) < 0) {
        Toast("结束时间不能小于开始时间");
        return;
      }
      if (this.dataName == "vEndTime") {
        this.form.duration =
          timeFn(this.form.vStartTime, this.form.vEndTime) + 1;
      }
      this.openDate = false;
    },
    submitClick() {
      // let str = "";
      // for (const item of this.form.fileList) {
      //   str += item.url + ",";
      // }
      let list = [];
      for (const item of this.form.fileList) {
        list.push(item.url);
      }
      this.form.fileUrls = list;
      let arr = [
        "请选择请假类型",
        "请选择开始时间",
        "请选择结束时间",
        "由开始结束时间计算得",
      ];
      for (const key in this.form) {
        if (arr.indexOf(this.form[key]) >= 0) {
          Toast(this.form[key]);
          return;
        }
      }

      let check = false;
      this.form.sysExamineConfigProcessList = [];
      this.processList.forEach(obj=>{
        let people = {
          level: obj.level,
          roleId : obj.roleId,
          roleName :  obj.roleName,
          userId :  obj.userId,
          userName : obj.userName,
          ccRoleId :  obj.ccRoleId,
          ccRoleName :  obj.ccRoleName,
          ccUserId :  obj.ccUserId,
          ccUserName :  obj.ccUserName,
        };
        if(obj.userId == null){
          check = true;
        }
        this.form.sysExamineConfigProcessList.push(people);
      });
      if(check){
        Toast("请选择审批人");
        this.form.sysExamineConfigProcessList = [];
        return;
      }
      let {
        leaveType,
        type,
        vStartTime,
        vEndTime,
        duration,
        remarks,
        isSalary,
        fileUrls,
        sysExamineConfigProcessList,
      } = this.form;

      E04VacationEditSave({
        leaveType,
        type,
        vStartTime,
        vEndTime,
        duration,
        remarks,
        isSalary,
        fileUrls,
        sysExamineConfigProcessList,
        id:this.$route.params.id
      }).then((res) =>{
        this.$router.push('/leave')
      });
    },
    // 请假
    leaveClick() {
      this.open = true;
    },
    // 时间
    timeClick(name) {
      this.dataName = name;
      switch (name) {
        case "vEndTime":
          if (
            !this.form.vStartTime ||
            this.form.vStartTime == "请选择开始时间"
          ) {
            Toast("请选择开始时间");
            return;
          }
          break;

        default:
          if (this.dataName == "vStartTime") {
            console.log(timeFn(this.form.vStartTime, this.form.vEndTime));
            this.form.duration =
              timeFn(this.form.vStartTime, this.form.vEndTime) == NaN
                ? timeFn(this.form.vStartTime, this.form.vEndTime) + 1
                : 0;
          }
          break;
      }
      this.openDate = true;
    },
  },
  mounted() {
    vacationE02VacationDetail({ id: this.$route.params.id }).then((res) => {
      console.log(res);
       if (res.data.fileList) {
          res.data.fileList = [{url:res.data.fileList[0]}]
      } else {
          res.data.fileList =[]
      }
      this.form = res.data;
      this.form.leaveType = res.data.typeEnum
     
    });
    vacationE03VacationTypeList({}).then((res) => {
      this.actions = res.rows;
    });
    queryExamineConfigProcessList({ type: 2 , relationId:this.$route.params.id}).then((res) => {
      console.log(res);
      this.processList = res.rows;
    });
  },
  components: {
    Header,
  },
};
</script>

<style lang="scss" scoped>
.text-cell {
  //   text-align: left;
}
.upload-img {
  display: flex;
  text-align: left;
  padding: 0.2rem 0.32rem;
  .text {
    width: 2.4rem;
    // width: 8.2em;
    font-size: 0.28rem;
    margin-right: 0.24rem;
  }
}
</style>

<style lang="scss">
.leave-field {
  text-align: left;
  .van-field__label {
    width: 8.2em;
  }
}
</style>
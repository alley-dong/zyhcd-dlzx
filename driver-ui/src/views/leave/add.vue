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

      <div v-for="(item,index) in processList" :key="item.id">
        <div v-if="processList[index].sysUserList != null">
          <van-field
                  clickable
                  is-link
                  arrow-direction="down"
                  :value="processList[index].userName"
                  label="选择审批人:"
                  placeholder="请选择"
                  label-width="auto"
                  @click="processList[index].spLevel = true"
          />
          <van-popup v-model="processList[index].spLevel" position="bottom">
            <van-picker
                    show-toolbar
                    value-key="nickName"
                    :columns="processList[index].sysUserList"
                    @confirm="onConfirmSpr"
                    @cancel="processList[index].spLevel = false"
            />
          </van-popup>
        </div>
        <van-cell v-if="processList[index].sysUserList == null"
                  :value="processList[index].userName"
                  title-class="left-title"
                  value-class="text-cell"
        >
          <template #title>
            <span>审批人： </span>
          </template>
        </van-cell>


        <van-cell v-if="processList[index].sysCcUserList == null"
                  :value="processList[index].ccUserName"
                  title-class="left-title"
                  value-class="text-cell"
        >
          <template #title>
            <span>抄送人： </span>
          </template>
        </van-cell>

        <div v-if="processList[index].sysCcUserList != null">
          <van-field
                  clickable
                  is-link
                  arrow-direction="down"
                  :value="processList[index].ccUserName"
                  label="选择抄送人:"
                  placeholder="请选择"
                  label-width="auto"
                  @click="processList[index].ccLevel = true"
          />
          <van-popup v-model="processList[index].ccLevel" position="bottom">
            <van-picker
                    show-toolbar
                    value-key="nickName"
                    :columns="processList[index].sysCcUserList"
                    @confirm="onConfirmCcr"
                    @cancel="processList[index].ccLevel = false"
            />
          </van-popup>
        </div>
      </div>
    </div>
    <van-button
      round
      type="info"
      @click="submitClick"
      :disabled="isDisabled"
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
  vacationE04VacationAdd,
  vacationE03VacationTypeList,
} from "@/api/leave/index";
export default {
  data() {
    return {
      isDisabled:false,
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
        sysExamineConfigProcessList:[]
      },
    };
  },
  methods: {
    readClick(file, detail) {
      let fd = new FormData();
      console.log("param.file", file);
      file.status = 'uploading'
      file.message = '上传中...'
      fd.append("file", file.file); // 要提交给后台的文件
      minioChooseBucketUpload(fd)
        .then((response) => {
          this.form.fileList.splice(this.form.fileList.indexOf(file), 1);
          let obj = {};
          console.log("提交文件", response.msg);
          obj.url = response.msg;
          this.form.fileList.push(obj);
          file.status = ''
          file.message = ''
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
      console.log("this.form.vEndTime",this.form.vEndTime);
      if (this.dataName == "vStartTime") {
        console.log("1111",this.form.vEndTime);
        if (this.form.vEndTime && this.form.vEndTime != "请选择结束时间") {
          this.form.duration = timeFn(this.form.vStartTime, this.form.vEndTime) + 1;
          console.log("2222",this.form.duration);
        }
      }
      this.openDate = false;
    },
    submitClick() {
      // let str = "";
      // for (const item of this.form.fileList) {
      //   str += item.url + ",";
      // }
      if(this.form.fileList.length>0){
        if(this.form.fileList[0].status === 'uploading') {
          Toast("请等待图片上传完成");
          return
        }
      }

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
        sysExamineConfigProcessList
      } = this.form;



      this.isDisabled = true
      vacationE04VacationAdd({
        leaveType,
        type,
        vStartTime,
        vEndTime,
        duration,
        remarks,
        isSalary,
        fileUrls,
        sysExamineConfigProcessList
      }).then((res)=>{
        this.$router.go(-1)
      }).catch(e=>{
        this.form.sysExamineConfigProcessList = [];
        this.isDisabled = false
      });
    },
    // 请假
    leaveClick() {
      this.open = true;
    },

    // 审批人
    onConfirmSpr(value) {
      this.processList.forEach(res=>{
        if(res.level == value.level){
          res.userName = value.nickName;
          res.userId = value.userId;
          res.spLevel=false;
        }
      })
    },

    // 抄送人
    onConfirmCcr(value) {
      this.processList.forEach(res=>{
        if(res.level == value.level){
          res.ccUserName = value.nickName;
          res.ccUserId = value.userId;
          res.ccLevel=false;
        }
      })
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
            console.log("11111",timeFn(this.form.vStartTime, this.form.vEndTime));
            this.form.duration =
          timeFn(this.form.vStartTime, this.form.vEndTime)==NaN?timeFn(this.form.vStartTime, this.form.vEndTime) + 1:0;
          }
          break;
      }
      this.openDate = true;
    },
  },
  mounted() {
    vacationE03VacationTypeList({}).then((res) => {
      this.actions = res.rows;
    });
    queryExamineConfigProcessList({ type: 2 }).then((res) => {
      console.log(res);
      this.processList = res.rows;
      this.processList.forEach(res=>{
        this.$set(res,'spLevel',false)
        this.$set(res,'ccLevel',false)
      })
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
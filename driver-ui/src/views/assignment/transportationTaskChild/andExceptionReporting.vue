<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-row>
        <van-col span="24"
          ><van-form>
            <van-field
              clickable
              is-link
              arrow-direction="down"
              :value="value"
              label="异常类型:"
              placeholder="请选择"
              label-width="auto"
              @click="showPicker = true"
            />

            <!-- <van-popup v-model="showPicker" position="bottom">
              <van-picker
                show-toolbar
                :columns="listItems"
                @confirm="onConfirm"
                @cancel="showPicker = false"
              />
            </van-popup> -->

            <van-action-sheet
              v-model="showPicker"
              :actions="listItems"
              cancel-text="取消"
              close-on-click-action
              @cancel="showPicker = false"
              @select="onConfirm"
            /> </van-form
        ></van-col>
      </van-row>
      <van-form>
        <van-field
          v-model="form.remarks"
          rows="2"
          autosize
          :label="form.type == 2 ? '任务异常情况说明' : '异常情况描述:'"
          label-width="auto"
          type="textarea"
          maxlength="50"
          placeholder="异常情况描述"
          show-word-limit
        />
      </van-form>
      <van-field name="uploader" label="图片">
        <template #input>
          <van-uploader v-model="files" :after-read="readClick" multiple />
        </template>
      </van-field>
      <van-cell-group>
        <van-field
          v-if="form.type == 2"
          v-model="form.kilometre"
          label="实际行驶公里数"
          placeholder="请输入实际行驶公里数"
          label-width="auto"
        />
      </van-cell-group>
      <!-- <van-form>
        <van-field readonly value="value" label="审批人:" />
      </van-form>
      <van-form>
        <van-field readonly value="value" label="抄送人:" />
      </van-form> -->
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
      <div style="margin: 16px">
        <van-button
          @click="subClick"
          round
          block
          type="info"
          native-type="submit"
          >提交</van-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { upload } from "@/components/upload";
import { Toast } from "vant";
import {
  transportTaskM07TransportTaskExceptionInfo,
  transportTaskM11ResubmitTaskException,
  transportTaskM03TransportTaskInfo,
} from "@/api/assignment/index";
import { queryExamineConfigProcessList } from "@/api/api";
export default {
  components: {
    Header,
  },
  data() {
    return {
      // 异常类型
      value: "",
      // 异常
      listItems: [
        { name: "运行异常", value: 1 },
        { name: "任务异常", value: 2 },
      ],

      processList: [],
      showPicker: false,
      files: [],
      message: "",
      form: {
        type: undefined,
        taskId: undefined,
        remarks: undefined,
        shipmentXid: undefined,
        kilometre: undefined,
        fileUrls: [],
      },
      fileStatus:[]
    };
  },
  mounted() {
    queryExamineConfigProcessList({  type: 9,relationId: this.$route.query.id}).then((res) => {
      console.log(res);
      this.processList = res.rows;
      this.processList.forEach(res=>{
        this.$set(res,'spLevel',false)
        this.$set(res,'ccLevel',false)
      })
    });
    transportTaskM07TransportTaskExceptionInfo({
      id: this.$route.query.id,
    }).then((res) => {
      this.form = res.data;
      this.value = res.data.typeEnum;
      if (res.data.taskExceptionFileList) {
        let arr = [];
        for (const item of res.data.taskExceptionFileList) {
          arr.push({ url: item });
        }
        this.files = arr;
      }
    });
    this.form.taskId = this.$route.params.id;
    transportTaskM03TransportTaskInfo({ id: this.$route.query.id }).then(
      (res) => {
        console.log(res);
        this.form.shipmentXid = res.data.shipmentXid;
      }
    );
    this.form.taskId = this.$route.query.id;
  },
  methods: {
    subClick() {
      this.form.sysExamineConfigProcessList = []
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

      let acc = false;
      this.fileStatus.forEach(r=>{
        if(!r){
          acc = true
        }
      })
      if(acc){
        Toast("照片有未上传完毕的，请等待上传完毕后再提交");
        return;
      }

      let arr = [];
      for (const item of this.files) {
        arr.push(item.url);
      }
      this.form.fileUrls = arr;
      console.log(this.form);
      transportTaskM11ResubmitTaskException(this.form).then((res) => {
        this.handleRouter();
      });
    },
    // 上传
    readClick(file, detail) {
      this.fileStatus[detail.index] = false
      file.status = 'uploading';
      file.message = '上传中...';
      upload(file, detail).then((res) => {
        this.files.length--;
        let obj = {};
        obj.url = res;
        this.files.push(obj);
        this.fileStatus[detail.index] = true
      });
    },
    handleRouter() {
      this.$router.push("/abnormalConditionRecord/" + this.$route.params.id);
    },
    // 异常
    onConfirm(value) {
      this.value = value.name;
      this.form.type = value.value;
      this.form.kilometre = undefined;
      Toast(value.name);
      this.showPicker = false;
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
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

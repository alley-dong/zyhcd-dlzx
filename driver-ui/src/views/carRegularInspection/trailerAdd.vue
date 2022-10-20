<template>
  <div>
    <!-- 挂车定检 -->
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <van-cell
        title="车牌号"
        is-link
        :value="form.licensePlate"
        @click="URLClick(2)"
        title-class="left-title"
      />
      <div style="font-weight: 600; margin-top: 0.2rem">点检内容</div>
      <!-- -->
      <van-radio-group
        v-for="(item, index) in problemList"
        :key="index"
        v-model="item.status"
      >
        <van-cell class="text" :title="index + 1 + '、' + item.name" />
        <van-cell-group>
          <van-cell clickable title="正常" @click="toggle(index)" class="text">
            <template #right-icon>
              <van-radio
                shape="round"
                icon-size="16"
                :name="1"
                ref="checkboxes"
              />
            </template>
          </van-cell>
          <van-cell
            clickable
            title="需维修"
            @click="toggle(index)"
            class="text"
          >
            <template #right-icon>
              <van-radio
                shape="round"
                icon-size="16"
                :name="2"
                ref="checkboxes"
              />
            </template>
          </van-cell>
        </van-cell-group>
        <div @click="numClick(index)">
          <van-field name="uploader" label="上传照片">
            <template #input>
              <van-uploader
                v-model="item.files"
                :before-read="bfClick"
                :after-read="readClick"
                :max-count="5"
                multiple
              />
            </template>
          </van-field>
        </div>
      </van-radio-group>
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
    <van-button
      round
      type="info"
      :disabled="isDisabled"
      @click="submitClick"
      style="margin-bottom: 1rem; width: 50%"
      >{{ sub_text }}</van-button
    >
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  minioChooseBucketUpload,
  minioChooseBucketUploadList,
  queryExamineConfigProcessList,
} from "@/api/api";
import {
  inspectI02Add,
  inspectI07Add,
  inspectI04List,
} from "@/api/carRegularInspection/index";
import { Toast } from "vant";
export default {
  components: {
    Header,
  },
  data() {
    return {
      isDisabled: false,
      form: {
        licensePlate: "请选择车牌号",
        vehicleId: undefined,
        sysExamineConfigProcessList:[]
      },
      //  uploader: [{ url: 'https://img.yzcdn.cn/vant/leaf.jpg' }],
      answer: undefined,
      processList: [],
      problemList: [],
      problemList1: [],
      url_index: undefined,
      sub_text: "提交",
      uploadStatus:false
    };
  },
  mounted() {
    queryExamineConfigProcessList({
      type: 13,
      relationId:this.$route.query.id
    }).then((res) => {
      console.log(res);
      this.processList = res.rows;
      this.processList.forEach(res=>{
        this.$set(res,'spLevel',false)
        this.$set(res,'ccLevel',false)
      })
    });
    console.log(this.$store.state);
    switch (this.$store.state.type) {
      case 2:
        // 车
        if (!this.$store.state.vehicle) break;
        if(localStorage.getItem("problemList")){
          this.problemList = JSON.parse(localStorage.getItem("problemList"));
        } else {
          this.getDicts("c_vehicle_inspect_hang").then((res) => {
            for (const item of res.data) {
              let obj = {};
              obj.itemType = item.dictValue;
              obj.name = item.dictLabel;
              obj.status = undefined;
              obj.files = [];
              this.problemList.push(obj);
            }
          });
        }
        this.form.vehicleId = this.$store.state.vehicle.vehicleId;
        this.form.type = 2
        this.form.licensePlate = this.$store.state.vehicle.licensePlate;
        console.log(this);
        return;
    }
    this.getDicts("c_vehicle_inspect_hang").then((res) => {
      for (const item of res.data) {
        let obj = {};
        obj.itemType = item.dictValue;
        obj.name = item.dictLabel;
        obj.status = undefined;
        obj.files = [];
        this.problemList.push(obj);
      }
    });
    if (this.$route.query.id) {
      inspectI04List(this.$route.query.id).then((res) => {
        console.log(res.data);
        this.form = res.data;
        for (const item of res.data.cVehicleInspectItemList) {
          for (const data of this.problemList) {
            if (item.itemType == data.itemType) {
              data.status = item.status;
              let temp = item.files.split(",");
              if(temp){
                temp.forEach(p=>{
                  if(p != ''){
                    let obj = {url:p};
                    data.files.push(obj);
                  }
                })
              }
            }
          }
        }
        this.problemList = JSON.parse(JSON.stringify(this.problemList));
      });
    }
  },

  methods: {
    remove_problemList() {
      localStorage.removeItem("problemList");
    },
    numClick(index) {
      if(this.uploadStatus){
        return false
      }
      this.url_index = index;
    },
    submitClick() {
      if (this.check()) {

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
        this.isDisabled = true;
        if (this.$route.query.id) {
          inspectI07Add({
            ...this.form,
            cVehicleInspectItemList: this.problemList1,
          }).then((res)=>{
            this.$router.push("/carRegularInspection");
          }).catch(e=>{
            this.isDisabled = false;
          });
        } else {
          inspectI02Add({
            ...this.form,
            cVehicleInspectItemList: this.problemList1,
          }).then((res) => {
            Toast(res.msg);
            this.remove_problemList(); 
            this.$router.go(-1);
          }).catch(e=>{
            this.form.sysExamineConfigProcessList = [];
            this.isDisabled = false;
          });
        }
      }
    },
    toggle(index) {
      // this.$refs.checkboxes[index].toggle();
    },
    check() {
      if (this.form.licensePlate == "请选择车牌号") {
        Toast("请选择车牌号");
        return;
      }
      let arr = JSON.parse(JSON.stringify(this.problemList));
      for (const [index,item] of arr.entries()) {
        if (item.status && item.status == 2) {
          if(item.files.length == 0){
            Toast(`请上传第${index+1}项的图片`);
            return;
          }
        }
        let str = "";
        for (const data of item.files) {
          str += data.url + ",";
        }
        item.files = str;
      }
      this.problemList1 = arr;
      return true;
    },
    // 跳转
    URLClick(val) {
      localStorage.setItem("problemList", JSON.stringify(this.problemList));
      this.$store.dispatch("CarNumber", val);

      this.$router.push("/search");
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

    bfClick(){
      if(this.uploadStatus){
        Toast("上一张图片过大，请耐心等待上一张图片上传完毕");
        return false
      }
      return true
    },

    // 上传
    readClick(file, detail) {
      this.uploadStatus = true;
      file.status = 'uploading';
      file.message = '上传中...';

      let fd = new FormData();
      if(file instanceof Array){
        for(let i = 0;i<file.length;i++){
          fd.append("file", file[i].file);
        }
      } else {
        fd.append("file", file.file);
      }
      minioChooseBucketUploadList(fd)
        .then((response) => {
          if(file instanceof Array){
            for(let i = 0;i<file.length;i++){
              this.problemList[this.url_index].files.splice(
                      this.problemList[this.url_index].files.indexOf(file[i]),
                      1
              );
            }
          } else {
            this.problemList[this.url_index].files.splice(
                    this.problemList[this.url_index].files.indexOf(file),
                    1
            );
          }
          var urlArr = response.data;
          urlArr.forEach(e=>{
            let obj = {};
            obj.url = e;
            this.problemList[this.url_index].files.push(obj);
          })
          console.log(this.problemList[this.url_index]);
          this.uploadStatus = false;
          file.status = 'done';
          file.message = '上传完成';
        })
        .catch((err) => {
          console.log(err);
          this.uploadStatus = false;
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.text {
  text-align: left;
  font-size: 0.25rem;
}
.click-disable{
  pointer-events: none;
}
</style>
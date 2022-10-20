<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row>
        <van-col span="24"
          ><van-form>
            <van-field
              clickable
              is-link
              arrow-direction="down"
              :value="value"
              label="选择补助类型:"
              placeholder="请选择"
              label-width="auto"
              @click="showPicker = true"
            />
            <van-popup v-model="showPicker" position="bottom">
              <van-picker
                show-toolbar
                :columns="listItems"
                @confirm="onConfirm"
                @cancel="showPicker = false"
              />
            </van-popup> </van-form
        ></van-col>
      </van-row>
      <van-form>
        <van-field
          is-link
          arrow-direction="down"
          clickable
          :value="form.subsidyStartDate"
          label="开始时间:"
          placeholder="请选择:"
          @click="showTime1 = true"
        />
        <van-popup v-model="showTime1" position="bottom">
          <van-datetime-picker
                  type="datetime"
                  @confirm="onConfirmData1"
                  v-model="currentDate"
                  :formatter="formatter"
                  @cancel="showTime1 = false"
          />
        </van-popup>

      </van-form>
      <van-form>
        <van-field
          is-link
          arrow-direction="down"
          clickable
          :value="form.subsidyEndDate"
          label="结束时间:"
          placeholder="请选择:"
          @click="showTime = true"
        />
        <van-popup v-model="showTime" position="bottom">
          <van-datetime-picker
                  type="datetime"
                  @confirm="onConfirmData"
                  v-model="currentDate"
                  :formatter="formatter"
                  @cancel="showTime = false"
          />
        </van-popup>
      </van-form>
      <van-form>
        <van-field
          v-model="form.remarks"
          rows="2"
          autosize
          label="申请原因:"
          type="textarea"
          maxlength="50"
          placeholder="请输入申请原因"
          show-word-limit
        />
      </van-form>
      <!-- <van-form> -->
        <!-- <van-field readonly value="value" label="审批人:" />
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
          round
          block
          @click="subClick"
          :disabled="isDisabled"
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
import {
  driverSubsidyF02Add,
  driverSubsidyF07GetInfo,
  driverSubsidyF07Edit,
} from "@/api/subsidyList/subsidy";
import { formatDate } from "@/components/index";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessList,
} from "@/api/api";
import { Toast } from "vant";
export default {
  components: {
    Header,
  },
  mounted() {
    if (this.$route.query.id) {
      driverSubsidyF07GetInfo({ id: this.$route.query.id }).then((res) => {
        this.form = res.data;
        this.value = res.data.type == 1 ? "异地装卸货" : "加班";
      });
    }
    queryExamineConfigProcessList({ type: 1, relationId: this.$route.query.id }).then((res) => {
      this.processList = res.rows;
      this.processList.forEach(res=>{
        this.$set(res,'spLevel',false)
        this.$set(res,'ccLevel',false)
      })
    });
  },

  data() {
    return {
      isDisabled: false,
      processList: [],
      // 物品值
      value: "",
      // 车牌号
      carValue: "",
      // 时间
      timeValue: "",
      // 物品列表 类型 1异地装卸货 2加班
      listItems: [
        { text: "异地装卸货", value: 1 },
        { text: "加班", value: 2 },
      ],
      // 车牌号下拉
      carList: ["1111", "2222"],
      showPicker: false,
      showPickerSp: false,
      showPickerCc: false,
      showCar: false,
      showTime: false,
      showTime1: false,
      currentDate: new Date(),
      message: "",
      form: {
        type: undefined,
        subsidyStartDate: undefined,
        subsidyEndDate: undefined,
        remarks: undefined,
        sysExamineConfigProcessList:[]
      },
    };
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

      if (this.$route.query.id) {
        this.isDisabled = true
        driverSubsidyF07Edit(this.form).then((res) => {
          this.$router.push("/subsidy");
        });
        this.isDisabled = false
        return;
      }

      this.isDisabled = true
      driverSubsidyF02Add(this.form).then((res) => {
        this.$router.push("/subsidy");
      }).catch(e=>{
        this.form.sysExamineConfigProcessList = [];
        this.isDisabled = false
      });
    },
    // 物品
    onConfirm(value) {
      this.value = value.text;
      this.form.type = value.value;
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

    // 修改时间格式
    formatter(type, val) {
      if (type === 'year') {
        return `${val}年`;
      } else if (type === 'month') {
        return `${val}月`;
      } else if (type === 'day') {
        return `${val}日`;
      } else if (type === 'hour') {
        return `${val}时`;
      } else if (type === 'minute') {
        return `${val}分`;
      }
      return val;
    },

    // 车牌号触发事件
    onConfirmCar(car) {
      this.carValue = car;
      this.showCar = false;
    },
    // 时间
    onConfirmData(date, name) {
      this.form.subsidyEndDate = formatDate(date, "yyyy-MM-dd HH:mm");
      this.showTime = false;
    },
    onConfirmData1(date, name) {
      this.form.subsidyStartDate = formatDate(date, "yyyy-MM-dd HH:mm");
      this.showTime1 = false;
    },
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

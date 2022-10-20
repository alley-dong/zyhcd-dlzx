<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row>
        <van-col span="15"
          ><van-form>
            <van-field
              clickable
              is-link
              readonly
              arrow-direction="down"
              :value="form.goodsName"
              label="领用物品:"
              placeholder="请选择"
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
        <van-col span="9">
          <van-form>
            <van-field
              :value="form.amount"
              label="库存:"
              label-width="auto"
              readonly
            />
          </van-form>
        </van-col>
      </van-row>
      <van-form>
        <van-field readonly :value="form.specification" label="规格：" />
      </van-form>
      <van-form>
        <van-field readonly :value="form.unit" label="单位：" />
      </van-form>
      <van-form>
        <van-field
          is-link
          arrow-direction="down"
          readonly
          clickable
          :value="form.licensePlate"
          label="车牌号:"
          placeholder="请选择车牌号"
          @click="handleSearch"
        />
        <van-popup v-model="showCar" position="bottom"> </van-popup>
      </van-form>
      <van-form>
        <van-field
          v-model="form.number"
          label="数量："
          placeholder="请输入领用数量"
        />
      </van-form>
      <van-field
        v-model="message"
        rows="2"
        autosize
        label="领用原因:"
        type="textarea"
        maxlength="50"
        placeholder="请输入领用原因"
        show-word-limit
      />
      <van-form>
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
      </van-form>
      <div style="margin: 16px">
        <van-button
          type="info"
          round
          style="margin: 1rem 0; width: 50%"
          :disabled="isDisable"
          @click="handleClickCom"
          >提交</van-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  L07GetGoodList,
  goodsReceivingRecordL08GetInfo,
  L04Add,
  L12EditSave,
} from "@/api/articlesUsingRegistrationList/index";
import {
  J02SafeAccidentInfo,
  queryExamineConfigProcessList,
} from "@/api/reportTheAccident/incidentRecordDetails";
import { Toast } from "vant";

export default {
  components: {
    Header,
  },
  data() {
    return {
      isDisable:false,
      // 物品列表
      listItems: [],
      showPicker: false,
      showTime: false,
      showCar: false,
      processList: undefined,
      message: undefined,
      form: {
        goodsTypeId: "",
        goodsTypeName: "",
        goodsId: "",
        goodsName: "",
        vehicleId: "",
        licensePlate: "",
        specification: "",
        unit: "",
        number: "",
        amount: "",
        sysExamineConfigProcessList:[]
      },
    };
  },
  mounted() {
    if (this.$route.query.id && !this.$store.state.AccidentCar.licensePlate) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
          this.message = res.data.remarks;
          // this.getL07GetGoodList(res.data.goodsId);
        }
      );
    }
    this.getqueryExamineConfigProcessList();
    this.getL07GetGoodList();
    if (this.$store.state.AccidentCar.licensePlate) {
      this.form = JSON.parse(sessionStorage.getItem("workingDinner"));
      if (this.form) {
        this.form.licensePlate = this.$store.state.AccidentCar.licensePlate;
        this.form.vehicleId = this.$store.state.AccidentCar.id;
        this.message = this.form.remarks;
        console.log(this.form);
      } else {
        this.form = {
          goodsTypeId: "",
          goodsTypeName: "",
          goodsId: "",
          goodsName: "",
          vehicleId: "",
          licensePlate: "",
          specification: "",
          unit: "",
          number: "",
          amount: "",
        };
      }
    }
  },
  methods: {
    //  
    getqueryExamineConfigProcessList() {
      queryExamineConfigProcessList({
        type: 12,
        relationId: this.$route.query.id,
      }).then((res) => {
        console.log(res);
        this.processList = res.rows;
        this.processList.forEach(res=>{
          this.$set(res,'spLevel',false)
          this.$set(res,'ccLevel',false)
        })
      });
    },
    // 提交
    handleClickCom() {
      console.log(this.form.id);

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
      if (!this.message) {
        Toast("请填写领用原因");
        return;
      }
      this.form.remarks = this.message;
      if(check){
        Toast("请选择审批人");
        this.form.sysExamineConfigProcessList = [];
        return;
      }
      this.isDisable = true
      if (this.form.id) {
        L12EditSave(this.form).then((res) => {
          sessionStorage.removeItem("workingDinner");
          localStorage.removeItem("replacementPartList");
          localStorage.removeItem("spareListItems");
          this.$router.push("/recipientsRecords");
          this.$store.state.AccidentCar = {};
        }).catch(e=>{
          this.isDisable = false
        });
        return;
      }
      L04Add(this.form).then((res) => {
        sessionStorage.removeItem("workingDinner");
        localStorage.removeItem("replacementPartList");
        localStorage.removeItem("spareListItems");
        this.$router.push("/recipientsRecords");
        this.$store.state.AccidentCar = {};
      }).catch(e=>{
        this.form.sysExamineConfigProcessList = [];
        this.isDisable = false
      });
    },
    // 车辆
    handleSearch() {
      this.form.remarks = this.message;
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
      this.$router.push("/theAccidentReportSearch");
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

    // 物品列表
    getL07GetGoodList(my_id) {
      let id = {
        goodsTypeId: this.$route.query.goodsTypeId || this.$route.params.id,
      };
      L07GetGoodList(id).then((res) => {
        for (const item of res.rows) {
          item.text = item.name;
        }
        this.listItems = res.rows;
        // for (const item of res.rows) {
        //   // debugger
        //   if (item.id == my_id) {
        //     this.onConfirm(item)
        //   }
        // }
        console.log(this.listItems);
        // this.form.amount = res.rows[0].amount
      });
    },
    // 物品
    onConfirm(value) {
      this.form.goodsTypeName = value.goodsTypeName;
      this.form.goodsTypeId = value.goodsTypeId;
      this.form.specification = value.specification; //规格
      this.form.goodsName = value.name;
      this.form.amount = value.amount;
      this.form.goodsId = value.id;
      this.form.unit = value.unit; //单位
      this.showPicker = false;
    },
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

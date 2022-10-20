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
              arrow-direction="down"
              v-model="form.goodsName"
              label="领用物品:"
              placeholder="请选择"
              @click="showPicker = true"
              readonly
            />
            <van-popup v-model="showPicker" position="bottom">
              <van-picker
                value-key="name"
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
        <van-field
          clearable
          readonly
          name="picker"
          :value="form.specification"
          label="规格："
          placeholder=""
        />
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
      </van-form>
      <van-form>
        <van-field
          is-link
          arrow-direction="down"
          clickable
          readonly
          :value="form.routeName"
          label="线路:"
          placeholder="请选择线路"
          @click="showLine = true"
        />
        <van-popup v-model="showLine" position="bottom">
          <van-picker
            value-key="name"
            show-toolbar
            :columns="lineList"
            @confirm="onConfirmLine"
            @cancel="showLine = false"
          />
        </van-popup>
      </van-form>
      <van-form>
        <van-field v-model="form.number" label="数量：" />
      </van-form>
      <van-form>
        <van-field
          readonly
          name="picker"
          :value="form.createBy"
          label="领用人"
        />
      </van-form>
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
      <!-- <button @click="handleCom">提交</button> -->
      <van-button
        round
        type="info"
        style="margin: 1rem 0; width: 50%"
        @click="handleCom"
        :disabled="isDisable"
        >提交</van-button
      >
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  L07GetGoodList,
  RoutList,
  L02Add,
  goodsReceivingRecordL08GetInfo,
  L12EditSave,
} from "@/api/articlesUsingRegistrationList/index";
import {
  J02SafeAccidentInfo,
  queryExamineConfigProcessList,
} from "@/api/reportTheAccident/incidentRecordDetails";
import { Toast } from "vant";
import Cookies from "js-cookie";
export default {
  components: {
    Header,
  },
  data() {
    return {
      isDisable:false,
      processList: undefined,
      // 物品值
      value: "",
      // 车牌号
      carValue: "",
      // 线路列表
      lineValue: "",
      // 物品列表
      listItems: [],
      // 车牌号下拉
      carList: [],
      // 线路
      lineList: [],
      listItems1: [],
      showPicker: false,
      showCar: false,
      showLine: false,
      form: {
        createBy: "",
        goodsTypeId: "",
        goodsTypeName: "",
        goodsId: "",
        goodsName: "",
        vehicleId: "",
        licensePlate: "",
        routeId: "",
        routeName: "",
        specification: "",
        unit: "",
        number: "",
        amount: "",
        sysExamineConfigProcessList:[]
      },
    };
  },
  mounted() {
    this.form.createBy = Cookies.get("username1");
    this.getL07GetGoodList();
    this.getqueryExamineConfigProcessList();
    this.getRoutList();
    if (this.$route.query.id &&!this.$store.state.AccidentCar.licensePlate) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
          this.form.routeName = res.data.routeName
        }
      );
    }
    if (this.$store.state.AccidentCar.licensePlate) {
      this.form = JSON.parse(sessionStorage.getItem("workingDinner"));
      if (this.form) {
        this.form.licensePlate = this.$store.state.AccidentCar.licensePlate;
        this.form.vehicleId = this.$store.state.AccidentCar.id;
      } else {
        this.form = {
          createBy: undefined,
          goodsTypeId: undefined,
          goodsTypeName: undefined,
          goodsId: undefined,
          goodsName: undefined,
          vehicleId: undefined,
          licensePlate: undefined,
          routeId: undefined,
          routeName: undefined,
          specification: undefined,
          unit: undefined,
          number: undefined,
          amount: undefined,
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
    handleCom() {

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
      L02Add(this.form).then((res) => {
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
    // 线路
    getRoutList() {
      RoutList({}).then((res) => {
        this.lineList = res.data;
      });
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
    // 车牌号
    handleSearch() {
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
      this.$router.push("/theAccidentReportSearch");
    },
    // 物品
    getL07GetGoodList() {
      let id = { goodsTypeId: this.$route.query.goodsTypeId|| this.$route.params.id };
      L07GetGoodList(id).then((res) => {
        this.listItems = res.rows;
        // this.form.amount = res.rows[0].amount
        // res.rows.forEach((item) => {
        //   this.form.createBy = item.createBy
        // })
      });
    },
    // 物品
    onConfirm(value) {
      this.form.goodsTypeName = value.goodsTypeName;
      this.form.goodsTypeId = value.goodsTypeId;
      this.form.specification = value.specification; //规格
      this.form.amount = value.amount;
      this.form.goodsName = value.name;
      this.form.goodsId = value.id;
      this.form.unit = value.unit; //单位
      this.showPicker = false;
    },
    // 车牌号触发事件
    onConfirmCar(car) {
      this.form.licensePlate = car.licensePlate;
      this.showCar = false;
    },
    // 线路
    onConfirmLine(line) {
      this.form.routeName = line.name;
      this.form.routeId = line.id;

      this.showLine = false;
    },
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

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
              :value="form.goodsName"
              label="领用物品:"
              placeholder="请选择"
              readonly
              @click="showPicker = true" />

            <van-popup v-model="showPicker" position="bottom">
              <van-picker
                value-key="name"
                show-toolbar
                :columns="listItems"
                @confirm="onConfirm"
                @cancel="showPicker = false"
              /> </van-popup></van-form
        ></van-col>
        <van-col span="9">
          <van-form>
            <van-field
              :value="form.amount"
              label="库存:"
              readonly
              label-width="auto"
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
          arrow-direction
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
          label="领用日期:"
          :value="'共选择了' + form.receiveDate.length + '个日期'"
          @click="show = true"
          readonly
        >
          <!-- <template #input>
            <van-calendar
              title="领用日期:"
              :poppable="false"
              :show-confirm="false"
              @select="onConfirmData"
              type="multiple"
              :value="timeValue"
              @confirm="onConfirmData"
              :style="{ height: '500px' }"
            />
          </template> -->
        </van-field>
      </van-form>
      <van-calendar
        v-model="show"
        title="领用日期:"
        type="multiple"
        :min-date="minDate"
        :max-date="maxDate"
        @confirm="onConfirmData"
        :style="{ height: '500px' }"
        :formatter="formatter"
        :default-date="defaultDate"
        @unselect="handle_unselect"
        @close="handleClose"
      />
      <van-form>
        <van-field :value="form.createBy" label="领用人:" readonly />
      </van-form>
      <van-form>
        <van-field
          readonly
          v-model="form.receiveDate.length"
          label="领用数量："
          placeholder="请输入领用数量"
        />
      </van-form>
      <van-form>
        <van-field
          v-model="message"
          rows="2"
          autosize
          label="领用原因:"
          type="textarea"
          maxlength="50"
          show-word-limit
        />
      </van-form>
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
        <div style="margin: 16px">
          <van-button
            round
            type="info"
            style="margin: 1rem 0; width: 50%"
            :disabled="isDisable"
            @click="handleCom"
            >提交</van-button
          >
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { formatDate } from "@/components/index";
import {
  L07GetGoodList,
  L03Add,
  goodsReceivingRecordL08GetInfo,
  L12EditSave,
} from "@/api/articlesUsingRegistrationList/index";

import { queryExamineConfigProcessList } from "@/api/api";
import Cookies from "js-cookie";
import { Toast } from "vant";
export default {
  name: "workingDdinner",
  components: {
    Header,
  },
  data() {
    return {
      defaultDate: [],
      isDisable:false,
      dis_date_list: [],
      minDate: new Date(1970, 0, 1),
      maxDate: new Date(2060, 0, 31),
      show: false,
      text: undefined,
      processList: [],
      // 物品值
      value: "",
      // 规格
      specificationValues: "",
      // 单位
      unitValue: "",
      // 车牌号
      carValue: "",
      // 时间
      timeValue: "",
      // 物品列表
      listItems: [],
      // 车牌号下拉
      carList: [],
      showPicker: false,
      message: undefined,
      form: {
        amount: "",
        createBy: "",
        goodsTypeId: "",
        goodsTypeName: "",
        goodsId: "",
        goodsName: "",
        vehicleId: "",
        licensePlate: null,
        specification: "",
        unit: "",
        number: "",
        receiveDate: [],
        sysExamineConfigProcessList:[],
      },
    };
  },
  mounted() {
    let now = formatDate(new Date(), "yyyy");
    let mm = formatDate(new Date(), "MM");
    (this.minDate = new Date(now, parseInt(mm) - 3, 1)),
      (this.maxDate = new Date(now, parseInt(mm) + 1, 31));
    console.log(this.minDate, this.maxDate);
    this.form.createBy = Cookies.get("username1");
    this.getL07GetGoodList();
    if (this.$route.query.id&&!this.$store.state.AccidentCar.licensePlate ) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
          this.form.receiveDate = [];
          let arr = [];

          this.message = res.data.remarks
          this.defaultDate = [];
          for (const item of res.data.gGoodsReceivingRecordDates) {
            if (item.receiveDate) {
              let date = item.receiveDate.split("-");
              date.shift();
              arr.push(date);
              var t = new Date(item.receiveDate);
              this.defaultDate.push(t);
              this.form.receiveDate.push(item.receiveDate);
            }
          }
          this.dis_date_list = arr;
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
          amount: "",
          createBy: "",
          goodsTypeId: "",
          goodsTypeName: "",
          goodsId: "",
          goodsName: "",
          vehicleId: "",
          licensePlate: null,
          specification: "",
          unit: "",
          number: "",
          receiveDate: [],
        };
      }
    }
    queryExamineConfigProcessList({ type: 12, relationId: this.$route.query.id }).then(
      (res) => {
        console.log(res);
        this.processList = res.rows;
        this.processList.forEach(res=>{
          this.$set(res,'spLevel',false)
          this.$set(res,'ccLevel',false)
        })
      }
    );
  },

  methods: {
    handleClose(val) {
      console.log("关闭", val);
    },
    handle_unselect(val) {
      let date = formatDate(val, "yyyy-MM-dd");
      let index = this.form.receiveDate.indexOf(date);
      this.form.receiveDate.splice(index, 1);
    },
    formatter(day) {
      if (!this.form.id) {
        return day;
      }
      const month = day.date.getMonth() + 1;
      const date = day.date.getDate();
      // if (month==1) {
      // if (date ==25) {
      //   day.type = "end";

      // }
      // }
      for (const item of this.dis_date_list) {
        if (month == item[0]) {
          if (date == item[1]) {
            // day.type = "disabled";
            // day.className = "van-calendar__day--multiple-selected";
          }
        }
      }

      return day;
    },
    // 提交
    handleCom() {
      if (this.form.receiveDate.length==0) {
          Toast("请选择领用日期");
        return;
      }
      if (!this.message) {
        Toast("请填写领用原因");
        return;
      }

      let check = false;
      this.form.sysExamineConfigProcessList = [],
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
      };
      this.form.number = this.form.receiveDate.length;
      this.form.remarks = this.message;
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
      L03Add(this.form).then((res) => {
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
    // 车辆搜索
    handleSearch() {
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
    // 物品下拉
    getL07GetGoodList() {
      let id = { goodsTypeId:this.$route.query.goodsTypeId|| this.$route.params.id };
      console.log(id);
      L07GetGoodList(id).then((res) => {
        this.listItems = res.rows;
        // res.rows.forEach((item) => {
        //   this.form.createBy = item.createBy;
        //   // this.amount = item.amount
        // });
      });
    },
    // 物品
    onConfirm(value) {
      this.form.goodsTypeName = value.goodsTypeName;
      this.form.goodsTypeId = value.goodsTypeId;
      this.form.specification = value.specification; //规格
      this.form.goodsName = value.name;
      this.form.goodsId = value.id;
      this.form.unit = value.unit; //单位
      this.form.amount = value.amount;
      this.showPicker = false;
    },

    onConfirmData(date) {
      console.log("1111",date);
      let arr = [];
      date.forEach((e) => {
        // this.form.receiveDate.push(
        //   JSON.parse(JSON.stringify(formatDate(e, "yyyy-MM-dd")))
        // );
        arr.push(formatDate(e, "yyyy-MM-dd"));
      });
      this.form.receiveDate = arr;
      // this.form.receiveDate = Array.form(new Set(this.form.receiveDate))
      // console.log(this.form.receiveDate);
      // debugger
      // this.form.unit= this.form.receiveDate.length;
      this.showTime = false;
      this.show = false;
    },
  },
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
.gray {
  color: #ccc;
}
</style>

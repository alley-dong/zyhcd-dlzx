<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row>
        <van-col span="24"
          ><van-form>
            <van-field
              clickable
              arrow-direction="down"
              :value="form.goodsTypeName"
              label="领用物品:"
              placeholder="请选择"
              readonly />

            <van-popup v-model="showPicker" position="bottom">
              <van-picker
                value-key="goodsTypeName"
                show-toolbar
                :columns="listItems"
                @confirm="onConfirm"
                @cancel="showPicker = false"
              /> </van-popup></van-form
        ></van-col>
        <!-- <van-col span="9">
          <van-form>
            <van-field
              :value="form.amount"
              label="库存:"
              readonly
              label-width="auto"
            />
          </van-form>
        </van-col> -->
      </van-row>
      <van-form>
        <van-field readonly :value="form.specification" label="规格：" />
      </van-form>
      <van-form>
        <van-field readonly :value="form.unit" label="单位：" />
      </van-form>
      <van-form>
        <van-field
          arrow-direction
          readonly
          clickable
          :value="form.licensePlate"
          label="车牌号:"
          placeholder="请选择车牌号"
        />
      </van-form>
      <van-form>
        <!-- <van-field
          label="领用日期:"
          :value="'共选择了' + form.receiveDate.length + '个日期'"
          @click="show = true"
          readonly
        > -->
        <div class="label-readonly">
          <van-field label="领用日期:" readonly>
            <template #input>
              <!--   <van-calendar
              title="领用日期:"
              :poppable="false"
              :show-confirm="false"
              @select="onConfirmData"
              type="multiple"
              :value="timeValue"
              @confirm="onConfirmData"
              :style="{ height: '500px' }"
            />-->
              <div v-for="(item, index) in dis_date_list" :key="index">
                {{ item }}
              </div>
            </template>
          </van-field>
        </div>
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
        :default-date="null"
        @close="handleClose"
      />
      <van-form>
        <van-field :value="form.createBy" label="领用人:" readonly />
      </van-form>
      <van-form>
        <van-field
          readonly
          v-model="dis_date_list.length"
          label="领用数量："
          placeholder="请输入领用数量"
        />
      </van-form>
      <van-form>
        <van-field
          v-model="message"
          rows="2"
          autosize
          label="申请原因:"
          type="textarea"
          maxlength="50"
          show-word-limit
          readonly
        />
      </van-form>
         <van-form>
        <van-row>
          <div v-for="item in processList" :key="item.id">
            <van-col span="24">
              <van-field readonly :value="item.userName" label="审批人:">
                <template #button>
                  <van-tag type="primary">{{item.statusEnum}}</van-tag>
                </template>
              </van-field>
            </van-col>
          </div>
        </van-row>
      </van-form>
    </div>
    <van-button
      round
      type="info"
      style="margin: 1rem 0; width: 50%"
      v-show="form.status === 1"
      @click="handCom(form.status)"
    >
      {{ form.status | button_text }}
    </van-button>
    <!-- 已通过 -->
    <van-button
      round
      type="info"
      style="margin: 1rem 0; width: 50%"
      v-show="form.status === 2"
      @click="handCom(form.status)"
    >
      {{ form.status | button_text }}
    </van-button>
    <!-- 已拒绝 -->
    <van-button
      round
      type="info"
      style="margin: 1rem 0; width: 50%"
      v-show="form.status === 3"
      @click="handCom(form.status)"
    >
      {{ form.status | button_text }}
    </van-button>
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
  goodsReceivingRecordL10Affirm,
  goodsReceivingRecordL08Recall,
} from "@/api/articlesUsingRegistrationList/index";

import { queryExamineConfigProcessListDetail } from "@/api/api";
import Cookies from "js-cookie";
import { Toast } from "vant";
export default {
  name: "workingDdinner",
  components: {
    Header,
  },
  data() {
    return {
      dis_date_list: [],
      minDate: new Date(2021, 0, 1),
      maxDate: new Date(2021, 0, 31),
      show: false,
      text: undefined,
      processList: undefined,
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
      },
    };
  },
  mounted() {
    let now = formatDate(new Date(), "yyyy");
    let mm = formatDate(new Date(), "MM");
    console.log(mm);
    (this.minDate = new Date(now, parseInt(mm) - 3, 1)),
      (this.maxDate = new Date(now, parseInt(mm) + 1, 31));
    console.log(this.minDate, this.maxDate);
    this.form.createBy = Cookies.get("username1");
    this.getL07GetGoodList();
    if (this.$route.query.id) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
          this.form.receiveDate = [];
          let arr = [];
          this.message = res.data.remarks;
          for (const item of res.data.gGoodsReceivingRecordDates) {
            if (item.receiveDate) {
              // let date = item.receiveDate.split("-");
              // date.shift();
              arr.push(item.receiveDate);
            }
          }
          this.dis_date_list = arr;
        }
      );
    }
    if (this.$store.state.AccidentCar.licensePlate) {
      this.form = JSON.parse(sessionStorage.getItem("workingDinner"));
      this.form.licensePlate = this.$store.state.AccidentCar.licensePlate;
      this.form.vehicleId = this.$store.state.AccidentCar.id;
    }
    queryExamineConfigProcessListDetail({ type: 12, relationId: this.$route.params.id }).then(
      (res) => {
        console.log(res);
        this.processList = res.data.sysExamineUserTaskList;
      }
    );
  },
  filters: {
    status: function (value) {
      switch (value) {
        case 1:
          return "待审核";
        case 2:
          return "已通过";
        case 3:
          return "已拒绝";
        case 4:
          return "已领取";
        case 5:
          return "已撤回";
        default:
          break;
      }
    },
    button_text: function (value) {
      switch (value) {
        case 1:
          return "撤销";
        case 2:
          return "确认领取";
        case 3:
          return "重新领取";

        default:
          break;
      }
    },
  },
  methods: {
    handCom(val) {
      switch (val) {
        case 1:
          goodsReceivingRecordL08Recall({ id: this.$route.params.id }).then(
            (res) => {
              Toast(res.msg);
              this.$router.go(-1);
            }
          );
          break;
        case 2:
          goodsReceivingRecordL10Affirm({ id: this.$route.params.id }).then(
            (res) => {
              Toast(res.msg);
              this.$router.go(-1);
            }
          );
          break;
        case 3:
          L07GetGoodList({ id: this.$route.params.id }).then((res) => {
            let listItems = res.rows;
            // for (const item of listItems) {
              // if (item.goodsTypeId == this.form.goodsTypeId) {
                this.$router.push({
                  path: "/workingDdinner/" + this.form.goodsId,
                  query: { id: this.$route.params.id, goodsTypeId:this.form.goodsTypeId},
                });
                this.$route.meta.title = this.form.goodsTypeName;
              // }
            // }
          });
          break;
        default:
          break;
      }
    },
    handleClose(val) {
      console.log("关闭", val);
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
            day.type = "disabled";
            day.className = "gray";
          }
        }
      }

      return day;
    },
    // 提交
    // handleCom() {
    //   if (!this.message) {
    //     Toast("请选择领用原因");
    //     return;
    //   }
    //   this.form.number = this.form.receiveDate.length;
    //   this.form.remarks = this.message;
    //   if (this.form.id) {
    //     L12EditSave(this.form).then((res) => {
    //       sessionStorage.removeItem("workingDinner");
    //       localStorage.removeItem("replacementPartList");
    //       localStorage.removeItem("spareListItems");
    //       this.$router.go(-1);
    //     });
    //     return;
    //   }
    //   L03Add(this.form).then(() => {
    //     sessionStorage.removeItem("workingDinner");
    //     localStorage.removeItem("replacementPartList");
    //     localStorage.removeItem("spareListItems");
    //     this.$router.go(-1);
    //   });
    // },
    // 车辆搜索
    handleSearch() {
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
      this.$router.push("/theAccidentReportSearch");
    },
    // 物品下拉
    getL07GetGoodList() {
      let id = { goodsTypeId: this.$route.params.id };
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
      console.log(date);
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
<style lang="scss">
.label-readonly {
  .van-field__control:read-only {
    display: block;
  }
}
</style>

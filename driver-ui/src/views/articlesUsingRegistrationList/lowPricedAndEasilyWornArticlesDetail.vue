<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row>
        <van-col span="24"
          ><van-form>
            <van-field
              clickable
              readonly
              arrow-direction="down"
              :value="form.goodsName"
              label="领用物品:"
              placeholder="请选择"
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
        <!-- <van-col span="9">
          <van-form>
            <van-field
              :value="form.amount"
              label="库存:"
              label-width="auto"
              readonly
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
          arrow-direction="down"
          readonly
          clickable
          :value="form.licensePlate"
          label="车牌号:"
          placeholder="请选择车牌号"
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
        readonly
        show-word-limit
      />
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
      <!-- <div style="margin: 16px">
        <van-button type="info" @click="handleClickCom">提交</van-button>
      </div> -->
      <!-- 待审核 -->
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
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  L07GetGoodList,
  goodsReceivingRecordL08GetInfo,
  L04Add,
  goodsReceivingRecordL10Affirm,
  goodsReceivingRecordL08Recall,
  L12EditSave,
} from "@/api/articlesUsingRegistrationList/index";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessListDetail,
} from "@/api/api";
import { Toast } from "vant";

export default {
  components: {
    Header,
  },
  data() {
    return {
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
      },
    };
  },
  mounted() {
    if (this.$route.query.id) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
          this.message = res.data.remarks;
        }
      );
    }
    this.getqueryExamineConfigProcessList();
    this.getL07GetGoodList();
    if (this.$store.state.AccidentCar.licensePlate) {
      this.form = JSON.parse(sessionStorage.getItem("workingDinner"));
      this.form.licensePlate = this.$store.state.AccidentCar.licensePlate;
      this.form.vehicleId = this.$store.state.AccidentCar.id;
    }
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
            console.log(JSON.stringify(listItems));
            // for (const item of listItems) {
              // if (item.goodsTypeId == this.form.goodsTypeId) {
                this.$router.push({
                  path: "/lowPricedAndEasilyWornArticles/" + this.form.goodsId,
                  query: { id: this.$route.params.id,goodsTypeId:this.form.goodsTypeId },
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
    //  
    getqueryExamineConfigProcessList() {
      queryExamineConfigProcessListDetail({
        type: 12,
        relationId: this.$route.params.id,
      }).then((res) => {
        console.log(res);
        this.processList = res.data.sysExamineUserTaskList;
      });
    },
    // 提交
    handleClickCom() {
      console.log(this.form.id);
      if (!message) {
        Toast("请填写领用原因");
        return;
      }
      if (this.form.id) {
        L12EditSave(this.form).then((res) => {
          sessionStorage.removeItem("workingDinner");
          localStorage.removeItem("replacementPartList");
          localStorage.removeItem("spareListItems");
          this.$router.push("/recipientsRecords");
        });
        return;
      }
      L04Add(this.form).then((res) => {
        sessionStorage.removeItem("workingDinner");
        localStorage.removeItem("replacementPartList");
        localStorage.removeItem("spareListItems");
        this.$router.push("/recipientsRecords");
      });
    },
    // 车辆
    handleSearch() {
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
      this.$router.push("/theAccidentReportSearch");
    },
    // 物品列表
    getL07GetGoodList() {
      let id = { goodsTypeId: this.$route.params.id };
      L07GetGoodList(id).then((res) => {
        for (const item of res.rows) {
          item.text = item.name;
        }
        this.listItems = res.rows;
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

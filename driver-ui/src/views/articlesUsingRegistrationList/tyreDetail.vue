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
          arrow-direction="down"
          readonly
          clickable
          :value="form.licensePlate"
          label="车牌号:"
        />
      </van-form>
      <van-form>
        <van-field v-model="form.number" label="数量：" readonly />
      </van-form>
      <van-field
        v-model="message"
        rows="2"
        autosize
        label="领用原因:"
        type="textarea"
        maxlength="50"
        show-word-limit
        readonly
      />
      <van-form>
        <van-row>
          <div v-for="item in processList" :key="item.id">
            <van-col span="24">
              <van-field readonly :value="item.userName" label="审批人:">
                <template #button>
                  <van-tag type="primary">{{ item.statusEnum }}</van-tag>
                </template>
              </van-field>
            </van-col>
          </div>
        </van-row>
      </van-form>
      <!-- <div style="margin: 16px">
        <van-button type="info" round style="margin: 1rem 0 ; width: 50%" @click="handleClickCom">提交</van-button>
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
  L04Add,
  goodsReceivingRecordL08GetInfo,
  L12EditSave,
  goodsReceivingRecordL10Affirm,
  goodsReceivingRecordL08Recall,
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
      // 物品值
      value: "",
      listItems: undefined,
      processList: undefined,
      // 车牌号
      carValue: "",
      showPicker: false,
      showCar: false,
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
    this.getqueryExamineConfigProcessList();

    this.getL07GetGoodList();
    if (this.$route.query.id) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
        }
      );
    }
    if (this.$store.state.AccidentCar.licensePlate) {
      // 车牌号
      this.form = JSON.parse(sessionStorage.getItem("workingDinner"));
      this.form.licensePlate = this.$store.state.AccidentCar.licensePlate;
      this.form.vehicleId = this.$store.state.AccidentCar.id;
    }
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
                  path: "/tyre/" +  this.form.goodsId,
                  query: { id: this.$route.params.id },
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
        this.processList = res.rows;
      });
    },
    // 物品领用申请——低值易耗品 / 轮胎
    handleClickCom() {
      if (!this.message) {
        Toast("请填写领用原因");
        return;
      }
      this.form.remarks = this.message;
      if (this.form.id) {
        L12EditSave(this.form).then((res) => {
          sessionStorage.removeItem("workingDinner");
          localStorage.removeItem("replacementPartList");
          localStorage.removeItem("spareListItems");
          this.$router.go(-1);
        });
        return;
      }
      L04Add(this.form).then((res) => {
        sessionStorage.removeItem("workingDinner");
        localStorage.removeItem("replacementPartList");
        localStorage.removeItem("spareListItems");
        this.$router.go(-1);
      });
    },
    // 车牌号
    handleClickCr() {
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
      this.$router.push("/theAccidentReportSearch");
    },
    // 物品下拉
    getL07GetGoodList() {
      let id = { goodsTypeId: this.$route.params.id };
      L07GetGoodList(id).then((res) => {
        this.listItems = res.rows;
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
};
</script>

<style scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

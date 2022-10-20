<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-form>
        <van-field readonly label="领用物品:" :value="form.goodsName" />
      </van-form>
      <van-form>
        <van-field readonly label="规格:" :value="form.specification" />
      </van-form>
      <van-form>
        <van-field readonly label="单位:" :value="form.unit" />
      </van-form>
      <van-form>
        <van-field readonly label="车牌号:" :value="form.licensePlate" />
      </van-form>
      <van-form>
        <van-field readonly label="线路:" :value="form.routeName" />
      </van-form>
      <van-form>
        <van-field readonly label="数量:" :value="form.number" />
      </van-form>
      <van-form>
        <van-field readonly label="领用人:" :value="form.updateBy" />
      </van-form>
      <van-form>
        <van-field readonly label="申请日期:" :value="form.createTime" />
      </van-form>
      <van-row>
        <van-col span="18">
          <van-form>
            <van-field
              readonly
              label="审批人:"
              :value="form.reviewUser"
            /> </van-form
        ></van-col>
        <van-col span="6">
          <div style="margin-top: 20px">
            {{ form.statusEnum }}
          </div>
        </van-col>
      </van-row>
    </div>
    <!-- 待审核 -->
    <van-button
      type="primary"
      v-show="form.status === 1"
      @click="handCom(form.status)"
    >
      {{ form.status | button_text }}
    </van-button>
    <!-- 已通过 -->
    <van-button
      type="primary"
      v-show="form.status === 2"
      @click="handCom(form.status)"
    >
      {{ form.status | button_text }}
    </van-button>
    <!-- 已拒绝 -->
    <van-button
      type="primary"
      v-show="form.status === 3"
      @click="handCom(form.status)"
    >
      {{ form.status | button_text }}
    </van-button>
    <!-- <button @click="handCom(3)">已拒绝</button> -->
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  goodsReceivingRecordL08GetInfo,
  goodsReceivingRecordL10Affirm,
  goodsReceivingRecordL08Recall,
} from "@/api/articlesUsingRegistrationList/index";
import { L07GetGoodList } from "@/api/articlesUsingRegistrationList/index";
import { Toast } from "vant";
import Vue from "vue";
Vue.use(Toast);
export default {
  components: {
    Header,
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
  data() {
    return {
      form: {},
    };
  },
  mounted() {
    this.$route.meta.title = "记录详情";
    goodsReceivingRecordL08GetInfo({ id: this.$route.params.id }).then(
      (res) => {
        this.form = res.data;
      }
    );
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
            for (const item of listItems) {
              if (item.goodsTypeId == this.form.goodsTypeId) {
                console.log(item.goodsType);
                switch (this.form.goodsType) {
                  case 1:
                    this.$router.push({
                      path: "/urea/" + item.id,
                      query: { id: this.$route.params.id },
                    });
                    break;
                  case 2:
                    this.$router.push({
                      path: "/workingDdinner/" + item.id,
                      query: { id: this.$route.params.id },
                    });
                    break;
                  case 3:
                    this.$router.push({
                      path: "/tyre/" + item.id,
                      query: { id: this.$route.params.id },
                    });
                    break;
                  case 5:
                    this.$router.push({
                      path: "/replacementPart/" + item.id,
                      query: { id: this.$route.params.id },
                    });
                    break;

                  default:
                    this.$router.push({
                      path: "/lowPricedAndEasilyWornArticles/" + item.id,
                      query: { id: this.$route.params.id },
                    });
                    this.$route.meta.title = this.form.goodsTypeName;
                    break;
                }
              }
            }
            // this.form.amount = res.rows[0].amount
          });
          break;
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

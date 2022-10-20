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
              v-model="form.goodsTypeName"
              readonly
              label="领用物品:"
              placeholder="请选择"
            />
            <van-popup v-model="showPicker" position="bottom">
              <van-picker
                value-key="goodsTypeName"
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
         
          arrow-direction="down"
          readonly
          clickable
          :value="form.licensePlate"
          label="车牌号:"
          placeholder="请选择车牌号"
        />
      </van-form>
      <van-form>
        <van-field
         
          arrow-direction="down"
          clickable
          readonly
          :value="form.routeName"
          label="线路:"
          placeholder="请选择线路"
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
        <van-field
          readonly
          v-model="form.number"
          label="数量："
        />
      </van-form>
        <van-form>
          <van-field
            readonly
            name="picker"
            :value="form.createBy"
            label="领用人"
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
      <!-- <button @click="handleCom">提交</button> -->
      <!-- <van-button type="info" @click="handleCom">提交</van-button> -->
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
  goodsReceivingRecordL10Affirm,
  goodsReceivingRecordL08Recall,
} from "@/api/articlesUsingRegistrationList/index";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessListDetail,
} from "@/api/api";
import { Toast } from "vant";
import Cookies from "js-cookie";
export default {
  components: {
    Header,
  },
  data() {
    return {
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
        createBy:undefined,
      },
    };
  },
  mounted() {
    this.form.createBy = Cookies.get("username1");
    this.getL07GetGoodList();
    this.getqueryExamineConfigProcessList();
    this.getRoutList();
    if (this.$route.query.id) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
        }
      );
    }
    if (this.$store.state.AccidentCar.licensePlate) {
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
                  path: "/urea/" + this.form.goodsId,
                  query: { id: this.$route.params.id, goodsTypeId:this.form.goodsTypeId },
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
    handleCom() {
      if (this.form.id) {
        L12EditSave(this.form).then((res) => {
          sessionStorage.removeItem("workingDinner");
          localStorage.removeItem("replacementPartList");
          localStorage.removeItem("spareListItems");
          this.$router.push('/recipientsRecords')
        });
        return;
      }
      L02Add(this.form).then((res) => {
        sessionStorage.removeItem("workingDinner");
        localStorage.removeItem("replacementPartList");
        localStorage.removeItem("spareListItems");
        this.$router.push('/recipientsRecords')
      });
    },
    // 线路
    getRoutList() {
      RoutList({}).then((res) => {
        this.lineList = res.data;
      });
    },
    // 车牌号
    handleSearch() {
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
      this.$router.push("/theAccidentReportSearch");
    },
    // 物品
    getL07GetGoodList() {
      let id = { goodsTypeId: this.$route.params.id };
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

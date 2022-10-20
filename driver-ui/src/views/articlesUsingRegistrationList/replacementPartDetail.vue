<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <van-row>
        <!-- <van-col span="24"
          ><van-form>
            <van-field
              readonly
              :value="form.goodsTypeName"
              label="领用物品:"
              placeholder="请选择"
            /> </van-form
        ></van-col> -->
      </van-row>
      <van-form>
        <van-field
          arrow-direction="down"
          readonly
          clickable
          :value="form.licensePlate"
          label="车牌号:"
          placeholder="请选择"
        />
      </van-form>
      <van-form>
        <van-field readonly label="备件:">
          <!-- <template #input> -->
          <!-- <van-button type="info"  size="small" style="width:1.5rem" @click="handleChange"
              >选择</van-button
            > -->
          <!-- </template> -->
        </van-field>
      </van-form>

      <el-table :data="listItems" style="width: 100%" border>
        <el-table-column prop="goodsName" align="center" label="备件名称">
        </el-table-column>
        <!-- <el-table-column prop="amount" align="center" width="50px" label="库存"> </el-table-column> -->
        <el-table-column label="数量" align="center">
          <template slot-scope="scope">
            <!-- <van-stepper
              input-width="0.4rem"
              :max="scope.row.amount"
              v-model="scope.row.number"
            /> -->
            {{ scope.row.number }}
          </template>
        </el-table-column>
        <!-- <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="small"  @click.stop="handleDelete(scope)"
              >删除</el-button
            >
          </template>
        </el-table-column> -->
      </el-table>
      <van-form>
        <van-field
          arrow-direction="down"
          clickable
          :value="form.typeEnum"
          readonly
          label="零件用途:"
        />
        <van-popup v-model="showreplacementList" position="bottom">
          <van-picker
            show-toolbar
            value-key="name"
            :columns="replacementList"
            @confirm="onConfirmre"
            @cancel="showreplacementList = false"
          />
        </van-popup>
      </van-form>
      <van-field
        v-model="form.remarks"
        rows="2"
        autosize
        readonly
        label="备注:"
        type="textarea"
        maxlength="50"
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
      <!-- <div style="margin: 16px"> -->
      <!-- <van-button round style="margin: 1rem 0 ; width: 50%" type="info" @click="handleCom">提交</van-button> -->
      <!-- </div> -->
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
import Vue from "vue";
// import { Stepper } from 'vant'
// Vue.use(Stepper)
import Header from "@/components/Header";
import {
  L07GetGoodList,
  L06Add,
  L12EditSave,
  goodsReceivingRecordL08GetInfo,
  goodsReceivingRecordL10Affirm,
  goodsReceivingRecordL08Recall,
} from "@/api/articlesUsingRegistrationList/index";
import {
  J02SafeAccidentInfo,
  queryExamineConfigProcessList,
} from "@/api/reportTheAccident/incidentRecordDetails";
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
      processList: undefined,
      form: {
        name: "",
        goodsTypeId: "",
        goodsTypeName: "",
        vehicleId: "",
        licensePlate: undefined,
        type: "",
        gGoodsReceivingRecordItems: [{ goodsId: "", goodsName: "", number: 3 }],
      },
      // 物品列表
      listItems: [],
      // 备件
      replacementList: [
        { name: "维修", type: "1" },
        { name: "换装", type: "2" },
      ],
      value: "",
      message: "",
      showPicker: false,
      show: true,
      showreplacementList: false,
    };
  },
  mounted() {
    this.getqueryExamineConfigProcessList();
    console.log("收到");
    if (this.$route.query.id) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
         this.listItems = res.data.gGoodsReceivingRecordItems
        }
      );
    }
    // let spareListItems = JSON.parse(localStorage.getItem("spareListItems"));

    // // console.log(spareListItems,
    // // replacementPartList);
    // // debugger
    // if (spareListItems) {
    //   this.listItems = spareListItems;
    // }
    // // this.getL07GetGoodList()
    // if (this.$store.state.AccidentCar&&this.$store.state.AccidentCar.licensePlate) {
    //   this.form = JSON.parse(sessionStorage.getItem("workingDinner"));
    //   this.form.licensePlate = this.$store.state.AccidentCar.licensePlate;
    //   this.form.vehicleId = this.$store.state.AccidentCar.id;
    // }
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
            //   if (item.goodsTypeId == this.form.goodsTypeId) {
                this.$router.push({
                  path: "/replacementPart/" + this.form.goodsTypeId,
                  query: { id: this.$route.params.id, goodsTypeId:this.form.goodsTypeId },
                });
                this.$route.meta.title = this.form.goodsTypeName;
            //   }
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
    // 备件选择
    handleChange() {
      localStorage.setItem(
        "replacementPartList",
        JSON.stringify(this.listItems)
      );

      this.$router.push({
        path: "/sparePartsSelection",
        query: {
          goodsTypeId: this.$route.params.id,
          goodsTypeName: this.form.goodsTypeName,
        },
      });
    },
    // 备件申请
    handleCom() {
      if (!this.form.licensePlate) {
        Toast("请选择车牌号");
      }
      console.log(this.form.id);
      this.form.goodsTypeId = this.$route.params.id;
      this.form.goodsTypeName = "备件";
      this.form.gGoodsReceivingRecordItems = this.listItems;
      for (const item of this.form.gGoodsReceivingRecordItems) {
        //goodsTypeId
        if (item.id) {
          item.goodsId = item.id;
          delete item.id;
        }
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
      L06Add(this.form).then((res) => {
        sessionStorage.removeItem("workingDinner");
        localStorage.removeItem("replacementPartList");
        localStorage.removeItem("spareListItems");
        this.$router.push("/recipientsRecords");
      });
    },
    // 零件用途
    onConfirmre(value) {
      this.form.name = value.name;
      this.form.type = value.type;
      this.showreplacementList = false;
      console.log(this.form);
    },
    // 车牌号搜索
    handleSearch() {
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
      this.$router.push("/theAccidentReportSearch");
    },

    // getL07GetGoodList() {
    //   let id = { id: this.$route.params.id }
    //   L07GetGoodList(id).then((res) => {
    //     this.listItems = res.rows
    //     res.rows.forEach((el) => {
    //       this.form.goodsTypeName = el.goodsTypeName
    //       this.form.goodsTypeId = el.goodsTypeId
    //       this.form.gGoodsReceivingRecordItems[0].goodsId = el.id
    //       this.form.gGoodsReceivingRecordItems[0].goodsName = el.name
    //     })
    //   })
    // },
    //物品
    onConfirm(value) {
      this.form.goodsTypeName = value.goodsTypeName;
      this.showPicker = false;
    },
    handleDelete(row) {
      let index = row.$index;
      this.listItems.splice(index, 1);
      console.log(row);
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

<style lang="less" scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

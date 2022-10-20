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
          is-link
          arrow-direction="down"
          readonly
          clickable
          :value="form.licensePlate"
          label="车牌号:"
          placeholder="请选择"
          @click="handleSearch"
        />
      </van-form>
      <van-form>
        <van-field label="备件:">
          <template #input>
            <van-button
              type="info"
              size="small"
              style="width: 1.5rem"
              @click="handleChange"
              >选择</van-button
            >
          </template>
        </van-field>
      </van-form>

      <el-table :data="listItems" style="width: 100%" border>
        <el-table-column prop="name" align="center" label="备件名称">
        </el-table-column>
        <el-table-column prop="amount" align="center" width="50px" label="库存">
        </el-table-column>
        <el-table-column label="数量" align="center">
          <template slot-scope="scope">
            <van-stepper
              input-width="0.4rem"
              :max="scope.row.amount"
              v-model="scope.row.number"
            />
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="small" @click.stop="handleDelete(scope)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <van-form>
        <van-field
          is-link
          arrow-direction="down"
          clickable
          :value="form.name"
          readonly
          label="零件用途:"
          placeholder="请选择"
          @click="showreplacementList = true"
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
        v-model="message"
        rows="2"
        autosize
        label="备注:"
        type="textarea"
        maxlength="50"
        placeholder="请输入备注内容"
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
          round
          style="margin: 1rem 0; width: 50%"
          type="info"
          @click="handleCom"
          :disabled="isDisable"
          >提交</van-button
        >
      </div>
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
      processList: undefined,
      form: {
        name: "",
        goodsTypeId: "",
        goodsTypeName: "",
        vehicleId: "",
        licensePlate: undefined,
        type: "",
        gGoodsReceivingRecordItems: [{ goodsId: "", goodsName: "", number: 3 }],
        sysExamineConfigProcessList:[]
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
    let spareListItems = JSON.parse(localStorage.getItem("spareListItems"));
    if (this.$route.query.id && !this.$store.state.AccidentCar.licensePlate) {
      goodsReceivingRecordL08GetInfo({ id: this.$route.query.id }).then(
        (res) => {
          this.form = res.data;
          this.form.name = res.data.typeEnum;
          this.message = res.data.remarks;
          for (const item of res.data.gGoodsReceivingRecordItems) {
            item.name = item.goodsName;
          }
          if (spareListItems) {
            this.listItems = spareListItems;
          } else {
            this.listItems = res.data.gGoodsReceivingRecordItems;
          }
        }
      );
    }
    // console.log(spareListItems,
    // replacementPartList);
    if (spareListItems) {
      this.listItems = spareListItems;
    }
    // this.getL07GetGoodList()
    if (this.$store.state.AccidentCar.licensePlate) {
      this.form = JSON.parse(sessionStorage.getItem("workingDinner"));
      this.message = this.form.remarks;
      if (this.form) {
        this.form.licensePlate = this.$store.state.AccidentCar.licensePlate;
        this.form.vehicleId = this.$store.state.AccidentCar.id;
      } else {
        this.form = {
          name: undefined,
          goodsTypeId: undefined,
          goodsTypeName: undefined,
          vehicleId: undefined,
          licensePlate: undefined,
          type: undefined,
          gGoodsReceivingRecordItems: [
            { goodsId: "", goodsName: "", number: 3 },
          ],
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
    // 备件选择
    handleChange() {
      console.log(this.listItems);
      localStorage.setItem("spareListItems", JSON.stringify(this.listItems));
      this.form.remarks = this.message;
      sessionStorage.setItem("workingDinner", JSON.stringify(this.form));
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
        return
      }
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
      console.log(this.form.id);
      this.form.goodsTypeId = this.$route.params.id;
      this.form.goodsTypeName = "备件";
      this.form.remarks = this.message
      this.form.gGoodsReceivingRecordItems = this.listItems;
      for (const item of this.form.gGoodsReceivingRecordItems) {
        //goodsTypeId
        if (item.id) {
          item.goodsName = item.name;
          item.goodsId = item.goodsId||item.id;
          delete item.id;
          delete item.name;
        }
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
      L06Add(this.form).then((res) => {
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
    // 零件用途
    onConfirmre(value) {
      this.form.name = value.name;
      this.form.type = value.type;
      this.showreplacementList = false;
      console.log(this.form);
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
    // 车牌号搜索
    handleSearch() {
      this.form.remarks = this.message;
      localStorage.setItem("spareListItems", JSON.stringify(this.listItems));
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
};
</script>

<style lang="less" scoped>
.van-field {
  border-bottom: 1px solid #dcdcdc;
}
</style>

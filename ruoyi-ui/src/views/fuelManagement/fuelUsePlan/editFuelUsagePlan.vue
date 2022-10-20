<template>
  <div>
    <div class="box">
      <div class="btn-group">
        <el-button @click="resetForm">返回</el-button>
        <el-button type="primary" @click="submitForm('updateform')"
          >保存</el-button
        >
      </div>
    </div>
    <div class="wrap">
      <div class="text">编辑</div>
      <div class="line"></div>
    </div>
    <div style="display: flex; margin-top: 30px">
      <el-row :gutter="15" style="width: 80%">
        <el-form
          ref="updateform"
          :model="updateform"
          :rules="rules"
          size="medium"
          label-width="121px"
        >
          <el-col :span="12">
            <el-form-item label="车牌号" prop="licensePlate">
              <el-input
                v-model="updateform.licensePlate"
                placeholder="请输入车牌号"
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
            <el-form-item label="车辆类型" prop="vehicleModel">
              <el-input
                v-model="updateform.vehicleModelName"
                placeholder="请输入车辆类型"
                clearable
                :disabled="true"
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>

            <el-form-item label="线路" prop="routeName">
              <el-select
                v-model="updateform.routeId"
                placeholder="请选择线路"
                clearable
                @change="addquotaRouterBoxData"
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in routerOptions"
                  :key="index"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>

            <el-form-item
              label="计划趟数"
              prop="planQuantity"
              :rules="[
                {
                  max: 99999,
                  required: true,
                  type: 'number',
                  message: '请输入趟数且不大于五位数',
                },
              ]"
            >
              <el-input
                v-model.number="updateform.planQuantity"
                placeholder="请输入计划趟数"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="油料信息">
              <el-table border :data="tableData" style="width: 98%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px">
                <el-table-column label="油料类型" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.fuelTypeEnum }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="油料单价" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.unitPrice }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="油料定额" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.fuelQuota }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="油料金额" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.fuelPrice }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="空放定额" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.ballastQuota }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="空放金额" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.ballastQuotaPrice }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="倒货定额" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.pourQuota }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="倒货金额" align="center">
                  <template slot-scope="scope">
                    <span>{{ scope.row.pourQuotaPrice }}</span>
                  </template>
                </el-table-column>
                <el-table-column label="操作" align="center">
                  <template slot-scope="scope">
                    <el-button size="small" type="primary" @click="editClick(scope.row)"
                    >编辑</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form >
        <el-form-item label="油料类型" label-width="80px">
          <el-input  v-model="editfuelTypeEnum" disabled="true"/>
        </el-form-item>
        <el-form-item label="油料定额" label-width="80px">
          <el-input  v-model="editquota" disabled="true"/>
        </el-form-item>
        <el-form-item label="空放定额" label-width="80px">
          <el-input  v-model="editballastQuota" disabled="true"/>
        </el-form-item>
        <el-form-item label="倒货定额" label-width="80px">
          <el-input v-model="editpourQuota" disabled="true"/>
        </el-form-item>
        <el-form-item label="油料单价" label-width="80px">
          <el-input v-model="editprice" placeholder="请输入油料单价" type="number" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary"  @click="addPrice(editfuelTypeEnum, editprice, editquota,editballastQuota,editpourQuota)" >确 定</el-button>
        <el-button @click="open=false">取 消</el-button>
      </div>
    </el-dialog>
  </div>


</template>

<script>
import {
  getI08CarFuelOilPlanEditSave,
  I08CarFuelOilPlanByLicensePlateUpdate,
  I08CarFuelOilPlanByLicensePlateInfo,
  getI09CarFuelOilPlanMoney,
} from "@/api/card/editFuelUsagePlan";
import { routerBox, I09CarFuelOilPlanMoney } from "@/api/card/fuelration";
export default {
  components: {},
  props: [],
  data() {
    var price = (rule, value, callback) => {
      const reg = /^\d+.?\d{0,2}$/;
      if (!value) {
        callback(new Error("请输入油料价格"));
      } else if (!Number(value)) {
        callback(new Error("请输入数字值"));
      } else {
        if (reg.test(value)) {
          callback();
        } else {
          callback(new Error("小数点后最多只能输入两位"));
        }
      }
    };
    return {

      title:'',
      open:false,
      editfuelTypeEnum:"",
      editprice:"",
      editquota:"",
      editballastQuota:"",
      editpourQuota:"",

      formData: {
        licensePlate: undefined,
        etcCardNumber: undefined,
        orderNumber: undefined,
        status: null,
        orderTime: undefined,
        method: undefined,
        obu: undefined,
        id: undefined,
      },
      // 线路下拉
      routerOptions: [],
      tableData: [],
      // 编辑
      updateform: {
        planDate: "",
        licensePlate: "",
        vehicleModel: "",
        vehicleModelName: "",
        vehicleId:"",
        routeId: "",
        routeName: "",
        planQuantity: "",
        info:[],
      },
      rules: {
        unitPrice: [{ required: true, validator: price, trigger: "blur" }],

        id: [
          {
            required: false,
            message: "请选择下拉选择",
            trigger: "change",
          },
        ],
        etcCardNumber: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
        orderNumber: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
        orderTime: [
          {
            required: false,
            message: "请选择时间选择",
            trigger: "change",
          },
        ],
        status: [
          {
            required: false,
            message: "请选择下拉选择",
            trigger: "change",
          },
        ],
        method: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
        obu: [
          {
            required: false,
            message: "请输入单行文本",
            trigger: "blur",
          },
        ],
      },
      field101Options: [
        {
          label: "选项一",
          value: 1,
        },
        {
          label: "选项二",
          value: 2,
        },
      ],
      field107Options: [
        {
          label: "已发货",
          value: 1,
        },
        {
          label: "未发货",
          value: 2,
        },
      ],
    };
  },
  computed: {},
  watch: {},
  created() {
    this.routerselect();
    let trouteId = this.$route.query.routeId;
    if(trouteId != null && trouteId != undefined && trouteId != ''){
      I08CarFuelOilPlanByLicensePlateInfo({
        vehicleModel:this.$route.query.vehicleModel,
        vehicleId:this.$route.query.vehicleId,
        planDate:this.$route.query.planDate,
        routeId:this.$route.query.routeId,
      }).then((res)=>{
        console.log("初始化数据",res)
        this.updateform.planQuantity = res.data.planQuantity
        this.updateform.routeName = res.data.routeName
        let t = [];
        res.data.info.forEach(da=>{
          da.fuelPrice = (da.unitPrice * da.fuelQuota).toFixed(2);
          da.ballastQuotaPrice =  (da.unitPrice * da.ballastQuota).toFixed(2);
          da.pourQuotaPrice =  (da.unitPrice * da.pourQuota).toFixed(2);
        })
        this.tableData = res.data.info
      })
    }
  },
  mounted() {
    this.updateform.id = this.$route.query.id;
    this.updateform.licensePlate = this.$route.query.licensePlate;
    this.updateform.vehicleModelName = this.$route.query.vehicleModelName;
    this.updateform.vehicleModel = this.$route.query.vehicleModel;
    this.updateform.vehicleId = this.$route.query.vehicleId;
    this.updateform.planDate = this.$route.query.planDate;
    this.updateform.routeId = this.$route.query.routeId;
  },
  methods: {
    getfuelPrice() {
      this.updateform.fuelPrice =
        this.updateform.planQuantity *
        this.updateform.unitPrice *
        this.updateform.fuelQuota;
      return this.updateform.fuelPrice.toFixed(2);
    },
    // 路线下拉
    routerselect() {
      routerBox({}).then((res) => {
        this.routerOptions = res.data;
      });
    },

    addPrice(typeName, price,editquota,editballastQuota,editpourQuota){
      if(!this.editprice){
        this.$message({
          type: "error",
          message: "请输入油料单价",
        });
        return;
      }
      if(!(this.editprice.match(/^[0-9]+(\.[0-9]{0,2})?$/g))){
        this.editprice = null;
        this.$message({
          type: "error",
          message: "请输入最多2位小数的数字",
        });
        return;
      }

      if(this.tableData){
        let temp = [];
        this.tableData.forEach(r=>{
          if(r.fuelTypeEnum == typeName){
            r.unitPrice = price;
            r.fuelPrice = (price*editquota).toFixed(2);
            r.ballastQuotaPrice = (price*editballastQuota).toFixed(2);
            r.pourQuotaPrice = (price*editpourQuota).toFixed(2);
          }
          temp.push(r);
        })
        this.tableData = temp;
      }
      this.open = false;
    },

    addquotaRouterBoxData(val) {
      // 对比两个routeName是否相等
      for (const item of this.routerOptions) {
        if (val == item.id) {
          this.updateform.routeName = item.name;
          this.updateform.routeId = item.id;
          console.log("this.updateform.routeName",this.updateform)
          getI09CarFuelOilPlanMoney({
            routeId: item.id,
            vehicleModel: this.updateform.vehicleModel,
            planDate: this.$route.query.planDate
          }).then(res=>{
            let temp = [];
            res.data.forEach(r=>{
              this.$set(r,'fuelQuota',r.quota)
              temp.push(r)
            })
            this.tableData = temp;
          }).catch(e=>{
            this.tableData = [];
          });
        }
      }
    },
    //提交
    submitForm() {
      this.$refs["updateform"].validate((valid) => {
        if (valid) {
          let check = false;
          this.tableData.forEach(r=>{
            if(r.unitPrice == undefined || r.unitPrice == '' || r.unitPrice == null){
              this.$message({
                type: "error",
                message: "请先填写油价",
              });
              check = true;
              return;
            }
          })
          if(check){
            return;
          }
          let tempInfo = [];
          this.tableData.forEach(r=>{
            let params = {
              ballastQuota: r.ballastQuota,
              ballastQuotaPrice: r.ballastQuotaPrice*this.updateform.planQuantity,
              fuelPrice: r.fuelPrice*this.updateform.planQuantity,
              unitPrice: r.unitPrice,
              pourQuota: r.pourQuota,
              fuelQuota: r.fuelQuota,
              pourQuotaPrice: r.pourQuotaPrice*this.updateform.planQuantity,
              planDate: this.updateform.planDate,
              fuelType: r.fuelType,
              fuelTypeEnum: r.fuelTypeEnum,
              id: r.id,
              licensePlate: this.updateform.licensePlate,
              planDate:  this.updateform.planDate,
              planQuantity: this.updateform.planQuantity,
              routeId: this.updateform.routeId,
              routeName: this.updateform.routeName,
              vehicleId: this.updateform.vehicleId,
              vehicleModel: this.updateform.vehicleModel,
              vehicleModelName: this.updateform.vehicleModelName,
            };
            tempInfo.push(params);
          })

          this.updateform.info = tempInfo;
          I08CarFuelOilPlanByLicensePlateUpdate(this.updateform).then((res) => {
            this.$message({
              type: "success",
              message: "修改成功",
            });
            this.resetForm();
          });
        }
      });
    },

    editClick(row) {
      this.open = true;
      this.title = "编辑油料单价";
      this.editfuelTypeEnum = row.fuelTypeEnum
      this.editprice = row.unitPrice
      this.editquota = row.fuelQuota
      this.editballastQuota = row.ballastQuota
      this.editpourQuota = row.pourQuota
    },

    resetForm() {
      this.$router.go(-1);
    },
  },
};
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
  width: 10%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
  font-weight: 400;
}
.line {
  width: 90%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
</style>

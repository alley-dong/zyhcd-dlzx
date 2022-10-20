<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick()">返回</el-button>
        <el-button type="primary" @click="saveClick()">提交</el-button>
        <!-- <el-button type="primary" @click="subClick()">提交</el-button> -->
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">基本信息</div>
      <div class="xian"></div>
    </div>
    <div class="box">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          style="margin-left: 120px"
          :model="formData"
          size="medium"
          label-width="150px"
        >
          <el-col :span="12">
            <el-form-item label="主车车牌号">
              <div style="text-align: left">
                {{ formData.mainLicensePlate }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="主车车型">
              <div style="text-align: left">
                {{ formData.mainVehicleModelName }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车牌号">
              <div style="text-align: left">
                {{ formData.hangLicensePlate }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="挂车车型">
              <div style="text-align: left">
                {{ formData.hangVehicleModelName }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="故障时公里数">
              <div style="text-align: left">{{ formData.mileage }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行驶路线">
              <div style="text-align: left">{{ formData.routeName }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发生故障地点">
              <div style="text-align: left">
                {{ formData.accidentLocation }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="故障时荷载/总质量">
              <div style="text-align: left">{{ formData.totalMass }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="故障时路况">
              <div style="text-align: left">{{ formData.road }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="	故障时天气">
              <div style="text-align: left">{{ formData.weather }}</div>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
    </div>
    <div class="border">
      <div class="text">维修信息</div>
      <div class="xian"></div>
    </div>
    <div class="box">
      <el-row :gutter="15">
        <el-form
          ref="elForm"
          style="margin-left: 120px"
          :model="formData"
          size="medium"
          label-width="150px"
        >
          <el-col :span="24">
            <el-form-item label="维修单号">
              <div style="text-align: left">{{ formData.code }}</div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类别">
              <div style="text-align: left">
                {{ formData.type == 1 ? "主车维保" : "挂车维保" }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="维保类型">
              <div style="text-align: left">
                {{ formData.repairType | repairType }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否是异地维修">
              <div style="text-align: left">
                {{ formData.isOther == "1" ? "是" : "否" }}
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="视频">
              <div style="text-align: left">
                <video
                  v-if="formData.accidentVideo"
                  style="width: 440px; height: 350px"
                  controls="controls"
                  :src="formData.accidentVideo"
                ></video>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="维修服务站">
              <div style="text-align: left">{{ formData.garageName }}</div>
            </el-form-item>
          </el-col>
          <div v-for="item in formData.repairItemDate" :key="item.id">
            <el-col :span="24">
              <el-form-item label="维修保养项">
                <div style="text-align: left">{{ item.itemName }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="维修原因">
                <div style="text-align: left">{{ item.remarks }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="图片">
                  <div style="text-align: left">
                    <img
                      v-for="(im, index) in item.fileList"
                      :src="im"
                      alt=""
                      srcset=""
                      style="width: 200px; height: 200px; margin-right: 20px"
                    />
                  </div>
              </el-form-item>
            </el-col>
          </div>

          <!-- <el-col :span="24">
            <el-form-item label="车辆维修单">
              <div style="text-align:left">{{ formData.remarks }}</div>
            </el-form-item>
          </el-col> -->
          <!-- <el-col :span="24">
            <el-form-item label="车辆维修单">
              <div>
                <img
                  v-for="(item, index) in formData.remarks"
                  :key="index"
                  :src="item"
                  alt=""
                  srcset=""
                  style="width: 200px; height: 200px; margin-right: 20px"
                />
              </div>
            </el-form-item>
          </el-col> -->
        </el-form>
      </el-row>
    </div>
    <div class="border">
      <div class="text">维修明细</div>
      <div class="xian"></div>
    </div>
    <el-button
      size="small"
      type="primary"
      @click="addClick"
      style="float: right; margin: 20px; margin-top: 0"
      >新增</el-button
    >
    <el-table
      border
      :data="tableData1"
      style="width: 98%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <el-table-column label="车型" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.vehicleModelName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单位" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.unit }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单价（元）" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.unitPrice }}</span>
        </template>
      </el-table-column>
      <el-table-column label="工时数" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.duration }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.number }}</span>
        </template>
      </el-table-column>
      <el-table-column label="金额" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.amount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.remarks }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="统计" align="center">
        <template slot-scope="scope">
          <span
            >本月第{{ scope.row.monthNumber }}次，本年第{{
              scope.row.yearNumber
            }}次</span
          >
        </template>
      </el-table-column> -->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:repair:edit']"
            plain
            type="primary"
            >审批</el-button
          > -->
          <el-button size="small" type="primary" @click="editClick(scope.row)"
            >编辑</el-button
          >
          <el-button size="small" @click="delClick(scope.row)" type="danger"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-bottom: 20px">
      合计金额：
      {{ total }}
    </div>

    <div class="border">
      <div class="text">上传附件</div>
      <div class="xian"></div>
    </div>
    <el-upload
      class="upload-demo"
      :action="uploadAction"
      :http-request="UploadFileGroups"
      :before-remove="handleRemove"
      list-type="picture"
      style="margin: 15px 0 50px 37px; width: 419px"
    >
      <el-button size="small" type="primary" icon="el-icon-upload"
      >点击上传</el-button
      >
      <span style="color: #82848a; margin-left: 20px">请上传相关图片</span>
    </el-upload>


    <el-form
      ref="elForm"
      :model="formData1"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="备注" prop="reviewRemarks">
        <el-input
          v-model="formData1.reviewRemarks"
          type="textarea"
          placeholder="请输入备注"
          :autosize="{ minRows: 4, maxRows: 4 }"
          :style="{ width: '100%' }"
        ></el-input>
      </el-form-item>
    </el-form>
    <!-- 列表1弹窗 -->
    <el-dialog
      :title="listTitle"
      :visible.sync="listOpen"
      width="500px"
      append-to-body
    >
      <el-form
        ref="listForm"
        :model="listFormData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="车型" prop="vehicleModel">
          <el-select
            v-model="listFormData.vehicleModel"
            @change="handleChange"
            placeholder="请选择车型"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in vehicleOptions"
              :key="index"
              :label="item.vehicleModelName"
              :value="item.vehicleModel"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-autocomplete
            class="inline-input"
            v-model="listFormData.name"
            :fetch-suggestions="querySearch"
            @select="listChange"
            @change="openBlur(listFormData.name)"
            clearable
            :disabled="!listFormData.vehicleModel"
            :placeholder="
              listFormData.pl_name ? listFormData.pl_name : '请选择名称'
            "
            :style="{ width: '100%' }"
          >
            <template slot-scope="{ item }" :style="{ width: '100%' }">
              <!-- <div class="name">{{ item.id }}</div> -->
              <span class="addr">{{ item.name }}</span>
            </template>
          </el-autocomplete>
          <!-- <el-select
            :disabled="!listFormData.vehicleModel"
            @change="listChange"
            v-model="listFormData.nameId"
            :placeholder="listFormData.pl_name?listFormData.pl_name:'请输入名称'"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in nameOptions"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select> -->
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <!-- <el-input
            v-model="listFormData.unit"
            placeholder="请输入单位"
            clearable
            :style="{ width: '100%' }"
          ></el-input> -->
          <div>{{ listFormData.unit }}</div>
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <!-- <el-input
            v-model="listFormData.unitPrice"
            placeholder="请输入单价"
            clearable
            :style="{ width: '100%' }"
          > -->
          <!-- </el-input> -->
          <div>{{ listFormData.unitPrice }}</div>
        </el-form-item>
        <el-form-item label="工时数" prop="duration">
          <!-- <el-input
            v-model="listFormData.duration"
            placeholder="请输入工时数"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input> -->
          <div>{{ listFormData.duration }}</div>
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input
            v-model="listFormData.number"
            placeholder="请输入数量"
            clearable
            :style="{ width: '100%' }"
            type="number"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input
            v-model="listFormData.remarks"
            type="textarea"
            placeholder="请输入备注"
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="but_loading" @click="handelConfirm"
          >确 定</el-button
        >
        <el-button @click="close">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 列表2弹窗 -->
    <el-dialog
      :title="listTitle2"
      :visible.sync="listOpen2"
      width="500px"
      append-to-body
    >
      <el-form
        ref="listForm"
        :model="listFormData2"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="车型" prop="vehicleModelName">
          <el-select
            v-model="listFormData2.vehicleModel"
            @change="handleChange2"
            placeholder="请选择车型"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in vehicleOptions"
              :key="index"
              :label="item.vehicleModelName"
              :value="item.vehicleModel"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input
            v-model="listFormData2.name"
            placeholder="请输入名称"
            clearable
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input
            v-model="listFormData2.unit"
            placeholder="请输入单位"
            clearable
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input
            v-model="listFormData2.unitPrice"
            placeholder="请输入单价"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="工时数" prop="duration">
          <el-input
            v-model="listFormData2.duration"
            placeholder="请输入工时数"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input
            v-model="listFormData2.number"
            placeholder="请输入数量"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input
            v-model="listFormData2.remarks"
            type="textarea"
            placeholder="请输入备注"
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          :loading="but_loading2"
          @click="handelConfirm2"
          >确 定</el-button
        >
        <el-button @click="close2">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  repairInfo,
  H10NotSubmittedList,
  commonSelectBoxH10AddBox,
  commonSelectBoxH10VehicleModelByGarageId,
  repairH10NotSubmittedAddInfo,
  repairH10NotSubmittedAdd,
  repairH10NotSubmittedEditSave,
  repairH10NotSubmittedEdit,
  repairH10NotSubmittedDelete,
  repairH10NotSubmittedIsNewList,
  repairH10NotSubmittedIsNewAdd,
  repairH10NotSubmittedIsNewEditSave,
  repairH10NotSubmittedIsNewEdit,
  repairH10NotSubmittedIsNewDelete,
  repairH02ReviewTwoLevel,
} from "@/api/maintenance";
import { Message } from 'element-ui'
import { UploadFiles } from "@/api/upLoad/persomUpload";
// import { queryExamineConfigProcessList } from "@/api/com";
export default {
  data() {
    return {
      but_loading: false,
      but_loading2: false,
      fileUrl: [],
      tableData2: [],
      total2: undefined,
      tableData1: [{}],
      total: undefined,
      listTitle2: "",
      listOpen2: false,
      listTitle: "",
      listOpen: false,
      uploadAction: "",
      formData: {
        mainLicensePlate: undefined,
        mainVehicleModelName: undefined,
        hangLicensePlate: undefined,
        hangVehicleModelName: undefined,
        mileage: undefined,
        routeName: undefined,
        accidentLocation: undefined,
        totalMass: undefined,
        roadCondition: undefined,
        weatherCondition: undefined,
      },
      fileTempUrls:[],
      formData1: {
        fileUrls:[],
        reviewRemarks: undefined,
      },
      option: [],
      field101Options: [
        {
          label: "通过",
          value: 1,
        },
        {
          label: "不通过",
          value: 2,
        },
      ],
      listFormData: {
        garageId: undefined,
        vehicleModelName: undefined,
        vehicleModel: undefined,
        nameId: undefined,
        name: undefined,
        unit: undefined,
        unitPrice: undefined,
        duration: undefined,
        number: undefined,
        remarks: undefined,
        repairId: undefined,
        repairCode: undefined,
        pl_name: undefined,
      },
      listFormData2: {
        garageId: undefined,
        vehicleModelName: undefined,
        vehicleModel: undefined,
        name: undefined,
        unit: undefined,
        unitPrice: undefined,
        duration: undefined,
        number: undefined,
        remarks: undefined,
        repairId: undefined,
        repairCode: undefined,
      },
      rules: {
        vehicleModelName: [],
        name: [],
        unit: [],
        unitPrice: [],
        duration: [],
        number: [],
        mainLicensePlate: [],
        remarks: [],
      },
      vehicleOptions: [],
      nameOptions: [],
    };
  },
  filters: {
    repairType: function (value) {
      switch (value) {
        case 1:
          return "正常维修";
          break;
        case 2:
          return "事故维修";
          break;
        case 3:
          return "保养";
          break;
        default:
          break;
      }
    },
  },
  mounted() {
    this.formData1.id = this.$route.params.id;
    this.formData1.status = this.$route.query.status;
    this.listFormData.garageId = this.listFormData2.garageId = this.$route.query.id;
    console.log(this.$route.query.id);
    // 维修明细表一车型下拉选 TODO:换1
    commonSelectBoxH10VehicleModelByGarageId(this.$route.query.id).then(
      (res) => {
        console.log(res);
        this.vehicleOptions = res.data;
      }
    );
    // 维修保养查看
    repairInfo(this.$route.params.id).then((res) => {
      this.formData = res.data;
    });
    // 维修明细表一列表
    this.getList1();
    // 维修明细表二列表
    this.getList2();
  },
  methods: {
    createFilter(queryString) {
      return (restaurant) => {
        return (
          restaurant.name
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    querySearch(queryString, cb) {
      var restaurants = this.nameOptions;
      console.log(this.createFilter(queryString));
      //   var results = this.createFilter(queryString);
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    openBlur(name){
      var restaurants = this.nameOptions;
      var results = name
        ? restaurants.filter(this.createFilter(name))
        : restaurants;
      if(results.length == 1 && results[0].name == name){
        this.listChange(results[0])
      } else {
        this.listFormData.name = "";
        Message({message: '请在选项中选择名称',type: 'error'})
      }
    },
    getList1() {
      // 维修明细表一列表  TODO:1换成this.$route.params.id
      H10NotSubmittedList(this.$route.params.id).then((res) => {
        console.log(res.data.detailedList);
        this.tableData1 = res.data.notSubmittedList;
        this.total = res.data.totalCost;
      });
    },
    getList2() {
      // 维修明细表二列表  TODO:1换成this.$route.params.id
      repairH10NotSubmittedIsNewList(this.$route.params.id).then((res) => {
        console.log(res.data);
        this.tableData2 = res.data.notSubmittedIsNewList;
        this.total2 = res.data.totalCost;
      });
    },

    UploadFileGroups(param) {
      let fd = new FormData();
      let _this = this;
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then(response => {
          let obj = {
            name:param.file.name,
            url:response.msg
          }
          this.fileTempUrls.push(obj)
        })
        .catch(err => {
          console.log(err);
        });
    },
    // 文件删除
    handleRemove(file, fileList) {
      for (const [index, item] of this.fileTempUrls.entries()) {
        if (item.name == file.name) {
          console.log("123213333333",index);
          this.fileTempUrls.splice(index, 1);
        }
      }
    },

    // 维修明细表一新增带出单位单价数据
    repairH10NotSubmittedAddInfoData(id) {
      // TODO:1换成传进来的id
      repairH10NotSubmittedAddInfo(id).then((res) => {
        this.listFormData.unit = res.data.unit;
        this.listFormData.unitPrice = res.data.unitPrice;
        this.listFormData.duration = res.data.duration;
        console.log(res);
      });
    },
    // 维修明细表一新增车型和名称二级联动
    commonSelectBoxH10AddBoxOption(obj = {}) {
      commonSelectBoxH10AddBox(obj).then((res) => {
        this.nameOptions = res.data;
        console.log(res);
      });
    },
    // 审批
    // queryExamineConfigProcessDataList() {
    //   let obj = {
    //     type: 5,
    //   };
    //   queryExamineConfigProcessList(obj).then((res) => {
    //     // debugger
    //     this.tableData = res.rows;
    //   });
    // },
    // 选择事件
    handleChange(val) {
      console.log(val);
      this.listFormData.nameId = undefined;
      this.listFormData.name = undefined;
      let name;
      for (const item of this.vehicleOptions) {
        if (val == item.vehicleModel) {
          this.listFormData.vehicleModelName = item.vehicleModelName;
          name = item.vehicleModelName;
        }
      }
      let obj = {
        id: this.$route.query.id,
        name,
      };
      this.commonSelectBoxH10AddBoxOption(obj);
    },
    handleChange2(val) {
      for (const item of this.vehicleOptions) {
        if (val == item.vehicleModel) {
          this.listFormData2.vehicleModelName = item.vehicleModelName;
          // name = item.vehicleModelName;
        }
      }
    },
    listChange(val) {
      // for (const item of this.nameOptions) {
      //   if (val == item.id) {
      //     this.listFormData.name = item.name;
      //   }
      // }
      this.listFormData.name = val.name;
      this.listFormData.nameId = val.id;
      if (val == "") {
        return;
      }
      this.repairH10NotSubmittedAddInfoData(val.id);
    },
    // 维修新增
    addRepairClick() {
      console.log("维修新增");
      this.listTitle2 = "新增";
      this.listOpen2 = true;
      (this.listFormData2 = {
        garageId: undefined,
        vehicleModelName: undefined,
        vehicleModel: undefined,
        name: undefined,
        unit: undefined,
        unitPrice: undefined,
        duration: undefined,
        number: undefined,
        remarks: undefined,
        repairId: undefined,
        repairCode: undefined,
      }),
        (this.listFormData2.repairId = this.$route.params.id);
      this.listFormData2.repairCode = this.formData.code;
    },
    // 维修修改
    editRepairClick(row) {
      console.log("维修修改");
      repairH10NotSubmittedIsNewEdit(row.id).then((res) => {
        console.log(res.data);
        this.listFormData2 = res.data;
        this.listOpen2 = true;
      });
    },
    // 维修删除
    delRepairClick(row) {
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return repairH10NotSubmittedIsNewDelete(row.id);
        })
        .then(() => {
          this.getList2();
          this.msgSuccess("删除成功");
        });
      // console.log("维修删除");
      // repairH10NotSubmittedIsNewDelete(row.id).then((res) => {
      //   this.getList2();
      // });
    },
    handelConfirm2() {
      console.log(this.listFormData2);
      this.listFormData2.garageName = this.formData.garageName;
      this.listFormData2.garageId = this.$route.query.id;
      this.$refs["listForm"].validate((valid) => {
        console.log();
        if (this.listFormData2.id) {
          repairH10NotSubmittedIsNewEditSave(this.listFormData2).then((res) => {
            this.getList2();
            this.listOpen2 = false;
            this.resetlistFormData2();
          });
        } else {
          this.but_loading2 = true;
          repairH10NotSubmittedIsNewAdd(this.listFormData2).then((res) => {
            this.getList2();
            this.listOpen2 = false;
            this.but_loading2 = false;
            this.resetlistFormData2();
          });
        }

        if (!valid) return;
      });
    },
    close2() {
      this.listOpen2 = false;
      this.resetlistFormData2();
      console.log("关闭");
    },
    // 新增
    addClick() {
      console.log("新增");
      this.listTitle = "新增";
      this.listOpen = true;
      (this.listFormData = {
        garageId: undefined,
        vehicleModelName: undefined,
        vehicleModel: undefined,
        nameId: undefined,
        name: undefined,
        unit: undefined,
        unitPrice: undefined,
        duration: undefined,
        number: undefined,
        remarks: undefined,
        repairId: undefined,
        repairCode: undefined,
      }),
        (this.listFormData.repairId = this.$route.params.id);
      this.listFormData.repairCode = this.formData.code;
    },
    // 保存
    saveClick() {
      if(this.fileTempUrls){
        this.fileTempUrls.forEach(r=>{
          this.formData1.fileUrls.push(r.url);
        })
      }
      repairH02ReviewTwoLevel(this.formData1).then(this.backClick());
    },
    // 提交
    subClick() {
      console.log("提交");
    },
    // 返回
    backClick() {
      console.log("返回");
      this.$router.go(-1);
    },
    // 修改
    editClick(row) {
      this.listTitle = "修改";
      console.log("修改");
      repairH10NotSubmittedEdit(row.id).then((res) => {
        console.log(res.data);
        this.listFormData = res.data;
        let obj = {
          id: this.$route.query.id,
          name: res.data.vehicleModelName,
        };
        this.listFormData.pl_name = res.data.name;
        this.commonSelectBoxH10AddBoxOption(obj);
        this.listOpen = true;
      });
    },
    // 删除
    delClick(row) {
      console.log("删除");
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return repairH10NotSubmittedDelete(row.id);
        })
        .then(() => {
          this.getList1();
          this.msgSuccess("删除成功");
        });
      // repairH10NotSubmittedDelete(row.id).then((res) => {
      //   this.getList1();
      // });
    },
    close() {
      this.listOpen = false;
      this.resetlistFormData();
      console.log("关闭");
    },
    resetlistFormData() {
      this.listFormData = {
        vehicleModelName: undefined,
        vehicleModel: undefined,
        nameId: undefined,
        name: undefined,
        unit: undefined,
        unitPrice: undefined,
        duration: undefined,
        number: undefined,
        remarks: undefined,
      };
    },
    resetlistFormData2() {
      this.listFormData2 = {
        vehicleModelName: undefined,
        vehicleModel: undefined,
        name: undefined,
        unit: undefined,
        unitPrice: undefined,
        duration: undefined,
        number: undefined,
        remarks: undefined,
      };
    },
    handelConfirm() {
      if(this.listFormData.vehicleModel == "" || this.listFormData.vehicleModel == null){
        Message({message: '请选择车型',type: 'error'})
        return;
      }
      if(this.listFormData.name == "" || this.listFormData.name == null){
        Message({message: '请选择名称',type: 'error'})
        return;
      }
      if(this.listFormData.number == "" || this.listFormData.number == null){
        Message({message: '请输入数量',type: 'error'})
        return;
      }
      this.listFormData.garageName = this.formData.garageName;
      this.listFormData.garageId = this.$route.query.id;
      console.log(this.listFormData);
      this.$refs["listForm"].validate((valid) => {
        if (this.listFormData.id) {
          repairH10NotSubmittedEditSave(this.listFormData).then((res) => {
            this.getList1();
            this.listOpen = false;
            this.resetlistFormData();
          });
        } else {
          this.but_loading = true;
          repairH10NotSubmittedAdd(this.listFormData).then((res) => {
            this.getList1();
            this.listOpen = false;
            this.but_loading = false;
            this.resetlistFormData();
          });
        }

        if (!valid) return;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.border {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  .text {
    width: 120px;
    text-align: right;
    line-height: 40px;
    margin-right: 30px;
  }
  .xian {
    flex: 1;
    border-bottom: 3px solid #333399;
    height: 50%;
  }
}
</style>

<style lang="scss">
.box {
  .el-form-item__label {
    font-size: 16px;
  }
  .el-form-item__content {
    font-size: 16px;
  }
  .el-radio__inner {
    width: 18px;
    height: 18px;
  }
  .el-radio__label {
    font-size: 16px;
  }
}
</style>

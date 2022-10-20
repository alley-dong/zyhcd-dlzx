<template>
  <div class="app-container">
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm">保存</el-button>
        <el-button type="primary" @click="resetForm">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">车辆信息</div>
      <div class="line"></div>
    </div>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="195px"
      >
        <el-col :span="12">
          <el-form-item label="档案编号：" prop="identifier">
            <el-input
              v-model="formData.identifier"
              placeholder="请输入档案编号"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*车辆类别：" prop="vehicleTypeName">
            <!-- <el-input
              v-model="formData.vehicleTypeName"
              placeholder="请输入类别"
              show-word-limit
              
              :style="{ width: '100%' }"
            ></el-input> -->
            <el-select
              v-model="formData.vehicleType"
              :style="{ width: '100%' }"
              @change="vehicleChange"
              placeholder="请选择"
            >
              <el-option
                v-for="item in categoryOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*车牌号：" prop="licensePlate">
            <el-input
              v-model="formData.licensePlate"
              placeholder="请输入车牌号"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*车辆类型：" prop="vehicleModelName">
              <el-autocomplete
              class="inline-input"
              v-model="formData.vehicleModelName"
              :fetch-suggestions="querySearch"
              @select="handleSelect"
              placeholder="请输入车辆类型"
              :disabled="formData.vehicleType ? false : true"
              :style="{ width: '100%' }"
            >
              <template slot-scope="{ item }" :style="{ width: '100%' }">
                <span class="addr">{{ item.name }}</span>
              </template>
            </el-autocomplete>
            <!-- <el-select
              v-model="formData.vehicleModel"
              @change="vehicleModelChange"
              :style="{ width: '100%' }"
              placeholder="请选择车辆类型"
              :disabled="formData.vehicleType ? false : true"
            >
              <el-option
                v-for="item in modelOptions"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="品牌型号：" prop="brand">
            <el-input
              v-model="formData.brand"
              placeholder="请输入品牌型号"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*车辆颜色：" prop="vehicleModelName">
            <el-select
              v-model="formData.color"
              :style="{ width: '100%' }"
              placeholder="请选择"
            >
              <el-option
                v-for="item in colorOptions"
                :key="item.dictValue"
                :label="item.dictLabel"
                :value="item.dictValue"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="驱动形式：" prop="identifier">
            <el-select
              v-model="formData.driveForm"
              placeholder="请选择驱动形式"
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="dict in driveFormOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车长：" prop="vehicleTypeName">
            <el-input
              v-model="formData.conductor"
              placeholder="请输入车长"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*长（米）：" prop="vehicleTypeName">
            <el-input
              v-model="formData.lasting"
              placeholder="请输入"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*宽（米）：" prop="vehicleTypeName">
            <el-input
              v-model="formData.wide"
              placeholder="请输入"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*高（米）：" prop="vehicleTypeName">
            <el-input
              v-model="formData.high"
              placeholder="请输入"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发动机号：" prop="identifier">
            <el-input
              v-model="formData.engineNumber"
              placeholder="请输入发动机号"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="马力：" prop="vehicleTypeName">
            <el-input
              v-model="formData.horsepower"
              placeholder="请输入马力"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*车架号：" prop="vehicleTypeName">
            <el-input
              v-model="formData.frameNumber"
              placeholder="请输入车架号"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="变速箱：" prop="identifier">
            <el-input
              v-model="formData.gearbox"
              placeholder="请输入变速箱"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发动机：" prop="vehicleTypeName">
            <el-input
              v-model="formData.engine"
              placeholder="请输入发动机"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*燃料种类：" prop="identifier">
            <!-- <el-input
              type="number"
              v-model="formData.fuelType"
              placeholder="请输入燃料种类"
              
              :style="{ width: '100%' }"
            > -->
            <el-select
              v-model="formData.fuelType"
              placeholder="请选择燃油种类"
              :style="{ width: '100%' }"
            >
              <el-option
                v-for="dict in fuelTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
            <!-- </el-input> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="后桥：" prop="vehicleTypeName">
            <el-input
              v-model="formData.rearAxle"
              placeholder="请输入后桥"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="制造厂商：" prop="identifier">
            <el-input
              v-model="formData.manufacturer"
              placeholder="请输入制造厂商"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主油箱容积：" prop="vehicleTypeName">
            <el-input
              v-model="formData.mainTankVolume"
              placeholder="请输入主油箱容积"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*车总质量：" prop="identifier">
            <el-input
              v-model="formData.totalMass"
              placeholder="请输入车总质量"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="副油箱容积：" prop="vehicleTypeName">
            <el-input
              v-model="formData.viceTankVolume"
              placeholder="请输入副油箱容积"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*核定载质量：" prop="identifier">
            <el-input
              v-model="formData.approvedLoad"
              placeholder="请输入核定载质量"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整备质量：" prop="vehicleTypeName">
            <el-input
              v-model="formData.curbWeight"
              placeholder="请输入整备质量"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="购车服务站：" prop="identifier">
            <el-input
              v-model="formData.distributor"
              placeholder="请输入购车服务站"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="含税购车金额（含增值税）：" prop="identifier">
            <el-input
              v-model="formData.purchasePrice"
              placeholder="请输入含税购车金额（含增值税）"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="购车日期：" prop="vehicleTypeName">
            <!-- <el-input
              v-model="formData.vehicleTypeName"
              placeholder="请输入购车日期"
              show-word-limit
              
            ></el-input> -->
            <el-date-picker
              @change="formData.purchaseDate = dataTime(formData.purchaseDate)"
              v-model="formData.purchaseDate"
              type="date"
              :style="{ width: '100%' }"
              placeholder="请输入购车日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="GPS费用：" prop="vehicleTypeName">
            <el-input
              v-model="formData.gpsCost"
              placeholder="请输入GPS费用"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车辆原值：" prop="identifier">
            <el-input
              v-model="formData.originalPrice"
              placeholder="请输入车辆原值"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="购置税：" prop="vehicleTypeName">
            <el-input
              v-model="formData.purchaseTax"
              placeholder="请输入购置税"
              show-word-limit
              :style="{ width: '100%' }"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检车落籍费用：" prop="identifier">
            <el-input
              v-model="formData.inspectCost"
              placeholder="请输入检车落籍费用"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="强制报废期：" prop="vehicleTypeName">
            <el-date-picker
              @change="formData.scrapDate = dataTime(formData.scrapDate)"
              v-model="formData.scrapDate"
              type="date"
              :style="{ width: '100%' }"
              placeholder="请输入强制报废期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行驶证注册日期：" prop="identifier">
            <el-date-picker
              @change="formData.registerDate = dataTime(formData.registerDate)"
              v-model="formData.registerDate"
              type="date"
              :style="{ width: '100%' }"
              placeholder="请输入行驶证注册日期"
            />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="*道路运输许可证号：" prop="vehicleTypeName">
            <el-input
              v-model="formData.permit"
              placeholder="请输入道路运输许可证号"
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="*是否需要定检：" prop="brand">
            <!-- <el-input
              v-model="formData.brand"
              placeholder="请输入品牌型号"
              
              :style="{ width: '40.9%' }"
            >
            </el-input> -->
            <el-select
              v-model="formData.isInspect"
              :style="{ width: '100%' }"
              placeholder="请选择"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24"> </el-col>
      </el-form>
    </el-row>
    <div class="wrap">
      <div class="text">上传附件</div>
      <div class="line"></div>
    </div>
    <el-upload
      class="upload-demo"
      :action="uploadAction"
      :http-request="UploadFileGroups"
      :file-list="formData.fileUrl"
      list-type="picture"
      style="margin: 15px 0 50px 37px; width: 419px"
    >
      <el-button size="small" type="primary" icon="el-icon-upload"
        >点击上传</el-button
      >
      <span style="color: #82848a; margin-left: 20px">格式不限，其他材料</span>
    </el-upload>
  </div>
</template>
<script>
import { UploadFiles } from "@/api/upLoad/persomUpload";
import { VehicleAdd, vhicleSelectBox } from "@/api/car/vehicle";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        identifier: "", //档案编号
        vehicleTypeName: null, //车辆类别-名称
        licensePlate: "", //车牌号
        vehicleModelName: null, //车辆型号-名称
        brand: "", //品牌型号
        driveForm: null, //驱动形式
        horsepower: "", //马力
        engineNumber: "", //发动机号
        frameNumber: "", //车架号
        gearbox: "", //变速箱
        engine: "", //发动机
        fuelType: null, //燃油种类
        rearAxle: "", //后桥
        vehicleType: null, //车辆类别 关联sys_dictionary表 ID
        vehicleModel: null, // 车辆型号 关联sys_dictionary表 ID
        manufacturer: "", //制造厂商
        mainTankVolume: "", //主油箱容积
        viceTankVolume: "", //副油箱容积
        totalMass: "", //车总质量
        approvedLoad: "", //核定载质量
        curbWeight: "", //整备质量
        distributor: "", //购车服务站
        purchaseDate: "", //购车日期
        purchasePrice: "", //购车金额
        purchaseTax: "", //购置税
        originalPrice: "", //车辆原值
        gpsCost: "", //GPS费用
        inspectCost: "", //检车落籍费用
        scrapDate: "", //强制报废日期
        registerDate: "", //行驶证注册日期
        color: "", //车辆颜色
        conductor: "", //车长
        isInspect: undefined, //是否需要定检
        permit: "", //道路运输许可证号：
        lasting: "", //
        wide: "", //
        high: "", //
        fileUrl: []
      },
      rules: {
        identifier: [],
        vehicleTypeName: [],
        licensePlate: [],
        vehicleModelName: [],
        brand: []
      },
      colorOptions: [],
      fuelTypeOptions: [],
      driveFormOptions: [],
      categoryOptions: [],
      modelOptions: [],
      uploadfileList: [],
      uploadAction: "https://jsonplaceholder.typicode.com/posts/",

      options: [
        {
          value: 1,
          label: "是"
        },
        {
          value: 2,
          label: "否"
        }
      ]
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    this.getDicts("vehicle_color_code").then(response => {
      console.log(response.data);
      this.colorOptions = response.data;
    });
    this.getDicts("c_vehicle_drive_form").then(response => {
      this.driveFormOptions = response.data;
    });
    this.getDicts("c_vehicle_drive_fuel_type").then(response => {
      this.fuelTypeOptions = response.data;
    });
    this.vhicleSelectBoxData();
  },

  methods: {
        handleSelect(item) {
      console.log(item);

          this.formData.vehicleModel = item.id;
          this.formData.vehicleModelName = item.name;
      // this.formData.frameNumber = item.frameNumber;
      // this.formData.vehicleId = item.id;
      // this.formData.licensePlate = item.licensePlate;
      // if (this.formData.type == 2) {
      //   this.insuranceVehicleE02Judge_fn(this.formData.type, item.id);
      // }else{
      //     this.checkItemDataList = [];

      // }
    },
    querySearch(queryString, cb) {
      var restaurants = this.modelOptions;
      console.log(this.createFilter(queryString));
      //   var results = this.createFilter(queryString);
      var results = queryString
        ? restaurants.filter(this.createFilter(queryString))
        : restaurants;
      // 调用 callback 返回建议列表的数据
      cb(results);
    },
    createFilter(queryString) {
      return restaurant => {
        return (
          restaurant.name.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    vehicleChange(val) {
      console.log(val);
      for (const item of this.categoryOptions) {
        if (item.id == val) {
          this.formData.vehicleTypeName = item.name;
        }
      }
      this.vhicleSelectBoxData(val);
    },
    vehicleModelChange(val) {
      console.log(val);
      for (const item of this.modelOptions) {
        if (item.id == val) {
          this.formData.vehicleModelName = item.name;
        }
      }
    },
    vhicleSelectBoxData(id = 1) {
      vhicleSelectBox(id).then(res => {
        if (this.formData.vehicleType) {
          this.modelOptions = res.data;
        } else {
          this.categoryOptions = res.data;
        }
      });
    },
    submitForm() {
      // this.$refs["elForm"].validate((valid) => {
      //   if (!valid) return;
      //   // TODO 提交表单
      // });
      console.log(this.formData);
      // this.formData.fileUrl = ""; //TODO: 注掉
      // this.formData.vehicleTypeName = parseInt(this.formData.vehicleTypeName)
      VehicleAdd(this.formData).then(res => {
        console.log(res);
        this.resetForm();
      });
    },
    dataTime(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
    UploadFileGroups(param) {
      let fd = new FormData();
      let _this = this;
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then(response => {
          _this.formData.fileUrl.push(response.msg);
          _this.formData = JSON.parse(JSON.stringify(_this.formData));
        })
        .catch(err => {
          console.log(err);
        });
    },
    resetForm() {
      this.$router.go(-1);
      // this.$router.push({ path: "/vehicleFiles", name: "vehicleFiles" });
    },
    uploadBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2;
      if (!isRightSize) {
        this.$message.error("文件大小超过 2MB");
      }
      return isRightSize;
    }
  }
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
.enclosure {
  display: flex;
  margin: 20px 60px;
  img {
    flex: 1;
    height: 300px;
  }
}
</style>

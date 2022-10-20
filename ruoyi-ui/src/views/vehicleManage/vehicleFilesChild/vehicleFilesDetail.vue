<template>
  <div class="app-container">
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm">编辑</el-button>
        <el-button type="primary"  @click="handleBack">返回</el-button>
      </div>
    </div>
    <div class="wrap">
      <div class="text">车辆信息</div>
      <div class="line"></div>
    </div>
    <el-row :gutter="16">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="195px"
      >
        <el-col :span="12">
          <el-form-item label="档案编号：" prop="identifier">
            <div :style="{ width: '100%' }">
              {{ formData.identifier }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车辆类别：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">
              {{ formData.vehicleTypeName }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车牌号：" prop="licensePlate">
            <div :style="{ width: '100%' }">{{ formData.licensePlate }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车辆类型：" prop="vehicleModelName">
            <div :style="{ width: '100%' }">
              {{ formData.vehicleModelName }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="品牌型号：" prop="brand">
            <div :style="{ width: '100%' }">{{ formData.brand }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车辆颜色：" prop="vehicleModelName">
            <div :style="{ width: '100%' }">{{ formData.colorName }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="驱动形式：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.driveFormLable }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车长：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.conductor }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="长（米）：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.lasting }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="宽（米）：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.wide }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="高（米）：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.high }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发动机号：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.engineNumber }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="马力：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.horsepower }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车架号：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.frameNumber }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="变速箱：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.gearbox }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="发动机：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.engine }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="燃料种类：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.fuelTypeLable }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="后桥：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.rearAxle }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="制造厂商：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.manufacturer }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主油箱容积：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.mainTankVolume }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车总质量：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.totalMass }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="副油箱容积：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.viceTankVolume }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="核定载质量：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.approvedLoad }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="整备质量：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.curbWeight }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="购车服务站：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.distributor }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="含税购车金额（含增值税）：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.purchasePrice }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="购车日期：" prop="vehicleTypeName">
            <!-- <div>{{ dataTime(formData.purchaseDate) }}</div> -->
            <div>{{ formData.purchaseDate }}</div>
            <!-- <el-date-picker
              @change="formData.purchaseDate = dataTime(formData.purchaseDate)"
              v-model="formData.purchaseDate"
              type="date"
              :style="{ width: '100%' }"
              placeholder="请输入购车日期"
            > -->
            <!-- </el-date-picker> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="GPS费用：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.gpsCost }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车辆原值：" prop="identifier">
            <div :style="{ width: '100%' }">{{ formData.originalPrice }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="购置税：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">{{ formData.purchaseTax }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检车落籍费用：" prop="identifier">
            <div>{{ formData.inspectCost }}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="强制报废期：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">
              <!-- {{ dataTime(formData.scrapDate) }} -->
              {{formData.scrapDate }}
            </div>
            <!-- <el-date-picker
              @change="formData.scrapDate = dataTime(formData.scrapDate)"
              v-model="formData.scrapDate"
              type="date"
              :style="{ width: '100%' }"
              placeholder="请输入强制报废期期"
            /> -->
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行驶证注册日期：" prop="identifier">
            <!-- <el-date-picker
              @change="formData.registerDate = dataTime(formData.registerDate)"
              v-model="formData.registerDate"
              type="date"
              :style="{ width: '100%' }"
              placeholder="请输入行驶证注册日期"
            /> -->
            <div :style="{ width: '100%' }">
              <!-- {{ dataTime(formData.registerDate) }} -->
              {{formData.registerDate }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="道路运输许可证号：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">
              {{ formData.permit }}
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否需要定检：" prop="brand">
            <div :style="{ width: '100%' }">
              {{ formData.isInspect | state }}
            </div>
            <!-- <el-select
              v-model="formData.isInspect"
              :style="{ width: '42.6%' }"
              placeholder="请选择"
            >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select> -->
          </el-form-item>
        </el-col>
           <el-col :span="12">
          <el-form-item label="转固金额：" prop="vehicleTypeName">
            <div :style="{ width: '100%' }">
              {{ formData.turnCost }}
            </div>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <div class="wrap">
      <div class="text">附件</div>
      <div class="line"></div>
    </div>
    <!-- <el-upload
      class="upload-demo"
      :action="uploadAction"
      :http-request="UploadFileGroups"
      :file-list="formData.fileUrl"
      list-type="picture"
      disabled
      style="margin: 15px 0 50px 37px; width: 419px"
    >
      <el-button size="small" type="primary" icon="el-icon-upload"
        >点击上传</el-button
      >
      <span style="color: #82848a; margin-left: 20px">格式不限，其他材料</span>
    </el-upload> -->
    <div style="diaplay:flex" v-if="formData.fileUrl">
<img v-for="(item,index) in formData.fileUrl" :key="index" :src="item" alt="" srcset="" style="margin:20px 20px 0 0;">
    </div>
  </div>
</template>
<script>
import { UploadFiles } from "@/api/upLoad/persomUpload";
import { VehicleAdd, vehicleInfo } from "@/api/car/vehicle";
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        identifier: undefined, //档案编号
        vehicleTypeName: null, //车辆类别-名称
        licensePlate: undefined, //车牌号
        vehicleModelName: null, //车辆型号-名称
        brand: undefined, //品牌型号
        driveForm: null, //驱动形式
        horsepower: undefined, //马力
        engineNumber: undefined, //发动机号
        frameNumber: undefined, //车架号
        gearbox: undefined, //变速箱
        engine: undefined, //发动机
        fuelType: null, //燃油种类
        rearAxle: undefined, //后桥
        vehicleType: null, //车辆类别 关联sys_dictionary表 ID
        vehicleModel: null, // 车辆型号 关联sys_dictionary表 ID
        manufacturer: undefined, //制造厂商
        mainTankVolume: undefined, //主油箱容积
        viceTankVolume: undefined, //副油箱容积
        totalMass: undefined, //车总质量
        approvedLoad: undefined, //核定载质量
        curbWeight: undefined, //整备质量
        distributor: undefined, //购车服务站
        purchaseDate: undefined, //购车日期
        purchasePrice: undefined, //购车金额
        purchaseTax: undefined, //购置税
        originalPrice: undefined, //车辆原值
        gpsCost: undefined, //GPS费用
        inspectCost: undefined, //检车落籍费用
        scrapDate: undefined, //强制报废日期
        registerDate: undefined, //行驶证注册日期
        color: undefined, //车辆颜色
        conductor: undefined, //车长
        isInspect: undefined, //是否需要定检
        permit: undefined, //道路运输许可证号：
        lasting: undefined,
        wide: undefined,
        high: undefined,
        fileUrl: [],
      },
      rules: {
        identifier: [],
        vehicleTypeName: [],
        licensePlate: [],
        vehicleModelName: [],
        brand: [],
      },

      uploadfileList: [],
      uploadAction: "https://jsonplaceholder.typicode.com/posts/",

      options: [
        {
          value: null,
          label: "全部",
        },
        {
          value: 1,
          label: "是",
        },
        {
          value: 2,
          label: "否",
        },
      ],
    };
  },
  filters: {
    state: function (value) {
      switch (value) {
        case 1:
          return "是";

          break;
        case 2:
          return "否";

          break;
        default:
          return "全部";

          break;
      }
    },
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    this.init();
  },

  methods: {
    init() {
      this.vehicleInfoDetail();
    },
    // 查询单条车辆档案信息
    vehicleInfoDetail() {
      vehicleInfo(this.$route.params.id).then((res) => {
        this.formData = res.data;
        this.formData.fileUrl = this.formData.fileUrl
          ? this.formData.fileUrl
          : [];
      });
    },
    submitForm() {
      // this.$refs["elForm"].validate((valid) => {
      //   if (!valid) return;
      //   // TODO 提交表单
      // });
      //   console.log(this.formData);
      //   VehicleAdd(this.formData).then((res) => {
      //     console.log(res);
      //     this.handleBack();
      //   });
      this.$router.push({ path: "/vehicleFilesEdit/" + this.$route.params.id });
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
        .then((response) => {
          _this.formData.fileUrl.push(response.msg);
          _this.formData = JSON.parse(JSON.stringify(_this.formData));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleBack() {
      this.$router.go(-1)
    },
    uploadBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2;
      if (!isRightSize) {
        this.$message.error("文件大小超过 2MB");
      }
      return isRightSize;
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
.enclosure {
  display: flex;
  margin: 20px 60px;
  img {
    flex: 1;
    height: 300px;
  }
}
</style>
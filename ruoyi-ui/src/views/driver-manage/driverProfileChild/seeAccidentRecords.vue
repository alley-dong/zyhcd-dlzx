<template>
  <div class="app-container">
    <div style="width: 100%; margin: 20px 10px 20px 0; overflow: hidden">
      <el-button type="primary" style="float: right; margin-right: 10px"
       @click="backClick"
        >返回</el-button
      >
    </div>
    <div class="wrap">
      <div class="text">事故信息</div>
      <div class="line"></div>
    </div>
    <!-- 查看 -->
    <el-row :gutter="0">
      <el-col :span="12">
        <div class="box">
          <div class="text1">驾驶员：</div>
          <div class="context">{{ formData.driverName }}</div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="box">
          <div class="text1">车牌号：</div>
          <div class="context">{{ formData.vehicleName }}</div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="box">
          <div class="text1">事故地点：</div>
          <div class="context">{{ formData.accidentPlace }}</div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="box">
          <div class="text1">事故时间：</div>
          <div class="context">{{ formData.accidentTime }}</div>
        </div>
      </el-col>
      <el-col :span="24">
        <div class="box">
          <div class="text1">事故照片：</div>
          <div class="context">
            <div
              class="driver"
              v-for="(item, index) in formData.accidentFileList"
              :key="index"
            >
              <img :src="item" alt="" class="img" />
              <!-- {{index}} -->
            </div>
            <!-- <div class="driver"><div class="img"></div></div>
            <div class="driver"><div class="img"></div></div>
            <div class="driver"><div class="img"></div></div> -->
          </div>
        </div>
        <div class="box">
          <div class="text1">双方行驶证（正本、副本）：</div>
          <div class="context">
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.oneSideDriveLicenceCardJust"
                alt=""
                class="img"
              />
              <!-- <div class="name">正本</div> -->
            </div>
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.oneSideDriveLicenceCardAgainst"
                alt=""
                class="img"
              />
              <!-- <div class="name">副本</div> -->
            </div>
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.oneSideTravelLicenceCardJust"
                alt=""
                class="img"
              />
              <!-- <div class="name">正面</div> -->
            </div>
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.oneSideTravelLicenceCardAgainst"
                alt=""
                class="img"
              />
              <!-- <div class="name">反面</div> -->
            </div>
          </div>
        </div>
        <div class="box">
          <div class="text1">双方行驶证（正本、副本）：</div>
          <div class="context">
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.theOtherDriveLicenceCardJust"
                alt=""
                class="img"
              />
              <!-- <div class="name">正本</div> -->
            </div>
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.theOtherDriveLicenceCardAgainst"
                alt=""
                class="img"
              />
              <!-- <div class="name">副本</div> -->
            </div>
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.theOtherTravelLicenceCardJust"
                alt=""
                class="img"
              />
              <!-- <div class="name">正面</div> -->
            </div>
            <div class="driver">
              <!-- <div class="img"></div> -->
              <img
                :src="formData.theOtherTravelLicenceCardAgainst"
                alt=""
                class="img"
              />
              <!-- <div class="name">反面</div> -->
            </div>
          </div>
        </div>
        <div class="box">
          <div class="text1">驾驶员身份证（正面、背面）：</div>
          <div class="context">
            <div class="driver">
              <img :src="formData.driverIdCardJust" alt="" class="img" />
            </div>
            <div class="driver">
              <img
                :src="formData.driverIdCardAgainst"
                alt=""
                class="img"
              />
            </div>
          </div>
        </div>
        <div class="box">
          <div class="text1">被保险人身份证：</div>
          <div class="context">
            <div class="driver">
              <img :src="formData.carTheInsuredIdCardJust" alt="" class="img" />
            </div>
            <div class="driver">
              <img
                :src="formData.carTheInsuredIdCardAgainst"
                alt=""
                class="img"
              />
            </div>
          </div>
        </div>
        <div class="box">
          <div class="text1">事故责任认定书：</div>
          <div class="context">
            <div class="driver">
              <img :src="formData.liabilityStatement" alt="" class="img" />
            </div>
          </div>
        </div>
        <div class="box">
          <div class="text1">其他：</div>
          <div class="context">
            <div
              class="driver"
              v-for="(item, index) in formData.partsFilesList"
              :key="index"
            >
              <img :src="item" alt="" class="img" />
              <!-- {{index}} -->
            </div>
            <!-- <div class="driver"><div class="img"></div></div>
            <div class="driver"><div class="img"></div></div>
            <div class="driver"><div class="img"></div></div> -->
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { A31GetInfo } from "@/api/driver/interviewManage";
export default {
  data() {
    return {
      formData: {},
    };
  },
  methods: {
    backClick() {
        this.$router.go(-1)
    },
  },
  mounted() {
    console.log(this.$route.params.id);
    A31GetInfo(this.$route.params.id).then((res) => {
      console.log(res);
      this.formData = res.data;
    });
  },
};
</script>

<style lang="scss" scoped>
.box {
  display: flex;
  margin: 30px 0;
  margin-bottom: 10px;
}
.text1 {
  width: 150px;
  text-align: right;
}
.context {
  flex: 1;
  margin-left: 20px;
}
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
.driver {
  width: 20%;
  max-width: 270px;
  display: inline-block;
  margin-right: 30px;
}
.img {
  //   background-color: #000;
  height: 200px;
}
.name {
  text-align: center;
  line-height: 36px;
}
</style>
<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
      <div class="top-text">
        {{ form.type == 1 ? "主车维保" : "挂车维保" }}：
      </div>
      <!-- <van-cell
        title="主车车牌号："
        :value="form.driverName"
        title-class="title-class"
        value-class="value-class"
      /> -->
      <van-cell
        title="维修类型："
        :value="form.repairType | repairType"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="主车车牌号："
        :value="form.mainLicensePlate"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="挂车车牌号："
        :value="form.hangLicensePlate"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="故障时公里数："
        :value="form.mileage"
        title-class="title-class"
        value-class="value-class"
      />
      <div v-for="item in form.repairItemDate" :key="item.id">
        <van-cell
          title="维修保养项："
          :value="item.itemName"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="维修原因："
          :value="item.remarks"
          title-class="title-class"
          value-class="value-class"
        />
        <div class="upload-img">
          <div class="text">图片：</div>
          <div v-for="im in item.fileList">
            <van-image
              @click="handleClick(im)"
              width="100"
              height="100"
              :src="im"
            />
          </div>
        </div>
      </div>
      <van-cell
        title="天气环境："
        :value="form.weather"
        title-class="title-class"
        value-class="value-class"
      />
      <!-- <van-row>
  <van-col span="8">span: 8</van-col>
  <van-col span="8">span: 8</van-col>
  <van-col span="8">span: 8</van-col>
</van-row> -->
      <!-- <div v-if="show"> -->
      <!-- <div class="upload-img">
        <div class="text">审批人：</div>
        <div>
          <div class="context">
            {{form.reviewUser}}<span
              style="margin-left: 0.2rem; color: #333; font-weight: 600"
              >{{form.status | status}}</span
            >
          </div>
        </div>
      </div>
      <van-cell
        title="抄送人："
        value="form.driverName"
        title-class="title-class"
        value-class="value-class"
      /> -->
      <van-cell
        title="维修服务站："
        :value="form.garageName"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="维修单号："
        :value="form.code"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="维修服务站："
        :value="form.garageName"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="电话："
        :value="form.mobile"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="地址："
        :value="form.address"
        title-class="title-class"
        value-class="value-class"
      />
      <div class="upload-img">
        <div class="text">维修明细：</div>
        <table border="5" style="border: 0.03rem solid #333; width: 3.5rem">
          <tr>
            <th>名称</th>
            <th>工时数</th>
          </tr>
          <tr v-for="item in tableList" :key="item.id">
            <td>{{ item.name }}</td>
            <td>{{ item.duration }}</td>
          </tr>
        </table>
      </div>
      <div class="upload-img">
        <div class="text">维修单：</div>
        <div>
          <van-image
            @click="handleClick(form.repairDetialPhoto)"
            width="100"
            height="100"
            :src="form.repairDetialPhoto"
          />
        </div>
      </div>
      <van-cell
        title="维修确认："
        :value="form.status | status"
        title-class="title-class"
        value-class="value-class"
      />
      <van-cell
        title="维修时间："
        :value="form.reviewTime "
        title-class="title-class"
        value-class="value-class"
      />
    </div>
    <!-- </div> -->
    <div style="margin-bottom: 0.3rem" v-for="item in processList" :key="item.id">
      <div class="upload-img">
        <div class="text">审批人：</div>
        <div style="float: right; flex: 1; text-align: left">
          {{ item.userName }}
          <van-tag type="primary" style="margin-left: 0.3rem">{{
            form.status | status
          }}</van-tag>
        </div>
      </div>
      <!-- <van-cell
        title-class="left-title"
        title="抄送人"
        :value="item.ccUserName"
      /> -->
    </div>
    <div style="margin-bottom: 0.3rem" v-for="item in ccList" :key="item.id">
      <van-cell
        title-class="left-title"
        title="抄送人"
        :value="item.userName"
      />
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { ImagePreview } from "vant";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessListDetail,
} from "@/api/api";
import { repairH01Info, H03AllListDetail } from "@/api/maintenance/index";
import { queryExamineConfigProcessList } from "@/api/reportTheAccident/incidentRecordDetails";
export default {
  components: {
    Header,
  },
  mounted() {
    queryExamineConfigProcessListDetail({
        type: 7,
        relationId: this.$route.params.id,
      }).then((res) => {
        console.log(res);
        
        this.processList = res.data.sysExamineUserTaskList;
        this.ccList = res.data.sysDuplicateUserTaskList;
      });
    // queryExamineConfigProcessList({
    //   type: 13,
    //   id: this.$route.params.id,
    // }).then((res) => {
    //   console.log(res);
    //   this.processList = res.rows;
    // });
    repairH01Info({ id: this.$route.params.id }).then((res) => {
      console.log(res);
      this.form = res.data;
    });
    H03AllListDetail(this.$route.params.id).then((res) => {
      console.log(res.data);
      this.tableList = res.data.detailedList;
    });
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
    status: function (value) {
      switch (value) {
        case 1:
          return "待审批";
          break;
        case 2:
          return "已拒绝";
          break;
        case 3:
          return "已通过";
          break;
        case 4:
          return "已确认";
          break;
        case 5:
          return "已撤销";
          break;

        default:
          break;
      }
    },
  },
  data() {
    return {
      list: [],
      form: {},
      tableList: [],
      processList: undefined,
      ccList:[]
    };
  },
  methods: {
    handleClick(img) {
      ImagePreview([img]);
    },
  },
};
</script>

<style lang="scss" scoped>
.title-class {
  text-align: left;
}
.value-class {
  text-align: left;
}
.upload-img {
  display: flex;
  //   text-align: left;
  text-align: left;
  padding: 0.2rem 0.32rem;
  .text {
    // width: 10em;
    width: 12.1em;
    font-size: 0.28rem;
    // margin-right: 0.24rem;
  }
}
.value-class {
  text-align: left;
}
.top-text {
  font-weight: 600;
  text-align: left;
  height: 1rem;
  line-height: 1.3rem;
  padding-left: 1rem;
}
th {
  border: 1px solid #ccc;
  width: 2rem;
  text-align: center;
  height: 0.5rem;
  line-height: 0.5rem;
}
td {
  border: 1px solid #ccc;
  width: 2rem;
  text-align: center;
  height: 0.5rem;
  line-height: 0.5rem;
}
.context {
  font-size: 0.14rem;
  color: #969799;
}
.mar-top {
  margin-top: 0.3rem;
}
// .upload-img {
//   display: flex;
//   text-align: right;
//   padding: 0.2rem 0.32rem;
//   .text {
//     width: 2.4rem;
//     // width: 8.2em;
//     font-size: 0.28rem;
//     margin-right: 0.24rem;
//   }
// }
</style>
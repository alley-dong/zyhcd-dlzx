<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container" style="background: #fff">
      <div>
        <div class="top-text">基本信息：</div>
        <!-- <van-cell
        title="主车车牌号："
        :value="form.driverName"
        title-class="title-class"
        value-class="value-class"
      /> -->
        <van-cell
          title="主车车牌号："
          :value="form.mainLicensePlate"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="主车车型："
          :value="form.mainVehicleModelName"
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
          title="挂车车型："
          :value="form.hangVehicleModelName"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="故障时公里数："
          :value="form.mileage"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="行驶路线："
          :value="form.routeName"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="发生故障地点："
          :value="form.accidentLocation"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="故障时荷载/总质量："
          :value="form.totalMass"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="故障时路况："
          :value="form.road"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="故障时天气："
          :value="form.weather"
          title-class="title-class"
          value-class="value-class"
        />
      </div>
      <div>
        <div class="top-text">维修信息：</div>
        <van-cell
          title="维修单号："
          :value="form.code"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="维修类别："
          :value="form.type == 1 ? '主车维保' : '挂车维保'"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="维修类型："
          :value="form.repairType | repairType"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="是否是异地维修："
          :value="form.isOther == 1 ? '是' : '否'"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="视频："
          value=""
          title-class="title-class"
          value-class="value-class"
        />
        <video
          v-if="form.accidentVideo"
          style="width: 80%"
          controls="controls"
          :src="form.accidentVideo"
        ></video>
        <!-- <div class="upload-img">
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
        </div> -->
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
          <div class="upload-img" sty>
            <div class="text">图片：</div>
            <div v-for="(im,index) in item.fileList">
              <van-image
                @click="handleClick(im)"
                width="100"
                height="100"
                :src="im"
              />
            </div>
          </div>
        </div>
      </div>

      <div>
        <div v-if="tableData && tableData.length > 0" class="top-text">
          维修明细：
        </div>
        <div
          v-for="item in tableData"
          :key="item.id"
          style="border-bottom: 0.05rem solid"
        >
          <van-cell
            title="车型："
            :value="item.vehicleModelName"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="名称："
            :value="item.name"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="单位："
            :value="item.unit"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="单价（元）："
            :value="item.unitPrice"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="工时数："
            :value="item.duration"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="数量："
            :value="item.number"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="金额："
            :value="item.amount"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="备注："
            :value="item.remarks"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="统计："
            :value="`本月第${item.monthNumber}次，本年第${item.yearNumber}次`"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
                  title="备注："
                  :value="form.reviewRemarks"
                  title-class="title-class"
                  value-class="value-class"
          />
        </div>
      </div>
      <div>
        <div v-if="tableData1 && tableData1.length > 0" class="top-text">
          新维修明细：
        </div>
        <div
          v-for="item in tableData1"
          :key="item.id"
          style="border-bottom: 0.05rem solid"
        >
          <van-cell
            title="车型："
            :value="item.vehicleModelName"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="名称："
            :value="item.name"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="单位："
            :value="item.unit"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="单价（元）："
            :value="item.unitPrice"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="工时数："
            :value="item.duration"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="数量："
            :value="item.number"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="金额："
            :value="item.amount"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="备注："
            :value="item.remarks"
            title-class="title-class"
            value-class="value-class"
          />
          <!-- <van-cell
            title="统计："
            :value="`本月第${item.monthNumber}次，本年第${item.yearNumber}次`"
            title-class="title-class"
            value-class="value-class"
          /> -->
        </div>
      </div>




      <div>
        <div class="top-text">上传附件：</div>
        <div class="upload-img" sty>
          <div class="text">图片：</div>
          <div v-for="(im,index) in form.fileList">
            <van-image
                    @click="handleClick(im)"
                    width="100"
                    height="100"
                    :src="im"
            />
          </div>
        </div>
      </div>




      <div>
        <div class="top-text">审批记录：</div>
        <div
          v-for="item in tableData2"
          :key="item.id"
          style="border-bottom: 0.05rem solid"
        >
          <van-cell
            title="审批人姓名："
            :value="item.userName"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="审批状态："
            :value="item.statusEnum"
            title-class="title-class"
            value-class="value-class"
          />
        </div>
      </div>
    </div>

    <div v-if="status_show && form && form.isOther == 2 && form.status == 1">
      <van-field
        clearable
        :value="value"
        is-link
        label="维修服务站："
        placeholder="请选择维修服务站"
        @click="showPicker = true"
      />
      <van-popup v-model="showPicker" position="bottom">
        <van-picker
          show-toolbar
          :columns="listItems"
          @confirm="onConfirm"
          @cancel="showPicker = false"
        />
      </van-popup>
    </div>
    <van-field
      v-else
      clearable
      :value="form.garageName"
      label="维修服务站："
      readonly
    />
    <van-field v-model="text" label="备注" placeholder="请输入备注" />
    <van-row style="background: #fff; padding: 1rem 0">
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(1)"
          >同意</van-button
        ></van-col
      >
      <van-col span="12"
        ><van-button type="primary" @click="handleAddClick(2)"
          >拒绝</van-button
        ></van-col
      >
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  repairInfo,
  repairDetailAllList,
  repairH10NotSubmittedIsNewList,
} from "@/api/approvalRecord/index";
import { sysExamineTaskList } from "@/api/com";
import { ImagePreview } from "vant";
import { Toast } from "vant";
import {
  H07StationCheck,
  repairH01Check,
  commonSelectBoxList
} from "@/api/myAgent";
export default {
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      const day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      const hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      const min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      return year + "." + month + "." + day + " " + hours + ":" + min;
    },
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
  components: {
    Header,
  },
  methods: {
    // 数据处理
    data_fn(list) {
      let arr = [];
      for (const item of list) {
        let obj = {};
        obj.text = item.name;
        obj.goodsTypeId = item.id;
        arr.push(obj);
      }
      return arr;
    },
    onConfirm(value) {
      this.value = value.text;
      this.garageId = value.goodsTypeId;
      this.showPicker = false;
      // this.getList()
    },
    handleAddClick(status) {
      //
      if (!this.form.garageName) {
        
        if (this.status_show && this.form.isOther == 2 && status == 1) {
          if (!this.garageId) {
            // this.$message({
            //   message: "请选择维修服务站",
            //   type: "warning",
            // });
            Toast.fail("请选择维修服务站");
            return;
          }
        }
      }
      if (this.form.status == 2 && this.form.garageStatus == 2) {

        H07StationCheck({
          relationId: this.$route.params.id,
          reviewRemarks: this.text,
          status,
        }).then((res) => {
          if(status == 1){
            Toast("审批通过");
          } else {
            Toast("审批拒绝");
          }
          this.$router.go(-1);
        });
      } else {
        repairH01Check({
          garageId:this.garageId,
          garageName:this.value,
          relationId: this.$route.params.id,
          reviewRemarks: this.text,
          status,
        }).then((res) => {
          Toast.fail(res.msg);
          this.$router.go(-1);
        });
      }
    },
    get_data() {
      console.log();
      let { name, val } = this.returnVal(this.$route.query.status);
      console.log(name, val);
      this.name = name;
      this.button_text = val;
    },

    returnVal(value) {
      console.log(value);
      switch (value) {
        case "1":
          return { name: "待审批", val: "撤销" };
        case "2":
          return { name: "已拒绝", val: "再次提交申请" };
        case "3":
          return { name: "已通过", val: "确认完成" };
        case "4":
          return { name: "已确认", val: "" };
        case "5":
          return { name: "已撤销", val: "" };

        default:
          break;
      }
    },
    // 上传
    readClick(file, detail) {
      let fd = new FormData();
      console.log("param.file", file);
      fd.append("file", file.file); // 要提交给后台的文件
      minioChooseBucketUpload(fd)
        .then((response) => {
          this.repairDetialPhoto = response.msg;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 查看图片
    handleClick(url) {
      ImagePreview([url]);
    },
    revokeClick(name) {
      console.log(name);
      if (name == "撤销") {
        repairH01Revoke(this.$route.params.id).then(this.$router.go(-1));
      }
      if (name == "确认完成") {
        //TODO:等完成接口
        // repairH01Revoke(this.$route.params.id).then(this.$router.go(-1));
      }
      if (name == "再次提交申请") {
        this.$router.push({
          path: "/maintenanceAdd",
          query: { id: this.$route.params.id },
        });
      }
    },
  },
  mounted() {
    // 维修服务站下拉
    commonSelectBoxList({}).then((res) => {
      this.listItems = this.data_fn(res.data);
    });
    repairInfo(this.$route.params.id).then((res) => {
      console.log(res);
      this.form = res.data;
    });
    repairDetailAllList(this.$route.params.id).then((res) => {
      this.tableData = res.data.notSubmittedList;
      this.total = res.data.totalCost;
      // this.tableData = res.data.detailedList;
    });
    repairH10NotSubmittedIsNewList(this.$route.params.id).then((res) => {
      console.log(res.data);

      this.tableData1 = res.data.notSubmittedIsNewList; //新
      this.total1 = res.data.totalCost;
    });
    let type = this.$route.query.type;
    sysExamineTaskList({ type, relationId: this.$route.params.id }).then(
      (res) => {
        console.log(res.data.sysExamineUserTaskList);
        this.tableData2 = res.data.sysExamineUserTaskList;
        for (const item of res.data.sysExamineUserTaskList) {
          if (item.level == 1 && item.status == 1) {
            this.status_show = true;
          }
        }
      }
    );
    // this.get_data();
  },
  data() {
    return {
      value: "",
      garageId: undefined,
      status_show: false,
      showPicker: false,
      listItems: [{ text: "全部", goodsTypeId: null }],
      form: {},
      text: undefined,
      files: [],
      name: undefined,
      repairDetialPhoto: undefined,
      button_text: "撤销",
      tableList: [],
      tableData: undefined,
      tableData1: undefined,
      tableData2: [],
      total: undefined,
      total1: undefined,
      list: [
        "故障时公里数：",
        "234568",
        "维修保养项：",
        "发动机",
        "维修原因：",
        "图片：",
        "审批人：",
        "抄送人：",
        "维保认定员",
        "调度",
        "项目管理员",
        "部门经理",
        "待审批",
        "待审批",
        "待审批",
        "基本信息：",
        "故障时天气：",
        "行驶路线：",
        "发生故障地点：",
        "故障时荷载/总质量：",
        "吨",
        "故障时路况：",
        "长春-无锡",
        "45/50",
        "平原",
        "晴",
        "主车车型：",
        "半挂牵引车",
        "主车车型：",
        "仓栏",
        "维修信息：",
        "维修类型：",
        "正常维修",
        "主车维保",
        "维修类别：",
        "是否是异地维修：",
        "是",
        "视频：",
        "审批记录：",
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
.value-class {
  text-align: left;
}
.title-class {
  text-align: left;
}
.top-text {
  font-weight: 600;
  text-align: left;
  height: 1rem;
  line-height: 1.3rem;
  padding-left: 0.5rem;
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
.repair-text {
  text-align: left;
  font-size: 0.28rem;
  width: 12.1em;
  padding: 0.2rem 0.32rem;
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
</style>

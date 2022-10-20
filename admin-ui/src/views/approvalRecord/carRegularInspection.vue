<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container" style="background: #fff">
      <!-- 车辆定检审批记录 -->
      <div>
        <div class="top-text">申请信息：</div>

        <van-cell
          title="上报人："
          :value="form.createBy"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="上报时间："
          :value="form.createTime"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="任务编号："
          :value="form.licensePlate"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="定检类型："
          :value="form.type == 1?'主车':'挂车'"
          title-class="title-class"
          value-class="value-class"
        />
      </div>
      <div>
        <div class="top-text">点检：</div>
        <div v-for="item in form.cVehicleInspectItemList" :key="item.id">

        <van-cell
          title="点检内容："
          :value="item.name"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="点检结果："
          :value="item.status == 1?'正常':'维修'"
          title-class="title-class"
          value-class="value-class"
        />
        <van-field name="uploader" label="点检图片">
          <template #input>
           <van-image
               v-for="(data,index) in item.files" :key="index"
                width="100"
                height="100"
                @click="handleClick(data)"
                style="margin-right: 0.1rem"
                :src="data"
              />
          </template>
        </van-field>
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
            title="审批级别："
            :value="item.level"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="审批时间："
            :value="item.reviewTime"
            title-class="title-class"
            value-class="value-class"
          />
          <van-cell
            title="审批角色："
            :value="item.roleName"
            title-class="title-class"
            value-class="value-class"
          />
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
          <van-cell
            title="备注："
            :value="item.reviewRemarks"
            title-class="title-class"
            value-class="value-class"
          />
        </div>

        <!-- <div class="repair" style="display: flex; margin-top: 0.5rem">
          <div class="repair-text">审批人：</div>
          <div style="font-size: 0.25rem">
            <div>
              维保认定员 <span style="margin-left: 0.5rem">{{ name }}</span>
            </div>
            <div style="margin-top: 0.4rem">
              维保认定员 <span style="margin-left: 0.5rem">{{ name }}</span>
            </div>
            <div style="margin-top: 0.4rem">
              维保认定员 <span style="margin-left: 0.5rem">{{ name }}</span>
            </div>
          </div>
        </div>
        <div class="repair" style="display: flex; margin-top: 0.5rem">
          <div class="repair-text">抄送人：</div>
          <div style="font-size: 0.25rem">
            <div>调度</div>
          </div>
        </div> -->
      </div>
      <!-- <van-field name="uploader" label="请上传维修单">
        <template #input>
          <van-uploader
            v-model="files"
            :after-read="readClick"
            :max-count="1"
            multiple
          />
        </template>
      </van-field> -->
      <!-- <van-button
        v-if="name !== '已确认' && name !== '已撤销'"
        round
        type="info"
        @click="revokeClick(button_text)"
        style="margin: 1rem 0; width: 50%"
        >{{ button_text }}</van-button
      > -->
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  vehicleInspectH20List,
} from "@/api/approvalRecord/index";
import { sysExamineTaskList } from "@/api/com";
import { ImagePreview } from "vant";
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
    vehicleInspectH20List({ id: this.$route.params.id }).then((res) => {
     for (const item of res.data.cVehicleInspectItemList) {
        if (item.files != "") {
          let arr = item.files.split(",");
          arr.length--;
          item.files = arr;
        } else {
          item.files = [];
        }
      }
      this.form = res.data;
    });
    // repairDetailAllList(this.$route.params.id).then((res) => {
    //   console.log(res.data.detailedList);
    //   this.tableData = res.data.detailedList;
    //   this.total = res.data.totalCost;
    //   // this.tableData = res.data.detailedList;
    // });
    // repairH10NotSubmittedIsNewList(this.$route.params.id).then((res) => {
    //   console.log(res.data);

    //   this.tableData1 = res.data.notSubmittedIsNewList;
    //   this.total1 = res.data.totalCost;
    // });
    sysExamineTaskList({ relationId: this.$route.params.id }).then((res) => {
      console.log(res.data.sysExamineUserTaskList);
      this.tableData2 = res.data.sysExamineUserTaskList;
    });
    // this.get_data();
  },
  data() {
    return {
      form: {},
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
        "维保类别：",
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
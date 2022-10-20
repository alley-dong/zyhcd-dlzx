<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <div class="app-container">
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
          title="维保类别："
          :value="form.type == 1 ? '主车维保' : '挂车维保'"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="维保类型："
          :value="form.repairType | repairType"
          title-class="title-class"
          value-class="value-class"
        />
        <van-cell
          title="维修单号："
          :value="form.code"
          title-class="title-class"
          value-class="value-class"
        />
        <div v-if="name=='已通过'||name=='已确认'">
          <!-- TODO: 电话和地址 缺字段 3.5-->
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
        </div>
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
            <div>
              <van-image
                v-for="imge in item.fileList"
                @click="handleClick(imge)"
                width="100"
                height="100"
                :src="imge"
              />
            </div>
          </div>
        </div>
      </div>
      <div>
        <div class="top-text">审批记录：</div>

        <div  v-for="item in processList"
              :key="item.id">
            <van-row>
                <div style="text-align: left; padding: 0.5rem 1rem">
                    <div>
                        <van-col span="6">审批人：</van-col>
                        <van-col span="9" v-if="item.userName" >{{ item.userName }} </van-col>
                        <van-col span="9"><span style="margin-left: 0.5rem">{{ item.statusEnum }}</span></van-col>
                    </div>
                </div>
            </van-row>
            <van-row v-if="item.ccUserName">
                <div style="text-align: left; padding: 0.5rem 1rem">
                    <div>
                        <van-col span="6" v-if="item.ccUserName">抄送人:</van-col>
                        <van-col span="18" v-if="item.ccUserName"
                        >{{ item.ccUserName }}
                        </van-col>
                    </div>
                </div>
            </van-row>
        </div>


        <!-- 审批没对 -->
      </div>
      <van-field
        v-if="form.repairStatus != 3 || form.status != 3"
        name="uploader"
        label="请上传维修单"
      >
        <template #input>
          <van-uploader
            v-model="files"
            :after-read="readClick"
            :max-count="1"
            multiple
          />
        </template>
      </van-field>
      <van-button
        v-if="name !== '已确认' && name !== '已撤销' && form.repairStatus !== 3"
        round
        type="info"
        @click="revokeClick(button_text)"
        style="margin: 1rem 0; width: 50%"
        >{{ button_text }}</van-button
      >
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  repairH01Info,
  H03AllListDetail,
  repairH01Revoke,
  repairH02Save,
  repairH02Upload,
} from "@/api/maintenance/index";
import {
  minioChooseBucketUpload,
  queryExamineConfigProcessListDetail,
} from "@/api/api";
import { ImagePreview, Toast } from "vant";
export default {
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
          return "已通过";
          break;
        case 3:
          return "已拒绝";
          break;
        case 4:
          return "已确认";
          break;
        // case 5:
        //   return "已撤销";
        //   break;

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
      console.log(value, this.$route.query.repairStatus);
      switch (value) {
        case "1":
          return { name: "待审批", val: "撤销" };
        case "2":
          switch (this.$route.query.repairStatus) {
            case "2":
              return { name: "已通过", val: "确认完成" };
              break;
            default:
              return { name: "已通过", val: "" };
              break;
          }
        case "3":
          return { name: "已拒绝", val: "再次提交申请" };
        case "4":
          return { name: "已确认", val: "" };
        // case "5":
        //   return { name: "已撤销", val: "" };

        default:
          break;
      }
    },
    // 上传
    readClick(file, detail) {
      let fd = new FormData();
      console.log("param.file", file);
      file.status = 'uploading'
      file.message = '上传中...'
      console.log('.this.files', this.files)
      fd.append("file", file.file); // 要提交给后台的文件
      minioChooseBucketUpload(fd)
        .then((response) => {
          this.repairDetialPhoto = response.msg;
          repairH02Upload({
            id: this.$route.params.id,
            repairDetialPhoto: response.msg,
          });
          file.status = ''
          file.message = ''
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
      if(this.files.length > 0){
        if(this.files[0].status === 'uploading') {
          Toast("请等待图片上传完成");
          return
        }
      }
      if (name == "撤销") {
        repairH01Revoke(this.$route.params.id).then((res) =>{
          this.$router.go(-1)
        });
      }
      if (name == "确认完成") {
        repairH02Save(this.$route.params.id).then((res) =>{
          this.$router.go(-1)
        });
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
    queryExamineConfigProcessListDetail({
      type: 7,
      relationId: this.$route.params.id,
      loginType: 1,
    }).then((res) => {
      this.processList = res.data.sysExamineUserTaskList;
      this.processList_cc = res.data.sysDuplicateUserTaskList;
    });
    repairH01Info({ id: this.$route.params.id }).then((res) => {
      console.log(res);
      this.form = res.data;
    });
    H03AllListDetail(this.$route.params.id).then((res) => {
      console.log(res.data);
      this.tableList = res.data.detailedList;
    });
    this.get_data();
  },
  data() {
    return {
      processList_cc: [],
      processList: [],
      form: {},
      files: [],
      name: undefined,
      repairDetialPhoto: undefined,
      button_text: "撤销",
      tableList: [],
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
  padding-left: 1rem;
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
  text-align: right;
  font-size: 0.28rem;
  width: 12.1em;
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

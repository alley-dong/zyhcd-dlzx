<template>
  <div>
    <Header
      :show="true"
      :showRight="false"
      :buttonShow="buttonShow&&bool"
      @handleClick="handleDetailClick"
      buttonText="点检记录"
    ></Header>
    <div class="app-container">
      <div class="top-text">
        点检内容
        <span v-if="count.sum1">{{ count.sum + "/" + count.sum1 }}</span>
      </div>
      <div class="check">
        <van-radio-group
          v-for="(item, index) in checkList"
          :key="item.id"
          v-model="item.my_status"
          :disabled="!bool"
        >
          <van-cell class="text" :title="index + 1 + '、' + item.dictLabel" />
          <van-cell-group>
            <!-- <van-cell
              v-for="(data, index) in option"
              clickable
              :key="index"
              :title="data.name"
              @click="toggle(index)"
              class="text"
            >
              <template #right-icon>
                <van-checkbox
                  shape="square"
                  icon-size="16"
                  :name="1"
                  ref="checkboxes"
                />
              </template>
            </van-cell>  -->
            <van-cell
              class="text"
              :title="item.name"
              clickable
              @click="item.my_status = item.value"
              v-for="(item, index) in option"
              :key="index"
            >
              <template #right-icon>
                <van-radio :name="item.value" />
              </template>
            </van-cell>
          </van-cell-group>
          <div @click="numClick(index)">
            <van-field name="uploader" readonly label="上传照片">
              <template #input>
                <!-- item.fileList -->
                <van-uploader
                v-if="bool"
                  v-model="item.fileList"
                  :before-read="bfClick"
                  :after-read="readClick"
                  multiple
                />
                <van-image v-else width="100"  v-for="(data,index) in item.fileList" :key="index" height="100" :src="data.url" @click="imgClick(data.url)"/>
              </template>
            </van-field>
          </div>
          <van-field
            v-model="item.remarks"
            rows="2"
            autosize
            label="备注"
            type="textarea"
            :maxlength="!bool?null:50"
            placeholder="请输入备注"
            show-word-limit
            :readonly="!bool"
          />
          <!-- :disabled="!bool" -->
        </van-radio-group>
      </div>
    </div>
    <van-button v-if="bool" type="info" round @click="handleClick">提交</van-button>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from "vant";
import { upload } from "@/components/upload";
import { ImagePreview } from 'vant';
import {
  minioChooseBucketUploadList,
} from "@/api/api";
import {
  transportTaskM03TransportTaskInfo,
  transportM13SubmitTaskInspect,
  transportM17TaskInspectInfo
} from "@/api/assignment/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      buttonShow: false,
      checkList: [],
      my_index: undefined,
      taskForm: {},
      count: {},
      bool:true,
      option: [
        {
          name: "正常",
          value: 1,
        },
        {
          name: "需维修",
          value: 2,
        },
      ],
      uploadStatus:false
    };
  },
  methods: {
    imgClick(url){
ImagePreview([url]);

    },
    toggle(index) {
      this.$refs.checkboxes[index].toggle();
    },
    handleDetailClick() {
      this.$router.push({path:"/spotCheckDetail/" + this.$route.params.id,query:{id:this.$route.query.value}});
    },
    handleClick() {
      if(this.uploadStatus){
        Toast("有图片上传中，请耐心等待上传完毕");
        return false
      }
      let arr = [];
      for (const item of this.checkList) {
        if(item.my_status){
          let obj = {};
          obj.taskId = this.$route.params.id;
          obj.shipmentXid = this.taskForm.shipmentXid;
          obj.type = item.dictValue;
          obj.status = item.my_status;
          obj.name = item.dictLabel;
          obj.remarks = item.remarks;

          if (item.my_status == 2) {
            if(!item.fileList){
              Toast("需要维修的点检内容请上传图片");
              return;
            }
          }
          let fileList = [];
          if (item.fileList) {
            for (const data of item.fileList) {
              fileList.push(data.url);
            }
          }
          obj.fileList = fileList;
          arr.push(obj);
        }
      }
      this.taskForm.tTransportTaskInspectItemModels = arr;
      transportM13SubmitTaskInspect(this.taskForm).then((res) => {
        this.$router.go(-1);
      });
    },

    numClick(index) {
      if(this.uploadStatus){
        return false
      }
      this.my_index = index;
    },

    bfClick(){
      if(this.uploadStatus){
        Toast("上一张图片过大，请耐心等待上一张图片上传完毕");
        return false
      }
      return true
    },

    readClick(file, detail) {
      this.uploadStatus = true;
      file.status = 'uploading';
      file.message = '上传中...';

      let fd = new FormData();
      if(file instanceof Array){
        for(let i = 0;i<file.length;i++){
          fd.append("file", file[i].file);
        }
      } else {
        fd.append("file", file.file);
      }
      minioChooseBucketUploadList(fd).then((res) => {
        if(file instanceof Array){
          for(let i = 0;i<file.length;i++){
            this.checkList[this.my_index].fileList.splice(
                    this.checkList[this.my_index].fileList.indexOf(file[i]),
                    1
            );
          }
        } else {
          this.checkList[this.my_index].fileList.splice(
                  this.checkList[this.my_index].fileList.indexOf(file),
                  1
          );
        }

        var urlArr = res.data;
        console.log("urlArr",res.data)
        urlArr.forEach(e=>{
          let obj = {};
          obj.url = e;
          this.checkList[this.my_index].fileList.push(obj);
        })
        this.uploadStatus = false;

      }).catch(e=>{
        for(let i = 0;i<file.length;i++){
          this.checkList[this.my_index].fileList.splice(
                  this.checkList[this.my_index].fileList.indexOf(file[i]),
                  1
          );
        }
        this.uploadStatus = false;
      });
    },
  },
  mounted() {
    let { name, value,bool } = this.$route.query;
    this.bool = !bool
    switch (value) {
      case "1":
        this.getDicts("t_transport_task_inspect_item_start_type").then(
          (res) => {
            // for (const item of res.data) {
            //     item.my_status = null
            // }
            this.checkList = res.data;
          }
        );
        break;
      case "2":
        this.buttonShow = true;
        this.getDicts("t_transport_task_inspect_item_process_type").then(
          (res) => {
            // for (const item of res.data) {
            //     item.my_status = null
            // }
            this.checkList = res.data;
          }
        );
        break;
      case "3":
        this.getDicts("t_transport_task_inspect_item_end_type").then((res) => {
          // for (const item of res.data) {
          //     item.my_status = null
          // }
          this.checkList = res.data;
        });
        break;
      default:
        break;
    }
    transportTaskM03TransportTaskInfo({ id: this.$route.params.id }).then(
      (res) => {
        let { shipmentXid, type, driverId, driverName, routeName } = res.data;
        type = this.$route.query.value;
        let sum, sum1;
        switch (value) {
          case "1":
            sum = res.data.startInspectCount;
            this.count = { sum };
            break;
          case "2":
            sum = res.data.processInspectCount;
            sum1 = res.data.processInspectMinCount;
            this.count = { sum, sum1 };
            break;
          case "3":
            sum = res.data.endInspectCount;
            this.count = { sum };

            break;
          default:
            break;
        }
        this.taskForm = { shipmentXid, type, driverId, driverName, routeName };
        this.taskForm.taskId = this.$route.params.id;
        // this.count.sum>0&&
        if (this.$route.query.bool) {
          transportM17TaskInspectInfo({id:this.$route.params.id}).then(res=>{
            for (const item of res.data.tTransportTaskInspectItemVms) {
              let {status,fileList,remarks,type} = item
              console.log(fileList);
              for (const data of this.checkList) {
                if (type==data.dictValue) {
                  data.my_status = status
                  data.remarks = remarks
                  let arr = []
                  if (fileList) {
                    for (const _ of fileList) {
                      arr.push({url:_})
                      // _ ={url:_}
                    }
                    data.fileList = arr
                    // debugger
                  }
                }
              }
// console.log(JSON.stringify(this.checkList));
              this.checkList=JSON.parse(JSON.stringify(this.checkList))
            }
          })
        }
      }
    );
  },
};
</script>

<style lang="scss" scoped>
.top-text {
  font-weight: 800;
  text-align: left;
  height: 1rem;
  line-height: 1.3rem;
  padding-left: 0.5rem;
  span {
    float: right;
    margin-right: 0.4rem;
    font-weight: 400;
  }
}
.check {
  .title {
    text-align: left;
    padding-left: 0.5rem;
  }
}
.text {
  text-align: left;
  font-size: 0.25rem;
}
.click-disable{
  pointer-events: none;
}
</style>
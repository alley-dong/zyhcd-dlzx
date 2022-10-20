<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <van-row type="flex" justify="center" style="margin-top: 92px">
      <van-col span="24">
        <!-- <van-field label="浏览时长" value="00:10:00" readonly /> -->
        <!-- <van-count-down :time="time" /> -->
        <van-field label="浏览时长" center>
          <template #input>
            <van-count-down
              style="text-align: left"
              v-if="time_m"
              :time="time_m"
            />
          </template>
        </van-field>
        <van-field label="培训标题" :value="form.title" readonly />
        <van-field label="发布培训时间" :value="form.createTime" readonly />

        <van-field
          v-model="form.education"
          rows="2"
          autosize
          label="培训要求"
          type="textarea"
          maxlength="50"
          show-word-limit
          readonly
        />
        <van-field
          label="培训截止时间"
          :value="form.trainingEndTime"
          readonly
        />
        <van-field label="最少阅读时长" :value="form.minDuration" readonly />
        <van-field label="视频" readonly />
        <video
          v-if="form.videoUrl"
          width="100%"
          autoplay
          muted
          controls="controls"
          :src="form.videoUrl"
        ></video>
        <van-field
          label="培训内容"
          :value="form.content"
          v-html="form.content"
          readonly
        />
        <!-- <van-image width="100%" src="https://img.yzcdn.cn/vant/cat.jpeg" /> -->
      </van-col>
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { Toast } from 'vant'
import {
  driverTrainingC02List,
  driverTrainingC02Edit,
} from "@/api/training/myTraining";
import {changeStatus} from "../../../../ruoyi-ui/src/api/systemSettings/accountManage";
export default {
  components: {
    Header,
  },
  data() {
    return {
      id: undefined,
      time_m: undefined,
      fileList: [],
      form: {},
      message: "asdkjaskdkasldd开发接口上的纠纷收到借款纠纷 就方式的空间",
      current: 0,
      comStatus: 0,
        subStatus:0
    };
  },
  mounted() {
    this.id = this.$route.params.id;
    driverTrainingC02List({ id: this.$route.params.id }).then((res) => {
      console.log('.res', res)
      this.form = res.data;
      this.time_m = res.data.minDuration * 60 * 1000;
        this.comStatus = res.data.userStatus;

      this.subStatus= res.data.minDuration * 60;
      this.time = setInterval(() => {
        this.current++;
        if(this.current == this.subStatus){
            if(this.comStatus != 2){
                this.changeStatus()
            }
        }
      }, 1000);
      if( res.data.fileUrls){
        for (let item of res.data.fileUrls) {
          this.fileList.push(item.substring(item.lastIndexOf("/") + 1));
        }
      }

    });
  },
  methods: {
    fileClick(name) {
      // if (file.name.indexOf(".") == -1) {
      //   return;
      // }
      let obj = {
        originalName: name,
        filename: name,
      };
      window.location.href = `/foo/pfserver-person/personFile/minioChooseBucketDownload?originalName=${obj.originalName}&filename=${obj.originalName}`;
    },

      changeStatus(){
        driverTrainingC02Edit({
            seeTime: this.current,
            id: this.id,
        })
      }

  },
  beforeDestroy() {
    clearInterval(this.time);
    if(this.comStatus != 2){
        if(this.current < this.subStatus){
            Toast("阅读时长未达到最短阅读时长");
        }
    }
  },
};
</script>

<style lang="scss" scoped>
.text {
  margin-bottom: 0.2rem;
}
.box {
  width: 90%;
  border: 1px solid #ccc;
  margin: auto;
  margin-top: 0.5rem;
}
</style>

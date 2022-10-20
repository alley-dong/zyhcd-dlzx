<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <van-row type="flex" justify="center" style="margin-top: 92px">
      <van-col span="24">
        <van-field
          label="违法车辆:"
          value=""
          readonly
          v-model="form.licensePlate"
        />
        <van-field label="违法地点:" value="" readonly v-model="form.wzdd" />
        <van-field
          label="违法时间:"
          value=""
          readonly
          label-width="auto"
          v-model="form.wzsj"
        />
        <van-field label="违法行为:" value="" readonly v-model="form.wzxw" />
        <van-field label="处罚:" value="" readonly v-model="form.wzfk" />
        <van-field v-show="wztpshow" label="违章图片:" value="" readonly />
        <van-image
          v-for="(item, index) in form.urlList_violation"
          :key="index"
          :src="item"
        />
        <!-- <van-image width="25%" src="https://img.yzcdn.cn/vant/cat.jpeg" />
        <van-image width="25%" src="https://img.yzcdn.c n/vant/cat.jpeg" />
        <van-image width="25%" src="https://img.yzcdn.cn/vant/cat.jpeg" /> -->
        <van-field
          label="处理违章截止时间:"
          value=""
          readonly
          label-width="auto"
          v-model="form.deadline"
        />
        <div v-if="form.status == 1">
          <van-field name="uploader" label="上传处理凭证">
            <template #input>
              <van-uploader
                v-model="uploader"
                :after-read="readClick"
                multiple
              />
            </template>
          </van-field>
          <van-button
            color="linear-gradient(to right, #8B8B83,#CDCDC1)"
            @click="handCom(form.id)"
          >
            处理完成
          </van-button>
        </div>
        <div v-else>
          <van-field label="上传处理凭证:" value="" readonly />
          <van-image
            v-for="(item, index) in form.urlList_upload"
            :key="index"
            :src="item"
          />
          <van-field
            label="处理完成时间:"
            value=""
            readonly
            label-width="auto"
            v-model="form.completeTime"
          />
        </div>
      </van-col>
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  driverTrafficK02GetInfo,
  driverTrafficK02Shang,
} from "@/api/endorsementList/endorsement";
import { formatDate } from "@/components/index";
import { upload } from "@/components/upload";
export default {
  components: {
    Header,
  },
  data() {
    return {
      uploader: [],
        wztpshow:true,
      form: {
        id: "",
        vehicleName: "",
        driverName: "",
        accidentTime: "",
        accidentPlace: "",
        createTime: "",
        reviewUser: "",
        status: "",
        statusEnum: "",
      },
    };
  },
  mounted() {
    this.geJ02SafeAccidentInfo();
  },
  methods: {
    readClick(file, detail) {
      this.uploader.length--;
      upload(file, detail).then((res) => {
        let obj = {};
        obj.url = res;
        this.uploader.push(obj);
      });
    },
    // 详情列表
    geJ02SafeAccidentInfo() {
      let id = {
        id: this.$route.params.id,
      };
      driverTrafficK02GetInfo(id).then((res) => {

        if(res.data.fileList){
            this.wztpshow = true;
            let urlList_violation = [];
            for (const item of res.data.fileList) {
                if (item.type == 7) {
                    urlList_violation.push(item.filePath);
                }
            }
            res.data.urlList_violation = urlList_violation;
        } else {
            this.wztpshow = false;
        }

        if(res.data.voucherFileList){
          let urlList_upload = [];
          for (const item of res.data.voucherFileList) {
            if (item.type == 8) {
              urlList_upload.push(item.filePath);
              let obj = {};
              obj.url = item.filePath;
              this.uploader.push(obj);
            }
          }
          res.data.urlList_upload = urlList_upload;
        }
        this.form = res.data;
        this.form.wzsj = formatDate(res.data.wzsj, "yyyy-MM-dd");
      });
    },
    // 再次提交
    handCom(id) {
      // this.$router.push("/reportAgain/" + id);
      let obj = {};
      obj.id = this.$route.params.id;
      console.log(JSON.stringify(this.uploader));
      obj.url = this.uploader.map((item) => item.url);
      driverTrafficK02Shang(obj).then((res)=>{
        this.$router.go(-1)
      })
    },
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
.preview-cover {
  position: absolute;
  bottom: 0;
  box-sizing: border-box;
  width: 100%;
  padding: 4px;
  color: #fff;
  font-size: 12px;
  text-align: center;
  background: rgba(0, 0, 0, 0.3);
}
</style>

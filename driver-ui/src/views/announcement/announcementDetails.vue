<template>
  <div>
    <Header :show="true" :showRight="false">></Header>
    <van-row type="flex" justify="center" style="margin-top: 92px">
      <van-col span="24">
        <van-field label="公告标题" :value="form.title" readonly />
        <van-field label="发布时间" :value="form.createTime" readonly />
        <van-field label="公告截至时间" :value="form.deadline" readonly />
        <van-col span="6"
          ><div
            style="
              font-size: 0.28rem;
              text-align: left;
              margin-bottom: 0.5rem;
              color: #646566;
              padding-left: 0.3rem;
            "
          >
            公告内容：
          </div></van-col
        >
        <!-- <van-col span="8">span: 8</van-col> -->
        <!-- <van-field label="" :value="form.content" readonly /> -->
        <!-- <van-image width="100%" src="https://img.yzcdn.cn/vant/cat.jpeg" /> -->
        <!-- <van-field
          v-model="message"
          rows="2"
          autosize
          label="培训内容"
          type="textarea"
          maxlength="50"
          show-word-limit
          readonly
        /> -->
        <!-- <van-col span="6"></van-col> -->
        <div class="col">
          <van-col
            span="18"
            v-html="
             form.content
            "
          ></van-col>
        </div>
      </van-col>
    </van-row>
  </div>
</template>

<script>
import Header from "@/components/Header";

import { driverNoticeD02List } from "@/api/announcement/index";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form: {},
      message: "asdkjaskdkasldd开发接口上的纠纷收到借款纠纷 就方式的空间",
    };
  },
  methods: {},
  mounted() {
    driverNoticeD02List({ id: this.$route.params.id }).then((res) => {
      this.form = res.data;
    });
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
<style lang="scss">
  .col{
    text-align: left;
    p{
      line-height: 0.5rem;
    }
  img{
    width:100%
  }
}
</style>
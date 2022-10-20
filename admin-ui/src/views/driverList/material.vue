<template>
  <div>
    <Header :show="true" :showRight="false"></Header>

    <div class="app-container">
      <van-field v-for="(item, index) in fileUrl" :key="index">
        <template #input>
          <van-image
            width="50%"
            height="200"
            :src="item"
            @click="handleClick(item)"
            style="margin:auto"
          />
        </template>
      </van-field>
      <!-- <van-field label="身份证背面:">
        <template #input>
          <van-image
            width="100"
            height="100"
            src="https://img.yzcdn.cn/vant/cat.jpeg"
          />
        </template>
      </van-field>
      <van-field label="驾驶证(正本):">
        <template #input>
          <van-image
            width="100"
            height="100"
            src="https://img.yzcdn.cn/vant/cat.jpeg"
          />
        </template>
      </van-field>
      <van-field label="驾驶证(副本):">
        <template #input>
          <van-image
            width="100"
            height="100"
            src="https://img.yzcdn.cn/vant/cat.jpeg"
          />
        </template>
      </van-field> -->
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  info,
  // educationExperienceList,
  // A11DriverDriverList,
  // A11DriverAccidentList,
  // A11DriverFamilyList,
} from "@/api/driverList/index";
import { ImagePreview } from "vant";
export default {
  data() {
    return {
      fileUrl: undefined
    }
  },
  mounted() {
    info(this.$route.params.id).then((res) => {
      this.fileUrl = res.data.fileUrl;
    });
  },
  methods: {
    handleClick(item) {
      ImagePreview([item]);
    },
  },
  components: {
    Header,
  },
};
</script>

<style></style>

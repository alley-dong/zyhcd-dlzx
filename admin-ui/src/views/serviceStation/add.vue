<template>
  <div>
    <div class="app-container">
      <Header :show="true" :showRight="false"></Header>
      <van-form @submit="onSubmit" label-width="2rem">
        <van-field
          is-link
          v-model="form.name"
          name="维修服务站名称"
          label="维修服务站名称"
          placeholder="请输入维修服务站名称"
        />
        <!-- :rules="[{ required: true, message: '请填写用户名' }]" -->
        <van-field
          is-link
          v-model="form.tel"
          name="电话"
          label="电话"
          placeholder="请输入电话"
        />
        <van-field
          is-link
          v-model="form.address"
          name="地点"
          label="地点"
          placeholder="请输入地点"
        />
        <van-field
          is-link
          v-model="form.isFixedName"
          readonly
          name="定点维修站"
          label="定点维修站"
          placeholder="请选择定点维修站"
          @click="show = true"
        />
        <!-- :rules="[{ required: true, message: '请填写密码' }]" -->
        <div style="margin: 16px">
          <van-button
            round
            block
            type="info"
            style="width: 80%; margin: auto; margin-top: 1rem"
            native-type="submit"
            >提交</van-button
          >
        </div>
      </van-form>
    </div>
    <van-action-sheet v-model="show" :actions="actions" @select="onSelect" />
  </div>
</template>

<script>
import { Toast } from "vant";
import Header from "@/components/Header";
import { garageCarH15Add } from "@/api/serviceStation";
export default {
  components: {
    Header,
  },
  data() {
    return {
      form: {
        isFixed: undefined,
        name: undefined,
        isFixedName: undefined,
        tel: undefined,
        address: undefined,
      },
      show: false,
      actions: [
        // { label: null, name: "全部" },
        { label: 1, name: "是" },
        { label: 2, name: "否" },
      ],
    };
  },
  methods: {
    onSubmit(values) {
      console.log("submit", values);
      garageCarH15Add(this.form).then((res) => {
        this.$router.back();
      });
    },
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.show = false;
      this.form.isFixed = item.label;
      this.form.isFixedName = item.name;
      Toast(item.name);
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
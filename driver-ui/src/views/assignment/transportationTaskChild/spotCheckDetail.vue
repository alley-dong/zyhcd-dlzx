<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <div class="app-container">
      <div class="box" v-for="item in dataList" :key="item.id">
        <van-cell
          :title="item.typeEnum"
          title-style="text-align:left;font-weight:600"
          @click="handleClick(item)"
          :border="false"
        />
        <div style="text-align: left; font-size: 0.25rem; padding-left: 0.6rem">
          <div class="text1">点检时间：{{ item.createTime }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import { transportTaskM15TaskInspectList } from "@/api/assignment/index";
export default {
      components: {
    Header,
  },
  data() {
    return {
      dataList: [],
    };
  },
  mounted () {
      console.log(this.$route.params.id);;
      transportTaskM15TaskInspectList({id:this.$route.params.id,type:2}).then(res=>{
        console.log(res);
        this.dataList = res.rows
      })
  },
  methods: {
    handleClick(data) {
      this.$router.push({path:'/spotCheck/'+data.id,query:{name:data.typeEnum,value:data.type,bool:true}})
      
    }
  },
};
</script>

<style lang="scss" scoped>
.text1 {
  margin-bottom: 0.2rem;
}
</style>
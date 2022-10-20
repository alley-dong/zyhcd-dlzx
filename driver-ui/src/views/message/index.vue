<template>
  <div>
    <Header :showRight="showRight"></Header>
    <ul class="content">
      <li>
            <router-link to="/index" >
                <span>时间：2020.09.18  19:23</span>
                <i class="right-1"></i>
                <van-icon name="delete" class="right-2" />
                <p>您的请假申请已通过</p>
            </router-link>
          </li>
    </ul>
  </div>
</template>
  
  <script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import { getA03DriverMessageList } from "@/api/api";

export default {
  data() {
    return {
      showRight: false,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  created() {
    this.getList();
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    // 获取table的表格数据
    getList() {
      getA03DriverMessageList(this.listQuery)
        .then((response) => {
          console.log(response);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
  
  <style scoped>
.content {
  width: 100%;
  position: fixed;
  top: 48px;
  height: calc(100% - 48px);
  padding-bottom: 10px;
  overflow-y: auto;
}
</style>
  
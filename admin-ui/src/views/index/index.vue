<template>
  <div>
    <Header :show="show"></Header>
    <div class="content">
      <div style="display: flex; padding: 0.6rem 0 0.2rem 0.5rem">
        <div style="line-height: 0.62rem">{{ name }}，您好！</div>
        <div style="flex: 1"></div>
        <van-button
          plain
          type="info"
          size="small"
          style="margin-right: 0.5rem"
          @click="removeClick"
          >注销</van-button
        >
      </div>
      <div class="app">
        <div v-for="value in data" :key="value.name">
          <h2>{{ value.name }}</h2>
          <van-grid :gutter="10">
            <!-- <van-badge :content="5"> -->
            <van-grid-item
              v-for="li in value.list"
              @click="handleClick(li.title)"
              :key="li.title"
              :icon="li.icon"
              :text="li.title"
              :dot="li.dot"
              :badge="value.name == '我的审批' ? li.num : undefined"
            />
            <!-- </van-badge> -->
          </van-grid>
        </div>
      </div>
    </div>
    <Footer page="index"></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";

import { getToken, removeToken, setToken } from "@/utils/auth";
import { userGetInfo, logout, remove, queryToken } from "@/api/login";
import { N02AdministratorQueryUnreadList } from "@/api/index";
export default {
  data() {
    return {
      show: false,
      name: undefined,
      data: [
        {
          name: "我的审批",
          list: [
            {
              title: "我的待办",
              icon: "todo-list-o",
              num: undefined,
              dot: false,
            },
            {
              title: "审批记录",
              icon: "records",
              num: undefined,
              dot: false,
            },
            {
              title: "抄送",
              icon: "edit",
              num: undefined,
              dot: false,
            },
          ],
        },
        {
          name: "驾驶员档案",
          list: [
            {
              title: "驾驶员档案",
              icon: "contact",
              dot: false,
            },
          ],
        },
        {
          name: "库存管理",
          list: [
            {
              title: "领用记录",
              icon: "records",
              dot: false,
            },
            {
              title: "盘点",
              icon: "points",
              dot: false,
            },
          ],
        },
        {
          name: "维修服务站",
          list: [
            {
              title: "维修服务站",
              icon: "setting-o",
              dot: false,
            },
          ],
        },
      ],
    };
  },
  created() {
  },
  components: {
    Footer,
    Header,
  },
  mounted() {
    userGetInfo().then((res) => {
      this.name = res.user.nickName;
    }).catch(e=>{
      this.$router.push("/login");
    });
    /* 
            backlogCount 待办
taskCount 审批记录
sendCount 抄送
    */
    N02AdministratorQueryUnreadList({}).then((res) => {
      this.data[0].list[0].num = res.data.backlogCount;
      this.data[0].list[1].num = res.data.taskCount;
      this.data[0].list[2].num = res.data.sendCount;
    });
  },
  methods: {
    removeClick() {
      let deviceType = localStorage.getItem("deviceType");
      console.log("deviceType",deviceType);
      try {
        if (deviceType == "android" || deviceType == "ios") {
          test.removeToken();
        }
      } catch (e) {
      }

      queryToken().then((r) => {
        if(r.data == 1){
          remove().then((res)=>{
            removeToken()
            logout().then(this.$router.push("/login"));
          })
        }
      }).catch(e=>{
        removeToken()
        logout().then(this.$router.push("/login"));
      });

    },
    handleClick(val) {
      console.log(val);
      switch (val) {
        case "审批记录":
          this.$router.push("/approvalRecord");
          break;
        case "抄送":
          this.$router.push("/cc");
          break;
        case "我的待办":
          this.$router.push("/myAgent");
          break;
        case "驾驶员档案":
          this.$router.push("/driver");
          break;
        case "领用记录":
          this.$router.push("/recipientsRecords");
          break;
        case "盘点":
          this.$router.push("/check");
          break;
        case "维修服务站":
          this.$router.push("/serviceStation");
          break;
        default:
          break;
      }
    },
  },
  beforeRouteEnter(to, from, next) {
    // 在渲染该组件的对应路由被 confirm 前调用
    // 不！能！获取组件实例 `this`
    // 因为当守卫执行前，组件实例还没被创建
    if(to.query.token){
      setToken(to.query.token)
    }
    if (!getToken()) {
      next("/login");
    } else {
      next();
    }
  },
};
</script>

<style scoped>
h2 {
  text-align: left;
  margin: 20px 0 10px 10px;
}
.content {
  width: 100%;
  position: fixed;
  top: 48px;
  height: calc(100% - 98px);
  padding-bottom: 10px;
  overflow-y: auto;
}
</style>
<style lang="scss">
.app {
  .van-grid-item__content {
    background-color: transparent !important;
  }
}
</style>
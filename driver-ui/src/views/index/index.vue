<template>
  <div>
    <Header :show="show"></Header>

    <div class="content">
      <div
        style="display: flex; padding: 0.6rem 0 0.2rem 0.5rem; margin-top: 10px"
      >
        <div style="line-height: 0.62rem">{{ name }}，您好！</div>
        <div style="flex: 1"></div>
        <!-- <van-button
          plain
          type="info"
          size="small"
          style="margin-right: 0.5rem"
          @click="removeClick"
          >注销</van-button
        > -->
      </div>
      <div v-for="value in data" :key="value.name">
        <h2>{{ value.name }}</h2>
        <van-grid :gutter="10">
          <van-grid-item
            v-for="li in value.list"
            @click="handleClick(li.title, 1)"
            :key="li.title"
            :icon="li.icon"
            :text="li.title"
            :dot="li.dot"
          />
        </van-grid>
      </div>
    </div>
    <Footer page="index"></Footer>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import { getToken, setToken, removeToken } from "@/utils/auth";
import { logout } from "@/api/login";
import { A04QueryUnreadList, A01DriverInfo } from "@/api/api";
import Cookies from "js-cookie";
export default {
  data() {
    return {
      show: false,
      name: undefined,
      flag: undefined,
      data: [
        {
          name: "考试/培训/公告",
          list: [
            {
              title: "考试",
              icon: "todo-list-o",
              dot: false,
            },
            {
              title: "培训",
              icon: "records",
              dot: false,
              type: 4,
            },
            {
              title: "公告",
              icon: "volume-o",
              dot: false,
              type: 5,
            },
          ],
        },
        {
          name: "考勤绩效",
          list: [
            {
              title: "请假",
              icon: "underway-o",
              dot: false,
              type: 1,
            },
            {
              title: "补助",
              icon: "refund-o",
              dot: false,
              type: 3,
            },
            {
              title: "薪酬",
              icon: "balance-o",
              dot: false,
            },
          ],
        },
        {
          name: "运输任务",
          list: [
            {
              title: "长途任务",
              icon: "envelop-o",
              dot: false,
              type: 6,
            },
            {
              title: "加班任务",
              icon: "envelop-o",
              dot: false,
            },
          ],
        },
        {
          name: "事故/违章",
          list: [
            {
              title: "事故记录",
              icon: "logistics",
              dot: false,
              type: 7,
            },
            {
              title: "违章处理",
              icon: "logistics",
              dot: false,
              type: 8,
            },
          ],
        },

        {
          name: "维修保养",
          list: [
            {
              title: "维修保养",
              icon: "question",
              dot: false,
              type: 2,
            },
            {
              title: "车辆定检",
              icon: "checked",
              dot: false,
              type: 9,
            },
          ],
        },
        {
          name: "物品领用",
          list: [
            {
              title: "物品领用",
              icon: "point-gift",
              dot: false,
              type: 10,
            },
          ],
        },
      ],
      loginForm: {
        // contact: '13388888888',
        contact: "18840920318",
      },
    };
  },
  created() {
    if (this.$route.query.token) {
      setToken(this.$route.query.token);
    }
    if (this.$route.query.pwdFlg) {
      localStorage.setItem("pwdFlg",1)
    }
  },
  mounted() {
    this.name = Cookies.get("username1");

    A01DriverInfo().then((res) => {
      this.name = res.data.name;
    })

    if (!this.$route.query.flag) {
      A04QueryUnreadList({}).then((res) => {
        for (const item of res.data) {
          for (const data of this.data) {
            for (const data1 of data.list) {
              if (item.type == data1.type && item.typeCount > 0) {
                data1.dot = true;
              }
            }
          }
        }
      }).catch(err =>{
        if(err.msg == '用户信息为空'){
          this.$router.push("/login");
        };
      });
    }
    console.log(this.$route.query);
    // debugger;
    // this.data = JSON.parse(JSON.stringify(this.data));
    // // console.log(this.$route.query.token);;//取token
    // this.loginForm.contact = this.$route.query.contact || this.loginForm.contact
    // this.$store
    //   .dispatch('Login', this.loginForm)
    //   .then(() => {
    //     console.log(111)
    //   })
    //   .catch(() => {})
    if (this.$route.query.flag) {
      this.$store
        .dispatch("Login", { flag: this.$route.query.flag })
        .then(() => {
          console.log(111);
          this.name = Cookies.get("username1");
          A04QueryUnreadList({}).then((res) => {
            for (const item of res.data) {
              for (const data of this.data) {
                for (const data1 of data.list) {
                  if (item.type == data1.type && item.typeCount > 0) {
                    data1.dot = true;
                  }
                }
              }
            }
          });
        })
        .catch(() => {
          console.log("错误");
        });
    } else {
    //    A04QueryUnreadList({}).then((res) => {
    //   for (const item of res.data) {
    //     for (const data of this.data) {
    //       for (const data1 of data.list) {
    //         if (item.type == data1.type && item.typeCount > 0) {
    //           data1.dot = true;
    //         }
    //       }
    //     }
    //   }
    // });
    }
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    removeClick() {
      removeToken();
      // if (test) {
      //   test.removeToken();
      // }
      this.$router.push("/login");
    },
    handleClick(name, num) {
      console.log(name, num);
      switch (name) {
        case "考试":
          this.$router.push("/writtenExamination");
          break;
        case "培训":
          this.$router.push("/myTraining");
          break;
        case "公告":
          this.$router.push("/announcement");
          break;
        case "请假":
          this.$router.push("/leave");
          break;
        case "薪酬":
          this.$router.push("/salary");
          break;
        case "事故记录":
          this.$router.push("/reportTheAccident");
          break;
        case "违章处理":
          this.$router.push("/endorsement");
          break;
        case "车辆定检":
          this.$router.push("/carRegularInspection");
          break;
        case "维修保养":
          this.$router.push("/maintenance");
          break;
        case "长途任务":
          this.$router.push("/assignment");
          break;
        case "加班任务":
          this.$router.push("/workOvertimeTask");
          break;
        case "物品领用":
          this.$router.push("/articlesUsingRegistration");
          break;
        case "补助":
          this.$router.push("/subsidy");
          break;
        default:
          break;
      }
    },
    login_fn() {
      this.$store
        .dispatch("Login", { flag: this.$route.query.flag })
        .then(() => {
          console.log(111);
        })
        .catch(() => {
          console.log("错误");
          next();
        });
    },
  },
  beforeRouteEnter(to, from, next) {

    if(to.query.pwdFlg == 1){
      if(to.query.token != undefined && to.query.token != "" && to.query.token != null ){
        next();
      } else {
        next("/login");
      }
    } else {
      if (!getToken()) {
        console.log(to, from);
        if (to.query.flag || (to.query.token)) {
          next();
        } else {
          next("/login");
        }
      } else {
        next();
      }
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

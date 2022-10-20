<template>
  <div class="app-container home">
    <h1 style="text-align: center; font-size: 40px">
      欢迎使用解放车务管理系统
    </h1>
    <div class="app-tltle">数据统计</div>

    <div class="container">
      <div class="app-box">
        <div class="img">
          <img src="@/assets/image/人.png" alt="" srcset="" />
        </div>
        <div class="context">
          <div>驾驶员总数</div>
          <div style="line-height: 60px">{{ form.driverCount }}</div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="app-box">
        <div class="img">
          <img src="@/assets/image/车.png" alt="" srcset="" />
        </div>
        <div class="context">
          <div>车辆总数</div>
          <div style="line-height: 60px">{{ form.vehicleTotalCount }}</div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="app-box">
        <div class="img">
          <img src="@/assets/image/车.png" alt="" srcset="" />
        </div>
        <div class="context">
          <div>主车总数</div>
          <div style="line-height: 60px">{{ form.mainCount }}</div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="app-box">
        <div class="img">
          <img src="@/assets/image/车.png" alt="" srcset="" />
        </div>
        <div class="context">
          <div>挂车总数</div>
          <div style="line-height: 60px">{{ form.hangCount }}</div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="app-box">
        <div class="img">
          <img src="@/assets/image/线路.png" alt="" srcset="" />
        </div>
        <div class="context">
          <div>线路总数</div>
          <div style="line-height: 60px">{{ form.routeCount}}</div>
        </div>
      </div>
    </div>

    <div class="app-tltle">我的待办</div>
    <ul class="app-ul">
      <li v-for="item in dataList" :key="item.id">
        您有新的{{ item.typeEnum }}待处理！
        <a @click="handleClick(item.type,item.relationId)">点击处理</a>
      </li>
      <!-- <li>您有新的补助申请审批待处理！ <a @click="handleClick">点击处理</a></li>
      <li>您有新的请假申请审批待处理！ <a @click="handleClick">点击处理</a></li> -->
    </ul>

  </div>
</template>

<script>
import {
  statisticsIndexExamineTaskList,
  statisticsIndexStatistics,
} from "@/api/index";
export default {
  name: "index",
  data() {
    return {
      // 版本号
      version: "2.2.0",
      dataList: [],
      form: {},
    };
  },
  mounted() {
    // document.cookie = ";HttpOnly"
    statisticsIndexStatistics({}).then((res) => {
      console.log(res);
      this.form = res.data;
    });
    statisticsIndexExamineTaskList({}).then((res) => {
      console.log(res);
      this.dataList = res.rows;
    });
  },
  methods: {
    handleClick(data,id) {
      let arr1 = [
        {
          name: "请假",
          path: "/attendanceManageApproval",
          type: 2,
        },
        {
          name: "招聘",
          path: "/recruitmentPlanExamine",
          type: 6,
        },
        {
          name: "薪酬",
          path: "/approvalOfSalaryDetails",
          type: 10,
        },
      ];
      let arr = [
        {
          name: "补助",
          path: "/examineAndApprove/",
          type: 1,
        },
        {
          name: "交通事故",
          path: "/trafficAccidentManagementExaminationAndApproval/",
          type: 4,
        },
        {
          name: "维修保养驾驶员",
          path: "/approval/",
          type: 7,
        },
        {
          name: "运输任务",
          path: "/detailAbnormalSituation/",
          type: 9,
        },
        {
          name: "车辆保险",
          path: "/editInsurance/",
          type: 11,
        },
        {
          name: "物品领用",
          path: "/editCollectionManagement/",
          type: 12,
        },
        {
          name: "车辆定检",
          path: "/editCarRegularInspection/",
          type: 13,
        },

        {
          name: "维修保养管理（维修服务站）审批",
          path: "/approvalServiceStation/",
          type: 8,
        },
        {
          name: "货物保险",
          path: "/goodsEditInsurance/",
          type: 14,
        },
      ];
      for (const item of arr) {
        if (data == item.type) {
          this.$router.push(item.path+id)
        }
      }
         for (const item of arr1) {
        if (data == item.type) {
          this.$router.push({path:item.path,query:{id}})
        }
      }


      console.log(11);
    },
    // goTarget(href) {
    //   window.open(href, "_blank");
    // },
  },
};
</script>
<style lang="scss">
.container {
  width: 350px!important;
  border: 1px solid #333;
  display: inline-block;
  height: 150px!important;
  margin: 0 20px 20px 0;
}
.app-tltle {
  font-size: 30px;
  font-weight: 600;
  color: #333;
  margin: 20px 0;
}
.app-box {
  display: flex;
  img {
    width: 75px;
    height: 75px;
    margin: auto;
  }
  .img {
    flex: 1;
    height: 150px;
    display: flex;
  }
  .context {
    flex: 1;
    div {
      height: 50%;
      text-align: center;
      height: 75px;
      line-height: 80px;
      font-size: 20px;
    }
  }
}
.app-ul {
  font-size: 15px;
  margin-left: 30px !important;
  font-size: 25px;
  li {
    list-style: disc;
    margin: 10px 0;
    a {
      text-decoration: underline;
    }
  }
}
</style>
<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>


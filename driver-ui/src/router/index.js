import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: "/",
      redirect: "/index"
    },
    {
      path: "/index",
      component: () => import("@/views/index/index"),
      meta: {
        title: "工作台"
      },
    },
    {
      path: "/login",
      component: () => import("@/views/login/login"),
      meta: {
        title: "登陆"
      },
    },
    {
      path: "/editPassword",
      component: () =>
          import ("@/views/my/password/editPassword"),
      meta: {
          title: "修改密码"
      }
  },
    {
      path: "/message",
      component: () => import("@/views/message/index"),
      meta: {
        title: "消息中心"
      },
    },
    {
      path: "/writtenExamination",
      component: () => import("@/views/examination/writtenExamination"),
      meta: {
        title: "入职笔试"
      }
    },
    {
      path: "/detailWrittenExamination",
      component: () => import("@/views/examination/detailWrittenExamination"),
      meta: {
        title: "笔试记录"
      }
    },
    {
      path: "/subsidy",
      component: () => import("@/views/subsidyList/subsidy"),
      meta: {
        title: "补助"
      }
    },
    {
      path: "/subsidyRemote",
      component: () => import("@/views/subsidyList/subsidyRemote"),
      meta: {
        title: "申请补助"
      }
    },
    {
      path: "/approvalPending/:id",
      component: () => import("@/views/subsidyList/approvalPending"),
      meta: {
        title: "申请补助"
      }
    },
    {
      path: "/alreadyPassed/:id",
      component: () => import("@/views/subsidyList/alreadyPassed"),
      meta: {
        title: "申请补助(已通过)"
      }
    },
    {
      path: "/refused/:id",
      component: () => import("@/views/subsidyList/refused"),
      meta: {
        title: "申请补助(已拒绝)"
      }
    },
    {
      path: "/undone/:id",
      component: () => import("@/views/subsidyList/undone"),
      meta: {
        title: "申请补助(已撤销)"
      }
    },
    {
      path: "/myTraining",
      component: () => import("@/views/training/myTraining"),
      meta: {
        title: "我的培训"
      }
    },
    {
      path: "/trainingDetails/:id",
      component: () => import("@/views/training/trainingDetails"),
      meta: {
        title: "培训详情"
      }
    },
    {
      path: "/announcement",
      component: () => import("@/views/announcement/announcement"),
      meta: {
        title: "公告"
      }
    },
    {
      path: "/announcementDetails/:id",
      component: () => import("@/views/announcement/announcementDetails"),
      meta: {
        title: "公告详情"
      }
    },
    {
      path: "/reportTheAccident",
      component: () => import("@/views/accidentRecord/reportTheAccident"),
      meta: {
        title: "事故记录"
      }
    },
    {
      path: "/theAccidentReport",
      component: () => import("@/views/accidentRecord/theAccidentReport"),
      meta: {
        title: "事故上报"
      }
    },
    {
      path: "/theAccidentReportSearch",
      component: () => import("@/views/accidentRecord/theAccidentReportSearch"),
      meta: {
        title: "车牌号搜索"
       
      }
    },
    {
      path: "/locationOfMalfunction",
      component: () => import("@/views/accidentRecord/locationOfMalfunction"),
      meta: {
        title: "事故定位"
      }
    },
    {
      path: "/incidentRecordDetails/:id",
      component: () => import("@/views/accidentRecord/incidentRecordDetails"),
      meta: {
        title: "事故记录详情"
      }
    },
    {
      path: "/endorsement",
      component: () => import("@/views/endorsementList/endorsement"),
      meta: {
        title: "违章处理"
      }
    },
    {
      path: "/violationOfTheDetails/:id",
      component: () => import("@/views/endorsementList/violationOfTheDetails"),
      meta: {
        title: "违章详情"
      }
    },
    {
      path: "/reportAgain/:id",
      component: () => import("@/views/accidentRecord/reportAgain"),
      meta: {
        title: "再次上报"
      }
    },
    {
      path: "/salary",
      component: () => import("@/views/salary/index"),
      meta: {
        title: "薪酬"
      }
    },
    {
      path: "/salaryDetail/:id",
      component: () => import("@/views/salary/detail"),
      meta: {
        title: "薪酬详情"
      }
    },
    {
      path: "/leave",
      component: () => import("@/views/leave/index"),
      meta: {
        title: "请假记录"
      }
    },
     {
      path: "/leaveAdd",
      component: () => import("@/views/leave/add"),
      meta: {
        title: "请假"
      }
    },
    {
      path: "/leaveDetail/:id",
      component: () => import("@/views/leave/detail"),
      meta: {
        title: "请假记录"
      }
    },
    {
      path: "/leaveEdit/:id",
      component: () => import("@/views/leave/edit"),
      meta: {
        title: "再次提交"
      }
    },
    {
      path: "/carRegularInspection",
      component: () => import("@/views/carRegularInspection/index"),
      meta: {
        title: "车辆定检"
      }
    },
    {
      path: "/mainVehicleAdd",
      component: () => import("@/views/carRegularInspection/mainVehicleAdd"),
      meta: {
        title: "主车定检新增"
      }
    }, {
      path: "/trailerAdd",
      component: () => import("@/views/carRegularInspection/trailerAdd"),
      meta: {
        title: "挂车定检新增"
      }
    },
    {
      path: "/detailCarRegularInspection/:id",
      component: () => import("@/views/carRegularInspection/detail"),
      meta: {
        title: "车辆定检"
      }
    },
    {
      path: "/leaveDetail",
      component: () => import("@/views/leave/detail"),
      meta: {
        title: "请假记录"
      }
    },
    {
      path: "/maintenance",
      component: () => import("@/views/maintenance/index"),
      meta: {
        title: "维修保养"
      }
    },
    {
      path: "/maintenanceAdd",
      component: () => import("@/views/maintenance/add"),
      meta: {
        title: "维修保养"
      }
    },
    {
      path: "/detailMaintenance/:id",
      component: () => import("@/views/maintenance/detailMaintenance"),
      meta: {
        title: "维修保养"
      }
    },
    {
      path: "/maintenanceAdopt/:id",
      component: () => import("@/views/maintenance/adopt"),
      meta: {
        title: "记录详情"
      }
    },
    {
      path: "/maintenanceDetail/:id",
      component: () => import("@/views/maintenance/detail"),
      meta: {
        title: "记录详情"
      }
    },
    {
      path: "/assignment",
      component: () => import("@/views/assignment/index"),
      meta: {
        title: "长途任务"
      },
    },
    {
      path: "/workOvertimeTask",
      component: () => import("@/views/workOvertimeTaskList/workOvertimeTask"),
      meta: {
        title: "加班任务"
      },
    },
    {
      path: "/taskdetail/:id",
      component: () => import("@/views/workOvertimeTaskList/taskdetail"),
      meta: {
        title: "加班任务详情"
      },
    },
    {
      path: "/missionRecords",
      component: () => import("@/views/assignment/missionRecords"),
      meta: {
        title: "任务记录"
      },
    },
    {
      path: "/spotCheck/:id",
      component: () => import("@/views/assignment/transportationTaskChild/spotCheck"),
      meta: {
        title: "点检记录"
      },
    },
    {
      path: "/spotCheckDetail/:id",
      component: () => import("@/views/assignment/transportationTaskChild/spotCheckDetail"),
      meta: {
        title: "点检记录详情"
      },
    },
    {
      path: "/exceptionReporting/:id",
      component: () => import("@/views/assignment/transportationTaskChild/exceptionReporting"),
      meta: {
        title: "异常情况"
      },
    },
    {
      path: "/andExceptionReporting/:id",
      component: () => import("@/views/assignment/transportationTaskChild/andExceptionReporting"),
      meta: {
        title: "异常情况再次提交"
      },
    },
    {
      path: "/abnormalConditionRecord/:id",
      component: () => import("@/views/assignment/transportationTaskChild/abnormalConditionRecord"),
      meta: {
        title: "异常情况记录"
      },
    },
    {
      path: "/applicationDetails/:id",
      component: () => import("@/views/assignment/transportationTaskChild/applicationDetails"),
      meta: {
        title: "异常申报记录"
      },
    },
    {
      path: "/transportationTask/:id",
      component: () => import("@/views/assignment/transportationTask"),
      meta: {
        title: "运输任务"
      },
    },
    {
      path: "/missionRecordsDetail/:id",
      component: () => import("@/views/assignment/missionRecordsChild/missionRecordsDetail"),
      meta: {
        title: "任务记录详情"
      },
    },

    {
      path: "/search",
      component: () => import("@/views/search/index"),
      meta: {
        title: "搜索"
      }
    },
    {
      path: "/articlesUsingRegistration",
      component: () => import("@/views/articlesUsingRegistrationList/articlesUsingRegistration"),
      meta: {
        title: "物品领用"
      }
    },
    {
      path: "/urea/:id",
      component: () => import("@/views/articlesUsingRegistrationList/urea"),
      meta: {
        title: "尿素"
      }
    },
    {
      path: "/ureaDetail/:id",
      component: () => import("@/views/articlesUsingRegistrationList/ureaDetail"),
      meta: {
        title: "尿素详情"
      }
    },
    {
      path: "/workingDdinner/:id",
       component: () => import("@/views/articlesUsingRegistrationList/workingDdinner"),
      meta: {
        title: "工作餐",  
      },
    },
    {
      path: "/workingDdinnerDetail/:id",
       component: () => import("@/views/articlesUsingRegistrationList/workingDdinnerDetail"),
      meta: {
        title: "工作餐详情",  
      },
    },
    {
      path: "/tyre/:id",
      component: () => import("@/views/articlesUsingRegistrationList/tyre"),
      meta: {
        title: "轮胎"
      }
    },
    {
      path: "/tyreDetail/:id",
      component: () => import("@/views/articlesUsingRegistrationList/tyreDetail"),
      meta: {
        title: "轮胎详情"
      }
    },
    {
      path: "/lowPricedAndEasilyWornArticles/:id",
      component: () => import("@/views/articlesUsingRegistrationList/lowPricedAndEasilyWornArticles"),
      meta: {
        title: "低值易耗品"
      }
    },
    {
      path: "/lowPricedAndEasilyWornArticlesDetail/:id",
      component: () => import("@/views/articlesUsingRegistrationList/lowPricedAndEasilyWornArticlesDetail"),
      meta: {
        title: "低值易耗品详情"
      }
    },
    {
      path: "/replacementPart/:id",
      component: () => import("@/views/articlesUsingRegistrationList/replacementPart"),
      meta: {
        title: "备件"
      }
    },
    {
      path: "/replacementPartDetail/:id",
      component: () => import("@/views/articlesUsingRegistrationList/replacementPartDetail"),
      meta: {
        title: "备件详情"
      }
    },
    {
      path: "/sparePartsSelection",
      component: () => import("@/views/articlesUsingRegistrationList/sparePartsSelection"),
      meta: {
        title: "备件选择"
      }
    },
    {
      path: "/recipientsRecords",
      component: () => import("@/views/articlesUsingRegistrationList/recipientsRecords"),
      meta: {
        title: "领用记录"
      }
    },
    {
      path: "/receiveGoods",
      component: () => import("@/views/articlesUsingRegistrationList/receiveGoods"),
      meta: {
        title: "重新申请"
      }
    },
    {
      path: "/callDetailRecording/:id",
      component: () => import("@/views/articlesUsingRegistrationList/callDetailRecording"),
      meta: {
        title: "记录详情"
      }
    },
    {
      path: "/video",
      component: () => import("@/views/video"),
      meta: {
        title: "视频"
      }
    },
    {
      path: "/fpassword",
      component: () => import("@/views/login/fpassword"),
      meta: {
        title: "视频"
      }
    },
    {
      path: "/my",
      component: () => import("@/views/my/index"),
      redirect: "/my/center",
      children: [
        {
          path: "center",
          meta: {
            title: "个人中心"
          },
          component: () => import("@/views/my/children/center")
        }
      ]
    },
    {
      path: "*",
      component: () => import("@/components/NotFound")
    }
  ]
});

router.beforeEach((to, from, next) => {
  let { title } = to.meta;
  document.title = title;

  next();
});

export default router;

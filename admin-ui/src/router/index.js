import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    routes: [{
            path: "/",
            redirect: "/index"
        },
        {
            path: "/index",
            component: () =>
                import ("@/views/index/index"),
            meta: {
                title: "工作台"
            }
        },
        {
            path: "/cc",
            component: () =>
                import ("@/views/cc/index"),
            meta: {
                title: "抄送记录"
            }
        },
        {
            path: "/approvalRecord",
            component: () =>
                import ("@/views/approvalRecord/index"),
            meta: {
                title: "审批记录"
            }
        },
        {
            path: "/approvalRecord/leave/:id",
            component: () =>
                import ("@/views/approvalRecord/leave"),
            meta: {
                title: "请假审批记录"
            }
        },
        {
            path: "/approvalRecord/adopt/:id",
            component: () =>
                import ("@/views/approvalRecord/adopt"),
            meta: {
                title: "维修保养记录"
            }
        },
        {
            path: "/approvalRecord/subsidyList/:id",
            component: () =>
                import ("@/views/approvalRecord/subsidyList"),
            meta: {
                title: "补助审批记录"
            }
        },
        {
            path: "/approvalRecord/accident/:id",
            component: () =>
                import ("@/views/approvalRecord/accident"),
            meta: {
                title: "交通事故审批记录"
            }
        },
        {
            path: "/approvalRecord/collectionManagement/:id",
            component: () =>
                import ("@/views/approvalRecord/collectionManagement"),
            meta: {
                title: "物品领用审批记录"
            }
        },
        {
            path: "/approvalRecord/approved/:id",
            component: () =>
                import ("@/views/approvalRecord/approved"),
            meta: {
                title: "薪酬审批记录"
            }
        },
        {
            path: "/approvalRecord/carRegularInspection/:id",
            component: () =>
                import ("@/views/approvalRecord/carRegularInspection"),
            meta: {
                title: "车辆定检审批记录"
            }
        },
        {
            path: "/approvalRecord/recruitmentPlanExamine/:id",
            component: () =>
                import ("@/views/approvalRecord/recruitmentPlanExamine"),
            meta: {
                title: "招聘审批记录"
            }
        },
        {
            path: "/approvalRecord/insurance/:id",
            component: () =>
                import ("@/views/approvalRecord/insurance"),
            meta: {
                title: "保险审批记录"
            }
        },
        {
            path: "/approvalRecord/abnormalSituation/:id",
            component: () =>
                import ("@/views/approvalRecord/abnormalSituation"),
            meta: {
                title: "运输任务"
            }
        },
        {
            path: "/myAgent",
            component: () =>
                import ("@/views/myAgent/index"),
            meta: {
                title: "我的待办"
            }
        },
        {
            path: "/myAgent/leave/:id",
            component: () =>
                import ("@/views/myAgent/leave"),
            meta: {
                title: "请假审批记录"
            }
        },
        {
            path: "/myAgent/adopt/:id",
            component: () =>
                import ("@/views/myAgent/adopt"),
            meta: {
                title: "维修保养记录"
            }
        },
        {
            path: "/myAgent/subsidyList/:id",
            component: () =>
                import ("@/views/myAgent/subsidyList"),
            meta: {
                title: "补助审批记录"
            }
        },
        {
            path: "/myAgent/accident/:id",
            component: () =>
                import ("@/views/myAgent/accident"),
            meta: {
                title: "交通事故审批记录"
            }
        },
        {
            path: "/myAgent/collectionManagement/:id",
            component: () =>
                import ("@/views/myAgent/collectionManagement"),
            meta: {
                title: "物品领用审批记录"
            }
        },
        {
            path: "/myAgent/approved/:id",
            component: () =>
                import ("@/views/myAgent/approved"),
            meta: {
                title: "薪酬审批记录"
            }
        },
        {
            path: "/myAgent/carRegularInspection/:id",
            component: () =>
                import ("@/views/myAgent/carRegularInspection"),
            meta: {
                title: "车辆定检审批记录"
            }
        },
        {
            path: "/myAgent/recruitmentPlanExamine/:id",
            component: () =>
                import ("@/views/myAgent/recruitmentPlanExamine"),
            meta: {
                title: "招聘审批记录"
            }
        },
        {
            path: "/myAgent/insurance/:id",
            component: () =>
                import ("@/views/myAgent/insurance"),
            meta: {
                title: "保险审批记录"
            }
        },
        {
            path: "/myAgent/abnormalSituation/:id",
            component: () =>
                import ("@/views/myAgent/abnormalSituation"),
            meta: {
                title: "运输任务"
            }
        },
        {
            path: "/driver",
            component: () =>
                import ("@/views/driverList/driver"),
            meta: {
                title: "驾驶员档案"
            }
        },
        {
            path: "/driverDetails/:id",
            component: () =>
                import ("@/views/driverList/driverDetails"),
            meta: {
                title: "驾驶员档案详情"
            }
        },
        {
            path: "/material/:id",
            component: () =>
                import ("@/views/driverList/material"),
            meta: {
                title: "驾驶员档案资料"
            }
        },
        {
            path: "/recipientsRecords",
            component: () =>
                import ("@/views/recipientsRecordsList/recipientsRecords"),
            meta: {
                title: "物品领用"
            }
        },
        {
            path: "/recordDetails/:id",
            component: () =>
                import ("@/views/recipientsRecordsList/recordDetails"),
            meta: {
                title: "物品领用详情"
            }
        },
        {
            path: "/check",
            component: () =>
                import ("@/views/checkList/check"),
            meta: {
                title: "盘点"
            }
        },
      
        {
            path: "/check/add/:id",
            component: () =>
                import ("@/views/checkList/add"),
            meta: {
                title: "添加"
            }
        },
        {
            path: "/edit/:id",
            component: () =>
                import ("@/views/checkList/edit"),
            meta: {
                title: "编辑"
            }
        },
        {
            path: "/see/:id",
            component: () =>
                import ("@/views/checkList/see"),
            meta: {
                title: "查看"
            }
        },
        {
            path: "/message",
            component: () =>
                import ("@/views/message/index"),
            meta: {
                title: "消息中心"
            }
        },
        {
            path: "/login",
            component: () =>
                import ("@/views/login/index"),
            meta: {
                title: "登录"
            }
        },
          {
            path: "/serviceStation",
            component: () =>
                import ("@/views/serviceStation/index"),
            meta: {
                title: "维修服务站"
            }
        },
        {
            path: "/serviceStationAdd",
            component: () =>
                import ("@/views/serviceStation/add"),
            meta: {
                title: "维修服务站"
            }
        },
        {
            path: "/my",
            component: () =>
                import ("@/views/my/index"),
            redirect: "/my/center",
            children: [{
                path: "center",
                meta: {
                    title: "个人中心"
                },
                component: () =>
                    import ("@/views/my/children/center")
            }]
        },
        {
            path: "*",
            component: () =>
                import ("@/components/NotFound")
        }
    ]
});

router.beforeEach((to, from, next) => {
    let { title } = to.meta;
    document.title = title;

    next();
});

export default router;
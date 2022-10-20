import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    noCache: true                // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
  }
 */

/* 
path路由地址
component （driver-manage/recruitmentPlan）组件路径

*/


// 公共路由
export const constantRoutes = [{
        path: '/redirect',
        component: Layout,
        hidden: true,
        children: [{
            path: '/redirect/:path(.*)',
            component: (resolve) => require(['@/views/redirect'], resolve)
        }]
    },
    {
        path: '/login',
        component: (resolve) => require(['@/views/login'], resolve),
        hidden: true
    },
    {
        path: '/404',
        component: (resolve) => require(['@/views/error/404'], resolve),
        hidden: true
    },
    {
        path: '/401',
        component: (resolve) => require(['@/views/error/401'], resolve),
        hidden: true
    },
    {
        path: '',
        component: Layout,
        redirect: 'index',
        children: [{
            path: 'index',
            component: (resolve) => require(['@/views/index'], resolve),
            name: '首页',
            meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
        }]
    },
    {
        path: '/user',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [{
            path: 'profile',
            component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
            name: 'Profile',
            meta: { title: '个人中心', icon: 'user' }
        }]
    },
    {
        path: '/dict',
        component: Layout,
        hidden: true,
        children: [{
            path: 'type/data/:dictId(\\d+)',
            component: (resolve) => require(['@/views/system/dict/data'], resolve),
            name: 'Data',
            meta: { title: '字典数据', icon: '' }
        }]
    },
    {
        path: '/job',
        component: Layout,
        hidden: true,
        children: [{
            path: 'log',
            component: (resolve) => require(['@/views/monitor/job/log'], resolve),
            name: 'JobLog',
            meta: { title: '调度日志' }
        }]
    },
    {
        path: '/gen',
        component: Layout,
        hidden: true,
        children: [{
            path: 'edit/:tableId(\\d+)',
            component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
            name: 'GenEdit',
            meta: { title: '修改生成配置' }
        }]
    },
    // {
    //   path: '/driver',
    //   component: Layout,
    //   hidden: true,
    //   meta: { title: '驾驶员管理', icon: 'table' },
    //   children: [
    //     {
    //       path: '/recruitmentPlan',
    //       component: (resolve) => require(['@/views/driver-manage/recruitmentPlan'], resolve),
    //       name: 'recruitmentPlan',
    //       meta: { title: '招聘计划', icon: 'table' }
    //     },
    //     // {
    //     //   path: '/addRecruitmentInfo',//地址
    //     //   component: () => import('@/views/driver-manage/addRecruitmentInfo'),
    //     //   name: 'addRecruitmentInfo',
    //     //   meta: { title: '招聘计划新增' },//招聘计划
    //     //   hidden: true
    //     // },
    //     {
    //       path: '/recruitmentPlanExamine',
    //       component: () => import('@/views/driver-manage/recruitmentPlanExamine'),
    //       name: 'recruitmentPlanExamine',
    //       meta: { title: '招聘计划审核' },
    //       hidden: true
    //     },
    //     {
    //       path: '/recruitmentPlanCheck',
    //       component: () => import('@/views/driver-manage/recruitmentPlanCheck'),
    //       name: 'recruitmentPlanCheck',
    //       meta: { title: '招聘计划查看' },
    //       hidden: true
    //     },
    //     {
    //       path: '/basicInfoEdit/:id',
    //       component: () => import('@/views/driver-manage/basicInfoEdit'),
    //       name: 'basicInfoEdit',
    //       meta: { title: '驾驶员信息编辑' },
    //       hidden: true
    //     },
    //     {
    //       path: '/basicInfoWrite/:id',
    //       component: () => import('@/views/driver-manage/basicInfoWrite'),
    //       name: 'basicInfoWrite',
    //       meta: { title: '笔试记录' },
    //       hidden: true
    //     },
    //     {
    //       path: '/basicInfoInterview/:id',
    //       component: () => import('@/views/driver-manage/basicInfoInterview'),
    //       name: 'basicInfoInterview',
    //       meta: { title: '面试上传' },
    //       hidden: true
    //     },
    //     {
    //       path: '/basicInfoSem/:id',
    //       component: () => import('@/views/driver-manage/basicInfoSem'),
    //       name: 'basicInfoSem',
    //       meta: { title: '实操记录' },
    //       hidden: true
    //     },
    //     {
    //       path: '/recruitmentPlanEdit',
    //       component: () => import('@/views/driver-manage/recruitmentPlanEdit'),
    //       name: 'recruitmentPlanEdit',
    //       meta: { title: '编辑' },
    //       hidden: true
    //     },
    //     {
    //       path: '/interviewManage',
    //       component: (resolve) => require(['@/views/driver-manage/interviewManage'], resolve),
    //       name: 'interviewManage',
    //       meta: { title: '面试管理', icon: 'table' }
    //     },
    //     {
    //       path: '/basicInfo/:id',
    //       component: () => import('@/views/driver-manage/basicInfo'),
    //       name: 'basicInfo',
    //       meta: { title: '基本信息' },
    //       hidden: true
    //     },
    //     // {
    //     //   path: '/paperManage',
    //     //   component: (resolve) => require(['@/views/driver-manage/paperManage'], resolve),
    //     //   name: 'paperManage',
    //     //   meta: { title: '试卷管理', icon: 'table' },
    //     //   children:[
    //     //     {
    //     //       path: 'addPaperManage',
    //     //       component: (resolve) => require(['@/views/driver-manage/paperManageChild/addPaperManage'], resolve),
    //     //       name: 'addPaperManage',
    //     //       meta: { title: '新增试卷', icon: 'table' },
    //     //       hidden: true
    //     //     },
    //     //   ]
    //     // },
    //     // {
    //     //   path: '/paperManageWrite/:id',
    //     //   component: (resolve) => require(['@/views/driver-manage/paperManageChild/paperManageWrite'], resolve),
    //     //   name: 'paperManageWrite',
    //     //   meta: { title: '查看试卷', icon: 'table' },
    //     //   hidden: true
    //     // },
    //     // {
    //     //   path: '/paperManageDetail/:id',
    //     //   component: (resolve) => require(['@/views/driver-manage/paperManageChild/paperManageDetail'], resolve),
    //     //   name: 'paperManageDetail',
    //     //   meta: { title: '配置试卷', icon: 'table' },
    //     //   hidden: true
    //     // },
    //     // {
    //     //   path: '/addPaperManage',
    //     //   component: (resolve) => require(['@/views/driver-manage/paperManageChild/addPaperManage'], resolve),
    //     //   name: 'addPaperManage',
    //     //   meta: { title: '新增试卷', icon: 'table' },
    //     //   hidden: true
    //     // },
    //     {
    //       path: '/questionBankManage',
    //       component: (resolve) => require(['@/views/driver-manage/questionBankManage'], resolve),
    //       name: 'questionBankManage',
    //       meta: { title: '题库管理', icon: 'table' }
    //     },
    //     {
    //       path: '/driverTypeMange',
    //       component: (resolve) => require(['@/views/driver-manage/driverTypeManage'], resolve),
    //       name: 'driverTypeMange',
    //       meta: { title: '驾驶员类型管理', icon: 'table' }
    //     },
    //     {
    //       path: '/driverProfile',
    //       component: (resolve) => require(['@/views/driver-manage/driverProfile'], resolve),
    //       name: 'driverProfile',
    //       meta: { title: '驾驶员档案', icon: 'table' },
    //       children: [

    //       ]
    //     },
    //     {
    //       path: '/driverProfileAdd',
    //       component: (resolve) => require(['@/views/driver-manage/driverProfileChild/driverProfileAdd'], resolve),
    //       name: 'driverProfileAdd',
    //       meta: { title: '驾驶员档案新增', icon: 'table' },
    //       hidden: true

    //     },
    //     {
    //       path: '/seeAccidentRecords/:id',
    //       component: (resolve) => require(['@/views/driver-manage/driverProfileChild/seeAccidentRecords'], resolve),
    //       name: 'seeAccidentRecords',
    //       meta: { title: '查看', icon: 'table' },
    //       hidden: true

    //     },
    //     {
    //       path: '/driverProfileDetail/:id',
    //       component: (resolve) => require(['@/views/driver-manage/driverProfileChild/driverProfileDetail'], resolve),
    //       name: 'driverProfileDetail',
    //       meta: { title: '驾驶员档案详情', icon: 'table' },
    //       hidden: true

    //     },
    //     {
    //       path: '/trainingManage',
    //       component: (resolve) => require(['@/views/driver-manage/trainingManage'], resolve),
    //       name: 'trainingManage',
    //       meta: { title: '培训管理', icon: 'table' }
    //     },
    //     {
    //       path: '/selectTrainers',
    //       component: (resolve) => require(['@/views/driver-manage/addTrainingInfoChild/selectTrainers'], resolve),
    //       name: 'selectTrainers',
    //       hidden: true,
    //       meta: { title: '选择培训人员', icon: 'table' }
    //     },

    //     {
    //       path: '/seetrainingManage/:id',
    //       component: (resolve) => require(['@/views/driver-manage/addTrainingInfoChild/seetrainingManage'], resolve),
    //       name: 'seetrainingManage',
    //       hidden: true,
    //       meta: { title: '查看', icon: 'table' }
    //     },
    //     {
    //       path: '/addTrainingInfo',
    //       component: () => import('@/views/driver-manage/addTrainingInfo'),
    //       name: 'addTrainingInfo',
    //       meta: { title: '新增' },
    //       hidden: true
    //     },
    //     {
    //       path: '/trainingStatistics',
    //       component: () => import('@/views/driver-manage/addTrainingInfoChild/trainingStatistics'),
    //       name: 'trainingStatistics',
    //       meta: { title: '培训统计' },
    //       hidden: true
    //     },
    //     {
    //       path: '/trainingInfoModify/:id',
    //       component: () => import('@/views/driver-manage/addTrainingInfoChild/trainingInfoModify'),
    //       name: 'trainingInfoModify',
    //       meta: { title: '编辑' },
    //       hidden: true
    //     },
    //     {
    //       path: '/groupManage',
    //       component: (resolve) => require(['@/views/driver-manage/groupManage'], resolve),
    //       name: 'groupManage',
    //       meta: { title: '群组管理', icon: 'table' }
    //     },
    //     {
    //       path: '/addGroupInfo',
    //       component: () => import('@/views/driver-manage/addGroupInfo'),
    //       name: 'addGroupInfo',
    //       meta: { title: '新增群组信息' },
    //       hidden: true
    //     },
    //     {
    //       path: '/editGroupInfo',
    //       component: () => import('@/views/driver-manage/editGroupInfo'),
    //       name: 'editGroupInfo',
    //       meta: { title: '编辑群组信息' },
    //       hidden: true
    //     },
    //     {
    //       path: '/noticeManage',
    //       component: (resolve) => require(['@/views/driver-manage/noticeManage'], resolve),
    //       name: 'noticeManage',
    //       meta: { title: '公告管理', icon: 'table' }
    //     },
    //     {
    //       path: '/addNotice',
    //       component: () => import('@/views/driver-manage/addNotice'),
    //       name: 'addNotice',
    //       meta: { title: '新增公告' },
    //       hidden: true
    //     },

    //     {
    //       path: '/checkNotice',
    //       component: () => import('@/views/driver-manage/checkNotice'),
    //       name: 'checkNotice',
    //       meta: { title: '查看公告' },
    //       hidden: true
    //     },
    //     {

    //       path: '/addSelectParticipants ',
    //       component: () => import('@/views/driver-manage/addSelectParticipants'),
    //       name: 'addSelectParticipants',
    //       meta: { title: '选择参与人员' },
    //       hidden: true
    //     },

    //     {
    //       path: '/readedList',
    //       component: () => import('@/views/driver-manage/readedList'),
    //       name: 'readedList',
    //       meta: { title: '已读列表' },
    //       hidden: true
    //     },
    //     {
    //       path: '/editNotice',
    //       component: () => import('@/views/driver-manage/editNotice'),
    //       name: 'editNotice',
    //       meta: { title: '编辑' },
    //       hidden: true

    //     },
    //     {
    //       path: '/addSelectParticipants',
    //       component: () => import('@/views/driver-manage/addSelectParticipants'),
    //       name: 'addSelectParticipants',
    //       meta: { title: '选择参与人员' },
    //       hidden: true
    //     },

    //     {
    //       path: '/editSelectParticipants',
    //       component: () => import('@/views/driver-manage/editSelectParticipants'),
    //       name: 'editSelectParticipants',
    //       meta: { title: '选择参与人员' },
    //       hidden: true
    //     },
    //     {
    //       path: '/leaveTypeManage',
    //       component: (resolve) => require(['@/views/driver-manage/leaveTypeManage'], resolve),
    //       name: 'leaveTypeManage',
    //       meta: { title: '请假类型管理', icon: 'table' }
    //     },
    //     {
    //       path: '/attendanceManage',
    //       component: (resolve) => require(['@/views/driver-manage/attendanceManage'], resolve),
    //       name: 'attendanceManage',
    //       meta: { title: '考勤管理', icon: 'table' }
    //     },
    //     {
    //       path: '/leaveStatistics/',
    //       component: () => import('@/views/driver-manage/leaveStatistics'),
    //       name: 'leaveStatistics',
    //       meta: { title: '请假统计' },
    //       hidden: true
    //     },
    //     {
    //       path: '/attendanceManageApproval',
    //       component: () => import('@/views/driver-manage/attendanceManageApproval'),
    //       name: 'attendanceManageApproval',
    //       meta: { title: '审批' },
    //       hidden: true
    //     },
    //     {
    //       path: '/attendanceManageCheck',
    //       component: () => import('@/views/driver-manage/attendanceManageCheck'),
    //       name: 'attendanceManageCheck',
    //       meta: { title: '查看' },
    //       hidden: true
    //     },
    //     {
    //       path: '/attendanceStatistics',
    //       component: (resolve) => require(['@/views/driver-manage/attendanceStatistics'], resolve),
    //       name: 'attendanceStatistics',
    //       meta: { title: '考勤统计', icon: 'table' }
    //     },
    //     {
    //       path: '/attendanceStatisticsCheck/',
    //       component: (resolve) => require(['@/views/driver-manage/attendanceStatisticsCheck'], resolve),
    //       hidden: true,
    //       name: 'attendanceStatisticsCheck',
    //       meta: { title: '考勤统计查看', icon: 'table' }
    //     }
    //   ]
    // },
    // {
    //   path: '/vehicle',
    //   component: Layout,
    //   hidden: true,
    //   meta: { title: '车辆管理', icon: 'table' },

    //   children: [
    //     {
    //       path: '/vehicleFiles',
    //       component: (resolve) => require(['@/views/vehicleManage/vehicleFiles'], resolve),
    //       name: 'vehicleFiles',
    //       meta: { title: '车辆档案', icon: 'table' }
    //     }, {
    //       path: '/vehicleFilesAdd',
    //       component: (resolve) => require(['@/views/vehicleManage/vehicleFilesChild/vehicleFilesAdd'], resolve),
    //       name: 'vehicleFilesAdd',
    //       meta: { title: '车辆档案新增' },
    //       hidden: true
    //     }, {
    //       path: '/vehicleFilesEdit/:id',
    //       component: (resolve) => require(['@/views/vehicleManage/vehicleFilesChild/vehicleFilesEdit'], resolve),
    //       name: 'vehicleFilesEdit',
    //       meta: { title: '车辆档案修改' },
    //       hidden: true
    //     }, {
    //       path: '/vehicleFilesDetail/:id',
    //       component: (resolve) => require(['@/views/vehicleManage/vehicleFilesChild/vehicleFilesDetail'], resolve),
    //       name: 'vehicleFilesDetail',
    //       meta: { title: '车辆档案详情' },
    //       hidden: true
    //     }, {
    //       path: '/yearlyInspection',
    //       component: (resolve) => require(['@/views/vehicleManage/vehicleFilesChild/yearlyInspection'], resolve),
    //       name: 'yearlyInspection',
    //       meta: { title: '年辆年检记录', icon: 'table' },
    //     }, {
    //       path: '/lookYearlyInspection/:id',
    //       component: (resolve) => require(['@/views/vehicleManage/vehicleFilesChild/lookYearlyInspection'], resolve),
    //       name: 'lookYearlyInspection',
    //       meta: { title: '年检记录', icon: 'table' },
    //       hidden: true
    //     }, {
    //       path: '/stateManagement',
    //       component: (resolve) => require(['@/views/vehicleManage/vehicleFilesChild/stateManagement'], resolve),
    //       name: 'stateManagement',
    //       meta: { title: '年辆状态管理', icon: 'table' },
    //     },
    //   ]
    // },
    // {
    //   path: '/fuelManagement',
    //   component: Layout,
    //   meta: { title: '燃料管理', icon: 'table' },

    //   children: [{
    //     path: '/oilCardManagement',
    //     component: (resolve) => require(['@/views/fuelManagement/oilCardManagement/oilCardManagement'], resolve),
    //     name: 'oilCardManagement',
    //     meta: { title: '油卡管理', icon: 'table' }
    //   },
    //   {
    //     path: '/fuelration',
    //     component: (resolve) => require(['@/views/fuelManagement/oilQuotaManagement/fuelration'], resolve),
    //     name: 'fuelration',
    //     meta: { title: '油料定额管理', icon: 'table' }
    //   },
    //   {
    //     path: '/fuelPoint',
    //     component: (resolve) => require(['@/views/fuelManagement/fuelPointChild/fuelPoint'], resolve),
    //     name: 'fuelPoint',
    //     meta: { title: '燃料定点管理', icon: 'table' }
    //   },
    //   {
    //     path: '/see/:id',
    //     component: (resolve) => require(['@/views/fuelManagement/fuelPointChild/see'], resolve),
    //     hidden: true,
    //     name: 'see',
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   {
    //     path: '/FuelUsageRecord',
    //     component: (resolve) => require(['@/views/fuelManagement/FuelUsageRecord/fuelrecord'], resolve),
    //     name: 'FuelUsageRecord',
    //     meta: { title: '油料使用记录', icon: 'table' }
    //   },
    //   {
    //     path: '/exceptionRecord',
    //     component: (resolve) => require(['@/views/fuelManagement/FuelUsageRecord/exceptionRecord'], resolve),
    //     hidden: true,
    //     name: 'exceptionRecord',
    //     meta: { title: '异常记录', icon: 'table' }
    //   },
    //   {
    //     path: '/fuelUsePlan',
    //     component: (resolve) => require(['@/views/fuelManagement/fuelUsePlan/fuelUsePlan'], resolve),
    //     name: 'fuelUsePlan',
    //     meta: { title: '燃料使用计划', icon: 'table' }
    //   },
    //   {
    //     path: '/checkYourFuelUsagePlan/:id',
    //     component: (resolve) => require(['@/views/fuelManagement/fuelUsePlan/checkYourFuelUsagePlan'], resolve),
    //     hidden: true,
    //     name: 'checkYourFuelUsagePlan',
    //     meta: { title: '查看燃料使用计划', icon: 'table' }
    //   },
    //   {
    //     path: '/editFuelUsagePlan/',
    //     hidden: true,
    //     component: (resolve) => require(['@/views/fuelManagement/fuelUsePlan/editFuelUsagePlan'], resolve),
    //     name: 'editFuelUsagePlan',
    //     meta: { title: '编辑燃料使用计划', icon: 'table' }
    //   },
    //   {
    //     path: '/chargeableBattery/:id',
    //     component: (resolve) => require(['@/views/fuelManagement/chargeableBattery/chargeableBattery'], resolve),
    //     name: 'chargeableBattery',
    //     meta: { title: '充电卡管理', icon: 'table' }
    //   },
    //   {
    //     path: '/recordOfcharging',
    //     component: (resolve) => require(['@/views/fuelManagement/recordOfcharging/recordofcharging'], resolve),
    //     name: 'recordOfcharging',
    //     meta: { title: '充电记录', icon: 'table' }
    //   },
    //   ]
    // },
    // {
    //   path: '/Insurance',
    //   component: Layout,
    //   hidden: true,
    //   meta: { title: '保险管理', icon: 'table' },

    //   children: [
    //     {
    //       path: '/claimManagement',
    //       component: (resolve) => require(['@/views/Insurance/claimManagement'], resolve),
    //       name: 'claimManagement',
    //       meta: { title: '理赔管理', icon: 'table' }
    //     },
    //     {
    //       path: '/car',
    //       component: (resolve) => require(['@/views/Insurance/car'], resolve),
    //       name: 'car',
    //       meta: { title: '车辆保险', icon: 'table' }
    //     },
    //     {
    //       path: '/goods',
    //       component: (resolve) => require(['@/views/Insurance/goods'], resolve),
    //       name: 'goods',
    //       meta: { title: '货物保险', icon: 'table' }
    //     },
    //     {
    //       path: '/insuranceAccount',
    //       component: (resolve) => require(['@/views/Insurance/insuranceAccount'], resolve),
    //       name: 'insuranceAccount',
    //       meta: { title: '保险台账管理', icon: 'table' }
    //     },
    //     {
    //       path: '/coverageManage',
    //       component: (resolve) => require(['@/views/Insurance/coverageManage'], resolve),
    //       name: 'coverageManage',
    //       meta: { title: '险种管理', icon: 'table' }
    //     }, {
    //       path: '/coverageManageDetail/:id',
    //       component: (resolve) => require(['@/views/Insurance/coverageManageChild/coverageManageDetail'], resolve),
    //       name: 'coverageManageDetail',
    //       hidden: true,

    //       meta: { title: '险种', icon: 'table' }
    //     },
    //     {
    //       path: '/addInsurance/',
    //       component: (resolve) => require(['@/views/Insurance/InsuranceChild/addInsurance'], resolve),
    //       hidden: true,
    //       name: 'addInsurance',
    //       meta: { title: '新增车辆保险', icon: 'table' }
    //     }, {
    //       path: '/editInsurance/:id',
    //       component: (resolve) => require(['@/views/Insurance/InsuranceChild/editInsurance'], resolve),
    //       hidden: true,
    //       name: 'editInsurance',
    //       meta: { title: '审批', icon: 'table' }
    //     }, {
    //       path: '/seeInsurance/:id',
    //       component: (resolve) => require(['@/views/Insurance/InsuranceChild/seeInsurance'], resolve),
    //       hidden: true,
    //       name: 'seeInsurance',
    //       meta: { title: '查看', icon: 'table' }
    //     },
    //     {
    //       path: '/goodsAdd',
    //       component: (resolve) => require(['@/views/Insurance/goodsChild/goodsAdd'], resolve),
    //       hidden: true,
    //       name: 'goodsAdd',
    //       meta: { title: '新增', icon: 'table' }
    //     },
    //     {
    //       path: '/insuranceDetail/:id',
    //       component: (resolve) => require(['@/views/Insurance/goodsChild/insuranceDetail'], resolve),
    //       hidden: true,
    //       name: 'insuranceDetail',
    //       meta: { title: '查看', icon: 'table' }
    //     },
    //     {
    //       path: '/insuranceVehicle',
    //       component: (resolve) => require(['@/views/Insurance/goodsChild/insuranceVehicle'], resolve),
    //       hidden: true,
    //       name: 'insuranceVehicle',
    //       meta: { title: '选择保险车辆', icon: 'table' }
    //     },
    //     {
    //       path: '/coverage',
    //       component: (resolve) => require(['@/views/Insurance/goodsChild/coverage'], resolve),
    //       hidden: true,
    //       name: 'coverage',
    //       meta: { title: '选择险种', icon: 'table' }
    //     },
    //     {
    //       path: '/goodsEditInsurance/:id',
    //       component: (resolve) => require(['@/views/Insurance/goodsChild/editInsurance'], resolve),
    //       hidden: true,
    //       name: 'goodsEditInsurance',
    //       meta: { title: '审批', icon: 'table' }
    //     },
    //     {
    //       path: '/seeInsuranceAccount/:id',
    //       component: (resolve) => require(['@/views/Insurance/insuranceAccountChild/seeInsuranceAccount'], resolve),
    //       hidden: true,
    //       name: 'seeInsuranceAccount',
    //       meta: { title: '查看', icon: 'table' }
    //     },
    //     {
    //       path: '/editInsuranceAccount/:id',
    //       component: (resolve) => require(['@/views/Insurance/insuranceAccountChild/editInsuranceAccount'], resolve),
    //       hidden: true,
    //       name: 'editInsuranceAccount',
    //       meta: { title: '查看', icon: 'table' }
    //     },
    //     {
    //       path: '/detailInsuranceAccount/:id',
    //       component: (resolve) => require(['@/views/Insurance/insuranceAccountChild/detailInsuranceAccount'], resolve),
    //       hidden: true,
    //       name: 'detailInsuranceAccount',
    //       meta: { title: '明细(修改)', icon: 'table' }
    //     },
    //   ]
    // },
    // {
    //   path: '/partner',
    //   component: Layout,
    //   hidden: true,
    //   meta: { title: '合作方管理', icon: 'table' },

    //   children: [{
    //     path: '/customerContract',
    //     component: (resolve) => require(['@/views/partner/customerContract'], resolve),
    //     name: 'customerContract',
    //     meta: { title: '客户合同', icon: 'table' }
    //   },
    //   {
    //     path: '/supplier',
    //     component: (resolve) => require(['@/views/partner/supplier'], resolve),
    //     name: 'supplier',
    //     meta: { title: '供应商合同', icon: 'table' }
    //   },
    //   {
    //     path: '/supplierEvaluation',
    //     component: (resolve) => require(['@/views/partner/supplierEvaluation'], resolve),
    //     name: 'supplierEvaluation',
    //     meta: { title: '供应商评价', icon: 'table' }
    //   },
    //   {
    //     path: '/supplierList',
    //     component: (resolve) => require(['@/views/partner/supplierList'], resolve),
    //     name: 'supplierList',
    //     meta: { title: '供应商管理', icon: 'table' }
    //   },
    //   {
    //     path: '/evaluationType',
    //     component: (resolve) => require(['@/views/partner/evaluationType'], resolve),
    //     name: 'evaluationType',
    //     meta: { title: '评价指标管理', icon: 'table' }
    //   },
    //   {
    //     path: '/addCustomerContract',//客户合同新增
    //     component: (resolve) => require(['@/views/partner/customerContractChild/addCustomerContract'], resolve),
    //     hidden: true,
    //     name: 'addCustomerContract',
    //     meta: { title: '客户合同新增', icon: 'table' }
    //   },
    //   {
    //     path: '/addSupplier',//供应商
    //     component: (resolve) => require(['@/views/partner/supplierChild/addSupplier'], resolve),
    //     hidden: true,
    //     name: 'addSupplier',
    //     meta: { title: '供应商新增合同', icon: 'table' }
    //   },
    //   {
    //     path: '/editSupplier/:id',
    //     component: (resolve) => require(['@/views/partner/supplierChild/editSupplier'], resolve),
    //     hidden: true,
    //     name: 'editSupplier',
    //     meta: { title: '供应商修改合同', icon: 'table' }
    //   },
    //   {
    //     path: '/seeSupplier/:id',
    //     component: (resolve) => require(['@/views/partner/supplierChild/seeSupplier'], resolve),
    //     hidden: true,
    //     name: 'seeSupplier',
    //     meta: { title: '供应商查看合同', icon: 'table' }
    //   },
    //   {
    //     path: '/editCustomerContract/:id',
    //     component: (resolve) => require(['@/views/partner/customerContractChild/editCustomerContract'], resolve),
    //     hidden: true,
    //     name: 'editCustomerContract',
    //     meta: { title: '客户合同编辑', icon: 'table' }
    //   },
    //   {
    //     path: '/detailCustomerContract/:id',
    //     component: (resolve) => require(['@/views/partner/customerContractChild/detailCustomerContract'], resolve),
    //     hidden: true,
    //     name: 'detailCustomerContract',
    //     meta: { title: '客户合同查看', icon: 'table' }
    //   },
    //   {
    //     path: '/evaluatingIndicator/:id',
    //     component: (resolve) => require(['@/views/partner/evaluationTypeChild/evaluatingIndicator'], resolve),
    //     hidden: true,
    //     name: 'evaluatingIndicator',
    //     meta: { title: '评价指标', icon: 'table' }
    //   },
    //   {
    //     path: '/newComments/:id',
    //     component: (resolve) => require(['@/views/partner/supplierEvaluationChild/newComments'], resolve),
    //     hidden: true,
    //     name: 'newComments',
    //     meta: { title: '编辑评价', icon: 'table' }
    //   },
    //   {
    //     path: '/detailComments/:id',
    //     component: (resolve) => require(['@/views/partner/supplierEvaluationChild/detailComments'], resolve),
    //     hidden: true,
    //     name: 'detailComments',
    //     meta: { title: '查看评价', icon: 'table' }
    //   },
    //   ]
    // },
    // {
    //   path: '/etc',
    //   component: Layout,
    //   hidden: false,
    //   meta: { title: 'ETC管理', icon: 'table' },

    //   children: [{
    //     path: '/ectCardList',
    //     component: (resolve) => require(['@/views/etc/etcCard'], resolve),
    //     name: 'ectCardList',
    //     meta: { title: 'ETC管理', icon: 'table' }
    //   },
    //   {
    //     path: '/etcQuotaList',
    //     component: (resolve) => require(['@/views/etc/etcQuotaList'], resolve),
    //     name: 'etcQuotaList',
    //     meta: { title: 'ETC定额', icon: 'table' }
    //   },
    //   {
    //     path: '/addCard',
    //     component: (resolve) => require(['@/views/etc/addEtcCard'], resolve),
    //     hidden: true,
    //     name: 'addCard',
    //     meta: { title: '新增ETC', icon: 'table' }
    //   },
    //   {
    //     path: '/editEtcCard',
    //     component: (resolve) => require(['@/views/etc/editEtcCard'], resolve),
    //     hidden: true,
    //     name: 'editEtcCard',
    //     meta: { title: '编辑ETC', icon: 'table' }
    //   },
    //   {
    //     path: '/etcRecord',
    //     component: (resolve) => require(['@/views/etc/etcRecord'], resolve),
    //     hidden: false,
    //     name: 'etcRecord',
    //     meta: { title: 'ETC使用记录', icon: 'table' }
    //   },
    //   ]
    // },
    // {
    //   path: '/maintenance',
    //   component: Layout,
    //   hidden: true,
    //   meta: { title: '维修保养管理', icon: 'table' },
    //   children: [
    //     {
    //       path: '/maintenanceAccount',
    //     component: (resolve) => require(['@/views/maintenance/maintenanceAccount'], resolve),
    //     name: 'maintenanceAccount',
    //     meta: { title: '维修保养台账', icon: 'table' }
    //     },
    //     {
    //       path: '/maintenanceAccountEdit/:id',
    //     component: (resolve) => require(['@/views/maintenance/maintenanceAccountChild/maintenanceAccountEdit'], resolve),
    //     name: 'maintenanceAccountEdit',
    //     meta: { title: '编辑', icon: 'table' }
    //     },
    //     {
    //       path: '/maintenanceAccountDetail/:id',
    //     component: (resolve) => require(['@/views/maintenance/maintenanceAccountChild/maintenanceAccountDetail'], resolve),
    //     name: 'maintenanceAccountDetail',
    //     meta: { title: '维修保养台账', icon: 'table' }
    //     }
    // {
    //     path: '/maintenanceDriver',
    //     component: (resolve) => require(['@/views/maintenance/maintenanceDriver'], resolve),
    //     name: 'maintenanceDriver',
    //     meta: { title: '驾驶员保养', icon: 'table' }
    //   },
    //   {
    //     path: '/serviceStation',
    //     component: (resolve) => require(['@/views/maintenance/serviceStation'], resolve),
    //     name: 'serviceStation',
    //     meta: { title: '维修服务站申请审批', icon: 'table' }
    //   },
    //   {
    //     path: '/repairOrder',
    //     component: (resolve) => require(['@/views/maintenance/repairOrder'], resolve),
    //     name: 'repairOrder',
    //     meta: { title: '维修服务站申请', icon: 'table' }
    //   },
    //   {
    //     path: '/maintenanceManage',
    //     component: (resolve) => require(['@/views/maintenance/maintenanceManage'], resolve),
    //     name: 'maintenanceManage',
    //     meta: { title: '维修保养项管理', icon: 'table' }
    //   },
    //   {
    //     path: '/servicStation',
    //     component: (resolve) => require(['@/views/maintenance/servicStation'], resolve),
    //     name: 'servicStation',
    //     meta: { title: '维修服务站管理', icon: 'table' }
    //   },
    //   {
    //     path: '/carRegularInspection',
    //     component: (resolve) => require(['@/views/maintenance/carRegularInspection'], resolve),
    //     name: 'carRegularInspection',
    //     meta: { title: '车辆定检管理', icon: 'table' }
    //   },
    //   {
    //     path: '/inspectionRecord',
    //     component: (resolve) => require(['@/views/maintenance/inspectionRecord'], resolve),
    //     name: 'inspectionRecord',
    //     meta: { title: '车辆定检记录', icon: 'table' }
    //   },
    //   {
    //     path: '/approval/:id',
    //     component: (resolve) => require(['@/views/maintenance/maintenanceDriverChild/approval'], resolve),
    //     name: 'approval',
    //     hidden: true,
    //     meta: { title: '审批', icon: 'table' }
    //   },
    //   {
    //     path: '/lookUp/:id',
    //     component: (resolve) => require(['@/views/maintenance/maintenanceDriverChild/lookUp'], resolve),
    //     name: 'lookUp',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   {
    //     path: '/applyMaintenance',
    //     component: (resolve) => require(['@/views/maintenance/serviceStationChild/applyMaintenance'], resolve),
    //     name: 'applyMaintenance',
    //     hidden: true,
    //     meta: { title: '新增维修明细记录', icon: 'table' }
    //   },
    //   {
    //     path: '/approvalServiceStation/:id',
    //     component: (resolve) => require(['@/views/maintenance/serviceStationChild/approvalServiceStation'], resolve),
    //     name: 'approvalServiceStation',
    //     hidden: true,
    //     meta: { title: '审批', icon: 'table' }
    //   },
    //   {
    //     path: '/seeServiceStation/:id',
    //     component: (resolve) => require(['@/views/maintenance/serviceStationChild/seeServiceStation'], resolve),
    //     name: 'seeServiceStation',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   {
    //     path: '/subRepairOrder/:id',
    //     component: (resolve) => require(['@/views/maintenance/repairOrderChild/subRepairOrder'], resolve),
    //     name: 'subRepairOrder',
    //     hidden: true,
    //     meta: { title: '审批', icon: 'table' }
    //   },
    //   {
    //     path: '/seeRepairOrder/:id',
    //     component: (resolve) => require(['@/views/maintenance/repairOrderChild/seeRepairOrder'], resolve),
    //     name: 'seeRepairOrder',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   {
    //     path: '/detailAServicStation/:id',
    //     component: (resolve) => require(['@/views/maintenance/servicStationChild/detailAServicStation'], resolve),
    //     name: 'detailAServicStation',
    //     hidden: true,
    //     meta: { title: '维修明细', icon: 'table' }
    //   },
    //   {
    //     path: '/editCarRegularInspection/:id',
    //     component: (resolve) => require(['@/views/maintenance/carRegularInspectionChild/editCarRegularInspection'], resolve),
    //     name: 'editCarRegularInspection',
    //     hidden: true,
    //     meta: { title: '审批', icon: 'table' }
    //   },
    //   {
    //     path: '/seeCarRegularInspection/:id',
    //     component: (resolve) => require(['@/views/maintenance/carRegularInspectionChild/seeCarRegularInspection'], resolve),
    //     name: 'seeCarRegularInspection',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   {
    //     path: '/detailInspectionRecord/:id',
    //     component: (resolve) => require(['@/views/maintenance/inspectionRecordChild/detailInspectionRecord'], resolve),
    //     name: 'detailInspectionRecord',
    //     hidden: true,
    //     meta: { title: '车辆详情', icon: 'table' }
    //   },
    //   {
    //     path: '/seeInspectionRecord/:id',
    //     component: (resolve) => require(['@/views/maintenance/inspectionRecordChild/seeInspectionRecord'], resolve),
    //     name: 'seeInspectionRecord',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   ],
    // },
    // {
    //   path: '/salaryAdministration',
    //   component: Layout,
    //   meta: { title: '薪酬管理', icon: 'table' },
    //   children: [
    //     {
    //       path: '/subsidyManagementex',
    //       component: (resolve) => require(['@/views/salaryAdministration/subsidyManagement/subsidyManagementex'], resolve),
    //       name: 'subsidyManagementex',
    //       meta: { title: '补助管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/examineAndApprove/:id',
    //       hidden: true,
    //       component: (resolve) => require(['@/views/salaryAdministration/subsidyManagement/examineAndApprove'], resolve),
    //       name: 'examineAndApprove',
    //       meta: { title: '审批', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/subsidySee/:id',
    //       hidden: true,
    //       component: (resolve) => require(['@/views/salaryAdministration/subsidyManagement/subsidySee'], resolve),
    //       name: 'subsidySee',
    //       meta: { title: '补助查看', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/ancillarySstimator/',
    //       component: (resolve) => require(['@/views/salaryAdministration/ancillarySstimator'], resolve),
    //       name: 'ancillarySstimator',
    //       meta: { title: '补助统计', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/salary/',
    //       component: (resolve) => require(['@/views/salaryAdministration/salary/salary'], resolve),
    //       name: 'salary',
    //       meta: { title: '薪资管理', icon: 'dashboard' }
    //     },
    // {
    //   path: '/payrollAccounting/',
    //   component: (resolve) => require(['@/views/salaryAdministration/salary/payrollAccounting'], resolve),
    //   hidden: true,
    //   name: 'payrollAccounting',
    //   meta: { title: '工资核算', icon: 'dashboard' }
    // },
    //     {
    //       path: '/approvalOfSalaryDetails/',
    //       component: (resolve) => require(['@/views/salaryAdministration/salary/approvalOfSalaryDetails'], resolve),
    //       hidden: true,
    //       name: 'approvalOfSalaryDetails',
    //       meta: { title: '工资明细审批', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/detailsReviewed/',
    //       hidden: true,
    //       component: (resolve) => require(['@/views/salaryAdministration/salary/detailsReviewed'], resolve),
    //       name: 'detailsReviewed',
    //       meta: { title: '薪资待审核明细', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/approvedDetails/',
    //       hidden: true,
    //       component: (resolve) => require(['@/views/salaryAdministration/salary/approvedDetails'], resolve),
    //       name: 'approvedDetails',
    //       meta: { title: '薪资已通过明细', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/rejectedDetails/',
    //       hidden: true,
    //       component: (resolve) => require(['@/views/salaryAdministration/salary/rejectedDetails'], resolve),
    //       name: 'rejectedDetails',
    //       meta: { title: '薪资已拒绝明细', icon: 'dashboard' }
    //     },

    //   ]
    // },
    // //费用统计
    // {
    //   path: '/calculateExpense/',
    //   component: Layout,
    //   meta: { title: '费用统计', icon: 'table' },
    //   children: [
    //     {
    //       path: '/maintenanceCost/',
    //       component: (resolve) => require(['@/views/calculateExpense/maintenanceCost'], resolve),
    //       name: 'maintenanceCost',
    //       meta: { title: '维修保养费用', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/insuranceExpenses/',
    //       component: (resolve) => require(['@/views/calculateExpense/insuranceExpenses'], resolve),
    //       name: 'insuranceExpenses/',
    //       meta: { title: '保险费', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/fuelCos/',
    //       component: (resolve) => require(['@/views/calculateExpense/fuelCos'], resolve),
    //       name: 'fuelCos',
    //       meta: { title: '燃料费', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/ETCcost/',
    //       component: (resolve) => require(['@/views/calculateExpense/ETCcost'], resolve),
    //       name: 'ETCcost',
    //       meta: { title: 'ETC费', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/checkThefare/',
    //       component: (resolve) => require(['@/views/calculateExpense/checkThefare'], resolve),
    //       name: 'checkThefare',
    //       meta: { title: '检车费', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/ depreciationExpense/',
    //       component: (resolve) => require(['@/views/calculateExpense/depreciationExpense'], resolve),
    //       name: 'depreciationExpense',
    //       meta: { title: '折旧费用', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/costOfurea/',
    //       component: (resolve) => require(['@/views/calculateExpense/costOfurea'], resolve),
    //       name: 'costOfurea',
    //       meta: { title: '尿素费用', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/tireCostea/',
    //       component: (resolve) => require(['@/views/calculateExpense/tireCostea'], resolve),
    //       name: 'tireCostea',
    //       meta: { title: '轮胎费用', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/informationSystemServicefee/',
    //       component: (resolve) => require(['@/views/calculateExpense/informationSystemServicefee'], resolve),
    //       name: 'informationSystemServicefee',
    //       meta: { title: '信息系统服务费', icon: 'dashboard' }
    //     },
    //   ]
    // },
    // {
    //   path: '/stock',
    //   component: Layout,
    //   hidden: true,
    //   meta: { title: '库存管理', icon: 'table' },

    //   children: [{
    //     path: '/category',
    //     component: (resolve) => require(['@/views/stock/category'], resolve),
    //     name: 'Category',
    //     meta: { title: '物品类别管理', icon: 'table' }
    //   },
    //   {
    //     path: '/stockGoods',
    //     component: (resolve) => require(['@/views/stock/stockGoods'], resolve),
    //     name: 'StockGoods',
    //     meta: { title: '物品管理', icon: 'table' }
    //   },
    //   {
    //     path: '/warehousing',
    //     component: (resolve) => require(['@/views/stock/warehousing'], resolve),
    //     name: 'Warehousing',
    //     meta: { title: '入库管理', icon: 'table' }
    //   },
    //   {
    //     path: '/delivery',
    //     component: (resolve) => require(['@/views/stock/delivery'], resolve),
    //     name: 'Delivery',
    //     meta: { title: '出库管理', icon: 'table' }
    //   },
    //   {
    //     path: '/stockManage',
    //     component: (resolve) => require(['@/views/stock/stockManage'], resolve),
    //     name: 'StockManage',
    //     meta: { title: '库存管理', icon: 'table' }
    //   },
    //   {
    //     path: '/scrapManagement',
    //     component: (resolve) => require(['@/views/stock/scrapManagement'], resolve),
    //     name: 'scrapManagement',
    //     meta: { title: '报废管理', icon: 'table' }
    //   },
    //   {
    //     path: '/inventoryManagement',
    //     component: (resolve) => require(['@/views/stock/inventoryManagement'], resolve),
    //     name: 'inventoryManagement',
    //     meta: { title: '盘点管理', icon: 'table' }
    //   },
    //   {
    //     path: '/collectionManagement',
    //     component: (resolve) => require(['@/views/stock/collectionManagement'], resolve),
    //     name: 'CollectionManagement',
    //     meta: { title: '领用管理', icon: 'table' }
    //   },
    //   {
    //     path: '/addWarehousing',
    //     component: (resolve) => require(['@/views/stock/warehousingChild/addWarehousing'], resolve),
    //     name: 'AddWarehousing',
    //     hidden: true,
    //     meta: { title: '新增', icon: 'table' }
    //   }, {
    //     path: '/editWarehousing/:id',
    //     component: (resolve) => require(['@/views/stock/warehousingChild/editWarehousing'], resolve),
    //     name: 'EditWarehousing',
    //     hidden: true,
    //     meta: { title: '修改', icon: 'table' }
    //   },
    //   {
    //     path: '/addDelivery',
    //     component: (resolve) => require(['@/views/stock/deliverChild/addDelivery'], resolve),
    //     name: 'AddDelivery',
    //     hidden: true,
    //     meta: { title: '新增', icon: 'table' }
    //   },
    //   {
    //     path: '/editDelivery/:id',
    //     component: (resolve) => require(['@/views/stock/deliverChild/editDelivery'], resolve),
    //     name: 'EditDelivery',
    //     hidden: true,
    //     meta: { title: '修改', icon: 'table' }
    //   },
    //   {
    //     path: '/seeDelivery/:id',
    //     component: (resolve) => require(['@/views/stock/deliverChild/seeDelivery'], resolve),
    //     name: 'SeeDelivery',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   {
    //     path: '/detailStockManage/:id',
    //     component: (resolve) => require(['@/views/stock/stockManageChild/detailStockManage'], resolve),
    //     name: 'DetailStockManage',
    //     hidden: true,
    //     meta: { title: '库存明细', icon: 'table' }
    //   },
    //   {
    //     path: '/addScrapManagement',
    //     component: (resolve) => require(['@/views/stock/scrapManagementChild/addScrapManagement'], resolve),
    //     name: 'AddScrapManagement',
    //     hidden: true,
    //     meta: { title: '新增', icon: 'table' }
    //   },
    //   {
    //     path: '/editScrapManagement/:id',
    //     component: (resolve) => require(['@/views/stock/scrapManagementChild/editScrapManagement'], resolve),
    //     name: 'EditScrapManagement',
    //     hidden: true,
    //     meta: { title: '修改', icon: 'table' }
    //   },
    //   {
    //     path: '/addInventoryManagement/:id',
    //     component: (resolve) => require(['@/views/stock/inventoryManagementChild/addInventoryManagement'], resolve),
    //     name: 'AddInventoryManagement',
    //     hidden: true,
    //     meta: { title: '新增', icon: 'table' }
    //   },
    //   {
    //     path: '/seeInventoryManagement/:id',
    //     component: (resolve) => require(['@/views/stock/inventoryManagementChild/seeInventoryManagement'], resolve),
    //     name: 'SeeInventoryManagement',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   {
    //     path: '/editCollectionManagement/:id',
    //     component: (resolve) => require(['@/views/stock/collectionManagementChild/editCollectionManagement'], resolve),
    //     name: 'EditCollectionManagement',
    //     hidden: true,
    //     meta: { title: '审批', icon: 'table' }
    //   },
    //   {
    //     path: '/seeCollectionManagement/:id',
    //     component: (resolve) => require(['@/views/stock/collectionManagementChild/seeCollectionManagement'], resolve),
    //     name: 'SeeCollectionManagement',
    //     hidden: true,
    //     meta: { title: '查看', icon: 'table' }
    //   },
    //   ]
    // },

    // //任务管理
    // {
    //   path: '/taskManagement',
    //   component: Layout,
    //   meta: { title: '任务管理', icon: 'table' },
    //   children: [
    //     {
    //       path: '/lineManagement',
    //       component: (resolve) => require(['@/views/taskManagement/lineManagement'], resolve),
    //       name: 'LineManagement',
    //       meta: { title: '线路管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/tasksManagement',
    //       component: (resolve) => require(['@/views/taskManagement/tasksManagement'], resolve),
    //       name: 'TasksManagement',
    //       meta: { title: '任务管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/overtimeManagement',
    //       component: (resolve) => require(['@/views/taskManagement/overtimeManagement'], resolve),
    //       name: 'OvertimeManagement',
    //       meta: { title: '加班管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/abnormalSituation',
    //       component: (resolve) => require(['@/views/taskManagement/abnormalSituation'], resolve),
    //       name: 'AbnormalSituation',
    //       meta: { title: '任务异常状况管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/spotCheckManagement',
    //       component: (resolve) => require(['@/views/taskManagement/spotCheckManagement'], resolve),
    //       name: 'SpotCheckManagement',
    //       meta: { title: '点检管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/detailTasksManagement/:id',
    //       component: (resolve) => require(['@/views/taskManagement/tasksManagementChild/detailTasksManagement'], resolve),
    //       name: 'DetailTasksManagement',
    //       hidden: true,
    //       meta: { title: '任务详情', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/editLineManagement/:id',
    //       component: (resolve) => require(['@/views/taskManagement/lineManagementChild/editLineManagement'], resolve),
    //       name: 'EditLineManagement',
    //       hidden: true,
    //       meta: { title: '线路管理修改', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/detailSpotCheckManagement/:id',
    //       component: (resolve) => require(['@/views/taskManagement/spotCheckManagementChild/detailSpotCheckManagement'], resolve),
    //       name: 'DetailSpotCheckManagement',
    //       hidden: true,
    //       meta: { title: '点检详情', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/detailAbnormalSituation/:id',
    //       component: (resolve) => require(['@/views/taskManagement/abnormalSituationChild/detailAbnormalSituation'], resolve),
    //       name: 'detailAbnormalSituation',
    //       hidden: true,
    //       meta: { title: '任务异常审批', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/editAbnormalSituation/:id',
    //       component: (resolve) => require(['@/views/taskManagement/abnormalSituationChild/editAbnormalSituation'], resolve),
    //       name: 'editAbnormalSituation',
    //       hidden: true,
    //       meta: { title: '任务异常查看', icon: 'dashboard' }
    //     },
    //   ]
    // },
    //   //数据统计
    // {
    //   path: '/dataStatistics',
    //   component: Layout,
    //   meta: { title: '数据统计', icon: 'table' },
    //   children: [
    //     {
    //       path: '/information',
    //       component: (resolve) => require(['@/views/dataStatistics/information'], resolve),
    //       name: 'Information',
    //       meta: { title: '数据统计', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/costAccounting',
    //       component: (resolve) => require(['@/views/dataStatistics/costAccounting'], resolve),
    //       name: 'costAccounting',
    //       meta: { title: '任务成本核算', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/costCalculation',
    //       component: (resolve) => require(['@/views/dataStatistics/costCalculation'], resolve),
    //       name: 'costCalculation',
    //       meta: { title: '承运商成本核算', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/detailInformation/:id',
    //       component: (resolve) => require(['@/views/dataStatistics/informationChild/detailInformation'], resolve),
    //       name: 'DetailInformation',
    //       hidden: true,
    //       meta: { title: '详情', icon: 'dashboard' }
    //     },

    //   ]
    // },
    // //安全管理
    // {
    //   path: '/safetyManagement',
    //   component: Layout,
    //   meta: { title: '安全管理', icon: 'table' },
    //   children: [
    //     {
    //       path: '/trafficAccidentManagement',
    //       component: (resolve) => require(['@/views/safetyManagement/trafficAccidentManagement/trafficAccidentManagement'], resolve),
    //       name: 'trafficAccidentManagement',
    //       meta: { title: '交通事故管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/trafficAccidentManagementExaminationAndApproval/:id',
    //       component: (resolve) => require(['@/views/safetyManagement/trafficAccidentManagement/trafficAccidentManagementExaminationAndApproval'], resolve),
    //       name: 'trafficAccidentManagementExaminationAndApproval',
    //       hidden: true,
    //       meta: { title: '交通事故管理审批', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/trafficAccidentManagementView/:id',
    //       component: (resolve) => require(['@/views/safetyManagement/trafficAccidentManagement/trafficAccidentManagementView'], resolve),
    //       name: 'trafficAccidentManagementView',
    //       hidden: true,
    //       meta: { title: '交通事故管理查看', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/trafficPunishmentManagementSystem',
    //       component: (resolve) => require(['@/views/safetyManagement/trafficPunishmentManagementSystem/trafficPunishmentManagementSystem'], resolve),
    //       name: 'trafficPunishmentManagementSystem',
    //       meta: { title: '交通违章管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/newDetailsOfViolations',
    //       component: (resolve) => require(['@/views/safetyManagement/trafficPunishmentManagementSystem/newDetailsOfViolations'], resolve),
    //       name: 'newDetailsOfViolations',
    //       hidden: true,
    //       meta: { title: '新增违章详情', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/violationOfTheDetails/:id',
    //       component: (resolve) => require(['@/views/safetyManagement/trafficPunishmentManagementSystem/violationOfTheDetails'], resolve),
    //       hidden: true,
    //       name: 'violationOfTheDetails',
    //       meta: { title: '违章详情', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/editForViolationDetails/:id',
    //       component: (resolve) => require(['@/views/safetyManagement/trafficPunishmentManagementSystem/editForViolationDetails'], resolve),
    //       hidden: true,
    //       name: 'editForViolationDetails',
    //       meta: { title: '违章详情编辑', icon: 'dashboard' }
    //     },
    //  {
    //   path: '/didNotPay/:id',
    //   component: (resolve) => require(['@/views/safetyManagement/trafficPunishmentManagementSystem/didNotPay'], resolve),
    //   hidden: true,
    //   name: 'didNotPay',
    //   meta: { title: '未派发查看', icon: 'dashboard' }
    // },
    // {
    //   path: '/detailsOfViolationHaveBeenDealtWith/:id',
    //   component: (resolve) => require(['@/views/safetyManagement/trafficPunishmentManagementSystem/detailsOfViolationHaveBeenDealtWith'], resolve),
    //   hidden: true,
    //   name: 'detailsOfViolationHaveBeenDealtWith',
    //   meta: { title: '已处理查看', icon: 'dashboard' }
    // },
    //   ]
    // },

    // 绩效管理
    // {
    //   path: '/performanceManagement',
    //   component: Layout,
    //   meta: { title: '绩效管理', icon: 'table' },
    //   children: [
    //     {
    //       path: '/documentPerformance',
    //       component: (resolve) => require(['@/views/performanceManagement/performanceManagementlist/documentPerformance'], resolve),
    //       name: 'documentPerformance',
    //       meta: { title: '绩效管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/addLongDistancePerformance',
    //       component: (resolve) => require(['@/views/performanceManagement/performanceManagementlist/addLongDistancePerformance'], resolve),
    //       hidden:true,
    //       name: 'addLongDistancePerformance',
    //       meta: { title: '新增绩效管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/seeLongDistancePerformance/:id',
    //       component: (resolve) => require(['@/views/performanceManagement/performanceManagementlist/seeLongDistancePerformance'], resolve),
    //       hidden:true,
    //       name: 'seeLongDistancePerformance',
    //       meta: { title: '查看绩效管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/editorLongDistancePerformance/:id',
    //       component: (resolve) => require(['@/views/performanceManagement/performanceManagementlist/editorLongDistancePerformance'], resolve),
    //       hidden:true,
    //       name: 'editorLongDistancePerformance',
    //       meta: { title: '编辑绩效管理', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/documentPerformanceRecord',
    //       component: (resolve) => require(['@/views/performanceManagement/documentPerformanceRecord/documentPerformanceRecord'], resolve),
    //       name: 'documentPerformanceRecord',
    //       meta: { title: '绩效记录(长途)', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/ViewLongDistancePerformanceRecords/:id',
    //       component: (resolve) => require(['@/views/performanceManagement/documentPerformanceRecord/ViewLongDistancePerformanceRecords'], resolve),
    //       hidden:true,
    //       name: 'ViewLongDistancePerformanceRecords',
    //       meta: { title: '查看长途绩效', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/editorLongDistancePerformanceRecords/:id',
    //       component: (resolve) => require(['@/views/performanceManagement/documentPerformanceRecord/editorLongDistancePerformanceRecords'], resolve),
    //       hidden:true,
    //       name: 'editorLongDistancePerformanceRecords',
    //       meta: { title: '编辑长途绩效', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/overtimePerformanceRecord',
    //       component: (resolve) => require(['@/views/performanceManagement/overtimePerformanceRecord'], resolve),
    //       name: 'overtimePerformanceRecord',
    //       meta: { title: '绩效记录(加班)', icon: 'dashboard' }
    //     },
    //     {
    //       path: '/shortTermPerformanceRecord',
    //       component: (resolve) => require(['@/views/performanceManagement/shortTermPerformanceRecord'], resolve),
    //       name: 'shortTermPerformanceRecord',
    //       meta: { title: '绩效记录(短途)', icon: 'dashboard' }
    //     },
    //   ]
    // }
]

export default new Router({
    mode: 'history', // 去掉url中的#
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
})
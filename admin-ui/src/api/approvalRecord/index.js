import request from '@/utils/request'

// 管理员app-审批记录
export function taskQ01ExamineList(query) {
    return request({
        url: '/system/administrator-app/task/Q01ExamineList',
        method: 'post',
        data: query
    })
}
//考勤-请假详情
export function getAttendanceManageDetails(id) {
    return request({
        url: '/pfserver-person/vacation/A50ClockInInfo/' + id,
        method: 'get'
    })
}

// 补助-补助详情App
export function driverSubsidyF07GetInfo(params) {
    return request({
        url: '/pfserver-person/person/subsidy/F07GetInfo',
        method: 'get',
        params
    })
}
// 维修保养查看
export function repairInfo(id) {
    return request({
        url: '/pfserver-car/car/repair/H03Info/' + id,
        method: 'get',
    })
}

// 查看审核中的维修明细
export function repairDetailAllList(id) {
    return request({
        url: '/pfserver-car/car/repair/H10NotSubmittedList/' + id,
        method: 'get',
    })
}
// 查看审核中的维修明细
export function repairH10NotSubmittedIsNewList(id) {
    return request({
        url: '/pfserver-car/car/repair/H10NotSubmittedIsNewList/' + id,
        method: 'get',
    })
}
// 查看页面渲染
export function getRecruitmentPlanDetails(id) {
    return request({
        url: '/pfserver-person/person/dRecruitPlan/A08Info/' + id,
        method: 'get'
    })
}
// 交通事故-事故详情接口
export function L03SafeAccidentInfo(id) {
    return request({
        url: '/pfserver-car/accident/L03SafeAccidentInfo/' + id,
        method: 'get',

    })
}

// 物品领用-物品领用记录详情
export function T01AdminGGoodsReceivingInfo(data) {
    return request({
        url: '/system/administrator-app/goodsReceivingRecord/T01AdminGGoodsReceivingInfo',
        method: 'post',
        data
    })
}

// 获取任务管理-运输任务详细信息
export function transportTaskP04TransportTaskInfo(query) {
    return request({
        url: '/pfserver-car/transportTask/P04TransportTaskInfo',
        method: 'post',
        data: query
    })
}
// 获取任务管理-运输任务详细信息
export function P08TransportTaskExceptionInfo(query) {
    return request({
        url: '/pfserver-car/transportException/P08TransportTaskExceptionInfo',
        method: 'post',
        data: query
    })
}

// 薪酬
export function C06Detail(id) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C06Detail/' + id,
        method: 'get',
    })
}

// 保险管理-查看（已审批）
export function insuranceVehicleE02GetInfo(id) {
    return request({
        url: '/pfserver-car/car/insuranceVehicle/E02GetInfo/' + id,
        method: 'get',
    })
}
// 车辆定检管理-审批
export function vehicleInspectH20List(params) {
    return request({
        url: '/pfserver-car/car/vehicleInspect/H20List/' + params.id,
        method: 'get',

    })
}

//考勤-请假详情（区分四种状态）
export function vacationE02VacationDetail(query) {
    return request({
        url: '/pfserver-car/driver-api/vacation/E02VacationDetail',
        method: 'get',
        params: query
    })
}


// 个人消息-消息删除接口
export function R01SendDuplicateCount(query) {
    return request({
        url: '/system/administrator-app/task/R01SendDuplicateCount',
        method: 'post',
        data: {}
    })
}
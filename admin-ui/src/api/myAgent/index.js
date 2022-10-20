import request from '@/utils/request'

// 管理员app-抄送记录
export function P01ExamineBacklogListt(query) {
    return request({
        url: '/system/administrator-app/task/P01ExamineBacklogList',
        method: 'post',
        data: query
    })
}

// 请假审批
export function vacationA51ClockInCheck(query) {
    return request({
        url: '/pfserver-person/vacation/A51ClockInCheck',
        method: 'post',
        data: query
    })
}
// 补助审批
export function subsidyC02check(query) {
    return request({
        url: '/pfserver-person/person/subsidy/C02Check',
        method: 'post',
        data: query
    })
}



// 交通事故审批
export function accidentL02SafeAccidentCheck(query) {
    return request({
        url: '/pfserver-person/accident/L02SafeAccidentCheck',
        method: 'post',
        data: query
    })
}


// 招聘计划审批
export function dRecruitPlanA07Submmit(query) {
    return request({
        url: '/pfserver-person/person/dRecruitPlan/A07Submmit',
        method: 'post',
        data: query
    })
}

// 薪酬审批
export function salaryRecordsC08Review(query) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C08Review',
        method: 'post',
        data: query
    })
}

// 运输审批
export function P07TransportTaskExceptionCheck(query) {
    return request({
        url: '/pfserver-car/transportException/P07TransportTaskExceptionCheck',
        method: 'post',
        data: query
    })
}


// 车辆审批
export function insuranceVehicleE03Check(query) {
    return request({
        url: '/pfserver-person/car/insuranceVehicle/E03Check',
        method: 'post',
        data: query
    })
}
// 领用记录-审批
// export function goodsReceivingRecordK18EditSave(query) {
//   return request({
//     url: '/pfserver-person/parts/goodsReceivingRecord/K18EditSave',
//     method: 'post',
//     data: query
//   })
// }

// // 审批
export const goodsReceivingRecordK18EditSave = (data) => {
        return request({
            url: '/pfserver-parts/parts/goodsReceivingRecord/K18EditSave',
            method: 'post',
            data
        })
    }
    // 维修保养审核
export function H07StationCheck(query) {
    return request({
        url: '/pfserver-car/car/repair/H07StationCheck',
        method: 'post',
        data: query
    })
}
// 维修保养审批
export function repairH01Check(query) {
    return request({
        url: '/pfserver-car/car/repair/H01Check',
        method: 'post',
        data: query
    })
}

// 交通事故-交通事故审批
export function accidentL02SafeAccidentCheckD(data) {
    return request({
        url: '/pfserver-car/accident/L02SafeAccidentCheck',
        method: 'post',
        data
    })
}

// 招聘计划审核
export function A07check(data) {
    return request({
        url: '/pfserver-person/person/dRecruitPlan/A07check',
        method: 'post',
        data: data
    })
}

// 审批
export function insuranceVehicleListE03Check(query) {
    return request({
        url: '/pfserver-car/car/insuranceVehicle/E03Check',
        method: 'post',
        data: query
    })
}
// 维修站下拉
export function commonSelectBox(query) {
    return request({
        url: '/pfserver-car/commonSelectBox/H02RGarage',
        method: 'post',
        data: query
    })
}

export function vehicleInspectE20Review(data) {
    return request({
        url: '/pfserver-car/car/vehicleInspect/H20Review',
        method: 'post',
        data
    })
}

// 维修站下拉
export function commonSelectBoxList(query) {
    return request({
        url: '/pfserver-car/commonSelectBox/H02RGarageList',
        method: 'post',
        data: query
    })
}
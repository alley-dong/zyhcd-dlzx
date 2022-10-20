import request from '@/utils/request'

// 违章管理-车牌号下拉选
export function J02CarNumList(data) {
    return request({
        url: '/pfserver-car/commonSelectBox/J02CarNumList',
        method: 'post',
        data
    })
}

// 违章管理-通过的驾驶员下拉选
export function L06DriverList(data) {
    return request({
        url: '/pfserver-person/select/common/L06DriverList',
        method: 'post',
        data
    })
}
// 违章管理-新增违章
export function L06Add(data) {
    return request({
        url: '/pfserver-person/person/safeTrafficTransgression/L06Add',
        method: 'post',
        data
    })
}
// 违章管理-新增违章
export function L09Edit(data) {
    return request({
        url: '/pfserver-person/person/safeTrafficTransgression/L09Edit',
        method: 'post',
        data
    })
}

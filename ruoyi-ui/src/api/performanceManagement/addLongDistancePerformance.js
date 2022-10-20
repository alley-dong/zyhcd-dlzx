import request from '@/utils/request'

// 绩效管理-路线下拉选
export function J04RoutList(data) {
    return request({
        url: '/pfserver-person/select/common/J04RoutList',
        method: 'post',
        data
    })
}
// 车辆类型下拉选
export function G02CarList(data) {
    return request({
        url: '/pfserver-person/select/common/G02CarList',
        method: 'post',
        data
    })
}
// 绩效管理-新增绩效长途
export function G02AddChang(data) {
    return request({
        url: "/pfserver-person/person/performance/G02AddChang",
        method: "post",
        data
    })
}
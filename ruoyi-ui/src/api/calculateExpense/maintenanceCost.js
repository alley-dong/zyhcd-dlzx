import request from '@/utils/request'

// 维修保养费用列表
export function M01List(data) {
    return request({
        url: '/pfserver-car/repair/inspection/M01List',
        method: 'post',
        data
    })
}
// 维修服务站下拉选（公共）
export function H02RGarage(data) {
    return request({
        url: '/pfserver-car/commonSelectBox/H02RGarage',
        method: 'post',
        data
    })
}
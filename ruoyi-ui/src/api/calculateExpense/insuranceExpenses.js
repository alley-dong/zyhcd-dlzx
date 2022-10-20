import request from '@/utils/request'

// 维修保养费用列表
export function M05List(data) {
    return request({
        url: '/pfserver-car/car/insuranceVehicle/M05List',
        method: 'post',
        data
    })
}
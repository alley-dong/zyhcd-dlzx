import request from '@/utils/request'

// ETC费用列表
export function M07List(data) {
    return request({
        url: '/pfserver-car/car/etcCost/M07List',
        method: 'post',
        data
    })
}
// ETC费用导入
export function M07ImportData(data) {
    return request({
        url: '/pfserver-car/car/etcCost/M07ImportData',
        method: 'post',
        data
    })
}

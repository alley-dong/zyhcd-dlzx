import request from '@/utils/request'

// 检车费用列表 
export function M08List(data) {
    return request({
        url: '/pfserver-car/car/inspection/M08List',
        method: 'post',
        data
    })
}
import request from '@/utils/request'

// 检车费用列表 
export function M10List(data) {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/M10List',
        method: 'post',
        data
    })
}

// 检车费用列表 
export function M11Info(data) {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/M11Info',
        method: 'get',
    })
}
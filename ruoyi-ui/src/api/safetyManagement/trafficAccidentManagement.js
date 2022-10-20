import request from '@/utils/request'

// 交通事故-事故记录列表
export function L01SafeAccidentList(data) {
    return request({
        url: '/pfserver-car/accident/L01SafeAccidentList',
        method: 'post',
        data
    })
}

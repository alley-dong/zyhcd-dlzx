import request from '@/utils/request'

// 交通事故-事故详情接口
export function L03SafeAccidentInfo(id) {
    return request({
        url: '/pfserver-car/accident/L03SafeAccidentInfo/' + id,
        method: 'get',

    })
}
// 交通事故-交通事故审批
export function L02SafeAccidentCheck(data) {
    return request({
        url: '/pfserver-car/accident/L02SafeAccidentCheck',
        method: 'post',
        data
    })
}

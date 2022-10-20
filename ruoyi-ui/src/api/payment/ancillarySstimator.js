import request from '@/utils/request'
// 补助管理-补助统计及明细
export function C05List(data) {
    return request({
        url: '/pfserver-person/person/subsidy/C05List',
        method: 'post',
        data
    })
}

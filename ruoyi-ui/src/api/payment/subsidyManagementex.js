import request from '@/utils/request'

// 补助管理-补助列表
export function C01List(data) {
    return request({
        url: '/pfserver-person/person/subsidy/C01List',
        method: 'post',
        data: data
    })
}
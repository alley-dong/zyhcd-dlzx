import request from '@/utils/request'

// 补助管理-补助详情
export function C01GetInfo(id) {
    return request({
        url: '/pfserver-person/person/subsidy/C01GetInfo/' + id,
        method: 'get',
    })
}


// 补助管理-审核
export function C02check(data) {
    return request({
        url: '/pfserver-person/person/subsidy/C02Check',
        method: 'post',
        data
    })
}


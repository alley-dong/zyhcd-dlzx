import request from '@/utils/request'

// 违章管理-驳回处理
export function L10Change(id) {
    return request({
        url: '/pfserver-person/person/safeTrafficTransgression/L10Change/' + id,
        method: 'get',
    })
}
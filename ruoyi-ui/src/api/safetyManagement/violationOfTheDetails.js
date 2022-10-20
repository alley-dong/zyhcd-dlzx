import request from '@/utils/request'

// 违章管理-违章列表
export function L05GetInfo(id) {
    return request({
        url: '/pfserver-person/person/safeTrafficTransgression/L05GetInfo/' + id,
        method: 'get',
    })
}
import request from '@/utils/request'

// 绩效管理-编辑绩效长途
export function G04EditChang(data) {
    return request({
        url: '/pfserver-person/person/performance/G04EditChang',
        method: 'post',
        data
    })
}
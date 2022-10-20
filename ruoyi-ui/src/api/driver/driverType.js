import request from '@/utils/request'

// 绩效管理-驾驶员类型下拉选
export function A10DriverBox() {
    return request({
        url: '/pfserver-person/select/common/A10DriverBox',
        method: 'post',
    })
}

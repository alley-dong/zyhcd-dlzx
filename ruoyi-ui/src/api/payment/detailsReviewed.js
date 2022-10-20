import request from '@/utils/request'
// 撤销按钮
export function C09Revoke(id) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C09Revoke/' + id,
        method: 'get',

    })
}
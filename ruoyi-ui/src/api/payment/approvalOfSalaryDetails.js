import request from '@/utils/request'
// 提交审核
export function C08Review(data) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C08Review',
        method: 'post',
        data
    })
}

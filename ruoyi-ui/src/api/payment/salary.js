import request from '@/utils/request'

// 薪酬管理列表
export function salaryRecordsC07List(data) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C07List',
        method: 'post',
        data
    })
}
// 工资核算
export function C09WageAc(data) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C09WageAc',
        method: 'post',
        data
    })
}
// 工资核算
export function C09Issue(id) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C09Issue/'+id,
        method: 'get',
    })
}

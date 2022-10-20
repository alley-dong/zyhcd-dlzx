import request from '@/utils/request'
// 工资明细导出
export function C10Export(data) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C10Export',
        method: 'post',
        data
    })
}
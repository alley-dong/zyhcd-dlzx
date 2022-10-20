import request from '@/utils/request'

// 补助管理-补助统计及明细
export function sysExamineTaskList(data) {
    return request({
        url: '/system/task/sysExamineTaskList',
        method: 'post',
        data
    })
}
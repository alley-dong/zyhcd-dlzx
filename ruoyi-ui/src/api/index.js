import request from '@/utils/request'

// 首页——我的待办
export function statisticsIndexExamineTaskList(query) {
    return request({
        url: '/pfserver-car/index/statistics/indexExamineTaskList',
        method: 'post',
        data: query
    })
}

//  首页——统计数据
export function statisticsIndexStatistics(query) {
    return request({
        url: '/pfserver-car/index/statistics/indexStatistics',
        method: 'post',
        data: query
    })
} 
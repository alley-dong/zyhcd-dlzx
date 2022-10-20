import request from '@/utils/request'

// 考勤统计-列表显示当月有多少考勤人数
export function A53List(query) {
    return request({
        url: '/pfserver-person/person/vacationDay/A53List',
        method: 'post',
        data: query
    })
}

// 
export function A54List(query) {
    return request({
        url: '/pfserver-person/person/vacationDay/A54List',
        method: 'post',
        data: query
    })
}

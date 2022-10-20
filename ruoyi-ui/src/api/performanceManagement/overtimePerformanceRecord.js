import request from '@/utils/request'

// 加班绩效记录列表
export function G14List (data) {
  return request({
    url: '/pfserver-person/person/performanceOvertimeRecord/G14List',
    method: 'post',
    data
  })
}
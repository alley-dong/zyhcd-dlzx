import request from '@/utils/request'

// 列表
export function transportTaskTaskOvertimeList(query) {
  return request({
    url: '/pfserver-car/driver-api/transportTask/TaskOvertimeList',
    method: 'post',
    data: query
  })
}


// 运输任务-运输任务加班记录详情
export function transportTaskTaskOvertimeInfo(query) {
    return request({
      url: '/pfserver-car/driver-api/transportTask/TaskOvertimeInfo',
      method: 'post',
      data: query
    })
  }
  
  
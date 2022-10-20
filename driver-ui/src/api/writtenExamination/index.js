import request from '@/utils/request'

// 列表
export function driverProblemB01List(query) {
  return request({
    url: '/pfserver-car/driver-api/driverProblem/B01List',
    method: 'post',
    data: query
  })
}

// 考试-答题App
export function driverProblemB02List(query) {
    return request({
      url: '/pfserver-car/driver-api/driverProblem/B02List',
      method: 'post',
      data: query
    })
  }
  
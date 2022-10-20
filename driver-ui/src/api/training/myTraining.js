import request from '@/utils/request'

// 培训-培训列表App
export function driverTrainingC01List(query) {
  return request({
    url: '/pfserver-car/driver-api/driverTraining/C01List',
    method: 'post',
    data: query
  })
}

// 培训-培训详情App
export function driverTrainingC02List(query) {
  return request({
    url: '/pfserver-car/driver-api/driverTraining/C02List',
    method: 'get',
    params: query
  })
}

// 培训-培训详情App
export function driverTrainingC02Edit(query) {
  return request({
    url: '/pfserver-car/driver-api/driverTraining/C02Edit',
    method: 'post',
    data: query
  })
}
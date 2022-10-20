import request from '@/utils/request'

// 违章-违章列表App
export function K01List(query) {
  return request({
    url: '/pfserver-car/driver-api/driverTraffic/K01List',
    method: 'post',
    data: query
  })
}

// 获取token
export function driverLogin(query) {
  return request({
    url: 'http://115.29.205.180:10025/driverLogin',
    method: 'post',
    data: query
  })
}
// 违章-违章详情App
export function driverTrafficK02GetInfo(params) {
  return request({
    url: '/pfserver-car/driver-api/driverTraffic/K02GetInfo',
    method: 'get',
    params
  })
}
// 处理完成
export function driverTrafficK02Shang(data) {
  return request({
    url: '/pfserver-car/driver-api/driverTraffic/K02Shang',
    method: 'post',
    data
  })
}
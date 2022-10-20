import request from '@/utils/request'

// 列表
export function salaryG01List(query) {
  return request({
    url: '/pfserver-car/driver-api/salary/G01ListApp',
    method: 'post',
    data: query
  })
}
// 列表
export function salaryG02Info(id) {
    return request({
      url: '/pfserver-car/driver-api/salary/G02Info/' + id,
      method: 'get',
    })
  }
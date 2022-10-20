import request from '@/utils/request'

// 交通事故-事故记录列表
export function J01SafeAccidentList(query) {
  return request({
    url: '/pfserver-car/driver-api/accident/J01SafeAccidentList',
    method: 'post',
    data: query
  })
}
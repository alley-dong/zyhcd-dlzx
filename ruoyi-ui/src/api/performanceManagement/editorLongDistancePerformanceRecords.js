import request from '@/utils/request'

// 长途绩效记录编辑
export function G10Edit (data) {
  return request({
    url: '/pfserver-person/person/performanceRecord/G10Edit',
    method: 'post',
    data
  })
}
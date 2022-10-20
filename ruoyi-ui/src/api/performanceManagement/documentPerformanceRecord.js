import request from '@/utils/request'

// 长途绩效记录列表
export function G08List (data) {
  return request({
    url: '/pfserver-person/person/performanceRecord/G08List',
    method: 'post',
    data
  })
}

// 绩效名称下拉选
export function commonG08List (data) {
  return request({
    url: '/pfserver-person/select/common/G08List',
    method: 'post',
    data
  })
}

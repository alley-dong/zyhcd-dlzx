import request from '@/utils/request'

// 参数列表
export function statisticsConfigB08Info(query) {
  return request({
    url: '/system/statisticsConfig/B08Info',
    method: 'get',
    params: query
  })
}



  // 编辑
export function statisticsConfigB08Edit(query) {
    return request({
      url: '/system/statisticsConfig/B08Edit',
      method: 'post',
      data: query
    })
  }
import request from '@/utils/request'

// 列表
export function driverNoticeD01List(query) {
  return request({
    url: '/pfserver-car/driver-api/driverNotice/D01List',
    method: 'post',
    data: query
  })
}


// 公告-公告详情App
export function driverNoticeD02List(query) {
    return request({
      url: '/pfserver-car/driver-api/driverNotice/D02List/',
      method: 'get',
      params: query
    })
  }
  
  
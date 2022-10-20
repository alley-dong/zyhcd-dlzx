import request from '@/utils/request'

// 绩效管理-绩效列表
export function G01Listt(data) {
    return request({
        url: '/pfserver-person/person/performance/G01List',
        method: 'post',
        data
    })
}
// 绩效管理-删除绩效
export function G01Delete(id) {
  return request({
      url: '/pfserver-person/person/performance/G01Delete/'+id,
      method: 'get',
  })
}
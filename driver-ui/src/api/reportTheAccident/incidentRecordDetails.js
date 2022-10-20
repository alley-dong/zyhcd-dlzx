import request from '@/utils/request'

// 交通事故-事故详情接口
export function J02SafeAccidentInfo(query) {
  return request({
    url: '/pfserver-car/driver-api/accident/J02SafeAccidentInfo',
    method: 'post',
    data: query
  })
}
// 获取审批业务审批人
export function queryExamineConfigProcessList(query) {
  return request({
    url: '/system/task/queryExamineConfigProcessList',
    method: 'get',
    params: query
  })
}
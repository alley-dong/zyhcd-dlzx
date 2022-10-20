import request from '@/utils/request'

// 查询ETC管理-ETC卡使用记录列表
export function listRecord(data) {
  return request({
    url: '/pfserver-car/car/etcCardUsageRecord/J05List',
    method: 'post',
    data
  })
}
//查询ETC管理-ETC卡使用记录校验导入
export function J05Check(data){
  return request({
    url:'/pfserver-car/car/etcCardUsageRecord/J05Check',
    method: 'post',
    data
  })
}
//ETC记录管理-记录导入
export function J05ImportData(data){
  return request({
    url:'/pfserver-car/car/etcCardUsageRecord/J05ImportData',
    method: 'post',
    data
  })
}


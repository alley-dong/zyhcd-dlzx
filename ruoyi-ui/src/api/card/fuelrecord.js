import request from '@/utils/request'

// 异常记录-油料使用异常记录列表
export function PlantsAbnormalRecordList(data) {
  return request({
    url: '/pfserver-car/oilCardRecord/I06OilPlantsAbnormalRecordList',
    method: 'post',
    data
  })
}

// 油料使用记录-油料使用记录列表
export function PlantsRecordList(data) {
  return request({
    url: '/pfserver-car/oilCardRecord/I05OilPlantsRecordList',
    method: 'post',
    data:data
  })
}
  
// 油料使用记录导入
export function I05OilPlantsRecordImport(data) {
  return request({
    url: '/pfserver-car/oilCardRecord/I05OilPlantsRecordImport',
    method: 'post',
    data:data
  })
}
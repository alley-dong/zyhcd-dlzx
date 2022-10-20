import request from '@/utils/request'

// 异常记录-油料使用异常记录列表
export function PlantsAbnormalRecordList(data) {
  return request({
    url: '/pfserver-car/oilCardRecord/I06OilPlantsAbnormalRecordList',
    method: 'post',
    data
  })
}

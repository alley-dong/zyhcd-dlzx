import request from '@/utils/request'

// 盘点-盘点记录列表
export function T03AdminGGoodsCheckList(query) {
  return request({
    url: '/system/administrator-app/goodsReceivingRecord/T03AdminGGoodsCheckList',
    method: 'post',
    data: query
  })
}

// 盘点-盘点记录详情
export function T03AdminGGoodsCheckInfo(query) {
  return request({
    url: '/system/administrator-app/goodsReceivingRecord/T03AdminGGoodsCheckInfo',
    method: 'post',
    data: query
  })
}

// 盘点-新增盘点
export function T03AdminInsertCheck(query) {
  return request({
    url: '/system/administrator-app/goodsReceivingRecord/T03AdminInsertCheck',
    method: 'post',
    data: query
  })
}

// 盘点项-编辑
export function T05AdminUpdateCheckItem(query) {
  return request({
    url: '/system/administrator-app/goodsReceivingRecord/T05AdminUpdateCheckItem',
    method: 'post',
    data: query
  })
}
// 
// 盘点项-编辑
export function T04AdminSubmitCheckItem(query) {
  return request({
    url: '/system/administrator-app/goodsReceivingRecord/T04AdminSubmitCheckItem',
    method: 'post',
    data: query
  })
}
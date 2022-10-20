import request from '@/utils/request'

// 查询车辆管理-货物保险-车辆列表
export function OilCardList(data) {
  return request({
    url: '/pfserver-car/car/oilCard/I01OilCardList',
    method: 'post',
    data
  })
}
// 车辆列表接口
export function addcarlist(data) {
  return request({
    url: '/pfserver-car/commonSelectBox/J02CarNumList',
    method: 'post',
    data
  })
}

// 油卡管理-新增油卡
export function addCard(data) {
  return request({
    url: '/pfserver-car/car/oilCard/I01OilCardAdd',
    method: 'post',
    data
  })
}

// 油卡管理-修改油卡
export function updateCard(data) {
  return request({
    url: '/pfserver-car/car/oilCard/I01OilCardEditSave',
    method: 'post',
    data
  })
}
// 油卡管理-删除油卡
export function deleteCard(id) {
  return request({
    url: '/pfserver-car/car/oilCard/I01OilCardDelete/' + id,
    method: 'get',
  })
}
// 燃料定额管理-燃油定额列表  
export function getPlantsMoneyInfo(id) {
  return request({
    url: '/pfserver-car/fuelQuota/I02OilPlantsMoneyInfo/' + id,
    method: 'get',
  })
}

// 油卡管理-油卡导入 
export function I01OilCardImport(data) {
  return request({
    url: '/pfserver-car/car/oilCard/I01OilCardImport/',
    method: 'post',
    data: data
  })
}
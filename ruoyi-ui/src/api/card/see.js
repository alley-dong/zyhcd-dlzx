import request from '@/utils/request'

// 定点管理-定点详情信息
export function I03OilPlantsRouteInfo(id) {
  return request({
    url: '/pfserver-car/fuelPlace/I03OilPlantsRouteInfo/'+id,
    method: 'get',
  })
}

// 定点加油站管理-定点加油站列表
export function I04OilPlantsAddressList(data) {
  return request({
    url: '/pfserver-car/placeItem/I04OilPlantsAddressList',
    method: 'post',
    data
  })
}


// 定点加油站管理-新增定点加油站
export function I04OilPlantsAddressAdd(data) {
  return request({
    url: '/pfserver-car/placeItem/I04OilPlantsAddressAdd',
    method: 'post',
    data
  })
}
// 定点加油站管理-修改定点加油站
export function I04OilPlantsAddressEditSave(data) {
  return request({
    url: '/pfserver-car/placeItem/I04OilPlantsAddressEditSave',
    method: 'post',
    data
  })
}
// 定点加油站管理-删除定点加油站
export function I04OilPlantsAddressDelete(id) {
  return request({
    url: '/pfserver-car/placeItem/I04OilPlantsAddressDelete/'+id,
    method: 'get',
  
  })
}

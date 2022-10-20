import request from '@/utils/request'

// 查询车辆管理-车辆年检记录列表
export function listVehicleInspectRecord(query) {
  return request({
    url: '/pfservercar/vehicleInspectRecord/list',
    method: 'get',
    params: query
  })
}

// 查询车辆管理-车辆年检记录详细
export function getVehicleInspectRecord(id) {
  return request({
    url: '/pfservercar/vehicleInspectRecord/' + id,
    method: 'get'
  })
}

// 新增车辆管理-车辆年检记录
export function addVehicleInspectRecord(data) {
  return request({
    url: '/pfservercar/vehicleInspectRecord',
    method: 'post',
    data: data
  })
}

// 修改车辆管理-车辆年检记录
export function updateVehicleInspectRecord(data) {
  return request({
    url: '/pfservercar/vehicleInspectRecord',
    method: 'put',
    data: data
  })
}

// 删除车辆管理-车辆年检记录
export function delVehicleInspectRecord(id) {
  return request({
    url: '/pfservercar/vehicleInspectRecord/' + id,
    method: 'delete'
  })
}

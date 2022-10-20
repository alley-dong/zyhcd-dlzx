import request from '@/utils/request'

// 查询车辆管理-车辆保险列表
export function listVehicle(query) {
  return request({
    url: '/pfserver-car/car/vehicle/D01VehicleList',
    method: 'post',
    data: query
  })
}

// 查询车辆管理-车辆保险详细
export function getVehicle(id) {
  return request({
    url: '/pfserver-car/vehicle/' + id,
    method: 'get'
  })
}

// 新增车辆管理-车辆保险
export function addVehicle(data) {
  return request({
    url: '/pfserver-car/vehicle',
    method: 'post',
    data: data
  })
}

// 修改车辆管理-车辆保险
export function updateVehicle(data) {
  return request({
    url: '/pfserver-car/vehicle',
    method: 'put',
    data: data
  })
}
// 新增车辆档案
export function VehicleAdd(data) {
  return request({
    url: '/pfserver-car/car/vehicle/D02VehicleAdd',
    method: 'post',
    data: data
  })
}
// 编辑保存车辆档案
export function VehicleSave(data) {
  return request({
    url: '/pfserver-car/car/vehicle/D04VehicleEditSave',
    method: 'post',
    data: data
  })
}
// 删除车辆管理-车辆保险
export function delVehicle(id) {
  return request({
    url: '/pfserver-car/car/vehicle/D01VehicleDelete/' + id,
    method: 'get'
  })
}

// 查询单条车辆档案信息
export function vehicleInfo(id) {
  return request({
    url: '/pfserver-car/car/vehicle/D03VehicleInfo/' + id,
    method: 'get'
  })
}
// 车辆年检记录列表
export function vehicleInspectRecordList(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspectRecord/D05list',
    method: 'post',
    data: data
  })
}
// 车辆年检记录查询列表

export function vehicleInspectRecordInfoList(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspectRecord/D05InfoList',
    method: 'post',
    data: data
  })
}

// 车辆年检记录新增
export function vehicleInspectRecordAdd(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspectRecord/D06add',
    method: 'post',
    data: data
  })
}
// 车辆年检记录编辑
export function vehicleInspectRecordEditSave(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspectRecord/D06EditSave',
    method: 'post',
    data: data
  })
}
// 车辆年检记录回显
export function vehicleInspectRecordEdit(id) {
  return request({
    url: '/pfserver-car/car/vehicleInspectRecord/D06Edit/' + id,
    method: 'get',
  })
}// 车辆年检记录删除
export function vehicleInspectRecordDelete(id) {
  return request({
    url: '/pfserver-car/car/vehicleInspectRecord/D06Delete/' + id,
    method: 'get',
  })
}

// 车辆状态编辑保存
export function vehicleStatusEditSave(data) {
  return request({
    url: '/pfserver-car/car/vehicleStatus/D07EditSave',
    method: 'post',
    data: data
  })
}

// 车辆状态列表

export function vehicleInspectRecord7List(data) {
  return request({
    url: '/pfserver-car/car/vehicleStatus/D07List',
    method: 'post',
    data: data
  })
}

// 导入车辆档案

export function vehicleImport(data) {
  return request({
    url: '/pfserver-car/car/vehicle/D01VehicleImport',
    method: 'post',
    data: data
  })
}
//车辆档案车辆类型下拉框
export function vhicleSelectBox(id) {
  return request({
    url: '/pfserver-car/commonSelectBox/D02CvhicleSelectBox/' + id,
    method: 'get',
  })
}

export function D01Disable(id) {
  return request({
    url: '/pfserver-car/car/vehicle/D01Disable/' + id,
    method: 'get',
  })
}


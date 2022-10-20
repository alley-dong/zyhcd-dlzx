import request from '@/utils/request'

// 列表
export function repairH01RepairList(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H01RepairList',
    method: 'post',
    data: query
  })
}
// 记录详情（待审批）App
export function repairH01Info(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H01Info',
    method: 'post',
    data: query
  })
}

// 维保-主挂车车牌号App

export function repairH06LicensePlate(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H06LicensePlate',
    method: 'post',
    data: query
  })
}

// 绩效管理-路线下拉选
export function commonJ04RoutList(query) {
  return request({
    url: '/pfserver-person/select/common/J04RoutList',
    method: 'post',
    data: query
  })
}
// 维保-维修保养项接口App
export function repairH08RepairItemInfo(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H08RepairItemInfo',
    method: 'post',
    data: query
  })
}
// 维保-事故下拉选
export function repairH08Accident(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H08Accident',
    method: 'post',
    data: query
  })
}

// 维保-事故下拉选
export function repairH01Add(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H01Add',
    method: 'post',
    data: query
  })
}

// 表
export function repairH08DetailAllList(id) {
  return request({
    url: '/pfserver-car/car/repair/H08DetailAllList/'+id,
    method: 'get',
  })
}

// 撤销维修保养申请接口
export function repairH01Revoke(id) {
  return request({
    url: '/pfserver-car/driver-api/repair/H01Revoke?id='+id,
    method: 'post',
  })
}

// 列表
export function H03AllListDetail(id) {
  return request({
    url: '/pfserver-car/driver-api/repair/H03AllListDetail/'+id,
    method: 'get',
  })
}

// 确认
export function repairH02Save(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H02Save/'+query,
    method: 'post',
    // data: query
  })
}

export function repairH02Upload(query) {
  return request({
    url: '/pfserver-car/driver-api/repair/H02Upload',
    method: 'post',
    data: query
  })
}

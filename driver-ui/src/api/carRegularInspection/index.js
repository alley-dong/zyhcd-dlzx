import request from '@/utils/request'

// 列表
export function vehicleInspectI01List(query) {
  return request({
    url: '/pfserver-car/driver-api/inspect/I01List',
    method: 'post',
    data: query
  })
}


// 车辆定检-新增车辆定检-车牌号回显
export function inspectI02GetInfo(query) {
  return request({
    url: '/pfserver-car/driver-api/inspect/I02GetInfo',
    method: 'post',
    data: query
  })
}

// 车辆定检-新增车辆定检-挂车车牌号回显
export function inspectI03GetInfo(query) {
  return request({
    url: '/pfserver-car/driver-api/inspect/I03GetInfo',
    method: 'post',
    data: query
  })
}

// 车辆定检-主车新增定检
export function inspectI02Add(query) {
  return request({
    url: '/pfserver-car/driver-api/inspect/I02Add',
    method: 'post',
    data: query
  })
}

// 车辆定检-主车新增定检
export function inspectI04List(id) {
  return request({
    url: '/pfserver-car/driver-api/inspect/I04List/'+id,
    method: 'get',
  })
}

// 车辆定检-再次提交
export function inspectI07Add(query) {
  return request({
    url: '/pfserver-car/driver-api/inspect/I07Add',
    method: 'post',
    data: query
  })
}
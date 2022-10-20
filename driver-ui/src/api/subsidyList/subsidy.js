import request from '@/utils/request'

// 补助-补助列表App
export function F01List(query) {
  return request({
    url: '/pfserver-car/driver-api/driverSubsidy/F01List',
    method: 'post',
    data: query
  })
}

// 补助-补助列表App
export function driverSubsidyF02Add(query) {
  return request({
    url: '/pfserver-car/driver-api/driverSubsidy/F02Add',
    method: 'post',
    data: query
  })
}

// 补助-补助详情App
export function driverSubsidyF07GetInfo(params) {
  return request({
    url: '/pfserver-car/driver-api/driverSubsidy/F07GetInfo',
    method: 'get',
    params
  })
}


// 补助-撤销申请App
export function driverSubsidyF04Change(params) {
  return request({
    url: '/pfserver-car/driver-api/driverSubsidy/F04Change' ,
    method: 'get',
    params
  })
}
// 补助-重新申请App
export function driverSubsidyF07Edit(data) {
  return request({
    url: '/pfserver-car/driver-api/driverSubsidy/F07Edit' ,
    method: 'post',
    data
  })
}


// 补助详情 
export function sysExamineTaskList(data) {
  return request({
    url: '/pfserver-car/task/sysExamineTaskList' ,
    method: 'post',
    data
  })
}
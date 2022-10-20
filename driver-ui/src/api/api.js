import request from '@/utils/request'

// 列表
export function getA03DriverMessageList(query) {
  return request({
    url: '/pfserver-car/driver-api/msg/A03DriverMessageList',
    method: 'post',
    data: query
  })
}

// parts服务   
export function minioChooseBucketUpload(query) {
  return request({
    url: '/pfserver-person/personFile/minioChooseBucketUpload',
    method: 'post',
    data:query
  })
}

export function minioChooseBucketUploadList(query) {
  return request({
    url: '/pfserver-person/personFile/minioChooseBucketUploadList',
    method: 'post',
    data:query
  })
}

// 审批
export function queryExamineConfigProcessList(query) {
  return request({
    url: '/system/task/queryExamineConfigProcessList',
    method: 'get',
    params: query
  })
}
// 个人消息-工作台未读消息列表
export function A04QueryUnreadList(query) {
  return request({
    url: '/pfserver-car/driver-api/msg/A04QueryUnreadList',
    method: 'post',
    data: query
  })
}

// 审批详情

export function queryExamineConfigProcessListDetail(query) {
  return request({
    url: '/system/task/sysExamineTaskList',
    method: 'post',
    data:query
  })
}

// 审批详情

export function A01DriverInfo(query) {
  return request({
    url: '/pfserver-car/driver-api/msg/A01DriverInfo',
    method: 'post',
    data:query
  })
}

export function driverUpdatePassword(query) {
  return request({
    url: '/pfserver-car/driver-api/login/driverUpdatePassword',
    method: 'post',
    data:query
  })
}


// 审批详情
export function getSysExamineTaskDriverList(query) {
  return request({
    url: '/system/task/sysExamineTaskDriverList',
    method: 'post',
    data:query
  })
}

// 审批详情
export function getSysExamineTaskList(query) {
  return request({
    url: '/system/task/sysExamineTaskList',
    method: 'post',
    data:query
  })
}
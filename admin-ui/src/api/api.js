import request from '@/utils/request'

// 列表
export function getA03DriverMessageList(query) {
  return request({
    url: '/pfserver-car/driver-api/msg/A03DriverMessageList',
    method: 'post',
    data: query
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

export function minioChooseBucketUploadList(query) {
  return request({
    url: '/pfserver-person/personFile/minioChooseBucketUploadList',
    method: 'post',
    data:query
  })
}

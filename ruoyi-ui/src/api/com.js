import request from '@/utils/request'

// 查询参数列表
export function queryExamineConfigProcessList(query) {
  return request({
    url: '/system/task/sysExamineTaskList',
    method: 'post',
    data:query
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

// parts服务 
export function minioChooseBucketDownload(query) {
  return request({
    url: '/pfserver-person/personFile/minioChooseBucketDownload',
    method: 'get',
    params:query
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
import request from '@/utils/request'

// 管理员app-抄送记录
export function taskR01SendDuplicateList(query) {
  return request({
    url: '/system/administrator-app/task/R01SendDuplicateList',
    method: 'post',
    data: query
  })
}
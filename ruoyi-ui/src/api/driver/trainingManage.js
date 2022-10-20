import request from '@/utils/request'

// 列表
export function trainingManageList(query) {
  return request({
    url: '/pfserver-person/person/training/A33List',
    method: 'post',
    data: query
  })
}

// 培训管理-发布状态修改
export function trainingChange(id) {
  return request({
    url: '/pfserver-person/person/training/A33Change/' + id,
    method: 'get',
  })
}
// 培训管理-培训删除
export function trainingDelete(id) {
  return request({
    url: '/pfserver-person/person/training/A33Delete/' + id,
    method: 'get',
  })
}
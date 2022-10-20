import request from '@/utils/request'

// 查询驾驶员-请假类型列表
export function listLeave(query) {
  return request({
    url: '/system/leave/list',
    method: 'get',
    params: query
  })
}

// 查询驾驶员-请假类型详细
export function getLeave(id) {
  return request({
    url: '/system/leave/' + id,
    method: 'get'
  })
}

// 新增驾驶员-请假类型
export function addLeave(data) {
  return request({
    url: '/system/leave',
    method: 'post',
    data: data
  })
}

// 修改驾驶员-请假类型
export function updateLeave(data) {
  return request({
    url: '/system/leave',
    method: 'put',
    data: data
  })
}

// 删除驾驶员-请假类型
export function delLeave(id) {
  return request({
    url: '/system/leave/' + id,
    method: 'delete'
  })
}

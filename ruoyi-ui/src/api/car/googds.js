import request from '@/utils/request'

// 查询车辆管理-货物保险-车辆列表
export function listGoogds(query) {
  return request({
    url: '/system/googds/list',
    method: 'get',
    params: query
  })
}

// 查询车辆管理-货物保险-车辆详细
export function getGoogds(id) {
  return request({
    url: '/system/googds/' + id,
    method: 'get'
  })
}

// 新增车辆管理-货物保险-车辆
export function addGoogds(data) {
  return request({
    url: '/system/googds',
    method: 'post',
    data: data
  })
}

// 修改车辆管理-货物保险-车辆
export function updateGoogds(data) {
  return request({
    url: '/system/googds',
    method: 'put',
    data: data
  })
}

// 删除车辆管理-货物保险-车辆
export function delGoogds(id) {
  return request({
    url: '/system/googds/' + id,
    method: 'delete'
  })
}

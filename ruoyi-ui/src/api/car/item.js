import request from '@/utils/request'

// 查询车辆管理-车辆保险保险项列表
export function listItem(query) {
  return request({
    url: '/car/item/list',
    method: 'get',
    params: query
  })
}

// 查询车辆管理-车辆保险保险项详细
export function getItem(id) {
  return request({
    url: '/car/item/' + id,
    method: 'get'
  })
}

// 新增车辆管理-车辆保险保险项
export function addItem(data) {
  return request({
    url: '/car/item',
    method: 'post',
    data: data
  })
}

// 修改车辆管理-车辆保险保险项
export function updateItem(data) {
  return request({
    url: '/car/item',
    method: 'put',
    data: data
  })
}

// 删除车辆管理-车辆保险保险项
export function delItem(id) {
  return request({
    url: '/car/item/' + id,
    method: 'delete'
  })
}

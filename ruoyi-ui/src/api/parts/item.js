import request from '@/utils/request'

// 查询库存管理-盘点项列表
export function listItem(query) {
  return request({
    url: '/system/item/list',
    method: 'get',
    params: query
  })
}

// 查询库存管理-盘点项详细
export function getItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'get'
  })
}

// 新增库存管理-盘点项
export function addItem(data) {
  return request({
    url: '/system/item',
    method: 'post',
    data: data
  })
}

// 修改库存管理-盘点项
export function updateItem(data) {
  return request({
    url: '/system/item',
    method: 'put',
    data: data
  })
}

// 删除库存管理-盘点项
export function delItem(id) {
  return request({
    url: '/system/item/' + id,
    method: 'delete'
  })
}

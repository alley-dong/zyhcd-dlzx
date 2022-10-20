import request from '@/utils/request'

// 查询库存管理-物品类别列表
export function listPfserverparts(query) {
  return request({
    url: '/system/pfserverparts/list',
    method: 'get',
    params: query
  })
}

// 查询库存管理-物品类别详细
export function getPfserverparts(id) {
  return request({
    url: '/system/pfserverparts/' + id,
    method: 'get'
  })
}

// 新增库存管理-物品类别
export function addPfserverparts(data) {
  return request({
    url: '/system/pfserverparts',
    method: 'post',
    data: data
  })
}

// 修改库存管理-物品类别
export function updatePfserverparts(data) {
  return request({
    url: '/system/pfserverparts',
    method: 'put',
    data: data
  })
}

// 删除库存管理-物品类别
export function delPfserverparts(id) {
  return request({
    url: '/system/pfserverparts/' + id,
    method: 'delete'
  })
}

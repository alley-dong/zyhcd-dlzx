import request from '@/utils/request'

// 查询库存管理-盘点列表
export function listCheck(query) {
  return request({
    url: '/system/check/list',
    method: 'get',
    params: query
  })
}

// 查询库存管理-盘点详细
export function getCheck(id) {
  return request({
    url: '/system/check/' + id,
    method: 'get'
  })
}

// 新增库存管理-盘点
export function addCheck(data) {
  return request({
    url: '/system/check',
    method: 'post',
    data: data
  })
}

// 修改库存管理-盘点
export function updateCheck(data) {
  return request({
    url: '/system/check',
    method: 'put',
    data: data
  })
}

// 删除库存管理-盘点
export function delCheck(id) {
  return request({
    url: '/system/check/' + id,
    method: 'delete'
  })
}

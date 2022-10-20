import request from '@/utils/request'

// 查询驾驶员-司机档案列表
export function listDriver(query) {
  return request({
    url: '/system/driver/list',
    method: 'get',
    params: query
  })
}

// 查询驾驶员-司机档案详细
export function getDriver(id) {
  return request({
    url: '/system/driver/' + id,
    method: 'get'
  })
}

// 新增驾驶员-司机档案
export function addDriver(data) {
  return request({
    url: '/system/driver',
    method: 'post',
    data: data
  })
}

// 修改驾驶员-司机档案
export function updateDriver(data) {
  return request({
    url: '/system/driver',
    method: 'put',
    data: data
  })
}

// 删除驾驶员-司机档案
export function delDriver(id) {
  return request({
    url: '/system/driver/' + id,
    method: 'delete'
  })
}

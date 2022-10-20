import request from '@/utils/request'

// 查询角色列表
export function listRole(query) {
  return request({
    url: '/system/role/B02List',
    method: 'post',
    params: query
  })
}

// 查询角色详细
export function getRole(roleId) {
  return request({
    url: '/system/role/B02GetInfo/' + roleId,
    method: 'get'
  })
}

// 新增角色
export function addRole(data) {
  return request({
    url: '/system/role/B03Add',
    method: 'post',
    data: data
  })
}

// 修改角色
export function updateRole(data) {
  return request({
    url: '/system/role/B04Edit',
    method: 'post',
    data: data
  })
}

// 角色数据权限
export function dataScope(data) {
  return request({
    url: '/system/role/dataScope',
    method: 'put',
    data: data
  })
}

// 角色状态修改
export function changeRoleStatus(data) {
 
  return request({
    url: '/system/role/B02ChangeStatus',
    method: 'post',
    data
  })
}

// 删除角色
export function delRole(roleId) {
  return request({
    url: '/system/role/B02Delete' + roleId,
    method: 'post'
  })
}

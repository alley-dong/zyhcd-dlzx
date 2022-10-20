import request from '@/utils/request'

// 群组管理列表
export function groupManageList(query) {
    return request({
      url: '/pfserver-person/person/dDriverGroup/A39List',
      method: 'post',
      data: query
    })
}

// 编辑页面渲染
export function groupManageList1(id) {
  return request({
    url: '/pfserver-person/person/dDriverGroup/A39GetInfo/' + id,
    method: 'get',
  })
}
// 新增信息列表
export function addGroupList(query) {
  return request({
    url: '/pfserver-person/person/driver/A19List',
    method: 'post',
    data: query
  })
}

// 新增保存按钮 --> 新增群组
export function addGroup(query) {
  return request({
    url: '/pfserver-person/person/dDriverGroup/A40Add',
    method: 'post',
    data: query
  })
}

// 编辑页面渲染
export function getgroupManageDetails(id) {
  return request({
    url: '/pfserver-person/person/dDriverGroup/A39GetInfo/' + id,
    method: 'get'
  })
}

// 编辑
export function editGroup(data) {
  return request({
    url: '/pfserver-person/person/dDriverGroup/A41Edit',
    method: 'post',
    data: data
  })
}

// 删除
export function groupManageDelete(tableId) {
  return request({
    url: '/pfserver-person/person/dDriverGroup/A39Delete/' + tableId,
    method: 'get',
  })
}
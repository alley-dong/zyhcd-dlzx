import request from '@/utils/request'

// 列表
export function driverTypeList(query) {
    return request({
      url: '/pfserver-person/person/driverType/A23List',
      method: 'post',
      data: query
    })
}

// 详情
export function driverTypeDetails(id) {
  return request({
    url: '/pfserver-person/person/driverType/A23GetInfo/' + id,
    method: 'get'
  })
}

// 新增
export function driverTypeAdd(query) {
  return request({
    url: '/pfserver-person/person/driverType/A23Add',
    method: 'post',
    data: query
  })
}

// 编辑
export function driverTypeEdit(data) {
  return request({
    url: '/pfserver-person/person/driverType/A23EditSave',
    method: 'post',
    data: data
  })
}

// 删除
export function driverTypeDelete(tableId) {
  return request({
    url: '/pfserver-person/person/driverType/A23Delete/' + tableId,
    method: 'get',
  })
}



import request from '@/utils/request'

// 列表
export function leaveTypeManageList(query) {
    return request({
      url: '/pfserver-person/person/leaveType/A48LeaveTypeList',
      method: 'post',
      data: query
    })
}

// 详情
export function leaveTypeManageDetails(id) {
  return request({
    url: '/pfserver-person/person/leaveType/A48LeaveTypeInfo/' + id,
    method: 'get'
  })
}

// 新增
export function leaveTypeManageAdd(query) {
  return request({
    url: '/pfserver-person/person/leaveType/A48LeaveTypeAdd',
    method: 'post',
    data: query
  })
}

// 编辑
export function leaveTypeManageEdit(data) {
  return request({
    url: '/pfserver-person/person/leaveType/A48LeaveTypeEditSave',
    method: 'post',
    data: data
  })
}

// 删除
export function leaveTypeManageDelete(tableId) {
  return request({
    url: '/pfserver-person/person/leaveType/A48LeaveTypeDelete/' + tableId,
    method: 'get',
  })
}
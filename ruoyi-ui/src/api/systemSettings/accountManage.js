import request from '@/utils/request'
let host = `http://192.168.2.82:9201`
 host = ``
// target: `http://192.168.2.82:9201`,

// 列表
export function accountManageList(query) {
    return request({
      url: host + '/system/user/B01List',
      method: 'post',
      data: query
    })
} 

// 详情
export function accountManageDetails(id) {
  return request({
    url: host + '/system/user/B01GetInfoL/' + id,
    method: 'get',
  })
}

// 新增
export function accountAdd(query) {
  return request({
    url: host + '/system/user/B01Add',
    method: 'post',
    data: query
  })
}

// 编辑
export function accountEdit(data) {
  return request({
    url: host + '/system/user/B01Edit',
    method: 'post',
    data: data
  })
}

// 删除
export function accountDelete(tableId) {
  return request({
    url: host + '/system/user/B01Delete/' + tableId,
    method: 'get',
  })
}

// 维修厂下拉选
export function B01ListCar() {
  return request({
    url: host + '/system/garage/B01ListCar',
    method: 'post',
  })
}

// 角色下拉选
export function B02ListTr() {
  return request({
    url: host + '/system/role/B02ListT',
    method: 'post',
    data:{}
  })
}

// 账号管理-账号启用/禁用
export function changeStatus(data) {
  return request({
    url: host + '/system/user/B01ChangeStatus',
    method: 'post',
    data
  })
}
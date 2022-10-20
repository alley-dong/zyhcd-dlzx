import request from '@/utils/request'

// 查询ETC管理-ETC定额列表
export function listQuota(data) {
  return request({
    url: '/pfserver-car/car/etcQuota/J04List',
    method: 'post',
    data: data
  })
}

// 查询ETC管理-ETC定额详细
export function getQuota(id) {
  return request({
    url: '/pfserver-car/car/etcQuota/J04GetInfo/' + id,
    method: 'get'
  })
}

// 新增ETC管理-ETC定额
export function addQuota(data) {
  return request({
    url: '/pfserver-car/car/etcQuota/J04Add',
    method: 'post',
    data: data
  })
}

// 修改ETC管理-ETC定额
export function updateQuota(data) {
  return request({
    url: '/pfserver-car/car/etcQuota/J04Edit',
    method: 'post',
    data: data
  })
}

// 删除ETC管理-ETC定额
export function delQuota(id) {
  return request({
    url: '/pfserver-car/car/etcQuota/J04Delete/' + id,
    method: 'get'
  })
}

// 获取路线下拉选-ETC定额
export function routerBox(data) {
  return request({
    url: '/pfserver-person/select/common/J04RoutList',
    method: 'post',
    data: data
  })
}

// 获取车型下拉选-ETC定额
export function vehicleModelBox() {
  return request({
    url: '/pfserver-person/select/common/G02CarList',
    method: 'post'
  })
}
// ETC定额管理-车辆类型下拉选
export function G02ZhuCarList() {
  return request({
    url: '/pfserver-person/select/common/G02ZhuCarList',
    method: 'post'
  })
}

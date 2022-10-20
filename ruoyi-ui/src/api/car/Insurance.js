import request from '@/utils/request'

// 查询车辆管理-险种类别列表
export function listInsurance(query) {
  return request({
    url: '/car/Insurance/list',
    method: 'get',
    params: query
  })
}

// 查询车辆管理-险种类别详细
export function getInsurance(id) {
  return request({
    url: '/car/Insurance/' + id,
    method: 'get'
  })
}

// 新增车辆管理-险种类别
export function addInsurance(data) {
  return request({
    url: '/car/Insurance',
    method: 'post',
    data: data
  })
}

// 修改车辆管理-险种类别
export function updateInsurance(data) {
  return request({
    url: '/car/Insurance',
    method: 'put',
    data: data
  })
}

// 删除车辆管理-险种类别
export function delInsurance(id) {
  return request({
    url: '/car/Insurance/' + id,
    method: 'delete'
  })
}

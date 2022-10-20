import request from '@/utils/request'

// 查询驾驶员-驾驶员类型列表
export function listType(query) {
  return request({
    url: '/pfserverperson/type/list',
    method: 'get',
    params: query
  })
}

// 查询驾驶员-驾驶员类型详细
export function getType(id) {
  return request({
    url: '/pfserverperson/type/' + id,
    method: 'get'
  })
}

// 新增驾驶员-驾驶员类型
export function addType(data) {
  return request({
    url: '/pfserverperson/type',
    method: 'post',
    data: data
  })
}

// 修改驾驶员-驾驶员类型
export function updateType(data) {
  return request({
    url: '/pfserverperson/type',
    method: 'put',
    data: data
  })
}

// 删除驾驶员-驾驶员类型
export function delType(id) {
  return request({
    url: '/pfserverperson/type/' + id,
    method: 'delete'
  })
}

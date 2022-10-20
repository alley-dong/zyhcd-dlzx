import request from '@/utils/request'

// 查询驾驶员-司机试卷问题列表
export function listProblem(query) {
  return request({
    url: '/pfserverperson/problem/list',
    method: 'get',
    params: query
  })
}

// 查询驾驶员-司机试卷问题详细
export function getProblem(id) {
  return request({
    url: '/pfserverperson/problem/' + id,
    method: 'get'
  })
}

// 新增驾驶员-司机试卷问题
export function addProblem(data) {
  return request({
    url: '/pfserverperson/problem',
    method: 'post',
    data: data
  })
}

// 修改驾驶员-司机试卷问题
export function updateProblem(data) {
  return request({
    url: '/pfserverperson/problem',
    method: 'put',
    data: data
  })
}

// 删除驾驶员-司机试卷问题
export function delProblem(id) {
  return request({
    url: '/pfserverperson/problem/' + id,
    method: 'delete'
  })
}

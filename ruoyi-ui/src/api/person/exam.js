import request from '@/utils/request'

// 查询驾驶员-司机考试列表
export function listExam(query) {
  return request({
    url: '/pfserverperson/exam/list',
    method: 'get',
    params: query
  })
}

// 查询驾驶员-司机考试详细
export function getExam(id) {
  return request({
    url: '/pfserverperson/exam/' + id,
    method: 'get'
  })
}

// 新增驾驶员-司机考试
export function addExam(data) {
  return request({
    url: '/pfserverperson/exam',
    method: 'post',
    data: data
  })
}

// 修改驾驶员-司机考试
export function updateExam(data) {
  return request({
    url: '/pfserverperson/exam',
    method: 'put',
    data: data
  })
}

// 删除驾驶员-司机考试
export function delExam(id) {
  return request({
    url: '/pfserverperson/exam/' + id,
    method: 'delete'
  })
}

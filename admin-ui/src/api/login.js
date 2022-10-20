import request from '@/utils/request'

// 列表
export function login(query) {
  return request({
    url: '/auth/login',
    method: 'post',
    data: query
  })
}

// 列表
export function userGetInfo() {
  return request({
    url: '/system/user/getInfo',
    method: 'get',
  })
}
// 退出方法
export function logout() {
  return request({
    url: '/auth/logout',
    method: 'delete'
  })
}

// 退出方法
export function remove() {
  return request({
    url: '/system/task/tokenClean',
    method: 'post',
  data:{}
  })
}

// 查询token是否存在
export function queryToken() {
  return request({
    url: '/system/task/queryToken',
    method: 'post',
    data:{}
  })
}


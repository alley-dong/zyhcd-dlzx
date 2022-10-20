import request from '@/utils/request'

// 列表
export function roleManageList(query) {
    return request({
      url: '/system/role/B02List',
      method: 'post',
      data: query
    })
}

// 删除
export function delUser(tableId) {
  return request({
    url: '/system/role/B02Delete/' + tableId,
    method: 'get'
  })
}

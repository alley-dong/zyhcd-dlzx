import request from '@/utils/request'

// 违章管理-违章列表
export function L05List(data) {
    return request({
        url: '/pfserver-person/person/safeTrafficTransgression/L05List',
        method: 'post',
        data
    })
}

// 删除
export function L05Delete(id) {
  return request({
      url: '/pfserver-person/person/safeTrafficTransgression/L05Delete/'+id,
      method: 'get',
      
  })
}
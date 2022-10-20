import request from '@/utils/request'

// 信息系统服务费列表
export function M12List(data) {
    return request({
        url: '/pfserver-car/car/informationSystemServiceCost/M12List',
        method: 'post',
        data
    })
}
// 信息系统服务费导入
export function M12ImportData(data) {
  return request({
      url: '/pfserver-car/car/informationSystemServiceCost/M12ImportData',
      method: 'post',
      data
  })
}
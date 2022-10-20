import request from '@/utils/request'

// 查询车辆管理-货物保险-车辆列表
export function OilCardList(data) {
  return request({
    url: '/pfserver-car/car/oilCard/I01OilCardList',
    method: 'post',
    data
  })
}

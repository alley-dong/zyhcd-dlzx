import request from '@/utils/request'

// 交通事故-事故上报
export function J02SafeAccidentReport(query) {
  return request({
    url: '/pfserver-car/driver-api/accident/J02SafeAccidentReport',
    method: 'post',
    data: query
  })
}

// 交通事故-事故上报车牌号下拉
export function J02CarVehicleNameList(query) {
  return request({
    url: '/pfserver-car/commonSelectBox/J02CarNumList',
    method: 'post',
    data: query
  })
}

// 交通事故-事故上报
export function J02SafeAccidentAgainReport(query) {
  return request({
    url: '/pfserver-car/driver-api/accident/J02SafeAccidentAgainReport',
    method: 'post',
    data: query
  })
}
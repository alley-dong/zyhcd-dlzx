import request from '@/utils/request'

// 燃料管理-充电记录列表
export function FuelRechargeList(data) {
  return request({
    url: '/pfserver-car/fuelRecharge/I11FuelRechargeList',
    method: 'post',
    data
  })
}

  

// 导入充电记录
export function I11FuelRechargeImport(data) {
  return request({
    url: '/pfserver-car/fuelRecharge/I11FuelRechargeImport',
    method: 'post',
    data:data
  })
}

// 导入充电记录
export function M13PurchasesCostImport(data) {
  return request({
    url: '/pfserver-car/purchasesCost/M13PurchasesCostImport',
    method: 'post',
    data:data
  })
}
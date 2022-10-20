import request from '@/utils/request'

// 燃料管理-充电卡管理列表
export function getRechargeCardList(data) {
    return request({
        url: '/pfserver-car/fuelRechargeCard/I10FuelRechargeCardList',
        method: 'post',
        data
    })
}
// 燃料管理-燃料管理-新增/修改 充电卡
export function RechargeCardAdd(data) {
    return request({
        url: '/pfserver-car/fuelRechargeCard/I10FuelRechargeCardAdd',
        method: 'post',
        data
    })
}
// 燃料管理-充电卡详情
export function FuelRechargeCardInfo(data) {
    return request({
        url: '/pfserver-car/fuelRechargeCard/I10FuelRechargeCardInfo',
        method: 'post',
        data
    })
}
// 充电卡 车辆下拉
export function getCarNumList(data) {
    return request({
        url: '/pfserver-car/commonSelectBox/J02CarNumList',
        method: 'post',
        data
    })
}

// 删除充电卡
export function FuelRechargeCardDelete(data) {
    return request({
        url: '/pfserver-car/fuelRechargeCard/I10FuelRechargeCardDelete',
        method: 'post',
        data:data
    })
}


// 充电卡导入
export function I10FuelRechargeCardImport(data) {
    return request({
        url: '/pfserver-car/fuelRechargeCard/I10FuelRechargeCardImport',
        method: 'post',
        data:data
    })
}

 // 充电卡——车辆车牌号/车架号下拉列表
export function J02CarNumList(data) {
  return request({
      url: '/pfserver-car/commonSelectBox/J02CarNumList',
      method: 'post',
      data:data
  })
}
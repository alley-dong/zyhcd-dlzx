import request from '@/utils/request'
// 燃油使用计划列表下-车辆编辑
export function getI08CarFuelOilPlanEditSave(data) {
    return request({
        url: '/pfserver-car/fuelPlan/I08CarFuelOilPlanEditSave',
        method: 'post',
        data
    })
}
// 获取定额
export function getI09CarFuelOilPlanMoney(data) {
  return request({
      url: '/pfserver-car/fuelPlan/I09CarFuelOilPlanMoney',
      method: 'get',
      params:data
  })
}

export function I08CarFuelOilPlanByLicensePlateUpdate(data) {
  return request({
    url: '/pfserver-car/fuelPlan/I08CarFuelOilPlanByLicensePlateUpdate',
    method: 'post',
    data
  })
}

export function I08CarFuelOilPlanByLicensePlateInfo(data) {
  return request({
    url: '/pfserver-car/fuelPlan/I08CarFuelOilPlanByLicensePlateInfo',
    method: 'post',
    data
  })
}

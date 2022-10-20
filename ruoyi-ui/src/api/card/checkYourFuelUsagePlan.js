import request from '@/utils/request'

// 燃料管理-充电卡管理列表
export function I08CarFuelOilPlanList(data) {
    return request({
        url: '/pfserver-car/fuelPlan/I08CarFuelOilPlanList',
        method: 'post',
        data
    })
}
// 燃油使用计划列表下 车辆删除
export function FuelOilPlanDelete(id) {
    return request({
        url: '/pfserver-car/fuelPlan/I08CarFuelOilPlanDelete/'+id,
        method: 'get',
    })
}

export function I08CarFuelOilPlanByLicensePlateDelete(data) {
  return request({
    url: '/pfserver-car/fuelPlan/I08CarFuelOilPlanByLicensePlateDelete',
    method: 'post',
    data
  })
}

import request from '@/utils/request'
// 燃油使用计划-燃油使用计划列表
export function FuelOilPlanList(data) {
    return request({
        url: '/pfserver-car/fuelPlan/I07FuelOilPlanList',
        method: 'post',
        data
    })
}

// 燃油使用计划-生成燃油使用计划
export function FuelOilPlanAdd(data) {
    return request({
        url: '/pfserver-car/fuelPlan/I07FuelOilPlanAdd',
        method: 'post',
        data
    })
}
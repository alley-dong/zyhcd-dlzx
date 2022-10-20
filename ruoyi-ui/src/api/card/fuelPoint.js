import request from '@/utils/request'

// 绩效管理-路线下拉选
export function RoutList(data) {
    return request({
        url: '/pfserver-person/select/common/J04RoutList',
        method: 'post',
        data: data
    })
}

// 定点管理-定点列表
export function PlantsRouteList(data) {
    return request({
        url: '/pfserver-car/fuelPlace/I03OilPlantsRouteList',
        method: 'post',
        data
    })
}

// 定点管理-新增燃油定点
export function PlantsRouteAdd(data) {
    return request({
        url: '/pfserver-car/fuelPlace/I03OilPlantsRouteAdd',
        method: 'post',
        data: data
    })
}

// 定点管理-修改定点
export function PlantsRouteEditSave(data) {
    return request({
        url: '/pfserver-car/fuelPlace/I03OilPlantsRouteEditSave',
        method: 'post',
        data: data
    })
}
// 定点管理-定点详情信息
export function CardInfo(id) {
    return request({
        url: '/pfserver-car/fuelPlace/I03OilPlantsRouteInfo/' + id,
        method: 'get',
    })
}
// 定点管理-删除定点
export function delectCardInfo(id) {
    return request({
        url: '/pfserver-car/fuelPlace/I03OilPlantsRouteDelete/' + id,
        method: 'get',
    })
}

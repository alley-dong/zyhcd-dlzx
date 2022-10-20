import request from '@/utils/request'

// 燃料定额管理-燃油定额列表
export function PlantsMoneyList(data) {
    return request({
        url: '/pfserver-car/fuelQuota/I02OilPlantsMoneyList',
        method: 'post',
        data: data
    })
}


// 查询ETC管理-ETC定额详细
export function getQuota(id) {
    return request({
        url: '/pfserver-car/car/etcQuota/J04GetInfo/' + id,
        method: 'get'
    })
}

// 获取车型下拉选-ETC定额
export function vehicleModelBox() {
    return request({
        url: '/pfserver-person/select/common/G02CarList',
        method: 'post'
    })
}

// 新增油料管理-油料定额
export function addQuota(data) {
    return request({
        url: '/pfserver-car/fuelQuota/I02OilPlantsMoneyAdd',
        method: 'post',
        data: data
    })
}
// 燃料定额管理-燃油定额详情信息
export function geteQuota(id) {
    return request({
        url: '/pfserver-car/fuelQuota/I02OilPlantsMoneyInfo/' + id,
        method: 'get',
    })
}

// 修改油料管理-油料定额
export function updateQuota(data) {
    return request({
        url: '/pfserver-car/fuelQuota/I02OilPlantsMoneyEditSave',
        method: 'post',
        data: data
    })
}
// 删除油卡管理-油卡定额
export function delfuelQuota(id) {
    return request({
        url: '/pfserver-car/fuelQuota/I02OilPlantsMoneyDelete/' + id,
        method: 'get'
    })
}

// 获取路线下拉选
export function routerBox(data) {
    return request({
        url: '/pfserver-person/select/common/J04RoutList',
        method: 'post',
        data: data
    })
}


// 燃料定额管理-燃油定额详情信息
export function I09CarFuelOilPlanMoney(params) {
    return request({
        url: '/pfserver-car/fuelPlan//I09CarFuelOilPlanMoney',
        method: 'get',
        params
    })
}

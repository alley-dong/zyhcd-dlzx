import request from '@/utils/request'

// 维修保养费用列表
export function M06FuelCostList(data) {
    return request({
        url: '/pfserver-car/fuelCost/M06FuelCostList',
        method: 'post',
        data
    })
}
// 维修保养费用列表
export function M06FuelCostPullDown(data) {
    return request({
        url: '/pfserver-car/fuelCost/M06FuelCostPullDown',
        method: 'post',
        data
    })
}
// 费用统计-导入燃料费用统计列表
export function M06FuelCostImportn(data) {
    return request({
        url: '/pfserver-car/fuelCost/M06FuelCostImport',
        method: 'post',
        data
    })
}
// 费用统计-导入燃料费用统计列表
export function M13PurchasesCostList(data) {
    return request({
        url: '/pfserver-car/purchasesCost/M13PurchasesCostList',
        method: 'post',
        data
    })
}
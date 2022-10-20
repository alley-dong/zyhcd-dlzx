import request from '@/utils/request'

// 检车费用列表 
export function M09list(data) {
    return request({
        url: '/pfserver-car/depreciationCost/M09list',
        method: 'post',
        data
    })
}
// 折旧费用导出 
export function M09export(data) {
    return request({
        url: '/pfserver-car/car/depreciationCost/M09export',
        method: 'post',
        data
    })
}
// 折旧费用导入
export function M09CostImport(data) {
  return request({
      url: '/pfserver-car/depreciationCost/M09CostImport',
      method: 'post',
      data
  })
}


// 折旧费用——获取折旧费用车辆下拉列表
export function M09DepreciationCostPullDown(data) {
  return request({
      url: '/pfserver-car/depreciationCost/M09DepreciationCostPullDown',
      method: 'post',
     
  })
}
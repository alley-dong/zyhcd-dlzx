import request from '@/utils/request'
/* 
数据统计
*/
// 列表
export function vehicleConsumeMonthN01List(query) {
    return request({
      url:  '/pfserver-car/car/vehicleConsumeMonth/N01List',
      method: 'post',
      data: query
    })
} 
// 详情列表
export function vehicleConsumeMonthN03List(query) {
    return request({
      url:  '/pfserver-car/car/vehicleConsumeMonth/N03List',
      method: 'post',
      data: query
    })
} 

// 详情列表 编辑
export function vehicleConsumeMonthN03Edit(query) {
    return request({
      url:  '/pfserver-car/car/vehicleConsumeMonth/N03Edit',
      method: 'post',
      data: query
    })
} 

// 下拉
export function commonSelectBoxN02CarNumList(query) {
    return request({
      url:  '/pfserver-car/commonSelectBox/N02CarNumList',
      method: 'post',
      data: query
    })
} 
//承运商成本核算-列表
export function transportTaskN05TaskList(query) {
  return request({
    url:  '/pfserver-car/transportTask/N05TaskList',
    method: 'post',
    data: query
  })
} 

//承运商成本核算-线路下拉选择
export function transportTaskN05RouteName(query) {
  return request({
    url:  '/pfserver-car/transportTask/N05RouteName',
    method: 'get',
  })
} 


//任务成本核算列表
export function costN04List(query) {
  return request({
    url:  '/pfserver-car/cost/N04List',
    method: 'post',
    data:query
  })
} 
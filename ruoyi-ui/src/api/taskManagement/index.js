import request from '@/utils/request'
/* 
任务管理
*/
// 列表
export function tRouteP01RouteList(query) {
    return request({
      url:  '/pfserver-car/tRoute/P01RouteList',
      method: 'post',
      data: query
    })
} 

// 删除
export function tRouteP01RouteDelete(id) {
  return request({
    url:  `/pfserver-car/tRoute/P01RouteDelete/${id}`,
    method: 'get',
  })
} 

// 任务管理-线路详细信息
export function tRouteP01RouteInfo(data) {
  return request({
    url:  `/pfserver-car/tRoute/P01RouteInfo`,
    method: 'post',
    data
  })
} 
// 任务管理-修改线路
export function tRouteP02RouteEditSave(query) {
  return request({
    url:  '/pfserver-car/tRoute/P02RouteEditSave',
    method: 'post',
    data: query
  })
} 

// 查询任务管理-运输任务列表
export function transportTaskP04TransportTaskList(query) {
  return request({
    url:  '/pfserver-car/transportTask/P04TransportTaskList',
    method: 'post',
    data: query
  })
} 

// 获取任务管理-运输任务详细信息
export function transportTaskP04TransportTaskInfo(query) {
  return request({
    url:  '/pfserver-car/transportTask/P04TransportTaskInfo',
    method: 'post',
    data: query
  })
} 

// 任务管理-运输任务加班列表
export function transportTaskOvertimeP11TransportTaskOvertimeList(query) {
  return request({
    url:  '/pfserver-car/transportTaskOvertime/P11TransportTaskOvertimeList',
    method: 'post',
    data: query
  })
} 

// 任务管理-运输任务点检列表
export function transportTaskOvertimeP09TransportTaskInspectList(query) {
  return request({
    url:  '/pfserver-car/transportTaskInspect/P09TransportTaskInspectList',
    method: 'post',
    data: query
  })
} 

// 任务管理-运输任务点检详细信息
export function transportTaskInspectP10TransportTaskInspectInfo(query) {
  return request({
    url:  '/pfserver-car/transportTaskInspect/P10TransportTaskInspectInfo',
    method: 'post',
    data: query
  })
} 

// 任务管理-运输任务异常上报列表
export function transportExceptionP06TransportTaskExceptionList(query) {
  return request({
    url:  '/pfserver-car/transportException/P06TransportTaskExceptionList',
    method: 'post',
    data: query
  })
} 

// 任务管理-运输任务异常上报详细信息
export function transportExceptionP08TransportTaskExceptionInfo(query) {
  return request({
    url:  '/pfserver-car/transportException/P08TransportTaskExceptionInfo',
    method: 'post',
    data: query
  })
} 


// 任务管理-运输任务异常上报审核
export function transportExceptionP07TransportTaskExceptionCheck(query) {
  return request({
    url:  '/pfserver-car/transportException/P07TransportTaskExceptionCheck',
    method: 'post',
    data: query
  })
} 
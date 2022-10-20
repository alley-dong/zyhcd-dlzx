import request from '@/utils/request'

// 列表
export function vacationE01VacationList(query) {
  return request({
    url: '/pfserver-car/driver-api/vacation/E01VacationList',
    method: 'post',
    data: query
  })
}
// 考勤-新增请假记录
export function vacationE04VacationAdd(query) {
  return request({
    url: '/pfserver-car/driver-api/vacation/E04VacationAdd',
    method: 'post',
    data: query
  })
}
export function E04VacationEditSave(query) {
  return request({
    url: '/pfserver-car/driver-api/vacation/E04VacationEditSave',
    method: 'post',
    data: query
  })
}
//考勤-请假详情（区分四种状态）
export function vacationE02VacationDetail(query) {
  return request({
    url: '/pfserver-car/driver-api/vacation/E02VacationDetail',
    method: 'get',
    params: query
  })
}
//考勤-撤销请假申请接口
export function vacationE03UndoVacation(query) {
  return request({
    url: '/pfserver-car/driver-api/vacation/E03UndoVacation',
    method: 'post',
    data: query
  })
}

//考勤——请假类型列表
export function vacationE03VacationTypeList(query) {
  return request({
    url: '/pfserver-car/driver-api/vacation/E03VacationTypeList',
    method: 'post',
    data: query
  })
}

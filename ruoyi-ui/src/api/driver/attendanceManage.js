import request from '@/utils/request'

// 列表
export function attendanceManageList(query) {
    return request({
      url: '/pfserver-person/vacation/A49ClockInList',
      method: 'post',
      data: query
    })
}

// 查看页面渲染
export function getAttendanceManageDetails(id) {
  return request({
    url: '/pfserver-person/vacation/A50ClockInInfo/' + id,
    method: 'get'
  })
}

// 请假统计列表
export function leaveStatisticsList(query) {
    return request({
      url: '/pfserver-person/vacation/A52ClockInStatistics',
      method: 'get',
      params: query
    })
}

// 请假统计列表
export function vacationA51ClockInCheck(query) {
  return request({
    url: '/pfserver-person/vacation/A51ClockInCheck',
    method: 'post',
    data: query
  })
}
import request from '@/utils/request'
// 短途绩效记录列表
export function G13List (data) {
  return request({
    url: "/pfserver-person/person/performanceShortHaulRecord/G13List",
    method: "post",
    data
  })
}
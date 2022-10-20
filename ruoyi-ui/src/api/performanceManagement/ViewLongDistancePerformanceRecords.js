import request from '@/utils/request'
// 长途绩效记录详情
export function G09GetInfo (id) {
  return request({
    url: "/pfserver-person/person/performanceRecord/G09GetInfo/" + id,
    method: "get",
  })
}
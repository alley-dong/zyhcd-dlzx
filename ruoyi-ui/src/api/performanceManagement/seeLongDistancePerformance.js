import request from '@/utils/request'
// 绩效管理-查看绩效
export function G01GetInfo(id) {
    return request({
        url: "/pfserver-person/person/performance/G01GetInfo/" + id,
        method: "get",
    })
}
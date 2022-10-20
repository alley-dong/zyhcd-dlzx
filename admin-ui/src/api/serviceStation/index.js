import request from '@/utils/request'
// 维修服务站管理列表
export function garageCarH15List(query) {
    return request({
        url: '/pfserver-car/car/garageCar/H15List',
        method: 'post',
        data: query
    })
}
// 维修服务站管理新增 
export function garageCarH15Add(query) {
    return request({
        url: '/pfserver-car/car/garageCar/H15Add',
        method: 'post',
        data: query
    })
}
import request from '@/utils/request'

// 物品领用——领用记录列表
export function goodsReceivingRecordL08List(query) {
    return request({
        url: '/pfserver-car/driver-api/goodsReceivingRecord/L08List',
        method: 'post',
        data: query
    })
}

//物品领用——物品记录详情
export function goodsReceivingRecordL08GetInfo(query) {
    return request({
        url: '/pfserver-car/driver-api/goodsReceivingRecord/L08GetInfo',
        method: 'post',
        data: query
    })
}

//   //物品领用——物品记录详情 copy
// export function goodsReceivingRecordL08GetInfo(query) {
//   return request1({   
//     url: '/driver-api/goodsReceivingRecord/L08GetInfo',
//     method: 'post',
//     data: query
//   })
// }


//物品领用-领用提交撤回
export function goodsReceivingRecordL08Recall(query) {
    return request({
        url: '/pfserver-car/driver-api/goodsReceivingRecord/L08Recall',
        method: 'post',
        data: query
    })
}


//物品领用-确认领取
export function goodsReceivingRecordL10Affirm(query) {
    return request({
        url: '/pfserver-car/driver-api/goodsReceivingRecord/L10Affirm',
        method: 'post',
        data: query
    })
}


// 物品领用——再次提交申请
export function L12EditSave(query) {
    return request({
        url: '/pfserver-car//driver-api/goodsReceivingRecord/L12EditSave',
        method: 'post',
        data: query
    })
}
//物品下拉
export function goodsReceivingRecordT01GoodsList(query) {
    return request({
        url: '/system/administrator-app/goodsReceivingRecord/T01GoodsList',
        method: 'post',
        data: query
    })
}
// 物品领用——物品类别列表
export function L01GoodTypeList(query) {
    return request({
        url: '/pfserver-car/driver-api/goodsReceivingRecord/L01GoodTypeList',
        method: 'post',
        data: query
    })
}
// 物品领用——根据物品类别ID获取物品(备件列表)
export function L07GetGoodList(query) {
    return request({
        url: '/pfserver-car/driver-api/goodsReceivingRecord/L07GetGoodList',
        method: 'post',
        data: query
    })
}
// 物品领用申请——工作餐
export function L03Add(query) {
    return request({
        url: '/pfserver-car/driver-api/goodsReceivingRecord/L03Add',
        method: 'post',
        data: query
    })
}

// 物品领用申请——低值易耗品 / 轮胎
export function L04Add(query) {
    return request({
        url: '/pfserver-car//driver-api/goodsReceivingRecord/L04Add',
        method: 'post',
        data: query
    })
}
// 路线下拉选
export function RoutList(data) {
    return request({
        url: '/pfserver-person/select/common/J04RoutList',
        method: 'post',
        data: data
    })
}
// 物品领用申请——备件
export function L06Add(query) {
    return request({
        url: '/pfserver-car//driver-api/goodsReceivingRecord/L06Add',
        method: 'post',
        data: query
    })
}

// 物品领用申请——尿素
export function L02Add(query) {
    return request({
        url: '/pfserver-car//driver-api/goodsReceivingRecord/L02Add',
        method: 'post',
        data: query
    })
}

export function goodsReceivingRecordL07GetGoodList(query) {
    return request({
        url: '/pfserver-car//driver-api/goodsReceivingRecord/L07GetGoodList',
        method: 'post',
        data: query
    })
}
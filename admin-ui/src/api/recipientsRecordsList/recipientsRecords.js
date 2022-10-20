import request from '@/utils/request'


// 物品领用——物品下拉列表
export function T01GoodsList(query) {
    return request({
        url: '/system/administrator-app/goodsReceivingRecord/T01GoodsList',
        method: 'post',
        data: query
    })
}

// 物品领用——记录列表
export function T01AdminGGoodsReceivingList(query) {
    return request({
        url: '/system/administrator-app/goodsReceivingRecord/T01AdminGGoodsReceivingList',
        method: 'post',
        data: query
    })
}
// 物品领用-物品领用记录详情
export function T01AdminGGoodsReceivingInfo(query) {
    return request({
        url: '/system/administrator-app/goodsReceivingRecord/T01AdminGGoodsReceivingInfo',
        method: 'post',
        data: query
    })
}

//盘点页面点击新增选择类型-回显 
export const goodsCheckK14AddGetInfo = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsCheck/K14AddGetInfo',
        method: 'post',
        data
    })
}
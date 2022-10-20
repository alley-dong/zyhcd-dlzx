import request from '@/utils/request'

//  物品类别管理-查看
export const goodsTypeK01List = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsType/K01List',
        method: 'post',
        data
    })
}

//  物品类别管理-编辑页面回显
export const goodsTypeK01GetInfo = (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsType/K01GetInfo/' + id,
        method: 'get',
    })
}


//  物品类别管理-删除
export const goodsK01Delete = (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsType/K01Delete/' + id,
        method: 'get',
    })
}

// 物品类别-新增
export const goodsTypeK01Add = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsType/K01Add',
        method: 'post',
        data
    })
}

// 物品类别管理-修改
export const goodsTypeK01EditSave = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsType/K01EditSave',
        method: 'post',
        data
    })
}


//  物品管理-列表
export const goodsK02List = (data) => {
    return request({
        url: '/pfserver-parts/parts/goods/K02List',
        method: 'post',
        data
    })
}


// 物品管理-编辑
export const goodsK02EditSave = (data) => {
    return request({
        url: '/pfserver-parts/parts/goods/K02EditSave',
        method: 'post',
        data
    })
}

//  物品管理-删除
export const goodsK02Delete = (id) => {
    return request({
        url: '/pfserver-parts/parts/goods/K02Delete/' + id,
        method: 'get',
    })
}

// // 物品管理-编辑
// export const goodsK02EditSave = (data) => {
//     return request({
//         url: '/pfserver-parts/parts/goods/K02EditSave',
//         method: 'post',
//         data
//     })
// }

// 
export const goodsK02AddList = (data) => {
    return request({
        url: '/pfserver-parts/parts/goods/K02Add',
        method: 'post',
        data
    })
}
//  物品管理-编辑回显 
export const goodsK02EditSave1 = (id) => {
    return request({
        url: '/pfserver-parts/parts/goods/K02EditSave/' + id,
        method: 'get',
    })
}


//  入库管理-列表
export const goodsK03List = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K03List',
        method: 'post',
        data
    })
}

//  入库管理-删除 
export const goodsInventoryK03Delete = (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K03Delete/' + id,
        method: 'get',
    })
}


//物品编号下拉
export const goodsInventoryK13EditGetInfo = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K13EditGetInfo',
        method: 'post',
        data
    })
}

//入库管理-新增
export const goodsInventoryK04Add = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K04Add',
        method: 'post',
        data
    })
}

//物入库管理编辑页面查看Code
export const goodsInventoryK05EditGetInfo = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K05EditGetInfo',
        method: 'post',
        data
    })
}

//入库管理-修改
export const goodsInventoryK05EditSave = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K05EditSave',
        method: 'post',
        data
    })
}

//出库管理列表查看
export const goodsInventoryK06List = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K06List',
        method: 'post',
        data
    })
}

//出库管理逻辑删除
export const goodsInventoryK06Delete = (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K06Delete/' + id,
        method: 'get',
    })
}

//出库管理-新增
export const goodsInventoryK07Add = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K07Add',
        method: 'post',
        data
    })
}

//新增-物品编号查询
export const goodsInventoryK07AddGetInfo = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K07AddGetInfo',
        method: 'post',
        data
    })
}

//出库管理编辑-物品编号查询
export const goodsInventoryK08EditGetInfo = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K08EditGetInfo',
        method: 'post',
        data
    })
}


//出库管理编辑
export const goodsInventoryK08EditSave = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K08EditSave',
        method: 'post',
        data
    })
}


//出库管理编辑
export const goodsInventoryK09List = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K09List',
        method: 'post',
        data
    })
}

//库存管理查看明细
export const goodsInventoryK10List = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K10List' ,
        method: 'post',
        data
    })
}


//报废管理-查看
export const goodsInventoryK11List= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K11List' ,
        method: 'post',
        data
    })
}


// //  报废管理删除-删除
export const goodsInventoryK11Delete = (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K11Delete/' + id,
        method: 'get',
    })
}

//报废管理-新增
export const goodsInventoryK12Add= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K12Add' ,
        method: 'post',
        data
    })
}


//报废管理编辑页面查询Code
export const goodsInventoryK13EditGetInfod= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K13EditGetInfo' ,
        method: 'post',
        data
    })
}



//报废管理编辑 
export const goodsInventoryK13EditSave= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/K13EditSave' ,
        method: 'post',
        data
    })
}

//盘点查看 
export const goodsCheckK14List= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsCheck/K14List' ,
        method: 'post',
        data
    })
}

//盘点页面点击新增选择类型-回显 
export const goodsCheckK14AddGetInfo= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsCheck/K14AddGetInfo' ,
        method: 'post',
        data
    })
}

//盘点页面点击新增
export const goodsCheckK14Add= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsCheck/K14Add' ,
        method: 'post',
        data
    })
}
// //  报废管理删除-删除
export const goodsCheckK14Delete = (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsCheck/K14Delete/' + id,
        method: 'get',
    })
}

// //  盘点管理-新增页面的列表数据
export const checkItemK15CheckList = (data) => {
    return request({
        url: '/pfserver-parts/parts/checkItem/K15CheckList',
        method: 'post',
        data
    })
}

// 盘点管理-新增界面计算盘点数量
export const checkItemK15EditSave = (data) => {
    return request({
        url: '/pfserver-parts/parts/checkItem/K15EditSave',
        method: 'post',
        data
    })
}

// 盘点管理-增加界面的提交
export const checkItemK15Add = (data) => {
    return request({
        url: '/pfserver-parts/parts/checkItem/K15Add',
        method: 'post',
        data
    })
}


// 盘点管理-添加界面的编辑保存
export const checkItemK15Preservation= (data) => {
    return request({
        url: '/pfserver-parts/parts/checkItem/K15Preservation',
        method: 'post',
        data
    })
}

// 领用管理-列表
export const goodsReceivingRecordK17List= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K17List',
        method: 'post',
        data
    })
}

// 领用管理-列表
export const goodsReceivingRecordK18GetInfo= (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K18GetInfo/'+id,
        method: 'get',
    })
}

// 审批
export const goodsReceivingRecordK18ListGetInfo= (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K18ListGetInfo',
        method: 'post',
        data
    })
}

//工作餐
export const goodsReceivingRecordK19GetInfo= (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K19GetInfo/'+id,
        method: 'get',
    })
}

//工作餐
export const goodsReceivingRecordK18List =  (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K18GetInfo/'+id,
        method: 'get',
    })
}

//备件
export const goodsReceivingRecordK21List =  (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K21GetInfo/'+id,
        method: 'get',
    })
}

//查看低值易耗品 轮胎
export const goodsReceivingRecordK20GetInfo =  (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K20GetInfo/'+id,
        method: 'get',
    })
}
//  入库管理-回显
export const goodsInventoryGetInfo = (id) => {
    return request({
        url: '/pfserver-parts/parts/goodsInventory/getInfo/'+id,
        method: 'get',
    })
}

// // 审批
export const goodsReceivingRecordK18EditSave = (data) => {
    return request({
        url: '/pfserver-parts/parts/goodsReceivingRecord/K18EditSave',
        method: 'post',
        data
    })
}


//首页——获取物品领用名称
export function getLingyongName(query) {
  return request({
    url: '/pfserver-parts/parts/goodsReceivingRecord/T01AdminGGoodsReceivingInfo',
    method: 'post',
    data: query
  })
}

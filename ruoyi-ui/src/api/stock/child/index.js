import service1 from '@/api/stock/demo'

//  物品类别管理-查看
// export const goodsTypeK01List = (data) => {
//     return service1({
//         url: '/pfserver-parts/parts/goodsType/K01List',
//         method: 'post',
//         data
//     })
// }

//入库管理-新增
export const goodsInventoryK04Add = (data) => {
    return service1({
        url: '/parts/goodsInventory/K04Add',
        method: 'post',
        data
    })
}
//  入库管理-回显
export const goodsInventoryGetInfo = (id) => {
    return service1({
        url: '/parts/goodsInventory/getInfo/'+id,
        method: 'get',
    })
}


//  入库管理-列表
export const goodsK03List = (data) => {
    return service1({
        url: '/parts/goodsInventory/K03List',
        method: 'post',
        data
    })
}

//出库管理-新增
export const goodsInventoryK07Add = (data) => {
    return service1({
        url: '/parts/goodsInventory/K07Add',
        method: 'post',
        data
    })
}

//出库管理编辑
export const goodsInventoryK08EditSave = (data) => {
    return service1({
        url: '/parts/goodsInventory/K08EditSave',
        method: 'post',
        data
    })
}

//库存管理查看明细
export const goodsInventoryK10List = (data) => {
    return service1({
        url: '/parts/goodsInventory/K10List' ,
        method: 'post',
        data
    })
}
//报废管理-查看
export const goodsInventoryK11List= (data) => {
    return service1({
        url: '/parts/goodsInventory/K11List' ,
        method: 'post',
        data
    })
}

//盘点页面点击新增
export const goodsCheckK14Add= (data) => {
    return service1({
        url: '/parts/goodsCheck/K14Add' ,
        method: 'post',
        data
    })
}

// //  盘点管理-新增页面的列表数据
export const checkItemK15CheckList = (data) => {
    return service1({
        url: '/parts/checkItem/K15CheckList',
        method: 'post',
        data
    })
}
// 盘点管理-增加界面的提交
export const checkItemK15Add = (data) => {
    return service1({
        url: '/parts/checkItem/K15Add',
        method: 'post',
        data
    })
}

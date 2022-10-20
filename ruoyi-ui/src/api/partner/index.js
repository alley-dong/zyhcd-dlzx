import request from '@/utils/request'

// 查询部门列表
export function customerContractList(data) {
    return request({
        url: '/pfserver-person/contract/F01CustomerContractList',
        method: 'post',
        data
    })
}

// 查询部门列表-添加
export function CustomerContractAdd(data) {
    return request({
        url: '/pfserver-person/contract/F02CustomerContractAdd',
        method: 'post',
        data
    })
}

// 查询部门列表-回显
export function CustomerContractInfo(id) {
    return request({
        url: '/pfserver-person/contract/F04CustomerContractInfo/' + id,
        method: 'get',
    })
}

// 客户/供应商合同页面-删除合同 
export function CustomerContractDelete(id) {
    return request({
        url: '/pfserver-person/contract/F01CustomerContractDelete/' + id,
        method: 'get',
    })
}

// 客户/供应商合同 标记/取消预警 
export function CustomerContractStatus(data) {
    return request({
        url: '/pfserver-person/contract/F01CustomerContractStatus',
        method: 'post',
        data
    })
}

// 供应商合同列表
export function CarriersContractList(data) {
    return request({
        url: '/pfserver-person/contract/F05CarriersContractList',
        method: 'post',
        data
    })
}

// 供应商合同列表
export function CarriersList(data) {
    return request({
        url: '/pfserver-person/carrier/F09CarriersList',
        method: 'post',
        data
    })
}

// 供应商管理页面-新增供应商
export function CarriersAdd(data) {
    return request({
        url: '/pfserver-person/carrier/F09CarriersAdd',
        method: 'post',
        data
    })
}

// 供应商管理页面-供应商详细信息
export function CarriersInfo(id) {
    return request({
        url: '/pfserver-person/carrier/F09CarriersInfo/'+id,
        method: 'get',
    })
}


// 供应商管理页面-修改供应商
export function CarriersEditSave(data) {
    return request({
        url: '/pfserver-person/carrier/F09CarriersEditSave',
        method: 'post',
        data
    })
}

// 供应商评价 删除
export function evaluateF12ContractEvaluateDelete(id) {
    return request({
        url: '/pfserver-person/evaluate/F12ContractEvaluateDelete/' + id,
        method: 'get',
    })
}


// 供应商管理页面-修改供应商
export function CarriersDelete(id) {
    return request({
        url: '/pfserver-person/carrier/F09CarriersDelete/' + id,
        method: 'get',
    })
}
// 供应商管理页面-新增供应商
export function ContractMetricsTypePullDown() {
    return request({
        url: '/pfserver-person/metricsType/F10ContractMetricsTypePullDown',
        method: 'get',
    })
}

// 评价指标类型列表
export function ContractMetricsTypeList(data) {
    return request({
        url: '/pfserver-person/metricsType/F10ContractMetricsTypeList',
        method: 'post',
        data
    })
}

// 评价指标类型新增
export function ContractMetricsTypeAdd(data) {
    return request({
        url: '/pfserver-person/metricsType/F10ContractMetricsTypeAdd',
        method: 'post',
        data
    })
}

// 评价指标类型详情
export function ContractMetricsTypeInfo(id) {
    return request({
        url: '/pfserver-person/metricsType/F10ContractMetricsTypeInfo/'+id ,
        method: 'get',
        // params:{
        //     id
        // }
    })
}

// 评价指标类型删除
export function ContractMetricsTypeDelete(id) {
    return request({
        url: '/pfserver-person/metricsType/F10ContractMetricsTypeDelete/' + id,
        method: 'get',
        // params:{
        //     ids:id
        // }
    })
}


// 评价指标类型修改
export function ContractMetricsTypeEditSave(data) {
    return request({
        url: '/pfserver-person/metricsType/F10ContractMetricsTypeEditSave',
        method: 'post',
        data
    })
}

// 评价指标类型下 指标列表
export function ContractMetricsList(data) {
    return request({
        url: '/pfserver-person/metrics/F11ContractMetricsList',
        method: 'post',
        data
    })
}

// 评价指标类型下 指标删除
export function F11ContractMetricsDelete(id) {
    return request({
        url: '/pfserver-person/metrics/F11ContractMetricsDelete/' + id,
        method: 'get',
        // params:{
        //     ids:id
        // }
    })
}

// 评价指标类型下 新增
export function F11ContractMetricsAdd(data) {
    return request({
        url: '/pfserver-person/metrics/F11ContractMetricsAdd',
        method: 'post',
        data
    })
}

// 评价指标类型下 修改
export function F11ContractMetricsEditSave(data) {
    return request({
        url: '/pfserver-person/metrics/F11ContractMetricsEditSave',
        method: 'post',
        data
    })
}

// 评价指标类型下 详情
export function F11ContractMetricsInfo(id) {
    return request({
        url: '/pfserver-person/metrics/F11ContractMetricsInfo/' + id,
        method: 'get',
        // params:{
        //     ids:id
        // }
    })
}

// 评价指标类型列表
export function F12ContractEvaluateList(data) {
    return request({
        url: '/pfserver-person/evaluate/F12ContractEvaluateList',
        method: 'post',
        data
    })
}

// 新增供应商评价
export function F12ContractEvaluateAdd(data) {
    return request({
        url: '/pfserver-person/evaluate/F12ContractEvaluateAdd',
        method: 'post',
        data
    })
}
// 供应商评价详情
export function F13ContractEvaluateInfo(data) {
    return request({
        url: '/pfserver-person/evaluate/F13ContractEvaluateInfo',
        method: 'post',
        data
    })
}

// 导出
export function evaluateExport(params) {
    return request({
        url: '/pfserver-person/evaluate/export',
        method: 'get',
        params
    })
}
// 修改客户合同
export function F03CustomerContractEditSave(data) {
    return request({
        url: '/pfserver-person/contract/F03CustomerContractEditSave',
        method: 'post',
        data
    })
}

// 供应商评价列表下 评价详情
export function F14ContractEvaluateMetricsInfo(id) {
    return request({
        url: '/pfserver-person/evaluateMetrics/F14ContractEvaluateMetricsInfo/' + id,
        method: 'get',
        // params:{
        //     ids:id
        // }
    })
}

// 供应商评价列表下 修改评价
export function F13ContractEvaluateMetricsEditSave(data) {
    return request({
        url: '/pfserver-person/evaluateMetrics/F13ContractEvaluateMetricsEditSave',
        method: 'post',
        data
    })
}

// 供应商保存
export function F12ContractEvaluateEditSave(data) {
    return request({
        url: '/pfserver-person/evaluate/F12ContractEvaluateEditSave',
        method: 'post',
        data
    })
}

// 新增供应商合同
export function F06CarriersContractAdd(data) {
    return request({
        url: '/pfserver-person/contract/F06CarriersContractAdd',
        method: 'post',
        data
    })
}

// 修改供应商合同
export function F07CarriersContractEditSave(data) {
    return request({
        url: '/pfserver-person/contract/F07CarriersContractEditSave',
        method: 'post',
        data
    })
}
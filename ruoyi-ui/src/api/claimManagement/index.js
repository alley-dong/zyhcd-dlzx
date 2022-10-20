import request from '@/utils/request'

// 查询驾驶员-司机档案列表
export function insuranceClaimsE18List(data) {
    return request({
        url: '/pfserver-car/car/insuranceClaims/E18List',
        method: 'post',
        data
    })
}

// 理赔管理-事故时间下拉选  vehicleName
export function commonSelectBoxE18ShiGuTimeList(data) {
    return request({
        url: '/pfserver-car/commonSelectBox/E18ShiGuTimeList',
        method: 'post',
        data
    })
}

// 理赔管理-事故车牌号下拉选
export function commonSelectBoxE18ShiGuCarNumList(data) {
    return request({
        url: '/pfserver-car/commonSelectBox/E18ShiGuCarNumList',
        method: 'post',
        data
    })
}

// 理赔管理-编辑页面渲染
export function insuranceClaimsE18GetInfo(id) {
    return request({
        url: '/pfserver-car/car/insuranceClaims/E18GetInfo/' + id,
        method: 'get',
    })
}

// 理赔管理-理赔删除
export function insuranceClaimsE18Delete(id) {
    return request({
        url: '/pfserver-car/car/insuranceClaims/E18Delete/' + id,
        method: 'get',
    })
}

// 理赔管理-理赔新增
export function insuranceClaimsE18Add(data) {
    return request({
        url: '/pfserver-car/car/insuranceClaims/E18Add',
        method: 'post',
        data
    })
}
// 理赔管理-理赔编辑
export function insuranceClaimsE18Edit(data) {
    return request({
        url: '/pfserver-car/car/insuranceClaims/E18Edit',
        method: 'post',
        data
    })
}

// 理赔管理-理赔编辑
export function E18GetInfo(id) {
    return request({
        url: `/pfserver-car/car/insuranceClaims/E18GetInfo/${id}`,
        method: 'get',
    })
}


// 理赔管理-理赔编辑
export function E18ParticipantAdd(data) {
    return request({
        url: `/pfserver-car/participant/E18ParticipantAdd`,
        method: 'post',
        data
    })
}
// 理赔管理-理赔编辑
export function E18ParticipantEditSave(data) {
    return request({
        url: `/pfserver-car/participant/E18ParticipantEditSave`,
        method: 'post',
        data
    })
}
// 理赔管理-理赔编辑
export function E18DeleteParticipant(id) {
    return request({
        url: `/pfserver-car/participant/E18DeleteParticipant/${id}`,
        method: 'get',
    })
}
// 理赔管理-理赔编辑
export function E18ParticipantInfo(id) {
    return request({
        url: `/pfserver-car/participant/E18ParticipantInfo/${id}`,
        method: 'get',
    })
}
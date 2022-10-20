import request from '@/utils/request'

// 未读
export function N02AdministratorQueryUnreadList(query) {
    return request({
        url: '/system/administrator-app/msg/N02AdministratorQueryUnreadList',
        method: 'post',
        data: query
    })
}
// 个人消息-消息列表接口
export function N03AdministratorMessageList(query) {
    return request({
        url: '/system/administrator-app/msg/N03AdministratorMessageList',
        method: 'post',
        data: query
    })
}
// 个人消息-消息删除接口
export function msgN03AdministratorDelMessage(query) {
    return request({
        url: '/system/administrator-app/msg/N03AdministratorDelMessage',
        method: 'post',
        data: query
    })
}

// 个人消息-消息删除接口
export function N03AdministratorReadStatus(query) {
    return request({
        url: '/system/administrator-app/msg/N03AdministratorReadStatus',
        method: 'post',
        data: query
    })
}

// 个人消息-消息删除接口
export function Q01ReadStatus(query) {
    return request({
        url: '/system/administrator-app/task/Q01ReadStatus',
        method: 'post',
        data: query
    })
}


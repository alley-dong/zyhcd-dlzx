import request from '@/utils/request'

// 补助管理-补助详情
export function C06Detail(id) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C06Detail/' + id,
        method: 'get',
    })
}

//单条编辑
export function C09EditSave(data) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C09EditSave',
        method: 'post',
        data
    })
}

// 工资核算保存
export function C09CheckSave(id) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C09CheckSave/' + id,
        method: 'get',
    })
}

//工资核算提交
export function C09Submit(data) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C09Submit',
        method: 'post',
        data
    })
}

//工资核算提交
export function C09Info(id) {
    return request({
        url: '/pfserver-parts/parts/salaryRecords/C09Info/'+id,
        method: 'get',
    })
}
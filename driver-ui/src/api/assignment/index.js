import request from '@/utils/request'

// 列表
export function transportTaskM01TransportTaskList(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M01TransportTaskList',
        method: 'post',
        data: query
    })
}


// 驾驶员运输任务记录列表
export function transportM01TransportTaskRecordList(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M01TransportTaskRecordList',
        method: 'post',
        data: query
    })
}

// 运输任务-运输任务详情
export function transportTaskM03TransportTaskInfo(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M03TransportTaskInfo',
        method: 'post',
        data: query
    })
}

// 运输任务-运输任务点检记录列表
export function transportTaskM15TaskInspectList(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M15TaskInspectList',
        method: 'post',
        data: query
    })
}


// 运输任务-运输任务异常情况记录详情
export function transportTaskM07TransportTaskExceptionInfo(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M07TransportTaskExceptionInfo',
        method: 'post',
        data: query
    })
}


// 运输任务-提交运输点检记录
export function transportM13SubmitTaskInspect(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M13SubmitTaskInspect',
        method: 'post',
        data: query
    })
}

// 运输任务-运输任务点检记录详情
export function transportM17TaskInspectInfo(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M17TaskInspectInfo',
        method: 'post',
        data: query
    })
}

// 运输任务-运输任务异常上报
export function transportM05SubmitTaskException(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M05SubmitTaskException',
        method: 'post',
        data: query
    })
}


//运输任务-运输任务异常情况记录列表
export function transportM06TransportTaskExceptionList(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M06TransportTaskExceptionList',
        method: 'post',
        data: query
    })
}
//运输任务-运输任务-运输任务异常撤销
export function transportTaskM08RevocationTaskException(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M08RevocationTaskException',
        method: 'post',
        data: query
    })
}
//运输任务-运输任务异常再次上报

export function transportTaskM11ResubmitTaskException(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/M11ResubmitTaskException',
        method: 'post',
        data: query
    })
}

export function TransportTaskUpload(query) {
    return request({
        url: '/pfserver-car/driver-api/transportTask/TransportTaskUpload',
        method: 'post',
        data: query
    })
}
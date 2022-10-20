import request from '@/utils/request'

// 列表
export function noticeManageList(query) {
    return request({
      url: '/pfserver-person/person/notice/A42List',
      method: 'post',
      data: query
    })
}

// 选择参与人员列表 群主
export function selectParticipantsList(query) {
  return request({
    url: '/pfserver-person/person/driver/A19List',
    method: 'post',
    data: query
  })
}
// 选择人员
export function selectParticipants(query) {
  return request({
    url: '/pfserver-person/person/driver/A19ListTong',
    method: 'post',
    data: query
  })
}

// 群组列表
export function selectGroupList(query) {
  return request({
    url: '/pfserver-person/person/dDriverGroup/A39ListT',
    method: 'post',
    data: query
  })
}

// 已读列表
export function getReadedListDetails(query) {
  return request({
    url: '/pfserver-person/person/notice/A47ListRead/',
    method: 'post',
    data: query
  })
}

// 新增
export function noticeAdd(query) {
  return request({
    url: '/pfserver-person/person/notice/A43Add',
    method: 'post',
    data: query
  })
}
// 培训管理-新增培训
export function training(query) {
  return request({
    url: '/pfserver-person/person/training/A34Add',
    method: 'post',
    data: query
  })
}
// 培训管理-培训统计列表
export function trainingListRead(query) {
  return request({
    url: '/pfserver-person/person/training/A38ListRead',
    method: 'post',
    data: query
  })
}
// 培训管理-编辑培训
export function trainingEdit(query) {
  return request({
    url: '/pfserver-person/person/training/A36Edit',
    method: 'post',
    data: query
  })
}
// 培训管理-查看培训
export function trainingGetInfoCK(id) {
  return request({
    url: '/pfserver-person/person/training/A37GetInfoCK/'+id,
    method: 'get',
  })
}
// 培训管理-编辑页面渲染
export function trainingGetInfo(id) {
  return request({
    url: '/pfserver-person/person/training/A36GetInfo/'+id,
    method: 'get',
  })
}
// 编辑页面渲染
export function getNoticeDetails(id) {
  return request({
    url: '/pfserver-person/person/notice/A45GetInfo/' + id,
    method: 'get'
  })
}

// 查看按钮点击详情
export function noticeManageDetails(id) {
  return request({
    url: '/pfserver-person/person/notice/A46GetInfoCk/' + id,
    method: 'get'
  })
}

// 删除
export function noticeDelete(tableId) {
  return request({
    url: '/pfserver-person/person/notice/A42Delete/' + tableId,
    method: 'get',
  })
}

// 试卷管理-试卷详情
export function dExamPaperGetInfo(id) {
  return request({
    url: '/pfserver-person/person/dExamPaperProblem/A18GetInfo/'+id,
    method: 'get',
  })
}

// 公告管理-发布状态修改
export function noticeA42Change(id) {
  return request({
    url: '/pfserver-person/person/notice/A42Change/'+id,
    method: 'get',
  })
}

// 公告管理-编辑公告
export function noticeA45Edit(data) {
  return request({
    url: '/pfserver-person/person/notice/A45Edit',
    method: 'post',
    data
  })
}
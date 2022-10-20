import request from '@/utils/request'

// 列表
export function paperManageList(query) {
    return request({
      url: '/pfserver-person/person/dExamPaper/A18List',
      method: 'post',
      data: query
    })
}

// 试卷管理-司机列表
export function paperManageListZhong(query) {
  return request({
    url: '/pfserver-person/person/driver/A19ListZhong',
    method: 'post',
    data: query
  })
}
// 试卷管理-更新试卷人员
export function dDriverExamAdd1(query) {
  return request({
    url: '/pfserver-person/person/dDriverExam/A19Add1',
    method: 'post',
    data: query
  })
}
// 试卷管理-试卷删除
export function dExamPaperDelete(id) {
  return request({
    url: '/pfserver-person/person/dExamPaper/A18Delete/' + id,
    method: 'get',
  })
}
// 试卷管理-配置试卷详情
export function dExamPaperListDriver(data) {
  return request({
    url: '/pfserver-person/person/dDriverExam/A19ListDriver' ,
    method: 'post',
    data
  })
}
// 试卷管理-新增试卷
export function paperManageAdd1(query) {
  return request({
    url: '/pfserver-person/person/dExamPaperProblem/A21Add1',
    method: 'post',
    data: query
  })
}

// 试卷管理-试题列表
export function dExamProblemListS(query) {
  return request({
    url: '/pfserver-person/person/dExamProblem/A21ListS',
    method: 'post',
    data: query
  })
}
// 试卷管理-试题列表
export function dDriverGroupA39GetInfo(id) {
  return request({
    url: '/pfserver-person/person/dDriverGroup/A39GetInfo/'+id,
    method: 'get',
  })
}
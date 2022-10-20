import request from '@/utils/request'

// 面试管理列表
export function interviewManageList(query) {
  console.log(JSON.stringify(query));
  return request({
    url: '/pfserver-person/person/driver/A09List',
    method: 'post',
    data: query
  })
}

// 教育经历列表
export function educationExperienceList(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverEducationList/${id}`,
    method: 'get',
  })
}

// 教育经历新增
export function A11DriverEducationAdd(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverEducationAdd`,
    method: 'post',
    data
  })
}
// 教育经历编辑保存
export function A11DriverEducationEditSave(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverEducationEditSave`,
    method: 'post',
    data
  })
}
// 教育经历编辑
export function A11DriverEducationEdit(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverEducationEdit/${id}`,
    method: 'post',
  })
}
// 教育经历删除
export function A11DriverEducationDelete(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverEducationDelete/${id}`,
    method: 'get',
  })
}

// 驾驶经历列表
export function A11DriverDriverList(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverDriverList/${id}`,
    method: 'get',
  })
}
// 驾驶经历新增
export function A11DriverDriverAdd(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverDriverAdd`,
    method: 'post',
    data
  })
}
// 驾驶经历编辑保存
export function A11DriverDriverEditSave(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverDriverEditSave`,
    method: 'post',
    data
  })
}
// 驾驶经历编辑
export function A11DriverDriverEdit(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverDriverEdit/${id}`,
    method: 'post',
  })
}
// 驾驶经历删除
export function A11DriverDriverDelete(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverDriverDelete/${id}`,
    method: 'get',
  })
}
// 交通事故经历列表
export function A11DriverAccidentList(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverAccidentList/${id}`,
    method: 'get',
  })
}
// 交通事故经历新增
export function A11DriverAccidentAdd(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverAccidentAdd`,
    method: 'post',
    data
  })
}
// 交通事故经历删除
export function A11DriverAccidentDelete(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverAccidentDelete/${id}`,
    method: 'get',
  })
}
// 交通事故经编辑保存
export function A11DriverAccidentEditSave(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverAccidentEditSave`,
    method: 'post',
    data
  })
}
// 基本信息-交通事故经历编辑
export function A11DriverAccidentEdit(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverAccidentEdit/${id}`,
    method: 'post',
  })
}
// 基本信息-家庭事故列表
export function A11DriverFamilyList(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverFamilyList/${id}`,
    method: 'get',
  })
}
// 基本信息-家庭状况新增
export function A11DriverFamilyAdd(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverFamilyAdd`,
    method: 'post',
    data
  })
}
// 基本信息-家庭状况删除
export function A11DriverFamilyDelete(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverFamilyDelete/${id}`,
    method: 'get',
  })
}
// 基本信息-家庭状况编辑保存
export function A11DriverFamilyEditSave(data) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverFamilyEditSave`,
    method: 'post',
    data
  })
}
// 基本信息-家庭状况编辑
export function A11DriverFamilyEdit(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverFamilyEdit/${id}`,
    method: 'post',
  })
}
// 新增
export function interviewManageAdd(query) {
  return request({
    url: '/pfserver-person/person/driver/A09Add',
    method: 'post',
    data: query
  })
}
export function driverA24Add(query) {
  return request({
    url: '/pfserver-person/person/driver/A24Add',
    method: 'post',
    data: query
  })
}
// 保存
export function A10EditSave(query) {
  return request({
    url: '/pfserver-person/person/driver/A10EditSave',
    method: 'post',
    data: query
  })
}
// 面试上传-审核
export function A15InterviewReview(query) {
  return request({
    url: '/pfserver-person/person/driver/A15InterviewReview',
    method: 'post',
    data: query
  })
}
// 面试删除
export function interviewManageDelete(id) {
  return request({
    url: `/pfserver-person/person/driver/A09Delete/${id}`,
    method: 'get',
  })
}
// 基本信息-笔试记录

export function driverWrittenExamination(id) {
  return request({
    url: `/pfserver-person/person/driver/A13WrittenExamination/${id}`,
    method: 'get',
  })
}

// 基本信息
export function A11Info(id) {
  return request({
    url: `/pfserver-person/person/driver/A11Info/${id}`,
    method: 'get',
  })
}

// 实操保存
export function A17PracticeReviewSave(query) {
  return request({
    url: '/pfserver-person/person/driver/A17PracticeReviewSave',
    method: 'post',
    data: query
  })
}

// 保存状态
export function A24DisableStatusSave(query) {
  return request({
    url: '/pfserver-person/person/dDriverFile/A24DisableStatusSave',
    method: 'post',
    data: query
  })
}
// 重置密码
export function A24ResetPasswordSave(query) {
  return request({
    url: '/pfserver-person/person/dDriverFile/A24ResetPasswordSave',
    method: 'post',
    data: query
  })
}
// 笔试查看-审核
export function A16WrittenReviewSave(query) {
  return request({
    url: '/pfserver-person/person/driver/A16WrittenReviewSave',
    method: 'post',
    data: query
  })
}

// 附件上传
export function A10Enclosure(query) {
  return request({
    url: '/pfserver-person/person/driver/A10Enclosure',
    method: 'post',
    data: query
  })
}

// 基本信息-事故记录列表
export function A30List(query) {
  return request({
    url: '/pfserver-car/accident/A30List',
    method: 'post',
    data: query
  })
}

// 基本信息-事故记录-查看
export function A31GetInfo(id) {
  return request({
    url: `/pfserver-car/accident/A31GetInfo/${id}`,
    method: 'get',
  })
}

// 基本信息-违章记录列表
export function A32List(query) {
  return request({
    url: '/pfserver-person/person/safeTrafficTransgression/A32List',
    method: 'post',
    data: query
  })
}

// 驾驶员档案-驾驶员线路记录
export function driverTRouteList(query) {
  return request({
    url: '/pfserver-car/transportTask/driverTRouteList',
    method: 'post',
    data: query
  })
}

// 驾驶员列表
export function driverA24List(query) {
  return request({
    url: '/pfserver-person/person/driver/A24List',
    method: 'post',
    data: query
  })
}

export function A09Employment(id) {
  return request({
    url: '/pfserver-person/person/driver/A09Employment/' + id,
    method: 'get',
  })
}
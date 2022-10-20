import request from '@/utils/request'


// 驾驶员档案APP列表
export function archives(query) {
  return request({
    url: '/system//administrator-app/driver/archives',
    method: 'post',
    data: query
  })
}

// 驾驶员详情
export function info(id) {
  return request({
    url:`/pfserver-person/person/driver/A11Info/${id}`,
    method: 'get',
  })
}

// 教育经历列表
export function educationExperienceList(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverEducationList/${id}`,
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

// 交通事故经历列表
export function A11DriverAccidentList(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverAccidentList/${id}`,
    method: 'get',
  })
}
// 基本信息-家庭事故列表
export function A11DriverFamilyList(id) {
  return request({
    url: `/pfserver-person/person/driver/A11DriverFamilyList/${id}`,
    method: 'get',
  })
}
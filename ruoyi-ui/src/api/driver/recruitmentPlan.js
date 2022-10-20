import request from '@/utils/request'

// 列表
export function recruitmentPlanList(query) {
    return request({
      url: '/pfserver-person/person/dRecruitPlan/A04List',
      method: 'post',
      data: query
    })
}

// 查看页面渲染
export function getRecruitmentPlanDetails(id) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/A08Info/' + id,
    method: 'get'
  })
}

// 新增
export function recruitmentPlanAdd(query) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/A05Add',
    method: 'post',
    data: query
  })
}

// 编辑页面渲染
export function RecruitmentPlanEdit(id) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/A06Edit/' + id,
    method: 'post'
  })
}

// 编辑
export function recruitmentPlanEdit(data) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/A06EditSave',
    method: 'post',
    data: data
  })
}

// 删除
export function recruitmentPlanDelete(tableId) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/A04Delete/' + tableId,
    method: 'get',
  })
}


// 招聘计划审核
export function A07Submmit(data) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/A07Submmit',
    method: 'post',
    data: data
  })
}

// 招聘计划审核
export function A07check(data) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/A07check',
    method: 'post',
    data: data
  })
}
import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询驾驶员-招聘计划列表
export function listDRecruitPlan(query) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/list',
    method: 'get',
    params: query
  })
}

// 查询驾驶员-招聘计划详细
export function getDRecruitPlan(id) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/info' + id,
    method: 'get'
  })
}

// 新增驾驶员-招聘计划
export function addDRecruitPlan(data) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/add',
    method: 'post',
    data: data
  })
}

// 修改驾驶员-招聘计划
export function updateDRecruitPlan(data) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/edit',
    method: 'post',
    data: data
  })
}

// 删除驾驶员-招聘计划
export function delDRecruitPlan(id) {
  return request({
    url: '/pfserver-person/person/dRecruitPlan/' + id,
    method: 'post'
  })
}

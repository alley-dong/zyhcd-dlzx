import request from '@/utils/request'

// 查询参数列表
export function configB05list(query) {
  return request({
    url: '/system/sysExamineConfig/examineList',
    method: 'get',
    params: query
  })
}


// 查询参数列表
export function dataDictCode() {
  return request({
    url: '/system/dict/data/dictCode',
    method: 'get',
  })
}

// 补助审批详情
export function miniofileB05list(params) {
  return request({
    url: '/system/sysExamineConfigProcess/examineConfigProcessList' ,
    method: 'get',
    params
  })
}


// 通过角色查询用户
export function configB05UserRolelist(params) {
  return request({
    url: '/system/config/B05UserRolelist' ,
    method: 'get',
    params
  })
}

// 新增
export function miniofileB05Add(data) {
  return request({
    url: '/system/sysExamineConfigProcess/examineConfigProcessAdd' ,
    method: 'post',
    data
  })
}

// 通过角色查询用户
export function miniofileB05EditSource(data) {
  return request({
    url: '/system/sysExamineConfigProcess/examineConfigProcessEdit' ,
    method: 'post',
    data
  })
}

// 删除
export function miniofileB05get(params) {
  return request({
    url: '/system/sysExamineConfigProcess/examineConfigProcessDelete' ,
    method: 'get',
    params
  })
}

// 查询参数列表
export function dataDictCodeForSp() {
  return request({
    url: '/system/sysExamineConfig/dictCode',
    method: 'get',
  })
}

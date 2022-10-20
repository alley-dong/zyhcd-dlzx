import request from '@/utils/request'

// 列表
export function questionBankList(query) {
    return request({
      url: '/pfserver-person/person/dExamProblem/A22List',
      method: 'post',
      data: query
    })
}

// 新增
export function questionBankAdd(query) {
  return request({
    url: '/pfserver-person/person/dExamProblem/A22Add',
    method: 'post',
    data: query
  })
}

// 编辑
export function questionBankEdit(data) {
  return request({
    url: '/pfserver-person/person/dExamProblem/A22Edit',
    method: 'post',
    data: data
  })
}

// 详情
export function questionBankDetails(id) {
  return request({
    url: '/pfserver-person/person/dExamProblem/A22GetInfo/' + id,
    method: 'get'
  })
}

//导入文件
export function UploadFiles(formData) {
  return request({
    url: '/pfserver-person/person/dExamProblem/A22ImportData',
    method: 'post',
    data: formData
  })
}



// 删除
export function questionBankDelete(tableId) {
  return request({
    url: '/pfserver-person/person/dExamProblem/A22Delete/' + tableId,
    method: 'get',
  })
}
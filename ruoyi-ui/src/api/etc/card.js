import request from '@/utils/request'

// 查询ETC管理-ETC卡列表
export function listCard(query) {
  return request({
    url: '/pfserver-car/car/etcCard/J01List',
    method: 'post',
    data: query
  })
}

// 新增ETC管理-ETC卡
export function addCard(data) {
  return request({
    url: '/pfserver-car/car/etcCard/J02Add',
    method: 'post',
    data: data
  })
}

// ETC管理数据回显
export function cardEdit(id) {
  return request({
    url: '/pfserver-car/car/etcCard/J01GetInfo/'+id,
    method: 'get',
  })
}

// 修改ETC管理-ETC卡
export function cardSave(data) {
  return request({
    url: '/pfserver-car/car/etcCard/J03Edit',
    method: 'post',
    data: data
  })
}

// 删除ETC管理-ETC卡
export function delCard(id) {
  return request({
    url: '/pfserver-car/car/etcCard/J01Delete/' + id,
    method: 'get'
  })
}

//ETC管理-车牌号下拉选
export function etcCardBox(data){
  return request({
    url:'/pfserver-car/commonSelectBox/J02CarNumList',
    method: 'post',
    data
  })
}

  //ETC管理-ETC导入
  export function etcCardImprot(data){
    return request({
      url: '/pfserver-car/car/etcCard/J01ImportData',
      method: 'post',
      data : data
    })
  
}

import request from '@/utils/request'

// 查询参数列表
export function vehicleList(data) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E01List',
    method: 'post',
    data
  })
}

// 新增页面的选择险种查询
export function insuranceTypeItemList(data) {
  return request({
    url: '/pfserver-car/car/insuranceTypeItem/E13List',
    method: 'post',
    data
  })
}

// 险种类别列表
export function insuranceTypeList(data) {
  return request({
    url: '/pfserver-car/car/insuranceType/E12List',
    method: 'post',
    data
  })
}
// 新增
export function insuranceVehicleAdd(query) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E02Add',
    method: 'post',
    data: query
  })
}

//险种管理-添加
export function insuranceTypeAdd(query) {
  return request({
    url: '/pfserver-car/car/insuranceType/E12Add',
    method: 'post',
    data: query
  })
}

//编辑险种类别
export function insuranceTypeEditSave(query) {
  return request({
    url: '/pfserver-car/car/insuranceType/E12EditSave',
    method: 'post',
    data: query
  })
}

//编辑险种类别
export function insuranceDelete(id) {
  return request({
    url: '/pfserver-car/car/insuranceType/E12Delete/' + id,
    method: 'get',
  })
}

// 车辆保险-查看
export function insuranceVehicleList(query) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E04List',
    method: 'post',
    data: query
  })
}
// 查看险种列表
// export function insuranceTypeItemListId(id) {
//   return request({
//     url: '/pfserver-car/car/insuranceTypeItem/E13List/'+ id ,
//     method: 'get',
//     // params
//   })
// }
// 险种管理-添加
export function insuranceTypeItemAdd(data) {
  return request({
    url: '/pfserver-car/car/insuranceTypeItem/E13Add',
    method: 'post',
    data
  })
}
// 编辑险种
export function insuranceTypeItemEditSave(data) {
  return request({
    url: '/pfserver-car/car/insuranceTypeItem/E13EditSave',
    method: 'post',
    data
  })
}

// 险种管理-逻辑删除
export function insuranceTypeItemDelete(id) {
  return request({
    url: '/pfserver-car/car/insuranceTypeItem/E13Delete/' + id,
    method: 'get',
  })
}

// 保险管理货物保险查看
export function insuranceVehicleList06(data) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E06List',
    method: 'post',
    data
  })
}

// 保险管理货物保险选择车辆保险列表
export function vehicleList08(data) {
  return request({
    url: '/pfserver-car/car/vehicle/E08VehicleList',
    method: 'post',
    data
  })
}

// 保险管理货物保险选择险种
export function insuranceTypeItemE09List(data) {
  return request({
    url: '/pfserver-car/car/insuranceTypeItem/E09List',
    method: 'post',
    data
  })
}

// 车辆保险-查看
export function insuranceVehicleE05List(data) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E05List',
    method: 'post',
    data
  })
}

// 车辆保险-查看
export function vehicleE10List(data) {
  return request({
    url: '/pfserver-car/car/googds/E10List',
    method: 'get',
    data
  })
}


// 保险台账查看
export function vehicleE14List(data) {
  return request({
    url: '/pfserver-car/car/vehicle/E14List',
    method: 'post',
    data
  })
}



// 保险管理货物保险-新增
export function vehicleE07VehicleAdd(data) {
  return request({
    url: '/pfserver-car/car/googds/E07VehicleAdd',
    method: 'post',
    data
  })
}

// 保险台账查看
export function vehicleE15List(data) {
  return request({
    url: '/pfserver-car/car/googds/E15List',
    method: 'post',
    data
  })
}

// 保险台账查看
export function googdsE16List(data) {
  return request({
    url: '/pfserver-car/car/googds/E16List',
    method: 'post',
    data
  })
}

// 保险台账查看
export function E03EditSave(data) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E03EditSave',
    method: 'post',
    data
  })
}

// 保险台账编辑
export function E16SaveEdit(query) {
  return request({
    url: '/pfserver-car/car/googds/E16SaveEdit',
    method: 'post',
    data: query
  })
}

// 保险台账编辑
export function insuranceVehicleE02Box(query) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E02Box',
    method: 'post',
    data: query
  })
}

// 保险台账编辑
export function insuranceVehicleE02InsuranceTypeList(query) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E02InsuranceTypeList',
    method: 'post',
    data: query
  })
}

// 保险管理-查看（已审批）
export function insuranceVehicleE02GetInfo(id) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E02GetInfo/' + id,
    method: 'get',
  })
}


// 审批
export function insuranceVehicleListE03Check(query) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E03Check',
    method: 'post',
    data: query
  })
}

// 审批
export function googdsE11Info(id) {
  return request({
    url: '/pfserver-car/car/googds/E11Info/' + id,
    method: 'get',
  })
}

// 保险台账管理-查看 明细
export function googdsE10GetInfo(id) {
  return request({
    url: '/pfserver-car/car/googds/E10GetInfo/' + id,
    method: 'get',
  })
}


// 保险台账管理-查看 明细
export function googdsH16SelectBox(id) {
  return request({
    url: '/pfserver-car/car/googds/H16SelectBox/' + id,
    method: 'get',
  })
}

// 保险台账管理-查看
export function googdsE15List(data) {
  return request({
    url: '/pfserver-car/car/googds/E15List',
    method: 'post',
    data
  })
}
// 保险台账管理 明细-查看
export function googdsH16Info(id) {
  return request({
    url: '/pfserver-car/car/googds/H16Info/' + id,
    method: 'get',
  })
}

// 保险台账管理 明细-编辑
export function googdsH16EditSave(data) {
  return request({
    url: '/pfserver-car/car/googds/H16EditSave',
    method: 'post',
    data
  })
}

// 自动代入上次缴纳的保险项目明细
export function insuranceVehicleE02Judge(data) {
  return request({
    url: '/pfserver-car/car/insuranceVehicle/E02Judge',
    method: 'post',
    data
  })
}
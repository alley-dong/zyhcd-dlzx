import request from '@/utils/request'

// 查询参数列表
export function repairList(query) {
  return request({
    url: '/pfserver-car/car/repair/H01List',
    method: 'post',
    data: query
  })
}
// 维修保养查看
export function repairInfo(id) {
  return request({
    url: '/pfserver-car/car/repair/H03Info/' + id,
    method: 'get',
  })
}

// 维修站下拉
export function commonSelectBox(query) {
  return request({
    url: '/pfserver-car/commonSelectBox/H02RGarage',
    method: 'post',
    data: query
  })
}

// 维修站下拉
export function commonSelectBoxList(query) {
  return request({
    url: '/pfserver-car/commonSelectBox/H02RGarageList',
    method: 'post',
    data: query
  })
}

// 维修保养审核
export function repairCheck(query) {
  return request({
    url: '/pfserver-car/car/repair/H01Check',
    method: 'post',
    data: query
  })
}
// 维修保养审核
export function H07StationCheck(query) {
  return request({
    url: '/pfserver-car/car/repair/H07StationCheck',
    method: 'post',
    data: query
  })
}

// 查看审核中的维修明细
export function repairDetailAllList(id) {
  return request({
    url: '/pfserver-car/car/repair/H08DetailAllList/' + id,
    method: 'get',
  })
}

// 维修保养管理（维修服务站列表
export function repairH05List(query) {
  return request({
    url: '/pfserver-car/car/repair/H05List',
    method: 'post',
    data: query
  })
}

// 新增维修保养管理记录列表
export function repairH06AddList(query) {
  return request({
    url: '/pfserver-car/car/repair/H06AddList',
    method: 'post',
    data: query
  })
}

// 维修明细表一列表

export function H10NotSubmittedList(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedList/' + id,
    method: 'get',
  })
}

// 维修明细表二列表

export function H10NotSubmittedIsNewList(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedIsNewList/' + id,
    method: 'get',
  })
}

// 维修保养项管理列表
export function repairH14List(query) {
  return request({
    url: '/pfserver-car/car/repairItem/H14List',
    method: 'post',
    data: query
  })
}

// 维修保养项管理新增
export function repairH14Add(query) {
  return request({
    url: '/pfserver-car/car/repairItem/H14Add',
    method: 'post',
    data: query
  })
}



// 维修保养项管理查询

export function repairItemH14Info(id) {
  return request({
    url: '/pfserver-car/car/repairItem/H14Info/' + id,
    method: 'get',
  })
}

// 维修保养项管理编辑
export function repairItemH14EditSave(query) {
  return request({
    url: '/pfserver-car/car/repairItem/H14EditSave',
    method: 'post',
    data: query
  })
}

// 维修保养项管理删除

export function repairItemH14Delete(id) {
  return request({
    url: '/pfserver-car/car/repairItem/H14Delete/' + id,
    method: 'get',
  })
}

// 维修保养项管理编辑
export function repairItemH09List(query) {
  return request({
    url: '/pfserver-car/car/repair/H09List',
    method: 'post',
    data: query
  })
}


// 维修明细表二新增
export function H10NotSubmittedIsNewAdd(query) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedIsNewAdd',
    method: 'post',
    data: query
  })
}

// 维修明细表一新增车型和名称二级联动
export function commonSelectBoxH10AddBox(query) {
  return request({
    url: '/pfserver-car/commonSelectBox/H10AddBox',
    method: 'post',
    data: query
  })
}

// 维修服务站管理列表
export function garageCarH15List(query) {
  return request({
    url: '/pfserver-car/car/garageCar/H15List',
    method: 'post',
    data: query
  })
}

// 维修服务站管理新增 
export function garageCarH15Add(query) {
  return request({
    url: '/pfserver-car/car/garageCar/H15Add',
    method: 'post',
    data: query
  })
}

// 维修服务站管理查看
export function garageCarH15Info(id) {
  return request({
    url: '/pfserver-car/car/garageCar/H15Info/' + id,
    method: 'get',
  })
}

// 维修服务站管理编辑
export function garageCarH15EditSave(data) {
  return request({
    url: '/pfserver-car/car/garageCar/H15EditSave',
    method: 'post',
    data
  })
}

// 维修服务站管理删除
export function garageCarH15Delete(id) {
  return request({
    url: '/pfserver-car/car/garageCar/H15Delete/' + id,
    method: 'get',
  })
}


// 维修明细列表
export function garageRepairItemH16list(data) {
  return request({
    url: '/pfserver-car/car/GarageRepairItem/H16list',
    method: 'post',
    data
  })
}
// 车辆档案车辆类型下拉框
export function commonSelectBoxD02CvhicleSelectBox(data) {
  return request({
    url: '/pfserver-car/commonSelectBox/H16SelectBox' ,
    method: 'post',
    data
  })
}

export function queryRepairSelectBox(data) {
  return request({
    url: '/pfserver-car/commonSelectBox/queryRepairSelectBox' ,
    method: 'post',
    data
  })
}

// 维修明细新增
export function garageRepairItemH16Add(data) {
  return request({
    url: '/pfserver-car/car/GarageRepairItem/H16Add',
    method: 'post',
    data
  })
}


// 维修明细编辑
export function garageRepairItemH16EditSave(data) {
  return request({
    url: '/pfserver-car/car/GarageRepairItem/H16EditSave',
    method: 'post',
    data
  })
}

// 维修明细详情
export function garageRepairItemH16Info(id) {
  return request({
    url: '/pfserver-car/car/GarageRepairItem/H16Info/' + id,
    method: 'get',
  })
}
// 维修明细删除
export function garageRepairItemH16Delete(id) {
  return request({
    url: '/pfserver-car/car/GarageRepairItem/H16Delete/' + id,
    method: 'get',
  })
}

export function UploadFiles(data, params) {
  return request({
    url: '/pfserver-car/car/GarageRepairItem/H16Import',
    method: 'post',
    params,
    data
  })
}
// 车辆定检管理-列表
export function vehicleInspectH19List(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspect/H19List',
    method: 'post',
    data
  })
}
// 车辆定检管理-审批
export function vehicleInspectH20List(params) {
  return request({
    url: '/pfserver-car/car/vehicleInspect/H20List/'+params.id,
    method: 'get',
  })
}
// 车辆定检管理-列表
export function vehicleInspectMonthlyItemH23List(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspectMonthlyItem/H23List',
    method: 'post',
    data
  })
}


export function vehicleInspectE20Review(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspect/H20Review',
    method: 'post',
    data
  })
}

export function vehicleInspectMonthlyH22List(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspectMonthly/H22List',
    method: 'post',
    data
  })
}
// 车辆定检记录-详情
export function vehicleInspectH23List(data) {
  return request({
    url: '/pfserver-car/car/vehicleInspect/H23List',
    method: 'post',
    data
  })
}

// 维修明细表一车型下拉选
export function commonSelectBoxH10VehicleModelByGarageId(id) {
  return request({
    url: '/pfserver-car/commonSelectBox/H10VehicleModelByGarageId/' + id,
    method: 'get',
  })
}

// 维修明细表一新增带出单位单价数据
export function repairH10NotSubmittedAddInfo(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedAddInfo/' + id,
    method: 'get',
  })
}

// 维修明细表一新增
export function repairH10NotSubmittedAdd(data) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedAdd',
    method: 'post',
    data
  })
}

// 维修明细表一编辑数据回显
export function repairH10NotSubmittedEdit(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedEdit/' + id,
    method: 'get',
  })
}

// 维修明细表一编辑
export function repairH10NotSubmittedEditSave(data) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedEditSave',
    method: 'post',
    data
  })
}


// 维修明细表一编辑数据删除
export function repairH10NotSubmittedDelete(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedDelete/' + id,
    method: 'get',
  })
}

// 维修明细表二列表
export function repairH10NotSubmittedIsNewList(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedIsNewList/' + id,
    method: 'get',
  })
}


// 维修明细表二新增
export function repairH10NotSubmittedIsNewAdd(data) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedIsNewAdd',
    method: 'post',
    data
  })
}


// 维修明细表二编辑
export function repairH10NotSubmittedIsNewEditSave(data) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedIsNewEditSave',
    method: 'post',
    data
  })
}

// 维修明细表二编辑数据回显
export function repairH10NotSubmittedIsNewEdit(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedIsNewEdit/' + id,
    method: 'get',
  })
}

// 维修明细表二编辑数据删除
export function repairH10NotSubmittedIsNewDelete(id) {
  return request({
    url: '/pfserver-car/car/repair/H10NotSubmittedIsNewDelete/' + id,
    method: 'get',
  })
}

// 维修保养台账列表 
export function repairH17List(data) {
  return request({
    url: '/pfserver-car/car/repair/H17List',
    method: 'post',
    data
  })
}
// 维修保养台账列表 
export function repairH03Info(id) {
  return request({
    url: '/pfserver-car/car/repair/H03Info/'+id,
    method: 'get',
  })
}
// 维修保养审核二、三 
export function repairH02ReviewTwoLevel(data) {
  return request({
    url: '/pfserver-car/car/repair/H02ReviewTwoLevel',
    method: 'post',
    data
  })
}
// 维修保养台账列表 
export function vehicleInspectMonthlyItemH24Info(id) {
  return request({
    url: '/pfserver-car/car/vehicleInspectMonthlyItem/H24Info/'+id,
    method: 'get',
  })
}


export function vehicleInspectH20EditSave(params) {
  return request({
    url: '/pfserver-car/car/vehicleInspect/H20EditSave',
    method: 'get',
    params
  })
}
export function repairItemH18List(data) {
  return request({
    url: '/pfserver-car/car/repairItem/H18List',
    method: 'POST',
    data
  })
}

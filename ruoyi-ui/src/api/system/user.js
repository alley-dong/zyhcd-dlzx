import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";
import qs from 'qs'
// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/B01list',
    method: 'post',
    params: query
  })
}

// 查询用户详细
export function getUser(userId) {
  return request({
    url: '/system/user/B01GetInfoL' + praseStrEmpty(userId),
    method: 'post'
  })
}

export function getGarage(query) {
  return request({
    url: '/system/garage/listL',
    method: 'get',
    params: query
  })
}
// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user/B01Add',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user/B01Edit',
    method: 'post',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  console.log(userId);
  return request({
    url: '/system/user/B01Delete/' + userId,
    method: 'get'
  })
}

// 用户密码重置
export function resetUserPwd(data) {
  // const data = {
  //   userId,
  //   password
  // }
  // console.log(data,11);
  return request({
    url: '/system/user/B01ResetPwd',
    method: 'post',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/system/user/B01ChangeStatus',
    method: 'post',
    data: data
  })
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 用户头像上传
export function findPassword({ phone,
  smsCode,
  passwd }) {
  return request({
    url: `/system/user/profile/findPassword?phone=${phone}&smsCode=${smsCode}&passwd=${passwd}`,
    method: 'post',
  })
}

export function sendCode(data) {
  return request({
    url: '/system/user/profile/sendCode',
    method: 'post',
    data:data
  })
}


import request from '@/utils/request'
// person服务 文件上传
export function UploadFiles(data) {
  return request({
    url: '/pfserver-person/personFile/minioChooseBucketUpload',
    method: 'post',
    data
  })
}

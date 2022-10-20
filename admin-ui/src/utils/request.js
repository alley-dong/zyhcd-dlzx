import axios from 'axios'
import { getToken, removeToken } from '@/utils/auth'
import { Toast } from "vant";
// console.log(process.env.BASE_API);
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
const service = axios.create({
  baseURL: '/foo',
  timeout: 10*5000
})

service.interceptors.request.use(
  config => {
    const isToken = (config.headers || {}).isToken === false
    if (getToken() && !isToken) {
      config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际
      // config.headers['Authorization'] = 'Bearer ' + '1b284903-69ac-41f2-90c9-5d6bb6b22317' // 让每个请求携带自定义token 请根据实际
    }
    console.log(config);
    return config
  },
  error => {
    console.log(error)
    Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    console.log('.response', response)
    const res = response.data
    if (res.code !== 200) {
      Toast.fail(res.msg);
      return Promise.reject(res.msg)
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error)
    Toast.fail(error.msg);
    return Promise.reject(error)
  }
)

export default service

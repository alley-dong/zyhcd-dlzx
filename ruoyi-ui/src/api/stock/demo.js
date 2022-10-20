import { getToken } from '@/utils/auth'
import axios from 'axios'
// console.log(process.env.VUE_APP_BASE_API);
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
const service1 = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: '/foo',
    // 超时
    timeout: 10*60*1000
  })

// export const request = (...data) => {
//     data.push({headers:'Bearer ' + getToken()})
//     return new Promise((resolve, reject) => {
//         axios(...data).then(res => {
//             resolve(res)
//         }).catch(err => {
//             reject(err)
//         });
//     });
// }

//   // request拦截器
  service1.interceptors.request.use(config => {
      console.log(config);
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false
    // console.log(isToken);
    if (getToken() && !isToken) {
      config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际
    }
    console.log(config);
    return config
  }, error => {
    //   console.log(error)
      Promise.reject(error)
  })

export default service1

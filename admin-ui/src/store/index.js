import Vue from 'vue'
import Vuex from 'vuex'
import some from './modules/some'

Vue.use(Vuex)
import { getToken, setToken, removeToken } from '@/utils/auth'
import { login } from '@/api/login'

export default new Vuex.Store({
  state: {
    // value: 'my value'
    token: getToken(),
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
  },
  actions: {
    // 登录
    Login({ commit }, userInfo) {
      // const username = userInfo.username.trim()
      // const password = userInfo.password
      // const code = userInfo.code
      return new Promise((resolve, reject) => {
        // TODO:发ajax
        login(userInfo).then(res => {
          console.log(res);
          let data = res.data
          // data.access_token='214f8b4f-9ced-4000-93ed-ebe733ebc168'
          setToken(data.access_token)//TODO:
          try {
            if (userInfo.deviceType=='android' || userInfo.deviceType=='ios' ) {
              console.log(data.access_token);
              test.setToken(data.access_token)
            }
          } catch (e) {

            console.log("进来了")
          }
          commit('SET_TOKEN', data.access_token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
  },
  modules: {
    some
  },
})

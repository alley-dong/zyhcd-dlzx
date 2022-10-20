import Vue from 'vue'
import Vuex from 'vuex'
import some from './modules/some'
import Cookies from 'js-cookie'

import { driverAuthLogin as login, authDriverLogin } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
Vue.use(Vuex)
import { Base } from '@hips/plugin-vue-jssdk';
const { getBaseInfo } = Base;

export default new Vuex.Store({
    state: {
        // value: 'my value'
        token: getToken(),
        map_Value: {},
        type: undefined,
        vehicle: undefined,
        AccidentCar: {}
    },
    getters: {
        // value: state => {
        //   return state.value;
        // }
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_MAP_VALUE: (state, map_Value) => {
            state.map_Value = map_Value
        },
        SET_TYPE: (state, type) => {
            state.type = type
        },
        SET_VEHICLE: (state, vehicle) => {
            state.vehicle = vehicle
        },
        SET_ACCIDENTCAR: (state, AccidentCar) => {
            state.AccidentCar = AccidentCar
        }
    },
    actions: {
        // 登录
        Login({ commit }, userInfo) {
            // const username = userInfo.username.trim()
            // const password = userInfo.password
            // const code = userInfo.code
            console.log('.userInfo='+ userInfo)
            const flag = userInfo.flag
            return new Promise((resolve, reject) => {
                if (flag) {
                    // getBaseInfo()
                    // let resMsg, errMsg
                    //  window.onGetBaseInfoSuccess = (message) => {
                    //     resMsg = JSON.parse(message)
                    //     Cookies.set("username1", resMsg.loginAccount);
                    //     setToken(resMsg.token)
                    //     resolve()
                    // }
                    //  window.onGetBaseInfoError = (error) => {
                    //     errMsg = error
                    //     reject(error)
                    // }
                    // console.log(onSuccess,
                    //     onError);
                    getBaseInfo().then(res => {
                        console.log('.getBaseInfo', res)
                        let data = res.data
                        Cookies.set("username1", data.loginAccount);
                        authDriverLogin(data.loginAccount).then(_ => {
                            setToken(_.msgBody.access_token)
                            commit('SET_TOKEN', _.msgBody.access_token)
                            Cookies.set("username1", _.msgBody.user_name);
                            resolve()
                        }).catch(errmsg=>{
                        });
                        // setToken(data.token)
                        // commit('SET_TOKEN', data.token)
                    }).catch(error => {
                        console.log(error);
                        reject(error)
                    })
                    // authDriverLogin().then(_ => {
                    //     setToken(_.data.msgBody.access_token)
                    //     commit('SET_TOKEN', _.data.msgBody.access_token)
                    //     resolve()
                    // })
                    return
                }
                login(userInfo).then(res => {
                    console.log(res);
                    let data = res.data
                    Cookies.set("username1", data.user_name);
                    // if (test) {
                    //     test.setToken(data.access_token)
                    // }
                    setToken(data.access_token)
                    // localStorage.setItem('token',data.access_token)
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
        // 地图
        SetData({ commit }, val) {
            commit('SET_MAP_VALUE', val)
        },
        CarNumber({ commit }, val) {
            commit('SET_TYPE', val)
        },
        SetVehicleId({ commit }, val) {
            commit('SET_VEHICLE', val)
        },
        // 事故上报车牌号
        AccidentCar({ commit }, val) {
            commit('SET_ACCIDENTCAR', val)
        },
    },
    modules: {
        some
    },
})
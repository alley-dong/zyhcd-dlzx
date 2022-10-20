import request from '@/utils/request'
import axios from 'axios'

// 登录方法
export function driverAuthLogin(data) {
    return request({
        url: '/pfserver-car/driver-api/login/driverAuthLogin',
        method: 'post',
        data
    })
}

// 登录方法
export function authDriverLogin(data) {
    return request({
        url: '/auth/driverLogin',
        method: 'post',
        data: {
            msgHeader: {
                messageId: "",
                interfaceId: "",
                transId: "",
                sender: "",
                receiver: "",
                count: "",
                comment: ""
            },
            msgBody: {
                contact: data
            }

        }
    })
   /* return axios({
        method: 'post',
        url: '/tar/VMS/OTM/OTM2VMS_driverLogin/ProxyServices/driverLoginPS',
        data: {
            msgHeader: {
                messageId: "",
                interfaceId: "VMS-OTM-012",
                transId: "",
                sender: "VMS",
                receiver: "OTM",
                count: "",
                comment: ""
            },
            msgBody: {
                contact: data
            }

        }
    });*/
}


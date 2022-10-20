import { constantRoutes } from '@/router'
import { getRouters } from '@/api/menu'
import Layout from '@/layout/index'

const permission = {
  state: {
    routes: [],
    addRoutes: []
  },
  mutations: {
    SET_ROUTES: (state, routes) => {
      state.addRoutes = routes
      state.routes = constantRoutes.concat(routes)
    }
  },
  actions: {
    // 生成路由
    GenerateRoutes({ commit }) {
      return new Promise(resolve => {
        // 向后端请求路由数据
        getRouters().then(res => {
          console.log(JSON.stringify(res.data));
          // res = {
          //   "msg": "操作成功",
          //   "code": 200,
          //   "data": [
          //     {
          //       "name": "Driver",
          //       "path": "/driver",
          //       "hidden": false,
          //       "redirect": "noRedirect",
          //       "component": "Layout",
          //       "alwaysShow": true,
          //       "meta": {
          //         "title": "驾驶员管理",
          //         "icon": "#",
          //         "noCache": false
          //       },
          //       "children": [
          //         {
          //           "name": "PaperManage",
          //           "path": "/paperManage",
          //           "hidden": false,
          //           "component": "driver-manage/paperManage",
          //           "meta": {
          //             "title": "试卷管理",
          //             "icon": "#",
          //             "noCache": false
          //           },
                  
          //           // "children": [
                     
          //           // ]
          //         },
          //         {
          //           "name": "AddPaperManage",
          //           "path": "/paperManageWrite/:id",
          //           "hidden": true,
          //           "component": "driver-manage/paperManageChild/paperManageWrite",
          //           "meta": {
          //             "title": "查看试卷",
          //             "icon": "#",
          //             "noCache": false
          //           }
          //         }
          //       ]
          //     }
          //   ]
          // }
          // for (const [index, item] of res.data.entries()) {
          //   if (item.name == 'Person') {
          //     res.data.splice(index, 1)
          //   }
          // }
          // let obj = {
          //   "name": "Approval",
          //   "path": "approval",
          //   "hidden": false,
          //   "component": "system/approval/index",
          //   "meta": {
          //     "title": "审批管理",
          //     "icon": "tree-table",
          //     "noCache": false
          //   }
          // }
          // let obj1 = {
          //   "name": "DetailApproval",
          //   "path": "/detailApproval/:id",
          //   "hidden": true,
          //   "component": "system/approval/detailApproval",
          //   "meta": {
          //     "title": "审批管理(查看)",
          //     "noCache": false
          //   }
          // }
          // let obj2 = {
          //   "name": "Preferences",
          //   "path": "preferences",
          //   "hidden": false,
          //   "component": "system/preferences/index",
          //   "meta": {
          //     "title": "参数设置",
          //     "noCache": false
          //   }
          // }
          // for (const item of res.data) {
          //   if (item.name == 'System' && item.children) {
          //     item.children.push(obj)
          //     item.children.push(obj1)
          //     item.children.push(obj2)

          //   }
          // }
        //   function name(list) {
        //     for (const item of list) {
        //       if (item.meta.icon) {
        //         item.meta.icon = '#'
        //       }
        //         if (item.children) {
        //             name(item.children)
        //         }
        //     }
        // }
        // name(res.data)
          // debugger
          // res.data = [] // TODO:删
          const accessedRoutes = filterAsyncRouter(res.data)
          accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
          commit('SET_ROUTES', accessedRoutes)
          resolve(accessedRoutes)
        })
      })
    }
  }
}

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap) {
  return asyncRouterMap.filter(route => {
    if (route.component) {
      // Layout组件特殊处理
      if (route.component === 'Layout') {
        route.component = Layout
      } else {
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children)
    }
    return true
  })
}

export const loadView = (view) => { // 路由懒加载
  return (resolve) => require([`@/views/${view}`], resolve)
}

export default permission

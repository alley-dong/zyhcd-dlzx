import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/utils/flexable'
import "@/assets/style/index.less";
import {getDicts} from "@/api/data";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import BaiduMap from 'vue-baidu-map'
Vue.use(BaiduMap, {
  // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
  ak: 'YsUcqHGCsFH6t9n3uht6z2FGsSfhGG05'
})
Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.getDicts = getDicts
import "@/components/Vant";

// // 开发环境下面使用vConsole进行调试
// if (process.env.NODE_ENV === 'development') {
//   const VConsole = require('vconsole')
//   new VConsole()
// }

import moment from 'moment'
//手动引入所需要的语言包
import 'moment/locale/zh-cn';
// 指定使用的语言
moment.locale('zh-cn');
// day.js代替moment

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

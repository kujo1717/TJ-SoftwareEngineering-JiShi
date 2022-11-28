import Vue from 'vue'
//kanban组件
import vueKanban from 'vue-kanban'
Vue.use(vueKanban)



//全局样式
import './assets/css/global.css'
//chat box
import LemonIMUI from 'lemon-imui'
import 'lemon-imui/dist/index.css';
Vue.use(LemonIMUI)

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css
import './styles/element-variables.scss'

import App from './App.vue'
import store from './store'
import router from './router'
import animated from "animate.css"
import '@/icons' // icon
import '@/permission' // permission control
//--------------------
//-------------------
Vue.use(animated)
/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online ! ! !
 */
// if (process.env.NODE_ENV === 'production') {
//   const { mockXHR } = require('../mock')
//   mockXHR()
// }

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})

import Vue from 'vue'
import App from './App.vue'

//引入element-ui
import 'element-ui/lib/theme-chalk/index.css'


// 按需引入Element-ui
import {
  Button
} from 'element-ui'

//注册使用element-ui
Vue.use(Button)



Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')


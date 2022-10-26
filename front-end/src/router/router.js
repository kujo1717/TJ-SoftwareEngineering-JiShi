import Vue from 'vue'
import VueRouter from 'vue-router'
import cookies from "vue-cookies";
import axios from 'axios'
Vue.use(VueRouter)

//路由组件
import Main from '../views/Main.vue'
import CalendarView from '../views/CalendarView/CalendarView.vue'
import ViewList from '../views/ViewList/viewList.vue'
//路由配置项
const routes = [
    {
        path: '/',
        name: 'Main',
        component: Main,
        meta: {
            needAuth: true,
            title: 'Main Page'
        },
        children: [
            {
                path: 'CalendarView',
                name: 'CalendarView',
                component: CalendarView,
                meta: {
                    needAuth: true,
                    title: 'CalendarView Page'
                },
                children: [

                ]
            },
            {
                path: 'ViewList',
                name: 'ViewList',
                component: ViewList,
                meta: {
                    needAuth: true,
                    title: 'ViewList Page'
                },
                children: [

                ]
            },

        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    routes: routes
})

//全局前置路由
//来自from路由，目标to路由，next放行函数
router.beforeEach((to, from, next) => {
    next();
})


//全局后置路由
//修改标题用
router.afterEach((to, from) => {
    document.title = to.meta.title || 'demo';
})
//对外暴露router
export default router
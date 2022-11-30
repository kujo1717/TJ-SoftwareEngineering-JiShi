import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  // {
  //   path: '/login',
  //   component: () => import('@/views/login/index'),
  //   hidden: true
  // },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }],
    hidden: true
  },
  {
    path: '/calendarView',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'calendarView',
        component: () => import('@/views/calendarView/index'),
        meta: { title: '日程', icon: 'form' }
      }
    ],
  },

  {
    path: '/viewList',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'viewList',
        component: () => import('@/views/viewList/index'),
        meta: { title: '视图', icon: 'form' }
      }
    ],
  },
  {
    path: '/quadrantView',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'quadrantView',
        component: () => import('@/views/quadrantView/index'),
        meta: { title: '四象限视图#待合并', icon: 'form' }
      }
    ],
  },
  {
    path: '/viewLog',
    component: Layout,
    children: [
      {
        path: 'logview',
        name: 'viewLog',
        component: () => import('@/views/logviewing/logview'),
        meta: { title: '日志查看#待删除', icon: 'form' }
      }
    ]
  },
  {
    path: '/dataReport',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'DataReport',
        component: () => import('@/views/dataReport/index'),
        meta: { title: '数据分析', icon: 'form' }
      }
    ],
  },
  {
    path: '/dustbin',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Dustbin',
        component: () => import('@/views/dustbin/index'),
        meta: { title: '回收站', icon: 'form' }
      }
    ],
  },
  {
    path: '/concentration',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'concentration',
        component: () => import('@/views/concentration/index'),
        meta: { title: '专注', icon: 'form' }
      }
    ],
  },
  {
    path: '/message',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'message',
        component: () => import('@/views/message/index'),
        meta: { title: '消息', icon: 'form' }
      }
    ],
  },
  {
    path: '/projects',
    component: Layout,
    redirect: '/projects/project1',
    name: 'Projects',
    meta: {
      title: '项目',
      icon: 'nested'
    },
    children: [
      {
        path: 'myproject',
        component: () => import('@/views/projects/MyProject'),
        name: '我的项目',
        meta: { title: '我的项目' },
      },
      {
        path: 'project1',
        component: () => import('@/views/projects/project1'),
        name: 'project1',
        meta: { title: '测试用静态项目页面' },
      }
    ],
  },
  {
    path: '/teamspace',
    component: Layout,
    redirect: '/teamspace/team1',
    name: 'TeamSpace',
    meta: {
      title: '团队',
      icon: 'nested'
    },
    children: [
      {
        path: '团队1',
        component: () => import('@/views/teamspace/team1'),
        name: '团队1',
        meta: { title: '团队1' },
      },
      {
        path: '团队2',
        component: () => import('@/views/teamspace/team2'),
        name: '团队2',
        meta: { title: '团队2' },
      },


    ],
  },



  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router

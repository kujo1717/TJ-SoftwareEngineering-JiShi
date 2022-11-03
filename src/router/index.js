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
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

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
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ],
    hidden: true
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ],
    hidden: true
  },



  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ],
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
        meta: { title: '四象限视图', icon: 'form' }
      }
    ],
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
    path: '/personal',
    component: Layout,
    redirect: '/personal/overView',
    name: 'personal',
    hidden: true,
    meta: {
      title: '我的日程管理',
      icon: 'nested'
    },
    children: [
      {
        path: 'overView',
        name: 'overView',
        component: () => import('@/views/personalSchedule/overview/index'),
        meta: { title: '概览', icon: 'form' }
      },
      {
        path: 'tasklist',
        name: 'tasklist',
        component: () => import('@/views/personalSchedule/tasklist/index'),
        meta: { title: '事项', icon: 'form' }
      },
      {
        path: 'schedule',
        name: 'schedule',
        component: () => import('@/views/personalSchedule/schedule/index'),
        meta: { title: '日程', icon: 'form' }
      },

    ],
  },
  {
    path: '/teams',
    component: Layout,
    redirect: '/teams/team1',
    name: 'Teams',
    meta: {
      title: '团队',
      icon: 'nested'
    },
    children: [
      {
        path: 'team1',
        component: () => import('@/views/teams/team1/index'), // Parent router-view
        name: 'team1',
        meta: { title: 'team1' },
        redirect: '/teams/team1',
        children: [
          {
            path: '项目1-1',
            component: () => import('@/views/teams/team1/proj1-1'),
            name: '项目1-1',
            meta: { title: '项目1-1' },
          },
          {
            path: '项目1-2',
            component: () => import('@/views/teams/team1/proj1-2'),
            name: '项目1-2',
            meta: { title: '项目1-2' },
          },
        ]
      },
      {
        path: 'team2',
        component: () => import('@/views/teams/team2/index'),
        name: 'team2',
        meta: { title: 'team2' }
      }
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

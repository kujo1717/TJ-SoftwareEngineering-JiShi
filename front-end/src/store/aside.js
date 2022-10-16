/** 
 * 左侧导航栏数据的管理，导航栏的折叠
 * @author yy
 * @editor yy
 * @lastUpdate 10.15
*/


export default {
    state: {
        aside_data: [],
        aside_title: 'aside_title',//导航栏标题

        isCollapse: false
    },
    mutations: {
        //清空
        clearAsideData(state) {
            state.aside_data = []
        },
        //设置用户的导航栏
        SetClientAsideData(state) {
            state.aside_title = 'aside_title';
            state.aside_data = [
                {
                    path: '/Calendarview',
                    name: 'CalendarView',
                    label: '总日历',
                    icon: 'icon/calendar.png',
                    index: '1',
                    children:[
                        {
                            path: '/Calendarview',
                            name: 'CalendarView',
                            label: '总日历-1',
                            icon: 's-home',
                            index: '1-1',
                        }
                    ]

                },
                {
                    path: '/Calendarview',
                    name: 'CalendarView',
                    label: '视图',
                    icon: 's-home',
                    index: '2'
                },

            ]
        }
        ,


        //折叠
        collapseMenu(state) {
            state.isCollapse = !state.isCollapse
        }
    }
}

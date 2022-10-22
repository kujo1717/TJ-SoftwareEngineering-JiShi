/**
全日历页面
@author yy
@editor yy, hym
@lastUpdata hym 10.22
*/
<template>
  <div>
    <FullCalendar :options="calendarOptions" />
    <CreateTaskBox/>
    
  </div>
</template>

<script>
// 引入日历组件
import '@fullcalendar/core/vdom' // solves problem with Vite
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import CreateTaskBox from '../../components/CreateTaskBox.vue'


export default {
  name: "CalenderView",
  components: { FullCalendar, CreateTaskBox },
  data () {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin, interactionPlugin],
        initialView: 'dayGridMonth',
        dateClick: this.handleDateClick,
        events: [
          { title: '事项1', date: '2022-10-07' },
          { title: '事项2', date: '2022-10-07' }
        ]
      },

    }

  },
  methods: {
    handleDateClick: function (arg) {
      //alert('date click! ' + arg.dateStr)
      this.dialogVisible = true;
    }
  }
};
</script>

<style lang="less" scoped>
</style>
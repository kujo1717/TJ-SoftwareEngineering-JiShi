/**
全日历页面
@author yy
@editor yy, hym
@lastUpdata hym 10.22
*/
<template>
  <div>
    <FullCalendar :options="calendarOptions" />
    <CreateTaskBox :createTaskBoxDialogVisible="createTaskBoxDialogVisible" :date="chosen_date" :key="timer"/>
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
      timer: '',//用于每次调用子组件都按照当前时间重新生成DOM元素
      chosen_date:'',//当前选中的日期
      createTaskBoxDialogVisible: false,
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
      this.timer = new Date().getTime();
      this.createTaskBoxDialogVisible = true;
      this.chosen_date = arg.dateStr;
      //alert('date click! ' + arg.dateStr)     
    }
  },
  watch:{
    
  }
};
</script>

<style lang="less" scoped>
</style>
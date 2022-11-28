/**
全日历页面
@author yy
@editor yy, hym
@lastUpdata hym 10.22
*/
<template>
  <div>
    <span>请选择</span>
    <el-select
      v-model="value"
      :popper-append-to-body="false"
    >

      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
    <FullCalendar :options="calendarOptions" />

    <!--用于每次调用子组件都按照当前时间重新生成DOM元素-->
    <CreateTaskBox
      :createTaskBoxDialogVisible="createTaskBoxDialogVisible"
      :dateObj="chosen_dateObj"
      @resetDialogVisible="resetDialogVisible()"
    />
    <TaskBox
      :taskBoxDialogVisible="taskBoxDialogVisible"
      :taskid="chosen_taskid"
      @resetDialogVisible="resetDialogVisible()"
    />
  </div>
</template>

<script>
// 引入日历组件
import CreateTaskBox from "../../components/CreateTaskBox.vue";
import TaskBox from "../../components/TaskBox.vue";
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import resourceTimelinePlugin from "@fullcalendar/resource-timeline";
import listPlugin from "@fullcalendar/list";
import timeGridPlugin from "@fullcalendar/timegrid";
// import 'bootstrap/dist/css/bootstrap.css';
// import 'bootstrap-icons/font/bootstrap-icons.css'; // needs additional webpack config!
// import bootstrap5Plugin from '@fullcalendar/bootstrap5';

export default {
  name: "CalenderView",
  components: { FullCalendar, CreateTaskBox, TaskBox },
  data () {
    return {
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      value: '',


      chosen_dateObj: "", //当前选中的日期
      chosen_taskid: "", //当前选中的任务

      createTaskBoxDialogVisible: false,
      taskBoxDialogVisible: false,
      isDateClicking: false,
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          interactionPlugin,
          resourceTimelinePlugin,
          listPlugin,
          timeGridPlugin,
          //bootstrap5Plugin,
        ],
        locale: "zh",
        timeZone: "local",
        buttonText: {
          today: "今天",
          month: "月",
          week: "周",
          day: "日",
          resourceTimeline: "子任务列表",
        },
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay resourceTimeline",
        },
        initialView: "dayGridMonth",
        //themeSystem: 'bootstrap5',
        selectable: true,
        editable: true, //拖拽编辑
        nowIndicator: true, //当前时间指示器
        //点击事件
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,

        //每格显示数量限制
        dayMaxEventRows: true,
        views: {
          timeGrid: {
            dayMaxEventRows: 6, // adjust to 6 only for timeGridWeek/timeGridDay
          },
        },
        events: [
          {
            id: "001",
            groupid: "1",
            title: "事项1",
            start: "2022-10-23T12:30:00",
            end: "2022-10-23T15:30:00",
            color: "#378006",
            allDay: false,
            displayEventTime: true,
            resourceIds: ["a1", "a2"],
          },
          {
            title: "事项2",
            date: "2022-10-23",
            allDay: true,
            resourceIds: ["a"],
          },
          { title: "事项2", date: "2022-10-23", allDay: true },
          { title: "事项2", date: "2022-10-23", allDay: true },
          { title: "事项2", date: "2022-10-23", allDay: true },
          { title: "事项2", date: "2022-10-23", allDay: true },
          { title: "事项2", date: "2022-10-23", allDay: true },
        ],
        resources: [
          {
            id: "a",
            title: "Room A",
            children: [
              {
                id: "a1",
                title: "Room A1",
              },
              {
                id: "a2",
                title: "Room A2",
              },
            ],
          },
        ],
      },
    };
  },
  methods: {
    resetDialogVisible () {
      this.createTaskBoxDialogVisible = false;
      this.taskBoxDialogVisible = false;
    },
    //选中日期，进入创建事项
    handleDateSelect: function (arg) {
      this.chosen_dateObj = arg;
      this.resetDialogVisible();
      this.createTaskBoxDialogVisible = true;
      console.group("日期信息");
      console.log("选中的日期信息为：", arg);
      console.groupEnd("日期信息");
    },
    //选中事项，查看事项详情
    handleEventClick: function (arg) {
      this.chosen_taskid = arg.event.id;
      this.taskBoxDialogVisible = true;

      console.log("事项信息：", arg);
    },
  },

  watch: {},
};
</script>

<style lang="less" scoped>
//改变日历主题色
/deep/ .fc-button-primary {
  background-color: var(--calendar-primary) !important;
  border-color: var(--calendar-primary) !important;
}

/deep/ .fc-button-primary:hover {
  background-color: var(--calendar-hover) !important;
  border-color: var(--calendar-hover) !important;
}

/deep/ .fc-button-primary:active {
  background-color: var(--calendar-hover) !important;
  border-color: var(--calendar-hover) !important;
}

//隐藏盗版
/deep/ .fc-license-message {
  opacity: 0 !important;
}


//修改下拉框
::v-deep .el-select {
  width: 7em;
}

::v-deep .el-input__inner {
  background-color: transparent;
}

// ::v-deep .el-input__inner:hover {
//   background-color: transparent;
//   border-color: transparent;
// }

// ::v-deep .el-input__inner:active {
//   background-color: transparent;
//   border-color: transparent;
// }






</style>
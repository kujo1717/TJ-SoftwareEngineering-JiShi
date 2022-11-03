<template>
  <div>
    <FullCalendar :options="calendarOptions" />
    <!--用于每次调用子组件都按照当前时间重新生成DOM元素-->
    <CreateTaskBox
      :createTaskBoxDialogVisible="createTaskBoxDialogVisible"
      :timeRange="chosen_dateObj"
      @resetDialogVisible="resetDialogVisible()"
    />
    <TaskBox
      :taskBoxDialogVisible="taskBoxDialogVisible"
      :taskObj="chosen_taskObj"
      :key="new Date().toString()"
      @resetDialogVisible="resetDialogVisible()"
    />
  </div>
</template>

<script>
// 引入日历组件
import CreateTaskBox from "../../../../../components/CreateTaskBox/index.vue";
import TaskBox from  "../../../../../components/TaskBox/index.vue";

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
  name: "TimeSchedule",
  components: { FullCalendar, CreateTaskBox, TaskBox },
  data () {
    return {
      chosen_dateObj: {}, //当前选中的日期
      chosen_taskObj: {//当前选中的任务
        taskTitle: '背单词',
        taskDetail: "先背两小时单词",//详情的文字
        isdone: null,//初始时计算得出，不是从后端请求来的
        taskState: '进行中',
        classification: '学习',
        priority: '无优先级',
        timeRange: [new Date(), ''],
        familyPosition: 'parent',
        familyMembers: [],
      },

      createTaskBoxDialogVisible: false,
      taskBoxDialogVisible: false,
      isDateClicking: false,

      //task列表
      taskList: [
        {
          id: '001',
          taskTitle: '背单词1',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '无优先级',
          timeRange: ["2022-11-01T12:30:00", '2022-11-02T12:30:00'],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '无优先级',
          timeRange: ['2022-11-02T10:30:00', '2022-11-02T12:30:00'],
          familyPosition: 'parent',
          familyMembers: [],
        }
      ],
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          interactionPlugin,
          resourceTimelinePlugin,
          listPlugin,
          timeGridPlugin,
        ],
        locale: "zh",
        timeZone: "local",
        buttonText: {
          today: "今天",
          dayGridThreeDays: "四天",
          resourceTimelineMonth: "月",
        },
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridThreeDays,resourceTimelineMonth",
        },
        initialView: "dayGridThreeDays",
        //themeSystem: 'bootstrap5',
        selectable: true,
        editable: false, //拖拽编辑
        nowIndicator: true, //当前时间指示器

        //事件的回调函数
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,


        //每格显示数量限制
        dayMaxEventRows: true,
        views: {
          dayGridThreeDays: {
            type: 'dayGridDay',
            duration: { days: 4 }
          }
        },
        events: this.getEvents(),
        resources: this.getResources()
      },

    };
  },

  methods: {
    resetDialogVisible () {
      this.createTaskBoxDialogVisible = false;
      this.taskBoxDialogVisible = false;
    },
    /*
     处理回调
   */
    //选中日期，进入创建事项
    handleDateSelect: function (arg) {
      this.chosen_dateObj = [arg.start, arg.end];
      console.group("日期信息");
      console.log(this.chosen_dateObj)
      console.groupEnd("日期信息");
      this.createTaskBoxDialogVisible = true;
    },
    //选中事项，查看事项详情
    handleEventClick: function (arg) {
      for (let i = 0; i < this.taskList.length; i++) {
        if (this.taskList[i].id == arg.event.id) {
          this.chosen_taskObj = this.taskList[i];
          break;
        }
      }
      this.taskBoxDialogVisible = true;
    },


    /*
      获取events和resources
    */
    getEvents () {
      let eventList = []
      const taskList = [
        {
          id: '001',
          taskTitle: '背单词1',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '进行中',
          classification: '学习',
          priority: '无优先级',
          timeRange: ["2022-11-01T12:30:00", "2022-11-02T12:30:00"],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '无优先级',
          timeRange: ["2022-11-02T12:30:00", "2022-11-02T13:30:00"],
          familyPosition: 'parent',
          familyMembers: [],
        }
      ]
      taskList.forEach((value) => {

        //计算要不要allDay
        let my_allDay = value['timeRange'] === [] || value.taskState.indexOf("完成") >= 0 ? true : false;

        //如果完成了，就标灰
        let my_color = "#378006"//灰色       
        if (value.taskState.indexOf("完成") > 0)//如果完成
          my_color = "#d1d2c8"

        //往event列表里加
        eventList.push({
          id: value['id'],
          title: value['taskTitle'],
          start: value['timeRange'][0],
          end: value['timeRange'][1],
          allDay: my_allDay,
          displayEventTime: false,
          resourceIds: [value['id']],
          color: my_color
        })

      })
      return eventList;
    },//end of method
    getResources () {
      let resourceList = []
      const taskList = [
        {
          id: '001',
          taskTitle: '背单词1',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '无优先级',
          timeRange: ["2022-11-01T12:30:00", '2022-11-02T12:30:00'],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '无优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        }
      ]
      taskList.forEach((value) => {
        //计算要不要children
        let my_children = [];
        if (value['familyPosition'] == 'parent') {
          my_children = value['familyMembers'].slice(0);
        }

        //往resource列表里加
        resourceList.push({
          id: value['id'],
          title: value['taskTitle'],
          children: my_children
        })
      })
      return resourceList
    }
  },


  mounted: function () {

  }

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
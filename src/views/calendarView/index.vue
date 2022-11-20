/**
全日历页面
@author yy
@editor yy, hym
@lastUpdata hym 10.22
*/
<template>
  <div>
    <el-button @click="test()">测试按钮</el-button>
    <FullCalendar
      ref="fullCalendar"
      :options="calendarOptions"
    />

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
import { getTaskListByMonth, getAllTask } from "@/api/task.js"
// 引入日历组件
import CreateTaskBox from "../../components/CreateTaskBox";
import TaskBox from "../../components/TaskBox";
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import resourceTimelinePlugin from "@fullcalendar/resource-timeline";
import listPlugin from "@fullcalendar/list";
import timeGridPlugin from "@fullcalendar/timegrid";
import moment from "moment";


export default {
  name: "CalenderView",
  components: { FullCalendar, CreateTaskBox, TaskBox },
  data () {
    return {
      fisrt: true,
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
      //需要维护的视图当前信息
      titleDom: '',
      currentViewInfo: {
        currentViewType: '',//当前视图种类
        currentViewStartTime: '',//视图开始时间
        currentViewEndTime: '',//视图结束时间
      },

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

        //事件的回调函数
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventDrop: this.handleEventDragAndResize,
        eventResize: this.handleEventDragAndResize,

        //每格显示数量限制
        dayMaxEventRows: true,
        views: {
          timeGrid: {
            dayMaxEventRows: 6, // adjust to 6 only for timeGridWeek/timeGridDay
          },
        },
        events: this.getEvents(),
        resources: this.getResources(),

      },

      //事项列表
      taskList: [
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
          taskState: '延期中',
          classification: '学习',
          priority: '无优先级',
          timeRange: ["2022-11-02T12:30:00", "2022-11-02T13:30:00"],
          familyPosition: 'parent',
          familyMembers: [],
        }
      ]
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
      this.updateCurrentViewInfo();
    },
    //处理drag和resize
    handleEventDragAndResize: function (arg) {
      console.log(arg)
      //1.更新taskList
      for (let i = 0; i < this.taskList.length; i++) {
        if (this.taskList[i].id == arg.event.id) {
          this.taskList[i].timeRange = [arg.event.start, arg.event.end];
        }
      }

      //2.更新event
      for (let i = 0; i < this.calendarOptions.events.length; i++) {
        if (this.calendarOptions.events[i].id == arg.event.id) {
          this.calendarOptions.events[i].start = arg.event.start;
          this.calendarOptions.events[i].end = arg.event.end;
        }
      }
      console.log('hahaha', this.calendarOptions)

      //3.向后端发出更改put请求
      //code here...

    },

    /*
      获取events和resources
    */
    getEvents () {
      let eventList = []
      let taskList = []
      new Promise((resolve, reject) => {
        taskList = this.getFrontendTaskList(2022, 11)
      })
        .then(() => {
          console.log("我先打印一下看看", taskList)
          taskList.forEach((value) => {

            //计算要不要allDay
            let my_allDay = value['timeRange'] === [] || value.taskState.indexOf("完成") >= 0 ? true : false;

            //如果完成了，就标灰
            let my_color;
            if (value.taskState.indexOf("完成") >= 0)
              my_color = "#378006"//完成：灰色       
            else if (value.taskState == "进行中")
              my_color = "#d1d2c8"//进行中：绿色
            else if (value.taskState == "延期中")
              my_color = "#c6cb25"//延期：黄色


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
        })
    },//end of method
    getResources () {
      let resourceList = []
      let taskList = []
      new Promise((resolve, reject) => {
        taskList = this.getFrontendTaskList(2022, 11);
      })
        .then(() => {
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
        })
    },
    updateCurrentViewInfo () {
      //如果页面发生改变，就要从后端重新拉取数据
      let time, start, end;
      if (this.first) {
        this.first = false;
        //当月的月份
        let monthNum = moment().month(); // 当月的月份
        //某月第一天
        let start = moment(time).month(monthNum - 1).date(1).startOf("month").format("YYYY-MM-DD");
        //某月最后一天
        let end = moment(time).month(monthNum - 1).date(1).endOf("month").format("YYYY-MM-DD");
      }
      else {
        // 获取当前视图日历的范围
        time = this.$refs.fullCalendar.getApi().currentDataManager.state.dateProfile.renderRange
        this.currentViewInfo.currentViewType = this.$refs.fullCalendar.getApi().currentDataManager.data.currentViewType
        this.currentViewInfo.currentViewStartTime = moment(time.start).format() // 视图开始时间
        this.currentViewInfo.currentViewEndTime = moment(time.end).format() // 视图结束时间

        console.log("检测到页面跳转！", this.currentViewInfo)

        start = new Date(this.currentViewInfo.currentViewStartTime);
        end = new Date(this.currentViewInfo.currentViewEndTime);
      }


      const startYear = start.getFullYear();
      const endYear = end.getFullYear();

      const startMonth = start.getMonth() + 1;
      const startDay = start.getDate();

      const endMonth = end.getMonth() + 1;
      const endDay = end.getDate();

      //获取年份
      const currentYear = startMonth > endMonth ? endYear : startYear;

      //获取月份
      let currentMonth = 0;
      if (endMonth - startMonth == 2)
        currentMonth = (startMonth + endMonth) / 2;
      else
        currentMonth = startDay > endDay ? endMonth : startMonth;

      console.log(startYear, startMonth, startDay, endYear, endMonth, endDay)

      this.taskList = this.getFrontendTaskList(currentYear, currentMonth);
    },
    getFrontendTaskList (year, month) {
      //从后端拉取数据
      getAllTask()//todo
        .then((res) => {
          console.log("月份后端请求成功！");

          let taskList = []
          //然后要把后端拉回来的task对象转换成前端要用的对象
          if (res.data != null || res.data != []) {

            let backendDataList = res.data;
            if (backendDataList == null)
              return;
            backendDataList.forEach((value) => {

              //获取taskState
              let _taskState = '';
              switch (value.taskState) {
                case 0:
                  _taskState = "已完成";
                  break;
                case 1:
                  _taskState = "进行中";
                  break;
                case 2:
                  _taskState = "延期完成";
                  break;
                case 3:
                  _taskState = "延期中";
                  break;
              }

              //获取isdone
              let _isdone = _taskState.indexOf("完成") >= 0 ? true : false;

              //获取priority
              let _priority = '';
              switch (value.priority) {
                case 0:
                  _priority = "无优先级";
                  break;
                case 1:
                  _priority = "低优先级";
                  break;
                case 2:
                  _priority = "中优先级";
                  break;
                case 3:
                  _priority = "高优先级";
                  break;
              }

              taskList.push({
                id: value.taskId,
                taskTitle: value.taskTitle,
                taskDetail: value.taskDetail,
                isdone: _isdone,
                taskState: _taskState,
                classification: value.classificationTitle,
                priority: _priority,
                timeRange: [value.startTime, value.endTime],
                familyPosition: value.isParent == 1 ? 'parent' : 'child',
                familyMembers: []//todo
              })
            })
          }//end of if
          return taskList;

        })//end of then
        .catch((err) => {
          console.log("月份后端请求失败！", err);
          return null;
        })
      return null;
    },
    updateTitleDOM () {
      this.titleDom = document.getElementById('fc-dom-1');
    },
    test () {

      this.$refs.fullCalendar.getApi().fullCalendar('gotoDate', new Date())
    }



  },

  watch: {
    calendarOptions: {
      handler (newVal) {
        console.log(newVal);
      },
      deep: true
    },
    titleDom: {
      handler (newVal) {
        this.updateCurrentViewInfo();
      }
    }
  },

  mounted: function () {
    console.log('mounted');
    console.log(this.calendarOptions)
    this.updateCurrentViewInfo();
    this.updateTitleDOM();

    //添加DOM元素监听，识别页面跳转
    var elements = document.getElementsByTagName('button')
    for (let i = 0; i < elements.length; i++)
      elements[i].addEventListener("click", () => { this.updateCurrentViewInfo() })
  }
};
</script>

<style lang="less" scoped>
//改变日历主题色
/deep/ .fc-button-primary {
  background-color: green !important;
  border-color: green !important;
}

/deep/ .fc-button-primary:hover {
  background-color: green !important;
  border-color: green !important;
}

/deep/ .fc-button-primary:active {
  background-color: green !important;
  border-color: green !important;
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
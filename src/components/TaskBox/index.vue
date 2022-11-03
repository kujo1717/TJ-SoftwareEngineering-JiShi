/** 
单个事项信息的组件
@author: hym
@editor: hym
@lastUpdate hym 10.22

传参说明：
taskBoxDialogVisible: bool 控制对话框显示
taskObj: obj 事项
*/
<template>
  <div>

    <!--对话框-->
    <el-dialog
      :visible.sync="dialogVisible"
      width="1000px"
      :append-to-body="true"
    >
      <el-container>

        <!--header-->
        <el-header>
          <!--勾选框-->
          <el-radio
            class="isDone_radio"
            v-model="taskInfo.isdone"
            :label="true"
            @click.native.prevent="onTaskDoneRadioChange()"
          >
            <span></span>
          </el-radio>
          <!--事项状态-->
          <el-tag :type='tagStateColor'>{{taskInfo.taskState}}</el-tag>

          <!--事项与ddl的时间差-->
          <font :color="timeLengthColor">{{timeLengthBetweenDDL}}</font>
        </el-header>
        <el-container>

          <el-aside width="40em">
            <!--事项详情-->
            <div class="detailPanel">
              <h2 class="taskTitle">{{taskInfo.taskTitle}}</h2>
              <!--点击编辑详情-->
              <el-button
                size="mini"
                @click="changeEditState()"
                class="editButton"
              ><i
                  :class="isEditting ? 'el-icon-document-checked' : 'el-icon-edit'"></i>
              </el-button>

              <div v-if="!isEditting">
                {{taskInfo.taskDetail}}
              </div>
              <el-input
                v-else
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 5}"
                placeholder="请输入内容"
                v-model="taskInfo.taskDetail"
              >
              </el-input>
            </div>

            <!--事项属性-->
            <el-form label-width="0px">
              <!--分类-->
              <el-form-item class="formItem">
                <i class="iconfont icon-fenlei"></i>分类：
                <el-select
                  v-model="taskInfo.classification"
                  :popper-append-to-body="false"
                >

                  <el-option
                    v-for="item in typeList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>

              <!--优先级-->
              <el-form-item class="formItem">
                <i class="iconfont icon-louti"></i>优先级：
                <el-dropdown
                  trigger="click"
                  @command="handlePriorityCommand"
                >
                  <span class="el-dropdown-link">
                    <el-tag
                      :type="priorityColor"
                      effect="dark"
                      class="el-icon-arrow-down el-icon--right"
                    >{{taskInfo.priority}}</el-tag>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="高优先级"><i
                        class="iconfont icon-youxianji"
                        style="color:#d81e06"
                      ></i>高优先级</el-dropdown-item>
                    <el-dropdown-item command="中优先级"><i
                        class="iconfont icon-youxianji"
                        style="color:#f4ea2a"
                      ></i>中优先级</el-dropdown-item>
                    <el-dropdown-item command="低优先级"><i
                        class="iconfont icon-youxianji"
                        style="color:#1afa29"
                      ></i>低优先级</el-dropdown-item>
                    <el-dropdown-item command="无优先级"><i
                        class="iconfont icon-youxianji"
                        style="color:#bfbfbf"
                      ></i>无优先级</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </el-form-item>

              <!--时间-->
              <el-form-item class="formItem">
                <i class="el-icon-time"></i>时间：
                <span class="block">
                  <el-date-picker
                    v-model="taskInfo.timeRange"
                    type="datetimerange"
                    :picker-options="pickerOptions"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    align="right"
                    size="mini"
                  >
                  </el-date-picker>
                </span>
              </el-form-item>

              <el-form-item class="formItem">
                <i class="iconfont icon-org"></i>
                <span v-if="taskInfo.familyPosition=='child'">父事项：</span>
                <span v-else-if="taskInfo.familyPosition=='parent'">子事项：</span>

                <el-table
                  ref="multipleTable"
                  :data="taskInfo.familyMembers"
                  tooltip-effect="dark"
                  style="width: 100%"
                >
                  <el-table-column
                    type="selection"
                    width="55"
                  >
                  </el-table-column>
                  <el-table-column
                    label="事项名称"
                    width="120"
                  >
                    <template slot-scope="scope">{{ scope.row.date }}</template>
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="状态"
                    width="120"
                  >
                  </el-table-column>
                </el-table>
              </el-form-item>
            </el-form>

          </el-aside>

          <!--main-->
          <el-main>
            这里是团队项目的聊天窗口
          </el-main>

        </el-container>
      </el-container>

    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "TaskBox",
  data () {
    return {
      dialogVisible: this.taskBoxDialogVisible,//是否弹出对话框
      isEditting: false,//是否正在编辑，用于动态class

      //事项详细数据
      taskInfo: {
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

      typeList: [{
        value: '选项1',
        label: '学习'
      }, {
        value: '选项2',
        label: '工作'
      }, {
        value: '选项3',
        label: '生活'
      }],

      //日历选择
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick (picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick (picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick (picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },

    }
  },
  props: ["taskBoxDialogVisible", "taskObj"],
  methods: {
    //显示组件
    openDialog () {
      this.dialogVisible = true;
    },
    //切换"编辑"/"保存"按钮
    changeEditState () {
      this.isEditting = !this.isEditting;
    },
    //处理isdone勾选框事件
    onTaskDoneRadioChange () {
      this.taskInfo.isdone = !this.taskInfo.isdone;
    },
    //处理优先级下拉框的指令
    handlePriorityCommand (command) {
      this.taskInfo.priority = command;
    },
    //确定isdone属性
    setIsdone () {
      this.taskInfo.isdone = this.taskInfo.taskState.indexOf("完成") >= 0;
    }
  },
  computed: {
    //计算优先级标签的颜色
    priorityColor () {
      let tagColor = 'info'
      switch (this.taskInfo.priority) {
        case '高优先级':
          tagColor = 'danger';
          break;
        case '中优先级':
          tagColor = 'warning';
          break;
        case '低优先级':
          tagColor = 'success';
          break;
        default:
          tagColor = 'info';
          break;
      }
      return tagColor;
    },

    //计算ddl与此刻的相对时间差
    timeLengthBetweenDDL () {
      let ddlDate = this.taskInfo.timeRange[1];
      if (ddlDate == '' || !ddlDate) {
        return null;
      }

      var dateEnd = new Date(ddlDate);
      var dateBegin = new Date();

      var dateDiff = dateEnd.getTime() - dateBegin.getTime(); //时间差的毫秒数
      var dayDiff = Math.floor(dateDiff / (24 * 3600 * 1000)); //计算出相差天数

      var leave1 = dateDiff % (24 * 3600 * 1000); //计算天数后剩余的毫秒数
      var hours = Math.floor(leave1 / (3600 * 1000)); //计算出小时数
      //计算相差分钟数
      var leave2 = leave1 % (3600 * 1000); //计算小时数后剩余的毫秒数
      var minutes = Math.floor(leave2 / (60 * 1000)); //计算相差分钟数
      //计算相差秒数
      var leave3 = leave2 % (60 * 1000); //计算分钟数后剩余的毫秒数
      var seconds = Math.round(leave3 / 1000);


      //将时间差表示为一个对象
      //负数不应该地板除，应该天花板除
      var timeLength = {
        day: dayDiff < 0 ? dayDiff + 1 : dayDiff,
        hour: hours < 0 ? hours + 1 : hours,
        minute: minutes < 0 ? minutes + 1 : minutes,
        second: seconds < 0 ? seconds + 1 : seconds,
      }
      console.log(timeLength)

      var Str = '';
      if (Math.abs(timeLength.day) >= 1) {
        Str = timeLength.day + "天";
      }
      else if (Math.abs(timeLength.hour) >= 1) {
        Str = timeLength.hour + "小时";
      }
      else if (Math.abs(timeLength.minute) >= 1) {
        Str = timeLength.minute + "分钟";
      }


      if (dateDiff > 0) {
        Str += '后';
      }
      else if (dateDiff < 0) {
        Str += '前';
      }
      else {
        Str = "事项状态更新中..."
      }

      //删除首位负号
      if (Str[0] == '-') {
        Str = Str.slice(1);
      }

      return Str;
    },

    //确定显示时间差的颜色
    timeLengthColor () {
      if (!this.timeLengthBetweenDDL)
        return null;

      return this.timeLengthBetweenDDL.endsWith("前") ? "red" : "blue";
    },

    //确定状态标签的颜色
    tagStateColor () {
      var color = ''
      switch (this.taskInfo.taskState) {
        case '已完成':
          color = 'success';
          break;
        case '延期完成':
          color = 'warning';
          break;
        case '延期中':
          color = 'danger';
          break;
        default:
          color = '';
          break;
      }
      return color;
    },


  },
  watch: {
    taskBoxDialogVisible: {
      handler (newVal) {
        this.dialogVisible = newVal;
      },
      deep: true,
      immediate: true
    },
    dialogVisible: {
      handler (newVal) {
        //如果子组件被关闭了，把dialogVisible=false的值同步给父组件
        if (newVal == false)
          this.$emit('resetDialogVisible');
      }
    },
  },
  mounted: function () {
    this.taskInfo = this.taskObj;//接收从父组件传来的事项信息
    console.log('对象信息：', this.taskObj)
    this.dialogVisible = this.taskBoxDialogVisible;
    this.setIsdone();
  }
}
</script>

<style lang="less" scoped>
//编辑按钮
.editButton {
  margin-left: 18px;
  margin-top: 12px;
  margin-bottom: 20px;
}

//属性表单里的一项
.formItem {
  text-align: left;
}

//页面布局css
.el-container {
  height: 530px;
  border: 1px solid rgb(240, 243, 238);
  background-color: #eaf6ea;

  .el-header {
    background-color: #ffffff;
    color: #333;
    line-height: 60px;
    //勾选框
    .isDone_radio {
      margin-right: 0px;

      //从默认圆改成方形勾
      ::v-deep .el-radio__inner {
        border-radius: 2px;
        border-width: 2px;
        border-color: var(--color-primary) !important; //外框颜色
      }
    }
    .el-tag {
      margin-left: 1em;
    }
  }

  .el-container {
    height: 0%; //保证不溢出对话框
    .el-aside {
      color: #333;
      line-height: 10px;
      overflow: hidden; // 超出部分隐藏
      overflow-y: scroll; // 设置y轴方向的滚动条

      //事项标题
      .taskTitle {
        margin-top: 21px;
        margin-bottom: 25px;
        float: left;
      }

      //详情信息
      .detailPanel {
        margin-bottom: 20px;
      }
    }

    .el-main {
      background-color: #ffffff;
      color: #333;
      //line-height: 5px;
    }
  }
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-header,
.el-container:nth-child(6) .el-header {
  line-height: 260px;
}

.el-container:nth-child(7) .el-header {
  line-height: 320px;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}

//单选框样式
.isDone_radio {
  margin-right: 0px;

  //从默认圆改成方形勾
  ::v-deep .el-radio__inner {
    border-radius: 2px;
    border-width: 2px;
    border-color: var(--color-primary) !important; //外框颜色
  }

  ::v-deep .el-radio__input.is-checked .el-radio__inner::after {
    content: "";
    width: 10px;
    height: 5px;
    border: 1px solid white;
    border-top: transparent;
    border-right: transparent;
    text-align: center;
    display: block;
    position: absolute;
    top: 0px;
    left: 0px;
    vertical-align: middle;
    transform: rotate(-45deg);
    border-radius: 0px;
    background: none;
  }
}

//修改下拉框
::v-deep .el-select {
  width: 7em;

  ::v-deep .el-input__inner {
    background-color: transparent !important;
  }
}
</style>
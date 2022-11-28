/** 
创建事项的组件
@author: hym
@editor: hym
@lastUpdate hym 10.22

传参说明：
1.createTaskBoxDialogVisible: bool 控制对话框显示
2.timeRange: 列表 时间范围
[Date(), Date()]
*/
<template>
  <div>
    <el-dialog
      title="创建新事项"
      :visible.sync="dialogVisible"
      width="600px"
      append-to-body
    >
      <el-form
        ref="form"
        :model="taskInfo"
        label-width="80px"
        :rules="createForm_rules"
      >
        <!--事项名称-->
        <el-form-item
          label="事项名称"
          prop="taskTitle"
        >
          <el-input
            v-model="taskInfo.taskTitle"
            placeholder="事项名称"
            maxlength="10"
            show-word-limit
          ></el-input>
        </el-form-item>

        <!--事项详述-->
        <el-form-item
          label="事项详述"
          prop="taskDetail"
        >
          <el-input
            type="textarea"
            placeholder="背景/信息说明"
            v-model="taskInfo.taskDetail"
            :autosize="{ minRows: 3, maxRows: 5 }"
            maxlength="30"
            show-word-limit
          ></el-input>
        </el-form-item>

        <!--分类-->
        <i class="iconfont icon-fenlei iconPosition" />
        <el-form-item class="formItem">
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
        <i class="iconfont icon-louti iconPosition" />
        <el-form-item class="formItem">
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

        <!--选择时间范围-->
        <i class="el-icon-time iconPosition" />
        <el-form-item prop="timeRange">
          <el-date-picker
            v-model="taskInfo.timeRange"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>

      <!--按钮-->
      <div class="divToRight">
        <el-button
          type="primary"
          @click="buttonExit()"
        >确认</el-button>
        <el-button
          type="info"
          @click="buttonExit()"
        >取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: "CreateTaskBox",
  data () {
    return {
      dialogVisible: this.createTaskBoxDialogVisible,
      // taskInfo: {
      //   title: "", //事项名称
      //   detail: "", //事项详述
      //   classification: "默认分类", //事项分类
      //   priority: "无优先级", //优先级
      //   start: this.dateObj.start,
      //   end: this.dateObj.end,
      // },
      taskInfo: {
        taskTitle: '',
        taskDetail: "",//详情的文字
        isdone: true,
        classification: '',
        priority: '无优先级',
        timeRange: this.timeRange,
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
      createForm_rules: {
        title: [
          { required: true, message: "请输入事项名称", trigger: "blur" },
          { max: 10, message: "事项名称最多不超过10个字符", trigger: "blur" },
        ],
        detail: [
          { max: 50, message: "事项详述最多不超过50个字符", trigger: "blur" },
        ],
      },
    };
  },
  props: ["createTaskBoxDialogVisible", "timeRange"],
  methods: {
    //处理分类的下拉框
    handleClassifyCommand () {
      this.createForm.classification = "学习";
    },

    //处理优先级下拉框的指令
    handlePriorityCommand (command) {
      this.taskInfo.priority = command;
    },

    //按钮的退出方法
    buttonExit () {
      this.dialogVisible = false;

      //这里要写把新事项往后端存的代码
      //code here......

      this.$message({
        message: "事项创建成功！",
        type: "success",
      });
    },
  },
  mounted: function () {
    this.dialogVisible = this.createTaskBoxDialogVisible;
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
  },
  watch: {
    createTaskBoxDialogVisible: {
      handler (newVal) {
        this.dialogVisible = newVal;
        this.taskInfo.timeRange = this.timeRange;
      },
      deep: true,
      immediate: true,
    },
    dialogVisible: {
      handler (newVal) {
        //如果子组件被关闭了，把dialogVisible同步给父组件
        if (newVal == false)
          this.$emit('resetDialogVisible');
      }
    }
  },
};
</script>

<style lang="less" scoped>
//设置div块级元素靠右
.divToRight {
  text-align: right;
  display: flex;
  justify-content: flex-end;
  padding-right: 20px;
}

//icon位置
.iconPosition {
  float: left;
  position: relative;
  left: 50px;
  top: 14px;
  transform: scale(1.2);
}
</style>
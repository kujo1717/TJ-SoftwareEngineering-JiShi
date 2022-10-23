/** 
单个事项信息的组件
@author: hym
@editor: hym
@lastUpdate hym 10.22

传参说明：
taskBoxDialogVisible: bool 控制对话框显示
taskid: string 事项id
*/
<template>
  <div>

    <!--对话框-->
    <el-dialog
      :visible.sync="dialogVisible"
      width="1000px"
      append-to-body
    >
      <el-container style="height: 510px; border: 1px solid #eee">

        <!--header-->
        <el-header>
          这里是header
        </el-header>
        <!--sider-->

        <el-container>

          <el-aside width="450px">
            <!--事项详情-->
            <div class="siderPanel">
              <div class="detailPanel">
                <h2 class="taskTitle">背单词</h2>
                <!--点击编辑详情-->
                <el-button
                  size="mini"
                  @click="changeEditState()"
                  class="editButton"
                ><i
                    :class="isEditting ? 'el-icon-document-checked' : 'el-icon-edit'"></i>
                </el-button>

                <div v-if="!isEditting">
                  {{detailTextArea}}
                </div>
                <el-input
                  v-else
                  type="textarea"
                  :autosize="{ minRows: 2, maxRows: 5}"
                  placeholder="请输入内容"
                  v-model="detailTextArea"
                >
                </el-input>
              </div>

              <!--事项属性-->
              <el-form label-width="0px">
                <!--分类-->
                <el-form-item class="formItem">
                  <i class="iconfont icon-fenlei"></i>分类：
                  <el-dropdown trigger="click">
                    <span class="el-dropdown-link">
                      <el-tag
                        type="success"
                        class="el-icon-arrow-down el-icon--right"
                      >{{sideTable.classification}}</el-tag>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item>学习</el-dropdown-item>
                      <el-dropdown-item>工作</el-dropdown-item>
                      <el-dropdown-item>生活</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </el-form-item>

                <!--优先级-->
                <el-form-item class="formItem">
                  <i class="iconfont icon-louti"></i>优先级：
                  <el-dropdown trigger="click">
                    <span class="el-dropdown-link">
                      <el-tag
                        type="info"
                        effect="dark"
                        class="el-icon-arrow-down el-icon--right"
                      >{{sideTable.priority}}</el-tag>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item><i
                          class="iconfont icon-youxianji"
                          style="color:#d81e06"
                        ></i>高优先级</el-dropdown-item>
                      <el-dropdown-item><i
                          class="iconfont icon-youxianji"
                          style="color:#f4ea2a"
                        ></i>中优先级</el-dropdown-item>
                      <el-dropdown-item><i
                          class="iconfont icon-youxianji"
                          style="color:#1afa29"
                        ></i>低优先级</el-dropdown-item>
                      <el-dropdown-item><i
                          class="iconfont icon-youxianji"
                          style="color:#bfbfbf"
                        ></i>无优先级</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </el-form-item>

                <!--终止时间-->
                <el-form-item class="formItem">
                  <i class="el-icon-time"></i>时间：
                  <span class="block">
                    <el-date-picker
                      v-model="value2"
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
              </el-form>
            </div>

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
      detailTextArea: "abandon",//详情的文字

      //卡片的侧边栏数据
      sideTable: {
        classification: '学习',
        priority: '无优先级',
        timeRange: '2022-10-01 00:00:00'
      },

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
      value1: '',
      value2: '',
      value3: ''
    }
  },
  props: ["taskBoxDialogVisible", "taskid"],
  methods: {
    openDialog () {
      this.dialogVisible = true;
    },
    changeEditState () {
      this.isEditting = !this.isEditting;
    }
  },
  mounted: function () {
    this.dialogVisible = this.taskBoxDialogVisible;
  },
  computed: {

  },
  watch: {
    taskBoxDialogVisible: {
      handler (newVal) {
          this.dialogVisible = newVal;
      },
      deep: true,
      immediate: true
    },
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

//侧边面板
.siderPanel {
  margin-bottom: 30px;
  margin-left: 15px;
}

//详情信息
.detailPanel {
  margin-bottom: 20px;
}

//事项标题
.taskTitle {
  margin-top: 21px;
  margin-bottom: 25px;
  float: left;
}

//属性表单里的一项
.formItem {
  text-align: left;
}
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  line-height: 5px;
  height: 470px;
}

.el-aside {
  background-color: #ecf4eb;
  color: #333;
  line-height: 10px;
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
</style>
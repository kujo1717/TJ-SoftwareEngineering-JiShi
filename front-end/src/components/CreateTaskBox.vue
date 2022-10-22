/** 
创建事项的组件
@author: hym
@editor: hym
@lastUpdate hym 10.22
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
        :model="createForm"
        label-width="80px"
        :rules="createForm_rules"
      >
        <!--事项名称-->
        <el-form-item
          label="事项名称"
          prop="title"
        >
          <el-input
            v-model="createForm.name"
            placeholder="事项名称"
            maxlength="10"
            show-word-limit
          ></el-input>
        </el-form-item>

        <!--事项详述-->
        <el-form-item
          label="事项详述"
          prop="detail"
        >
          <el-input
            type="textarea"
            placeholder="背景/信息说明"
            v-model="createForm.detail"
            :autosize="{ minRows: 3, maxRows: 5}"
            maxlength="30"
            show-word-limit
          ></el-input>
        </el-form-item>

        <!--分类-->
        <i class="iconfont icon-fenlei iconPosition" />
        <el-form-item prop="classification">
          <el-dropdown
            @command="handleClassifyCommand"
            trigger="click"
          >
            <span class="el-dropdown-link">
              {{createForm.classification}}<i
                class="el-icon-arrow-down el-icon--right"
              ></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="a">学习</el-dropdown-item>
              <el-dropdown-item command="b">工作</el-dropdown-item>
              <el-dropdown-item command="c">生活</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-form-item>

        <!--优先级-->
        <i class="iconfont icon-louti iconPosition" />
        <el-form-item prop="classification">
          <el-dropdown
            @command="handlePriorityCommand"
            trigger="click"
          >
            <span class="el-dropdown-link">
              {{createForm.priority}}<i
                class="el-icon-arrow-down el-icon--right"
              ></i>
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

        <!--选择时间范围-->
        <i class="el-icon-time iconPosition" />
        <el-form-item prop="timeRange">
          <el-time-picker
            is-range
            arrow-control
            v-model="createForm.timeRange"
            range-separator="至"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            placeholder="选择时间范围"
          >
          </el-time-picker>
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
export default {
  name: "CreateTaskBox",
  data () {
    return {
      dialogVisible: this.createTaskBoxDialogVisible,
      createForm: {
        title: '',//事项名称
        detail: '',//事项详述
        classification: '默认分类',//事项分类
        priority: '无优先级',//优先级
        timeRange: [new Date(2016, 9, 10, 8, 40), new Date(2016, 9, 10, 9, 40)],//时间范围
      },
      createForm_rules: {
        title: [
          { required: true, message: "请输入事项名称", trigger: "blur" },
          { max: 10, message: "事项名称最多不超过10个字符", trigger: "blur" },
        ],
        detail: [
          { max: 50, message: "事项详述最多不超过50个字符", trigger: "blur" },
        ],
      },
    }
  },
  props: ["createTaskBoxDialogVisible", "date"],
  methods: {
    //处理分类的下拉框
    handleClassifyCommand () {
      this.createForm.classification = "学习";
    },

    //处理优先级的下拉框
    handlePriorityCommand () {
      this.createForm.priority = "高优先级";
    },

    //按钮的退出方法
    buttonExit () {
      this.dialogVisible = false;

      //这里要写把新事项往后端存的代码
      //code here......

      this.$message({
        message: '事项创建成功！',
        type: 'success'
      });
    }
  },
  mounted: function () {
    this.dialogVisible = this.createTaskBoxDialogVisible;
  },
  computed: {

  },
  watch: {
    createTaskBoxDialogVisible: {
      handler (newVal) {
        console.log('检测到父组件参数变化！', newVal)
        console.log("子组件接收到日期：", this.date)
        this.dialogVisible = newVal;
      },
      deep: true,
      immediate: true
    },
  }

}
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
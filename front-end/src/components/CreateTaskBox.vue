/** 
创建事项的组件
@author: hym
@editor: hym
@lastUpdate 10.22
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
        :model="form"
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
            maxlength="30"
            show-word-limit
          ></el-input>
        </el-form-item>

        <!--分类-->
        <el-form-item
          label="分类"
          prop="classification"
        >
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
              <el-dropdown-item command="a">黄金糕</el-dropdown-item>
              <el-dropdown-item command="b">狮子头</el-dropdown-item>
              <el-dropdown-item command="c">螺蛳粉</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-form-item>

        <!--优先级-->
        <el-form-item
          label="优先级"
          prop="classification"
        >
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
              <el-dropdown-item command="a">黄金糕</el-dropdown-item>
              <el-dropdown-item command="b">狮子头</el-dropdown-item>
              <el-dropdown-item command="c">螺蛳粉</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-form-item>

        <!--选择时间范围-->
        <el-form-item
          label="时间"
          prop="timeRange"
        >
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
      dialogVisible: true,
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
    }
  },
  mounted: function () {

  },
  computed: {

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
</style>
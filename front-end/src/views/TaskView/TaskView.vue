/**
全日历页面
@author hym
@editor hym
@lastUpdata 10.21
*/
<template>
  <div>
    <TaskBox></TaskBox>

<el-dropdown>
  <span class="el-dropdown-link">
    下拉菜单<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
  <el-dropdown-menu slot="dropdown">
    <el-dropdown-item>黄金糕</el-dropdown-item>
    <el-dropdown-item>狮子头</el-dropdown-item>
    <el-dropdown-item>螺蛳粉</el-dropdown-item>
    <el-dropdown-item disabled>双皮奶</el-dropdown-item>
    <el-dropdown-item divided>蚵仔煎</el-dropdown-item>
  </el-dropdown-menu>
</el-dropdown>
  </div>
</template>

<script>
import TaskBox from '../../components/TaskBox.vue'
export default {
  name: "TaskView",
  components: { TaskBox },
  data () {
    return {};
  },
  mounted () { },
};
</script>

<style lang="less" scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
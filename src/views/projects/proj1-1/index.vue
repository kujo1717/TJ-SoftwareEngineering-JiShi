<template>
  <div>
    <el-tabs tab-position="right" v-model="active_tab_key">
      <el-tab-pane label="项目概览" name="overview">
        <over-view></over-view>
      </el-tab-pane>
      <el-tab-pane label="事项" name="viewlist">事项</el-tab-pane>
      <el-tab-pane label="日程" name="schedule">日程</el-tab-pane>
      <el-tab-pane label="数据报表" name="datareport">数据报表</el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import OverView from "./overview/overview.vue";
export default {
  name: "ProjectViews",
  components: { OverView },
  data() {
    return {
      active_tab_key: "overview",
    };
  },
};
</script>
<style>
</style>


<template>
  <div>
    <el-tabs tab-position="right" v-model="active_tab_key">
      <el-tab-pane label="项目概览" name="overview">
        <over-view></over-view>
      </el-tab-pane>
      <el-tab-pane label="事项" name="viewlist">
        <viewlist></viewlist>
      </el-tab-pane>
      <el-tab-pane label="日程" name="schedule">
        <time-schedule></time-schedule>
      </el-tab-pane>
      <el-tab-pane label="数据报表" name="datareport">
        <datareport></datareport>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import OverView from "./overview/overview.vue";
import Viewlist from "./viewlist/viewlist.vue";
import Datareport from "./datareport/datareport.vue";
import TimeSchedule from "./schedule/schedule.vue";
export default {
  name: "ProjectViews",
  components: { OverView, Datareport, Viewlist, TimeSchedule },
  data() {
    return {
      active_tab_key: "overview",
    };
  },
};
</script>
<style>
</style>


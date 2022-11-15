<template>
  <div class="proj_overview_view">
    <!-- 左列 -->
    <el-col
      :xs="{ span: 24 }"
      :sm="{ span: 24 }"
      :md="{ span: 24 }"
      :lg="{ span: 18 }"
      :xl="{ span: 12 }"
      style="padding-right: 8px; margin-bottom: 30px"
    >
      <!-- 项目简介卡片 -->
      <intro-card> </intro-card>
      <!-- 数据概要 -->
      <pie-chart></pie-chart>
      <!-- 项目动态 -->
      <project-log></project-log>
    </el-col>
    <!-- 右列 -->
    <el-col
      :xs="{ span: 24 }"
      :sm="{ span: 24 }"
      :md="{ span: 24 }"
      :lg="{ span: 6 }"
      :xl="{ span: 12 }"
      style="padding-right: 8px; margin-bottom: 30px"
    >
      <!-- 事项分析统计 -->
      <simple-task-analy style="margin-bottom: 1em"></simple-task-analy>

      <!-- 项目数据排行榜 -->
      <proj-ranking-list></proj-ranking-list>
    </el-col>
  </div>
</template>

<script>
import IntroCard from "./components/IntroCard.vue";
import SimpleTaskAnaly from "./components/SimpleTaskAnaly.vue";
import ProjRankingList from "./components/RangkingList.vue";
import PieChart from "./components/PieChart.vue";
import ProjectLog from "./components/ProjectLog.vue";
export default {
  name: "OverView",
  components: {
    IntroCard,
    SimpleTaskAnaly,
    ProjRankingList,
    PieChart,
    ProjectLog,
  },
  data() {
    return {};
  },
};
</script>

<style lang="less" scoped>
</style>
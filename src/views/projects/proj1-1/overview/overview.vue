<template>
  <div class="proj_overview_view">
    <el-col
      :xs="{ span: 24 }"
      :sm="{ span: 24 }"
      :md="{ span: 24 }"
      :lg="{ span: 18 }"
      :xl="{ span: 12 }"
      style="padding-right: 8px; margin-bottom: 30px"
    >
      <intro-card> </intro-card>
      <el-card>
        <pie-chart></pie-chart>
      </el-card>
    </el-col>

    <el-col
      :xs="{ span: 24 }"
      :sm="{ span: 24 }"
      :md="{ span: 24 }"
      :lg="{ span: 6 }"
      :xl="{ span: 12 }"
      style="padding-right: 8px; margin-bottom: 30px"
    >
      <simple-task-analy></simple-task-analy>
      <proj-ranking-list></proj-ranking-list>
    </el-col>
  </div>
</template>

<script>
import IntroCard from "./components/IntroCard.vue";
import SimpleTaskAnaly from "./components/SimpleTaskAnaly.vue";
import ProjRankingList from "./components/RangkingList.vue";
import PieChart from "./components/PieChart.vue";
export default {
  name: "OverView",
  components: { IntroCard, SimpleTaskAnaly, ProjRankingList, PieChart },
  data() {
    return {};
  },
};
</script>

<style lang="less" scoped>
</style>
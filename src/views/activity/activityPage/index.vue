<template>
  <div class="actpi_view">
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane label="活动概览" name="overview">
        <ActivityPageOverView></ActivityPageOverView>
      </el-tab-pane>
      <el-tab-pane label="讨论" name="chat">讨论</el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import ActivityPageOverView from "./overview.vue";
export default {
  name: "ActivityPage",
  components: { ActivityPageOverView },
  data() {
    return {
      activeName: "overview",
    };
  },
  methods: {},
  mounted() {},
};
</script>
<style lang="less" scoped>
.actpi_view {
}
</style>>



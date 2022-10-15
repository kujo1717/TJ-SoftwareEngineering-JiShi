<template>
  <div id="app">
    <!-- 路由视图 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
};
</script>

<style>
html,
body,
#app {
  height: 100%;
  min-height: 600px;
}
.el-autocomplete-suggestion {
  width: auto !important;
}
</style>

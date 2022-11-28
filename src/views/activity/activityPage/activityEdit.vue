<template>
  <div>
    <el-button @click="returnPage">返回</el-button>
  </div>
</template>
<script>
export default {
  name: "ActivityEdit",
  data() {
    return {
      id: "",
    };
  },
  methods: {
    returnPage() {
      this.$router.push({ path: "/activity/activityPage?id=" + this.id });
    },
  },
  mounted() {
    this.id = this.$route.query.id;
  },
};
</script>
<style lang="less" scoped>
</style>

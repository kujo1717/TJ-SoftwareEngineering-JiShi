<template>
  <div>
    <el-col
      :xs="{ span: 24 }"
      :sm="{ span: 24 }"
      :md="{ span: 24 }"
      :lg="{ span: 12 }"
      :xl="{ span: 12 }"
      style="padding-right: 8px; margin-bottom: 30px"
    >
<intro-card>
</intro-card>
    </el-col>
  </div>
</template>
<script>
import IntroCard from "./components/IntroCard.vue"
export default {
  name: "OverView",
  components:{IntroCard},
  data() {
    return {};
  },
};
</script>
<style>
</style>


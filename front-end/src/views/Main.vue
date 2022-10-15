/** 
单页面应用的框架，规定页面由header,main,footer组成
@author yy
@editor yy
@lastUpdate 10.15
*/
<template>
  <div class="MainView">
    <el-container>
      <el-aside class="Aside">
        <CommonAside></CommonAside>
      </el-aside>
      <el-container>
        <el-main class="Main" :style="{ minHeight: Height + 'px' }">
          <router-view></router-view>
        </el-main>
        <el-footer class="Footer" height="">
          <CommonFooter></CommonFooter>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CommonFooter from "../components/commonFooter.vue";
import CommonAside from "../components/commonAside.vue";
export default {
  name: "Main",
  components: { CommonFooter, CommonAside },
  data() {
    return {
      Height: 1000,
    };
  },
  computed: {
    isCollapse() {
      return this.$store.state.aside.isCollapse;
    },
  },
  mounted() {
    //动态设置内容高度 让footer始终居底
    this.Height = document.documentElement.clientHeight - 100;
    //监听浏览器窗口变化
    window.onresize = () => {
      this.Height = document.documentElement.clientHeight - 100;
    };

    //导航栏数据配置
    this.$store.commit("SetClientAsideData");
  },
};
</script>

<style lang="less" scoped>
.MainView {
  .Aside {
    width: auto;
    display: block;
    position: relative;
    border: solid black 2px;
  }
  .Main {
    border: solid black 2px;
    background-color: cornflowerblue;
  }
  .Footer {
    width: 100%;
    border: solid black 2px;
    background-color: darkorange;
  }
}
</style>
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
  height: 100%;
  .Aside {
    width: auto;
    display: block;
    position: relative;
    box-shadow: 2.4px 1.4px 3.1px -9px rgba(0, 0, 0, 0.029),
      6.9px 4px 8.5px -9px rgba(0, 0, 0, 0.044),
      14.4px 8.4px 17.8px -9px rgba(0, 0, 0, 0.051),
      27.1px 15.8px 35.8px -9px rgba(0, 0, 0, 0.053),
      55px 32px 80px -9px rgba(0, 0, 0, 0.05);

    border-radius: 5px;
  }
  .Main {
    background-color: var(--theme--grey);
  }
  .Footer {
    width: 100%;
    // background-color: darkorange;
  }
}
</style>
/** 
aside组件，页面统一使用的侧边导航栏，可收起
@author yy
@editor yy
@lastUpdate 10.15
*/

<template>
  <el-menu
    class="AsideView"
    :unique-opened="true"
    :collapse="isCollapse"
    default-active="1"
  >
    <!-- 根据menu数据，动态渲染一级的noChildren目录 -->
    <el-menu-item
      @click="clickMenu(item)"
      v-for="item in noChildren"
      :index="item.index"
      :key="item.path"
    >
 
      <!-- <i :class="'el-icon-' + item.icon" style="color: #ffc107"></i> -->
      <template slot="title">
        <span slot="title">{{ item.label }}</span>
      </template>
    </el-menu-item>

    <!-- 根据menu数据，动态渲染hasChildren目录 -->
    <el-submenu
      v-for="item in hasChildren"
      :index="item.index"
      :key="item.name"
    >
      <!-- 1级目录 -->
      <template slot="title">
        <!-- <i :class="'el-icon-' + item.icon" style="color: #ffc107"></i> -->
        <span slot="title">{{ item.label }}</span>
      </template>

      <!-- 2级目录 -->
      <el-menu-item-group v-for="subItem in item.children" :key="subItem.path">
        <el-menu-item :index="subItem.index" @click="clickMenu(subItem)">
          {{ subItem.label }}
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>
export default {
  name: "commonAside",
  components: {},
  data() {
    return {};
  },
  mounted() {},
  methods: {
    // 菜单点击事件，编程式路由
    clickMenu(item) {
      this.$router.push({
        name: item.name,
      });
    },
  },
  //计算属性
  computed: {
    // 返回menu中，有children的项
    noChildren() {
      return this.menu.filter((item) => {
        return !item.children;
      });
    },
    // 返回menu中，无children的项
    hasChildren() {
      return this.menu.filter((item) => {
        return item.children;
      });
    },
    isCollapse() {
      return this.$store.state.aside.isCollapse;
    },
    menu() {
      return this.$store.state.aside.aside_data;
    },
    aside_title() {
      return this.$store.state.aside.aside_title;
    },
  },

  //监听属性
  watch: {
    menu: {
      handler(new_val, old_val) {
        // console.log(new_val, old_val);
      },

      immediate: true,
    },
  },
};
</script>

<style lang="less" scoped>
.AsideView {

  /deep/ .el-submenu__title:hover {
    background-color: var(--menu-hover) !important;
  }
  /deep/ .el-menu-item:hover {
    background-color: var(--menu-hover) !important;

  }

  .el-menu-item {
    color: black;
  }
  .el-menu-item.is-active {
    color: black;

    background-color: var(--menu-chosen);
  }
}
</style>
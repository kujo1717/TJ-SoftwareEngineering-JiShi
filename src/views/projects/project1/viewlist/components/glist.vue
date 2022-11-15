<template>
  <div class="glist_view">
    <div class="glv_group" v-for="(item, index) in listData" :key="index">
      <div class="glv_group_title">{{ item.title }}</div>
      <draggable class="glv_group_drag" :list="item.list" group="people">
        <transition-group style="min-height: 3em; display: block">
          <div
            class="draggable-item"
            v-for="element in item.list"
            :key="element.id"
          >
            <span>{{ element.name }}</span>
          </div>
        </transition-group>
      </draggable>
    </div>
  </div>
</template>
<script>
// import glistitem from "./glistitem";
import draggable from "vuedraggable";

export default {
  name: "glist",
  props: {
    listData: {
      type: [Array],
      default: () => [],
    },
  },
  components: {
    // glistitem,
    draggable,
  },
};
</script>
<style lang="less" scoped>
.glist_view {
  height: 100%;
  background-color: #f5f5f5;
  padding: 20px;
  .glv_group {
    float: left;
    width: 240px;
    height: 100%;
    margin-right: 20px;
    .glv_group_title {
      font-size: 14px;
      font-weight: 700;
      margin-bottom: 10px;
    }
    .glv_group_drag {
      height: 100%;
      .draggable-item {
        margin-bottom: 8px;
      }
    }
  }
}
</style>

<template>
  <el-card class="SimpleTaskAnaly_view">
    <div class="sta_content_box">
      <div class="sta_line1">
        <div class="sta_all_task sta_numberspan">
          <span class="colornum">{{ all_task.num }}</span>
          <span class="hint">{{ all_task.label }}</span>
        </div>
        <div class="sta_done sta_numberspan">
          <span class="colornum">{{ done.num }}</span>
          <span class="hint">{{ done.label }}</span>
        </div>
        <div class="sta_not_done sta_numberspan">
          <span class="colornum">{{not_done.num }}</span>
          <span class="hint">{{not_done.label }}</span>
        </div>
      </div>

      <div class="sta_line2">
        <div class="sta_todoay_task sta_numberspan">
          <span class="colornum">{{
           todoay_task.num
          }}</span>
          <span class="hint">{{
           todoay_task.label
          }}</span>
        </div>
        <div class="sta_delayed sta_numberspan">
          <span class="colornum">{{delayed.num }}</span>
          <span class="hint">{{delayed.label }}</span>
        </div>
        <div class="sta_delayed_done sta_numberspan">
          <span class="colornum">{{
           delayed_done.num
          }}</span>
          <span class="hint">{{
           delayed_done.label
          }}</span>
        </div>
      </div>
    </div>
    <div class="sta_bottom"></div>
  </el-card>
</template>
<script>
export default {
  name: "SimpleTaskAnaly",
  data() {
    return {
      //报表展示的数据
      all_task: {},
      done: {},
      not_done: {},
      todoay_task: {},
      delayed: {},
      delayed_done: {},
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.GetStaContentData();
    });
  },
  methods: {
    GetStaContentData() {
      this.all_task = {
        key: "all_task",
        label: "总事项",
        num: 5,
      };
      this.done = {
        key: "done",
        label: "已完成",
        num: 2,
      };
      this.not_done = {
        key: "not_done",
        label: "未完成",
        num: 3,
      };
      this.todoay_task = {
        key: "todoay_task",
        label: "今日事项",
        num: 0,
      };
      this.delayed = {
        key: "delayed",
        label: "已延期",
        num: 1,
      };
      this.delayed_done = {
        key: "delayed_done",
        label: "延期完成",
        num: 0,
      };
    },
  },
  computed: {},
};
</script>

<style lang="less" scoped>
.SimpleTaskAnaly_view {
  .sta_content_box {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .sta_line1 {
      display: flex;
      flex-direction: row;
      margin-bottom: 2em;
      justify-content: center;
      align-items: center;
    }
    .sta_line2 {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
    }
    .sta_numberspan {
      display: flex;
      flex-direction: column;
      margin-left: 1em;
      margin-right: 1em;
      justify-content: center;
      align-items: center;
    }
    .hint {
      font-size: 0.8em;
      color: #8f8f8f;
    }
    .colornum {
      font-size: 2em;
      font-weight: bold;
    }
  }
  .sta_bottom {
  }
}
</style>
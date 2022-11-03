<template>
  <el-card class="SimpleTaskAnaly_view">
    <div class="sta_content_box">
      <div class="sta_line1">
        <div class="sta_all_task sta_numberspan">
          <span class="colornum">{{ sta_content_data_dict.all_task.num }}</span>
          <span class="hint">{{ sta_content_data_dict.all_task.label }}</span>
        </div>
        <div class="sta_done sta_numberspan">
          <span class="colornum">{{ sta_content_data_dict.done.num }}</span>
          <span class="hint">{{ sta_content_data_dict.done.label }}</span>
        </div>
        <div class="sta_not_done sta_numberspan">
          <span class="colornum">{{ sta_content_data_dict.not_done.num }}</span>
          <span class="hint">{{ sta_content_data_dict.not_done.label }}</span>
        </div>
      </div>

      <div class="sta_line2">
        <div class="sta_todoay_task sta_numberspan">
          <span class="colornum">{{
            sta_content_data_dict.todoay_task.num
          }}</span>
          <span class="hint">{{
            sta_content_data_dict.todoay_task.label
          }}</span>
        </div>
        <div class="sta_delayed sta_numberspan">
          <span class="colornum">{{ sta_content_data_dict.delayed.num }}</span>
          <span class="hint">{{ sta_content_data_dict.delayed.label }}</span>
        </div>
        <div class="sta_delayed_done sta_numberspan">
          <span class="colornum">{{
            sta_content_data_dict.delayed_done.num
          }}</span>
          <span class="hint">{{
            sta_content_data_dict.delayed_done.label
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
      sta_content_data: [],
    };
  },
  mounted() {
    this.GetStaContentData();
  },
  methods: {
    GetStaContentData() {
      this.sta_content_data = [
        {
          key: "all_task",
          label: "总事项",
          num: 5,
        },
        {
          key: "done",
          label: "已完成",
          num: 2,
        },
        {
          key: "not_done",
          label: "未完成",
          num: 3,
        },
        {
          key: "todoay_task",
          label: "今日事项",
          num: 0,
        },
        {
          key: "delayed",
          label: "已延期",
          num: 1,
        },
        {
          key: "delayed_done",
          label: "延期完成",
          num: 0,
        },
      ];
    },
  },
  computed: {
    sta_content_data_dict: {
      // getter
      get: function () {
        let dict = {};
        this.sta_content_data.forEach((ele) => {
          dict[ele.key] = ele;
        });
        return dict;
      },
      // setter
      // set: function (newValue) {},
    },
  },
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
    .colornum{
        font-size: 2em;
        font-weight: bold;
    }
  }
  .sta_bottom {
  }
}
</style>
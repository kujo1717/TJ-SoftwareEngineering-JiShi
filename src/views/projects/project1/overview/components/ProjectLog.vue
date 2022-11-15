<template>
  <el-card class="mpl_view">
    <div class="mpl_view_brief_head">
      <span class="title_bb">项目动态</span>
    </div>
    <el-divider></el-divider>
    <div class="mpl_brief_content_box">
      <!-- 一个log分块 -->
      <div
        class="mpl_brief_block"
        v-for="(logs, index) in data_log_brief"
        :key="index"
      >
        <span style="color: #8f8f8f;font-weight:bold;" class="mpl_brief_block_head">{{
          logs.date_label
        }}</span>
        <div
          class="mpl_brief_block_item"
          v-for="(log, log_index) in logs.logs"
          :key="log_index"
          style="margin-top: 0.5em; margin-bottom: 1em"
        >
          <span>{{ log.content_label }}</span>
          <span style="float: right; color: #8f8f8f; font-size: 0.8em">{{
            log.time_label
          }}</span>
        </div>
      </div>
    </div>
  </el-card>
</template>
<script>
export default {
  name: "ProjectLog",
  data() {
    return {
      data_log_brief: [],
      form_log_brief: [],
    };
  },
  methods: {
    //获取日志，简要版
    GetLogBrief() {
      //获取日志brief
      this.data_log_brief = [
        {
          //原数据
          date: {
            year: "2022",
            month: "11",
            day: "2",
          },
          date_label: "",
          logs: [
            {
              hour: "3",
              minute: "12",
              subject: "user_name",
              object: "task_112",
              operation: "delete",
              //需要计算的label
              time_label: "",
              content_label: "",
            },
          ],
        },
        {
          //原数据
          date: {
            year: "2022",
            month: "11",
            day: "4",
          },
          date_label: "",
          logs: [
            {
              hour: "8",
              minute: "45",
              subject: "user_name",
              object: "task_12",
              operation: "create",
              //需要计算的label
              time_label: "",
              content_label: "",
            },
          ],
        },
      ];
      //根据原数据，处理得到展示的label
      this.data_log_brief.forEach((time_logs) => {
        time_logs.date_label =
          time_logs.date.year +
          "年" +
          time_logs.date.month +
          "月" +
          time_logs.date.day +
          "日";
        time_logs.logs.forEach((ele) => {
          ele.time_label = ele.hour + ":" + ele.minute;
          ele.content_label =
            ele.subject + " do " + ele.operation + " to " + ele.object;
        });
      });
      // console.log(this.data_log_brief);
    },
  },
  mounted() {
    this.GetLogBrief();
  },
};
</script>
<style lang="less" scoped>
.mpl_view {
  margin-top: 1em;
  .mpl_view_brief_head {
    .title_bb {
      font-size: 1.5em;
      color: black;
      font-weight: bold;
    }
  }
}
</style>

<template>
  <div>
    <el-card class="piechart_el_card">
      <div slot="header" class="pelc_header">
        <span class="header_text">数据概要</span>
        <!-- <span class="pelc_time_label">{{ time_label.date_label }}</span> -->
        <span class="header_dates">
          <div class="pel_time_picker">
            <el-date-picker
              v-model="time_picker"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="pickerOptions"
              　format="yyyy-MM-dd"
              @change="selectTimeChange"
              v-on:DatePickerShortCut="t"
            >
            </el-date-picker>
          </div>
        </span>
      </div>
      <div class="pie_chart1_chart" id="pie_chart1_chart"></div>

      <el-divider direction="vertical"></el-divider>

      <div class="pie_chart2_chart" id="pie_chart2_chart"></div>
    </el-card>
  </div>
</template>
 
<script>
import * as echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "./mixins/resize";

export default {
  name: "PieChart",
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart",
    },
    width: {
      type: String,
      default: "100%",
    },
    height: {
      type: String,
      default: "300px",
    },
  },
  data() {
    return {
      //chart itself
      chart1: null,
      chart2: null,

      //纯数据data
      chart1_data: [],
      chart2_data: [],

      //组装后的option
      chart1_opt: {},
      chart2_opt: {},

      /*yy mm dd */

      time_label: {
        start_time_label: {
          year: "",
          month: "",
          day: "",
        },
        end_time_label: {
          year: "",
          month: "",
          day: "",
        },
        date_label: "本月",
      },
      time_picker: [this.GetCurrentTime(), this.GetMonthLaterTime()],
      pickerOptions: {
        shortcuts: [
          {
            text: "本周",
            onClick(picker) {
              // 获取今天
              const end = new Date();
              // 获取当前周的第一天
              const start = new Date(
                end.getFullYear(),
                end.getMonth(),
                end.getDate() - ((end.getDay() + 6) % 7)
              );
              start.setTime(start.getTime());
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "本月",
            onClick(picker) {
              const end = new Date();
              // 获取当前月的第一天
              const start = new Date(end.getFullYear(), end.getMonth(), 1);
              start.setTime(start.getTime());
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "上一周",
            onClick(picker) {
              const dataValue = new Date();
              const year = dataValue.getFullYear();
              const month = dataValue.getMonth() + 1;
              const day = dataValue.getDate();
              var thisWeekStart; //本周周一的时间
              if (dataValue.getDay() == 0) {
                //周天的情况；
                thisWeekStart =
                  new Date(year + "/" + month + "/" + day).getTime() -
                  (dataValue.getDay() + 6) * 86400000;
              } else {
                thisWeekStart =
                  new Date(year + "/" + month + "/" + day).getTime() -
                  (dataValue.getDay() - 1) * 86400000;
              }
              const prevWeekStart = thisWeekStart - 7 * 86400000; //上周周一的时间
              const prevWeekEnd = thisWeekStart - 1 * 86400000; //上周周日的时间
              const start = new Date(prevWeekStart); //开始时间
              const end = new Date(prevWeekEnd); //结束时间
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "上一月",
            onClick(picker) {
              const oDate = new Date();
              var year = oDate.getFullYear();
              var month = oDate.getMonth();
              var start, end;
              if (month == 0) {
                year--;
                start = new Date(year, 11, 1);
                end = new Date(year, 11, 31);
              } else {
                start = new Date(year, month - 1, 1);
                end = new Date(year, month, 0);
              }

              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一周",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart();
    });
  },
  beforeDestroy() {
    if (!this.chart1) {
      return;
    }
    this.chart1.dispose();
    this.chart1 = null;

    this.chart2.dispose();
    this.chart2 = null;
  },
  methods: {
    //
    t(val) {
      console.log(val);
    },
    testUpd() {
      this.chart2_data = this.chart1_data;
      this.GetOptChart2();
      this.chart2.setOption(this.chart2_opt);
    },
    /**时间选择 */
    selectTimeChange(val) {
      //default is recent month

      if (val == null) {
        // console.log(end);
        this.$nextTick(() => {
          this.time_picker = new Array(2);
          this.$set(this.time_picker, 0, this.GetCurrentTime());
          this.$set(this.time_picker, 1, this.GetMonthLaterTime());
          this.time_label.date_label = "本月";
        });
      }

      this.$nextTick(() => {
        // console.log(this.time_picker);
        // console.log(this.start_time_label, this.end_time_label);
        this.GetTimeLabel();
      });
    },
    GetCurrentTime() {
      return new Date();
    },
    GetMonthLaterTime() {
      const end = new Date();
      const start = new Date();
      end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
      return end;
    },
    GetTimeLabel() {
      this.time_label.start_time_label.year = this.time_picker[0].getFullYear();
      this.time_label.start_time_label.month =
        this.time_picker[0].getMonth() + 1;
      this.time_label.start_time_label.day = this.time_picker[0].getDate();

      this.time_label.end_time_label.year = this.time_picker[1].getFullYear();
      this.time_label.end_time_label.month = this.time_picker[1].getMonth() + 1;
      this.time_label.end_time_label.day = this.time_picker[1].getDate();
    },
    //画图init
    initChart() {
      //第一pie chart
      this.GetDataChart1();
      this.GetOptChart1();
      this.chart1 = echarts.init(
        document.getElementById("pie_chart1_chart"),
        "macarons"
      );
      this.chart1.setOption(this.chart1_opt);

      //第2 pie chart
      this.GetDataChart2();
      this.GetOptChart2();
      this.chart2 = echarts.init(
        document.getElementById("pie_chart2_chart"),
        "macarons"
      );
      this.chart2.setOption(this.chart2_opt);
    },
    /*获取图表1数据 */
    GetDataChart1() {
      this.chart1_data = [
        { value: 320, name: "进行中" },
        { value: 240, name: "已完成" },
        { value: 149, name: "已延期" },
        { value: 100, name: "延期完成" },
      ];
    },
    /*获取图表2数据 */
    GetDataChart2() {
      this.chart2_data = [
        { value: 1048, name: "分组1" },
        { value: 735, name: "分组2" },
        { value: 580, name: "分组3" },
        { value: 300, name: "分组4" },
      ];
    },

    /*组装chart1的opt */
    GetOptChart1() {
      this.chart1_opt = {
        tooltip: {
          trigger: "item",
          formatter: "{b} : {c} ({d}%)",
        },
        legend: {
          left: "center",
          bottom: "10",
          data: ["进行中", "已完成", "已延期", "延期完成"],
        },
        series: [
          {
            // name: "WEEKLY WRITE ARTICLES",
            type: "pie",
            roseType: "radius",
            radius: [15, 95],
            center: ["50%", "38%"],
            data: this.chart1_data,
            animationEasing: "cubicInOut",
            animationDuration: 2600,
          },
        ],
      };
    },

    /*组装chart2的opt */
    GetOptChart2() {
      this.chart2_opt = {
        title: {
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          left: "center",
          bottom: "10",
        },
        series: [
          {
            type: "pie",
            radius: "50%",
            data: this.chart2_data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };
    },
  },
};
</script>
<style lang="less" scoped>
.piechart_el_card {
  height: 100%;
  width: 100%;
  /deep/.pelc_header {
    display: flex;
    align-items: center;
    .pelc_time_label {
      border-radius: 30px;
      border: 2px solid #dcdfe6;
      padding: 0.3em;
    }
    .header_text {
      font-weight: bold;
      font-size: 1.3em;
      margin-right: 1em;
    }
    .header_dates {
      margin-left: auto;
    }
  }
  /deep/.el-card__body {
    display: flex;
    flex-direction: row;
  }

  .pie_chart1_chart {
    height: 300px;
    width: 50%;
  }
  /deep/.el-divider--vertical {
    display: inline-block;
    width: 1px;
    height: 300px;
    margin: 0 8px;
    vertical-align: middle;
    // position: relative;
  }
  .pie_chart2_chart {
    height: 300px;
    width: 50%;
  }
}
</style>

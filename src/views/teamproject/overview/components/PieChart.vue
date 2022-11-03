<template>
  <div>
    <el-card class="piechart_el_card">
      <div slot="header">
        <span class="header_text">数据概要</span>
        <span class="header_dates"></span>
      </div>

      <div class="pie_chart1_chart" id="pie_chart1_chart"></div>
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
      chart1: null,
      chart2: null,
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
    //画图
    initChart() {
      //第一pie chart
      this.chart1 = echarts.init(
        document.getElementById("pie_chart1_chart"),
        "macarons"
      );
      this.chart1.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
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
            data: [
              { value: 320, name: "进行中" },
              { value: 240, name: "已完成" },
              { value: 149, name: "已延期" },
              { value: 100, name: "延期完成" },
            ],
            animationEasing: "cubicInOut",
            animationDuration: 2600,
          },
        ],
      });

      //第2 pie chart
      this.chart2 = echarts.init(
        document.getElementById("pie_chart2_chart"),
        "macarons"
      );
      this.chart2.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
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
            data: [
              { value: 320, name: "进行中" },
              { value: 240, name: "已完成" },
              { value: 149, name: "已延期" },
              { value: 100, name: "延期完成" },
            ],
            animationEasing: "cubicInOut",
            animationDuration: 2600,
          },
        ],
      });
    },
  },
};
</script>
<style lang="less" scoped>
.piechart_el_card {
  height: 100%;
  width: 100%;
  /deep/.el-card__body {
    display: flex;
    flex-direction: row;
  }

  .pie_chart1_chart {
    height: 300px;
    width: 50%;
  }
  .pie_chart2_chart {
    height: 300px;
    width: 50%;
  }
}
</style>

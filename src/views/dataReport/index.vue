<template>
  <div>
    <el-card>
      <el-date-picker
        v-model="value2"
        type="month"
        placeholder="选择月"
        :editable="false"
        size="mini"
      >
      </el-date-picker>
    </el-card>

    <el-card>
      <el-row :gutter="10">
        <!--上面是顶部展示栏-->

        <!--header-->
        <div
          slot="header"
          class="clearfix"
        >
          <span>专家方案</span>
        </div>

        <el-col
          :xs="bigSize*upLeftPanelRate"
          :sm="smallSize*upLeftPanelRate"
          :md="smallSize*upLeftPanelRate"
          :lg="smallSize*upLeftPanelRate"
          :xl="smallSize*upLeftPanelRate"
        >
          <div id="col1">
            <div><i class="el-icon-edit"></i></div>
            <div>
              <h1>5件</h1>
            </div>
            <div>
              <h3>已完成</h3>
            </div>
          </div>
        </el-col>

        <el-col
          :xs="bigSize*upLeftPanelRate"
          :sm="smallSize*upLeftPanelRate"
          :md="smallSize*upLeftPanelRate"
          :lg="smallSize*upLeftPanelRate"
          :xl="smallSize*upLeftPanelRate"
        >
          <div id="col2">
            <i class="el-icon-edit"></i>
            <div>
              <h1>5件</h1>
            </div>
            <div>
              <h3>进行中</h3>
            </div>
          </div>
        </el-col>

        <el-col
          :xs="bigSize*upLeftPanelRate"
          :sm="smallSize*upLeftPanelRate"
          :md="smallSize*upLeftPanelRate"
          :lg="smallSize*upLeftPanelRate"
          :xl="smallSize*upLeftPanelRate"
        >
          <div id="col3">
            <i class="el-icon-edit"></i>
            <div>
              <h1>5件</h1>
            </div>
            <div>
              <h3>延期中</h3>
            </div>
          </div>
        </el-col>

        <el-col
          :xs="bigSize*upLeftPanelRate"
          :sm="smallSize*upLeftPanelRate"
          :md="smallSize*upLeftPanelRate"
          :lg="smallSize*upLeftPanelRate"
          :xl="smallSize*upLeftPanelRate"
        >
          <div id="col4">
            <i class="el-icon-edit"></i>
            <div>
              <h1>5件</h1>
            </div>
            <div>
              <h3>延期完成</h3>
            </div>
          </div>
        </el-col>

      </el-row>
    </el-card>
    <br>

    <!--下面的主展示行-->
    <el-row :gutter="10">
      <!--曲线图-->
      <el-col
        :xs="24"
        :sm="16"
        :md="16"
        :lg="16"
        :xl="16"
      >
        <el-card shadow="always">
          <div
            slot="header"
            class="clearfix"
          >
            <span>数据趋势</span>
            <span class="card-div-desc">{{ lineCardTitle }}</span>
            <el-radio-group
              style="float: right; padding: 3px 0"
              v-model="lineDataType"
              size="mini"
              @change="handleLineChange"
            >
              <el-radio-button label="order">新建事项数</el-radio-button>
              <el-radio-button label="sale">完成事项数</el-radio-button>
            </el-radio-group>
          </div>
          <div>
            <LineHeapChart
              height="600px"
              :xAxisData="lineXAxisData"
              :seriesData="lineSeriesData"
            />
          </div>
        </el-card>
      </el-col>

      <el-col
        :xs="12"
        :sm="8"
        :md="8"
        :lg="8"
        :xl="8"
      >
        <el-card shadow="always">
          <!--圆饼图-->
          <PieFlatChart :seriesData="pieSeriesData" />
        </el-card>

        <el-card shadow="always">
          <!--柱状图-->
          <BarChart :seriesData="barSeriesData" />
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import LineHeapChart from './components/LineHeapChart'
import PieFlatChart from './components/PieFlatChart'
import PieFilletChart from './components/PieFilletChart'
import BarChart from './components/BarChart'
export default {
  name: "DataReport",
  components: {
    LineHeapChart,
    PieFlatChart,
    PieFilletChart,
    BarChart
  },
  data () {
    return {
      bigSize: 3,
      smallSize: 2,
      upLeftPanelRate: 3,

      //状态圆饼图的数据
      pieSeriesData: [
        {
          name: '状态数据',
          type: 'pie',
          radius: '50%',
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          },
          itemStyle: {
            normal: {

              label: {
                show: true,
                overflow: 'none',
                formatter: '{b} : {c} ({d}%)',
                //设置标签溢出显示
                position: 'outer',
                alignTo: 'edge',
                margin: 0
              },
              labelLine: { show: true }
            }
          },
          data: [
            { value: 10, name: '已完成' },
            { value: 13, name: '进行中' },
            { value: 2, name: '延期中' },
            { value: 4, name: '延期完成' }
          ]
        }
      ],
      barSeriesData: [
        {
          name: '完成率',
          value: 0.3,
          itemStyle: {
            color: 'green'
          }
        },
        {
          name: '及时率',
          value: 0.5,
          itemStyle: {
            color: 'blue'
          }
        },
        {
          name: '延期率',
          value: 0.7,
          itemStyle: {
            color: 'orange'
          }
        },
      ],

      lineSeriesData: [{
        name: '新建事项数',
        type: 'line',
        stack: '总量',
        emphasis: {
          focus: 'series'
        },
        areaStyle: {
          // 改变区域颜色
          //color: '#fadb14'
        },
        //光滑线条
        smooth: true,
        data: [67, 6213, 66, 67, 78, 632, 1410, 4552, 4512, 555]
      }, {
        name: '完成事项数',
        type: 'line',
        stack: '总量',
        areaStyle: {},
        emphasis: {
          focus: 'series'
        },
        smooth: true,
        data: [120, 132, 101, 134, 90, 230, 210, 3466, 67, 36]
      }],
      lineXAxisData: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15',
        '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30'],



    };
  },



};
</script>

<style lang="less" scoped>
// .divCenter {
// margin-left: 30px;
// }

div {
  .el-card {
    margin-bottom: 1em;
  }
  .el-card {
    .el-row {
      .el-col {
        div#col1 {
          margin-left: 5em;
          i {
            position: relative;
            left: 1em;
          }
          h3 {
            color: rgb(68, 68, 236);
          }
        }
      }
      .el-col {
        div#col2 {
          margin-left: 5em;
          i {
            position: relative;
            left: 1em;
          }
          h3 {
            color: green;
          }
        }
      }
      .el-col {
        div#col3 {
          margin-left: 5em;
          i {
            position: relative;
            left: 1em;
          }
          h3 {
            color: red;
          }
        }
      }
      .el-col {
        div#col4 {
          margin-left: 5em;
          i {
            position: relative;
            left: 1em;
          }
          h3 {
            color: rgb(199, 199, 13);
          }
        }
      }
    }
  }
  // .el-card{

  // }
}
</style>

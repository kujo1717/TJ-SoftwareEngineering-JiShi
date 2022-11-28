/**
/**
四象限视图
@author hym
@editor hym
@lastUpdata hym 11.2
*/

<template>
  <div>
    <div
      id="myChart"
      style="width:100%;height:700px;"
    ></div>
  </div>
</template>

<script>
export default {
  name: 'QuadrantView',
  data () {
    return {
      r: 40,
      echartOptions: {
        title: [
          {
            text: '四象限事项管理法',
            subtext: '分析对象：事项',
            left: 'center',
            textStyle: {
              fontSize: 16,
            },
          },
          {
            text: '重要且紧急',
            z: 1,
            textAlign: 'center',
            left: '25%',
            top: '7%',
            textStyle: {
              fontSize: 14,
              fontWeight: 'normal',
              color: '#f2757d',
            },
          },
          {
            text: '重要不紧急',
            z: 1,
            textAlign: 'center',
            left: '75%',
            top: '7%',
            textStyle: {
              fontSize: 14,
              fontWeight: 'normal',
              color: '#8ce759',
            },
          },
          {
            text: '不重要但紧急',
            z: 1,
            textAlign: 'center',
            left: '25%',
            top: '85%',
            textStyle: {
              fontSize: 14,
              fontWeight: 'normal',
              color: '#a271f8',
            },
          },
          {
            text: '不重要不紧急',
            z: 1,
            textAlign: 'center',
            left: '75%',
            top: '85%',
            textStyle: {
              fontSize: 14,
              fontWeight: 'normal',
              color: '#37dee5',
            },
          },

        ],
        grid: {
          top: '10%',
          left: '3%',
          right: '7%',
          bottom: '1%',
          containLabel: true,
        },
        tooltip: {
          showDelay: 0,
          formatter: (params) => {
            if (params.value.length > 1) {
              return `${params.seriesName}优先级:<br/>${params.value[0]}cm${params.value[1]}kg${params.value[2]}`;
            }
            return false;
          },

        },
        legend: [
          {
            orient: 'horizontal',
            x: '89%',
            y: '7%',
            align: 'left',
            data: ['高'],
            textStyle: {
              fontSize: 14,
            },
          },
          {
            orient: 'horizontal',
            x: '94%',
            y: '7%',
            align: 'left',
            data: ['中'],
            textStyle: {
              fontSize: 14,
            },
          },
          {
            orient: 'horizontal',
            x: '89%',
            y: '10%',
            align: 'left',
            data: ['低'],
            textStyle: {
              fontSize: 14,
            },
          },
          {
            orient: 'horizontal',
            x: '94%',
            y: '10%',
            align: 'left',
            data: ['无'],
            textStyle: {
              fontSize: 14,
            },
          },
        ],
        xAxis: [{
          type: 'value',
          name: '紧急度',
          scale: true,
          splitLine: {
            show: false,
          },
          axisTick: {
            show: false // 不显示坐标轴刻度线
          },
          data: [0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1],
        }],
        yAxis: [{
          type: 'value',
          scale: true,
          name: '重要度',
          splitLine: {
            show: false,
          },
          axisTick: {
            show: false // 不显示坐标轴刻度线
          },
          max: '1',
        }],
        series: [
          {
            name: '高',
            z: 3,
            type: 'scatter',
            //symbol: 'roundRect',
            symbolSize: 0,
            itemStyle: {
              normal: {
                color: '#EF535C',
              },
            },
            data: [
              [0.4, 0.65], [0.1, 0.59], [0.3, 0.63], [0.26, 0.790],
            ],
          },
          {
            name: '中',
            z: 3,
            type: 'scatter',
            symbolSize: 0,
            itemStyle: {
              normal: {
                color: '#6FE12F',
              },
            },
            data: [
              [0.6, 0.8], [0.7, 0.66], [0.8, 0.54], [0.89, 0.9],
            ],
          },

          {
            name: '低',
            type: 'scatter',
            symbolSize: 0,
            data: [
              [0.3, 0.2], [0.4, 0.28]
            ],
            itemStyle: {
              normal: {
                color: '#8B4DF6',
              },
            },
          },
          {
            name: '无',
            type: 'scatter',
            symbolSize: 0,
            data: [
              [0.6, 0.3]
            ],
            itemStyle: {
              normal: {
                color: '#05D6DE',
              },
            },

          },
          {
            name: 'wu',
            type: 'scatter',
            data: [[0.5, 0]],
            itemStyle: {
              normal: {
                color: '#999',
              },
            },
            markLine: {
              data: [
                {
                  type: 'average',
                  name: 'wu',
                  valueIndex: 0,
                },
              ],
            },

          },
          {
            name: 'six',
            type: 'scatter',
            data: [[0, 0.5]],
            itemStyle: {
              normal: {
                color: '#999',
              },
            },
            markLine: {
              data: [
                {
                  type: 'average',
                  name: 'six',
                },
              ],
              label: {
                show: false,
              },
            },
          },
        ],

        graphic: this.getGraphic()


      }
    }
  },
  methods: {
    drawLine () {
      let echarts = require('echarts');
      // 基于准备好的dom，初始化echarts实例
      let dom = echarts.init(document.getElementById("myChart"));
      // 绘制图表
      dom.setOption(this.echartOptions);
    },

    //根据taskList来生成graphic
    getGraphic () {
      const taskList = [
        {
          id: '001',
          taskTitle: '背单词1',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '无优先级',
          timeRange: ["2022-11-01T12:30:00", '2022-11-02T12:30:00'],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '无优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '低优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '低优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '中优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '中优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '高优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '高优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        },
        {
          id: '002',
          taskTitle: '背单词2',
          taskDetail: "先背两小时单词",//详情的文字
          isdone: true,
          taskState: '延期完成',
          classification: '学习',
          priority: '高优先级',
          timeRange: [new Date(), new Date()],
          familyPosition: 'parent',
          familyMembers: [],
        }
      ]
      const r = 40;
      let groupList = []
      let ii = [0, 0, 0, 0]
      for (let i = 0; i < taskList.length; i++) {
        let leftBase;
        let topBase;
        let index;
        let color;
        switch (taskList[i].priority) {
          case '高优先级':
            leftBase = 80;
            topBase = 80;
            color = "#EF535C"
            index = 0;
            break;
          case '中优先级':
            leftBase = 650;
            topBase = 80;
            color = "#6FE12F";
            index = 1;
            break;
          case '低优先级':
            leftBase = 80;
            topBase = 400;
            color = "#8B4DF6";
            index = 2;
            break;
          case '无优先级':
            leftBase = 650;
            topBase = 400;
            color = "#05D6DE";
            index = 3;
            break;
        }
        groupList.push({
          type: 'group',// 组（可以包含图形、文字、图片）
          left: leftBase + ii[index] % 5 * r * 2.5,
          top: topBase + Math.floor(ii[index] / 5) * r * 2.5,
          children: [
            {
              type: 'circle',
              z: 100,
              left: '0',
              top: '0',
              shape: {
                cx: 50,
                cy: 50,
                r: 40
              },
              style: {
                fill: color,
              }
            },
            {
              type: 'text',// 文字
              z: 100,
              left: 20,
              top: 32,
              style: {
                fill: '#000000',
                text: taskList[i].taskTitle,
                font: '14px Microsoft YaHei'
              }
            },
          ]
        })
        ii[index]++;
      }
      return groupList
    }//end of method
  },
  mounted: function () {
    this.drawLine();
  }
}
</script>

<style lang="less" scoped>
.echart {
  width: 600px;
  height: 800px;
}
</style>
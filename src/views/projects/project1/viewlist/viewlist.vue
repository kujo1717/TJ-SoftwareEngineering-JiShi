<template>
  <div class="mpv_viewlist_view">
    <div class="mpvv_tabs">
      <el-tabs v-model="activeName" type="card">
        <el-tab-pane label="表格" name="table" class="mpvv_T_"
          >表格</el-tab-pane
        >
        <el-tab-pane label="分组" name="group" class="mpvv_G_">
          <!-- 顶部下拉框筛选 -->
          <div>
            <el-select
              @change="G_TopSelectValChange"
              v-model="G_select_allTasks_Option"
              placeholder="请选择"
            >
              <el-option
                v-for="item in G_select_allTasks_Options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </div>

          <!-- 最终分组展示 -->
          <div class="mpvv_G_content">
            <grouplist :listsData="G_group_lists"></grouplist>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
import draggable from "vuedraggable";
import grouplist from "./components/grouplist.vue";
export default {
  name: "ProjectViewList",
  components: { draggable, grouplist },

  data() {
    return {
      activeName: "group",
      /**
       * Group数据
       */
      //顶部Select
      G_select_allTasks_Options: [
        {
          value: "all_task",
          label: "所有事项",
        },
        {
          value: "I_create",
          label: "我创建的事项",
        },
        {
          value: "I_participate",
          label: "我参与的事项",
        },
      ],
      G_select_allTasks_Option: "all_task",
      //分组展示的数据
      Group_content_data: [],
      //glist测试数据
      todoData: [
        {
          title: "待处理",
          list: [
            {
              id: 1,
              name: "测试1",
            },
          ],
        },
        {
          title: "进行中",
          list: [
            {
              id: 6,
              name: "测试6",
            },
          ],
        },
        {
          title: "已完成",
          list: [
            {
              id: 11,
              name: "测试11",
            },
          ],
        },
      ],
      //新group测试数据
      ColGroups_1: [
        {
          name: "分组1",
          list: [
            {
              name: "t1",
            },
            {
              name: "t2",
            },
          ],
        },
        {
          name: "分组2",
          list: [
            {
              name: "t11",
            },
            {
              name: "t22",
            },
          ],
        },
      ],
      ColGroups_2: [
        {
          name: "分组10",
          list: [
            {
              name: "t10",
            },
            {
              name: "t20",
            },
          ],
        },
        {
          name: "分组20",
          list: [
            {
              name: "t110",
            },
            {
              name: "t220",
            },
          ],
        },
      ],
      //最终group测试数据
      G_group_lists: [
        {
          name: "分组1",
          id: "分组1ID",
          task_list: [
            {
              name: "g1t1_NAME",
              id: "g1t1_ID",
              isDone: false,
            },
            {
              name: "g1t2_NAME",
              id: "g1t2_ID",
              isDone: false,
            },
          ],
          /**
           * 快捷创建新的事项
           */
          isShow_qucikCreate_new_task: false,
          //临时存储 新事项
          quickCreate_task_tmp: {
            task_name: "",
          },
        },
        {
          name: "分组2",
          id: "分组2ID",
          task_list: [
            {
              name: "g2t1_NAME",
              id: "g2t1_ID",
              isDone: false,
            },
            {
              name: "g2t2_NAME",
              id: "g2t2_ID",
              isDone: false,
            },
          ],
          /**
           * 快捷创建新的事项
           */
          isShow_qucikCreate_new_task: false,
          //临时存储 新事项
          quickCreate_task_tmp: {
            task_name: "",
          },
        },
        {
          name: "分组1",
          id: "分组1ID",
          task_list: [
            {
              name: "g1t1_NAME",
              id: "g1t1_ID",
              isDone: false,
            },
            {
              name: "g1t2_NAME",
              id: "g1t2_ID",
              isDone: false,
            },
          ],
          /**
           * 快捷创建新的事项
           */
          isShow_qucikCreate_new_task: false,
          //临时存储 新事项
          quickCreate_task_tmp: {
            task_name: "",
          },
        },
        {
          name: "分组2",
          id: "分组2ID",
          task_list: [
            {
              name: "g2t1_NAME",
              id: "g2t1_ID",
              isDone: false,
            },
            {
              name: "g2t2_NAME",
              id: "g2t2_ID",
              isDone: false,
            },
          ],
          /**
           * 快捷创建新的事项
           */
          isShow_qucikCreate_new_task: false,
          //临时存储 新事项
          quickCreate_task_tmp: {
            task_name: "",
          },
        },        {
          name: "分组1",
          id: "分组1ID",
          task_list: [
            {
              name: "g1t1_NAME",
              id: "g1t1_ID",
              isDone: false,
            },
            {
              name: "g1t2_NAME",
              id: "g1t2_ID",
              isDone: false,
            },
          ],
          /**
           * 快捷创建新的事项
           */
          isShow_qucikCreate_new_task: false,
          //临时存储 新事项
          quickCreate_task_tmp: {
            task_name: "",
          },
        },
        {
          name: "分组2",
          id: "分组2ID",
          task_list: [
            {
              name: "g2t1_NAME",
              id: "g2t1_ID",
              isDone: false,
            },
            {
              name: "g2t2_NAME",
              id: "g2t2_ID",
              isDone: false,
            },
          ],
          /**
           * 快捷创建新的事项
           */
          isShow_qucikCreate_new_task: false,
          //临时存储 新事项
          quickCreate_task_tmp: {
            task_name: "",
          },
        },
      ],

      /*
    Table 数据
    */
    };
  },
  methods: {
    /**
     * Group 函数
     *  */
    //顶部select筛选
    G_TopSelectValChange() {},
    //获取分组展示的数据
    G_Get_GroupContentData() {
      this.Group_content_data = [
        {
          group_name: "Group1_Name",
          group_id: "Group1_ID",
          list: [
            {
              id: "t1l1",
              name: "名称1",
              state_val: 0,
              state_label: "",
              isDone: false,
              time: {
                start_date: { year: "2022", month: "2", day: "1" },
                end_date: { year: "2022", month: "11", day: "3" },
              },
              time_label: {
                start_date_label: "",
                end_date_label: "",
              },
            },
          ],
        },
        {
          group_name: "Group2_Name",
          group_id: "Group2_ID",
          list: [
            {
              id: "t2l2",
              name: "名称2",
              state_val: 0,
              state_label: "",
              isDone: false,
              time: {
                start_date: { year: "2022", month: "2", day: "1" },
                end_date: { year: "2022", month: "11", day: "3" },
              },
              time_label: {
                start_date_label: "",
                end_date_label: "",
              },
            },
            {
              id: "t3l3",
              name: "名称3",
              state_val: 0,
              state_label: "",
              isDone: false,
              time: {
                start_date: { year: "2022", month: "2", day: "1" },
                end_date: { year: "2022", month: "11", day: "3" },
              },
              time_label: {
                start_date_label: "",
                end_date_label: "",
              },
            },
          ],
        },
      ];
      this.G_Calc_GroupContentData_label();
    },
    //Group_content_data 计算得到label
    G_Calc_GroupContentData_label() {
      this.Group_content_data.forEach((group) => {
        //glist需要的title
        group.title = group.group_name;
        group.list.forEach((ele) => {
          let state_label = "";
          switch (ele.state_val) {
            case 0:
              state_label = "已完成";
              break;
            case 1:
              state_label = "进行中";
              break;
            case 2:
              state_label = "延期中";
              break;
            case 3:
              state_label = "延期完成";
              break;
          }
          ele.state_label = state_label;
          let start_date_label = "";
          let end_date_label = "";
          start_date_label =
            ele.time.start_date.year +
            "年" +
            ele.time.start_date.month +
            "月" +
            ele.time.start_date.day +
            "日";
          end_date_label =
            ele.time.end_date.year +
            "年" +
            ele.time.end_date.month +
            "月" +
            ele.time.end_date.day +
            "日";

          ele.time_label.start_date_label = start_date_label;
          ele.time_label.end_date_label = end_date_label;
        });
      });
      // console.log(this.Group_content_data);
    },
    //主滚动条设置
    setScrollBar() {
      this.$nextTick(function () {
        this.mpvv_G_content = document.querySelector(".mpvv_G_content");
        div.style.height = window.innerHeight - 10 + "px";
      });
    },

    /*
     *Table 函数
     *
     */
  },
  mounted() {
    /*Group */
    this.G_Get_GroupContentData();
    /**
     * 所有group共存的大盒子,滚动条设置
     *
     */
    this.$nextTick(function () {
      var div = document.querySelector(".mpvv_G_content");
      div.style.height = window.innerHeight + "px";
    });
    this.setScrollBar();
    // 浏览器缩放，更新ScrollBar位置
    window.addEventListener("resize", this.setScrollBar);

    /* Table */
  },
  beforeDestroy() {
    var div = document.querySelector(".mpvv_G_content");
  },
};
</script>
<style lang="less" scoped>
.mpv_viewlist_view {
  .mpvv_tabs {
    .mpvv_G_ {
      .mpvv_G_content {
        display: flex;
        flex-direction: row;
        width: 100%;
        height: 100%;
        overflow: scroll;
      }
    }
    .mpvv_T_ {
    }
  }
}
</style>

<template>
  <div class="mpvlgl_view">
    <draggable :list="listsData" group="gg" class="mpvg_big_drag">
      <transition-group
        class="mpvg_groups"
        style="min-height: 3em; display: flex; flex-direction: row"
      >
        <span
          :span="12"
          v-for="group in listsData"
          :key="group.id"
          class="mpvg_group"
        >
          <span class="mpvg_group_title">{{ group.name }}</span>
          <!-- 在该分组新建task -->
          <el-card
            v-if="!group.isShow_qucikCreate_new_task"
            @click.native="group.isShow_qucikCreate_new_task = true"
            >新建事项</el-card
          >
          <!-- 点击快捷新建，输入框 -->
          <el-card v-else class="mpvg_group_createNewTask">
            <el-input
              v-model="group.quickCreate_task_tmp.task_name"
              placeholder="请输入内容"
            ></el-input>
            <el-button
              type="primary"
              style="float: right; margin-top: 0.5em; margin-bottom: 0.5em"
              @click="CreateQuickTaskCreate(group)"
              >创建</el-button
            >
            <el-button
              type="default"
              style="float: right; margin: 0.5em 0.5em 0.5em"
              @click="group.isShow_qucikCreate_new_task = false"
              >取消</el-button
            >
          </el-card>
          <div class="mpvg_group_tasklist">
            <draggable
              class="task_item_drag"
              :list="group.task_list"
              group="task"
            >
              <transition-group style="min-height: 3em; display: block">
                <div
                  class="mpvg_task_item"
                  v-for="(task, task_i) in group.task_list"
                  :key="task_i"
                >
                  <el-card>
                    <el-radio
                      class="isDone_radio"
                      v-model="task.isDone"
                      :label="true"
                      @click.native.prevent="onTaskDoneRadioChange(task)"
                      ><span></span>
                    </el-radio>
                    {{ task.name }}
                  </el-card>
                </div>
              </transition-group>
            </draggable>
          </div>
        </span>
      </transition-group>
    </draggable>
    <el-card class="mpvg_quickCreate_group">
      <span
        v-if="!isShow_input_quickCreate_group"
        @click="isShow_input_quickCreate_group = true"
        >新建分组</span
      >
      <span v-else>
        <el-input
          placeholder="请输入分组名"
          @blur="isShow_input_quickCreate_group = false"
          v-model="quickCreate_group_name"
        ></el-input>
        <el-button
          @mousedown.native="CreateQucikGroup"
          style="float: right; margin-top: 2em"
          type="primary"
          >创建</el-button
        >
      </span>
    </el-card>
  </div>
</template>
<script>
// import glistitem from "./glistitem";
import draggable from "vuedraggable";

export default {
  name: "grouplist",
  props: {},
  components: {
    draggable,
  },
  data() {
    return {
      /**显示的数据源 */
      listsData: [
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
      ],

      /*快捷创建新的分组 */
      quickCreate_group_name: "",
      quickCreate_group: {
        name: "",
        id: "",
        task_list: [],
        /**
         * 快捷创建新的事项
         */
        isShow_qucikCreate_new_task: false,
        //临时存储 新事项
        quickCreate_task_tmp: {
          task_name: "",
        },
      },
      isShow_input_quickCreate_group: false,
    };
  },
  methods: {
    /*task check */
    onTaskDoneRadioChange(model) {
      model.isDone = !model.isDone;
    },
    /**
     *
     * 快捷创建新的事项
     *
     *
     */
    //确认创建新的事项
    CreateQuickTaskCreate(group) {
      //api
      //请求为这个group 新增一个事项
      let new_task_id = group.quickCreate_task_tmp + "_ID";
      group.task_list.push({
        name: group.quickCreate_task_tmp.task_name,
        id: new_task_id,
        isDone: false,
      });
      //关闭输入框
      group.isShow_qucikCreate_new_task = false;
    },

    /**
     * 快捷创建新分组
     */
    CreateQucikGroup() {
      //api
      //请求为这个项目新增一个空分组
      let new_group_id = this.quickCreate_group_name + "_ID";

      this.listsData.unshift({
        name: this.quickCreate_group_name,
        id: new_group_id,
        task_list: [],
        /**
         * 快捷创建新的事项
         */
        isShow_qucikCreate_new_task: false,
        //临时存储 新事项
        quickCreate_task_tmp: {
          task_name: "",
        },
      });

      //滚动条到最左
      var div = document.querySelector(".mpvv_G_content");
      div.scrollLeft = 0;
      //清空输入
      this.quickCreate_group_name = "";
      this.isShow_input_quickCreate_group = false;
    },

    //滚动条监听
    //主滚动条设置
    setScrollBar() {
      this.$nextTick(function () {
        var div = document.querySelector(".mpvv_G_content");

        div.style.height = window.innerHeight - 10 + "px";
      });
    },
  },
  mounted() {
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
  },
  beforeDestroy() {
    var div = document.querySelector(".mpvv_G_content");
    window.removeEventListener("resize", this.setScrollBar);
  },
};
</script>
<style lang="less" scoped>
.mpvlgl_view {
  display: flex;
  //   width: 100%;
  .mpvg_big_drag {
    .mpvg_groups {
      .mpvg_group {
        margin-left: 2em;
        border: solid black 2px;
        width: 20em;
        .mpvg_group_title {
          font-size: 1.2em;
          font-weight: bold;
        }
        .mpvg_group_createNewTask {
          width: 100%;
        }
        .mpvg_group_tasklist {
          display: flex;
          flex-direction: column;
          .task_item_drag {
            .mpvg_task_item {
              .isDone_radio {
                margin-right: 0px;

                //从默认圆改成方形勾
                ::v-deep .el-radio__inner {
                  border-radius: 2px;
                  border-width: 2px;
                  //   border-color: var(--color-primary) !important; //外框颜色
                }

                ::v-deep .el-radio__input.is-checked .el-radio__inner::after {
                  content: "";
                  width: 10px;
                  height: 5px;
                  border: 1px solid white;
                  border-top: transparent;
                  border-right: transparent;
                  text-align: center;
                  display: block;
                  position: absolute;
                  top: 0px;
                  left: 0px;
                  vertical-align: middle;
                  transform: rotate(-45deg);
                  border-radius: 0px;
                  background: none;
                }
              }
            }
          }
        }
      }
    }
  }
  .mpvg_quickCreate_group {
    width: 20em;
    height: 10em;
    margin-left: 2em;
    margin-right: 5em;
    border: solid black 2px;
  }
}
</style>

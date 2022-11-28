<template>
  <div class="viewList_view">
    <!-- 顶部工具栏 -->
    <div class="drop_down_box">
      <div class="left_box">
        <!-- 左边的下拉框，可快捷筛选，即为表格的筛选器赋值 -->
        <el-select
          @change="TopSelectValChange"
          v-model="select_allTasks_Option"
          placeholder="请选择"
        >
          <el-option
            v-for="item in select_allTasks_Options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </div>
      <div class="right_box">
        <div class="search_allTasks">
          <el-autocomplete
            class="inline-input"
            v-model="searchText"
            style="width: 300px; float: right"
            :fetch-suggestions="SearchTaskByKeyWord"
            placeholder="请输入事项关键词"
            @select="SelectTaskSearchSuggestion"
          ></el-autocomplete>
        </div>
        <!-- 表头设置 -->
        <el-card
          shadow="hover"
          style="display: flex; justify-content: center; align-items: center"
          body-style="padding:0;margin:0 .3em 0 .3em;"
          class="arrange_head"
        >
          <!-- poper弹框 -->
          <el-popover
            class="headset_pop"
            placement="top"
            width="160"
            v-model="ShowHeadSet"
            @after-leave="HeadSetAfterLeave"
          >
            <!-- 可拖拽内容 -->
            <div class="headset_list">
              <draggable v-model="HeadSetData" filter=".undraggable">
                <transition-group>
                  <div
                    v-for="element in HeadSetData"
                    :key="element.label"
                    :key-name="element.key"
                    :class="{
                      undraggable: element.key == 'name',
                    }"
                  >
                    <span>{{ element.label }}</span>

                    <el-radio
                      style="float: right"
                      v-model="element.isShow"
                      :label="true"
                      @click.native.prevent="
                        (event) => HeadSetClickRadio(event, element)
                      "
                      ><span> </span
                    ></el-radio>
                  </div>
                </transition-group>
              </draggable>
            </div>
            <!-- poper的底部按钮 -->
            <div style="" class="buttom_buttons">
              <el-button size="mini" type="text" @click="HeadSetPopCancel">
                取消
              </el-button>
              <el-button type="primary" size="mini" @click="HeadSetPopConfirm">
                确定
              </el-button>
            </div>
            <span slot="reference">表头设置</span>
          </el-popover>
        </el-card>
      </div>
    </div>

    <!-- 表格部分 -->
    <div class="contents_table_box">
      <el-table
        v-if="contents_data.length > 0"
        class="contents_table"
        :data="contents_data"
        border
        :row-class-name="GetRowClass"
        :show-header="true"
        @cell-dblclick="
          (row, column, cell, event) => cellDbClick(row, column, cell, event)
        "
        @cell-click="
          (row, column, cell, event) => cellClick(row, column, cell, event)
        "
        lazy
        :load="getTaskChildrenByID"
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
        row-key="id"
      >
        <!-- 列排序，每次表头顺序修改都重新渲染 -->
        <span
          v-for="(head_item, head_index) in head_data_common"
          :key="head_index"
        >
          <!-- <el-table-column width="1" /> -->
          <!-- fixed 名称 Col,name -->
          <el-table-column
            fixed
            :label="head_data_common_dict['name'].label"
            class-name="name_col"
            :width="head_data_common_dict['name'].width"
            v-if="
              head_item.key == 'name' && head_data_common_dict['name'].isShow
            "
            :key="head_data_common_dict['name'].prio"
            ref="name_col_ref"
          >
            <template slot-scope="props">
              <!-- isDone的勾选 -->
              <el-radio
                class="isDone_radio"
                v-model="props.row.isDone"
                :label="true"
                @click.native.prevent="onTaskDoneRadioChange(props.row)"
                ><span></span>
              </el-radio>

              <span>{{ props.row.name }}</span>
            </template>
          </el-table-column>

          <!-- 事项时间Col,task_time -->
          <el-table-column
            :label="head_data_common_dict['task_time'].label"
            class-name="task_time_col"
            :width="head_data_common_dict['task_time'].width"
            v-if="
              head_item.key == 'task_time' &&
              head_data_common_dict['task_time'].isShow
            "
            :key="head_data_common_dict['task_time'].prio"
          >
            <template slot-scope="props">
              <el-dialog
                :title="props.row.rowIndex + '行 事项的日期和时间选择器'"
                v-if="
                  props.row.rowIndex === cellSgRowIndex &&
                  'task_time_col' == cellSgColClass
                "
                :before-close="HandleCloseTaskTimeDialog"
                :visible.sync="TaskTimePickerBlur"
                :modal="false"
                custom-class="el_dialog"
              >
                <!-- 月日的选择器 -->

                <el-date-picker
                  class="TaskDatePicker"
                  v-model="props.row.task_time.date"
                  type="daterange"
                  align="right"
                  unlink-panels
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  @change="(val) => HandleTaskTimeDateChange(val, props.row)"
                  :picker-options="TaskTimeDatePickerOptions"
                >
                </el-date-picker>

                <span class="TaskTimePickers">
                  <!-- 时间的选择器,分为开始和结束2个 -->
                  <el-time-picker
                    v-model="props.row.task_time.time.start"
                    format="HH:mm"
                    placeholder="添加起始时间"
                    :picker-options="{}"
                    @change="
                      (val) => HandleTaskTimeStartTimeChange(val, props.row)
                    "
                    class="TaskTimePicker"
                  >
                  </el-time-picker>

                  <el-time-picker
                    v-model="props.row.task_time.time.end"
                    format="HH:mm"
                    placeholder="添加结束时间"
                    :picker-options="{
                      selectableRange: `${
                        props.row.task_time.time.start
                          ? props.row.task_time.time.start + ':00'
                          : '00:00:00'
                      }-23:59:00`,
                    }"
                    @change="
                      (val) => HandleTaskTimeEndTimeChange(val, props.row)
                    "
                    class="TaskTimePicker"
                  >
                  </el-time-picker>
                </span>
              </el-dialog>
              <span>
                <span>{{
                  FormatTaskTime_Date(props.row.task_time_label)
                }}</span>
                <br />
                <span>{{
                  FormatTaskTime_Time(props.row.task_time_label)
                }}</span>
              </span>
            </template>
          </el-table-column>

          <!-- 分组Col,group -->
          <el-table-column
            :label="head_data_common_dict['group'].label"
            class-name="group_col"
            :width="head_data_common_dict['group'].width"
            v-if="
              head_item.key == 'group' && head_data_common_dict['group'].isShow
            "
            :key="head_data_common_dict['group'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.group }}</span>
            </template>
          </el-table-column>

          <!-- 参与人Col,participator -->

          <el-table-column
            :label="head_data_common_dict['participator'].label"
            class-name="participator_col"
            :width="head_data_common_dict['participator'].width"
            v-if="
              head_item.key == 'participator' &&
              head_data_common_dict['participator'].isShow
            "
            :key="head_data_common_dict['participator'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.participator }}</span>
            </template>
          </el-table-column>

          <!-- 事项状态Col,state_label -->
          <el-table-column
            :label="head_data_common_dict['state_label'].label"
            class-name="state_label_col  click_col"
            :width="head_data_common_dict['state_label'].width"
            v-if="
              head_item.key == 'state_label' &&
              head_data_common_dict['state_label'].isShow
            "
            :key="head_data_common_dict['state_label'].prio"
            :filters="[
              { text: '已完成', value: '0' },
              { text: '进行中', value: '1' },
              { text: '延期中', value: '2' },
              { text: '延期完成', value: '3' },
            ]"
            :filter-method="StateFilterHandler"
            ref="state_col_ref"
          >
            <template slot-scope="props">
              <el-tag :type="StateTagName(props.row.state_val, props.row)">
                <span>{{ props.row["state_label"] }}</span>
              </el-tag>
            </template>
          </el-table-column>

          <!-- 项目Col,project -->
          <el-table-column
            :label="head_data_common_dict['project'].label"
            class-name="project_col"
            :width="head_data_common_dict['project'].width"
            v-if="
              head_item.key == 'project' &&
              head_data_common_dict['project'].isShow
            "
            :key="head_data_common_dict['project'].prio"
          >
            <template slot-scope="props">
              <!-- 下拉的项目选择器 -->
              <el-select
                @change="HandleProjectChange(props.row)"
                v-model="props.row.project"
                value-key="id"
                placeholder="选择项目"
                v-if="
                  props.row.rowIndex == cellSgRowIndex &&
                  'project_col' == cellSgColClass
                "
              >
                <el-option
                  v-for="project in MyProjects"
                  :key="project.id"
                  :label="project.name"
                  :value="project"
                  :disabled="DisbledProjectSelectOption(props.row)"
                >
                </el-option>
              </el-select>
              <span v-else class="textflow">{{
                props.row["project"].name
              }}</span>
            </template>
          </el-table-column>

          <!-- 总结Col,conclusion -->
          <el-table-column
            :label="head_data_common_dict['conclusion'].label"
            class-name="conclusion_col"
            :width="head_data_common_dict['conclusion'].width"
            v-if="
              head_item.key == 'conclusion' &&
              head_data_common_dict['conclusion'].isShow
            "
            :key="head_data_common_dict['conclusion'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.conclusion }}</span>
            </template>
          </el-table-column>

          <!-- 日程隐藏Col,hidden -->
          <el-table-column
            :label="head_data_common_dict['hidden'].label"
            class-name="hidden_col"
            :width="head_data_common_dict['hidden'].width"
            v-if="
              head_item.key == 'hidden' &&
              head_data_common_dict['hidden'].isShow
            "
            :key="head_data_common_dict['hidden'].prio"
          >
            <template slot-scope="props">
              <span>
                <el-switch v-model="props.row['hidden']"> </el-switch>
                <span>{{ props.row["hidden"] ? "显示" : "隐藏" }}</span>
              </span>
            </template>
          </el-table-column>

          <!-- 事项完成时间Col,taskDoneTime -->
          <el-table-column
            :label="head_data_common_dict['taskDoneTime'].label"
            class-name="taskDoneTime_col"
            :width="head_data_common_dict['taskDoneTime'].width"
            v-if="
              head_item.key == 'taskDoneTime' &&
              head_data_common_dict['taskDoneTime'].isShow
            "
            :key="head_data_common_dict['taskDoneTime'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.taskDoneTime }}</span>
            </template>
          </el-table-column>

          <!-- 我的完成时间Col,taskDoneMyTime -->
          <el-table-column
            :label="head_data_common_dict['taskDoneMyTime'].label"
            class-name="taskDoneMyTime_col"
            :width="head_data_common_dict['taskDoneMyTime'].width"
            v-if="
              head_item.key == 'taskDoneMyTime' &&
              head_data_common_dict['taskDoneMyTime'].isShow
            "
            :key="head_data_common_dict['taskDoneMyTime'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.taskDoneMyTime }}</span>
            </template>
          </el-table-column>

          <!-- 关注Col,star -->
          <el-table-column
            :label="head_data_common_dict['star'].label"
            class-name="star_col"
            :width="head_data_common_dict['star'].width"
            v-if="
              head_item.key == 'star' && head_data_common_dict['star'].isShow
            "
            :key="head_data_common_dict['star'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row["star"] }}</span>
              <star
                :isActive="props.row['star']"
                :star_ref="props.row.id + '_star'"
                :eventName="'StarEvent'"
                :valName="'SetViewListVal'"
                :otherStaff="{
                  line_index: contents_data.findIndex(
                    (line) => line.id == props.row.id
                  ),
                }"
                v-on:StarEvent="HandleStarEvent"
              ></star>
            </template>
          </el-table-column>

          <!-- 完成情况Col,taskAchievement -->
          <el-table-column
            :label="head_data_common_dict['taskAchievement'].label"
            class-name="taskAchievement_col"
            :width="head_data_common_dict['taskAchievement'].width"
            v-if="
              head_item.key == 'taskAchievement' &&
              head_data_common_dict['taskAchievement'].isShow
            "
            :key="head_data_common_dict['taskAchievement'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.taskAchievement }}</span>
            </template>
          </el-table-column>

          <!-- 创建人Col,creator -->
          <el-table-column
            :label="head_data_common_dict['creator'].label"
            class-name="creator_col"
            :width="head_data_common_dict['creator'].width"
            v-if="
              head_item.key == 'creator' &&
              head_data_common_dict['creator'].isShow
            "
            :key="head_data_common_dict['creator'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.creator }}</span>
            </template>
          </el-table-column>

          <!-- 更新时间Col,updateTime -->
          <el-table-column
            :label="head_data_common_dict['updateTime'].label"
            class-name="updateTime_col"
            :width="head_data_common_dict['updateTime'].width"
            v-if="
              head_item.key == 'updateTime' &&
              head_data_common_dict['updateTime'].isShow
            "
            :key="head_data_common_dict['updateTime'].prio"
          >
            <template slot-scope="props">
              <span>{{ props.row.updateTime }}</span>
            </template>
          </el-table-column>
        </span>
      </el-table>
    </div>
  </div>
</template>

<script>
import star from "../../../../../components/Star/index.vue";
import draggable from "vuedraggable";
export default {
  name: "tablelist",
  components: { star, draggable },
  data() {
    return {
      /**
       * 表格数据
       */
      contents_data: [],

      //表格真正使用的表头数据
      head_data_common: [
        //名称
        {
          key: "name",
          width: "200rem",
          isShow: true,
          label: "名称",
          prio: 0,
        },
        //事项时间
        {
          key: "task_time",
          width: "150rem",
          isShow: true,
          label: "事项时间",
          prio: 1,
        },
        //分组
        {
          key: "group",
          width: "150rem",
          isShow: true,
          label: "分组",
          prio: 2,
        },
        //参与人
        {
          key: "participator",
          width: "150rem",
          isShow: true,
          label: "参与人",
          prio: 3,
        },
        //事项状态
        {
          key: "state_label",
          width: "100rem",
          isShow: true,
          label: "事项状态",
          prio: 4,
        },
        //项目
        {
          key: "project",
          width: "200rem",
          isShow: true,
          label: "项目",
          prio: 5,
        },
        //总结
        {
          key: "conclusion",
          width: "300rem",
          isShow: true,
          label: "总结",
          prio: 6,
        },

        //日程隐藏
        {
          key: "hidden",
          width: "120rem",
          isShow: true,
          label: "日程隐藏",
          prio: 8,
        },
        //事项完成时间
        {
          key: "taskDoneTime",
          width: "150rem",
          isShow: true,
          label: "事项完成时间",
          prio: 9,
        },
        //我的完成时间
        {
          key: "taskDoneMyTime",
          width: "150rem",
          isShow: true,
          label: "我的完成时间",
          prio: 10,
        },
        //关注
        {
          key: "star",
          width: "80rem",
          isShow: true,
          label: "关注",
          prio: 11,
        },
        //完成情况
        {
          key: "taskAchievement",
          width: "150rem",
          isShow: true,
          label: "完成情况",
          prio: 12,
        },
        //创建人
        {
          key: "creator",
          width: "150rem",
          isShow: true,
          label: "创建人",
          prio: 13,
        },
        //更新时间
        {
          key: "updateTime",
          width: "150rem",
          isShow: true,
          label: "更新时间",
          prio: 14,
        },
      ],

      /**
       * 表头位置的的下拉框，快捷筛选
       */
      //下拉框元数据
      select_allTasks_Options: [
        {
          value: "all_task",
          label: "所有事项",
        },
        {
          value: "I_not_done",
          label: "我未完成",
        },
        {
          value: "I_am_doing",
          label: "我进行中",
        },
        {
          value: "I_am_delaying",
          label: "我延期中",
        },
        {
          value: "I_have_done",
          label: "我已完成",
        },
      ],
      //下拉框取得的数据
      select_allTasks_Option: "all_task",

      /**
       * 表头排序
       *
       */
      ShowHeadSet: false,
      HeadSetData: [
        //名称
        // {
        //   key: "name",
        //   width: "200rem",
        //   isShow: true,
        //   label: "名称",
        //   prio: 0,
        // },
        //事项时间
        {
          key: "task_time",
          width: "150rem",
          isShow: true,
          label: "事项时间",
          prio: 1,
        },
        //分组
        {
          key: "group",
          width: "150rem",
          isShow: true,
          label: "分组",
          prio: 2,
        },
        //参与人
        {
          key: "participator",
          width: "150rem",
          isShow: true,
          label: "参与人",
          prio: 3,
        },
        //事项状态
        {
          key: "state_label",
          width: "100rem",
          isShow: true,
          label: "事项状态",
          prio: 4,
        },
        //项目
        {
          key: "project",
          width: "200rem",
          isShow: true,
          label: "项目",
          prio: 5,
        },
        //总结
        {
          key: "conclusion",
          width: "300rem",
          isShow: true,
          label: "总结",
          prio: 6,
        },
        //日程隐藏
        {
          key: "hidden",
          width: "120rem",
          isShow: true,
          label: "日程隐藏",
          prio: 8,
        },
        //事项完成时间
        {
          key: "taskDoneTime",
          width: "150rem",
          isShow: true,
          label: "事项完成时间",
          prio: 9,
        },
        //我的完成时间
        {
          key: "taskDoneMyTime",
          width: "150rem",
          isShow: true,
          label: "我的完成时间",
          prio: 10,
        },
        //关注
        {
          key: "star",
          width: "80rem",
          isShow: true,
          label: "关注",
          prio: 11,
        },
        //完成情况
        {
          key: "taskAchievement",
          width: "150rem",
          isShow: true,
          label: "完成情况",
          prio: 12,
        },
        //创建人
        {
          key: "creator",
          width: "150rem",
          isShow: true,
          label: "创建人",
          prio: 13,
        },
        //更新时间
        {
          key: "updateTime",
          width: "150rem",
          isShow: true,
          label: "更新时间",
          prio: 14,
        },
      ],
      /**
       * 搜索框
       */
      searchText: "",
      /**
       * 单元格
       */
      //双击单元格的坐标信息。行下标,列class名称
      cellDbRowIndex: null,
      cellDbColClass: "",
      //单击单元格的坐标信息。行下标,列class名称
      cellSgRowIndex: null,
      cellSgColClass: "",

      /**
       * filter筛选
       */
      //我缓存的filter配置数据
      MyFilterConfig: {
        state: [],
      },
      //表头cell是否需要点击，来使得filter元素出现并获取到
      HeadCellNeddClick: true,
      //全部的filter元素的按钮，等待按下confirm按钮
      AllFilterCellsButtons: [],
      //表格中拥有filter的列ref
      tableFilterColRefs: {
        state: null,
      },
      //名称列
      NameColRef: null,
      /**
       * 事项时间
       **/
      //日期、时间选择器dialog的显示
      TaskTimePickerBlur: false,
      //日期选择器的快捷时间
      TaskTimeDatePickerOptions: {
        shortcuts: [
          {
            text: "最近一周",
            onClick(picker) {
              const start = new Date();
              const end = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近一个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 30);
              picker.$emit("pick", [start, end]);
            },
          },
          {
            text: "最近三个月",
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 90);
              picker.$emit("pick", [start, end]);
            },
          },
        ],
      },

      /***
       * 项目
       */
      //我的所有项目，缓存;
      MyProjects: -1,
      //项目下拉选择器的显示
      ShowProjectSelect: false,
    };
  },
  mounted() {
    this.$nextTick(() => {
      //contents data 初始化
      this.SetContentsData();
      //用户的所有项目简洁数据
      this.GetMyProjects();
      //点击表头，使得filter元素出现
      // this.ClickTableHead();
    });
  },
  directives: {
    focus: {
      // 指令的定义
      inserted: function (el) {
        el.focus();
      },
    },
  },
  destroyed() {
    //监听事件解绑
  },
  methods: {
    TestClick() {
      console.log(this.HeadSetData);
    },
    /**
     * 单元格
     */
    //表格的单元格,双击
    cellDbClick(row, column, cell, event) {
      // console.group("cellDbClick");
      //点击cell，记录下行列信息

      this.cellDbRowIndex = row.rowIndex;
      this.cellDbColClass = column.className;

      // console.log("cellDbRowIndex", this.cellDbRowIndex);
      // console.groupEnd("cellDbClick");
    },
    //表格的单元格，单击事件
    cellClick(row, column, cell, event) {
      // console.group("cellClick");
      // console.log(this.cellSgRowIndex);
      // console.log(this.cellSgColClass);
      // console.log("----");
      this.cellSgRowIndex = row.rowIndex;
      this.cellSgColClass = column.className;
      //事项时间的列
      if (this.cellSgColClass == "task_time_col") {
        this.TaskTimePickerBlur = true;
      } else {
        this.TaskTimePickerBlur = false;
      }
      // console.log(this.cellSgRowIndex);
      // console.log(this.cellSgColClass);

      // console.groupEnd("cellClick");
    },

    //根据每行的childrenNum，返回对应的class
    GetRowClass(props) {
      //为每行更新rowIndex
      props.row.rowIndex = props.rowIndex;
      switch (props.row.childrenNum) {
        case -1:
          return "noBorder childRow";
        default:
          if (props.row.showChildren) {
            return "noBorder parentRow";
          } else {
            return "";
          }
      }
    },

    /**
     *
     * 顶部工具栏
     */

    //顶部左边的下拉框,值改变时
    //根据下拉框的值，给my筛选器重新赋值
    async TopSelectValChange(val) {
      /**
       * 事项状态的filter取值范围
       *    { text: '已完成', value: '0' },
            { text: '进行中', value: '1' },
            { text: '延期中', value: '2' },
            { text: '延期完成', value: '3' },
            
       */
      await this.MyFilterInit().then(async (res) => {
        if (val == "all_task") {
          //所有事项
          this.MyFilterConfig.state = ["0", "1", "2", "3"];
        } else if (val == "I_not_done") {
          //我未完成
          this.MyFilterConfig.state = ["1", "2"];
        } else if (val == "I_am_doing") {
          //我进行中
          this.MyFilterConfig.state = ["1"];
        } else if (val == "I_am_delaying") {
          //我延期中
          this.MyFilterConfig.state = ["2"];
        } else if (val == "I_have_done") {
          //已经完成的
          this.MyFilterConfig.state = ["0", "3"];
        }
        this.UseMyFilterConfig();
        this.ConfirmFilterCell();

        // await this.UseMyFilterConfig().then((res) => {
        //   this.ConfirmFilterCell();
        // });
      });
    },

    /**
     * 表头设置
     */
    //表头设置的click,弃用
    ClickHeadSet() {
      this.ShowHeadSet = !this.ShowHeadSet;
      // console.log("ClickHeadSet");
    },

    //表头设置pop的关闭事件,包括了所有的情况
    HeadSetAfterLeave() {
      // console.log("HeadSetAfterLeave");

      //HeadSet检查是否相较于head_data_common变化了
      let changed = false;
      let keys = ["key", "isShow"];
      for (let i = 0; i < this.HeadSetData.length; i++) {
        for (let key in keys) {
          // console.log(
          //   this.head_data_common[i + 1][keys[key]],
          //   this.HeadSetData[i][keys[key]]
          // );
          if (
            this.head_data_common[i + 1][keys[key]] !=
            this.HeadSetData[i][keys[key]]
          ) {
            changed = true;
            break;
          }
        }
        if (changed) {
          break;
        }
      }
      if (changed) {
        console.log("changed");
        this.HeadSetData = this.HeadSetData.map((ele, index) => {
          // console.log(ele, "#", index + 1);
          // if (ele.prio == index+1) {
          //   console.log(ele, "stable");
          // } else {
          //   console.log(ele, "unstable,", index);
          // }
          return {
            key: ele.key,
            width: ele.width,
            isShow: ele.isShow,
            label: ele.label,
            prio: Math.random(),
          };
        });

        // HeadSet数据赋值给head_data_common
        this.head_data_common.splice(1, this.HeadSetData.length);
        this.HeadSetData.forEach((ele) => {
          let newEle = { ...ele };
          this.head_data_common.push(newEle);
        });
      }

      // this.$forceUpdate();
      console.log("this.head_data_common", this.head_data_common);
    },
    //表头设置pop的按钮取消
    HeadSetPopCancel() {
      this.ShowHeadSet = false;
    },
    //表头设置pop的按钮确认
    HeadSetPopConfirm() {
      this.ShowHeadSet = false;
    },

    //表头元素isShow的勾选
    HeadSetClickRadio(event, element) {
      element.isShow = !element.isShow;
      // console.log("this.head_data_common", this.head_data_common);
      // this.$forceUpdate();
      // console.log(element.isShow);
    },
    /**
     * filter筛选
     */

    async MyFilterInit() {
      //是否需要重新初始化，
      //点击表头cell，获取表头filter元素，获取拥有filter元素的列
      if (this.HeadCellNeddClick) {
        await this.ClickTableHead().then((click_res) => {
          return new Promise((resolve, reject) => {
            setTimeout(async () => {
              //获取所有的filter cells按钮
              await this.GetAllFilterCell().then((res) => {
                //获取拥有filter的列
                this.GetAllFilterColRefs();
                this.HeadCellNeddClick = false;
                // console.log(
                //   "AllFilterCellsButtons",
                //   this.AllFilterCellsButtons
                // );
                this.ClickTableHead();

                resolve(true);
              });
            }, 20);
          });
        });
      }
    },

    //使用我缓存的filter config数据，赋值给对应的filter cell元素
    //后面需要获取filter cell元素，并且按下它们的confirm按钮即可筛选
    //达到的效果：不用点击表头筛选器也能筛选
    //场景：顶部工具栏，左侧下拉框可快捷筛选，单点下拉框后即可进行筛选
    async UseMyFilterConfig() {
      return new Promise((resolve, reject) => {
        //赋值state
        // console.group("UseMyFilterConfig");
        // console.log(
        //   "this.tableFilterColRefs.state",
        //   this.tableFilterColRefs.state
        // );

        // console.log("this.MyFilterConfig.state", this.MyFilterConfig.state);
        this.tableFilterColRefs.state.columnConfig.filteredValue =
          this.MyFilterConfig.state;
        resolve(true);
        // console.groupEnd("UseMyFilterConfig");
      });
    },

    //点击表头单元格，使得filter元素出现
    async ClickTableHead() {
      return new Promise((resolve, reject) => {
        //点击表头的cell，对应的filter才会出现
        let headersHTML = document.getElementsByClassName(
          "el-table__header-wrapper"
        );
        let headersArr = Array.from(headersHTML);
        headersArr.forEach((header) => {
          let cells = Array.from(header.getElementsByClassName("click_col"));
          // console.log("cells", cells);
          cells.forEach((cell) => {
            cell.click();
          });
        });
        resolve(true);
      });
    },
    //获取拥有filter的列
    GetAllFilterColRefs() {
      //事项状态列
      let state_;
      state_ = this.$refs.state_col_ref;
      if (state_ instanceof Array) {
        state_ = state_[0];
      }

      this.tableFilterColRefs = {
        state: state_,
      };
      // console.log("this.tableFilterColRefs ", this.tableFilterColRefs);
    },

    //获取所有filter cell元素，只是为了按click启动筛选
    async GetAllFilterCell() {
      return new Promise((resolve, reject) => {
        let filters_cell = document.getElementsByClassName("el-table-filter");
        let cellButtons = [];
        for (let c of filters_cell) {
          let bottom = Array.from(
            c.getElementsByClassName("el-table-filter__bottom")
          );
          let twoButtons = {
            confirm: "",
            reset: "",
          };
          // 获取到俩按钮，一个confirm，一个reset
          let buttons = Array.from(bottom[0].getElementsByTagName("button"));

          if (
            buttons[0].innerText.indexOf("筛选") != -1 ||
            buttons[0].innerText.indexOf("Confirm") != -1
          ) {
            twoButtons = {
              confirm: buttons[0],
              reset: buttons[1],
            };
          } else {
            twoButtons = {
              confirm: buttons[1],
              reset: buttons[0],
            };
          }

          cellButtons.push(twoButtons);
          // console.log("twoButtons", twoButtons);
        }
        this.AllFilterCellsButtons = cellButtons;
        resolve(this.AllFilterCellsButtons.length);
        console.log("AllFilterCellsButtons", this.AllFilterCellsButtons);
        // console.log(filters_cell);
      });
    },
    //按下所有filter cell元素的确定按钮
    ConfirmFilterCell() {
      // console.log("AllFilterCellsButtons", this.AllFilterCellsButtons);
      this.AllFilterCellsButtons.forEach((ele) => {
        ele.confirm.click();

        console.log(ele.confirm);
      });
    },

    //事项状态 state的真正filter
    //state按照这个模式来过滤
    StateFilterHandler(value, row, column) {
      /**
       *    { text: '已完成', value: '0' },
            { text: '进行中', value: '1' },
            { text: '延期中', value: '2' },
            { text: '延期完成', value: '3' },
            
       */

      return row.state_val == value;
    },

    /**
     *
     * 数据初始化与获取
     */
    //表格数据contents data初始化
    SetContentsData() {
      this.contents_data = [
        {
          id: "t1l1",
          name: "名称1",
          task_time: {
            date: "",
            time: {
              start: "",
              end: "",
            },
          },
          task_time_label: {
            start_date: { year: "", month: "", day: "" },
            end_date: { year: "", month: "", day: "" },
            start_time: { hour: "", minute: "" },
            end_time: { hour: "", minute: "" },
          },
          group: "分组1",
          project: {
            name: "",
            id: "",
          },
          conclusion: "总结1",
          state_val: 0,
          state_label: "",
          isDone: false,
          childrenNum: 1,
          showChildren: false,
          hidden: true,
          star: true,
          children: [
            {
              id: "t11l2",
              name: "名称12",
              task_time: {
                date: "",
                time: {
                  start: "",
                  end: "",
                },
              },
              task_time_label: {
                start_date: { year: "", month: "", day: "" },
                end_date: { year: "", month: "", day: "" },
                start_time: { hour: "", minute: "" },
                end_time: { hour: "", minute: "" },
              },
              group: "分组1",
              project: {
                name: "",
                id: "",
              },
              conclusion: "总结1",
              state_val: 1,
              state_label: "",
              isDone: false,
              childrenNum: 3,
              showChildren: false,
              hidden: false,
              star: false,
              children: null,
            },
          ],
        },
        {
          id: "t1l2",
          name: "名称2",
          task_time: {
            date: "",
            time: {
              start: "",
              end: "",
            },
          },
          task_time_label: {
            start_date: { year: "", month: "", day: "" },
            end_date: { year: "", month: "", day: "" },
            start_time: { hour: "", minute: "" },
            end_time: { hour: "", minute: "" },
          },
          group: "分组1",
          project: {
            name: "",
            id: "",
          },
          conclusion: "总结1",
          state_val: 1,
          state_label: "",
          isDone: false,
          childrenNum: 3,
          showChildren: false,
          hidden: false,
          star: false,
          children: null,
        },
        {
          id: "t1l3",
          name: "名称3",
          task_time: {
            date: "",
            time: {
              start: "",
              end: "",
            },
          },
          task_time_label: {
            start_date: { year: "", month: "", day: "" },
            end_date: { year: "", month: "", day: "" },
            start_time: { hour: "", minute: "" },
            end_time: { hour: "", minute: "" },
          },
          group: "分组1",
          project: {
            name: "",
            id: "",
          },
          conclusion: "总结1",
          state_val: 2,
          state_label: "",
          isDone: false,
          childrenNum: 1,
          showChildren: false,
          hidden: false,
          star: false,
          children: null,
        },
        {
          id: "t1l4",
          name: "名称4",
          task_time: {
            date: "",
            time: {
              start: "",
              end: "",
            },
          },
          task_time_label: {
            start_date: { year: "", month: "", day: "" },
            end_date: { year: "", month: "", day: "" },
            start_time: { hour: "", minute: "" },
            end_time: { hour: "", minute: "" },
          },
          group: "分组1",
          project: {
            name: "",
            id: "",
          },
          conclusion: "总结1",
          state_val: 3,
          state_label: "",
          isDone: false,
          childrenNum: 4,
          showChildren: false,
          hidden: false,
          star: false,
          children: null,
        },
      ];
    },

    // 请求某id事项的children事项，
    getTaskChildrenByID(tree, treeNode, resolve) {
      //向后端请求 该id事项的的children
      console.log("tree", tree);
      console.log("treeNode", treeNode);
    },

    /**
     * 事项名字
     */
    onTaskDoneRadioChange(model) {
      model.isDone = !model.isDone;
    },
    /***
     *
     * 事项时间
     */
    //dialog关闭前事件
    HandleCloseTaskTimeDialog(done) {
      done();
      this.TaskTimePickerBlur = false;
      this.cellSgColClass = "";
      // console.log(this.TaskTimePickerBlur);
    },
    //date picker;
    //值改变时,更新task_time_label
    HandleTaskTimeDateChange(val, model) {
      model.task_time_label.start_date = {
        year: val[0].getFullYear(),
        month: val[0].getMonth() + 1,
        day: val[0].getDate(),
      };
      model.task_time_label.end_date = {
        year: val[0].getFullYear(),
        month: val[1].getMonth() + 1,
        day: val[1].getDate(),
      };
    },
    //time picker,start time
    //值改变时,更新task_time_label
    HandleTaskTimeStartTimeChange(val, model) {
      model.task_time_label.start_time = {
        hour: val.getHours(),
        minute: val.getMinutes(),
      };
    },
    //time picker,end time
    //值改变时,更新task_time_label
    HandleTaskTimeEndTimeChange(val, model) {
      model.task_time_label.end_time = {
        hour: val.getHours(),
        minute: val.getMinutes(),
      };
      // console.log(model.task_time_label.end_time);
    },
    //事项时间，返回task_time的 月日 的打印字符串
    FormatTaskTime_Date(model) {
      const m = model;
      let str = "";
      str = `开始:${m.start_date.year}年${m.start_date.month}月${m.start_date.day}日`;
      str +=
        "\n" +
        `结束:${m.end_date.year}年${m.end_date.month}月${m.end_date.day}日`;
      return str;
    },

    //事项时间，返回task_time的 时分 的打印字符串
    FormatTaskTime_Time(model) {
      const m = model;
      let str = "";
      str += `${m.start_time.hour}:${m.start_time.minute}`;

      str += "\n~" + `${m.end_time.hour}:${m.end_time.minute}`;

      return str;
    },

    /**
     * 事项状态
     */
    //根据事项的状态值state_val，返回对应的type类型
    //同时为每一行的state_label赋值
    StateTagName(state_val, model) {
      //tag的类型
      let tag_name;
      switch (state_val) {
        case 0:
          tag_name = "success";
          break;
        case 1:
          tag_name = "";
          break;
        case 2:
          tag_name = "danger";
          break;
        case 3:
          tag_name = "warning";
          break;
      }
      //state_label赋值
      let label;
      switch (model.state_val) {
        case 0:
          label = "已完成";
          break;
        case 1:
          label = "进行中";
          break;
        case 2:
          label = "延期中";
          break;
        case 3:
          label = "延期完成";
          break;
      }
      model.state_label = label;
      return tag_name;
    },

    /***
     * 项目
     */
    //项目,返回用户的所有项目简洁数据
    GetMyProjects() {
      //没有缓存，请求获取
      if (this.MyProjects == -1) {
        let projects = [];
        [1, 2, 3].forEach((i) => {
          projects = [
            {
              name: "项目" + i.toString() + ".1",
              id: "项目" + i.toString() + ".1-ID",
            },
            {
              name: "项目" + i.toString() + ".2",
              id: "项目" + i.toString() + ".2-ID",
            },
            {
              name: "项目" + i.toString() + ".3",
              id: "项目" + i.toString() + ".3-ID",
            },
          ];
        });

        this.MyProjects = projects;
      }

      return this.MyProjects;
    },
    //项目，project;下拉选择器 值变化时
    HandleProjectChange(prop) {
      // console.log(prop);
      // //回显后选择强制刷新
      this.$forceUpdate();
    },
    //项目，下拉选择器，判断能否取这个值
    DisbledProjectSelectOption() {},

    /***
     * 关注
     */
    //组件star 返回值事件
    HandleStarEvent(val) {
      //根据传回来的line index，设置对应的行的star值
      this.contents_data[val.line_index].star = val.isActive;
    },
    /**
     * 顶部搜索事项
     */
    //api
    //根据关键词，搜索匹配的事项
    SearchTaskByKeyWord(queryString, cb) {
      let results = this.contents_data.map((ele) => {
        return {
          value: ele.name,
          id: ele.id,
        };
      });
      cb(results);
    },
    //点击搜索返回的建议
    SelectTaskSearchSuggestion(item) {
      console.log("搜索框点击task,",item);
    },
  },
  computed: {
    //方便直接获取head_data_common的值
    head_data_common_dict: {
      // getter
      get: function () {
        let dict = {};
        this.head_data_common.forEach((ele) => {
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
.viewList_view {
  height: 100%;
  //下拉框
  .drop_down_box {
    width: 100%;

    margin-bottom: 1em;
    display: flex;
    flex-direction: row;
    font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue",
      Helvetica, Arial, "Lucida Grande", sans-serif;
    font-size: 0.9rem;
    // color: var(--head--grey);

    .left_box {
      //所有事项
      .filt_allTasks {
        // flex:30
      }
    }
    .right_box {
      margin-left: auto;
      display: flex;
      flex-direction: row;
      align-content: center;
      margin-right: 3em;
      font-size: 1.2em;
      //搜索
      .search_allTasks {
        // flex: 1;
        margin-right: 0.9em;
      }

      //表头设置
      .arrange_head {
        // flex: 1;
        .headset_pop {
          .buttom_buttons {
            text-align: right;
            margin: 0;
          }
        }
      }
    }
  }

  //内容表格
  .contents_table_box {
    .contents_table {
      width: 100%;

      /deep/.el-table__body-wrapper {
        /*解决固定列遮盖底部滚定条导致滚动条无法滚动的问题*/
        // z-index: 1 !important;
      }

      //事项时间col,另外引入全局样式，否则el-dialog不生效
      .task_time_col .el_dialog {
        width: 35%;
      }

      // 文字溢出成省略号
      //textflow需要加到文本所在的元素上
      /deep/ .textflow {
        width: 100%; /*一定要设置宽度，或者元素内含的百分比*/
        overflow: hidden; /*溢出的部分隐藏*/
        white-space: nowrap; /*文本不换行*/
        text-overflow: ellipsis; /*ellipsis:文本溢出显示省略号（...）；clip：不显示省略标记（...），而是简单的裁切*/
        .InputBig {
          width: 80%;
        }
        .InputSmall {
          width: 70%;
        }
      }

      .name_col {
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
  //透明
  .transparent {
    opacity: 0;
  }
}
</style>




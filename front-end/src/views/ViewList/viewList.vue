/**
视图的列表页面
@author yy
@editor yy
@lastUpdata 10.22
*/
<template>
  <div class="viewList_view">
    <!-- 下拉框 -->
    <div class="drop_down_box">
      <div class="left_box">
        <div class="filt_allTasks">所有事项</div>
      </div>
      <div class="right_box">
        <div class="search_allTasks">搜索</div>
        <div class="arrange_head">表头设置</div>
      </div>
    </div>
    <!-- 表头
    是一个只有一行的表格
     -->
    <div class="table_head_box">
      <el-table
        ref="sync_scroller_head"
        class="table_head"
        stripe
        :data="head_data_col"
        row-class-name="head_col"
        :show-header="false"
        border
      >
        <!-- 固定的名称name列 -->
        <el-table-column
          fixed
          prop="name"
          :width="head_data_common_dict['name'].width"
        >
        </el-table-column>
        <span
          v-for="(head_col, index) in head_data_common"
          :key="index"
          class="head_col"
        >
          <el-table-column
            v-if="head_col.key != 'name' && head_col.isShow == 'true'"
            :prop="head_col.key"
            :width="head_col.width"
          >
          </el-table-column>
        </span>
      </el-table>
    </div>
    <!-- 内容table，分块，每块有多line -->
    <div class="contents">
      <div
        class="content"
        v-for="(content_data, index) in contents_data"
        :key="index"
      >
        <!-- 该块的时间 -->
        <div class="content_time">
          <span class="time_text">{{ content_data.time }}</span>
        </div>
        <!-- 内容table -->
        <el-table
          border
          ref="sync_scroller_con"
          :class="{
            content_table: true,
          }"
          :data="content_data.data"
          :row-class-name="GetRowClass"
          :show-header="false"
        >
          <!-- fixed 名称 Col,name -->
          <span :set="(field1 = 'name')">
            <el-table-column
              fixed
              :class-name="field1 + '_col'"
              :width="head_data_common_dict[field1].width"
              v-if="head_data_common_dict[field1].isShow == 'true'"
            >
              <template slot-scope="props">
                <span class="textflow">
                  <!-- 展开子事项列表的icon -->
                  <span
                    :class="{
                      foldChildren_icon: true,
                      transparent: props.row.childrenNum <= 0,
                    }"
                  >
                    <i
                      :class="{
                        'el-icon-caret-right': !props.row.showChildren,
                        'el-icon-caret-bottom': props.row.showChildren,
                      }"
                      @click="
                        ClickfoldChildren_icon(props.row, content_data.id)
                      "
                    ></i>
                  </span>
                  <!-- 单选框 -->
                  <el-radio
                    class="isDone_radio"
                    v-model="props.row.isDone"
                    :label="true"
                    @click.native.prevent="
                      onTaskDoneRadioChange(props.row, content_data.id)
                    "
                    ><span></span>
                  </el-radio>
                  <span>{{ props.row[field1] }}</span>
                </span>
              </template>
            </el-table-column>
          </span>

          <!-- 事项时间Col,task_time -->
          <span :set="(field2 = 'task_time')">
            <el-table-column
              :width="head_data_common_dict[field2].width"
              v-if="head_data_common_dict[field2].isShow == 'true'"
              :class-name="field2 + '_col'"
            >
              <template slot-scope="props">
                <span class="textflow">{{ props.row[field2] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 分组Col,group -->
          <span :set="(field21 = 'group')">
            <el-table-column
              :width="head_data_common_dict[field21].width"
              v-if="head_data_common_dict[field21].isShow == 'true'"
              :class-name="field21 + '_col'"
            >
              <template slot-scope="props">
                <span class="textflow">{{ props.row[field21] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 参与人Col,participator -->
          <span :set="(field22 = 'participator')">
            <el-table-column
              :width="head_data_common_dict[field22].width"
              v-if="head_data_common_dict[field22].isShow == 'true'"
              :class-name="field22 + '_col'"
            >
              <template slot-scope="props">
                <span class="textflow">{{ props.row[field22] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 事项状态Col,state_label -->
          <span :set="(field3 = 'state_label')">
            <el-table-column
              :width="head_data_common_dict[field3].width"
              :class-name="field3 + '_col'"
              v-if="head_data_common_dict[field3].isShow == 'true'"
            >
              <template slot-scope="props">
                <el-tag :type="StateTagName(props.row.state_val)">
                  <span>{{ props.row[field3] }}</span>
                </el-tag>
              </template>
            </el-table-column>
          </span>

          <!-- 项目Col,project -->
          <span :set="(field4 = 'project')">
            <el-table-column
              :width="head_data_common_dict[field4].width"
              :class-name="field4 + '_col'"
              v-if="head_data_common_dict[field4].isShow == 'true'"
            >
              <template slot-scope="props">
                <span class="textflow">{{ props.row[field4] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 总结Col,conclusion -->
          <span :set="(field5 = 'conclusion')">
            <el-table-column
              :width="head_data_common_dict[field5].width"
              :class-name="field5 + '_col'"
              v-if="head_data_common_dict[field5].isShow == 'true'"
            >
              <template slot-scope="props">
                <span class="textflow">{{ props.row[field5] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 团队Col,team -->
          <span :set="(field6 = 'team')">
            <el-table-column
              :width="head_data_common_dict[field6].width"
              :class-name="field6 + '_col'"
              v-if="head_data_common_dict[field6].isShow == 'true'"
            >
              <template slot-scope="props">
                <span class="textflow">{{ props.row[field6] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 日程隐藏Col,hidden -->
          <span :set="(field61 = 'hidden')">
            <el-table-column
              :width="head_data_common_dict[field61].width"
              :class-name="field61 + '_col'"
              v-if="head_data_common_dict[field61].isShow == 'true'"
            >
              <template slot-scope="props">
                <span>
                  <el-switch v-model="props.row[field61]"> </el-switch>
                  <span>{{ props.row[field61] ? "显示" : "隐藏" }}</span>
                </span>
              </template>
            </el-table-column>
          </span>

          <!-- 事项完成时间Col,taskDoneTime -->
          <span :set="(field62 = 'taskDoneTime')">
            <el-table-column
              :width="head_data_common_dict[field62].width"
              :class-name="field62 + '_col'"
              v-if="head_data_common_dict[field62].isShow == 'true'"
            >
              <template slot-scope="props">
                <span>{{ props.row[field62] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 我的完成时间Col,taskDoneMyTime -->
          <span :set="(field63 = 'taskDoneMyTime')">
            <el-table-column
              :width="head_data_common_dict[field63].width"
              :class-name="field63 + '_col'"
              v-if="head_data_common_dict[field63].isShow == 'true'"
            >
              <template slot-scope="props">
                <span>{{ props.row[field63] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 关注Col,star -->
          <span :set="(field64 = 'star')">
            <el-table-column
              :width="head_data_common_dict[field64].width"
              :class-name="field64 + '_col'"
              v-if="head_data_common_dict[field64].isShow == 'true'"
            >
              <template slot-scope="props">
                <span>{{ props.row[field64] }}</span>
                <star
                  :isActive="props.row[field64]"
                  :star_ref="props.row.id + '_star'"
                  :eventName="'StarEvent'"
                  :valName="'SetViewListVal'"
                  :otherStaff="{
                    content_index: index,
                    line_index: contents_data[index].data.findIndex(
                      (line) => line.id == props.row.id
                    ),
                  }"
                  v-on:StarEvent="HandleStarEvent"
                ></star>
              </template>
            </el-table-column>
          </span>

          <!-- 完成情况Col,taskAchievement -->
          <span :set="(field65 = 'taskAchievement')">
            <el-table-column
              :width="head_data_common_dict[field65].width"
              :class-name="field65 + '_col'"
              v-if="head_data_common_dict[field65].isShow == 'true'"
            >
              <template slot-scope="props">
                <span>{{ props.row[field65] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 创建人Col,creator -->
          <span :set="(field66 = 'creator')">
            <el-table-column
              :width="head_data_common_dict[field66].width"
              :class-name="field66 + '_col'"
              v-if="head_data_common_dict[field66].isShow == 'true'"
            >
              <template slot-scope="props">
                <span class="textflow">{{ props.row[field66] }}</span>
              </template>
            </el-table-column>
          </span>

          <!-- 更新时间Col,updateTime -->
          <span :set="(field67 = 'updateTime')">
            <el-table-column
              :width="head_data_common_dict[field67].width"
              :class-name="field67 + '_col'"
              v-if="head_data_common_dict[field67].isShow == 'true'"
            >
              <template slot-scope="props">
                <span>{{ props.row[field67] }}</span>
              </template>
            </el-table-column>
          </span>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import star from "../../components/star.vue";
export default {
  name: "ViewList",
  components: { star },
  data() {
    return {
      TestLines: [],
      //同步滚动条,内容table
      SyncScroll_contents: [],
      //同步滚动条,表头table
      SyncScroll_head: "",
      //所有表格通用的for遍历head
      head_data_common: [
        //名称
        {
          key: "name",
          width: "200rem",
          isShow: "true",
        },
        //事项时间
        {
          key: "task_time",
          width: "150rem",
          isShow: "true",
        },
        //分组
        {
          key: "group",
          width: "150rem",
          isShow: "true",
        },
        //参与人
        {
          key: "participator",
          width: "150rem",
          isShow: "true",
        },
        //事项状态
        {
          key: "state_label",
          width: "100rem",
          isShow: "true",
        },
        //项目
        {
          key: "project",
          width: "200rem",
          isShow: "true",
        },
        //总结
        {
          key: "conclusion",
          width: "300rem",
          isShow: "true",
        },
        //团队
        {
          key: "team",
          width: "300rem",
          isShow: "true",
        },
        //日程隐藏
        {
          key: "hidden",
          width: "120rem",
          isShow: "true",
        },
        //事项完成时间
        {
          key: "taskDoneTime",
          width: "150rem",
          isShow: "true",
        },
        //我的完成时间
        {
          key: "taskDoneMyTime",
          width: "150rem",
          isShow: "true",
        },
        //关注
        {
          key: "star",
          width: "80rem",
          isShow: "true",
        },
        //完成情况
        {
          key: "taskAchievement",
          width: "150rem",
          isShow: "true",
        },
        //创建人
        {
          key: "creator",
          width: "150rem",
          isShow: "true",
        },
        //更新时间
        {
          key: "updateTime",
          width: "150rem",
          isShow: "true",
        },
      ],

      //表头使用的label
      head_data_col: [
        {
          name: "名称",
          task_time: "事项时间",
          group: "分组",
          project: "项目",
          conclusion: "总结",
          team: "团队",
          state_label: "事项状态",
          gourp: "分组",
          participator: "参与人",
          hidden: "日程隐藏",
          taskDoneTime: "事项完成时间",
          taskDoneMyTime: "我的完成时间",
          star: "关注",
          taskAchievement: "完成情况",
          creator: "创建人",
          updateTime: "更新时间",
        },
      ],
      /**关于children
       * 点击最左的按钮来展开子事项，将children行插入到父行后面
       * 折叠子事项，filter删除掉这个父行的children行
       * childrenNum:-1自己是child，>-1代表拥有child 的个数
       */
      //渲染table的内容表格
      contents_data: [
        {
          id: "t1",
          time: "2022.10.22",
          data: [
            {
              id: "t1l1",
              name: "名称1",
              task_time: "事项时间1",
              group: "分组1",
              project:
                "项目1项目1项目1项目1项目1项目1项目1项目1项目1项目1项目1项目1",
              conclusion: "总结1",
              team: "团队1",
              state_val: 0,
              state_label: "",
              isDone: false,
              childrenNum: 2,
              showChildren: false,
              hidden: true,
              star: true,
            },
            {
              id: "t1l2",
              name: "名称2",
              task_time: "事项时间2",
              group: "分组1",
              project: "项目1",
              conclusion: "总结1",
              team: "团队1",
              state_val: 1,
              state_label: "",
              isDone: false,
              childrenNum: 3,
              showChildren: false,
              hidden: false,
              star: false,
            },
            {
              id: "t1l3",
              name: "名称3",
              task_time: "事项时间3",
              group: "分组1",
              project: "项目1",
              conclusion: "总结1",
              team: "团队1",
              state_val: 2,
              state_label: "",
              isDone: false,
              childrenNum: 1,
              showChildren: false,
              hidden: false,
              star: false,
            },
            {
              id: "t1l4",
              name: "名称4",
              task_time: "事项时间4",
              group: "分组1",
              project: "项目1",
              conclusion: "总结1",
              team: "团队1",
              state_val: 3,
              state_label: "",
              isDone: false,
              childrenNum: 4,
              showChildren: false,
              hidden: false,
              star: false,
            },
          ],
        },
      ],
    };
  },
  mounted() {
    //取得滚动条dom
    this.SyncScroll_head = this.$refs.sync_scroller_head.bodyWrapper;
    this.$refs.sync_scroller_con.forEach((ele) => {
      this.SyncScroll_contents.push(ele.bodyWrapper);
    });
    //滚动条监听启动
    this.SyncScroll_head.addEventListener("scroll", this.listenerScroll);

    //为每个事项生成state label
    this.InitStateLabel();

    //测试leader line画图
    // this.TestLeaderLine();
  },
  destroyed() {
    //监听事件解绑
    this.SyncScroll_head.removeEventListener("scroll ", this.listenerScroll);
  },
  methods: {
    //遍历this.contents_data ，根据state_val,生成对应的state_label
    InitStateLabel() {
      this.contents_data.forEach((content) => {
        content.data.forEach((ele) => {
          let label;
          switch (ele.state_val) {
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
          ele.state_label = label;
        });
      });
    },

    //滚动条同步，监听事件
    listenerScroll() {
      this.SyncScroll_contents.forEach((ele) => {
        // 横滚同步
        ele.scrollLeft = this.SyncScroll_head.scrollLeft;
      });
    },
    //事项isDone，单选框事件
    onTaskDoneRadioChange(model, content_id) {
      // 有子事项，操作全部的事项
      if (model.childrenNum > 0) {
        //若未展开
        let indexs = {};
        if (!model.showChildren) {
          model.showChildren = true;
          indexs = this.TableInsertChildren(model, content_id);
        } else {
          //先找是哪个时间块
          let content_index = this.contents_data.findIndex(
            (content) => content.id == content_id
          );
          //再找父行的位置
          let line_index = this.contents_data[content_index].data.findIndex(
            (line) => {
              return line.id == model.id;
            }
          );
          indexs.content_index = content_index;
          indexs.line_index = line_index;
        }
        //操作所有的事项
        let tar =
          !this.contents_data[indexs.content_index].data[indexs.line_index]
            .isDone;
        for (let i = 0; i <= model.childrenNum; i++) {
          this.contents_data[indexs.content_index].data[
            i + indexs.line_index
          ].isDone = tar;
        }
      } else {
        model.isDone = !model.isDone;
      }
      // console.log(this.contents_data[0].data);
    },

    //根据表头的字段名，返回表头列的宽度
    GetHeadFieldWidth(field) {
      let width = "auto";
      this.head_data_common.forEach((ele) => {
        if (ele.key == field) {
          width = ele.width;
        }
      });
      return width;
    },
    //根据事项的状态值state_val，返回对应的type类型
    StateTagName(state_val) {
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
      return tag_name;
    },
    //点击 展开子事项列表的icon
    //图标切换;插入或删除children行
    //content_id确认是哪一个是时间块的，model是具体的行
    ClickfoldChildren_icon(model, content_id) {
      model.showChildren = !model.showChildren;
      //
      if (model.showChildren) {
        this.TableInsertChildren(model, content_id);
        this.InitStateLabel();
      } else {
        this.TableDeleteChildren(model, content_id);
      }
    },
    //某个事项存在children，展开children时，往列表模板中插入相应的children行
    TableInsertChildren(model, content_id) {
      // console.group("TableInsertChildren");
      //获取到children
      let children = this.GetTaskChildren(model);
      //先找是哪个时间块
      let content_index = this.contents_data.findIndex(
        (content) => content.id == content_id
      );
      //再找父行的位置
      let line_index = this.contents_data[content_index].data.findIndex(
        (line) => {
          return line.id == model.id;
        }
      );
      // console.log(content_index);
      // console.log(line_index);
      // console.log("model:", model.id);

      //向对应时间块，对应行，往后插入其children
      this.contents_data[content_index].data.splice(
        line_index + 1,
        0,
        ...children
      );
      return { content_index: content_index, line_index: line_index };

      // console.groupEnd("TableInsertChildren");
    },
    //某个事项存在children，折叠children时，往列表模板中删除相应的children行
    TableDeleteChildren(model, content_id) {
      //先找是哪个时间块
      let content_index = this.contents_data.findIndex(
        (content) => content.id == content_id
      );
      //再找父行的位置
      let line_index = this.contents_data[content_index].data.findIndex(
        (line) => {
          return line.id == model.id;
        }
      );

      //splice删除
      this.contents_data[content_index].data.splice(
        line_index + 1,
        model.childrenNum
      );
    },
    //获取某一事项的所有子事项，static
    GetTaskChildren(model) {
      //static生成
      let f = model;
      let res = [];
      for (let i = 0; i < f.childrenNum; i++) {
        res.push({
          id: f.id + "-child-" + i.toString(),
          name: f.name + "-child-" + i.toString(),
          task_time: "事项时间1",
          group: "分组1",
          project: "项目1",
          conclusion: "总结1",
          team: "团队1",
          state_val: 3,
          state_label: "",
          isDone: false,
          childrenNum: -1,
          showChildren: false,
        });
      }
      return res;
    },
    //根据每行的childrenNum，返回对应的class
    GetRowClass(props) {
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

    //组件star 返回值事件
    HandleStarEvent(val) {
      //根据传回来的content index和line index，设置对应的行的star值
      this.contents_data[val.content_index].data[val.line_index].star =
        val.isActive;
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
  width: 100%;
  //下拉框
  .drop_down_box {
    width: 100%;

    margin-bottom: 1em;
    display: flex;
    flex-direction: row;
    font-family: "HelveticaNeue-Light", "Helvetica Neue Light", "Helvetica Neue",
      Helvetica, Arial, "Lucida Grande", sans-serif;
    font-size: 0.9rem;
    color: var(--head--grey);

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

      //搜索
      .search_allTasks {
        // flex: 1;
        margin-right: 0.9em;
      }

      //表头设置
      .arrange_head {
        // flex: 1;
      }
    }
  }

  //表头
  .table_head_box {
    margin-bottom: 1em;
    // color: black;
    .table_head {
      border-radius: 5px;
    }
    //fixed块的高度
    /deep/ .el-table__fixed {
      height: 100% !important ;
    }

    // 滚动条大小设置
    ::v-deep .el-table__body-wrapper::-webkit-scrollbar {
      /*纵向滚动条*/
      // width:0.5em;
      /*横向滚动条*/
      height: 0.5em;
    }

    // 滚动条滑块样式设置
    ::v-deep .el-table__body-wrapper::-webkit-scrollbar-thumb {
      background-color: #bfbfc0;
      border-radius: 5px;
    }

    // 滚动条背景样式设置
    ::v-deep .el-table__body-wrapper::-webkit-scrollbar-track {
      background: none;
    }

    // 表格横向和纵向滚动条对顶角样式设置
    ::v-deep .el-table__body-wrapper::-webkit-scrollbar-corner {
      background-color: #111;
    }

    // 去除滚动条上方多余显示
    ::v-deep .el-table__header .has-gutter th.gutter {
      display: none !important;
    }

    //隐藏表头的x scroller
    /deep/ .el-table--scrollable-x .el-table__body-wrapper {
      // overflow-x:hidden;
    }
  }

  //内容，分块，每块有多line
  .contents {
    .content {
      margin-bottom: 1.8em;

      //时间分块
      .content_time {
        border-radius: 5px;

        width: 100%;
        background-color: #ffffff;

        .time_text {
          color: var(--head--grey);
        }
      }
      .content_table {
        border-radius: 5px;
        //去除下划线
        /deep/ .noBorder > td {
          border-bottom: none !important;
        }
        //展开子事项时
        /deep/ .parentRow {
        }
        /deep/ .childRow {
          .name_col {
            .foldChildren_icon {
              margin-left: 1em;
            }
          }
        }

        // 名称col
        .name_col {
          .foldChildren_icon {
            margin-right: 0.5em;
            background-color: #f2efef;
          }

          .isDone_radio {
            margin-right: 0px;

            //从默认圆改成方形勾
            ::v-deep .el-radio__inner {
              border-radius: 2px;
              border-width: 2px;
              border-color: var(--color-primary) !important; //外框颜色
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

        // 文字溢出成省略号
        //textflow需要加到文本所在的元素上
        /deep/ .textflow {
          width: 100%; /*一定要设置宽度，或者元素内含的百分比*/
          overflow: hidden; /*溢出的部分隐藏*/
          white-space: nowrap; /*文本不换行*/
          text-overflow: ellipsis; /*ellipsis:文本溢出显示省略号（...）；clip：不显示省略标记（...），而是简单的裁切*/
        }
      }
      //fixed块的高度
      /deep/ .el-table__fixed {
        height: 100% !important ;
      }
      //隐藏表头的x scroller
      /deep/ .el-table--scrollable-x .el-table__body-wrapper {
        overflow-x: hidden;
      }
    }
  }
}

//透明
.transparent {
  opacity: 0;
}
</style>
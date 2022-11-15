<template>
  <el-card class="proj_rangking_list_view">
    <div class="prl_header_box">排行榜</div>
    <div class="prl_content_box">
      <el-tabs v-model="active_tab_key" @tab-click="handleClick">
        <el-tab-pane label="接收量" name="accept">
          <el-table
            :data="accept_ranking_list"
            style="width: 100%"
            :show-header="false"
          >
            <el-table-column class-name="prl_rank_col">
              <template slot-scope="props">
                <span>{{ props.row.rank }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_avator_col">
              <template slot-scope="props">
                <span>{{ props.row.avatar }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_name_col">
              <template slot-scope="props">
                <span>{{ props.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_num_col">
              <template slot-scope="props">
                <span class="pr_num_col_num theme_color">{{
                  props.row.num
                }}</span>
                <span class="pr_num_col_hint">件</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="派发量" name="dispense">
          <el-table
            :data="dispense_ranking_list"
            style="width: 100%"
            :show-header="false"
          >
            <el-table-column class-name="prl_rank_col">
              <template slot-scope="props">
                <span>{{ props.row.rank }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_avator_col">
              <template slot-scope="props">
                <span>{{ props.row.avatar }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_name_col">
              <template slot-scope="props">
                <span>{{ props.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_num_col">
              <template slot-scope="props">
                <span class="pr_num_col_num theme_color">{{
                  props.row.num
                }}</span>
                <span class="pr_num_col_hint">件</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="完成率" name="done_percent">
          <el-table
            :data="done_percent_ranking_list"
            style="width: 100%"
            :show-header="false"
          >
            <el-table-column class-name="prl_avator_col">
              <template slot-scope="props">
                <span>{{ props.row.avatar }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_name_col">
              <template slot-scope="props">
                <span>{{ props.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_num_col">
              <template slot-scope="props">
                <span class="pr_num_col_num theme_color">{{
                  props.row.num
                }}</span>
                <span class="pr_num_col_hint">%</span>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="延期率" name="delay_percent">
          <el-table
            :data="delay_percent_ranking_list"
            style="width: 100%"
            :show-header="false"
          >
            <el-table-column class-name="prl_avator_col">
              <template slot-scope="props">
                <span>{{ props.row.avatar }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_name_col">
              <template slot-scope="props">
                <span>{{ props.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column class-name="prl_num_col">
              <template slot-scope="props">
                <span class="pr_num_col_num alert_color">{{
                  props.row.num
                }}</span>
                <span class="pr_num_col_hint">%</span>
              </template>
            </el-table-column>
          </el-table></el-tab-pane
        >
      </el-tabs>
    </div>
  </el-card>
</template>
<script>
export default {
  name: "ProjRankingList",
  data() {
    return {
      /***
       * 排行榜展示的数据
       */
      //接收事项的排行榜
      accept_ranking_list: [],
      //派发事项的排行榜
      dispense_ranking_list: [],
      //完成率的排行榜
      done_percent_ranking_list: [],
      //延期率的排行榜
      delay_percent_ranking_list: [],
      //tabs选择页
      active_tab_key: "accept",
    };
  },
  mounted() {
    this.GetProjRankingLists();
  },
  methods: {
    //获取项目的4种排行榜数据
    GetProjRankingLists() {
      //获取数据
      this.accept_ranking_list = [
        {
          name: "ena",
          avatar: null,
          num: 2,
        },
        {
          name: "moony",
          avatar: null,
          num: 4,
        },
      ];
      this.dispense_ranking_list = [
        {
          name: "ena",
          avatar: null,
          num: 20,
        },
        {
          name: "moony",
          avatar: null,
          num: 40,
        },
      ];
      this.done_percent_ranking_list = [
        {
          name: "ena",
          avatar: null,
          num: 50,
        },
        {
          name: "moony",
          avatar: null,
          num: 30,
        },
      ];
      this.delay_percent_ranking_list = [
        {
          name: "ena",
          avatar: null,
          num: 50,
        },
        {
          name: "moony",
          avatar: null,
          num: 70,
        },
      ];

      //排序
      this.accept_ranking_list.sort((a, b) => {
        return b.num - a.num;
      });
      this.dispense_ranking_list.sort((a, b) => {
        return b.num - a.num;
      });
      this.done_percent_ranking_list.sort((a, b) => {
        return b.num - a.num;
      });
      this.delay_percent_ranking_list.sort((a, b) => {
        return b.num - a.num;
      });

      //rank
      this.accept_ranking_list = this.accept_ranking_list.map((ele, index) => {
        return {
          name: ele.name,
          avatar: ele.avatar,
          num: ele.num,
          rank: index + 1,
        };
      });
      this.dispense_ranking_list = this.dispense_ranking_list.map(
        (ele, index) => {
          return {
            name: ele.name,
            avatar: ele.avatar,
            num: ele.num,
            rank: index + 1,
          };
        }
      );
      this.done_percent_ranking_list = this.done_percent_ranking_list.map(
        (ele, index) => {
          return {
            name: ele.name,
            avatar: ele.avatar,
            num: ele.num,
            rank: index + 1,
          };
        }
      );
      this.delay_percent_ranking_list = this.delay_percent_ranking_list.map(
        (ele, index) => {
          return {
            name: ele.name,
            avatar: ele.avatar,
            num: ele.num,
            rank: index + 1,
          };
        }
      );
    },
    handleClick(tab, event) {
      //   console.log(tab, event);
    },
  },
};
</script>

<style lang="less" scoped>
.proj_rangking_list_view {
  .prl_header_box {
  }
  .prl_content_box {
    .prl_avator_col {
    }
    /deep/.prl_name_col {
      font-weight: bold;
    }
    .prl_num_col {
      .pr_num_col_num {
        font-size: 1.8em;
        font-weight: bold;
      }
      .pr_num_col_hint {
        color: #8f8f8f;
        font-size: 00.8em;
      }
      .theme_color {
        color: #4fd1c0;
      }
      .alert_color {
        color: #f7ac40;
      }
    }
  }
}
</style>
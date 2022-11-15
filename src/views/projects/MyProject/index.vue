<template>
  <div class="mp_view">
    <el-card class="mp_head_card">
      <el-row class="mp_head_box">
        <el-col class="join_label" :span="3">
          <span>通过邀请码加入项目</span>
        </el-col>
        <el-col :span="10">
          <el-input
            class="join_input"
            placeholder="请输入分享码"
            prefix-icon="el-icon-search"
            v-model="join_proj_code"
          >
          </el-input>
          <el-button @click="JoinProjByShareCode" type="primary" class="join_button">加入</el-button>
        </el-col>
      </el-row>
    </el-card>

    <div class="mp_main_projects_box">
      <el-card class="mp_all_proj_cards">
        <!-- 创建项目 -->
        <el-col class="mp__proj_col" :span="6">
          <el-card
            @click.native="ClickAddCard"
            shadow="hover"
            class="add_card mp_proj_card"
          >
            <p>+</p>
            <p>创建项目</p>
          </el-card></el-col
        >
        <!-- 展示所有项目 -->
        <el-col
          class="mp__proj_col"
          :span="6"
          v-for="(proj, index) in participant_projs"
          :key="index"
        >
          <el-card
            @click.native="ClickProjCard(proj)"
            class="item_card mp_proj_card"
            shadow="hover"
          >
            <div class="proj_title">
              <span>{{ proj.title_name }}</span>
            </div>
            <div class="proj_progress">
              <el-progress
                :format="(per) => format(per, proj)"
                :percentage="proj.proj_progress"
              ></el-progress>
            </div>
            <div class="today_news">
              <div class="new_item">
                <span class="new_num">{{ proj.todayTask_new_create }}</span>
                <span class="new_hint">今日新增</span>
              </div>
              <div class="new_item">
                <span class="new_num">{{ proj.todayTask_new_create }}</span>
                <span class="new_hint">今日新增</span>
              </div>
              <div class="new_item">
                <span class="new_num">{{ proj.todayTask_new_create }}</span>
                <span class="new_hint">今日新增</span>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-card>
    </div>
    <!-- 创建新项目的弹窗 -->
    <el-dialog
      :modal="false"
      :v-if="isShow_dialog_createProj"
      :visible.sync="isShow_dialog_createProj"
    >
      <el-form
        :rules="creatProj_rules"
        ref="create_proj_form"
        :model="form_creatNewProj"
        label-width="6em"
      >
        <el-form-item prop="title_name" label="项目名称">
          <el-input v-model="form_creatNewProj.title_name"></el-input>
        </el-form-item>
        <el-form-item prop="intro_text" label="项目概述">
          <el-input
            type="textarea"
            v-model="form_creatNewProj.intro_text"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            class="marginLeftAuto"
            type="primary"
            @click="CreateNewProj"
            >立即创建</el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 新项目创建成功的弹窗 -->
    <el-dialog
      :modal="false"
      :v-if="isShow_dialog_createdDone"
      :visible.sync="isShow_dialog_createdDone"
      class="mpcd_dialog"
    >
      <div class="mpdc_content">
        <div v-show="loading_createNewProj">
          <div class="mpcd_head">项目创建中</div>
          <div class="mpcd_main" v-loading="loading_createNewProj"></div>
        </div>
        <div v-show="!loading_createNewProj">
          <div class="mpcd_head">项目创建成功！</div>
          <div class="proj_code_box">
            <span class="code_label">分享码:</span>
            <span class="code_value">1123321</span>
          </div>
          <el-button type="primary" @click="isShow_dialog_createdDone = false"
            >确定</el-button
          >
        </div>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "MyProject",
  data() {
    return {
      /*我的所有项目信息brief */
      participant_projs: [],
      /*
      创建新项目
       */

      isShow_dialog_createProj: false,
      isShow_dialog_createdDone: false,
      form_creatNewProj: {
        title_name: "",
        intro_text: "",
      },
      creatProj_rules: {
        title_name: [
          { required: true, message: "请输入项目名称", trigger: "blur" },
        ],
      },
      //创建新项目中loading
      loading_createNewProj: true,
      /*加入项目 */
      join_proj_code: "123321",
    };
  },
  methods: {
    format(percentage, proj) {
      return `${proj.nowTask_complete}/${proj.nowTask_all}`;
    },
    GetMyAllProjectsBrief() {
      this.participant_projs = [
        {
          proj_id: "我参与的项目1_ID",
          title_name: "我参与的项目1",
          //目前的所有事项数量 ： 目前已经complete 的事项
          nowTask_all: 12,
          nowTask_complete: 5,
          //今日事项的新增、完成、延期数量
          todayTask_new_create: 1,
          todayTask_new_complete: 2,
          todayTask_new_delay: 3,
        },
        {
          proj_id: "我参与的项目2_ID",
          title_name: "我参与的项目2",
          //目前的所有事项数量 ： 目前已经complete 的事项
          nowTask_all: 120,
          nowTask_complete: 50,
          //今日事项的新增、完成、延期数量
          todayTask_new_create: 5,
          todayTask_new_complete: 6,
          todayTask_new_delay: 4,
        },
        {
          proj_id: "我创建的项目1_ID",
          title_name: "我创建的项目1",
          //目前的所有事项数量 ： 目前已经complete 的事项
          nowTask_all: 12,
          nowTask_complete: 5,
          //今日事项的新增、完成、延期数量
          todayTask_new_create: 1,
          todayTask_new_complete: 2,
          todayTask_new_delay: 3,
        },
        {
          proj_id: "我创建的项目2_ID",
          title_name: "我创建的项目2",
          //目前的所有事项数量 ： 目前已经complete 的事项
          nowTask_all: 140,
          nowTask_complete: 60,
          //今日事项的新增、完成、延期数量
          todayTask_new_create: 5,
          todayTask_new_complete: 6,
          todayTask_new_delay: 4,
        },
      ];
      this.participant_projs.forEach((ele) => {
        ele.proj_progress = Math.floor(
          (ele.nowTask_complete / ele.nowTask_all) * 100
        );
      });
    },
    ClickProjCard(proj) {
      //进入目标项目的详情页面
      console.log(proj);
    },
    ClickAddCard() {
      //显示弹窗，创建新项目
      this.isShow_dialog_createProj = true;
      console.log(true);
    },
    //创建项目
    CreateNewProj() {
      console.log(this.form_creatNewProj);
      this.isShow_dialog_createProj = false;
      this.isShow_dialog_createdDone = true;
      this.loading_createNewProj = true;
      //后端请求，创建新项目
      setTimeout(() => {
        this.loading_createNewProj = false;
      }, 500);
    },
  },
  mounted() {
    //我的所有项目，初始get
    this.GetMyAllProjectsBrief();
  },
};
</script>
<style lang="less" scoped>
.mp_view {
  .mp_head_card {
    font-family: "Goudy Bookletter 1911", sans-serif;

    margin-bottom: 0.5em;
    .mp_head_box {
      display: flex;
      flex-direction: row;
      align-items: center;
      .join_label {
      }
      .join_input {
        width: auto;
        display: inline-block;
      }
      .join_button {
        margin-left: 0.3em;
      }
    }
  }

  .mp_main_projects_box {
    .mp_all_proj_cards {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      .mp__proj_col {
        margin-right: 6em;
        margin-bottom: 1em;
        .mp_proj_card {
        }
        .add_card {
        }
        .item_card {
          .proj_title {
            font-size: 1em;
            font-weight: bold;
          }
          .today_news {
            display: flex;
            align-items: center;
            flex-direction: row;
            justify-content: center;
            .new_item {
              margin-right: 1em;
              margin-top: 1em;
              display: flex;
              flex-direction: column;
              justify-content: center;
              align-items: center;
              .new_num {
                font-size: 1.2em;
                font-weight: bold;
              }
              .new_hint {
                margin-bottom: 0.5em;
                margin-top: 0.5em;
                color: #8f8f8f;
                font-size: 0.8em;
              }
            }
          }
        }
      }
    }
  }
  .mpcd_dialog {
    .mpdc_content {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .mpcd_head {
        font-size: 1.5em;
        font-weight: bold;
        margin-bottom: 1em;
      }
      .proj_code_box {
        font-size: 1.5em;
        .code_label {
        }
        .code_value {
        }
      }
      .mpcd_main {
      }
    }
  }
}
</style>

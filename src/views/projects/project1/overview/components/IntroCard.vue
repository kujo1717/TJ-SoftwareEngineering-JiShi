<template>
  <el-card class="intro-card-component">
    <!-- header -->
    <div class="icc_header">
      <span v-show="!intro_editable" class="header_title">
        {{ title_name }}
      </span>
      <el-input
        class="title_input"
        v-show="intro_editable"
        v-model="title_name"
      />

      <span class="set_project_time">设置项目完成时间</span>
    </div>
    <div class="hint">项目时间显示</div>

    <div class="intro_text">
      <span v-show="!intro_editable">
        {{ proj_introText }}
      </span>
      <el-input
        v-show="intro_editable"
        type="textarea"
        autosize
        v-model="proj_introText"
        placeholder="请输入内容"
      >
      </el-input>
    </div>

    <i
      v-if="UserIdentity == 'creator'"
      :class="{
        'el-icon-edit': !intro_editable,
        'el-icon-check': intro_editable,
        edit_icon: true,
      }"
      @click="intro_editable = !intro_editable"
    />

    <el-divider style="margin-top: 1em"></el-divider>
    <!-- 项目成员 -->
    <div class="projmembers_box">
      <!-- header -->
      <div class="pmb_header">
        <span class="hint" style="font-size:1.2em">项目成员</span>
      </div>
      <!-- 成员展示 -->
      <div class="pmb_members">
        <el-tabs v-model="activeName">
          <el-tab-pane class="pmb_manager" label="管理员" name="manager">
            <div
              class="pmb_manager_item"
              v-for="(manager, index) in manager_datalist"
              :key="index"
            >
              <el-dropdown>
                <el-popover
                  placement="bottom"
                  :title="manager.name"
                  width="200"
                  trigger="click"
                >
                  <div>
                    <span>身份:{{ manager.role }}</span>
                  </div>
                  <el-avatar
                    slot="reference"
                    :src="manager.avatar"
                    style="margin: 20px"
                  >
                    {{ manager.name }}
                  </el-avatar>
                </el-popover>
              </el-dropdown>
            </div>
          </el-tab-pane>
          <el-tab-pane class="pmb_member" label="一般成员" name="member">
            <div
              class="pmb_member_item"
              v-for="(member, index) in member_datalist"
              :key="index"
            >
              <el-dropdown>
                <el-popover
                  placement="bottom"
                  :title="member.name"
                  width="200"
                  trigger="click"
                >
                  <div>
                    <span>身份:{{ member.role }}</span>
                  </div>
                  <el-avatar
                    slot="reference"
                    :src="member.avatar"
                    style="margin: 20px"
                  >
                    {{ member.name }}
                  </el-avatar>
                </el-popover>
              </el-dropdown>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </el-card>
</template>
<script>
export default {
  name: "IntroCard",
  data() {
    return {
      /**
       * 项目名字
       */
      title_name: "{$项目名字}",
      /**
       * 介绍
       */
      proj_introText: "项目内容介绍文本",

      //项目完成时间，时间设置 enable
      proj_time_setter_enable: false,
      //项目名字+介绍，修改设置
      intro_editable: false,

      /**
       * 用户个人信息
       */
      //creator,manager,member
      UserID: "p1_creator_ID",
      UserIdentity: "creator",

      //创建人data
      creator_data: {},

      /**
       * 管理员data
       */
      manager_datalist: [],

      /**
       * 一般成员data
       */
      member_datalist: [],

      /**展示tab */
      activeName: "manager",
    };
  },
  methods: {
    /**
     * 项目名字
     */
    //点击修改
    title_name_click_edit() {
      this.intro_editable = !this.intro_editable;
    },

    /**项目成员信息 */
    getAllMemberData() {
      this.creator_data = {
        id: "p1_creator_ID",
        name: "p1_creator_NAME",
        avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
        role: "creator",
      };
      this.manager_datalist = [
        {
          id: "p1_creator_ID",
          name: "p1_creator_NAME",
          avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
          role: "creator",
        },
        {
          id: "p1_manager1_ID",
          name: "p1_manager1_NAME",
          avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
          role: "manager",
        },
        {
          id: "p1_manager2_ID",
          name: "p1_manager2_NAME",
          avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
          role: "manager",
        },
      ];
      this.member_datalist = [
        {
          id: "p1_member1_ID",
          name: "p1_member1_NAME",
          avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
          role: "member",
        },
        {
          id: "p1_member2_ID",
          name: "p1_member2_NAME",
          avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
          role: "member",
        },
      ];
    },
  },
  mounted() {
    //获取所有成员信息
    this.getAllMemberData();
  },
};
</script>
<style lang="less" scoped>
.intro-card-component {
  border-radius: 10px;
  .icc_header {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    .header_title {
    }
    .title_input {
      width: auto;
    }

    .set_project_time {
      margin-left: auto;
    }
    margin-bottom: 1em;
  }

  .intro_text {
    margin-bottom: 0.8em;
    margin-top: 0.8em;
    min-height: 3em;
  }
  .edit_icon {
    float: right;
    font-size: 1.5em;
    margin-top: -0.8em;
  }
  .projmembers_box {
    .pmb_header {
    }
    .pmb_members {
      margin-top: 0.6em;

      .pmb_manager {
        .pmb_manager_item {
          display: inline-block;
          margin-right: 0.5em;
        }
      }
      .pmb_member {
        .pmb_member_item {
          display: inline-block;
          margin-right: 0.5em;
        }
      }
    }
  }
}
.hint {
  margin-bottom: 0.5em;
  margin-top: 0.5em;
  color: #8f8f8f;
  font-size: 0.8em;
}
</style>>



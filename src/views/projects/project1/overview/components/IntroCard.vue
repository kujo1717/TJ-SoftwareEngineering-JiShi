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
        <span class="hint" style="font-size: 1.2em">项目成员</span>
        <span
          style="float: right; font-weight: bold"
          @click="isShow_dialog_adminAllMembers = true"
          >管理</span
        >
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
             
            </div>
          </el-tab-pane>
          <el-tab-pane class="pmb_member" label="一般成员" name="member">
            <div
              class="pmb_member_item"
              v-for="(member, index) in member_datalist"
              :key="index"
            >
             
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
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
    <el-dialog
      class="icc_adminMember_dialog"
      :v-if="isShow_dialog_adminAllMembers"
      :visible.sync="isShow_dialog_adminAllMembers"
    >
      <span class="icc_adminMember_dashBoard">
        <div
          style="
            margin-top: -1em;
            font-size: 2em;
            color: black;
            font-weight: bold;
          "
        >
          项目成员
        </div>
        <div class="hint">创建者</div>
        <div class="icca_creator_main">
          <el-avatar
            class="icca_creator_avatar"
            :src="proj_creator_data.avatar"
          >
          </el-avatar>
          <span class="icca_proj_info">
            <span style="font-size: 1.2em; color: black">{{
              proj_creator_data.name
            }}</span>
            <span class="hint">创建于{{ proj_time_created }}</span>
          </span>
        </div>

        <el-tabs v-model="adminAllMembers_tabValue">
          <el-tab-pane label="管理员" name="manager">
            <div
              @click="isShow_dialog_addManager = true"
              class="icca_add_manager"
              style="font-size: 1.5em"
            >
              <i class="el-icon-circle-plus-outline"></i>
              <span>邀请管理员</span>
            </div>

            <div
              class="icca_memberline"
              v-for="(mem, index) in manager_datalist"
              :key="index"
            >
              <el-avatar
                class="icca_memerline_avatar"
                :src="proj_creator_data.avatar"
              >
              </el-avatar>
              <span class="icca_memberline_info">
                <span style="font-size: 1.2em; color: black">{{
                  mem.name
                }}</span>
                <span class="hint">{{ mem.role }}</span>
              </span>
              <span class="icca_memberline_buttons">
                <el-button type="primary" @click="ClickDemoteManager(mem)"
                  >取消管理员权限</el-button
                >
                <el-button @click="ClickDeleteManager(mem)">删除</el-button>
              </span>
            </div>
          </el-tab-pane>
          <el-tab-pane label="一般成员" name="member">
            <div
              @click="isShow_dialog_addMember = true"
              class="icca_add_manager"
              style="font-size: 1.5em"
            >
              <i class="el-icon-circle-plus-outline"></i>
              <span>邀请一般成员</span>
            </div>

            <div
              class="icca_memberline"
              v-for="(mem, index) in member_datalist"
              :key="index"
            >
              <el-avatar
                class="icca_memerline_avatar"
                :src="proj_creator_data.avatar"
              >
              </el-avatar>
              <span class="icca_memberline_info">
                <span style="font-size: 1.2em; color: black">{{
                  mem.name
                }}</span>
                <span class="hint">{{ mem.role }}</span>
              </span>
              <span class="icca_memberline_buttons">
                <el-button type="primary" @click="ClickUpgradeManager(mem)">
                  授予管理员权限
                </el-button>
                <el-button @click="ClickDeleteMember(mem)">删除</el-button>
              </span>
            </div>
          </el-tab-pane>
        </el-tabs>
      </span>
      <!-- 邀请管理员 -->
      <el-dialog
        width="40%"
        title="邀请管理员"
        :visible.sync="isShow_dialog_addManager"
        class="invite_dialog"
        append-to-body
      >
        <div
          style="
            text-align: center;
            font-size: 1.5em;
            color: black;
            font-weight: bold;
          "
        >
          复制邀请链接给他人
        </div>
        <div
          style="
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5em;
            color: black;
            margin-top: 1.4em;
          "
        >
          <div
            style="
              border: solid #039789 1px;
              border-radius: 3px;
              padding: 0.5em;
            "
          >
            {{ invite_manager_code }}
          </div>
        </div>
      </el-dialog>

      <!-- 邀请一般成员 -->
      <el-dialog
        width="40%"
        title="邀请一般成员"
        :visible.sync="isShow_dialog_addMember"
        class="invite_dialog"
        append-to-body
      >
        <div
          style="
            text-align: center;
            font-size: 1.5em;
            color: black;
            font-weight: bold;
          "
        >
          复制邀请链接给他人
        </div>
        <div
          style="
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5em;
            color: black;
            margin-top: 1.4em;
          "
        >
          <div
            style="
              border: solid #039789 1px;
              border-radius: 3px;
              padding: 0.5em;
            "
          >
            {{ invite_member_code }}
          </div>
        </div>
      </el-dialog>
    </el-dialog>
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

      //项目完成时间，是否设置时间
      proj_time_setter_enable: false,
      //项目名字+介绍，是否修改内容
      intro_editable: false,
      //项目创建时间
      proj_time_created: "2022.1.1",
      //项目创建者信息
      proj_creator_data: {
        name: "creator_NAME",
        id: "",
        avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
      },
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
      //管理所有成员的dialog
      isShow_dialog_adminAllMembers: false,
      adminAllMembers_tabValue: "manager",
      //邀请管理员
      isShow_dialog_addManager: false,
      invite_manager_code: "www.1234321.com",
      //邀请一般成员
      isShow_dialog_addMember: false,
      invite_member_code: "www.1234123321.com",

      /**主tab的value */
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
    /*管理员降级ButtonClick*/
    ClickDemoteManager(mem) {
      //弹窗确认
      const h = this.$createElement;
      this.$msgbox({
        title: "取消管理员权限",
        message: h("p", null, [
          h("span", null, "将把管理员 "),
          h("i", { style: "color: teal" }, mem.name),
          h("span", null, " 降级为一般成员"),
        ]),
        // dangerouslyUseHTMLString: true,
        showCancelButton: true,
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        beforeClose: (action, instance, done) => {
          //点击了 确认
          if (action === "confirm") {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = "执行中...";
            //向后端接口请求，修改管理员权限
            //静态测试，3s后完成操作
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);
          } else {
            done();
          }
        },
      }).then((action) => {
        this.$message({
          type: "info",
          message: "action: " + action,
        });
      });
    },
    /*删除管理员 */
    ClickDeleteManager(mem) {
      //弹窗确认
      const h = this.$createElement;
      this.$msgbox({
        title: "删除管理员",
        message: h("p", null, [
          h("span", null, "将把管理员 "),
          h("i", { style: "color: teal" }, mem.name),
          h("span", null, " 移除出本项目！"),
        ]),
        // dangerouslyUseHTMLString: true,
        showCancelButton: true,
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        beforeClose: (action, instance, done) => {
          //点击了 确认
          if (action === "confirm") {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = "执行中...";
            //向后端接口请求，删除该成员
            //静态测试，3s后完成操作
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);
          } else {
            done();
          }
        },
      }).then((action) => {
        this.$message({
          type: "info",
          message: "action: " + action,
        });
      });
    },
    /*一般成员升级ButtonClick*/
    ClickUpgradeManager(mem) {
      //弹窗确认
      const h = this.$createElement;
      this.$msgbox({
        title: "授予管理员权限",
        message: h("p", null, [
          h("span", null, "将把一般成员 "),
          h("i", { style: "color: teal" }, mem.name),
          h("span", null, " 升级为管理员"),
        ]),
        // dangerouslyUseHTMLString: true,
        showCancelButton: true,
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        beforeClose: (action, instance, done) => {
          //点击了 确认
          if (action === "confirm") {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = "执行中...";
            //向后端接口请求，修改管理员权限
            //静态测试，3s后完成操作
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);
          } else {
            done();
          }
        },
      }).then((action) => {
        this.$message({
          type: "info",
          message: "action: " + action,
        });
      });
    },
    /*删除一般成员 */
    ClickDeleteMember(mem) {
      //弹窗确认
      const h = this.$createElement;
      this.$msgbox({
        title: "删除一般成员",
        message: h("p", null, [
          h("span", null, "将把成员 "),
          h("i", { style: "color: teal" }, mem.name),
          h("span", null, " 移除出本项目！"),
        ]),
        // dangerouslyUseHTMLString: true,
        showCancelButton: true,
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        beforeClose: (action, instance, done) => {
          //点击了 确认
          if (action === "confirm") {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = "执行中...";
            //向后端接口请求，删除该成员
            //静态测试，3s后完成操作
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 3000);
          } else {
            done();
          }
        },
      }).then((action) => {
        this.$message({
          type: "info",
          message: "action: " + action,
        });
      });
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
  .icc_adminMember_dashBoard {
    .icca_creator_main {
      display: flex;
      align-items: center;
      .icca_creator_avatar {
      }
      .icca_proj_info {
        display: flex;
        flex-direction: column;
        margin-left: 1em;
      }
    }
    .icca_memberline {
      display: flex;
      flex-direction: row;
      margin-bottom: 1em;
      .icca_memerline_avatar {
      }
      .icca_memberline_info {
        display: flex;
        flex-direction: column;
        margin-left: 1em;
      }
      .icca_memberline_buttons {
        margin-left: auto;
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



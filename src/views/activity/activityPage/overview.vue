<template>
  <div :loading="edit_confirm_loading">
    <el-card class="intro-card-component">
      <!-- header -->
      <div class="icc_header">
        <span class="header_title">
          {{ title_name }}
        </span>
      </div>
      <div class="hint">创建于{{ act_time_created_label }}</div>

      <div class="intro_text">
        <span>
          {{ act_introText }}
        </span>
      </div>
      <!-- 活动时间展示 -->
      <div style="margin-bottom: 1em; font-weight: bold">
        <span>开始时间:{{ start_time.label }}</span>
        <br />
        <span>结束时间:{{ end_time.label }}</span>
      </div>
      <div class="images">
        <el-image
          style="margin-right: 1em"
          v-for="(img, img_i) in act_imgs"
          :key="img_i"
          :src="img"
        >
          <div slot="error" class="image-slot">
            <i class="el-icon-picture-outline"></i>
          </div>
        </el-image>
      </div>

      <i
        v-if="UserIdentity == 'creator'"
        class="el-icon-edit edit_icon user-select-none"
        @click="title_name_click_edit"
        >修改</i
      >

      <el-divider style="margin-top: 1em"></el-divider>
      <!-- 成员 -->
      <div class="projmembers_box">
        <!-- header -->
        <div class="pmb_header">
          <span class="hint" style="font-size: 1.2em">参与人</span>
          <span>{{ member_num }}/{{ member_limit }}</span>
          <span
            style="float: right; font-weight: bold"
            @click="isShow_dialog_adminAllMembers = true"
            >更多</span
          >
        </div>
        <!-- 成员展示 -->
        <div class="pmb_members">
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
              <span class="avator_button_box" style="display: block">
                <el-button
                  @click="CheckUserDetail(member)"
                  icon="el-icon-search"
                  circle
                ></el-button>
              </span>
              <el-avatar
                slot="reference"
                :src="member.avatar"
                style="margin: 20px"
              >
                {{ member.name }}
              </el-avatar>
            </el-popover>
          </div>
        </div>
      </div>

      <!-- 活动成员的全部展示 -->
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
            参与人员
          </div>
          <div class="hint">活动创建者</div>
          <div class="icca_creator_main">
            <el-avatar
              @click.native="CheckUserDetail(creator_data)"
              class="icca_creator_avatar"
              :src="creator_data.avatar"
            >
            </el-avatar>
            <span class="icca_proj_info">
              <span style="font-size: 1.2em; color: black">{{
                creator_data.name
              }}</span>
              <span class="hint">创建于{{ act_time_created_label }}</span>
            </span>
            <el-button
              style="margin-left: auto"
              type="primary"
              @click="CheckUserDetail(creator_data)"
            >
              查看</el-button
            >
          </div>

          <div
            class="icca_memberline"
            v-for="(mem, index) in member_datalist"
            :key="index"
          >
            <el-avatar
              @click.native="CheckUserDetail(mem)"
              class="icca_memerline_avatar"
              :src="creator_data.avatar"
            >
            </el-avatar>
            <span class="icca_memberline_info">
              <span style="font-size: 1.2em; color: black">{{ mem.name }}</span>
            </span>
            <el-button
              style="margin-left: auto"
              type="primary"
              @click="CheckUserDetail(mem)"
            >
              查看</el-button
            >
          </div>
        </span>
      </el-dialog>

      <!-- 某个用户的详情查看 -->
      <el-dialog
        class="mac_user_detail_dialog"
        :v-if="isShow_dialog_user"
        :visible.sync="isShow_dialog_user"
        title="其他用户的详情页面，可添加好友"
      >
        <div>
          <el-avatar :src="other_user_detail_data.avatar"> </el-avatar>
        </div>
        <div>
          <span>{{ other_user_detail_data.name }}</span>
        </div>
        <div>
          <el-button type="primary" v-if="other_user_detail_data.relation == 2"
            >添加好友</el-button
          >
        </div>
      </el-dialog>
    </el-card>

    <!-- 活动时间投票卡片 -->
    <el-col :span="12">
      <!-- 创建人发起投票 -->
      <div></div>

      <!-- 展示投票 -->
      <el-card>
        <div>
          <vue-poll v-bind="options" @addvote="addVote" />
        </div>
      </el-card>
    </el-col>

    <!-- 修改活动信息dialog -->
    <el-dialog
      title="修改活动信息"
      :visible.sync="isShow_dialog_edit"
      width="50%"
      :before-close="handleClose_edit"
    >
      <span>
        <div>
          <!-- 开始日期  -->
          <el-date-picker
            v-model="start_date_picker"
            type="datetime"
            placeholder="选择开始时间"
          >
          </el-date-picker>
          <!-- 开始日期  -->
          <el-date-picker
            v-model="end_date_picker"
            type="datetime"
            placeholder="选择结束日期"
          >
          </el-date-picker>
        </div>
      </span>

      <span slot="footer" class="dialog-footer">
        <el-button @click="ConfirmEdit" type="primary">确认修改</el-button>
        <el-button @click="CancelEdit">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import VuePoll from "vue-poll";
export default {
  name: "ActivityPageOverView",
  components: { VuePoll },
  data() {
    return {
      /**
       * 活动信息
       */
      id: "",
      title_name: "",
      /**
       * 文本介绍
       */
      act_introText: "",
      //配图
      act_imgs: [],

      //活动创建时间
      act_time_created_label: "2022.1.1",

      /**
       * 用户个人信息
       */
      //creator,member
      UserID: "p1_creator_ID",
      UserIdentity: "creator",

      //创建人data
      creator_data: {},

      /**
       * 参与人data
       */
      member_datalist: [],
      member_limit: "",
      member_num: "",

      //管理所有成员的dialog
      isShow_dialog_adminAllMembers: false,

      //查看某个用户的dialog
      isShow_dialog_user: false,
      //查看的这个用户的detail
      other_user_detail_data: {},

      /*日期展示 */
      start_time: {},
      end_time: {},
      // date picker
      start_date_picker: "",
      end_date_picker: "",

      /*
      投票 
      */

      //发起投票
      options: {
        question: "What's your favourite <strong>JS</strong> framework?",
        answers: [
          { value: 1, text: "Vue", votes: 53 },
          { value: 2, text: "React", votes: 35 },
          { value: 3, text: "Angular", votes: 30 },
          { value: 4, text: "Other", votes: 10 },
        ],
      },

      //创建者 修改信息的flag
      isShow_dialog_edit: false,
      edit_confirm_loading: false,
    };
  },
  methods: {
    /**时间投票*/
    addVote(obj) {
      console.log("You voted " + obj.value + "!");
    },
    /**
     * 活动信息
     */

    //获取活动详细信息
    getActDetail(id) {
      //api
      //根据活动id，获取详情

      //活动介绍信息
      this.title_name = id + "{$活动名字}";
      this.act_introText = id + "活动内容文本";
      this.act_time_created_label = "2022.1.1";
      this.act_imgs = [
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.5ABMjlXUlPCUHSj6xFdEZAHaE8?pid=ImgDet&rs=1",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.5ABMjlXUlPCUHSj6xFdEZAHaE8?pid=ImgDet&rs=1",
      ];
      //活动日期date
      this.start_time = {
        label: "2022",
        year: "",
        month: "",
        day: "",
        hour: "",
        minute: "",
        second: "",
        exist: true,
      };
      this.end_time = {
        label: "未定",
        year: "",
        month: "",
        day: "",
        hour: "",
        minute: "",
        second: "",
        exist: false,
      };

      //活动人员信息
      this.creator_data = {
        id: "p1_creator_ID",
        name: "p1_creator_NAME",
        avatar: "https://s1.ax1x.com/2022/11/13/zF8nrd.jpg",
        role: "creator",
      };
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
      this.member_limit = 20;
      this.member_num = 2;
    },
    //点击修改,弹窗
    title_name_click_edit() {
      //跳转路由
      // let query_data = {
      //   id: this.id,
      // };
      // this.$router.push({
      //   name: "ActivityEdit",
      //   query: query_data,
      // });
      this.isShow_dialog_edit = true;
    },

    /*管理员降级ButtonClick*/
    ClickDemotemember(mem) {
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

    //查看某个user的 detail
    CheckUserDetail(user) {
      const userID = user.id;
      // console.log(userID);
      this.isShow_dialog_user = true;
      //api
      //userID请求user详情
      this.other_user_detail_data = {};
      this.other_user_detail_data = {
        id: userID,
        name: user.name,
        avatar: user.avatar,
        role: user.role,
        relation: 2, //0 是我，1是好友，2不是好友
      };
    },

    /*修改活动信息 */
    //cancel
    CancelEdit() {
      this.isShow_dialog_edit = false;
    },
    //confirm
    ConfirmEdit() {
      //api
      //提交新的活动信息
      this.edit_confirm_loading = true;

      //全局loading模板
      let thisContent = this;
      let edit_loading = thisContent.$loading({
        lock: true,
        text: "修改中，请稍候...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.5)",
      });

      // 中间进行一系列的操作

      // 上传成功后关闭loading， 并显示上传成功

      setTimeout(() => {
        this.isShow_dialog_edit = false;
        this.edit_confirm_loading = false;
        edit_loading.close();
      }, 1000);
    },
    //dialog 关闭事件
    handleClose_edit() {
      this.edit_confirm_loading = false;
      this.isShow_dialog_edit = false;
    },
  },
  mounted() {
    this.id = this.$route.query.id;
    //根据传入的id，获取活动的详情
    this.getActDetail(this.id);
  },
};
</script>
<style lang="less" scoped>
.intro-card-component {
  margin: 1em;
  border-radius: 10px;
  .icc_header {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    .header_title {
      margin-right: auto;
    }
    .title_input {
      width: auto;
      margin-right: auto;
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
      display: flex;
    }
  }
  .icc_adminMember_dashBoard {
    .icca_creator_main {
      display: flex;
      align-items: center;
      margin-bottom: 1em;
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



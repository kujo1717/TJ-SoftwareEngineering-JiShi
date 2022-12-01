<script>
// import UserData from "@/assets/user";
import ContactsData from "@/assets/contacts";
import MessagesData from "@/assets/messages";
import EmojiData from "@/assets/emoji";
import { getOneGroupAllUser, getUserInfoById } from "@/api/chat";
export default {
  name: "QqImui",
  components: {},
  data() {
    return {
      GroupMems: null,
      userId: 1,
      userData: {
        id: null,
        avatar: null,
        displayName: null
      },
    };
  },
  render() {
    return (
      <div class="qq-lemon-imui">
        <lemon-imui
          width="100%"
          class="lemon-slot"
          theme="blue"
          hideMenu
          user={this.userData}
          avatar-cricle
          ref="IMUI"
          on={{
            "pull-messages": this.handlePullMessages,
            "change-contact": this.handleChansgeContact,
            send: this.handleSend,
          }}
          scopedSlots={{
            "message-title": (contact) => {
              return (
                <div>
                  <div style="display:flex;justify-content:space-between">
                    <span style="font-size:22px;">{contact.displayName}</span>
                    <span style="font-size:12px;">
                      <button
                        class="cursor:pointer;"
                        on-click={() => this.openDrawer("center")}
                      >
                        居中
                      </button>
                    </span>
                  </div>
                  {contact.isGroup && (
                    <div class="slot-group-menu">
                      <button>聊天</button>
                      <button>公告</button>
                      <button>相册</button>
                      <button>文件</button>
                      <button>活动</button>
                      <span
                        v-lemon-contextmenu_click={[
                          {
                            text: "操作一",
                            click(e, instance, hide) {
                              hide();
                            },
                          },
                          {
                            text: "操作二",
                          },
                        ]}
                      >
                        设置(左键弹出菜单)
                      </span>
                    </div>
                  )}
                </div>
              );
            },
            "sidebar-contact-fixedtop": (contact) => {
              return (
                <div class="slot-contact-fixedtop">
                  <input class="slot-search" placeholder="搜索通讯录" />
                </div>
              );
            },
            "message-side": (contact) => {
              if (contact.isGroup) {
                return (
                  <div class="slot-group">
                    <div class="slot-group-title">群通知</div>
                    <div class="slot-group-notice">
                      进群请改备注，格式，工作地点-姓名，请大家配合谢谢
                    </div>
                    <div class="slot-group-title">群成员</div>
                    <div class="slot-group-panel">
                      <input class="slot-search" placeholder="搜索群成员" />
                      {this.GroupMems.map((item) => (
                        <div>{item.name}</div>
                      ))}
                    </div>
                  </div>
                );
              }
            },
          }}
        />
      </div>
    );
  },
  computed: {
   
    /**
     * 用户个人信息
     */
    userId: {
      get: function () {
        return this.$store.getters.id;
      },

    },
    user_name: {
      get: function () {
        return this.$store.getters.name;
      },

    },
  },
  watch: {},
  created() {},
  async mounted() {
    const IMUI = this.$refs.IMUI;
    IMUI.initContacts(ContactsData);
    IMUI.initEmoji(EmojiData);
    IMUI.changeContact(13);
    await getUserInfoById(this.userId)
    .then(res => {
      console.log("用户数据",res);
      let tmp = res.data;
      this.userData.id = tmp.userId;
      this.avatar = tmp.avatar;
      this.displayName = tmp.name;
    })
    .catch(err => {
      console.log(err);
    })
    await getOneGroupAllUser(this.userId)
    .then((res) => {
      this.GroupMems = res.data;
      console.log("!", this.GroupMems);
    })
    .catch(err => {
      console.log(err);
    })
    
  },
  methods: {
    openDrawer(position) {
      const IMUI = this.$refs.IMUI;
      const params = {
        position,
        render: (contact) => {
          return (
            <div style="padding:15px">
              <h5>{contact.displayName}</h5>
              <button on-click={IMUI.closeDrawer}>关闭抽屉</button>
            </div>
          );
        },
      };
      if (position == "center") {
        params.width = "50%";
        params.height = "50%";
      } else if (position == "rightInside") {
        params.height = "90%";
        params.offsetY = "10%";
      }
      IMUI.openDrawer(params);
    },
    handlePullMessages(contact, next) {
      console.log("contact",contact);
      // next(MessagesData[contact.id], true);
      next(
        [{
          id: 1,
          status: "succeed",
          type: "text",
          sendTime: 1566047865417,
          content: "我是测试时候看到的",
          toContactId: 1,
          fromUser: this.userData,
        }], true);
      // const { IMUI } = this.$refs;
      // setTimeout(() => {
      //   next(MessagesData[contact.id], true);
      // }, 3000);
    },
    handleChangeContact() {},
    handleSend(message, next, file) {
      console.log(message, next, file);
      setTimeout(() => {
        next();
      }, 1000);
    },
  },
};
</script>
<style lang="less">
.slot-group {
  width: 170px;
  border-left: 1px solid #ddd;
  height: 100%;
  box-sizing: border-box;
  padding: 10px;
}
.slot-group .slot-search {
  margin: 5px 0;
}
.slot-group-notice {
  color: #999;
  padding: 6px 0;
  font-size: 12px;
}
.slot-group-title {
  font-size: 12px;
}
.slot-group-member {
  font-size: 12px;
  line-height: 18px;
}
.slot-group-menu span {
  display: inline-block;
  cursor: pointer;
  color: #888;
  margin: 4px 10px 0 0;
  border-bottom: 2px solid transparent;
}
.slot-group-menu span:hover {
  color: #000;
  border-color: #333;
}
.slot-contact-fixedtop {
  padding: 10px;
  border-bottom: 1px solid #ddd;
}
.slot-search {
  width: 100%;
  box-sizing: border-box;
  font-size: 14px;
  border: 1px solid #bbb;
  padding: 5px 10px;
}
// 隐藏侧边联系人列表
.lemon-sidebar {
  display: none;
}
</style>

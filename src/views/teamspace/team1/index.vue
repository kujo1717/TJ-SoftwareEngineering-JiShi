<template>
  <el-container style="border: 1px solid #eee">
    <div style="width: 100%">
      <!--该card展示团队资料管理-->
      <el-card class="box-card1">
        <div slot="header" class="clearfix" style="padding-left: 20px">
          <span>团队名称：</span>
          <span v-show="!edit1">{{ team.teamName }}</span>
          <el-input
            v-show="edit1"
            v-model="team.teamName"
            style="width: 200px"
          />
          <i
            v-if="user.useridentity"
            :class="{ 'el-icon-edit': !edit1, 'el-icon-check': edit1 }"
            style="padding-left: 10px"
            @click="edit1 = !edit1"
          />
          <el-button
            v-if="user.useridentity == 2"
            style="float: right; padding: 3px"
            type="text"
            @click="confirmdelete()"
            >解散团队</el-button
          >
          <el-button
            v-if="!user.useridentity"
            style="float: right; padding: 3px"
            type="text"
            @click="getinvitationcode()"
            >邀请加入</el-button
          >
        </div>
        <div style="padding-left: 20px; padding-right: 20px">
          <div style="padding-bottom: 10px">团队介绍</div>
          <span v-show="!edit2">{{ team.teamIntro }}</span>
          <el-input
            v-show="edit2"
            v-model="team.teamIntro"
            style="width: 100%"
          />
          <i
            v-if="user.useridentity"
            :class="{ 'el-icon-edit': !edit2, 'el-icon-check': edit2 }"
            style="padding-top: 20px"
            @click="edit2 = !edit2"
          />
        </div>
      </el-card>
      <!--该card展示成员管理-->
      <el-card
        v-if="user.useridentity"
        class="box-card2"
        style="margin-top: 20px"
      >
        <!--只有为管理员时，显示成员管理。根据管理员身份，管理选项不同-->
        <el-menu
          :default-active="activeIndex1"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
        >
          <el-menu-item
            index="1"
            :class="{ active: con_teammember == 1 }"
            @click="con_teammember = 1"
            >管理员</el-menu-item
          >
          <el-menu-item
            index="2"
            :class="{ active: con_teammember == 2 }"
            @click="con_teammember = 2"
            >一般成员</el-menu-item
          >
        </el-menu>
        <div v-show="con_teammember == 1" style="padding: 32px 20px">
          <div
            v-for="(list, index) in team.members"
            :key="index"
            style="display: inline-block"
          >
            <el-dropdown v-if="list.useridentity">
              <i>
                <el-avatar style="margin: 20px">{{ list.username }}</el-avatar>
              </i>
              <el-dropdown-menu slot="dropdown">
                <!--打开聊天系统，对其他用户调用发起私聊的api-->
                <el-dropdown-item v-if="list.userid != user.userid"
                  >发起私聊</el-dropdown-item
                >
                <el-dropdown-item>
                  <el-button type="text" @click="open(index)"
                    >编辑昵称</el-button
                  >
                </el-dropdown-item>
                <!--调用修改身份的api-->
                <el-dropdown-item
                  v-if="user.useridentity == 2 && list.useridentity == 1"
                  >取消管理员身份</el-dropdown-item
                >
                <!--调用修改身份的api-->
                <el-dropdown-item
                  v-if="user.useridentity == 2 && list.useridentity != 2"
                  >设为创建者</el-dropdown-item
                >
                <!--调用删除成员的api-->
                <el-dropdown-item
                  v-if="user.useridentity == 2 && list.useridentity == 1"
                  >删除成员</el-dropdown-item
                >
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
        <div v-show="con_teammember == 2" style="padding: 20px">
          <div
            v-for="(list, index) in team.members"
            :key="index"
            style="display: inline-block"
          >
            <el-dropdown v-if="!list.useridentity">
              <i>
                <el-avatar style="margin: 20px">{{ list.username }}</el-avatar>
              </i>
              <el-dropdown-menu slot="dropdown">
                <!--打开聊天系统，调用发起私聊的api-->
                <el-dropdown-item>发起私聊</el-dropdown-item>
                <el-dropdown-item>
                  <el-button type="text" @click="open(index)"
                    >编辑昵称</el-button
                  >
                </el-dropdown-item>
                <!--调用修改身份的api-->
                <el-dropdown-item v-if="user.useridentity == 2"
                  >设为管理员</el-dropdown-item
                >
                <!--调用修改身份的api-->
                <el-dropdown-item v-if="user.useridentity == 2"
                  >设为创建者</el-dropdown-item
                >
                <!--调用删除成员的api-->
                <el-dropdown-item>删除成员</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <el-button type="text" @click="getinvitationcode()">
            <el-avatar style="margin: 20px"
              ><i class="el-icon-plus"
            /></el-avatar>
          </el-button>
        </div>
      </el-card>
      <!--该card展示我参与的项目-->
      <el-card class="box-card3" style="margin-top: 20px">
        <div>
          <el-card>
            <el-menu
              :default-active="activeIndex2"
              class="el-menu-demo"
              mode="horizontal"
              @select="handleSelect"
            >
              <el-menu-item
                index="1"
                :class="{ active: con_task == 1 }"
                @click="con_task = 1"
                >我参与的项目</el-menu-item
              >
              <!--下面这一行，只有身份为管理员时展示-->
              <el-menu-item
                v-if="user.useridentity == 2"
                index="2"
                :class="{ active: con_task == 2 }"
                @click="con_task = 2"
                >团队所有项目</el-menu-item
              >
            </el-menu>
          </el-card>
          <el-card v-show="con_task == 1">
            <el-row
              v-for="(i, index1) in 3"
              :key="i"
              :span="8"
              :offset="index > 0 ? 4 : 0"
            >
              <el-col
                v-for="(j, index2) in 4"
                :key="j"
                :span="8"
                :offset="index > 0 ? 4 : 0"
                style="width: 240px; margin: 20px"
              >
                <el-card :body-style="{ padding: '0px' }">
                  <div style="padding: 14px">
                    <span style="font-size: 18px"
                      >我的项目{{ index1 * 4 + 1 + index2 }}</span
                    >
                    <div>
                      <el-progress
                        :percentage="
                          percentage(
                            user.project.tasks.today_dued,
                            user.project.tasks.today_accomplished
                          )
                        "
                        style="padding-top: 20px"
                      />
                      <p>今日新增：{{ user.project.tasks.today_added }}</p>
                      <p>
                        今日完成：{{ user.project.tasks.today_accomplished }}
                      </p>
                      <p>今日推迟：{{ user.project.tasks.today_delayed }}</p>
                      <p>今日应完成：{{ user.project.tasks.today_dued }}</p>
                      <el-link href="projectviewing">点击查看</el-link>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
          <el-card v-show="con_task == 2">
            <el-row
              v-for="(i, index1) in 4"
              :key="i"
              :span="8"
              :offset="index > 0 ? 4 : 0"
            >
              <el-col
                v-for="(j, index2) in 4"
                :key="j"
                :span="8"
                :offset="index > 0 ? 4 : 0"
                style="width: 240px; margin: 20px"
              >
                <el-card :body-style="{ padding: '0px' }">
                  <div style="padding: 14px">
                    <span style="font-size: 18px"
                      >团队项目{{ index1 * 4 + 1 + index2 }}</span
                    >
                    <div>
                      <el-progress
                        :percentage="
                          percentage(
                            team.project.tasks.today_dued,
                            team.project.tasks.today_accomplished
                          )
                        "
                        style="padding-top: 20px"
                      />
                      <p>今日新增：{{ team.project.tasks.today_added }}</p>
                      <p>
                        今日完成：{{ team.project.tasks.today_accomplished }}
                      </p>
                      <p>今日推迟：{{ team.project.tasks.today_delayed }}</p>
                      <p>今日应完成：{{ team.project.tasks.today_dued }}</p>
                      <el-link href="projectviewing">点击查看</el-link>
                    </div>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </el-card>
    </div>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      team: {
        teamid: 1,
        teamName: "用户1的团队1",
        teamIntro:
          "此处是一段可编辑的团队介绍，此处是一段可编辑的团队介绍，此处是一段可编辑的团队介绍，此处是一段可编辑的团队介绍，此处是一段可编辑的团队介绍！",
        // 应根据teamid从数据库获取一组projects，使用v-for遍历并作为团队项目展示在前端，此处简化，只定义一个project。
        project: {
          tasks: {
            today_accomplished: 4,
            today_added: 2,
            today_delayed: 3,
            today_dued: 5,
          },
        },
        members: [
          {
            userid: 1,
            username: "user1",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 2,
          },
          {
            userid: 2,
            username: "user2",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 1,
          },
          {
            userid: 3,
            username: "user3",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
          {
            userid: 4,
            username: "user4",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
          {
            userid: 5,
            username: "user5",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 1,
          },
          {
            userid: 6,
            username: "user6",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
          {
            userid: 6,
            username: "user6",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
          {
            userid: 7,
            username: "user7",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
          {
            userid: 7,
            username: "user7",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
          {
            userid: 7,
            username: "user7",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
          {
            userid: 7,
            username: "user7",
            useravatar:
              "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
            // 0:一般成员，1：管理员，2：创建者。可以直接用作判断，展示特定的元素。
            useridentity: 0,
          },
        ],
      },

      user: {
        userid: 1,
        username: "用户昵称",
        useridentity: 2,
        // 应根据userid和teamid，从数据库获取一组projects，使用v-for遍历并作为我的项目展示在前端，此处简化，只定义一个project。
        project: {
          tasks: {
            today_accomplished: 1,
            today_added: 9,
            today_delayed: 6,
            today_dued: 4,
          },
        },
      },
      // 邀请码由系统生成，调用api获取
      invitationcode: "1234567890",
      imageurl:
        "https://img1.baidu.com/it/u=350310368,288322537&fm=253&fmt=auto&app=138&f=JPEG?w=660&h=500",

      // 以下为与数据库无关的数据，无需更改
      edit1: false,
      edit2: false,
      activeIndex1: "1",
      activeIndex2: "1",
      con_task: 1,
      con_teammember: 1,
    };
  },
  mounted() {
    console.log(this.navselected);
  },
  methods: {
    confirmdelete() {
      this.$confirm("此操作将解散团队且不可恢复，是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "解散成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消解散",
          });
        });
    },
    open(index) {
      this.$prompt("请输入新昵称", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputValidator: (value) => {
          // 点击按钮时，对文本框里面的值进行验证
          if (!value) {
            return "输入不能为空";
          } else this.team.members[index].username = value;
        },
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "完成修改",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
    getinvitationcode() {
      this.$alert(
        "邀请码十分钟内有效，请尽快使用：" + this.invitationcode,
        "邀请码",
        {
          confirmButtonText: "确定",
          callback: (action) => {
            this.$message({
              type: "info",
              message: `action: ${action}`,
            });
          },
        }
      );
    },
    percentage(today_dued, today_accomplished) {
      return Number((today_accomplished * 100) / today_dued);
    },
  },
};
</script>

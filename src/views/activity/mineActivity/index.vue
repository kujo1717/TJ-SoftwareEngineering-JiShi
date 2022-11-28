<template>
  <div class="ma_view">
    <el-button @click="TestAPI_static">test API</el-button>
    <el-radio-group
      class="ma_view_redios"
      v-model="ActivityOrTemplate"
      @input="radio_input_change"
    >
      <el-radio-button label="activity">活动</el-radio-button>
      <el-radio-button label="template">模板</el-radio-button>
    </el-radio-group>

    <div class="ma_activity_page" v-if="ActivityOrTemplate === 'activity'">
      <div class="ma_sort_find_box">
        <el-select
          v-model="select_value"
          placeholder="请选择"
          @change="select_change"
        >
          <el-option key="done_time" label="按截至时间倒序" value="done_time">
          </el-option>
          <el-option
            key="create_time"
            label="按创建时间倒序"
            value="create_time"
          >
          </el-option>
        </el-select>
        <el-button type="primary" @click="ClickCreateNewAct"
          >新建活动</el-button
        >
      </div>
      <el-tabs
        v-model="activity_tab"
        @tab-click="(tab) => ActivityTabsClick(tab.name)"
      >
        <el-tab-pane label="测试用活动" name="test">
          <el-col
            :span="6"
            v-for="(act, act_i) in activity_data_test"
            :key="act_i"
            class="ma_act_col"
          >
            <el-card @click.native="ClickActDetail(act)">
              <span class="ma_act_page_card">
                <div class="user-select-none">{{ act.name }}</div>
                <el-image style="width: 100%" :src="act.img"></el-image>
              </span>
            </el-card>
          </el-col>
        </el-tab-pane>

        <el-tab-pane label="所有活动" name="all">
          <el-col
            :span="6"
            v-for="(act, act_i) in activity_data_all"
            :key="act_i"
            class="ma_act_col"
          >
            <el-card @click.native="ClickActDetail(act)">
              <span class="ma_act_page_card">
                <div class="user-select-none">{{ act.name }}</div>
                <el-image style="width: 100%" :src="act.img"></el-image>
              </span>
            </el-card>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="我创建的" name="create">
          <el-col
            :span="6"
            v-for="(act, act_i) in activity_data_create"
            :key="act_i"
            class="ma_act_col"
          >
            <el-card @click.native="ClickActDetail(act)">
              <span class="ma_act_page_card">
                <div class="user-select-none">{{ act.name }}</div>
                <el-image style="width: 100%" :src="act.img"></el-image>
              </span>
            </el-card>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="我报名的" name="apply">
          <el-col
            :span="6"
            v-for="(act, act_i) in activity_data_apply"
            :key="act_i"
            class="ma_act_col"
          >
            <el-card @click.native="ClickActDetail(act)">
              <span class="ma_act_page_card">
                <div class="user-select-none">{{ act.name }}</div>
                <el-image style="width: 100%" :src="act.img"></el-image>
              </span>
            </el-card>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="我参与的" name="involve">
          <el-col
            :span="6"
            v-for="(act, act_i) in activity_data_involve"
            :key="act_i"
            class="ma_act_col"
          >
            <el-card @click.native="ClickActDetail(act)">
              <span class="ma_act_page_card">
                <div class="user-select-none">{{ act.name }}</div>
                <el-image style="width: 100%" :src="act.img"></el-image>
              </span>
            </el-card>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="正在进行" name="doing">
          <el-col
            :span="6"
            v-for="(act, act_i) in activity_data_doing"
            :key="act_i"
            class="ma_act_col"
          >
            <el-card @click.native="ClickActDetail(act)">
              <span class="ma_act_page_card">
                <div class="user-select-none">{{ act.name }}</div>
                <el-image style="width: 100%" :src="act.img"></el-image>
              </span>
            </el-card>
          </el-col>
        </el-tab-pane>
        <el-tab-pane label="已经完成" name="done">
          <el-col
            :span="6"
            v-for="(act, act_i) in activity_data_done"
            :key="act_i"
            class="ma_act_col"
          >
            <el-card @click.native="ClickActDetail(act)">
              <span class="ma_act_page_card">
                <div class="user-select-none">{{ act.name }}</div>
                <el-image style="width: 100%" :src="act.img"></el-image>
              </span>
            </el-card>
          </el-col>
        </el-tab-pane>
      </el-tabs>
    </div>

    <div class="ma_template_page" v-if="ActivityOrTemplate === 'template'">
      <el-col
        :span="6"
        v-for="(act, act_i) in template_data"
        :key="act_i"
        class="ma_act_col"
      >
        <el-card @click.native="ClickModifyTemplate(act)">
          <span class="ma_act_page_card">
            <div class="user-select-none">{{ act.name }}</div>
            <el-image style="width: 100%" :src="act.img"></el-image>
          </span>
          <div class="bottom clearfix">
            <el-button type="primary" @click.stop="ClickModifyTemplate(act)"
              >修改</el-button
            >
            <el-button type="danger" @click.stop="ClickDeleteTemplate(act)"
              >删除</el-button
            >
          </div>
        </el-card>
      </el-col>
    </div>

    <CreateActivity
      v-on:CreateActivityClose="CreateActivityClose_Handler"
      v-show="isShow_dialog_createNewAct"
      :isShow="isShow_dialog_createNewAct"
    ></CreateActivity>
  </div>
</template>
<script>
import { HelloID } from "@/api/user";
import {
  getActBrief_UserCreate,
  postAct,
  getActBrief_Test,
} from "@/api/activity";
import CreateActivity from "../components/createAct.vue";
export default {
  name: "MyActivity",
  components: { CreateActivity },
  data() {
    return {
      /**用户信息 */
      user_id: 1145,
      user_name: "用户名-静态测试",
      //tab 值
      ActivityOrTemplate: "",
      activity_tab: "create",

      //活动data
      activity_data_all: [],
      activity_data_create: [],
      activity_data_involve: [],
      activity_data_apply: [],

      activity_data_doing: [],
      activity_data_done: [],
      activity_data_test: [],

      // act单选框，筛选
      select_value: "",

      //模板data
      template_data: [],

      /*创建新活动 */
      isShow_dialog_createNewAct: false,
    };
  },
  methods: {
    /*测试后端接口 */
    TestAPI_static() {
      postAct({
        activity_id: 9009,
        title_name: "9009_title_name",
      })
        .then((res) => {
          console.log("res", res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    /*
    获取活动列表data
     */
    async GetActivityData_Test() {
      let activity_data_test = [];
      let img_url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";
      // img_url = "";
      await getActBrief_Test(this.user_id)
        .then((res) => {
          console.log("user_id:", this.user_id, "getActBrief_Test:", res);
          res.data.forEach((ele) => {
            activity_data_test.push({
              id: ele.activity_id,
              name: ele.title_name,
              img: img_url,
            });
          });
          this.activity_data_test = activity_data_test;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    async GetActivityData_All() {
      // let activity_data_all = [];
      // let img_url =
      //   "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";
      // // img_url = "";
      // await getActBrief_UserCreate(this.user_id)
      //   .then((res) => {
      //     console.log("user_id:", this.user_id, "getByCreatorID:", res);
      //     res.data.forEach((ele) => {
      //       activity_data_all.push({
      //         id: ele.activity_id,
      //         name: ele.title_name,
      //         img: img_url,
      //       });
      //     });
      //     this.activity_data_all = activity_data_all;
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
    },
    async GetActivityData_Create() {
      let activity_data_create = [];
      let img_url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";
      // img_url = "";
      await getActBrief_UserCreate(this.user_id)
        .then((res) => {
          console.log("user_id:", this.user_id, "getByCreatorID:", res);
          res.data.forEach((ele) => {
            activity_data_create.push({
              id: ele.activity_id,
              name: ele.title_name,
              img: img_url,
            });
          });
          this.activity_data_create = activity_data_create;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    GetActivityData_Apply() {
      let prefix = "apply";
      let tmp = [];
      let img_url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";

      for (let i = 0; i < 6; i++) {
        tmp.push({
          id: prefix + "_" + "id" + "_" + i.toString(),
          name: prefix + "_" + "name" + "_" + i.toString(),
          img: img_url,
        });
      }
      this.activity_data_apply = tmp;
    },
    GetActivityData_Involve() {
      let prefix = "involve";
      let tmp = [];
      let img_url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";

      for (let i = 0; i < 6; i++) {
        tmp.push({
          id: prefix + "_" + "id" + "_" + i.toString(),
          name: prefix + "_" + "name" + "_" + i.toString(),
          img: img_url,
        });
      }
      this.activity_data_involve = tmp;
    },
    GetActivityData_Doing() {
      let prefix = "doing";
      let tmp = [];
      let img_url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";

      for (let i = 0; i < 6; i++) {
        tmp.push({
          id: prefix + "_" + "id" + "_" + i.toString(),
          name: prefix + "_" + "name" + "_" + i.toString(),
          img: img_url,
        });
      }
      this.activity_data_doing = tmp;
    },
    GetActivityData_Done() {
      let prefix = "done";
      let tmp = [];
      let img_url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";

      for (let i = 0; i < 6; i++) {
        tmp.push({
          id: prefix + "_" + "id" + "_" + i.toString(),
          name: prefix + "_" + "name" + "_" + i.toString(),
          img: img_url,
        });
      }
      this.activity_data_done = tmp;
    },
    //获取模板data
    GetTemplateData() {
      let prefix = "template";
      let tmp = [];
      let img_url =
        "https://ts1.cn.mm.bing.net/th/id/R-C.f4470ef67e6e8803479dc44bb3c66574?rik=FXJkAJS%2fLCy9vg&riu=http%3a%2f%2fwww.szshequ.org%2fuserfiles%2fmanagers%2fdachong%2fimage%2f20191028%2f20191028193129_387.jpg&ehk=8FRYXacd0vDrnp2VfQlyA2xK1jIEETABsKgITDdCJXs%3d&risl=&pid=ImgRaw&r=0";

      for (let i = 0; i < 6; i++) {
        tmp.push({
          id: prefix + "_" + "id" + "_" + i.toString(),
          name: prefix + "_" + "name" + "_" + i.toString(),
          img: img_url,
        });
      }
      this.template_data = tmp;
    },
    //单选框点击事件
    select_change(val) {
      console.log(val);
    },
    radio_input_change(val) {
      switch (val) {
        case "activity":
          this.GetActivityData_All();
          break;
        case "template":
          this.GetTemplateData();
          break;
      }
    },
    //Act tabs click
    ActivityTabsClick(name) {
      switch (name) {
        case "test":
          this.GetActivityData_Test();
          break;
        case "all":
          this.GetActivityData_All();
          break;
        case "create":
          this.GetActivityData_Create();
          break;
        case "apply":
          this.GetActivityData_Apply();
          break;
        case "involve":
          this.GetActivityData_Involve();
          break;
        case "doing":
          this.GetActivityData_Doing();
          break;
        case "done":
          this.GetActivityData_Done();
          break;
      }
    },

    /*活动操作 */
    //点击活动，查看详情
    ClickActDetail(act) {
      // console.log(act);
      //跳转路由
      let query_data = {
        id: act.id,
      };
      this.$router.push({
        name: "ActivityPage",
        query: query_data,
      });
    },

    /*
    模板操作
    */
    //删除模板
    ClickDeleteTemplate(act) {
      console.log(act);
      //弹窗确认
      const h = this.$createElement;
      this.$msgbox({
        title: "删除该模板！",
        // message: h("p", null, [
        //   h("span", null, "将把管理员 "),
        //   h("i", { style: "color: teal" }, mem.name),
        //   h("span", null, " 降级为一般成员"),
        // ]),
        // dangerouslyUseHTMLString: true,
        showCancelButton: true,
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        beforeClose: (action, instance, done) => {
          //点击了 确认
          if (action === "confirm") {
            instance.confirmButtonLoading = true;
            instance.confirmButtonText = "执行中...";
            //向后端接口请求，删除
            //静态测试，2s后完成操作
            setTimeout(() => {
              done();
              setTimeout(() => {
                instance.confirmButtonLoading = false;
              }, 300);
            }, 2000);
          } else {
            done();
          }
        },
      }).then((action) => {
        this.$message({
          type: "info",
          message: "模板删除成功",
        });
        //重新获取模板data
        this.GetTemplateData();
      });
    },

    //点击模板，修改
    ClickModifyTemplate(act) {
      //进入模板的修改页面
      console.log(act);
      let query_data = {
        id: act.id,
      };
      this.$router.push({ name: "TemplateView", query: query_data });
    },
    /*创建新活动 */
    ClickCreateNewAct() {
      this.isShow_dialog_createNewAct = true;
    },
    //创建活动的组件，关闭事件
    CreateActivityClose_Handler(val) {
      this.ActivityTabsClick(this.activity_tab);

      this.isShow_dialog_createNewAct = val;
    },
  },
  mounted() {
    const query = this.$route.query;
    // console.log(query);

    this.ActivityOrTemplate = "value" in query ? query.value : "activity";
    if (this.ActivityOrTemplate == "activity") {
      this.GetActivityData_All();
      this.GetActivityData_Create();
    } else {
      this.GetTemplateData();
    }
  },
};
</script>
<style lang="less" scoped>
.ma_view {
  .ma_view_redios {
  }
  .ma_activity_page {
    .ma_act_col {
      margin-left: 1em;

      margin-right: 2em;
      margin-bottom: 2em;
    }
  }
  .ma_template_page {
    .ma_act_col {
      margin-left: 1em;

      margin-right: 2em;
      margin-bottom: 2em;
    }
  }
}
</style>

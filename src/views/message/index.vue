<template>
  <div class="views">
    <el-tabs type="card" v-model="tab_name" @tab-click="test">
      <el-tab-pane label="系统通知" name="sys">
        <span slot="label">
          系统通知
          <el-badge
            v-if="(sys_unread_num > 0) & (sys_unread_num <= 99)"
            class="mark"
            :value="sys_unread_num"
          />
          <el-badge v-else-if="sys_unread_num > 99" class="mark" value="99+" />
        </span>

        <el-card
          v-for="info in system_infos"
          :key="info.index"
          class="card-item"
        >
          <div slot="header">
            <el-row>
              <el-col :span="16" class="title-item">
                {{ info.title
                }}<el-badge v-if="info.status == 0" value="new" class="mark" />
              </el-col>
              <el-col :span="8">
                <el-button
                  class="button-item"
                  type="text"
                  @click="ShowDetails(info.index)"
                  >查看详情</el-button
                >
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8" class="date-item">
                {{ info.createTime }}
              </el-col>
            </el-row>
          </div>
          <div class="content-item">
            {{ info.content }}
          </div>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="活动通知" name="act">
        <span slot="label">
          活动通知
          <el-badge
            v-if="(act_unread_num > 0) & (act_unread_num <= 99)"
            class="mark"
            :value="act_unread_num"
          />
          <el-badge v-else-if="act_unread_num > 99" class="mark" value="99+" />
        </span>

        <el-card
          v-for="info in activity_infos"
          :key="info.index"
          class="card-item"
        >
          <div slot="header">
            <el-row>
              <el-col :span="16" class="title-item">
                {{ info.title
                }}<el-badge v-if="info.status == 0" value="new" class="mark" />
              </el-col>
              <el-col :span="8">
                <el-button
                  class="button-item"
                  type="text"
                  @click="ShowDetails(info.index)"
                  >查看详情</el-button
                >
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8" class="date-item">
                {{ info.createTime }}
              </el-col>
            </el-row>
          </div>
          <div class="content-item">
            {{ info.content }}
          </div>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="事项通知" name="item">
        <span slot="label">
          事项通知
          <el-badge
            v-if="(item_unread_num > 0) & (item_unread_num <= 99)"
            class="mark"
            :value="item_unread_num"
          />
          <el-badge v-else-if="item_unread_num > 99" class="mark" value="99+" />
        </span>

        <el-card
          v-for="info in item_infos"
          :key="info.index"
          class="card-item"
        >
          <div slot="header">
            <el-row>
              <el-col :span="16" class="title-item">
                {{ info.title
                }}<el-badge v-if="info.status == 0" value="new" class="mark" />
              </el-col>
              <el-col :span="8">
                <el-button
                  class="button-item"
                  type="text"
                  @click="ShowDetails(info.index)"
                  >查看详情</el-button
                >
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8" class="date-item">
                {{ info.createTime }}
              </el-col>
            </el-row>
          </div>
          <div class="content-item">
            {{ info.content }}
          </div>
        </el-card>
      </el-tab-pane>
    </el-tabs>
    <el-dialog
      v-if="check_info != null"
      :title="check_info.title"
      :visible.sync="dialogVisible"
    >
      {{ check_info.content }}
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>
<script>
import {
  getSystemInfo,
  getActivityInfo,
  getItemInfo,
  modifyNoticeStatus,
  modifyItemNoticeStatus
} from "@/api/message";
export default {
  name: "Message",
  data() {
    return {
      user_id: 1,
      tab_name: "sys",
      system_infos: [],
      activity_infos: [],
      item_infos: [],
      sys_unread_num: 0,
      act_unread_num: 0,
      item_unread_num: 0,
      check_info: null,
      dialogVisible: false,
    };
  },
  methods: {
    ShowDetails(index) {
      console.log(index);
      if(this.tab_name=="sys"){
        this.check_info = this.system_infos[0];
      }
      else if(this.tab_name=="act"){
        this.check_info = this.activity_infos[0];
      }
      else if(this.tab_name=="item"){
        this.check_info = this.item_infos[0];
      }
      this.dialogVisible = true;
      // 更新消息状态
      if(this.check_info.status == 0){
        if(this.tab_name == "sys"){
          this.system_infos[0].status = 1;
          this.sys_unread_num--;
          modifyNoticeStatus(this.user_id, this.system_infos[0].noticeId);
        }
        else if(this.tab_name == "act"){
          this.activity_infos[0].status = 1;
          this.act_unread_num--;
          modifyNoticeStatus(this.user_id, this.activity_infos[0].noticeId);
        }
        else if(this.tab_name == "item"){
          this.item_infos[0].status = 1;
          this.item_unread_num--;
          modifyItemNoticeStatus(this.user_id, this.item_infos[0].itemNoticeId);
        }
      }
    },
    test(tab) {
      console.log(tab.name);
    },
  },
  async mounted() {
    await getSystemInfo(this.user_id)
      .then((res) => {
        console.log(res);
        this.system_infos = res.data;
        this.system_infos.forEach((value, index) => {
          // 添加index
          value.index = index;
          // 计算未读通知数
          if (value.status == 0) {
            this.sys_unread_num++;
          }
        });
        console.log("添加index后", this.system_infos);
      })
      .catch((err) => {
        console.log(err);
      });
    await getActivityInfo(this.user_id)
      .then((res) => {
        console.log(res);
        this.activity_infos = res.data;
        this.activity_infos.forEach((value, index) => {
          // 添加index
          value.index = index;
          // 计算未读通知数
          if (value.status == 0) {
            this.act_unread_num++;
          }
        });
        console.log("添加index后", this.activity_infos);
      })
      .catch((err) => {
        console.log(err);
      });
    await getItemInfo(this.user_id)
      .then((res) => {
        console.log(res);
        this.item_infos = res.data;
        this.item_infos.forEach((value, index) => {
          // 添加index
          value.index = index;
          // 计算未读通知数
          if (value.status == 0) {
            this.item_unread_num++;
          }
        });
        console.log("添加index后", this.item_infos);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>
<style lang="less" scoped>
.views {
  .card-item {
    background: white;
    border-radius: 10px;
    margin-bottom: 15px;
    padding: 5px;
    .title-item {
      font-weight: bold;
      font-family: "Hiragino Sans GB";
      font-size: 20px;
      line-height: 1.7;
    }
    .date-item {
      font-family: "Hiragino Sans GB";
      font-size: 15px;
      line-height: 1.7;
      color: darkgrey;
    }
    .content-item {
      font-weight: bold;
      font-family: "Hiragino Sans GB";
      font-size: px;
      line-height: 1.7;
    }
    .button-item {
      float: right;
      padding: 3px;
    }
  }
}
.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>

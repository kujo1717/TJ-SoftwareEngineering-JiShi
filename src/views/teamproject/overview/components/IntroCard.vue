<template>
  <el-card class="intro-card-component">
    <!-- header -->
    <div class="icc_header">
      <span class="header_title">{{ title_name }}</span>
      <span class="set_project_time">设置项目完成时间</span>
    </div>
    <div class="hint">项目时间显示</div>

    <div class="intro_text">
      <el-input
        type="textarea"
        autosize
        v-model="proj_introText"
        placeholder="请输入内容"
      >
      </el-input>
    </div>

    <el-divider></el-divider>
    <!-- 项目成员 -->
    <div class="projmembers_box">
      <!-- header -->
      <div class="pmb_header">
        <span class="hint">项目成员</span>
      </div>
      <!-- 成员展示 -->
      <div class="pmb_members">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane class="pmb_manager" label="管理员" name="manager"
            >管理员</el-tab-pane
          >
          <el-tab-pane class="pmb_member" label="一般成员" name="member"
            >项目成员</el-tab-pane
          >
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
      title_name: "{项目名字}",
      proj_introText: "项目内容介绍文本",
      //项目内容文本，input enable
      proj_introText_input_enable: false,
      //项目完成时间，时间设置 enable
      proj_time_setter_enable: false,
      /**
       * 管理员
       */
      mamager_list: [],

      /**
       * 一般成员
       */
      member_list: [],
    };
  },
};
</script>
<style lang="less" scoped>
.intro-card-component {
    border-radius: 10px;
  .icc_header {
    display: flex;
    flex-direction: row;
    .header_title {
    }
    .set_project_time {
      margin-left: auto;
    }
    margin-bottom: 1em;
  }

  .intro_text {
    margin-bottom: 0.8em;
    margin-top: 0.8em;
  }
  .projmembers_box {
    .pmb_header {
    }
    .pmb_members {
      margin-top: 0.6em;
      
      .pmb_manager {
      }
      .pmb_member {
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



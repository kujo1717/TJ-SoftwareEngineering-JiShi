<template>
  <div>
    <span>留言板</span>
    <div class="form-item section">
      <el-timeline style="margin-right:80px;">
        <el-timeline-item timestamp="2018/4/12" placement="top">
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 2018/4/12 20:46</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/3" placement="top">
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 2018/4/3 20:46</p>
          </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/2" placement="top">
          <el-card>
            <h4>更新 Github 模板</h4>
            <p>王小虎 提交于 2018/4/2 20:46</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
    <div class="el-card-messages">
      <el-input
        type="textarea"
        :rows="5"
        placeholder="输入留言"
        maxlength="200"
        v-model="message"
      ></el-input>
      <el-button type="info" round class="submit-message"
        >留言</el-button
      >
    </div>
  </div>
</template>
<script>
export default {
  name: "Forum",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  mounted() {},
  methods: {},
};
</script>

<style>
.form-item {
  max-height: 420px;
  overflow: auto;
  overflow-x: hidden;
  margin-bottom: 10px;
  
}
::-webkit-scrollbar {
  width: 6px;
}




::-webkit-scrollbar-thumb {
    background: grey;
}
</style>

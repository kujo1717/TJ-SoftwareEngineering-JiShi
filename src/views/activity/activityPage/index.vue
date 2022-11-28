<template>
  <div class="actpi_view">
    <el-tabs v-model="activeName" type="card" >
      <el-tab-pane label="活动概览" name="overview">
        <ActivityPageOverView></ActivityPageOverView
      ></el-tab-pane>
      <el-tab-pane label="讨论" name="chat">
        <el-row>
          <el-col :span="16">
            <el-card class="card-item">
              <BigChatBox></BigChatBox>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="card-item">
              <Forum></Forum>
            </el-card>
          </el-col>
        </el-row>
        
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import ActivityPageOverView from "./overview.vue";
import Forum from "@/components/forum/index.vue"
import BigChatBox from "@/components/BigChatBox/index.vue"
export default {
  name: "ActivityPage",
  components: { ActivityPageOverView, BigChatBox, Forum},
  data() {
    return {
      activeName:'overview'
    };
  },
  methods: {},
  mounted() {

  },
};
</script>
<style lang="less" scoped>
.actpi_view{

}
.card-item {
  background: white;
  border-radius: 10px;
  margin-bottom: 15px;
  margin-right: 10px;
  margin-left: 10px;
  margin-top: 5px;
  padding: 5px;
}
</style>>



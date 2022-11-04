<template>
  <div>
    <el-card>
      <span>
        <p style="text-align:center">《加入团队注意事项》</p>
        <p>由于疫情的原因，考试方式将做出必需的调整和改变，考生应予以充分理解、积极配合，并自觉遵守考试纪律，严格践行考试诚信。线上考试将全程录音、录像，并长期备存可供追溯。同济大学考场纪律中的有关规定同样适用于线上考试。</p>
        <p>考生在考前应持续关注学校的相关通知，按通知要求提前准备考试软件和硬件，确保考试环境符合考试要求，准时参加考试。</p>
        <p>考生应充分评估家中软硬件及电力、网络情况（特别是地处自然灾害高发地区或电力、网络供应不稳定地区）。若无法满足线上考试条件，可申请缓考，或第一时间与学院辅导员取得联系反映困难。考试不因个别学生的考生端原因延期或改期。</p>
      </span>
      <div style="text-align:center;">
        <p style="display:inline-block;padding-right: 20px;"> 我已阅读加入团队注意事项，并保证遵守</p>
        <el-button style="display:inline-block" @click="open()">确认</el-button>
      </div>
    </el-card>
  </div>

</template>

<script>
export default {
  data() {
    return {}
  },
  methods: {
    open(index) {
      this.$prompt('请输入邀请码', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValidator: (value) => {
          // 需要发往后台对邀请码进行验证
          if (!value) {
            return '邀请码错误'
          }
        }
      })
        .then(() => {
          this.$message({
            type: 'success',
            message: '成功加入'
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          })
        })
    }
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}
</style>


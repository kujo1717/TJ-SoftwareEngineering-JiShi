<template>
  <div>
    <el-card v-if="!display">
      <span>
        <p style="text-align:center">《创建团队注意事项》</p>
        <p>由于疫情的原因，考试方式将做出必需的调整和改变，考生应予以充分理解、积极配合，并自觉遵守考试纪律，严格践行考试诚信。线上考试将全程录音、录像，并长期备存可供追溯。同济大学考场纪律中的有关规定同样适用于线上考试。</p>
        <p>考生在考前应持续关注学校的相关通知，按通知要求提前准备考试软件和硬件，确保考试环境符合考试要求，准时参加考试。</p>
        <p>考生应充分评估家中软硬件及电力、网络情况（特别是地处自然灾害高发地区或电力、网络供应不稳定地区）。若无法满足线上考试条件，可申请缓考，或第一时间与学院辅导员取得联系反映困难。考试不因个别学生的考生端原因延期或改期。</p>
      </span>
      <div style="text-align:center;">
        <p style="display:inline-block;padding-right: 20px;"> 我已阅读创建团队注意事项，并保证遵守</p>
        <el-button style="display:inline-block" @click="check()">确认</el-button>
      </div>
    </el-card>
    <div v-if="display" class="app-container" style="width:800px">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="团队名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="团队类别">
          <el-select v-model="form.catagory" placeholder="请选择团队类别">
            <el-option label="学习" value="学习" />
            <el-option label="工作" value="工作" />
            <el-option label="兴趣小组" value="兴趣小组" />
          </el-select>
        </el-form-item>

        <el-form-item label="团队介绍">
          <el-input v-model="form.desc" type="textarea" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit();clean();check()">创建</el-button>
          <el-button @click="onCancel();clean()">取消</el-button>
          <el-button @click="clean()">清空</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        catagory: '',
        desc: ''
      },
      display: 0
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    },
    clean() {
      this.form.catagory = ''
      this.form.name = ''
      this.form.desc = ''
    },
    check() {
      this.display = !this.display
    }
  }
}
</script>

<style scoped>
.line {
  text-align: center;
}
</style>


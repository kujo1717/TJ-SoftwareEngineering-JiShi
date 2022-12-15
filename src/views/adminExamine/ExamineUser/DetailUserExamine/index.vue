
<template>
  <div class="views">

    <!--举报单信息-->
    <div>
      <el-descriptions
        title="活动举报单"
        :column="2"
        border
      >
        <el-descriptions-item label="举报单ID">{{form.reportId}}
        </el-descriptions-item>
        <el-descriptions-item label="举报人ID">{{form.informerId}}
        </el-descriptions-item>
        <el-descriptions-item label="被举报人ID">{{form.userId}}
        </el-descriptions-item>
        <el-descriptions-item label="活动ID">{{form.activityId}}
        </el-descriptions-item>
        <el-descriptions-item label="举报分类">{{form.type}}</el-descriptions-item>
        <el-descriptions-item label="举报时间">{{form.reportTime}}
        </el-descriptions-item>
        <el-descriptions-item label="举报详述">{{form.detail}}
        </el-descriptions-item>
      </el-descriptions>
    </div>

    <!--用户信息-->
    <div>
      <el-descriptions
        title="被举报人信息"
        :column="2"
        border
      >
        <el-descriptions-item label="举报单ID">{{form.reportId}}
        </el-descriptions-item>
        <el-descriptions-item label="举报人ID">{{form.informerId}}
        </el-descriptions-item>
        <el-descriptions-item label="被举报人ID">{{form.userId}}
        </el-descriptions-item>
        <el-descriptions-item label="举报分类">{{form.type}}</el-descriptions-item>
        <el-descriptions-item label="举报详述">{{form.detail}}
        </el-descriptions-item>
        <el-descriptions-item label="举报时间">{{form.reportTime}}
        </el-descriptions-item>
      </el-descriptions>
    </div>

    <!--惩罚形式选择-->
    <div>
      管理员裁决
      <el-select
        v-model="form.punishment"
        placeholder="请选择"
      >
        <el-option
          v-for="item in punishment"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
          <span style="float: left">{{ item.label }}</span>
          <span
            style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
        </el-option>
      </el-select>
    </div>

    <!--确认/取消按钮-->
    <div style="position:relative;
			left:50%;
			width:400px;
			height:400px;
			margin-left:-200px;
		        display: block;
">
      <el-button
        type="primary"
        @click="onSubmit()"
        style="margin-right:1em;"
      >提交</el-button>
      <el-button @click="Cancel()">取消</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DetailUserExamine",
  components: {},
  data () {
    return {
      flag: true,
      image_url: '',
      punishment: [
        {
          value: '0',
          label: '不作惩罚'
        },
        {
          value: '1',
          label: '封号1天'
        }, {
          value: '3',
          label: '封号3天'
        }, {
          value: '7',
          label: '封号7天'
        }, {
          value: '14',
          label: '封号14天'
        }, {
          value: '30',
          label: '封号30天'
        }, {
          value: '90',
          label: '封号90天'
        }],

      faultSide: '',
      punishType: '',
      form: {},
      his_obj: {},
      loading: false,
    }
  },
  methods: {
    // 测试从后端获取图片
    getPhoto (report_id) {
      axios
        .request({
          // 以Blob对象的数据类型返回数据
          // 需要在main.js中注释掉对mock的引用才能够正常生效
          responseType: "blob",
          url: "/API/file/photo/report/" + report_id + "/png",
          method: "get",
          headers: {
            TokenValue: this.access_token,
          }
        })
        .then((res) => {
          console.log("成功拿到举报单图片！");
          this.image_url = window.URL.createObjectURL(res.data);
          console.log(res);
          this.loading = false;
        })
        .catch((err) => {
          console.log(err);
          console.log("后端请求图片失败");
          this.get_jpg(report_id);
        });
    },
    get_jpg (id) {
      axios
        .request({
          // 以Blob对象的数据类型返回数据
          // 需要在main.js中注释掉对mock的引用才能够正常生效
          responseType: "blob",
          url: "/API/file/photo/report/" + id + "/jpg",
          method: "get",
          headers: {
            TokenValue: this.access_token,
          }
        })
        .then((res) => {
          console.log("成功拿到举报单图片！");
          this.image_url = window.URL.createObjectURL(res.data);
          console.log(res);
          this.loading = false;
        })
        .catch((err) => {
          console.log(err);
          console.log("后端请求图片失败");
          this.get_jpeg(id);
        });
    },
    get_jpeg (id) {
      axios
        .request({
          // 以Blob对象的数据类型返回数据
          // 需要在main.js中注释掉对mock的引用才能够正常生效
          responseType: "blob",
          url: "/API/file/photo/report/" + id + "/jpeg",
          method: "get",
          headers: {
            TokenValue: this.access_token,
          }
        })
        .then((res) => {
          console.log("成功拿到举报单图片！");
          this.image_url = window.URL.createObjectURL(res.data);
          console.log(res);
          this.loading = false;
        })
        .catch((err) => {
          console.log(err);
          console.log("后端请求图片失败");
          this.get_gif(id);
        });
    },
    get_gif (id) {
      axios
        .request({
          // 以Blob对象的数据类型返回数据
          // 需要在main.js中注释掉对mock的引用才能够正常生效
          responseType: "blob",
          url: "/API/file/photo/report/" + id + "/gif",
          method: "get",
          headers: {
            TokenValue: this.access_token,
          }
        })
        .then((res) => {
          console.log("成功拿到举报单图片！");
          this.image_url = window.URL.createObjectURL(res.data);
          console.log(res);
          this.loading = false;
        })
        .catch((err) => {
          console.log(err);
          console.log("后端请求图片失败");
          this.loading = false;
        });
    },
    getRole (id) {
      if (id[0] == '0' && id[1] == '0')
        return "老人";
      else if (id[0] == '1' && id[1] == '0')
        return "医生";
      else if (id[0] == '1' && id[1] == '1')
        return "护工";
      else if (id[0] == '2' && id[1] == '2')
        return "管理员";
      else
        return "未知角色";
    },
    isAfterToday (bantime)//传入的参数为string类型
    {
      if (typeof (bantime) != 'string' || bantime.length < 9)
        return false;

      //处理当前时间
      let currentDate = new Date();
      let currentYear = currentDate.getFullYear();
      let currentMonth = currentDate.getMonth() + 1;
      let currentDay = currentDate.getDay();

      //处理封禁的截止时间
      let previousYear = parseInt(bantime.substring(0, 4), 10);
      let previousMonth = parseInt(bantime.substring(5, 7), 10);
      let previousDay = parseInt(bantime.substring(8, 10), 10);

      if (currentYear > previousYear)
        return false;
      else if (currentMonth > previousMonth)
        return false;
      else if (currentDay > previousDay)
        return false;

      return true;

    },
    dateToString (currentDate)//返回类型为string
    {
      let currentYear = currentDate.getFullYear();
      let currentMonth = (currentDate.getMonth() + 1);
      let currentDay = (currentDate.getDate());

      if (currentMonth < 10)
        currentMonth = '0' + currentMonth;
      if (currentDay < 10)
        currentDay = '0' + currentDay;
      let str = currentYear + '-' + currentMonth + '-' + currentDay;
      return str;
    },
    onSubmit () {
      //审核单没填完整就直接跳出
      if (this.form.punishType==null || this.form.punishType=='') {
        this.$message({
          type: 'info',
          message: '审核单未填写裁决方式！'
        });
        return false;
      }

      //向后端发送patch申请
      this.form['state'] = 1;
      patchOneReport(this.form)
      .then((res)=>{
        console.log(res)
      })
      .catch((err)=>{
        console.log(err)
      })

      this.$message({
        type:'success',
        message:'审核完成！'
      })
      this.$router.push({path:'/adminExamineUser/index'})

      return true;
    },

    Cancel () {
      this.$message({
        type:'info',
        message:'取消审核'
      })
      this.dialogVisible = false;
      this.$router.push({path:'/adminExamineUser/index'})
    },
    FormIsFull () {
      if (this.form.PUNITIVEMEASURE == '' || this.form.ISREAL == '')
        return false;
      return true;
    },


  },
  mounted: function () {
    //上一个页面传来的参数
    let reportDetail = this.$route.query.res.data.report;
    console.group("详细审核页面的res")
    console.log(reportDetail)
    console.log(reportDetail.reportId)
    console.groupEnd("详细审核页面的res")
    this.form = reportDetail;
    for (let i in this.form) {
      if (this.form[i] == '' || this.form[i] == null)
        this.form[i] = "未知";
    }
    // this.getPhoto(this.form.ID);

    //根据用户ID,请求用户详细信息

  }
};
</script>

// lang选择less语法，scoped限制该样式只在本文件使用，不影响其他组件
<style lang="less" scoped>
.myimage {
  width: 600px;
  height: 400px;
}
</style>


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

    <!--活动信息-->
    <div>
      <el-descriptions
        title="活动详情"
        :column="2"
        border
      >
        <el-descriptions-item label="举报单ID">{{form.reportId}}
        </el-descriptions-item>
        <el-descriptions-item label="举报人ID">{{form.informerId}}
        </el-descriptions-item>
        <el-descriptions-item label="活动ID">{{form.activityId}}
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
  name: "DetailActivityExamine",
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
          label: '封禁活动'
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
      if (!this.FormIsFull()) {
        this.$message({
          type: 'info',
          message: '审核单未填写完整！'
        });
        return false;
      }

      //填写完整审核日期和审核状态
      this.form.ISDONE = (this.form.ISREAL == "是") ? "审核通过" : "审核不通过";
      const today = new Date();
      this.form.AUDITTIME = this.dateToString(today);
      this.toHisObj();

      //向后端发送put申请
      putReportCard(this.form.ID, this.his_obj)
        .then((res) => {
          console.log(res);
          this.$message({
            type: 'success',
            message: '审核单提交成功！审核结果：' + this.form.ISDONE
          });
        })
        .catch((err) => {
          if (err.message == "Request failed with status code 403" && this.flag) {
            this.flag = false;
            AccessTokenFailed();
          }
          console.log(err);
          this.$message({
            type: 'info',
            message: '审核单提交失败'
          });
          return false;
        });

      //put更改封号天数
      if (this.form.PUNITIVEMEASURE != '0') {
        let num = parseInt(this.form.PUNITIVEMEASURE);
        if (this.getRole(this.form.DEFENDANTID) == "医生") {
          let uid = this.form.DEFENDANTID;
          let doctorCard = {};
          //先获取医生原本信息
          getDoctorCard(uid)
            .then((res) => {
              doctorCard = res.data[0];
              console.log("获取医生信息成功！");

              //然后修改该用户的bantime（修改可覆盖，避免恶意多次举报）
              var tempDate = new Date() // 获取今天的日期
              tempDate.setDate(tempDate.getDate() + num) // 今天的前N天的日期，N自定义
              let dateString = this.dateToString(tempDate);
              doctorCard.BANTIME = dateString;

              doctorCard = this.toHisDoctorObj(doctorCard);
              console.log(doctorCard);

              //PUT修改后端数据，完成封号
              putDoctorDisable(uid, doctorCard)
                .then((res) => {
                  console.log("医生封号的PUT请求成功！");
                  console.log(res);
                })
                .catch((err) => {
                  if (err.message == "Request failed with status code 403" && this.flag) {
                    this.flag = false;
                    AccessTokenFailed();
                  }
                  console.log("医生封号失败！");
                  console.log(err);
                })
            })
            .catch((err) => {
              if (err.message == "Request failed with status code 403" && this.flag) {
                this.flag = false;
                AccessTokenFailed();
              }
              console.log("获取医生原本信息失败！");
              console.log(err);
            })

        }
        else if (this.getRole(this.form.DEFENDANTID) == "护工") {
          let uid = this.form.DEFENDANTID;
          let nurseCard = {};
          //先获取护工原本信息
          getNurseCard(uid)
            .then((res) => {
              nurseCard = res.data.message[0];

              //然后修改该用户的bantime（修改可覆盖，避免恶意多次举报）
              var tempDate = new Date() // 获取今天的日期
              tempDate.setDate(tempDate.getDate() + num) // 今天的前N天的日期，N自定义
              let dateString = this.dateToString(tempDate);
              nurseCard.bantime = dateString;

              nurseCard = this.toHisNurseObj(nurseCard);
              //PUT修改后端数据，完成封号
              putNurseDisable(uid, nurseCard)
                .then((res) => {
                  console.log("护工封号的PUT请求成功！");
                  console.log(res);
                })
                .catch((err) => {
                  if (err.message == "Request failed with status code 403" && this.flag) {
                    this.flag = false;
                    AccessTokenFailed();
                  }
                  console.log("护工封号的PUT请求失败！");
                  console.log(err);
                })
            })
            .catch((err) => {
              if (err.message == "Request failed with status code 403" && this.flag) {
                this.flag = false;
                AccessTokenFailed();
              }
              console.log(err);
            })

        }
      }

      this.$router.push({
        name: "ReportScan",
      })

      return true;
    },

    Cancel () {
      this.dialogVisible = false;
      this.$router.push({
        name: "ReportScan",
      })
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

    //根据活动ID，请求活动详情
    
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
